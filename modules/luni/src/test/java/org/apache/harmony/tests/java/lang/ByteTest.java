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

public class ByteTest extends TestCase {

	/**
	 * @tests java.lang.Byte#valueOf(byte)
	 */
	public void test_valueOfB() {
		assertEquals(new Byte(Byte.MIN_VALUE), Byte.valueOf(Byte.MIN_VALUE));
		assertEquals(new Byte(Byte.MAX_VALUE), Byte.valueOf(Byte.MAX_VALUE));
		assertEquals(new Byte((byte) 0), Byte.valueOf((byte) 0));

		byte b = Byte.MIN_VALUE + 1;
		while (b < Byte.MAX_VALUE) {
			assertEquals(new Byte(b), Byte.valueOf(b));
			assertSame(Byte.valueOf(b), Byte.valueOf(b));
			b++;
		}
	}
    
    /**
     * @tests java.lang.Byte#hashCode()
     */
    public void test_hashCode() {
        assertEquals(1, new Byte((byte)1).hashCode());
        assertEquals(2, new Byte((byte)2).hashCode());
        assertEquals(0, new Byte((byte)0).hashCode());
        assertEquals(-1, new Byte((byte)-1).hashCode());
    }

    /**
     * @tests java.lang.Byte#Byte(String)
     */
    public void test_ConstructorLjava_lang_String() {
        assertEquals(new Byte((byte)0), new Byte("0"));
        assertEquals(new Byte((byte)1), new Byte("1"));
        assertEquals(new Byte((byte)-1), new Byte("-1"));
        
        try {
            new Byte("0x1");
            fail("Expected NumberFormatException with hex string.");
        } catch (NumberFormatException e) {}

        try {
            new Byte("9.2");
            fail("Expected NumberFormatException with floating point string.");
        } catch (NumberFormatException e) {}

        try {
            new Byte("");
            fail("Expected NumberFormatException with empty string.");
        } catch (NumberFormatException e) {}
        
        try {
            new Byte(null);
            fail("Expected NumberFormatException with null string.");
        } catch (NumberFormatException e) {}
    }

    /**
     * @tests java.lang.Byte#Byte(byte)
     */
    public void test_ConstructorB() {
        assertEquals(1, new Byte((byte)1).byteValue());
        assertEquals(2, new Byte((byte)2).byteValue());
        assertEquals(0, new Byte((byte)0).byteValue());
        assertEquals(-1, new Byte((byte)-1).byteValue());
    }

    /**
     * @tests java.lang.Byte#byteValue()
     */
    public void test_booleanValue() {
        assertEquals(1, new Byte((byte)1).byteValue());
        assertEquals(2, new Byte((byte)2).byteValue());
        assertEquals(0, new Byte((byte)0).byteValue());
        assertEquals(-1, new Byte((byte)-1).byteValue());
    }

    /**
     * @tests java.lang.Byte#equals(Object)
     */
    public void test_equalsLjava_lang_Object() {
        assertEquals(new Byte((byte)0), Byte.valueOf((byte)0));
        assertEquals(new Byte((byte)1), Byte.valueOf((byte)1));
        assertEquals(new Byte((byte)-1), Byte.valueOf((byte)-1));
        
        Byte fixture = new Byte((byte)25);
        assertEquals(fixture, fixture);
        assertFalse(fixture.equals(null));
        assertFalse(fixture.equals("Not a Byte"));
    }

    /**
     * @tests java.lang.Byte#toString()
     */
    public void test_toString() {
        assertEquals("-1", new Byte((byte)-1).toString());
        assertEquals("0", new Byte((byte)0).toString());
        assertEquals("1", new Byte((byte)1).toString());
        assertEquals("-1", new Byte((byte)0xFF).toString());
    }

    /**
     * @tests java.lang.Byte#toString(byte)
     */
    public void test_toStringB() {
        assertEquals("-1", Byte.toString((byte)-1));
        assertEquals("0", Byte.toString((byte)0));
        assertEquals("1", Byte.toString((byte)1));
        assertEquals("-1", Byte.toString((byte)0xFF));
    }

    /**
     * @tests java.lang.Byte#valueOf(String)
     */
    public void test_valueOfLjava_lang_String() {
        assertEquals(new Byte((byte)0), Byte.valueOf("0"));
        assertEquals(new Byte((byte)1), Byte.valueOf("1"));
        assertEquals(new Byte((byte)-1), Byte.valueOf("-1"));
        
        try {
            Byte.valueOf("0x1");
            fail("Expected NumberFormatException with hex string.");
        } catch (NumberFormatException e) {}

        try {
            Byte.valueOf("9.2");
            fail("Expected NumberFormatException with floating point string.");
        } catch (NumberFormatException e) {}

        try {
            Byte.valueOf("");
            fail("Expected NumberFormatException with empty string.");
        } catch (NumberFormatException e) {}
        
        try {
            Byte.valueOf(null);
            fail("Expected NumberFormatException with null string.");
        } catch (NumberFormatException e) {}
    }
    
    /**
     * @tests java.lang.Byte#valueOf(String,int)
     */
    public void test_valueOfLjava_lang_StringI() {
        assertEquals(new Byte((byte)0), Byte.valueOf("0", 10));
        assertEquals(new Byte((byte)1), Byte.valueOf("1", 10));
        assertEquals(new Byte((byte)-1), Byte.valueOf("-1", 10));
        
        //must be consistent with Character.digit()
        assertEquals(Character.digit('1', 2), Byte.valueOf("1", 2).byteValue());
        assertEquals(Character.digit('F', 16), Byte.valueOf("F", 16).byteValue());
        
        try {
            Byte.valueOf("0x1", 10);
            fail("Expected NumberFormatException with hex string.");
        } catch (NumberFormatException e) {}

        try {
            Byte.valueOf("9.2", 10);
            fail("Expected NumberFormatException with floating point string.");
        } catch (NumberFormatException e) {}

        try {
            Byte.valueOf("", 10);
            fail("Expected NumberFormatException with empty string.");
        } catch (NumberFormatException e) {}
        
        try {
            Byte.valueOf(null, 10);
            fail("Expected NumberFormatException with null string.");
        } catch (NumberFormatException e) {}
    }
    
    /**
     * @tests java.lang.Byte#parseByte(String)
     */
    public void test_parseByteLjava_lang_String() {
        assertEquals(0, Byte.parseByte("0"));
        assertEquals(1, Byte.parseByte("1"));
        assertEquals(-1, Byte.parseByte("-1"));
        
        try {
            Byte.parseByte("0x1");
            fail("Expected NumberFormatException with hex string.");
        } catch (NumberFormatException e) {}

        try {
            Byte.parseByte("9.2");
            fail("Expected NumberFormatException with floating point string.");
        } catch (NumberFormatException e) {}

        try {
            Byte.parseByte("");
            fail("Expected NumberFormatException with empty string.");
        } catch (NumberFormatException e) {}
        
        try {
            Byte.parseByte(null);
            fail("Expected NumberFormatException with null string.");
        } catch (NumberFormatException e) {}
    }
    
    /**
     * @tests java.lang.Byte#parseByte(String,int)
     */
    public void test_parseByteLjava_lang_StringI() {
        assertEquals(0, Byte.parseByte("0", 10));
        assertEquals(1, Byte.parseByte("1", 10));
        assertEquals(-1, Byte.parseByte("-1", 10));
        
        //must be consistent with Character.digit()
        assertEquals(Character.digit('1', 2), Byte.parseByte("1", 2));
        assertEquals(Character.digit('F', 16), Byte.parseByte("F", 16));
        
        try {
            Byte.parseByte("0x1", 10);
            fail("Expected NumberFormatException with hex string.");
        } catch (NumberFormatException e) {}

        try {
            Byte.parseByte("9.2", 10);
            fail("Expected NumberFormatException with floating point string.");
        } catch (NumberFormatException e) {}

        try {
            Byte.parseByte("", 10);
            fail("Expected NumberFormatException with empty string.");
        } catch (NumberFormatException e) {}
        
        try {
            Byte.parseByte(null, 10);
            fail("Expected NumberFormatException with null string.");
        } catch (NumberFormatException e) {}
    }
    
    /**
     * @tests java.lang.Byte#decode(String)
     */
    public void test_decodeLjava_lang_String() {
        assertEquals(new Byte((byte)0), Byte.decode("0"));
        assertEquals(new Byte((byte)1), Byte.decode("1"));
        assertEquals(new Byte((byte)-1), Byte.decode("-1"));
        assertEquals(new Byte((byte)0xF), Byte.decode("0xF"));
        assertEquals(new Byte((byte)0xF), Byte.decode("#F"));
        assertEquals(new Byte((byte)0xF), Byte.decode("0XF"));
        assertEquals(new Byte((byte)07), Byte.decode("07"));
        
        try {
            Byte.decode("9.2");
            fail("Expected NumberFormatException with floating point string.");
        } catch (NumberFormatException e) {}

        try {
            Byte.decode("");
            fail("Expected NumberFormatException with empty string.");
        } catch (NumberFormatException e) {}
        
        try {
            Byte.decode(null);
            //undocumented NPE, but seems consistent across JREs
            fail("Expected NullPointerException with null string.");
        } catch (NullPointerException e) {}
    }
    
    /**
     * @tests java.lang.Byte#doubleValue()
     */
    public void test_doubleValue() {
        assertEquals(-1D, new Byte((byte)-1).doubleValue(), 0D);
        assertEquals(0D, new Byte((byte)0).doubleValue(), 0D);
        assertEquals(1D, new Byte((byte)1).doubleValue(), 0D);
    }
    
    /**
     * @tests java.lang.Byte#floatValue()
     */
    public void test_floatValue() {
        assertEquals(-1F, new Byte((byte)-1).floatValue(), 0F);
        assertEquals(0F, new Byte((byte)0).floatValue(), 0F);
        assertEquals(1F, new Byte((byte)1).floatValue(), 0F);
    }
    
    /**
     * @tests java.lang.Byte#intValue()
     */
    public void test_intValue() {
        assertEquals(-1, new Byte((byte)-1).intValue());
        assertEquals(0, new Byte((byte)0).intValue());
        assertEquals(1, new Byte((byte)1).intValue());
    }
    
    /**
     * @tests java.lang.Byte#longValue()
     */
    public void test_longValue() {
        assertEquals(-1L, new Byte((byte)-1).longValue());
        assertEquals(0L, new Byte((byte)0).longValue());
        assertEquals(1L, new Byte((byte)1).longValue());
    }
    
    /**
     * @tests java.lang.Byte#shortValue()
     */
    public void test_shortValue() {
        assertEquals(-1, new Byte((byte)-1).shortValue());
        assertEquals(0, new Byte((byte)0).shortValue());
        assertEquals(1, new Byte((byte)1).shortValue());
    }
    
    /**
     * @tests java.lang.Byte#compareTo(Byte)
     */
    public void test_compareToLjava_lang_Byte() {
        final Byte min = new Byte(Byte.MIN_VALUE);
        final Byte zero = new Byte((byte)0);
        final Byte max = new Byte(Byte.MAX_VALUE);
        
        assertTrue(max.compareTo(max) == 0);
        assertTrue(min.compareTo(min) == 0);
        assertTrue(zero.compareTo(zero) == 0);
        
        assertTrue(max.compareTo(zero) > 0);
        assertTrue(max.compareTo(min) > 0);
        
        assertTrue(zero.compareTo(max) < 0);
        assertTrue(zero.compareTo(min) > 0);
        
        assertTrue(min.compareTo(zero) < 0);
        assertTrue(min.compareTo(max) < 0);
        
        try {
            min.compareTo(null);
            fail("No NPE");
        } catch (NullPointerException e) {
        }
    }
}
