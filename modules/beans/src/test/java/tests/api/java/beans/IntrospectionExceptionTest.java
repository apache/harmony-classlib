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

package tests.api.java.beans;

import java.beans.IntrospectionException;
import java.io.IOException;

import tests.util.SerializationTester;

import junit.framework.TestCase;

/**
 * Unit test of IntrospectionException.
 */

public class IntrospectionExceptionTest extends TestCase {

    public void testConstructor() {
        String message = "IntrospectionExceptionTest";
        IntrospectionException e = new IntrospectionException(message);
        assertSame(message, e.getMessage());
    }

    public void testConstructor_MessageNull() {
        IntrospectionException e = new IntrospectionException(null);
        assertNull(e.getMessage());
    }

    public void testSerialization() throws IOException, ClassNotFoundException {
        String message = "IntrospectionExceptionTest";
        IntrospectionException e = new IntrospectionException(message);
        assertSame(message, e.getMessage());

        IntrospectionException de = (IntrospectionException) SerializationTester
                .getDeserilizedObject(e);
        assertEquals(message, de.getMessage());
    }

    public void testSerialization_Compatibility() throws Exception {
        String message = "IntrospectionExceptionTest";
        IntrospectionException e = new IntrospectionException(message);
        assertSame(message, e.getMessage());

        IntrospectionException de = (IntrospectionException) SerializationTester
                .readObject(e,
                        "tests/api/java/beans/IntrospectionException.ser");
        assertEquals(message, de.getMessage());
    }
}
