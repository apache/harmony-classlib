/* Copyright 1998, 2006 The Apache Software Foundation or its licensors, as applicable
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class DecimalFormatSymbolsTest extends junit.framework.TestCase {

	DecimalFormatSymbols dfs;

	DecimalFormatSymbols dfsUS;

	/**
	 * @tests java.text.DecimalFormatSymbols#DecimalFormatSymbols()
	 */
	public void test_Constructor() {
		// Test for method java.text.DecimalFormatSymbols()
		// Used in tests
	}

	/**
	 * @tests java.text.DecimalFormatSymbols#DecimalFormatSymbols(java.util.Locale)
	 */
	public void test_ConstructorLjava_util_Locale() {
		DecimalFormatSymbols dfs = new DecimalFormatSymbols(new Locale("en",
				"us"));
		assertTrue("Returned incorrect symbols", dfs.getPercent() == '%');
	}

	/**
	 * @tests java.text.DecimalFormatSymbols#equals(java.lang.Object)
	 */
	public void test_equalsLjava_lang_Object() {
		assertTrue("Equal objects returned false", dfs.equals(dfs.clone()));
		dfs.setDigit('B');
		assertTrue("Un-Equal objects returned true", !dfs
				.equals(new DecimalFormatSymbols()));

	}

	/**
	 * @tests java.text.DecimalFormatSymbols#getCurrency()
	 */
	public void test_getCurrency() {
		Currency currency = Currency.getInstance("USD");
		assertTrue("Returned incorrect currency",
				dfsUS.getCurrency() == currency);

		Currency currK = Currency.getInstance("KRW");
		Currency currX = Currency.getInstance("XXX");
		Currency currE = Currency.getInstance("EUR");
		//Currency currF = Currency.getInstance("FRF");

		DecimalFormatSymbols dfs1 = new DecimalFormatSymbols(new Locale("ko",
				"KR"));
		assertTrue("Test1: Returned incorrect currency",
				dfs1.getCurrency() == currK);
		assertTrue("Test1: Returned incorrect currencySymbol", dfs1
				.getCurrencySymbol().equals("\uffe6"));
		assertTrue("Test1: Returned incorrect intlCurrencySymbol", dfs1
				.getInternationalCurrencySymbol().equals("KRW"));

		dfs1 = new DecimalFormatSymbols(new Locale("", "KR"));
		assertTrue("Test2: Returned incorrect currency",
				dfs1.getCurrency() == currK);
		assertTrue("Test2: Returned incorrect currencySymbol", dfs1
				.getCurrencySymbol().equals("KRW"));
		assertTrue("Test2: Returned incorrect intlCurrencySymbol", dfs1
				.getInternationalCurrencySymbol().equals("KRW"));

		dfs1 = new DecimalFormatSymbols(new Locale("ko", ""));
		assertTrue("Test3: Returned incorrect currency",
				dfs1.getCurrency() == currX);
		assertTrue("Test3: Returned incorrect currencySymbol", dfs1
				.getCurrencySymbol().equals("\u00a4"));
		assertTrue("Test3: Returned incorrect intlCurrencySymbol", dfs1
				.getInternationalCurrencySymbol().equals("XXX"));

		dfs1 = new DecimalFormatSymbols(new Locale("fr", "FR"));
		assertTrue("Test4: Returned incorrect currency",
				dfs1.getCurrency() == currE);
		assertTrue("Test4: Returned incorrect currencySymbol", dfs1
				.getCurrencySymbol().equals("\u20ac"));
		assertTrue("Test4: Returned incorrect intlCurrencySymbol", dfs1
				.getInternationalCurrencySymbol().equals("EUR"));

		// RI fails these tests since it doesn't have the PREEURO variant
		// dfs1 = new DecimalFormatSymbols(new Locale("fr", "FR","PREEURO"));
		// assertTrue("Test5: Returned incorrect currency", dfs1.getCurrency()
		// == currF);
		// assertTrue("Test5: Returned incorrect currencySymbol",
		// dfs1.getCurrencySymbol().equals("F"));
		// assertTrue("Test5: Returned incorrect intlCurrencySymbol",
		// dfs1.getInternationalCurrencySymbol().equals("FRF"));
	}

	/**
	 * @tests java.text.DecimalFormatSymbols#getCurrencySymbol()
	 */
	public void test_getCurrencySymbol() {
		assertTrue("Returned incorrect currencySymbol", dfsUS
				.getCurrencySymbol().equals("$"));
	}

	/**
	 * @tests java.text.DecimalFormatSymbols#getDecimalSeparator()
	 */
	public void test_getDecimalSeparator() {
		dfs.setDecimalSeparator('*');
		assertTrue("Returned incorrect DecimalSeparator symbol", dfs
				.getDecimalSeparator() == '*');
	}

	/**
	 * @tests java.text.DecimalFormatSymbols#getDigit()
	 */
	public void test_getDigit() {
		dfs.setDigit('*');
		assertTrue("Returned incorrect Digit symbol", dfs.getDigit() == '*');
	}

	/**
	 * @tests java.text.DecimalFormatSymbols#getGroupingSeparator()
	 */
	public void test_getGroupingSeparator() {
		dfs.setGroupingSeparator('*');
		assertTrue("Returned incorrect GroupingSeparator symbol", dfs
				.getGroupingSeparator() == '*');
	}

	/**
	 * @tests java.text.DecimalFormatSymbols#getInfinity()
	 */
	public void test_getInfinity() {
		dfs.setInfinity("&");
		assertTrue("Returned incorrect Infinity symbol",
				dfs.getInfinity() == "&");
	}

	/**
	 * @tests java.text.DecimalFormatSymbols#getInternationalCurrencySymbol()
	 */
	public void test_getInternationalCurrencySymbol() {
		assertTrue("Returned incorrect InternationalCurrencySymbol", dfsUS
				.getInternationalCurrencySymbol().equals("USD"));
	}

	/**
	 * @tests java.text.DecimalFormatSymbols#getMinusSign()
	 */
	public void test_getMinusSign() {
		dfs.setMinusSign('&');
		assertTrue("Returned incorrect MinusSign symbol",
				dfs.getMinusSign() == '&');
	}

	/**
	 * @tests java.text.DecimalFormatSymbols#getNaN()
	 */
	public void test_getNaN() {
		dfs.setNaN("NAN!!");
		assertTrue("Returned incorrect nan symbol", dfs.getNaN()
				.equals("NAN!!"));
	}

	/**
	 * @tests java.text.DecimalFormatSymbols#getPatternSeparator()
	 */
	public void test_getPatternSeparator() {
		dfs.setPatternSeparator('X');
		assertTrue("Returned incorrect PatternSeparator symbol", dfs
				.getPatternSeparator() == 'X');
	}

	/**
	 * @tests java.text.DecimalFormatSymbols#getPercent()
	 */
	public void test_getPercent() {
		dfs.setPercent('*');
		assertTrue("Returned incorrect Percent symbol", dfs.getPercent() == '*');
	}

	/**
	 * @tests java.text.DecimalFormatSymbols#getPerMill()
	 */
	public void test_getPerMill() {
		dfs.setPerMill('#');
		assertTrue("Returned incorrect PerMill symbol", dfs.getPerMill() == '#');
	}

	/**
	 * @tests java.text.DecimalFormatSymbols#getZeroDigit()
	 */
	public void test_getZeroDigit() {
		dfs.setZeroDigit('*');
		assertTrue("Returned incorrect ZeroDigit symbol",
				dfs.getZeroDigit() == '*');
	}

	/**
	 * @tests java.text.DecimalFormatSymbols#setCurrency(java.util.Currency)
	 */
	public void test_setCurrencyLjava_util_Currency() {
		Locale locale = Locale.CANADA;
		DecimalFormatSymbols dfs = ((DecimalFormat) NumberFormat
				.getCurrencyInstance(locale)).getDecimalFormatSymbols();

		try {
			dfs.setCurrency(null);
			fail("Expected NullPointerException");
		} catch (NullPointerException e) {
		}

		Currency currency = Currency.getInstance("JPY");
		dfs.setCurrency(currency);

		assertTrue("Returned incorrect currency", currency == dfs.getCurrency());
		assertTrue("Returned incorrect currency symbol", currency.getSymbol(
				locale).equals(dfs.getCurrencySymbol()));
		assertTrue("Returned incorrect international currency symbol", currency
				.getCurrencyCode().equals(dfs.getInternationalCurrencySymbol()));
	}

	/**
	 * @tests java.text.DecimalFormatSymbols#setDecimalSeparator(char)
	 */
	public void test_setDecimalSeparatorC() {
		dfs.setDecimalSeparator('*');
		assertTrue("Returned incorrect DecimalSeparator symbol", dfs
				.getDecimalSeparator() == '*');
	}

	/**
	 * @tests java.text.DecimalFormatSymbols#setDigit(char)
	 */
	public void test_setDigitC() {
		dfs.setDigit('*');
		assertTrue("Returned incorrect Digit symbol", dfs.getDigit() == '*');
	}

	/**
	 * @tests java.text.DecimalFormatSymbols#setGroupingSeparator(char)
	 */
	public void test_setGroupingSeparatorC() {
		dfs.setGroupingSeparator('*');
		assertTrue("Returned incorrect GroupingSeparator symbol", dfs
				.getGroupingSeparator() == '*');
	}

	/**
	 * @tests java.text.DecimalFormatSymbols#setInfinity(java.lang.String)
	 */
	public void test_setInfinityLjava_lang_String() {
		dfs.setInfinity("&");
		assertTrue("Returned incorrect Infinity symbol",
				dfs.getInfinity() == "&");
	}

	/**
	 * @tests java.text.DecimalFormatSymbols#setInternationalCurrencySymbol(java.lang.String)
	 */
	public void test_setInternationalCurrencySymbolLjava_lang_String() {
		Locale locale = Locale.CANADA;
		DecimalFormatSymbols dfs = ((DecimalFormat) NumberFormat
				.getCurrencyInstance(locale)).getDecimalFormatSymbols();
		Currency currency = Currency.getInstance("JPY");
		dfs.setInternationalCurrencySymbol(currency.getCurrencyCode());

		assertTrue("Test1: Returned incorrect currency", currency == dfs
				.getCurrency());
		assertTrue("Test1: Returned incorrect currency symbol", currency
				.getSymbol(locale).equals(dfs.getCurrencySymbol()));
		assertTrue("Test1: Returned incorrect international currency symbol",
				currency.getCurrencyCode().equals(
						dfs.getInternationalCurrencySymbol()));

		String symbol = dfs.getCurrencySymbol();
		dfs.setInternationalCurrencySymbol("bogus");
		assertTrue("Test2: Returned incorrect currency",
				dfs.getCurrency() == null);
		assertTrue("Test2: Returned incorrect currency symbol", dfs
				.getCurrencySymbol().equals(symbol));
		assertTrue("Test2: Returned incorrect international currency symbol",
				dfs.getInternationalCurrencySymbol().equals("bogus"));
	}

	/**
	 * @tests java.text.DecimalFormatSymbols#setMinusSign(char)
	 */
	public void test_setMinusSignC() {
		dfs.setMinusSign('&');
		assertTrue("Returned incorrect MinusSign symbol",
				dfs.getMinusSign() == '&');
	}

	/**
	 * @tests java.text.DecimalFormatSymbols#setNaN(java.lang.String)
	 */
	public void test_setNaNLjava_lang_String() {
		dfs.setNaN("NAN!!");
		assertTrue("Returned incorrect nan symbol", dfs.getNaN()
				.equals("NAN!!"));
	}

	/**
	 * @tests java.text.DecimalFormatSymbols#setPatternSeparator(char)
	 */
	public void test_setPatternSeparatorC() {
		dfs.setPatternSeparator('X');
		assertTrue("Returned incorrect PatternSeparator symbol", dfs
				.getPatternSeparator() == 'X');
	}

	/**
	 * @tests java.text.DecimalFormatSymbols#setPercent(char)
	 */
	public void test_setPercentC() {
		dfs.setPercent('*');
		assertTrue("Returned incorrect Percent symbol", dfs.getPercent() == '*');
	}

	/**
	 * @tests java.text.DecimalFormatSymbols#setPerMill(char)
	 */
	public void test_setPerMillC() {
		dfs.setPerMill('#');
		assertTrue("Returned incorrect PerMill symbol", dfs.getPerMill() == '#');
	}

	/**
	 * @tests java.text.DecimalFormatSymbols#setZeroDigit(char)
	 */
	public void test_setZeroDigitC() {
		dfs.setZeroDigit('*');
		assertTrue("Set incorrect ZeroDigit symbol", dfs.getZeroDigit() == '*');
	}

	/**
	 * Sets up the fixture, for example, open a network connection. This method
	 * is called before a test is executed.
	 */
	protected void setUp() {
		dfs = new DecimalFormatSymbols();
		dfsUS = new DecimalFormatSymbols(new Locale("en", "us"));
	}

	/**
	 * Tears down the fixture, for example, close a network connection. This
	 * method is called after a test is executed.
	 */
	protected void tearDown() {
	}

	// Test serialization mechanism of DecimalFormatSymbols
	public void test_serialization() throws Exception {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.FRANCE);
		Currency currency = symbols.getCurrency();
		assertNotNull(currency);

		// serialize
		ByteArrayOutputStream byteOStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOStream = new ObjectOutputStream(byteOStream);
		objectOStream.writeObject(symbols);

		// and deserialize
		ObjectInputStream objectIStream = new ObjectInputStream(
				new ByteArrayInputStream(byteOStream.toByteArray()));
		DecimalFormatSymbols symbolsD = (DecimalFormatSymbols) objectIStream
				.readObject();

		// The associated currency will not persist
		currency = symbolsD.getCurrency();
		assertNotNull(currency);
	}

	// Use RI to write DecimalFormatSymbols out, use Harmony to read
	// DecimalFormatSymbols in. The read symbol will be equal with those
	// instantiated inside Harmony.
	
	// This assertion will not come into existence the other way around. This is
	// probably caused by different serialization mechanism used by RI and
	// Harmony.
	public void test_RIHarmony_compatible() throws Exception {
		ObjectInputStream i = null;
		try {
			DecimalFormatSymbols symbols = new DecimalFormatSymbols(
					Locale.FRANCE);
			i = new ObjectInputStream(
					new FileInputStream(
							new File(URI.create(
									this.getClass().getResource(
										"/serialization/java/text/DecimalFormatSymbols.ser")
											.toString()))));
			DecimalFormatSymbols symbolsD = (DecimalFormatSymbols) i
					.readObject();
			assertEquals(symbols, symbolsD);
		} finally {
			try {
				if (i != null) {
					i.close();
				}
			} catch (Exception e) {
				// ignore
			}
		}
	}
}
