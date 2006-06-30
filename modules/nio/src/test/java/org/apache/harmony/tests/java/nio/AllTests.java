/* Copyright 2005, 2006 The Apache Software Foundation or its licensors, as applicable
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

package org.apache.harmony.tests.java.nio;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Test suite for java.nio package
 * 
 */
public class AllTests {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static Test suite() {
        TestSuite suite = new TestSuite("Tests for java.nio");
        //$JUnit-BEGIN$
        suite.addTestSuite(InvalidMarkExceptionTest.class);
        suite.addTestSuite(ReadOnlyCharBufferTest.class);
        suite.addTestSuite(IntBufferTest.class);
        suite.addTestSuite(SliceWrappedByteBufferTest.class);
        suite.addTestSuite(ByteOrderTest.class);
        suite.addTestSuite(WrappedCharBufferTest1.class);
        suite.addTestSuite(LongBufferTest.class);
        suite.addTestSuite(HeapByteBufferTest.class);
        suite.addTestSuite(ReadOnlyDirectByteBufferTest.class);
        suite.addTestSuite(SliceDirectByteBufferTest.class);
        suite.addTestSuite(ReadOnlyWrappedCharBufferTest1.class);
        suite.addTestSuite(WrappedCharBufferTest2.class);
        suite.addTestSuite(ShortBufferTest.class);
        suite.addTestSuite(BufferUnderflowExceptionTest.class);
        suite.addTestSuite(FloatBufferTest.class);
        suite.addTestSuite(DuplicateDirectByteBufferTest.class);
        suite.addTestSuite(DirectCharBufferTest.class);
        suite.addTestSuite(ByteBufferTest.class);
        suite.addTestSuite(ReadOnlyWrappedByteBufferTest.class);
        suite.addTestSuite(DirectByteBufferTest.class);
        suite.addTestSuite(BufferOverflowExceptionTest.class);
        suite.addTestSuite(BufferTest.class);
        suite.addTestSuite(DuplicateWrappedByteBufferTest.class);
        suite.addTestSuite(WrappedByteBufferTest.class);
        suite.addTestSuite(ReadOnlyBufferExceptionTest.class);
        suite.addTestSuite(DoubleBufferTest.class);
        suite.addTestSuite(CharBufferTest.class);
        suite.addTestSuite(HeapCharBufferTest.class);
        suite.addTestSuite(DuplicateHeapByteBufferTest.class);
        suite.addTestSuite(ReadOnlyHeapByteBufferTest.class);
        suite.addTestSuite(ReadOnlyHeapCharBufferTest.class);
        suite.addTestSuite(SliceHeapByteBufferTest.class);
        //$JUnit-END$
        return suite;
    }
}
