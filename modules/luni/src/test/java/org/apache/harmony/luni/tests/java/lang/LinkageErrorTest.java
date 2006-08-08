/* Copyright 1998, 2005 The Apache Software Foundation or its licensors, as applicable
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

package org.apache.harmony.luni.tests.java.lang;

public class LinkageErrorTest extends junit.framework.TestCase {

	/**
	 * @tests java.lang.LinkageError#LinkageError()
	 */
	public void test_Constructor() {
		try {
			if (true) {
				throw new LinkageError();
			}
			fail("Error not thrown.");
		} catch (LinkageError e) {
			assertEquals("Error not initialized.", 
					"java.lang.LinkageError", e.toString());
		}
	}

	/**
	 * @tests java.lang.LinkageError#LinkageError(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		// Indirect testing is done for subclasses.
		try {
			if (true) {
				throw new LinkageError("Hello World");
			}
			fail("Error not thrown.");
		} catch (LinkageError e) {
			assertTrue("Wrong error message: " + e.getMessage(), e.getMessage()
					.equals("Hello World"));
		}
	}

	protected void setUp() {
	}

	protected void tearDown() {
	}
}
