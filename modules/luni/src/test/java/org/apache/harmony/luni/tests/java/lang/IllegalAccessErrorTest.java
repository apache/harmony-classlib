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

public class IllegalAccessErrorTest extends junit.framework.TestCase {

	/**
	 * @tests java.lang.IllegalAccessError#IllegalAccessError()
	 */
	public void test_Constructor() {
		try {
			if (true) {
				throw new IllegalAccessError();
			}
			fail("IllegalAccessError not thrown.");
		} catch (IllegalAccessError e) {
			assertTrue("Initializer failed." + e.toString(), e.toString()
					.equals("java.lang.IllegalAccessError"));
		} catch (Exception e) {
			fail("Exception in test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.lang.IllegalAccessError#IllegalAccessError(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		try {
			if (true)
				throw new IllegalAccessError("hello world.");
		} catch (IllegalAccessError e) {
			assertTrue("Wrong toString displayed." + e.toString(), e.toString()
					.equals("java.lang.IllegalAccessError: hello world."));
			assertTrue("Wrong message displayed." + e.getMessage(), e
					.getMessage().equals("hello world."));
			return;
		} catch (Exception e) {
			fail("Exception during test : " + e.getMessage());
		}
		fail("Error not thrown.");
	}

	protected void setUp() {
	}

	protected void tearDown() {
	}
}
