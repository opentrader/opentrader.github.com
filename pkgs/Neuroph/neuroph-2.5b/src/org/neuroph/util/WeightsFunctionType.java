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

package org.neuroph.util;

import org.neuroph.core.input.Difference;
import org.neuroph.core.input.WeightedInput;

/**
 * Contains weights functions types and labels.
 */
public enum WeightsFunctionType {

    WEIGHTED_INPUT("WeightedInput"),
    DIFFERENCE("Difference");
    private String typeLabel;

    private WeightsFunctionType(String typeLabel) {
        this.typeLabel = typeLabel;
    }

    public String getTypeLabel() {
        return typeLabel;
    }

    public Class getTypeClass() {
        switch (this) {
            case WEIGHTED_INPUT:
                return WeightedInput.class;
            case DIFFERENCE:
                return Difference.class;
        }

        return null;
    }
}
