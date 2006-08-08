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

public class RuntimeExceptionTest extends junit.framework.TestCase {

	/**
	 * @tests java.lang.RuntimeException#RuntimeException()
	 */
	public void test_Constructor() {
		// Test for method java.lang.RuntimeException()
		// Effectively tested by the subclasses

		try {
			if (true)
				throw new RuntimeException();
		} catch (RuntimeException e) {
			return;
		}
		fail("Failed to throw Runtime Exception");
	}

	/**
	 * @tests java.lang.RuntimeException#RuntimeException(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		// Test for method java.lang.RuntimeException(java.lang.String)
		// Effectively tested by the subclasses

		try {
			if (true)
				throw new RuntimeException("Runtime message");
		} catch (RuntimeException e) {
			assertEquals("Incorrect message", 
					"Runtime message", e.getMessage());
			return;
		}
		fail("Failed to throw Runtime Exception");
	}

	/**
	 * Sets up the fixture, for example, open a network connection. This method
	 * is called before a test is executed.
	 */
	protected void setUp() {
	}

	/**
	 * Tears down the fixture, for example, close a network connection. This
	 * method is called after a test is executed.
	 */
	protected void tearDown() {
	}
}
