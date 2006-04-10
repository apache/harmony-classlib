/* Copyright 2005 The Apache Software Foundation or its licensors, as applicable
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

package tests.api.java.security;

import java.security.InvalidParameterException;

public class InvalidParameterExceptionTest extends junit.framework.TestCase {

	/**
	 * @tests java.security.InvalidParameterException#InvalidParameterException()
	 */
	public void test_Constructor() {
		// Test for method java.security.InvalidParameterException()
		InvalidParameterException e = new InvalidParameterException();
		assertTrue("Failed toString test for constructed instance", e
				.toString().equals("java.security.InvalidParameterException"));
	}

	/**
	 * @tests java.security.InvalidParameterException#InvalidParameterException(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		// Test for method
		// java.security.InvalidParameterException(java.lang.String)
		InvalidParameterException e = new InvalidParameterException(
				"test message");
		assertTrue(
				"Failed toString test for constructed instance",
				e
						.toString()
						.equals(
								"java.security.InvalidParameterException: test message"));
	}
}