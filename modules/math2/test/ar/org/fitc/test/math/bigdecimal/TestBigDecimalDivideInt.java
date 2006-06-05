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
//import java.math.RoundingMode;


import ar.org.fitc.test.util.Messages;
import junit.framework.TestCase;
/**
 * Test cases for
 * divide(BigDecimal, MathContext )
 * divide(BigDecimal,int,int)
 * 
 *  
 *
 */
public class TestBigDecimalDivideInt extends TestCase implements Messages{
	BigDecimal bigDec=null;
	
		
	public TestBigDecimalDivideInt(String name) {
		super(name);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	/*
	 * Test method for 'java.math.BigDecimal.divide(BigDecimal, Int)'
	 */
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt393() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, 0)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt394() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), 0);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, 1)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt395() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), 1);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, 2)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt396() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), 2);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, 3)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt397() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), 3);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, 4)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt398() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), 4);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, 5)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt399() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), 5);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, 6)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt400() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), 6);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt401() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt402() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt403() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("1"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, 0)</b> should be 0
     */
   public void testDivideBigDecimalInt404() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, 1)</b> should be 0
     */
   public void testDivideBigDecimalInt405() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 1));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, 2)</b> should be 0
     */
   public void testDivideBigDecimalInt406() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 2));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, 3)</b> should be 0
     */
   public void testDivideBigDecimalInt407() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 3));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, 4)</b> should be 0
     */
   public void testDivideBigDecimalInt408() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 4));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, 5)</b> should be 0
     */
   public void testDivideBigDecimalInt409() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 5));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, 6)</b> should be 0
     */
   public void testDivideBigDecimalInt410() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 6));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, 7)</b> should be 0
     */
   public void testDivideBigDecimalInt411() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 7));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt412() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("1"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt413() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("-0.1"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, 0)</b> should be 0
     */
   public void testDivideBigDecimalInt414() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), 0));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, 1)</b> should be 0
     */
   public void testDivideBigDecimalInt415() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), 1));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, 2)</b> should be 0
     */
   public void testDivideBigDecimalInt416() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), 2));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, 3)</b> should be 0
     */
   public void testDivideBigDecimalInt417() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), 3));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, 4)</b> should be 0
     */
   public void testDivideBigDecimalInt418() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), 4));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, 5)</b> should be 0
     */
   public void testDivideBigDecimalInt419() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), 5));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, 6)</b> should be 0
     */
   public void testDivideBigDecimalInt420() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), 6));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, 7)</b> should be 0
     */
   public void testDivideBigDecimalInt421() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), 7));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt422() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("-0.1"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt423() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 0)</b> should be 0
     */
   public void testDivideBigDecimalInt424() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 1)</b> should be 0
     */
   public void testDivideBigDecimalInt425() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 1));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 2)</b> should be 0
     */
   public void testDivideBigDecimalInt426() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 2));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 3)</b> should be 0
     */
   public void testDivideBigDecimalInt427() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 3));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 4)</b> should be 0
     */
   public void testDivideBigDecimalInt428() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 4));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 5)</b> should be 0
     */
   public void testDivideBigDecimalInt429() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 5));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 6)</b> should be 0
     */
   public void testDivideBigDecimalInt430() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 6));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 7)</b> should be 0
     */
   public void testDivideBigDecimalInt431() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 7));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt432() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt433() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0)</b> should be 0
     */
   public void testDivideBigDecimalInt434() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 1)</b> should be 0
     */
   public void testDivideBigDecimalInt435() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 1));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 2)</b> should be 0
     */
   public void testDivideBigDecimalInt436() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 2));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 3)</b> should be 0
     */
   public void testDivideBigDecimalInt437() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 3));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 4)</b> should be 0
     */
   public void testDivideBigDecimalInt438() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 4));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 5)</b> should be 0
     */
   public void testDivideBigDecimalInt439() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 5));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 6)</b> should be 0
     */
   public void testDivideBigDecimalInt440() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 6));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 7)</b> should be 0
     */
   public void testDivideBigDecimalInt441() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 7));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt442() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of the division  
     * should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt443() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of the division
     * should be 0
     */
   public void testDivideBigDecimalInt444() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of the division
     * should be 0
     */
   public void testDivideBigDecimalInt445() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 1));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of the division
     * should be 0
     */
   public void testDivideBigDecimalInt446() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 2));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * the division should be 0
     */
   public void testDivideBigDecimalInt447() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 3));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of the division
     *  should be 0
     */
   public void testDivideBigDecimalInt448() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 4));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of the division
     *  should be 0
     */
   public void testDivideBigDecimalInt449() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 5));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of the division
     *  should be 0
     */
   public void testDivideBigDecimalInt450() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 6));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of the division
     *  should be 0
     */
   public void testDivideBigDecimalInt451() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 7));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of the division 
     *  should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt452() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of the division
     *  should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt453() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of the division 
     *  should be 0
     */
   public void testDivideBigDecimalInt454() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of the division
     * should be 0
     */
   public void testDivideBigDecimalInt455() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 1));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of the division 
     * should be 0
     */
   public void testDivideBigDecimalInt456() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 2));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of the division
     * should be 0
     */
   public void testDivideBigDecimalInt457() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 3));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of the division
     * should be 0
     */
   public void testDivideBigDecimalInt458() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 4));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of the division 
     * should be 0
     */
   public void testDivideBigDecimalInt459() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 5));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of the division 
     * should be 0
     */
   public void testDivideBigDecimalInt460() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 6));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of the division 
     * should be 0
     */
   public void testDivideBigDecimalInt461() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 7));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of the division
     * should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt462() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt463() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, 0)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt464() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), 0);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, 1)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt465() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), 1);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, 2)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt466() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), 2);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, 3)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt467() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), 3);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, 4)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt468() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), 4);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, 5)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt469() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), 5);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, 6)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt470() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), 6);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt471() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt472() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 1, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt473() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("1"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 1, 0)</b> should be 1
     */
   public void testDivideBigDecimalInt474() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("1"), 0));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 1, 1)</b> should be 1
     */
   public void testDivideBigDecimalInt475() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("1"), 1));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 1, 2)</b> should be 1
     */
   public void testDivideBigDecimalInt476() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("1"), 2));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 1, 3)</b> should be 1
     */
   public void testDivideBigDecimalInt477() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("1"), 3));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 1, 4)</b> should be 1
     */
   public void testDivideBigDecimalInt478() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("1"), 4));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 1, 5)</b> should be 1
     */
   public void testDivideBigDecimalInt479() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("1"), 5));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 1, 6)</b> should be 1
     */
   public void testDivideBigDecimalInt480() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("1"), 6));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 1, 7)</b> should be 1
     */
   public void testDivideBigDecimalInt481() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("1"), 7));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 1, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt482() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("1"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -0.1, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt483() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-0.1"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -0.1, 0)</b> should be -10
     */
   public void testDivideBigDecimalInt484() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-10"),bigDec.divide(new BigDecimal("-0.1"), 0));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -0.1, 1)</b> should be -10
     */
   public void testDivideBigDecimalInt485() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-10"),bigDec.divide(new BigDecimal("-0.1"), 1));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -0.1, 2)</b> should be -10
     */
   public void testDivideBigDecimalInt486() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-10"),bigDec.divide(new BigDecimal("-0.1"), 2));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -0.1, 3)</b> should be -10
     */
   public void testDivideBigDecimalInt487() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-10"),bigDec.divide(new BigDecimal("-0.1"), 3));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -0.1, 4)</b> should be -10
     */
   public void testDivideBigDecimalInt488() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-10"),bigDec.divide(new BigDecimal("-0.1"), 4));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -0.1, 5)</b> should be -10
     */
   public void testDivideBigDecimalInt489() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-10"),bigDec.divide(new BigDecimal("-0.1"), 5));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -0.1, 6)</b> should be -10
     */
   public void testDivideBigDecimalInt490() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-10"),bigDec.divide(new BigDecimal("-0.1"), 6));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -0.1, 7)</b> should be -10
     */
   public void testDivideBigDecimalInt491() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-10"),bigDec.divide(new BigDecimal("-0.1"), 7));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -0.1, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt492() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-0.1"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt493() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   
   public void testDivideBigDecimalInt494() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001430"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0));
}
   
   public void testDivideBigDecimalInt495() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001429"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 1));
}
   
   public void testDivideBigDecimalInt496() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001429"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 2));
}
   
   public void testDivideBigDecimalInt497() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001430"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 3));
}
   
   public void testDivideBigDecimalInt498() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001429"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 4));
}
   
   public void testDivideBigDecimalInt499() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001429"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 5));
}

   public void testDivideBigDecimalInt500() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001429"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 6));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt501() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt502() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt503() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0)</b> should be -1
     */
   public void testDivideBigDecimalInt504() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 1)</b> should be 0
     */
   public void testDivideBigDecimalInt505() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 1));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 2)</b> should be 0
     */
   public void testDivideBigDecimalInt506() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 2));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 3)</b> should be -1
     */
   public void testDivideBigDecimalInt507() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 3));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 4)</b> should be 0
     */
   public void testDivideBigDecimalInt508() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 4));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 5)</b> should be 0
     */
   public void testDivideBigDecimalInt509() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 5));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 6)</b> should be 0
     */
   public void testDivideBigDecimalInt510() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 6));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt511() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt512() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}

   public void testDivideBigDecimalInt513() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}

   public void testDivideBigDecimalInt514() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0));
}
   
   public void testDivideBigDecimalInt515() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 1));
}
   
   public void testDivideBigDecimalInt516() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 2));
}
   
   public void testDivideBigDecimalInt517() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 3));
}
   
   public void testDivideBigDecimalInt518() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 4));
}
   
   public void testDivideBigDecimalInt519() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 5));
}
   
   public void testDivideBigDecimalInt520() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 6));
}
   
   public void testDivideBigDecimalInt521() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}

   public void testDivideBigDecimalInt522() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   
   public void testDivideBigDecimalInt523() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}

   public void testDivideBigDecimalInt524() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0));
}

   public void testDivideBigDecimalInt525() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 1));
}

   public void testDivideBigDecimalInt526() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 2));
}

   public void testDivideBigDecimalInt527() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 3));
}

   public void testDivideBigDecimalInt528() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 4));
}

   public void testDivideBigDecimalInt529() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 5));
}

   public void testDivideBigDecimalInt530() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 6));
}
   
   public void testDivideBigDecimalInt531() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}

   public void testDivideBigDecimalInt532() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt533() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, 0)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt534() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), 0);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, 1)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt535() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), 1);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, 2)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt536() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), 2);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, 3)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt537() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), 3);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, 4)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt538() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), 4);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, 5)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt539() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), 5);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, 6)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt540() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), 6);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt541() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt542() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 1, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt543() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("1"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 1, 0)</b> should be -0.1
     */
   public void testDivideBigDecimalInt544() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-0.1"),bigDec.divide(new BigDecimal("1"), 0));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 1, 1)</b> should be -0.1
     */
   public void testDivideBigDecimalInt545() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-0.1"),bigDec.divide(new BigDecimal("1"), 1));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 1, 2)</b> should be -0.1
     */
   public void testDivideBigDecimalInt546() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-0.1"),bigDec.divide(new BigDecimal("1"), 2));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 1, 3)</b> should be -0.1
     */
   public void testDivideBigDecimalInt547() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-0.1"),bigDec.divide(new BigDecimal("1"), 3));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 1, 4)</b> should be -0.1
     */
   public void testDivideBigDecimalInt548() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-0.1"),bigDec.divide(new BigDecimal("1"), 4));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 1, 5)</b> should be -0.1
     */
   public void testDivideBigDecimalInt549() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-0.1"),bigDec.divide(new BigDecimal("1"), 5));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 1, 6)</b> should be -0.1
     */
   public void testDivideBigDecimalInt550() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-0.1"),bigDec.divide(new BigDecimal("1"), 6));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 1, 7)</b> should be -0.1
     */
   public void testDivideBigDecimalInt551() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-0.1"),bigDec.divide(new BigDecimal("1"), 7));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 1, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt552() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("1"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -0.1, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt553() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-0.1"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -0.1, 0)</b> should be 1.0
     */
   public void testDivideBigDecimalInt554() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.0"),bigDec.divide(new BigDecimal("-0.1"), 0));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -0.1, 1)</b> should be 1.0
     */
   public void testDivideBigDecimalInt555() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.0"),bigDec.divide(new BigDecimal("-0.1"), 1));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -0.1, 2)</b> should be 1.0
     */
   public void testDivideBigDecimalInt556() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.0"),bigDec.divide(new BigDecimal("-0.1"), 2));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -0.1, 3)</b> should be 1.0
     */
   public void testDivideBigDecimalInt557() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.0"),bigDec.divide(new BigDecimal("-0.1"), 3));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -0.1, 4)</b> should be 1.0
     */
   public void testDivideBigDecimalInt558() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.0"),bigDec.divide(new BigDecimal("-0.1"), 4));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -0.1, 5)</b> should be 1.0
     */
   public void testDivideBigDecimalInt559() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.0"),bigDec.divide(new BigDecimal("-0.1"), 5));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -0.1, 6)</b> should be 1.0
     */
   public void testDivideBigDecimalInt560() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.0"),bigDec.divide(new BigDecimal("-0.1"), 6));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -0.1, 7)</b> should be 1.0
     */
   public void testDivideBigDecimalInt561() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.0"),bigDec.divide(new BigDecimal("-0.1"), 7));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -0.1, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt562() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-0.1"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt563() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalInt564() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000143.0"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0));
}

   public void testDivideBigDecimalInt565() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000142.9"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 1));
}
    
   public void testDivideBigDecimalInt566() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000143.0"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 2));
}
    
   public void testDivideBigDecimalInt567() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000142.9"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 3));
}

   public void testDivideBigDecimalInt568() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000142.9"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 4));
}

   public void testDivideBigDecimalInt569() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000142.9"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 5));
}

   public void testDivideBigDecimalInt570() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000142.9"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 6));
}

   /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt571() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt572() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt573() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0)</b> should be 0.1
     */
   public void testDivideBigDecimalInt574() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 1)</b> should be 0.0
     */
   public void testDivideBigDecimalInt575() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 1));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 2)</b> should be 0.1
     */
   public void testDivideBigDecimalInt576() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 2));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 3)</b> should be 0.0
     */
   public void testDivideBigDecimalInt577() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 3));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 4)</b> should be 0.0
     */
   public void testDivideBigDecimalInt578() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 4));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 5)</b> should be 0.0
     */
   public void testDivideBigDecimalInt579() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 5));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 6)</b> should be 0.0
     */
   public void testDivideBigDecimalInt580() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 6));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt581() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt582() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}

   public void testDivideBigDecimalInt583() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}

   public void testDivideBigDecimalInt584() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-0.1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0));
}
   
   public void testDivideBigDecimalInt585() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 1));
}

   public void testDivideBigDecimalInt586() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 2));
}

   public void testDivideBigDecimalInt587() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-0.1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 3));
}

   public void testDivideBigDecimalInt588() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 4));
}

   public void testDivideBigDecimalInt589() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 5));
}

   public void testDivideBigDecimalInt590() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 6));
}

   public void testDivideBigDecimalInt591() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}

   public void testDivideBigDecimalInt592() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}

   public void testDivideBigDecimalInt593() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   
   public void testDivideBigDecimalInt594() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.1"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0));
}

   public void testDivideBigDecimalInt595() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 1));
}

   public void testDivideBigDecimalInt596() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.1"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 2));
}
   
   public void testDivideBigDecimalInt597() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 3));
}
    
   public void testDivideBigDecimalInt598() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 4));
}

   public void testDivideBigDecimalInt599() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 5));
}

   public void testDivideBigDecimalInt600() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 6));
}

   public void testDivideBigDecimalInt601() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}

   public void testDivideBigDecimalInt602() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt603() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, 0)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt604() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), 0);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, 1)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt605() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), 1);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, 2)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt606() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), 2);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, 3)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt607() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), 3);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, 4)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt608() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), 4);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, 5)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt609() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), 5);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, 6)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt610() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), 6);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt611() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt612() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt613() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("1"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, 0)</b> should be -9.693583160302274182E-751
     */
   public void testDivideBigDecimalInt614() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583160302274182E-751"),bigDec.divide(new BigDecimal("1"), 0));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, 1)</b> should be -9.693583160302274182E-751
     */
   public void testDivideBigDecimalInt615() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583160302274182E-751"),bigDec.divide(new BigDecimal("1"), 1));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, 2)</b> should be -9.693583160302274182E-751
     */
   public void testDivideBigDecimalInt616() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583160302274182E-751"),bigDec.divide(new BigDecimal("1"), 2));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, 3)</b> should be -9.693583160302274182E-751
     */
   public void testDivideBigDecimalInt617() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583160302274182E-751"),bigDec.divide(new BigDecimal("1"), 3));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, 4)</b> should be -9.693583160302274182E-751
     */
   public void testDivideBigDecimalInt618() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583160302274182E-751"),bigDec.divide(new BigDecimal("1"), 4));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, 5)</b> should be -9.693583160302274182E-751
     */
   public void testDivideBigDecimalInt619() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583160302274182E-751"),bigDec.divide(new BigDecimal("1"), 5));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, 6)</b> should be -9.693583160302274182E-751
     */
   public void testDivideBigDecimalInt620() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583160302274182E-751"),bigDec.divide(new BigDecimal("1"), 6));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, 7)</b> should be -9.693583160302274182E-751
     */
   public void testDivideBigDecimalInt621() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583160302274182E-751"),bigDec.divide(new BigDecimal("1"), 7));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt622() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("1"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt623() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-0.1"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, 0)</b> should be 9.6935831603022741820E-750
     */
   public void testDivideBigDecimalInt624() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("9.6935831603022741820E-750"),bigDec.divide(new BigDecimal("-0.1"), 0));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, 1)</b> should be 9.6935831603022741820E-750
     */
   public void testDivideBigDecimalInt625() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("9.6935831603022741820E-750"),bigDec.divide(new BigDecimal("-0.1"), 1));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, 2)</b> should be 9.6935831603022741820E-750
     */
   public void testDivideBigDecimalInt626() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("9.6935831603022741820E-750"),bigDec.divide(new BigDecimal("-0.1"), 2));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, 3)</b> should be 9.6935831603022741820E-750
     */
   public void testDivideBigDecimalInt627() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("9.6935831603022741820E-750"),bigDec.divide(new BigDecimal("-0.1"), 3));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, 4)</b> should be 9.6935831603022741820E-750
     */
   public void testDivideBigDecimalInt628() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("9.6935831603022741820E-750"),bigDec.divide(new BigDecimal("-0.1"), 4));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, 5)</b> should be 9.6935831603022741820E-750
     */
   public void testDivideBigDecimalInt629() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("9.6935831603022741820E-750"),bigDec.divide(new BigDecimal("-0.1"), 5));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, 6)</b> should be 9.6935831603022741820E-750
     */
   public void testDivideBigDecimalInt630() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("9.6935831603022741820E-750"),bigDec.divide(new BigDecimal("-0.1"), 6));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, 7)</b> should be 9.6935831603022741820E-750
     */
   public void testDivideBigDecimalInt631() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("9.6935831603022741820E-750"),bigDec.divide(new BigDecimal("-0.1"), 7));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt632() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-0.1"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt633() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}

   public void testDivideBigDecimalInt634() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0));
}
   
   public void testDivideBigDecimalInt635() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 1));
}

   public void testDivideBigDecimalInt636() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 2));
}

   public void testDivideBigDecimalInt637() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 3));
}

   public void testDivideBigDecimalInt638() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 4));
}

   public void testDivideBigDecimalInt639() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 5));
}

   public void testDivideBigDecimalInt640() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 6));
}
   
   public void testDivideBigDecimalInt641() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 7));
}

   /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt642() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt643() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0)</b> should be 1E-769
     */
   public void testDivideBigDecimalInt644() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1E-769"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 1)</b> should be 0E-769
     */
   public void testDivideBigDecimalInt645() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-769"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 1));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 2)</b> should be 1E-769
     */
   public void testDivideBigDecimalInt646() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1E-769"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 2));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 3)</b> should be 0E-769
     */
   public void testDivideBigDecimalInt647() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-769"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 3));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 4)</b> should be 0E-769
     */
   public void testDivideBigDecimalInt648() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-769"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 4));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 5)</b> should be 0E-769
     */
   public void testDivideBigDecimalInt649() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-769"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 5));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 6)</b> should be 0E-769
     */
   public void testDivideBigDecimalInt650() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-769"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 6));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt651() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt652() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
 
   public void testDivideBigDecimalInt653() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}

   public void testDivideBigDecimalInt654() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("-1E-769"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0));
}

   public void testDivideBigDecimalInt655() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-769"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 1));
}

   public void testDivideBigDecimalInt656() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-769"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 2));
}

   public void testDivideBigDecimalInt657() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("-1E-769"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 3));
}
   
   public void testDivideBigDecimalInt658() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-769"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 4));
}

   public void testDivideBigDecimalInt659() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-769"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 5));
}

   public void testDivideBigDecimalInt660() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-769"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 6));
}

   public void testDivideBigDecimalInt661() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}

   public void testDivideBigDecimalInt662() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}

   public void testDivideBigDecimalInt663() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}

   public void testDivideBigDecimalInt664() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1E-769"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0));
}

   public void testDivideBigDecimalInt665() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-769"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 1));
}

   public void testDivideBigDecimalInt666() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1E-769"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 2));
}

   public void testDivideBigDecimalInt667() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-769"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 3));
}

   public void testDivideBigDecimalInt668() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-769"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 4));
}
    
   public void testDivideBigDecimalInt669() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-769"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 5));
}
   
   public void testDivideBigDecimalInt670() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-769"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 6));
}

   public void testDivideBigDecimalInt671() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}

   public void testDivideBigDecimalInt672() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt673() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, 0)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt674() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), 0);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, 1)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt675() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), 1);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, 2)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt676() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), 2);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, 3)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt677() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), 3);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, 4)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt678() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), 4);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, 5)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt679() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), 5);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, 6)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt680() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), 6);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt681() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt682() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 1, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt683() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("1"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 1, 0)</b> should be -9.693583160302274182E+506
     */
   public void testDivideBigDecimalInt684() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583160302274182E+506"),bigDec.divide(new BigDecimal("1"), 0));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 1, 1)</b> should be -9.693583160302274182E+506
     */
   public void testDivideBigDecimalInt685() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583160302274182E+506"),bigDec.divide(new BigDecimal("1"), 1));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 1, 2)</b> should be -9.693583160302274182E+506
     */
   public void testDivideBigDecimalInt686() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583160302274182E+506"),bigDec.divide(new BigDecimal("1"), 2));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 1, 3)</b> should be -9.693583160302274182E+506
     */
   public void testDivideBigDecimalInt687() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583160302274182E+506"),bigDec.divide(new BigDecimal("1"), 3));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 1, 4)</b> should be -9.693583160302274182E+506
     */
   public void testDivideBigDecimalInt688() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583160302274182E+506"),bigDec.divide(new BigDecimal("1"), 4));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 1, 5)</b> should be -9.693583160302274182E+506
     */
   public void testDivideBigDecimalInt689() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583160302274182E+506"),bigDec.divide(new BigDecimal("1"), 5));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 1, 6)</b> should be -9.693583160302274182E+506
     */
   public void testDivideBigDecimalInt690() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583160302274182E+506"),bigDec.divide(new BigDecimal("1"), 6));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 1, 7)</b> should be -9.693583160302274182E+506
     */
   public void testDivideBigDecimalInt691() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583160302274182E+506"),bigDec.divide(new BigDecimal("1"), 7));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 1, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt692() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("1"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -0.1, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt693() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-0.1"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -0.1, 0)</b> should be 9.6935831603022741820E+507
     */
   public void testDivideBigDecimalInt694() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9.6935831603022741820E+507"),bigDec.divide(new BigDecimal("-0.1"), 0));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -0.1, 1)</b> should be 9.6935831603022741820E+507
     */
   public void testDivideBigDecimalInt695() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9.6935831603022741820E+507"),bigDec.divide(new BigDecimal("-0.1"), 1));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -0.1, 2)</b> should be 9.6935831603022741820E+507
     */
   public void testDivideBigDecimalInt696() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9.6935831603022741820E+507"),bigDec.divide(new BigDecimal("-0.1"), 2));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -0.1, 3)</b> should be 9.6935831603022741820E+507
     */
   public void testDivideBigDecimalInt697() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9.6935831603022741820E+507"),bigDec.divide(new BigDecimal("-0.1"), 3));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -0.1, 4)</b> should be 9.6935831603022741820E+507
     */
   public void testDivideBigDecimalInt698() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9.6935831603022741820E+507"),bigDec.divide(new BigDecimal("-0.1"), 4));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -0.1, 5)</b> should be 9.6935831603022741820E+507
     */
   public void testDivideBigDecimalInt699() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9.6935831603022741820E+507"),bigDec.divide(new BigDecimal("-0.1"), 5));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -0.1, 6)</b> should be 9.6935831603022741820E+507
     */
   public void testDivideBigDecimalInt700() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9.6935831603022741820E+507"),bigDec.divide(new BigDecimal("-0.1"), 6));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -0.1, 7)</b> should be 9.6935831603022741820E+507
     */
   public void testDivideBigDecimalInt701() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9.6935831603022741820E+507"),bigDec.divide(new BigDecimal("-0.1"), 7));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -0.1, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt702() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-0.1"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt703() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}

   public void testDivideBigDecimalInt704() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0));
}

   public void testDivideBigDecimalInt705() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 1));
}

   public void testDivideBigDecimalInt706() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 2));
}

   public void testDivideBigDecimalInt707() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 3));
}

   public void testDivideBigDecimalInt708() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 4));
}

   public void testDivideBigDecimalInt709() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 5));
}

   public void testDivideBigDecimalInt710() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 6));
}
   
   public void testDivideBigDecimalInt711() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 7));
}
   
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt712() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt713() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0)</b> should be 1E+488
     */
   public void testDivideBigDecimalInt714() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1E+488"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 1)</b> should be 0E+488
     */
   public void testDivideBigDecimalInt715() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+488"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 1));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 2)</b> should be 1E+488
     */
   public void testDivideBigDecimalInt716() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1E+488"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 2));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 3)</b> should be 0E+488
     */
   public void testDivideBigDecimalInt717() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+488"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 3));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 4)</b> should be 0E+488
     */
   public void testDivideBigDecimalInt718() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+488"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 4));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 5)</b> should be 0E+488
     */
   public void testDivideBigDecimalInt719() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+488"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 5));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 6)</b> should be 0E+488
     */
   public void testDivideBigDecimalInt720() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+488"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 6));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalInt721() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalInt722() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}

   public void testDivideBigDecimalInt723() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}

   public void testDivideBigDecimalInt724() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-1E+488"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0));
}

   public void testDivideBigDecimalInt725() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+488"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 1));
}

   public void testDivideBigDecimalInt726() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+488"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 2));
}

   public void testDivideBigDecimalInt727() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-1E+488"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 3));
}

   public void testDivideBigDecimalInt728() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+488"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 4));
}

   public void testDivideBigDecimalInt729() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+488"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 5));
}

   public void testDivideBigDecimalInt730() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+488"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 6));
}

   public void testDivideBigDecimalInt731() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}

   public void testDivideBigDecimalInt732() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}

   public void testDivideBigDecimalInt733() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}

   public void testDivideBigDecimalInt734() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1E+488"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0));
}

   public void testDivideBigDecimalInt735() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+488"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 1));
}

   public void testDivideBigDecimalInt736() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1E+488"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 2));
}

   public void testDivideBigDecimalInt737() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+488"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 3));
}

   public void testDivideBigDecimalInt738() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+488"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 4));
}

   public void testDivideBigDecimalInt739() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+488"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 5));
}

   public void testDivideBigDecimalInt740() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+488"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 6));
}

   public void testDivideBigDecimalInt741() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}

   public void testDivideBigDecimalInt742() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}

   public void testDivideBigDecimalInt743() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    
   public void testDivideBigDecimalInt744() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), 0);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}

   public void testDivideBigDecimalInt745() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), 1);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}

   public void testDivideBigDecimalInt746() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), 2);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}

   public void testDivideBigDecimalInt747() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), 3);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}

   public void testDivideBigDecimalInt748() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), 4);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}

   public void testDivideBigDecimalInt749() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), 5);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}

   public void testDivideBigDecimalInt750() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), 6);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}

   public void testDivideBigDecimalInt751() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   
   public void testDivideBigDecimalInt752() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}

   public void testDivideBigDecimalInt753() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("1"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}

   public void testDivideBigDecimalInt754() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 0));
}

   public void testDivideBigDecimalInt755() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 1));
}

   public void testDivideBigDecimalInt756() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 2));
}
   
   public void testDivideBigDecimalInt757() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 3));
}
   
   public void testDivideBigDecimalInt758() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 4));
}

   public void testDivideBigDecimalInt759() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 5));
}

   public void testDivideBigDecimalInt760() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 6));
}
   
   public void testDivideBigDecimalInt761() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 7));
}

   public void testDivideBigDecimalInt762() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("1"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}

   public void testDivideBigDecimalInt763() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-0.1"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    
   public void testDivideBigDecimalInt764() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 0));
}

   public void testDivideBigDecimalInt765() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 1));
}

   public void testDivideBigDecimalInt766() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 2));
}

   public void testDivideBigDecimalInt767() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 3));
}

   public void testDivideBigDecimalInt768() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 4));
}

   public void testDivideBigDecimalInt769() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 5));
}

   public void testDivideBigDecimalInt770() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 6));
}

   public void testDivideBigDecimalInt771() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 7));
}

   public void testDivideBigDecimalInt772() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-0.1"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}

   public void testDivideBigDecimalInt773() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
 
   public void testDivideBigDecimalInt774() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0));
}

   public void testDivideBigDecimalInt775() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 1));
}

   public void testDivideBigDecimalInt776() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 2));
}

   public void testDivideBigDecimalInt777() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 3));
}

   public void testDivideBigDecimalInt778() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 4));
}
   
   public void testDivideBigDecimalInt779() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 5));
}
   
   public void testDivideBigDecimalInt780() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 6));
}
   
   public void testDivideBigDecimalInt781() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 7));
}
   
   public void testDivideBigDecimalInt782() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   
   public void testDivideBigDecimalInt783() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   
   public void testDivideBigDecimalInt784() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0));
}
   
   public void testDivideBigDecimalInt785() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 1));
}
   
   public void testDivideBigDecimalInt786() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 2));
}
   
   public void testDivideBigDecimalInt787() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 3));
}
   
   public void testDivideBigDecimalInt788() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 4));
}
   
   public void testDivideBigDecimalInt789() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 5));
}
   
   public void testDivideBigDecimalInt790() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 6));
}
   
   public void testDivideBigDecimalInt791() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 7));
}

   public void testDivideBigDecimalInt792() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   
   public void testDivideBigDecimalInt793() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   
   public void testDivideBigDecimalInt794() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0));
}
   
   public void testDivideBigDecimalInt795() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 1));
}
   
   public void testDivideBigDecimalInt796() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 2));
}

   public void testDivideBigDecimalInt797() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 3));
}

   public void testDivideBigDecimalInt798() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 4));
}

   public void testDivideBigDecimalInt799() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 5));
}

   public void testDivideBigDecimalInt800() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 6));
}

   public void testDivideBigDecimalInt801() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 7));
}

   public void testDivideBigDecimalInt802() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}

   public void testDivideBigDecimalInt803() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    
   public void testDivideBigDecimalInt804() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058821"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0));
}
   
   public void testDivideBigDecimalInt805() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 1));
}

   public void testDivideBigDecimalInt806() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 2));
}
   
   public void testDivideBigDecimalInt807() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058821"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 3));
}

   public void testDivideBigDecimalInt808() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058821"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 4));
}

   public void testDivideBigDecimalInt809() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058821"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 5));
}

   public void testDivideBigDecimalInt810() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058821"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 6));
}

   public void testDivideBigDecimalInt811() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   
   public void testDivideBigDecimalInt812() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}

   public void testDivideBigDecimalInt813() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   
   public void testDivideBigDecimalInt814() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), 0);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalInt815() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), 1);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalInt816() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), 2);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalInt817() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), 3);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalInt818() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), 4);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalInt819() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), 5);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}

   public void testDivideBigDecimalInt820() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), 6);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}

   public void testDivideBigDecimalInt821() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}

   public void testDivideBigDecimalInt822() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}

   public void testDivideBigDecimalInt823() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("1"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    
   public void testDivideBigDecimalInt824() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"),bigDec.divide(new BigDecimal("1"), 0));
}

   public void testDivideBigDecimalInt825() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"),bigDec.divide(new BigDecimal("1"), 1));
}
   
   public void testDivideBigDecimalInt826() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"),bigDec.divide(new BigDecimal("1"), 2));
}

   public void testDivideBigDecimalInt827() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"),bigDec.divide(new BigDecimal("1"), 3));
}

   public void testDivideBigDecimalInt828() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"),bigDec.divide(new BigDecimal("1"), 4));
}
   public void testDivideBigDecimalInt829() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"),bigDec.divide(new BigDecimal("1"), 5));
}
 
   public void testDivideBigDecimalInt830() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"),bigDec.divide(new BigDecimal("1"), 6));
}
    
   public void testDivideBigDecimalInt831() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"),bigDec.divide(new BigDecimal("1"), 7));
}

   public void testDivideBigDecimalInt832() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("1"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   
   public void testDivideBigDecimalInt833() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-0.1"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}

   public void testDivideBigDecimalInt834() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545454.8941897486153411851161574144074171110008145041504505740487789714555504897047890447860104596140450648604415670445604864646888978945612315645489748195260320561840"),bigDec.divide(new BigDecimal("-0.1"), 0));
}
   
   public void testDivideBigDecimalInt835() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545454.8941897486153411851161574144074171110008145041504505740487789714555504897047890447860104596140450648604415670445604864646888978945612315645489748195260320561840"),bigDec.divide(new BigDecimal("-0.1"), 1));
}

   public void testDivideBigDecimalInt836() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545454.8941897486153411851161574144074171110008145041504505740487789714555504897047890447860104596140450648604415670445604864646888978945612315645489748195260320561840"),bigDec.divide(new BigDecimal("-0.1"), 2));
}

   public void testDivideBigDecimalInt837() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545454.8941897486153411851161574144074171110008145041504505740487789714555504897047890447860104596140450648604415670445604864646888978945612315645489748195260320561840"),bigDec.divide(new BigDecimal("-0.1"), 3));
}
    
   public void testDivideBigDecimalInt838() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545454.8941897486153411851161574144074171110008145041504505740487789714555504897047890447860104596140450648604415670445604864646888978945612315645489748195260320561840"),bigDec.divide(new BigDecimal("-0.1"), 4));
}
   
   public void testDivideBigDecimalInt839() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545454.8941897486153411851161574144074171110008145041504505740487789714555504897047890447860104596140450648604415670445604864646888978945612315645489748195260320561840"),bigDec.divide(new BigDecimal("-0.1"), 5));
}
   
   public void testDivideBigDecimalInt840() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545454.8941897486153411851161574144074171110008145041504505740487789714555504897047890447860104596140450648604415670445604864646888978945612315645489748195260320561840"),bigDec.divide(new BigDecimal("-0.1"), 6));
}
   
   public void testDivideBigDecimalInt841() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545454.8941897486153411851161574144074171110008145041504505740487789714555504897047890447860104596140450648604415670445604864646888978945612315645489748195260320561840"),bigDec.divide(new BigDecimal("-0.1"), 7));
}
   
   public void testDivideBigDecimalInt842() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-0.1"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalInt843() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalInt844() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831.2346700103102320806410189224181657476572912061020038625572304239544660537490711090527469614498307060868879871743013460412982694423872490290047502076133522185495"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0));
}
   public void testDivideBigDecimalInt845() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831.2346700103102320806410189224181657476572912061020038625572304239544660537490711090527469614498307060868879871743013460412982694423872490290047502076133522185494"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 1));
}
   public void testDivideBigDecimalInt846() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831.2346700103102320806410189224181657476572912061020038625572304239544660537490711090527469614498307060868879871743013460412982694423872490290047502076133522185495"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 2));
}
   public void testDivideBigDecimalInt847() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831.2346700103102320806410189224181657476572912061020038625572304239544660537490711090527469614498307060868879871743013460412982694423872490290047502076133522185494"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 3));
}
   public void testDivideBigDecimalInt848() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831.2346700103102320806410189224181657476572912061020038625572304239544660537490711090527469614498307060868879871743013460412982694423872490290047502076133522185494"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 4));
}
   public void testDivideBigDecimalInt849() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831.2346700103102320806410189224181657476572912061020038625572304239544660537490711090527469614498307060868879871743013460412982694423872490290047502076133522185494"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 5));
}
   public void testDivideBigDecimalInt850() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831.2346700103102320806410189224181657476572912061020038625572304239544660537490711090527469614498307060868879871743013460412982694423872490290047502076133522185494"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 6));
}
   public void testDivideBigDecimalInt851() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalInt852() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalInt853() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalInt854() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1E-160"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0));
}
   public void testDivideBigDecimalInt855() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E-160"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 1));
}
   public void testDivideBigDecimalInt856() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1E-160"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 2));
}
   public void testDivideBigDecimalInt857() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E-160"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 3));
}
   public void testDivideBigDecimalInt858() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E-160"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 4));
}
   public void testDivideBigDecimalInt859() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E-160"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 5));
}

   public void testDivideBigDecimalInt860() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E-160"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 6));
}
   public void testDivideBigDecimalInt861() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalInt862() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalInt863() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalInt864() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-1E-160"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0));
}
   public void testDivideBigDecimalInt865() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E-160"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 1));
}
   public void testDivideBigDecimalInt866() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E-160"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 2));
}
   public void testDivideBigDecimalInt867() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-1E-160"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 3));
}
   public void testDivideBigDecimalInt868() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E-160"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 4));
}
   public void testDivideBigDecimalInt869() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E-160"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 5));
}
   public void testDivideBigDecimalInt870() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E-160"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 6));
}
   public void testDivideBigDecimalInt871() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalInt872() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalInt873() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalInt874() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0));
}
   public void testDivideBigDecimalInt875() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 1));
}
   public void testDivideBigDecimalInt876() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 2));
}
   public void testDivideBigDecimalInt877() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 3));
}
   public void testDivideBigDecimalInt878() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 4));
}
   public void testDivideBigDecimalInt879() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 5));
}
   public void testDivideBigDecimalInt880() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 6));
}
   public void testDivideBigDecimalInt881() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 7));
}
   public void testDivideBigDecimalInt882() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}

	
	
	
	
	
}