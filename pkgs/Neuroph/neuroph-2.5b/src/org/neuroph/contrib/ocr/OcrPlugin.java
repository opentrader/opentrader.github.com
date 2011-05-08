package org.neuroph.contrib.ocr;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import org.neuroph.contrib.imgrec.ColorMode;
import org.neuroph.contrib.imgrec.ImageRecognitionPlugin;
import org.neuroph.core.Neuron;
import org.neuroph.util.plugins.PluginBase;

/**
 * Provides OCR interface for neural network.
 *
 * @author Zoran Sevarac, Ivana Jovicic, Vladimir Kolarevic, Marko Ivanovic,
 * Boris Horvat, Damir Kocic, Nemanja Jovanovic
 */
public class OcrPlugin extends PluginBase implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Ocr plugin name field used for getting plugin from parent neural network.
     */
    public static final String OCR_PLUGIN_NAME = "OCR Plugin";

    /**
     * Image sampling resolution (image dimensions)
     */
    private Dimension samplingResolution;
    /**
     * Color mode used for recognition (full color or black and white)
     */
    private ColorMode colorMode;


    /**
     * Constructor, creates new OCR Plugin for specified sampling resolution and color mode
     *
     * @param samplingResolution
     *            image sampling resolution (dimensions)
     * @param colorMode recognition color mode
     */
    public OcrPlugin(Dimension samplingResolution, ColorMode colorMode) {
        super(OCR_PLUGIN_NAME);
        this.samplingResolution = samplingResolution;
        this.colorMode = colorMode;
    }

    /**
     * This method scales character image to the given dimensions and then does the character recognition.
     * Returns recognized character.
     * @param charImage character image
     * @param scaleToDim dimensions to scale the image before character recognition is done
     * @return recognized character
     */
    public Character recognizeCharacter(BufferedImage charImage, Dimension scaleToDim) {
        BufferedImage resizedImage = this.resizeImage(charImage, scaleToDim.width, scaleToDim.height);
        return recognizeCharacter(resizedImage);
    }

    /**
     * Recognizes character from the image and returns character
     * @param charImage character image
     * @return recognized character
     */
    public Character recognizeCharacter(BufferedImage charImage) {
        // get the image recognition plugin from neural network
        ImageRecognitionPlugin imageRecognition = (ImageRecognitionPlugin) this.getParentNetwork().getPlugin(ImageRecognitionPlugin.IMG_REC_PLUGIN_NAME);

        HashMap<String, Double> output = imageRecognition.recognizeImage(charImage);
        HashMap<String, Neuron> n = imageRecognition.getMaxOutput();

        String ch = n.toString().substring(1, 2);
        return Character.valueOf(ch.charAt(0));
    }

    /**
     * Recogize the character from the image and returns HashMap with keys as 
     * characters and recognition probability as values sorted descending by probability.
     *
     * @param charImage character image
     * @return HashMap with keys as characters and recognition probability as values
     */
    public HashMap recognizeCharacterProbabilities(BufferedImage charImage) {
        // get the image recognition plugin from neural network
        ImageRecognitionPlugin imageRecognition =
                (ImageRecognitionPlugin) this.getParentNetwork().getPlugin(ImageRecognitionPlugin.IMG_REC_PLUGIN_NAME);

        // image recognition is done here
        HashMap<String, Double> output = imageRecognition.recognizeImage(charImage);
        HashMap<Character, Double> recognized = sortHashMapByValues(output);

        return recognized;
    }


    /**
     * Resize image to given dimensions (width and height)
     * @param image image to resize
     * @param width width to resize to
     * @param height height to resize to
     * @return scaled image
     */
    private BufferedImage resizeImage(BufferedImage image, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(image, 0, 0, width, height, null);
        g.dispose();

        return resizedImage;
    }

    /**
     * This private method sorts the result of the recogntion, in order to
     * see which letter has the highest probability
     *
     * @param passedMap the HashMap that holds the resault of the recognition process
     *
     * @return LinkedHashMap that represents the combination of letters with the
     *                       probability of the correct recognition
     */
    private LinkedHashMap sortHashMapByValues(HashMap passedMap) {
        List mapKeys = new ArrayList(passedMap.keySet());
        List mapValues = new ArrayList(passedMap.values());
        Collections.sort(mapValues);
        Collections.sort(mapKeys);
        Collections.reverse(mapValues);

        LinkedHashMap sortedMap = new LinkedHashMap();

        Iterator valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Object val = valueIt.next();
            Iterator keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                Object key = keyIt.next();
                String comp1 = passedMap.get(key).toString();
                String comp2 = val.toString();

                if (comp1.equals(comp2)) {
                    passedMap.remove(key);
                    mapKeys.remove(key);
                    Character charKey = Character.valueOf(key.toString().charAt(0));
                    sortedMap.put(charKey, (Double) val);
                    break;
                }

            }

        }
        return sortedMap;
    }

    /**
     * Returns color mode used for OCR
     * @return color mode used for OCR
     */
    public ColorMode getColorMode() {
        return colorMode;
    }

   /**
     * Returns sampling resolution used for OCR
     * @return sampling resolution used for OCR
     */
    public Dimension getSamplingResolution() {
        return samplingResolution;
    }



}
