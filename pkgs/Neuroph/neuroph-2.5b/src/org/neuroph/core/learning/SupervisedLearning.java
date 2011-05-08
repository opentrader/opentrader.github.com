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
import java.util.Iterator;


// TODO:  random pattern order

/**
 * Base class for all supervised learning algorithms.
 * It extends IterativeLearning, and provides general supervised learning principles.
 * 
 * @author Zoran Sevarac <sevarac@gmail.com>
 */

abstract public class SupervisedLearning extends IterativeLearning implements
		Serializable {

	/**
	 * The class fingerprint that is set to indicate serialization 
	 * compatibility with a previous version of the class
	 */	
	private static final long serialVersionUID = 3L;

	/**
	 * Total network error
	 */
	protected transient double totalNetworkError;

	/**
	 * Sum of squared errors of each output neuron for one pattern 0.5*sum((et-e)^2)
	 */
	protected transient double patternErrorSqrSum;

	/**
	 * Totoal sum of all pattern errors
	 */
      	protected transient double totalPatternErrorSqrSum;

        /**
         * Total network error in previous epoch
         */
        protected transient double previousEpochError;

	/**
	 * Max allowed network error (condition to stop learning)
	 */
	protected double maxError = 0.01d;

        /**
         * Stopping condition: training stops if total network error change is smaller than minErrorChange
         * for minErrorChangeIterationsLimit number of iterations
         */
        private transient double minErrorChange = Double.POSITIVE_INFINITY;

        /**
         * Stopping condition: training stops if total network error change is smaller than minErrorChange
         * for minErrorChangeStopIterations number of iterations
         */
        private transient int minErrorChangeIterationsLimit = Integer.MAX_VALUE;

        /**
         * Count iterations where error change is smaller then minErrorChange
         */
        private transient int minErrorChangeIterationsCount;


	/**
	 * Creates new supervised learning rule
	 */
	public SupervisedLearning() {
		super();
	}


        /**
         * Trains network for the specified training set and number of iterations
         * @param trainingSet training set to learn
         * @param maxError maximum number of iterations to learn
         *
         */
        public void learn(TrainingSet trainingSet, double maxError) {
            this.maxError = maxError;   
            this.learn(trainingSet);
        }

        /**
         * Trains network for the specified training set and number of iterations
         * @param trainingSet training set to learn
         * @param maxIterations maximum number of learning iterations
         *
         */
        public void learn(TrainingSet trainingSet, double maxError, int maxIterations) {
            this.maxError = maxError;            
            this.setMaxIterations(maxIterations);            
            this.learn(trainingSet);
        }

        @Override
        protected void reset() {
            super.reset();
            this.minErrorChangeIterationsCount = 0;
            this.totalNetworkError = 0d;
            this.previousEpochError = 0d;
        }

        
	/**
	 * This method implements basic logic for one learning epoch for the
	 * supervised learning algorithms. Epoch is the one pass through the
	 * training set. This method  iterates through the training set
	 * and trains network for each element. It also sets flag if conditions 
	 * to stop learning has been reached: network error below some allowed
	 * value, or maximum iteration count 
	 * 
	 * @param trainingSet
	 *            training set for training network
	 */
        @Override
	public void doLearningEpoch(TrainingSet trainingSet) {

                this.previousEpochError = this.totalNetworkError;
		this.totalNetworkError = 0d;
                this.totalPatternErrorSqrSum = 0d;

		Iterator<TrainingElement> iterator = trainingSet.iterator();
		while (iterator.hasNext() && !isStopped()) {
        		SupervisedTrainingElement supervisedTrainingElement = (SupervisedTrainingElement)iterator.next();
			this.learnPattern(supervisedTrainingElement);
        	}

                this.updateTotalNetworkError();


                // moved stopping condition to separate method hasReachedStopCondition() so it can be overriden / customized in subclasses
		if (hasReachedStopCondition()) {
			stopLearning();
                }
                
	}

        /**
         * Returns true if stop condition has been reached, false otherwise.
         * Override this method in derived classes to implement custom stop criteria.
         *
         * @return true if stop condition is reached, false otherwise
         */
        protected boolean hasReachedStopCondition() {
            // da li ovd etreba staviti da proverava i da li se koristi ovaj uslov??? ili staviti da uslov bude automatski samo s ajaako malom vrednoscu za errorChange Doule.minvalue
            return (this.totalNetworkError < this.maxError) || this.errorChangeStalled();
        }

        /**
         * Returns true if absolute error change is sufficently small (<=minErrorChange) for minErrorChangeStopIterations number of iterations
         * @return true if absolute error change is stalled (error is sufficently small for some number of iterations)
         */
        protected boolean errorChangeStalled() {
            double absErrorChange = Math.abs(previousEpochError - totalNetworkError);
            
            if (absErrorChange <= this.minErrorChange)  {
                this.minErrorChangeIterationsCount++;

                if (this.minErrorChangeIterationsCount >= this.minErrorChangeIterationsLimit) {
                    return true;
                }
            } else {
                this.minErrorChangeIterationsCount = 0;
            }
            
            return false;
        }

	/**
	 * Trains network with the pattern from the specified training element
	 * 
	 * @param trainingElement
	 *            supervised training element which contains input and desired
	 *            output
	 */
	protected void learnPattern(SupervisedTrainingElement trainingElement) {
                double[] input = trainingElement.getInput();
                this.neuralNetwork.setInput(input);
                this.neuralNetwork.calculate();
                double[] output = this.neuralNetwork.getOutput();
                double[] desiredOutput = trainingElement.getDesiredOutput();
                double[] patternError = this.getPatternError(output, desiredOutput);
                this.updatePatternError(patternError);
                this.updateNetworkWeights(patternError);
	}

	/**
	 * Calculates the network error for the current pattern - diference between
	 * desired and actual output
	 * 
	 * @param output
	 *            actual network output
	 * @param desiredOutput
	 *            desired network output
	 * @return pattern error
	 */
	protected double[] getPatternError(double[] output, double[] desiredOutput) {
		double[] patternError = new double[output.length];

		for(int i = 0; i < output.length; i++) {
			patternError[i] = desiredOutput[i] - output[i];
		}
		
		return patternError;
	}



	/**
	 * Sets allowed network error, which indicates when to stopLearning training
	 * 
	 * @param maxError
	 *            network error
	 */
	public void setMaxError(double maxError) {
		this.maxError = maxError;
	}


	/**
	 * Returns learning error tolerance - the value of total network error to stop learning.
	 *
	 * @return learning error tolerance
	 */
        public double getMaxError() {
            return maxError;
        }



	/**
	 * Returns total network error in current learning epoch
	 * 
	 * @return total network error in current learning epoch
	 */
	public synchronized double getTotalNetworkError() {
		return totalNetworkError;
	}

	/**
	 * Returns total network error in previous learning epoch
	 *
	 * @return total network error in previous learning epoch
	 */
        public double getPreviousEpochError() {
            return previousEpochError;
        }

        /**
         * Returns min error change stopping criteria
         *
         * @return min error change stopping criteria
         */
        public double getMinErrorChange() {
            return minErrorChange;
        }

        /**
         * Sets min error change stopping criteria
         *
         * @param minErrorChange value for min error change stopping criteria
         */
        public void setMinErrorChange(double minErrorChange) {
            this.minErrorChange = minErrorChange;
        }

        /**
         * Returns number of iterations for min error change stopping criteria
         *
         * @return number of iterations for min error change stopping criteria
         */
        public int getMinErrorChangeIterationsLimit() {
            return minErrorChangeIterationsLimit;
        }

        /**
         * Sets number of iterations for min error change stopping criteria
         * @param minErrorChangeIterationsLimit number of iterations for min error change stopping criteria
         */
        public void setMinErrorChangeIterationsLimit(int minErrorChangeIterationsLimit) {
            this.minErrorChangeIterationsLimit = minErrorChangeIterationsLimit;
        }

         /**
         * Returns number of iterations count for for min error change stopping criteria
         *
         * @return number of iterations count for for min error change stopping criteria
         */
        public int getMinErrorChangeIterationsCount() {
            return minErrorChangeIterationsCount;
        }


	/**
	 * Subclasses update total network error for each training pattern with this
	 * method. Error update formula is learning rule specific.
	 * @deprecated 
	 */
        abstract protected void updateTotalNetworkError(double[] patternError);

	/**
	 * This method should implement the weights update procedure
	 * 
	 * @param patternError
	 *            pattern error vector
	 */
	abstract protected void updateNetworkWeights(double[] patternError);

        /**
         * This method should calculate sum sqr for single pattern error and
         * update the error sum for all patterns.
         * @param patternErrorVector Error vector for pattern
         */
        abstract protected void updatePatternError(double[] patternErrorVector);

        /**
         * This method should calculate the MSE or other type or total network error used
         */
        abstract protected void updateTotalNetworkError();
}
