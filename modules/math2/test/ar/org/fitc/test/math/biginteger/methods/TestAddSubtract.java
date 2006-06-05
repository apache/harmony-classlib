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

import ar.org.fitc.test.util.Messages;
import java.math.BigInteger;

import junit.framework.TestCase;

/**
 * Test cases for 
 * add(BigInteger), 
 * subtract(BigInteger) 
 * 
 */
public class TestAddSubtract extends TestCase implements Messages {

	private BigInteger bi = null;

	private String valString = null;

	private int radix;

	/** Creates a new instance of TestAdd */
	public TestAddSubtract(String name) {
		super(name);
	}

	public static void main(String args[]) {
		junit.textui.TestRunner.run(TestAddSubtract.class);
	}

	/*
	 * Test method for 'java.math.BigInteger.add(BigInteger)'
	 */
	public void testAdd001() {
		radix = 2;
		valString = "-010101010101011111111111111111110000000000000111111111000000001111111111100000000000001111100000000111111110000000000000000001111111111111100000000";
		bi = new BigInteger(valString, radix);
		radix = 16;
		valString = "151874e184186b411167841874411c21384874513f52d41854157484a746";

		BigInteger bi2 = new BigInteger(valString, radix);
		assertEquals(
				"145596021000347458991175548924363955711420062764813560153550645421713478",
				bi.add(bi2).toString());
	}

	public void testAdd002() {
		try {
			bi = new BigInteger("1111111111111111111111111111111", 2);

			BigInteger bi2 = new BigInteger("11100", 2);
			BigInteger bi3 = new BigInteger("10000000000000000000000000011011",
					2);

			assertEquals(bi.add(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAdd003() {
		try {
			bi = new BigInteger("0", 2);

			assertEquals(bi.add(bi), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAdd004() {
		try {
			bi = new BigInteger("-1111111111111111111111111111111", 2);
			bi.add(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testAdd005() {
		try {
			bi = new BigInteger("1111111111", 2);

			BigInteger bi2 = new BigInteger("-abcdeffecda", 16);
			assertEquals(bi.add(bi2), new BigInteger("-11806311442651", 10));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAdd006() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("55555555555555555555555", 16);
			assertEquals(bi.add(bi2), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAdd007() {
		try {
			bi = new BigInteger("55555555555555555555555", 36);

			BigInteger bi2 = new BigInteger("0");
			assertEquals(bi.add(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAdd008() {
		try {
			bi = new BigInteger("1111111111000", 2);

			BigInteger bi2 = new BigInteger("yreashtuiywwxzyq", 36);
			BigInteger bi3 = new BigInteger("7684757988897141159072842");

			assertEquals(bi.add(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAdd009() {
		try {
			bi = new BigInteger("aaaaajyrwgfsauiy", 36);

			BigInteger bi2 = new BigInteger("-aaaaajyrwgfsauiy", 36);
			BigInteger bi3 = new BigInteger("0");
			assertEquals(bi.add(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAdd010() {
		try {
			bi = new BigInteger("99999999999999999999999999998");

			BigInteger bi2 = new BigInteger("-11111111111111111111111111111");
			BigInteger bi3 = new BigInteger("88888888888888888888888888887");
			assertEquals(bi.add(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAdd011() {
		try {
			bi = new BigInteger("-fadcee", 16);

			BigInteger bi2 = new BigInteger("100000000", 2);
			BigInteger bi3 = new BigInteger("-fadbee", 16);

			assertEquals(bi.add(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAdd012() {
		try {
			bi = new BigInteger("-999999845004844981047841", 16);

			BigInteger bi2 = new BigInteger("999999555555551415842333333376",
					16);
			BigInteger bi3 = new BigInteger(
					"797536728806542399361944890467334965");
			assertEquals(bi.add(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAdd013() {
		try {
			byte[] val = new byte[1];

			bi = new BigInteger("-999999845004844981047841", 16);

			BigInteger bi2 = new BigInteger(val);
			assertEquals(bi.add(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAdd014() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("999999555555551415842333333376",
					16);
			assertEquals(bi.add(bi2), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAdd015() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);
			assertEquals(bi.add(bi), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	/*
	 * Test method for 'java.math.BigInteger.subtract(BigInteger)'
	 */
	public void testSubtract001() {
		radix = 2;
		valString = "-010101010101011111111111111111110000000000000111111111000000001111111111100000000000001111100000000111111110000000000000000001111111111111100000000";
		bi = new BigInteger(valString, radix);
		radix = 16;
		valString = "151874e184186b411167841874411c21384874513f52d41854157484a746";

		BigInteger bi2 = new BigInteger(valString, radix);
		assertEquals(
				"-145596021000347458991175549043315782068172051299315928128503520845669958",
				bi.subtract(bi2).toString());

	}

	public void testSubtract002() {
		try {
			bi = new BigInteger("1111111111111111111111111111111", 2);

			BigInteger bi2 = new BigInteger("11100", 2);
			BigInteger bi3 = new BigInteger("2147483619");

			assertEquals(bi.subtract(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtract003() {
		try {
			bi = new BigInteger("0", 2);

			assertEquals(bi.subtract(bi), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtract004() {
		try {
			bi = new BigInteger("-1111111111111111111111111111111", 2);
			assertEquals(bi.subtract(null), bi);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testSubtract005() {
		try {
			bi = new BigInteger("1111111111", 2);

			BigInteger bi2 = new BigInteger("-abcdeffecda", 16);

			assertEquals(bi.subtract(bi2), new BigInteger("11806311444697"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtract006() {
		try {
			bi = new BigInteger("0");

			BigInteger bi2 = new BigInteger("55555555555555555555555", 16);
			BigInteger bi3 = new BigInteger("-55555555555555555555555", 16);
			assertEquals(bi.subtract(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtract007() {
		try {
			bi = new BigInteger("55555555555555555555555", 36);

			BigInteger bi2 = new BigInteger("0");
			assertEquals(bi.subtract(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtract008() {
		try {
			bi = new BigInteger("1111111111000", 2);

			BigInteger bi2 = new BigInteger("yreashtuiywwxzyq", 36);

			BigInteger bi3 = new BigInteger("-7684757988897141159056474");
			assertEquals(bi.subtract(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtract009() {
		try {
			bi = new BigInteger("aaaaajyrwgfsauiy", 36);

			BigInteger bi2 = new BigInteger("0");
			assertEquals(bi.subtract(bi), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtract010() {
		try {
			bi = new BigInteger("99999999999999999999999999998");

			BigInteger bi2 = new BigInteger("-11111111111111111111111111111");
			BigInteger bi3 = new BigInteger("111111111111111111111111111109");
			assertEquals(bi.subtract(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtract011() {
		try {
			bi = new BigInteger("-fadcee", 16);

			BigInteger bi2 = new BigInteger("100000000", 2);
			BigInteger bi3 = new BigInteger("-16440814");
			assertEquals(bi.subtract(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtract012() {
		try {
			bi = new BigInteger("-999999845004844981047841", 16);

			BigInteger bi2 = new BigInteger("999999555555551415842333333376",
					16);

			BigInteger bi3 = new BigInteger(
					"-797536823880336631111563766813666231");
			assertEquals(bi.subtract(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtract013() {
		try {
			byte[] val = new byte[1];

			bi = new BigInteger("-999999845004844981047841", 16);

			BigInteger bi2 = new BigInteger(val);
			assertEquals(bi.subtract(bi2), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtract014() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			BigInteger bi2 = new BigInteger("999999555555551415842333333376",
					16);
			assertEquals(bi.subtract(bi2), new BigInteger(
					"-999999555555551415842333333376", 16));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtract015() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);
			assertEquals(bi.subtract(bi), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

}
