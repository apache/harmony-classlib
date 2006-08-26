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

public class UnsupportedOperationExceptionTest extends TestCase {

	private static final String SERIALIZATION_FILE_NAME =
		"serialization/java/lang/UnsupportedOperationException.ser"; //$NON-NLS-1$
	
	/**
	 * @tests java.lang.UnsupportedOperationException#UnsupportedOperationException()
	 */
    public void test_Constructor() {
        UnsupportedOperationException e = new UnsupportedOperationException();
        assertNull(e.getMessage());
        assertNull(e.getLocalizedMessage());
        assertNull(e.getCause());
    }

    /**
     * @tests java.lang.UnsupportedOperationException#UnsupportedOperationException(java.lang.String)
     */
    public void test_ConstructorLjava_lang_String() {
        UnsupportedOperationException e = new UnsupportedOperationException("fixture");
        assertEquals("fixture", e.getMessage());
        assertNull(e.getCause());
    }
	
	/**
	 * @tests serialization/deserilazation.
	 */
	public void test_serialization() throws Exception {
		UnsupportedOperationException srcUnsupportedOperationException = new UnsupportedOperationException();
		UnsupportedOperationException destUnsupportedOperationException = (UnsupportedOperationException) SerializationTester
				.getDeserilizedObject(srcUnsupportedOperationException);
	}

	/**
	 * @tests serialization/deserilazation compatibility with RI.
	 */
	public void test_serializationCompatibility() throws Exception {
		UnsupportedOperationException srcUnsupportedOperationException = new UnsupportedOperationException();
		UnsupportedOperationException destUnsupportedOperationException = (UnsupportedOperationException) SerializationTester
				.readObject(srcUnsupportedOperationException,
						SERIALIZATION_FILE_NAME);
	}
}
