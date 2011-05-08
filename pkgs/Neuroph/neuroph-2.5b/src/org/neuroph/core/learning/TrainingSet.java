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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.encog.engine.data.EngineData;
import org.encog.engine.data.EngineDataSet;
import org.encog.engine.data.EngineIndexableSet;
import org.neuroph.core.exceptions.VectorSizeMismatchException;

/**
 * A set of training elements for training neural network.
 * 
 * @author Zoran Sevarac <sevarac@gmail.com>
 */
public class TrainingSet implements Serializable, EngineIndexableSet {

	/**
	 * The class fingerprint that is set to indicate serialization compatibility
	 * with a previous version of the class
	 */
	private static final long serialVersionUID = 2L;

	/**
	 * Collection of training elements
	 */
	private List<TrainingElement> elements;

	private int inputVectorSize = 0;
	private int outputVectorSize = 0;

	/**
	 * Label for this training set
	 */
	private String label;

	/**
	 * Full file path incuding file name
	 */
	private transient String filePath;

	/**
	 * Creates an instance of new empty training set
	 */
	public TrainingSet() {
		this.elements = new ArrayList<TrainingElement>();
	}

	/**
	 * Creates an instance of new empty training set with given label
	 * 
	 * @param label
	 *            training set label
	 */
	public TrainingSet(String label) {
		this.label = label;
		this.elements = new ArrayList<TrainingElement>();
	}

	/**
	 * Creates an instance of new empty training set
	 * 
	 * @param inputVectorSize
	 */
	public TrainingSet(int inputVectorSize) {
		this.elements = new ArrayList<TrainingElement>();
		this.inputVectorSize = inputVectorSize;
	}

	/**
	 * Creates an instance of new empty training set
	 * 
	 * @param inputVectorSize
	 * @param outputVectorSize
	 */
	public TrainingSet(int inputVectorSize, int outputVectorSize) {
		this.elements = new ArrayList<TrainingElement>();
		this.inputVectorSize = inputVectorSize;
		this.outputVectorSize = outputVectorSize;
	}

	/**
	 * Adds new training element to this training set
	 * 
	 * @param el
	 *            training element to add
	 */
	public void addElement(TrainingElement el)
			throws VectorSizeMismatchException {
		// check input vector size if it is predefined
		if ((this.inputVectorSize != 0)
				&& (el.getInput().length != this.inputVectorSize)) {
			throw new VectorSizeMismatchException(
					"Input vector size does not match training set!");
		}
		// check output vector size if it is predefined
		if (el instanceof SupervisedTrainingElement) {
			SupervisedTrainingElement sel = (SupervisedTrainingElement) el;
			if ((this.outputVectorSize != 0)
					&& (sel.getDesiredOutput().length != this.outputVectorSize)) {
				throw new VectorSizeMismatchException(
						"Output vector size does not match training set!");
			}
		}
		// if everything went ok add training element
		this.elements.add(el);
	}

	/**
	 * Removes training element at specified index position
	 * 
	 * @param idx
	 *            position of element to remove
	 */
	public void removeElementAt(int idx) {
		this.elements.remove(idx);
	}

	/**
	 * Returns Iterator for iterating training elements collection
	 * 
	 * @return Iterator for iterating training elements collection
	 */
	public Iterator<TrainingElement> iterator() {
		return this.elements.iterator();
	}

	/**
	 * Returns training elements collection
	 * 
	 * @return training elements collection
	 */
	public List<TrainingElement> trainingElements() {
		return this.elements;
	}

	/**
	 * Returns training element at specified index position
	 * 
	 * @param idx
	 *            index position of training element to return
	 * @return training element at specified index position
	 */
	public TrainingElement elementAt(int idx) {
		return this.elements.get(idx);
	}

	/**
	 * Removes all alements from training set
	 */
	public void clear() {
		this.elements.clear();
	}

	/**
	 * Returns true if training set is empty, false otherwise
	 * 
	 * @return true if training set is empty, false otherwise
	 */
	public boolean isEmpty() {
		return this.elements.isEmpty();
	}

	/**
	 * Returns number of training elements in this training set set
	 * 
	 * @return number of training elements in this training set set
	 */
	public int size() {
		return this.elements.size();
	}

	/**
	 * Returns label for this training set
	 * 
	 * @return label for this training set
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets label for this training set
	 * 
	 * @param label
	 *            label for this training set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Sets full file path for this training set
	 * 
	 * @param filePath
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * Returns full file path for this training set
	 * 
	 * @return full file path for this training set
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * Returns label of this training set
	 * 
	 * @return label of this training set
	 */
	@Override
	public String toString() {
		return this.label;
	}

	/**
	 * Saves this training set to the specified file
	 * 
	 * @param filePath
	 */
	public void save(String filePath) {
		this.filePath = filePath;
		this.save();
	}

	/**
	 * Saves this training set to file specified in its filePath field
	 */
	public void save() {
		ObjectOutputStream out = null;

		try {
			File file = new File(this.filePath);
			out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(this);
			out.flush();

		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException ioe) {
				}
			}
		}
	}

	/**
	 * Loads training set from the specified file
	 * 
	 * @param filePath
	 *            training set file
	 * @return loded training set
	 */
	public static TrainingSet load(String filePath) {
		ObjectInputStream oistream = null;

		try {
			File file = new File(filePath);
			if (!file.exists()) {
				throw new FileNotFoundException("Cannot find file: " + filePath);
			}

			oistream = new ObjectInputStream(new FileInputStream(filePath));
			TrainingSet tSet = (TrainingSet) oistream.readObject();

			return tSet;

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} finally {
			if (oistream != null) {
				try {
					oistream.close();
				} catch (IOException ioe) {
				}
			}
		}

		return null;
	}

	/**
	 * Returns output vector size of training elements in this training set This
	 * method is implementation of EngineIndexableSet interface, and it is added
	 * to provide compatibility with Encog data sets and FlatNetwork
	 */
	@Override
	public int getIdealSize() {
		return this.outputVectorSize;
	}

	/**
	 * Returns input vector size of training elements in this training set This
	 * method is implementation of EngineIndexableSet interface, and it is added
	 * to provide compatibility with Encog data sets and FlatNetwork
	 */
	@Override
	public int getInputSize() {
		return this.inputVectorSize;
	}

	/**
	 * Returns true if training set contains supervised training elements This
	 * method is implementation of EngineIndexableSet interface, and it is added
	 * to provide compatibility with Encog data sets and FlatNetwork
	 */
	@Override
	public boolean isSupervised() {
		return this.outputVectorSize > 0;
	}

	/**
	 * Gets training data/record at specified index position. This method is
	 * implementation of EngineIndexableSet interface. It is added for
	 * Encog-Engine compatibility.
	 */
	@Override
	public void getRecord(long index, EngineData pair) {
		EngineData item = this.elements.get((int) index);
		pair.setInputArray(item.getInputArray());
		pair.setIdealArray(item.getIdealArray());
	}

	/**
	 * Returns training elements/records count This method is implementation of
	 * EngineIndexableSet interface. It is added for Encog-Engine compatibility.
	 */
	@Override
	public long getRecordCount() {
		return this.elements.size();
	}

	/**
	 * This method is implementation of EngineIndexableSet interface, and it is
	 * added to provide compatibility with Encog data sets and FlatNetwork.
	 * 
	 * Some datasets are not memory based, they may make use of a SQL connection
	 * or a binary flat file. Because of this these datasets need to be cloned
	 * for multi-threaded training or performance will greatly suffer. Because
	 * this is a memory-based dataset, no cloning takes place and the "this"
	 * object is returned.
	 */
	@Override
	public EngineIndexableSet openAdditional() {
		return this;
	}
}
