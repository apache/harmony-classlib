/* Copyright 1998, 2006 The Apache Software Foundation or its licensors, as applicable
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

import junit.framework.TestCase;
import tests.util.SerializationTester;

public class SecurityExceptionTest extends TestCase {
	
	private static final String SERIALIZATION_FILE_NAME =
		"serialization/java/lang/SecurityException.ser"; //$NON-NLS-1$
	
	/**
	 * @tests java.lang.SecurityException#SecurityException()
	 */
    public void test_Constructor() {
        SecurityException e = new SecurityException();
        assertNull(e.getMessage());
        assertNull(e.getLocalizedMessage());
        assertNull(e.getCause());
    }

    /**
     * @tests java.lang.SecurityException#SecurityException(java.lang.String)
     */
    public void test_ConstructorLjava_lang_String() {
        SecurityException e = new SecurityException("fixture");
        assertEquals("fixture", e.getMessage());
        assertNull(e.getCause());
    }

	/**
	 * @tests serialization/deserilazation.
	 */
	public void test_serialization() throws Exception {
		SecurityException srcSecurityException = new SecurityException();
		SecurityException destSecurityException = (SecurityException) SerializationTester
				.getDeserilizedObject(srcSecurityException);
	}

	/**
	 * @tests serialization/deserilazation compatibility with RI.
	 */
	public void test_serializationCompatibility() throws Exception {
		SecurityException srcSecurityException = new SecurityException();
		SecurityException destSecurityException = (SecurityException) SerializationTester
				.readObject(srcSecurityException,
						SERIALIZATION_FILE_NAME);
	}
}
