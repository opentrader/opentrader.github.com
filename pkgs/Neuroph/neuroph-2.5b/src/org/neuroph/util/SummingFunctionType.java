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

import org.neuroph.core.input.And;
import org.neuroph.core.input.Intensity;
import org.neuroph.core.input.Max;
import org.neuroph.core.input.Min;
import org.neuroph.core.input.Or;
import org.neuroph.core.input.Product;
import org.neuroph.core.input.Sum;
import org.neuroph.core.input.SumSqr;

/**
 * Contains summing functions types and labels.
 */
public enum SummingFunctionType {
	SUM("Sum"),
	INTENSITY("Intensity"),
	AND("And"),
	OR("Or"),
	SUMSQR("SumSqr"),
	MIN("Min"),
	MAX("Max"),
	PRODUCT("Product");
	
	private String typeLabel;
	
	private SummingFunctionType(String typeLabel) {
		this.typeLabel = typeLabel;
	}
	
	public String getTypeLabel() {
		return typeLabel;
	}

        public Class getTypeClass() {
            switch (this) {
                case SUM:
			return Sum.class;
		case INTENSITY:
			return Intensity.class;
		case AND:
			return And.class;
		case OR:
			return Or.class;
		case SUMSQR:
			return SumSqr.class;
		case MIN:
			return Min.class;
		case MAX:
			return Max.class;
		case PRODUCT:
			return Product.class;
		}

            return null;
        }
}
