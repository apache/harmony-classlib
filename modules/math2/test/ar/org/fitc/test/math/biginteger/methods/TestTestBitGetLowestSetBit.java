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
 * testBit(int) 
 * getLowestSetBit() 
 *
 */
public class TestTestBitGetLowestSetBit extends TestCase implements Messages {

	private BigInteger bi = null;

	/** Creates a new instance of TestTestBitGetLowestSetBit */
	public TestTestBitGetLowestSetBit(String name) {
		super(name);
	}

	public static void main(String args[]) {
		junit.textui.TestRunner.run(TestTestBitGetLowestSetBit.class);
	}

	/*
	 * Test method for 'java.math.bigInteger.testBit(int)'
	 */
	public void testTestBit001() {
		try {
			bi = new BigInteger("0");
			int n = 0;
			assertEquals(bi.testBit(n), false);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit002() {
		try {
			bi = new BigInteger("0");
			int n = 6;
			assertEquals(bi.testBit(n), false);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit003() {
		try {
			bi = new BigInteger("0");
			int n = 10;
			assertEquals(bi.testBit(n), false);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit004() {
		try {
			bi = new BigInteger("0");
			int n = 17;
			assertEquals(bi.testBit(n), false);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit005() {
		try {
			bi = new BigInteger("0");
			int n = 35;
			assertEquals(bi.testBit(n), false);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit006() {
		try {
			bi = new BigInteger("0");
			int n = 99;
			assertEquals(bi.testBit(n), false);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit007() {
		try {
			bi = new BigInteger("0");
			int n = 2147483647;
			assertEquals(bi.testBit(n), false);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit008() {
		try {
			bi = new BigInteger("0");
			int n = -1;
			bi.testBit(n);
			fail("Should raise ArithmeticException");
		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit009() {
		try {
			bi = new BigInteger("0");
			int n = -2147483648;
			bi.testBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit010() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int n = 0;
			assertEquals(bi.testBit(n), false);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit011() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int n = 6;
			assertEquals(bi.testBit(n), true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit012() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int n = 10;
			assertEquals(bi.testBit(n), true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit013() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int n = 17;
			assertEquals(bi.testBit(n), false);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit014() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int n = 35;
			assertEquals(bi.testBit(n), true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit015() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int n = 99;
			assertEquals(bi.testBit(n), false);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit016() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int n = 2147483647;
			assertEquals(bi.testBit(n), true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit017() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int n = -1;
			bi.testBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit018() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int n = -2147483648;
			bi.testBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit019() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int n = 0;
			assertEquals(bi.testBit(n), true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit020() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int n = 6;
			assertEquals(bi.testBit(n), true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit021() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int n = 10;
			assertEquals(bi.testBit(n), false);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit022() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int n = 17;
			assertEquals(bi.testBit(n), false);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit023() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int n = 35;
			assertEquals(bi.testBit(n), true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit024() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int n = 99;
			assertEquals(bi.testBit(n), true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit025() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int n = 2147483647;
			assertEquals(bi.testBit(n), false);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit026() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int n = -1;
			bi.testBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit027() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int n = -2147483648;
			bi.testBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit028() {
		try {
			bi = new BigInteger("21474836455");
			int n = 0;
			assertEquals(bi.testBit(n), true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit029() {
		try {
			bi = new BigInteger("21474836455");
			int n = 6;
			assertEquals(bi.testBit(n), true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit030() {
		try {
			bi = new BigInteger("21474836455");
			int n = 10;
			assertEquals(bi.testBit(n), true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit031() {
		try {
			bi = new BigInteger("21474836455");
			int n = 17;
			assertEquals(bi.testBit(n), true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit032() {
		try {
			bi = new BigInteger("21474836455");
			int n = 35;
			assertEquals(bi.testBit(n), false);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit033() {
		try {
			bi = new BigInteger("21474836455");
			int n = 99;
			assertEquals(bi.testBit(n), false);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit034() {
		try {
			bi = new BigInteger("21474836455");
			int n = 2147483647;
			assertEquals(bi.testBit(n), false);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit035() {
		try {
			bi = new BigInteger("21474836455");
			int n = -1;
			bi.testBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit036() {
		try {
			bi = new BigInteger("21474836455");
			int n = -2147483648;
			bi.testBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit037() {
		try {
			bi = new BigInteger("-2147483646");
			int n = 0;
			assertEquals(bi.testBit(n), false);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit038() {
		try {
			bi = new BigInteger("-2147483646");
			int n = 6;
			assertEquals(bi.testBit(n), false);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit039() {
		try {
			bi = new BigInteger("-2147483646");
			int n = 10;
			assertEquals(bi.testBit(n), false);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit040() {
		try {
			bi = new BigInteger("-2147483646");
			int n = 17;
			assertEquals(bi.testBit(n), false);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit041() {
		try {
			bi = new BigInteger("-2147483646");
			int n = 35;
			assertEquals(bi.testBit(n), true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit042() {
		try {
			bi = new BigInteger("-2147483646");
			int n = 99;
			assertEquals(bi.testBit(n), true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit043() {
		try {
			bi = new BigInteger("-2147483646");
			int n = 2147483647;
			assertEquals(bi.testBit(n), true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit044() {
		try {
			bi = new BigInteger("-2147483646");
			int n = -1;
			bi.testBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit045() {
		try {
			bi = new BigInteger("-2147483646");
			int n = -2147483648;
			bi.testBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit046() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int n = 0;
			assertEquals(bi.testBit(n), false);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit047() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int n = 6;
			assertEquals(bi.testBit(n), false);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit048() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int n = 10;
			assertEquals(bi.testBit(n), true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit049() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int n = 17;
			assertEquals(bi.testBit(n), false);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit050() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int n = 35;
			assertEquals(bi.testBit(n), true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit051() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int n = 99;
			assertEquals(bi.testBit(n), false);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit052() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int n = 2147483647;
			assertEquals(bi.testBit(n), false);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit053() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int n = -1;
			bi.testBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit054() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int n = -2147483648;
			bi.testBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit055() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int n = 0;
			assertEquals(bi.testBit(n), false);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit056() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int n = 6;
			assertEquals(bi.testBit(n), true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit057() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int n = 10;
			assertEquals(bi.testBit(n), false);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit058() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int n = 17;
			assertEquals(bi.testBit(n), true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit059() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int n = 35;
			assertEquals(bi.testBit(n), false);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit060() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int n = 99;
			assertEquals(bi.testBit(n), true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit061() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int n = 2147483647;
			assertEquals(bi.testBit(n), true);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit062() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int n = -1;
			bi.testBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testTestBit063() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int n = -2147483648;
			bi.testBit(n);
			fail("Should raise ArithmeticException");

		} catch (ArithmeticException e) {
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	/*
	 * Test method for 'java.math.bigInteger.getLowestSetBit()'
	 */
	public void testGetLowestSetBit001() {
		try {
			bi = new BigInteger("0");
			int lowSet = -1;
			assertEquals(bi.getLowestSetBit(), lowSet);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testGetLowestSetBit002() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int lowSet = 2;
			assertEquals(bi.getLowestSetBit(), lowSet);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testGetLowestSetBit003() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int lowSet = 0;
			assertEquals(bi.getLowestSetBit(), lowSet);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testGetLowestSetBit004() {
		try {
			bi = new BigInteger("21474836455");
			int lowSet = 0;
			assertEquals(bi.getLowestSetBit(), lowSet);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testGetLowestSetBit005() {
		try {
			bi = new BigInteger("-2147483646");
			int lowSet = 1;
			assertEquals(bi.getLowestSetBit(), lowSet);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testGetLowestSetBit006() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int lowSet = 1;
			assertEquals(bi.getLowestSetBit(), lowSet);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testGetLowestSetBit007() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int lowSet = 1;
			assertEquals(bi.getLowestSetBit(), lowSet);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testGetLowestSetBit008() {
		try {
			bi = new BigInteger("30794919706624");
			int lowSet = 22;
			assertEquals(bi.getLowestSetBit(), lowSet);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testGetLowestSetBit009() {
		try {
			bi = new BigInteger("9223512774344179712");
			int lowSet = 20;
			assertEquals(bi.getLowestSetBit(), lowSet);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testGetLowestSetBit010() {
		try {
			bi = new BigInteger("5757952");
			int lowSet = 10;
			assertEquals(bi.getLowestSetBit(), lowSet);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testGetLowestSetBit011() {
		try {
			bi = new BigInteger("2097152");
			int lowSet = 21;
			assertEquals(bi.getLowestSetBit(), lowSet);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testGetLowestSetBit012() {
		try {
			bi = new BigInteger("-9223372036854775808");
			int lowSet = 63;
			assertEquals(bi.getLowestSetBit(), lowSet);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

}
