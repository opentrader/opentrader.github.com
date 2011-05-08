package org.neuroph.contrib.matrixmlp;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.transfer.TransferFunction;
import org.neuroph.nnet.learning.MomentumBackpropagation;

/**
 * Momentum Backpropagation for matrix based MLP
 * @author Zoran Sevarac
 */
public class MatrixMomentumBackpropagation extends MomentumBackpropagation {

        private MatrixMultiLayerPerceptron matrixMlp;
        private MatrixLayer[] matrixLayers;

        @Override
        public void setNeuralNetwork(NeuralNetwork neuralNetwork) {
            super.setNeuralNetwork(neuralNetwork);
            this.matrixMlp = (MatrixMultiLayerPerceptron)this.getNeuralNetwork();
            matrixLayers = matrixMlp.getMatrixLayers();
        }

        /**
	 * This method implements weights update procedure for the output neurons
	 *
	 * @param patternErrors
	 *            single pattern error vector
	 */
        @Override
	protected void adjustOutputNeurons(double[] patternError) {
            
                // get output layer
                MatrixMlpLayer outputLayer = (MatrixMlpLayer)matrixLayers[matrixLayers.length - 1];
                TransferFunction transferFunction = outputLayer.getTransferFunction();

                // get output vector
                double[] outputs = outputLayer.getOutputs();
                double[] netInputs = outputLayer.getNetInput();
                double[] neuronErrors = outputLayer.getErrors(); // these will hold  -should be set from here!!!!

                // calculate errors(deltas) for all output neurons
                for(int i = 0; i < outputs.length; i++) {
                    neuronErrors[i] = patternError[i] * transferFunction.getDerivative(netInputs[i]); // ovde mi treba weighted sum, da ne bi morao ponovo da racunam
                }

                // update weights
                this.updateLayerWeights(outputLayer, neuronErrors);
        }


        // http://netbeans.org/kb/docs/java/profiler-profilingpoints.html
        protected void updateLayerWeights(MatrixMlpLayer layer, double[] errors) {

            double[] inputs = layer.getInputs();
            double[][] weights = layer.getWeights();
            double[][] deltaWeights = layer.getDeltaWeights();

                for(int neuronIdx = 0; neuronIdx < layer.getNeuronsCount(); neuronIdx++) { // iterate neurons
                  for(int weightIdx = 0; weightIdx < weights[neuronIdx].length; weightIdx++ ) { // iterate weights
                      // calculate weight change
                      double deltaWeight = this.learningRate * errors[neuronIdx] * inputs[weightIdx] +
                                       momentum * (deltaWeights[neuronIdx][weightIdx]);

                      deltaWeights[neuronIdx][weightIdx] = deltaWeight; // save weight change to calculate momentum
                      weights[neuronIdx][weightIdx] += deltaWeight; // apply weight change

                  }
                }
        }


        /**
         * backpropogate errors through all hidden layers and update conneciion weights
         * for those layers.
         */
        @Override
        protected void adjustHiddenLayers()
        {
            int layersCount = matrixMlp.getLayersCount();
            
            for ( int layerIdx = layersCount -2 ; layerIdx > 0 ; layerIdx--) {
               MatrixMlpLayer currentLayer = (MatrixMlpLayer)matrixLayers[layerIdx];

               TransferFunction transferFunction = currentLayer.getTransferFunction();
               int neuronsCount = currentLayer.getNeuronsCount();

               double[] neuronErrors = currentLayer.getErrors();
               double[] netInputs = currentLayer.getNetInput();

               MatrixMlpLayer nextLayer = (MatrixMlpLayer)currentLayer.getNextLayer();
               double[] nextLayerErrors = nextLayer.getErrors();
               double[][] nextLayerWeights = nextLayer.getWeights();

               // calculate error for each neuron in current layer
               for(int neuronIdx = 0; neuronIdx < neuronsCount; neuronIdx++) {
                   // calculate weighted sum of errors of all neuron it is attached to - calculate how much this neuron is contributing to errors in next layer
                   double weightedErrorsSum = 0;

                   for(int nextLayerNeuronIdx = 0; nextLayerNeuronIdx < nextLayer.getNeuronsCount(); nextLayerNeuronIdx++) {
                     weightedErrorsSum += nextLayerErrors[nextLayerNeuronIdx] * nextLayerWeights[nextLayerNeuronIdx][neuronIdx];
                   }

                   // calculate the error for this neuron
                   neuronErrors[neuronIdx] = transferFunction.getDerivative(netInputs[neuronIdx]) * weightedErrorsSum;
               } // neuron iterator

               this.updateLayerWeights(currentLayer, neuronErrors);
               
            } // layer iterator
        }
}