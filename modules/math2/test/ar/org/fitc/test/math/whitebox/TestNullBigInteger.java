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

import java.math.BigInteger;
import java.util.Random;

import ar.org.fitc.test.util.Messages;

import junit.framework.TestCase;
/**
 * This class verifies that a NullPointerException is thrown when null is passed as a parameter in BigInteger methods.  
 * 
 *
 */
public class TestNullBigInteger extends TestCase implements Messages {
	int radix;

	String valString = null;

	private BigInteger bi = null;

	private BigInteger bi2 = null;

	public TestNullBigInteger(String args) {
		super(args);
	}

	public static void main(String args[]) {
		junit.textui.TestRunner.run(TestNullBigInteger.class);
	}

	public void testNullAnd001() {
		try {
			bi = new BigInteger("0");
			bi.and(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullAnd002() {
		try {
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			bi2.and(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullAnd003() {
		try {

			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");

			bi2.and(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullAnd004() {
		try {

			BigInteger bi2 = new BigInteger("21474836455");
			bi2.and(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullAnd005() {
		try {

			BigInteger bi2 = new BigInteger("-2147483646");
			bi2.and(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullAnd006() {
		try {

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			bi2.and(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullAnd007() {
		try {

			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			bi2.and(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	/*
	 * Test method for 'java.math.bigInteger.or(bigInteger)'
	 */
	public void testNullOr001() {
		try {
			bi = new BigInteger("0");

			bi.or(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullOr002() {
		try {

			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			bi2.or(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullOr003() {
		try {

			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			bi2.or(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullOr004() {
		try {

			BigInteger bi2 = new BigInteger("21474836455");
			bi2.or(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullOr005() {
		try {

			BigInteger bi2 = new BigInteger("-2147483646");
			bi2.or(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullOr006() {
		try {

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			bi2.or(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullOr007() {
		try {

			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			bi2.or(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	/*
	 * Test method for 'java.math.bigInteger.xor(bigInteger)'
	 */
	public void testNullXor001() {
		try {

			BigInteger bi2 = new BigInteger("0");
			bi2.or(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullXor002() {
		try {

			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			bi2.or(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullXor003() {
		try {

			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			bi2.or(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullXor004() {
		try {

			BigInteger bi2 = new BigInteger("21474836455");
			bi2.or(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullXor005() {
		try {

			BigInteger bi2 = new BigInteger("-2147483646");
			bi2.or(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullXor006() {
		try {

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			bi2.or(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullXor007() {
		try {

			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			bi2.or(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	/*
	 * Test method for 'java.math.bigInteger.andNot(bigInteger)'
	 */
	public void testNullAndNot001() {
		try {
			BigInteger bi2 = new BigInteger("0");
			bi2.or(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullAndNot002() {
		try {

			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			bi2.or(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullAndNot003() {
		try {

			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			bi2.or(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullAndNot004() {
		try {

			BigInteger bi2 = new BigInteger("21474836455");
			bi2.or(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullAndNot005() {
		try {

			BigInteger bi2 = new BigInteger("-2147483646");
			bi2.or(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullAndNot006() {
		try {

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			bi2.or(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullAndNot007() {
		try {

			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			bi2.or(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullConstructorString001() {
		try {
			String s = null;
			new BigInteger(s);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullConstructorStringInt001() {
		try {
			String s = null;
			new BigInteger(s, 3);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullConstructorByteArray001() {
		try {
			byte[] s = null;
			new BigInteger(s);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullConstructorIntByteArray001() {
		try {
			byte[] s = null;
			new BigInteger(1, s);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullConstructorIntByteArray002() {
		try {
			byte[] s = null;
			new BigInteger(0, s);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullConstructorIntByteArray003() {
		try {
			byte[] s = null;
			new BigInteger(-1, s);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullConstructorIntRandom001() {
		try {
			Random r = null;
			new BigInteger(0, r);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail("Failed with:" + e);

		}
	}

	public void testNullConstructorIntRandom002() {
		try {
			Random r = null;
			new BigInteger(1, r);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {

			fail("Failed with:" + e);

		}
	}

	public void testNullConstructorIntRandom003() {
		try {
			Random r = null;
			new BigInteger(9999, r);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail("Failed with:" + e);

		}
	}

	public void testNullConstructorIntIntRandom001() {
		try {

			new BigInteger(2, 0, null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {

			fail(msgNullPointer);
		}
	}

	public void testNullConstructorIntIntRandom002() {
		try {

			new BigInteger(129, 100, null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullConstructorIntIntRandom003() {
		try {

			new BigInteger(129, -100, null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullAdd001() {
		try {
			radix = 2;
			valString = "-010101010101011111111111111111110000000000000111111111000000001111111111100000000000001111100000000111111110000000000000000001111111111111100000000";
			bi = new BigInteger(valString, radix);
			bi.add(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullAdd002() {
		try {
			bi = new BigInteger("1111111111111111111111111111111", 2);
			bi.add(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullAdd003() {
		try {
			bi = new BigInteger("0", 2);
			bi.add(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullAdd004() {
		try {
			BigInteger.ZERO.add(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}
	public void testNullAdd005() {
		try {
			bi = new BigInteger("1111111111", 2);
			bi.add(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullAdd006() {
		try {

			BigInteger bi2 = new BigInteger("55555555555555555555555", 16);
			bi2.add(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullAdd007() {
		try {
			bi = new BigInteger("55555555555555555555555", 36);
			bi.add(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullAdd008() {
		try {

			BigInteger bi2 = new BigInteger("yreashtuiywwxzyq", 36);
			bi2.add(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullAdd009() {
		try {

			BigInteger bi2 = new BigInteger("-aaaaajyrwgfsauiy", 36);
			bi2.add(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullAdd010() {
		try {
			bi = new BigInteger("99999999999999999999999999998");
			bi.add(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullAdd011() {
		try {
			bi = new BigInteger("-fadcee", 16);
			bi.add(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullAdd012() {
		try {
			bi = new BigInteger("-999999845004844981047841", 16);
			bi.add(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullAdd013() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);
			bi.add(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	/*
	 * Test method for 'java.math.BigInteger.subtract(BigInteger)'
	 */
	public void testNullSubtract001() {
		try {
			radix = 2;
			valString = "-010101010101011111111111111111110000000000000111111111000000001111111111100000000000001111100000000111111110000000000000000001111111111111100000000";
			bi = new BigInteger(valString, radix);
			bi.subtract(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullSubtract002() {
		try {
			bi = new BigInteger("1111111111111111111111111111111", 2);
			bi.subtract(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullSubtract003() {
		try {
			bi = new BigInteger("0", 2);
			bi.subtract(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullSubtract005() {
		try {
			bi = new BigInteger("1111111111", 2);
			bi.subtract(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullSubtract006() {
		try {

			BigInteger bi2 = new BigInteger("55555555555555555555555", 16);
			bi2.subtract(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullSubtract007() {
		try {
			bi = new BigInteger("55555555555555555555555", 36);
			bi.subtract(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullSubtract008() {
		try {

			BigInteger bi2 = new BigInteger("yreashtuiywwxzyq", 36);
			bi2.subtract(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullSubtract009() {
		try {

			BigInteger bi2 = new BigInteger("-aaaaajyrwgfsauiy", 36);
			bi2.subtract(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullSubtract010() {
		try {
			bi = new BigInteger("99999999999999999999999999998");
			bi.subtract(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullSubtract011() {
		try {
			bi = new BigInteger("-fadcee", 16);
			bi.subtract(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullSubtract012() {
		try {
			bi = new BigInteger("-999999845004844981047841", 16);
			bi.subtract(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullSubtract013() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);
			bi.subtract(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMultiply001() {
		try {

			BigInteger bi2 = new BigInteger("21474836455");
			bi2.multiply(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMultiply002() {
		try {

			BigInteger bi2 = new BigInteger("0");
			bi2.multiply(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMultiply003() {
		try {

			BigInteger bi2 = new BigInteger("-1");
			bi2.multiply(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMultiply004() {
		try {

			BigInteger bi2 = new BigInteger("1");
			bi2.multiply(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMultiply005() {
		try {

			BigInteger bi2 = new BigInteger("-2147483649");
			bi2.multiply(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMultiply006() {
		try {

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			bi2.multiply(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMultiply007() {
		try {

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi2.multiply(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMod001() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			bi.mod(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMod002() {
		try {

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.mod(bi2), bi);
			bi2.mod(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMod003() {
		try {

			BigInteger bi2 = new BigInteger("21474836455");
			bi2.mod(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMod004() {
		try {

			BigInteger bi2 = new BigInteger("-2147483649");
			bi2.mod(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMod005() {
		try {

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			bi2.mod(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullModPow002() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);
			bi.modPow(null, null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullModPow003() {
		try {

			BigInteger bi2 = new BigInteger("0");
			bi2.modPow(null, BigInteger.ONE);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullModPow004() {
		try {

			BigInteger bi2 = new BigInteger("-1");
			bi2.modPow(BigInteger.ONE, null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullModPow005() {
		try {

			BigInteger bi2 = new BigInteger("1");
			bi2.modPow(null, null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullModPow006() {
		try {

			BigInteger bi2 = new BigInteger("-2147483649");
			bi2.modPow(null, null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullModPow007() {
		try {

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			bi2.modPow(null, BigInteger.ONE);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullProbablePrime001() {
		try {
			BigInteger.probablePrime(6, null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullProbablePrime002() {
		try {
			BigInteger.probablePrime(999, null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(e.getMessage());
		}
	}

	public void testNullProbablePrime003() {
		try {
			BigInteger.probablePrime(2, null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMax001() {
		try {

			bi2 = new BigInteger("0");
			bi2.max(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMax002() {
		try {

			bi2 = new BigInteger("1");
			bi2.max(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMax003() {
		try {

			bi2 = new BigInteger("-1");
			bi2.max(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMax004() {
		try {

			bi2 = new BigInteger(
					"-854775804896135189412317861400000487666604786032185406489408870448940548940148940548044804789045409847840948922337203685477580489613518941231786140000048766660478603218540648940887044894054894014894054");
			bi2.max(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMax005() {
		try {

			bi2 = new BigInteger(
					"-854775804896135189412317861400000487666604786032185406489408870448940548940148940548044804789045409847840948922337203685477580489613518941231786140000048766660478603218540648940887044894054894014894055");
			bi2.max(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMax006() {
		try {

			bi2 = new BigInteger(
					"854775804896135189412317861400000487666604786032185406489408870448940548940148940548044804789045409847840948922337203685477580489613518941231786140000048766660478603218540648940887044894054894014894054");
			bi2.max(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMax007() {
		try {

			bi2 = new BigInteger(
					"854775804896135189412317861400000487666604786032185406489408870448940548940148940548044804789045409847840948922337203685477580489613518941231786140000048766660478603218540648940887044894054894014894055");
			bi2.max(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMax008() {
		try {

			bi2 = new BigInteger("21474836455");
			bi2.max(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMax009() {
		try {

			bi2 = new BigInteger("21474836454");
			bi2.max(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMax010() {
		try {

			bi2 = new BigInteger("-2147483646");
			bi2.max(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMax011() {
		try {

			bi2 = new BigInteger("-2147483645");
			bi2.max(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMax012() {
		try {

			bi2 = new BigInteger("999999999997777773151874");
			bi2.max(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMax013() {
		try {

			bi2 = new BigInteger("999999999997777773151873");
			bi2.max(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMax014() {
		try {

			bi2 = new BigInteger("-999999999997777773151874");
			bi2.max(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMax015() {
		try {

			bi2 = new BigInteger("-999999999997777773151875");
			bi2.max(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMin001() {
		try {

			bi2 = new BigInteger("0");
			bi2.min(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMin002() {
		try {

			bi2 = new BigInteger("1");
			bi2.min(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMin003() {
		try {

			bi2 = new BigInteger("-1");
			bi2.min(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMin004() {
		try {

			bi2 = new BigInteger(
					"-854775804896135189412317861400000487666604786032185406489408870448940548940148940548044804789045409847840948922337203685477580489613518941231786140000048766660478603218540648940887044894054894014894054");
			bi2.min(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMin005() {
		try {

			bi2 = new BigInteger(
					"-854775804896135189412317861400000487666604786032185406489408870448940548940148940548044804789045409847840948922337203685477580489613518941231786140000048766660478603218540648940887044894054894014894055");
			bi2.min(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMin006() {
		try {

			bi2 = new BigInteger(
					"854775804896135189412317861400000487666604786032185406489408870448940548940148940548044804789045409847840948922337203685477580489613518941231786140000048766660478603218540648940887044894054894014894054");
			bi2.min(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMin007() {
		try {

			bi2 = new BigInteger(
					"854775804896135189412317861400000487666604786032185406489408870448940548940148940548044804789045409847840948922337203685477580489613518941231786140000048766660478603218540648940887044894054894014894055");
			bi2.min(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMin008() {
		try {

			bi2 = new BigInteger("21474836455");
			bi2.min(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMin009() {
		try {

			bi2 = new BigInteger("21474836454");
			bi2.min(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMin010() {
		try {

			bi2 = new BigInteger("-2147483646");
			bi2.min(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMin011() {
		try {

			bi2 = new BigInteger("-2147483645");
			bi2.min(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMin012() {
		try {

			bi2 = new BigInteger("999999999997777773151874");
			bi2.min(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMin013() {
		try {

			bi2 = new BigInteger("999999999997777773151873");
			bi2.min(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMin014() {
		try {

			bi2 = new BigInteger("-999999999997777773151874");
			bi2.min(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullMin015() {
		try {

			bi2 = new BigInteger("-999999999997777773151875");
			bi2.min(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullGcd001() {
		try {
			BigInteger.ZERO.gcd(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullGcd002() {
		try {
			BigInteger.ONE.gcd(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullGcd003() {
		try {
			new BigInteger("2147483647").gcd(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullGcd004() {
		try {
			bi = new BigInteger("-1").gcd(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullGcd005() {
		try {

			bi2 = new BigInteger("-189411864111111864116").gcd(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullGcd006() {
		try {
			bi = new BigInteger("189411864111111864116").gcd(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullModInverse001() {
		try {
			BigInteger.ZERO.modInverse(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullModInverse002() {
		try {
			BigInteger.ONE.modInverse(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullModInverse003() {
		try {
			new BigInteger("2147483643987539683397").modInverse(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullModInverse004() {
		try {
			new BigInteger("-214738763975483647").modInverse(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullDivide001() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);
			bi.divide(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullDivide002() {
		try {

			BigInteger bi2 = new BigInteger("21474836455");
			bi2.divide(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullDivide004() {
		try {

			BigInteger bi2 = new BigInteger("-1");

			bi2.divide(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullDivide005() {
		try {

			BigInteger.ONE.divide(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullDivide006() {
		try {

			BigInteger bi2 = new BigInteger("-2147483649");
			bi2.divide(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullDivide007() {
		try {

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			bi2.divide(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullDivide008() {
		try {

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi2.divide(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullRemainder001() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);
			bi.remainder(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullRemainder002() {
		try {

			BigInteger bi2 = new BigInteger("21474836455");
			bi2.remainder(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullRemainder003() {
		try {

			BigInteger bi2 = new BigInteger("0");
			bi2.remainder(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullRemainder004() {
		try {

			BigInteger bi2 = new BigInteger("-1");
			bi2.remainder(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullRemainder005() {
		try {

			BigInteger bi2 = new BigInteger("1");
			bi2.remainder(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullRemainder006() {
		try {

			BigInteger bi2 = new BigInteger("-2147483649");
			bi2.remainder(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullRemainder007() {
		try {

			BigInteger bi2 = new BigInteger("999999999997777773151874");
			bi2.remainder(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullRemainder008() {
		try {

			BigInteger bi2 = new BigInteger("-999999998745555544448974");
			bi2.remainder(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullDivideAndRemainder001() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);

			bi.divideAndRemainder(null);

			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullDivideAndRemainder002() {
		try {

			BigInteger bi2 = new BigInteger("21474836455");
			bi2.divideAndRemainder(null);

			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullDivideAndRemainder004() {
		try {

			BigInteger bi2 = new BigInteger("-1");
			bi2.divideAndRemainder(null);

			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullCompareTo001() {
		try {

			bi2 = new BigInteger("0");
			bi2.compareTo(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullCompareTo002() {
		try {

			bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			bi2.compareTo(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullCompareTo003() {
		try {

			bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			bi2.compareTo(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullCompareTo004() {
		try {

			bi2 = new BigInteger("21474836455");
			bi2.compareTo(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullCompareTo005() {
		try {

			bi2 = new BigInteger("-2147483646");
			bi2.compareTo(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullCompareTo006() {
		try {

			bi2 = new BigInteger("999999999997777773151874");
			bi2.compareTo(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullCompareTo007() {
		try {

			bi2 = new BigInteger("-999999999997777773151874");
			bi2.compareTo(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullCompareTo008() {
		try {

			bi2 = new BigInteger(
					"8547758048961351894123178614000004876666047860321854064894088704489405489401489405480448047890454098478409489223372036854775804896135189412317861400000487666604786032185406489408870448940548940148940548044804789045409847840948922337203685477580489613518941231786140000048766660478603218540648940887044894054894014894054804480478904540984784094892233720368547758048961351894123178614000004876666047860321854064894088704489405489401489405480448047890454098478409489223372036854775804896135189412317861400000487666604786032185406489408870448940548940148940548044804789045409847840948922337203685477580489613518941231786140000048766660478603218540648940887044894054894014894054804480478904540984784094892233720368547758048961351894123178614000004876666047860321854064");
			bi2.compareTo(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

	public void testNullCompareTo009() {
		try {

			bi2 = new BigInteger(
					"-8547758048961351894123178614000004876666047860321854064894088704489405489401489405480448047890454098478409489223372036854775804896135189412317861400000487666604786032185406489408870448940548940148940548044804789045409847840948922337203685477580489613518941231786140000048766660478603218540648940887044894054894014894054804480478904540984784094892233720368547758048961351894123178614000004876666047860321854064894088704489405489401489405480448047890454098478409489223372036854775804896135189412317861400000487666604786032185406489408870448940548940148940548044804789045409847840948922337203685477580489613518941231786140000048766660478603218540648940887044894054894014894054804480478904540984784094892233720368547758048961351894123178614000004876666047860321854064");
			bi2.compareTo(null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgNullPointer);
		}
	}

}