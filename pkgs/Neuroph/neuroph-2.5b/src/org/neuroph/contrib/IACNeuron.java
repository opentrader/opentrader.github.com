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

import org.neuroph.core.Neuron;

/**
 * Neuron for Interactive Activation Neural Network.
 * @see org.neuroph.contrib.IACNetwork
 * @author Zoran Sevarac <sevarac@gmail.com>
 */
public class IACNeuron extends Neuron {

	/**
	 * The class fingerprint that is set to indicate serialization
	 * compatibility with a previous version of the class.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Flag which is set true if neuron input is externaly set
	 */
	private boolean externalInputSet;


    public IACNeuron() {
        this.setTransferFunction(new IACFunction());
    }

	/**
	 * Sets total net input for this cell
	 *
	 * @param input
	 *            input value
	 */
	@Override
	public void setInput(double input) {
		this.netInput = input;
		this.externalInputSet = true;
	}

	/**
	 * Calculates neuron output
	 */
	@Override
	public void calculate() {
		if (!externalInputSet) { // ako ulaz nije setovan spolja
				netInput = inputFunction.getOutput(this.inputConnections);
		}

		// calculate neuron output
		this.output = ((IACFunction)transferFunction).getOutput(this.netInput, this.output);

		if (externalInputSet) { // ulaz setovan 'spolja' vazi samo za jedno izracunavanje
			externalInputSet = false;
			netInput = 0;
		}
	}

}
