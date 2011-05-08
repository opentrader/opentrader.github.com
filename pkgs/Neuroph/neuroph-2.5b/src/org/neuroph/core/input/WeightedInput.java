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

package org.neuroph.core.input;

import java.io.Serializable;
import java.util.List;

import org.neuroph.core.Connection;

/**
 * Calculates weighted input for neuron's InputFunction.
 * 
 * @author Zoran Sevarac <sevarac@gmail.com>
 */
public class WeightedInput extends WeightsFunction implements Serializable {
	
	/**
	 * The class fingerprint that is set to indicate serialization
	 * compatibility with a previous version of the class.
	 */	
	private static final long serialVersionUID = 1L;
	

	/**
	 * Returns weighted input vector.
	 * 
	 * @param inputConnections
	 *            Reference to neuron's input connections array.
	 * @return weighted input vector
	 */
	public double[] getOutput(List<Connection> inputConnections) {
		double[] outputVector = new double[inputConnections.size()];

		int i = 0;
		for(Connection connection : inputConnections) {
			outputVector[i] = connection.getWeightedInput();
			i++;
		}
                
		return outputVector;
	}

	public double[] getOutput(double[] inputs, double[] weights) {
		double[] output = new double[inputs.length];

		for(int i = 0; i < inputs.length; i++) {
			output[i] = inputs[i] * weights[i];
		}

		return output;
	}

}
