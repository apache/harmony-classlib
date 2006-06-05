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
import java.math.MathContext;
import java.math.RoundingMode;

import junit.framework.TestCase;
import ar.org.fitc.test.util.Messages;

public class TestBigDecimalDivideAndRemainder extends TestCase implements
		Messages {

	private BigDecimal bigDec;

	public TestBigDecimalDivideAndRemainder() {
		super();
	}

	public TestBigDecimalDivideAndRemainder(String str) {
		super(str);
	}

	public final void testDivideAndRemainderBigDecimalMathContext001() {
		String number = "5.136152271503443783E+571";
		String number2 = "-132211193758049719790383061606554207965680936592856243856929759054881158247262269165037842087943056969518242405004671660851200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
		MathContext mc = new MathContext("precision=1 roundingMode=UNNECESSARY");
		bigDec = new BigDecimal(number);
		BigDecimal bd = new BigDecimal(number2);
		try {
			bigDec.divideAndRemainder(bd, mc)[0].toString();
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail("Should raise ArithmeticException but raised: " + e);
		}
	}

	public final void testDivideAndRemainderBigDecimalMathContext002() {
		String number = "5136152271503443783E+571";
		String number2 = "150";
		MathContext mc = new MathContext("precision=9999 roundingMode=UP");
		bigDec = new BigDecimal(number);
		BigDecimal bd = new BigDecimal(number2);

		assertEquals(
				msgNotSame,
				"342410151433562918866666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666",
				bigDec.divideAndRemainder(bd, mc)[0].toString());
		assertEquals(msgNotSame, "100", bigDec.divideAndRemainder(bd)[1]
				.toString());
	}

	public final void testDivideToIntegralValueBigDecimalMathContext001() {
		try {
			BigDecimal b1 = new BigDecimal(new BigInteger("22"), -1);
			BigDecimal b2 = new BigDecimal(new BigInteger("20"), 0);
			MathContext mc = new MathContext(1, RoundingMode.HALF_EVEN);
			b1.divideToIntegralValue(b2, mc);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail("Should raise ArithmeticException but raised: " + e);
		}
	}

}
