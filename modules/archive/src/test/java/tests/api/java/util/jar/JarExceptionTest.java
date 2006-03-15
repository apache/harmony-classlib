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
package tests.api.java.util.jar;



import java.io.IOException;
import java.util.jar.Manifest;

public class JarExceptionTest extends junit.framework.TestCase {

	/**
	 * @tests java.util.jar.JarException#JarException(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		// Test for method java.util.jar.JarException(java.lang.String)
		try {
			new Manifest(new java.io.ByteArrayInputStream(
					"jlkasj dl: dsklf jlks dslka : fdsfsd\n\n\n\ndsfas"
							.getBytes()));
			fail("Should have thrown exception");
		} catch (IOException e) {
			// correct
		} catch (Exception e) {
			fail("Threw the wrong exception: " + e.toString());
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
