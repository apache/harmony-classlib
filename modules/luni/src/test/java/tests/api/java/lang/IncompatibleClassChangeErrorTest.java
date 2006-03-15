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

package tests.api.java.lang;

public class IncompatibleClassChangeErrorTest extends junit.framework.TestCase {

	/**
	 * @tests java.lang.IncompatibleClassChangeError#IncompatibleClassChangeError()
	 */
	public void test_Constructor() {
		try {
			if (true) {
				throw new IncompatibleClassChangeError();
			}
			fail("Error not thrown.");
		} catch (IncompatibleClassChangeError e) {
			assertTrue("Wrong message." + e.toString(), e.toString().equals(
					"java.lang.IncompatibleClassChangeError"));
		}
	}

	/**
	 * @tests java.lang.IncompatibleClassChangeError#IncompatibleClassChangeError(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		// Indirect testing done through subclasses.
		try {
			if (true)
				throw new IncompatibleClassChangeError("Hello World");
		} catch (IncompatibleClassChangeError e) {
			assertTrue("Wrong message." + e.getMessage(), e.getMessage()
					.equals("Hello World"));
		} catch (Exception e) {
			fail("Exception during test : " + e.getMessage());
		}
	}

	protected void setUp() {
	}

	protected void tearDown() {
	}
}
