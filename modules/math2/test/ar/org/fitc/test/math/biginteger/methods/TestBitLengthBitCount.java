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
 * 	bitLength(), 
 *  bitCount() 
 * 
 */
public class TestBitLengthBitCount extends TestCase implements Messages {

	private BigInteger bi = null;

	/** Creates a new instance of TestBitLengthBitCount */
	public TestBitLengthBitCount(String name) {
		super(name);
	}

	public static void main(String args[]) {
		junit.textui.TestRunner.run(TestBitLengthBitCount.class);
	}

	/*
	 * Test method for 'java.math.bigInteger.bitLength()'
	 */
	public void testBitLength001() {
		try {
			bi = new BigInteger("0");
			int length = 0;
			assertEquals(bi.bitLength(), length);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBitLength002() {
		try {
			bi = new BigInteger("-2097152");
			int length = 21;
			assertEquals(bi.bitLength(), length);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBitLength003() {
		try {
			bi = new BigInteger("5757952");
			int length = 23;
			assertEquals(bi.bitLength(), length);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBitLength004() {
		try {
			bi = new BigInteger("21474836455");
			int length = 35;
			assertEquals(bi.bitLength(), length);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBitLength005() {
		try {
			bi = new BigInteger("-2147483646");
			int length = 31;
			assertEquals(bi.bitLength(), length);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBitLength006() {
		try {
			bi = new BigInteger("9223512774344179712");
			int length = 64;
			assertEquals(bi.bitLength(), length);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBitLength007() {
		try {
			bi = new BigInteger("-922337203685477580");
			int length = 60;
			assertEquals(bi.bitLength(), length);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBitLength008() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int length = 80;
			assertEquals(bi.bitLength(), length);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBitLength009() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int length = 80;
			assertEquals(bi.bitLength(), length);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBitLength010() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int length = 207;
			assertEquals(bi.bitLength(), length);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBitLength011() {
		try {
			bi = new BigInteger(
					"1018941560489102123187480189704051894054864066108789260456087899");
			int length = 210;
			assertEquals(bi.bitLength(), length);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBitLength012() {
		try {
			bi = new BigInteger(
					"-8547758048961351894123178614000004876666047860321854064894088704489405489401489405480448047890454098478409489223372036854775804896135189412317861400000487666604786032185406489408870448940548940148940548044804789045409847840948922337203685477580489613518941231786140000048766660478603218540648940887044894054894014894054804480478904540984784094892233720368547758048961351894123178614000004876666047860321854064894088704489405489401489405480448047890454098478409489223372036854775804896135189412317861400000487666604786032185406489408870448940548940148940548044804789045409847840948922337203685477580489613518941231786140000048766660478603218540648940887044894054894014894054804480478904540984784094892233720368547758048961351894123178614000004876666047860321854064894088704489405489401489405480448047890454098478409489223372036854775804896135189412317861400000487666604786032185406489408870448940548940148940548044804789045409847840948922337203685477580489613518941231786140000048766660478603218540648940887044894054894014894054804480478904540984784094892");
			int length = 3502;
			assertEquals(bi.bitLength(), length);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBitLength013() {
		try {
			bi = new BigInteger(
					"8547758048961351894123178614000004876666047860321854064894088704489405489401489405480448047890454098478409489223372036854775804896135189412317861400000487666604786032185406489408870448940548940148940548044804789045409847840948922337203685477580489613518941231786140000048766660478603218540648940887044894054894014894054804480478904540984784094892233720368547758048961351894123178614000004876666047860321854064894088704489405489401489405480448047890454098478409489223372036854775804896135189412317861400000487666604786032185406489408870448940548940148940548044804789045409847840948922337203685477580489613518941231786140000048766660478603218540648940887044894054894014894054804480478904540984784094892233720368547758048961351894123178614000004876666047860321854064894088704489405489401489405480448047890454098478409489223372036854775804896135189412317861400000487666604786032185406489408870448940548940148940548044804789045409847840948922337203685477580489613518941231786140000048766660478603218540648940887044894054894014894054804480478904540984784094892");
			int length = 3502;
			assertEquals(bi.bitLength(), length);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBitLength014() {
		try {
			bi = new BigInteger("00000000000000000000000001");
			int length = 1;
			assertEquals(bi.bitLength(), length);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	/*
	 * Test method for 'java.math.bigInteger.bitCount()'
	 */
	public void testBitCount001() {
		try {
			bi = new BigInteger("0");
			int count = 0;
			assertEquals(bi.bitCount(), count);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBitCount002() {
		try {
			bi = new BigInteger("5757952");
			int count = 10;
			assertEquals(bi.bitCount(), count);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBitCount003() {
		try {
			bi = new BigInteger("-5757952");
			int count = 19;
			assertEquals(bi.bitCount(), count);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBitCount004() {
		try {
			bi = new BigInteger("1229782938247303441");
			int count = 16;
			assertEquals(bi.bitCount(), count);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBitCount005() {
		try {
			bi = new BigInteger("-1229782938247303441");
			int count = 15;
			assertEquals(bi.bitCount(), count);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBitCount006() {
		try {
			bi = new BigInteger(
					"9223512774344179712158610000064510564105651056");
			int count = 68;
			assertEquals(bi.bitCount(), count);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBitCount007() {
		try {
			bi = new BigInteger(
					"-922337203685477580125604650000015860476444649");
			int count = 81;
			assertEquals(bi.bitCount(), count);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBitCount008() {
		try {
			bi = new BigInteger("999999999997777773151874");
			int count = 40;
			assertEquals(bi.bitCount(), count);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBitCount009() {
		try {
			bi = new BigInteger("-999999999997777773151874");
			int count = 40;
			assertEquals(bi.bitCount(), count);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBitCount010() {
		try {
			bi = new BigInteger(
					"150448610311864301189460189400002623041896410056489748904168108");
			int count = 102;
			assertEquals(bi.bitCount(), count);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBitCount011() {
		try {
			bi = new BigInteger(
					"-150448610311864301189460189400002623041896410056489748904168108");
			int count = 103;
			assertEquals(bi.bitCount(), count);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBitCount012() {
		try {
			bi = new BigInteger(
					"8547758048961351894123178614000004876666047860321854064894088704489405489401489405480448047890454098478409489223372036854775804896135189412317861400000487666604786032185406489408870448940548940148940548044804789045409847840948922337203685477580489613518941231786140000048766660478603218540648940887044894054894014894054804480478904540984784094892233720368547758048961351894123178614000004876666047860321854064894088704489405489401489405480448047890454098478409489223372036854775804896135189412317861400000487666604786032185406489408870448940548940148940548044804789045409847840948922337203685477580489613518941231786140000048766660478603218540648940887044894054894014894054804480478904540984784094892233720368547758048961351894123178614000004876666047860321854064894088704489405489401489405480448047890454098478409489223372036854775804896135189412317861400000487666604786032185406489408870448940548940148940548044804789045409847840948922337203685477580489613518941231786140000048766660478603218540648940887044894054894014894054804480478904540984784094892");
			int count = 1753;
			assertEquals(bi.bitCount(), count);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBitCount013() {
		try {
			bi = new BigInteger(
					"-8547758048961351894123178614000004876666047860321854064894088704489405489401489405480448047890454098478409489223372036854775804896135189412317861400000487666604786032185406489408870448940548940148940548044804789045409847840948922337203685477580489613518941231786140000048766660478603218540648940887044894054894014894054804480478904540984784094892233720368547758048961351894123178614000004876666047860321854064894088704489405489401489405480448047890454098478409489223372036854775804896135189412317861400000487666604786032185406489408870448940548940148940548044804789045409847840948922337203685477580489613518941231786140000048766660478603218540648940887044894054894014894054804480478904540984784094892233720368547758048961351894123178614000004876666047860321854064894088704489405489401489405480448047890454098478409489223372036854775804896135189412317861400000487666604786032185406489408870448940548940148940548044804789045409847840948922337203685477580489613518941231786140000048766660478603218540648940887044894054894014894054804480478904540984784094892");
			int count = 1754;
			assertEquals(bi.bitCount(), count);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBitCount014() {
		try {
			bi = new BigInteger("100000000000001000000000000");
			int count = 39;
			assertEquals(bi.bitCount(), count);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testBitCount015() {
		try {
			bi = new BigInteger("-100000000000001000000000000");
			int count = 50;
			assertEquals(bi.bitCount(), count);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

}
