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

import java.util.Properties;

public class IntegerTest extends junit.framework.TestCase {

	Properties orgProps;

	/**
	 * @tests java.lang.Integer#Integer(int)
	 */
	public void test_ConstructorI() {
		// Test for method java.lang.Integer(int)
		Integer i = new Integer(-89000);
		assertTrue("Incorrect Integer created", i.intValue() == -89000);
	}

	/**
	 * @tests java.lang.Integer#Integer(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		// Test for method java.lang.Integer(java.lang.String)
		Integer i = new Integer("65000");
		assertTrue("Created incorrect Integer", i.intValue() == 65000);
	}

	/**
	 * @tests java.lang.Integer#byteValue()
	 */
	public void test_byteValue() {
		// Test for method byte java.lang.Integer.byteValue()
		assertTrue("Returned incorrect byte value", new Integer(65535)
				.byteValue() == -1);
		assertTrue("Returned incorrect byte value", new Integer(127)
				.byteValue() == 127);
	}

	/**
	 * @tests java.lang.Integer#compareTo(java.lang.Integer)
	 */
	public void test_compareToLjava_lang_Integer() {
		// Test for method int java.lang.Integer.compareTo(java.lang.Integer)
		assertTrue("-2 compared to 1 gave non-negative answer", new Integer(-2)
				.compareTo(new Integer(1)) < 0);
		assertTrue("-2 compared to -2 gave non-zero answer", new Integer(-2)
				.compareTo(new Integer(-2)) == 0);
		assertTrue("3 compared to 2 gave non-positive answer", new Integer(3)
				.compareTo(new Integer(2)) > 0);
	}

	/**
	 * @tests java.lang.Integer#compareTo(java.lang.Object)
	 */
	public void test_compareToLjava_lang_Object() {
		// Test for method int java.lang.Integer.compareTo(java.lang.Object)
		assertTrue("-2 compared to 1 gave non-negative answer", new Integer(-2)
				.compareTo((Object) new Integer(1)) < 0);
		assertTrue("-2 compared to -2 gave non-zero answer", new Integer(-2)
				.compareTo((Object) new Integer(-2)) == 0);
		assertTrue("3 compared to 2 gave non-positive answer", new Integer(3)
				.compareTo((Object) new Integer(2)) > 0);
		try {
			new Integer(3).compareTo(new Object());
		} catch (ClassCastException e) {
			// correct
			return;
		}
		fail(
				"3 compared to a non-integer object failed to throw an exception");
	}

	/**
	 * @tests java.lang.Integer#decode(java.lang.String)
	 */
	public void test_decodeLjava_lang_String() {
		// Test for method java.lang.Integer
		// java.lang.Integer.decode(java.lang.String)
		assertTrue("Failed for 132233",
				Integer.decode("132233").intValue() == 132233);
		assertTrue("Failed for 07654321",
				Integer.decode("07654321").intValue() == 07654321);
		assertTrue("Failed for #1234567",
				Integer.decode("#1234567").intValue() == 0x1234567);
		assertTrue("Failed for 0xdAd",
				Integer.decode("0xdAd").intValue() == 0xdad);
		assertTrue("Failed for -23", Integer.decode("-23").intValue() == -23);
		assertTrue("Returned incorrect value for 0 decimal", Integer
				.decode("0").intValue() == 0);
		assertTrue("Returned incorrect value for 0 hex", Integer.decode("0x0")
				.intValue() == 0);
		assertTrue("Returned incorrect value for most negative value decimal",
				Integer.decode("-2147483648").intValue() == 0x80000000);
		assertTrue("Returned incorrect value for most negative value hex",
				Integer.decode("-0x80000000").intValue() == 0x80000000);
		assertTrue("Returned incorrect value for most positive value decimal",
				Integer.decode("2147483647").intValue() == 0x7fffffff);
		assertTrue("Returned incorrect value for most positive value hex",
				Integer.decode("0x7fffffff").intValue() == 0x7fffffff);

		boolean exception = false;
		try {
			Integer.decode("0a");
		} catch (NumberFormatException e) {
			// correct
			exception = true;
		}
		assertTrue("Failed to throw NumberFormatException for \"Oa\"",
				exception);

		exception = false;
		try {
			Integer.decode("2147483648");
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception for MAX_VALUE + 1", exception);

		exception = false;
		try {
			Integer.decode("-2147483649");
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception for MIN_VALUE - 1", exception);

		exception = false;
		try {
			Integer.decode("0x80000000");
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception for hex MAX_VALUE + 1", exception);

		exception = false;
		try {
			Integer.decode("-0x80000001");
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception for hex MIN_VALUE - 1", exception);

		exception = false;
		try {
			Integer.decode("9999999999");
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception for 9999999999", exception);
	}

	/**
	 * @tests java.lang.Integer#doubleValue()
	 */
	public void test_doubleValue() {
		// Test for method double java.lang.Integer.doubleValue()
		assertTrue("Returned incorrect double value", new Integer(2147483647)
				.doubleValue() == 2147483647.0);
		assertTrue("Returned incorrect double value", new Integer(-2147483647)
				.doubleValue() == -2147483647.0);
	}

	/**
	 * @tests java.lang.Integer#equals(java.lang.Object)
	 */
	public void test_equalsLjava_lang_Object() {
		// Test for method boolean java.lang.Integer.equals(java.lang.Object)
		Integer i1 = new Integer(1000);
		Integer i2 = new Integer(1000);
		Integer i3 = new Integer(-1000);
		assertTrue("Equality test failed", i1.equals(i2) && !(i1.equals(i3)));
	}

	/**
	 * @tests java.lang.Integer#floatValue()
	 */
	public void test_floatValue() {
		// Test for method float java.lang.Integer.floatValue()
		assertTrue("Returned incorrect float value", new Integer(65535)
				.floatValue() == 65535.0f);
		assertTrue("Returned incorrect float value", new Integer(-65535)
				.floatValue() == -65535.0f);
	}

	/**
	 * @tests java.lang.Integer#getInteger(java.lang.String)
	 */
	public void test_getIntegerLjava_lang_String() {
		// Test for method java.lang.Integer
		// java.lang.Integer.getInteger(java.lang.String)
		Properties tProps = new Properties();
		tProps.put("testInt", "99");
		System.setProperties(tProps);
		assertTrue("returned incorrect Integer", Integer.getInteger("testInt")
				.equals(new Integer(99)));
		assertTrue("returned incorrect default Integer", Integer
				.getInteger("ff") == null);
	}

	/**
	 * @tests java.lang.Integer#getInteger(java.lang.String, int)
	 */
	public void test_getIntegerLjava_lang_StringI() {
		// Test for method java.lang.Integer
		// java.lang.Integer.getInteger(java.lang.String, int)
		Properties tProps = new Properties();
		tProps.put("testInt", "99");
		System.setProperties(tProps);
		assertTrue("returned incorrect Integer", Integer.getInteger("testInt",
				4).equals(new Integer(99)));
		assertTrue("returned incorrect default Integer", Integer.getInteger(
				"ff", 4).equals(new Integer(4)));
	}

	/**
	 * @tests java.lang.Integer#getInteger(java.lang.String, java.lang.Integer)
	 */
	public void test_getIntegerLjava_lang_StringLjava_lang_Integer() {
		// Test for method java.lang.Integer
		// java.lang.Integer.getInteger(java.lang.String, java.lang.Integer)
		Properties tProps = new Properties();
		tProps.put("testInt", "99");
		System.setProperties(tProps);
		assertTrue("returned incorrect Integer", Integer.getInteger("testInt",
				new Integer(4)).equals(new Integer(99)));
		assertTrue("returned incorrect default Integer", Integer.getInteger(
				"ff", new Integer(4)).equals(new Integer(4)));
	}

	/**
	 * @tests java.lang.Integer#hashCode()
	 */
	public void test_hashCode() {
		// Test for method int java.lang.Integer.hashCode()

		Integer i1 = new Integer(1000);
		Integer i2 = new Integer(-1000);
		assertTrue("Returned incorrect hashcode", i1.hashCode() == 1000
				&& (i2.hashCode() == -1000));
	}

	/**
	 * @tests java.lang.Integer#intValue()
	 */
	public void test_intValue() {
		// Test for method int java.lang.Integer.intValue()

		Integer i = new Integer(8900);
		assertTrue("Returned incorrect int value", i.intValue() == 8900);
	}

	/**
	 * @tests java.lang.Integer#longValue()
	 */
	public void test_longValue() {
		// Test for method long java.lang.Integer.longValue()
		Integer i = new Integer(8900);
		assertTrue("Returned incorrect long value", i.longValue() == 8900L);
	}

	/**
	 * @tests java.lang.Integer#parseInt(java.lang.String)
	 */
	public void test_parseIntLjava_lang_String() {
		// Test for method int java.lang.Integer.parseInt(java.lang.String)

		int i = Integer.parseInt("-8900");
		assertTrue("Returned incorrect int", i == -8900);
		assertTrue("Returned incorrect value for 0", Integer.parseInt("0") == 0);
		assertTrue("Returned incorrect value for most negative value", Integer
				.parseInt("-2147483648") == 0x80000000);
		assertTrue("Returned incorrect value for most positive value", Integer
				.parseInt("2147483647") == 0x7fffffff);

		boolean exception = false;
		try {
			Integer.parseInt("999999999999");
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception for value > int", exception);

		exception = false;
		try {
			Integer.parseInt("2147483648");
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception for MAX_VALUE + 1", exception);

		exception = false;
		try {
			Integer.parseInt("-2147483649");
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception for MIN_VALUE - 1", exception);
	}

	/**
	 * @tests java.lang.Integer#parseInt(java.lang.String, int)
	 */
	public void test_parseIntLjava_lang_StringI() {
		// Test for method int java.lang.Integer.parseInt(java.lang.String, int)
		assertTrue("Parsed dec val incorrectly",
				Integer.parseInt("-8000", 10) == -8000);
		assertTrue("Parsed hex val incorrectly",
				Integer.parseInt("FF", 16) == 255);
		assertTrue("Parsed oct val incorrectly",
				Integer.parseInt("20", 8) == 16);
		assertTrue("Returned incorrect value for 0 hex", Integer.parseInt("0",
				16) == 0);
		assertTrue("Returned incorrect value for most negative value hex",
				Integer.parseInt("-80000000", 16) == 0x80000000);
		assertTrue("Returned incorrect value for most positive value hex",
				Integer.parseInt("7fffffff", 16) == 0x7fffffff);
		assertTrue("Returned incorrect value for 0 decimal", Integer.parseInt(
				"0", 10) == 0);
		assertTrue("Returned incorrect value for most negative value decimal",
				Integer.parseInt("-2147483648", 10) == 0x80000000);
		assertTrue("Returned incorrect value for most positive value decimal",
				Integer.parseInt("2147483647", 10) == 0x7fffffff);

		boolean exception = false;
		try {
			Integer.parseInt("FFFF", 10);
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue(
				"Failed to throw exception when passes hex string and dec parm",
				exception);

		exception = false;
		try {
			Integer.parseInt("2147483648", 10);
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception for MAX_VALUE + 1", exception);

		exception = false;
		try {
			Integer.parseInt("-2147483649", 10);
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception for MIN_VALUE - 1", exception);

		exception = false;
		try {
			Integer.parseInt("80000000", 16);
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception for hex MAX_VALUE + 1", exception);

		exception = false;
		try {
			Integer.parseInt("-80000001", 16);
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception for hex MIN_VALUE + 1", exception);

		exception = false;
		try {
			Integer.parseInt("9999999999", 10);
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception for 9999999999", exception);
	}

	/**
	 * @tests java.lang.Integer#shortValue()
	 */
	public void test_shortValue() {
		// Test for method short java.lang.Integer.shortValue()
		Integer i = new Integer(2147450880);
		assertTrue("Returned incorrect long value", i.shortValue() == -32768);
	}

	/**
	 * @tests java.lang.Integer#toBinaryString(int)
	 */
	public void test_toBinaryStringI() {
		// Test for method java.lang.String
		// java.lang.Integer.toBinaryString(int)
		assertTrue("Incorrect string returned", Integer.toBinaryString(
				Integer.MAX_VALUE).equals("1111111111111111111111111111111"));
		assertTrue("Incorrect string returned", Integer.toBinaryString(
				Integer.MIN_VALUE).equals("10000000000000000000000000000000"));
	}

	/**
	 * @tests java.lang.Integer#toHexString(int)
	 */
	public void test_toHexStringI() {
		// Test for method java.lang.String java.lang.Integer.toHexString(int)

		String[] hexvals = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"a", "b", "c", "d", "e", "f" };

		for (int i = 0; i < 16; i++)
			assertTrue("Incorrect string returned " + hexvals[i], Integer
					.toHexString(i).equals(hexvals[i]));

		assertTrue("Returned incorrect hex string: "
				+ Integer.toHexString(Integer.MAX_VALUE), Integer.toHexString(
				Integer.MAX_VALUE).equals("7fffffff"));
		assertTrue("Returned incorrect hex string: "
				+ Integer.toHexString(Integer.MIN_VALUE), Integer.toHexString(
				Integer.MIN_VALUE).equals("80000000"));
	}

	/**
	 * @tests java.lang.Integer#toOctalString(int)
	 */
	public void test_toOctalStringI() {
		// Test for method java.lang.String java.lang.Integer.toOctalString(int)
		// Spec states that the int arg is treated as unsigned
		assertTrue("Returned incorrect octal string", Integer.toOctalString(
				Integer.MAX_VALUE).equals("17777777777"));
		assertTrue("Returned incorrect octal string", Integer.toOctalString(
				Integer.MIN_VALUE).equals("20000000000"));
	}

	/**
	 * @tests java.lang.Integer#toString()
	 */
	public void test_toString() {
		// Test for method java.lang.String java.lang.Integer.toString()

		Integer i = new Integer(-80001);

		assertTrue("Returned incorrect String", i.toString().equals("-80001"));
	}

	/**
	 * @tests java.lang.Integer#toString(int)
	 */
	public void test_toStringI() {
		// Test for method java.lang.String java.lang.Integer.toString(int)

		assertTrue("Returned incorrect String", Integer.toString(-80765)
				.equals("-80765"));
		assertTrue("Returned incorrect octal string", Integer.toString(
				Integer.MAX_VALUE).equals("2147483647"));
		assertTrue("Returned incorrect octal string", Integer.toString(
				-Integer.MAX_VALUE).equals("-2147483647"));
		assertTrue("Returned incorrect octal string", Integer.toString(
				Integer.MIN_VALUE).equals("-2147483648"));
	}

	/**
	 * @tests java.lang.Integer#toString(int, int)
	 */
	public void test_toStringII() {
		// Test for method java.lang.String java.lang.Integer.toString(int, int)
		assertTrue("Returned incorrect octal string", Integer.toString(
				2147483647, 8).equals("17777777777"));
		assertTrue("Returned incorrect hex string--wanted 7fffffff but got: "
				+ Integer.toString(2147483647, 16), Integer.toString(
				2147483647, 16).equals("7fffffff"));
		assertTrue("Incorrect string returned", Integer.toString(2147483647, 2)
				.equals("1111111111111111111111111111111"));
		assertTrue("Incorrect string returned", Integer
				.toString(2147483647, 10).equals("2147483647"));

		assertTrue("Returned incorrect octal string", Integer.toString(
				-2147483647, 8).equals("-17777777777"));
		assertTrue("Returned incorrect hex string--wanted -7fffffff but got: "
				+ Integer.toString(-2147483647, 16), Integer.toString(
				-2147483647, 16).equals("-7fffffff"));
		assertTrue("Incorrect string returned", Integer
				.toString(-2147483647, 2).equals(
						"-1111111111111111111111111111111"));
		assertTrue("Incorrect string returned", Integer.toString(-2147483647,
				10).equals("-2147483647"));

		assertTrue("Returned incorrect octal string", Integer.toString(
				-2147483648, 8).equals("-20000000000"));
		assertTrue("Returned incorrect hex string--wanted -80000000 but got: "
				+ Integer.toString(-2147483648, 16), Integer.toString(
				-2147483648, 16).equals("-80000000"));
		assertTrue("Incorrect string returned", Integer
				.toString(-2147483648, 2).equals(
						"-10000000000000000000000000000000"));
		assertTrue("Incorrect string returned", Integer.toString(-2147483648,
				10).equals("-2147483648"));
	}

	/**
	 * @tests java.lang.Integer#valueOf(java.lang.String)
	 */
	public void test_valueOfLjava_lang_String() {
		// Test for method java.lang.Integer
		// java.lang.Integer.valueOf(java.lang.String)
		assertTrue("Returned incorrect int", Integer.valueOf("8888888")
				.intValue() == 8888888);
		assertTrue("Returned incorrect int", Integer.valueOf("2147483647")
				.intValue() == Integer.MAX_VALUE);
		assertTrue("Returned incorrect int", Integer.valueOf("-2147483648")
				.intValue() == Integer.MIN_VALUE);

		boolean exception = false;
		try {
			Integer.valueOf("2147483648");
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception with MAX_VALUE + 1", exception);

		exception = false;
		try {
			Integer.valueOf("-2147483649");
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception with MIN_VALUE - 1", exception);
	}

	/**
	 * @tests java.lang.Integer#valueOf(java.lang.String, int)
	 */
	public void test_valueOfLjava_lang_StringI() {
		// Test for method java.lang.Integer
		// java.lang.Integer.valueOf(java.lang.String, int)
		assertTrue("Returned incorrect int for hex string", Integer.valueOf(
				"FF", 16).intValue() == 255);
		assertTrue("Returned incorrect int for oct string", Integer.valueOf(
				"20", 8).intValue() == 16);
		assertTrue("Returned incorrect int for bin string", Integer.valueOf(
				"100", 2).intValue() == 4);

		assertTrue("Returned incorrect int for - hex string", Integer.valueOf(
				"-FF", 16).intValue() == -255);
		assertTrue("Returned incorrect int for - oct string", Integer.valueOf(
				"-20", 8).intValue() == -16);
		assertTrue("Returned incorrect int for - bin string", Integer.valueOf(
				"-100", 2).intValue() == -4);
		assertTrue("Returned incorrect int", Integer.valueOf("2147483647", 10)
				.intValue() == Integer.MAX_VALUE);
		assertTrue("Returned incorrect int", Integer.valueOf("-2147483648", 10)
				.intValue() == Integer.MIN_VALUE);
		assertTrue("Returned incorrect int", Integer.valueOf("7fffffff", 16)
				.intValue() == Integer.MAX_VALUE);
		assertTrue("Returned incorrect int", Integer.valueOf("-80000000", 16)
				.intValue() == Integer.MIN_VALUE);

		boolean exception = false;
		try {
			Integer.valueOf("FF", 2);
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue(
				"Failed to throw exception with hex string and base 2 radix",
				exception);

		exception = false;
		try {
			Integer.valueOf("2147483648", 10);
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception with MAX_VALUE + 1", exception);

		exception = false;
		try {
			Integer.valueOf("-2147483649", 10);
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception with MIN_VALUE - 1", exception);

		exception = false;
		try {
			Integer.valueOf("80000000", 16);
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception with hex MAX_VALUE + 1",
				exception);

		exception = false;
		try {
			Integer.valueOf("-80000001", 16);
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception with hex MIN_VALUE - 1",
				exception);
	}

	/**
	 * Sets up the fixture, for example, open a network connection. This method
	 * is called before a test is executed.
	 */
	protected void setUp() {
		orgProps = System.getProperties();
	}

	/**
	 * Tears down the fixture, for example, close a network connection. This
	 * method is called after a test is executed.
	 */
	protected void tearDown() {
		System.setProperties(orgProps);
	}
}
