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

package org.apache.harmony.luni.tests.java.lang;

import java.util.Arrays;

import junit.framework.TestCase;

public class CharacterTest extends TestCase {

    public void test_valueOfC() {
        // test the cache range
        for (char c = '\u0000'; c < 512; c++) {
            Character e = new Character(c);
            Character a = Character.valueOf(c);
            assertEquals(e, a);
        }
        // test the rest of the chars
        for (int c = '\u0512'; c <= Character.MAX_VALUE; c++) {
            assertEquals(new Character((char) c), Character.valueOf((char) c));
        }
    }

    public void test_isValidCodePointI() {
        assertFalse(Character.isValidCodePoint(-1));
        assertTrue(Character.isValidCodePoint(0));
        assertTrue(Character.isValidCodePoint(1));
        assertFalse(Character.isValidCodePoint(Integer.MAX_VALUE));

        for (int c = '\u0000'; c <= 0x10FFFF; c++) {
            assertTrue(Character.isValidCodePoint(c));
        }

        assertFalse(Character.isValidCodePoint(0x10FFFF + 1));
    }

    public void test_isSupplementaryCodePointI() {
        assertFalse(Character.isSupplementaryCodePoint(-1));

        for (int c = '\u0000'; c <= '\uFFFF'; c++) {
            assertFalse(Character.isSupplementaryCodePoint(c));
        }

        for (int c = 0xFFFF + 1; c <= 0x10FFFF; c++) {
            assertTrue(Character.isSupplementaryCodePoint(c));
        }

        assertFalse(Character.isSupplementaryCodePoint(0x10FFFF + 1));
    }

    public void test_isHighSurrogateC() {
        // (\uD800-\uDBFF)
        assertFalse(Character.isHighSurrogate((char) ('\uD800' - 1)));
        for (int c = '\uD800'; c <= '\uDBFF'; c++) {
            assertTrue(Character.isHighSurrogate((char) c));
        }
        assertFalse(Character.isHighSurrogate((char) ('\uDBFF' + 1)));
        assertFalse(Character.isHighSurrogate('\uFFFF'));
    }

    public void test_isLowSurrogateC() {
        // (\uDC00-\uDFFF)
        assertFalse(Character.isLowSurrogate((char) ('\uDC00' - 1)));
        for (int c = '\uDC00'; c <= '\uDFFF'; c++) {
            assertTrue(Character.isLowSurrogate((char) c));
        }
        assertFalse(Character.isLowSurrogate((char) ('\uDFFF' + 1)));
    }

    public void test_isSurrogatePairCC() {
        assertFalse(Character.isSurrogatePair('\u0000', '\u0000'));
        assertFalse(Character.isSurrogatePair('\u0000', '\uDC00'));

        assertTrue(Character.isSurrogatePair('\uD800', '\uDC00'));
        assertTrue(Character.isSurrogatePair('\uD800', '\uDFFF'));
        assertTrue(Character.isSurrogatePair('\uDBFF', '\uDFFF'));

        assertFalse(Character.isSurrogatePair('\uDBFF', '\uF000'));
    }

    public void test_charCountI() {

        for (int c = '\u0000'; c <= '\uFFFF'; c++) {
            assertEquals(1, Character.charCount(c));
        }

        for (int c = 0xFFFF + 1; c <= 0x10FFFF; c++) {
            assertEquals(2, Character.charCount(c));
        }

        // invalid code points work in this method
        assertEquals(2, Character.charCount(Integer.MAX_VALUE));
    }

    public void test_toCodePointCC() {
        int result = Character.toCodePoint('\uD800', '\uDC00');
        assertEquals(0x00010000, result);

        result = Character.toCodePoint('\uD800', '\uDC01');
        assertEquals(0x00010001, result);

        result = Character.toCodePoint('\uD801', '\uDC01');
        assertEquals(0x00010401, result);

        result = Character.toCodePoint('\uDBFF', '\uDFFF');
        assertEquals(0x00010FFFF, result);
    }

    public void test_codePointAtLjava_lang_CharSequenceI() {

        assertEquals('a', Character.codePointAt((CharSequence) "abc", 0));
        assertEquals('b', Character.codePointAt((CharSequence) "abc", 1));
        assertEquals('c', Character.codePointAt((CharSequence) "abc", 2));
        assertEquals(0x10000, Character.codePointAt(
                (CharSequence) "\uD800\uDC00", 0));
        assertEquals('\uDC00', Character.codePointAt(
                (CharSequence) "\uD800\uDC00", 1));

        try {
            Character.codePointAt((CharSequence) null, 0);
            fail("No NPE.");
        } catch (NullPointerException e) {
        }

        try {
            Character.codePointAt((CharSequence) "abc", -1);
            fail("No IOOBE, negative index.");
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            Character.codePointAt((CharSequence) "abc", 4);
            fail("No IOOBE, index too large.");
        } catch (IndexOutOfBoundsException e) {
        }
    }

    public void test_codePointAt$CI() {

        assertEquals('a', Character.codePointAt("abc".toCharArray(), 0));
        assertEquals('b', Character.codePointAt("abc".toCharArray(), 1));
        assertEquals('c', Character.codePointAt("abc".toCharArray(), 2));
        assertEquals(0x10000, Character.codePointAt("\uD800\uDC00"
                .toCharArray(), 0));
        assertEquals('\uDC00', Character.codePointAt("\uD800\uDC00"
                .toCharArray(), 1));

        try {
            Character.codePointAt((char[]) null, 0);
            fail("No NPE.");
        } catch (NullPointerException e) {
        }

        try {
            Character.codePointAt("abc".toCharArray(), -1);
            fail("No IOOBE, negative index.");
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            Character.codePointAt("abc".toCharArray(), 4);
            fail("No IOOBE, index too large.");
        } catch (IndexOutOfBoundsException e) {
        }
    }

    public void test_codePointAt$CII() {

        assertEquals('a', Character.codePointAt("abc".toCharArray(), 0, 3));
        assertEquals('b', Character.codePointAt("abc".toCharArray(), 1, 3));
        assertEquals('c', Character.codePointAt("abc".toCharArray(), 2, 3));
        assertEquals(0x10000, Character.codePointAt("\uD800\uDC00"
                .toCharArray(), 0, 2));
        assertEquals('\uDC00', Character.codePointAt("\uD800\uDC00"
                .toCharArray(), 1, 2));
        assertEquals('\uD800', Character.codePointAt("\uD800\uDC00"
                .toCharArray(), 0, 1));

        try {
            Character.codePointAt((char[]) null, 0, 1);
            fail("No NPE.");
        } catch (NullPointerException e) {
        }

        try {
            Character.codePointAt("abc".toCharArray(), -1, 3);
            fail("No IOOBE, negative index.");
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            Character.codePointAt("abc".toCharArray(), 4, 3);
            fail("No IOOBE, index too large.");
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            Character.codePointAt("abc".toCharArray(), 2, 1);
            fail("No IOOBE, index larger than limit.");
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            Character.codePointAt("abc".toCharArray(), 2, -1);
            fail("No IOOBE, limit is negative.");
        } catch (IndexOutOfBoundsException e) {
        }
    }

    public void test_codePointBeforeLjava_lang_CharSequenceI() {

        assertEquals('a', Character.codePointBefore((CharSequence) "abc", 1));
        assertEquals('b', Character.codePointBefore((CharSequence) "abc", 2));
        assertEquals('c', Character.codePointBefore((CharSequence) "abc", 3));
        assertEquals(0x10000, Character.codePointBefore(
                (CharSequence) "\uD800\uDC00", 2));
        assertEquals('\uD800', Character.codePointBefore(
                (CharSequence) "\uD800\uDC00", 1));

        try {
            Character.codePointBefore((CharSequence) null, 0);
            fail("No NPE.");
        } catch (NullPointerException e) {
        }

        try {
            Character.codePointBefore((CharSequence) "abc", 0);
            fail("No IOOBE, index below one.");
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            Character.codePointBefore((CharSequence) "abc", 4);
            fail("No IOOBE, index too large.");
        } catch (IndexOutOfBoundsException e) {
        }
    }

    public void test_codePointBefore$CI() {

        assertEquals('a', Character.codePointBefore("abc".toCharArray(), 1));
        assertEquals('b', Character.codePointBefore("abc".toCharArray(), 2));
        assertEquals('c', Character.codePointBefore("abc".toCharArray(), 3));
        assertEquals(0x10000, Character.codePointBefore("\uD800\uDC00"
                .toCharArray(), 2));
        assertEquals('\uD800', Character.codePointBefore("\uD800\uDC00"
                .toCharArray(), 1));

        try {
            Character.codePointBefore((char[]) null, 0);
            fail("No NPE.");
        } catch (NullPointerException e) {
        }

        try {
            Character.codePointBefore("abc".toCharArray(), -1);
            fail("No IOOBE, negative index.");
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            Character.codePointBefore("abc".toCharArray(), 4);
            fail("No IOOBE, index too large.");
        } catch (IndexOutOfBoundsException e) {
        }
    }

    public void test_codePointBefore$CII() {

        assertEquals('a', Character.codePointBefore("abc".toCharArray(), 1, 0));
        assertEquals('b', Character.codePointBefore("abc".toCharArray(), 2, 0));
        assertEquals('c', Character.codePointBefore("abc".toCharArray(), 3, 0));
        assertEquals(0x10000, Character.codePointBefore("\uD800\uDC00"
                .toCharArray(), 2, 0));
        assertEquals('\uDC00', Character.codePointBefore("\uD800\uDC00"
                .toCharArray(), 2, 1));
        assertEquals('\uD800', Character.codePointBefore("\uD800\uDC00"
                .toCharArray(), 1, 0));

        try {
            Character.codePointBefore((char[]) null, 1, 0);
            fail("No NPE.");
        } catch (NullPointerException e) {
        }

        try {
            Character.codePointBefore("abc".toCharArray(), 0, 1);
            fail("No IOOBE, index less than start.");
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            Character.codePointBefore("abc".toCharArray(), 4, 0);
            fail("No IOOBE, index larger than length.");
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            Character.codePointBefore("abc".toCharArray(), 2, -1);
            fail("No IOOBE, start is negative.");
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            Character.codePointBefore("abc".toCharArray(), 2, 4);
            fail("No IOOBE, start larger than length.");
        } catch (IndexOutOfBoundsException e) {
        }
    }

    public void test_toCharsI$CI() {
        char[] dst = new char[2];
        int result = Character.toChars(0x10000, dst, 0);
        assertEquals(2, result);
        assertTrue(Arrays.equals(new char[] { '\uD800', '\uDC00' }, dst));

        result = Character.toChars(0x10001, dst, 0);
        assertEquals(2, result);
        assertTrue(Arrays.equals(new char[] { '\uD800', '\uDC01' }, dst));

        result = Character.toChars(0x10401, dst, 0);
        assertEquals(2, result);
        assertTrue(Arrays.equals(new char[] { '\uD801', '\uDC01' }, dst));

        result = Character.toChars(0x10FFFF, dst, 0);
        assertEquals(2, result);
        assertTrue(Arrays.equals(new char[] { '\uDBFF', '\uDFFF' }, dst));

        try {
            Character.toChars(Integer.MAX_VALUE, new char[2], 0);
            fail("No IAE, invalid code point.");
        } catch (IllegalArgumentException e) {
        }

        try {
            Character.toChars('a', null, 0);
            fail("No NPE, null char[].");
        } catch (NullPointerException e) {
        }

        try {
            Character.toChars('a', new char[1], -1);
            fail("No IOOBE, negative index.");
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            Character.toChars('a', new char[1], 1);
            fail("No IOOBE, index equal to length.");
        } catch (IndexOutOfBoundsException e) {
        }
    }

    public void test_toCharsI() {
        assertTrue(Arrays.equals(new char[] { '\uD800', '\uDC00' }, Character
                .toChars(0x10000)));
        assertTrue(Arrays.equals(new char[] { '\uD800', '\uDC01' }, Character
                .toChars(0x10001)));
        assertTrue(Arrays.equals(new char[] { '\uD801', '\uDC01' }, Character
                .toChars(0x10401)));
        assertTrue(Arrays.equals(new char[] { '\uDBFF', '\uDFFF' }, Character
                .toChars(0x10FFFF)));

        try {
            Character.toChars(Integer.MAX_VALUE);
            fail("No IAE, invalid code point.");
        } catch (IllegalArgumentException e) {
        }
    }

    public void test_codePointCountLjava_lang_CharSequenceII() {
        assertEquals(1, Character.codePointCount("\uD800\uDC00", 0, 2));
        assertEquals(1, Character.codePointCount("\uD800\uDC01", 0, 2));
        assertEquals(1, Character.codePointCount("\uD801\uDC01", 0, 2));
        assertEquals(1, Character.codePointCount("\uDBFF\uDFFF", 0, 2));

        assertEquals(3, Character.codePointCount("a\uD800\uDC00b", 0, 4));
        assertEquals(4, Character.codePointCount("a\uD800\uDC00b\uD800", 0, 5));

        try {
            Character.codePointCount((CharSequence) null, 0, 1);
            fail("No NPE, null char sequence.");
        } catch (NullPointerException e) {
        }

        try {
            Character.codePointCount("abc", -1, 1);
            fail("No IOOBE, negative start.");
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            Character.codePointCount("abc", 0, 4);
            fail("No IOOBE, end greater than length.");
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            Character.codePointCount("abc", 2, 1);
            fail("No IOOBE, end greater than start.");
        } catch (IndexOutOfBoundsException e) {
        }
    }

    public void test_offsetByCodePointsLjava_lang_CharSequenceII() {
        int result = Character.offsetByCodePoints("a\uD800\uDC00b", 0, 2);
        assertEquals(3, result);

        result = Character.offsetByCodePoints("abcd", 3, -1);
        assertEquals(2, result);

        result = Character.offsetByCodePoints("a\uD800\uDC00b", 0, 3);
        assertEquals(4, result);

        result = Character.offsetByCodePoints("a\uD800\uDC00b", 3, -1);
        assertEquals(1, result);

        result = Character.offsetByCodePoints("a\uD800\uDC00b", 3, 0);
        assertEquals(3, result);

        result = Character.offsetByCodePoints("\uD800\uDC00bc", 3, 0);
        assertEquals(3, result);

        result = Character.offsetByCodePoints("a\uDC00bc", 3, -1);
        assertEquals(2, result);

        result = Character.offsetByCodePoints("a\uD800bc", 3, -1);
        assertEquals(2, result);

        try {
            Character.offsetByCodePoints((CharSequence) null, 0, 1);
            fail();
        } catch (NullPointerException e) {
        }

        try {
            Character.offsetByCodePoints("abc", -1, 1);
            fail();
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            Character.offsetByCodePoints("abc", 4, 1);
            fail();
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            Character.offsetByCodePoints("abc", 1, 3);
            fail();
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            Character.offsetByCodePoints("abc", 1, -2);
            fail();
        } catch (IndexOutOfBoundsException e) {
        }
    }

    public void test_offsetByCodePoints$CIIII() {
        int result = Character.offsetByCodePoints("a\uD800\uDC00b"
                .toCharArray(), 0, 4, 0, 2);
        assertEquals(3, result);

        result = Character.offsetByCodePoints("a\uD800\uDC00b".toCharArray(),
                0, 4, 0, 3);
        assertEquals(4, result);

        result = Character.offsetByCodePoints("a\uD800\uDC00b\uD800c"
                .toCharArray(), 0, 5, 0, 3);
        assertEquals(4, result);

        result = Character
                .offsetByCodePoints("abcd".toCharArray(), 0, 4, 3, -1);
        assertEquals(2, result);

        result = Character
                .offsetByCodePoints("abcd".toCharArray(), 1, 2, 3, -2);
        assertEquals(1, result);

        result = Character.offsetByCodePoints("a\uD800\uDC00b".toCharArray(),
                0, 4, 3, -1);
        assertEquals(1, result);

        result = Character.offsetByCodePoints("a\uD800\uDC00b".toCharArray(),
                0, 2, 2, -1);
        assertEquals(1, result);

        result = Character.offsetByCodePoints("a\uD800\uDC00b".toCharArray(),
                0, 4, 3, 0);
        assertEquals(3, result);

        result = Character.offsetByCodePoints("\uD800\uDC00bc".toCharArray(),
                0, 4, 3, 0);
        assertEquals(3, result);

        result = Character.offsetByCodePoints("a\uDC00bc".toCharArray(), 0, 4,
                3, -1);
        assertEquals(2, result);

        result = Character.offsetByCodePoints("a\uD800bc".toCharArray(), 0, 4,
                3, -1);
        assertEquals(2, result);

        try {
            Character.offsetByCodePoints(null, 0, 4, 1, 1);
            fail();
        } catch (NullPointerException e) {
        }

        try {
            Character.offsetByCodePoints("abcd".toCharArray(), -1, 4, 1, 1);
            fail();
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            Character.offsetByCodePoints("abcd".toCharArray(), 0, -1, 1, 1);
            fail();
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            Character.offsetByCodePoints("abcd".toCharArray(), 2, 4, 1, 1);
            fail();
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            Character.offsetByCodePoints("abcd".toCharArray(), 1, 3, 0, 1);
            fail();
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            Character.offsetByCodePoints("abcd".toCharArray(), 1, 1, 3, 1);
            fail();
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            Character.offsetByCodePoints("abc".toCharArray(), 0, 3, 1, 3);
            fail();
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            Character.offsetByCodePoints("abc".toCharArray(), 0, 2, 1, 2);
            fail();
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            Character.offsetByCodePoints("abc".toCharArray(), 1, 3, 1, -2);
            fail();
        } catch (IndexOutOfBoundsException e) {
        }
    }
    
    /**
     * @tests java.lang.Character#compareTo(Character)
     */
    public void test_compareToLjava_lang_Byte() {
        final Character min = new Character(Character.MIN_VALUE);
        final Character mid = new Character((char)(Character.MAX_VALUE/2));
        final Character max = new Character(Character.MAX_VALUE);
        
        assertTrue(max.compareTo(max) == 0);
        assertTrue(min.compareTo(min) == 0);
        assertTrue(mid.compareTo(mid) == 0);
        
        assertTrue(max.compareTo(mid) > 0);
        assertTrue(max.compareTo(min) > 0);
        
        assertTrue(mid.compareTo(max) < 0);
        assertTrue(mid.compareTo(min) > 0);
        
        assertTrue(min.compareTo(mid) < 0);
        assertTrue(min.compareTo(max) < 0);
        
        try {
            min.compareTo(null);
            fail("No NPE");
        } catch (NullPointerException e) {
        }
    }
}
