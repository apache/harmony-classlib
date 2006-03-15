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

import java.text.ChoiceFormat;
import java.text.FieldPosition;
import java.text.MessageFormat;
import java.text.ParsePosition;

public class ChoiceFormatTest extends junit.framework.TestCase {

	double[] limits = new double[] { 0, 1, ChoiceFormat.nextDouble(1),
			ChoiceFormat.nextDouble(2) };

	String[] formats = new String[] { "Less than one", "one",
			"Between one and two", "Greater than two" };

	ChoiceFormat f1 = new ChoiceFormat(limits, formats);

	/**
	 * @tests java.text.ChoiceFormat#ChoiceFormat(double[], java.lang.String[])
	 */
	public void test_Constructor$D$Ljava_lang_String() {
		// Test for method java.text.ChoiceFormat(double [], java.lang.String
		// [])
		String formattedString;
		double[] appleLimits = { 1, 2, 3, 4, 5 };
		String[] appleFormats = { "Tiny Apple", "Small Apple", "Medium Apple",
				"Large Apple", "Huge Apple" };
		ChoiceFormat cf = new ChoiceFormat(appleLimits, appleFormats);

		formattedString = cf.format(Double.NEGATIVE_INFINITY);
		assertTrue("a) Incorrect format returned: " + formattedString,
				formattedString.equals("Tiny Apple"));
		formattedString = cf.format(0.5d);
		assertTrue("b) Incorrect format returned: " + formattedString,
				formattedString.equals("Tiny Apple"));
		formattedString = cf.format(1d);
		assertTrue("c) Incorrect format returned: " + formattedString,
				formattedString.equals("Tiny Apple"));
		formattedString = cf.format(1.5d);
		assertTrue("d) Incorrect format returned: " + formattedString,
				formattedString.equals("Tiny Apple"));
		formattedString = cf.format(2d);
		assertTrue("e) Incorrect format returned: " + formattedString,
				formattedString.equals("Small Apple"));
		formattedString = cf.format(2.5d);
		assertTrue("f) Incorrect format returned: " + formattedString,
				formattedString.equals("Small Apple"));
		formattedString = cf.format(3d);
		assertTrue("g) Incorrect format returned: " + formattedString,
				formattedString.equals("Medium Apple"));
		formattedString = cf.format(4d);
		assertTrue("h) Incorrect format returned: " + formattedString,
				formattedString.equals("Large Apple"));
		formattedString = cf.format(5d);
		assertTrue("i) Incorrect format returned: " + formattedString,
				formattedString.equals("Huge Apple"));
		formattedString = cf.format(5.5d);
		assertTrue("j) Incorrect format returned: " + formattedString,
				formattedString.equals("Huge Apple"));
		formattedString = cf.format(6.0d);
		assertTrue("k) Incorrect format returned: " + formattedString,
				formattedString.equals("Huge Apple"));
		formattedString = cf.format(Double.POSITIVE_INFINITY);
		assertTrue("l) Incorrect format returned: " + formattedString,
				formattedString.equals("Huge Apple"));
	}

	/**
	 * @tests java.text.ChoiceFormat#ChoiceFormat(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		// Test for method java.text.ChoiceFormat(java.lang.String)
		String formattedString;
		String patternString = "-2#Inverted Orange| 0#No Orange| 0<Almost No Orange| 1#Normal Orange| 2#Expensive Orange";
		ChoiceFormat cf = new ChoiceFormat(patternString);

		formattedString = cf.format(Double.NEGATIVE_INFINITY);
		assertTrue("a) Incorrect format returned: " + formattedString,
				formattedString.equals("Inverted Orange"));
		formattedString = cf.format(-3);
		assertTrue("b) Incorrect format returned: " + formattedString,
				formattedString.equals("Inverted Orange"));
		formattedString = cf.format(-2);
		assertTrue("c) Incorrect format returned: " + formattedString,
				formattedString.equals("Inverted Orange"));
		formattedString = cf.format(-1);
		assertTrue("d) Incorrect format returned: " + formattedString,
				formattedString.equals("Inverted Orange"));
		formattedString = cf.format(-0);
		assertTrue("e) Incorrect format returned: " + formattedString,
				formattedString.equals("No Orange"));
		formattedString = cf.format(0);
		assertTrue("f) Incorrect format returned: " + formattedString,
				formattedString.equals("No Orange"));
		formattedString = cf.format(0.1);
		assertTrue("g) Incorrect format returned: " + formattedString,
				formattedString.equals("Almost No Orange"));
		formattedString = cf.format(1);
		assertTrue("h) Incorrect format returned: " + formattedString,
				formattedString.equals("Normal Orange"));
		formattedString = cf.format(1.5);
		assertTrue("i) Incorrect format returned: " + formattedString,
				formattedString.equals("Normal Orange"));
		formattedString = cf.format(2);
		assertTrue("j) Incorrect format returned: " + formattedString,
				formattedString.equals("Expensive Orange"));
		formattedString = cf.format(3);
		assertTrue("k) Incorrect format returned: " + formattedString,
				formattedString.equals("Expensive Orange"));
		formattedString = cf.format(Double.POSITIVE_INFINITY);
		assertTrue("l) Incorrect format returned: " + formattedString,
				formattedString.equals("Expensive Orange"));

	}

	/**
	 * @tests java.text.ChoiceFormat#applyPattern(java.lang.String)
	 */
	public void test_applyPatternLjava_lang_String() {
		// Test for method void
		// java.text.ChoiceFormat.applyPattern(java.lang.String)
		ChoiceFormat f = (ChoiceFormat) f1.clone();
		f.applyPattern("0#0|1#1");
		assertTrue("Incorrect limits", java.util.Arrays.equals(f.getLimits(),
				new double[] { 0, 1 }));
		assertTrue("Incorrect formats", java.util.Arrays.equals(f.getFormats(),
				new String[] { "0", "1" }));
	}

	/**
	 * @tests java.text.ChoiceFormat#clone()
	 */
	public void test_clone() {
		// Test for method java.lang.Object java.text.ChoiceFormat.clone()
		ChoiceFormat f = (ChoiceFormat) f1.clone();
		assertTrue("Not equal", f.equals(f1));
		f.setChoices(new double[] { 0, 1, 2 }, new String[] { "0", "1", "2" });
		assertTrue("Equal", !f.equals(f1));
	}

	/**
	 * @tests java.text.ChoiceFormat#equals(java.lang.Object)
	 */
	public void test_equalsLjava_lang_Object() {
		// Test for method boolean
		// java.text.ChoiceFormat.equals(java.lang.Object)

		String patternString = "-2#Inverted Orange| 0#No Orange| 0<Almost No Orange| 1#Normal Orange| 2#Expensive Orange";
		double[] appleLimits = { 1, 2, 3, 4, 5 };
		String[] appleFormats = { "Tiny Apple", "Small Apple", "Medium Apple",
				"Large Apple", "Huge Apple" };
		double[] orangeLimits = { -2, 0, ChoiceFormat.nextDouble(0), 1, 2 };
		String[] orangeFormats = { "Inverted Orange", "No Orange",
				"Almost No Orange", "Normal Orange", "Expensive Orange" };

		ChoiceFormat appleChoiceFormat = new ChoiceFormat(appleLimits,
				appleFormats);
		ChoiceFormat orangeChoiceFormat = new ChoiceFormat(orangeLimits,
				orangeFormats);
		ChoiceFormat orangeChoiceFormat2 = new ChoiceFormat(patternString);
		ChoiceFormat hybridChoiceFormat = new ChoiceFormat(appleLimits,
				orangeFormats);

		assertTrue("Apples should not equal oranges", !appleChoiceFormat
				.equals(orangeChoiceFormat));
		assertTrue("Different limit list--should not appear as equal",
				!orangeChoiceFormat.equals(hybridChoiceFormat));
		assertTrue("Different format list--should not appear as equal",
				!appleChoiceFormat.equals(hybridChoiceFormat));
		assertTrue("Should be equal--identical format", appleChoiceFormat
				.equals(appleChoiceFormat));
		assertTrue("Should be equals--same limits, same formats",
				orangeChoiceFormat.equals(orangeChoiceFormat2));

		ChoiceFormat f2 = new ChoiceFormat(
				"0#Less than one|1#one|1<Between one and two|2<Greater than two");
		assertTrue("Not equal", f1.equals(f2));
	}

	/**
	 * @tests java.text.ChoiceFormat#format(double, java.lang.StringBuffer,
	 *        java.text.FieldPosition)
	 */
	public void test_formatDLjava_lang_StringBufferLjava_text_FieldPosition() {
		// Test for method java.lang.StringBuffer
		// java.text.ChoiceFormat.format(double, java.lang.StringBuffer,
		// java.text.FieldPosition)
		FieldPosition field = new FieldPosition(0);
		StringBuffer buf = new StringBuffer();
		String r = f1.format(-1, buf, field).toString();
		assertTrue("Wrong choice for -1", r.equals("Less than one"));
		buf.setLength(0);
		r = f1.format(0, buf, field).toString();
		assertTrue("Wrong choice for 0", r.equals("Less than one"));
		buf.setLength(0);
		r = f1.format(1, buf, field).toString();
		assertTrue("Wrong choice for 1", r.equals("one"));
		buf.setLength(0);
		r = f1.format(2, buf, field).toString();
		assertTrue("Wrong choice for 2", r.equals("Between one and two"));
		buf.setLength(0);
		r = f1.format(3, buf, field).toString();
		assertTrue("Wrong choice for 3", r.equals("Greater than two"));
	}

	/**
	 * @tests java.text.ChoiceFormat#format(long, java.lang.StringBuffer,
	 *        java.text.FieldPosition)
	 */
	public void test_formatJLjava_lang_StringBufferLjava_text_FieldPosition() {
		// Test for method java.lang.StringBuffer
		// java.text.ChoiceFormat.format(long, java.lang.StringBuffer,
		// java.text.FieldPosition)
		FieldPosition field = new FieldPosition(0);
		StringBuffer buf = new StringBuffer();
		String r = f1.format(0.5, buf, field).toString();
		assertTrue("Wrong choice for 0.5", r.equals("Less than one"));
		buf.setLength(0);
		r = f1.format(1.5, buf, field).toString();
		assertTrue("Wrong choice for 1.5", r.equals("Between one and two"));
		buf.setLength(0);
		r = f1.format(2.5, buf, field).toString();
		assertTrue("Wrong choice for 2.5", r.equals("Greater than two"));
	}

	/**
	 * @tests java.text.ChoiceFormat#getFormats()
	 */
	public void test_getFormats() {
		// Test for method java.lang.Object []
		// java.text.ChoiceFormat.getFormats()
		String[] orgFormats = (String[]) formats.clone();
		String[] f = (String[]) f1.getFormats();
		assertTrue("Wrong formats", f.equals(formats));
		f[0] = "Modified";
		assertTrue("Formats copied", !f.equals(orgFormats));
	}

	/**
	 * @tests java.text.ChoiceFormat#getLimits()
	 */
	public void test_getLimits() {
		// Test for method double [] java.text.ChoiceFormat.getLimits()
		double[] orgLimits = (double[]) limits.clone();
		double[] l = f1.getLimits();
		assertTrue("Wrong limits", l.equals(limits));
		l[0] = 3.14527;
		assertTrue("Limits copied", !l.equals(orgLimits));
	}

	/**
	 * @tests java.text.ChoiceFormat#hashCode()
	 */
	public void test_hashCode() {
		// Test for method int java.text.ChoiceFormat.hashCode()
		ChoiceFormat f2 = new ChoiceFormat(
				"0#Less than one|1#one|1<Between one and two|2<Greater than two");
		assertTrue("Different hash", f1.hashCode() == f2.hashCode());
	}

	/**
	 * @tests java.text.ChoiceFormat#nextDouble(double)
	 */
	public void test_nextDoubleD() {
		// Test for method double java.text.ChoiceFormat.nextDouble(double)
		assertTrue("Not greater 5", ChoiceFormat.nextDouble(5) > 5);
		assertTrue("Not greater 0", ChoiceFormat.nextDouble(0) > 0);
		assertTrue("Not greater -5", ChoiceFormat.nextDouble(-5) > -5);
		assertTrue("Not NaN", Double.isNaN(ChoiceFormat.nextDouble(Double.NaN)));
	}

	/**
	 * @tests java.text.ChoiceFormat#nextDouble(double, boolean)
	 */
	public void test_nextDoubleDZ() {
		// Test for method double java.text.ChoiceFormat.nextDouble(double,
		// boolean)
		assertTrue("Not greater 0", ChoiceFormat.nextDouble(0, true) > 0);
		assertTrue("Not less 0", ChoiceFormat.nextDouble(0, false) < 0);
	}

	/**
	 * @tests java.text.ChoiceFormat#parse(java.lang.String,
	 *        java.text.ParsePosition)
	 */
	public void test_parseLjava_lang_StringLjava_text_ParsePosition() {
		// Test for method java.lang.Number
		// java.text.ChoiceFormat.parse(java.lang.String,
		// java.text.ParsePosition)
		ChoiceFormat format = new ChoiceFormat("1#one|2#two|3#three");
		assertTrue("Case insensitive", format
				.parse("One", new ParsePosition(0)).intValue() == 0);

		ParsePosition pos = new ParsePosition(0);
		Number result = f1.parse("Greater than two", pos);
		assertTrue("Not a Double1", result instanceof Double);
		assertTrue("Wrong value ~>2", result.doubleValue() == ChoiceFormat
				.nextDouble(2));
		assertTrue("Wrong position ~16", pos.getIndex() == 16);
		pos = new ParsePosition(0);
		assertTrue("Incorrect result", Double.isNaN(f1.parse("12one", pos)
				.doubleValue()));
		assertTrue("Wrong position ~0", pos.getIndex() == 0);
		pos = new ParsePosition(2);
		result = f1.parse("12one and two", pos);
		assertTrue("Not a Double2", result instanceof Double);
		assertTrue("Ignored parse position", result.doubleValue() == 1.0);
		assertTrue("Wrong position ~5", pos.getIndex() == 5);
	}

	/**
	 * @tests java.text.ChoiceFormat#previousDouble(double)
	 */
	public void test_previousDoubleD() {
		// Test for method double java.text.ChoiceFormat.previousDouble(double)
		assertTrue("Not less 5", ChoiceFormat.previousDouble(5) < 5);
		assertTrue("Not less 0", ChoiceFormat.previousDouble(0) < 0);
		assertTrue("Not less -5", ChoiceFormat.previousDouble(-5) < -5);
		assertTrue("Not NaN", Double.isNaN(ChoiceFormat
				.previousDouble(Double.NaN)));
	}

	/**
	 * @tests java.text.ChoiceFormat#setChoices(double[], java.lang.String[])
	 */
	public void test_setChoices$D$Ljava_lang_String() {
		// Test for method void java.text.ChoiceFormat.setChoices(double [],
		// java.lang.String [])
		ChoiceFormat f = (ChoiceFormat) f1.clone();
		double[] l = new double[] { 0, 1 };
		String[] fs = new String[] { "0", "1" };
		f.setChoices(l, fs);
		assertTrue("Limits copied", f.getLimits() == l);
		assertTrue("Formats copied", f.getFormats() == fs);
	}

	/**
	 * @tests java.text.ChoiceFormat#toPattern()
	 */
	public void test_toPattern() {
		// Test for method java.lang.String java.text.ChoiceFormat.toPattern()

		MessageFormat mf = new MessageFormat("CHOICE {1,choice}");
		String ptrn = mf.toPattern();
		assertTrue("Unused message format returning incorrect pattern", ptrn
				.equals("CHOICE {1,choice,}"));

		String pattern = f1.toPattern();
		assertTrue(
				"Wrong pattern: " + pattern,
				pattern
						.equals("0.0#Less than one|1.0#one|1.0<Between one and two|2.0<Greater than two"));

		ChoiceFormat cf = new ChoiceFormat(
				"-1#is negative| 0#is zero or fraction | 1#is one |1.0<is 1+|2#is two |2<is more than 2.");
		String str = "org.apache.harmony.tests.java.lang.share.MyResources2";
		cf.applyPattern(str);
		ptrn = cf.toPattern();
		assertTrue("Return value should be empty string for invalid pattern",
				ptrn.length() == 0);
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
