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
import java.nio.ShortBuffer;
import java.nio.InvalidMarkException;
import java.nio.ReadOnlyBufferException;

import junit.framework.TestCase;

/**
 * Tests java.nio.ShortBuffer
 *
 */
public class ShortBufferTest extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(ShortBufferTest.class);
    }

    public static void testShortBufferInstance(ShortBuffer buf) {
        // test Buffer functions
        BufferTest.testBufferInstance(buf);

        // test ShortBuffer functions
        testHashCode(buf);
        testEquals(buf);
        testToString(buf);
        testSlice(buf);
        testDuplicate(buf);
        testAsReadOnlyBuffer(buf);
        testGet(buf);
        testPutshort(buf);
        testGetint(buf);
        testPutintshort(buf);
        testGetshortArrayintint(buf);
        testGetshortArray(buf);
        testPutShortBuffer(buf);
        testPutshortArrayintint(buf);
        testPutshortArray(buf);
        testHasArray(buf);
        testArray(buf);
        testArrayOffset(buf);
        testCompact(buf);
        testIsDirect(buf);
        testCompareTo(buf);
        testOrder(buf);
    }

    public static void testArray(ShortBuffer buf) {
        if (buf.hasArray()) {
            short array[] = buf.array();
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

    public static void testArrayOffset(ShortBuffer buf) {
        if (buf.hasArray()) {
            short array[] = buf.array();
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

    public static void testAsReadOnlyBuffer(ShortBuffer buf) {
        buf.clear();
        buf.mark();
        buf.position(buf.limit());

        // readonly's contents should be the same as buf
        ShortBuffer readonly = buf.asReadOnlyBuffer();
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

    public static void testCompact(ShortBuffer buf) {
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
        ShortBuffer ret = buf.compact();
        assertSame(ret, buf);
        assertEquals(buf.position(), buf.capacity());
        assertEquals(buf.limit(), buf.capacity());
        assertContentLikeTestData1(buf, 0, (short) 0, buf.capacity());
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
        assertContentLikeTestData1(buf, 0, (short) 0, buf.capacity());
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
        assertContentLikeTestData1(buf, 0, (short) 1, 4);
        try {
            buf.reset();
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (InvalidMarkException e) {
            // expected
        }
    }

    public static void testCompareTo(ShortBuffer buf) {
        // compare to self
        assertEquals(0, buf.compareTo(buf));

        // normal cases
        if (!buf.isReadOnly()) {
            assertTrue(buf.capacity() > 5);
            buf.clear();
            ShortBuffer other = ShortBuffer.allocate(buf.capacity());
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

    public static void testDuplicate(ShortBuffer buf) {
        buf.clear();
        buf.mark();
        buf.position(buf.limit());

        // duplicate's contents should be the same as buf
        ShortBuffer duplicate = buf.duplicate();
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

    public static void testEquals(ShortBuffer buf) {
        // equal to self
        assertTrue(buf.equals(buf));
        ShortBuffer readonly = buf.asReadOnlyBuffer();
        assertTrue(buf.equals(readonly));
        ShortBuffer duplicate = buf.duplicate();
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
     * Class under test for short get()
     */
    public static void testGet(ShortBuffer buf) {
        buf.clear();
        for (int i = 0; i < buf.capacity(); i++) {
            assertEquals(buf.position(), i);
            assertEquals(buf.get(), buf.get(i));
        }
        try {
            buf.get();
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (BufferUnderflowException e) {
            // expected
        }
    }

    /*
     * Class under test for java.nio.ShortBuffer get(short[])
     */
    public static void testGetshortArray(ShortBuffer buf) {
        short array[] = new short[1];
        buf.clear();
        for (int i = 0; i < buf.capacity(); i++) {
            assertEquals(buf.position(), i);
            ShortBuffer ret = buf.get(array);
            assertEquals(array[0], buf.get(i));
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
     * Class under test for java.nio.ShortBuffer get(short[], int, int)
     */
    public static void testGetshortArrayintint(ShortBuffer buf) {
        buf.clear();
        short array[] = new short[buf.capacity()];

        try {
            buf.get(new short[buf.capacity() + 1], 0, buf.capacity() + 1);
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
        ShortBuffer ret = buf.get(array, 0, array.length);
        assertEquals(buf.position(), buf.capacity());
        assertContentEquals(buf, array, 0, array.length);
        assertSame(ret, buf);
    }

    /*
     * Class under test for short get(int)
     */
    public static void testGetint(ShortBuffer buf) {
        buf.clear();
        for (int i = 0; i < buf.capacity(); i++) {
            assertEquals(buf.position(), i);
            assertEquals(buf.get(), buf.get(i));
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

    public static void testHasArray(ShortBuffer buf) {
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

    public static void testHashCode(ShortBuffer buf) {
        buf.clear();
        ShortBuffer readonly = buf.asReadOnlyBuffer();
        ShortBuffer duplicate = buf.duplicate();
        assertTrue(buf.hashCode() == readonly.hashCode());

        assertTrue(buf.capacity() > 5);
        duplicate.position(buf.capacity() / 2);
        assertTrue(buf.hashCode() != duplicate.hashCode());
    }

    public static void testIsDirect(ShortBuffer buf) {
        buf.isDirect();
    }

    public static void testOrder(ShortBuffer buf) {
        buf.order();
        if (buf.hasArray()) {
            assertEquals(ByteOrder.nativeOrder(), buf.order());
        }
    }

    /*
     * Class under test for java.nio.ShortBuffer put(short)
     */
    public static void testPutshort(ShortBuffer buf) {
        if (buf.isReadOnly()) {
            try {
                buf.clear();
                buf.put((short) 0);
                fail("Should throw Exception"); //$NON-NLS-1$
            } catch (ReadOnlyBufferException e) {
                // expected
            }
            return;
        }

        buf.clear();
        for (int i = 0; i < buf.capacity(); i++) {
            assertEquals(buf.position(), i);
            ShortBuffer ret = buf.put((short) i);
            assertEquals(buf.get(i), (short) i);
            assertSame(ret, buf);
        }
        try {
            buf.put((short) 0);
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (BufferOverflowException e) {
            // expected
        }
    }

    /*
     * Class under test for java.nio.ShortBuffer put(short[])
     */
    public static void testPutshortArray(ShortBuffer buf) {
        short array[] = new short[1];
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
            array[0] = (short) i;
            ShortBuffer ret = buf.put(array);
            assertEquals(buf.get(i), (short) i);
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
     * Class under test for java.nio.ShortBuffer put(short[], int, int)
     */
    public static void testPutshortArrayintint(ShortBuffer buf) {
        buf.clear();
        short array[] = new short[buf.capacity()];
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
            buf.put(new short[buf.capacity() + 1], 0, buf.capacity() + 1);
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
        ShortBuffer ret = buf.put(array, 0, array.length);
        assertEquals(buf.position(), buf.capacity());
        assertContentEquals(buf, array, 0, array.length);
        assertSame(ret, buf);
    }

    /*
     * Class under test for java.nio.ShortBuffer put(java.nio.ShortBuffer)
     */
    public static void testPutShortBuffer(ShortBuffer buf) {
        ShortBuffer other = ShortBuffer.allocate(buf.capacity());
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
            buf.put(ShortBuffer.allocate(buf.capacity() + 1));
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (BufferOverflowException e) {
            // expected
        }

        loadTestData2(other);
        other.clear();
        buf.clear();
        ShortBuffer ret = buf.put(other);
        assertEquals(other.position(), other.capacity());
        assertEquals(buf.position(), buf.capacity());
        assertContentEquals(other, buf);
        assertSame(ret, buf);
    }

    /*
     * Class under test for java.nio.ShortBuffer put(int, short)
     */
    public static void testPutintshort(ShortBuffer buf) {
        if (buf.isReadOnly()) {
            try {
                buf.put(0, (short) 0);
                fail("Should throw Exception"); //$NON-NLS-1$
            } catch (ReadOnlyBufferException e) {
                // expected
            }
            return;
        }

        buf.clear();
        for (int i = 0; i < buf.capacity(); i++) {
            assertEquals(buf.position(), 0);
            ShortBuffer ret = buf.put(i, (short) i);
            assertEquals(buf.get(i), (short) i);
            assertSame(ret, buf);
        }
        try {
            buf.put(-1, (short) 0);
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
        try {
            buf.put(buf.limit(), (short) 0);
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
    }

    public static void testSlice(ShortBuffer buf) {
        assertTrue(buf.capacity() > 5);
        buf.position(1);
        buf.limit(buf.capacity() - 1);

        ShortBuffer slice = buf.slice();
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
            assertContentLikeTestData1(buf, 1, (short) 0, slice.capacity());
            buf.put(2, (short) 500);
            assertEquals(slice.get(1), 500);
        }
    }

    public static void testToString(ShortBuffer buf) {
        String str = buf.toString();
        assertTrue(str.indexOf("Short") >= 0 || str.indexOf("short") >= 0);
        assertTrue(str.indexOf("" + buf.position()) >= 0);
        assertTrue(str.indexOf("" + buf.limit()) >= 0);
        assertTrue(str.indexOf("" + buf.capacity()) >= 0);
    }

    private static void loadTestData1(short array[], int offset, int length) {
        for (int i = 0; i < length; i++) {
            array[offset + i] = (short) i;
        }
    }

    private static void loadTestData2(short array[], int offset, int length) {
        for (int i = 0; i < length; i++) {
            array[offset + i] = (short) (length - i);
        }
    }

    private static void loadTestData1(ShortBuffer buf) {
        buf.clear();
        for (int i = 0; i < buf.capacity(); i++) {
            buf.put(i, (short) i);
        }
    }

    private static void loadTestData2(ShortBuffer buf) {
        buf.clear();
        for (int i = 0; i < buf.capacity(); i++) {
            buf.put(i, (short) (buf.capacity() - i));
        }
    }

    private static void assertContentEquals(ShortBuffer buf, short array[],
            int offset, int length) {
        for (int i = 0; i < length; i++) {
            assertEquals(buf.get(i), array[offset + i]);
        }
    }

    private static void assertContentEquals(ShortBuffer buf, ShortBuffer other) {
        assertEquals(buf.capacity(), other.capacity());
        for (int i = 0; i < buf.capacity(); i++) {
            assertEquals(buf.get(i), other.get(i));
        }
    }

    private static void assertContentLikeTestData1(ShortBuffer buf,
            int startIndex, short startValue, int length) {
        short value = startValue;
        for (int i = 0; i < length; i++) {
            assertEquals(buf.get(startIndex + i), value);
            value = (short) (value + 1);
        }
    }

    public void testAllocatedShortBuffer() {
        try {
            ShortBuffer.allocate(-1);
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (IllegalArgumentException e) {
            // expected 
        }
        ShortBuffer buf = ShortBuffer.allocate(16);
        testShortBufferInstanceThoroughly(buf);
    }

    public void testWrappedShortBuffer() {
        ShortBuffer buf = ShortBuffer.wrap(new short[16]);
        testShortBufferInstanceThoroughly(buf);
    }

    public void testWrappedShortBuffer2() {
        short array[] = new short[20];
        try {
            ShortBuffer.wrap(array, -1, 0);
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
        try {
            ShortBuffer.wrap(array, 21, 0);
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
        try {
            ShortBuffer.wrap(array, 0, -1);
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
        try {
            ShortBuffer.wrap(array, 0, 21);
            fail("Should throw Exception"); //$NON-NLS-1$
        } catch (IndexOutOfBoundsException e) {
            // expected
        }

        ShortBuffer buf = ShortBuffer.wrap(array, 2, 16);
        assertEquals(buf.position(), 2);
        assertEquals(buf.limit(), 18);
        assertEquals(buf.capacity(), 20);
        testShortBufferInstanceThoroughly(buf);
    }

    public void testByteBufferAsShortBuffer() {
        ShortBuffer buf = ByteBuffer.allocate(160).asShortBuffer();
        testShortBufferInstanceThoroughly(buf);
    }

    private void testShortBufferInstanceThoroughly(ShortBuffer buf) {
        assertTrue(buf.capacity() > 15);
        buf.clear();
        loadTestData1(buf);

        buf.limit(15).position(1);
        testShortBufferInstance(buf);
        testShortBufferInstance(buf.duplicate());
        testShortBufferInstance(buf.asReadOnlyBuffer());
        buf.limit(15).position(1);
        testShortBufferInstance(buf.slice());

        ShortBuffer duplicate = buf.duplicate();
        duplicate.limit(15).position(1);
        testShortBufferInstance(duplicate.duplicate());
        testShortBufferInstance(duplicate.asReadOnlyBuffer());
        duplicate.limit(15).position(1);
        testShortBufferInstance(duplicate.slice());

        ShortBuffer readonly = buf.asReadOnlyBuffer();
        readonly.limit(15).position(1);
        testShortBufferInstance(readonly.duplicate());
        testShortBufferInstance(readonly.asReadOnlyBuffer());
        readonly.limit(15).position(1);
        testShortBufferInstance(readonly.slice());

        buf.limit(15).position(1);
        ShortBuffer slice = buf.slice();
        slice.limit(10).position(1);
        testShortBufferInstance(slice.duplicate());
        testShortBufferInstance(slice.asReadOnlyBuffer());
        slice.limit(10).position(1);
        testShortBufferInstance(slice.slice());
    }

}
