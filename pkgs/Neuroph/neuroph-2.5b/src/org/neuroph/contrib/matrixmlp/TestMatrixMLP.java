package org.neuroph.contrib.matrixmlp;

import org.neuroph.contrib.matrixmlp.MatrixLayer;
import org.neuroph.contrib.matrixmlp.MatrixMultiLayerPerceptron;
import org.neuroph.core.Layer;
import org.neuroph.core.Neuron;
import org.neuroph.core.learning.SupervisedTrainingElement;
import org.neuroph.core.learning.TrainingSet;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;

/**
 * Test class for matrix based MLP implementation.
 * @author Zoran Sevarac
 */
public class TestMatrixMLP {

    /**
     * Create and run MLP with XOR training set
     */
    public static void main(String[] args) {
        // create training set (logical XOR function)
        TrainingSet trainingSet = new TrainingSet(2, 1);
        trainingSet.addElement(new SupervisedTrainingElement(new double[]{0, 0}, new double[]{0}));
        trainingSet.addElement(new SupervisedTrainingElement(new double[]{0, 1}, new double[]{1}));
        trainingSet.addElement(new SupervisedTrainingElement(new double[]{1, 0}, new double[]{1}));
        trainingSet.addElement(new SupervisedTrainingElement(new double[]{1, 1}, new double[]{0}));

        MultiLayerPerceptron nnet = new MultiLayerPerceptron( TransferFunctionType.TANH ,2, 3, 1);
        MatrixMultiLayerPerceptron mnet = new MatrixMultiLayerPerceptron(nnet);

        System.out.println("Training network...");

        mnet.learnInSameThread(trainingSet);

        System.out.println("Done training network.");
    }


}
