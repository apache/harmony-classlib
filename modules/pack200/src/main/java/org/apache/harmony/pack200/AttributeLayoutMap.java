/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.harmony.pack200;
//NOTE: Do not use generics in this code; it needs to run on JVMs < 1.5
//NOTE: Do not extract strings as messages; this code is still a work-in-progress
//NOTE: Also, don't get rid of 'else' statements for the hell of it ...
import java.util.HashMap;
import java.util.Map;

/**
 * Stores a mapping from attribute names to their corresponding layout types.
 * Note that names of attribute layouts and their formats are <emph>not</emph>
 * internationalized, and should not be translated.
 */
public class AttributeLayoutMap {
	// create a whole bunch of AttributeLayouts here
	private static AttributeLayout[] getDefaultAttributeLayouts()
			throws Pack200Exception {
		return new AttributeLayout[] {
				new AttributeLayout(AttributeLayout.ATTRIBUTE_LINE_NUMBER_TABLE,
						AttributeLayout.CONTEXT_CODE, "NH[PHH]", 1),
				new AttributeLayout(AttributeLayout.ATTRIBUTE_LOCAL_VARIABLE_TABLE,
						AttributeLayout.CONTEXT_CODE, "NH[PHOHRUHRSHH]", 2),
				new AttributeLayout(AttributeLayout.ATTRIBUTE_LOCAL_VARIABLE_TYPE_TABLE,
						AttributeLayout.CONTEXT_CODE, "NH[PHOHRUHRSHH]", 3),
				new AttributeLayout(AttributeLayout.ATTRIBUTE_SOURCE_FILE,
						AttributeLayout.CONTEXT_CLASS, "RUNH", 17),
				new AttributeLayout(AttributeLayout.ATTRIBUTE_CONSTANT_VALUE,
						AttributeLayout.CONTEXT_FIELD, "KQH", 17),
				new AttributeLayout(AttributeLayout.ATTRIBUTE_CODE, AttributeLayout.CONTEXT_METHOD,
						"*", 17),
				new AttributeLayout(AttributeLayout.ATTRIBUTE_ENCLOSING_METHOD,
						AttributeLayout.CONTEXT_CLASS, "RCHRDNH", 18),
				new AttributeLayout(AttributeLayout.ATTRIBUTE_EXCEPTIONS,
						AttributeLayout.CONTEXT_METHOD, "NH[RCH]", 18),
				new AttributeLayout(AttributeLayout.ATTRIBUTE_SIGNATURE, AttributeLayout.CONTEXT_CLASS,
						"RSH", 19),
				new AttributeLayout(AttributeLayout.ATTRIBUTE_SIGNATURE, AttributeLayout.CONTEXT_FIELD,
						"RSH", 19),
				new AttributeLayout(AttributeLayout.ATTRIBUTE_SIGNATURE,
						AttributeLayout.CONTEXT_METHOD, "RSH", 19),
				new AttributeLayout(AttributeLayout.ATTRIBUTE_DEPRECATED,
						AttributeLayout.CONTEXT_CLASS, "", 20),
				new AttributeLayout(AttributeLayout.ATTRIBUTE_DEPRECATED,
						AttributeLayout.CONTEXT_FIELD, "", 20),
				new AttributeLayout(AttributeLayout.ATTRIBUTE_DEPRECATED,
						AttributeLayout.CONTEXT_METHOD, "", 20),
				new AttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_VISIBLE_ANNOTATIONS,
						AttributeLayout.CONTEXT_CLASS, "*", 21),
				new AttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_VISIBLE_ANNOTATIONS,
						AttributeLayout.CONTEXT_FIELD, "*", 21),
				new AttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_VISIBLE_ANNOTATIONS,
						AttributeLayout.CONTEXT_METHOD, "*", 21),
				new AttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_INVISIBLE_ANNOTATIONS,
						AttributeLayout.CONTEXT_CLASS, "*", 22),
				new AttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_INVISIBLE_ANNOTATIONS,
						AttributeLayout.CONTEXT_FIELD, "*", 22),
				new AttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_INVISIBLE_ANNOTATIONS,
						AttributeLayout.CONTEXT_METHOD, "*", 22),
				new AttributeLayout(AttributeLayout.ATTRIBUTE_INNER_CLASSES,
						AttributeLayout.CONTEXT_CLASS, "*", 23),
				new AttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS,
						AttributeLayout.CONTEXT_METHOD, "*", 23),
				new AttributeLayout(AttributeLayout.ATTRIBUTE_CLASS_FILE_VERSION,
						AttributeLayout.CONTEXT_CLASS, "*", 24),
				new AttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS,
						AttributeLayout.CONTEXT_METHOD, "*", 24),
				new AttributeLayout(AttributeLayout.ATTRIBUTE_ANNOTATION_DEFAULT,
						AttributeLayout.CONTEXT_METHOD, "*", 25) };
	}

	private Map layouts;

	public AttributeLayoutMap() throws Pack200Exception {
		this.layouts = new HashMap();
		AttributeLayout[] defaultAttributeLayouts = getDefaultAttributeLayouts();
		for (int i = 0; i < defaultAttributeLayouts.length; i++) {
			add(defaultAttributeLayouts[i]);
		}
	}

	public void add(AttributeLayout layout) {
		layouts.put(layout.key, layout);
	}

	public AttributeLayout getAttributeLayout(String name, int context)
			throws Pack200Exception {
		return (AttributeLayout) layouts.get(new AttributeLayout.Key(name,
				context));
	}
}
