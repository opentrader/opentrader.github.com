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

package org.neuroph.core;

import org.neuroph.core.learning.TrainingData;

/**
 * Neuron connection weight.
 * 
 * @see Connection
 * @author Zoran Sevarac <sevarac@gmail.com>
 */
public class Weight implements java.io.Serializable {
	/**
	 * The class fingerprint that is set to indicate serialization 
	 * compatibility with a previous version of the class
	 */	
	private static final long serialVersionUID = 1L;


        /**
         * Training data buffer holds various algorithm specific data which is
         * used for adjusting this weight value during training
         */
        private transient TrainingData trainingData;
	
	/**
	 * Weight value
	 */
	private double value;
	

	/**
	 * Creates an instance of connection weight with random weight value in range [0..1]
	 */
	public Weight() {
		this.value = Math.random() - 0.5d;
                this.trainingData = new TrainingData(2);
	}

	/**
	 * Creates an instance of connection weight with the specified weight value
	 * 
	 * @param value
	 *            weight value
	 */
	public Weight(double value) {
		this.value = value;
                 this.trainingData = new TrainingData(2);
	}

        /**
         * Initialize training data buffer to specified size
         * @param size size of the training buffer (number of variables used by
         * the learning rule)
         */
        public void initTrainingDataBuffer(int size) {
            this.trainingData = new TrainingData(size);
        }

	/**
	 * Increases the weight for the specified amount
	 * 
	 * @param amount
	 *            amount to add to current weight value
	 */
	public void inc(double amount) {
		this.value += amount;
	}

	/**
	 * Decreases the weight for specified amount
	 * 
	 * @param amount
	 *            amount to subtract from the current weight value
	 */
	public void dec(double amount) {
		this.value -= amount;
	}

	/**
	 * Sets the weight value
	 * 
	 * @param value
	 *            weight value to set
	 */
	public void setValue(double value) {
		this.value = value;
	}

	/**
	 * Returns weight value
	 * 
	 * @return value of this weight
	 */
	public double getValue() {
		return this.value;
	}
	
	/**
	 * Returns weight value as String
	 */
	@Override
	public String toString() {
		return Double.valueOf(value).toString();
	}

	/**
	 * Sets random weight value
	 */
	public void randomize() {
		this.value = Math.random() - 0.5d;
                //this.previousValue = this.value;
                this.trainingData.set(TrainingData.PREVIOUS_WEIGHT, this.value); // used for momentum
	}

	/**
	 * Sets random weight value within specified interval
	 */
	public void randomize(double min, double max) {
		this.value = min + Math.random() * (max - min);
               //this.previousValue = this.value;
                this.trainingData.set(TrainingData.PREVIOUS_WEIGHT, this.value); // used for momentum
	}


        /**
         * Returns training data buffer for this weight
         * @return training data buffer for this weight
         */
        public TrainingData getTrainingData() {
            return trainingData;
        }

}
