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

package tests.api.java.text;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Currency;
import java.util.Locale;

import tests.support.Support_BitSet;
import tests.support.Support_DecimalFormat;

public class DecimalFormatTest extends junit.framework.TestCase {

	/**
	 * @tests java.text.DecimalFormat#DecimalFormat(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		// Test for method java.text.DecimalFormat(java.lang.String)
		// the constructor form that specifies a pattern is equal to the form
		// constructed with no pattern and applying that pattern using the
		// applyPattern call
		DecimalFormat format = new DecimalFormat("'$'0000.0000");
		DecimalFormat format1 = new DecimalFormat();
		format1.applyPattern("'$'0000.0000");
		assertTrue("Constructed format did not match applied format object",
				format.equals(format1));
	}

	/**
	 * @tests java.text.DecimalFormat#applyPattern(java.lang.String)
	 */
	public void test_applyPatternLjava_lang_String() {
		DecimalFormat format = new DecimalFormat("#.#");
		assertTrue("Wrong pattern 1", format.toPattern().equals("#0.#"));
		format = new DecimalFormat("#.");
		assertTrue("Wrong pattern 2", format.toPattern().equals("#0."));
		format = new DecimalFormat("#");
		assertTrue("Wrong pattern 3", format.toPattern().equals("#"));
		format = new DecimalFormat(".#");
		assertTrue("Wrong pattern 4", format.toPattern().equals("#.0"));
	}

	/**
	 * @tests java.text.DecimalFormat#clone()
	 */
	public void test_clone() {
		DecimalFormat format = new DecimalFormat("'$'0000.0000");
		DecimalFormat format1 = (DecimalFormat) (format.clone());
		// make sure the objects are equal
		assertTrue("Object's clone isn't equal!", format.equals(format1));
		// change the content of the clone and make sure it's not equal anymore
		// verifies that it's data is now distinct from the original
		format1.applyPattern("'$'0000.####");
		assertTrue("Object's changed clone should not be equal!", !format
				.equals(format1));
	}

	private void compare(String testName, String format, String expected) {
		assertTrue(testName + " got: " + format + " expected: " + expected,
				format.equals(expected));
	}

	private boolean compare(int count, String format, String expected) {
		boolean result = format.equals(expected);
		if (!result)
			System.out.println("Failure test: " + count + " got: " + format
					+ " expected: " + expected);
		return result;
	}

	/**
	 * @tests java.text.DecimalFormat#format(double, java.lang.StringBuffer,
	 *        java.text.FieldPosition)
	 */
	public void test_formatDLjava_lang_StringBufferLjava_text_FieldPosition() {
		new Support_DecimalFormat(
				"test_formatDLjava_lang_StringBufferLjava_text_FieldPosition")
				.t_format_with_FieldPosition();

		int failCount = 0;
		Support_BitSet failures = new Support_BitSet();

		DecimalFormat df = new DecimalFormat("00.0#E0");
		compare("00.0#E0: 0.0", df.format(0.0), "00.0E0");
		compare("00.0#E0: 1.0", df.format(1.0), "10.0E-1");
		compare("00.0#E0: 12.0", df.format(12.0), "12.0E0");
		compare("00.0#E0: 123.0", df.format(123.0), "12.3E1");
		compare("00.0#E0: 1234.0", df.format(1234.0), "12.34E2");
		compare("00.0#E0: 12346.0", df.format(12346.0), "12.35E3");
		compare("00.0#E0: 99999.0", df.format(99999.0), "10.0E4");
		compare("00.0#E0: 1.2", df.format(1.2), "12.0E-1");
		compare("00.0#E0: 12.3", df.format(12.3), "12.3E0");
		compare("00.0#E0: 123.4", df.format(123.4), "12.34E1");
		compare("00.0#E0: 1234.6", df.format(1234.6), "12.35E2");
		compare("00.0#E0: 9999.9", df.format(9999.9), "10.0E3");
		compare("00.0#E0: 0.1", df.format(0.1), "10.0E-2");
		compare("00.0#E0: 0.12", df.format(0.12), "12.0E-2");
		compare("00.0#E0: 0.123", df.format(0.123), "12.3E-2");
		compare("00.0#E0: 0.1234", df.format(0.1234), "12.34E-2");
		compare("00.0#E0: 0.12346", df.format(0.12346), "12.35E-2");
		compare("00.0#E0: 0.99999", df.format(0.99999), "10.0E-1");
		compare("00.0#E0: -0.0", df.format(-0.0), "-00.0E0");
		compare("00.0#E0: -1.0", df.format(-1.0), "-10.0E-1");
		compare("00.0#E0: -12.0", df.format(-12.0), "-12.0E0");
		compare("00.0#E0: -123.0", df.format(-123.0), "-12.3E1");
		compare("00.0#E0: -1234.0", df.format(-1234.0), "-12.34E2");
		compare("00.0#E0: -12346.0", df.format(-12346.0), "-12.35E3");
		compare("00.0#E0: -99999.0", df.format(-99999.0), "-10.0E4");

		df = new DecimalFormat("##0.0E0");
		compare("##0.0E0: -0.0", df.format(-0.0), "-0.0E0");
		compare("##0.0E0: 0.0", df.format(0.0), "0.0E0");
		compare("##0.0E0: 1.0", df.format(1.0), "1.0E0");
		compare("##0.0E0: 12.0", df.format(12.0), "12E0");
		compare("##0.0E0: 123.0", df.format(123.0), "123E0");
		compare("##0.0E0: 1234.0", df.format(1234.0), "1.234E3");
		compare("##0.0E0: 12346.0", df.format(12346.0), "12.35E3");
		// Fails in JDK 1.2.2
		if (!compare(failCount, df.format(99999.0), "100E3"))
			failures.set(failCount);
		failCount++;
		compare("##0.0E0: 999999.0", df.format(999999.0), "1.0E6");

		df = new DecimalFormat("#00.0##E0");
		compare("#00.0##E0: 0.1", df.format(0.1), ".100E0");
		compare("#00.0##E0: 0.12", df.format(0.12), ".120E0");
		compare("#00.0##E0: 0.123", df.format(0.123), ".123E0");
		compare("#00.0##E0: 0.1234", df.format(0.1234), ".1234E0");
		compare("#00.0##E0: 0.1234567", df.format(0.1234567), ".123457E0");
		compare("#00.0##E0: 0.01", df.format(0.01), "10.0E-3");
		compare("#00.0##E0: 0.012", df.format(0.012), "12.0E-3");
		compare("#00.0##E0: 0.0123", df.format(0.0123), "12.3E-3");
		compare("#00.0##E0: 0.01234", df.format(0.01234), "12.34E-3");
		compare("#00.0##E0: 0.01234567", df.format(0.01234567), "12.3457E-3");
		compare("#00.0##E0: 0.001", df.format(0.001), "1.00E-3");
		compare("#00.0##E0: 0.0012", df.format(0.0012), "1.20E-3");
		compare("#00.0##E0: 0.00123", df.format(0.00123), "1.23E-3");
		compare("#00.0##E0: 0.001234", df.format(0.001234), "1.234E-3");
		compare("#00.0##E0: 0.001234567", df.format(0.001234567), "1.23457E-3");
		compare("#00.0##E0: 0.0001", df.format(0.0001), "100E-6");
		compare("#00.0##E0: 0.00012", df.format(0.00012), "120E-6");
		compare("#00.0##E0: 0.000123", df.format(0.000123), "123E-6");
		compare("#00.0##E0: 0.0001234", df.format(0.0001234), "123.4E-6");
		compare("#00.0##E0: 0.0001234567", df.format(0.0001234567),
				"123.457E-6");

		// Fails in JDK 1.2.2
		if (!compare(failCount, df.format(0.0), "0.00E0"))
			failures.set(failCount);
		failCount++;
		compare("#00.0##E0: 1.0", df.format(1.0), "1.00E0");
		compare("#00.0##E0: 12.0", df.format(12.0), "12.0E0");
		compare("#00.0##E0: 123.0", df.format(123.0), "123E0");
		compare("#00.0##E0: 1234.0", df.format(1234.0), "1.234E3");
		compare("#00.0##E0: 12345.0", df.format(12345.0), "12.345E3");
		compare("#00.0##E0: 123456.0", df.format(123456.0), "123.456E3");
		compare("#00.0##E0: 1234567.0", df.format(1234567.0), "1.23457E6");
		compare("#00.0##E0: 12345678.0", df.format(12345678.0), "12.3457E6");
		compare("#00.0##E0: 99999999.0", df.format(99999999.0), "100E6");

		df = new DecimalFormat("#.0E0");
		compare("#.0E0: -0.0", df.format(-0.0), "-.0E0");
		compare("#.0E0: 0.0", df.format(0.0), ".0E0");
		compare("#.0E0: 1.0", df.format(1.0), ".1E1");
		compare("#.0E0: 12.0", df.format(12.0), ".12E2");
		compare("#.0E0: 123.0", df.format(123.0), ".12E3");
		compare("#.0E0: 1234.0", df.format(1234.0), ".12E4");
		compare("#.0E0: 9999.0", df.format(9999.0), ".1E5");

		df = new DecimalFormat("0.#E0");
		compare("0.#E0: -0.0", df.format(-0.0), "-0E0");
		compare("0.#E0: 0.0", df.format(0.0), "0E0");
		compare("0.#E0: 1.0", df.format(1.0), "1E0");
		compare("0.#E0: 12.0", df.format(12.0), "1.2E1");
		compare("0.#E0: 123.0", df.format(123.0), "1.2E2");
		compare("0.#E0: 1234.0", df.format(1234.0), "1.2E3");
		compare("0.#E0: 9999.0", df.format(9999.0), "1E4");

		df = new DecimalFormat(".0E0");
		compare(".0E0: -0.0", df.format(-0.0), "-.0E0");
		compare(".0E0: 0.0", df.format(0.0), ".0E0");
		compare(".0E0: 1.0", df.format(1.0), ".1E1");
		compare(".0E0: 12.0", df.format(12.0), ".1E2");
		compare(".0E0: 123.0", df.format(123.0), ".1E3");
		compare(".0E0: 1234.0", df.format(1234.0), ".1E4");
		compare(".0E0: 9999.0", df.format(9999.0), ".1E5");

		df = new DecimalFormat("0.E0");
		// Fails in JDK 1.2.2
		if (!compare(failCount, df.format(0.0), "0.E0"))
			failures.set(failCount);
		failCount++;
		if (!compare(failCount, df.format(1.0), "1.E0"))
			failures.set(failCount);
		failCount++;
		if (!compare(failCount, df.format(12.0), "1.E1"))
			failures.set(failCount);
		failCount++;
		if (!compare(failCount, df.format(123.0), "1.E2"))
			failures.set(failCount);
		failCount++;
		if (!compare(failCount, df.format(1234.0), "1.E3"))
			failures.set(failCount);
		failCount++;
		if (!compare(failCount, df.format(9999.0), "1.E4"))
			failures.set(failCount);
		failCount++;

		df = new DecimalFormat("##0.00#E0");
		compare("##0.00#E0: 0.1", df.format(0.1), ".100E0");
		compare("##0.00#E0: 0.1234567", df.format(0.1234567), ".123457E0");
		compare("##0.00#E0: 0.9999999", df.format(0.9999999), "1.00E0");
		compare("##0.00#E0: 0.01", df.format(0.01), "10.0E-3");
		compare("##0.00#E0: 0.01234567", df.format(0.01234567), "12.3457E-3");
		compare("##0.00#E0: 0.09999999", df.format(0.09999999), ".100E0");
		compare("##0.00#E0: 0.001", df.format(0.001), "1.00E-3");
		compare("##0.00#E0: 0.001234567", df.format(0.001234567), "1.23457E-3");
		compare("##0.00#E0: 0.009999999", df.format(0.009999999), "10.0E-3");
		compare("##0.00#E0: 0.0001", df.format(0.0001), "100E-6");
		compare("##0.00#E0: 0.0001234567", df.format(0.0001234567),
				"123.457E-6");
		compare("##0.00#E0: 0.0009999999", df.format(0.0009999999), "1.00E-3");

		df = new DecimalFormat("###0.00#E0");
		compare("###0.00#E0: 0.1", df.format(0.1), ".100E0");
		compare("###0.00#E0: 0.12345678", df.format(0.12345678), ".1234568E0");
		compare("###0.00#E0: 0.99999999", df.format(0.99999999), "1.00E0");
		compare("###0.00#E0: 0.01", df.format(0.01), "100E-4");
		compare("###0.00#E0: 0.012345678", df.format(0.012345678),
				"123.4568E-4");
		compare("###0.00#E0: 0.099999999", df.format(0.099999999), ".100E0");
		compare("###0.00#E0: 0.001", df.format(0.001), "10.0E-4");
		compare("###0.00#E0: 0.0012345678", df.format(0.0012345678),
				"12.34568E-4");
		compare("###0.00#E0: 0.0099999999", df.format(0.0099999999), "100E-4");
		compare("###0.00#E0: 0.0001", df.format(0.0001), "1.00E-4");
		compare("###0.00#E0: 0.00012345678", df.format(0.00012345678),
				"1.234568E-4");
		compare("###0.00#E0: 0.00099999999", df.format(0.00099999999),
				"10.0E-4");
		// Fails in JDK 1.2.2
		if (!compare(failCount, df.format(0.00001), "1000E-8"))
			failures.set(failCount);
		failCount++;
		compare("###0.00#E0: 0.000012345678", df.format(0.000012345678),
				"1234.568E-8");
		compare("###0.00#E0: 0.000099999999", df.format(0.000099999999),
				"1.00E-4");

		df = new DecimalFormat("###0.0#E0");
		compare("###0.0#E0: 0.1", df.format(0.1), ".10E0");
		compare("###0.0#E0: 0.1234567", df.format(0.1234567), ".123457E0");
		compare("###0.0#E0: 0.9999999", df.format(0.9999999), "1.0E0");
		// Fails in JDK 1.2.2
		if (!compare(failCount, df.format(0.01), "100E-4"))
			failures.set(failCount);
		failCount++;
		compare("###0.0#E0: 0.01234567", df.format(0.01234567), "123.457E-4");
		compare("###0.0#E0: 0.09999999", df.format(0.09999999), ".10E0");
		compare("###0.0#E0: 0.001", df.format(0.001), "10E-4");
		compare("###0.0#E0: 0.001234567", df.format(0.001234567), "12.3457E-4");
		// Fails in JDK 1.2.2
		if (!compare(failCount, df.format(0.009999999), "100E-4"))
			failures.set(failCount);
		failCount++;
		compare("###0.0#E0: 0.0001", df.format(0.0001), "1.0E-4");
		compare("###0.0#E0: 0.0001234567", df.format(0.0001234567),
				"1.23457E-4");
		compare("###0.0#E0: 0.0009999999", df.format(0.0009999999), "10E-4");
		// Fails in JDK 1.2.2
		if (!compare(failCount, df.format(0.00001), "1000E-8"))
			failures.set(failCount);
		failCount++;
		compare("###0.0#E0: 0.00001234567", df.format(0.00001234567),
				"1234.57E-8");
		compare("###0.0#E0: 0.00009999999", df.format(0.00009999999), "1.0E-4");

		assertTrue("Failed " + failures + " of " + failCount,
				failures.length() == 0);

		String formatString = "##0.#";
		df = new DecimalFormat(formatString);
		df.setMinimumFractionDigits(30);
		compare(formatString + ": 0.000000000000000000000000000000", df
				.format(0.0), "0.000000000000000000000000000000");
		compare(formatString + ": -0.000000000000000000000000000000", df
				.format(-0.0), "-0.000000000000000000000000000000");
		compare(formatString + ": 1.000000000000000000000000000000", df
				.format(1.0), "1.000000000000000000000000000000");
		compare(formatString + ": -1.000000000000000000000000000000", df
				.format(-1.0), "-1.000000000000000000000000000000");

		df = new DecimalFormat(formatString);
		df.setMaximumFractionDigits(30);
		compare(formatString + ": 0", df.format(0.0), "0");
		compare(formatString + ": -0", df.format(-0.0), "-0");
		compare(formatString + ": 1", df.format(1.0), "1");
		compare(formatString + ": -1", df.format(-1.0), "-1");
	}

	/**
	 * @tests java.text.DecimalFormat#format(long, java.lang.StringBuffer,
	 *        java.text.FieldPosition)
	 */
	public void test_formatJLjava_lang_StringBufferLjava_text_FieldPosition() {
		int failCount = 0;
		Support_BitSet failures = new Support_BitSet();

		DecimalFormat df = new DecimalFormat("00.0#E0");
		assertTrue("00.0#E0: 0", df.format(0).equals("00.0E0"));
		assertTrue("00.0#E0: 1", df.format(1).equals("10.0E-1"));
		assertTrue("00.0#E0: 12", df.format(12).equals("12.0E0"));
		assertTrue("00.0#E0: 123", df.format(123).equals("12.3E1"));
		assertTrue("00.0#E0: 1234", df.format(1234).equals("12.34E2"));
		assertTrue("00.0#E0: 12346", df.format(12346).equals("12.35E3"));
		assertTrue("00.0#E0: 99999", df.format(99999).equals("10.0E4"));
		assertTrue("00.0#E0: -1", df.format(-1).equals("-10.0E-1"));
		assertTrue("00.0#E0: -12", df.format(-12).equals("-12.0E0"));
		assertTrue("00.0#E0: -123", df.format(-123).equals("-12.3E1"));
		assertTrue("00.0#E0: -1234", df.format(-1234).equals("-12.34E2"));
		assertTrue("00.0#E0: -12346", df.format(-12346).equals("-12.35E3"));
		assertTrue("00.0#E0: -99999", df.format(-99999).equals("-10.0E4"));

		df = new DecimalFormat("##0.0E0");
		assertTrue("##0.0E0: 0", df.format(0).equals("0.0E0"));
		assertTrue("##0.0E0: 1", df.format(1).equals("1.0E0"));
		assertTrue("##0.0E0: 12", df.format(12).equals("12E0"));
		assertTrue("##0.0E0: 123", df.format(123).equals("123E0"));
		assertTrue("##0.0E0: 1234", df.format(1234).equals("1.234E3"));
		assertTrue("##0.0E0: 12346", df.format(12346).equals("12.35E3"));
		// Fails in JDK 1.2.2
		if (!df.format(99999).equals("100E3"))
			failures.set(failCount);
		failCount++;
		assertTrue("##0.0E0: 999999", df.format(999999).equals("1.0E6"));

		df = new DecimalFormat("#00.0##E0");
		// Fails in JDK 1.2.2
		if (!df.format(0).equals("0.00E0"))
			failures.set(failCount);
		failCount++;
		assertTrue("#00.0##E0: 1", df.format(1).equals("1.00E0"));
		assertTrue("#00.0##E0: 12", df.format(12).equals("12.0E0"));
		assertTrue("#00.0##E0: 123", df.format(123).equals("123E0"));
		assertTrue("#00.0##E0: 1234", df.format(1234).equals("1.234E3"));
		assertTrue("#00.0##E0: 12345", df.format(12345).equals("12.345E3"));
		assertTrue("#00.0##E0: 123456", df.format(123456).equals("123.456E3"));
		assertTrue("#00.0##E0: 1234567", df.format(1234567).equals("1.23457E6"));
		assertTrue("#00.0##E0: 12345678", df.format(12345678).equals(
				"12.3457E6"));
		assertTrue("#00.0##E0: 99999999", df.format(99999999).equals("100E6"));

		df = new DecimalFormat("#.0E0");
		assertTrue("#.0E0: 0", df.format(0).equals(".0E0"));
		assertTrue("#.0E0: 1", df.format(1).equals(".1E1"));
		assertTrue("#.0E0: 12", df.format(12).equals(".12E2"));
		assertTrue("#.0E0: 123", df.format(123).equals(".12E3"));
		assertTrue("#.0E0: 1234", df.format(1234).equals(".12E4"));
		assertTrue("#.0E0: 9999", df.format(9999).equals(".1E5"));

		df = new DecimalFormat("0.#E0");
		assertTrue("0.#E0: 0", df.format(0).equals("0E0"));
		assertTrue("0.#E0: 1", df.format(1).equals("1E0"));
		assertTrue("0.#E0: 12", df.format(12).equals("1.2E1"));
		assertTrue("0.#E0: 123", df.format(123).equals("1.2E2"));
		assertTrue("0.#E0: 1234", df.format(1234).equals("1.2E3"));
		assertTrue("0.#E0: 9999", df.format(9999).equals("1E4"));

		assertTrue("Failed " + failures + " of " + failCount,
				failures.length() == 0);
	}

	/**
	 * @tests java.text.DecimalFormat#formatToCharacterIterator(java.lang.Object)
	 */
	public void test_formatToCharacterIteratorLjava_lang_Object() {
		new Support_DecimalFormat(
				"test_formatToCharacterIteratorLjava_lang_Object")
				.t_formatToCharacterIterator();
	}

	/**
	 * @tests java.text.DecimalFormat#format(double)
	 */
	public void test_formatD() {
		DecimalFormat format = (DecimalFormat) NumberFormat
				.getInstance(Locale.ENGLISH);
		format.setGroupingUsed(false);
		format.setMaximumFractionDigits(400);
		for (int i = 0; i < 309; i++) {
			String tval = "1";
			for (int j = 0; j < i; j++)
				tval += "0";
			double d = Double.parseDouble(tval);
			String result = format.format(d);
			assertEquals(i + ") e:" + tval + " r:" + result, tval, result);
		}
		for (int i = 0; i < 322; i++) {
			String tval = "0.";
			for (int j = 0; j < i; j++)
				tval += "0";
			tval += "1";
			double d = Double.parseDouble(tval);
			String result = format.format(d);
			assertEquals(i + ") e:" + tval + " r:" + result, tval, result);
		}
		assertEquals("999999999999999", format.format(999999999999999.));
		assertEquals("1", "999999999999999.9", format.format(999999999999999.9));
		assertEquals("2", "99999999999999.98", format.format(99999999999999.99));
		assertEquals("3", "9999999999999.998", format.format(9999999999999.999));
		assertEquals("4", "999999999999.9999", format.format(999999999999.9999));
		assertEquals("5", "99999999999.99998", format.format(99999999999.99999));
		assertEquals("6", "9999999999.999998", format.format(9999999999.999999));
		assertEquals("7", "999999999.9999999", format.format(999999999.9999999));
		assertEquals("8", "99999999.99999999", format.format(99999999.99999999));
		assertEquals("9", "9999999.999999998", format.format(9999999.999999999));
		assertEquals("10", "99999.99999999999", format
				.format(99999.99999999999));
		assertEquals("11", "9999.999999999998", format
				.format(9999.999999999999));
		assertEquals("12", "999.9999999999999", format
				.format(999.9999999999999));
		assertEquals("13", "99.99999999999999", format
				.format(99.99999999999999));
		assertEquals("14", "9.999999999999998", format
				.format(9.999999999999999));
		assertEquals("15", "0.9999999999999999", format
				.format(.9999999999999999));
	}

	/**
	 * @tests java.text.DecimalFormat#getDecimalFormatSymbols()
	 */
	public void test_getDecimalFormatSymbols() {
		DecimalFormat df = (DecimalFormat) NumberFormat
				.getInstance(Locale.ENGLISH);
		DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
		assertTrue("Identical symbols", dfs != df.getDecimalFormatSymbols());
	}

	/**
	 * @tests java.text.DecimalFormat#getCurrency()
	 */
	public void test_getCurrency() {
		Currency currK = Currency.getInstance("KRW");
		Currency currX = Currency.getInstance("XXX");
		Currency currE = Currency.getInstance("EUR");
		//Currency currF = Currency.getInstance("FRF");

		DecimalFormat df = (DecimalFormat) NumberFormat
				.getCurrencyInstance(new Locale("ko", "KR"));
		assertTrue("Test1: Returned incorrect currency",
				df.getCurrency() == currK);

		df = (DecimalFormat) NumberFormat.getCurrencyInstance(new Locale("",
				"KR"));
		assertTrue("Test2: Returned incorrect currency",
				df.getCurrency() == currK);

		df = (DecimalFormat) NumberFormat.getCurrencyInstance(new Locale("ko",
				""));
		assertTrue("Test3: Returned incorrect currency",
				df.getCurrency() == currX);

		df = (DecimalFormat) NumberFormat.getCurrencyInstance(new Locale("fr",
				"FR"));
		assertTrue("Test4: Returned incorrect currency",
				df.getCurrency() == currE);

		// JDK fails these tests since it doesn't have the PREEURO variant
		// df = (DecimalFormat)NumberFormat.getCurrencyInstance(new Locale("fr",
		// "FR","PREEURO"));
		// assertTrue("Test5: Returned incorrect currency", df.getCurrency() ==
		// currF);
	}

	/**
	 * @tests java.text.DecimalFormat#getGroupingSize()
	 */
	public void test_getGroupingSize() {
		DecimalFormat df = new DecimalFormat("###0.##");
		assertTrue("Wrong unset size", df.getGroupingSize() == 0);
		df = new DecimalFormat("#,##0.##");
		assertTrue("Wrong set size", df.getGroupingSize() == 3);
		df = new DecimalFormat("#,###,###0.##");
		assertTrue("Wrong multiple set size", df.getGroupingSize() == 4);
	}

	/**
	 * @tests java.text.DecimalFormat#getMultiplier()
	 */
	public void test_getMultiplier() {
		DecimalFormat df = new DecimalFormat("###0.##");
		assertTrue("Wrong unset multiplier", df.getMultiplier() == 1);
		df = new DecimalFormat("###0.##%");
		assertTrue("Wrong percent multiplier", df.getMultiplier() == 100);
		df = new DecimalFormat("###0.##\u2030");
		assertTrue("Wrong mille multiplier", df.getMultiplier() == 1000);
	}

	/**
	 * @tests java.text.DecimalFormat#isDecimalSeparatorAlwaysShown()
	 */
	public void test_isDecimalSeparatorAlwaysShown() {
		DecimalFormat df = new DecimalFormat("###0.##");
		assertTrue("Wrong unset value", !df.isDecimalSeparatorAlwaysShown());
		df = new DecimalFormat("###0.00");
		assertTrue("Wrong unset2 value", !df.isDecimalSeparatorAlwaysShown());
		df = new DecimalFormat("###0.");
		assertTrue("Wrong set value", df.isDecimalSeparatorAlwaysShown());
	}

	/**
	 * @tests java.text.DecimalFormat#parse(java.lang.String,
	 *        java.text.ParsePosition)
	 */
	public void test_parseLjava_lang_StringLjava_text_ParsePosition() {
		DecimalFormat format = (DecimalFormat) NumberFormat
				.getNumberInstance(Locale.ENGLISH);
		ParsePosition pos = new ParsePosition(0);
		Number result = format.parse("9223372036854775807", pos);
		assertTrue("Wrong result type for Long.MAX_VALUE",
				result.getClass() == Long.class);
		assertTrue("Wrong result Long.MAX_VALUE",
				result.longValue() == Long.MAX_VALUE);
		pos = new ParsePosition(0);
		result = format.parse("-9223372036854775808", pos);
		assertTrue("Wrong result type for Long.MIN_VALUE",
				result.getClass() == Long.class);
		assertTrue("Wrong result Long.MIN_VALUE: " + result.longValue(), result
				.longValue() == Long.MIN_VALUE);
		pos = new ParsePosition(0);
		result = format.parse("9223372036854775808", pos);
		assertTrue("Wrong result type for Long.MAX_VALUE+1",
				result.getClass() == Double.class);
		assertTrue("Wrong result Long.MAX_VALUE + 1",
				result.doubleValue() == (double) Long.MAX_VALUE + 1);
		pos = new ParsePosition(0);
		result = format.parse("-9223372036854775809", pos);
		assertTrue("Wrong result type for Long.MIN_VALUE+1",
				result.getClass() == Double.class);
		assertTrue("Wrong result Long.MIN_VALUE - 1",
				result.doubleValue() == (double) Long.MIN_VALUE - 1);

		pos = new ParsePosition(0);
		result = format.parse("18446744073709551629", pos);
		assertTrue("Wrong result type for overflow",
				result.getClass() == Double.class);
		assertTrue("Wrong result for overflow",
				result.doubleValue() == 18446744073709551629d);

		pos = new ParsePosition(0);
		result = format.parse("42325917317067571199", pos);
		assertTrue("Wrong result type for overflow a: " + result, result
				.getClass() == Double.class);
		assertTrue("Wrong result for overflow a: " + result, result
				.doubleValue() == 42325917317067571199d);
		pos = new ParsePosition(0);
		result = format.parse("4232591731706757119E1", pos);
		assertTrue("Wrong result type for overflow b: " + result, result
				.getClass() == Double.class);
		assertTrue("Wrong result for overflow b: " + result, result
				.doubleValue() == 42325917317067571190d);
		pos = new ParsePosition(0);
		result = format.parse(".42325917317067571199E20", pos);
		assertTrue("Wrong result type for overflow c: " + result, result
				.getClass() == Double.class);
		assertTrue("Wrong result for overflow c: " + result, result
				.doubleValue() == 42325917317067571199d);
		pos = new ParsePosition(0);
		result = format.parse("922337203685477580.9E1", pos);
		assertTrue("Wrong result type for overflow d: " + result, result
				.getClass() == Double.class);
		assertTrue("Wrong result for overflow d: " + result, result
				.doubleValue() == 9223372036854775809d);
		pos = new ParsePosition(0);
		result = format.parse("9.223372036854775809E18", pos);
		assertTrue("Wrong result type for overflow e: " + result, result
				.getClass() == Double.class);
		assertTrue("Wrong result for overflow e: " + result, result
				.doubleValue() == 9223372036854775809d);

		// test parse with multipliers
		format.setMultiplier(100);
		result = format.parse("9223372036854775807", new ParsePosition(0));
		assertTrue("Wrong result type multiplier 100: " + result, result
				.getClass() == Long.class);
		assertTrue("Wrong result for multiplier 100: " + result, result
				.longValue() == 92233720368547758L);

		format.setMultiplier(1000);
		result = format.parse("9223372036854775807", new ParsePosition(0));
		assertTrue("Wrong result type multiplier 1000: " + result, result
				.getClass() == Long.class);
		assertTrue("Wrong result for multiplier 1000: " + result, result
				.longValue() == 9223372036854776L);

		format.setMultiplier(10000);
		result = format.parse("9223372036854775807", new ParsePosition(0));
		assertTrue("Wrong result type multiplier 10000: " + result, result
				.getClass() == Double.class);
		assertTrue("Wrong result for multiplier 10000: " + result, result
				.doubleValue() == 922337203685477.5807d);

	}

	/**
	 * @tests java.text.DecimalFormat#setDecimalFormatSymbols(java.text.DecimalFormatSymbols)
	 */
	public void test_setDecimalFormatSymbolsLjava_text_DecimalFormatSymbols() {
		DecimalFormat df = new DecimalFormat("###0.##");
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator('@');
		df.setDecimalFormatSymbols(dfs);
		assertTrue("Not set", df.getDecimalFormatSymbols().equals(dfs));
		assertTrue("Symbols not used", df.format(1.2).equals("1@2"));
	}

	/**
	 * @tests java.text.DecimalFormat#setDecimalSeparatorAlwaysShown(boolean)
	 */
	public void test_setDecimalSeparatorAlwaysShownZ() {
		DecimalFormat df = new DecimalFormat("###0.##");
		assertTrue("Wrong default result", df.format(5).equals("5"));
		df.setDecimalSeparatorAlwaysShown(true);
		assertTrue("Not set", df.isDecimalSeparatorAlwaysShown());
		assertTrue("Wrong set result", df.format(7).equals("7."));
	}

	/**
	 * @tests java.text.DecimalFormat#setCurrency(java.util.Currency)
	 */
	public void test_setCurrencyLjava_util_Currency() {
		Locale locale = Locale.CANADA;
		DecimalFormat df = ((DecimalFormat) NumberFormat
				.getCurrencyInstance(locale));

		try {
			df.setCurrency(null);
			fail("Expected NullPointerException");
		} catch (NullPointerException e) {
		}

		Currency currency = Currency.getInstance("AED");
		df.setCurrency(currency);
		assertTrue("Returned incorrect currency", currency == df.getCurrency());
		assertTrue("Returned incorrect currency symbol", currency.getSymbol(
				locale)
				.equals(df.getDecimalFormatSymbols().getCurrencySymbol()));
		assertTrue("Returned incorrect international currency symbol", currency
				.getCurrencyCode().equals(
						df.getDecimalFormatSymbols()
								.getInternationalCurrencySymbol()));
	}

	/**
	 * @tests java.text.DecimalFormat#setGroupingSize(int)
	 */
	public void test_setGroupingSizeI() {
		DecimalFormat df = new DecimalFormat("###0.##",
				new DecimalFormatSymbols(Locale.ENGLISH));
		df.setGroupingUsed(true);
		df.setGroupingSize(2);
		assertTrue("Value not set", df.getGroupingSize() == 2);
		String result = df.format(123);
		assertTrue("Invalid format:" + result, result.equals("1,23"));
	}

	/**
	 * @tests java.text.DecimalFormat#setMaximumFractionDigits(int)
	 */
	public void test_setMaximumFractionDigitsI() {
		DecimalFormat df = new DecimalFormat("###0.##");
		df.setMaximumFractionDigits(3);
		assertTrue("Not set", df.getMaximumFractionDigits() == 3);
		assertTrue("Wrong maximum", df.format(1.23456).equals("1.235"));
		df.setMinimumFractionDigits(4);
		assertTrue("Not changed", df.getMaximumFractionDigits() == 4);
		assertTrue("Incorrect fraction", df.format(456).equals("456.0000"));
	}

	/**
	 * @tests java.text.DecimalFormat#setMaximumIntegerDigits(int)
	 */
	public void test_setMaximumIntegerDigitsI() {
		DecimalFormat df = new DecimalFormat("###0.##");
		df.setMaximumIntegerDigits(2);
		assertTrue("Not set", df.getMaximumIntegerDigits() == 2);
		assertTrue("Wrong maximum", df.format(1234).equals("34"));
		df.setMinimumIntegerDigits(4);
		assertTrue("Not changed", df.getMaximumIntegerDigits() == 4);
		assertTrue("Incorrect integer", df.format(26).equals("0026"));
	}

	/**
	 * @tests java.text.DecimalFormat#setMinimumFractionDigits(int)
	 */
	public void test_setMinimumFractionDigitsI() {
		DecimalFormat df = new DecimalFormat("###0.##");
		df.setMinimumFractionDigits(4);
		assertTrue("Not set", df.getMinimumFractionDigits() == 4);
		assertTrue("Wrong minimum", df.format(1.23).equals("1.2300"));
		df.setMaximumFractionDigits(2);
		assertTrue("Not changed", df.getMinimumFractionDigits() == 2);
		assertTrue("Incorrect fraction", df.format(456).equals("456.00"));
	}

	/**
	 * @tests java.text.DecimalFormat#setMinimumIntegerDigits(int)
	 */
	public void test_setMinimumIntegerDigitsI() {
		DecimalFormat df = new DecimalFormat("###0.##");
		df.setMinimumIntegerDigits(3);
		assertTrue("Not set", df.getMinimumIntegerDigits() == 3);
		assertTrue("Wrong minimum", df.format(12).equals("012"));
		df.setMaximumIntegerDigits(2);
		assertTrue("Not changed", df.getMinimumIntegerDigits() == 2);
		assertTrue("Incorrect integer", df.format(0.7).equals("00.7"));
	}

	/**
	 * @tests java.text.DecimalFormat#setMultiplier(int)
	 */
	public void test_setMultiplierI() {
		DecimalFormat df = new DecimalFormat("###0.##");
		df.setMultiplier(10);
		assertTrue("Wrong multiplier", df.getMultiplier() == 10);
		assertTrue("Wrong format", df.format(5).equals("50"));
		assertTrue("Wrong parse", df.parse("50", new ParsePosition(0))
				.intValue() == 5);
	}
}
