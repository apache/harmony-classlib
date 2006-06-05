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

package ar.org.fitc.test.math.mathcontext;

import java.math.MathContext;
import ar.org.fitc.test.util.Messages;
import junit.framework.TestCase;
import java.math.RoundingMode;

public class TestMathContext extends TestCase implements Messages {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestMathContext.class);
	}

	public TestMathContext(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/*
	 * Test method for 'java.math.MathContext(int setPrecision)'
	 */
	public final void testMathContextInt001() {
		try {
			@SuppressWarnings("unused")
			MathContext mc = new MathContext(-1);
			fail(msgRaise + "IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		} catch (Throwable e) {
			fail(msgRaise + "IllegalArgumentException");
		}
	}

	public final void testMathContextInt002() {
		try {
			@SuppressWarnings("unused")
			MathContext mc = new MathContext(Integer.MIN_VALUE);
			fail(msgRaise + "IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		} catch (Throwable e) {
			fail(msgRaise + "IllegalArgumentException");
		}
	}

	public final void testMathContextInt003() {
		try {
			@SuppressWarnings("unused")
			MathContext mc = new MathContext(Integer.MAX_VALUE);
			assertTrue(msgNotInstance + "MathContext",
					mc instanceof MathContext);
		} catch (Throwable e) {
			fail(msgNoException);
		}
	}

	/*
	 * Test method for 'java.math.MathContext(int setPrecision, RoundingMode
	 * setRoundingMode)'
	 */
	public final void testMathContextIntRoundingMode001() {
		try {
			@SuppressWarnings("unused")
			MathContext mc = new MathContext(-1, RoundingMode.UP);
			fail(msgRaise + "IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		} catch (Throwable e) {
			fail(msgRaise + "IllegalArgumentException");
		}
	}

	public final void testMathContextIntRoundingMode002() {
		try {
			@SuppressWarnings("unused")
			MathContext mc = new MathContext(-1, RoundingMode.DOWN);
			fail(msgRaise + "IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		} catch (Throwable e) {
			fail(msgRaise + "IllegalArgumentException");
		}
	}

	public final void testMathContextIntRoundingMode003() {
		try {
			@SuppressWarnings("unused")
			MathContext mc = new MathContext(-1, null);
			fail(msgRaise + "IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		} catch (Throwable e) {
			fail(msgRaise + "IllegalArgumentException");
		}
	}

	public final void testMathContextIntRoundingMode004() {
		try {
			@SuppressWarnings("unused")
			MathContext mc = new MathContext(2, null);
			fail(msgRaise + "NullPointerException");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail(msgRaise + "NullPointerException");
		}
	}

	public final void testMathContextIntRoundingMode005() {
		try {
			@SuppressWarnings("unused")
			MathContext mc = new MathContext(2, RoundingMode.UP);
			assertTrue(msgNotInstance + "MathContext",
					mc instanceof MathContext);
		} catch (Throwable e) {
			fail(msgNoException);
		}
	}

	/*
	 * Test method for 'java.math.MathContext(String val)'
	 */

	public final void testMathContextString001() {
		try {
			@SuppressWarnings("unused")
			MathContext mc = new MathContext("precision=999 roundingMode=DOWN");
			assertTrue(msgNotInstance + "MathContext",
					mc instanceof MathContext);
		} catch (Throwable e) {
			fail(msgNoException);
		}
	}

	public final void testMathContextString002() {
		try {
			@SuppressWarnings("unused")
			MathContext mc = new MathContext(
					"precision=2000000 roundingMode=UP");
			assertTrue(msgNotInstance + "MathContext",
					mc instanceof MathContext);
		} catch (Throwable e) {
			fail(msgNoException);
		}
	}

	public final void testMathContextString003() {
		try {
			@SuppressWarnings("unused")
			MathContext mc = new MathContext(
					"precision=99999999999 roundingMode=UP");
			assertTrue(msgNotInstance + "MathContext",
					mc instanceof MathContext);
		} catch (IllegalArgumentException e) {
		} catch (Throwable e) {
			fail(msgNoException);
		}
	}

	/*
	 * Test method for 'java.math.MathContext.hashCode()'
	 */
	public final void testHashCode001() {
		MathContext mc = new MathContext("precision=10 roundingMode=UP");
		MathContext mc2 = new MathContext("precision=10 roundingMode=UP");
		int hc = mc2.hashCode();
		for (int i = 1; i < 15; i++) {
			assertTrue("The MathContext Objects settings differ",
					mc.hashCode() == hc);
		}
	}

	public final void testHashCode002() {
		MathContext mc = new MathContext("precision=19 roundingMode=UP");
		MathContext mc2 = new MathContext("precision=10 roundingMode=UP");
		assertFalse("The MathContext Objects settings differ",
				mc.hashCode() == mc2.hashCode());
	}

	/*
	 * Test method for 'java.math.MathContext.equals(Object)'
	 */
	public final void testEquals001() {
		MathContext mc = new MathContext("precision=10 roundingMode=UP");
		MathContext mc2 = new MathContext("precision=10 roundingMode=UP");
		assertTrue("The MathContext Objects settings differ", mc2.equals(mc));
	}

	public final void testEquals002() {
		MathContext mc = new MathContext("precision=99999999 roundingMode=UP");
		MathContext mc2 = new MathContext("precision=9999 roundingMode=UP");
		assertFalse("The MathContext Objects settings do not differ", mc2
				.equals(mc));
	}

	/*
	 * Test method for 'java.math.MathContext.getPrecision()'
	 */
	public final void testGetPrecision001() {
		MathContext mc = new MathContext("precision=15 roundingMode=UP");
		assertEquals("The MathContext Objects settings differ", 15, mc
				.getPrecision());
	}

	public final void testGetPrecision002() {
		MathContext mc = new MathContext("precision=20000000 roundingMode=UP");
		assertEquals("The MathContext Objects settings differ", 20000000, mc
				.getPrecision());
	}

	/*
	 * Test method for 'java.math.MathContext.getRoundingMode()'
	 */
	public final void testGetRoundingMode001() {
		MathContext mc = new MathContext("precision=20000000 roundingMode=UP");
		assertEquals("The MathContext Objects settings differ",
				RoundingMode.UP, mc.getRoundingMode());
	}

	public final void testGetRoundingMode002() {
		MathContext mc = new MathContext("precision=20000000 roundingMode=DOWN");
		assertEquals("The MathContext Objects settings differ",
				RoundingMode.DOWN, mc.getRoundingMode());
	}

	/*
	 * Test method for 'java.math.MathContext.toString()'
	 */

	public final void testToString001() {
		MathContext mc = new MathContext("precision=20000000 roundingMode=DOWN");
		assertEquals("The MathContext Objects settings differ",
				"precision=20000000 roundingMode=DOWN", mc.toString());
	}

	public final void testToString002() {
		MathContext mc = new MathContext("precision=15 roundingMode=FLOOR");
		assertEquals("The MathContext Objects settings differ",
				"precision=15 roundingMode=FLOOR", mc.toString());
	}

}
