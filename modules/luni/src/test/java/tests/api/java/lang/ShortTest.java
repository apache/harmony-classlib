/* Copyright 1998, 2005 The Apache Software Foundation or its licensors, as applicable
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

package tests.api.java.lang;

public class ShortTest extends junit.framework.TestCase {

	Short sp = new Short((short) 18000);

	Short sp2 = new Short((short) 18000);

	Short sn = new Short((short) -19000);

	/**
	 * @tests java.lang.Short#Short(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		// Test for method java.lang.Short(java.lang.String)

		Short s = new Short("-19000");
		assertTrue("Incorrect Short created", s.shortValue() == (short) -19000);
	}

	/**
	 * @tests java.lang.Short#Short(short)
	 */
	public void test_ConstructorS() {
		// Test for method java.lang.Short(short)
		Short s = new Short((short) 32746);

		assertTrue("Incorrect Short created", s.shortValue() == (short) 32746);
	}

	/**
	 * @tests java.lang.Short#byteValue()
	 */
	public void test_byteValue() {
		// Test for method byte java.lang.Short.byteValue()
		assertEquals("Returned incorrect byte value", 0, new Short(Short.MIN_VALUE)
				.byteValue());
		assertEquals("Returned incorrect byte value", -1, new Short(Short.MAX_VALUE)
				.byteValue());
	}

	/**
	 * @tests java.lang.Short#compareTo(java.lang.Short)
	 */
	public void test_compareToLjava_lang_Short() {
		// Test for method int java.lang.Short.compareTo(java.lang.Short)
		Short s = new Short((short) 1);
		Short x = new Short((short) 3);
		assertTrue(
				"Should have returned negative value when compared to greater short",
				s.compareTo(x) < 0);
		x = new Short((short) -1);
		assertTrue(
				"Should have returned positive value when compared to lesser short",
				s.compareTo(x) > 0);
		x = new Short((short) 1);
		assertEquals("Should have returned zero when compared to equal short",
                             0, s.compareTo(x));
        
        try {
            new Short((short)0).compareTo(null);
            fail("No NPE");
        } catch (NullPointerException e) {
        }
	}

	/**
	 * @tests java.lang.Short#decode(java.lang.String)
	 */
	public void test_decodeLjava_lang_String() {
		// Test for method java.lang.Short
		// java.lang.Short.decode(java.lang.String)
		assertTrue("Did not decode -1 correctly", Short.decode("-1")
				.shortValue() == (short) -1);
		assertTrue("Did not decode -100 correctly", Short.decode("-100")
				.shortValue() == (short) -100);
		assertTrue("Did not decode 23 correctly", Short.decode("23")
				.shortValue() == (short) 23);
		assertTrue("Did not decode 0x10 correctly", Short.decode("0x10")
				.shortValue() == (short) 16);
		assertTrue("Did not decode 32767 correctly", Short.decode("32767")
				.shortValue() == (short) 32767);
		assertTrue("Did not decode -32767 correctly", Short.decode("-32767")
				.shortValue() == (short) -32767);
		assertTrue("Did not decode -32768 correctly", Short.decode("-32768")
				.shortValue() == (short) -32768);

		boolean exception = false;
		try {
			Short.decode("123s");
		} catch (NumberFormatException e) {
			// correct
			exception = true;
		}
		assertTrue("Did not throw NumberFormatException decoding 123s",
				exception);

		exception = false;
		try {
			Short.decode("32768");
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception for MAX_VALUE + 1", exception);

		exception = false;
		try {
			Short.decode("-32769");
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception for MIN_VALUE - 1", exception);

		exception = false;
		try {
			Short.decode("0x8000");
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception for hex MAX_VALUE + 1", exception);

		exception = false;
		try {
			Short.decode("-0x8001");
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception for hex MIN_VALUE - 1", exception);
	}

	/**
	 * @tests java.lang.Short#doubleValue()
	 */
	public void test_doubleValue() {
		// Test for method double java.lang.Short.doubleValue()
		assertEquals("Returned incorrect double value",
				-32768.0, new Short(Short.MIN_VALUE).doubleValue());
		assertEquals("Returned incorrect double value",
				32767.0, new Short(Short.MAX_VALUE).doubleValue());
	}

	/**
	 * @tests java.lang.Short#equals(java.lang.Object)
	 */
	public void test_equalsLjava_lang_Object() {
		// Test for method boolean java.lang.Short.equals(java.lang.Object)
		;
		assertTrue("Equality test failed", sp.equals(sp2)
				&& !(sp.equals(new Object())));
	}

	/**
	 * @tests java.lang.Short#floatValue()
	 */
	public void test_floatValue() {
		// Test for method float java.lang.Short.floatValue()
		assertTrue("Returned incorrect float value", new Short(Short.MIN_VALUE)
				.floatValue() == -32768.0f);
		assertTrue("Returned incorrect float value", new Short(Short.MAX_VALUE)
				.floatValue() == 32767.0f);
	}

	/**
	 * @tests java.lang.Short#hashCode()
	 */
	public void test_hashCode() {
		// Test for method int java.lang.Short.hashCode()

		int phash = new Short(Short.MAX_VALUE).hashCode();
		int nhash = new Short(Short.MIN_VALUE).hashCode();
		assertTrue("Incorrect hash obtained", Short.MAX_VALUE == phash
				&& (Short.MIN_VALUE == nhash));
	}

	/**
	 * @tests java.lang.Short#intValue()
	 */
	public void test_intValue() {
		// Test for method int java.lang.Short.intValue()
		assertEquals("Returned incorrect int value", -32768, new Short(Short.MIN_VALUE)
				.intValue());
		assertEquals("Returned incorrect int value", 32767, new Short(Short.MAX_VALUE)
				.intValue());
	}

	/**
	 * @tests java.lang.Short#longValue()
	 */
	public void test_longValue() {
		// Test for method long java.lang.Short.longValue()
		assertEquals("Returned incorrect long value", -32768, new Short(Short.MIN_VALUE)
				.longValue());
		assertEquals("Returned incorrect long value", 32767, new Short(Short.MAX_VALUE)
				.longValue());
	}

	/**
	 * @tests java.lang.Short#parseShort(java.lang.String)
	 */
	public void test_parseShortLjava_lang_String() {
		// Test for method short java.lang.Short.parseShort(java.lang.String)
		short sp = Short.parseShort("32746");
		short sn = Short.parseShort("-32746");

		assertTrue("Incorrect parse of short", sp == (short) 32746
				&& (sn == (short) -32746));
		assertEquals("Returned incorrect value for 0", 0, Short.parseShort("0"));
		assertTrue("Returned incorrect value for most negative value", Short
				.parseShort("-32768") == (short) 0x8000);
		assertTrue("Returned incorrect value for most positive value", Short
				.parseShort("32767") == 0x7fff);

		boolean exception = false;
		try {
			Short.parseShort("32768");
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception for MAX_VALUE + 1", exception);

		exception = false;
		try {
			Short.parseShort("-32769");
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception for MIN_VALUE - 1", exception);
	}

	/**
	 * @tests java.lang.Short#parseShort(java.lang.String, int)
	 */
	public void test_parseShortLjava_lang_StringI() {
		// Test for method short java.lang.Short.parseShort(java.lang.String,
		// int)
		boolean aThrow = true;
		assertEquals("Incorrectly parsed hex string",
				255, Short.parseShort("FF", 16));
		assertEquals("Incorrectly parsed oct string",
				16, Short.parseShort("20", 8));
		assertEquals("Incorrectly parsed dec string",
				20, Short.parseShort("20", 10));
		assertEquals("Incorrectly parsed bin string",
				4, Short.parseShort("100", 2));
		assertEquals("Incorrectly parsed -hex string", -255, Short
				.parseShort("-FF", 16));
		assertEquals("Incorrectly parsed -oct string",
				-16, Short.parseShort("-20", 8));
		assertEquals("Incorrectly parsed -bin string", -4, Short
				.parseShort("-100", 2));
		assertEquals("Returned incorrect value for 0 hex", 0, Short.parseShort("0",
				16));
		assertTrue("Returned incorrect value for most negative value hex",
				Short.parseShort("-8000", 16) == (short) 0x8000);
		assertTrue("Returned incorrect value for most positive value hex",
				Short.parseShort("7fff", 16) == 0x7fff);
		assertEquals("Returned incorrect value for 0 decimal", 0, Short.parseShort(
				"0", 10));
		assertTrue("Returned incorrect value for most negative value decimal",
				Short.parseShort("-32768", 10) == (short) 0x8000);
		assertTrue("Returned incorrect value for most positive value decimal",
				Short.parseShort("32767", 10) == 0x7fff);

		try {
			Short.parseShort("FF", 2);
		} catch (NumberFormatException e) {
			// Correct
			aThrow = false;
		}
		if (aThrow)
			fail(
					"Failed to throw exception when passed hex string and base 2 radix");

		boolean exception = false;
		try {
			Short.parseShort("10000000000", 10);
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue(
				"Failed to throw exception when passed string larger than 16 bits",
				exception);

		exception = false;
		try {
			Short.parseShort("32768", 10);
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception for MAX_VALUE + 1", exception);

		exception = false;
		try {
			Short.parseShort("-32769", 10);
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception for MIN_VALUE - 1", exception);

		exception = false;
		try {
			Short.parseShort("8000", 16);
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception for hex MAX_VALUE + 1", exception);

		exception = false;
		try {
			Short.parseShort("-8001", 16);
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception for hex MIN_VALUE + 1", exception);
	}

	/**
	 * @tests java.lang.Short#shortValue()
	 */
	public void test_shortValue() {
		// Test for method short java.lang.Short.shortValue()
		assertTrue("Comparison to primitive type failed",
				sp.shortValue() == (short) 18000
						&& (sn.shortValue() == (short) -19000));
	}

	/**
	 * @tests java.lang.Short#toString()
	 */
	public void test_toString() {
		// Test for method java.lang.String java.lang.Short.toString()
		assertTrue("Invalid string returned", sp.toString().equals("18000")
				&& (sn.toString().equals("-19000")));
		assertEquals("Returned incorrect string", "32767", new Short((short) 32767)
				.toString());
		assertEquals("Returned incorrect string", "-32767", new Short((short) -32767)
				.toString());
		assertEquals("Returned incorrect string", "-32768", new Short((short) -32768)
				.toString());
	}

	/**
	 * @tests java.lang.Short#toString(short)
	 */
	public void test_toStringS() {
		// Test for method java.lang.String java.lang.Short.toString(short)
		assertEquals("Returned incorrect string", "32767", Short.toString((short) 32767)
				);
		assertEquals("Returned incorrect string", "-32767", Short.toString((short) -32767)
				);
		assertEquals("Returned incorrect string", "-32768", Short.toString((short) -32768)
				);
	}

	/**
	 * @tests java.lang.Short#valueOf(java.lang.String)
	 */
	public void test_valueOfLjava_lang_String() {
		// Test for method java.lang.Short
		// java.lang.Short.valueOf(java.lang.String)
		assertEquals("Returned incorrect short", -32768, Short.valueOf("-32768")
				.shortValue());
		assertEquals("Returned incorrect short", 32767, Short.valueOf("32767")
				.shortValue());
	}

	/**
	 * @tests java.lang.Short#valueOf(java.lang.String, int)
	 */
	public void test_valueOfLjava_lang_StringI() {
		// Test for method java.lang.Short
		// java.lang.Short.valueOf(java.lang.String, int)
		boolean aThrow = true;
		assertEquals("Incorrectly parsed hex string", 255, Short.valueOf("FF", 16)
				.shortValue());
		assertEquals("Incorrectly parsed oct string", 16, Short.valueOf("20", 8)
				.shortValue());
		assertEquals("Incorrectly parsed dec string", 20, Short.valueOf("20", 10)
				.shortValue());
		assertEquals("Incorrectly parsed bin string", 4, Short.valueOf("100", 2)
				.shortValue());
		assertEquals("Incorrectly parsed -hex string", -255, Short.valueOf("-FF", 16)
				.shortValue());
		assertEquals("Incorrectly parsed -oct string", -16, Short.valueOf("-20", 8)
				.shortValue());
		assertEquals("Incorrectly parsed -bin string", -4, Short.valueOf("-100", 2)
				.shortValue());
		assertTrue("Did not decode 32767 correctly", Short.valueOf("32767", 10)
				.shortValue() == (short) 32767);
		assertTrue("Did not decode -32767 correctly", Short.valueOf("-32767",
				10).shortValue() == (short) -32767);
		assertTrue("Did not decode -32768 correctly", Short.valueOf("-32768",
				10).shortValue() == (short) -32768);
		try {
			Short.valueOf("FF", 2);
		} catch (NumberFormatException e) {
			// Correct
			aThrow = false;
		}
		if (aThrow)
			fail(
					"Failed to throw exception when passed hex string and base 2 radix");
		try {
			Short.valueOf("10000000000", 10);
		} catch (NumberFormatException e) {
			// Correct
			return;
		}
		fail(
				"Failed to throw exception when passed string larger than 16 bits");
	}

	/**
	 * Sets up the fixture, for example, open a network connection. This method
	 * is called before a test is executed.
	 */
	protected void setUp() {
	}

	/**
	 * Tears down the fixture, for example, close a network connection. This
	 * method is called after a test is executed.
	 */
	protected void tearDown() {
	}
}
