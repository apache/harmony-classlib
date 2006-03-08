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

package org.apache.harmony.tests.java.lang;

import junit.framework.TestCase;

public class StringBufferTest extends TestCase {

	/**
	 * @tests java.lang.StringBuffer#setLength(int)
	 */
	public void test_setLengthI() {
		// Regression for HARMONY-90
		StringBuffer buffer = new StringBuffer("abcde");
		try {
			buffer.setLength(-1);
			fail("Assert 0: IndexOutOfBoundsException must be thrown");
		} catch (IndexOutOfBoundsException e) {
			// expected
		}
	}
    
    /**
     * @tests StringBuffer.StringBuffer(CharSequence);
     */
    public void test_constructorLjava_lang_CharSequence() {
        try {
            new StringBuffer((CharSequence) null);
            fail("Assert 0: NPE must be thrown.");
        } catch (NullPointerException e) {}
        
        assertEquals("Assert 1: must equal 'abc'.", "abc", new StringBuffer((CharSequence)"abc").toString());
    }
    
    public void test_trimToSize() {
        StringBuffer buffer = new StringBuffer(25);
        buffer.append("abc");
        int origCapacity = buffer.capacity();
        buffer.trimToSize();
        int trimCapacity = buffer.capacity();
        assertTrue("Assert 0: capacity must be smaller.", trimCapacity < origCapacity);
        assertEquals("Assert 1: length must still be 3", 3, buffer.length());
        assertEquals("Assert 2: value must still be 'abc'.", "abc", buffer.toString());
    }
    
    /**
     * @tests java.lang.StringBuffer.append(CharSequence)
     */
    public void test_appendLjava_lang_CharSequence() {
        StringBuffer sb = new StringBuffer();
        assertSame(sb, sb.append((CharSequence) "ab"));
        assertEquals("ab", sb.toString());
        sb.setLength(0);
        assertSame(sb, sb.append((CharSequence) "cd"));
        assertEquals("cd", sb.toString());
        sb.setLength(0);
        assertSame(sb, sb.append((CharSequence) null));
        assertEquals("null", sb.toString());
    }

    /**
     * @tests java.lang.StringBuffer.append(CharSequence, int, int)
     */
    public void test_appendLjava_lang_CharSequenceII() {
        StringBuffer sb = new StringBuffer();
        assertSame(sb, sb.append((CharSequence) "ab", 0, 2));
        assertEquals("ab", sb.toString());
        sb.setLength(0);
        assertSame(sb, sb.append((CharSequence) "cd", 0, 2));
        assertEquals("cd", sb.toString());
        sb.setLength(0);
        assertSame(sb, sb.append((CharSequence) "abcd", 0, 2));
        assertEquals("ab", sb.toString());
        sb.setLength(0);
        assertSame(sb, sb.append((CharSequence) "abcd", 2, 4));
        assertEquals("cd", sb.toString());
        sb.setLength(0);
        assertSame(sb, sb.append((CharSequence) null, 0, 2));
        assertEquals("nu", sb.toString());
    }
    
    /**
     * @tests java.lang.StringBuffer.insert(int, CharSequence)
     */
    public void test_insertILjava_lang_CharSequence() {
        final String fixture = "0000";
        StringBuffer sb = new StringBuffer(fixture);
        assertSame(sb, sb.insert(0, (CharSequence) "ab"));
        assertEquals("ab0000", sb.toString());
        assertEquals(6, sb.length());

        sb = new StringBuffer(fixture);
        assertSame(sb, sb.insert(2, (CharSequence) "ab"));
        assertEquals("00ab00", sb.toString());
        assertEquals(6, sb.length());

        sb = new StringBuffer(fixture);
        assertSame(sb, sb.insert(4, (CharSequence) "ab"));
        assertEquals("0000ab", sb.toString());
        assertEquals(6, sb.length());

        sb = new StringBuffer(fixture);
        assertSame(sb, sb.insert(4, (CharSequence) null));
        assertEquals("0000null", sb.toString());
        assertEquals(8, sb.length());

        try {
            sb = new StringBuffer(fixture);
            sb.insert(-1, (CharSequence) "ab");
            fail("no IOOBE, negative index");
        } catch (IndexOutOfBoundsException e) {
            // Expected
        }

        try {
            sb = new StringBuffer(fixture);
            sb.insert(5, (CharSequence) "ab");
            fail("no IOOBE, index too large index");
        } catch (IndexOutOfBoundsException e) {
            // Expected
        }
    }

    /**
     * @tests java.lang.StringBuffer.insert(int, CharSequence, int, int)
     */
    public void test_insertILjava_lang_CharSequenceII() {
        final String fixture = "0000";
        StringBuffer sb = new StringBuffer(fixture);
        assertSame(sb, sb.insert(0, (CharSequence) "ab", 0, 2));
        assertEquals("ab0000", sb.toString());
        assertEquals(6, sb.length());

        sb = new StringBuffer(fixture);
        assertSame(sb, sb.insert(0, (CharSequence) "ab", 0, 1));
        assertEquals("a0000", sb.toString());
        assertEquals(5, sb.length());

        sb = new StringBuffer(fixture);
        assertSame(sb, sb.insert(2, (CharSequence) "ab", 0, 2));
        assertEquals("00ab00", sb.toString());
        assertEquals(6, sb.length());

        sb = new StringBuffer(fixture);
        assertSame(sb, sb.insert(2, (CharSequence) "ab", 0, 1));
        assertEquals("00a00", sb.toString());
        assertEquals(5, sb.length());

        sb = new StringBuffer(fixture);
        assertSame(sb, sb.insert(4, (CharSequence) "ab", 0, 2));
        assertEquals("0000ab", sb.toString());
        assertEquals(6, sb.length());

        sb = new StringBuffer(fixture);
        assertSame(sb, sb.insert(4, (CharSequence) "ab", 0, 1));
        assertEquals("0000a", sb.toString());
        assertEquals(5, sb.length());

        sb = new StringBuffer(fixture);
        assertSame(sb, sb.insert(4, (CharSequence) null, 0, 2));
        assertEquals("0000nu", sb.toString());
        assertEquals(6, sb.length());

        try {
            sb = new StringBuffer(fixture);
            sb.insert(-1, (CharSequence) "ab", 0, 2);
            fail("no IOOBE, negative index");
        } catch (IndexOutOfBoundsException e) {
            // Expected
        }

        try {
            sb = new StringBuffer(fixture);
            sb.insert(5, (CharSequence) "ab", 0, 2);
            fail("no IOOBE, index too large index");
        } catch (IndexOutOfBoundsException e) {
            // Expected
        }

        try {
            sb = new StringBuffer(fixture);
            sb.insert(5, (CharSequence) "ab", -1, 2);
            fail("no IOOBE, negative offset");
        } catch (IndexOutOfBoundsException e) {
            // Expected
        }

        try {
            sb = new StringBuffer(fixture);
            sb.insert(5, new char[] { 'a', 'b' }, 0, -1);
            fail("no IOOBE, negative length");
        } catch (IndexOutOfBoundsException e) {
            // Expected
        }

        try {
            sb = new StringBuffer(fixture);
            sb.insert(5, new char[] { 'a', 'b' }, 0, 3);
            fail("no IOOBE, too long");
        } catch (IndexOutOfBoundsException e) {
            // Expected
        }
    }
}
