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

public class ShortTest extends TestCase {

	/**
	 * @tests java.lang.Short#valueOf(byte)
	 */
	public void test_valueOfS() {
		assertEquals(new Short(Short.MIN_VALUE), Short.valueOf(Short.MIN_VALUE));
		assertEquals(new Short(Short.MAX_VALUE), Short.valueOf(Short.MAX_VALUE));
		assertEquals(new Short((short) 0), Short.valueOf((short) 0));

		short s = -128;
		while (s < 128) {
			assertEquals(new Short(s), Short.valueOf(s));
			assertSame(Short.valueOf(s), Short.valueOf(s));
			s++;
		}
	}
    
    /**
     * @tests java.lang.Short#hashCode()
     */
    public void test_hashCode() {
        assertEquals(1, new Short((short)1).hashCode());
        assertEquals(2, new Short((short)2).hashCode());
        assertEquals(0, new Short((short)0).hashCode());
        assertEquals(-1, new Short((short)-1).hashCode());
    }

    /**
     * @tests java.lang.Short#Short(String)
     */
    public void test_ConstructorLjava_lang_String() {
        assertEquals(new Short((short)0), new Short("0"));
        assertEquals(new Short((short)1), new Short("1"));
        assertEquals(new Short((short)-1), new Short("-1"));
        
        try {
            new Short("0x1");
            fail("Expected NumberFormatException with hex string.");
        } catch (NumberFormatException e) {}

        try {
            new Short("9.2");
            fail("Expected NumberFormatException with floating point string.");
        } catch (NumberFormatException e) {}

        try {
            new Short("");
            fail("Expected NumberFormatException with empty string.");
        } catch (NumberFormatException e) {}
        
        try {
            new Short(null);
            fail("Expected NumberFormatException with null string.");
        } catch (NumberFormatException e) {}
    }

    /**
     * @tests java.lang.Short#Short(short)
     */
    public void test_ConstructorS() {
        assertEquals(1, new Short((short)1).shortValue());
        assertEquals(2, new Short((short)2).shortValue());
        assertEquals(0, new Short((short)0).shortValue());
        assertEquals(-1, new Short((short)-1).shortValue());
    }

    /**
     * @tests java.lang.Short#byteValue()
     */
    public void test_booleanValue() {
        assertEquals(1, new Short((short)1).byteValue());    
        assertEquals(2, new Short((short)2).byteValue());
        assertEquals(0, new Short((short)0).byteValue());
        assertEquals(-1, new Short((short)-1).byteValue());
    }

    /**
     * @tests java.lang.Short#equals(Object)
     */
    public void test_equalsLjava_lang_Object() {
        assertEquals(new Short((short)0), Short.valueOf((short)0));
        assertEquals(new Short((short)1), Short.valueOf((short)1));
        assertEquals(new Short((short)-1), Short.valueOf((short)-1));
        
        Short fixture = new Short((short)25);
        assertEquals(fixture, fixture);
        assertFalse(fixture.equals(null));
        assertFalse(fixture.equals("Not a Short"));
    }

    /**
     * @tests java.lang.Short#toString()
     */
    public void test_toString() {
        assertEquals("-1", new Short((short)-1).toString());
        assertEquals("0", new Short((short)0).toString());
        assertEquals("1", new Short((short)1).toString());
        assertEquals("-1", new Short((short)0xFFFF).toString());
    }

    /**
     * @tests java.lang.Short#toString(short)
     */
    public void test_toStringS() {
        assertEquals("-1", Short.toString((short)-1));
        assertEquals("0", Short.toString((short)0));
        assertEquals("1", Short.toString((short)1));
        assertEquals("-1", Short.toString((short)0xFFFF));
    }

    /**
     * @tests java.lang.Short#valueOf(String)
     */
    public void test_valueOfLjava_lang_String() {
        assertEquals(new Short((short)0), Short.valueOf("0"));
        assertEquals(new Short((short)1), Short.valueOf("1"));
        assertEquals(new Short((short)-1), Short.valueOf("-1"));
        
        try {
            Short.valueOf("0x1");
            fail("Expected NumberFormatException with hex string.");
        } catch (NumberFormatException e) {}

        try {
            Short.valueOf("9.2");
            fail("Expected NumberFormatException with floating point string.");
        } catch (NumberFormatException e) {}

        try {
            Short.valueOf("");
            fail("Expected NumberFormatException with empty string.");
        } catch (NumberFormatException e) {}
        
        try {
            Short.valueOf(null);
            fail("Expected NumberFormatException with null string.");
        } catch (NumberFormatException e) {}
    }
    
    /**
     * @tests java.lang.Short#valueOf(String,int)
     */
    public void test_valueOfLjava_lang_StringI() {
        assertEquals(new Short((short)0), Short.valueOf("0", 10));
        assertEquals(new Short((short)1), Short.valueOf("1", 10));
        assertEquals(new Short((short)-1), Short.valueOf("-1", 10));
        
        //must be consistent with Character.digit()
        assertEquals(Character.digit('1', 2), Short.valueOf("1", 2).byteValue());
        assertEquals(Character.digit('F', 16), Short.valueOf("F", 16).byteValue());
        
        try {
            Short.valueOf("0x1", 10);
            fail("Expected NumberFormatException with hex string.");
        } catch (NumberFormatException e) {}

        try {
            Short.valueOf("9.2", 10);
            fail("Expected NumberFormatException with floating point string.");
        } catch (NumberFormatException e) {}

        try {
            Short.valueOf("", 10);
            fail("Expected NumberFormatException with empty string.");
        } catch (NumberFormatException e) {}
        
        try {
            Short.valueOf(null, 10);
            fail("Expected NumberFormatException with null string.");
        } catch (NumberFormatException e) {}
    }
    
    /**
     * @tests java.lang.Short#parseShort(String)
     */
    public void test_parseShortLjava_lang_String() {
        assertEquals(0, Short.parseShort("0"));
        assertEquals(1, Short.parseShort("1"));
        assertEquals(-1, Short.parseShort("-1"));
        
        try {
            Short.parseShort("0x1");
            fail("Expected NumberFormatException with hex string.");
        } catch (NumberFormatException e) {}

        try {
            Short.parseShort("9.2");
            fail("Expected NumberFormatException with floating point string.");
        } catch (NumberFormatException e) {}

        try {
            Short.parseShort("");
            fail("Expected NumberFormatException with empty string.");
        } catch (NumberFormatException e) {}
        
        try {
            Short.parseShort(null);
            fail("Expected NumberFormatException with null string.");
        } catch (NumberFormatException e) {}
    }
    
    /**
     * @tests java.lang.Short#parseShort(String,int)
     */
    public void test_parseShortLjava_lang_StringI() {
        assertEquals(0, Short.parseShort("0", 10));
        assertEquals(1, Short.parseShort("1", 10));
        assertEquals(-1, Short.parseShort("-1", 10));
        
        //must be consistent with Character.digit()
        assertEquals(Character.digit('1', 2), Short.parseShort("1", 2));
        assertEquals(Character.digit('F', 16), Short.parseShort("F", 16));
        
        try {
            Short.parseShort("0x1", 10);
            fail("Expected NumberFormatException with hex string.");
        } catch (NumberFormatException e) {}

        try {
            Short.parseShort("9.2", 10);
            fail("Expected NumberFormatException with floating point string.");
        } catch (NumberFormatException e) {}

        try {
            Short.parseShort("", 10);
            fail("Expected NumberFormatException with empty string.");
        } catch (NumberFormatException e) {}
        
        try {
            Short.parseShort(null, 10);
            fail("Expected NumberFormatException with null string.");
        } catch (NumberFormatException e) {}
    }
    
    /**
     * @tests java.lang.Short#decode(String)
     */
    public void test_decodeLjava_lang_String() {
        assertEquals(new Short((short)0), Short.decode("0"));
        assertEquals(new Short((short)1), Short.decode("1"));
        assertEquals(new Short((short)-1), Short.decode("-1"));
        assertEquals(new Short((short)0xF), Short.decode("0xF"));
        assertEquals(new Short((short)0xF), Short.decode("#F"));
        assertEquals(new Short((short)0xF), Short.decode("0XF"));
        assertEquals(new Short((short)07), Short.decode("07"));
        
        try {
            Short.decode("9.2");
            fail("Expected NumberFormatException with floating point string.");
        } catch (NumberFormatException e) {}

        try {
            Short.decode("");
            fail("Expected NumberFormatException with empty string.");
        } catch (NumberFormatException e) {}
        
        try {
            Short.decode(null);
            //undocumented NPE, but seems consistent across JREs
            fail("Expected NullPointerException with null string.");
        } catch (NullPointerException e) {}
    }
    
    /**
     * @tests java.lang.Short#doubleValue()
     */
    public void test_doubleValue() {
        assertEquals(-1D, new Short((short)-1).doubleValue(), 0D);
        assertEquals(0D, new Short((short)0).doubleValue(), 0D);
        assertEquals(1D, new Short((short)1).doubleValue(), 0D);
    }
    
    /**
     * @tests java.lang.Short#floatValue()
     */
    public void test_floatValue() {
        assertEquals(-1F, new Short((short)-1).floatValue(), 0F);
        assertEquals(0F, new Short((short)0).floatValue(), 0F);
        assertEquals(1F, new Short((short)1).floatValue(), 0F);
    }
    
    /**
     * @tests java.lang.Short#intValue()
     */
    public void test_intValue() {
        assertEquals(-1, new Short((short)-1).intValue());
        assertEquals(0, new Short((short)0).intValue());
        assertEquals(1, new Short((short)1).intValue());
    }
    
    /**
     * @tests java.lang.Short#longValue()
     */
    public void test_longValue() {
        assertEquals(-1L, new Short((short)-1).longValue());
        assertEquals(0L, new Short((short)0).longValue());
        assertEquals(1L, new Short((short)1).longValue());
    }
    
    /**
     * @tests java.lang.Short#shortValue()
     */
    public void test_shortValue() {
        assertEquals(-1, new Short((short)-1).shortValue());
        assertEquals(0, new Short((short)0).shortValue());
        assertEquals(1, new Short((short)1).shortValue());
    }
    
    /**
     * @tests java.lang.Short#reverseBytes(short)
     */
    public void test_reverseBytesS() {
        assertEquals((short)0xABCD, Short.reverseBytes((short)0xCDAB));
        assertEquals((short)0x1234, Short.reverseBytes((short)0x3412));
        assertEquals((short)0x0011, Short.reverseBytes((short)0x1100));
        assertEquals((short)0x2002, Short.reverseBytes((short)0x0220));
    }
    
}
