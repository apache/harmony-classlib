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

package ar.org.fitc.test.math.bigdecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;


import ar.org.fitc.test.util.Messages;
import junit.framework.TestCase;
/**
 * Test cases for
 * divide(BigDecimal,int,RoundingMode) 
 *
 */
public class TestBigDecimalDivideIntRoundingMode extends TestCase implements Messages{
	BigDecimal bigDec=null;
	
	
	public TestBigDecimalDivideIntRoundingMode(String name) {
		super(name);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	
	
	public void testDivideBigDecimalIntRoundingMode004() {
		try {
			bigDec= new BigDecimal("0");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode005() {
		try {
			bigDec= new BigDecimal("0");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode006() {
		try {
			bigDec= new BigDecimal("0");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode007() {
		try {
			bigDec= new BigDecimal("0");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode008() {
		try {
			bigDec= new BigDecimal("0");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode009() {
		try {
			bigDec= new BigDecimal("0");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode010() {
		try {
			bigDec= new BigDecimal("0");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.CEILING);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode011() {
		try {
			bigDec= new BigDecimal("0");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.CEILING);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode012() {
		try {
			bigDec= new BigDecimal("0");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.CEILING);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode013() {
		try {
			bigDec= new BigDecimal("0");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.FLOOR);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode014() {
		try {
			bigDec= new BigDecimal("0");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.FLOOR);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode015() {
		try {
			bigDec= new BigDecimal("0");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.FLOOR);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode016() {
		try {
			bigDec= new BigDecimal("0");  
			bigDec.divide(new BigDecimal("0"), 0, 4);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode017() {
		try {
			bigDec= new BigDecimal("0");  
			bigDec.divide(new BigDecimal("0"), 600, 4);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode018() {
		try {
			bigDec= new BigDecimal("0");  
			bigDec.divide(new BigDecimal("0"), -500, 4);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode019() {
		try {
			bigDec= new BigDecimal("0");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.HALF_DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode020() {
		try {
			bigDec= new BigDecimal("0");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.HALF_DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode021() {
		try {
			bigDec= new BigDecimal("0");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.HALF_DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode022() {
		try {
			bigDec= new BigDecimal("0");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.HALF_EVEN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode023() {
		try {
			bigDec= new BigDecimal("0");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.HALF_EVEN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode024() {
		try {
			bigDec= new BigDecimal("0");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.HALF_EVEN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode025() {
		try {
			bigDec= new BigDecimal("0");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode026() {
		try {
			bigDec= new BigDecimal("0");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode027() {
		try {
			bigDec= new BigDecimal("0");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	public void testDivideBigDecimalIntRoundingMode034() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode035() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode036() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode037() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode038() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode039() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode040() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode041() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode042() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode043() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode044() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode045() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode046() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0, 4));
	}
	
	public void testDivideBigDecimalIntRoundingMode047() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("1"), 600, 4));
	}
	
	public void testDivideBigDecimalIntRoundingMode048() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, 4));
	}
	
	public void testDivideBigDecimalIntRoundingMode049() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode050() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode051() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode052() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode053() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode054() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode055() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode056() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode057() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.UNNECESSARY));
	}
	
	
	public void testDivideBigDecimalIntRoundingMode064() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode065() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode066() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode067() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode068() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode069() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode070() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode071() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode072() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode073() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode074() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode075() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode076() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode077() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode078() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode079() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode080() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode081() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode082() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode083() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode084() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode085() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode086() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode087() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.UNNECESSARY));
	}
	
	
	public void testDivideBigDecimalIntRoundingMode094() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode095() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode096() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode097() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode098() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode099() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode100() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode101() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode102() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode103() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode104() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode105() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode106() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode107() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode108() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode109() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode110() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode111() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode112() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode113() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode114() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode115() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode116() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode117() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.UNNECESSARY));
	}
	
	
	public void testDivideBigDecimalIntRoundingMode124() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode125() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode126() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode127() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode128() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode129() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode130() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode131() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode132() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode133() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode134() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode135() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode136() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode137() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode138() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode139() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode140() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode141() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode142() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode143() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode144() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode145() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode146() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode147() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.UNNECESSARY));
	}
	
	
	public void testDivideBigDecimalIntRoundingMode154() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode155() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode156() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode157() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode158() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode159() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode160() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode161() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode162() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode163() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode164() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode165() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode166() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode167() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode168() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode169() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode170() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode171() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode172() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode173() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode174() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode175() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode176() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode177() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.UNNECESSARY));
	}
	
	
	public void testDivideBigDecimalIntRoundingMode184() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode185() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode186() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode187() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode188() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode189() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode190() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode191() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode192() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode193() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode194() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode195() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode196() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode197() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode198() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode199() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode200() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode201() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode202() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode203() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode204() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode205() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode206() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode207() {
		bigDec= new BigDecimal("0");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.UNNECESSARY));
	}
	
	
	public void testDivideBigDecimalIntRoundingMode214() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode215() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode216() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode217() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode218() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode219() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode220() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.CEILING);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode221() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.CEILING);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode222() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.CEILING);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode223() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.FLOOR);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode224() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.FLOOR);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode225() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.FLOOR);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode226() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.HALF_UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode227() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.HALF_UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode228() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.HALF_UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode229() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.HALF_DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode230() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.HALF_DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode231() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.HALF_DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode232() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.HALF_EVEN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode233() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.HALF_EVEN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode234() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.HALF_EVEN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode235() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode236() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode237() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	public void testDivideBigDecimalIntRoundingMode244() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode245() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode246() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode247() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode248() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode249() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode250() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode251() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode252() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode253() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode254() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode255() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode256() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode257() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode258() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode259() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode260() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode261() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode262() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode263() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode264() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode265() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode266() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode267() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("1"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	public void testDivideBigDecimalIntRoundingMode274() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-10"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode275() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-10.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode276() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode277() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-10"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode278() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-10.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode279() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode280() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-10"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode281() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-10.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode282() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode283() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-10"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode284() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-10.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode285() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode286() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-10"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode287() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-10.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode288() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode289() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-10"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode290() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-10.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode291() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode292() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-10"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode293() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-10.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode294() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode295() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-10"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode296() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-10.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode297() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	public void testDivideBigDecimalIntRoundingMode304() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001430"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode305() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001429.199138701469601817900815730210160797549498812659101141078310918181186467405837258301854902611851539552925536812148087833931271404818099594442290634487673271506134948428318754989895579819853827441094339046313402182639856389245184214145993703346916654990935272059959180586750997010557400772424932321668872485957213441231258475122259772474253119966408687837697979413235517598820640224895938976119877225138639712028919948364454188296730140432006354554879596510122867286198899806165140246425809140641483919381207690903318303926335802807002427051876659970891435674484547102259508057186155800234724094919664"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode306() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1.0316102760589687712197314074433556050036868898293327725877491019079725021928354687598478757384519543252901530335684000135119584969720199726547590876476392996018041615977441845486624023113343810367833570681029778085717948378491868353783550158320237774E+750"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode307() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001429"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode308() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001429.199138701469601817900815730210160797549498812659101141078310918181186467405837258301854902611851539552925536812148087833931271404818099594442290634487673271506134948428318754989895579819853827441094339046313402182639856389245184214145993703346916654990935272059959180586750997010557400772424932321668872485957213441231258475122259772474253119966408687837697979413235517598820640224895938976119877225138639712028919948364454188296730140432006354554879596510122867286198899806165140246425809140641483919381207690903318303926335802807002427051876659970891435674484547102259508057186155800234724094919663"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode309() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1.0316102760589687712197314074433556050036868898293327725877491019079725021928354687598478757384519543252901530335684000135119584969720199726547590876476392996018041615977441845486624023113343810367833570681029778085717948378491868353783550158320237773E+750"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode310() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001429"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode311() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001429.199138701469601817900815730210160797549498812659101141078310918181186467405837258301854902611851539552925536812148087833931271404818099594442290634487673271506134948428318754989895579819853827441094339046313402182639856389245184214145993703346916654990935272059959180586750997010557400772424932321668872485957213441231258475122259772474253119966408687837697979413235517598820640224895938976119877225138639712028919948364454188296730140432006354554879596510122867286198899806165140246425809140641483919381207690903318303926335802807002427051876659970891435674484547102259508057186155800234724094919663"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode312() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1.0316102760589687712197314074433556050036868898293327725877491019079725021928354687598478757384519543252901530335684000135119584969720199726547590876476392996018041615977441845486624023113343810367833570681029778085717948378491868353783550158320237773E+750"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode313() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001430"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode314() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001429.199138701469601817900815730210160797549498812659101141078310918181186467405837258301854902611851539552925536812148087833931271404818099594442290634487673271506134948428318754989895579819853827441094339046313402182639856389245184214145993703346916654990935272059959180586750997010557400772424932321668872485957213441231258475122259772474253119966408687837697979413235517598820640224895938976119877225138639712028919948364454188296730140432006354554879596510122867286198899806165140246425809140641483919381207690903318303926335802807002427051876659970891435674484547102259508057186155800234724094919664"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode315() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1.0316102760589687712197314074433556050036868898293327725877491019079725021928354687598478757384519543252901530335684000135119584969720199726547590876476392996018041615977441845486624023113343810367833570681029778085717948378491868353783550158320237774E+750"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode316() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001429"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode317() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001429.199138701469601817900815730210160797549498812659101141078310918181186467405837258301854902611851539552925536812148087833931271404818099594442290634487673271506134948428318754989895579819853827441094339046313402182639856389245184214145993703346916654990935272059959180586750997010557400772424932321668872485957213441231258475122259772474253119966408687837697979413235517598820640224895938976119877225138639712028919948364454188296730140432006354554879596510122867286198899806165140246425809140641483919381207690903318303926335802807002427051876659970891435674484547102259508057186155800234724094919664"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode318() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1.0316102760589687712197314074433556050036868898293327725877491019079725021928354687598478757384519543252901530335684000135119584969720199726547590876476392996018041615977441845486624023113343810367833570681029778085717948378491868353783550158320237774E+750"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode319() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001429"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode320() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001429.199138701469601817900815730210160797549498812659101141078310918181186467405837258301854902611851539552925536812148087833931271404818099594442290634487673271506134948428318754989895579819853827441094339046313402182639856389245184214145993703346916654990935272059959180586750997010557400772424932321668872485957213441231258475122259772474253119966408687837697979413235517598820640224895938976119877225138639712028919948364454188296730140432006354554879596510122867286198899806165140246425809140641483919381207690903318303926335802807002427051876659970891435674484547102259508057186155800234724094919664"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode321() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1.0316102760589687712197314074433556050036868898293327725877491019079725021928354687598478757384519543252901530335684000135119584969720199726547590876476392996018041615977441845486624023113343810367833570681029778085717948378491868353783550158320237774E+750"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode322() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001429"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode323() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001429.199138701469601817900815730210160797549498812659101141078310918181186467405837258301854902611851539552925536812148087833931271404818099594442290634487673271506134948428318754989895579819853827441094339046313402182639856389245184214145993703346916654990935272059959180586750997010557400772424932321668872485957213441231258475122259772474253119966408687837697979413235517598820640224895938976119877225138639712028919948364454188296730140432006354554879596510122867286198899806165140246425809140641483919381207690903318303926335802807002427051876659970891435674484547102259508057186155800234724094919664"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode324() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1.0316102760589687712197314074433556050036868898293327725877491019079725021928354687598478757384519543252901530335684000135119584969720199726547590876476392996018041615977441845486624023113343810367833570681029778085717948378491868353783550158320237774E+750"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode325() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode326() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode327() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	public void testDivideBigDecimalIntRoundingMode334() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode335() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875739E-507"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode336() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode337() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode338() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738E-507"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode339() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode340() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode341() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738E-507"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode342() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode343() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode344() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875739E-507"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode345() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode346() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode347() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738E-507"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode348() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode349() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode350() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738E-507"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode351() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode352() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode353() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738E-507"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode354() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode355() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode356() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode357() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	
	public void testDivideBigDecimalIntRoundingMode364() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode365() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875739E-507"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode366() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode367() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode368() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738E-507"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode369() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode370() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode371() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875739E-507"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode372() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode373() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode374() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738E-507"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode375() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode376() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode377() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738E-507"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode378() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode379() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode380() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738E-507"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode381() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode382() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode383() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738E-507"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode384() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode385() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode386() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode387() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	public void testDivideBigDecimalIntRoundingMode394() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode395() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-2.07618283657996465747258171570026336665397047403619606116247958273894317567384498906898536284277101127456329863670152203694414071308940624741181765637958115712082541977393356989838998590860013277971362704160926934754024929934011729503040601593075703334247029978337268565350480029822973616190782327395445903353340377107009228753367258545457176364452718737061776243106443801176229968770089038043098804744109500348969800789772318502643268876065123937540776452264425651675052708717312463591804312103312764220864348448761542832950787851E-70"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode396() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode397() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode398() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-2.07618283657996465747258171570026336665397047403619606116247958273894317567384498906898536284277101127456329863670152203694414071308940624741181765637958115712082541977393356989838998590860013277971362704160926934754024929934011729503040601593075703334247029978337268565350480029822973616190782327395445903353340377107009228753367258545457176364452718737061776243106443801176229968770089038043098804744109500348969800789772318502643268876065123937540776452264425651675052708717312463591804312103312764220864348448761542832950787850E-70"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode399() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode400() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode401() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-2.07618283657996465747258171570026336665397047403619606116247958273894317567384498906898536284277101127456329863670152203694414071308940624741181765637958115712082541977393356989838998590860013277971362704160926934754024929934011729503040601593075703334247029978337268565350480029822973616190782327395445903353340377107009228753367258545457176364452718737061776243106443801176229968770089038043098804744109500348969800789772318502643268876065123937540776452264425651675052708717312463591804312103312764220864348448761542832950787850E-70"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode402() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode403() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode404() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-2.07618283657996465747258171570026336665397047403619606116247958273894317567384498906898536284277101127456329863670152203694414071308940624741181765637958115712082541977393356989838998590860013277971362704160926934754024929934011729503040601593075703334247029978337268565350480029822973616190782327395445903353340377107009228753367258545457176364452718737061776243106443801176229968770089038043098804744109500348969800789772318502643268876065123937540776452264425651675052708717312463591804312103312764220864348448761542832950787851E-70"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode405() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode406() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode407() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-2.07618283657996465747258171570026336665397047403619606116247958273894317567384498906898536284277101127456329863670152203694414071308940624741181765637958115712082541977393356989838998590860013277971362704160926934754024929934011729503040601593075703334247029978337268565350480029822973616190782327395445903353340377107009228753367258545457176364452718737061776243106443801176229968770089038043098804744109500348969800789772318502643268876065123937540776452264425651675052708717312463591804312103312764220864348448761542832950787851E-70"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode408() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode409() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode410() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-2.07618283657996465747258171570026336665397047403619606116247958273894317567384498906898536284277101127456329863670152203694414071308940624741181765637958115712082541977393356989838998590860013277971362704160926934754024929934011729503040601593075703334247029978337268565350480029822973616190782327395445903353340377107009228753367258545457176364452718737061776243106443801176229968770089038043098804744109500348969800789772318502643268876065123937540776452264425651675052708717312463591804312103312764220864348448761542832950787851E-70"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode411() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode412() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode413() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("-2.07618283657996465747258171570026336665397047403619606116247958273894317567384498906898536284277101127456329863670152203694414071308940624741181765637958115712082541977393356989838998590860013277971362704160926934754024929934011729503040601593075703334247029978337268565350480029822973616190782327395445903353340377107009228753367258545457176364452718737061776243106443801176229968770089038043098804744109500348969800789772318502643268876065123937540776452264425651675052708717312463591804312103312764220864348448761542832950787851E-70"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode414() {
		bigDec= new BigDecimal("1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode415() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode416() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode417() {
		try {
			bigDec= new BigDecimal("1");  
			bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	public void testDivideBigDecimalIntRoundingMode424() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode425() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode426() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode427() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode428() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode429() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode430() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.CEILING);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode431() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.CEILING);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode432() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.CEILING);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode433() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.FLOOR);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode434() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.FLOOR);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode435() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.FLOOR);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode436() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.HALF_UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode437() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.HALF_UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode438() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.HALF_UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode439() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.HALF_DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode440() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.HALF_DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode441() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.HALF_DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode442() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.HALF_EVEN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode443() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.HALF_EVEN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode444() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.HALF_EVEN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode445() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode446() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode447() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	public void testDivideBigDecimalIntRoundingMode454() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode455() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("-0.100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode456() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode457() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode458() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("-0.100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode459() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode460() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode461() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("-0.100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode462() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode463() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode464() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("-0.100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode465() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode466() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode467() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("-0.100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode468() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode469() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode470() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("-0.100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode471() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode472() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode473() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("-0.100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode474() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode475() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("1"), 0, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode476() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("-0.100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode477() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("1"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	public void testDivideBigDecimalIntRoundingMode484() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode485() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode486() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode487() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode488() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode489() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode490() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode491() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode492() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode493() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode494() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode495() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode496() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode497() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode498() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode499() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode500() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode501() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode502() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode503() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode504() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode505() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode506() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode507() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	
	public void testDivideBigDecimalIntRoundingMode514() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000143"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode515() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000142.919913870146960181790081573021016079754949881265910114107831091818118646740583725830185490261185153955292553681214808783393127140481809959444229063448767327150613494842831875498989557981985382744109433904631340218263985638924518421414599370334691665499093527205995918058675099701055740077242493232166887248595721344123125847512225977247425311996640868783769797941323551759882064022489593897611987722513863971202891994836445418829673014043200635455487959651012286728619889980616514024642580914064148391938120769090331830392633580280700242705187665997089143567448454710225950805718615580023472409491967"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode516() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023778E+749"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode517() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000142"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode518() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000142.919913870146960181790081573021016079754949881265910114107831091818118646740583725830185490261185153955292553681214808783393127140481809959444229063448767327150613494842831875498989557981985382744109433904631340218263985638924518421414599370334691665499093527205995918058675099701055740077242493232166887248595721344123125847512225977247425311996640868783769797941323551759882064022489593897611987722513863971202891994836445418829673014043200635455487959651012286728619889980616514024642580914064148391938120769090331830392633580280700242705187665997089143567448454710225950805718615580023472409491966"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode519() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777E+749"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode520() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000143"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode521() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000142.919913870146960181790081573021016079754949881265910114107831091818118646740583725830185490261185153955292553681214808783393127140481809959444229063448767327150613494842831875498989557981985382744109433904631340218263985638924518421414599370334691665499093527205995918058675099701055740077242493232166887248595721344123125847512225977247425311996640868783769797941323551759882064022489593897611987722513863971202891994836445418829673014043200635455487959651012286728619889980616514024642580914064148391938120769090331830392633580280700242705187665997089143567448454710225950805718615580023472409491967"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode522() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023778E+749"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode523() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000142"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode524() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000142.919913870146960181790081573021016079754949881265910114107831091818118646740583725830185490261185153955292553681214808783393127140481809959444229063448767327150613494842831875498989557981985382744109433904631340218263985638924518421414599370334691665499093527205995918058675099701055740077242493232166887248595721344123125847512225977247425311996640868783769797941323551759882064022489593897611987722513863971202891994836445418829673014043200635455487959651012286728619889980616514024642580914064148391938120769090331830392633580280700242705187665997089143567448454710225950805718615580023472409491966"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode525() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777E+749"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode526() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000143"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode527() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000142.919913870146960181790081573021016079754949881265910114107831091818118646740583725830185490261185153955292553681214808783393127140481809959444229063448767327150613494842831875498989557981985382744109433904631340218263985638924518421414599370334691665499093527205995918058675099701055740077242493232166887248595721344123125847512225977247425311996640868783769797941323551759882064022489593897611987722513863971202891994836445418829673014043200635455487959651012286728619889980616514024642580914064148391938120769090331830392633580280700242705187665997089143567448454710225950805718615580023472409491966"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode528() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777E+749"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode529() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000143"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode530() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000142.919913870146960181790081573021016079754949881265910114107831091818118646740583725830185490261185153955292553681214808783393127140481809959444229063448767327150613494842831875498989557981985382744109433904631340218263985638924518421414599370334691665499093527205995918058675099701055740077242493232166887248595721344123125847512225977247425311996640868783769797941323551759882064022489593897611987722513863971202891994836445418829673014043200635455487959651012286728619889980616514024642580914064148391938120769090331830392633580280700242705187665997089143567448454710225950805718615580023472409491966"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode531() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777E+749"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode532() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000143"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode533() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000142.919913870146960181790081573021016079754949881265910114107831091818118646740583725830185490261185153955292553681214808783393127140481809959444229063448767327150613494842831875498989557981985382744109433904631340218263985638924518421414599370334691665499093527205995918058675099701055740077242493232166887248595721344123125847512225977247425311996640868783769797941323551759882064022489593897611987722513863971202891994836445418829673014043200635455487959651012286728619889980616514024642580914064148391938120769090331830392633580280700242705187665997089143567448454710225950805718615580023472409491966"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode534() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777E+749"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode535() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode536() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode537() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	public void testDivideBigDecimalIntRoundingMode544() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode545() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1.03161027605896877121973140744335560500368688982933277258774910190797250219283546875984787574E-508"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode546() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode547() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode548() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1.03161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573E-508"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode549() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode550() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode551() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1.03161027605896877121973140744335560500368688982933277258774910190797250219283546875984787574E-508"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode552() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode553() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode554() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1.03161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573E-508"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode555() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode556() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode557() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1.03161027605896877121973140744335560500368688982933277258774910190797250219283546875984787574E-508"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode558() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode559() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode560() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1.03161027605896877121973140744335560500368688982933277258774910190797250219283546875984787574E-508"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode561() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode562() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode563() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1.03161027605896877121973140744335560500368688982933277258774910190797250219283546875984787574E-508"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode564() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode565() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode566() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode567() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	public void testDivideBigDecimalIntRoundingMode574() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode575() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("-1.03161027605896877121973140744335560500368688982933277258774910190797250219283546875984787574E-508"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode576() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode577() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode578() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("-1.03161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573E-508"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode579() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode580() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode581() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("-1.03161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573E-508"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode582() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode583() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode584() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("-1.03161027605896877121973140744335560500368688982933277258774910190797250219283546875984787574E-508"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode585() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode586() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode587() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("-1.03161027605896877121973140744335560500368688982933277258774910190797250219283546875984787574E-508"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode588() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode589() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode590() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("-1.03161027605896877121973140744335560500368688982933277258774910190797250219283546875984787574E-508"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode591() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode592() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode593() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("-1.03161027605896877121973140744335560500368688982933277258774910190797250219283546875984787574E-508"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode594() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode595() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode596() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode597() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	public void testDivideBigDecimalIntRoundingMode604() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode605() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("2.0761828365799646574725817157002633666539704740361960611624795827389431756738449890689853628427710112745632986367015220369441407130894062474118176563795811571208254197739335698983899859086001327797136270416092693475402492993401172950304060159307570333424702997833726856535048002982297361619078232739544590335334037710700922875336725854545717636445271873706177624310644380117622996877008903804309880474410950034896980078977231850264326887606512393754077645226442565167505270871731246359180431210331276422086434844876154283295078786E-71"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode606() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode607() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode608() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("2.0761828365799646574725817157002633666539704740361960611624795827389431756738449890689853628427710112745632986367015220369441407130894062474118176563795811571208254197739335698983899859086001327797136270416092693475402492993401172950304060159307570333424702997833726856535048002982297361619078232739544590335334037710700922875336725854545717636445271873706177624310644380117622996877008903804309880474410950034896980078977231850264326887606512393754077645226442565167505270871731246359180431210331276422086434844876154283295078785E-71"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode609() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode610() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode611() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("2.0761828365799646574725817157002633666539704740361960611624795827389431756738449890689853628427710112745632986367015220369441407130894062474118176563795811571208254197739335698983899859086001327797136270416092693475402492993401172950304060159307570333424702997833726856535048002982297361619078232739544590335334037710700922875336725854545717636445271873706177624310644380117622996877008903804309880474410950034896980078977231850264326887606512393754077645226442565167505270871731246359180431210331276422086434844876154283295078786E-71"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode612() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode613() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode614() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("2.0761828365799646574725817157002633666539704740361960611624795827389431756738449890689853628427710112745632986367015220369441407130894062474118176563795811571208254197739335698983899859086001327797136270416092693475402492993401172950304060159307570333424702997833726856535048002982297361619078232739544590335334037710700922875336725854545717636445271873706177624310644380117622996877008903804309880474410950034896980078977231850264326887606512393754077645226442565167505270871731246359180431210331276422086434844876154283295078785E-71"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode615() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode616() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode617() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("2.0761828365799646574725817157002633666539704740361960611624795827389431756738449890689853628427710112745632986367015220369441407130894062474118176563795811571208254197739335698983899859086001327797136270416092693475402492993401172950304060159307570333424702997833726856535048002982297361619078232739544590335334037710700922875336725854545717636445271873706177624310644380117622996877008903804309880474410950034896980078977231850264326887606512393754077645226442565167505270871731246359180431210331276422086434844876154283295078785E-71"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode618() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode619() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode620() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("2.0761828365799646574725817157002633666539704740361960611624795827389431756738449890689853628427710112745632986367015220369441407130894062474118176563795811571208254197739335698983899859086001327797136270416092693475402492993401172950304060159307570333424702997833726856535048002982297361619078232739544590335334037710700922875336725854545717636445271873706177624310644380117622996877008903804309880474410950034896980078977231850264326887606512393754077645226442565167505270871731246359180431210331276422086434844876154283295078785E-71"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode621() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode622() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode623() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("2.0761828365799646574725817157002633666539704740361960611624795827389431756738449890689853628427710112745632986367015220369441407130894062474118176563795811571208254197739335698983899859086001327797136270416092693475402492993401172950304060159307570333424702997833726856535048002982297361619078232739544590335334037710700922875336725854545717636445271873706177624310644380117622996877008903804309880474410950034896980078977231850264326887606512393754077645226442565167505270871731246359180431210331276422086434844876154283295078785E-71"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode624() {
		bigDec= new BigDecimal("-0.1");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode625() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode626() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode627() {
		try {
			bigDec= new BigDecimal("-0.1");  
			bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	public void testDivideBigDecimalIntRoundingMode634() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode635() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode636() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode637() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode638() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode639() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode640() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.CEILING);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode641() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.CEILING);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode642() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.CEILING);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode643() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.FLOOR);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode644() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.FLOOR);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode645() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.FLOOR);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode646() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.HALF_UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode647() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.HALF_UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode648() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.HALF_UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode649() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.HALF_DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode650() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.HALF_DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode651() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.HALF_DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode652() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.HALF_EVEN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode653() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.HALF_EVEN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode654() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.HALF_EVEN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode655() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode656() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode657() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	public void testDivideBigDecimalIntRoundingMode664() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode665() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("-1E-600"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode666() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode667() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode668() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode669() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode670() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode671() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode672() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode673() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode674() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("-1E-600"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode675() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode676() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode677() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode678() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode679() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode680() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode681() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode682() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode683() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode684() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode685() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("1"), 0, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode686() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("1"), 600, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode687() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("1"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	public void testDivideBigDecimalIntRoundingMode694() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode695() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("1E-600"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode696() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode697() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode698() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode699() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode700() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode701() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("1E-600"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode702() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode703() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode704() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode705() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode706() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode707() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode708() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode709() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode710() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode711() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode712() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode713() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode714() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode715() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode716() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode717() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	public void testDivideBigDecimalIntRoundingMode724() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode725() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode726() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode727() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode728() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode729() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode730() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode731() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode732() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode733() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode734() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode735() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode736() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode737() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode738() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode739() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode740() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode741() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode742() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode743() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode744() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode745() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode746() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode747() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	public void testDivideBigDecimalIntRoundingMode754() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode755() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("1E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode756() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode757() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode758() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode759() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode760() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode761() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("1E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode762() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode763() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode764() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode765() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode766() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode767() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode768() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode769() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode770() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode771() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode772() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode773() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode774() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode775() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode776() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode777() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	public void testDivideBigDecimalIntRoundingMode784() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode785() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("-1E-600"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode786() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode787() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode788() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode789() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode790() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode791() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode792() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode793() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode794() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("-1E-600"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode795() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode796() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode797() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode798() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode799() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode800() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode801() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode802() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode803() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode804() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode805() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode806() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode807() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	public void testDivideBigDecimalIntRoundingMode814() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode815() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("1E-600"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode816() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode817() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode818() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode819() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode820() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode821() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("1E-600"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode822() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode823() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode824() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode825() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode826() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode827() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode828() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode829() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode830() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode831() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode832() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode833() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode834() {
		bigDec= new BigDecimal("-9.693583160302274182E-751");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode835() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode836() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode837() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E-751");  
			bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	public void testDivideBigDecimalIntRoundingMode844() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E+506");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode845() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E+506");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode846() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E+506");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode847() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E+506");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode848() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E+506");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode849() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E+506");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode850() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E+506");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.CEILING);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode851() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E+506");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.CEILING);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode852() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E+506");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.CEILING);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode853() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E+506");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.FLOOR);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode854() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E+506");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.FLOOR);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode855() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E+506");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.FLOOR);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode856() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E+506");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.HALF_UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode857() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E+506");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.HALF_UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode858() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E+506");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.HALF_UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode859() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E+506");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.HALF_DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode860() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E+506");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.HALF_DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode861() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E+506");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.HALF_DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode862() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E+506");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.HALF_EVEN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode863() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E+506");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.HALF_EVEN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode864() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E+506");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.HALF_EVEN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode865() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E+506");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode866() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E+506");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode867() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E+506");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	public void testDivideBigDecimalIntRoundingMode874() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode875() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode876() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-9.693584E+506"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode877() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode878() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode879() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-9.693583E+506"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode880() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode881() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode882() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-9.693583E+506"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode883() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode884() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode885() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-9.693584E+506"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode886() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode887() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode888() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-9.693583E+506"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode889() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode890() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode891() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-9.693583E+506"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode892() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode893() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode894() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-9.693583E+506"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode895() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode896() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode897() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E+506");  
			bigDec.divide(new BigDecimal("1"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	public void testDivideBigDecimalIntRoundingMode904() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode905() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode906() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("9.6935832E+507"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode907() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode908() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode909() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("9.6935831E+507"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode910() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode911() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode912() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("9.6935832E+507"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode913() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode914() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode915() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("9.6935831E+507"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode916() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode917() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode918() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("9.6935832E+507"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode919() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode920() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode921() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("9.6935832E+507"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode922() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode923() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode924() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("9.6935832E+507"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode925() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode926() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode927() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E+506");  
			bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	public void testDivideBigDecimalIntRoundingMode934() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode935() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode936() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode937() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode938() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode939() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode940() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode941() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode942() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode943() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode944() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode945() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode946() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode947() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode948() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode949() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode950() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode951() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode952() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode953() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode954() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode955() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode956() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode957() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.UNNECESSARY));
	}
	
	
	public void testDivideBigDecimalIntRoundingMode964() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode965() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode966() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode967() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode968() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode969() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode970() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode971() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode972() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode973() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode974() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode975() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode976() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode977() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode978() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode979() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode980() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode981() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode982() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode983() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode984() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode985() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode986() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode987() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E+506");  
			bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	public void testDivideBigDecimalIntRoundingMode994() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode995() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode996() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode997() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode998() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode999() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1000() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1001() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1002() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1003() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1004() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1005() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1006() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1007() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1008() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1009() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1010() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1011() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1012() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1013() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1014() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1015() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode1016() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("-1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode1017() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E+506");  
			bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	public void testDivideBigDecimalIntRoundingMode1024() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058821"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1025() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820.600697140157680714794373765790240804089441878486603728799067422180952273406230765272142281448957283104588253356961565193923906596548490581764714131876932339280776672819054636424976476474469934016821834821980305484404905986074075443960732819806095082362128566355394772407583758319664078598513365217443156368163705514869753994013597149383596888184010989403638508885897718207827287681416208232665231672804875130753611630827868366205269578391717093187385607640345830863398349172307240577537520856375700516696804539730328639573594377497347920664344054413331897298544423309827730490403500573622217915775290"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1026() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1027() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1028() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820.600697140157680714794373765790240804089441878486603728799067422180952273406230765272142281448957283104588253356961565193923906596548490581764714131876932339280776672819054636424976476474469934016821834821980305484404905986074075443960732819806095082362128566355394772407583758319664078598513365217443156368163705514869753994013597149383596888184010989403638508885897718207827287681416208232665231672804875130753611630827868366205269578391717093187385607640345830863398349172307240577537520856375700516696804539730328639573594377497347920664344054413331897298544423309827730490403500573622217915775289"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1029() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1030() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058821"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1031() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820.600697140157680714794373765790240804089441878486603728799067422180952273406230765272142281448957283104588253356961565193923906596548490581764714131876932339280776672819054636424976476474469934016821834821980305484404905986074075443960732819806095082362128566355394772407583758319664078598513365217443156368163705514869753994013597149383596888184010989403638508885897718207827287681416208232665231672804875130753611630827868366205269578391717093187385607640345830863398349172307240577537520856375700516696804539730328639573594377497347920664344054413331897298544423309827730490403500573622217915775290"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1032() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1033() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1034() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820.600697140157680714794373765790240804089441878486603728799067422180952273406230765272142281448957283104588253356961565193923906596548490581764714131876932339280776672819054636424976476474469934016821834821980305484404905986074075443960732819806095082362128566355394772407583758319664078598513365217443156368163705514869753994013597149383596888184010989403638508885897718207827287681416208232665231672804875130753611630827868366205269578391717093187385607640345830863398349172307240577537520856375700516696804539730328639573594377497347920664344054413331897298544423309827730490403500573622217915775289"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1035() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1036() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058821"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1037() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820.600697140157680714794373765790240804089441878486603728799067422180952273406230765272142281448957283104588253356961565193923906596548490581764714131876932339280776672819054636424976476474469934016821834821980305484404905986074075443960732819806095082362128566355394772407583758319664078598513365217443156368163705514869753994013597149383596888184010989403638508885897718207827287681416208232665231672804875130753611630827868366205269578391717093187385607640345830863398349172307240577537520856375700516696804539730328639573594377497347920664344054413331897298544423309827730490403500573622217915775289"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1038() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1039() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058821"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1040() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820.600697140157680714794373765790240804089441878486603728799067422180952273406230765272142281448957283104588253356961565193923906596548490581764714131876932339280776672819054636424976476474469934016821834821980305484404905986074075443960732819806095082362128566355394772407583758319664078598513365217443156368163705514869753994013597149383596888184010989403638508885897718207827287681416208232665231672804875130753611630827868366205269578391717093187385607640345830863398349172307240577537520856375700516696804539730328639573594377497347920664344054413331897298544423309827730490403500573622217915775289"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1041() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1042() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058821"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1043() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820.600697140157680714794373765790240804089441878486603728799067422180952273406230765272142281448957283104588253356961565193923906596548490581764714131876932339280776672819054636424976476474469934016821834821980305484404905986074075443960732819806095082362128566355394772407583758319664078598513365217443156368163705514869753994013597149383596888184010989403638508885897718207827287681416208232665231672804875130753611630827868366205269578391717093187385607640345830863398349172307240577537520856375700516696804539730328639573594377497347920664344054413331897298544423309827730490403500573622217915775289"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1044() {
		bigDec= new BigDecimal("-9.693583160302274182E+506");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1045() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E+506");  
			bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1046() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E+506");  
			bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1047() {
		try {
			bigDec= new BigDecimal("-9.693583160302274182E+506");  
			bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	public void testDivideBigDecimalIntRoundingMode1054() {
		try {
			bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1055() {
		try {
			bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1056() {
		try {
			bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1057() {
		try {
			bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1058() {
		try {
			bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1059() {
		try {
			bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1060() {
		try {
			bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.CEILING);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1061() {
		try {
			bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.CEILING);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1062() {
		try {
			bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.CEILING);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1063() {
		try {
			bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.FLOOR);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1064() {
		try {
			bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.FLOOR);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1065() {
		try {
			bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.FLOOR);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1066() {
		try {
			bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.HALF_UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1067() {
		try {
			bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.HALF_UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1068() {
		try {
			bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.HALF_UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1069() {
		try {
			bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.HALF_DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1070() {
		try {
			bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.HALF_DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1071() {
		try {
			bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.HALF_DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1072() {
		try {
			bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.HALF_EVEN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1073() {
		try {
			bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.HALF_EVEN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1074() {
		try {
			bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.HALF_EVEN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1075() {
		try {
			bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1076() {
		try {
			bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1077() {
		try {
			bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	public void testDivideBigDecimalIntRoundingMode1084() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1085() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1086() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("9.693584E+506"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1087() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1088() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1089() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("9.693583E+506"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1090() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1091() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1092() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("9.693584E+506"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1093() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1094() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1095() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("9.693583E+506"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1096() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1097() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1098() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("9.693583E+506"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1099() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1100() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1101() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("9.693583E+506"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1102() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1103() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1104() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("9.693583E+506"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1105() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode1106() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode1107() {
		try {
			bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
			bigDec.divide(new BigDecimal("1"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	public void testDivideBigDecimalIntRoundingMode1114() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1115() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1116() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-9.6935832E+507"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1117() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1118() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1119() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-9.6935831E+507"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1120() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1121() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1122() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-9.6935831E+507"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1123() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1124() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1125() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-9.6935832E+507"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1126() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1127() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1128() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-9.6935832E+507"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1129() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1130() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1131() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-9.6935832E+507"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1132() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1133() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1134() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-9.6935832E+507"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1135() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode1136() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode1137() {
		try {
			bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
			bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	public void testDivideBigDecimalIntRoundingMode1144() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1145() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1146() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1147() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1148() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1149() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1150() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1151() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1152() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1153() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1154() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1155() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1156() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1157() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1158() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1159() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1160() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1161() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1162() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1163() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1164() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1165() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode1166() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode1167() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.UNNECESSARY));
	}
	
	
	public void testDivideBigDecimalIntRoundingMode1174() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1175() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1176() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1177() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1178() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1179() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1180() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1181() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1182() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1183() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1184() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1185() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1186() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1187() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1188() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1189() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1190() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1191() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1192() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1193() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1194() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1195() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode1196() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode1197() {
		try {
			bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
			bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	public void testDivideBigDecimalIntRoundingMode1204() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1205() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1206() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1207() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1208() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1209() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1210() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1211() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1212() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1213() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1214() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1215() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1216() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1217() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1218() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1219() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1220() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1221() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1222() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1223() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1224() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1225() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode1226() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode1227() {
		try {
			bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
			bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	public void testDivideBigDecimalIntRoundingMode1234() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058821"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1235() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820.600697140157680714794373765790240804089441878486603728799067422180952273406230765272142281448957283104588253356961565193923906596548490581764714131876932339280776672819054636424976476474469934016821834821980305484404905986074075443960732819806095082362128566355394772407583758319664078598513365217443156368163705514869753994013597149383596888184010989403638508885897718207827287681416208232665231672804875130753611630827868366205269578391717093187385607640345830863398349172307240577537520856375700516696804539730328639573594377497347920664344054413331897298544423309827730490403500573622217915775290"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1236() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1237() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1238() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820.600697140157680714794373765790240804089441878486603728799067422180952273406230765272142281448957283104588253356961565193923906596548490581764714131876932339280776672819054636424976476474469934016821834821980305484404905986074075443960732819806095082362128566355394772407583758319664078598513365217443156368163705514869753994013597149383596888184010989403638508885897718207827287681416208232665231672804875130753611630827868366205269578391717093187385607640345830863398349172307240577537520856375700516696804539730328639573594377497347920664344054413331897298544423309827730490403500573622217915775289"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1239() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1240() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1241() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820.600697140157680714794373765790240804089441878486603728799067422180952273406230765272142281448957283104588253356961565193923906596548490581764714131876932339280776672819054636424976476474469934016821834821980305484404905986074075443960732819806095082362128566355394772407583758319664078598513365217443156368163705514869753994013597149383596888184010989403638508885897718207827287681416208232665231672804875130753611630827868366205269578391717093187385607640345830863398349172307240577537520856375700516696804539730328639573594377497347920664344054413331897298544423309827730490403500573622217915775289"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1242() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1243() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058821"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1244() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820.600697140157680714794373765790240804089441878486603728799067422180952273406230765272142281448957283104588253356961565193923906596548490581764714131876932339280776672819054636424976476474469934016821834821980305484404905986074075443960732819806095082362128566355394772407583758319664078598513365217443156368163705514869753994013597149383596888184010989403638508885897718207827287681416208232665231672804875130753611630827868366205269578391717093187385607640345830863398349172307240577537520856375700516696804539730328639573594377497347920664344054413331897298544423309827730490403500573622217915775290"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1245() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1246() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058821"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1247() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820.600697140157680714794373765790240804089441878486603728799067422180952273406230765272142281448957283104588253356961565193923906596548490581764714131876932339280776672819054636424976476474469934016821834821980305484404905986074075443960732819806095082362128566355394772407583758319664078598513365217443156368163705514869753994013597149383596888184010989403638508885897718207827287681416208232665231672804875130753611630827868366205269578391717093187385607640345830863398349172307240577537520856375700516696804539730328639573594377497347920664344054413331897298544423309827730490403500573622217915775289"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1248() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1249() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058821"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1250() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820.600697140157680714794373765790240804089441878486603728799067422180952273406230765272142281448957283104588253356961565193923906596548490581764714131876932339280776672819054636424976476474469934016821834821980305484404905986074075443960732819806095082362128566355394772407583758319664078598513365217443156368163705514869753994013597149383596888184010989403638508885897718207827287681416208232665231672804875130753611630827868366205269578391717093187385607640345830863398349172307240577537520856375700516696804539730328639573594377497347920664344054413331897298544423309827730490403500573622217915775289"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1251() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1252() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058821"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1253() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820.600697140157680714794373765790240804089441878486603728799067422180952273406230765272142281448957283104588253356961565193923906596548490581764714131876932339280776672819054636424976476474469934016821834821980305484404905986074075443960732819806095082362128566355394772407583758319664078598513365217443156368163705514869753994013597149383596888184010989403638508885897718207827287681416208232665231672804875130753611630827868366205269578391717093187385607640345830863398349172307240577537520856375700516696804539730328639573594377497347920664344054413331897298544423309827730490403500573622217915775289"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1254() {
		bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1255() {
		try {
			bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
			bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1256() {
		try {
			bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
			bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1257() {
		try {
			bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
			bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	public void testDivideBigDecimalIntRoundingMode1264() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1265() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1266() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1267() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1268() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1269() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1270() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.CEILING);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1271() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.CEILING);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1272() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.CEILING);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1273() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.FLOOR);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1274() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.FLOOR);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1275() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.FLOOR);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1276() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.HALF_UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1277() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.HALF_UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1278() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.HALF_UP);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1279() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.HALF_DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1280() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.HALF_DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1281() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.HALF_DOWN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1282() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.HALF_EVEN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1283() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.HALF_EVEN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1284() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.HALF_EVEN);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1285() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("0"), 0, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1286() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("0"), 600, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1287() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("0"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	public void testDivideBigDecimalIntRoundingMode1294() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454546"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1295() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.489418974861534118511615741440741711100081450415045057404877897145555048970478904478601045961404506486044156704456048646468889789456123156454897481952603205618400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1296() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1297() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1298() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.489418974861534118511615741440741711100081450415045057404877897145555048970478904478601045961404506486044156704456048646468889789456123156454897481952603205618400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1299() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1300() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1301() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.489418974861534118511615741440741711100081450415045057404877897145555048970478904478601045961404506486044156704456048646468889789456123156454897481952603205618400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1302() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1303() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454546"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1304() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.489418974861534118511615741440741711100081450415045057404877897145555048970478904478601045961404506486044156704456048646468889789456123156454897481952603205618400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1305() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1306() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1307() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.489418974861534118511615741440741711100081450415045057404877897145555048970478904478601045961404506486044156704456048646468889789456123156454897481952603205618400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1308() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1309() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1310() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.489418974861534118511615741440741711100081450415045057404877897145555048970478904478601045961404506486044156704456048646468889789456123156454897481952603205618400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1311() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1312() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545"),bigDec.divide(new BigDecimal("1"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1313() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.489418974861534118511615741440741711100081450415045057404877897145555048970478904478601045961404506486044156704456048646468889789456123156454897481952603205618400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1314() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1315() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("1"), 0, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1316() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.489418974861534118511615741440741711100081450415045057404877897145555048970478904478601045961404506486044156704456048646468889789456123156454897481952603205618400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode1317() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("1"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	
	public void testDivideBigDecimalIntRoundingMode1324() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545455"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1325() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545454.894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1326() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1327() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545454"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1328() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545454.894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1329() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1330() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545455"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1331() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545454.894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1332() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1333() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545454"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1334() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545454.894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1335() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1336() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545455"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1337() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545454.894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1338() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1339() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545455"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1340() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545454.894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1341() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1342() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545455"),bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1343() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545454.894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1344() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1345() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("-0.1"), 0, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1346() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545454.894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode1347() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("-0.1"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	public void testDivideBigDecimalIntRoundingMode1354() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104832"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1355() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831.234670010310232080641018922418165747657291206102003862557230423954466053749071109052746961449830706086887987174301346041298269442387249029004750207613352218549424791426670691975678321162685302797300808545755910788399634722354981614992546349465999641366678987834008920484248159251353742322182885688156967105877992558581384513294124864743673409730540288715958165237460026624110163892636258018285514897669873081468259195112260489735506953890338342045799941324779393154801625759578869615975231239708726328681560393271121242811968977022340694259974262284789042293294974032928279705343278159631547388004140"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1356() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("4.9687833743886942356046508927943349662660903678934112903068506830221887811176600267263057539834034082552694058703603821563011036485984139738284768331846047491279568482630000021506029060538222023839998301549106997527016584630260383404030576260052457429787400116895597065859254362399857284607609595457217248297462250303179E+819"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1357() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1358() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831.234670010310232080641018922418165747657291206102003862557230423954466053749071109052746961449830706086887987174301346041298269442387249029004750207613352218549424791426670691975678321162685302797300808545755910788399634722354981614992546349465999641366678987834008920484248159251353742322182885688156967105877992558581384513294124864743673409730540288715958165237460026624110163892636258018285514897669873081468259195112260489735506953890338342045799941324779393154801625759578869615975231239708726328681560393271121242811968977022340694259974262284789042293294974032928279705343278159631547388004139"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1359() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("4.9687833743886942356046508927943349662660903678934112903068506830221887811176600267263057539834034082552694058703603821563011036485984139738284768331846047491279568482630000021506029060538222023839998301549106997527016584630260383404030576260052457429787400116895597065859254362399857284607609595457217248297462250303178E+819"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1360() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104832"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1361() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831.234670010310232080641018922418165747657291206102003862557230423954466053749071109052746961449830706086887987174301346041298269442387249029004750207613352218549424791426670691975678321162685302797300808545755910788399634722354981614992546349465999641366678987834008920484248159251353742322182885688156967105877992558581384513294124864743673409730540288715958165237460026624110163892636258018285514897669873081468259195112260489735506953890338342045799941324779393154801625759578869615975231239708726328681560393271121242811968977022340694259974262284789042293294974032928279705343278159631547388004140"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1362() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("4.9687833743886942356046508927943349662660903678934112903068506830221887811176600267263057539834034082552694058703603821563011036485984139738284768331846047491279568482630000021506029060538222023839998301549106997527016584630260383404030576260052457429787400116895597065859254362399857284607609595457217248297462250303179E+819"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1363() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1364() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831.234670010310232080641018922418165747657291206102003862557230423954466053749071109052746961449830706086887987174301346041298269442387249029004750207613352218549424791426670691975678321162685302797300808545755910788399634722354981614992546349465999641366678987834008920484248159251353742322182885688156967105877992558581384513294124864743673409730540288715958165237460026624110163892636258018285514897669873081468259195112260489735506953890338342045799941324779393154801625759578869615975231239708726328681560393271121242811968977022340694259974262284789042293294974032928279705343278159631547388004139"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1365() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("4.9687833743886942356046508927943349662660903678934112903068506830221887811176600267263057539834034082552694058703603821563011036485984139738284768331846047491279568482630000021506029060538222023839998301549106997527016584630260383404030576260052457429787400116895597065859254362399857284607609595457217248297462250303178E+819"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1366() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1367() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831.234670010310232080641018922418165747657291206102003862557230423954466053749071109052746961449830706086887987174301346041298269442387249029004750207613352218549424791426670691975678321162685302797300808545755910788399634722354981614992546349465999641366678987834008920484248159251353742322182885688156967105877992558581384513294124864743673409730540288715958165237460026624110163892636258018285514897669873081468259195112260489735506953890338342045799941324779393154801625759578869615975231239708726328681560393271121242811968977022340694259974262284789042293294974032928279705343278159631547388004139"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1368() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("4.9687833743886942356046508927943349662660903678934112903068506830221887811176600267263057539834034082552694058703603821563011036485984139738284768331846047491279568482630000021506029060538222023839998301549106997527016584630260383404030576260052457429787400116895597065859254362399857284607609595457217248297462250303178E+819"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1369() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1370() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831.234670010310232080641018922418165747657291206102003862557230423954466053749071109052746961449830706086887987174301346041298269442387249029004750207613352218549424791426670691975678321162685302797300808545755910788399634722354981614992546349465999641366678987834008920484248159251353742322182885688156967105877992558581384513294124864743673409730540288715958165237460026624110163892636258018285514897669873081468259195112260489735506953890338342045799941324779393154801625759578869615975231239708726328681560393271121242811968977022340694259974262284789042293294974032928279705343278159631547388004139"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1371() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("4.9687833743886942356046508927943349662660903678934112903068506830221887811176600267263057539834034082552694058703603821563011036485984139738284768331846047491279568482630000021506029060538222023839998301549106997527016584630260383404030576260052457429787400116895597065859254362399857284607609595457217248297462250303178E+819"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1372() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1373() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831.234670010310232080641018922418165747657291206102003862557230423954466053749071109052746961449830706086887987174301346041298269442387249029004750207613352218549424791426670691975678321162685302797300808545755910788399634722354981614992546349465999641366678987834008920484248159251353742322182885688156967105877992558581384513294124864743673409730540288715958165237460026624110163892636258018285514897669873081468259195112260489735506953890338342045799941324779393154801625759578869615975231239708726328681560393271121242811968977022340694259974262284789042293294974032928279705343278159631547388004139"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1374() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("4.9687833743886942356046508927943349662660903678934112903068506830221887811176600267263057539834034082552694058703603821563011036485984139738284768331846047491279568482630000021506029060538222023839998301549106997527016584630260383404030576260052457429787400116895597065859254362399857284607609595457217248297462250303178E+819"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1375() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1376() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1377() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	public void testDivideBigDecimalIntRoundingMode1384() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1385() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("4.968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127957E-438"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1386() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1387() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1388() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("4.968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956E-438"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1389() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1390() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1391() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("4.968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127957E-438"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1392() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1393() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1394() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("4.968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956E-438"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1395() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1396() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1397() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("4.968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127957E-438"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1398() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1399() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1400() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("4.968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127957E-438"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1401() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1402() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1403() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("4.968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127957E-438"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1404() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1405() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1406() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1407() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	
	public void testDivideBigDecimalIntRoundingMode1414() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1415() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("-4.968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127957E-438"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1416() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1417() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1418() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("-4.968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956E-438"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1419() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1420() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1421() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("-4.968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956E-438"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1422() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1423() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1424() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("-4.968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127957E-438"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1425() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1426() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1427() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("-4.968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127957E-438"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1428() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1429() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1430() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("-4.968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127957E-438"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1431() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1432() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1433() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("-4.968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127957E-438"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1434() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1435() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1436() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	public void testDivideBigDecimalIntRoundingMode1437() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
	
	
	public void testDivideBigDecimalIntRoundingMode1444() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1445() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1446() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1447() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1448() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1449() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1450() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1451() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1452() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.CEILING));
	}
	
	public void testDivideBigDecimalIntRoundingMode1453() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1454() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1455() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.FLOOR));
	}
	
	public void testDivideBigDecimalIntRoundingMode1456() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1457() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1458() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.HALF_UP));
	}
	
	public void testDivideBigDecimalIntRoundingMode1459() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1460() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1461() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.HALF_DOWN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1462() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1463() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1464() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.HALF_EVEN));
	}
	
	public void testDivideBigDecimalIntRoundingMode1465() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode1466() {
		bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
		assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, RoundingMode.UNNECESSARY));
	}
	
	public void testDivideBigDecimalIntRoundingMode1467() {
		try {
			bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
			bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, RoundingMode.UNNECESSARY);
			fail(msgRaise+"ArithmeticException");
		} catch (ArithmeticException e) {
		}
	}
}
