/**
 * Copyright 2010 Neuroph Project http://neuroph.sourceforge.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.neuroph.contrib;

import org.neuroph.core.transfer.TransferFunction;

/**
 * Transfer function for Neuron in Interactive Activation Neural Network.
 * @see org.neuroph.contrib.IACNetwork
 * @see org.neuroph.contrib.IACNeuron
 * @author Zoran Sevarac <sevarac@gmail.com>
 * http://www.itee.uq.edu.au/~cogs2010/cmc/chapters/IAC/index2.html#Mechanism
 * If the activation of a unit is equal to max then the net believes the hypothesis completely. 
 * If it is equal to min then the net disbelieves the hypothesis completely.
 * The rest corresponds to an "I don't know state". The (max - a i) or (a i - min)
 * terms ensure that the activation remains between min and max and doesn't continue to either grow
 * or shrink without bound [2]. The -decay (ai - rest) part of the equation forces the activation to return
 * to the rest value in the absence of external input.
 */
public class IACFunction extends TransferFunction {
	private static final long serialVersionUID = 1L;
	
	double max = 1,
            min = -0.2,
            rest = -0.1,
            decay = 0.1;

    public double getOutput(double netInput, double output) {
        double delta = 0;
        
        if (netInput>0) 
            delta = (max - output)*netInput - decay*(output - rest);
        else
            delta = (output - min)*netInput - decay*(output - rest);

        output = output + delta;

        return output;
    }

    public double getOutput(double netInput) {

        throw new RuntimeException("Method getOutput(double netInput) not implemented for this type of TransferFunctions. Use getOutput(double netInput, double output)");
    }

}
