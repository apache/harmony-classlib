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

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.InvalidMarkException;
import java.nio.ReadOnlyBufferException;

import junit.framework.TestCase;

/**
 * Tests java.nio.CharBuffer
 * 
 */
public class CharBufferTest extends TestCase {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(CharBufferTest.class);
	}

	public static void testCharBufferInstance(CharBuffer buf) {
		// test Buffer functions
		BufferTest.testBufferInstance(buf);

		// test CharBuffer functions
		testHashCode(buf);
		testEquals(buf);
		testToString(buf);
		testSlice(buf);
		testDuplicate(buf);
		testAsReadOnlyBuffer(buf);
		testGet(buf);
		testPutchar(buf);
		testGetint(buf);
		testPutintchar(buf);
		testGetcharArrayintint(buf);
		testGetcharArray(buf);
		testPutCharBuffer(buf);
		testPutcharArrayintint(buf);
		testPutcharArray(buf);
		testHasArray(buf);
		testArray(buf);
		testArrayOffset(buf);
		testCompact(buf);
		testIsDirect(buf);
		testCompareTo(buf);
		testOrder(buf);

		testCharAt(buf);
		testLength(buf);
		testSubSequence(buf);
		testPutString(buf);
		testPutStringintint(buf);
	}

	public static void testArray(CharBuffer buf) {
		if (buf.hasArray()) {
			char array[] = buf.array();
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

	public static void testArrayOffset(CharBuffer buf) {
		if (buf.hasArray()) {
			char array[] = buf.array();
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

	public static void testAsReadOnlyBuffer(CharBuffer buf) {
		buf.clear();
		buf.mark();
		buf.position(buf.limit());

		// readonly's contents should be the same as buf
		CharBuffer readonly = buf.asReadOnlyBuffer();
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

	public static void testCompact(CharBuffer buf) {
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
		CharBuffer ret = buf.compact();
		assertSame(ret, buf);
		assertEquals(buf.position(), buf.capacity());
		assertEquals(buf.limit(), buf.capacity());
		assertContentLikeTestData1(buf, 0, (char) 0, buf.capacity());
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
		assertContentLikeTestData1(buf, 0, (char) 0, buf.capacity());
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
		assertContentLikeTestData1(buf, 0, (char) 1, 4);
		try {
			buf.reset();
			fail("Should throw Exception"); //$NON-NLS-1$
		} catch (InvalidMarkException e) {
			// expected
		}
	}

	public static void testCompareTo(CharBuffer buf) {
		// compare to bad type
		try {
			buf.compareTo(ByteBuffer.allocate(10));
			fail("Should throw Exception"); //$NON-NLS-1$
		} catch (ClassCastException e) {
			// expected
		}

		// compare to self
		assertEquals(0, buf.compareTo(buf));

		// normal cases
		if (!buf.isReadOnly()) {
			assertTrue(buf.capacity() > 5);
			buf.clear();
			CharBuffer other = CharBuffer.allocate(buf.capacity());
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

	public static void testDuplicate(CharBuffer buf) {
		buf.clear();
		buf.mark();
		buf.position(buf.limit());

		// duplicate's contents should be the same as buf
		CharBuffer duplicate = buf.duplicate();
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

	public static void testEquals(CharBuffer buf) {
		// equal to self
		assertTrue(buf.equals(buf));
		CharBuffer readonly = buf.asReadOnlyBuffer();
		assertTrue(buf.equals(readonly));
		CharBuffer duplicate = buf.duplicate();
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
	 * Class under test for char get()
	 */
	public static void testGet(CharBuffer buf) {
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
	 * Class under test for java.nio.CharBuffer get(char[])
	 */
	public static void testGetcharArray(CharBuffer buf) {
		char array[] = new char[1];
		buf.clear();
		for (int i = 0; i < buf.capacity(); i++) {
			assertEquals(buf.position(), i);
			CharBuffer ret = buf.get(array);
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
	 * Class under test for java.nio.CharBuffer get(char[], int, int)
	 */
	public static void testGetcharArrayintint(CharBuffer buf) {
		buf.clear();
		char array[] = new char[buf.capacity()];

		try {
			buf.get(new char[buf.capacity() + 1], 0, buf.capacity() + 1);
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
		CharBuffer ret = buf.get(array, 0, array.length);
		assertEquals(buf.position(), buf.capacity());
		assertContentEquals(buf, array, 0, array.length);
		assertSame(ret, buf);
	}

	/*
	 * Class under test for char get(int)
	 */
	public static void testGetint(CharBuffer buf) {
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

	public static void testHasArray(CharBuffer buf) {
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

	public static void testHashCode(CharBuffer buf) {
		buf.clear();
		CharBuffer readonly = buf.asReadOnlyBuffer();
		CharBuffer duplicate = buf.duplicate();
		assertTrue(buf.hashCode() == readonly.hashCode());

		assertTrue(buf.capacity() > 5);
		duplicate.position(buf.capacity() / 2);
		assertTrue(buf.hashCode() != duplicate.hashCode());
	}

	public static void testIsDirect(CharBuffer buf) {
		buf.isDirect();
	}

	public static void testOrder(CharBuffer buf) {
		buf.order();
		if (buf.hasArray()) {
			assertEquals(ByteOrder.nativeOrder(), buf.order());
		}
	}

	/*
	 * Class under test for java.nio.CharBuffer put(char)
	 */
	public static void testPutchar(CharBuffer buf) {
		if (buf.isReadOnly()) {
			try {
				buf.clear();
				buf.put((char) 0);
				fail("Should throw Exception"); //$NON-NLS-1$
			} catch (ReadOnlyBufferException e) {
				// expected
			}
			return;
		}

		buf.clear();
		for (int i = 0; i < buf.capacity(); i++) {
			assertEquals(buf.position(), i);
			CharBuffer ret = buf.put((char) i);
			assertEquals(buf.get(i), (char) i);
			assertSame(ret, buf);
		}
		try {
			buf.put((char) 0);
			fail("Should throw Exception"); //$NON-NLS-1$
		} catch (BufferOverflowException e) {
			// expected
		}
	}

	/*
	 * Class under test for java.nio.CharBuffer put(char[])
	 */
	public static void testPutcharArray(CharBuffer buf) {
		char array[] = new char[1];
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
			array[0] = (char) i;
			CharBuffer ret = buf.put(array);
			assertEquals(buf.get(i), (char) i);
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
	 * Class under test for java.nio.CharBuffer put(char[], int, int)
	 */
	public static void testPutcharArrayintint(CharBuffer buf) {
		buf.clear();
		char array[] = new char[buf.capacity()];
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
			buf.put(new char[buf.capacity() + 1], 0, buf.capacity() + 1);
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
		CharBuffer ret = buf.put(array, 0, array.length);
		assertEquals(buf.position(), buf.capacity());
		assertContentEquals(buf, array, 0, array.length);
		assertSame(ret, buf);
	}

	/*
	 * Class under test for java.nio.CharBuffer put(java.nio.CharBuffer)
	 */
	public static void testPutCharBuffer(CharBuffer buf) {
		CharBuffer other = CharBuffer.allocate(buf.capacity());
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
			buf.put(CharBuffer.allocate(buf.capacity() + 1));
			fail("Should throw Exception"); //$NON-NLS-1$
		} catch (BufferOverflowException e) {
			// expected
		}

		loadTestData2(other);
		other.clear();
		buf.clear();
		CharBuffer ret = buf.put(other);
		assertEquals(other.position(), other.capacity());
		assertEquals(buf.position(), buf.capacity());
		assertContentEquals(other, buf);
		assertSame(ret, buf);
	}

	/*
	 * Class under test for java.nio.CharBuffer put(int, char)
	 */
	public static void testPutintchar(CharBuffer buf) {
		if (buf.isReadOnly()) {
			try {
				buf.put(0, (char) 0);
				fail("Should throw Exception"); //$NON-NLS-1$
			} catch (ReadOnlyBufferException e) {
				// expected
			}
			return;
		}

		buf.clear();
		for (int i = 0; i < buf.capacity(); i++) {
			assertEquals(buf.position(), 0);
			CharBuffer ret = buf.put(i, (char) i);
			assertEquals(buf.get(i), (char) i);
			assertSame(ret, buf);
		}
		try {
			buf.put(-1, (char) 0);
			fail("Should throw Exception"); //$NON-NLS-1$
		} catch (IndexOutOfBoundsException e) {
			// expected
		}
		try {
			buf.put(buf.limit(), (char) 0);
			fail("Should throw Exception"); //$NON-NLS-1$
		} catch (IndexOutOfBoundsException e) {
			// expected
		}
	}

	public static void testSlice(CharBuffer buf) {
		assertTrue(buf.capacity() > 5);
		buf.position(1);
		buf.limit(buf.capacity() - 1);

		CharBuffer slice = buf.slice();
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
			assertContentLikeTestData1(buf, 1, (char) 0, slice.capacity());
			buf.put(2, (char) 500);
			assertEquals(slice.get(1), 500);
		}
	}

	public static void testToString(CharBuffer buf) {
		String expected = "";
		for (int i = buf.position(); i < buf.limit(); i++) {
			expected += buf.get(i);
		}
		String str = buf.toString();
		assertEquals(expected, str);
	}

	public static void testCharAt(CharBuffer buf) {
		for (int i = 0; i < buf.remaining(); i++) {
			assertEquals(buf.get(buf.position() + i), buf.charAt(i));
		}
		try {
			buf.charAt(-1);
			fail("Should throw Exception"); //$NON-NLS-1$
		} catch (IndexOutOfBoundsException e) {
			// expected
		}
		try {
			buf.charAt(buf.remaining());
			fail("Should throw Exception"); //$NON-NLS-1$
		} catch (IndexOutOfBoundsException e) {
			// expected
		}
	}

	public static void testLength(CharBuffer buf) {
		assertEquals(buf.length(), buf.remaining());
	}

	public static void testSubSequence(CharBuffer buf) {
		try {
			buf.subSequence(-1, buf.length());
			fail("Should throw Exception"); //$NON-NLS-1$
		} catch (IndexOutOfBoundsException e) {
			// expected
		}
		try {
			buf.subSequence(buf.length() + 1, buf.length() + 1);
			fail("Should throw Exception"); //$NON-NLS-1$
		} catch (IndexOutOfBoundsException e) {
			// expected
		}
		assertEquals(buf.subSequence(buf.length(), buf.length()).length(), 0);
		try {
			buf.subSequence(1, 0);
			fail("Should throw Exception"); //$NON-NLS-1$
		} catch (IndexOutOfBoundsException e) {
			// expected
		}
		try {
			buf.subSequence(1, buf.length() + 1);
			fail("Should throw Exception"); //$NON-NLS-1$
		} catch (IndexOutOfBoundsException e) {
			// expected
		}

		assertEquals(buf.subSequence(0, buf.length()).toString(), buf
				.toString());

		if (buf.length() >= 2) {
			assertEquals(buf.subSequence(1, buf.length() - 1).toString(), buf
					.toString().substring(1, buf.length() - 1));
		}
	}

	public static void testPutString(CharBuffer buf) {
		String str = " ";
		if (buf.isReadOnly()) {
			try {
				buf.put(str);
				fail("Should throw Exception"); //$NON-NLS-1$
			} catch (ReadOnlyBufferException e) {
				// expected
			}
			return;
		}

		buf.clear();
		for (int i = 0; i < buf.capacity(); i++) {
			assertEquals(buf.position(), i);
			str = "" + (char) i;
			CharBuffer ret = buf.put(str);
			assertEquals(buf.get(i), (char) i);
			assertSame(ret, buf);
		}
		try {
			buf.put(str);
			fail("Should throw Exception"); //$NON-NLS-1$
		} catch (BufferOverflowException e) {
			// expected
		}
	}

	public static void testPutStringintint(CharBuffer buf) {
		buf.clear();
		String str = String.valueOf(new char[buf.capacity()]);
		if (buf.isReadOnly()) {
			try {
				buf.put(str, 0, str.length());
				fail("Should throw Exception"); //$NON-NLS-1$
			} catch (ReadOnlyBufferException e) {
				// expected
			}
			return;
		}

		try {
			buf.put(String.valueOf(new char[buf.capacity() + 1]), 0, buf
					.capacity() + 1);
			fail("Should throw Exception"); //$NON-NLS-1$
		} catch (BufferOverflowException e) {
			// expected
		}
		assertEquals(buf.position(), 0);
		buf.clear();
		try {
			buf.put(str, -1, str.length());
			fail("Should throw Exception"); //$NON-NLS-1$
		} catch (IndexOutOfBoundsException e) {
			// expected
		}
		try {
			buf.put(str, str.length() + 1, str.length() + 2);
			fail("Should throw Exception"); //$NON-NLS-1$
		} catch (IndexOutOfBoundsException e) {
			// expected
		}
		buf.put(str, str.length(), str.length());
		assertEquals(buf.position(), 0);
		try {
			buf.put(str, 2, 1);
			fail("Should throw Exception"); //$NON-NLS-1$
		} catch (IndexOutOfBoundsException e) {
			// expected
		}
		try {
			buf.put(str, 2, str.length() + 1);
			fail("Should throw Exception"); //$NON-NLS-1$
		} catch (IndexOutOfBoundsException e) {
			// expected
		}
		assertEquals(buf.position(), 0);

		char array[] = new char[buf.capacity()];
		loadTestData2(array, 0, array.length);
		str = String.valueOf(array);

		CharBuffer ret = buf.put(str, 0, str.length());
		assertEquals(buf.position(), buf.capacity());
		assertContentEquals(buf, str.toCharArray(), 0, str.length());
		assertSame(ret, buf);
	}

	private static void loadTestData1(char array[], int offset, int length) {
		for (int i = 0; i < length; i++) {
			array[offset + i] = (char) i;
		}
	}

	private static void loadTestData2(char array[], int offset, int length) {
		for (int i = 0; i < length; i++) {
			array[offset + i] = (char) (length - i);
		}
	}

	private static void loadTestData1(CharBuffer buf) {
		buf.clear();
		for (int i = 0; i < buf.capacity(); i++) {
			buf.put(i, (char) i);
		}
	}

	private static void loadTestData2(CharBuffer buf) {
		buf.clear();
		for (int i = 0; i < buf.capacity(); i++) {
			buf.put(i, (char) (buf.capacity() - i));
		}
	}

	private static void assertContentEquals(CharBuffer buf, char array[],
			int offset, int length) {
		for (int i = 0; i < length; i++) {
			assertEquals(buf.get(i), array[offset + i]);
		}
	}

	private static void assertContentEquals(CharBuffer buf, CharBuffer other) {
		assertEquals(buf.capacity(), other.capacity());
		for (int i = 0; i < buf.capacity(); i++) {
			assertEquals(buf.get(i), other.get(i));
		}
	}

	private static void assertContentLikeTestData1(CharBuffer buf,
			int startIndex, char startValue, int length) {
		char value = startValue;
		for (int i = 0; i < length; i++) {
			assertEquals(buf.get(startIndex + i), value);
			value = (char) (value + 1);
		}
	}

	private static void assertContentLikeTestData1(char array[],
			int startIndex, char startValue, int length) {
		char value = startValue;
		for (int i = 0; i < length; i++) {
			assertEquals(array[startIndex + i], value);
			value = (char) (value + 1);
		}
	}

	public void testAllocatedCharBuffer() {
		try {
			CharBuffer.allocate(-1);
			fail("Should throw Exception"); //$NON-NLS-1$
		} catch (IllegalArgumentException e) {
			// expected
		}
		CharBuffer buf = CharBuffer.allocate(16);
		testCharBufferInstanceThoroughly(buf);
	}

	public void testWrappedCharBuffer() {
		CharBuffer buf = CharBuffer.wrap(new char[16]);
		testCharBufferInstanceThoroughly(buf);
	}

	public void testWrappedCharBuffer2() {
		char array[] = new char[20];
		try {
			CharBuffer.wrap(array, -1, 0);
			fail("Should throw Exception"); //$NON-NLS-1$
		} catch (IndexOutOfBoundsException e) {
			// expected
		}
		try {
			CharBuffer.wrap(array, 21, 0);
			fail("Should throw Exception"); //$NON-NLS-1$
		} catch (IndexOutOfBoundsException e) {
			// expected
		}
		try {
			CharBuffer.wrap(array, 0, -1);
			fail("Should throw Exception"); //$NON-NLS-1$
		} catch (IndexOutOfBoundsException e) {
			// expected
		}
		try {
			CharBuffer.wrap(array, 0, 21);
			fail("Should throw Exception"); //$NON-NLS-1$
		} catch (IndexOutOfBoundsException e) {
			// expected
		}

		CharBuffer buf = CharBuffer.wrap(array, 2, 16);
		assertEquals(buf.position(), 2);
		assertEquals(buf.limit(), 18);
		assertEquals(buf.capacity(), 20);
		testCharBufferInstanceThoroughly(buf);
	}

	public void testWrappedCharSequence() {
		CharBuffer buf = CharBuffer.wrap("123456789abcdef12345");
		testCharBufferInstanceThoroughly(buf);
	}

	public void testWrappedCharSequence2() {
		String str = "123456789abcdef12345";
		try {
			CharBuffer.wrap(str, -1, 0);
			fail("Should throw Exception"); //$NON-NLS-1$
		} catch (IndexOutOfBoundsException e) {
			// expected
		}
		try {
			CharBuffer.wrap(str, 21, 21);
			fail("Should throw Exception"); //$NON-NLS-1$
		} catch (IndexOutOfBoundsException e) {
			// expected
		}
		try {
			CharBuffer.wrap(str, 2, 1);
			fail("Should throw Exception"); //$NON-NLS-1$
		} catch (IndexOutOfBoundsException e) {
			// expected
		}
		try {
			CharBuffer.wrap(str, 0, 21);
			fail("Should throw Exception"); //$NON-NLS-1$
		} catch (IndexOutOfBoundsException e) {
			// expected
		}

		CharBuffer buf = CharBuffer.wrap(str, 2, 16);
		assertEquals(buf.position(), 2);
		assertEquals(buf.limit(), 16);
		assertEquals(buf.capacity(), 20);
		testCharBufferInstanceThoroughly(buf);
	}

	public void testByteBufferAsCharBuffer() {
		CharBuffer buf = ByteBuffer.allocate(160).asCharBuffer();
		testCharBufferInstanceThoroughly(buf);
	}

	private void testCharBufferInstanceThoroughly(CharBuffer buf) {
		assertTrue(buf.capacity() > 15);
		buf.clear();
		if (!buf.isReadOnly()) {
			loadTestData1(buf);
		}

		buf.limit(15).position(1);
		testCharBufferInstance(buf);
		testCharBufferInstance(buf.duplicate());
		testCharBufferInstance(buf.asReadOnlyBuffer());
		buf.limit(15).position(1);
		testCharBufferInstance(buf.slice());

		CharBuffer duplicate = buf.duplicate();
		duplicate.limit(15).position(1);
		testCharBufferInstance(duplicate.duplicate());
		testCharBufferInstance(duplicate.asReadOnlyBuffer());
		duplicate.limit(15).position(1);
		testCharBufferInstance(duplicate.slice());

		CharBuffer readonly = buf.asReadOnlyBuffer();
		readonly.limit(15).position(1);
		testCharBufferInstance(readonly.duplicate());
		testCharBufferInstance(readonly.asReadOnlyBuffer());
		readonly.limit(15).position(1);
		testCharBufferInstance(readonly.slice());

		buf.limit(15).position(1);
		CharBuffer slice = buf.slice();
		slice.limit(10).position(1);
		testCharBufferInstance(slice.duplicate());
		testCharBufferInstance(slice.asReadOnlyBuffer());
		slice.limit(10).position(1);
		testCharBufferInstance(slice.slice());
	}

	public void testAppendSelf() throws Exception{
        CharBuffer cb = CharBuffer.allocate(10);
        CharBuffer cb2 = cb.duplicate();
        cb.append(cb);
        assertEquals(10, cb.position());
        cb.clear();
        assertEquals(cb2, cb);
        
        cb.put("abc");
        cb2 = cb.duplicate();
        cb.append(cb);
        assertEquals(10, cb.position());
        cb.clear();
        cb2.clear();
        assertEquals(cb2, cb);
        
        cb.put("edfg");
        cb.clear();
        cb2 = cb.duplicate();
        cb.append(cb);
        assertEquals(10, cb.position());
        cb.clear();
        cb2.clear();
        assertEquals(cb, cb2);
    }
    
	public void testAppendOverFlow() throws IOException {
		CharBuffer cb = CharBuffer.allocate(1);
		CharSequence cs = "String";
		cb.put('A');
		try {
			cb.append('C');
			fail("should throw BufferOverflowException.");
		} catch (BufferOverflowException ex) {
			// expected;
		}
		try {
			cb.append(cs);
			fail("should throw BufferOverflowException.");
		} catch (BufferOverflowException ex) {
			// expected;
		}
		try {
			cb.append(cs, 1, 2);
			fail("should throw BufferOverflowException.");
		} catch (BufferOverflowException ex) {
			// expected;
		}
	}

	public void testReadOnlyMap() throws IOException {
		CharBuffer cb = CharBuffer.wrap("ABCDE").asReadOnlyBuffer();
		CharSequence cs = "String";
		try {
			cb.append('A');
			fail("should throw ReadOnlyBufferException.");
		} catch (ReadOnlyBufferException ex) {
			// expected;
		}
		try {
			cb.append(cs);
			fail("should throw ReadOnlyBufferException.");
		} catch (ReadOnlyBufferException ex) {
			// expected;
		}
		try {
			cb.append(cs, 1, 2);
			fail("should throw ReadOnlyBufferException.");
		} catch (ReadOnlyBufferException ex) {
			// expected;
		}
		cb.append(cs, 1, 1);
	}

	public void testAppendCNormal() throws IOException {
		CharBuffer cb = CharBuffer.allocate(2);
		cb.put('A');
		assertSame(cb, cb.append('B'));
		assertEquals('B', cb.get(1));
	}

	public void testAppendCharSequenceNormal() throws IOException {
		CharBuffer cb = CharBuffer.allocate(10);
		cb.put('A');
		assertSame(cb, cb.append("String"));
		assertEquals("AString", cb.flip().toString());
		cb.append(null);
		assertEquals("null", cb.flip().toString());
	}

	public void testAppendCharSequenceIINormal() throws IOException {
		CharBuffer cb = CharBuffer.allocate(10);
		cb.put('A');
		assertSame(cb, cb.append("String", 1, 3));
		assertEquals("Atr", cb.flip().toString());

		cb.append(null, 0, 1);
		assertEquals("n", cb.flip().toString());
	}

	public void testAppendCharSequenceII_IllegalArgument() throws IOException {
		CharBuffer cb = CharBuffer.allocate(10);
		cb.append("String", 0, 0);
		cb.append("String", 2, 2);
		try {
			cb.append("String", -1, 1);
			fail("should throw IndexOutOfBoundsException.");
		} catch (IndexOutOfBoundsException ex) {
			// expected;
		}
		try {
			cb.append("String", -1, -1);
			fail("should throw IndexOutOfBoundsException.");
		} catch (IndexOutOfBoundsException ex) {
			// expected;
		}
		try {
			cb.append("String", 3, 2);
			fail("should throw IndexOutOfBoundsException.");
		} catch (IndexOutOfBoundsException ex) {
			// expected;
		}
		try {
			cb.append("String", 3, 0);
			fail("should throw IndexOutOfBoundsException.");
		} catch (IndexOutOfBoundsException ex) {
			// expected;
		}
		try {
			cb.append("String", 3, 110);
			fail("should throw IndexOutOfBoundsException.");
		} catch (IndexOutOfBoundsException ex) {
			// expected;
		}
	}

	public void testReadCharBuffer() throws IOException {
		// happy path
		CharBuffer source = CharBuffer.wrap("String");
		CharBuffer target = CharBuffer.allocate(10);
		assertEquals(6, source.read(target));
		assertEquals("String", target.flip().toString());
		// return -1 when nothing to read
		assertEquals(-1, source.read(target));
		// npe
		try {
			assertEquals(-1, source.read(null));
			fail("should throw NPE.");
		} catch (NullPointerException ex) {
			// expected;
		}

	}

	public void testReadReadOnly() throws IOException {
		CharBuffer source = CharBuffer.wrap("String");
		CharBuffer target = CharBuffer.allocate(10).asReadOnlyBuffer();
		try {
			source.read(target);
			fail("should throw ReadOnlyBufferException.");
		} catch (ReadOnlyBufferException ex) {
			// expected;
		}
		// if target has no remaining, needn't to check the isReadOnly
		target.flip();
		assertEquals(0, source.read(target));
	}

	public void testReadOverflow() throws IOException {
		CharBuffer source = CharBuffer.wrap("String");
		CharBuffer target = CharBuffer.allocate(1);
		assertEquals(1, source.read(target));
		assertEquals("S", target.flip().toString());
		assertEquals(1, source.position());
	}
    
    public void testReadSelf() throws Exception{
        CharBuffer source = CharBuffer.wrap("abuffer");
        try {
            source.read(source);
            fail("should throw IAE.");
        } catch (IllegalArgumentException e) {
        }
    }
}
