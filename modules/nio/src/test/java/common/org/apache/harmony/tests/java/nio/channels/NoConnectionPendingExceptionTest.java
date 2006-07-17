/* Copyright 2006 The Apache Software Foundation or its licensors, as applicable
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
package org.apache.harmony.tests.java.nio.channels;

import java.io.IOException;
import java.nio.channels.NoConnectionPendingException;

import junit.framework.TestCase;
import tests.util.SerializationTester;

/**
 * Tests for NoConnectionPendingException
 */
public class NoConnectionPendingExceptionTest extends TestCase {

    /**
     * @tests serialization/deserialization compatibility.
     */
    public void test_serialization() throws IOException, ClassNotFoundException {
        NoConnectionPendingException object = new NoConnectionPendingException();
        NoConnectionPendingException deObject = (NoConnectionPendingException) SerializationTester
                .getDeserilizedObject(object);
        assertNotNull(deObject);
    }

    /**
     * @tests serialization/deserialization compatibility with RI.
     */
    public void test_compatibilitySerialization() throws Exception {
        NoConnectionPendingException object = new NoConnectionPendingException();
        NoConnectionPendingException deObject = (NoConnectionPendingException) SerializationTester
                .readObject(object,
                        "serialization/java/nio/channels/NoConnectionPendingExceptionTest.golden.ser");
        assertNotNull(deObject);
    }
}
