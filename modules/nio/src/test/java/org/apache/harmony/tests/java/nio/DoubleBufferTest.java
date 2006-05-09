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
import java.nio.DoubleBuffer;
import java.nio.InvalidMarkException;
import java.nio.ReadOnlyBufferException;

import junit.framework.TestCase;

/**
 * Tests java.nio.DoubleBuffer
 */
public class DoubleBufferTest extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(DoubleBufferTest.class);
    }

    public static void testDoubleBufferInstance(DoubleBuffer buf) {
        // test Buffer functions
        BufferTest.testBufferInstance(buf);

        // test DoubleBuffer functions
        testHashCode(buf);
        testEquals(buf);
        testToString(buf);
        testSlice(buf);
        testDuplicate(buf);
        testAsReadOnlyBuffer(buf);
        testGet(buf);
        testPutdouble(buf);
        testGetint(buf);
        testPutintdouble(buf);
        testGetdoubleArrayintint(buf);
        testGetdoubleArray(buf);
        testPutDoubleBuffer(buf);
        testPutdoubleArrayintint(buf);
        testPutdoubleArray(buf);
        testHasArray(buf);
        testArray(buf);
        testArrayOffset(buf);
        testCompact(buf);
        testIsDirect(buf);
        testCompareTo(buf);
        testOrder(buf);
        testNaNs();
    }

	/* Test with bit sequences that represent the IEEE754 doubles
	 * Positive inifinity, negative infinity, and NaN.
	 */
    public static void testNaNs() {
		long[] nans = new long[] { 0x7ff0000000000000L, 0xfff0000000000000L, 0x7ff8000000000000L};
		for (int i = 0; i < nans.length; i++) {
			long longBitsIn = nans[i];
			double dbl = Double.longBitsToDouble(longBitsIn);
			long longBitsOut = Double.doubleToRawLongBits(dbl);
			// Sanity check
			assertTrue(longBitsIn == longBitsOut);

			// Store the double and retrieve it
			ByteBuffer buffer = ByteBuffer.allocate(8);
			buffer.putDouble(dbl);
			double bufDoubleOut = buffer.getDouble(0);

			// Check the bits sequence was not normalized
			long bufLongOut = Double.doubleToRawLongBits(bufDoubleOut);
			assertTrue(longBitsIn == bufLongOut);
		}
	}
    
    public static void testArray(DoubleBuffer buf) {
        if (buf.hasArray()) {
            double array[] = buf.array();
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

    public static void testArrayOffset(DoubleBuffer buf) {
        if (buf.hasArray()) {
            double array[] = buf.array();
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

    public static void testAsReadOnlyBuffer(DoubleBuffer buf) {
        buf.clear();
        buf.mark();
        buf.position(buf.limit());

        // readonly's contents should be the same as buf
        DoubleBuffer readonly = buf.asReadOnlyBuffer();
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

    public static void testCompact(DoubleBuffer buf) {
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
        DoubleBuffer ret = buf.compact();
        assertSame(ret, buf);
        assertEquals(buf.position(), buf.capacity());
        assertEquals(buf.limit(), buf.capacity());
        assertContentLikeTestData1(buf, 0, 0.0, buf.capacity());
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
        assertContentLikeTestData1(buf, 0, 0.0, buf.capacity());
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
        assertContentLikeTestData1(buf, 0, 1.0, 4);
        try {
            buf.reset();
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (InvalidMarkException e) {
            // expected
        }
    }

    public static void testCompareTo(DoubleBuffer buf) {
        // compare to self
        assertEquals(0, buf.compareTo(buf));

        // normal cases
        if (!buf.isReadOnly()) {
            assertTrue(buf.capacity() > 5);
            buf.clear();
            DoubleBuffer other = DoubleBuffer.allocate(buf.capacity());
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

    public static void testDuplicate(DoubleBuffer buf) {
        buf.clear();
        buf.mark();
        buf.position(buf.limit());

        // duplicate's contents should be the same as buf
        DoubleBuffer duplicate = buf.duplicate();
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

    public static void testEquals(DoubleBuffer buf) {
        // equal to self
        assertTrue(buf.equals(buf));
        DoubleBuffer readonly = buf.asReadOnlyBuffer();
        assertTrue(buf.equals(readonly));
        DoubleBuffer duplicate = buf.duplicate();
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
     * Class under test for double get()
     */
    public static void testGet(DoubleBuffer buf) {
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
     * Class under test for java.nio.DoubleBuffer get(double[])
     */
    public static void testGetdoubleArray(DoubleBuffer buf) {
        double array[] = new double[1];
        buf.clear();
        for (int i = 0; i < buf.capacity(); i++) {
            assertEquals(buf.position(), i);
            DoubleBuffer ret = buf.get(array);
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
     * Class under test for java.nio.DoubleBuffer get(double[], int, int)
     */
    public static void testGetdoubleArrayintint(DoubleBuffer buf) {
        buf.clear();
        double array[] = new double[buf.capacity()];

        try {
            buf.get(new double[buf.capacity() + 1], 0, buf.capacity() + 1);
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
        DoubleBuffer ret = buf.get(array, 0, array.length);
        assertEquals(buf.position(), buf.capacity());
        assertContentEquals(buf, array, 0, array.length);
        assertSame(ret, buf);
    }

    /*
     * Class under test for double get(int)
     */
    public static void testGetint(DoubleBuffer buf) {
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

    public static void testHasArray(DoubleBuffer buf) {
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

    public static void testHashCode(DoubleBuffer buf) {
        buf.clear();
        DoubleBuffer readonly = buf.asReadOnlyBuffer();
        DoubleBuffer duplicate = buf.duplicate();
        assertTrue(buf.hashCode() == readonly.hashCode());

        assertTrue(buf.capacity() > 5);
        duplicate.position(buf.capacity() / 2);
        assertTrue(buf.hashCode() != duplicate.hashCode());
    }

    public static void testIsDirect(DoubleBuffer buf) {
        buf.isDirect();
    }

    public static void testOrder(DoubleBuffer buf) {
        buf.order();
        if (buf.hasArray()) {
            assertEquals(ByteOrder.nativeOrder(), buf.order());
        }
    }

    /*
     * Class under test for java.nio.DoubleBuffer put(double)
     */
    public static void testPutdouble(DoubleBuffer buf) {
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
            DoubleBuffer ret = buf.put((double) i);
            assertEquals(buf.get(i), (double) i, 0.0);
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
     * Class under test for java.nio.DoubleBuffer put(double[])
     */
    public static void testPutdoubleArray(DoubleBuffer buf) {
        double array[] = new double[1];
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
            array[0] = (double) i;
            DoubleBuffer ret = buf.put(array);
            assertEquals(buf.get(i), (double) i, 0.0);
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
     * Class under test for java.nio.DoubleBuffer put(double[], int, int)
     */
    public static void testPutdoubleArrayintint(DoubleBuffer buf) {
        buf.clear();
        double array[] = new double[buf.capacity()];
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
            buf.put(new double[buf.capacity() + 1], 0, buf.capacity() + 1);
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
        DoubleBuffer ret = buf.put(array, 0, array.length);
        assertEquals(buf.position(), buf.capacity());
        assertContentEquals(buf, array, 0, array.length);
        assertSame(ret, buf);
    }

    /*
     * Class under test for java.nio.DoubleBuffer put(java.nio.DoubleBuffer)
     */
    public static void testPutDoubleBuffer(DoubleBuffer buf) {
        DoubleBuffer other = DoubleBuffer.allocate(buf.capacity());
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
            buf.put(DoubleBuffer.allocate(buf.capacity() + 1));
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (BufferOverflowException e) {
            // expected
        }

        loadTestData2(other);
        other.clear();
        buf.clear();
        DoubleBuffer ret = buf.put(other);
        assertEquals(other.position(), other.capacity());
        assertEquals(buf.position(), buf.capacity());
        assertContentEquals(other, buf);
        assertSame(ret, buf);
    }

    /*
     * Class under test for java.nio.DoubleBuffer put(int, double)
     */
    public static void testPutintdouble(DoubleBuffer buf) {
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
            DoubleBuffer ret = buf.put(i, (double) i);
            assertEquals(buf.get(i), (double) i, 0.0);
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

    public static void testSlice(DoubleBuffer buf) {
        assertTrue(buf.capacity() > 5);
        buf.position(1);
        buf.limit(buf.capacity() - 1);

        DoubleBuffer slice = buf.slice();
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

    public static void testToString(DoubleBuffer buf) {
        String str = buf.toString();
        assertTrue(str.indexOf("Double") >= 0 || str.indexOf("double") >= 0);
        assertTrue(str.indexOf("" + buf.position()) >= 0);
        assertTrue(str.indexOf("" + buf.limit()) >= 0);
        assertTrue(str.indexOf("" + buf.capacity()) >= 0);
    }

    private static void loadTestData1(double array[], int offset, int length) {
        for (int i = 0; i < length; i++) {
            array[offset + i] = (double) i;
        }
    }

    private static void loadTestData2(double array[], int offset, int length) {
        for (int i = 0; i < length; i++) {
            array[offset + i] = (double) length - i;
        }
    }

    private static void loadTestData1(DoubleBuffer buf) {
        buf.clear();
        for (int i = 0; i < buf.capacity(); i++) {
            buf.put(i, (double) i);
        }
    }

    private static void loadTestData2(DoubleBuffer buf) {
        buf.clear();
        for (int i = 0; i < buf.capacity(); i++) {
            buf.put(i, (double) buf.capacity() - i);
        }
    }

    private static void assertContentEquals(DoubleBuffer buf, double array[],
            int offset, int length) {
        for (int i = 0; i < length; i++) {
            assertEquals(buf.get(i), array[offset + i], 0.01);
        }
    }

    private static void assertContentEquals(DoubleBuffer buf, DoubleBuffer other) {
        assertEquals(buf.capacity(), other.capacity());
        for (int i = 0; i < buf.capacity(); i++) {
            assertEquals(buf.get(i), other.get(i), 0.01);
        }
    }

    private static void assertContentLikeTestData1(DoubleBuffer buf,
            int startIndex, double startValue, int length) {
        double value = startValue;
        for (int i = 0; i < length; i++) {
            assertEquals(buf.get(startIndex + i), value, 0.01);
            value = value + 1.0;
        }
    }

    public void testAllocatedDoubleBuffer() {
        try {
            DoubleBuffer.allocate(-1);
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (IllegalArgumentException e) {
            // expected 
        }
        DoubleBuffer buf = DoubleBuffer.allocate(16);
        testDoubleBufferInstanceThoroughly(buf);
    }

    public void testWrappedDoubleBuffer() {
        DoubleBuffer buf = DoubleBuffer.wrap(new double[16]);
        testDoubleBufferInstanceThoroughly(buf);
    }

    public void testWrappedDoubleBuffer2() {
        double array[] = new double[20];
        try {
            DoubleBuffer.wrap(array, -1, 0);
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
        try {
            DoubleBuffer.wrap(array, 21, 0);
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
        try {
            DoubleBuffer.wrap(array, 0, -1);
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
        try {
            DoubleBuffer.wrap(array, 0, 21);
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (IndexOutOfBoundsException e) {
            // expected
        }

        DoubleBuffer buf = DoubleBuffer.wrap(array, 2, 16);
        assertEquals(buf.position(), 2);
        assertEquals(buf.limit(), 18);
        assertEquals(buf.capacity(), 20);
        testDoubleBufferInstanceThoroughly(buf);
    }

    public void testByteBufferAsDoubleBuffer() {
        DoubleBuffer buf = ByteBuffer.allocate(160).asDoubleBuffer();
        testDoubleBufferInstanceThoroughly(buf);
    }

    private void testDoubleBufferInstanceThoroughly(DoubleBuffer buf) {
        assertTrue(buf.capacity() > 15);
        buf.clear();
        loadTestData1(buf);

        buf.limit(15).position(1);
        testDoubleBufferInstance(buf);
        testDoubleBufferInstance(buf.duplicate());
        testDoubleBufferInstance(buf.asReadOnlyBuffer());
        buf.limit(15).position(1);
        testDoubleBufferInstance(buf.slice());

        DoubleBuffer duplicate = buf.duplicate();
        duplicate.limit(15).position(1);
        testDoubleBufferInstance(duplicate.duplicate());
        testDoubleBufferInstance(duplicate.asReadOnlyBuffer());
        duplicate.limit(15).position(1);
        testDoubleBufferInstance(duplicate.slice());

        DoubleBuffer readonly = buf.asReadOnlyBuffer();
        readonly.limit(15).position(1);
        testDoubleBufferInstance(readonly.duplicate());
        testDoubleBufferInstance(readonly.asReadOnlyBuffer());
        readonly.limit(15).position(1);
        testDoubleBufferInstance(readonly.slice());

        buf.limit(15).position(1);
        DoubleBuffer slice = buf.slice();
        slice.limit(10).position(1);
        testDoubleBufferInstance(slice.duplicate());
        testDoubleBufferInstance(slice.asReadOnlyBuffer());
        slice.limit(10).position(1);
        testDoubleBufferInstance(slice.slice());
    }

}
