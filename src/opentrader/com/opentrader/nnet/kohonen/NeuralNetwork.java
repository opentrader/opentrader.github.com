/*
 * OpenTrader Trading Platform
 * The solution for online trading, technical analysis and automated trading.
 *
 * Copyright (C) 2011  Andrey Pudov
 * Andrey Pudov     <syscreat@gmail.com>
 *
 * http://opentrader.github.com/
 */

/*
 * CDDL HEADER START
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2011 Andrey Pudov. All rights reserved.
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License (the "License").
 * You may not use this file except in compliance with the License.
 *
 * You can obtain a copy of the license at usr/src/OPENSOLARIS.LICENSE
 * or http://www.opensolaris.org/os/licensing.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL HEADER in each
 * file and include the License file at usr/src/OPENSOLARIS.LICENSE.
 * If applicable, add the following below this CDDL HEADER, with the
 * fields enclosed by brackets "[]" replaced with your own identifying
 * information: Portions Copyright [yyyy] [name of copyright owner]
 *
 * CDDL HEADER END
 *
 *
 * Copyright 2011 Andrey Pudov.  All rights reserved.
 * Use is subject to license terms.
 *
 * Contributor(s):
 *
 * Portions Copyrighted 2011 Andrey Pudov.
 *
 */

package com.opentrader.nnet.kohonen;

/**
 *  @author    Andrey Pudov        <syscreat@gmail.com>
 *  @version   0.00.00
 *  %name      NeuralNetwork.java
 *  %pkg       com.opentrader.nnet.kohonen
 *  %date      12:32:47 PM, Apr 23, 2011
 */
public class NeuralNetwork {
    
    private static final java.util.logging.Logger LOG 
            = java.util.logging.Logger.getLogger("opentrader");
    
    private double[][]  inputVector;
    private double[][]  weights;
    private double[]    euclideanDistance;
    private int         numberOfCluster;
    private int         inputDimension;
    private int         numberOfInput;
    private double      learnRate;
    private int         winningNeuron;
    private int         maxIteration;
    
    public NeuralNetwork() {
        numberOfCluster = 2;
        learnRate       = 0.6;
        maxIteration    = 50;
    }
    
    private double learningRateDecay(double currentLearningRate) {
        return 0.8 * currentLearningRate;
    }
    
    private void initializeWeigths() {
        weights = new double[numberOfCluster][inputDimension];
        
        for (int i = 0; i < numberOfCluster; ++i) {
            for (int j = 0; j < inputDimension; ++j) {
                weights[i][j] = new java.util.Random().nextDouble();
            }
        }
    }
    
    private double computeEuclideanDistance(double[] vector1, double[] vector2) {
        double distance = 0;
        
        for (int j = 0; j < inputDimension; ++j) {
            distance += Math.pow((vector1[j] - vector2[j]), 2);
        }
        
        return distance;
    }
    
    private void train() {
        euclideanDistance = new double[numberOfCluster];
        
        for (int iter = 0; iter < maxIteration; ++iter) {
            for (int k = 0; k < numberOfInput; ++k) {
                /* get the winning neuron */
                winningNeuron = 0;
                for (int i = 0; i < numberOfCluster; ++i) {
                    euclideanDistance[i] = computeEuclideanDistance(
                            weights[i], inputVector[k]);
                    if (i != 0) {
                        if (euclideanDistance[i] 
                                < euclideanDistance[winningNeuron]) {
                            winningNeuron = i;
                        }
                    }
                }
                
                for (int i = 0; i < inputDimension; ++i) {
                    weights[winningNeuron][i] += learnRate 
                            * (inputVector[k][i] - weights[winningNeuron][i]);
                }
            }
        }
        
        learnRate = learningRateDecay(learnRate);
        System.out.println(learnRate);
    }
    
    private void mappingInputVector() {
        for (int k = 0; k < numberOfInput; ++k) {
            winningNeuron = 0;
            for (int i = 0; i < numberOfCluster; ++i) {
                euclideanDistance[i] = computeEuclideanDistance(
                        weights[i], inputVector[k]);
                if (i != 0) {
                    if (euclideanDistance[i] 
                            < euclideanDistance[winningNeuron]) {
                        winningNeuron = i;
                    }
                }
            }
            
            System.out.println("Input[" + k +"] -> Cluster No: " 
                               + winningNeuron);
        }
        
        System.out.println(
                "Input[");
    }
    
    public void run(double[][] inputVector) {
        this.inputVector = inputVector;
        numberOfInput    = inputVector.length;
        inputDimension   = inputVector[0].length;
        
        initializeWeigths();
        train();
        mappingInputVector();
    }
    
    public void setNumberOfClusters(int numberOfCluster) {
        this.numberOfCluster = numberOfCluster;
    }
    
    public void setLearnRate(double learnRate) {
        this.learnRate = learnRate;        
    }
    
    public void setMaxIteration(int maxIteration) {
        this.maxIteration = maxIteration;
    }
    
    public static void main(String[] args) {
        double input[][] = {{1, 1, -1, -1}, {-1, -1, 1, 1}, {-1, 1, 1, -1}, 
                            {-1, -1, 1, 1}};
        
        NeuralNetwork net = new NeuralNetwork();
        net.setNumberOfClusters(20);
        net.setLearnRate(0.6);
        net.setMaxIteration(50);
        net.run(input);
    }
    
}
