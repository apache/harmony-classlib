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
 * divide(BigInteger), 
 * remainder(BigInteger),
 * divideAndRemainder(BigInteger)
 * 
 */
public class TestDivideAndRemainder extends TestCase implements Messages {

	private BigInteger bi = null;

	private BigInteger bi2 = null;

	/** Creates a new instance of TestDivideAndRemainder */
	public TestDivideAndRemainder(String name) {
		super(name);
	}

	public static void main(String args[]) {
		junit.textui.TestRunner.run(TestDivideAndRemainder.class);
	}

	/*
	 * Test method for 'java.math.BigInteger.divide(BigInteger)'
	 */
	public void testDivide001() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);
			bi.divide(bi);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide002() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.divide(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide003() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("0");
			bi.divide(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide004() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-1");
			assertEquals(bi.divide(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide005() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.divide(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide006() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-2147483649");
			assertEquals(bi.divide(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide007() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.divide(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide008() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			assertEquals(bi.divide(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide009() {
		try {
			bi = new BigInteger("21474836455");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.divide(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide010() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.divide(bi2), BigInteger.ONE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide011() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("0");
			bi.divide(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide012() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-1");
			BigInteger bi3 = new BigInteger("-21474836455");
			assertEquals(bi.divide(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide013() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.divide(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide014() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-2147483649");
			BigInteger bi3 = new BigInteger("-9");
			assertEquals(bi.divide(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide015() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("999999999997777773151874");

			assertEquals(bi.divide(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide016() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");

			assertEquals(bi.divide(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide017() {
		try {
			bi = new BigInteger("0");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.divide(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide018() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.divide(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide019() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("0");
			bi.divide(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide020() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-1");
			assertEquals(bi.divide(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide021() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.divide(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide022() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-2147483649");
			assertEquals(bi.divide(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide023() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.divide(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide024() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			assertEquals(bi.divide(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide025() {
		try {
			bi = new BigInteger("-1");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.divide(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide026() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.divide(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide027() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("0");
			bi.divide(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide028() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-1");
			assertEquals(bi.divide(bi2), BigInteger.ONE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide029() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.divide(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide030() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-2147483649");

			assertEquals(bi.divide(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide031() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.divide(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide032() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			assertEquals(bi.divide(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide033() {
		try {
			bi = new BigInteger("1");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.divide(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide034() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("21474836455");

			assertEquals(bi.divide(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide035() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("0");
			bi.divide(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide036() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-1");
			assertEquals(bi.divide(bi2), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide037() {
		try {
			bi = new BigInteger("1");
			assertEquals(bi.divide(bi), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide038() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-2147483649");
			assertEquals(bi.divide(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide039() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.divide(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide040() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			assertEquals(bi.divide(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide041() {
		try {
			bi = new BigInteger("-2147483649");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.divide(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide042() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.divide(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide043() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("0");
			bi.divide(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide044() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("-1");
			BigInteger bi3 = new BigInteger("2147483649");
			assertEquals(bi.divide(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide045() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.divide(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide046() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("-2147483649");
			assertEquals(bi.divide(bi2), BigInteger.ONE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide047() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.divide(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide048() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			assertEquals(bi.divide(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide049() {
		try {
			bi = new BigInteger("999999999997777773151874");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.divide(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide050() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("46566128784880");
			assertEquals(bi.divide(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide051() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("0");
			bi.divide(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide052() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-1");
			BigInteger bi3 = new BigInteger("-999999999997777773151874");
			assertEquals(bi.divide(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide053() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.divide(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide054() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-2147483649");
			BigInteger bi3 = new BigInteger("-465661287089864");
			assertEquals(bi.divide(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide055() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.divide(bi2), BigInteger.ONE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide056() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			BigInteger bi3 = new BigInteger("-1");
			assertEquals(bi.divide(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide057() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.divide(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide058() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("-46566128726569");
			assertEquals(bi.divide(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide059() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("0");
			bi.divide(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide060() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-1");
			BigInteger bi3 = new BigInteger("999999998745555544448974");
			assertEquals(bi.divide(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide061() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.divide(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide062() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-2147483649");
			BigInteger bi3 = new BigInteger("465661286506752");
			assertEquals(bi.divide(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide063() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.divide(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivide064() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			assertEquals(bi.divide(bi2), BigInteger.ONE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	/*
	 * Test method for 'java.math.BigInteger.remainder(BigInteger)'
	 */
	public void testRemainder001() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);
			bi.remainder(bi);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder002() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.remainder(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder003() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("0");
			bi.remainder(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder004() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-1");
			assertEquals(bi.remainder(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder005() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.remainder(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder006() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-2147483649");
			assertEquals(bi.remainder(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder007() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.remainder(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder008() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			assertEquals(bi.remainder(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder009() {
		try {
			bi = new BigInteger("21474836455");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.remainder(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder010() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.remainder(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder011() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("0");
			bi.remainder(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder012() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-1");
			assertEquals(bi.remainder(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder013() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.remainder(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder014() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-2147483649");

			BigInteger bi3 = new BigInteger("2147483614");
			assertEquals(bi.remainder(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder015() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("999999999997777773151874");

			assertEquals(bi.remainder(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder016() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			assertEquals(bi.remainder(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder017() {
		try {
			bi = new BigInteger("0");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.remainder(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder018() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.remainder(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder019() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("0");
			bi.remainder(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder020() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-1");

			assertEquals(bi.remainder(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder021() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.remainder(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder022() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-2147483649");
			assertEquals(bi.remainder(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder023() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.remainder(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder024() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			assertEquals(bi.remainder(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder025() {
		try {
			bi = new BigInteger("-1");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.remainder(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder026() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.remainder(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder027() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("0");
			bi.remainder(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder028() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-1");
			assertEquals(bi.remainder(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder029() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.remainder(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder030() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-2147483649");
			assertEquals(bi.remainder(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder031() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.remainder(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder032() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			assertEquals(bi.remainder(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder033() {
		try {
			bi = new BigInteger("1");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.remainder(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder034() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.remainder(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder035() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("0");
			bi.remainder(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder036() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-1");
			assertEquals(bi.remainder(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder037() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.remainder(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder038() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-2147483649");
			assertEquals(bi.remainder(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder039() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.remainder(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder040() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			assertEquals(bi.remainder(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder041() {
		try {
			bi = new BigInteger("-2147483649");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.remainder(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder042() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.remainder(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder043() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("0");
			bi.remainder(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder044() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("-1");
			assertEquals(bi.remainder(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder045() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.remainder(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder046() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("-2147483649");
			assertEquals(bi.remainder(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder047() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("999999999997777773151874");

			assertEquals(bi.remainder(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder048() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");

			
			assertEquals(bi.remainder(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder049() {
		try {
			bi = new BigInteger("999999999997777773151874");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.remainder(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder050() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("11896351474");
			assertEquals(bi.remainder(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder051() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("0");
			bi.remainder(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder052() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-1");
			assertEquals(bi.remainder(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder053() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.remainder(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder054() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-2147483649");
			BigInteger bi3 = new BigInteger("39518138");
			assertEquals(bi.remainder(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder055() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.remainder(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder056() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			BigInteger bi3 = new BigInteger("1252222228702900");
			assertEquals(bi.remainder(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder057() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			byte[] val = new byte[1];
			bi2 = new BigInteger(val);
			bi.remainder(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder058() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("-8856176079");
			assertEquals(bi.remainder(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder059() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("0");
			bi.remainder(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder060() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-1");
			assertEquals(bi.remainder(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder061() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("1");

			assertEquals(bi.remainder(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder062() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-2147483649");
			BigInteger bi3 = new BigInteger("-1296350926");
			assertEquals(bi.remainder(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder063() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.remainder(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testRemainder064() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			assertEquals(bi.remainder(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	/*
	 * Test method for 'java.math.BigInteger.divideAndRemainder(BigInteger)'
	 */
	public void testDivideAndRemainder001() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger(val);
			bi.divideAndRemainder(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder002() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("21474836455");

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder003() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("0");
			bi.divideAndRemainder(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder004() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-1");

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder005() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("1");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = bi;
			bi3[1] = bi;

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder006() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-2147483649");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = bi;
			bi3[1] = bi;

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder007() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = bi;
			bi3[1] = bi;

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder008() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = bi;
			bi3[1] = bi;

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder009() {
		try {
			bi = new BigInteger("21474836455");

			byte[] val = new byte[1];
			BigInteger bi2 = new BigInteger(val);
			bi.divideAndRemainder(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder010() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger[] bi3 = new BigInteger[2];
			bi3[0] = BigInteger.ONE;
			bi3[1] = BigInteger.ZERO;

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi3[i]);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder011() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("0");
			bi.divideAndRemainder(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder012() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-1");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = new BigInteger("-21474836455");
			bi3[1] = BigInteger.ZERO;

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi3[i]);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder013() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("1");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = new BigInteger("21474836455");
			bi3[1] = BigInteger.ZERO;

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi3[i]);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder014() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-2147483649");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = new BigInteger("-9");
			bi3[1] = new BigInteger("2147483614");

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi3[i]);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder015() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = BigInteger.ZERO;
			bi3[1] = bi;

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi3[i]);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder016() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = BigInteger.ZERO;
			bi3[1] = bi;

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi3[i]);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder017() {
		try {
			bi = new BigInteger("0");

			byte[] val = new byte[1];
			BigInteger bi2 = new BigInteger(val);
			bi.divideAndRemainder(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder018() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("21474836455");

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], BigInteger.ZERO);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder019() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("0");
			bi.divideAndRemainder(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder020() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-1");

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], BigInteger.ZERO);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder021() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("1");

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], BigInteger.ZERO);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder022() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-2147483649");

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], BigInteger.ZERO);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder023() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("999999999997777773151874");

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], BigInteger.ZERO);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder024() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], BigInteger.ZERO);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder025() {
		try {
			bi = new BigInteger("-1");

			byte[] val = new byte[1];
			BigInteger bi2 = new BigInteger(val);
			bi.divideAndRemainder(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder026() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = BigInteger.ZERO;
			bi3[1] = bi;

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi3[i]);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder027() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("0");
			bi.divideAndRemainder(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder028() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-1");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = BigInteger.ONE;
			bi3[1] = BigInteger.ZERO;

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi3[i]);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder029() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("1");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = bi;
			bi3[1] = BigInteger.ZERO;

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi3[i]);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder030() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-2147483649");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = BigInteger.ZERO;
			bi3[1] = bi;

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi3[i]);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder031() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = BigInteger.ZERO;
			bi3[1] = bi;

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi3[i]);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder032() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = BigInteger.ZERO;
			bi3[1] = bi;

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi3[i]);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder033() {
		try {
			bi = new BigInteger("1");

			byte[] val = new byte[1];
			BigInteger bi2 = new BigInteger(val);
			bi.divideAndRemainder(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder034() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = BigInteger.ZERO;
			bi3[1] = bi;

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi3[i]);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder035() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("0");
			bi.divideAndRemainder(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder036() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-1");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = bi2;
			bi3[1] = BigInteger.ZERO;

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi3[i]);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder037() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("1");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = bi;
			bi3[1] = BigInteger.ZERO;

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi3[i]);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder038() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-2147483649");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = BigInteger.ZERO;
			bi3[1] = bi;

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi3[i]);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder039() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = BigInteger.ZERO;
			bi3[1] = bi;

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi3[i]);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder040() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = BigInteger.ZERO;
			bi3[1] = bi;

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi3[i]);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder041() {
		try {
			bi = new BigInteger("-2147483649");

			byte[] val = new byte[1];
			BigInteger bi2 = new BigInteger(val);
			bi.divideAndRemainder(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder042() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = BigInteger.ZERO;
			bi3[1] = bi;

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi3[i]);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder043() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("0");
			bi.divideAndRemainder(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder044() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("-1");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = new BigInteger("2147483649");
			bi3[1] = BigInteger.ZERO;

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi3[i]);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder045() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("1");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = new BigInteger("-2147483649");
			bi3[1] = BigInteger.ZERO;

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi3[i]);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder046() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("-2147483649");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = BigInteger.ONE;
			bi3[1] = BigInteger.ZERO;

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi3[i]);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder047() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = BigInteger.ZERO;
			bi3[1] = bi;

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi3[i]);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder048() {
		try {
			bi = new BigInteger("-2147483649");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = BigInteger.ZERO;
			bi3[1] = bi;

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi3[i]);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder049() {
		try {
			bi = new BigInteger("999999999997777773151874");

			byte[] val = new byte[1];
			BigInteger bi2 = new BigInteger(val);
			bi.divideAndRemainder(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder050() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = new BigInteger("46566128784880");
			bi3[1] = new BigInteger("11896351474");

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi3[i]);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder051() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("0");
			bi.divideAndRemainder(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder052() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-1");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = new BigInteger("-999999999997777773151874");
			bi3[1] = BigInteger.ZERO;

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi3[i]);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder053() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("1");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = bi;
			bi3[1] = BigInteger.ZERO;

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi3[i]);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder054() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-2147483649");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = new BigInteger("-465661287089864");
			bi3[1] = new BigInteger("39518138");

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi3[i]);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder055() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = BigInteger.ONE;
			bi3[1] = BigInteger.ZERO;

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi3[i]);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder056() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = new BigInteger("-1");
			bi3[1] = new BigInteger("1252222228702900");

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi3[i]);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder057() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			byte[] val = new byte[1];
			BigInteger bi2 = new BigInteger(val);
			bi.divideAndRemainder(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder058() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = new BigInteger("-46566128726569");
			bi3[1] = new BigInteger("-8856176079");

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi3[i]);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder059() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("0");
			bi.divideAndRemainder(bi2);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder060() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-1");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = new BigInteger("999999998745555544448974");
			bi3[1] = BigInteger.ZERO;

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi3[i]);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder061() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("1");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = bi;
			bi3[1] = BigInteger.ZERO;

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi3[i]);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder062() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-2147483649");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = new BigInteger("465661286506752");
			bi3[1] = new BigInteger("-1296350926");

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi3[i]);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder063() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = BigInteger.ZERO;
			bi3[1] = bi;

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi3[i]);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDivideAndRemainder064() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			BigInteger[] bi3 = new BigInteger[3];
			bi3[0] = BigInteger.ONE;
			bi3[1] = BigInteger.ZERO;

			for (int i = 0; i < 2; i++) {
				assertEquals(bi.divideAndRemainder(bi2)[i], bi3[i]);
			}
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

}
