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
 *<pre>
 * Neuron's input function. It has two subcomponents:
 * 
 * weightsFunction - which performs operation with input and weight vector
 * summingFunction - which performs operation with the resulting vector from weightsFunction
 * 
 * InputFunction implements the following behaviour:
 * output = summingFunction(weightsFunction(inputs))
 * 
 * Different neuron input functions can be created by setting different weights and summing functions.
 *</pre>
 * 
 * @author Zoran Sevarac <sevarac@gmail.com>
 * @see WeightsFunction
 * @see SummingFunction
 * @see org.neuroph.core.Neuron
 */
public class InputFunction implements Serializable {
	
	/**
	 * The class fingerprint that is set to indicate serialization
	 * compatibility with a previous version of the class.
	 */	
	private static final long serialVersionUID = 2L;
	
	/**
	 * Weights function component of the input function. It performs some
	 * operation with weights and input vector, and ouputs vector.
	 */
	private WeightsFunction weightsFunction;
	
	/**
	 * Summing function component of the input function. It performs some 
	 * summing operation on output vector from weightsFunction and outputs scalar.
	 */
	private SummingFunction summingFunction;

	/**
	 * Creates an instance of WeightedSum input function by default.
	 */
	public InputFunction() {
		this.weightsFunction = new WeightedInput();
		this.summingFunction = new Sum();
	}

	/**
	 * Creates an instance of input function with specified weights and summing function
	 * 
	 * @param weightsFunction
	 *            vector function performs some operation on input and weight
	 *            vector
	 * @param summingFunction
	 *            scalar function transforms output from VectorFunction to
	 *            scalar
	 */
	public InputFunction(WeightsFunction weightsFunction, SummingFunction summingFunction) {
		this.weightsFunction = weightsFunction;
		this.summingFunction = summingFunction;
	}

	/**
	 * Returns ouput value of this input function for the given neuron inputs
	 * 
	 * @param inputConnections
	 *            neuron's input connections
	 * @return input total net input
	 */
	public double getOutput(List<Connection> inputConnections) {
		double[] inputVector = this.weightsFunction.getOutput(inputConnections);
		double output = this.summingFunction.getOutput(inputVector);

		return output;
	}

	/**
	 * Returns summing function component of this InputFunction
	 * 
	 * @return summing function
	 */
	public SummingFunction getSummingFunction() {
		return summingFunction;
	}

	/**
	 * Returns weights functioncomponent of this InputFunction
	 * 
	 * @return weights function
	 */
	public WeightsFunction getWeightsFunction() {
		return weightsFunction;
	}

}
