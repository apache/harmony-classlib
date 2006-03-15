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

package tests.api.java.io;

public class CharConversionExceptionTest extends junit.framework.TestCase {

	/**
	 * @tests java.io.CharConversionException#CharConversionException()
	 */
	public void test_Constructor() {
		// Test for method java.io.CharConversionException()
		// Currently, there are no refs to CharConversionException so this is
		// the best test we can do
		try {
			if (true) // BB: getting around LF
				throw new java.io.CharConversionException();
			fail("Exception not thrown");
		} catch (java.io.CharConversionException e) {
			assertTrue(
					"Exception defined with no message answers non-null to getMessage()",
					e.getMessage() == null);
		}
	}

	/**
	 * @tests java.io.CharConversionException#CharConversionException(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		// Test for method java.io.CharConversionException(java.lang.String)
		try {
			if (true) // getting around LF
				throw new java.io.CharConversionException("Blah");
			fail("Exception not thrown");
		} catch (java.io.CharConversionException e) {
			assertTrue(
					"Exception defined with no message answers non-null to getMessage()",
					e.getMessage().equals("Blah"));
		}
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
