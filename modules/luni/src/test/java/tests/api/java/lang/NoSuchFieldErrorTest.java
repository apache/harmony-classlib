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

public class NoSuchFieldErrorTest extends junit.framework.TestCase {

	/**
	 * @tests java.lang.NoSuchFieldError#NoSuchFieldError()
	 */
	public void test_Constructor() {
		try {
			if (true)
				throw new NoSuchFieldError();
		} catch (NoSuchFieldError e) {
			assertNull("Initializer failed.", e.getMessage());
			assertEquals("To string failed.", 
					"java.lang.NoSuchFieldError", e.toString());
		}
	}

	/**
	 * @tests java.lang.NoSuchFieldError#NoSuchFieldError(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		// No accessible method throws this error.
		try {
			if (true)
				throw new NoSuchFieldError("Hello World");
		} catch (NoSuchFieldError e) {
			assertTrue("Incorrect message: " + e.getMessage(), e.getMessage()
					.equals("Hello World"));
			return;
		} catch (Throwable e) {
			fail("Wrong error thrown : " + e.getMessage());
		}
		fail("Error not thrown.");
	}

	protected void setUp() {
	}

	protected void tearDown() {
	}
}
