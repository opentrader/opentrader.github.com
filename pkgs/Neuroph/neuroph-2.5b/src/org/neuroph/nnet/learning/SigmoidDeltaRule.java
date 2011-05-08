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

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.Neuron;
import org.neuroph.core.transfer.TransferFunction;

/**
 * Delta rule learning algorithm for perceptrons with sigmoid (or any other diferentiable continuous) functions.
 *
 * TODO: Rename to DeltaRuleContinuous (ContinuousDeltaRule) or something like that, but that will break backward compatibility,
 * posibly with backpropagation which is the most used
 *
 * @see LMS
 * @author Zoran Sevarac <sevarac@gmail.com>
 */
public class SigmoidDeltaRule extends LMS {

	/**
	 * The class fingerprint that is set to indicate serialization
	 * compatibility with a previous version of the class.
	 */	
	private static final long serialVersionUID = 1L;

	/**
	 * Creates new SigmoidDeltaRule
	 */
	public SigmoidDeltaRule() {
		super();
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
		this.adjustOutputNeurons(patternError);
	}

	/**
	 * This method implements weights update procedure for the output neurons
	 * 
	 * @param patternError
	 *            single pattern error vector
	 */
	protected void adjustOutputNeurons(double[] patternError) {
		int i = 0;
		for(Neuron neuron : neuralNetwork.getOutputNeurons()) {
			double outputError = patternError[i];
			if (outputError == 0) {
				neuron.setError(0);
                                i++;
				continue;
			}
			
			TransferFunction transferFunction = neuron.getTransferFunction();
			double neuronInput = neuron.getNetInput();
			double delta = outputError * transferFunction.getDerivative(neuronInput);
			neuron.setError(delta);
			this.updateNeuronWeights(neuron);				
			i++;
		} // for				
	}

}
