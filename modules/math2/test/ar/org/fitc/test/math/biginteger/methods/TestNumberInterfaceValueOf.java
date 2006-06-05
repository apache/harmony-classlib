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
 * Test cases for intValue(), floatValue(), longValue(), doubleValue(),
 * valueOf(long)
 */
public class TestNumberInterfaceValueOf extends TestCase implements Messages {

	private BigInteger bi = null;

	/** Creates a new instance of TestNumberInterface */
	public TestNumberInterfaceValueOf(String name) {
		super(name);
	}

	public static void main(String args[]) {
		junit.textui.TestRunner.run(TestNumberInterfaceValueOf.class);
	}

	/*
	 * Test method for 'java.math.bigInteger.intValue()'
	 */
	public void testIntValue001() {
		try {
			int i = 0;
			assertEquals(BigInteger.ZERO.intValue(), i);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testIntValue002() {
		try {
			bi = new BigInteger("2147483647");
			assertEquals(bi.intValue(), Integer.MAX_VALUE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testIntValue003() {
		try {
			bi = new BigInteger("-2147483648");
			assertEquals(bi.intValue(), Integer.MIN_VALUE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testIntValue004() {
		try {
			bi = new BigInteger("2147483648");
			assertEquals(bi.intValue(), Integer.MIN_VALUE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testIntValue005() {
		try {
			bi = new BigInteger("-2147483649");
			assertEquals(bi.intValue(), Integer.MAX_VALUE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	/*
	 * Test method for 'java.math.bigInteger.longValue()'
	 */
	public void testLongValue001() {
		try {
			long l = 0;
			assertEquals(BigInteger.ZERO.longValue(), l);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testLongValue002() {
		try {
			bi = new BigInteger("9223372036854775807");
			assertEquals(bi.longValue(), Long.MAX_VALUE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testLongValue003() {
		try {
			bi = new BigInteger("-9223372036854775808");
			assertEquals(bi.longValue(), Long.MIN_VALUE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testLongValue004() {
		try {
			bi = new BigInteger("9223372036854775808");
			assertEquals(bi.longValue(), Long.MIN_VALUE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testLongValue005() {
		try {
			bi = new BigInteger("-9223372036854775809");
			assertEquals(bi.longValue(), Long.MAX_VALUE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	/*
	 * Test method for 'java.math.bigInteger.doubleValue()'
	 */
	public void testDoubleValue001() {
		try {
			double d = 0;
			assertEquals(BigInteger.ZERO.doubleValue(), d);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDoubleValue002() {
		try {
			bi = new BigInteger(
					"179769313486231570000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
			assertEquals(bi.doubleValue(), Double.MAX_VALUE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDoubleValue003() {
		try {
			bi = new BigInteger(
					"179769313486231570000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001");
			bi = bi
					.add(new BigInteger(
							"19958403095347198116563727130368000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
			assertEquals(bi.doubleValue(), Double.POSITIVE_INFINITY);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDoubleValue004() {
		try {
			bi = new BigInteger(
					"-179769313486231570000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
			bi = bi
					.subtract(new BigInteger(
							"9979201547673599058281863565184200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
			assertNotSame(bi.doubleValue(), Double.NEGATIVE_INFINITY);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDoubleValue005() {
		try {
			bi = new BigInteger(
					"-179769313486231570000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
			bi = bi
					.subtract(new BigInteger(
							"19958403095347198116563727130368000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
			assertEquals(bi.doubleValue(), Double.NEGATIVE_INFINITY);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testDoubleValue006() {
		try {
			bi = new BigInteger(
					"-179769313486231570000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");

			Double d = new Double(
					"-179769313486231570000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
			assertNotSame(bi.doubleValue(), d.doubleValue());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	/*
	 * Test method for 'java.math.bigInteger.floatValue()'
	 */
	public void testFloatValue001() {
		try {
			float f = 0;
			assertEquals(BigInteger.ZERO.floatValue(), f);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFloatValue002() {
		try {
			bi = new BigInteger("340282346638528860000000000000000000000");
			assertEquals(bi.floatValue(), Float.MAX_VALUE);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFloatValue003() {
		try {
			bi = new BigInteger("340282346638528860000000000000000000000");
			bi = bi.add(new BigInteger("10141204801825835211973625643008"));
			assertEquals(bi.floatValue(), Float.POSITIVE_INFINITY);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFloatValue004() {
		try {
			bi = new BigInteger("-3402823466385280000000000000000000000");
			bi = bi.subtract(new BigInteger("5070602400912917605986812821504"));
			assertNotSame(bi.floatValue(), Float.NEGATIVE_INFINITY);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFloatValue005() {
		try {
			bi = new BigInteger("-340282346638528860000000000000000000000");
			bi = bi
					.subtract(new BigInteger("10141204801825835211973625643008"));
			assertEquals(bi.floatValue(), Float.NEGATIVE_INFINITY);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testFloatValue006() {
		try {
			bi = new BigInteger("-340282346638528860000000000000000000000");

			Float f = new Float("-340282346638528860000000000000000000000");
			assertNotSame(bi.floatValue(), f.floatValue());
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	/*
	 * Test method for 'java.math.BigInteger.valueOf(long)'
	 */
	public void testValueOf001() {
		try {
			assertNotNull(msgNotNull, BigInteger.valueOf(0));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testValueOf002() {
		try {
			assertNotNull(msgNotNull, BigInteger.valueOf(Long.MAX_VALUE));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testValueOf003() {
		try {
			assertNotNull(msgNotNull, BigInteger.valueOf(Long.MIN_VALUE));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testValueOf004() {
		try {
			assertEquals(BigInteger.valueOf(0), new BigInteger("0"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testValueOf005() {
		try {
			assertEquals(BigInteger.valueOf(Long.MAX_VALUE), new BigInteger(
					"9223372036854775807"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testValueOf006() {
		try {
			assertEquals(BigInteger.valueOf(Long.MIN_VALUE), new BigInteger(
					"-9223372036854775808"));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

}
