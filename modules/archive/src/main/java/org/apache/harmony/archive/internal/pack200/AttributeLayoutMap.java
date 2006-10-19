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
package org.apache.harmony.archive.internal.pack200;
//NOTE: Do not use generics in this code; it needs to run on JVMs < 1.5
//NOTE: Do not extract strings as messages; this code is still a work-in-progress
//NOTE: Also, don't get rid of 'else' statements for the hell of it ...
import java.util.HashMap;
import java.util.Map;

/**
 * Stores a mapping from attribute names to their corresponding layout types.
 * Note that names of attribute layouts and their formats are <emph>not</emph>
 * internationalised, and should not be translated.
 */
public class AttributeLayoutMap {
	// private static final String METADATA = "[NH[(1)]][RSHNH[RUH(1)]][TB(66,67,73,83,90)[KIH](68)[KDH](70)[KFH](74)[KJH](99)[RSH](101)[RSHRUH](115)[RUH](91)[NH[(0)]](64)[RSH[RUH(0)]]()[]]";

	// create a whole bunch of AttributeLayouts here
	private static AttributeLayout[] getDefaultAttributeLayouts()
			throws Pack200Exception {
		return new AttributeLayout[] {
				new AttributeLayout("LineNumberTable",
						AttributeLayout.CONTEXT_CODE, "NH[PHH]", 1),
				new AttributeLayout("LocalVariableTable",
						AttributeLayout.CONTEXT_CODE, "NH[PHOHRUHRSHH]", 2),
				new AttributeLayout("LocalVariableTypeTable",
						AttributeLayout.CONTEXT_CODE, "NH[PHOHRUHRSHH]", 3),
				new AttributeLayout("SourceFile",
						AttributeLayout.CONTEXT_CLASS, "RUNH", 17),
				new AttributeLayout("ConstantValue",
						AttributeLayout.CONTEXT_FIELD, "KQH", 17),
				new AttributeLayout("Code", AttributeLayout.CONTEXT_METHOD,
						"*", 17),
				new AttributeLayout("EnclosingMethod",
						AttributeLayout.CONTEXT_CLASS, "RCHRDNH", 18),
				new AttributeLayout("Exceptions",
						AttributeLayout.CONTEXT_METHOD, "NH[RCH]", 18),
				new AttributeLayout("Signature", AttributeLayout.CONTEXT_CLASS,
						"RSH", 19),
				new AttributeLayout("Signature", AttributeLayout.CONTEXT_FIELD,
						"RSH", 19),
				new AttributeLayout("Signature",
						AttributeLayout.CONTEXT_METHOD, "RSH", 19),
				new AttributeLayout("Deprecated",
						AttributeLayout.CONTEXT_CLASS, "", 20),
				new AttributeLayout("Deprecated",
						AttributeLayout.CONTEXT_FIELD, "", 20),
				new AttributeLayout("Deprecated",
						AttributeLayout.CONTEXT_METHOD, "", 20),
				new AttributeLayout("RuntimeVisibleAnnotations",
						AttributeLayout.CONTEXT_CLASS, "*", 21),
				new AttributeLayout("RuntimeVisibleAnnotations",
						AttributeLayout.CONTEXT_FIELD, "*", 21),
				new AttributeLayout("RuntimeVisibleAnnotations",
						AttributeLayout.CONTEXT_METHOD, "*", 21),
				new AttributeLayout("RuntimeInvisibleAnnotations",
						AttributeLayout.CONTEXT_CLASS, "*", 22),
				new AttributeLayout("RuntimeInvisibleAnnotations",
						AttributeLayout.CONTEXT_FIELD, "*", 22),
				new AttributeLayout("RuntimeInvisibleAnnotations",
						AttributeLayout.CONTEXT_METHOD, "*", 22),
				new AttributeLayout("InnerClasses",
						AttributeLayout.CONTEXT_CLASS, "*", 23),
				new AttributeLayout("RuntimeVisibleParameterAnnotations",
						AttributeLayout.CONTEXT_METHOD, "*", 23),
				new AttributeLayout("class-file version",
						AttributeLayout.CONTEXT_CLASS, "*", 24),
				new AttributeLayout("RuntimeInvisibleParameterAnnotations",
						AttributeLayout.CONTEXT_METHOD, "*", 24),
				new AttributeLayout("AnnotationDefault",
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
