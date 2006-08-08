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

import junit.framework.TestCase;

public class IntegerTest extends TestCase {

	/**
	 * @tests java.lang.Integer#valueOf(byte)
	 */
	public void test_valueOfI() {
		assertEquals(new Integer(Integer.MIN_VALUE), Integer.valueOf(Integer.MIN_VALUE));
		assertEquals(new Integer(Integer.MAX_VALUE), Integer.valueOf(Integer.MAX_VALUE));
		assertEquals(new Integer(0), Integer.valueOf(0));

		short s = -128;
		while (s < 128) {
			assertEquals(new Integer(s), Integer.valueOf(s));
			assertSame(Integer.valueOf(s), Integer.valueOf(s));
			s++;
		}
	}
    
    /**
     * @tests java.lang.Integer#hashCode()
     */
    public void test_hashCode() {
        assertEquals(1, new Integer(1).hashCode());
        assertEquals(2, new Integer(2).hashCode());
        assertEquals(0, new Integer(0).hashCode());
        assertEquals(-1, new Integer(-1).hashCode());
    }

    /**
     * @tests java.lang.Integer#Integer(String)
     */
    public void test_ConstructorLjava_lang_String() {
        assertEquals(new Integer(0), new Integer("0"));
        assertEquals(new Integer(1), new Integer("1"));
        assertEquals(new Integer(-1), new Integer("-1"));
        
        try {
            new Integer("0x1");
            fail("Expected NumberFormatException with hex string.");
        } catch (NumberFormatException e) {}

        try {
            new Integer("9.2");
            fail("Expected NumberFormatException with floating point string.");
        } catch (NumberFormatException e) {}

        try {
            new Integer("");
            fail("Expected NumberFormatException with empty string.");
        } catch (NumberFormatException e) {}
        
        try {
            new Integer(null);
            fail("Expected NumberFormatException with null string.");
        } catch (NumberFormatException e) {}
    }

    /**
     * @tests java.lang.Integer#Integer
     */
    public void test_ConstructorI() {
        assertEquals(1, new Integer(1).intValue());
        assertEquals(2, new Integer(2).intValue());
        assertEquals(0, new Integer(0).intValue());
        assertEquals(-1, new Integer(-1).intValue());
    }

    /**
     * @tests java.lang.Integer#byteValue()
     */
    public void test_booleanValue() {
        assertEquals(1, new Integer(1).byteValue());    
        assertEquals(2, new Integer(2).byteValue());
        assertEquals(0, new Integer(0).byteValue());
        assertEquals(-1, new Integer(-1).byteValue());
    }

    /**
     * @tests java.lang.Integer#equals(Object)
     */
    public void test_equalsLjava_lang_Object() {
        assertEquals(new Integer(0), Integer.valueOf(0));
        assertEquals(new Integer(1), Integer.valueOf(1));
        assertEquals(new Integer(-1), Integer.valueOf(-1));
        
        Integer fixture = new Integer(25);
        assertEquals(fixture, fixture);
        assertFalse(fixture.equals(null));
        assertFalse(fixture.equals("Not a Integer"));
    }

    /**
     * @tests java.lang.Integer#toString()
     */
    public void test_toString() {
        assertEquals("-1", new Integer(-1).toString());
        assertEquals("0", new Integer(0).toString());
        assertEquals("1", new Integer(1).toString());
        assertEquals("-1", new Integer(0xFFFFFFFF).toString());
    }

    /**
     * @tests java.lang.Integer#toString
     */
    public void test_toStringI() {
        assertEquals("-1", Integer.toString(-1));
        assertEquals("0", Integer.toString(0));
        assertEquals("1", Integer.toString(1));
        assertEquals("-1", Integer.toString(0xFFFFFFFF));
    }

    /**
     * @tests java.lang.Integer#valueOf(String)
     */
    public void test_valueOfLjava_lang_String() {
        assertEquals(new Integer(0), Integer.valueOf("0"));
        assertEquals(new Integer(1), Integer.valueOf("1"));
        assertEquals(new Integer(-1), Integer.valueOf("-1"));
        
        try {
            Integer.valueOf("0x1");
            fail("Expected NumberFormatException with hex string.");
        } catch (NumberFormatException e) {}

        try {
            Integer.valueOf("9.2");
            fail("Expected NumberFormatException with floating point string.");
        } catch (NumberFormatException e) {}

        try {
            Integer.valueOf("");
            fail("Expected NumberFormatException with empty string.");
        } catch (NumberFormatException e) {}
        
        try {
            Integer.valueOf(null);
            fail("Expected NumberFormatException with null string.");
        } catch (NumberFormatException e) {}
    }
    
    /**
     * @tests java.lang.Integer#valueOf(String,int)
     */
    public void test_valueOfLjava_lang_StringI() {
        assertEquals(new Integer(0), Integer.valueOf("0", 10));
        assertEquals(new Integer(1), Integer.valueOf("1", 10));
        assertEquals(new Integer(-1), Integer.valueOf("-1", 10));
        
        //must be consistent with Character.digit()
        assertEquals(Character.digit('1', 2), Integer.valueOf("1", 2).byteValue());
        assertEquals(Character.digit('F', 16), Integer.valueOf("F", 16).byteValue());
        
        try {
            Integer.valueOf("0x1", 10);
            fail("Expected NumberFormatException with hex string.");
        } catch (NumberFormatException e) {}

        try {
            Integer.valueOf("9.2", 10);
            fail("Expected NumberFormatException with floating point string.");
        } catch (NumberFormatException e) {}

        try {
            Integer.valueOf("", 10);
            fail("Expected NumberFormatException with empty string.");
        } catch (NumberFormatException e) {}
        
        try {
            Integer.valueOf(null, 10);
            fail("Expected NumberFormatException with null string.");
        } catch (NumberFormatException e) {}
    }
    
    /**
     * @tests java.lang.Integer#parseInt(String)
     */
    public void test_parseIntLjava_lang_String() {
        assertEquals(0, Integer.parseInt("0"));
        assertEquals(1, Integer.parseInt("1"));
        assertEquals(-1, Integer.parseInt("-1"));
        
        try {
            Integer.parseInt("0x1");
            fail("Expected NumberFormatException with hex string.");
        } catch (NumberFormatException e) {}

        try {
            Integer.parseInt("9.2");
            fail("Expected NumberFormatException with floating point string.");
        } catch (NumberFormatException e) {}

        try {
            Integer.parseInt("");
            fail("Expected NumberFormatException with empty string.");
        } catch (NumberFormatException e) {}
        
        try {
            Integer.parseInt(null);
            fail("Expected NumberFormatException with null string.");
        } catch (NumberFormatException e) {}
    }
    
    /**
     * @tests java.lang.Integer#parseInt(String,int)
     */
    public void test_parseIntLjava_lang_StringI() {
        assertEquals(0, Integer.parseInt("0", 10));
        assertEquals(1, Integer.parseInt("1", 10));
        assertEquals(-1, Integer.parseInt("-1", 10));
        
        //must be consistent with Character.digit()
        assertEquals(Character.digit('1', 2), Integer.parseInt("1", 2));
        assertEquals(Character.digit('F', 16), Integer.parseInt("F", 16));
        
        try {
            Integer.parseInt("0x1", 10);
            fail("Expected NumberFormatException with hex string.");
        } catch (NumberFormatException e) {}

        try {
            Integer.parseInt("9.2", 10);
            fail("Expected NumberFormatException with floating point string.");
        } catch (NumberFormatException e) {}

        try {
            Integer.parseInt("", 10);
            fail("Expected NumberFormatException with empty string.");
        } catch (NumberFormatException e) {}
        
        try {
            Integer.parseInt(null, 10);
            fail("Expected NumberFormatException with null string.");
        } catch (NumberFormatException e) {}
    }
    
    /**
     * @tests java.lang.Integer#decode(String)
     */
    public void test_decodeLjava_lang_String() {
        assertEquals(new Integer(0), Integer.decode("0"));
        assertEquals(new Integer(1), Integer.decode("1"));
        assertEquals(new Integer(-1), Integer.decode("-1"));
        assertEquals(new Integer(0xF), Integer.decode("0xF"));
        assertEquals(new Integer(0xF), Integer.decode("#F"));
        assertEquals(new Integer(0xF), Integer.decode("0XF"));
        assertEquals(new Integer(07), Integer.decode("07"));
        
        try {
            Integer.decode("9.2");
            fail("Expected NumberFormatException with floating point string.");
        } catch (NumberFormatException e) {}

        try {
            Integer.decode("");
            fail("Expected NumberFormatException with empty string.");
        } catch (NumberFormatException e) {}
        
        try {
            Integer.decode(null);
            //undocumented NPE, but seems consistent across JREs
            fail("Expected NullPointerException with null string.");
        } catch (NullPointerException e) {}
    }
    
    /**
     * @tests java.lang.Integer#doubleValue()
     */
    public void test_doubleValue() {
        assertEquals(-1D, new Integer(-1).doubleValue(), 0D);
        assertEquals(0D, new Integer(0).doubleValue(), 0D);
        assertEquals(1D, new Integer(1).doubleValue(), 0D);
    }
    
    /**
     * @tests java.lang.Integer#floatValue()
     */
    public void test_floatValue() {
        assertEquals(-1F, new Integer(-1).floatValue(), 0F);
        assertEquals(0F, new Integer(0).floatValue(), 0F);
        assertEquals(1F, new Integer(1).floatValue(), 0F);
    }
    
    /**
     * @tests java.lang.Integer#intValue()
     */
    public void test_intValue() {
        assertEquals(-1, new Integer(-1).intValue());
        assertEquals(0, new Integer(0).intValue());
        assertEquals(1, new Integer(1).intValue());
    }
    
    /**
     * @tests java.lang.Integer#longValue()
     */
    public void test_longValue() {
        assertEquals(-1L, new Integer(-1).longValue());
        assertEquals(0L, new Integer(0).longValue());
        assertEquals(1L, new Integer(1).longValue());
    }
    
    /**
     * @tests java.lang.Integer#shortValue()
     */
    public void test_shortValue() {
        assertEquals(-1, new Integer(-1).shortValue());
        assertEquals(0, new Integer(0).shortValue());
        assertEquals(1, new Integer(1).shortValue());
    }
    /**
     * @tests java.lang.Integer#highestOneBit(int)
     */
    public void test_highestOneBitI() {
        assertEquals(0x08, Integer.highestOneBit(0x0A));
        assertEquals(0x08, Integer.highestOneBit(0x0B));
        assertEquals(0x08, Integer.highestOneBit(0x0C));
        assertEquals(0x08, Integer.highestOneBit(0x0F));
        assertEquals(0x80, Integer.highestOneBit(0xFF));
        
        assertEquals(0x080000, Integer.highestOneBit(0x0F1234));
        assertEquals(0x800000, Integer.highestOneBit(0xFF9977));
        
        assertEquals(0x80000000, Integer.highestOneBit(0xFFFFFFFF));
        
        assertEquals(0, Integer.highestOneBit(0));
        assertEquals(1, Integer.highestOneBit(1));
        assertEquals(0x80000000, Integer.highestOneBit(-1));
    }
    
    /**
     * @tests java.lang.Integer#lowestOneBit(int)
     */
    public void test_lowestOneBitI() {
        assertEquals(0x10, Integer.lowestOneBit(0xF0));
        
        assertEquals(0x10, Integer.lowestOneBit(0x90));
        assertEquals(0x10, Integer.lowestOneBit(0xD0));
        
        assertEquals(0x10, Integer.lowestOneBit(0x123490));
        assertEquals(0x10, Integer.lowestOneBit(0x1234D0));
        
        assertEquals(0x100000, Integer.lowestOneBit(0x900000));
        assertEquals(0x100000, Integer.lowestOneBit(0xD00000));
        
        assertEquals(0x40, Integer.lowestOneBit(0x40));
        assertEquals(0x40, Integer.lowestOneBit(0xC0));
        
        assertEquals(0x4000, Integer.lowestOneBit(0x4000));
        assertEquals(0x4000, Integer.lowestOneBit(0xC000));
        
        assertEquals(0x4000, Integer.lowestOneBit(0x99994000));
        assertEquals(0x4000, Integer.lowestOneBit(0x9999C000));
        
        assertEquals(0, Integer.lowestOneBit(0));
        assertEquals(1, Integer.lowestOneBit(1));
        assertEquals(1, Integer.lowestOneBit(-1));
    }
    /**
     * @tests java.lang.Integer#numberOfLeadingZeros(int)
     */
    public void test_numberOfLeadingZerosI() {
        assertEquals(32, Integer.numberOfLeadingZeros(0x0));
        assertEquals(31, Integer.numberOfLeadingZeros(0x1));
        assertEquals(30, Integer.numberOfLeadingZeros(0x2));
        assertEquals(30, Integer.numberOfLeadingZeros(0x3));
        assertEquals(29, Integer.numberOfLeadingZeros(0x4));
        assertEquals(29, Integer.numberOfLeadingZeros(0x5));
        assertEquals(29, Integer.numberOfLeadingZeros(0x6));
        assertEquals(29, Integer.numberOfLeadingZeros(0x7));
        assertEquals(28, Integer.numberOfLeadingZeros(0x8));
        assertEquals(28, Integer.numberOfLeadingZeros(0x9));
        assertEquals(28, Integer.numberOfLeadingZeros(0xA));
        assertEquals(28, Integer.numberOfLeadingZeros(0xB));
        assertEquals(28, Integer.numberOfLeadingZeros(0xC));
        assertEquals(28, Integer.numberOfLeadingZeros(0xD));
        assertEquals(28, Integer.numberOfLeadingZeros(0xE));
        assertEquals(28, Integer.numberOfLeadingZeros(0xF));
        assertEquals(27, Integer.numberOfLeadingZeros(0x10));
        assertEquals(24, Integer.numberOfLeadingZeros(0x80));
        assertEquals(24, Integer.numberOfLeadingZeros(0xF0));
        assertEquals(23, Integer.numberOfLeadingZeros(0x100));
        assertEquals(20, Integer.numberOfLeadingZeros(0x800));
        assertEquals(20, Integer.numberOfLeadingZeros(0xF00));
        assertEquals(19, Integer.numberOfLeadingZeros(0x1000));
        assertEquals(16, Integer.numberOfLeadingZeros(0x8000));
        assertEquals(16, Integer.numberOfLeadingZeros(0xF000));
        assertEquals(15, Integer.numberOfLeadingZeros(0x10000));
        assertEquals(12, Integer.numberOfLeadingZeros(0x80000));
        assertEquals(12, Integer.numberOfLeadingZeros(0xF0000));
        assertEquals(11, Integer.numberOfLeadingZeros(0x100000));
        assertEquals(8, Integer.numberOfLeadingZeros(0x800000));
        assertEquals(8, Integer.numberOfLeadingZeros(0xF00000));
        assertEquals(7, Integer.numberOfLeadingZeros(0x1000000));
        assertEquals(4, Integer.numberOfLeadingZeros(0x8000000));
        assertEquals(4, Integer.numberOfLeadingZeros(0xF000000));
        assertEquals(3, Integer.numberOfLeadingZeros(0x10000000));
        assertEquals(0, Integer.numberOfLeadingZeros(0x80000000));
        assertEquals(0, Integer.numberOfLeadingZeros(0xF0000000));
        
        assertEquals(1, Integer.numberOfLeadingZeros(Integer.MAX_VALUE));
        assertEquals(0, Integer.numberOfLeadingZeros(Integer.MIN_VALUE));
    }
    
    /**
     * @tests java.lang.Integer#numberOfTrailingZeros(int)
     */
    public void test_numberOfTrailingZerosI() {
        assertEquals(32, Integer.numberOfTrailingZeros(0x0));
        assertEquals(31, Integer.numberOfTrailingZeros(Integer.MIN_VALUE));
        assertEquals(0, Integer.numberOfTrailingZeros(Integer.MAX_VALUE));
        
        assertEquals(0, Integer.numberOfTrailingZeros(0x1));
        assertEquals(3, Integer.numberOfTrailingZeros(0x8));
        assertEquals(0, Integer.numberOfTrailingZeros(0xF));
        
        assertEquals(4, Integer.numberOfTrailingZeros(0x10));
        assertEquals(7, Integer.numberOfTrailingZeros(0x80));
        assertEquals(4, Integer.numberOfTrailingZeros(0xF0));
        
        assertEquals(8, Integer.numberOfTrailingZeros(0x100));
        assertEquals(11, Integer.numberOfTrailingZeros(0x800));
        assertEquals(8, Integer.numberOfTrailingZeros(0xF00));
        
        assertEquals(12, Integer.numberOfTrailingZeros(0x1000));
        assertEquals(15, Integer.numberOfTrailingZeros(0x8000));
        assertEquals(12, Integer.numberOfTrailingZeros(0xF000));
        
        assertEquals(16, Integer.numberOfTrailingZeros(0x10000));
        assertEquals(19, Integer.numberOfTrailingZeros(0x80000));
        assertEquals(16, Integer.numberOfTrailingZeros(0xF0000));
        
        assertEquals(20, Integer.numberOfTrailingZeros(0x100000));
        assertEquals(23, Integer.numberOfTrailingZeros(0x800000));
        assertEquals(20, Integer.numberOfTrailingZeros(0xF00000));
        
        assertEquals(24, Integer.numberOfTrailingZeros(0x1000000));
        assertEquals(27, Integer.numberOfTrailingZeros(0x8000000));
        assertEquals(24, Integer.numberOfTrailingZeros(0xF000000));
        
        assertEquals(28, Integer.numberOfTrailingZeros(0x10000000));
        assertEquals(31, Integer.numberOfTrailingZeros(0x80000000));
        assertEquals(28, Integer.numberOfTrailingZeros(0xF0000000));
    }
    
    /**
     * @tests java.lang.Integer#bitCount(int)
     */
    public void test_bitCountI() {
        assertEquals(0, Integer.bitCount(0x0));
        assertEquals(1, Integer.bitCount(0x1));
        assertEquals(1, Integer.bitCount(0x2));
        assertEquals(2, Integer.bitCount(0x3));
        assertEquals(1, Integer.bitCount(0x4));
        assertEquals(2, Integer.bitCount(0x5));
        assertEquals(2, Integer.bitCount(0x6));
        assertEquals(3, Integer.bitCount(0x7));
        assertEquals(1, Integer.bitCount(0x8));
        assertEquals(2, Integer.bitCount(0x9));
        assertEquals(2, Integer.bitCount(0xA));
        assertEquals(3, Integer.bitCount(0xB));
        assertEquals(2, Integer.bitCount(0xC));
        assertEquals(3, Integer.bitCount(0xD));
        assertEquals(3, Integer.bitCount(0xE));
        assertEquals(4, Integer.bitCount(0xF));
        
        assertEquals(8, Integer.bitCount(0xFF));
        assertEquals(12, Integer.bitCount(0xFFF));
        assertEquals(16, Integer.bitCount(0xFFFF));
        assertEquals(20, Integer.bitCount(0xFFFFF));
        assertEquals(24, Integer.bitCount(0xFFFFFF));
        assertEquals(28, Integer.bitCount(0xFFFFFFF));
        assertEquals(32, Integer.bitCount(0xFFFFFFFF));
    }
    
    /**
     * @tests java.lang.Integer#rotateLeft(int,int)
     */
    public void test_rotateLeftII() {
        assertEquals(0xF, Integer.rotateLeft(0xF, 0));
        assertEquals(0xF0, Integer.rotateLeft(0xF, 4));
        assertEquals(0xF00, Integer.rotateLeft(0xF, 8));
        assertEquals(0xF000, Integer.rotateLeft(0xF, 12));
        assertEquals(0xF0000, Integer.rotateLeft(0xF, 16));
        assertEquals(0xF00000, Integer.rotateLeft(0xF, 20));
        assertEquals(0xF000000, Integer.rotateLeft(0xF, 24));
        assertEquals(0xF0000000, Integer.rotateLeft(0xF, 28));
        assertEquals(0xF0000000, Integer.rotateLeft(0xF0000000, 32));
    }
    
    /**
     * @tests java.lang.Integer#rotateRight(int,int)
     */
    public void test_rotateRightII() {
        assertEquals(0xF, Integer.rotateRight(0xF0, 4));
        assertEquals(0xF, Integer.rotateRight(0xF00, 8));
        assertEquals(0xF, Integer.rotateRight(0xF000, 12));
        assertEquals(0xF, Integer.rotateRight(0xF0000, 16));
        assertEquals(0xF, Integer.rotateRight(0xF00000, 20));
        assertEquals(0xF, Integer.rotateRight(0xF000000, 24));
        assertEquals(0xF, Integer.rotateRight(0xF0000000, 28));
        assertEquals(0xF0000000, Integer.rotateRight(0xF0000000, 32));
        assertEquals(0xF0000000, Integer.rotateRight(0xF0000000, 0));
        
    }
    
    /**
     * @tests java.lang.Integer#reverseBytes(int)
     */
    public void test_reverseBytesI() {
        assertEquals(0xAABBCCDD, Integer.reverseBytes(0xDDCCBBAA));
        assertEquals(0x11223344, Integer.reverseBytes(0x44332211));
        assertEquals(0x00112233, Integer.reverseBytes(0x33221100));
        assertEquals(0x20000002, Integer.reverseBytes(0x02000020));
    }
    
    /**
     * @tests java.lang.Integer#reverse(int)
     */
    public void test_reverseI() {
        assertEquals(-1, Integer.reverse(-1));
        assertEquals(0x80000000,Integer.reverse(1));
    }
    
    /**
     * @tests java.lang.Integer#signum(int)
     */
    public void test_signumI() {
        for (int i = -128; i<0; i++) {
            assertEquals(-1, Integer.signum(i));
        }
        assertEquals(0, Integer.signum(0));
        for (int i = 1; i<=127; i++) {
            assertEquals(1, Integer.signum(i));
        }
    }
}
