/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
/**
 * @author Hugo Beilis
 * @author Osvaldo Demo
 * @author Jorge Rafael
 * @version 1.0
 */

package ar.org.fitc.test.math.bigdecimal;

import java.math.BigDecimal;
import java.math.BigInteger;
import junit.framework.TestCase;
import ar.org.fitc.test.util.Messages;
/**
 * Test cases for
 * valueOf(double) 
 * valueOf(long) 
 * valueOf(long, int) 
 * 
 */
public class TestBigDecimalValueOf extends TestCase implements Messages {

	public TestBigDecimalValueOf(String string) {
		super(string);
	}

	/*
	 * Test method for 'java.math.BigDecimal.valueOf(long, int)'
	 */
	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = 0 and an integer = 0the returning value is compared with the
	 * constructor BigDecimal(BigInteger unscaledVal, int scale)
	 */
	public void testValueOfLongInt001() {
		long unscaledVal = 0;
		int scale = 0;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger("0"), 0),
				BigDecimal.valueOf(unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = 0 and an integer = 2147483647the returning value is compared with
	 * the constructor BigDecimal(BigInteger unscaledVal, int scale)
	 */
	public void testValueOfLongInt002() {
		long unscaledVal = 0;
		int scale = 2147483647;
		assertEquals(msgNotSame,
				new BigDecimal(new BigInteger("0"), 2147483647), BigDecimal
						.valueOf(unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = 0 and an integer = -2147483648the returning value is compared with
	 * the constructor BigDecimal(BigInteger unscaledVal, int scale)
	 */
	public void testValueOfLongInt003() {
		long unscaledVal = 0;
		int scale = -2147483648;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger("0"),
				-2147483648), BigDecimal.valueOf(unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = 0 and an integer = 1789610230the returning value is compared with
	 * the constructor BigDecimal(BigInteger unscaledVal, int scale)
	 */
	public void testValueOfLongInt004() {
		long unscaledVal = 0;
		int scale = 1789610230;
		assertEquals(msgNotSame,
				new BigDecimal(new BigInteger("0"), 1789610230), BigDecimal
						.valueOf(unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = 0 and an integer = -644494099the returning value is compared with
	 * the constructor BigDecimal(BigInteger unscaledVal, int scale)
	 */
	public void testValueOfLongInt005() {
		long unscaledVal = 0;
		int scale = -644494099;
		assertEquals(msgNotSame,
				new BigDecimal(new BigInteger("0"), -644494099), BigDecimal
						.valueOf(unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = 9223372036854775807 and an integer = 0the returning value is
	 * compared with the constructor BigDecimal(BigInteger unscaledVal, int
	 * scale)
	 */
	public void testValueOfLongInt006() {
		long unscaledVal = 9223372036854775807L;
		int scale = 0;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"9223372036854775807"), 0), BigDecimal.valueOf(unscaledVal,
				scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = 9223372036854775807 and an integer = 2147483647the returning value
	 * is compared with the constructor BigDecimal(BigInteger unscaledVal, int
	 * scale)
	 */
	public void testValueOfLongInt007() {
		long unscaledVal = 9223372036854775807L;
		int scale = 2147483647;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"9223372036854775807"), 2147483647), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = 9223372036854775807 and an integer = -2147483648the returning
	 * value is compared with the constructor BigDecimal(BigInteger unscaledVal,
	 * int scale)
	 */
	public void testValueOfLongInt008() {
		long unscaledVal = 9223372036854775807L;
		int scale = -2147483648;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"9223372036854775807"), -2147483648), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = 9223372036854775807 and an integer = 1789610230the returning value
	 * is compared with the constructor BigDecimal(BigInteger unscaledVal, int
	 * scale)
	 */
	public void testValueOfLongInt009() {
		long unscaledVal = 9223372036854775807L;
		int scale = 1789610230;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"9223372036854775807"), 1789610230), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = 9223372036854775807 and an integer = -644494099the returning value
	 * is compared with the constructor BigDecimal(BigInteger unscaledVal, int
	 * scale)
	 */
	public void testValueOfLongInt010() {
		long unscaledVal = 9223372036854775807L;
		int scale = -644494099;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"9223372036854775807"), -644494099), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = -9223372036854775808 and an integer = 0the returning value is
	 * compared with the constructor BigDecimal(BigInteger unscaledVal, int
	 * scale)
	 */
	public void testValueOfLongInt011() {
		long unscaledVal = -9223372036854775808L;
		int scale = 0;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"-9223372036854775808"), 0), BigDecimal.valueOf(unscaledVal,
				scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = -9223372036854775808 and an integer = 2147483647the returning
	 * value is compared with the constructor BigDecimal(BigInteger unscaledVal,
	 * int scale)
	 */
	public void testValueOfLongInt012() {
		long unscaledVal = -9223372036854775808L;
		int scale = 2147483647;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"-9223372036854775808"), 2147483647), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = -9223372036854775808 and an integer = -2147483648the returning
	 * value is compared with the constructor BigDecimal(BigInteger unscaledVal,
	 * int scale)
	 */
	public void testValueOfLongInt013() {
		long unscaledVal = -9223372036854775808L;
		int scale = -2147483648;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"-9223372036854775808"), -2147483648), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = -9223372036854775808 and an integer = 1789610230the returning
	 * value is compared with the constructor BigDecimal(BigInteger unscaledVal,
	 * int scale)
	 */
	public void testValueOfLongInt014() {
		long unscaledVal = -9223372036854775808L;
		int scale = 1789610230;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"-9223372036854775808"), 1789610230), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = -9223372036854775808 and an integer = -644494099the returning
	 * value is compared with the constructor BigDecimal(BigInteger unscaledVal,
	 * int scale)
	 */
	public void testValueOfLongInt015() {
		long unscaledVal = -9223372036854775808L;
		int scale = -644494099;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"-9223372036854775808"), -644494099), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = 9223372036854775806 and an integer = 0the returning value is
	 * compared with the constructor BigDecimal(BigInteger unscaledVal, int
	 * scale)
	 */
	public void testValueOfLongInt016() {
		long unscaledVal = 9223372036854775806L;
		int scale = 0;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"9223372036854775806"), 0), BigDecimal.valueOf(unscaledVal,
				scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = 9223372036854775806 and an integer = 2147483647the returning value
	 * is compared with the constructor BigDecimal(BigInteger unscaledVal, int
	 * scale)
	 */
	public void testValueOfLongInt017() {
		long unscaledVal = 9223372036854775806L;
		int scale = 2147483647;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"9223372036854775806"), 2147483647), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = 9223372036854775806 and an integer = -2147483648the returning
	 * value is compared with the constructor BigDecimal(BigInteger unscaledVal,
	 * int scale)
	 */
	public void testValueOfLongInt018() {
		long unscaledVal = 9223372036854775806L;
		int scale = -2147483648;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"9223372036854775806"), -2147483648), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = 9223372036854775806 and an integer = 1789610230the returning value
	 * is compared with the constructor BigDecimal(BigInteger unscaledVal, int
	 * scale)
	 */
	public void testValueOfLongInt019() {
		long unscaledVal = 9223372036854775806L;
		int scale = 1789610230;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"9223372036854775806"), 1789610230), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = 9223372036854775806 and an integer = -644494099the returning value
	 * is compared with the constructor BigDecimal(BigInteger unscaledVal, int
	 * scale)
	 */
	public void testValueOfLongInt020() {
		long unscaledVal = 9223372036854775806L;
		int scale = -644494099;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"9223372036854775806"), -644494099), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = -9223372036854775807 and an integer = 0the returning value is
	 * compared with the constructor BigDecimal(BigInteger unscaledVal, int
	 * scale)
	 */
	public void testValueOfLongInt021() {
		long unscaledVal = -9223372036854775807L;
		int scale = 0;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"-9223372036854775807"), 0), BigDecimal.valueOf(unscaledVal,
				scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = -9223372036854775807 and an integer = 2147483647the returning
	 * value is compared with the constructor BigDecimal(BigInteger unscaledVal,
	 * int scale)
	 */
	public void testValueOfLongInt022() {
		long unscaledVal = -9223372036854775807L;
		int scale = 2147483647;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"-9223372036854775807"), 2147483647), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = -9223372036854775807 and an integer = -2147483648the returning
	 * value is compared with the constructor BigDecimal(BigInteger unscaledVal,
	 * int scale)
	 */
	public void testValueOfLongInt023() {
		long unscaledVal = -9223372036854775807L;
		int scale = -2147483648;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"-9223372036854775807"), -2147483648), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = -9223372036854775807 and an integer = 1789610230the returning
	 * value is compared with the constructor BigDecimal(BigInteger unscaledVal,
	 * int scale)
	 */
	public void testValueOfLongInt024() {
		long unscaledVal = -9223372036854775807L;
		int scale = 1789610230;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"-9223372036854775807"), 1789610230), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = -9223372036854775807 and an integer = -644494099the returning
	 * value is compared with the constructor BigDecimal(BigInteger unscaledVal,
	 * int scale)
	 */
	public void testValueOfLongInt025() {
		long unscaledVal = -9223372036854775807L;
		int scale = -644494099;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"-9223372036854775807"), -644494099), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = 422337299785477 and an integer = 0the returning value is compared
	 * with the constructor BigDecimal(BigInteger unscaledVal, int scale)
	 */
	public void testValueOfLongInt026() {
		long unscaledVal = 422337299785477L;
		int scale = 0;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"422337299785477"), 0), BigDecimal.valueOf(unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = 422337299785477 and an integer = 2147483647the returning value is
	 * compared with the constructor BigDecimal(BigInteger unscaledVal, int
	 * scale)
	 */
	public void testValueOfLongInt027() {
		long unscaledVal = 422337299785477L;
		int scale = 2147483647;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"422337299785477"), 2147483647), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = 422337299785477 and an integer = -2147483648the returning value is
	 * compared with the constructor BigDecimal(BigInteger unscaledVal, int
	 * scale)
	 */
	public void testValueOfLongInt028() {
		long unscaledVal = 422337299785477L;
		int scale = -2147483648;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"422337299785477"), -2147483648), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = 422337299785477 and an integer = 1789610230the returning value is
	 * compared with the constructor BigDecimal(BigInteger unscaledVal, int
	 * scale)
	 */
	public void testValueOfLongInt029() {
		long unscaledVal = 422337299785477L;
		int scale = 1789610230;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"422337299785477"), 1789610230), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = 422337299785477 and an integer = -644494099the returning value is
	 * compared with the constructor BigDecimal(BigInteger unscaledVal, int
	 * scale)
	 */
	public void testValueOfLongInt030() {
		long unscaledVal = 422337299785477L;
		int scale = -644494099;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"422337299785477"), -644494099), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = -32233746668547 and an integer = 0the returning value is compared
	 * with the constructor BigDecimal(BigInteger unscaledVal, int scale)
	 */
	public void testValueOfLongInt031() {
		long unscaledVal = -32233746668547L;
		int scale = 0;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"-32233746668547"), 0), BigDecimal.valueOf(unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = -32233746668547 and an integer = 2147483647the returning value is
	 * compared with the constructor BigDecimal(BigInteger unscaledVal, int
	 * scale)
	 */
	public void testValueOfLongInt032() {
		long unscaledVal = -32233746668547L;
		int scale = 2147483647;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"-32233746668547"), 2147483647), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = -32233746668547 and an integer = -2147483648the returning value is
	 * compared with the constructor BigDecimal(BigInteger unscaledVal, int
	 * scale)
	 */
	public void testValueOfLongInt033() {
		long unscaledVal = -32233746668547L;
		int scale = -2147483648;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"-32233746668547"), -2147483648), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = -32233746668547 and an integer = 1789610230the returning value is
	 * compared with the constructor BigDecimal(BigInteger unscaledVal, int
	 * scale)
	 */
	public void testValueOfLongInt034() {
		long unscaledVal = -32233746668547L;
		int scale = 1789610230;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"-32233746668547"), 1789610230), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = -32233746668547 and an integer = -644494099the returning value is
	 * compared with the constructor BigDecimal(BigInteger unscaledVal, int
	 * scale)
	 */
	public void testValueOfLongInt035() {
		long unscaledVal = -32233746668547L;
		int scale = -644494099;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"-32233746668547"), -644494099), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = 984051986416501 and an integer = 0the returning value is compared
	 * with the constructor BigDecimal(BigInteger unscaledVal, int scale)
	 */
	public void testValueOfLongInt036() {
		long unscaledVal = 984051986416501L;
		int scale = 0;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"984051986416501"), 0), BigDecimal.valueOf(unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = 984051986416501 and an integer = 2147483647the returning value is
	 * compared with the constructor BigDecimal(BigInteger unscaledVal, int
	 * scale)
	 */
	public void testValueOfLongInt037() {
		long unscaledVal = 984051986416501L;
		int scale = 2147483647;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"984051986416501"), 2147483647), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = 984051986416501 and an integer = -2147483648the returning value is
	 * compared with the constructor BigDecimal(BigInteger unscaledVal, int
	 * scale)
	 */
	public void testValueOfLongInt038() {
		long unscaledVal = 984051986416501L;
		int scale = -2147483648;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"984051986416501"), -2147483648), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = 984051986416501 and an integer = 1789610230the returning value is
	 * compared with the constructor BigDecimal(BigInteger unscaledVal, int
	 * scale)
	 */
	public void testValueOfLongInt039() {
		long unscaledVal = 984051986416501L;
		int scale = 1789610230;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"984051986416501"), 1789610230), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = 984051986416501 and an integer = -644494099the returning value is
	 * compared with the constructor BigDecimal(BigInteger unscaledVal, int
	 * scale)
	 */
	public void testValueOfLongInt040() {
		long unscaledVal = 984051986416501L;
		int scale = -644494099;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"984051986416501"), -644494099), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = -1941111896411165 and an integer = 0the returning value is
	 * compared with the constructor BigDecimal(BigInteger unscaledVal, int
	 * scale)
	 */
	public void testValueOfLongInt041() {
		long unscaledVal = -1941111896411165L;
		int scale = 0;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"-1941111896411165"), 0), BigDecimal
				.valueOf(unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = -1941111896411165 and an integer = 2147483647the returning value
	 * is compared with the constructor BigDecimal(BigInteger unscaledVal, int
	 * scale)
	 */
	public void testValueOfLongInt042() {
		long unscaledVal = -1941111896411165L;
		int scale = 2147483647;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"-1941111896411165"), 2147483647), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = -1941111896411165 and an integer = -2147483648the returning value
	 * is compared with the constructor BigDecimal(BigInteger unscaledVal, int
	 * scale)
	 */
	public void testValueOfLongInt043() {
		long unscaledVal = -1941111896411165L;
		int scale = -2147483648;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"-1941111896411165"), -2147483648), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = -1941111896411165 and an integer = 1789610230the returning value
	 * is compared with the constructor BigDecimal(BigInteger unscaledVal, int
	 * scale)
	 */
	public void testValueOfLongInt044() {
		long unscaledVal = -1941111896411165L;
		int scale = 1789610230;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"-1941111896411165"), 1789610230), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = -1941111896411165 and an integer = -644494099the returning value
	 * is compared with the constructor BigDecimal(BigInteger unscaledVal, int
	 * scale)
	 */
	public void testValueOfLongInt045() {
		long unscaledVal = -1941111896411165L;
		int scale = -644494099;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"-1941111896411165"), -644494099), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = 489105647416645 and an integer = 0the returning value is compared
	 * with the constructor BigDecimal(BigInteger unscaledVal, int scale)
	 */
	public void testValueOfLongInt046() {
		long unscaledVal = 489105647416645L;
		int scale = 0;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"489105647416645"), 0), BigDecimal.valueOf(unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = 489105647416645 and an integer = 2147483647the returning value is
	 * compared with the constructor BigDecimal(BigInteger unscaledVal, int
	 * scale)
	 */
	public void testValueOfLongInt047() {
		long unscaledVal = 489105647416645L;
		int scale = 2147483647;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"489105647416645"), 2147483647), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = 489105647416645 and an integer = -2147483648the returning value is
	 * compared with the constructor BigDecimal(BigInteger unscaledVal, int
	 * scale)
	 */
	public void testValueOfLongInt048() {
		long unscaledVal = 489105647416645L;
		int scale = -2147483648;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"489105647416645"), -2147483648), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = 489105647416645 and an integer = 1789610230the returning value is
	 * compared with the constructor BigDecimal(BigInteger unscaledVal, int
	 * scale)
	 */
	public void testValueOfLongInt049() {
		long unscaledVal = 489105647416645L;
		int scale = 1789610230;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"489105647416645"), 1789610230), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = 489105647416645 and an integer = -644494099the returning value is
	 * compared with the constructor BigDecimal(BigInteger unscaledVal, int
	 * scale)
	 */
	public void testValueOfLongInt050() {
		long unscaledVal = 489105647416645L;
		int scale = -644494099;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"489105647416645"), -644494099), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = -478498640848900489 and an integer = 0the returning value is
	 * compared with the constructor BigDecimal(BigInteger unscaledVal, int
	 * scale)
	 */
	public void testValueOfLongInt051() {
		long unscaledVal = -478498640848900489L;
		int scale = 0;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"-478498640848900489"), 0), BigDecimal.valueOf(unscaledVal,
				scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = -478498640848900489 and an integer = 2147483647the returning value
	 * is compared with the constructor BigDecimal(BigInteger unscaledVal, int
	 * scale)
	 */
	public void testValueOfLongInt052() {
		long unscaledVal = -478498640848900489L;
		int scale = 2147483647;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"-478498640848900489"), 2147483647), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = -478498640848900489 and an integer = -2147483648the returning
	 * value is compared with the constructor BigDecimal(BigInteger unscaledVal,
	 * int scale)
	 */
	public void testValueOfLongInt053() {
		long unscaledVal = -478498640848900489L;
		int scale = -2147483648;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"-478498640848900489"), -2147483648), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = -478498640848900489 and an integer = 1789610230the returning value
	 * is compared with the constructor BigDecimal(BigInteger unscaledVal, int
	 * scale)
	 */
	public void testValueOfLongInt054() {
		long unscaledVal = -478498640848900489L;
		int scale = 1789610230;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"-478498640848900489"), 1789610230), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long ,int)</b> with a
	 * long = -478498640848900489 and an integer = -644494099the returning value
	 * is compared with the constructor BigDecimal(BigInteger unscaledVal, int
	 * scale)
	 */
	public void testValueOfLongInt055() {
		long unscaledVal = -478498640848900489L;
		int scale = -644494099;
		assertEquals(msgNotSame, new BigDecimal(new BigInteger(
				"-478498640848900489"), -644494099), BigDecimal.valueOf(
				unscaledVal, scale));
	}

	/*
	 * Test method for 'java.math.BigDecimal.valueOf(long)'
	 */
	/**
	 * This method creates a BigDecimal with <b>ValueOf(long)</b> with a long =
	 * 0 , the returning value after calling the LongValue method should be
	 * equal to the long value parameter
	 */
	public void testValueOfLong001() {
		BigDecimal bd = BigDecimal.valueOf(0L);
		assertEquals(msgNotSame, bd.longValue(), 0L);
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long)</b> with a long =
	 * 9223372036854775807 , the returning value after calling the LongValue
	 * method should be equal to the long value parameter
	 */
	public void testValueOfLong002() {
		BigDecimal bd = BigDecimal.valueOf(9223372036854775807L);
		assertEquals(msgNotSame, bd.longValue(), 9223372036854775807L);
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long)</b> with a long =
	 * -9223372036854775808 , the returning value after calling the LongValue
	 * method should be equal to the long value parameter
	 */
	public void testValueOfLong003() {
		BigDecimal bd = BigDecimal.valueOf(-9223372036854775808L);
		assertEquals(msgNotSame, bd.longValue(), -9223372036854775808L);
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long)</b> with a long =
	 * 9223372036854775806 , the returning value after calling the LongValue
	 * method should be equal to the long value parameter
	 */
	public void testValueOfLong004() {
		BigDecimal bd = BigDecimal.valueOf(9223372036854775806L);
		assertEquals(msgNotSame, bd.longValue(), 9223372036854775806L);
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long)</b> with a long =
	 * -9223372036854775807 , the returning value after calling the LongValue
	 * method should be equal to the long value parameter
	 */
	public void testValueOfLong005() {
		BigDecimal bd = BigDecimal.valueOf(-9223372036854775807L);
		assertEquals(msgNotSame, bd.longValue(), -9223372036854775807L);
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long)</b> with a long =
	 * 422337299785477 , the returning value after calling the LongValue method
	 * should be equal to the long value parameter
	 */
	public void testValueOfLong006() {
		BigDecimal bd = BigDecimal.valueOf(422337299785477L);
		assertEquals(msgNotSame, bd.longValue(), 422337299785477L);
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long)</b> with a long =
	 * -32233746668547 , the returning value after calling the LongValue method
	 * should be equal to the long value parameter
	 */
	public void testValueOfLong007() {
		BigDecimal bd = BigDecimal.valueOf(-32233746668547L);
		assertEquals(msgNotSame, bd.longValue(), -32233746668547L);
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long)</b> with a long =
	 * 984051986416501 , the returning value after calling the LongValue method
	 * should be equal to the long value parameter
	 */
	public void testValueOfLong008() {
		BigDecimal bd = BigDecimal.valueOf(984051986416501L);
		assertEquals(msgNotSame, bd.longValue(), 984051986416501L);
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long)</b> with a long =
	 * -1941111896411165 , the returning value after calling the LongValue
	 * method should be equal to the long value parameter
	 */
	public void testValueOfLong009() {
		BigDecimal bd = BigDecimal.valueOf(-1941111896411165L);
		assertEquals(msgNotSame, bd.longValue(), -1941111896411165L);
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long)</b> with a long =
	 * 489105647416645 , the returning value after calling the LongValue method
	 * should be equal to the long value parameter
	 */
	public void testValueOfLong010() {
		BigDecimal bd = BigDecimal.valueOf(489105647416645L);
		assertEquals(msgNotSame, bd.longValue(), 489105647416645L);
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(long)</b> with a long =
	 * -478498640848900489 , the returning value after calling the LongValue
	 * method should be equal to the long value parameter
	 */
	public void testValueOfLong011() {
		BigDecimal bd = BigDecimal.valueOf(-478498640848900489L);
		assertEquals(msgNotSame, bd.longValue(), -478498640848900489L);
	}

	/*
	 * Test method for 'java.math.BigDecimal.valueOf(double)'
	 */

	/**
	 * This method creates a BigDecimal with <b>ValueOf(double)</b> with a
	 * double = 0.0 , the returning value after calling the DoubleValue method
	 * should be equal to the double value parameter
	 */
	public void testValueOfDouble001() {
		BigDecimal bigDec = BigDecimal.valueOf(0.0);
		assertEquals(msgNotSame, bigDec.doubleValue(), 0.0);
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(double)</b> with a
	 * double = 1.7976931348623157E308 , the returning value after calling the
	 * DoubleValue method should be equal to the double value parameter
	 */
	public void testValueOfDouble002() {
		BigDecimal bigDec = BigDecimal.valueOf(1.7976931348623157E308);
		assertEquals(msgNotSame, bigDec.doubleValue(), 1.7976931348623157E308);
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(double)</b> with a
	 * double = 4.9E-324 , the returning value after calling the DoubleValue
	 * method should be equal to the double value parameter
	 */
	public void testValueOfDouble003() {
		BigDecimal bigDec = BigDecimal.valueOf(4.9E-324);
		assertEquals(msgNotSame, bigDec.doubleValue(), 4.9E-324);
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(double)</b> with a
	 * double = -1.438171560950583E229 , the returning value after calling the
	 * DoubleValue method should be equal to the double value parameter
	 */
	public void testValueOfDouble004() {
		BigDecimal bigDec = BigDecimal.valueOf(-1.438171560950583E229);
		assertEquals(msgNotSame, bigDec.doubleValue(), -1.438171560950583E229);
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(double)</b> with a
	 * double = 2.80232001989931E178 , the returning value after calling the
	 * DoubleValue method should be equal to the double value parameter
	 */
	public void testValueOfDouble005() {
		BigDecimal bigDec = BigDecimal.valueOf(2.80232001989931E178);
		assertEquals(msgNotSame, bigDec.doubleValue(), 2.80232001989931E178);
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(double)</b> with a
	 * double = -5.151986048021127E257 , the returning value after calling the
	 * DoubleValue method should be equal to the double value parameter
	 */
	public void testValueOfDouble006() {
		BigDecimal bigDec = BigDecimal.valueOf(-5.151986048021127E257);
		assertEquals(msgNotSame, bigDec.doubleValue(), -5.151986048021127E257);
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(double)</b> with a
	 * double = 1.9556358865869468E108 , the returning value after calling the
	 * DoubleValue method should be equal to the double value parameter
	 */
	public void testValueOfDouble007() {
		BigDecimal bigDec = BigDecimal.valueOf(1.9556358865869468E108);
		assertEquals(msgNotSame, bigDec.doubleValue(), 1.9556358865869468E108);
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(double)</b> with a
	 * double = -1.277555892046705E99 , the returning value after calling the
	 * DoubleValue method should be equal to the double value parameter
	 */
	public void testValueOfDouble008() {
		BigDecimal bigDec = BigDecimal.valueOf(-1.277555892046705E99);
		assertEquals(msgNotSame, bigDec.doubleValue(), -1.277555892046705E99);
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(double)</b> with a
	 * double = -3.28450422437506E38 , the returning value after calling the
	 * DoubleValue method should be equal to the double value parameter
	 */
	public void testValueOfDouble009() {
		BigDecimal bigDec = BigDecimal.valueOf(-3.28450422437506E38);
		assertEquals(msgNotSame, bigDec.doubleValue(), -3.28450422437506E38);
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(double)</b> with a
	 * double = -2.6920823552616215E58 , the returning value after calling the
	 * DoubleValue method should be equal to the double value parameter
	 */
	public void testValueOfDouble010() {
		BigDecimal bigDec = BigDecimal.valueOf(-2.6920823552616215E58);
		assertEquals(msgNotSame, bigDec.doubleValue(), -2.6920823552616215E58);
	}

	/**
	 * This method creates a BigDecimal with <b>ValueOf(double)</b> with a
	 * double = -4.7849864084890048E17 , the returning value after calling the
	 * DoubleValue method should be equal to the double value parameter
	 */
	public void testValueOfDouble011() {
		BigDecimal bigDec = BigDecimal.valueOf(-4.7849864084890048E17);
		assertEquals(msgNotSame, bigDec.doubleValue(), -4.7849864084890048E17);
	}

	/**
	 * This method try to create a BigDecimal with <b>ValueOf(double)</b> with
	 * a double = NaN , should raise a <i>NumberFormatException</i>
	 */
	public void testValueOfDouble012() {
		try {
			BigDecimal.valueOf(Double.NaN);
			fail(msgRaise + "NumberFormatException");
		} catch (NumberFormatException e) {
		}
	}

	/**
	 * This method try to create a BigDecimal with <b>ValueOf(double)</b> with
	 * a double = POSITIVE_INFINITY , should raise a <i>NumberFormatException</i>
	 */
	public void testValueOfDouble013() {
		try {
			BigDecimal.valueOf(Double.POSITIVE_INFINITY);
			fail(msgRaise + "NumberFormatException");
		} catch (NumberFormatException e) {
		}
	}

	/**
	 * This method try to create a BigDecimal with <b>ValueOf(double)</b> with
	 * a double = NEGATIVE_INFINITY , should raise a <i>NumberFormatException</i>
	 */
	public void testValueOfDouble014() {
		try {
			BigDecimal.valueOf(Double.NEGATIVE_INFINITY);
			fail(msgRaise + "NumberFormatException");
		} catch (NumberFormatException e) {
		}
	}

}
