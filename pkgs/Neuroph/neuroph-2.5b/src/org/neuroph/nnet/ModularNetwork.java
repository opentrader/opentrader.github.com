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

package org.neuroph.nnet;

import java.util.HashMap;
import java.util.Map;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.util.plugins.LabelsPlugin;

/**
 * Neural networks container for creating modular neural networks
 * still under development - not finished
 * @author Zoran Sevarac <sevarac@gmail.com>
 */
public class ModularNetwork {

	/**
	 * Neural networks collection
	 */
	private Map<String, NeuralNetwork> neuralNets = new HashMap<String, NeuralNetwork>();
	
	// add neural connectors collection  which will be used to interconnect neural networks
	// neural connector could be collection of connection objects, put it in nnet.comp package
	// also it may be collection of linear neurons which will just pass signals, like patch panel
	// that way the networks will not be coupled
	
	public ModularNetwork() {
		
	}

        /**
         * Adds neural network
         * @param neuralNet
         */
	public void addNetwork(NeuralNetwork neuralNet) {
		LabelsPlugin labelsPlugin = ((LabelsPlugin)neuralNet.getPlugin("LabelsPlugin"));
		String label = labelsPlugin.getLabel(neuralNet);
		neuralNets.put(label, neuralNet);
	}

        /**
         * Gets neural network
         * @param label
         * @return neural network
         */
	public NeuralNetwork getNetwork(String label) {
		return neuralNets.get(label);
	}

        /**
         * Removes neural network
         * @param label
         */
	public void removeNetwork(String label) {
		// maybe we need to remove connections to other networks
		neuralNets.remove(label);
	}
	
	// calculate(what to calculate - labels sequence)
	
	
	
}
