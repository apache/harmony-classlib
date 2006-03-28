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

public class LongTest extends TestCase {

    /**
     * @tests java.lang.Long#valueOf(long)
     */
    public void test_valueOfJ() {
        assertEquals(new Long(Long.MIN_VALUE), Long.valueOf(Long.MIN_VALUE));
        assertEquals(new Long(Long.MAX_VALUE), Long.valueOf(Long.MAX_VALUE));
        assertEquals(new Long( 0), Long.valueOf( 0));

        long lng = -128;
        while (lng < 128) {
            assertEquals(new Long(lng), Long.valueOf(lng));
            assertSame(Long.valueOf(lng), Long.valueOf(lng));
            lng++;
        }
    }
    
    /**
     * @tests java.lang.Long#hashCode()
     */
    public void test_hashCode() {
        assertEquals((int)(1L ^ (1L >>> 32)), new Long(1).hashCode());
        assertEquals((int)(2L ^ (2L >>> 32)), new Long(2).hashCode());
        assertEquals((int)(0L ^ (0L >>> 32)), new Long(0).hashCode());
        assertEquals((int)(-1L ^ (-1L >>> 32)), new Long(-1).hashCode());
    }

    /**
     * @tests java.lang.Long#Long(String)
     */
    public void test_ConstructorLjava_lang_String() {
        assertEquals(new Long(0), new Long("0"));
        assertEquals(new Long(1), new Long("1"));
        assertEquals(new Long(-1), new Long("-1"));
        
        try {
            new Long("0x1");
            fail("Expected NumberFormatException with hex string.");
        } catch (NumberFormatException e) {}

        try {
            new Long("9.2");
            fail("Expected NumberFormatException with floating point string.");
        } catch (NumberFormatException e) {}

        try {
            new Long("");
            fail("Expected NumberFormatException with empty string.");
        } catch (NumberFormatException e) {}
        
        try {
            new Long(null);
            fail("Expected NumberFormatException with null string.");
        } catch (NumberFormatException e) {}
    }

    /**
     * @tests java.lang.Long#Long
     */
    public void test_ConstructorJ() {
        assertEquals(1, new Long(1).intValue());
        assertEquals(2, new Long(2).intValue());
        assertEquals(0, new Long(0).intValue());
        assertEquals(-1, new Long(-1).intValue());
    }

    /**
     * @tests java.lang.Long#byteValue()
     */
    public void test_booleanValue() {
        assertEquals(1, new Long(1).byteValue());    
        assertEquals(2, new Long(2).byteValue());
        assertEquals(0, new Long(0).byteValue());
        assertEquals(-1, new Long(-1).byteValue());
    }

    /**
     * @tests java.lang.Long#equals(Object)
     */
    public void test_equalsLjava_lang_Object() {
        assertEquals(new Long(0), Long.valueOf(0));
        assertEquals(new Long(1), Long.valueOf(1));
        assertEquals(new Long(-1), Long.valueOf(-1));
        
        Long fixture = new Long(25);
        assertEquals(fixture, fixture);
        assertFalse(fixture.equals(null));
        assertFalse(fixture.equals("Not a Long"));
    }

    /**
     * @tests java.lang.Long#toString()
     */
    public void test_toString() {
        assertEquals("-1", new Long(-1).toString());
        assertEquals("0", new Long(0).toString());
        assertEquals("1", new Long(1).toString());
        assertEquals("-1", new Long(0xFFFFFFFF).toString());
    }

    /**
     * @tests java.lang.Long#toString
     */
    public void test_toStringJ() {
        assertEquals("-1", Long.toString(-1));
        assertEquals("0", Long.toString(0));
        assertEquals("1", Long.toString(1));
        assertEquals("-1", Long.toString(0xFFFFFFFF));
    }

    /**
     * @tests java.lang.Long#valueOf(String)
     */
    public void test_valueOfLjava_lang_String() {
        assertEquals(new Long(0), Long.valueOf("0"));
        assertEquals(new Long(1), Long.valueOf("1"));
        assertEquals(new Long(-1), Long.valueOf("-1"));
        
        try {
            Long.valueOf("0x1");
            fail("Expected NumberFormatException with hex string.");
        } catch (NumberFormatException e) {}

        try {
            Long.valueOf("9.2");
            fail("Expected NumberFormatException with floating point string.");
        } catch (NumberFormatException e) {}

        try {
            Long.valueOf("");
            fail("Expected NumberFormatException with empty string.");
        } catch (NumberFormatException e) {}
        
        try {
            Long.valueOf(null);
            fail("Expected NumberFormatException with null string.");
        } catch (NumberFormatException e) {}
    }
    
    /**
     * @tests java.lang.Long#valueOf(String,long)
     */
    public void test_valueOfLjava_lang_StringJ() {
        assertEquals(new Long(0), Long.valueOf("0", 10));
        assertEquals(new Long(1), Long.valueOf("1", 10));
        assertEquals(new Long(-1), Long.valueOf("-1", 10));
        
        //must be consistent with Character.digit()
        assertEquals(Character.digit('1', 2), Long.valueOf("1", 2).byteValue());
        assertEquals(Character.digit('F', 16), Long.valueOf("F", 16).byteValue());
        
        try {
            Long.valueOf("0x1", 10);
            fail("Expected NumberFormatException with hex string.");
        } catch (NumberFormatException e) {}

        try {
            Long.valueOf("9.2", 10);
            fail("Expected NumberFormatException with floating point string.");
        } catch (NumberFormatException e) {}

        try {
            Long.valueOf("", 10);
            fail("Expected NumberFormatException with empty string.");
        } catch (NumberFormatException e) {}
        
        try {
            Long.valueOf(null, 10);
            fail("Expected NumberFormatException with null string.");
        } catch (NumberFormatException e) {}
    }
    
    /**
     * @tests java.lang.Long#parseLong(String)
     */
    public void test_parseLongLjava_lang_String() {
        assertEquals(0, Long.parseLong("0"));
        assertEquals(1, Long.parseLong("1"));
        assertEquals(-1, Long.parseLong("-1"));
        
        try {
            Long.parseLong("0x1");
            fail("Expected NumberFormatException with hex string.");
        } catch (NumberFormatException e) {}

        try {
            Long.parseLong("9.2");
            fail("Expected NumberFormatException with floating point string.");
        } catch (NumberFormatException e) {}

        try {
            Long.parseLong("");
            fail("Expected NumberFormatException with empty string.");
        } catch (NumberFormatException e) {}
        
        try {
            Long.parseLong(null);
            fail("Expected NumberFormatException with null string.");
        } catch (NumberFormatException e) {}
    }
    
    /**
     * @tests java.lang.Long#parseLong(String,long)
     */
    public void test_parseLongLjava_lang_StringJ() {
        assertEquals(0, Long.parseLong("0", 10));
        assertEquals(1, Long.parseLong("1", 10));
        assertEquals(-1, Long.parseLong("-1", 10));
        
        //must be consistent with Character.digit()
        assertEquals(Character.digit('1', 2), Long.parseLong("1", 2));
        assertEquals(Character.digit('F', 16), Long.parseLong("F", 16));
        
        try {
            Long.parseLong("0x1", 10);
            fail("Expected NumberFormatException with hex string.");
        } catch (NumberFormatException e) {}

        try {
            Long.parseLong("9.2", 10);
            fail("Expected NumberFormatException with floating point string.");
        } catch (NumberFormatException e) {}

        try {
            Long.parseLong("", 10);
            fail("Expected NumberFormatException with empty string.");
        } catch (NumberFormatException e) {}
        
        try {
            Long.parseLong(null, 10);
            fail("Expected NumberFormatException with null string.");
        } catch (NumberFormatException e) {}
    }
    
    /**
     * @tests java.lang.Long#decode(String)
     */
    public void test_decodeLjava_lang_String() {
        assertEquals(new Long(0), Long.decode("0"));
        assertEquals(new Long(1), Long.decode("1"));
        assertEquals(new Long(-1), Long.decode("-1"));
        assertEquals(new Long(0xF), Long.decode("0xF"));
        assertEquals(new Long(0xF), Long.decode("#F"));
        assertEquals(new Long(0xF), Long.decode("0XF"));
        assertEquals(new Long(07), Long.decode("07"));
        
        try {
            Long.decode("9.2");
            fail("Expected NumberFormatException with floating point string.");
        } catch (NumberFormatException e) {}

        try {
            Long.decode("");
            fail("Expected NumberFormatException with empty string.");
        } catch (NumberFormatException e) {}
        
        try {
            Long.decode(null);
            //undocumented NPE, but seems consistent across JREs
            fail("Expected NullPointerException with null string.");
        } catch (NullPointerException e) {}
    }
    
    /**
     * @tests java.lang.Long#doubleValue()
     */
    public void test_doubleValue() {
        assertEquals(-1D, new Long(-1).doubleValue(), 0D);
        assertEquals(0D, new Long(0).doubleValue(), 0D);
        assertEquals(1D, new Long(1).doubleValue(), 0D);
    }
    
    /**
     * @tests java.lang.Long#floatValue()
     */
    public void test_floatValue() {
        assertEquals(-1F, new Long(-1).floatValue(), 0F);
        assertEquals(0F, new Long(0).floatValue(), 0F);
        assertEquals(1F, new Long(1).floatValue(), 0F);
    }
    
    /**
     * @tests java.lang.Long#intValue()
     */
    public void test_intValue() {
        assertEquals(-1, new Long(-1).intValue());
        assertEquals(0, new Long(0).intValue());
        assertEquals(1, new Long(1).intValue());
    }
    
    /**
     * @tests java.lang.Long#longValue()
     */
    public void test_longValue() {
        assertEquals(-1L, new Long(-1).longValue());
        assertEquals(0L, new Long(0).longValue());
        assertEquals(1L, new Long(1).longValue());
    }
    
    /**
     * @tests java.lang.Long#shortValue()
     */
    public void test_shortValue() {
        assertEquals(-1, new Long(-1).shortValue());
        assertEquals(0, new Long(0).shortValue());
        assertEquals(1, new Long(1).shortValue());
    }
    /**
     * @tests java.lang.Long#highestOneBit(long)
     */
    public void test_highestOneBitJ() {
        assertEquals(0x08, Long.highestOneBit(0x0A));
        assertEquals(0x08, Long.highestOneBit(0x0B));
        assertEquals(0x08, Long.highestOneBit(0x0C));
        assertEquals(0x08, Long.highestOneBit(0x0F));
        assertEquals(0x80, Long.highestOneBit(0xFF));
        
        assertEquals(0x080000, Long.highestOneBit(0x0F1234));
        assertEquals(0x800000, Long.highestOneBit(0xFF9977));
        
        assertEquals(0x8000000000000000L, Long.highestOneBit(0xFFFFFFFFFFFFFFFFL));
        
        assertEquals(0, Long.highestOneBit(0));
        assertEquals(1, Long.highestOneBit(1));
        assertEquals(0x8000000000000000L, Long.highestOneBit(-1));
    }
    
    /**
     * @tests java.lang.Long#lowestOneBit(long)
     */
    public void test_lowestOneBitJ() {
        assertEquals(0x10, Long.lowestOneBit(0xF0));
        
        assertEquals(0x10, Long.lowestOneBit(0x90));
        assertEquals(0x10, Long.lowestOneBit(0xD0));
        
        assertEquals(0x10, Long.lowestOneBit(0x123490));
        assertEquals(0x10, Long.lowestOneBit(0x1234D0));
        
        assertEquals(0x100000, Long.lowestOneBit(0x900000));
        assertEquals(0x100000, Long.lowestOneBit(0xD00000));
        
        assertEquals(0x40, Long.lowestOneBit(0x40));
        assertEquals(0x40, Long.lowestOneBit(0xC0));
        
        assertEquals(0x4000, Long.lowestOneBit(0x4000));
        assertEquals(0x4000, Long.lowestOneBit(0xC000));
        
        assertEquals(0x4000, Long.lowestOneBit(0x99994000));
        assertEquals(0x4000, Long.lowestOneBit(0x9999C000));
        
        assertEquals(0, Long.lowestOneBit(0));
        assertEquals(1, Long.lowestOneBit(1));
        assertEquals(1, Long.lowestOneBit(-1));
    }
    /**
     * @tests java.lang.Long#numberOfLeadingZeros(long)
     */
    public void test_numberOfLeadingZerosJ() {
        assertEquals(64, Long.numberOfLeadingZeros(0x0L));
        assertEquals(63, Long.numberOfLeadingZeros(0x1));
        assertEquals(62, Long.numberOfLeadingZeros(0x2));
        assertEquals(62, Long.numberOfLeadingZeros(0x3));
        assertEquals(61, Long.numberOfLeadingZeros(0x4));
        assertEquals(61, Long.numberOfLeadingZeros(0x5));
        assertEquals(61, Long.numberOfLeadingZeros(0x6));
        assertEquals(61, Long.numberOfLeadingZeros(0x7));
        assertEquals(60, Long.numberOfLeadingZeros(0x8));
        assertEquals(60, Long.numberOfLeadingZeros(0x9));
        assertEquals(60, Long.numberOfLeadingZeros(0xA));
        assertEquals(60, Long.numberOfLeadingZeros(0xB));
        assertEquals(60, Long.numberOfLeadingZeros(0xC));
        assertEquals(60, Long.numberOfLeadingZeros(0xD));
        assertEquals(60, Long.numberOfLeadingZeros(0xE));
        assertEquals(60, Long.numberOfLeadingZeros(0xF));
        assertEquals(59, Long.numberOfLeadingZeros(0x10));
        assertEquals(56, Long.numberOfLeadingZeros(0x80));
        assertEquals(56, Long.numberOfLeadingZeros(0xF0));
        assertEquals(55, Long.numberOfLeadingZeros(0x100));
        assertEquals(52, Long.numberOfLeadingZeros(0x800));
        assertEquals(52, Long.numberOfLeadingZeros(0xF00));
        assertEquals(51, Long.numberOfLeadingZeros(0x1000));
        assertEquals(48, Long.numberOfLeadingZeros(0x8000));
        assertEquals(48, Long.numberOfLeadingZeros(0xF000));
        assertEquals(47, Long.numberOfLeadingZeros(0x10000));
        assertEquals(44, Long.numberOfLeadingZeros(0x80000));
        assertEquals(44, Long.numberOfLeadingZeros(0xF0000));
        assertEquals(43, Long.numberOfLeadingZeros(0x100000));
        assertEquals(40, Long.numberOfLeadingZeros(0x800000));
        assertEquals(40, Long.numberOfLeadingZeros(0xF00000));
        assertEquals(39, Long.numberOfLeadingZeros(0x1000000));
        assertEquals(36, Long.numberOfLeadingZeros(0x8000000));
        assertEquals(36, Long.numberOfLeadingZeros(0xF000000));
        assertEquals(35, Long.numberOfLeadingZeros(0x10000000));
        assertEquals(0, Long.numberOfLeadingZeros(0x80000000));
        assertEquals(0, Long.numberOfLeadingZeros(0xF0000000));
        
        assertEquals(1, Long.numberOfLeadingZeros(Long.MAX_VALUE));
        assertEquals(0, Long.numberOfLeadingZeros(Long.MIN_VALUE));
    }
    
    /**
     * @tests java.lang.Long#numberOfTrailingZeros(long)
     */
    public void test_numberOfTrailingZerosJ() {
        assertEquals(64, Long.numberOfTrailingZeros(0x0));
        assertEquals(63, Long.numberOfTrailingZeros(Long.MIN_VALUE));
        assertEquals(0, Long.numberOfTrailingZeros(Long.MAX_VALUE));
        
        assertEquals(0, Long.numberOfTrailingZeros(0x1));
        assertEquals(3, Long.numberOfTrailingZeros(0x8));
        assertEquals(0, Long.numberOfTrailingZeros(0xF));
        
        assertEquals(4, Long.numberOfTrailingZeros(0x10));
        assertEquals(7, Long.numberOfTrailingZeros(0x80));
        assertEquals(4, Long.numberOfTrailingZeros(0xF0));
        
        assertEquals(8, Long.numberOfTrailingZeros(0x100));
        assertEquals(11, Long.numberOfTrailingZeros(0x800));
        assertEquals(8, Long.numberOfTrailingZeros(0xF00));
        
        assertEquals(12, Long.numberOfTrailingZeros(0x1000));
        assertEquals(15, Long.numberOfTrailingZeros(0x8000));
        assertEquals(12, Long.numberOfTrailingZeros(0xF000));
        
        assertEquals(16, Long.numberOfTrailingZeros(0x10000));
        assertEquals(19, Long.numberOfTrailingZeros(0x80000));
        assertEquals(16, Long.numberOfTrailingZeros(0xF0000));
        
        assertEquals(20, Long.numberOfTrailingZeros(0x100000));
        assertEquals(23, Long.numberOfTrailingZeros(0x800000));
        assertEquals(20, Long.numberOfTrailingZeros(0xF00000));
        
        assertEquals(24, Long.numberOfTrailingZeros(0x1000000));
        assertEquals(27, Long.numberOfTrailingZeros(0x8000000));
        assertEquals(24, Long.numberOfTrailingZeros(0xF000000));
        
        assertEquals(28, Long.numberOfTrailingZeros(0x10000000));
        assertEquals(31, Long.numberOfTrailingZeros(0x80000000));
        assertEquals(28, Long.numberOfTrailingZeros(0xF0000000));
    }
    
    /**
     * @tests java.lang.Long#bitCount(long)
     */
    public void test_bitCountJ() {
        assertEquals(0, Long.bitCount(0x0));
        assertEquals(1, Long.bitCount(0x1));
        assertEquals(1, Long.bitCount(0x2));
        assertEquals(2, Long.bitCount(0x3));
        assertEquals(1, Long.bitCount(0x4));
        assertEquals(2, Long.bitCount(0x5));
        assertEquals(2, Long.bitCount(0x6));
        assertEquals(3, Long.bitCount(0x7));
        assertEquals(1, Long.bitCount(0x8));
        assertEquals(2, Long.bitCount(0x9));
        assertEquals(2, Long.bitCount(0xA));
        assertEquals(3, Long.bitCount(0xB));
        assertEquals(2, Long.bitCount(0xC));
        assertEquals(3, Long.bitCount(0xD));
        assertEquals(3, Long.bitCount(0xE));
        assertEquals(4, Long.bitCount(0xF));
        
        assertEquals(8, Long.bitCount(0xFF));
        assertEquals(12, Long.bitCount(0xFFF));
        assertEquals(16, Long.bitCount(0xFFFF));
        assertEquals(20, Long.bitCount(0xFFFFF));
        assertEquals(24, Long.bitCount(0xFFFFFF));
        assertEquals(28, Long.bitCount(0xFFFFFFF));
        assertEquals(64, Long.bitCount(0xFFFFFFFFFFFFFFFFL));
    }
    
    /**
     * @tests java.lang.Long#rotateLeft(long,long)
     */
    public void test_rotateLeftJI() {
        assertEquals(0xF, Long.rotateLeft(0xF, 0));
        assertEquals(0xF0, Long.rotateLeft(0xF, 4));
        assertEquals(0xF00, Long.rotateLeft(0xF, 8));
        assertEquals(0xF000, Long.rotateLeft(0xF, 12));
        assertEquals(0xF0000, Long.rotateLeft(0xF, 16));
        assertEquals(0xF00000, Long.rotateLeft(0xF, 20));
        assertEquals(0xF000000, Long.rotateLeft(0xF, 24));
        assertEquals(0xF0000000L, Long.rotateLeft(0xF, 28));
        assertEquals(0xF000000000000000L, Long.rotateLeft(0xF000000000000000L, 64));
    }
    
    /**
     * @tests java.lang.Long#rotateRight(long,long)
     */
    public void test_rotateRightJI() {
        assertEquals(0xF, Long.rotateRight(0xF0, 4));
        assertEquals(0xF, Long.rotateRight(0xF00, 8));
        assertEquals(0xF, Long.rotateRight(0xF000, 12));
        assertEquals(0xF, Long.rotateRight(0xF0000, 16));
        assertEquals(0xF, Long.rotateRight(0xF00000, 20));
        assertEquals(0xF, Long.rotateRight(0xF000000, 24));
        assertEquals(0xF, Long.rotateRight(0xF0000000L, 28));
        assertEquals(0xF000000000000000L, Long.rotateRight(0xF000000000000000L, 64));
        assertEquals(0xF000000000000000L, Long.rotateRight(0xF000000000000000L, 0));
        
    }
    
    /**
     * @tests java.lang.Long#reverseBytes(long)
     */
    public void test_reverseBytesJ() {
        assertEquals(0xAABBCCDD00112233L, Long.reverseBytes(0x33221100DDCCBBAAL));
        assertEquals(0x1122334455667788L, Long.reverseBytes(0x8877665544332211L));
        assertEquals(0x0011223344556677L, Long.reverseBytes(0x7766554433221100L));
        assertEquals(0x2000000000000002L, Long.reverseBytes(0x0200000000000020L));
    }
    
    /**
     * @tests java.lang.Long#reverse(long)
     */
    public void test_reverseJ() {
        assertEquals(0, Long.reverse(0));
        assertEquals(-1, Long.reverse(-1));
        assertEquals(0x8000000000000000L,Long.reverse(1));
    }
    
    /**
     * @tests java.lang.Long#signum(long)
     */
    public void test_signumJ() {
        for (int i = -128; i<0; i++) {
            assertEquals(-1, Long.signum((long)i));
        }
        assertEquals(0, Long.signum((long)0));
        for (int i = 1; i<=127; i++) {
            assertEquals(1, Long.signum((long)i));
        }
    }
}