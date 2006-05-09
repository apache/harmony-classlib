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

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.InvalidMarkException;
import java.nio.ReadOnlyBufferException;

import junit.framework.TestCase;

/**
 * Tests java.nio.FloatBuffer
 * 
 */
public class FloatBufferTest extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(FloatBufferTest.class);
    }

    public static void testFloatBufferInstance(FloatBuffer buf) {
        // test Buffer functions
        BufferTest.testBufferInstance(buf);

        // test FloatBuffer functions
        testHashCode(buf);
        testEquals(buf);
        testToString(buf);
        testSlice(buf);
        testDuplicate(buf);
        testAsReadOnlyBuffer(buf);
        testGet(buf);
        testPutfloat(buf);
        testGetint(buf);
        testPutintfloat(buf);
        testGetfloatArrayintint(buf);
        testGetfloatArray(buf);
        testPutFloatBuffer(buf);
        testPutfloatArrayintint(buf);
        testPutfloatArray(buf);
        testHasArray(buf);
        testArray(buf);
        testArrayOffset(buf);
        testCompact(buf);
        testIsDirect(buf);
        testCompareTo(buf);
        testOrder(buf);
    }

    public static void testArray(FloatBuffer buf) {
        if (buf.hasArray()) {
            float array[] = buf.array();
            assertContentEquals(buf, array, buf.arrayOffset(), buf.capacity());

            loadTestData1(array, buf.arrayOffset(), buf.capacity());
            assertContentEquals(buf, array, buf.arrayOffset(), buf.capacity());

            loadTestData2(array, buf.arrayOffset(), buf.capacity());
            assertContentEquals(buf, array, buf.arrayOffset(), buf.capacity());

            loadTestData1(buf);
            assertContentEquals(buf, array, buf.arrayOffset(), buf.capacity());

            loadTestData2(buf);
            assertContentEquals(buf, array, buf.arrayOffset(), buf.capacity());
        } else {
            if (buf.isReadOnly()) {
                try {
                    buf.array();
                    fail("Should throw Exception"); //$NON-NLS-1$
                } catch (UnsupportedOperationException e) {
                    // expected
                    // Note:can not tell when to throw 
                    // UnsupportedOperationException
                    // or ReadOnlyBufferException, so catch all.
                }
            } else {
                try {
                    buf.array();
                    fail("Should throw Exception"); //$NON-NLS-1$
                } catch (UnsupportedOperationException e) {
                    // expected
                }
            }
        }
    }

    public static void testArrayOffset(FloatBuffer buf) {
        if (buf.hasArray()) {
            float array[] = buf.array();
            assertContentEquals(buf, array, buf.arrayOffset(), buf.capacity());

            loadTestData1(array, buf.arrayOffset(), buf.capacity());
            assertContentEquals(buf, array, buf.arrayOffset(), buf.capacity());

            loadTestData2(array, buf.arrayOffset(), buf.capacity());
            assertContentEquals(buf, array, buf.arrayOffset(), buf.capacity());

            loadTestData1(buf);
            assertContentEquals(buf, array, buf.arrayOffset(), buf.capacity());

            loadTestData2(buf);
            assertContentEquals(buf, array, buf.arrayOffset(), buf.capacity());
        } else {
            if (buf.isReadOnly()) {
                try {
                    buf.arrayOffset();
                    fail("Should throw Exception"); //$NON-NLS-1$
                } catch (UnsupportedOperationException e) {
                    // expected
                    // Note:can not tell when to throw 
                    // UnsupportedOperationException
                    // or ReadOnlyBufferException, so catch all.
                }
            } else {
                try {
                    buf.arrayOffset();
                    fail("Should throw Exception"); //$NON-NLS-1$
                } catch (UnsupportedOperationException e) {
                    // expected
                }
            }
        }
    }

    public static void testAsReadOnlyBuffer(FloatBuffer buf) {
        buf.clear();
        buf.mark();
        buf.position(buf.limit());

        // readonly's contents should be the same as buf
        FloatBuffer readonly = buf.asReadOnlyBuffer();
        assertNotSame(buf, readonly);
        assertTrue(readonly.isReadOnly());
        assertEquals(buf.position(), readonly.position());
        assertEquals(buf.limit(), readonly.limit());
        assertEquals(buf.isDirect(), readonly.isDirect());
        assertEquals(buf.order(), readonly.order());
        assertContentEquals(buf, readonly);

        // readonly's position, mark, and limit should be independent to buf
        readonly.reset();
        assertEquals(readonly.position(), 0);
        readonly.clear();
        assertEquals(buf.position(), buf.limit());
        buf.reset();
        assertEquals(buf.position(), 0);
    }

    public static void testCompact(FloatBuffer buf) {
        if (buf.isReadOnly()) {
            try {
                buf.compact();
                fail("Should throw Exception"); //$NON-NLS-1$
            } catch (ReadOnlyBufferException e) {
                // expected
            }
            return;
        }

        // case: buffer is full
        buf.clear();
        buf.mark();
        loadTestData1(buf);
        FloatBuffer ret = buf.compact();
        assertSame(ret, buf);
        assertEquals(buf.position(), buf.capacity());
        assertEquals(buf.limit(), buf.capacity());
        assertContentLikeTestData1(buf, 0, 0.0f, buf.capacity());
        try {
            buf.reset();
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (InvalidMarkException e) {
            // expected
        }

        // case: buffer is empty
        buf.position(0);
        buf.limit(0);
        buf.mark();
        ret = buf.compact();
        assertSame(ret, buf);
        assertEquals(buf.position(), 0);
        assertEquals(buf.limit(), buf.capacity());
        assertContentLikeTestData1(buf, 0, 0.0f, buf.capacity());
        try {
            buf.reset();
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (InvalidMarkException e) {
            // expected
        }

        // case: normal
        assertTrue(buf.capacity() > 5);
        buf.position(1);
        buf.limit(5);
        buf.mark();
        ret = buf.compact();
        assertSame(ret, buf);
        assertEquals(buf.position(), 4);
        assertEquals(buf.limit(), buf.capacity());
        assertContentLikeTestData1(buf, 0, 1.0f, 4);
        try {
            buf.reset();
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (InvalidMarkException e) {
            // expected
        }
    }

    public static void testCompareTo(FloatBuffer buf) {
        // compare to self
        assertEquals(0, buf.compareTo(buf));

        // normal cases
        if (!buf.isReadOnly()) {
            assertTrue(buf.capacity() > 5);
            buf.clear();
            FloatBuffer other = FloatBuffer.allocate(buf.capacity());
            loadTestData1(buf);
            loadTestData1(other);
            assertEquals(0, buf.compareTo(other));
            assertEquals(0, other.compareTo(buf));
            buf.position(1);
            assertTrue(buf.compareTo(other) > 0);
            assertTrue(other.compareTo(buf) < 0);
            other.position(2);
            assertTrue(buf.compareTo(other) < 0);
            assertTrue(other.compareTo(buf) > 0);
            buf.position(2);
            other.limit(5);
            assertTrue(buf.compareTo(other) > 0);
            assertTrue(other.compareTo(buf) < 0);
        }
    }

    public static void testDuplicate(FloatBuffer buf) {
        buf.clear();
        buf.mark();
        buf.position(buf.limit());

        // duplicate's contents should be the same as buf
        FloatBuffer duplicate = buf.duplicate();
        assertNotSame(buf, duplicate);
        assertEquals(buf.position(), duplicate.position());
        assertEquals(buf.limit(), duplicate.limit());
        assertEquals(buf.isReadOnly(), duplicate.isReadOnly());
        assertEquals(buf.isDirect(), duplicate.isDirect());
        assertEquals(buf.order(), duplicate.order());
        assertContentEquals(buf, duplicate);

        // duplicate's position, mark, and limit should be independent to buf
        duplicate.reset();
        assertEquals(duplicate.position(), 0);
        duplicate.clear();
        assertEquals(buf.position(), buf.limit());
        buf.reset();
        assertEquals(buf.position(), 0);

        // duplicate share the same content with buf
        if (!duplicate.isReadOnly()) {
            loadTestData1(buf);
            assertContentEquals(buf, duplicate);
            loadTestData2(duplicate);
            assertContentEquals(buf, duplicate);
        }
    }

    public static void testEquals(FloatBuffer buf) {
        // equal to self
        assertTrue(buf.equals(buf));
        FloatBuffer readonly = buf.asReadOnlyBuffer();
        assertTrue(buf.equals(readonly));
        FloatBuffer duplicate = buf.duplicate();
        assertTrue(buf.equals(duplicate));

        // always false, if type mismatch
        assertFalse(buf.equals(Boolean.TRUE));

        assertTrue(buf.capacity() > 5);

        buf.limit(buf.capacity()).position(0);
        readonly.limit(readonly.capacity()).position(1);
        assertFalse(buf.equals(readonly));

        buf.limit(buf.capacity() - 1).position(0);
        duplicate.limit(duplicate.capacity()).position(0);
        assertFalse(buf.equals(duplicate));
    }

    /*
     * Class under test for float get()
     */
    public static void testGet(FloatBuffer buf) {
        buf.clear();
        for (int i = 0; i < buf.capacity(); i++) {
            assertEquals(buf.position(), i);
            assertEquals(buf.get(), buf.get(i), 0.01);
        }
        try {
            buf.get();
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (BufferUnderflowException e) {
            // expected
        }
    }

    /*
     * Class under test for java.nio.FloatBuffer get(float[])
     */
    public static void testGetfloatArray(FloatBuffer buf) {
        float array[] = new float[1];
        buf.clear();
        for (int i = 0; i < buf.capacity(); i++) {
            assertEquals(buf.position(), i);
            FloatBuffer ret = buf.get(array);
            assertEquals(array[0], buf.get(i), 0.01);
            assertSame(ret, buf);
        }
        try {
            buf.get(array);
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (BufferUnderflowException e) {
            // expected
        }
    }

    /*
     * Class under test for java.nio.FloatBuffer get(float[], int, int)
     */
    public static void testGetfloatArrayintint(FloatBuffer buf) {
        buf.clear();
        float array[] = new float[buf.capacity()];

        try {
            buf.get(new float[buf.capacity() + 1], 0, buf.capacity() + 1);
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (BufferUnderflowException e) {
            // expected
        }
        assertEquals(buf.position(), 0);
        try {
            buf.get(array, -1, array.length);
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
        buf.get(array, array.length, 0);
        try {
            buf.get(array, array.length + 1, 1);
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
        assertEquals(buf.position(), 0);
        try {
            buf.get(array, 2, -1);
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
        try {
            buf.get(array, 2, array.length);
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
        assertEquals(buf.position(), 0);

        buf.clear();
        FloatBuffer ret = buf.get(array, 0, array.length);
        assertEquals(buf.position(), buf.capacity());
        assertContentEquals(buf, array, 0, array.length);
        assertSame(ret, buf);
    }

    /*
     * Class under test for float get(int)
     */
    public static void testGetint(FloatBuffer buf) {
        buf.clear();
        for (int i = 0; i < buf.capacity(); i++) {
            assertEquals(buf.position(), i);
            assertEquals(buf.get(), buf.get(i), 0.01);
        }
        try {
            buf.get(-1);
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
        try {
            buf.get(buf.limit());
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
    }

    public static void testHasArray(FloatBuffer buf) {
        if (buf.hasArray()) {
            assertNotNull(buf.array());
        } else {
            if (buf.isReadOnly()) {
                try {
                    buf.array();
                    fail("Should throw Exception"); //$NON-NLS-1$
                } catch (UnsupportedOperationException e) {
                    // expected
                    // Note:can not tell when to throw 
                    // UnsupportedOperationException
                    // or ReadOnlyBufferException, so catch all.
                }
            } else {
                try {
                    buf.array();
                    fail("Should throw Exception"); //$NON-NLS-1$
                } catch (UnsupportedOperationException e) {
                    // expected
                }
            }
        }
    }

    public static void testHashCode(FloatBuffer buf) {
        buf.clear();
        FloatBuffer readonly = buf.asReadOnlyBuffer();
        FloatBuffer duplicate = buf.duplicate();
        assertTrue(buf.hashCode() == readonly.hashCode());

        assertTrue(buf.capacity() > 5);
        duplicate.position(buf.capacity() / 2);
        assertTrue(buf.hashCode() != duplicate.hashCode());
    }

    public static void testIsDirect(FloatBuffer buf) {
        buf.isDirect();
    }

    public static void testOrder(FloatBuffer buf) {
        buf.order();
        if (buf.hasArray()) {
            assertEquals(ByteOrder.nativeOrder(), buf.order());
        }
    }

    /*
     * Class under test for java.nio.FloatBuffer put(float)
     */
    public static void testPutfloat(FloatBuffer buf) {
        if (buf.isReadOnly()) {
            try {
                buf.clear();
                buf.put(0);
                fail("Should throw Exception"); //$NON-NLS-1$
            } catch (ReadOnlyBufferException e) {
                // expected
            }
            return;
        }

        buf.clear();
        for (int i = 0; i < buf.capacity(); i++) {
            assertEquals(buf.position(), i);
            FloatBuffer ret = buf.put((float) i);
            assertEquals(buf.get(i), (float) i, 0.0);
            assertSame(ret, buf);
        }
        try {
            buf.put(0);
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (BufferOverflowException e) {
            // expected
        }
    }

    /*
     * Class under test for java.nio.FloatBuffer put(float[])
     */
    public static void testPutfloatArray(FloatBuffer buf) {
        float array[] = new float[1];
        if (buf.isReadOnly()) {
            try {
                buf.put(array);
                fail("Should throw Exception"); //$NON-NLS-1$
            } catch (ReadOnlyBufferException e) {
                // expected
            }
            return;
        }

        buf.clear();
        for (int i = 0; i < buf.capacity(); i++) {
            assertEquals(buf.position(), i);
            array[0] = (float) i;
            FloatBuffer ret = buf.put(array);
            assertEquals(buf.get(i), (float) i, 0.0);
            assertSame(ret, buf);
        }
        try {
            buf.put(array);
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (BufferOverflowException e) {
            // expected
        }
    }

    /*
     * Class under test for java.nio.FloatBuffer put(float[], int, int)
     */
    public static void testPutfloatArrayintint(FloatBuffer buf) {
        buf.clear();
        float array[] = new float[buf.capacity()];
        if (buf.isReadOnly()) {
            try {
                buf.put(array, 0, array.length);
                fail("Should throw Exception"); //$NON-NLS-1$
            } catch (ReadOnlyBufferException e) {
                // expected
            }
            return;
        }

        try {
            buf.put(new float[buf.capacity() + 1], 0, buf.capacity() + 1);
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (BufferOverflowException e) {
            // expected
        }
        assertEquals(buf.position(), 0);
        try {
            buf.put(array, -1, array.length);
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
        try {
            buf.put(array, array.length + 1, 0);
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
        buf.put(array, array.length, 0);
        assertEquals(buf.position(), 0);
        try {
            buf.put(array, 0, -1);
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
        try {
            buf.put(array, 2, array.length);
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
        assertEquals(buf.position(), 0);

        loadTestData2(array, 0, array.length);
        FloatBuffer ret = buf.put(array, 0, array.length);
        assertEquals(buf.position(), buf.capacity());
        assertContentEquals(buf, array, 0, array.length);
        assertSame(ret, buf);
    }

    /*
     * Class under test for java.nio.FloatBuffer put(java.nio.FloatBuffer)
     */
    public static void testPutFloatBuffer(FloatBuffer buf) {
        FloatBuffer other = FloatBuffer.allocate(buf.capacity());
        if (buf.isReadOnly()) {
            try {
                buf.clear();
                buf.put(other);
                fail("Should throw Exception"); //$NON-NLS-1$
            } catch (ReadOnlyBufferException e) {
                // expected
            }
            return;
        }

        try {
            buf.put(buf);
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (IllegalArgumentException e) {
            // expected
        }
        try {
            buf.put(FloatBuffer.allocate(buf.capacity() + 1));
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (BufferOverflowException e) {
            // expected
        }

        loadTestData2(other);
        other.clear();
        buf.clear();
        FloatBuffer ret = buf.put(other);
        assertEquals(other.position(), other.capacity());
        assertEquals(buf.position(), buf.capacity());
        assertContentEquals(other, buf);
        assertSame(ret, buf);
    }

    /*
     * Class under test for java.nio.FloatBuffer put(int, float)
     */
    public static void testPutintfloat(FloatBuffer buf) {
        if (buf.isReadOnly()) {
            try {
                buf.put(0, 0);
                fail("Should throw Exception"); //$NON-NLS-1$
            } catch (ReadOnlyBufferException e) {
                // expected
            }
            return;
        }

        buf.clear();
        for (int i = 0; i < buf.capacity(); i++) {
            assertEquals(buf.position(), 0);
            FloatBuffer ret = buf.put(i, (float) i);
            assertEquals(buf.get(i), (float) i, 0.0);
            assertSame(ret, buf);
        }
        try {
            buf.put(-1, 0);
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
        try {
            buf.put(buf.limit(), 0);
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
    }

    public static void testSlice(FloatBuffer buf) {
        assertTrue(buf.capacity() > 5);
        buf.position(1);
        buf.limit(buf.capacity() - 1);

        FloatBuffer slice = buf.slice();
        assertEquals(buf.isReadOnly(), slice.isReadOnly());
        assertEquals(buf.isDirect(), slice.isDirect());
        assertEquals(buf.order(), slice.order());
        assertEquals(slice.position(), 0);
        assertEquals(slice.limit(), buf.remaining());
        assertEquals(slice.capacity(), buf.remaining());
        try {
            slice.reset();
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (InvalidMarkException e) {
            // expected
        }

        // slice share the same content with buf
        if (!slice.isReadOnly()) {
            loadTestData1(slice);
            assertContentLikeTestData1(buf, 1, 0, slice.capacity());
            buf.put(2, 500);
            assertEquals(slice.get(1), 500, 0.0);
        }
    }

    public static void testToString(FloatBuffer buf) {
        String str = buf.toString();
        assertTrue(str.indexOf("Float") >= 0 || str.indexOf("float") >= 0);
        assertTrue(str.indexOf("" + buf.position()) >= 0);
        assertTrue(str.indexOf("" + buf.limit()) >= 0);
        assertTrue(str.indexOf("" + buf.capacity()) >= 0);
    }

    private static void loadTestData1(float array[], int offset, int length) {
        for (int i = 0; i < length; i++) {
            array[offset + i] = (float) i;
        }
    }

    private static void loadTestData2(float array[], int offset, int length) {
        for (int i = 0; i < length; i++) {
            array[offset + i] = (float) length - i;
        }
    }

    private static void loadTestData1(FloatBuffer buf) {
        buf.clear();
        for (int i = 0; i < buf.capacity(); i++) {
            buf.put(i, (float) i);
        }
    }

    private static void loadTestData2(FloatBuffer buf) {
        buf.clear();
        for (int i = 0; i < buf.capacity(); i++) {
            buf.put(i, (float) buf.capacity() - i);
        }
    }

    private static void assertContentEquals(FloatBuffer buf, float array[],
            int offset, int length) {
        for (int i = 0; i < length; i++) {
            assertEquals(buf.get(i), array[offset + i], 0.01);
        }
    }

    private static void assertContentEquals(FloatBuffer buf, FloatBuffer other) {
        assertEquals(buf.capacity(), other.capacity());
        for (int i = 0; i < buf.capacity(); i++) {
            assertEquals(buf.get(i), other.get(i), 0.01);
        }
    }

    private static void assertContentLikeTestData1(FloatBuffer buf,
            int startIndex, float startValue, int length) {
        float value = startValue;
        for (int i = 0; i < length; i++) {
            assertEquals(buf.get(startIndex + i), value, 0.01);
            value = value + 1.0f;
        }
    }

    public void testAllocatedFloatBuffer() {
        try {
            FloatBuffer.allocate(-1);
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (IllegalArgumentException e) {
            // expected 
        }
        FloatBuffer buf = FloatBuffer.allocate(16);
        testFloatBufferInstanceThoroughly(buf);
    }

    public void testWrappedFloatBuffer() {
        FloatBuffer buf = FloatBuffer.wrap(new float[16]);
        testFloatBufferInstanceThoroughly(buf);
    }

    public void testWrappedFloatBuffer2() {
        float array[] = new float[20];
        try {
            FloatBuffer.wrap(array, -1, 0);
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
        try {
            FloatBuffer.wrap(array, 21, 0);
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
        try {
            FloatBuffer.wrap(array, 0, -1);
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
        try {
            FloatBuffer.wrap(array, 0, 21);
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (IndexOutOfBoundsException e) {
            // expected
        }

        FloatBuffer buf = FloatBuffer.wrap(array, 2, 16);
        assertEquals(buf.position(), 2);
        assertEquals(buf.limit(), 18);
        assertEquals(buf.capacity(), 20);
        testFloatBufferInstanceThoroughly(buf);
    }

    public void testByteBufferAsFloatBuffer() {
        FloatBuffer buf = ByteBuffer.allocate(160).asFloatBuffer();
        testFloatBufferInstanceThoroughly(buf);
    }

    private void testFloatBufferInstanceThoroughly(FloatBuffer buf) {
        assertTrue(buf.capacity() > 15);
        buf.clear();
        loadTestData1(buf);

        buf.limit(15).position(1);
        testFloatBufferInstance(buf);
        testFloatBufferInstance(buf.duplicate());
        testFloatBufferInstance(buf.asReadOnlyBuffer());
        buf.limit(15).position(1);
        testFloatBufferInstance(buf.slice());

        FloatBuffer duplicate = buf.duplicate();
        duplicate.limit(15).position(1);
        testFloatBufferInstance(duplicate.duplicate());
        testFloatBufferInstance(duplicate.asReadOnlyBuffer());
        duplicate.limit(15).position(1);
        testFloatBufferInstance(duplicate.slice());

        FloatBuffer readonly = buf.asReadOnlyBuffer();
        readonly.limit(15).position(1);
        testFloatBufferInstance(readonly.duplicate());
        testFloatBufferInstance(readonly.asReadOnlyBuffer());
        readonly.limit(15).position(1);
        testFloatBufferInstance(readonly.slice());

        buf.limit(15).position(1);
        FloatBuffer slice = buf.slice();
        slice.limit(10).position(1);
        testFloatBufferInstance(slice.duplicate());
        testFloatBufferInstance(slice.asReadOnlyBuffer());
        slice.limit(10).position(1);
        testFloatBufferInstance(slice.slice());
    }

}