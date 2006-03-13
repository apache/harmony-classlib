/*
 * Copyright 2006 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.apache.harmony.tests.java.text;

import java.text.BreakIterator;
import java.text.CharacterIterator;
import java.util.Locale;

import junit.framework.TestCase;

public class BreakIteratorTest extends TestCase {

	public void test_next() {
		// Regression test for HARMONY-30
		BreakIterator bi = BreakIterator.getWordInstance(Locale.US);
		bi.setText("This is the test, WordInstance");
		int n = bi.first();
		n = bi.next();
		assertEquals("Assert 0: next() returns incorrect value ", 4, n);
	}

	public void test_getShort() {
		try {
			MockBreakIterator.publicGetShort(null, 0);
			fail("should throw NPE.");
		} catch (NullPointerException e) {
		}
		try {
			MockBreakIterator.publicGetShort(null, -1);
			fail("should throw NPE.");
		} catch (NullPointerException e) {
		}
		try {
			MockBreakIterator.publicGetShort(new byte[] { 0, 0, 0, 1 , 1}, -1);
			fail("should throw ArrayIndexOutOfBoundsException.");
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			MockBreakIterator.publicGetShort(new byte[] { 0, 0 }, 1);
			fail("should throw ArrayIndexOutOfBoundsException.");
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		assertEquals(0, MockBreakIterator.publicGetShort(new byte[] { 0, 0 }, 0));
		assertEquals(1, MockBreakIterator.publicGetShort(new byte[] { 0, 1 }, 0));
		assertEquals(-1, MockBreakIterator.publicGetShort(new byte[] { (byte)0xff, (byte)0xff }, 0));
		assertEquals(1, MockBreakIterator.publicGetShort(new byte[] { 1, 0, 1, 0 }, 1));
		assertEquals(1, MockBreakIterator.publicGetShort(new byte[] { 0, 0, 1, 0 }, 1));
		assertEquals(1, MockBreakIterator.publicGetShort(new byte[] { 0, 0, 1, 1 }, 1));
		assertEquals(257, MockBreakIterator.publicGetShort(new byte[] { 0, 1, 1 }, 1));
	}
	
	public void test_getInt() {
		try {
			MockBreakIterator.publicGetInt(null, 0);
			fail("should throw NPE.");
		} catch (NullPointerException e) {
		}
		try {
			MockBreakIterator.publicGetInt(null, -1);
			fail("should throw NPE.");
		} catch (NullPointerException e) {
		}
		try {
			MockBreakIterator.publicGetInt(new byte[] { 0, 0, 0, 1 , 1}, -1);
			fail("should throw ArrayIndexOutOfBoundsException.");
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			MockBreakIterator.publicGetInt(new byte[] { 0, 0, 0, 0}, 1);
			fail("should throw ArrayIndexOutOfBoundsException.");
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		assertEquals(0, MockBreakIterator.publicGetInt(new byte[] { 0, 0, 0, 0 }, 0));
		assertEquals(1, MockBreakIterator.publicGetInt(new byte[] { 0, 0, 0, 1 }, 0));
		assertEquals(-1, MockBreakIterator.publicGetInt(new byte[] { (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff }, 0));
		assertEquals(1, MockBreakIterator.publicGetInt(new byte[] { 1, 0, 0, 0, 1, 0 }, 1));
		assertEquals(1, MockBreakIterator.publicGetInt(new byte[] { 0, 0, 0, 0, 1, 0 }, 1));
		assertEquals(1, MockBreakIterator.publicGetInt(new byte[] { 0, 0, 0, 0, 1, 1 }, 1));
		assertEquals(257, MockBreakIterator.publicGetInt(new byte[] { 0, 0, 0, 1, 1 }, 1));
	}
	
	public void test_getLong() {
		try {
			MockBreakIterator.publicGetLong(null, 0);
			fail("should throw NPE.");
		} catch (NullPointerException e) {
		}
		try {
			MockBreakIterator.publicGetLong(null, -1);
			fail("should throw NPE.");
		} catch (NullPointerException e) {
		}
		try {
			MockBreakIterator.publicGetLong(new byte[] { 0, 0, 0, 0, 0, 0, 1, 1}, -1);
			fail("should throw ArrayIndexOutOfBoundsException.");
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			MockBreakIterator.publicGetLong(new byte[] { 0, 0, 0, 0, 0, 0, 1, 1}, 1);
			fail("should throw ArrayIndexOutOfBoundsException.");
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		assertEquals(0, MockBreakIterator.publicGetLong(new byte[] { 0, 0, 0, 0, 0, 0, 0, 0 }, 0));
		assertEquals(1, MockBreakIterator.publicGetLong(new byte[] { 0, 0, 0, 0, 0, 0, 0, 1 }, 0));
		assertEquals(-1, MockBreakIterator.publicGetLong(new byte[] { (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff }, 0));
		assertEquals(1, MockBreakIterator.publicGetLong(new byte[] { 1, 0, 0, 0, 0, 0, 0, 0, 1, 0 }, 1));
		assertEquals(1, MockBreakIterator.publicGetLong(new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 }, 1));
		assertEquals(1, MockBreakIterator.publicGetLong(new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 1, 1 }, 1));
		assertEquals(257, MockBreakIterator.publicGetLong(new byte[] { 0, 0, 0, 0, 0, 0, 0, 1, 1 }, 1));
	}

	private static class MockBreakIterator extends BreakIterator {
		public static int publicGetInt(byte[] buf, int offset) {
			return BreakIterator.getInt(buf, offset);
		}

		public static long publicGetLong(byte[] buf, int offset) {
			return BreakIterator.getLong(buf, offset);
		}

		public static short publicGetShort(byte[] buf, int offset) {
			return BreakIterator.getShort(buf, offset);
		}

		public int current() {
			return 0;
		}

		public int first() {
			return 0;
		}

		public int following(int offset) {
			return 0;
		}

		public CharacterIterator getText() {
			return null;
		}

		public int last() {
			return 0;
		}

		public int next() {
			return 0;
		}

		public int next(int n) {
			return 0;
		}

		public int previous() {
			return 0;
		}

		public void setText(CharacterIterator newText) {
		}
	}
}
