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
package ar.org.fitc.test.math.whitebox;

import java.math.BigDecimal;
import java.math.BigInteger;

import ar.org.fitc.test.util.Messages;
import junit.framework.TestCase;

public class TestBigDecimalCobertura extends TestCase implements Messages {
	BigDecimal bigDec = null;

	public TestBigDecimalCobertura() {
		super();

	}

	public TestBigDecimalCobertura(String name) {
		super(name);

	}

	public void testBigDecimalDouble001() {
		try {
			assertEquals(4.0E-324, new BigDecimal(4.0E-324).doubleValue());
			new BigDecimal(4.0E-324);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigDecimalDouble002() {
		try {
			assertEquals(4.0E-200, new BigDecimal(4.0E-200).doubleValue());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigDecimalDouble003() {
		try {
			assertEquals(8.0E15, new BigDecimal(8.0E15).doubleValue());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigDecimalDouble004() {
		try {
			assertEquals(4.0E15, new BigDecimal(4.0E15).doubleValue());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigDecimalDouble005() {
		try {
			assertEquals(16.0E15, new BigDecimal(16.0E15).doubleValue());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public final void testBigDecimalDouble006() {

		try {
			bigDec = new BigDecimal(Double.NaN);
			fail(msgRaise + "NumberFormatException");
		} catch (NumberFormatException e) {
		} catch (Throwable e) {
			fail(msgRaise + "NumberFormatException");
		}
	}

	public final void testBigDecimalDouble007() {
		try {
			bigDec = new BigDecimal(Double.NEGATIVE_INFINITY);
			fail(msgRaise + "NumberFormatException");
		} catch (NumberFormatException e) {
		} catch (Throwable e) {
			fail(msgRaise + "NumberFormatException");
		}
	}

	public final void testBigDecimalDouble008() {
		try {
			bigDec = new BigDecimal(Double.POSITIVE_INFINITY);
			fail(msgRaise + "NumberFormatException");
		} catch (NumberFormatException e) {
		} catch (Throwable e) {
			fail(msgRaise + "NumberFormatException");
		}
	}

	public void testBigDecimalCharArrayIntInt001() {
		try {
			new BigDecimal("1.0E-2147483648");
			fail("NumberFormatException is expected");
		} catch (NumberFormatException e) {
		}
	}

	public void testBigDecimalCharArrayIntInt002() {
		try {
			new BigDecimal("100.0E-2147483649");
			fail("NumberFormatException is expected");
		} catch (NumberFormatException e) {
		}
	}

	public void testBigDecimalCharArrayIntInt003() {
		try {
			new BigDecimal("1.0E2147483648");
			fail("NumberFormatException is expected");
		} catch (NumberFormatException e) {
		}
	}

	public void testBigDecimalCharArrayIntInt004() {
		try {
			assertEquals("1.0E+2147483647", new BigDecimal("1.0E+2147483647")
					.toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigDecimalCharArrayIntInt005() {
		try {
			assertEquals("1E-2147483647", new BigDecimal("0.01E-2147483645")
					.toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testToBigInteger001() {
		try {
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -2147483648);
			assertEquals(BigInteger.ZERO, bi2.toBigInteger());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testToBigInteger002() {
		try {
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 2147483647);
			assertEquals(BigInteger.ZERO, bi2.toBigInteger());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testToString001() {
		try {
			assertEquals("10.0", new BigDecimal("100E-1").toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testToString002() {
		try {
			assertEquals("1.00E+3", new BigDecimal("100E+1").toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigDecimalToPlainString001() {
		try {
			bigDec = new BigDecimal(
					"+593735153444164875310.15867431586745418967451897411698745641634857");
			assertEquals(
					"593735153444164875310.15867431586745418967451897411698745641634857",
					bigDec.toPlainString());

		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigDecimalToPlainString002() {
		try {
			bigDec = new BigDecimal("00000000000000000000");
			assertEquals("0", bigDec.toPlainString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigDecimalToPlainString003() {
		try {
			bigDec = new BigDecimal(
					"548452489641354857451234864123486745123457865415348974534874566666664587534548974565");
			assertEquals(
					"548452489641354857451234864123486745123457865415348974534874566666664587534548974565",
					bigDec.toPlainString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigDecimalToPlainString004() {

		bigDec = new BigDecimal(BigInteger.ZERO, -10);
		try {
			assertEquals("0", bigDec.toPlainString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigDecimalToPlainString005() {

		bigDec = new BigDecimal(BigInteger.ZERO, Integer.MAX_VALUE);
		try {
			assertEquals("0", bigDec.toPlainString());
			fail("NegativeArraySizeException is expected");
		} catch (NegativeArraySizeException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigDecimalToPlainString006() {

		bigDec = new BigDecimal(BigInteger.ZERO, Integer.MIN_VALUE);
		try {
			assertEquals("0", bigDec.toPlainString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigDecimalToPlainString007() {
		try {
			bigDec = new BigDecimal("+00000000000000000001");
			assertEquals("1", bigDec.toPlainString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigDecimalToPlainString008() {
		try {
			bigDec = new BigDecimal("-00000000000000000001");
			assertEquals("-1", bigDec.toPlainString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigDecimalToPlainString009() {
		try {
			bigDec = new BigDecimal(
					"+54845248964135485745123486412348.6745123457865415348974534874566666664587534548974565");
			assertEquals(
					"54845248964135485745123486412348.6745123457865415348974534874566666664587534548974565",
					bigDec.toPlainString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigDecimalToPlainString010() {
		try {
			bigDec = new BigDecimal(
					"-54845248964135485745123486412348.6745123457865415348974534874566666664587534548974565");
			assertEquals(
					"-54845248964135485745123486412348.6745123457865415348974534874566666664587534548974565",
					bigDec.toPlainString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigDecimalToPlainString011() {
		try {
			bigDec = new BigDecimal("+00000000000000000000");
			assertEquals("0", bigDec.toPlainString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigDecimalToPlainString012() {
		try {
			bigDec = new BigDecimal("-00000000000000000000");
			assertEquals("0", bigDec.toPlainString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigDecimalDivide001() {
		try {
			assertEquals(BigDecimal.ZERO, BigDecimal.ZERO
					.divide(BigDecimal.ONE));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBigDecimalDivide002() {
		try {
			bigDec = new BigDecimal("0");
			assertEquals(bigDec, bigDec.divide(bigDec));
			fail(msgRaise + "ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public final void testBigDecimalBigInteger001() {
		BigInteger bigInt = null;
		try {
			bigDec = new BigDecimal(bigInt);
			System.out.print(bigDec.toString());
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgRaise + "NullPointerException");
		}
	}

	public final void testBigDecimalAdd001() {
		BigInteger bigInt = null;
		try {
			bigDec = new BigDecimal(bigInt);
			System.out.print(bigDec.toString());
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgRaise + "NullPointerException");
		}
	}

	public final void testAddBigDecimal001() {
		try {
			String number = "168";
			String number2 = "596628648";
			bigDec = new BigDecimal(new BigInteger(number), Integer.MIN_VALUE);
			BigDecimal bd = new BigDecimal(new BigInteger(number2), 1);
			bigDec.add(bd);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public final void testAddBigDecimal002() {
		try {
			BigDecimal bd = new BigDecimal(BigInteger.ZERO, 1);
			BigDecimal bd2 = new BigDecimal(BigInteger.ONE, 1);
			bd.add(bd2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public final void testmovePointLeft001() {
		try {
			BigDecimal.ONE.movePointLeft(Integer.MAX_VALUE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public final void testmovePointLeft002() {
		try {
			BigDecimal.ONE.movePointLeft(Integer.MIN_VALUE);
	
		} catch (Throwable e) {
			fail(msgRaise + e);
		}
	}

	public final void testmovePointLeft003() {
		try {
			BigDecimal.ONE.movePointLeft(Integer.MAX_VALUE - 7);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public final void testmovePointLeft004() {
		try {
			BigDecimal.ONE.movePointLeft(Integer.MIN_VALUE + 7);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public final void testmovePointLeft005() {
		try {
			BigDecimal.ZERO.movePointLeft(Integer.MAX_VALUE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public final void testmovePointLeft006() {
		try {
			BigDecimal.ZERO.movePointLeft(Integer.MIN_VALUE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public final void testmovePointLeft007() {
		try {
			BigDecimal.ZERO.movePointLeft(Integer.MAX_VALUE - 7);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public final void testmovePointLeft008() {
		try {
			BigDecimal.ZERO.movePointLeft(Integer.MIN_VALUE + 7);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public final void testmovePointRight001() {
		try {
			BigDecimal.ONE.movePointRight(Integer.MAX_VALUE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public final void testmovePointRight002() {
		try {
			BigDecimal.ONE.movePointRight(Integer.MAX_VALUE - 7);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public final void testmovePointRight003() {
		try {
			BigDecimal.ONE.movePointRight(Integer.MIN_VALUE);
			fail(msgRaise + "ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgRaise + "ArithmeticException");
		}
	}

	public final void testmovePointRight005() {
		try {
			BigDecimal.ZERO.movePointRight(Integer.MAX_VALUE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public final void testmovePointRight006() {
		try {
			BigDecimal.ZERO.movePointRight(Integer.MAX_VALUE - 7);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public final void testmovePointRight007() {
		try {
			BigDecimal.ZERO.movePointRight(Integer.MIN_VALUE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public final void testmovePointRight008() {
		try {
			BigDecimal.ZERO.movePointRight(Integer.MIN_VALUE + 7);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public final void testDivideIntInt001() {
		try {
			BigDecimal bigDec = new BigDecimal("11");
			assertEquals("6", bigDec.divide(new BigDecimal("2"), 0, 6)
					.toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public final void testRemainderBigDecimalMathContext001() {
		try {

			assertEquals("0E+2147483648", new BigDecimal(new BigInteger("-1"),
					Integer.MIN_VALUE).remainder(BigDecimal.ONE).toString());

		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
}