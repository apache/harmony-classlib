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
 * clearBit(int), 
 * flipBit(int),
 * setBit(int) 
 * 
 */
public class TestClearSetFlipBit extends TestCase implements Messages {

	private BigInteger bi = null;

	/** Creates a new instance of TestClearSetFlipBit */
	public TestClearSetFlipBit(String name) {
		super(name);
	}

	public static void main(String args[]) {
		junit.textui.TestRunner.run(TestClearSetFlipBit.class);
	}

	/*
	 * Test method for 'java.math.bigInteger.setBit(int)'
	 */
	public void testSetBit001() {
		try {
			bi = new BigInteger("0");
			int n = 0;
			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit002() {
		try {
			bi = new BigInteger("0");
			int n = 6;
			BigInteger bi2 = new BigInteger("64");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit003() {
		try {
			bi = new BigInteger("0");
			int n = 10;
			BigInteger bi2 = new BigInteger("1024");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit004() {
		try {
			bi = new BigInteger("0");
			int n = 17;
			BigInteger bi2 = new BigInteger("131072");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit005() {
		try {
			bi = new BigInteger("0");
			int n = 35;
			BigInteger bi2 = new BigInteger("34359738368");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit006() {
		try {
			bi = new BigInteger("0");
			int n = 99;
			BigInteger bi2 = new BigInteger("633825300114114700748351602688");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit007() {
		try {
			bi = new BigInteger("0");
			int n = 256;
			BigInteger bi2 = new BigInteger(
					"115792089237316195423570985008687907853269984665640564039457584007913129639936");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit008() {
		try {
			bi = new BigInteger("0");
			int n = -1;
			bi.setBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit009() {
		try {
			bi = new BigInteger("0");
			int n = -256;
			bi.setBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit010() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int n = 0;
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168107");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit011() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int n = 6;
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit012() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int n = 10;
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit013() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int n = 17;
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904037036");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit014() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int n = 35;
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit015() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int n = 99;
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400001989216596295941789000552565420");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit016() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int n = 256;
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit017() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int n = -1;
			bi.setBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit018() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int n = -256;
			bi.setBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit019() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int n = 0;
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit020() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int n = 6;
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit021() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int n = 10;
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456088923");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit022() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int n = 17;
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456218971");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit023() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int n = 35;
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit024() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int n = 99;
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit025() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int n = 256;
			BigInteger bi2 = new BigInteger(
					"115792089237317214365131474110811095333459688717534618903523692797173585727835");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit026() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int n = -1;
			bi.setBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit027() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int n = -256;
			bi.setBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit028() {
		try {
			bi = new BigInteger("21474836455");
			int n = 0;
			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit029() {
		try {
			bi = new BigInteger("21474836455");
			int n = 6;
			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit030() {
		try {
			bi = new BigInteger("21474836455");
			int n = 10;
			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit031() {
		try {
			bi = new BigInteger("21474836455");
			int n = 17;
			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit032() {
		try {
			bi = new BigInteger("21474836455");
			int n = 35;
			BigInteger bi2 = new BigInteger("55834574823");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit033() {
		try {
			bi = new BigInteger("21474836455");
			int n = 99;
			BigInteger bi2 = new BigInteger("633825300114114700769826439143");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit034() {
		try {
			bi = new BigInteger("21474836455");
			int n = 256;
			BigInteger bi2 = new BigInteger(
					"115792089237316195423570985008687907853269984665640564039457584007934604476391");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit035() {
		try {
			bi = new BigInteger("21474836455");
			int n = -1;
			bi.setBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit036() {
		try {
			bi = new BigInteger("21474836455");
			int n = -256;
			bi.setBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit037() {
		try {
			bi = new BigInteger("-2147483646");
			int n = 0;
			BigInteger bi2 = new BigInteger("-2147483645");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit038() {
		try {
			bi = new BigInteger("-2147483646");
			int n = 6;
			BigInteger bi2 = new BigInteger("-2147483582");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit039() {
		try {
			bi = new BigInteger("-2147483646");
			int n = 10;
			BigInteger bi2 = new BigInteger("-2147482622");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit040() {
		try {
			bi = new BigInteger("-2147483646");
			int n = 17;
			BigInteger bi2 = new BigInteger("-2147352574");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit041() {
		try {
			bi = new BigInteger("-2147483646");
			int n = 35;
			BigInteger bi2 = new BigInteger("-2147483646");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit042() {
		try {
			bi = new BigInteger("-2147483646");
			int n = 99;
			BigInteger bi2 = new BigInteger("-2147483646");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit043() {
		try {
			bi = new BigInteger("-2147483646");
			int n = 256;
			BigInteger bi2 = new BigInteger("-2147483646");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit044() {
		try {
			bi = new BigInteger("-2147483646");
			int n = -1;
			bi.setBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit045() {
		try {
			bi = new BigInteger("-2147483646");
			int n = -256;
			bi.setBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit046() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int n = 0;
			BigInteger bi2 = new BigInteger("999999999997777773151875");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit047() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int n = 6;
			BigInteger bi2 = new BigInteger("999999999997777773151938");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit048() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int n = 10;
			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit049() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int n = 17;
			BigInteger bi2 = new BigInteger("999999999997777773282946");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit050() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int n = 35;
			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit051() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int n = 99;
			BigInteger bi2 = new BigInteger("633826300114114698526124754562");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit052() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int n = 256;
			BigInteger bi2 = new BigInteger(
					"115792089237316195423570985008687907853269984665640565039457584005690902791810");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit053() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int n = -1;
			bi.setBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit054() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int n = -256;
			bi.setBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit055() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int n = 0;
			BigInteger bi2 = new BigInteger("-999999999997777773151873");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit056() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int n = 6;
			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit057() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int n = 10;
			BigInteger bi2 = new BigInteger("-999999999997777773150850");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit058() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int n = 17;
			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit059() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int n = 35;
			BigInteger bi2 = new BigInteger("-999999999997743413413506");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit060() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int n = 99;
			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit061() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int n = 256;
			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			assertEquals(bi.setBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit062() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int n = -1;
			bi.setBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSetBit063() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int n = -256;
			bi.setBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	/*
	 * Test method for 'java.math.bigInteger.clearBit(int)'
	 */
	public void testClearBit001() {
		try {
			bi = new BigInteger("0");
			int n = 0;
			BigInteger bi2 = new BigInteger("0");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit002() {
		try {
			bi = new BigInteger("0");
			int n = 6;
			BigInteger bi2 = new BigInteger("0");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit003() {
		try {
			bi = new BigInteger("0");
			int n = 10;
			BigInteger bi2 = new BigInteger("0");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit004() {
		try {
			bi = new BigInteger("0");
			int n = 17;
			BigInteger bi2 = new BigInteger("0");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit005() {
		try {
			bi = new BigInteger("0");
			int n = 35;
			BigInteger bi2 = new BigInteger("0");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit006() {
		try {
			bi = new BigInteger("0");
			int n = 99;
			BigInteger bi2 = new BigInteger("0");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit007() {
		try {
			bi = new BigInteger("0");
			int n = 256;
			BigInteger bi2 = new BigInteger("0");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit008() {
		try {
			bi = new BigInteger("0");
			int n = -1;
			bi.clearBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit009() {
		try {
			bi = new BigInteger("0");
			int n = -256;
			bi.clearBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit010() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int n = 0;
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit011() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int n = 6;
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168172");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit012() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int n = 10;
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904169132");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit013() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int n = 17;
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit014() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int n = 35;
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489783263906476");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit015() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int n = 99;
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit016() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int n = 256;
			BigInteger bi2 = new BigInteger(
					"-115792089237316345872181296872989097313459384668263605935867640497662033808044");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit017() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int n = -1;
			bi.clearBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit018() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int n = -256;
			bi.clearBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit019() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int n = 0;
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087898");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit020() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int n = 6;
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087835");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit021() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int n = 10;
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit022() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int n = 17;
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit023() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int n = 35;
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789226096349531");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit024() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int n = 99;
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051260229563951994088512104485211");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit025() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int n = 256;
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit026() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int n = -1;
			bi.clearBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit027() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int n = -256;
			bi.clearBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit028() {
		try {
			bi = new BigInteger("21474836455");
			int n = 0;
			BigInteger bi2 = new BigInteger("21474836454");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit029() {
		try {
			bi = new BigInteger("21474836455");
			int n = 6;
			BigInteger bi2 = new BigInteger("21474836391");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit030() {
		try {
			bi = new BigInteger("21474836455");
			int n = 10;
			BigInteger bi2 = new BigInteger("21474835431");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit031() {
		try {
			bi = new BigInteger("21474836455");
			int n = 17;
			BigInteger bi2 = new BigInteger("21474705383");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit032() {
		try {
			bi = new BigInteger("21474836455");
			int n = 35;
			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit033() {
		try {
			bi = new BigInteger("21474836455");
			int n = 99;
			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit034() {
		try {
			bi = new BigInteger("21474836455");
			int n = 256;
			BigInteger bi2 = new BigInteger("21474836455");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit035() {
		try {
			bi = new BigInteger("21474836455");
			int n = -1;
			bi.clearBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit036() {
		try {
			bi = new BigInteger("21474836455");
			int n = -256;
			bi.clearBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit037() {
		try {
			bi = new BigInteger("-2147483646");
			int n = 0;
			BigInteger bi2 = new BigInteger("-2147483646");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit038() {
		try {
			bi = new BigInteger("-2147483646");
			int n = 6;
			BigInteger bi2 = new BigInteger("-2147483646");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit039() {
		try {
			bi = new BigInteger("-2147483646");
			int n = 10;
			BigInteger bi2 = new BigInteger("-2147483646");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit040() {
		try {
			bi = new BigInteger("-2147483646");
			int n = 17;
			BigInteger bi2 = new BigInteger("-2147483646");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit041() {
		try {
			bi = new BigInteger("-2147483646");
			int n = 35;
			BigInteger bi2 = new BigInteger("-36507222014");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit042() {
		try {
			bi = new BigInteger("-2147483646");
			int n = 99;
			BigInteger bi2 = new BigInteger("-633825300114114700750499086334");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit043() {
		try {
			bi = new BigInteger("-2147483646");
			int n = 256;
			BigInteger bi2 = new BigInteger(
					"-115792089237316195423570985008687907853269984665640564039457584007915277123582");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit044() {
		try {
			bi = new BigInteger("-2147483646");
			int n = -1;
			bi.clearBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit045() {
		try {
			bi = new BigInteger("-2147483646");
			int n = -256;
			bi.clearBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit046() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int n = 0;
			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit047() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int n = 6;
			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit048() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int n = 10;
			BigInteger bi2 = new BigInteger("999999999997777773150850");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit049() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int n = 17;
			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit050() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int n = 35;
			BigInteger bi2 = new BigInteger("999999999997743413413506");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit051() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int n = 99;
			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit052() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int n = 256;
			BigInteger bi2 = new BigInteger("999999999997777773151874");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit053() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int n = -1;
			bi.clearBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit054() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int n = -256;
			bi.clearBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit055() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int n = 0;
			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit056() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int n = 6;
			BigInteger bi2 = new BigInteger("-999999999997777773151938");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit057() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int n = 10;
			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit058() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int n = 17;
			BigInteger bi2 = new BigInteger("-999999999997777773282946");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit059() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int n = 35;
			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit060() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int n = 99;
			BigInteger bi2 = new BigInteger("-633826300114114698526124754562");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit061() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int n = 256;
			BigInteger bi2 = new BigInteger(
					"-115792089237316195423570985008687907853269984665640565039457584005690902791810");
			assertEquals(bi.clearBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit062() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int n = -1;
			bi.clearBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testClearBit063() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int n = -256;
			bi.clearBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	/*
	 * Test method for 'java.math.bigInteger.flipBit(int)'
	 */
	public void testFlipBit001() {
		try {
			bi = new BigInteger("0");
			int n = 0;
			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit002() {
		try {
			bi = new BigInteger("0");
			int n = 6;
			BigInteger bi2 = new BigInteger("64");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit003() {
		try {
			bi = new BigInteger("0");
			int n = 10;
			BigInteger bi2 = new BigInteger("1024");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit004() {
		try {
			bi = new BigInteger("0");
			int n = 17;
			BigInteger bi2 = new BigInteger("131072");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit005() {
		try {
			bi = new BigInteger("0");
			int n = 35;
			BigInteger bi2 = new BigInteger("34359738368");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit006() {
		try {
			bi = new BigInteger("0");
			int n = 99;
			BigInteger bi2 = new BigInteger("633825300114114700748351602688");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit007() {
		try {
			bi = new BigInteger("0");
			int n = 256;
			BigInteger bi2 = new BigInteger(
					"115792089237316195423570985008687907853269984665640564039457584007913129639936");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit008() {
		try {
			bi = new BigInteger("0");
			int n = -1;
			bi.flipBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit009() {
		try {
			bi = new BigInteger("0");
			int n = -256;
			bi.flipBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit010() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int n = 0;
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168107");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit011() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int n = 6;
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168172");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit012() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int n = 10;
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904169132");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit013() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int n = 17;
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904037036");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit014() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int n = 35;
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489783263906476");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit015() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int n = 99;
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400001989216596295941789000552565420");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit016() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int n = 256;
			BigInteger bi2 = new BigInteger(
					"-115792089237316345872181296872989097313459384668263605935867640497662033808044");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit017() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int n = -1;
			bi.flipBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit018() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int n = -256;
			bi.flipBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit019() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int n = 0;
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087898");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit020() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int n = 6;
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087835");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit021() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int n = 10;
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456088923");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit022() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int n = 17;
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456218971");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit023() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int n = 35;
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789226096349531");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit024() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int n = 99;
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051260229563951994088512104485211");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit025() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int n = 256;
			BigInteger bi2 = new BigInteger(
					"115792089237317214365131474110811095333459688717534618903523692797173585727835");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit026() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int n = -1;
			bi.flipBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit027() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int n = -256;
			bi.flipBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit028() {
		try {
			bi = new BigInteger("21474836455");
			int n = 0;
			BigInteger bi2 = new BigInteger("21474836454");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit029() {
		try {
			bi = new BigInteger("21474836455");
			int n = 6;
			BigInteger bi2 = new BigInteger("21474836391");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit030() {
		try {
			bi = new BigInteger("21474836455");
			int n = 10;
			BigInteger bi2 = new BigInteger("21474835431");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit031() {
		try {
			bi = new BigInteger("21474836455");
			int n = 17;
			BigInteger bi2 = new BigInteger("21474705383");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit032() {
		try {
			bi = new BigInteger("21474836455");
			int n = 35;
			BigInteger bi2 = new BigInteger("55834574823");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit033() {
		try {
			bi = new BigInteger("21474836455");
			int n = 99;
			BigInteger bi2 = new BigInteger("633825300114114700769826439143");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit034() {
		try {
			bi = new BigInteger("21474836455");
			int n = 256;
			BigInteger bi2 = new BigInteger(
					"115792089237316195423570985008687907853269984665640564039457584007934604476391");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit035() {
		try {
			bi = new BigInteger("21474836455");
			int n = -1;
			bi.flipBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit036() {
		try {
			bi = new BigInteger("21474836455");
			int n = -256;
			bi.flipBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit037() {
		try {
			bi = new BigInteger("-2147483646");
			int n = 0;
			BigInteger bi2 = new BigInteger("-2147483645");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit038() {
		try {
			bi = new BigInteger("-2147483646");
			int n = 6;
			BigInteger bi2 = new BigInteger("-2147483582");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit039() {
		try {
			bi = new BigInteger("-2147483646");
			int n = 10;
			BigInteger bi2 = new BigInteger("-2147482622");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit040() {
		try {
			bi = new BigInteger("-2147483646");
			int n = 17;
			BigInteger bi2 = new BigInteger("-2147352574");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit041() {
		try {
			bi = new BigInteger("-2147483646");
			int n = 35;
			BigInteger bi2 = new BigInteger("-36507222014");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit042() {
		try {
			bi = new BigInteger("-2147483646");
			int n = 99;
			BigInteger bi2 = new BigInteger("-633825300114114700750499086334");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit043() {
		try {
			bi = new BigInteger("-2147483646");
			int n = 256;
			BigInteger bi2 = new BigInteger(
					"-115792089237316195423570985008687907853269984665640564039457584007915277123582");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit044() {
		try {
			bi = new BigInteger("-2147483646");
			int n = -1;
			bi.flipBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit045() {
		try {
			bi = new BigInteger("-2147483646");
			int n = -256;
			bi.flipBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit046() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int n = 0;
			BigInteger bi2 = new BigInteger("999999999997777773151875");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit047() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int n = 6;
			BigInteger bi2 = new BigInteger("999999999997777773151938");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit048() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int n = 10;
			BigInteger bi2 = new BigInteger("999999999997777773150850");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit049() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int n = 17;
			BigInteger bi2 = new BigInteger("999999999997777773282946");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit050() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int n = 35;
			BigInteger bi2 = new BigInteger("999999999997743413413506");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit051() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int n = 99;
			BigInteger bi2 = new BigInteger("633826300114114698526124754562");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit052() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int n = 256;
			BigInteger bi2 = new BigInteger(
					"115792089237316195423570985008687907853269984665640565039457584005690902791810");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit053() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int n = -1;
			bi.flipBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit054() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int n = -256;
			bi.flipBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit055() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int n = 0;
			BigInteger bi2 = new BigInteger("-999999999997777773151873");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit056() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int n = 6;
			BigInteger bi2 = new BigInteger("-999999999997777773151938");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit057() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int n = 10;
			BigInteger bi2 = new BigInteger("-999999999997777773150850");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit058() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int n = 17;
			BigInteger bi2 = new BigInteger("-999999999997777773282946");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit059() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int n = 35;
			BigInteger bi2 = new BigInteger("-999999999997743413413506");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit060() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int n = 99;
			BigInteger bi2 = new BigInteger("-633826300114114698526124754562");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit061() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int n = 256;
			BigInteger bi2 = new BigInteger(
					"-115792089237316195423570985008687907853269984665640565039457584005690902791810");
			assertEquals(bi.flipBit(n), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit062() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int n = -1;
			bi.flipBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFlipBit063() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int n = -256;
			bi.flipBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

}
