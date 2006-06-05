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

import java.math.MathContext;
import java.math.RoundingMode;

import ar.org.fitc.test.util.Messages;

import junit.framework.TestCase;

public class TestMathContextCobertura extends TestCase implements Messages {

	public TestMathContextCobertura() {
		super();
	}

	public TestMathContextCobertura(String name) {
		super(name);
	}

	public void testMathContextString001() {
		try {
			new MathContext("precision4 roundingMode=HALF_UP");
			fail("IllegalArgumentException is expected");
		} catch (IllegalArgumentException e) {
		}
	}

	public void testMathContextString002() {
		try {
			new MathContext("precision=fa roundingMode=HALF_UP");
			fail("IllegalArgumentException is expected");
		} catch (IllegalArgumentException e) {
		}
	}

	public void testMathContextString003() {
		try {
			new MathContext("precision= roundingMode=HALF_UP");
			fail("IllegalArgumentException is expected");
		} catch (IllegalArgumentException e) {
		}
	}

	public void testMathContextString004() {
		try {
			new MathContext("precision=43245234542335 roundingMode=HALF_UP");
			fail("IllegalArgumentException is expected");
		} catch (IllegalArgumentException e) {
		}
	}

	public void testMathContextString005() {
		try {
			new MathContext("precision=43 roundingModeHALF_UP");
			fail("IllegalArgumentException is expected");
		} catch (IllegalArgumentException e) {
		}
	}

	public void testMathContextString006() {
		try {
			new MathContext("precision=43 roundingMode=HALF_U");
			fail("IllegalArgumentException is expected");
		} catch (IllegalArgumentException e) {
		}
	}

	public void testMathContextString007() {
		try {
			new MathContext("precision= roundingMode=UP");
			fail("IllegalArgumentException is expected");
		} catch (IllegalArgumentException e) {
		}
	}

	public void testMathContextString008() {
		try {
			new MathContext("precision= roundingMode=UP");
			fail("IllegalArgumentException is expected");
		} catch (IllegalArgumentException e) {
		}
	}

	public void testMathContextString009() {
		try {
			new MathContext("roundingMode=UP");
			fail("IllegalArgumentException is expected");
		} catch (IllegalArgumentException e) {
		}
	}

	public void testMathContextString010() {
		try {
			new MathContext("precision=9999999999 roundingMode=UNNECESARY");
			fail("IllegalArgumentException is expected");
		} catch (IllegalArgumentException e) {
		}
	}

	public void testMathContextString011() {
		try {
			new MathContext("precision=1roundingMode=UNNECESARY");
			fail("IllegalArgumentException is expected");
		} catch (IllegalArgumentException e) {
		}
	}

	public void testMathContextString012() {
		try {
			RoundingMode.valueOf("UP");
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

}
