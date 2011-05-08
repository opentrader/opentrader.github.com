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

package org.neuroph.util.plugins;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides labeling of all neural network components
 * @author Zoran Sevarac <sevarac@gmail.com>
 */
public class LabelsPlugin extends PluginBase {

	public static final String LABELS_PLUGIN_NAME = "LabelsPlugin";

	/**
	 * The class fingerprint that is set to indicate serialization
	 * compatibility with a previous version of the class.
	 */
	private static final long serialVersionUID = 3L;

	/**
	 * Collection of labels for the neural network components
	 */
	private Map<Object, String> labels = new HashMap<Object, String>();

	/**
	 * Field for neural network label
	 * This field is required to solve the java bug described at
	 * http://bugs.sun.com/view_bug.do?bug_id=4957674
	 */
	private String neuralNetworkLabel = new String();

	public LabelsPlugin() {
		super(LABELS_PLUGIN_NAME);
	}

	/**
	 * Returns label for the specified object
	 * @param object object for which label should be returned
	 * @return label for the specified object
	 */
	public String getLabel(Object object) {
		if (object != getParentNetwork()) {
			return labels.get(object);
		} else {
			return neuralNetworkLabel;
		}
	}

	/**
	 * Sets label for the specified object
	 * @param object object to set label
	 * @param label label to set
	 */
	public void setLabel(Object object, String label) {
		if (object == getParentNetwork()) {
			neuralNetworkLabel = label;
		} else {
			labels.put(object, label);
		}
	}
}
