package org.neuroph.contrib.matrixmlp;

/**
 * Base interface for all matrix based layers.
 */
public interface MatrixLayer {

    /**
     * Sets layer input
     * @param inputs
     */
    public void setInputs(double[] inputs);

    /**
     * Returns layer input     *
     * @return layer input vector
     */
    public double[] getInputs();

    /**
     * Sets layer outputs
     * @param inputs
     */
    public void setOutputs(double[] inputs);

    /**
     * Returns layer outputs
     * @return layer outputs
     */
    public double[] getOutputs();

    /**
     * Calculate layer
     */
    public void calculate();

}
