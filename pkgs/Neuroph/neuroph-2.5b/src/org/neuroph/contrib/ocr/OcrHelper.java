package org.neuroph.contrib.ocr;

import java.awt.Dimension;
import java.util.List;
import java.util.Vector;
import org.neuroph.contrib.imgrec.ColorMode;
import org.neuroph.contrib.imgrec.ImageRecognitionHelper;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.util.TransferFunctionType;


/**
 * Provides methods to create Neural Network and Training set for OCR.
 * @author zoran
 */
public class OcrHelper extends ImageRecognitionHelper {

    /**
     * Creates neural network for OCR, which contains OCR plugin. OCR plugin provides interface for character recognition.
     * @param label neural network label
     * @param samplingResolution character size in pixels (all characters will be scaled to this dimensions during recognition)
     * @param colorMode color mode used fr recognition
     * @param characterLabels character labels for output neurons
     * @param layersNeuronsCount number of neurons ih hidden layers
     * @param transferFunctionType neurons transfer function type
     * @return returns NeuralNetwork with the OCR plugin
     */
    public static NeuralNetwork createNewNeuralNetwork(String label, Dimension samplingResolution, ColorMode colorMode, List<String> characterLabels,  Vector<Integer> layersNeuronsCount, TransferFunctionType transferFunctionType) {
        NeuralNetwork neuralNetwork = ImageRecognitionHelper.createNewNeuralNetwork(label, samplingResolution, colorMode, characterLabels, layersNeuronsCount, transferFunctionType);
        neuralNetwork.addPlugin(new OcrPlugin(samplingResolution, colorMode));

        return neuralNetwork;
    }
}