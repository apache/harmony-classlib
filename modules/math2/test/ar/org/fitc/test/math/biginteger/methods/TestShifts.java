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
 * shiftRight(int) 
 * shiftLeft(int)  
 *
 */
public class TestShifts extends TestCase implements Messages {

	private BigInteger bi = null;

	/** Creates a new instance of TestShifts */
	public TestShifts(String args) {
		super(args);
	}

	public static void main(String args[]) {
		junit.textui.TestRunner.run(TestShifts.class);
	}

	/*
	 * Test method for 'java.math.bigInteger.shiftLeft(int)'
	 */
	public void testShiftLeft001() {
		try {
			assertEquals(BigInteger.ZERO.shiftLeft(0), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testShiftLeft002() {
		try {
			assertEquals(BigInteger.ZERO.shiftLeft(2147483647), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testShiftLeft003() {
		try {
			assertEquals(BigInteger.ZERO.shiftLeft(-2147483648),
					BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testShiftLeft004() {
		try {
			bi = new BigInteger("2147483647");
			assertEquals(bi.shiftLeft(0), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testShiftLeft005() {
		try {
			bi = new BigInteger("2147483647");

			int n = 155;
			assertEquals(bi.shiftLeft(n), new BigInteger(
					"98079714569744960768343493543754636729215459455502647296"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testShiftLeft006() {
		try {
			bi = new BigInteger("2147483647");

			int n = -31;
			assertEquals(bi.shiftLeft(n), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testShiftLeft007() {
		try {
			bi = new BigInteger("2147483647");

			int n = -15;
			assertEquals(bi.shiftLeft(n), new BigInteger("65535"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testShiftLeft008() {
		try {
			bi = new BigInteger("-2147483647");
			assertEquals(bi.shiftLeft(0), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testShiftLeft009() {
		try {
			bi = new BigInteger("-2147483647");

			int n = 155;
			assertEquals(
					bi.shiftLeft(n),
					new BigInteger(
							"-98079714569744960768343493543754636729215459455502647296"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testShiftLeft010() {
		try {
			bi = new BigInteger("-2147483647");

			int n = -31;
			assertEquals(bi.shiftLeft(n), new BigInteger("-1"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testShiftLeft011() {
		try {
			bi = new BigInteger("-2147483647");

			int n = -15;
			assertEquals(bi.shiftLeft(n), new BigInteger("-65536"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testShiftLeft012() {
		try {
			bi = new BigInteger(
					"219441999784165123489161234789617894561789451567891531874864517812598178126174848158911116517816541794416874118674441684416");
			assertEquals(bi.shiftLeft(0), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testShiftLeft013() {
		try {
			bi = new BigInteger(
					"219441999784165123489161234789617894561789451567891531874864517812598178126174848158911116517816541794416874118674441684416");

			int n = 200;
			assertEquals(
					bi.shiftLeft(n),
					new BigInteger(
							"352629697961448069702271321161875241260689527407891272352032805076823368565367258869647959938615559721225484204229117988251372677842999984342546241521706213667469159663804496442556416"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testShiftLeft014() {
		try {
			bi = new BigInteger(
					"219441999784165123489161234789617894561789451567891531874864517812598178126174848158911116517816541794416874118674441684416");

			int n = -200;
			assertEquals(
					bi.shiftLeft(n),
					new BigInteger(
							"136559091726126092447688519808182211692187248349700435764937991"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testShiftLeft015() {
		try {
			bi = new BigInteger(
					"219441999784165123489161234789617894561789451567891531874864517812598178126174848158911116517816541794416874118674441684416");

			int n = -405;
			assertEquals(bi.shiftLeft(n), new BigInteger("2"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testShiftLeft016() {
		try {
			bi = new BigInteger(
					"-219441999784165123489161234789617894561789451567891531874864517812598178126174848158911116517816541794416874118674441684416");
			assertEquals(bi.shiftLeft(0), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testShiftLeft017() {
		try {
			bi = new BigInteger(
					"-219441999784165123489161234789617894561789451567891531874864517812598178126174848158911116517816541794416874118674441684416");

			int n = 200;
			assertEquals(
					bi.shiftLeft(n),
					new BigInteger(
							"-352629697961448069702271321161875241260689527407891272352032805076823368565367258869647959938615559721225484204229117988251372677842999984342546241521706213667469159663804496442556416"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testShiftLeft018() {
		try {
			bi = new BigInteger(
					"-219441999784165123489161234789617894561789451567891531874864517812598178126174848158911116517816541794416874118674441684416");

			int n = -200;
			assertEquals(
					bi.shiftLeft(n),
					new BigInteger(
							"-136559091726126092447688519808182211692187248349700435764937992"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testShiftLeft019() {
		try {
			bi = new BigInteger(
					"-219441999784165123489161234789617894561789451567891531874864517812598178126174848158911116517816541794416874118674441684416");

			int n = -405;
			assertEquals(bi.shiftLeft(n), new BigInteger("-3"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	/*
	 * Test method for 'java.math.bigInteger.shiftRight(int)'
	 */
	public void testShiftRight001() {
		try {
			assertEquals(BigInteger.ZERO.shiftRight(0), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testShiftRight002() {
		try {
			assertEquals(BigInteger.ZERO.shiftRight(-2147483648),
					BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testShiftRight003() {
		try {
			assertEquals(BigInteger.ZERO.shiftRight(2147483647),
					BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testShiftRight004() {
		try {
			bi = new BigInteger("2147483647");
			assertEquals(bi.shiftRight(0), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testShiftRight005() {
		try {
			bi = new BigInteger("2147483647");

			int n = -155;
			assertEquals(bi.shiftRight(n), new BigInteger(
					"98079714569744960768343493543754636729215459455502647296"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testShiftRight006() {
		try {
			bi = new BigInteger("2147483647");

			int n = 31;
			assertEquals(bi.shiftRight(n), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testShiftRight007() {
		try {
			bi = new BigInteger("2147483647");

			int n = 15;
			assertEquals(bi.shiftRight(n), new BigInteger("65535"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testShiftRight008() {
		try {
			bi = new BigInteger("-2147483647");
			assertEquals(bi.shiftRight(0), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testShiftRight009() {
		try {
			bi = new BigInteger("-2147483647");

			int n = -155;
			assertEquals(
					bi.shiftRight(n),
					new BigInteger(
							"-98079714569744960768343493543754636729215459455502647296"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testShiftRight010() {
		try {
			bi = new BigInteger("-2147483647");

			int n = 31;
			assertEquals(bi.shiftRight(n), new BigInteger("-1"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testShiftRight011() {
		try {
			bi = new BigInteger("-2147483647");

			int n = 15;
			assertEquals(bi.shiftRight(n), new BigInteger("-65536"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testShiftRight012() {
		try {
			bi = new BigInteger(
					"219441999784165123489161234789617894561789451567891531874864517812598178126174848158911116517816541794416874118674441684416");
			assertEquals(bi.shiftRight(0), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testShiftRight013() {
		try {
			bi = new BigInteger(
					"219441999784165123489161234789617894561789451567891531874864517812598178126174848158911116517816541794416874118674441684416");

			int n = -200;
			assertEquals(
					bi.shiftRight(n),
					new BigInteger(
							"352629697961448069702271321161875241260689527407891272352032805076823368565367258869647959938615559721225484204229117988251372677842999984342546241521706213667469159663804496442556416"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testShiftRight014() {
		try {
			bi = new BigInteger(
					"219441999784165123489161234789617894561789451567891531874864517812598178126174848158911116517816541794416874118674441684416");

			int n = 200;
			assertEquals(
					bi.shiftRight(n),
					new BigInteger(
							"136559091726126092447688519808182211692187248349700435764937991"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testShiftRight015() {
		try {
			bi = new BigInteger(
					"219441999784165123489161234789617894561789451567891531874864517812598178126174848158911116517816541794416874118674441684416");

			int n = 405;
			assertEquals(bi.shiftRight(n), new BigInteger("2"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testShiftRight016() {
		try {
			bi = new BigInteger(
					"-219441999784165123489161234789617894561789451567891531874864517812598178126174848158911116517816541794416874118674441684416");
			assertEquals(bi.shiftRight(0), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testShiftRight017() {
		try {
			bi = new BigInteger(
					"-219441999784165123489161234789617894561789451567891531874864517812598178126174848158911116517816541794416874118674441684416");

			int n = -200;
			assertEquals(
					bi.shiftRight(n),
					new BigInteger(
							"-352629697961448069702271321161875241260689527407891272352032805076823368565367258869647959938615559721225484204229117988251372677842999984342546241521706213667469159663804496442556416"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testShiftRight018() {
		try {
			bi = new BigInteger(
					"-219441999784165123489161234789617894561789451567891531874864517812598178126174848158911116517816541794416874118674441684416");

			int n = 200;
			assertEquals(
					bi.shiftRight(n),
					new BigInteger(
							"-136559091726126092447688519808182211692187248349700435764937992"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testShiftRight019() {
		try {
			bi = new BigInteger(
					"-219441999784165123489161234789617894561789451567891531874864517812598178126174848158911116517816541794416874118674441684416");

			int n = 405;
			assertEquals(bi.shiftRight(n), new BigInteger("-3"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

}
