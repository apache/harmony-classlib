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

import java.math.BigDecimal;
import java.math.BigInteger;
import ar.org.fitc.test.util.Messages;
import junit.framework.TestCase;

public class TestSubtractBigDecimal extends TestCase implements Messages {

	public TestSubtractBigDecimal() {
		super();
	}

	public TestSubtractBigDecimal(String name) {
		super(name);
	}

	public void testSubtractBigDecimal001() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 0);
			assertEquals("0", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal002() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 0);
			assertEquals("-1", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal003() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 0);
			assertEquals("1", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal004() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			assertEquals(
					"-897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal005() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			assertEquals(
					"8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal006() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 0);
			assertEquals("1", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal007() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 0);
			assertEquals("0", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal008() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 0);
			assertEquals("2", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal009() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			assertEquals(
					"-897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741319",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal010() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			assertEquals(
					"8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741321",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal011() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 0);
			assertEquals("-1", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal012() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 0);
			assertEquals("-2", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal013() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 0);
			assertEquals("0", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal014() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			assertEquals(
					"-897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741321",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal015() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			assertEquals(
					"8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741319",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal016() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 0);
			assertEquals(
					"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal017() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 0);
			assertEquals(
					"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741319",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal018() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 0);
			assertEquals(
					"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741321",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal019() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			assertEquals("0", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal020() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			assertEquals(
					"897489789741361086795310617660579482704223779482704223794826419482640223794837794827042237948264194827042237948264070422379483794827042237948264064179482640270422379482640",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal021() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 0);
			assertEquals(
					"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal022() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 0);
			assertEquals(
					"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741321",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal023() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 0);
			assertEquals(
					"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741319",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal024() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			assertEquals(
					"-897489789741361086795310617660579482704223779482704223794826419482640223794837794827042237948264194827042237948264070422379483794827042237948264064179482640270422379482640",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal025() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			assertEquals("0", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal026() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -1);
			assertEquals("0", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal027() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -1);
			assertEquals("-10", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal028() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -1);
			assertEquals("10", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal029() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			assertEquals(
					"-8974897897413521118974132041352897413521118897413521118974132097413201118974188974135211189741320974135211189741320352111897418974135211189741320320897413201352111897413200",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal030() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			assertEquals(
					"89748978974135252897413521118897413521118974132097413201118974188974135211189741320974135211189741320352111897418974135211189741320320897413201352111897413200",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal031() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -1);
			assertEquals("1", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal032() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -1);
			assertEquals("-9", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal033() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -1);
			assertEquals("11", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal034() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			assertEquals(
					"-8974897897413521118974132041352897413521118897413521118974132097413201118974188974135211189741320974135211189741320352111897418974135211189741320320897413201352111897413199",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal035() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			assertEquals(
					"89748978974135252897413521118897413521118974132097413201118974188974135211189741320974135211189741320352111897418974135211189741320320897413201352111897413201",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal036() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -1);
			assertEquals("-1", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal037() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -1);
			assertEquals("-11", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal038() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -1);
			assertEquals("9", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal039() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			assertEquals(
					"-8974897897413521118974132041352897413521118897413521118974132097413201118974188974135211189741320974135211189741320352111897418974135211189741320320897413201352111897413201",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal040() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			assertEquals(
					"89748978974135252897413521118897413521118974132097413201118974188974135211189741320974135211189741320352111897418974135211189741320320897413201352111897413199",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal041() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -1);
			assertEquals(
					"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal042() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -1);
			assertEquals(
					"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741310",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal043() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -1);
			assertEquals(
					"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741330",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal044() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			assertEquals(
					"-8077408107672169007076718837217607672169007007672169007076718887671881007076770076721690070767188876721690070767188316900707677076721690070767188288807671881216900707671880",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal045() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			assertEquals(
					"897489789741441860876387339388187154873230787154873230871545307154521230871607871548732308715453071548732308715452387323087160871548732308715452352987154521487323087154520",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal046() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -1);
			assertEquals(
					"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal047() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -1);
			assertEquals(
					"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741330",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal048() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -1);
			assertEquals(
					"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741310",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal049() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			assertEquals(
					"-8974897897413530093872029454878187154873230787154873230871545307154521230871607871548732308715453071548732308715452387323087160871548732308715452352987154521487323087154520",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal050() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			assertEquals(
					"80774081076721727607672169007007672169007076718887671881007076770076721690070767188876721690070767188316900707677076721690070767188288807671881216900707671880",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal051() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 2147483647);
			assertEquals("0E-2147483647", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal052() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 2147483647);
			assertEquals("-1E-2147483647", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal053() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 2147483647);
			assertEquals("1E-2147483647", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal054() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			assertEquals(
					"-8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E-2147483477",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal055() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			assertEquals(
					"8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E-2147483491",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal056() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal057() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal058() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal059() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal060() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal061() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal062() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal063() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal064() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal065() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal066() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal067() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal068() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal069() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal070() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal071() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal072() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal073() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal074() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal075() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal076() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -2147483648);
			assertEquals("0", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal077() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal078() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal079() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal080() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal081() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -2147483648);
			assertEquals("1", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal082() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal083() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal084() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal085() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal086() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -2147483648);
			assertEquals("-1", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal087() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal088() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal089() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal090() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal091() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -2147483648);
			assertEquals(
					"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal092() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal093() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal094() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal095() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal096() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -2147483648);
			assertEquals(
					"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal097() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal098() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal099() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal100() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal101() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 0);
			assertEquals("0", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal102() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 0);
			assertEquals("-1", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal103() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 0);
			assertEquals("1", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal104() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			assertEquals(
					"-897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal105() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			assertEquals(
					"8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal106() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 0);
			assertEquals("10", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal107() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 0);
			assertEquals("9", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal108() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 0);
			assertEquals("11", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal109() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			assertEquals(
					"-897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741310",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal110() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			assertEquals(
					"8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741330",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal111() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 0);
			assertEquals("-10", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal112() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 0);
			assertEquals("-11", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal113() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 0);
			assertEquals("-9", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal114() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			assertEquals(
					"-897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741330",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal115() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			assertEquals(
					"8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741310",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal116() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 0);
			assertEquals(
					"8974897897413521118974132041352897413521118897413521118974132097413201118974188974135211189741320974135211189741320352111897418974135211189741320320897413201352111897413200",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal117() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 0);
			assertEquals(
					"8974897897413521118974132041352897413521118897413521118974132097413201118974188974135211189741320974135211189741320352111897418974135211189741320320897413201352111897413199",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal118() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 0);
			assertEquals(
					"8974897897413521118974132041352897413521118897413521118974132097413201118974188974135211189741320974135211189741320352111897418974135211189741320320897413201352111897413201",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal119() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			assertEquals(
					"8077408107672169007076718837217607672169007007672169007076718887671881007076770076721690070767188876721690070767188316900707677076721690070767188288807671881216900707671880",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal120() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			assertEquals(
					"8974897897413530093872029454878187154873230787154873230871545307154521230871607871548732308715453071548732308715452387323087160871548732308715452352987154521487323087154520",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal121() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 0);
			assertEquals(
					"-89748978974135252897413521118897413521118974132097413201118974188974135211189741320974135211189741320352111897418974135211189741320320897413201352111897413200",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal122() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 0);
			assertEquals(
					"-89748978974135252897413521118897413521118974132097413201118974188974135211189741320974135211189741320352111897418974135211189741320320897413201352111897413201",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal123() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 0);
			assertEquals(
					"-89748978974135252897413521118897413521118974132097413201118974188974135211189741320974135211189741320352111897418974135211189741320320897413201352111897413199",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal124() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			assertEquals(
					"-897489789741441860876387339388187154873230787154873230871545307154521230871607871548732308715453071548732308715452387323087160871548732308715452352987154521487323087154520",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal125() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			assertEquals(
					"-80774081076721727607672169007007672169007076718887671881007076770076721690070767188876721690070767188316900707677076721690070767188288807671881216900707671880",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal126() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -1);
			assertEquals("0E+1", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal127() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -1);
			assertEquals("-1E+1", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal128() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -1);
			assertEquals("1E+1", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal129() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			assertEquals(
					"-8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E+171",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal130() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			assertEquals(
					"8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E+157",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal131() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -1);
			assertEquals("1E+1", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal132() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -1);
			assertEquals("0E+1", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal133() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -1);
			assertEquals("2E+1", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal134() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			assertEquals(
					"-8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741319E+171",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal135() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			assertEquals(
					"8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741321E+157",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal136() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -1);
			assertEquals("-1E+1", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal137() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -1);
			assertEquals("-2E+1", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal138() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -1);
			assertEquals("0E+1", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal139() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			assertEquals(
					"-8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741321E+171",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal140() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			assertEquals(
					"8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741319E+157",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal141() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -1);
			assertEquals(
					"8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E+171",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal142() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -1);
			assertEquals(
					"8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741319E+171",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal143() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -1);
			assertEquals(
					"8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741321E+171",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal144() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			assertEquals("0E+1", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal145() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			assertEquals(
					"8.97489789741361086795310617660579482704223779482704223794826419482640223794837794827042237948264194827042237948264070422379483794827042237948264064179482640270422379482640E+171",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal146() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -1);
			assertEquals(
					"-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E+157",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal147() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -1);
			assertEquals(
					"-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741321E+157",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal148() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -1);
			assertEquals(
					"-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741319E+157",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal149() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			assertEquals(
					"-8.97489789741361086795310617660579482704223779482704223794826419482640223794837794827042237948264194827042237948264070422379483794827042237948264064179482640270422379482640E+171",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal150() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			assertEquals("0E+1", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal151() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 2147483647);
			assertEquals("0E-2147483647", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal152() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 2147483647);
			assertEquals("-1E-2147483647", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal153() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 2147483647);
			assertEquals("1E-2147483647", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal154() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			assertEquals(
					"-8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E-2147483477",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal155() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			assertEquals(
					"8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E-2147483491",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal156() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal157() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal158() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal159() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal160() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal161() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal162() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal163() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal164() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal165() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal166() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal167() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal168() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal169() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal170() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal171() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal172() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal173() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal174() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal175() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal176() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -2147483648);
			assertEquals("0E+1", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal177() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal178() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal179() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal180() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal181() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -2147483648);
			assertEquals("1E+1", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal182() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal183() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal184() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal185() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal186() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -2147483648);
			assertEquals("-1E+1", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal187() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal188() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal189() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal190() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal191() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -2147483648);
			assertEquals(
					"8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E+171",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal192() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal193() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal194() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal195() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal196() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -2147483648);
			assertEquals(
					"-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E+157",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal197() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal198() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal199() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal200() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal201() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 0);
			assertEquals("0E-2147483647", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal202() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal203() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal204() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal205() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal206() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 0);
			assertEquals("1E-2147483647", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal207() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal208() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal209() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal210() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal211() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 0);
			assertEquals("-1E-2147483647", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal212() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal213() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal214() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal215() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal216() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 0);
			assertEquals(
					"8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E-2147483477",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal217() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal218() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal219() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal220() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal221() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 0);
			assertEquals(
					"-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E-2147483491",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal222() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal223() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal224() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal225() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal226() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -1);
			assertEquals("0E-2147483647", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal227() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal228() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal229() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal230() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal231() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -1);
			assertEquals("1E-2147483647", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal232() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal233() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal234() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal235() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal236() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -1);
			assertEquals("-1E-2147483647", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal237() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal238() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal239() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal240() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal241() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -1);
			assertEquals(
					"8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E-2147483477",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal242() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal243() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal244() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal245() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal246() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -1);
			assertEquals(
					"-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E-2147483491",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal247() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal248() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal249() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal250() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal251() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 2147483647);
			assertEquals("0E-2147483647", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal252() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 2147483647);
			assertEquals("-1E-2147483647", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal253() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 2147483647);
			assertEquals("1E-2147483647", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal254() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			assertEquals(
					"-8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E-2147483477",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal255() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			assertEquals(
					"8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E-2147483491",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal256() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 2147483647);
			assertEquals("1E-2147483647", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal257() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 2147483647);
			assertEquals("0E-2147483647", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal258() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 2147483647);
			assertEquals("2E-2147483647", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal259() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			assertEquals(
					"-8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741319E-2147483477",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal260() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			assertEquals(
					"8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741321E-2147483491",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal261() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 2147483647);
			assertEquals("-1E-2147483647", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal262() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 2147483647);
			assertEquals("-2E-2147483647", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal263() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 2147483647);
			assertEquals("0E-2147483647", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal264() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			assertEquals(
					"-8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741321E-2147483477",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal265() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			assertEquals(
					"8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741319E-2147483491",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal266() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 2147483647);
			assertEquals(
					"8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E-2147483477",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal267() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 2147483647);
			assertEquals(
					"8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741319E-2147483477",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal268() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 2147483647);
			assertEquals(
					"8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741321E-2147483477",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal269() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			assertEquals("0E-2147483647", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal270() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			assertEquals(
					"8.97489789741361086795310617660579482704223779482704223794826419482640223794837794827042237948264194827042237948264070422379483794827042237948264064179482640270422379482640E-2147483477",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal271() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 2147483647);
			assertEquals(
					"-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E-2147483491",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal272() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 2147483647);
			assertEquals(
					"-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741321E-2147483491",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal273() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 2147483647);
			assertEquals(
					"-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741319E-2147483491",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal274() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			assertEquals(
					"-8.97489789741361086795310617660579482704223779482704223794826419482640223794837794827042237948264194827042237948264070422379483794827042237948264064179482640270422379482640E-2147483477",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal275() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			assertEquals("0E-2147483647", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal276() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -2147483648);
			assertEquals("0E-2147483647", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal277() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal278() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal279() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal280() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), 2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal281() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -2147483648);
			assertEquals("1E-2147483647", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal282() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal283() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal284() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal285() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal286() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -2147483648);
			assertEquals("-1E-2147483647", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal287() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal288() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal289() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal290() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), 2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal291() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -2147483648);
			assertEquals(
					"8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E-2147483477",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal292() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal293() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal294() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal295() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal296() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -2147483648);
			assertEquals(
					"-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E-2147483491",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal297() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal298() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal299() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal300() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal301() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 0);
			assertEquals("0", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal302() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 0);
			assertEquals("-1", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal303() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 0);
			assertEquals("1", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal304() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			assertEquals(
					"-897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal305() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			assertEquals(
					"8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal306() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal307() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal308() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal309() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal310() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal311() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal312() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal313() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal314() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal315() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal316() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal317() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal318() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal319() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal320() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal321() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal322() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal323() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal324() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal325() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					0);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal326() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -1);
			assertEquals("0E+1", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal327() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -1);
			assertEquals("-1E+1", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal328() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -1);
			assertEquals("1E+1", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal329() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			assertEquals(
					"-8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E+171",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal330() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			assertEquals(
					"8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E+157",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal331() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal332() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal333() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal334() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal335() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal336() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal337() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal338() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal339() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal340() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal341() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal342() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal343() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal344() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal345() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal346() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal347() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal348() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal349() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal350() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-1);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal351() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 2147483647);
			assertEquals("0E-2147483647", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal352() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 2147483647);
			assertEquals("-1E-2147483647", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal353() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 2147483647);
			assertEquals("1E-2147483647", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal354() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			assertEquals(
					"-8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E-2147483477",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal355() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			assertEquals(
					"8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E-2147483491",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal356() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal357() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal358() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal359() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal360() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal361() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal362() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal363() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal364() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal365() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal366() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal367() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal368() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal369() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal370() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal371() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), 2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal372() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), 2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal373() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), 2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal374() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal375() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					2147483647);
			bi.subtract(bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal376() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -2147483648);
			assertEquals("0E+2147483648", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal377() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -2147483648);
			assertEquals("-1E+2147483648", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal378() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -2147483648);
			assertEquals("1E+2147483648", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal379() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			assertEquals(
					"-8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E+2147483818",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal380() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("0"), -2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			assertEquals(
					"8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E+2147483804",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal381() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -2147483648);
			assertEquals("1E+2147483648", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal382() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -2147483648);
			assertEquals("0E+2147483648", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal383() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -2147483648);
			assertEquals("2E+2147483648", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal384() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			assertEquals(
					"-8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741319E+2147483818",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal385() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			assertEquals(
					"8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741321E+2147483804",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal386() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -2147483648);
			assertEquals("-1E+2147483648", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal387() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -2147483648);
			assertEquals("-2E+2147483648", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal388() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -2147483648);
			assertEquals("0E+2147483648", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal389() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			assertEquals(
					"-8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741321E+2147483818",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal390() {
		try {
			BigDecimal bi = new BigDecimal(new BigInteger("-1"), -2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			assertEquals(
					"8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741319E+2147483804",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal391() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -2147483648);
			assertEquals(
					"8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E+2147483818",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal392() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -2147483648);
			assertEquals(
					"8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741319E+2147483818",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal393() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -2147483648);
			assertEquals(
					"8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741321E+2147483818",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal394() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			assertEquals("0E+2147483648", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal395() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			assertEquals(
					"8.97489789741361086795310617660579482704223779482704223794826419482640223794837794827042237948264194827042237948264070422379483794827042237948264064179482640270422379482640E+2147483818",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal396() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("0"), -2147483648);
			assertEquals(
					"-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E+2147483804",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal397() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("1"), -2147483648);
			assertEquals(
					"-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741321E+2147483804",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal398() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(new BigInteger("-1"), -2147483648);
			assertEquals(
					"-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741319E+2147483804",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal399() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			assertEquals(
					"-8.97489789741361086795310617660579482704223779482704223794826419482640223794837794827042237948264194827042237948264070422379483794827042237948264064179482640270422379482640E+2147483818",
					bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSubtractBigDecimal400() {
		try {
			BigDecimal bi = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			BigDecimal bi2 = new BigDecimal(
					new BigInteger(
							"-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),
					-2147483648);
			assertEquals("0E+2147483648", bi.subtract(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

}
