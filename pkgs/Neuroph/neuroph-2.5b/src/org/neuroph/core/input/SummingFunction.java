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

/**
 * Abstract base class for all summing functions, which perform some summing
 * operation on weighted input vector and return scalar.
 * SummingFunctions is subcomponents of InputFunction.
 * @see org.neuroph.core.input.InputFunction
 * @author Zoran Sevarac <sevarac@gmail.com>
 */

abstract public class SummingFunction {

	/**
	 * Returns summing function output
	 * 
	 * @param inputVector
	 *            input vector for summing function
	 * @return summing function output
	 */
	abstract public double getOutput(double[] inputVector);

	@Override
	public String toString() {
		return getClass().getName();
	}	
}
