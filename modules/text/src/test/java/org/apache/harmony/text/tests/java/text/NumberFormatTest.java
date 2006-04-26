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
package org.apache.harmony.text.tests.java.text;

import java.text.ChoiceFormat;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Currency;
import java.util.Locale;

public class NumberFormatTest extends junit.framework.TestCase {

	/**
	 * @tests java.text.NumberFormat#format(java.lang.Object,
	 *        java.lang.StringBuffer, java.text.FieldPosition)
	 */
	public void test_formatLjava_lang_ObjectLjava_lang_StringBufferLjava_text_FieldPosition() {
		FieldPosition pos;
		StringBuffer out;
		DecimalFormat format = (DecimalFormat) NumberFormat
				.getInstance(Locale.US);

		pos = new FieldPosition(0);
		out = format.format(new Long(Long.MAX_VALUE), new StringBuffer(), pos);
		assertTrue("Wrong result L1: " + out, out.toString().equals(
				"9,223,372,036,854,775,807"));

		pos = new FieldPosition(0);
		out = format.format(new Long(Long.MIN_VALUE), new StringBuffer(), pos);
		assertTrue("Wrong result L2: " + out, out.toString().equals(
				"-9,223,372,036,854,775,808"));

		pos = new FieldPosition(0);
		out = format.format(new java.math.BigInteger(String
				.valueOf(Long.MAX_VALUE)), new StringBuffer(), pos);
		assertTrue("Wrong result BI1: " + out, out.toString().equals(
				"9,223,372,036,854,775,807"));

		pos = new FieldPosition(0);
		out = format.format(new java.math.BigInteger(String
				.valueOf(Long.MIN_VALUE)), new StringBuffer(), pos);
		assertTrue("Wrong result BI2: " + out, out.toString().equals(
				"-9,223,372,036,854,775,808"));

		java.math.BigInteger big;
		pos = new FieldPosition(0);
		big = new java.math.BigInteger(String.valueOf(Long.MAX_VALUE))
				.add(new java.math.BigInteger("1"));
		out = format.format(big, new StringBuffer(), pos);
		assertTrue("Wrong result BI3: " + out, out.toString().equals(
				"9,223,372,036,854,776,000"));

		pos = new FieldPosition(0);
		big = new java.math.BigInteger(String.valueOf(Long.MIN_VALUE))
				.add(new java.math.BigInteger("-1"));
		out = format.format(big, new StringBuffer(), pos);
		assertTrue("Wrong result BI4: " + out, out.toString().equals(
				"-9,223,372,036,854,776,000"));

		pos = new FieldPosition(0);
		out = format.format(new java.math.BigDecimal("51.348"),
				new StringBuffer(), pos);
		assertTrue("Wrong result BD1: " + out, out.toString().equals("51.348"));

		pos = new FieldPosition(0);
		out = format.format(new java.math.BigDecimal("51"), new StringBuffer(),
				pos);
		assertTrue("Wrong result BD2: " + out, out.toString().equals("51"));

	}

	/**
	 * @tests java.text.NumberFormat#getIntegerInstance()
	 */
	public void test_getIntegerInstance() {
		// Test for method java.text.NumberFormat getIntegerInstance()
		Locale origLocale = Locale.getDefault();
		Locale.setDefault(Locale.US);

		DecimalFormat format = (DecimalFormat) NumberFormat
				.getIntegerInstance();

		assertEquals(
				"Test1: NumberFormat.getIntegerInstance().toPattern() returned wrong pattern",
				"#,##0", format.toPattern());
		assertEquals(
				"Test2: NumberFormat.getIntegerInstance().format(35.76) returned wrong value",
				"36", format.format(35.76));
		try {
			assertEquals(
					"Test3: NumberFormat.getIntegerInstance().parse(\"35.76\") returned wrong number",
					new Long(35), format.parse("35.76"));
			assertEquals(
					"Test4: NumberFormat.getIntegerInstance().parseObject(\"35.76\") returned wrong number",
					new Long(35), format.parseObject("35.76"));
		} catch (ParseException e) {
			fail("Unexpected exception" + e);
		}
		Locale.setDefault(origLocale);
	}

	/**
	 * @tests java.text.NumberFormat#getIntegerInstance(java.util.Locale)
	 */
	public void test_getIntegerInstanceLjava_util_Locale() {
		// Test for method java.text.NumberFormat
		// getIntegerInstance(java.util.Locale)
		Locale usLocale = Locale.US;
		Locale arLocale = new Locale("ar", "AE");

		DecimalFormat format = (DecimalFormat) NumberFormat
				.getIntegerInstance(usLocale);
		assertEquals(
				"Test1: NumberFormat.getIntegerInstance().toPattern() returned wrong pattern",
				"#,##0", format.toPattern());
		assertEquals(
				"Test2: NumberFormat.getIntegerInstance().format(-35.76) returned wrong value",
				"-36", format.format(-35.76));
		try {
			assertEquals(
					"Test3: NumberFormat.getIntegerInstance().parse(\"-36\") returned wrong number",
					new Long(-36), format.parse("-36"));
			assertEquals(
					"Test4: NumberFormat.getIntegerInstance().parseObject(\"-36\") returned wrong number",
					new Long(-36), format.parseObject("-36"));
		} catch (ParseException e) {
			fail("Unexpected exception" + e);
		}
		assertEquals(
				"Test5: NumberFormat.getIntegerInstance().getMaximumFractionDigits() returned wrong value",
				0, format.getMaximumFractionDigits());
		assertEquals(
				"Test6: NumberFormat.getIntegerInstance().isParseIntegerOnly() returned wrong value",
				true, format.isParseIntegerOnly());

		// try with a locale that has a different integer pattern
		format = (DecimalFormat) NumberFormat.getIntegerInstance(arLocale);
		assertEquals(
				"Test7: NumberFormat.getIntegerInstance(new Locale(\"ar\", \"AE\")).toPattern() returned wrong pattern",
				"#,##0;#,##0-", format.toPattern());
		assertEquals(
				"Test8: NumberFormat.getIntegerInstance(new Locale(\"ar\", \"AE\")).format(-35.76) returned wrong value",
				"36-", format.format(-35.76));
		try {
			assertEquals(
					"Test9: NumberFormat.getIntegerInstance(new Locale(\"ar\", \"AE\")).parse(\"-36-\") returned wrong number",
					new Long(-36), format.parse("36-"));
			assertEquals(
					"Test10: NumberFormat.getIntegerInstance(new Locale(\"ar\", \"AE\")).parseObject(\"36-\") returned wrong number",
					new Long(-36), format.parseObject("36-"));
		} catch (ParseException e) {
			fail("Unexpected exception" + e);
		}
		assertEquals(
				"Test11: NumberFormat.getIntegerInstance(new Locale(\"ar\", \"AE\")).getMaximumFractionDigits() returned wrong value",
				0, format.getMaximumFractionDigits());
		assertEquals(
				"Test12: NumberFormat.getIntegerInstance(new Locale(\"ar\", \"AE\")).isParseIntegerOnly() returned wrong value",
				true, format.isParseIntegerOnly());
	}

	/**
	 * @tests java.text.NumberFormat#getCurrency()
	 */
	public void test_getCurrency() {
		// Test for method java.util.Currency getCurrency()

		// a subclass that supports currency formatting
		Currency currH = Currency.getInstance("HUF");
		NumberFormat format = NumberFormat.getInstance(new Locale("hu", "HU"));
		assertTrue("Returned incorrect currency", format.getCurrency() == currH);

		// a subclass that doesn't support currency formatting
		ChoiceFormat cformat = new ChoiceFormat(
				"0#Less than one|1#one|1<Between one and two|2<Greater than two");
		try {
			((NumberFormat) cformat).getCurrency();
			fail("Expected UnsupportedOperationException");
		} catch (UnsupportedOperationException e) {
		}
	}

	/**
	 * @tests java.text.NumberFormat#getMaximumIntegerDigits()
	 */
	public void test_getMaximumIntegerDigits() {
		NumberFormat format = NumberFormat.getInstance();
		format.setMaximumIntegerDigits(2);
		assertEquals("Wrong result", "23", format.format(123));
	}

	/**
	 * @tests java.text.NumberFormat#setCurrency(java.util.Currency)
	 */
	public void test_setCurrencyLjava_util_Currency() {
		// Test for method void setCurrency(java.util.Currency)
		// a subclass that supports currency formatting
		Currency currA = Currency.getInstance("ARS");
		NumberFormat format = NumberFormat.getInstance(new Locale("hu", "HU"));
		format.setCurrency(currA);
		assertTrue("Returned incorrect currency", format.getCurrency() == currA);

		// a subclass that doesn't support currency formatting
		ChoiceFormat cformat = new ChoiceFormat(
				"0#Less than one|1#one|1<Between one and two|2<Greater than two");
		try {
			((NumberFormat) cformat).setCurrency(currA);
			fail("Expected UnsupportedOperationException");
		} catch (UnsupportedOperationException e) {
		}
	}

	protected void setUp() {
	}

	protected void tearDown() {
	}
}
