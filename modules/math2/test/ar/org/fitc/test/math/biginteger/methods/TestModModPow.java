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

import ar.org.fitc.test.util.Messages;

import junit.framework.TestCase;

/**
 * Test cases for 
 * mod(BigInteger) 
 * modPow(BigInteger, BigInteger)
 * 
 */
public class TestModModPow extends TestCase implements Messages {

	private BigInteger bi = null;

	private BigInteger bi2 = null;

	/** Creates a new instance of TestModModPow */
	public TestModModPow(String name) {
		super(name);
	}

	public static void main(String args[]) {
		junit.textui.TestRunner.run(TestModModPow.class);
	}

	/*
	 * Test method for 'java.math.BigInteger.mod(BigInteger)'
	 */
	public void testMod001() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);
			bi.mod(bi);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod002() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.mod(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod003() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("0");
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod004() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-1");
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod005() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.mod(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod006() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod007() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.mod(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod008() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod009() {
		try {
			bi = new BigInteger("21474836455");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod010() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.mod(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod011() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("0");
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod012() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-1");
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod013() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.mod(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod014() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod015() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("999999999997777773151874");

			assertEquals(bi.mod(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod016() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod017() {
		try {
			bi = new BigInteger("0");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod018() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.mod(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod019() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("0");
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod020() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-1");
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod021() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.mod(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod022() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod023() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.mod(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod024() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod025() {
		try {
			bi = new BigInteger("-1");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod026() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.mod(bi2), new BigInteger("21474836454"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod027() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("0");
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod028() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-1");
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod029() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.mod(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod030() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod031() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.mod(bi2),
					new BigInteger("999999999997777773151873"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod032() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod033() {
		try {
			bi = new BigInteger("1");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod034() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.mod(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod035() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("0");
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod036() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-1");
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod037() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.mod(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod038() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod039() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.mod(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod040() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod041() {
		try {
			bi = new BigInteger("-2147483649");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod042() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.mod(bi2), new BigInteger("19327352806"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod043() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("0");
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod044() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("-1");
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod045() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.mod(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod046() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod047() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("999999999997777773151874");

			assertEquals(bi.mod(bi2),
					new BigInteger("999999999997775625668225"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod048() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod049() {
		try {
			bi = new BigInteger("999999999997777773151874");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod050() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("11896351474");
			assertEquals(bi.mod(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod051() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("0");
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod052() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-1");
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod053() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.mod(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod054() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod055() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.mod(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod056() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod057() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod058() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("12618660376");
			assertEquals(bi.mod(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod059() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("0");
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod060() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-1");
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod061() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("1");

			assertEquals(bi.mod(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod062() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod063() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.mod(bi2), new BigInteger("1252222228702900"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMod064() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.mod(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	/*
	 * Test method for 'java.math.BigInteger.modPow(BigInteger, BigInteger)'
	 */
	public void testModPow001() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);
			bi.modPow(new BigInteger("0"), bi);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow002() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modPow(new BigInteger("0"), bi2), BigInteger.ONE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow003() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow004() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow005() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("0"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow006() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow007() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("0"), bi2), BigInteger.ONE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow008() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow009() {
		try {
			bi = new BigInteger("21474836455");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow010() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modPow(new BigInteger("0"), bi2), BigInteger.ONE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow011() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow012() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow013() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("0"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow014() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow015() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("0"), bi2), BigInteger.ONE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow016() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow017() {
		try {
			bi = new BigInteger("0");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow018() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modPow(new BigInteger("0"), bi2), BigInteger.ONE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow019() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow020() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow021() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("0"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow022() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow023() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("0"), bi2), BigInteger.ONE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow024() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow025() {
		try {
			bi = new BigInteger("-1");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow026() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modPow(new BigInteger("0"), bi2), BigInteger.ONE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow027() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow028() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow029() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("0"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow030() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow031() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("0"), bi2), BigInteger.ONE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow032() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow033() {
		try {
			bi = new BigInteger("1");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow034() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modPow(new BigInteger("0"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow035() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow036() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow037() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("0"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow038() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow039() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("0"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow040() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow041() {
		try {
			bi = new BigInteger("-2147483649");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow042() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modPow(new BigInteger("0"), bi2), BigInteger.ONE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow043() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow044() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow045() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("0"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow046() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow047() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("0"), bi2), BigInteger.ONE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow048() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow049() {
		try {
			bi = new BigInteger("999999999997777773151874");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow050() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("21474836455");

			assertEquals(bi.modPow(new BigInteger("0"), bi2), BigInteger.ONE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow051() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow052() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow053() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("0"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow054() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow055() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("0"), bi2), BigInteger.ONE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow056() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow057() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow058() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modPow(new BigInteger("0"), bi2), BigInteger.ONE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow059() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow060() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow061() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("0"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow062() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow063() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			assertEquals(bi.modPow(new BigInteger("0"), BigInteger.ONE),
					BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow064() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("0"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow101() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);
			bi.modPow(new BigInteger("-1"), bi);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow102() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("21474836455");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow103() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow104() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow105() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("-1"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow106() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow107() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow108() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow109() {
		try {
			bi = new BigInteger("21474836455");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow110() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("21474836455");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow111() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow112() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow113() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("-1"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow114() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow115() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("-1"), bi2), new BigInteger(
					"690326979068351477455255"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow116() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow117() {
		try {
			bi = new BigInteger("0");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow118() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("21474836455");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow119() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow120() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow121() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("-1"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow122() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow123() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow124() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow125() {
		try {
			bi = new BigInteger("-1");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow126() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modPow(new BigInteger("-1"), bi2), new BigInteger(
					"21474836454"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow127() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow128() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow129() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("-1"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow130() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow131() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("-1"), bi2), new BigInteger(
					"999999999997777773151873"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow132() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow133() {
		try {
			bi = new BigInteger("1");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow134() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modPow(new BigInteger("-1"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow135() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow136() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow137() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("-1"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow138() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow139() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("-1"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow140() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow141() {
		try {
			bi = new BigInteger("-2147483649");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow142() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modPow(new BigInteger("-1"), bi2), new BigInteger(
					"11657768361"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow143() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow144() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow145() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("-1"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow146() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow147() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("-1"), bi2), new BigInteger(
					"776888399952745128642687"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow148() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow149() {
		try {
			bi = new BigInteger("999999999997777773151874");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow150() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("6650177479");
			assertEquals(bi.modPow(new BigInteger("-1"), bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow151() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow152() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow153() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("-1"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow154() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow155() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow156() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow157() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow158() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("20537708431");
			assertEquals(bi.modPow(new BigInteger("-1"), bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow159() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow160() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow161() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("-1"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow162() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow163() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow164() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow165() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("21474836455");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow166() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("21474836455");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow167() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("21474836455");
			bi.modPow(new BigInteger("-1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow201() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);
			bi.modPow(new BigInteger("1"), bi);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow202() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modPow(new BigInteger("1"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow203() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow204() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow205() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("1"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow206() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow207() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("1"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow208() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow209() {
		try {
			bi = new BigInteger("21474836455");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow210() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modPow(new BigInteger("1"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow211() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow212() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow213() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("1"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow214() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow215() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("1"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow216() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow217() {
		try {
			bi = new BigInteger("0");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow218() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modPow(new BigInteger("1"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow219() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow220() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow221() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("1"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow222() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow223() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("1"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow224() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow225() {
		try {
			bi = new BigInteger("-1");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow226() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modPow(new BigInteger("1"), bi2), new BigInteger(
					"21474836454"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow227() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow228() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow229() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("1"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow230() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow231() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("1"), bi2), new BigInteger(
					"999999999997777773151873"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow232() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow233() {
		try {
			bi = new BigInteger("1");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow234() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modPow(new BigInteger("1"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow235() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow236() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow237() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("1"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow238() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow239() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("1"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow240() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow241() {
		try {
			bi = new BigInteger("-2147483649");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow242() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modPow(new BigInteger("1"), bi2), new BigInteger(
					"19327352806"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow243() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow244() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow245() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("1"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow246() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow247() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("1"), bi2), new BigInteger(
					"999999999997775625668225"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow248() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow249() {
		try {
			bi = new BigInteger("999999999997777773151874");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow250() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("11896351474");
			assertEquals(bi.modPow(new BigInteger("1"), bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow251() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow252() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow253() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("1"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow254() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow255() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("1"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow256() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow257() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow258() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("12618660376");
			assertEquals(bi.modPow(new BigInteger("1"), bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow259() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow260() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow261() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("1"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow262() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow263() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("1"), bi2), new BigInteger(
					"1252222228702900"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow264() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("1"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow301() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);
			bi.modPow(new BigInteger("2"), bi);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow302() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modPow(new BigInteger("2"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow303() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow304() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow305() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("2"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow306() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow307() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("2"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow308() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow309() {
		try {
			bi = new BigInteger("21474836455");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow310() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modPow(new BigInteger("2"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow311() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow312() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow313() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("2"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow314() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow315() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("2"), bi2), new BigInteger(
					"461168600768996967025"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow316() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow317() {
		try {
			bi = new BigInteger("0");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow318() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modPow(new BigInteger("2"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow319() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow320() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow321() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("2"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow322() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow323() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("2"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow324() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow325() {
		try {
			bi = new BigInteger("-1");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow326() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modPow(new BigInteger("2"), bi2), BigInteger.ONE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow327() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow328() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow329() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("2"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow330() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow331() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("2"), bi2), BigInteger.ONE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow332() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow333() {
		try {
			bi = new BigInteger("1");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow334() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modPow(new BigInteger("2"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow335() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow336() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow337() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("2"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow338() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow339() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("2"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow340() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow341() {
		try {
			bi = new BigInteger("-2147483649");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow342() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modPow(new BigInteger("2"), bi2), new BigInteger(
					"5368709126"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow343() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow344() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow345() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("2"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow346() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow347() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("2"), bi2), new BigInteger(
					"4611686022722355201"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow348() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow349() {
		try {
			bi = new BigInteger("999999999997777773151874");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow350() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("21474836455");

			assertEquals(bi.modPow(new BigInteger("2"), bi2), new BigInteger(
					"2276455971"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow351() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow352() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow353() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("2"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow354() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow355() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("2"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow356() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow357() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow358() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("16597015401");
			assertEquals(bi.modPow(new BigInteger("2"), bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow359() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow360() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow361() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("2"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow362() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow363() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("2"), bi2), new BigInteger(
					"510061142577647940865560"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow364() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("2"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow401() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);
			bi.modPow(new BigInteger("3"), bi);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow402() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modPow(new BigInteger("3"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow403() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow404() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow405() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("3"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow406() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow407() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("3"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow408() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow409() {
		try {
			bi = new BigInteger("21474836455");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow410() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modPow(new BigInteger("3"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow411() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow412() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow413() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("3"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow414() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow415() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("3"), bi2), new BigInteger(
					"279717404969287855699895"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow416() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow417() {
		try {
			bi = new BigInteger("0");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow418() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modPow(new BigInteger("3"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow419() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow420() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow421() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("3"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow422() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow423() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("3"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow424() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow425() {
		try {
			bi = new BigInteger("-1");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow426() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modPow(new BigInteger("3"), bi2), new BigInteger(
					"21474836454"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow427() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow428() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow429() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("3"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow430() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow431() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("3"), bi2), new BigInteger(
					"999999999997777773151873"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow432() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow433() {
		try {
			bi = new BigInteger("1");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow434() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modPow(new BigInteger("3"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow435() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow436() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow437() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("3"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow438() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow439() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("3"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow440() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow441() {
		try {
			bi = new BigInteger("-2147483649");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow442() {
		try {
			bi = new BigInteger("-2147483649");

			assertEquals(bi.modPow(new BigInteger("3"), new BigInteger(
					"11274289096")), new BigInteger("7814452759"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow443() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow444() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow445() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("3"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow446() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow447() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("3"), bi2), new BigInteger(
					"479671859890804378551647"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow448() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow449() {
		try {
			bi = new BigInteger("999999999997777773151874");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow450() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("21474836455");

			assertEquals(bi.modPow(new BigInteger("3"), bi2), new BigInteger(
					"2786335634"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow451() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow452() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow453() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("3"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow454() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow455() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("3"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow456() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow457() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow458() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("21474836455");

			assertEquals(bi.modPow(new BigInteger("3"), bi2), new BigInteger(
					"5814816336"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow459() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow460() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow461() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("3"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow462() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow463() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("3"), bi2), new BigInteger(
					"302917689180903915854374"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow464() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("3"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow501() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);
			bi.modPow(new BigInteger("999"), bi);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow502() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modPow(new BigInteger("999"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow503() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow504() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow505() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("999"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow506() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow507() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("999"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow508() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow509() {
		try {
			bi = new BigInteger("21474836455");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow510() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modPow(new BigInteger("999"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow511() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow512() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow513() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("999"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow514() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow515() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("999"), bi2), new BigInteger(
					"878801664450652925392659"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow516() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow517() {
		try {
			bi = new BigInteger("0");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow518() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modPow(new BigInteger("999"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow519() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow520() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow521() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("999"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow522() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow523() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("999"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow524() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow525() {
		try {
			bi = new BigInteger("-1");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow526() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modPow(new BigInteger("999"), bi2), new BigInteger(
					"21474836454"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow527() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow528() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow529() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("999"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow530() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow531() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("999"), bi2), new BigInteger(
					"999999999997777773151873"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow532() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow533() {
		try {
			bi = new BigInteger("1");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow534() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modPow(new BigInteger("999"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow535() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow536() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow537() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("999"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow538() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow539() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("999"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow540() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow541() {
		try {
			bi = new BigInteger("-2147483649");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow542() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modPow(new BigInteger("999"), bi2), new BigInteger(
					"1969388826"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow543() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow544() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow545() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("999"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow546() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow547() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("999"), bi2), new BigInteger(
					"156768815212193348361111"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow548() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow549() {
		try {
			bi = new BigInteger("999999999997777773151874");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow550() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("18268866119");
			assertEquals(bi.modPow(new BigInteger("999"), bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow551() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow552() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow553() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("999"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow554() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow555() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("999"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow556() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow557() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow558() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("1103359751");
			assertEquals(bi.modPow(new BigInteger("999"), bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow559() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow560() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow561() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("999"), bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow562() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow563() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("999"), bi2), new BigInteger(
					"719629857594738011706880"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow564() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow601() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);
			bi.modPow(new BigInteger("-999"), bi);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow602() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("21474836455");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow603() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow604() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow605() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("-999"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow606() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow607() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow608() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow609() {
		try {
			bi = new BigInteger("21474836455");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow610() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("21474836455");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow611() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow612() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow613() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("-999"), bi2),
					BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow614() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow615() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("-999"), bi2),
					new BigInteger("722853121649864770182671"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow616() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow617() {
		try {
			bi = new BigInteger("0");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow618() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("21474836455");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow619() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow620() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow621() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("-999"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow622() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow623() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow624() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow625() {
		try {
			bi = new BigInteger("-1");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow626() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modPow(new BigInteger("-999"), bi2),
					new BigInteger("21474836454"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow627() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow628() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow629() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("-999"), bi2),
					BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow630() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow631() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("-999"), bi2),
					new BigInteger("999999999997777773151873"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow632() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow633() {
		try {
			bi = new BigInteger("1");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow634() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modPow(new BigInteger("-999"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow635() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow636() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow637() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("-999"), bi2),
					BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow638() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow639() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("-999"), bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow640() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow641() {
		try {
			bi = new BigInteger("-2147483649");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow642() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.modPow(new BigInteger("-999"), bi2),
					new BigInteger("12643733156"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow643() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow644() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow645() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("-999"), bi2),
					BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow646() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow647() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.modPow(new BigInteger("-999"), bi2),
					new BigInteger("812487312952890411086673"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow648() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow649() {
		try {
			bi = new BigInteger("999999999997777773151874");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow650() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("21474836455");

			assertEquals(bi.modPow(new BigInteger("-999"), bi2),
					new BigInteger("14142669184"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow651() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow652() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow653() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("-999"), bi2),
					BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow654() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow655() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow656() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow657() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow658() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("17451222811");
			assertEquals(bi.modPow(new BigInteger("-999"), bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow659() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("0");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow660() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-1");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow661() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.modPow(new BigInteger("-999"), bi2),
					BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow662() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-2147483649");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow663() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testModPow664() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi.modPow(new BigInteger("-999"), bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

}
