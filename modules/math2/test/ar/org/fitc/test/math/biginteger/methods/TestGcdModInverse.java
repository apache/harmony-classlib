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

package ar.org.fitc.test.math.biginteger.methods;

import java.math.BigInteger;

import junit.framework.TestCase;

import ar.org.fitc.test.util.Messages;

/**
 * Test cases for 
 * gcd(BigInteger), 
 * modInverse(BigInteger)  
 *  
 */
public class TestGcdModInverse extends TestCase implements Messages {

	private BigInteger bi = null;

	private BigInteger bi2 = null;

	/** Creates a new instance of TestGcdModInverse */
	public TestGcdModInverse(String name) {
		super(name);
	}

	public static void main(String args[]) {
		junit.textui.TestRunner.run(TestGcdModInverse.class);
	}

	/*
	 * Test method for 'java.math.BigInteger.gcd(BigInteger)'
	 */
	public void testGcd001() {
		try {
			assertEquals(BigInteger.ZERO.gcd(BigInteger.ZERO), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testGcd002() {
		try {
			assertEquals(BigInteger.ZERO.gcd(BigInteger.TEN), BigInteger.TEN);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testGcd003() {
		try {
			assertEquals(BigInteger.ZERO.gcd(BigInteger.ONE), BigInteger.ONE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testGcd004() {
		try {
			bi = new BigInteger("-1");
			assertEquals(BigInteger.ZERO.gcd(bi), BigInteger.ONE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testGcd005() {
		try {
			bi = new BigInteger("-189411864111111864116");
			bi2 = new BigInteger("189411864111111864116");
			assertEquals(BigInteger.ZERO.gcd(bi), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testGcd006() {
		try {
			bi = new BigInteger("189411864111111864116");
			assertEquals(BigInteger.ZERO.gcd(bi), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testGcd007() {
		try {
			assertEquals(BigInteger.ONE.gcd(BigInteger.ZERO), BigInteger.ONE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testGcd008() {
		try {
			assertEquals(BigInteger.ONE.gcd(BigInteger.TEN), BigInteger.ONE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testGcd009() {
		try {
			assertEquals(BigInteger.ONE.gcd(BigInteger.ONE), BigInteger.ONE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testGcd010() {
		try {
			bi = new BigInteger("-1");
			assertEquals(BigInteger.ONE.gcd(bi), BigInteger.ONE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testGcd011() {
		try {
			bi = new BigInteger("-189411864111111864116");
			assertEquals(BigInteger.ONE.gcd(bi), BigInteger.ONE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testGcd012() {
		try {
			bi = new BigInteger("189411864111111864116");
			assertEquals(BigInteger.ONE.gcd(bi), BigInteger.ONE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testGcd013() {
		try {
			assertEquals(BigInteger.TEN.gcd(BigInteger.ZERO), BigInteger.TEN);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testGcd014() {
		try {
			assertEquals(BigInteger.ONE.gcd(BigInteger.ZERO), BigInteger.ONE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testGcd015() {
		try {
			bi = new BigInteger("-1");
			assertEquals(bi.gcd(BigInteger.ZERO), BigInteger.ONE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testGcd016() {
		try {
			bi = new BigInteger("-189411864111111864116");
			bi2 = new BigInteger("189411864111111864116");
			assertEquals(bi.gcd(BigInteger.ZERO), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testGcd017() {
		try {
			bi = new BigInteger("189411864111111864116");
			assertEquals(bi.gcd(BigInteger.ZERO), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testGcd018() {
		try {
			assertEquals(BigInteger.ZERO.gcd(BigInteger.ONE), BigInteger.ONE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testGcd019() {
		try {
			assertEquals(BigInteger.TEN.gcd(BigInteger.ONE), BigInteger.ONE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testGcd020() {
		try {
			bi = new BigInteger("-1");
			assertEquals(bi.gcd(BigInteger.ONE), BigInteger.ONE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testGcd021() {
		try {
			bi = new BigInteger("-189411864111111864116");
			assertEquals(bi.gcd(BigInteger.ONE), BigInteger.ONE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testGcd022() {
		try {
			bi = new BigInteger("189411864111111864116");
			assertEquals(bi.gcd(BigInteger.ONE), BigInteger.ONE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testGcd023() {
		try {
			bi = new BigInteger("4787904871411346712828305388");

			BigInteger bi2 = new BigInteger("-456949132047050867496");
			BigInteger bi3 = new BigInteger("685423698756");
			assertEquals(bi.gcd(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testGcd024() {
		try {
			bi = new BigInteger("-693696366696663336969632151427500816");

			BigInteger bi2 = new BigInteger("1234567890321654987147258369");
			BigInteger bi3 = new BigInteger("3");

			assertEquals(bi.gcd(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testGcd025() {
		try {
			bi = new BigInteger("-2521702249563911520067687");

			BigInteger bi2 = new BigInteger(
					"-37997320365054483000310561594431305158206224386051294260323");
			BigInteger bi3 = new BigInteger("209");
			assertEquals(bi.gcd(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	/*
	 * Test method for 'java.math.bigInteger.modInverse(bigInteger)'
	 */
	public void testModInverse001() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);
			bi.modInverse(bi);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse002() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("21474836455");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse003() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("0");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse004() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-1");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse005() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modInverse(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse006() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse007() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse008() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse009() {
		try {
			bi = new BigInteger("21474836455");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse010() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("21474836455");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse011() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("0");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse012() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-1");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse013() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modInverse(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse014() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse015() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modInverse(bi2), new BigInteger(
					"690326979068351477455255"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse016() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse017() {
		try {
			bi = new BigInteger("0");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse018() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("21474836455");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse019() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("0");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse020() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-1");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse021() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modInverse(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse022() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse023() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse024() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse025() {
		try {
			bi = new BigInteger("-1");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse026() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modInverse(bi2), new BigInteger("21474836454"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse027() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("0");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse028() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-1");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse029() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modInverse(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse030() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse031() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modInverse(bi2), new BigInteger(
					"999999999997777773151873"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse032() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse033() {
		try {
			bi = new BigInteger("1");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse034() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modInverse(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse035() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("0");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse036() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-1");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse037() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modInverse(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse038() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse039() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modInverse(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse040() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse041() {
		try {
			bi = new BigInteger("-2147483649");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse042() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modInverse(bi2), new BigInteger("11657768361"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse043() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("0");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse044() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("-1");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse045() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modInverse(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse046() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse047() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modInverse(bi2), new BigInteger(
					"776888399952745128642687"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse048() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse049() {
		try {
			bi = new BigInteger("999999999997777773151874");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse050() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("6650177479");
			assertEquals(bi.modInverse(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse051() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("0");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse052() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-1");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse053() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modInverse(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse054() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse055() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse056() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse057() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse058() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("20537708431");
			assertEquals(bi.modInverse(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse059() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("0");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse060() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-1");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse061() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modInverse(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse062() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse063() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse064() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse065() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("21474836455");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse066() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("21474836455");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModInverse067() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("21474836455");
			bi.modInverse(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

}
