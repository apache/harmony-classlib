package org.apache.harmony.tests.java.lang;

import junit.framework.TestCase;

public class EnumTest extends TestCase {

    enum Sample {
        LARRY, MOE, CURLY
    }

    Sample larry = Sample.LARRY;

    Sample moe = Sample.MOE;

    /**
     * @tests java.lang.Enum#compareTo(java.lang.Enum) 
     */
    public void test_compareToLjava_lang_Enum() {
        assertTrue(0 < Sample.MOE.compareTo(Sample.LARRY));
        assertEquals(0, Sample.MOE.compareTo(Sample.MOE));
        assertTrue(0 > Sample.MOE.compareTo(Sample.CURLY));
    }

    /**
     * @tests java.lang.Enum#equals(Object)
     */
    public void test_equalsLjava_lang_Object() {
        assertFalse(moe.equals("bob"));
        assertTrue(moe.equals(Sample.MOE));
        assertFalse(Sample.LARRY.equals(Sample.CURLY));
        assertTrue(Sample.LARRY.equals(larry));
    }

    /**
     * @tests java.lang.Enum#getDeclaringClass()
     */
    public void test_getDeclaringClass() {
        assertEquals(Sample.class, moe.getDeclaringClass());
    }

    /**
     * @tests java.lang.Enum#hashCode()
     */
    public void test_hashCode() {
        assertEquals (moe.hashCode(), moe.hashCode());
    }

    /**
     * @tests java.lang.Enum#name()
     */
    public void test_name() {
        assertEquals("MOE", moe.name());
    }

    /**
     * @tests java.lang.Enum#ordinal()
     */
    public void test_ordinal() {
        assertEquals(0, larry.ordinal());
        assertEquals(1, moe.ordinal());
        assertEquals(2, Sample.CURLY.ordinal());
    }

    /**
     * @tests java.lang.Enum#toString()
     */
    public void test_toString() {
        assertTrue(moe.toString().contains("MOE"));
    }

    /**
     * @tests java.lang.Enum#valueOf(Class, String)
     */
    public void test_valueOfLjava_lang_String() {
        assertSame(Sample.CURLY, Sample.valueOf("CURLY"));
        assertSame(Sample.LARRY, Sample.valueOf("LARRY"));
        assertSame(moe, Sample.valueOf("MOE"));
        try {
            Sample.valueOf("non-existant");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e){
            // Expected
        }
    }

    /**
     * @tests java.lang.Enum#values
     */
    public void test_values() {
        Sample[] myValues = Sample.values();
        assertEquals(3, myValues.length);

        assertEquals(Sample.LARRY, myValues[0]);
        assertEquals(Sample.MOE, myValues[1]);
        assertEquals(Sample.CURLY, myValues[2]);
    }
}
