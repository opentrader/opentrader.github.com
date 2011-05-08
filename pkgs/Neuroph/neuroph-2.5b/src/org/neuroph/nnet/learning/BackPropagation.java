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

import java.util.List;
import org.neuroph.core.Connection;
import org.neuroph.core.Layer;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.Neuron;
import org.neuroph.core.transfer.TransferFunction;

/**
 * Back Propagation learning rule for Multi Layer Perceptron neural networks.
 * 
 * @author Zoran Sevarac <sevarac@gmail.com> 
 * 
 */
public class BackPropagation extends SigmoidDeltaRule {
	
	/**
	 * The class fingerprint that is set to indicate serialization
	 * compatibility with a previous version of the class.
	 */	
	private static final long serialVersionUID = 1L;

	/**
	 * Creates new instance of BackPropagation learning
	 */
	public BackPropagation() {
		super();
	}


	/**
	 * This method implements weight update procedure for the whole network
	 * for the specified  error vector
	 * 
	 * @param patternError
	 *            single pattern error vector
	 */
	@Override
	protected void updateNetworkWeights(double[] patternError) {
		this.adjustOutputNeurons(patternError);
		this.adjustHiddenLayers();
	}

	/**
	 * This method implements weights adjustment for the hidden layers
	 */
	protected void adjustHiddenLayers() {
		int layerNum = this.neuralNetwork.getLayersCount();

		for (int i = layerNum - 2; i > 0; i--) {
			Layer layer = neuralNetwork.getLayerAt(i);
			
			for(Neuron neuron : layer.getNeurons()) {	
				double delta = this.calculateDelta(neuron);
				neuron.setError(delta);
				this.updateNeuronWeights(neuron);
			} // for
		} // for
	}

	/**
	 * Calculates and returns delta parameter (neuron error) for the specified
	 * neuron
	 * 
	 * @param neuron
	 *            neuron to calculate error for
	 * @return delta (neuron error) for the specified neuron
	 */
	private double calculateDelta(Neuron neuron) {
		List<Connection> connectedTo = ((Neuron) neuron).getOutConnections();

		double delta_sum = 0d;
		for(Connection connection : connectedTo) {	
			double d = connection.getToNeuron().getError()
					* connection.getWeight().getValue();
			delta_sum += d; // weighted sum from the next layer
		} // for

		TransferFunction transferFunction = neuron.getTransferFunction();
		double netInput = neuron.getNetInput();
		double f1 = transferFunction.getDerivative(netInput);
		double delta = f1 * delta_sum;
		return delta;
	}

}
