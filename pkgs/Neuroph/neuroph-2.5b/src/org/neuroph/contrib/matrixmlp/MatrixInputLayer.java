/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.neuroph.contrib.matrixmlp;

/**
 * Input matrix layer
 * @author Zoran Sevarac
 */
public class MatrixInputLayer implements MatrixLayer {
    double[] inputs;

    public MatrixInputLayer(int neuronsCount) {
        this.inputs = new double[neuronsCount];
    }
    
    public void setInputs(double[] inputs) {
        System.arraycopy(inputs, 0, this.inputs, 0, inputs.length);
        this.inputs[this.inputs.length - 1] = 1;
        //this.inputs = inputs;
        // dodaj i bias output
    }

    public double[] getInputs() {
        return inputs;
    }

    public void setOutputs(double[] outputs) {
        this.inputs = outputs;
    }

    public double[] getOutputs() {
        return inputs;
    }

    public void calculate() { }


}
