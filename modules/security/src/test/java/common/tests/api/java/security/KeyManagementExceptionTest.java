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

import java.security.KeyManagementException;

public class KeyManagementExceptionTest extends junit.framework.TestCase {

	/**
	 * @tests java.security.KeyManagementException#KeyManagementException()
	 */
	public void test_Constructor() {
		// Test for method java.security.KeyManagementException()
		KeyManagementException e = new KeyManagementException();
		assertEquals("Failed toString test for constructed instance",
				"java.security.KeyManagementException", e.toString());
	}

	/**
	 * @tests java.security.KeyManagementException#KeyManagementException(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		// Test for method
		// java.security.KeyManagementException(java.lang.String)
		KeyManagementException e = new KeyManagementException("test message");
		assertEquals("Failed toString test for constructed instance",
				"java.security.KeyManagementException: test message",
				e.toString());
	}
}