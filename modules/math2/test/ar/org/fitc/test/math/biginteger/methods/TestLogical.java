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
 * Test cases for Or(BigInteger), Xor(BigInteger), And(BigInteger),
 * AndNot(BigInteger), Not()
 * 
 */
public class TestLogical extends TestCase implements Messages {

	private BigInteger bi = null;

	/** Creates a new instance of TestLogical */
	public TestLogical(String args) {
		super(args);
	}

	public static void main(String args[]) {
		junit.textui.TestRunner.run(TestLogical.class);
	}

	/*
	 * Test method for 'java.math.bigInteger.and(bigInteger)'
	 */
	public void testAnd001() {
		try {
			bi = new BigInteger("0");
			BigInteger bi2 = new BigInteger("0");
			BigInteger bi3 = new BigInteger("0");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd002() {
		try {
			bi = new BigInteger("0");
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi3 = new BigInteger("0");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd003() {
		try {
			bi = new BigInteger("0");
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi3 = new BigInteger("0");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd004() {
		try {
			bi = new BigInteger("0");
			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("0");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd005() {
		try {
			bi = new BigInteger("0");
			BigInteger bi2 = new BigInteger("-2147483646");
			BigInteger bi3 = new BigInteger("0");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd006() {
		try {
			bi = new BigInteger("0");
			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger bi3 = new BigInteger("0");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd007() {
		try {
			bi = new BigInteger("0");
			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			BigInteger bi3 = new BigInteger("0");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd008() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi2 = new BigInteger("0");
			BigInteger bi3 = new BigInteger("0");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd009() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi3 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd010() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi3 = new BigInteger(
					"877388204458575327066805722164369465065389201761569783798862160");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd011() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("18252936516");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd012() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi2 = new BigInteger("-2147483646");
			BigInteger bi3 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489749977235456");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd013() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger bi3 = new BigInteger("619849407277561504138240");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd014() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			BigInteger bi3 = new BigInteger(
					"-150448610311864301189460189400002623042516259463767310408306348");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd015() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi2 = new BigInteger("0");
			BigInteger bi3 = new BigInteger("0");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd016() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi3 = new BigInteger(
					"877388204458575327066805722164369465065389201761569783798862160");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd017() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi3 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd018() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("21250756931");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd019() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi2 = new BigInteger("-2147483646");
			BigInteger bi3 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789258532683778");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd020() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger bi3 = new BigInteger("1405334362586890242");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd021() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			BigInteger bi3 = new BigInteger(
					"1018941560489102123187480189704051894054864064703454897869197658");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd022() {
		try {
			bi = new BigInteger("21474836455");
			BigInteger bi2 = new BigInteger("0");
			BigInteger bi3 = new BigInteger("0");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd023() {
		try {
			bi = new BigInteger("21474836455");
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi3 = new BigInteger("18252936516");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd024() {
		try {
			bi = new BigInteger("21474836455");
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi3 = new BigInteger("21250756931");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd025() {
		try {
			bi = new BigInteger("21474836455");
			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("21474836455");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd026() {
		try {
			bi = new BigInteger("21474836455");
			BigInteger bi2 = new BigInteger("-2147483646");
			BigInteger bi3 = new BigInteger("19327352834");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd027() {
		try {
			bi = new BigInteger("21474836455");
			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger bi3 = new BigInteger("972375682");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd028() {
		try {
			bi = new BigInteger("21474836455");
			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			BigInteger bi3 = new BigInteger("20502460774");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd029() {
		try {
			bi = new BigInteger("-2147483646");
			BigInteger bi2 = new BigInteger("0");
			BigInteger bi3 = new BigInteger("0");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd030() {
		try {
			bi = new BigInteger("-2147483646");
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi3 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489749977235456");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd031() {
		try {
			bi = new BigInteger("-2147483646");
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi3 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789258532683778");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd032() {
		try {
			bi = new BigInteger("-2147483646");
			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("19327352834");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd033() {
		try {
			bi = new BigInteger("-2147483646");
			BigInteger bi2 = new BigInteger("-2147483646");
			BigInteger bi3 = new BigInteger("-2147483646");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd034() {
		try {
			bi = new BigInteger("-2147483646");
			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger bi3 = new BigInteger("999999999997776800776194");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd035() {
		try {
			bi = new BigInteger("-2147483646");
			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			BigInteger bi3 = new BigInteger("-999999999997778948259838");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd036() {
		try {
			bi = new BigInteger("999999999997777773151874");
			BigInteger bi2 = new BigInteger("0");
			BigInteger bi3 = new BigInteger("0");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd037() {
		try {
			bi = new BigInteger("999999999997777773151874");
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi3 = new BigInteger("619849407277561504138240");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd038() {
		try {
			bi = new BigInteger("999999999997777773151874");
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi3 = new BigInteger("1405334362586890242");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd039() {
		try {
			bi = new BigInteger("999999999997777773151874");
			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("972375682");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd040() {
		try {
			bi = new BigInteger("999999999997777773151874");
			BigInteger bi2 = new BigInteger("-2147483646");
			BigInteger bi3 = new BigInteger("999999999997776800776194");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd041() {
		try {
			bi = new BigInteger("999999999997777773151874");
			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger bi3 = new BigInteger("999999999997777773151874");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd042() {
		try {
			bi = new BigInteger("999999999997777773151874");
			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			BigInteger bi3 = new BigInteger("2");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd043() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			BigInteger bi2 = new BigInteger("0");
			BigInteger bi3 = new BigInteger("0");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd044() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi3 = new BigInteger(
					"-150448610311864301189460189400002623042516259463767310408306348");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd045() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi3 = new BigInteger(
					"1018941560489102123187480189704051894054864064703454897869197658");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd046() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("20502460774");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd047() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			BigInteger bi2 = new BigInteger("-2147483646");
			BigInteger bi3 = new BigInteger("-999999999997778948259838");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd048() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger bi3 = new BigInteger("2");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAnd049() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			BigInteger bi3 = new BigInteger("-999999999997777773151874");
			assertEquals(bi.and(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	/*
	 * Test method for 'java.math.bigInteger.or(bigInteger)'
	 */
	public void testOr001() {
		try {
			bi = new BigInteger("0");
			BigInteger bi2 = new BigInteger("0");
			BigInteger bi3 = new BigInteger("0");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr002() {
		try {
			bi = new BigInteger("0");
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi3 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr003() {
		try {
			bi = new BigInteger("0");
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi3 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr004() {
		try {
			bi = new BigInteger("0");
			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("21474836455");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr005() {
		try {
			bi = new BigInteger("0");
			BigInteger bi2 = new BigInteger("-2147483646");
			BigInteger bi3 = new BigInteger("-2147483646");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr006() {
		try {
			bi = new BigInteger("0");
			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger bi3 = new BigInteger("999999999997777773151874");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr007() {
		try {
			bi = new BigInteger("0");
			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			BigInteger bi3 = new BigInteger("-999999999997777773151874");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr008() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi2 = new BigInteger("0");
			BigInteger bi3 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr009() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi3 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr010() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi3 = new BigInteger(
					"-8895254281337505068785721860320194052421545709270272246942369");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr011() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489745682268169");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr012() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi2 = new BigInteger("-2147483646");
			BigInteger bi3 = new BigInteger("-1074416298");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr013() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger bi3 = new BigInteger(
					"-150448610311864301189460189400002623041516259463769532635154474");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr014() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			BigInteger bi3 = new BigInteger("-380150592720216269013634");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr015() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi2 = new BigInteger("0");
			BigInteger bi3 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr016() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi3 = new BigInteger(
					"-8895254281337505068785721860320194052421545709270272246942369");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr017() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi3 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr018() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260680167423");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr019() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi2 = new BigInteger("-2147483646");
			BigInteger bi3 = new BigInteger("-224079525");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr020() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger bi3 = new BigInteger(
					"1018941560489102123187480189704051894055864064703452675642349531");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr021() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			BigInteger bi3 = new BigInteger("-999998594663415186261633");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr022() {
		try {
			bi = new BigInteger("21474836455");
			BigInteger bi2 = new BigInteger("0");
			BigInteger bi3 = new BigInteger("21474836455");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr023() {
		try {
			bi = new BigInteger("21474836455");
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi3 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489745682268169");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr024() {
		try {
			bi = new BigInteger("21474836455");
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi3 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260680167423");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr025() {
		try {
			bi = new BigInteger("21474836455");
			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("21474836455");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr026() {
		try {
			bi = new BigInteger("21474836455");
			BigInteger bi2 = new BigInteger("-2147483646");
			BigInteger bi3 = new BigInteger("-25");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr027() {
		try {
			bi = new BigInteger("21474836455");
			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger bi3 = new BigInteger("999999999997798275612647");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr028() {
		try {
			bi = new BigInteger("21474836455");
			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			BigInteger bi3 = new BigInteger("-999999999997776800776193");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr029() {
		try {
			bi = new BigInteger("-2147483646");
			BigInteger bi2 = new BigInteger("0");
			BigInteger bi3 = new BigInteger("-2147483646");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr030() {
		try {
			bi = new BigInteger("-2147483646");
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi3 = new BigInteger("-1074416298");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr031() {
		try {
			bi = new BigInteger("-2147483646");
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi3 = new BigInteger("-224079525");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr032() {
		try {
			bi = new BigInteger("-2147483646");
			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("-25");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr033() {
		try {
			bi = new BigInteger("-2147483646");
			BigInteger bi2 = new BigInteger("-2147483646");
			BigInteger bi3 = new BigInteger("-2147483646");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr034() {
		try {
			bi = new BigInteger("-2147483646");
			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger bi3 = new BigInteger("-1175107966");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr035() {
		try {
			bi = new BigInteger("-2147483646");
			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			BigInteger bi3 = new BigInteger("-972375682");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr036() {
		try {
			bi = new BigInteger("999999999997777773151874");
			BigInteger bi2 = new BigInteger("0");
			BigInteger bi3 = new BigInteger("999999999997777773151874");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr037() {
		try {
			bi = new BigInteger("999999999997777773151874");
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi3 = new BigInteger(
					"-150448610311864301189460189400002623041516259463769532635154474");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr038() {
		try {
			bi = new BigInteger("999999999997777773151874");
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi3 = new BigInteger(
					"1018941560489102123187480189704051894055864064703452675642349531");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr039() {
		try {
			bi = new BigInteger("999999999997777773151874");
			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("999999999997798275612647");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr040() {
		try {
			bi = new BigInteger("999999999997777773151874");
			BigInteger bi2 = new BigInteger("-2147483646");
			BigInteger bi3 = new BigInteger("-1175107966");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr041() {
		try {
			bi = new BigInteger("999999999997777773151874");
			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger bi3 = new BigInteger("999999999997777773151874");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr042() {
		try {
			bi = new BigInteger("999999999997777773151874");
			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			BigInteger bi3 = new BigInteger("-2");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr043() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			BigInteger bi2 = new BigInteger("0");
			BigInteger bi3 = new BigInteger("-999999999997777773151874");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr044() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi3 = new BigInteger("-380150592720216269013634");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr045() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi3 = new BigInteger("-999998594663415186261633");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr046() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("-999999999997776800776193");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr047() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			BigInteger bi2 = new BigInteger("-2147483646");
			BigInteger bi3 = new BigInteger("-972375682");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr048() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger bi3 = new BigInteger("-2");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testOr049() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			BigInteger bi3 = new BigInteger("-999999999997777773151874");
			assertEquals(bi.or(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	/*
	 * Test method for 'java.math.bigInteger.xor(bigInteger)'
	 */
	public void testXor001() {
		try {
			bi = new BigInteger("0");
			BigInteger bi2 = new BigInteger("0");
			BigInteger bi3 = new BigInteger("0");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor002() {
		try {
			bi = new BigInteger("0");
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi3 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor003() {
		try {
			bi = new BigInteger("0");
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi3 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor004() {
		try {
			bi = new BigInteger("0");
			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("21474836455");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor005() {
		try {
			bi = new BigInteger("0");
			BigInteger bi2 = new BigInteger("-2147483646");
			BigInteger bi3 = new BigInteger("-2147483646");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor006() {
		try {
			bi = new BigInteger("0");
			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger bi3 = new BigInteger("999999999997777773151874");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor007() {
		try {
			bi = new BigInteger("0");
			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			BigInteger bi3 = new BigInteger("-999999999997777773151874");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor008() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi2 = new BigInteger("0");
			BigInteger bi3 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor009() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi3 = new BigInteger("0");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor010() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi3 = new BigInteger(
					"-886283458739912832135591444024689659117810747470840056045804529");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor011() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489763935204685");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor012() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi2 = new BigInteger("-2147483646");
			BigInteger bi3 = new BigInteger(
					"150448610311864301189460189400002623041896410056489748902819158");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor013() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger bi3 = new BigInteger(
					"-150448610311864301189460189400002623042136108871047094139292714");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor014() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			BigInteger bi3 = new BigInteger(
					"150448610311864301189460189400002623042136108871047094139292714");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor015() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi2 = new BigInteger("0");
			BigInteger bi3 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor016() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi3 = new BigInteger(
					"-886283458739912832135591444024689659117810747470840056045804529");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor017() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");

			assertEquals(bi.xor(bi2), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor018() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789239429410492");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor019() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi2 = new BigInteger("-2147483646");
			BigInteger bi3 = new BigInteger(
					"-1018941560489102123187480189704051894054864066108789258756763303");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor020() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger bi3 = new BigInteger(
					"1018941560489102123187480189704051894055864063298118313055459289");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor021() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			BigInteger bi3 = new BigInteger(
					"-1018941560489102123187480189704051894055864063298118313055459291");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor022() {
		try {
			bi = new BigInteger("21474836455");
			BigInteger bi2 = new BigInteger("0");
			BigInteger bi3 = new BigInteger("21474836455");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor023() {
		try {
			bi = new BigInteger("21474836455");
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi3 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489763935204685");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor024() {
		try {
			bi = new BigInteger("21474836455");
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi3 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789239429410492");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor025() {
		try {
			bi = new BigInteger("21474836455");
			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("0");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor026() {
		try {
			bi = new BigInteger("21474836455");
			BigInteger bi2 = new BigInteger("-2147483646");
			BigInteger bi3 = new BigInteger("-19327352859");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor027() {
		try {
			bi = new BigInteger("21474836455");
			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger bi3 = new BigInteger("999999999997797303236965");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor028() {
		try {
			bi = new BigInteger("21474836455");
			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			BigInteger bi3 = new BigInteger("-999999999997797303236967");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor029() {
		try {
			bi = new BigInteger("-2147483646");
			BigInteger bi2 = new BigInteger("0");
			BigInteger bi3 = new BigInteger("-2147483646");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor030() {
		try {
			bi = new BigInteger("-2147483646");
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi3 = new BigInteger(
					"150448610311864301189460189400002623041896410056489748902819158");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor031() {
		try {
			bi = new BigInteger("-2147483646");
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi3 = new BigInteger(
					"-1018941560489102123187480189704051894054864066108789258756763303");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor032() {
		try {
			bi = new BigInteger("-2147483646");
			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("-19327352859");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor033() {
		try {
			bi = new BigInteger("-2147483646");
			BigInteger bi2 = new BigInteger("-2147483646");
			BigInteger bi3 = new BigInteger("0");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor034() {
		try {
			bi = new BigInteger("-2147483646");
			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger bi3 = new BigInteger("-999999999997777975884160");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor035() {
		try {
			bi = new BigInteger("-2147483646");
			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			BigInteger bi3 = new BigInteger("999999999997777975884156");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor036() {
		try {
			bi = new BigInteger("999999999997777773151874");
			BigInteger bi2 = new BigInteger("0");
			BigInteger bi3 = new BigInteger("999999999997777773151874");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor037() {
		try {
			bi = new BigInteger("999999999997777773151874");
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi3 = new BigInteger(
					"-150448610311864301189460189400002623042136108871047094139292714");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor038() {
		try {
			bi = new BigInteger("999999999997777773151874");
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi3 = new BigInteger(
					"1018941560489102123187480189704051894055864063298118313055459289");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor039() {
		try {
			bi = new BigInteger("999999999997777773151874");
			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("999999999997797303236965");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor040() {
		try {
			bi = new BigInteger("999999999997777773151874");
			BigInteger bi2 = new BigInteger("-2147483646");
			BigInteger bi3 = new BigInteger("-999999999997777975884160");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor041() {
		try {
			bi = new BigInteger("999999999997777773151874");
			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger bi3 = new BigInteger("0");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor042() {
		try {
			bi = new BigInteger("999999999997777773151874");
			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			BigInteger bi3 = new BigInteger("-4");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor043() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			BigInteger bi2 = new BigInteger("0");
			BigInteger bi3 = new BigInteger("-999999999997777773151874");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor044() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi3 = new BigInteger(
					"150448610311864301189460189400002623042136108871047094139292714");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor045() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi3 = new BigInteger(
					"-1018941560489102123187480189704051894055864063298118313055459291");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor046() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("-999999999997797303236967");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor047() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			BigInteger bi2 = new BigInteger("-2147483646");
			BigInteger bi3 = new BigInteger("999999999997777975884156");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor048() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger bi3 = new BigInteger("-4");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testXor049() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			BigInteger bi3 = new BigInteger("0");
			assertEquals(bi.xor(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	/*
	 * Test method for 'java.math.bigInteger.not()'
	 */
	public void testNot001() {
		try {
			bi = new BigInteger("0");
			BigInteger bi2 = new BigInteger("-1");
			assertEquals(bi.not(), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testNot002() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi2 = new BigInteger(
					"150448610311864301189460189400002623041896410056489748904168107");
			assertEquals(bi.not(), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testNot003() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi2 = new BigInteger(
					"-1018941560489102123187480189704051894054864066108789260456087900");
			assertEquals(bi.not(), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testNot004() {
		try {
			bi = new BigInteger("21474836455");
			BigInteger bi2 = new BigInteger("-21474836456");
			assertEquals(bi.not(), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testNot005() {
		try {
			bi = new BigInteger("-2147483646");
			BigInteger bi2 = new BigInteger("2147483645");
			assertEquals(bi.not(), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testNot006() {
		try {
			bi = new BigInteger("999999999997777773151874");
			BigInteger bi2 = new BigInteger("-999999999997777773151875");
			assertEquals(bi.not(), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testNot007() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			BigInteger bi2 = new BigInteger("999999999997777773151873");
			assertEquals(bi.not(), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	/*
	 * Test method for 'java.math.bigInteger.andNot(bigInteger)'
	 */
	public void testAndNot001() {
		try {
			bi = new BigInteger("0");
			BigInteger bi2 = new BigInteger("0");
			BigInteger bi3 = new BigInteger("0");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot002() {
		try {
			bi = new BigInteger("0");
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi3 = new BigInteger("0");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot003() {
		try {
			bi = new BigInteger("0");
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi3 = new BigInteger("0");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot004() {
		try {
			bi = new BigInteger("0");
			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("0");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot005() {
		try {
			bi = new BigInteger("0");
			BigInteger bi2 = new BigInteger("-2147483646");
			BigInteger bi3 = new BigInteger("0");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot006() {
		try {
			bi = new BigInteger("0");
			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger bi3 = new BigInteger("0");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot007() {
		try {
			bi = new BigInteger("0");
			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			BigInteger bi3 = new BigInteger("0");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot008() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi2 = new BigInteger("0");
			BigInteger bi3 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot009() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi3 = new BigInteger("0");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot010() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi3 = new BigInteger(
					"-1027836814770439628256265911564372088107285611818059532703030268");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot011() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489767157104624");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot012() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi2 = new BigInteger("-2147483646");
			BigInteger bi3 = new BigInteger("1073067348");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot013() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger bi3 = new BigInteger(
					"-150448610311864301189460189400002623042516259463767310408306348");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot014() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			BigInteger bi3 = new BigInteger("619849407277561504138240");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot015() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi2 = new BigInteger("0");
			BigInteger bi3 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot016() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi3 = new BigInteger(
					"141553356030526796120674467539682428989474864347219476657225739");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot017() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi3 = new BigInteger("0");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot018() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789239205330968");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot019() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi2 = new BigInteger("-2147483646");
			BigInteger bi3 = new BigInteger("1923404121");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot020() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger bi3 = new BigInteger(
					"1018941560489102123187480189704051894054864064703454897869197657");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot021() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			BigInteger bi3 = new BigInteger("1405334362586890241");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot022() {
		try {
			bi = new BigInteger("21474836455");
			BigInteger bi2 = new BigInteger("0");
			BigInteger bi3 = new BigInteger("21474836455");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot023() {
		try {
			bi = new BigInteger("21474836455");
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi3 = new BigInteger("3221899939");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot024() {
		try {
			bi = new BigInteger("21474836455");
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi3 = new BigInteger("224079524");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot025() {
		try {
			bi = new BigInteger("21474836455");
			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("0");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot026() {
		try {
			bi = new BigInteger("21474836455");
			BigInteger bi2 = new BigInteger("-2147483646");
			BigInteger bi3 = new BigInteger("2147483621");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot027() {
		try {
			bi = new BigInteger("21474836455");
			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger bi3 = new BigInteger("20502460773");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot028() {
		try {
			bi = new BigInteger("21474836455");
			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			BigInteger bi3 = new BigInteger("972375681");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot029() {
		try {
			bi = new BigInteger("-2147483646");
			BigInteger bi2 = new BigInteger("0");
			BigInteger bi3 = new BigInteger("-2147483646");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot030() {
		try {
			bi = new BigInteger("-2147483646");
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi3 = new BigInteger(
					"150448610311864301189460189400002623041896410056489747829751810");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot031() {
		try {
			bi = new BigInteger("-2147483646");
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi3 = new BigInteger(
					"-1018941560489102123187480189704051894054864066108789260680167424");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot032() {
		try {
			bi = new BigInteger("-2147483646");
			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("-21474836480");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot033() {
		try {
			bi = new BigInteger("-2147483646");
			BigInteger bi2 = new BigInteger("-2147483646");
			BigInteger bi3 = new BigInteger("0");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot034() {
		try {
			bi = new BigInteger("-2147483646");
			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger bi3 = new BigInteger("-999999999997778948259840");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot035() {
		try {
			bi = new BigInteger("-2147483646");
			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			BigInteger bi3 = new BigInteger("999999999997776800776192");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot036() {
		try {
			bi = new BigInteger("999999999997777773151874");
			BigInteger bi2 = new BigInteger("0");
			BigInteger bi3 = new BigInteger("999999999997777773151874");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot037() {
		try {
			bi = new BigInteger("999999999997777773151874");
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi3 = new BigInteger("380150592720216269013634");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot038() {
		try {
			bi = new BigInteger("999999999997777773151874");
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi3 = new BigInteger("999998594663415186261632");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot039() {
		try {
			bi = new BigInteger("999999999997777773151874");
			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("999999999997776800776192");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot040() {
		try {
			bi = new BigInteger("999999999997777773151874");
			BigInteger bi2 = new BigInteger("-2147483646");
			BigInteger bi3 = new BigInteger("972375680");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot041() {
		try {
			bi = new BigInteger("999999999997777773151874");
			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger bi3 = new BigInteger("0");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot042() {
		try {
			bi = new BigInteger("999999999997777773151874");
			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			BigInteger bi3 = new BigInteger("999999999997777773151872");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot043() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			BigInteger bi2 = new BigInteger("0");
			BigInteger bi3 = new BigInteger("-999999999997777773151874");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot044() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			BigInteger bi2 = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			BigInteger bi3 = new BigInteger(
					"150448610311864301189460189400002623041516259463769532635154474");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot045() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			BigInteger bi2 = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			BigInteger bi3 = new BigInteger(
					"-1018941560489102123187480189704051894055864064703452675642349532");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot046() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			BigInteger bi2 = new BigInteger("21474836455");
			BigInteger bi3 = new BigInteger("-999999999997798275612648");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot047() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			BigInteger bi2 = new BigInteger("-2147483646");
			BigInteger bi3 = new BigInteger("1175107964");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot048() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			BigInteger bi2 = new BigInteger("999999999997777773151874");
			BigInteger bi3 = new BigInteger("-999999999997777773151876");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAndNot049() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			BigInteger bi3 = new BigInteger("0");
			assertEquals(bi.andNot(bi2), bi3);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

}
