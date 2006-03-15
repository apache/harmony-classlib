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

public class ByteTest extends junit.framework.TestCase {

	/**
	 * @tests java.lang.Byte#Byte(byte)
	 */
	public void test_ConstructorB() {
		// Test for method java.lang.Byte(byte)

		Byte b = new Byte((byte) 127);
		assertTrue("Byte creation failed", b.byteValue() == (byte) 127);
	}

	/**
	 * @tests java.lang.Byte#Byte(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		// Test for method java.lang.Byte(java.lang.String)

		Byte b = new Byte("127");
		Byte nb = new Byte("-128");
		assertTrue("Incorrect Byte Object created", b.byteValue() == (byte) 127
				&& (nb.byteValue() == (byte) -128));

	}

	/**
	 * @tests java.lang.Byte#byteValue()
	 */
	public void test_byteValue() {
		// Test for method byte java.lang.Byte.byteValue()
		assertTrue("Returned incorrect byte value", new Byte((byte) 127)
				.byteValue() == (byte) (127));
	}

	/**
	 * @tests java.lang.Byte#compareTo(java.lang.Byte)
	 */
	public void test_compareToLjava_lang_Byte() {
		// Test for method int java.lang.Byte.compareTo(java.lang.Byte)
		assertTrue("Comparison failed", new Byte((byte) 1).compareTo(new Byte(
				(byte) 2)) < 0);
		assertTrue("Comparison failed", new Byte((byte) 1).compareTo(new Byte(
				(byte) -2)) > 0);
		assertTrue("Comparison failed", new Byte((byte) 1).compareTo(new Byte(
				(byte) 1)) == 0);
	}

	/**
	 * @tests java.lang.Byte#compareTo(java.lang.Object)
	 */
	public void test_compareToLjava_lang_Object() {
		// Test for method int java.lang.Byte.compareTo(java.lang.Object)
		assertTrue("Comparison failed", new Byte((byte) 1)
				.compareTo((Object) new Byte((byte) 2)) < 0);
		assertTrue("Comparison failed", new Byte((byte) 1)
				.compareTo((Object) new Byte((byte) -2)) > 0);
		assertTrue("Comparison failed", new Byte((byte) 1)
				.compareTo((Object) new Byte((byte) 1)) == 0);
		try {
			new Byte((byte) 1).compareTo(new Object());
		} catch (ClassCastException e) {
			// correct
			return;
		}
		fail("Failed to throw ClassCastException");
	}

	/**
	 * @tests java.lang.Byte#decode(java.lang.String)
	 */
	public void test_decodeLjava_lang_String() {
		// Test for method java.lang.Byte
		// java.lang.Byte.decode(java.lang.String)
		assertTrue("String decoded incorrectly, wanted: 1 got: "
				+ Byte.decode("1").toString(), Byte.decode("1").equals(
				new Byte((byte) 1)));
		assertTrue("String decoded incorrectly, wanted: -1 got: "
				+ Byte.decode("-1").toString(), Byte.decode("-1").equals(
				new Byte((byte) -1)));
		assertTrue("String decoded incorrectly, wanted: 127 got: "
				+ Byte.decode("127").toString(), Byte.decode("127").equals(
				new Byte((byte) 127)));
		assertTrue("String decoded incorrectly, wanted: -128 got: "
				+ Byte.decode("-128").toString(), Byte.decode("-128").equals(
				new Byte((byte) -128)));
		assertTrue("String decoded incorrectly, wanted: 127 got: "
				+ Byte.decode("0x7f").toString(), Byte.decode("0x7f").equals(
				new Byte((byte) 127)));
		assertTrue("String decoded incorrectly, wanted: -128 got: "
				+ Byte.decode("-0x80").toString(), Byte.decode("-0x80").equals(
				new Byte((byte) -128)));

		boolean exception = false;
		try {
			Byte.decode("128");
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception for MAX_VALUE + 1", exception);

		exception = false;
		try {
			Byte.decode("-129");
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception for MIN_VALUE - 1", exception);

		exception = false;
		try {
			Byte.decode("0x80");
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception for hex MAX_VALUE + 1", exception);

		exception = false;
		try {
			Byte.decode("-0x81");
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception for hex MIN_VALUE - 1", exception);
	}

	/**
	 * @tests java.lang.Byte#doubleValue()
	 */
	public void test_doubleValue() {
		// Test for method double java.lang.Byte.doubleValue()
		assertTrue("Returned incorrect double value", new Byte((byte) 127)
				.doubleValue() == (double) (127));
	}

	/**
	 * @tests java.lang.Byte#equals(java.lang.Object)
	 */
	public void test_equalsLjava_lang_Object() {
		// Test for method boolean java.lang.Byte.equals(java.lang.Object)
		Byte b1 = new Byte((byte) 90);
		Byte b2 = new Byte((byte) 90);
		Byte b3 = new Byte((byte) -90);
		assertTrue("Equality test failed", b1.equals(b2));
		assertTrue("Equality test failed", !b1.equals(b3));
	}

	/**
	 * @tests java.lang.Byte#floatValue()
	 */
	public void test_floatValue() {
		// Test for method float java.lang.Byte.floatValue()
		assertTrue("Returned incorrect float value", new Byte((byte) 127)
				.byteValue() == (float) (127));
	}

	/**
	 * @tests java.lang.Byte#hashCode()
	 */
	public void test_hashCode() {
		// Test for method int java.lang.Byte.hashCode()
		assertTrue("Incorrect hash returned",
				new Byte((byte) 127).hashCode() == 127);
	}

	/**
	 * @tests java.lang.Byte#intValue()
	 */
	public void test_intValue() {
		// Test for method int java.lang.Byte.intValue()
		assertTrue("Returned incorrect int value", new Byte((byte) 127)
				.intValue() == 127);
	}

	/**
	 * @tests java.lang.Byte#longValue()
	 */
	public void test_longValue() {
		// Test for method long java.lang.Byte.longValue()
		assertTrue("Returned incorrect long value", new Byte((byte) 127)
				.longValue() == 127L);
	}

	/**
	 * @tests java.lang.Byte#parseByte(java.lang.String)
	 */
	public void test_parseByteLjava_lang_String() {
		// Test for method byte java.lang.Byte.parseByte(java.lang.String)

		byte b = Byte.parseByte("127");
		byte bn = Byte.parseByte("-128");
		assertTrue("Invalid parse of byte", b == (byte) 127
				&& (bn == (byte) -128));
		assertTrue("Returned incorrect value for 0", Byte.parseByte("0") == 0);
		assertTrue("Returned incorrect value for most negative value", Byte
				.parseByte("-128") == (byte) 0x80);
		assertTrue("Returned incorrect value for most positive value", Byte
				.parseByte("127") == 0x7f);

		boolean exception = false;
		try {
			Byte.parseByte("-1000");
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception", exception);

		exception = false;
		try {
			Byte.parseByte("128");
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception for MAX_VALUE + 1", exception);

		exception = false;
		try {
			Byte.parseByte("-129");
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception for MIN_VALUE - 1", exception);
	}

	/**
	 * @tests java.lang.Byte#parseByte(java.lang.String, int)
	 */
	public void test_parseByteLjava_lang_StringI() {
		// Test for method byte java.lang.Byte.parseByte(java.lang.String, int)
		byte b = Byte.parseByte("127", 10);
		byte bn = Byte.parseByte("-128", 10);
		assertTrue("Invalid parse of dec byte", b == (byte) 127
				&& (bn == (byte) -128));
		assertTrue("Failed to parse hex value", Byte.parseByte("A", 16) == 10);
		assertTrue("Returned incorrect value for 0 hex", Byte
				.parseByte("0", 16) == 0);
		assertTrue("Returned incorrect value for most negative value hex", Byte
				.parseByte("-80", 16) == (byte) 0x80);
		assertTrue("Returned incorrect value for most positive value hex", Byte
				.parseByte("7f", 16) == 0x7f);
		assertTrue("Returned incorrect value for 0 decimal", Byte.parseByte(
				"0", 10) == 0);
		assertTrue("Returned incorrect value for most negative value decimal",
				Byte.parseByte("-128", 10) == (byte) 0x80);
		assertTrue("Returned incorrect value for most positive value decimal",
				Byte.parseByte("127", 10) == 0x7f);

		boolean exception = false;
		try {
			Byte.parseByte("-1000", 10);
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception", exception);

		exception = false;
		try {
			Byte.parseByte("128", 10);
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception for MAX_VALUE + 1", exception);

		exception = false;
		try {
			Byte.parseByte("-129", 10);
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception for MIN_VALUE - 1", exception);

		exception = false;
		try {
			Byte.parseByte("80", 16);
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception for hex MAX_VALUE + 1", exception);

		exception = false;
		try {
			Byte.parseByte("-81", 16);
		} catch (NumberFormatException e) {
			// Correct
			exception = true;
		}
		assertTrue("Failed to throw exception for hex MIN_VALUE + 1", exception);
	}

	/**
	 * @tests java.lang.Byte#shortValue()
	 */
	public void test_shortValue() {
		// Test for method short java.lang.Byte.shortValue()
		assertTrue("Returned incorrect short value", new Byte((byte) 127)
				.shortValue() == (short) (127));
	}

	/**
	 * @tests java.lang.Byte#toString()
	 */
	public void test_toString() {
		// Test for method java.lang.String java.lang.Byte.toString()
		assertTrue("Returned incorrect String", new Byte((byte) 127).toString()
				.equals("127"));
		assertTrue("Returned incorrect String", new Byte((byte) -127)
				.toString().equals("-127"));
		assertTrue("Returned incorrect String", new Byte((byte) -128)
				.toString().equals("-128"));
	}

	/**
	 * @tests java.lang.Byte#toString(byte)
	 */
	public void test_toStringB() {
		// Test for method java.lang.String java.lang.Byte.toString(byte)
		assertTrue("Returned incorrect String", Byte.toString((byte) 127)
				.equals("127"));
		assertTrue("Returned incorrect String", Byte.toString((byte) -127)
				.equals("-127"));
		assertTrue("Returned incorrect String", Byte.toString((byte) -128)
				.equals("-128"));
	}

	/**
	 * @tests java.lang.Byte#valueOf(java.lang.String)
	 */
	public void test_valueOfLjava_lang_String() {
		// Test for method java.lang.Byte
		// java.lang.Byte.valueOf(java.lang.String)
		assertTrue("Returned incorrect byte",
				Byte.valueOf("0").byteValue() == 0);
		assertTrue("Returned incorrect byte",
				Byte.valueOf("127").byteValue() == 127);
		assertTrue("Returned incorrect byte",
				Byte.valueOf("-127").byteValue() == -127);
		assertTrue("Returned incorrect byte",
				Byte.valueOf("-128").byteValue() == -128);

		try {
			Byte.valueOf("128");
		} catch (NumberFormatException e) {
			// Correct
			return;
		}
		fail("Failed to throw exception when passes value > byte");
	}

	/**
	 * @tests java.lang.Byte#valueOf(java.lang.String, int)
	 */
	public void test_valueOfLjava_lang_StringI() {
		// Test for method java.lang.Byte
		// java.lang.Byte.valueOf(java.lang.String, int)
		assertTrue("Returned incorrect byte",
				Byte.valueOf("A", 16).byteValue() == 10);
		assertTrue("Returned incorrect byte", Byte.valueOf("127", 10)
				.byteValue() == 127);
		assertTrue("Returned incorrect byte", Byte.valueOf("-127", 10)
				.byteValue() == -127);
		assertTrue("Returned incorrect byte", Byte.valueOf("-128", 10)
				.byteValue() == -128);
		assertTrue("Returned incorrect byte", Byte.valueOf("7f", 16)
				.byteValue() == 127);
		assertTrue("Returned incorrect byte", Byte.valueOf("-7f", 16)
				.byteValue() == -127);
		assertTrue("Returned incorrect byte", Byte.valueOf("-80", 16)
				.byteValue() == -128);

		try {
			Byte.valueOf("128", 10);
		} catch (NumberFormatException e) {
			// Correct
			return;
		}
		fail("Failed to throw exception when passes value > byte");
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
