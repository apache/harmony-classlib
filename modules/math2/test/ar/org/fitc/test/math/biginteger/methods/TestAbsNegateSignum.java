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
import java.util.Random;
import junit.framework.TestCase;

/**
 * Test cases for 
 * abs(), 
 * negate(),
 * signum() 
 * 
 */

public class TestAbsNegateSignum extends TestCase implements Messages {

	private byte[] magnitude = null;

	private BigInteger bi = null;

	private int signum = 0;

	private String valString = null;

	private int radix;

	/** Creates a new instance of TestAbs */
	public TestAbsNegateSignum(String name) {
		super(name);
	}

	public static void main(String args[]) {
		junit.textui.TestRunner.run(TestAbsNegateSignum.class);
	}

	public void testAbs054() {
		try {
			byte[] val = new byte[1];
			bi = new BigInteger(val);
			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs055() {
		try {
			byte[] val = new byte[500];

			for (int i = 1; i < 500; i++) {
				val[i] = (byte) 0xc7;
			}

			bi = new BigInteger(val);
			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs056() {
		try {
			magnitude = new byte[500];

			for (int i = 1; i < 500; i++) {
				magnitude[i] = (byte) 0xc7;
			}

			signum = 1;
			bi = new BigInteger(signum, magnitude);
			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs057() {
		try {
			magnitude = new byte[500];

			for (int i = 1; i < 500; i++) {
				magnitude[i] = 0;
			}

			signum = 0;
			bi = new BigInteger(signum, magnitude);

			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs058() {
		try {
			magnitude = new byte[500];

			for (int i = 1; i < 500; i++) {
				magnitude[i] = (byte) 0xc7;
			}

			signum = -1;
			bi = new BigInteger(signum, magnitude);

			assertEquals(bi.abs(), new BigInteger(1, magnitude));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs059() {
		try {
			magnitude = new byte[0];
			signum = 1;
			bi = new BigInteger(signum, magnitude);
			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs060() {
		try {
			magnitude = new byte[0];
			signum = 0;
			bi = new BigInteger(signum, magnitude);
			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs061() {
		try {
			magnitude = new byte[0];
			signum = -1;
			bi = new BigInteger(signum, magnitude);

			assertEquals(bi.abs(), new BigInteger(1, magnitude));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs062() {
		try {
			valString = "2084534089640156400533116641056046";
			bi = new BigInteger(valString);
			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs063() {
		try {
			valString = "0";
			bi = new BigInteger(valString);
			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs064() {
		try {
			valString = "-2084534089640156400533116641056046";
			bi = new BigInteger(valString);
			valString = "2084534089640156400533116641056046";

			BigInteger bi2 = new BigInteger(valString);
			assertEquals(bi.abs(), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs065() {
		try {
			radix = 2;
			valString = "1111111111000000000001111111111110000111111110111000000000110000000000011111111111100000000000001111111111110000000000011111111111111100000000000000111111111111111000000000001111111111111111111111111000000000111111111111111111111111111111111111111111111111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111111111111111111111111";
			
			bi = new BigInteger(valString, radix);
			
			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs066() {
		try {
			radix = 2;
			valString = "0";
			bi = new BigInteger(valString, radix);
			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs067() {
		try {
			radix = 2;
			valString = "-010101010101011111111111111111110000000000000111111111000000001111111111100000000000001111100000000111111110000000000000000001111111111111100000000";
			bi = new BigInteger(valString, radix);
			valString = "010101010101011111111111111111110000000000000111111111000000001111111111100000000000001111100000000111111110000000000000000001111111111111100000000";

			BigInteger bi2 = new BigInteger(valString, radix);
			assertEquals(bi.abs(), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs068() {
		try {
			radix = 16;
			valString = "151874e184186b411167841874411c21384874513f52d41854157484a746";
			bi = new BigInteger(valString, radix);
			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs069() {
		try {
			radix = 16;
			valString = "0";
			bi = new BigInteger(valString, radix);
			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs070() {
		try {
			radix = 16;
			valString = "-151874e184186b411167841874411c21384874513f52d41854157484a746";
			bi = new BigInteger(valString, radix);
			valString = "151874e184186b411167841874411c21384874513f52d41854157484a746";

			BigInteger bi2 = new BigInteger(valString, radix);
			assertEquals(bi.abs(), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs071() {
		try {
			radix = 36;
			valString = "151874e184186b41vewraaaajy11678liiiirroiup418744grweqaagncmpiut11zc21384874513f52d41854157484a746";
			bi = new BigInteger(valString, radix);
			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs072() {
		try {
			radix = 36;
			valString = "0";
			bi = new BigInteger(valString, radix);

			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs073() {
		try {
			radix = 36;
			valString = "-151874e184186b41vewraaaajy11678liiiirroiup418744grweqaagncmpiut11zc21384874513f52d41854157484a746";
			bi = new BigInteger(valString, radix);
			valString = "151874e184186b41vewraaaajy11678liiiirroiup418744grweqaagncmpiut11zc21384874513f52d41854157484a746";

			BigInteger bi2 = new BigInteger(valString, radix);
			assertEquals(bi.abs(), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs074() {
		try {
			int numBits = 1;
			Random rnd = new Random();
			bi = new BigInteger(numBits, rnd);
			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs075() {
		try {
			int numBits = 20000000;
			Random rnd = new Random();
			bi = new BigInteger(numBits, rnd);
			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs076() {
		try {
			int numBits = 0;
			Random rnd = new Random();
			bi = new BigInteger(numBits, rnd);

			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs077() {
		try {
			int numBits = 1;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(numBits, rnd);

			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs078() {
		try {
			int numBits = 10000000;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(numBits, rnd);

			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs079() {
		try {
			int numBits = 0;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(numBits, rnd);

			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs080() {
		try {
			int numBits = 1;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(numBits, rnd);

			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs081() {
		try {
			int numBits = 10000000;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(numBits, rnd);
			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs082() {
		try {
			int numBits = 0;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(numBits, rnd);

			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs083() {
		try {
			int bitLength = 2;
			int certainty = 0;
			Random rnd = new Random();
			bi = new BigInteger(bitLength, certainty, rnd);

			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs084() {
		try {
			int bitLength = 300;
			int certainty = 0;
			Random rnd = new Random();
			bi = new BigInteger(bitLength, certainty, rnd);

			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs085() {
		try {
			int bitLength = 2;
			int certainty = 0;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(bitLength, certainty, rnd);

			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs086() {
		try {
			int bitLength = 300;
			int certainty = 0;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(bitLength, certainty, rnd);

			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs087() {
		try {
			int bitLength = 2;
			int certainty = 0;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(bitLength, certainty, rnd);

			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs088() {
		try {
			int bitLength = 300;
			int certainty = 0;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(bitLength, certainty, rnd);

			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs089() {
		try {
			int bitLength = 2;
			int certainty = 10;
			Random rnd = new Random();
			bi = new BigInteger(bitLength, certainty, rnd);

			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs090() {
		try {
			int bitLength = 300;
			int certainty = 10;
			Random rnd = new Random();
			bi = new BigInteger(bitLength, certainty, rnd);

			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs091() {
		try {
			int bitLength = 2;
			int certainty = 10;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(bitLength, certainty, rnd);

			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs092() {
		try {
			int bitLength = 300;
			int certainty = 10;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(bitLength, certainty, rnd);

			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs093() {
		try {
			int bitLength = 300;
			int certainty = 10;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(bitLength, certainty, rnd);

			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs094() {
		try {
			int bitLength = 300;
			int certainty = 10;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(bitLength, certainty, rnd);

			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs095() {
		try {
			int bitLength = 2;
			int certainty = -1;
			Random rnd = new Random();
			bi = new BigInteger(bitLength, certainty, rnd);

			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs096() {
		try {
			int bitLength = 300;
			int certainty = -1;
			Random rnd = new Random();
			bi = new BigInteger(bitLength, certainty, rnd);

			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs097() {
		try {
			int bitLength = 2;
			int certainty = -1;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(bitLength, certainty, rnd);

			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs098() {
		try {
			int bitLength = 300;
			int certainty = -1;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(bitLength, certainty, rnd);

			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs099() {
		try {
			int bitLength = 2;
			int certainty = -1;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(bitLength, certainty, rnd);

			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs100() {
		try {
			int bitLength = 300;
			int certainty = -1;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(bitLength, certainty, rnd);

			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs101() {
		try {
			int bitLength = 2;
			int certainty = -10;
			Random rnd = new Random();
			bi = new BigInteger(bitLength, certainty, rnd);

			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs102() {
		try {
			int bitLength = 300;
			int certainty = -10;
			Random rnd = new Random();
			bi = new BigInteger(bitLength, certainty, rnd);

			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs103() {
		try {
			int bitLength = 2;
			int certainty = -10;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(bitLength, certainty, rnd);

			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs104() {
		try {
			int bitLength = 300;
			int certainty = -10;
			long seed = Long.MAX_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(bitLength, certainty, rnd);

			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs105() {
		try {
			int bitLength = 2;
			int certainty = -10;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(bitLength, certainty, rnd);

			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testAbs106() {
		try {
			int bitLength = 300;
			int certainty = -10;
			long seed = Long.MIN_VALUE;
			Random rnd = new Random(seed);
			bi = new BigInteger(bitLength, certainty, rnd);
			assertEquals(bi.abs(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	/*
	 * Test method for 'java.math.BigInteger.negate()'
	 */
	public void testNegate001() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi.negate(), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testNegate002() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-21474836455");
			assertEquals(bi.negate(), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testNegate003() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			assertEquals(bi.negate(), bi2);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testNegate004() {
		try {
			bi = new BigInteger("-1");

			BigInteger bi2 = new BigInteger("1");
			assertEquals(bi2.negate(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testNegate005() {
		try {
			bi = new BigInteger("21474836455");

			BigInteger bi2 = new BigInteger("-21474836455");
			assertEquals(bi2.negate(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testNegate006() {
		try {
			bi = new BigInteger("999999999997777773151874");

			BigInteger bi2 = new BigInteger("-999999999997777773151874");
			assertEquals(bi2.negate(), bi);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testNegate007() {
		try {
			assertEquals(BigInteger.ZERO.negate(), BigInteger.ZERO);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	/*
	 * Test method for 'java.math.BigInteger.signum()'
	 */
	public void testSignum001() {
		try {
			int sign = -1;
			bi = new BigInteger("-1");
			assertEquals(bi.signum(), sign);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSignum002() {
		try {
			int sign = -1;
			bi = new BigInteger(
					"-891418943111118971233311104897879048901784000305489674400189874000047886");
			assertEquals(bi.signum(), sign);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSignum003() {
		try {
			int sign = 0;
			assertEquals(BigInteger.ZERO.signum(), sign);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSignum004() {
		try {
			int sign = 1;
			assertEquals(BigInteger.ONE.signum(), sign);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSignum005() {
		try {
			int sign = 1;
			assertEquals(BigInteger.TEN.signum(), sign);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

	public void testSignum006() {
		try {
			int sign = 1;
			bi = new BigInteger(
					"50894168741408978000978019877401978704189704198445011879740");
			assertEquals(bi.signum(), sign);
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

}
