/* Copyright 2006 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.harmony.tests.java.util.jar;

import java.util.Collections;
import java.util.jar.Attributes;

import junit.framework.TestCase;

public class AttributesTest extends TestCase {

	/*
	 * @tests java.util.jar.Attributes.put(java.lang.Object, java.lang.Object)
	 */
	public void test_putLjava_lang_ObjectLjava_lang_Object() {
		Attributes atts = new Attributes();
		assertNull("Assert 0: ",
				atts.put(Attributes.Name.CLASS_PATH, "tools.jar"));
		assertEquals("Assert 1: ", "tools.jar", 
				atts.getValue(Attributes.Name.CLASS_PATH));

		// Regression for HARMONY-79
		try {
			atts.put("not a name", "value");
			fail("Assert 2: no class cast from key parameter");
		} catch (ClassCastException e) {
			// Expected
		}

		try {
			atts.put(Attributes.Name.CLASS_PATH, Boolean.TRUE);
			fail("Assert 3: no class cast from value parameter");
		} catch (ClassCastException e) {
			// Expected
		}
	}

	/**
	 * @tests java.util.jar.Attributes.putAll(java.util.Map)
	 */
	public void test_putAllLjava_util_Map() {
		Attributes atts = new Attributes();
		assertNull("Assert 0: ",
				atts.put(Attributes.Name.CLASS_PATH, "tools.jar"));
		assertNull("Assert 1: ", 
				atts.put(Attributes.Name.MANIFEST_VERSION, "1"));

		Attributes atts2 = new Attributes();
		atts2.putAll(atts);

		assertEquals("Assert 2:", "tools.jar", 
				atts2.get(Attributes.Name.CLASS_PATH));
		assertEquals("Assert 3: ", "1", 
				atts2.get(Attributes.Name.MANIFEST_VERSION));

		try {
			atts.putAll(Collections.EMPTY_MAP);
			fail("Assert 4: no class cast from attrib parameter");
		} catch (ClassCastException e) {
			// Expected
		}
	}
}
