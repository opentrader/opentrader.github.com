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
package org.neuroph.nnet.learning;

import org.neuroph.core.learning.TrainingData;
import java.io.Serializable;
import java.util.HashMap;
import org.neuroph.core.Connection;
import org.neuroph.core.Layer;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.Neuron;
import org.neuroph.core.Weight;
import org.neuroph.core.learning.SupervisedLearning;
import org.neuroph.core.learning.TrainingSet;

/**
 * LMS learning rule for neural networks.
 * 
 * @author Zoran Sevarac <sevarac@gmail.com>
 */
public class LMS extends SupervisedLearning implements Serializable {

    /**
     * The class fingerprint that is set to indicate serialization
     * compatibility with a previous version of the class.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Setting to determine if learning (weights update) is in batch mode
     * False by default.
     */
    private boolean batchMode = false;

    /**
     * Training data buffer is used to store various values during the training.
     * This value determines the buffer capacity.
     */
    protected int trainingDataBufferSize = 2;

    
    /**
     * Creates new LMS learning rule
     */
    public LMS() {
        super();
    }

    /**
     * Calculates and updates sum of squared errors for single pattern, and updates total sum of squared pattern errors
     *
     * @param patternError
     *            single pattern error vector
     */
    // see: http://www.vni.com/products/imsl/documentation/CNL06/stat/NetHelp/default.htm?turl=multilayerfeedforwardneuralnetworks.htm
    protected void updatePatternError(double[] patternErrorVector) {
       this.patternErrorSqrSum = 0;
        for (double error : patternErrorVector) {
            this.patternErrorSqrSum += (error * error) * 0.5;
        }

        this.totalPatternErrorSqrSum += this.patternErrorSqrSum;
    }

    @Override
    protected void updateTotalNetworkError() {
        this.totalNetworkError = totalPatternErrorSqrSum /(this.getTrainingSet().size());
    }

    /**
     * This method implements weight update procedure for the whole network for
     * this learning rule
     *
     * @param patternError
     *            single pattern error vector
     */
    @Override
    protected void updateNetworkWeights(double[] patternError) {
        int i = 0;
        for (Neuron neuron : neuralNetwork.getOutputNeurons()) {
            neuron.setError(patternError[i]);
            this.updateNeuronWeights(neuron);
            i++;
        }
    }

    /**
     * This method implements weights update procedure for the single neuron
     *
     * @param neuron
     *            neuron to update weights
     */
    protected void updateNeuronWeights(Neuron neuron) {
        // get the error for specified neuron,
        double neuronError = neuron.getError();

        // tanh can be used to minimise the impact of big error values, which can cause network instability
        // suggested at https://sourceforge.net/tracker/?func=detail&atid=1107579&aid=3130561&group_id=238532
        // double neuronError = Math.tanh(neuron.getError());
        
        // iterate through all neuron's input connections
        for (Connection connection : neuron.getInputConnections()) {
            // get the input from current connection
            double input = connection.getInput();
            // calculate the weight change
            double deltaWeight = this.learningRate * neuronError * input;
            // update the weight change
            this.applyWeightChange(connection.getWeight(), deltaWeight);
        }
    }

    /**
     * Returns true if learning is performed in batch mode, false otherwise
     * @return true if learning is performed in batch mode, false otherwise
     */
    public boolean isBatchMode() {
        return batchMode;
    }

    /**
     * Sets batch mode on/off (true/false)
     * @param batchMode batch mode setting
     */
    public void setBatchMode(boolean batchMode) {
        this.batchMode = batchMode;
    }

    /**
     * This method does one learning epoch (one pass through training set)
     * and after that does weight update if learning is in batch mode
     * @param trainingSet
     */
    @Override
    public void doLearningEpoch(TrainingSet trainingSet) {
        super.doLearningEpoch(trainingSet);

        if (this.batchMode == true) {
            // if learning is performed in batch mode, also apply accumulated weight changes from this epoch
            batchModeWeightsUpdate();
        }
    }

    /**
     * This methods first checks to see if learning is performed in online or batch mode.
     * If learning is in online  mode weight change is applied immediately.
     * If learning is in batch mode all weight changes during one epoch  are summed
     * in trainingData buffer, and that sum is applied after each epoch.
     * @param weight weight that should be changed
     * @param deltaWeight weight change
     */
    protected void applyWeightChange(Weight weight, double deltaWeight) {
        if (this.batchMode == false) {
            // if not in batch mode apply the weight change immediately
            weight.inc(deltaWeight);
        } else {
            // accumulate Weight change if its in batch mode
            double deltaWeightSum = weight.getTrainingData().get(TrainingData.DELTA_WEIGHT_SUM) + deltaWeight;
            weight.getTrainingData().set(TrainingData.DELTA_WEIGHT_SUM, deltaWeightSum);
        }
    }

    /**
     * This method updates network weights in batch mode - use accumulated weights change stored in trainingData
     * buffers to update all weights in network. It is executed after each epoch if learning is in batch mode.
     */
    protected void batchModeWeightsUpdate() {
        // iterate layers from output to input
        for (int i = neuralNetwork.getLayersCount() - 1; i > 0; i--) {
            Layer layer = neuralNetwork.getLayers().get(i);
            // iterate neurons at each layer
            for (Neuron neuron : layer.getNeurons()) {
                // iterate connections/weights for each neuron
                for (Connection connection : neuron.getInputConnections()) {
                    // for each connection weight apply accumulated weight change
                    Weight weight = connection.getWeight();
                    // get deltaWeightSum
                    double deltaWeightSum = weight.getTrainingData().get(TrainingData.DELTA_WEIGHT_SUM);
                    // apply the deltaWeightSum
                    weight.inc(deltaWeightSum);
                    // reset the deltaWeightSum to prepare it for next epoch
                    weight.getTrainingData().set(TrainingData.DELTA_WEIGHT_SUM, 0);
                }
            }
        }
    }

    @Override
    public void setNeuralNetwork(NeuralNetwork neuralNetwork) {
        super.setNeuralNetwork(neuralNetwork);
        this.initTrainingDataBuffer();
    }

    /**
     * This method initializes training data buffers in all network weights.
     * It can be overridden to create bigger training data buffer for each weight.
     */
    protected void initTrainingDataBuffer() {
        for (int i = neuralNetwork.getLayersCount() - 1; i > 0; i--) {
            Layer layer = neuralNetwork.getLayers().get(i);
            for (Neuron neuron : layer.getNeurons()) {
                for (Connection connection : neuron.getInputConnections()) {
                    Weight weight = connection.getWeight();
                    weight.initTrainingDataBuffer(this.trainingDataBufferSize);
                }
            }
        }
    }

    public int getTrainingDataBufferSize() {
        return trainingDataBufferSize;
    }

    final public void setTrainingDataBufferSize(int trainingDataBufferSize) {
        this.trainingDataBufferSize = trainingDataBufferSize;
    }

    /**
     *
     * @param patternError
     * @deprecated
     */
	@Override
	protected void updateTotalNetworkError(double[] patternError) {
		double sqrErrorSum = 0;
		for (double error : patternError) {
			sqrErrorSum += (error * error);
		}
		this.totalNetworkError += sqrErrorSum / (2 * patternError.length);
	}

  

}
