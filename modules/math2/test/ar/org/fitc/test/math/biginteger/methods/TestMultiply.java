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
 * multiply(BigInteger) 
 * 
 */
public class TestMultiply extends TestCase implements Messages {

	private BigInteger bi = null;

	private BigInteger bi2 = null;

	/** Creates a new instance of TestMultiply */
	public TestMultiply(String name) {
		super(name);
	}

	public static void main(String args[]) {
		junit.textui.TestRunner.run(TestMultiply.class);
	}

	/*
	 * Test method for 'java.math.BigInteger.multiply(BigInteger)'
	 */
	public void testMultiply001() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);
			bi2 = new BigInteger(val);
			assertEquals(bi.multiply(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply002() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("21474836455");
			bi.multiply(bi2);
			assertEquals(bi.multiply(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply003() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("0");

			assertEquals(bi.multiply(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply004() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-1");
			assertEquals(bi.multiply(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply005() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.multiply(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply006() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-2147483649");

			assertEquals(bi.multiply(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply007() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.multiply(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply008() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			assertEquals(bi.multiply(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply009() {
		try {
			bi = new BigInteger("21474836455");

			byte[] val = new byte[1];
			BigInteger bi2 = new BigInteger(val);
			assertEquals(bi.multiply(bi2), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply010() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("461168600768996967025");
			assertEquals(bi.multiply(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply011() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("0");
			assertEquals(bi.multiply(bi2), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply012() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-1");
			BigInteger bi3 = new BigInteger("-21474836455");
			assertEquals(bi.multiply(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply013() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.multiply(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply014() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-21474836449");

			BigInteger bi3 = new BigInteger("-461168600640147948295");
			assertEquals(bi.multiply(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply015() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("999999999997777773151874");

			BigInteger bi3 = new BigInteger(
					"21474836454952278041870584026766670");
			assertEquals(bi.multiply(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply016() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");

			BigInteger bi3 = new BigInteger(
					"-21474836428061010475160199742547170");
			assertEquals(bi.multiply(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply017() {
		try {
			bi = new BigInteger("0");

			byte[] val = new byte[1];
			BigInteger bi2 = new BigInteger(val);
			assertEquals(bi.multiply(bi2), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply018() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.multiply(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply019() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("0");
			assertEquals(bi.multiply(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply020() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-1");
			assertEquals(bi.multiply(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply021() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.multiply(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply022() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-21474836449");
			assertEquals(bi.multiply(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply023() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.multiply(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply024() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			assertEquals(bi.multiply(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply025() {
		try {
			bi = new BigInteger("-1");

			byte[] val = new byte[1];
			BigInteger bi2 = new BigInteger(val);
			assertEquals(bi.multiply(bi2), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply026() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("-21474836455");
			assertEquals(bi.multiply(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply027() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("0");

			assertEquals(bi.multiply(bi2), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply028() {
		try {
			bi = new BigInteger("-1");

			assertEquals(bi.multiply(bi), bi.pow(2));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply029() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.multiply(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply030() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-21474836449");
			BigInteger bi3 = new BigInteger("21474836449");
			assertEquals(bi.multiply(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply031() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger bi3 = new BigInteger("-999999999997777773151874");
			assertEquals(bi.multiply(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply032() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			BigInteger bi3 = new BigInteger("999999998745555544448974");
			assertEquals(bi.multiply(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply033() {
		try {
			bi = new BigInteger("1");

			byte[] val = new byte[1];
			BigInteger bi2 = new BigInteger(val);
			assertEquals(bi.multiply(bi2), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply034() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.multiply(bi2), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply035() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("0");
			assertEquals(bi.multiply(bi2), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply036() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-1");
			assertEquals(bi.multiply(bi2), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply037() {
		try {
			bi = new BigInteger("1");
			assertEquals(bi.multiply(bi), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply038() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-21474836449");
			assertEquals(bi.multiply(bi2), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply039() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.multiply(bi2), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply040() {
		try {
			bi = new BigInteger("1");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			assertEquals(bi.multiply(bi2), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply041() {
		try {
			bi = new BigInteger("-21474836449");

			byte[] val = new byte[1];
			BigInteger bi2 = new BigInteger(val);
			assertEquals(bi.multiply(bi2), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply042() {
		try {
			bi = new BigInteger("-21474836449");

			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("-461168600640147948295");
			assertEquals(bi.multiply(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply043() {
		try {
			bi = new BigInteger("-21474836449");

			BigInteger bi2 = new BigInteger("0");
			assertEquals(bi.multiply(bi2), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply044() {
		try {
			bi = new BigInteger("-21474836449");

			BigInteger bi2 = new BigInteger("-1");
			BigInteger bi3 = new BigInteger("21474836449");
			assertEquals(bi.multiply(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply045() {
		try {
			bi = new BigInteger("-21474836449");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.multiply(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply046() {
		try {
			bi = new BigInteger("-21474836449");
			assertEquals(bi.multiply(bi), bi.pow(2));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply047() {
		try {
			bi = new BigInteger("-21474836449");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger bi3 = new BigInteger(
					"-21474836448952278041883917387855426");

			assertEquals(bi.multiply(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply048() {
		try {
			bi = new BigInteger("-21474836449");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			BigInteger bi3 = new BigInteger(
					"21474836422061010482686866475853326");

			assertEquals(bi.multiply(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply049() {
		try {
			bi = new BigInteger("999999999997777773151874");

			byte[] val = new byte[1];
			BigInteger bi2 = new BigInteger(val);
			assertEquals(bi.multiply(bi2), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply050() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger(
					"21474836454952278041870584026766670");

			assertEquals(bi.multiply(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply051() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("0");
			assertEquals(bi.multiply(bi2), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply052() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-1");
			BigInteger bi3 = new BigInteger("-999999999997777773151874");
			assertEquals(bi.multiply(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply053() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.multiply(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply054() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-21474836449");
			BigInteger bi3 = new BigInteger(
					"-21474836448952278041883917387855426");

			assertEquals(bi.multiply(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply055() {
		try {
			bi = new BigInteger("999999999997777773151874");
			assertEquals(bi.multiply(bi), bi.pow(2));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply056() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			BigInteger bi3 = new BigInteger(
					"-999999998743333317603635660148608292612545477276");

			assertEquals(bi.multiply(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply057() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			byte[] val = new byte[1];
			BigInteger bi2 = new BigInteger(val);
			assertEquals(bi.multiply(bi2), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply058() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger(
					"-21474836428061010475160199742547170");

			assertEquals(bi.multiply(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply059() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("0");
			assertEquals(bi.multiply(bi2), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply060() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-1");
			BigInteger bi3 = new BigInteger("999999998745555544448974");
			assertEquals(bi.multiply(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply061() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.multiply(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply062() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-21474836449");
			BigInteger bi3 = new BigInteger(
					"21474836422061010482686866475853326");

			assertEquals(bi.multiply(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply063() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger bi3 = new BigInteger(
					"-999999998743333317603635660148608292612545477276");

			assertEquals(bi.multiply(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testMultiply064() {
		try {
			bi = new BigInteger("-999999998745555544448974");

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			assertEquals(bi.multiply(bi2), bi.pow(2));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

}
