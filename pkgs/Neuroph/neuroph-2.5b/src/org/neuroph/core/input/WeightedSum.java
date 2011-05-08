/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.neuroph.core.input;

import java.util.List;

import org.neuroph.core.Connection;

/**
 * Optimized version of weighted input function
 * 
 * @author zoran
 */
public class WeightedSum extends InputFunction {

	private static final long serialVersionUID = 1L;

	@Override
	public double getOutput(List<Connection> inputConnections) {
		double output = 0d;

		for (Connection connection : inputConnections) {
			output += connection.getWeightedInput();
		}

		return output;
	}

	public static double[] getOutput(double[] inputs, double[] weights) {
		double[] output = new double[inputs.length];

		for (int i = 0; i < inputs.length; i++) {
			output[i] += inputs[i] * weights[i];
		}

		return output;
	}

}
