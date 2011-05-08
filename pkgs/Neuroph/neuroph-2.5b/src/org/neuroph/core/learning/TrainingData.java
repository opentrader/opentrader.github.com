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

/**
 * This class holds training data buffer and defines buffer index positions
 * Training data buffer holds various values which are used for calculating weight
 * adjustments during training.
 *
 * @author Zoran Sevarac <sevarac@gmail.com>
 */
public class TrainingData implements Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Data buffer which actually stores training data. Each position witin
     * this array
     */
    private double[] trainingDataBuffer;

    /**
     * This is idx position for delta weight sum.
     * Delta weight sum is used for batch mode training.
     */
    public static final int DELTA_WEIGHT_SUM = 0;

    /**
     * This is idx position for orevious weight value .
     * Used to calculate momentum
     */
    public static final int PREVIOUS_WEIGHT = 1;

    /**
     * Constructor initializes buffer to specified capacity
     * @param capacity
     */
    public TrainingData(int capacity) {
        this.trainingDataBuffer = new double[capacity];
    }

    /**
     * Gets specified training data (at specified position)
     * @param idx training data idx position
     * @return training data value at specified idx position
     */
    public double get(int idx) {
        return this.trainingDataBuffer[idx];
    }

    /**
     * Sets specified training data value
     * @param idx training data position
     * @param val training data value
     */
    public void set(int idx, double val) {
        this.trainingDataBuffer[idx] = val;
    }
}
