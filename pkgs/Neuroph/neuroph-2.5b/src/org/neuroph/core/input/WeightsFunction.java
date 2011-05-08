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

import java.util.List;

import org.neuroph.core.Connection;

/**
 * Abstract base class for all weights functions, which perform some operation on
 * neuron's input vector and weights vector and return vector.
 * WeightsFunction is subcomponents of InputFunction.
 * @see org.neuroph.core.input.InputFunction
 * @author Zoran Sevarac <sevarac@gmail.com>
 */
abstract public class WeightsFunction {

	/**
	 * Returns function's output
	 * 
	 * @param inputs
	 *            neuron's input connections
	 * @return function's output
	 */
	abstract public double[] getOutput(List<Connection> inputConnections);

        /**
         * 
         * @param inputs
         * @param weights
         * @return
         */
	abstract public double[] getOutput(double[] inputs, double[] weights);

	
	@Override
	public String toString() {
		return getClass().getName();
	}	

}
