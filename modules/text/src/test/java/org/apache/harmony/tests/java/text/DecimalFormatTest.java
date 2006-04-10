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

package org.apache.harmony.tests.java.text;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Currency;
import java.util.Locale;

import junit.framework.TestCase;

public class DecimalFormatTest extends TestCase {

    /*
     * Test the getter and setter of parseBigDecimal and parseIntegerOnly and
     * test the default value of them.
     */
    public void test_isParseBigDecimalLjava_lang_Boolean_isParseIntegerOnlyLjava_lang_Boolean() {

        // parseBigDecimal default to false
        DecimalFormat form = (DecimalFormat) DecimalFormat.getInstance();
        assertFalse(form.isParseBigDecimal());
        form.setParseBigDecimal(true);
        assertTrue(form.isParseBigDecimal());
        form.setParseBigDecimal(false);
        assertFalse(form.isParseBigDecimal());

        // parseIntegerOnly default to false
        assertFalse(form.isParseIntegerOnly());
    }

    // Test the type of the returned object

    public void test_parseLjava_lang_String_Ljava_text_ParsePosition() {
        DecimalFormat form = (DecimalFormat) DecimalFormat.getInstance();
        Number number = form.parse("23.1", new ParsePosition(0));
        assertTrue(number instanceof Double);

        // Test parsed object of type double when
        // parseBigDecimal is set to true

        form = (DecimalFormat) DecimalFormat.getInstance();
        number = form.parse("23.1", new ParsePosition(0));
        assertTrue(number instanceof Double);

        form.setParseBigDecimal(true);
        number = form.parse("23.1", new ParsePosition(0));

        assertTrue(number instanceof BigDecimal);
        assertEquals(new BigDecimal("23.1"), number);

        // When parseIntegerOnly set to true, all float numbers will be parsed
        // into Long.
        // With the exception that, the value is out of the bound of Long or
        // some special values such as NaN or Infinity.

        form = (DecimalFormat) DecimalFormat.getInstance();
        form.setParseIntegerOnly(true);
        number = form.parse("23.1f", new ParsePosition(0));

        assertTrue(number instanceof Long);

        number = form.parse("23.0", new ParsePosition(0));
        assertTrue(number instanceof Long);

        number = form.parse("-0.0", new ParsePosition(0));
        assertTrue(number instanceof Long);
        assertTrue(new Long(0).equals(number));

        number = form.parse("-9,223,372,036,854,775,8080.00",
                new ParsePosition(0));
        assertTrue(number instanceof Double);

        // Even if parseIntegerOnly is set to true, NaN will be parsed to Double

        form = (DecimalFormat) DecimalFormat.getInstance();
        form.setParseIntegerOnly(true);
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        number = form.parse(symbols.getNaN(), new ParsePosition(0));
        assertTrue(number instanceof Double);

        // Even if parseIntegerOnly is set to true, Infinity will still be
        // parsed to Double

        form = (DecimalFormat) DecimalFormat.getInstance();
        form.setParseIntegerOnly(true);
        symbols = new DecimalFormatSymbols();
        number = form.parse(symbols.getInfinity(), new ParsePosition(0));
        assertTrue(number instanceof Double);

        // ParseBigDecimal take precedence of parseBigInteger

        form = (DecimalFormat) DecimalFormat.getInstance();
        form.setParseIntegerOnly(true);
        form.setParseBigDecimal(true);

        number = form.parse("23.1f", new ParsePosition(0));

        assertTrue(number instanceof BigDecimal);

        number = form.parse("23.0", new ParsePosition(0));
        assertTrue(number instanceof BigDecimal);

        number = form.parse("-9,223,372,036,854,775,8080.00",
                new ParsePosition(0));
        assertFalse(number instanceof BigInteger);
        assertTrue(number instanceof BigDecimal);

        // Test whether the parsed object is of type float. (To be specific,
        // they are of type Double)

        form = (DecimalFormat) DecimalFormat.getInstance();

        number = form.parse("23.1f", new ParsePosition(0));
        assertTrue(number instanceof Double);

        form.setParseBigDecimal(true);
        number = form.parse("23.1f", new ParsePosition(0));
        assertTrue(number instanceof BigDecimal);
        assertEquals(new BigDecimal("23.1"), number);

        // Integer will be parsed to Long, unless parseBigDecimal is set to true

        form = (DecimalFormat) DecimalFormat.getInstance();

        number = form.parse("123", new ParsePosition(0));
        assertTrue(number instanceof Long);

        form.setParseBigDecimal(true);
        number = form.parse("123", new ParsePosition(0));
        assertTrue(number instanceof BigDecimal);
        assertEquals(new BigDecimal("123"), number);

        // NaN will be parsed to Double, no matter parseBigDecimal set or not.

        form = (DecimalFormat) DecimalFormat.getInstance();
        symbols = new DecimalFormatSymbols();
        number = form.parse(symbols.getNaN() + "", new ParsePosition(0));
        assertTrue(number instanceof Double);

        form.setParseBigDecimal(true);
        number = form.parse(symbols.getNaN() + "", new ParsePosition(0));
        assertTrue(number instanceof Double);

        // Infinity will be parsed to Double, no matter parseBigDecimal set or
        // not.

        form = (DecimalFormat) DecimalFormat.getInstance();
        symbols = new DecimalFormatSymbols();

        number = form.parse(symbols.getInfinity(), new ParsePosition(0));

        assertTrue(number instanceof Double);
        assertEquals("Infinity", number.toString());
        // When set bigDecimal to true, the result of parsing infinity

        form = (DecimalFormat) DecimalFormat.getInstance();
        symbols = new DecimalFormatSymbols();
        form.setParseBigDecimal(true);

        number = form.parse(symbols.getInfinity(), new ParsePosition(0));
        assertTrue(number instanceof Double);
        assertEquals("Infinity", number.toString());

        // Negative infinity will be parsed to double no matter parseBigDecimal
        // set or not

        form = (DecimalFormat) DecimalFormat.getInstance();
        symbols = new DecimalFormatSymbols();

        number = form.parse("-" + symbols.getInfinity(), new ParsePosition(0));

        assertTrue(number instanceof Double);
        assertEquals("-Infinity", number.toString());

        // When set bigDecimal to true, the result of parsing minus infinity

        form = (DecimalFormat) DecimalFormat.getInstance();
        symbols = new DecimalFormatSymbols();
        form.setParseBigDecimal(true);

        number = form.parse("-" + symbols.getInfinity(), new ParsePosition(0));

        assertTrue(number instanceof Double);
        assertEquals("-Infinity", number.toString());

        // -0.0 will be parsed to different type according to the combination of
        // parseBigDecimal and parseIntegerOnly

        form = (DecimalFormat) DecimalFormat.getInstance();

        // parseBigDecimal == true;
        // parseIntegerOnly == false;
        form.setParseBigDecimal(true);
        number = form.parse("-0", new ParsePosition(0));
        assertTrue(number instanceof BigDecimal);

        number = form.parse("-0.0", new ParsePosition(0));
        assertTrue(number instanceof BigDecimal);

        // parseBigDecimal == false;
        // parseIntegerOnly == true;
        form.setParseBigDecimal(false);
        form.setParseIntegerOnly(true);
        number = form.parse("-0", new ParsePosition(0));

        assertTrue(number instanceof Long);

        number = form.parse("-0.0", new ParsePosition(0));
        assertTrue(number instanceof Long);

        // parseBigDecimal == false;
        // parseIntegerOnly == false;
        form.setParseBigDecimal(false);
        form.setParseIntegerOnly(false);
        number = form.parse("-0", new ParsePosition(0));
        assertTrue(number instanceof Double);

        number = form.parse("-0.0", new ParsePosition(0));
        assertTrue(number instanceof Double);

        // parseBigDecimal == true;
        // parseIntegerOnly == true;
        // parseBigDecimal take precedence of parseBigInteger
        form.setParseBigDecimal(true);
        form.setParseIntegerOnly(true);
        number = form.parse("-0", new ParsePosition(0));
        assertTrue(number instanceof BigDecimal);

        number = form.parse("-0.0", new ParsePosition(0));
        assertTrue(number instanceof BigDecimal);

        number = form.parse("12.4", new ParsePosition(0));
        assertTrue(number instanceof BigDecimal);

        // When parseBigDecimal is set to false, no matter how massive the
        // mantissa part of a number is, the number will be parsed into Double

        form = (DecimalFormat) DecimalFormat.getInstance();

        number = form.parse("9,223,372,036,854,775,808.00",
                new ParsePosition(0));

        assertTrue(number instanceof Double);
        assertEquals("9.223372036854776E18", number.toString());

        number = form.parse("-9,223,372,036,854,775,8080.00",
                new ParsePosition(0));
        assertTrue(number instanceof Double);
        assertEquals("-9.223372036854776E19", number.toString());

        // When parseBigDecimal is set to true, if mantissa part of number
        // exceeds Long.MAX_VALUE, the number will be parsed into BigDecimal

        form = (DecimalFormat) DecimalFormat.getInstance();

        form.setParseBigDecimal(true);
        number = form.parse("9,223,372,036,854,775,808.00",
                new ParsePosition(0));

        assertTrue(number instanceof BigDecimal);

        assertEquals(9.223372036854776E18, number.doubleValue(), 0);

        number = form.parse("-9,223,372,036,854,775,8080.00",
                new ParsePosition(0));

        assertTrue(number instanceof BigDecimal);
        assertEquals(-9.223372036854776E19, number.doubleValue(), 0);

        // The minimum value of Long will be parsed to Long when parseBigDecimal
        // is not set

        ParsePosition pos = new ParsePosition(0);
        DecimalFormat df = new DecimalFormat();
        pos = new ParsePosition(0);
        Number nb = df.parse("" + Long.MIN_VALUE, pos);
        assertTrue(nb instanceof Long);

        // The maximum value of Long will be parsed to Long when parseBigDecimal
        // is set
        pos = new ParsePosition(0);
        df = new DecimalFormat();
        pos = new ParsePosition(0);
        nb = df.parse("" + Long.MAX_VALUE, pos);
        assertTrue(nb instanceof Long);

        // When parsing invalid string( which is neither consist of digits nor
        // NaN/Infinity), a null will be returned.

        pos = new ParsePosition(0);
        df = new DecimalFormat();
        try {
            nb = df.parse("invalid", pos);
            assertNull(nb);
        } catch (NullPointerException e) {
            fail("Should not throw NPE");
        }
    }

    public void test_getMaximumFractionDigits() {
        NumberFormat nform = DecimalFormat.getInstance();
        DecimalFormat form = (DecimalFormat) nform;

        // getMaximumFractionDigits of NumberFormat default to 3
        // getMaximumFractionDigits of DecimalFormat default to 3
        assertEquals(3, nform.getMaximumFractionDigits());
        assertEquals(3, form.getMaximumFractionDigits());

        // Greater than 340 (critical number used to distinguish
        // BigInteger and BigDecimal)
        nform.setMaximumFractionDigits(500);
        assertEquals(500, nform.getMaximumFractionDigits());
        assertEquals(500, form.getMaximumFractionDigits());

        form.setMaximumFractionDigits(500);
        assertEquals(500, nform.getMaximumFractionDigits());
        assertEquals(500, form.getMaximumFractionDigits());

        form.format(12.3);
        assertEquals(500, nform.getMaximumFractionDigits());
        assertEquals(500, form.getMaximumFractionDigits());
    }

    public void test_getMinimumFractionDigits() {
        NumberFormat nform = DecimalFormat.getInstance();
        DecimalFormat form = (DecimalFormat) nform;

        // getMinimumFractionDigits from NumberFormat (default to 0)
        // getMinimumFractionDigits from DecimalFormat (default to 0)
        assertEquals(0, nform.getMinimumFractionDigits());
        assertEquals(0, form.getMinimumFractionDigits());

        // Greater than 340 (critical number used to distinguish
        // BigInteger and BigDecimal)
        nform.setMinimumFractionDigits(500);
        assertEquals(500, nform.getMinimumFractionDigits());
        assertEquals(500, form.getMinimumFractionDigits());

        form.setMaximumFractionDigits(400);
        assertEquals(400, nform.getMinimumFractionDigits());
        assertEquals(400, form.getMinimumFractionDigits());
    }

    public void test_getMaximumIntegerDigits() {
        final int maxIntDigit = 309;

        // When use default locale, in this case zh_CN
        // the returned instance of NumberFormat is a DecimalFormat
        DecimalFormat form = new DecimalFormat("00.###E0");
        assertEquals(2, form.getMaximumIntegerDigits());

        NumberFormat nform = DecimalFormat.getInstance();
        form = null;
        if (nform instanceof DecimalFormat) {
            form = (DecimalFormat) nform;
        }

        // Greater than 309 (critical number used to distinguish
        // BigInteger and BigDecimal)
        nform.setMaximumIntegerDigits(500);
        assertEquals(500, nform.getMaximumIntegerDigits());
        assertEquals(500, form.getMaximumIntegerDigits());

        form = new DecimalFormat("00.###E0");
        assertEquals(2, form.getMaximumIntegerDigits());

        form.setMaximumIntegerDigits(500);
        assertEquals(500, nform.getMaximumIntegerDigits());
        assertEquals(500, form.getMaximumIntegerDigits());
        form.format(12.3);
        assertEquals(500, nform.getMaximumIntegerDigits());
        assertEquals(500, form.getMaximumIntegerDigits());

        nform = DecimalFormat.getInstance();
        form = null;
        if (nform instanceof DecimalFormat) {
            form = (DecimalFormat) nform;
        }
        // getMaximumIntegerDigits from NumberFormat default to 309
        // getMaximumIntegerDigits from DecimalFormat default to 309
        // the following 2 assertions will fail on RI implementation, since the
        // implementation of ICU and RI are not identical. RI does not give
        // DecimalFormat an initial bound about its maximumIntegerDigits
        // (default to Integer.MAX_VALUE: 2147483647 )
        assertEquals(maxIntDigit, nform.getMaximumIntegerDigits());
        assertEquals(maxIntDigit, form.getMaximumIntegerDigits());

    }

    public void test_getMinimumIntegerDigits() {
        final int minIntDigit = 1;
        NumberFormat nform = DecimalFormat.getInstance();
        DecimalFormat form = (DecimalFormat) nform;

        // getMaximumIntegerDigits from NumberFormat (default to 1)
        // getMaximumIntegerDigits from DecimalFormat (default to 1)
        assertEquals(minIntDigit, nform.getMinimumIntegerDigits());
        assertEquals(minIntDigit, form.getMinimumIntegerDigits());

        // Greater than 309 (critical number used to distinguish
        // BigInteger and BigDecimal)
        nform.setMinimumIntegerDigits(500);
        assertEquals(500, nform.getMinimumIntegerDigits());
        assertEquals(500, form.getMinimumIntegerDigits());

        form.setMaximumIntegerDigits(400);
        assertEquals(400, nform.getMinimumIntegerDigits());
        assertEquals(400, form.getMinimumIntegerDigits());

    }

    // Default multiplier value of DecimalFormat is 1.
    public void test_getMultiplier() {
        final int defaultMultiplier = 1;
        NumberFormat nform = DecimalFormat.getInstance();
        DecimalFormat form = (DecimalFormat) nform;
        assertEquals(defaultMultiplier, form.getMultiplier());
    }

    public void test_formatLjava_lang_Obj_Ljava_StringBuffer_Ljava_text_FieldPosition() {
        NumberFormat nform = DecimalFormat.getInstance();
        DecimalFormat form = (DecimalFormat) nform;

        // If Object(including null) is not of type Nubmer,
        // IllegalArgumentException will be thrown out
        try {
            form.format(new Object(), new StringBuffer(), new FieldPosition(0));
            fail("Should throw IAE");
        } catch (IllegalArgumentException e) {
            // expected
        }
        try {
            form.format(null, new StringBuffer(), new FieldPosition(0));
            fail("Should throw IAE");
        } catch (IllegalArgumentException e) {
            // expected
        }

        // When StringBuffer == null || FieldPosition == null
        // NullPointerException will be thrown out.
        try {
            form.format(new Double(1.9), null, new FieldPosition(0));
            fail("Should throw NPE");
        } catch (NullPointerException e) {
            // expected
        }

        try {
            form.format(new Double(1.3), new StringBuffer(), null);
            fail("Should throw NPE");
        } catch (NullPointerException e) {
            // expected
        }

        try {
            form.format(new Double(1.4), null, null);
            fail("Should throw NPE");
        } catch (NullPointerException e) {
            // expected
        }

        try {
            form.format(new Object(), null, null);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }

        FieldPosition pos;
        StringBuffer out;
        DecimalFormat format = (DecimalFormat) NumberFormat
                .getInstance(Locale.US);

        // format maxLong
        pos = new FieldPosition(0);
        out = format.format(new Long(Long.MAX_VALUE), new StringBuffer(), pos);
        assertTrue("Wrong result L1: " + out, out.toString().equals(
                "9,223,372,036,854,775,807"));

        // format minLong
        pos = new FieldPosition(0);
        out = format.format(new Long(Long.MIN_VALUE), new StringBuffer(), pos);
        assertTrue("Wrong result L2: " + out, out.toString().equals(
                "-9,223,372,036,854,775,808"));

        // format maxLong of type BigInteger
        pos = new FieldPosition(0);
        out = format.format(new java.math.BigInteger(String
                .valueOf(Long.MAX_VALUE)), new StringBuffer(), pos);
        assertTrue("Wrong result BI1: " + out, out.toString().equals(
                "9,223,372,036,854,775,807"));

        // format minLong of type BigInteger
        pos = new FieldPosition(0);
        out = format.format(new java.math.BigInteger(String
                .valueOf(Long.MIN_VALUE)), new StringBuffer(), pos);
        assertTrue("Wrong result BI2: " + out, out.toString().equals(
                "-9,223,372,036,854,775,808"));

        // format maxLong + 1
        java.math.BigInteger big;
        pos = new FieldPosition(0);
        big = new java.math.BigInteger(String.valueOf(Long.MAX_VALUE))
                .add(new java.math.BigInteger("1"));
        out = format.format(big, new StringBuffer(), pos);
        assertTrue("Wrong result BI3: " + out, out.toString().equals(
                "9,223,372,036,854,775,808"));

        // format minLong - 1
        pos = new FieldPosition(0);
        big = new java.math.BigInteger(String.valueOf(Long.MIN_VALUE))
                .add(new java.math.BigInteger("-1"));
        out = format.format(big, new StringBuffer(), pos);
        assertTrue("Wrong result BI4: " + out, out.toString().equals(
                "-9,223,372,036,854,775,809"));

        // format big decimal
        pos = new FieldPosition(0);
        out = format.format(new java.math.BigDecimal("51.348"),
                new StringBuffer(), pos);
        assertTrue("Wrong result BD1: " + out, out.toString().equals("51.348"));

        // format big decimal
        pos = new FieldPosition(0);
        out = format.format(new java.math.BigDecimal("51"), new StringBuffer(),
                pos);
        assertTrue("Wrong result BD2: " + out, out.toString().equals("51"));

        // format big decimal Double.MAX_VALUE * 2
        java.math.BigDecimal bigDecimal;
        pos = new FieldPosition(0);
        final String doubleMax2 = "359,538,626,972,463,141,629,054,847,463,408,"
                + "713,596,141,135,051,689,993,197,834,953,606,314,521,560,057,077,"
                + "521,179,117,265,533,756,343,080,917,907,028,764,928,468,642,653,"
                + "778,928,365,536,935,093,407,075,033,972,099,821,153,102,564,152,"
                + "490,980,180,778,657,888,151,737,016,910,267,884,609,166,473,806,"
                + "445,896,331,617,118,664,246,696,549,595,652,408,289,446,337,476,"
                + "354,361,838,599,762,500,808,052,368,249,716,736";
        bigDecimal = new BigDecimal(Double.MAX_VALUE).add(new BigDecimal(
                Double.MAX_VALUE));
        out = format.format(bigDecimal, new StringBuffer(), pos);
        assertTrue("Wrong result BDmax2: " + out, out.toString().equals(
                doubleMax2));

        // format big decimal Double.MIN_VALUE + Double.MIN_VALUE
        // and Double.MIN_VALUE - Double.MIN_VALUE
        pos = new FieldPosition(0);

        bigDecimal = new BigDecimal(Double.MIN_VALUE).add(new BigDecimal(
                Double.MIN_VALUE));
        out = format.format(bigDecimal, new StringBuffer(), pos);

        bigDecimal = new BigDecimal(Float.MAX_VALUE).add(new BigDecimal(
                Float.MAX_VALUE));
        out = format.format(bigDecimal, new StringBuffer(), pos);
        final String BDFloatMax2 = "680,564,693,277,057,719,623,408,366,969,033,850,880";
        assertTrue("Wrong result BDFloatMax2: " + out, out.toString().equals(
                BDFloatMax2));
        // format big decimal Float.MIN_VALUE + Float.MIN_VALUE
        // and Float.MIN_VALUE - Float.MIN_VALUE
        bigDecimal = new BigDecimal(Float.MIN_VALUE).add(new BigDecimal(
                Float.MIN_VALUE));
        out = format.format(bigDecimal, new StringBuffer(), pos);
        final String BDFloatMin2 = "0";

        bigDecimal = new BigDecimal(Float.MIN_VALUE).subtract(new BigDecimal(
                Float.MIN_VALUE));
        out = format.format(bigDecimal, new StringBuffer(), pos);

        assertTrue("Wrong result BDFloatMax2: " + out, out.toString().equals(
                BDFloatMin2));

    }

    public void test_setMaximumFractionDigitsLjava_lang_Integer() {
        NumberFormat nform = DecimalFormat.getInstance();
        DecimalFormat form = (DecimalFormat) nform;

        form.setMaximumFractionDigits(-2);
        assertEquals(0, form.getMaximumFractionDigits());

        form.setMaximumFractionDigits(341);
        assertEquals(341, form.getMaximumFractionDigits());
    }

    public void test_setMinimumFractionDigitsLjava_lang_Integer() {
        NumberFormat nform = DecimalFormat.getInstance();
        DecimalFormat form = (DecimalFormat) nform;

        form.setMinimumFractionDigits(-3);
        assertEquals(0, form.getMinimumFractionDigits());

        form.setMinimumFractionDigits(310);
        assertEquals(310, form.getMinimumFractionDigits());
    }

    public void test_setMaximumIntegerDigitsLjava_lang_Integer() {
        NumberFormat nform = DecimalFormat.getInstance();
        DecimalFormat form = (DecimalFormat) nform;

        form.setMaximumIntegerDigits(-3);
        assertEquals(0, form.getMaximumIntegerDigits());

        form.setMaximumIntegerDigits(310);
        assertEquals(310, form.getMaximumIntegerDigits());
    }

    public void test_setMinimumIntegerDigitsLjava_lang_Integer() {
        NumberFormat nform = DecimalFormat.getInstance();
        DecimalFormat form = (DecimalFormat) nform;

        form.setMinimumIntegerDigits(-3);
        assertEquals(0, form.getMinimumIntegerDigits());

        form.setMinimumIntegerDigits(310);
        assertEquals(310, form.getMinimumIntegerDigits());
    }

    // When MaxFractionDigits is set first and less than MinFractionDigits, max
    // will be changed to min value
    public void test_setMinimumFactionDigitsLjava_lang_Integer_setMaximumFractionDigitsLjava_lang_Integer() {
        NumberFormat nform = DecimalFormat.getInstance();
        DecimalFormat form = (DecimalFormat) nform;

        form.setMaximumFractionDigits(100);
        form.setMinimumFractionDigits(200);

        assertEquals(200, form.getMaximumFractionDigits());
        assertEquals(200, form.getMinimumFractionDigits());

        form.setMaximumIntegerDigits(100);
        form.setMinimumIntegerDigits(200);

        assertEquals(200, form.getMaximumIntegerDigits());
        assertEquals(200, form.getMinimumIntegerDigits());
    }

    // When MinFractionDigits is set first and less than MaxFractionDigits, min
    // will be changed to max value
    public void test_setMaximumFactionDigitsLjava_lang_Integer_setMinimumFractionDigitsLjava_lang_Integer() {
        NumberFormat nform = DecimalFormat.getInstance();
        DecimalFormat form = (DecimalFormat) nform;

        form.setMinimumFractionDigits(200);
        form.setMaximumFractionDigits(100);

        assertEquals(100, form.getMaximumFractionDigits());
        assertEquals(100, form.getMinimumFractionDigits());

        form.setMinimumIntegerDigits(200);
        form.setMaximumIntegerDigits(100);

        assertEquals(100, form.getMaximumIntegerDigits());
        assertEquals(100, form.getMinimumIntegerDigits());
    }

    // DecimalFormatSymbols inside of DecimalFormat is not shallow copied, but
    // deep copied.
    public void test_clone() {
        DecimalFormat format = (DecimalFormat) DecimalFormat.getInstance();
        DecimalFormat cloned = (DecimalFormat) format.clone();
        assertEquals(cloned.getDecimalFormatSymbols(), format
                .getDecimalFormatSymbols());
    }

    public void test_equalsLjava_lang_Object() {
        DecimalFormat format = (DecimalFormat) DecimalFormat.getInstance();
        DecimalFormat cloned = (DecimalFormat) format.clone();
        cloned.setDecimalFormatSymbols(new DecimalFormatSymbols());
        assertEquals(format, cloned);

        Currency c = Currency.getInstance(Locale.getDefault());
        cloned.setCurrency(c);

        assertEquals(format, cloned);
    }

    public void test_setDecimalFormatSymbolsLjava_text_DecimalFormatSymbols() {

        // The returned symbols may be cloned in two spots
        // 1. When set
        // 2. When returned
        DecimalFormat format = new DecimalFormat();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        format.setDecimalFormatSymbols(symbols);
        DecimalFormatSymbols symbolsOut = format.getDecimalFormatSymbols();
        assertNotSame(symbols, symbolsOut);
    }

    public void test_setPositivePrefixLjava_lang_String() {
        DecimalFormat format = new DecimalFormat();
        assertEquals("", format.getPositivePrefix());
    }

    public void test_setPositiveSuffixLjava_lang_String() {
        DecimalFormat format = new DecimalFormat();
        assertEquals("", format.getPositiveSuffix());
    }

    public void test_setNegativePrefixLjava_lang_String() {
        DecimalFormat format = new DecimalFormat();
        assertEquals("-", format.getNegativePrefix());
    }

    public void test_setNegativeSuffixLjava_lang_String() {
        DecimalFormat format = new DecimalFormat();
        assertEquals("", format.getNegativeSuffix());
    }

}
