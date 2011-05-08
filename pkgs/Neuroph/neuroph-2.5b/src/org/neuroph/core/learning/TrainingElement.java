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

package org.neuroph.core.learning;

import java.io.Serializable;
import java.util.Vector;

import org.encog.engine.data.EngineData;
import org.neuroph.util.VectorParser;

/**
 * Represents single training element for neural network learning. This class
 * contains only network input and it is used for unsupervised learning
 * algorithms. It is also the base class for SupervisedTrainingElement.
 * Implementation of EngineData interface is added to provide compatibility with
 * Encog data sets and with high speed FlatNetwork
 * 
 * @author Zoran Sevarac <sevarac@gmail.com>
 */
public class TrainingElement implements Serializable, EngineData {

	/**
	 * The class fingerprint that is set to indicate serialization compatibility
	 * with a previous version of the class
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Input vector for this training element
	 */
	protected double[] input;

	/**
	 * Label for this training element
	 */
	protected String label;

	/**
	 * Creates new empty training element
	 */
	public TrainingElement() {

	}

	/**
	 * Creates new training element with specified input vector
	 * 
	 * @param input
	 *            input vector
	 */
	public TrainingElement(Vector<Double> inputVector) {
		this.input = VectorParser.toDoubleArray(inputVector);
	}

	/**
	 * Creates new training element with specified input vector
	 * 
	 * @param input
	 */
	public TrainingElement(String input) {
		this.input = VectorParser.parseDoubleArray(input);
	}

	/**
	 * Creates new training element with input array
	 * 
	 * @param input
	 *            input array
	 */
	public TrainingElement(double... input) {
		this.input = input;
	}

	/**
	 * Returns input vector
	 * 
	 * @return input vector
	 */
	public double[] getInput() {
		return this.input;
	}

	/**
	 * Sets input vector
	 * 
	 * @param input
	 *            input vector
	 */
	public void setInput(double[] input) {
		this.input = input;
	}

	/**
	 * Get training element label
	 * 
	 * @return training element label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Set training element label
	 * 
	 * @param label
	 *            label for this training element
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Method added for Encog-Engine compatibility.
	 * 
	 * @return True if this is a supervised training element, will always return
	 *         false, as this class is always used for unsupervised training.
	 */
	@Override
	public boolean isSupervised() {
		return false;
	}

	/**
	 * @return The internal ideal array. Necessary for Encog-Engine integration.
	 */
	@Override
	public double[] getIdealArray() {
		return null;
	}

	/**
	 * @return The internal input array. Necessary for Encog-Engine integration.
	 */
	@Override
	public double[] getInputArray() {
		return this.input;
	}

	/**
	 * Allows the internal ideal array to be set. Necessary for Encog-Engine
	 * integration.
	 * 
	 * @param data
	 *            The array to set.
	 */
	@Override
	public void setIdealArray(double[] data) {

	}

	/**
	 * Allows the internal input array to be set. Necessary for Encog-Engine
	 * integration.
	 * 
	 * @param data
	 *            The array to set.
	 */
	@Override
	public void setInputArray(double[] data) {
		this.input = data;
	}

}