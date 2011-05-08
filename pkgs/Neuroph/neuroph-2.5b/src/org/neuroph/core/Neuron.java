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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import org.neuroph.core.input.InputFunction;
import org.neuroph.core.transfer.Step;
import org.neuroph.core.transfer.TransferFunction;

/**
 *<pre>
 * Basic general neuron model according to McCulloch-Pitts neuron model.
 * Different neuron models can be created by using different input and transfer functions for instances of this class,
 * or by deriving from this class. The neuron is basic processing element of neural network.
 * This class implements the following behaviour:
 *  
 * output = transferFunction( inputFunction(inputConnections) )
 *</pre>
 * 
 * @see InputFunction
 * @see TransferFunction
 * @author Zoran Sevarac <sevarac@gmail.com>
 */

public class Neuron implements Serializable {
	/**
	 * The class fingerprint that is set to indicate serialization 
	 * compatibility with a previous version of the class
	 */	
	private static final long serialVersionUID = 3L;

	/**
	 * Parent layer for this neuron
	 */
	protected Layer parentLayer;

	/**
	 * Collection of neuron's input connections (connections to this neuron)
	 */
	protected List <Connection> inputConnections = new ArrayList<Connection>();


	/**
	 * Collection of neuron's output connections (connections from this to other
	 * neurons)
	 */
	protected List <Connection> outConnections = new ArrayList<Connection>();

	/**
	 * Total net input for this neuron. Represents total input for this neuron
	 * received from input function.
	 */
	protected transient double netInput = 0;

	/**
	 * Neuron output
	 */
	protected transient double output = 0;

	/**
	 * Local error for this neuron
	 */
	protected transient double error = 0;

	/**
	 * Input function for this neuron
	 */
	protected InputFunction inputFunction;

	/**
	 * Transfer function for this neuron
	 */
	protected TransferFunction transferFunction;

	/**
	 * Creates an instance of Neuron with the weighted sum, input function 
	 * and Step transfer function. This is the original McCulloch-Pitts 
	 * neuron model.
	 */
	public Neuron() {
		this.inputFunction = new InputFunction();
		this.transferFunction = new Step();
	}

	/**
	 * Creates an instance of Neuron with the specified input and transfer functions.
	 * 
	 * @param inputFunction
	 *            input function for this neuron
	 * @param transferFunction
	 *            transfer function for this neuron
	 */
	public Neuron(InputFunction inputFunction, TransferFunction transferFunction) {
		this.inputFunction = inputFunction;
		this.transferFunction = transferFunction;
	}

	/**
	 * Calculates neuron's output
	 */
	public void calculate() {
 //why fo we need this? for input neurons!!!
		if (this.hasInputConnections()) {
			this.netInput = this.inputFunction.getOutput(this.inputConnections);
		}

                this.output = this.transferFunction.getOutput(this.netInput);
	}

	/**
	 * Sets input and output activation levels to zero
	 */
	public void reset() {
		this.setInput(0d);
		this.setOutput(0d);
	}

	/**
	 * Sets neuron's input
	 * 
	 * @param input
	 *            input value to set
	 */
	public void setInput(double input) {
		this.netInput = input;
	}

	/**
	 * Returns total net input
	 * 
	 * @return total net input
	 */
	public double getNetInput() {
		return this.netInput;
	}

	/**
	 * Returns neuron's output
	 * 
	 * @return neuron output
	 */
	public double getOutput() {
		return this.output;
	}

	/**
	 * Returns true if there are input connections for this neuron, false
	 * otherwise
	 * 
	 * @return true if there is input connection, false otherwise
	 */
	public boolean hasInputConnections() {
		return (!this.inputConnections.isEmpty());
	}

	/**
	 * Returns Iterator interface for accessing input connections
	 * 
	 * @return iterator interface for accessing input connections
	 */
	public Iterator<Connection> getInputsIterator() {
		return this.inputConnections.iterator();
	}

	/**
	 * Adds the specified input connection
	 * 
	 * @param connection
	 *            input connection to add
	 */
	public void addInputConnection(Connection connection) {
		this.inputConnections.add(connection);
		Neuron fromNeuron = connection.getFromNeuron();
		fromNeuron.addOutputConnection(connection);
	}

	/**
	 * Adds input connection from specified neuron
	 *
	 * @param fromNeuron
	 *            neuron to connect from
	 */
	public void addInputConnection(Neuron fromNeuron) {
		Connection connection = new Connection(fromNeuron, this);
		this.addInputConnection(connection);
	}

	/**
	 * Adds input connection with the given weight, from given neuron
	 * 
	 * @param fromNeuron
	 *            neuron to connect from
	 * @param weightVal
	 *	      connection weight value
	 * 
	 */	
	public void addInputConnection(Neuron fromNeuron, double weightVal) {
		Connection connection = new Connection(fromNeuron, this, weightVal);
		this.addInputConnection(connection);
	}

	/**
	 * Adds the specified output connection
	 * 
	 * @param connection output connection to add
	 */
	protected void addOutputConnection(Connection connection) {
		this.outConnections.add(connection);
	}

	/**
	 * Returns input connections for this neuron as Vector collection
	 * 
	 * @return input connections of this neuron
	 */
	public List<Connection> getInputConnections() {
		return inputConnections;
	}

	/**
	 * Returns output connections from this neuron
	 * 
	 * @return output connections from this neuron
	 */
	public List<Connection> getOutConnections() {
		return outConnections;
	}

	/**
	 * Removes input connection which is connected to specified neuron
	 * 
	 * @param fromNeuron
	 *            neuron which is connected as input
	 */
	public void removeInputConnectionFrom(Neuron fromNeuron) {
		for(Connection connection : this.inputConnections) {
			if (connection.getFromNeuron() == fromNeuron) {
				this.inputConnections.remove(connection);
				return;
			}
		}				
	}

	/**
	 * Gets input connection from the specified neuron * @param fromNeuron
	 * neuron connected to this neuron as input
	 */
	public Connection getConnectionFrom(Neuron fromNeuron) {	
		for(Connection connection : this.inputConnections) {
			if (connection.getFromNeuron() == fromNeuron)
				return connection;		
		}
		return null;
	}

	/**
	 * Sets input function
	 * 
	 * @param inputFunction
	 *            input function for this neuron
	 */
	public void setInputFunction(InputFunction inputFunction) {
		this.inputFunction = inputFunction;
	}

	/**
	 * Sets transfer function
	 * 
	 * @param transferFunction
	 *            transfer function for this neuron
	 */
	public void setTransferFunction(TransferFunction transferFunction) {
		this.transferFunction = transferFunction;
	}

	/**
	 * Returns input function
	 * 
	 * @return input function
	 */
	public InputFunction getInputFunction() {
		return this.inputFunction;
	}

	/**
	 * Returns transfer function
	 * 
	 * @return transfer function
	 */
	public TransferFunction getTransferFunction() {
		return this.transferFunction;
	}

	/**
	 * Sets reference to parent layer for this neuron (layer in which the neuron
	 * is located)
	 * 
	 * @param parent
	 *            reference on layer in which the cell is located
	 */
	public void setParentLayer(Layer parent) {
		this.parentLayer = parent;
	}

	/**
	 * Returns reference to parent layer for this neuron
	 * 
	 * @return parent layer for this neuron
	 */
	public Layer getParentLayer() {
		return this.parentLayer;
	}

	/**
	 * Returns weights vector of input connections
	 * 
	 * @return weights vector of input connections
	 */
	public Vector<Weight> getWeightsVector() {
		Vector<Weight> weights = new Vector<Weight>();
		for(Connection connection : this.inputConnections) {
			Weight weight = connection.getWeight();
			weights.addElement(weight);			
		}		
		return weights;
	}

	/**
	 * Returns error for this neuron. This is used by supervised learing rules.
	 * 
	 * @return error for this neuron which is set by learning rule
	 */
	public double getError() {
		return error;
	}

	/**
	 * Sets error for this neuron. This is used by supervised learing rules.
	 * 
	 * @param error
	 *            neuron error
	 */
	public void setError(double error) {
		this.error = error;
	}

	/**
	 * Sets this neuron output
	 * 
	 * @param output
	 *            value to set
	 */
	public void setOutput(double output) {
		this.output = output;
	}

	/**
	 * Randomize all input weights
	 */
	public void randomizeInputWeights() {
		for(Connection connection : this.inputConnections) {
			connection.getWeight().randomize();
		}		
	}

	/**
	 * Randomize all input weights within specified value range
	 */
	public void randomizeInputWeights(double minWeight, double maxWeight) {
		for(Connection connection : this.inputConnections) {
			connection.getWeight().randomize(minWeight, maxWeight);
		}
	}

        /**
         * Initialize weights for all input connections to specified value
         * @param value the weight value
         */
        public void initializeWeights(double value) {
             for(Connection connection : this.inputConnections) {
                  connection.getWeight().setValue(value);
             }
        }

        /**
         * Initialize weights for all input connections to using random number generator
         * @param generator the random number generator
         */
        public void initializeWeights(Random generator) {
              for(Connection connection : this.inputConnections) {
                   connection.getWeight().setValue(generator.nextDouble());
              }
        }

        /**
         * Initialize weights for all input connections with random value within specified interval
         * @param min 
         * @param max
         */
         public void initializeWeights(double min, double max) {
              for(Connection connection : this.inputConnections) {
                   connection.getWeight().randomize(min, max);
              }
        }

}
