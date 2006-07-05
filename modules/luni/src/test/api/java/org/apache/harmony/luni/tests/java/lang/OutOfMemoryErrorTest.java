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

public class OutOfMemoryErrorTest extends junit.framework.TestCase {

	/**
	 * @tests java.lang.OutOfMemoryError#OutOfMemoryError()
	 */
	public void test_Constructor() {
		// Test for method java.lang.OutOfMemoryError()
		try {
			StringBuffer large[] = new StringBuffer[100000];

			for (int i = 0; i < large.length; i++)
				large[i] = new StringBuffer(1000000);
		} catch (OutOfMemoryError e) {
			return;
		}
		fail("No error generated");
	}

	/**
	 * @tests java.lang.OutOfMemoryError#OutOfMemoryError(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		// Test for method java.lang.OutOfMemoryError(java.lang.String)
		try {
			StringBuffer large[] = new StringBuffer[100000];

			for (int i = 0; i < large.length; i++)
				large[i] = new StringBuffer(1000000);
		} catch (OutOfMemoryError e) {
			return;
		}
		fail("No error generated");
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
		System.gc();
	}
}
