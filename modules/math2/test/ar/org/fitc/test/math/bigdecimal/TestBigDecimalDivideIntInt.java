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
 * divide(BigDecimal,int,int) 
 *
 */
public class TestBigDecimalDivideIntInt extends TestCase implements Messages{
	BigDecimal bigDec=null;
	
		
	public TestBigDecimalDivideIntInt(String name) {
		super(name);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	
	
	
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, 0, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt001() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, 600, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt002() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, -500, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt003() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, 0, 0)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt004() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), 0, 0);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, 600, 0)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt005() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), 600, 0);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, -500, 0)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt006() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), -500, 0);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, 0, 1)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt007() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), 0, 1);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, 600, 1)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt008() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), 600, 1);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, -500, 1)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt009() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), -500, 1);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, 0, 2)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt010() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), 0, 2);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, 600, 2)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt011() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), 600, 2);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, -500, 2)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt012() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), -500, 2);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, 0, 3)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt013() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), 0, 3);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, 600, 3)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt014() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), 600, 3);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, -500, 3)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt015() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), -500, 3);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, 0, 4)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt016() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), 0, 4);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, 600, 4)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt017() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), 600, 4);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, -500, 4)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt018() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), -500, 4);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, 0, 5)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt019() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), 0, 5);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, 600, 5)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt020() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), 600, 5);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, -500, 5)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt021() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), -500, 5);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, 0, 6)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt022() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), 0, 6);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, 600, 6)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt023() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), 600, 6);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, -500, 6)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt024() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), -500, 6);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, 0, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt025() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), 0, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, 600, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt026() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), 600, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, -500, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt027() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, 0, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt028() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, 600, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt029() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 0, -500, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt030() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, 0, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt031() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("1"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, 600, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt032() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("1"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, -500, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt033() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("1"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, 0, 0)</b> should be 0
     */
   public void testDivideBigDecimalIntInt034() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, 600, 0)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt035() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("1"), 600, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, -500, 0)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt036() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, 0, 1)</b> should be 0
     */
   public void testDivideBigDecimalIntInt037() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, 600, 1)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt038() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("1"), 600, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, -500, 1)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt039() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, 0, 2)</b> should be 0
     */
   public void testDivideBigDecimalIntInt040() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, 600, 2)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt041() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("1"), 600, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, -500, 2)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt042() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, 0, 3)</b> should be 0
     */
   public void testDivideBigDecimalIntInt043() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, 600, 3)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt044() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("1"), 600, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, -500, 3)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt045() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, 0, 4)</b> should be 0
     */
   public void testDivideBigDecimalIntInt046() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, 600, 4)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt047() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("1"), 600, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, -500, 4)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt048() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, 0, 5)</b> should be 0
     */
   public void testDivideBigDecimalIntInt049() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, 600, 5)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt050() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("1"), 600, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, -500, 5)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt051() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, 0, 6)</b> should be 0
     */
   public void testDivideBigDecimalIntInt052() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, 600, 6)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt053() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("1"), 600, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, -500, 6)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt054() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, 0, 7)</b> should be 0
     */
   public void testDivideBigDecimalIntInt055() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0, 7));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, 600, 7)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt056() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("1"), 600, 7));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, -500, 7)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt057() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, 7));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, 0, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt058() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("1"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, 600, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt059() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("1"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 1, -500, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt060() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("1"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt061() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("-0.1"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, 600, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt062() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("-0.1"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt063() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("-0.1"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 0)</b> should be 0
     */
   public void testDivideBigDecimalIntInt064() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), 0, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, 600, 0)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt065() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-0.1"), 600, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 0)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt066() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 1)</b> should be 0
     */
   public void testDivideBigDecimalIntInt067() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), 0, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, 600, 1)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt068() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-0.1"), 600, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 1)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt069() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 2)</b> should be 0
     */
   public void testDivideBigDecimalIntInt070() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), 0, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, 600, 2)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt071() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-0.1"), 600, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 2)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt072() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 3)</b> should be 0
     */
   public void testDivideBigDecimalIntInt073() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), 0, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, 600, 3)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt074() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-0.1"), 600, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 3)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt075() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 4)</b> should be 0
     */
   public void testDivideBigDecimalIntInt076() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), 0, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, 600, 4)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt077() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-0.1"), 600, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 4)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt078() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 5)</b> should be 0
     */
   public void testDivideBigDecimalIntInt079() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), 0, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, 600, 5)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt080() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-0.1"), 600, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 5)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt081() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 6)</b> should be 0
     */
   public void testDivideBigDecimalIntInt082() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), 0, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, 600, 6)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt083() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-0.1"), 600, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 6)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt084() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 7)</b> should be 0
     */
   public void testDivideBigDecimalIntInt085() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), 0, 7));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, 600, 7)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt086() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-0.1"), 600, 7));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 7)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt087() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, 7));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt088() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("-0.1"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, 600, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt089() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("-0.1"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt090() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("-0.1"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 0, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt091() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 600, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt092() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, -500, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt093() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 0, 0)</b> should be 0
     */
   public void testDivideBigDecimalIntInt094() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 600, 0)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt095() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, -500, 0)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt096() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 0, 1)</b> should be 0
     */
   public void testDivideBigDecimalIntInt097() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 600, 1)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt098() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, -500, 1)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt099() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 0, 2)</b> should be 0
     */
   public void testDivideBigDecimalIntInt100() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 600, 2)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt101() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, -500, 2)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt102() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 0, 3)</b> should be 0
     */
   public void testDivideBigDecimalIntInt103() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 600, 3)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt104() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, -500, 3)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt105() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 0, 4)</b> should be 0
     */
   public void testDivideBigDecimalIntInt106() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 600, 4)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt107() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, -500, 4)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt108() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 0, 5)</b> should be 0
     */
   public void testDivideBigDecimalIntInt109() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 600, 5)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt110() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, -500, 5)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt111() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 0, 6)</b> should be 0
     */
   public void testDivideBigDecimalIntInt112() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 600, 6)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt113() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, -500, 6)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt114() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 0, 7)</b> should be 0
     */
   public void testDivideBigDecimalIntInt115() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 7));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 600, 7)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt116() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 7));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, -500, 7)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt117() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 7));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 0, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt118() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 600, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt119() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, -500, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt120() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt121() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 600, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt122() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt123() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 0)</b> should be 0
     */
   public void testDivideBigDecimalIntInt124() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 600, 0)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt125() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 0)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt126() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 1)</b> should be 0
     */
   public void testDivideBigDecimalIntInt127() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 600, 1)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt128() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 1)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt129() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 2)</b> should be 0
     */
   public void testDivideBigDecimalIntInt130() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 600, 2)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt131() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 2)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt132() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 3)</b> should be 0
     */
   public void testDivideBigDecimalIntInt133() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 600, 3)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt134() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 3)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt135() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 4)</b> should be 0
     */
   public void testDivideBigDecimalIntInt136() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 600, 4)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt137() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 4)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt138() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 5)</b> should be 0
     */
   public void testDivideBigDecimalIntInt139() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 600, 5)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt140() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 5)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt141() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 6)</b> should be 0
     */
   public void testDivideBigDecimalIntInt142() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 600, 6)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt143() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 6)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt144() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 7)</b> should be 0
     */
   public void testDivideBigDecimalIntInt145() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 7));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 600, 7)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt146() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 7));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 7)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt147() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 7));
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt148() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 600, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt149() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt150() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, 0, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt151() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 0 the returning value of 
     * <b>divide(BigDecimal 969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, 600, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt152() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt153() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt154() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 0));
}
   public void testDivideBigDecimalIntInt155() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 0));
}
   public void testDivideBigDecimalIntInt156() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 0));
}
   public void testDivideBigDecimalIntInt157() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 1));
}
   public void testDivideBigDecimalIntInt158() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 1));
}
   public void testDivideBigDecimalIntInt159() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 1));
}
   public void testDivideBigDecimalIntInt160() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 2));
}
   public void testDivideBigDecimalIntInt161() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 2));
}
   public void testDivideBigDecimalIntInt162() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 2));
}
   public void testDivideBigDecimalIntInt163() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 3));
}
   public void testDivideBigDecimalIntInt164() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 3));
}
   public void testDivideBigDecimalIntInt165() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 3));
}
   public void testDivideBigDecimalIntInt166() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 4));
}
   public void testDivideBigDecimalIntInt167() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 4));
}
   public void testDivideBigDecimalIntInt168() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 4));
}
   public void testDivideBigDecimalIntInt169() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 5));
}
   public void testDivideBigDecimalIntInt170() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 5));
}
   public void testDivideBigDecimalIntInt171() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 5));
}
   public void testDivideBigDecimalIntInt172() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 6));
}
   public void testDivideBigDecimalIntInt173() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 6));
}
   public void testDivideBigDecimalIntInt174() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 6));
}
   public void testDivideBigDecimalIntInt175() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 7));
}
   public void testDivideBigDecimalIntInt176() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 7));
}
   public void testDivideBigDecimalIntInt177() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 7));
}
   public void testDivideBigDecimalIntInt178() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt179() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt180() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt181() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt182() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt183() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt184() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 0));
}
   public void testDivideBigDecimalIntInt185() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 0));
}
   public void testDivideBigDecimalIntInt186() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 0));
}
   public void testDivideBigDecimalIntInt187() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 1));
}
   public void testDivideBigDecimalIntInt188() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 1));
}
   public void testDivideBigDecimalIntInt189() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 1));
}
   public void testDivideBigDecimalIntInt190() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 2));
}
   public void testDivideBigDecimalIntInt191() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 2));
}
   public void testDivideBigDecimalIntInt192() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 2));
}
   public void testDivideBigDecimalIntInt193() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 3));
}
   public void testDivideBigDecimalIntInt194() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 3));
}
   public void testDivideBigDecimalIntInt195() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 3));
}
   public void testDivideBigDecimalIntInt196() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 4));
}
   public void testDivideBigDecimalIntInt197() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 4));
}
   public void testDivideBigDecimalIntInt198() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 4));
}
   public void testDivideBigDecimalIntInt199() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 5));
}
   public void testDivideBigDecimalIntInt200() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 5));
}
   public void testDivideBigDecimalIntInt201() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 5));
}
   public void testDivideBigDecimalIntInt202() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 6));
}
   public void testDivideBigDecimalIntInt203() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 6));
}
   public void testDivideBigDecimalIntInt204() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 6));
}
   public void testDivideBigDecimalIntInt205() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 7));
}
   public void testDivideBigDecimalIntInt206() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 7));
}
   public void testDivideBigDecimalIntInt207() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 7));
}
   public void testDivideBigDecimalIntInt208() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt209() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt210() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, 0, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt211() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, 600, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt212() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, -500, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt213() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, 0, 0)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt214() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), 0, 0);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, 600, 0)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt215() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), 600, 0);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, -500, 0)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt216() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), -500, 0);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, 0, 1)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt217() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), 0, 1);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, 600, 1)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt218() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), 600, 1);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, -500, 1)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt219() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), -500, 1);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, 0, 2)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt220() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), 0, 2);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, 600, 2)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt221() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), 600, 2);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, -500, 2)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt222() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), -500, 2);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, 0, 3)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt223() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), 0, 3);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, 600, 3)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt224() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), 600, 3);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, -500, 3)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt225() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), -500, 3);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, 0, 4)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt226() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), 0, 4);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, 600, 4)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt227() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), 600, 4);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, -500, 4)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt228() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), -500, 4);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, 0, 5)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt229() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), 0, 5);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, 600, 5)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt230() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), 600, 5);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, -500, 5)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt231() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), -500, 5);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, 0, 6)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt232() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), 0, 6);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, 600, 6)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt233() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), 600, 6);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, -500, 6)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt234() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), -500, 6);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, 0, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt235() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), 0, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, 600, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt236() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), 600, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, -500, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt237() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, 0, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt238() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, 600, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt239() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 0, -500, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt240() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 1, 0, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt241() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("1"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 1, 600, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt242() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("1"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 1, -500, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt243() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("1"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 1, 0, 0)</b> should be 1
     */
   public void testDivideBigDecimalIntInt244() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("1"), 0, 0));
}
   public void testDivideBigDecimalIntInt245() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 1, -500, 0)</b> should be 1E+500
     */
   public void testDivideBigDecimalIntInt246() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("1"), -500, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 1, 0, 1)</b> should be 1
     */
   public void testDivideBigDecimalIntInt247() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("1"), 0, 1));
}
   public void testDivideBigDecimalIntInt248() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 1, -500, 1)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt249() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 1, 0, 2)</b> should be 1
     */
   public void testDivideBigDecimalIntInt250() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("1"), 0, 2));
}
   public void testDivideBigDecimalIntInt251() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 1, -500, 2)</b> should be 1E+500
     */
   public void testDivideBigDecimalIntInt252() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("1"), -500, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 1, 0, 3)</b> should be 1
     */
   public void testDivideBigDecimalIntInt253() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("1"), 0, 3));
}
   public void testDivideBigDecimalIntInt254() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 1, -500, 3)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt255() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 1, 0, 4)</b> should be 1
     */
   public void testDivideBigDecimalIntInt256() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("1"), 0, 4));
}
   public void testDivideBigDecimalIntInt257() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 1, -500, 4)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt258() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 1, 0, 5)</b> should be 1
     */
   public void testDivideBigDecimalIntInt259() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("1"), 0, 5));
}
   public void testDivideBigDecimalIntInt260() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 1, -500, 5)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt261() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 1, 0, 6)</b> should be 1
     */
   public void testDivideBigDecimalIntInt262() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("1"), 0, 6));
}
   public void testDivideBigDecimalIntInt263() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 1, -500, 6)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt264() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 1, 0, 7)</b> should be 1
     */
   public void testDivideBigDecimalIntInt265() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("1"), 0, 7));
}
   public void testDivideBigDecimalIntInt266() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 7));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 1, -500, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt267() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("1"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 1, 0, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt268() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("1"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 1, 600, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt269() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("1"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal 1, -500, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt270() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("1"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt271() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-0.1"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -0.1, 600, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt272() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-0.1"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt273() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-0.1"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 0)</b> should be -10
     */
   public void testDivideBigDecimalIntInt274() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-10"),bigDec.divide(new BigDecimal("-0.1"), 0, 0));
}
   public void testDivideBigDecimalIntInt275() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-10.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 0)</b> should be -1E+500
     */
   public void testDivideBigDecimalIntInt276() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 1)</b> should be -10
     */
   public void testDivideBigDecimalIntInt277() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-10"),bigDec.divide(new BigDecimal("-0.1"), 0, 1));
}
   public void testDivideBigDecimalIntInt278() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-10.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 1)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt279() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 2)</b> should be -10
     */
   public void testDivideBigDecimalIntInt280() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-10"),bigDec.divide(new BigDecimal("-0.1"), 0, 2));
}
   public void testDivideBigDecimalIntInt281() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-10.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 2)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt282() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 3)</b> should be -10
     */
   public void testDivideBigDecimalIntInt283() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-10"),bigDec.divide(new BigDecimal("-0.1"), 0, 3));
}
   public void testDivideBigDecimalIntInt284() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-10.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 3)</b> should be -1E+500
     */
   public void testDivideBigDecimalIntInt285() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 4)</b> should be -10
     */
   public void testDivideBigDecimalIntInt286() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-10"),bigDec.divide(new BigDecimal("-0.1"), 0, 4));
}
   public void testDivideBigDecimalIntInt287() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-10.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 4)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt288() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 5)</b> should be -10
     */
   public void testDivideBigDecimalIntInt289() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-10"),bigDec.divide(new BigDecimal("-0.1"), 0, 5));
}
   public void testDivideBigDecimalIntInt290() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-10.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 5)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt291() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 6)</b> should be -10
     */
   public void testDivideBigDecimalIntInt292() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-10"),bigDec.divide(new BigDecimal("-0.1"), 0, 6));
}
   public void testDivideBigDecimalIntInt293() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-10.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 6)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt294() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 7)</b> should be -10
     */
   public void testDivideBigDecimalIntInt295() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-10"),bigDec.divide(new BigDecimal("-0.1"), 0, 7));
}
   public void testDivideBigDecimalIntInt296() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-10.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 7));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt297() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-0.1"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt298() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-0.1"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -0.1, 600, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt299() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-0.1"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt300() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-0.1"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 0, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt301() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 600, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt302() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, -500, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt303() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt304() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001430"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 0));
}
   public void testDivideBigDecimalIntInt305() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001429.199138701469601817900815730210160797549498812659101141078310918181186467405837258301854902611851539552925536812148087833931271404818099594442290634487673271506134948428318754989895579819853827441094339046313402182639856389245184214145993703346916654990935272059959180586750997010557400772424932321668872485957213441231258475122259772474253119966408687837697979413235517598820640224895938976119877225138639712028919948364454188296730140432006354554879596510122867286198899806165140246425809140641483919381207690903318303926335802807002427051876659970891435674484547102259508057186155800234724094919664"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 0));
}
   public void testDivideBigDecimalIntInt306() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1.0316102760589687712197314074433556050036868898293327725877491019079725021928354687598478757384519543252901530335684000135119584969720199726547590876476392996018041615977441845486624023113343810367833570681029778085717948378491868353783550158320237774E+750"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 0));
}
   public void testDivideBigDecimalIntInt307() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001429"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 1));
}
   public void testDivideBigDecimalIntInt308() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001429.199138701469601817900815730210160797549498812659101141078310918181186467405837258301854902611851539552925536812148087833931271404818099594442290634487673271506134948428318754989895579819853827441094339046313402182639856389245184214145993703346916654990935272059959180586750997010557400772424932321668872485957213441231258475122259772474253119966408687837697979413235517598820640224895938976119877225138639712028919948364454188296730140432006354554879596510122867286198899806165140246425809140641483919381207690903318303926335802807002427051876659970891435674484547102259508057186155800234724094919663"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 1));
}
   public void testDivideBigDecimalIntInt309() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1.0316102760589687712197314074433556050036868898293327725877491019079725021928354687598478757384519543252901530335684000135119584969720199726547590876476392996018041615977441845486624023113343810367833570681029778085717948378491868353783550158320237773E+750"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 1));
}
   public void testDivideBigDecimalIntInt310() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001429"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 2));
}
   public void testDivideBigDecimalIntInt311() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001429.199138701469601817900815730210160797549498812659101141078310918181186467405837258301854902611851539552925536812148087833931271404818099594442290634487673271506134948428318754989895579819853827441094339046313402182639856389245184214145993703346916654990935272059959180586750997010557400772424932321668872485957213441231258475122259772474253119966408687837697979413235517598820640224895938976119877225138639712028919948364454188296730140432006354554879596510122867286198899806165140246425809140641483919381207690903318303926335802807002427051876659970891435674484547102259508057186155800234724094919663"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 2));
}
   public void testDivideBigDecimalIntInt312() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1.0316102760589687712197314074433556050036868898293327725877491019079725021928354687598478757384519543252901530335684000135119584969720199726547590876476392996018041615977441845486624023113343810367833570681029778085717948378491868353783550158320237773E+750"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 2));
}
   public void testDivideBigDecimalIntInt313() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001430"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 3));
}
   public void testDivideBigDecimalIntInt314() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001429.199138701469601817900815730210160797549498812659101141078310918181186467405837258301854902611851539552925536812148087833931271404818099594442290634487673271506134948428318754989895579819853827441094339046313402182639856389245184214145993703346916654990935272059959180586750997010557400772424932321668872485957213441231258475122259772474253119966408687837697979413235517598820640224895938976119877225138639712028919948364454188296730140432006354554879596510122867286198899806165140246425809140641483919381207690903318303926335802807002427051876659970891435674484547102259508057186155800234724094919664"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 3));
}
   public void testDivideBigDecimalIntInt315() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1.0316102760589687712197314074433556050036868898293327725877491019079725021928354687598478757384519543252901530335684000135119584969720199726547590876476392996018041615977441845486624023113343810367833570681029778085717948378491868353783550158320237774E+750"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 3));
}
   public void testDivideBigDecimalIntInt316() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001429"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 4));
}
   public void testDivideBigDecimalIntInt317() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001429.199138701469601817900815730210160797549498812659101141078310918181186467405837258301854902611851539552925536812148087833931271404818099594442290634487673271506134948428318754989895579819853827441094339046313402182639856389245184214145993703346916654990935272059959180586750997010557400772424932321668872485957213441231258475122259772474253119966408687837697979413235517598820640224895938976119877225138639712028919948364454188296730140432006354554879596510122867286198899806165140246425809140641483919381207690903318303926335802807002427051876659970891435674484547102259508057186155800234724094919664"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 4));
}
   public void testDivideBigDecimalIntInt318() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1.0316102760589687712197314074433556050036868898293327725877491019079725021928354687598478757384519543252901530335684000135119584969720199726547590876476392996018041615977441845486624023113343810367833570681029778085717948378491868353783550158320237774E+750"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 4));
}
   public void testDivideBigDecimalIntInt319() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001429"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 5));
}
   public void testDivideBigDecimalIntInt320() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001429.199138701469601817900815730210160797549498812659101141078310918181186467405837258301854902611851539552925536812148087833931271404818099594442290634487673271506134948428318754989895579819853827441094339046313402182639856389245184214145993703346916654990935272059959180586750997010557400772424932321668872485957213441231258475122259772474253119966408687837697979413235517598820640224895938976119877225138639712028919948364454188296730140432006354554879596510122867286198899806165140246425809140641483919381207690903318303926335802807002427051876659970891435674484547102259508057186155800234724094919664"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 5));
}
   public void testDivideBigDecimalIntInt321() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1.0316102760589687712197314074433556050036868898293327725877491019079725021928354687598478757384519543252901530335684000135119584969720199726547590876476392996018041615977441845486624023113343810367833570681029778085717948378491868353783550158320237774E+750"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 5));
}
   public void testDivideBigDecimalIntInt322() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001429"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 6));
}
   public void testDivideBigDecimalIntInt323() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001429.199138701469601817900815730210160797549498812659101141078310918181186467405837258301854902611851539552925536812148087833931271404818099594442290634487673271506134948428318754989895579819853827441094339046313402182639856389245184214145993703346916654990935272059959180586750997010557400772424932321668872485957213441231258475122259772474253119966408687837697979413235517598820640224895938976119877225138639712028919948364454188296730140432006354554879596510122867286198899806165140246425809140641483919381207690903318303926335802807002427051876659970891435674484547102259508057186155800234724094919664"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 6));
}
   public void testDivideBigDecimalIntInt324() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1.0316102760589687712197314074433556050036868898293327725877491019079725021928354687598478757384519543252901530335684000135119584969720199726547590876476392996018041615977441845486624023113343810367833570681029778085717948378491868353783550158320237774E+750"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 0, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt325() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 600, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt326() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, -500, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt327() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 0, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt328() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 600, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt329() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, -500, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt330() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt331() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 600, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt332() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt333() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 0)</b> should be -1
     */
   public void testDivideBigDecimalIntInt334() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 0));
}
   public void testDivideBigDecimalIntInt335() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875739E-507"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 0)</b> should be -1E+500
     */
   public void testDivideBigDecimalIntInt336() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 1)</b> should be 0
     */
   public void testDivideBigDecimalIntInt337() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 1));
}
   public void testDivideBigDecimalIntInt338() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738E-507"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 1)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt339() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 2)</b> should be 0
     */
   public void testDivideBigDecimalIntInt340() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 2));
}
   public void testDivideBigDecimalIntInt341() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738E-507"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 2)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt342() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 3)</b> should be -1
     */
   public void testDivideBigDecimalIntInt343() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 3));
}
   public void testDivideBigDecimalIntInt344() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875739E-507"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 3)</b> should be -1E+500
     */
   public void testDivideBigDecimalIntInt345() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 4)</b> should be 0
     */
   public void testDivideBigDecimalIntInt346() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 4));
}
   public void testDivideBigDecimalIntInt347() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738E-507"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 4)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt348() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 5)</b> should be 0
     */
   public void testDivideBigDecimalIntInt349() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 5));
}
   public void testDivideBigDecimalIntInt350() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738E-507"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 5)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt351() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 6)</b> should be 0
     */
   public void testDivideBigDecimalIntInt352() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 6));
}
   public void testDivideBigDecimalIntInt353() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738E-507"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 6)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt354() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt355() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 600, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt356() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt357() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt358() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 600, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt359() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * 1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt360() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt361() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt362() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt363() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt364() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 0));
}
   public void testDivideBigDecimalIntInt365() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875739E-507"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 0));
}
   public void testDivideBigDecimalIntInt366() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 0));
}
   public void testDivideBigDecimalIntInt367() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 1));
}
   public void testDivideBigDecimalIntInt368() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738E-507"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 1));
}
   public void testDivideBigDecimalIntInt369() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 1));
}
   public void testDivideBigDecimalIntInt370() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 2));
}
   public void testDivideBigDecimalIntInt371() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875739E-507"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 2));
}
   public void testDivideBigDecimalIntInt372() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 2));
}
   public void testDivideBigDecimalIntInt373() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 3));
}
   public void testDivideBigDecimalIntInt374() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738E-507"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 3));
}
   public void testDivideBigDecimalIntInt375() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 3));
}
   public void testDivideBigDecimalIntInt376() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 4));
}
   public void testDivideBigDecimalIntInt377() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738E-507"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 4));
}
   public void testDivideBigDecimalIntInt378() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 4));
}
   public void testDivideBigDecimalIntInt379() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 5));
}
   public void testDivideBigDecimalIntInt380() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738E-507"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 5));
}
   public void testDivideBigDecimalIntInt381() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 5));
}
   public void testDivideBigDecimalIntInt382() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 6));
}
   public void testDivideBigDecimalIntInt383() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738E-507"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 6));
}
   public void testDivideBigDecimalIntInt384() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 6));
}
   public void testDivideBigDecimalIntInt385() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt386() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt387() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt388() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt389() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt390() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt391() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt392() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt393() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt394() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 0));
}
   public void testDivideBigDecimalIntInt395() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-2.07618283657996465747258171570026336665397047403619606116247958273894317567384498906898536284277101127456329863670152203694414071308940624741181765637958115712082541977393356989838998590860013277971362704160926934754024929934011729503040601593075703334247029978337268565350480029822973616190782327395445903353340377107009228753367258545457176364452718737061776243106443801176229968770089038043098804744109500348969800789772318502643268876065123937540776452264425651675052708717312463591804312103312764220864348448761542832950787851E-70"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 0));
}
   public void testDivideBigDecimalIntInt396() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 0));
}
   public void testDivideBigDecimalIntInt397() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 1));
}
   public void testDivideBigDecimalIntInt398() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-2.07618283657996465747258171570026336665397047403619606116247958273894317567384498906898536284277101127456329863670152203694414071308940624741181765637958115712082541977393356989838998590860013277971362704160926934754024929934011729503040601593075703334247029978337268565350480029822973616190782327395445903353340377107009228753367258545457176364452718737061776243106443801176229968770089038043098804744109500348969800789772318502643268876065123937540776452264425651675052708717312463591804312103312764220864348448761542832950787850E-70"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 1));
}
   public void testDivideBigDecimalIntInt399() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 1));
}
   public void testDivideBigDecimalIntInt400() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 2));
}
   public void testDivideBigDecimalIntInt401() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-2.07618283657996465747258171570026336665397047403619606116247958273894317567384498906898536284277101127456329863670152203694414071308940624741181765637958115712082541977393356989838998590860013277971362704160926934754024929934011729503040601593075703334247029978337268565350480029822973616190782327395445903353340377107009228753367258545457176364452718737061776243106443801176229968770089038043098804744109500348969800789772318502643268876065123937540776452264425651675052708717312463591804312103312764220864348448761542832950787850E-70"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 2));
}
   public void testDivideBigDecimalIntInt402() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 2));
}
   public void testDivideBigDecimalIntInt403() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 3));
}
   public void testDivideBigDecimalIntInt404() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-2.07618283657996465747258171570026336665397047403619606116247958273894317567384498906898536284277101127456329863670152203694414071308940624741181765637958115712082541977393356989838998590860013277971362704160926934754024929934011729503040601593075703334247029978337268565350480029822973616190782327395445903353340377107009228753367258545457176364452718737061776243106443801176229968770089038043098804744109500348969800789772318502643268876065123937540776452264425651675052708717312463591804312103312764220864348448761542832950787851E-70"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 3));
}
   public void testDivideBigDecimalIntInt405() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 3));
}
   public void testDivideBigDecimalIntInt406() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 4));
}
   public void testDivideBigDecimalIntInt407() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-2.07618283657996465747258171570026336665397047403619606116247958273894317567384498906898536284277101127456329863670152203694414071308940624741181765637958115712082541977393356989838998590860013277971362704160926934754024929934011729503040601593075703334247029978337268565350480029822973616190782327395445903353340377107009228753367258545457176364452718737061776243106443801176229968770089038043098804744109500348969800789772318502643268876065123937540776452264425651675052708717312463591804312103312764220864348448761542832950787851E-70"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 4));
}
   public void testDivideBigDecimalIntInt408() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 4));
}
   public void testDivideBigDecimalIntInt409() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 5));
}
   public void testDivideBigDecimalIntInt410() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-2.07618283657996465747258171570026336665397047403619606116247958273894317567384498906898536284277101127456329863670152203694414071308940624741181765637958115712082541977393356989838998590860013277971362704160926934754024929934011729503040601593075703334247029978337268565350480029822973616190782327395445903353340377107009228753367258545457176364452718737061776243106443801176229968770089038043098804744109500348969800789772318502643268876065123937540776452264425651675052708717312463591804312103312764220864348448761542832950787851E-70"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 5));
}
   public void testDivideBigDecimalIntInt411() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 5));
}
   public void testDivideBigDecimalIntInt412() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 6));
}
   public void testDivideBigDecimalIntInt413() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-2.07618283657996465747258171570026336665397047403619606116247958273894317567384498906898536284277101127456329863670152203694414071308940624741181765637958115712082541977393356989838998590860013277971362704160926934754024929934011729503040601593075703334247029978337268565350480029822973616190782327395445903353340377107009228753367258545457176364452718737061776243106443801176229968770089038043098804744109500348969800789772318502643268876065123937540776452264425651675052708717312463591804312103312764220864348448761542832950787851E-70"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 6));
}
   public void testDivideBigDecimalIntInt414() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 6));
}
   public void testDivideBigDecimalIntInt415() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt416() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt417() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt418() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt419() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt420() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, 0, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt421() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, 600, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt422() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, -500, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt423() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, 0, 0)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt424() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), 0, 0);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, 600, 0)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt425() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), 600, 0);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, -500, 0)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt426() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), -500, 0);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, 0, 1)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt427() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), 0, 1);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, 600, 1)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt428() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), 600, 1);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, -500, 1)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt429() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), -500, 1);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, 0, 2)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt430() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), 0, 2);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, 600, 2)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt431() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), 600, 2);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, -500, 2)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt432() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), -500, 2);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, 0, 3)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt433() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), 0, 3);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, 600, 3)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt434() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), 600, 3);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, -500, 3)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt435() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), -500, 3);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, 0, 4)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt436() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), 0, 4);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, 600, 4)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt437() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), 600, 4);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, -500, 4)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt438() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), -500, 4);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, 0, 5)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt439() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), 0, 5);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, 600, 5)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt440() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), 600, 5);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, -500, 5)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt441() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), -500, 5);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, 0, 6)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt442() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), 0, 6);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, 600, 6)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt443() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), 600, 6);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, -500, 6)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt444() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), -500, 6);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, 0, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt445() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), 0, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, 600, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt446() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), 600, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, -500, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt447() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, 0, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt448() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, 600, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt449() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 0, -500, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt450() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 1, 0, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt451() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("1"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 1, 600, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt452() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("1"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 1, -500, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt453() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("1"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 1, 0, 0)</b> should be -1
     */
   public void testDivideBigDecimalIntInt454() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("1"), 0, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 1, 600, 0)</b> should be -0.100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000
     */
   public void testDivideBigDecimalIntInt455() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-0.100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 1, -500, 0)</b> should be -1E+500
     */
   public void testDivideBigDecimalIntInt456() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("1"), -500, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 1, 0, 1)</b> should be 0
     */
   public void testDivideBigDecimalIntInt457() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0, 1));
}
   public void testDivideBigDecimalIntInt458() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-0.100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 1, -500, 1)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt459() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 1, 0, 2)</b> should be 0
     */
   public void testDivideBigDecimalIntInt460() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0, 2));
}
   public void testDivideBigDecimalIntInt461() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-0.100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 1, -500, 2)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt462() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 1, 0, 3)</b> should be -1
     */
   public void testDivideBigDecimalIntInt463() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("1"), 0, 3));
}
   public void testDivideBigDecimalIntInt464() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-0.100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 1, -500, 3)</b> should be -1E+500
     */
   public void testDivideBigDecimalIntInt465() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("1"), -500, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 1, 0, 4)</b> should be 0
     */
   public void testDivideBigDecimalIntInt466() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0, 4));
}
   public void testDivideBigDecimalIntInt467() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-0.100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 1, -500, 4)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt468() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 1, 0, 5)</b> should be 0
     */
   public void testDivideBigDecimalIntInt469() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0, 5));
}
   public void testDivideBigDecimalIntInt470() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-0.100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 1, -500, 5)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt471() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 1, 0, 6)</b> should be 0
     */
   public void testDivideBigDecimalIntInt472() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0, 6));
}
   public void testDivideBigDecimalIntInt473() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-0.100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 1, -500, 6)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt474() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 1, 0, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt475() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("1"), 0, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt476() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-0.100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 7));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 1, -500, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt477() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("1"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 1, 0, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt478() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("1"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 1, 600, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt479() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("1"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal 1, -500, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt480() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("1"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt481() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-0.1"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -0.1, 600, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt482() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-0.1"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt483() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-0.1"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 0)</b> should be 1
     */
   public void testDivideBigDecimalIntInt484() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-0.1"), 0, 0));
}
   public void testDivideBigDecimalIntInt485() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 0)</b> should be 1E+500
     */
   public void testDivideBigDecimalIntInt486() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 1)</b> should be 1
     */
   public void testDivideBigDecimalIntInt487() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-0.1"), 0, 1));
}
   public void testDivideBigDecimalIntInt488() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 1)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt489() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 2)</b> should be 1
     */
   public void testDivideBigDecimalIntInt490() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-0.1"), 0, 2));
}
   public void testDivideBigDecimalIntInt491() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 2)</b> should be 1E+500
     */
   public void testDivideBigDecimalIntInt492() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 3)</b> should be 1
     */
   public void testDivideBigDecimalIntInt493() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-0.1"), 0, 3));
}
   public void testDivideBigDecimalIntInt494() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 3)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt495() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 4)</b> should be 1
     */
   public void testDivideBigDecimalIntInt496() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-0.1"), 0, 4));
}
   public void testDivideBigDecimalIntInt497() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 4)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt498() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 5)</b> should be 1
     */
   public void testDivideBigDecimalIntInt499() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-0.1"), 0, 5));
}
   public void testDivideBigDecimalIntInt500() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 5)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt501() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 6)</b> should be 1
     */
   public void testDivideBigDecimalIntInt502() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-0.1"), 0, 6));
}
   public void testDivideBigDecimalIntInt503() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 6)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt504() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 7)</b> should be 1
     */
   public void testDivideBigDecimalIntInt505() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-0.1"), 0, 7));
}
   public void testDivideBigDecimalIntInt506() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 7));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt507() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-0.1"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt508() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-0.1"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -0.1, 600, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt509() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-0.1"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt510() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-0.1"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 0, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt511() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 600, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt512() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, -500, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt513() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt514() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000143"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 0));
}
   public void testDivideBigDecimalIntInt515() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000142.919913870146960181790081573021016079754949881265910114107831091818118646740583725830185490261185153955292553681214808783393127140481809959444229063448767327150613494842831875498989557981985382744109433904631340218263985638924518421414599370334691665499093527205995918058675099701055740077242493232166887248595721344123125847512225977247425311996640868783769797941323551759882064022489593897611987722513863971202891994836445418829673014043200635455487959651012286728619889980616514024642580914064148391938120769090331830392633580280700242705187665997089143567448454710225950805718615580023472409491967"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 0));
}
   public void testDivideBigDecimalIntInt516() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023778E+749"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 0));
}
   public void testDivideBigDecimalIntInt517() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000142"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 1));
}
   public void testDivideBigDecimalIntInt518() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000142.919913870146960181790081573021016079754949881265910114107831091818118646740583725830185490261185153955292553681214808783393127140481809959444229063448767327150613494842831875498989557981985382744109433904631340218263985638924518421414599370334691665499093527205995918058675099701055740077242493232166887248595721344123125847512225977247425311996640868783769797941323551759882064022489593897611987722513863971202891994836445418829673014043200635455487959651012286728619889980616514024642580914064148391938120769090331830392633580280700242705187665997089143567448454710225950805718615580023472409491966"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 1));
}
   public void testDivideBigDecimalIntInt519() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777E+749"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 1));
}
   public void testDivideBigDecimalIntInt520() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000143"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 2));
}
   public void testDivideBigDecimalIntInt521() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000142.919913870146960181790081573021016079754949881265910114107831091818118646740583725830185490261185153955292553681214808783393127140481809959444229063448767327150613494842831875498989557981985382744109433904631340218263985638924518421414599370334691665499093527205995918058675099701055740077242493232166887248595721344123125847512225977247425311996640868783769797941323551759882064022489593897611987722513863971202891994836445418829673014043200635455487959651012286728619889980616514024642580914064148391938120769090331830392633580280700242705187665997089143567448454710225950805718615580023472409491967"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 2));
}
   public void testDivideBigDecimalIntInt522() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023778E+749"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 2));
}
   public void testDivideBigDecimalIntInt523() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000142"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 3));
}
   public void testDivideBigDecimalIntInt524() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000142.919913870146960181790081573021016079754949881265910114107831091818118646740583725830185490261185153955292553681214808783393127140481809959444229063448767327150613494842831875498989557981985382744109433904631340218263985638924518421414599370334691665499093527205995918058675099701055740077242493232166887248595721344123125847512225977247425311996640868783769797941323551759882064022489593897611987722513863971202891994836445418829673014043200635455487959651012286728619889980616514024642580914064148391938120769090331830392633580280700242705187665997089143567448454710225950805718615580023472409491966"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 3));
}
   public void testDivideBigDecimalIntInt525() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777E+749"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 3));
}
   public void testDivideBigDecimalIntInt526() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000143"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 4));
}
   public void testDivideBigDecimalIntInt527() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000142.919913870146960181790081573021016079754949881265910114107831091818118646740583725830185490261185153955292553681214808783393127140481809959444229063448767327150613494842831875498989557981985382744109433904631340218263985638924518421414599370334691665499093527205995918058675099701055740077242493232166887248595721344123125847512225977247425311996640868783769797941323551759882064022489593897611987722513863971202891994836445418829673014043200635455487959651012286728619889980616514024642580914064148391938120769090331830392633580280700242705187665997089143567448454710225950805718615580023472409491966"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 4));
}
   public void testDivideBigDecimalIntInt528() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777E+749"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 4));
}
   public void testDivideBigDecimalIntInt529() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000143"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 5));
}
   public void testDivideBigDecimalIntInt530() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000142.919913870146960181790081573021016079754949881265910114107831091818118646740583725830185490261185153955292553681214808783393127140481809959444229063448767327150613494842831875498989557981985382744109433904631340218263985638924518421414599370334691665499093527205995918058675099701055740077242493232166887248595721344123125847512225977247425311996640868783769797941323551759882064022489593897611987722513863971202891994836445418829673014043200635455487959651012286728619889980616514024642580914064148391938120769090331830392633580280700242705187665997089143567448454710225950805718615580023472409491966"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 5));
}
   public void testDivideBigDecimalIntInt531() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777E+749"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 5));
}
   public void testDivideBigDecimalIntInt532() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000143"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 6));
}
   public void testDivideBigDecimalIntInt533() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000142.919913870146960181790081573021016079754949881265910114107831091818118646740583725830185490261185153955292553681214808783393127140481809959444229063448767327150613494842831875498989557981985382744109433904631340218263985638924518421414599370334691665499093527205995918058675099701055740077242493232166887248595721344123125847512225977247425311996640868783769797941323551759882064022489593897611987722513863971202891994836445418829673014043200635455487959651012286728619889980616514024642580914064148391938120769090331830392633580280700242705187665997089143567448454710225950805718615580023472409491966"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 6));
}
   public void testDivideBigDecimalIntInt534() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777E+749"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 0, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt535() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 600, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt536() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, -500, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt537() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 0, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt538() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 600, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt539() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, -500, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt540() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt541() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 600, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt542() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt543() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 0)</b> should be 1
     */
   public void testDivideBigDecimalIntInt544() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 0));
}
   public void testDivideBigDecimalIntInt545() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.03161027605896877121973140744335560500368688982933277258774910190797250219283546875984787574E-508"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 0)</b> should be 1E+500
     */
   public void testDivideBigDecimalIntInt546() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 1)</b> should be 0
     */
   public void testDivideBigDecimalIntInt547() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 1));
}
   public void testDivideBigDecimalIntInt548() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.03161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573E-508"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 1)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt549() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 2)</b> should be 1
     */
   public void testDivideBigDecimalIntInt550() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 2));
}
   public void testDivideBigDecimalIntInt551() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.03161027605896877121973140744335560500368688982933277258774910190797250219283546875984787574E-508"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 2)</b> should be 1E+500
     */
   public void testDivideBigDecimalIntInt552() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 3)</b> should be 0
     */
   public void testDivideBigDecimalIntInt553() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 3));
}
   public void testDivideBigDecimalIntInt554() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.03161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573E-508"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 3)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt555() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 4)</b> should be 0
     */
   public void testDivideBigDecimalIntInt556() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 4));
}
   public void testDivideBigDecimalIntInt557() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.03161027605896877121973140744335560500368688982933277258774910190797250219283546875984787574E-508"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 4)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt558() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 5)</b> should be 0
     */
   public void testDivideBigDecimalIntInt559() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 5));
}
   public void testDivideBigDecimalIntInt560() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.03161027605896877121973140744335560500368688982933277258774910190797250219283546875984787574E-508"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 5)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt561() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 6)</b> should be 0
     */
   public void testDivideBigDecimalIntInt562() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 6));
}
 assertEquals(msgNotSame,new BigDecimal("1.03161027605896877121973140744335560500368688982933277258774910190797250219283546875984787574E-508"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 6)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt564() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt565() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 600, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt566() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt567() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt568() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 600, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt569() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -0.1 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt570() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt571() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt572() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt573() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt574() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 0));
}
   public void testDivideBigDecimalIntInt575() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-1.03161027605896877121973140744335560500368688982933277258774910190797250219283546875984787574E-508"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 0));
}
   public void testDivideBigDecimalIntInt576() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 0));
}
   public void testDivideBigDecimalIntInt577() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 1));
}
   public void testDivideBigDecimalIntInt578() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-1.03161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573E-508"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 1));
}
   public void testDivideBigDecimalIntInt579() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 1));
}
   public void testDivideBigDecimalIntInt580() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 2));
}
   public void testDivideBigDecimalIntInt581() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-1.03161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573E-508"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 2));
}
   public void testDivideBigDecimalIntInt582() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 2));
}
   public void testDivideBigDecimalIntInt583() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 3));
}
   public void testDivideBigDecimalIntInt584() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-1.03161027605896877121973140744335560500368688982933277258774910190797250219283546875984787574E-508"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 3));
}
   public void testDivideBigDecimalIntInt585() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 3));
}
   public void testDivideBigDecimalIntInt586() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 4));
}
   public void testDivideBigDecimalIntInt587() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-1.03161027605896877121973140744335560500368688982933277258774910190797250219283546875984787574E-508"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 4));
}
   public void testDivideBigDecimalIntInt588() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 4));
}
   public void testDivideBigDecimalIntInt589() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 5));
}
   public void testDivideBigDecimalIntInt590() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-1.03161027605896877121973140744335560500368688982933277258774910190797250219283546875984787574E-508"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 5));
}
   public void testDivideBigDecimalIntInt591() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 5));
}
   public void testDivideBigDecimalIntInt592() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 6));
}
   public void testDivideBigDecimalIntInt593() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-1.03161027605896877121973140744335560500368688982933277258774910190797250219283546875984787574E-508"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 6));
}
   public void testDivideBigDecimalIntInt594() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 6));
}
   public void testDivideBigDecimalIntInt595() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt596() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt597() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt598() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt599() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt600() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt601() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt602() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt603() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt604() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 0));
}
   public void testDivideBigDecimalIntInt605() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("2.0761828365799646574725817157002633666539704740361960611624795827389431756738449890689853628427710112745632986367015220369441407130894062474118176563795811571208254197739335698983899859086001327797136270416092693475402492993401172950304060159307570333424702997833726856535048002982297361619078232739544590335334037710700922875336725854545717636445271873706177624310644380117622996877008903804309880474410950034896980078977231850264326887606512393754077645226442565167505270871731246359180431210331276422086434844876154283295078786E-71"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 0));
}
   public void testDivideBigDecimalIntInt606() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 0));
}
   public void testDivideBigDecimalIntInt607() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 1));
}
   public void testDivideBigDecimalIntInt608() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("2.0761828365799646574725817157002633666539704740361960611624795827389431756738449890689853628427710112745632986367015220369441407130894062474118176563795811571208254197739335698983899859086001327797136270416092693475402492993401172950304060159307570333424702997833726856535048002982297361619078232739544590335334037710700922875336725854545717636445271873706177624310644380117622996877008903804309880474410950034896980078977231850264326887606512393754077645226442565167505270871731246359180431210331276422086434844876154283295078785E-71"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 1));
}
   public void testDivideBigDecimalIntInt609() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 1));
}
   public void testDivideBigDecimalIntInt610() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 2));
}
   public void testDivideBigDecimalIntInt611() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("2.0761828365799646574725817157002633666539704740361960611624795827389431756738449890689853628427710112745632986367015220369441407130894062474118176563795811571208254197739335698983899859086001327797136270416092693475402492993401172950304060159307570333424702997833726856535048002982297361619078232739544590335334037710700922875336725854545717636445271873706177624310644380117622996877008903804309880474410950034896980078977231850264326887606512393754077645226442565167505270871731246359180431210331276422086434844876154283295078786E-71"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 2));
}
   public void testDivideBigDecimalIntInt612() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 2));
}
   public void testDivideBigDecimalIntInt613() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 3));
}
   public void testDivideBigDecimalIntInt614() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("2.0761828365799646574725817157002633666539704740361960611624795827389431756738449890689853628427710112745632986367015220369441407130894062474118176563795811571208254197739335698983899859086001327797136270416092693475402492993401172950304060159307570333424702997833726856535048002982297361619078232739544590335334037710700922875336725854545717636445271873706177624310644380117622996877008903804309880474410950034896980078977231850264326887606512393754077645226442565167505270871731246359180431210331276422086434844876154283295078785E-71"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 3));
}
   public void testDivideBigDecimalIntInt615() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 3));
}
   public void testDivideBigDecimalIntInt616() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 4));
}
   public void testDivideBigDecimalIntInt617() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("2.0761828365799646574725817157002633666539704740361960611624795827389431756738449890689853628427710112745632986367015220369441407130894062474118176563795811571208254197739335698983899859086001327797136270416092693475402492993401172950304060159307570333424702997833726856535048002982297361619078232739544590335334037710700922875336725854545717636445271873706177624310644380117622996877008903804309880474410950034896980078977231850264326887606512393754077645226442565167505270871731246359180431210331276422086434844876154283295078785E-71"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 4));
}
   public void testDivideBigDecimalIntInt618() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 4));
}
   public void testDivideBigDecimalIntInt619() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 5));
}
   public void testDivideBigDecimalIntInt620() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("2.0761828365799646574725817157002633666539704740361960611624795827389431756738449890689853628427710112745632986367015220369441407130894062474118176563795811571208254197739335698983899859086001327797136270416092693475402492993401172950304060159307570333424702997833726856535048002982297361619078232739544590335334037710700922875336725854545717636445271873706177624310644380117622996877008903804309880474410950034896980078977231850264326887606512393754077645226442565167505270871731246359180431210331276422086434844876154283295078785E-71"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 5));
}
   public void testDivideBigDecimalIntInt621() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 5));
}
   public void testDivideBigDecimalIntInt622() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 6));
}
   public void testDivideBigDecimalIntInt623() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("2.0761828365799646574725817157002633666539704740361960611624795827389431756738449890689853628427710112745632986367015220369441407130894062474118176563795811571208254197739335698983899859086001327797136270416092693475402492993401172950304060159307570333424702997833726856535048002982297361619078232739544590335334037710700922875336725854545717636445271873706177624310644380117622996877008903804309880474410950034896980078977231850264326887606512393754077645226442565167505270871731246359180431210331276422086434844876154283295078785E-71"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 6));
}
   public void testDivideBigDecimalIntInt624() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 6));
}
   public void testDivideBigDecimalIntInt625() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt626() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt627() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt628() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt629() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt630() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, 0, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt631() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, 600, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt632() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, -500, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt633() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, 0, 0)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt634() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), 0, 0);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, 600, 0)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt635() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), 600, 0);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, -500, 0)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt636() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), -500, 0);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, 0, 1)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt637() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), 0, 1);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, 600, 1)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt638() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), 600, 1);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, -500, 1)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt639() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), -500, 1);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, 0, 2)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt640() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), 0, 2);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, 600, 2)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt641() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), 600, 2);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, -500, 2)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt642() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), -500, 2);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, 0, 3)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt643() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), 0, 3);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, 600, 3)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt644() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), 600, 3);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, -500, 3)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt645() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), -500, 3);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, 0, 4)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt646() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), 0, 4);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, 600, 4)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt647() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), 600, 4);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, -500, 4)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt648() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), -500, 4);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, 0, 5)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt649() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), 0, 5);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, 600, 5)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt650() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), 600, 5);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, -500, 5)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt651() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), -500, 5);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, 0, 6)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt652() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), 0, 6);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, 600, 6)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt653() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), 600, 6);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, -500, 6)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt654() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), -500, 6);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, 0, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt655() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), 0, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, 600, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt656() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), 600, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, -500, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt657() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, 0, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt658() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, 600, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt659() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 0, -500, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt660() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, 0, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt661() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("1"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, 600, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt662() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("1"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, -500, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt663() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("1"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, 0, 0)</b> should be -1
     */
   public void testDivideBigDecimalIntInt664() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("1"), 0, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, 600, 0)</b> should be -1E-600
     */
   public void testDivideBigDecimalIntInt665() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("-1E-600"),bigDec.divide(new BigDecimal("1"), 600, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, -500, 0)</b> should be -1E+500
     */
   public void testDivideBigDecimalIntInt666() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("1"), -500, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, 0, 1)</b> should be 0
     */
   public void testDivideBigDecimalIntInt667() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, 600, 1)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt668() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("1"), 600, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, -500, 1)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt669() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, 0, 2)</b> should be 0
     */
   public void testDivideBigDecimalIntInt670() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, 600, 2)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt671() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("1"), 600, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, -500, 2)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt672() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, 0, 3)</b> should be -1
     */
   public void testDivideBigDecimalIntInt673() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("1"), 0, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, 600, 3)</b> should be -1E-600
     */
   public void testDivideBigDecimalIntInt674() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("-1E-600"),bigDec.divide(new BigDecimal("1"), 600, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, -500, 3)</b> should be -1E+500
     */
   public void testDivideBigDecimalIntInt675() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("1"), -500, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, 0, 4)</b> should be 0
     */
   public void testDivideBigDecimalIntInt676() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, 600, 4)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt677() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("1"), 600, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, -500, 4)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt678() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, 0, 5)</b> should be 0
     */
   public void testDivideBigDecimalIntInt679() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, 600, 5)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt680() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("1"), 600, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, -500, 5)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt681() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, 0, 6)</b> should be 0
     */
   public void testDivideBigDecimalIntInt682() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), 0, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, 600, 6)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt683() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("1"), 600, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, -500, 6)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt684() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, 0, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt685() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("1"), 0, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, 600, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt686() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("1"), 600, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, -500, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt687() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("1"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, 0, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt688() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("1"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, 600, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt689() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("1"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal 1, -500, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt690() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("1"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt691() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-0.1"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, 600, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt692() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-0.1"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt693() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-0.1"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 0)</b> should be 1
     */
   public void testDivideBigDecimalIntInt694() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-0.1"), 0, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, 600, 0)</b> should be 1E-600
     */
   public void testDivideBigDecimalIntInt695() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1E-600"),bigDec.divide(new BigDecimal("-0.1"), 600, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 0)</b> should be 1E+500
     */
   public void testDivideBigDecimalIntInt696() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 1)</b> should be 0
     */
   public void testDivideBigDecimalIntInt697() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), 0, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, 600, 1)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt698() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-0.1"), 600, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 1)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt699() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 2)</b> should be 1
     */
   public void testDivideBigDecimalIntInt700() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-0.1"), 0, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, 600, 2)</b> should be 1E-600
     */
   public void testDivideBigDecimalIntInt701() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1E-600"),bigDec.divide(new BigDecimal("-0.1"), 600, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 2)</b> should be 1E+500
     */
   public void testDivideBigDecimalIntInt702() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 3)</b> should be 0
     */
   public void testDivideBigDecimalIntInt703() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), 0, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, 600, 3)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt704() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-0.1"), 600, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 3)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt705() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 4)</b> should be 0
     */
   public void testDivideBigDecimalIntInt706() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), 0, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, 600, 4)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt707() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-0.1"), 600, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 4)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt708() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 5)</b> should be 0
     */
   public void testDivideBigDecimalIntInt709() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), 0, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, 600, 5)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt710() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-0.1"), 600, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 5)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt711() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 6)</b> should be 0
     */
   public void testDivideBigDecimalIntInt712() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), 0, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, 600, 6)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt713() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-0.1"), 600, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 6)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt714() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt715() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-0.1"), 0, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, 600, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt716() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-0.1"), 600, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt717() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-0.1"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt718() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-0.1"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, 600, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt719() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-0.1"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt720() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-0.1"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 0, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt721() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 600, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt722() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, -500, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt723() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 0, 0)</b> should be 1
     */
   public void testDivideBigDecimalIntInt724() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 0));
}
   public void testDivideBigDecimalIntInt725() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, -500, 0)</b> should be 1E+500
     */
   public void testDivideBigDecimalIntInt726() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 0, 1)</b> should be 1
     */
   public void testDivideBigDecimalIntInt727() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 1));
}
   public void testDivideBigDecimalIntInt728() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, -500, 1)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt729() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 0, 2)</b> should be 1
     */
   public void testDivideBigDecimalIntInt730() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 2));
}
   public void testDivideBigDecimalIntInt731() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, -500, 2)</b> should be 1E+500
     */
   public void testDivideBigDecimalIntInt732() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 0, 3)</b> should be 1
     */
   public void testDivideBigDecimalIntInt733() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 3));
}
   public void testDivideBigDecimalIntInt734() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, -500, 3)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt735() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 0, 4)</b> should be 1
     */
   public void testDivideBigDecimalIntInt736() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 4));
}
   public void testDivideBigDecimalIntInt737() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, -500, 4)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt738() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 0, 5)</b> should be 1
     */
   public void testDivideBigDecimalIntInt739() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 5));
}
   public void testDivideBigDecimalIntInt740() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, -500, 5)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt741() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 0, 6)</b> should be 1
     */
   public void testDivideBigDecimalIntInt742() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 6));
}
   public void testDivideBigDecimalIntInt743() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, -500, 6)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt744() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 0, 7)</b> should be 1
     */
   public void testDivideBigDecimalIntInt745() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 7));
}
   public void testDivideBigDecimalIntInt746() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 7));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, -500, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt747() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 0, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt748() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 600, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt749() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, -500, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt750() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt751() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 600, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt752() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt753() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 0)</b> should be 1
     */
   public void testDivideBigDecimalIntInt754() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 600, 0)</b> should be 1E-600
     */
   public void testDivideBigDecimalIntInt755() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 0)</b> should be 1E+500
     */
   public void testDivideBigDecimalIntInt756() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 1)</b> should be 0
     */
   public void testDivideBigDecimalIntInt757() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 600, 1)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt758() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 1)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt759() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 2)</b> should be 1
     */
   public void testDivideBigDecimalIntInt760() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 600, 2)</b> should be 1E-600
     */
   public void testDivideBigDecimalIntInt761() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 2)</b> should be 1E+500
     */
   public void testDivideBigDecimalIntInt762() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 3)</b> should be 0
     */
   public void testDivideBigDecimalIntInt763() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 600, 3)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt764() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 3)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt765() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 4)</b> should be 0
     */
   public void testDivideBigDecimalIntInt766() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 600, 4)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt767() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 4)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt768() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 5)</b> should be 0
     */
   public void testDivideBigDecimalIntInt769() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 600, 5)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt770() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 5)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt771() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 6)</b> should be 0
     */
   public void testDivideBigDecimalIntInt772() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 600, 6)</b> should be 0E-600
     */
   public void testDivideBigDecimalIntInt773() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 6)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt774() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt775() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 600, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt776() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt777() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt778() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 600, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt779() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E-751 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt780() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt781() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt782() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt783() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt784() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 0));
}
   public void testDivideBigDecimalIntInt785() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("-1E-600"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 0));
}
   public void testDivideBigDecimalIntInt786() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 0));
}
   public void testDivideBigDecimalIntInt787() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 1));
}
   public void testDivideBigDecimalIntInt788() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 1));
}
   public void testDivideBigDecimalIntInt789() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 1));
}
   public void testDivideBigDecimalIntInt790() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 2));
}
   public void testDivideBigDecimalIntInt791() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 2));
}
   public void testDivideBigDecimalIntInt792() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 2));
}
   public void testDivideBigDecimalIntInt793() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 3));
}
   public void testDivideBigDecimalIntInt794() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("-1E-600"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 3));
}
   public void testDivideBigDecimalIntInt795() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 3));
}
   public void testDivideBigDecimalIntInt796() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 4));
}
   public void testDivideBigDecimalIntInt797() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 4));
}
   public void testDivideBigDecimalIntInt798() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 4));
}
   public void testDivideBigDecimalIntInt799() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 5));
}
   public void testDivideBigDecimalIntInt800() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 5));
}
   public void testDivideBigDecimalIntInt801() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 5));
}
   public void testDivideBigDecimalIntInt802() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 6));
}
   public void testDivideBigDecimalIntInt803() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 6));
}
   public void testDivideBigDecimalIntInt804() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 6));
}
   public void testDivideBigDecimalIntInt805() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt806() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt807() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt808() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt809() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt810() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt811() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt812() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt813() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt814() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 0));
}
   public void testDivideBigDecimalIntInt815() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1E-600"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 0));
}
   public void testDivideBigDecimalIntInt816() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 0));
}
   public void testDivideBigDecimalIntInt817() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 1));
}
   public void testDivideBigDecimalIntInt818() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 1));
}
   public void testDivideBigDecimalIntInt819() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 1));
}
   public void testDivideBigDecimalIntInt820() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 2));
}
   public void testDivideBigDecimalIntInt821() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1E-600"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 2));
}
   public void testDivideBigDecimalIntInt822() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 2));
}
   public void testDivideBigDecimalIntInt823() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 3));
}
   public void testDivideBigDecimalIntInt824() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 3));
}
   public void testDivideBigDecimalIntInt825() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 3));
}
   public void testDivideBigDecimalIntInt826() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 4));
}
   public void testDivideBigDecimalIntInt827() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 4));
}
   public void testDivideBigDecimalIntInt828() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 4));
}
   public void testDivideBigDecimalIntInt829() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 5));
}
   public void testDivideBigDecimalIntInt830() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 5));
}
   public void testDivideBigDecimalIntInt831() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 5));
}
   public void testDivideBigDecimalIntInt832() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 6));
}
   public void testDivideBigDecimalIntInt833() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-600"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 6));
}
   public void testDivideBigDecimalIntInt834() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 6));
}
   public void testDivideBigDecimalIntInt835() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt836() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt837() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt838() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt839() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt840() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, 0, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt841() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, 600, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt842() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, -500, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt843() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, 0, 0)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt844() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), 0, 0);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, 600, 0)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt845() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), 600, 0);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, -500, 0)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt846() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), -500, 0);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, 0, 1)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt847() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), 0, 1);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, 600, 1)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt848() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), 600, 1);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, -500, 1)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt849() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), -500, 1);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, 0, 2)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt850() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), 0, 2);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, 600, 2)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt851() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), 600, 2);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, -500, 2)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt852() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), -500, 2);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, 0, 3)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt853() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), 0, 3);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, 600, 3)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt854() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), 600, 3);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, -500, 3)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt855() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), -500, 3);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, 0, 4)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt856() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), 0, 4);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, 600, 4)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt857() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), 600, 4);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, -500, 4)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt858() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), -500, 4);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, 0, 5)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt859() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), 0, 5);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, 600, 5)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt860() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), 600, 5);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, -500, 5)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt861() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), -500, 5);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, 0, 6)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt862() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), 0, 6);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, 600, 6)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt863() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), 600, 6);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, -500, 6)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt864() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), -500, 6);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, 0, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt865() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), 0, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, 600, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt866() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), 600, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, -500, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt867() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, 0, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt868() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, 600, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt869() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 0, -500, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt870() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 1, 0, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt871() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("1"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 1, 600, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt872() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("1"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 1, -500, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt873() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("1"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt874() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 0, 0));
}
   public void testDivideBigDecimalIntInt875() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 1, -500, 0)</b> should be -9.693584E+506
     */
   public void testDivideBigDecimalIntInt876() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-9.693584E+506"),bigDec.divide(new BigDecimal("1"), -500, 0));
}
   public void testDivideBigDecimalIntInt877() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 0, 1));
}
   public void testDivideBigDecimalIntInt878() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 1, -500, 1)</b> should be -9.693583E+506
     */
   public void testDivideBigDecimalIntInt879() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583E+506"),bigDec.divide(new BigDecimal("1"), -500, 1));
}
   public void testDivideBigDecimalIntInt880() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 0, 2));
}
   public void testDivideBigDecimalIntInt881() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 1, -500, 2)</b> should be -9.693583E+506
     */
   public void testDivideBigDecimalIntInt882() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583E+506"),bigDec.divide(new BigDecimal("1"), -500, 2));
}
   public void testDivideBigDecimalIntInt883() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 0, 3));
}
   public void testDivideBigDecimalIntInt884() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 1, -500, 3)</b> should be -9.693584E+506
     */
   public void testDivideBigDecimalIntInt885() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-9.693584E+506"),bigDec.divide(new BigDecimal("1"), -500, 3));
}
   public void testDivideBigDecimalIntInt886() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 0, 4));
}
   public void testDivideBigDecimalIntInt887() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 1, -500, 4)</b> should be -9.693583E+506
     */
   public void testDivideBigDecimalIntInt888() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583E+506"),bigDec.divide(new BigDecimal("1"), -500, 4));
}
   public void testDivideBigDecimalIntInt889() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 0, 5));
}
   public void testDivideBigDecimalIntInt890() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 1, -500, 5)</b> should be -9.693583E+506
     */
   public void testDivideBigDecimalIntInt891() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583E+506"),bigDec.divide(new BigDecimal("1"), -500, 5));
}
   public void testDivideBigDecimalIntInt892() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 0, 6));
}
   public void testDivideBigDecimalIntInt893() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 1, -500, 6)</b> should be -9.693583E+506
     */
   public void testDivideBigDecimalIntInt894() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583E+506"),bigDec.divide(new BigDecimal("1"), -500, 6));
}
   public void testDivideBigDecimalIntInt895() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 0, 7));
}
   public void testDivideBigDecimalIntInt896() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 7));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 1, -500, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt897() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("1"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 1, 0, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt898() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("1"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 1, 600, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt899() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("1"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 1, -500, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt900() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("1"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt901() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-0.1"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -0.1, 600, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt902() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-0.1"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt903() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-0.1"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt904() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 0, 0));
}
   public void testDivideBigDecimalIntInt905() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 0)</b> should be 9.6935832E+507
     */
   public void testDivideBigDecimalIntInt906() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9.6935832E+507"),bigDec.divide(new BigDecimal("-0.1"), -500, 0));
}
   public void testDivideBigDecimalIntInt907() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 0, 1));
}
   public void testDivideBigDecimalIntInt908() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 1)</b> should be 9.6935831E+507
     */
   public void testDivideBigDecimalIntInt909() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9.6935831E+507"),bigDec.divide(new BigDecimal("-0.1"), -500, 1));
}
   public void testDivideBigDecimalIntInt910() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 0, 2));
}
   public void testDivideBigDecimalIntInt911() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 2)</b> should be 9.6935832E+507
     */
   public void testDivideBigDecimalIntInt912() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9.6935832E+507"),bigDec.divide(new BigDecimal("-0.1"), -500, 2));
}
   public void testDivideBigDecimalIntInt913() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 0, 3));
}
   public void testDivideBigDecimalIntInt914() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 3)</b> should be 9.6935831E+507
     */
   public void testDivideBigDecimalIntInt915() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9.6935831E+507"),bigDec.divide(new BigDecimal("-0.1"), -500, 3));
}
   public void testDivideBigDecimalIntInt916() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 0, 4));
}
   public void testDivideBigDecimalIntInt917() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 4)</b> should be 9.6935832E+507
     */
   public void testDivideBigDecimalIntInt918() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9.6935832E+507"),bigDec.divide(new BigDecimal("-0.1"), -500, 4));
}
   public void testDivideBigDecimalIntInt919() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 0, 5));
}
   public void testDivideBigDecimalIntInt920() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 5)</b> should be 9.6935832E+507
     */
   public void testDivideBigDecimalIntInt921() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9.6935832E+507"),bigDec.divide(new BigDecimal("-0.1"), -500, 5));
}
   public void testDivideBigDecimalIntInt922() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 0, 6));
}
   public void testDivideBigDecimalIntInt923() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 6)</b> should be 9.6935832E+507
     */
   public void testDivideBigDecimalIntInt924() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9.6935832E+507"),bigDec.divide(new BigDecimal("-0.1"), -500, 6));
}
   public void testDivideBigDecimalIntInt925() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 0, 7));
}
   public void testDivideBigDecimalIntInt926() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 7));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt927() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-0.1"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -0.1, 0, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt928() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-0.1"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -0.1, 600, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt929() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-0.1"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -0.1, -500, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt930() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-0.1"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 0, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt931() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 600, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt932() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, -500, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt933() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt934() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 0));
}
   public void testDivideBigDecimalIntInt935() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 0));
}
   public void testDivideBigDecimalIntInt936() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 0));
}
   public void testDivideBigDecimalIntInt937() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 1));
}
   public void testDivideBigDecimalIntInt938() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 1));
}
   public void testDivideBigDecimalIntInt939() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 1));
}
   public void testDivideBigDecimalIntInt940() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 2));
}
   public void testDivideBigDecimalIntInt941() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 2));
}
   public void testDivideBigDecimalIntInt942() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 2));
}
   public void testDivideBigDecimalIntInt943() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 3));
}
   public void testDivideBigDecimalIntInt944() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 3));
}
   public void testDivideBigDecimalIntInt945() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 3));
}
   public void testDivideBigDecimalIntInt946() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 4));
}
   public void testDivideBigDecimalIntInt947() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 4));
}
   public void testDivideBigDecimalIntInt948() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 4));
}
   public void testDivideBigDecimalIntInt949() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 5));
}
   public void testDivideBigDecimalIntInt950() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 5));
}
   public void testDivideBigDecimalIntInt951() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 5));
}
   public void testDivideBigDecimalIntInt952() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 6));
}
   public void testDivideBigDecimalIntInt953() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 6));
}
   public void testDivideBigDecimalIntInt954() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 6));
}
   public void testDivideBigDecimalIntInt955() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 7));
}
   public void testDivideBigDecimalIntInt956() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 7));
}
   public void testDivideBigDecimalIntInt957() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 7));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 0, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt958() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, 600, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt959() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E-751, -500, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt960() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt961() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 600, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt962() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, -1)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt963() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 0)</b> should be 1
     */
   public void testDivideBigDecimalIntInt964() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 0));
}
   public void testDivideBigDecimalIntInt965() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 0)</b> should be 1E+500
     */
   public void testDivideBigDecimalIntInt966() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 0));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 1)</b> should be 1
     */
   public void testDivideBigDecimalIntInt967() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 1));
}
   public void testDivideBigDecimalIntInt968() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 1)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt969() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 1));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 2)</b> should be 1
     */
   public void testDivideBigDecimalIntInt970() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 2));
}
   public void testDivideBigDecimalIntInt971() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 2)</b> should be 1E+500
     */
   public void testDivideBigDecimalIntInt972() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 2));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 3)</b> should be 1
     */
   public void testDivideBigDecimalIntInt973() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 3));
}
   public void testDivideBigDecimalIntInt974() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 3)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt975() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 3));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 4)</b> should be 1
     */
   public void testDivideBigDecimalIntInt976() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 4));
}
   public void testDivideBigDecimalIntInt977() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 4)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt978() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 5)</b> should be 1
     */
   public void testDivideBigDecimalIntInt979() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 5));
}
   public void testDivideBigDecimalIntInt980() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 5)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt981() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 5));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 6)</b> should be 1
     */
   public void testDivideBigDecimalIntInt982() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 6));
}
   public void testDivideBigDecimalIntInt983() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 6)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt984() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 6));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 7)</b> should be 1
     */
   public void testDivideBigDecimalIntInt985() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 7));
}
   public void testDivideBigDecimalIntInt986() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 7));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 7)</b> should throw an <i>ArithmeticException</i>
     */
   public void testDivideBigDecimalIntInt987() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 0, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt988() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, 600, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt989() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal -9.693583160302274182E+506, -500, 8)</b> should throw an <i>IllegalArgumentException</i>
     */
   public void testDivideBigDecimalIntInt990() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt991() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt992() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt993() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt994() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 0));
}
   public void testDivideBigDecimalIntInt995() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 0));
}
   public void testDivideBigDecimalIntInt996() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 0));
}
   public void testDivideBigDecimalIntInt997() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 1));
}
   public void testDivideBigDecimalIntInt998() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 1));
}
   public void testDivideBigDecimalIntInt999() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 1));
}
   public void testDivideBigDecimalIntInt1000() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 2));
}
   public void testDivideBigDecimalIntInt1001() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 2));
}
   public void testDivideBigDecimalIntInt1002() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 2));
}
   public void testDivideBigDecimalIntInt1003() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 3));
}
   public void testDivideBigDecimalIntInt1004() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 3));
}
   public void testDivideBigDecimalIntInt1005() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 3));
}
   public void testDivideBigDecimalIntInt1006() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 4));
}
   public void testDivideBigDecimalIntInt1007() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 4));
}
    /** This method test that for a BigDecimal whose value is 
     * -9.693583160302274182E+506 the returning value of 
     * <b>divide(BigDecimal 969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, -500, 4)</b> should be 0E+500
     */
   public void testDivideBigDecimalIntInt1008() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 4));
}
   public void testDivideBigDecimalIntInt1009() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 5));
}
   public void testDivideBigDecimalIntInt1010() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 5));
}
   public void testDivideBigDecimalIntInt1011() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 5));
}
   public void testDivideBigDecimalIntInt1012() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 6));
}
   public void testDivideBigDecimalIntInt1013() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 6));
}
   public void testDivideBigDecimalIntInt1014() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 6));
}
   public void testDivideBigDecimalIntInt1015() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 7));
}
   public void testDivideBigDecimalIntInt1016() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 7));
}
   public void testDivideBigDecimalIntInt1017() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1018() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1019() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1020() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1021() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1022() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1023() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1024() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058821"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 0));
}
   public void testDivideBigDecimalIntInt1025() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820.600697140157680714794373765790240804089441878486603728799067422180952273406230765272142281448957283104588253356961565193923906596548490581764714131876932339280776672819054636424976476474469934016821834821980305484404905986074075443960732819806095082362128566355394772407583758319664078598513365217443156368163705514869753994013597149383596888184010989403638508885897718207827287681416208232665231672804875130753611630827868366205269578391717093187385607640345830863398349172307240577537520856375700516696804539730328639573594377497347920664344054413331897298544423309827730490403500573622217915775290"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 0));
}
   public void testDivideBigDecimalIntInt1026() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 0));
}
   public void testDivideBigDecimalIntInt1027() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 1));
}
   public void testDivideBigDecimalIntInt1028() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820.600697140157680714794373765790240804089441878486603728799067422180952273406230765272142281448957283104588253356961565193923906596548490581764714131876932339280776672819054636424976476474469934016821834821980305484404905986074075443960732819806095082362128566355394772407583758319664078598513365217443156368163705514869753994013597149383596888184010989403638508885897718207827287681416208232665231672804875130753611630827868366205269578391717093187385607640345830863398349172307240577537520856375700516696804539730328639573594377497347920664344054413331897298544423309827730490403500573622217915775289"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 1));
}
   public void testDivideBigDecimalIntInt1029() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 1));
}
   public void testDivideBigDecimalIntInt1030() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058821"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 2));
}
   public void testDivideBigDecimalIntInt1031() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820.600697140157680714794373765790240804089441878486603728799067422180952273406230765272142281448957283104588253356961565193923906596548490581764714131876932339280776672819054636424976476474469934016821834821980305484404905986074075443960732819806095082362128566355394772407583758319664078598513365217443156368163705514869753994013597149383596888184010989403638508885897718207827287681416208232665231672804875130753611630827868366205269578391717093187385607640345830863398349172307240577537520856375700516696804539730328639573594377497347920664344054413331897298544423309827730490403500573622217915775290"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 2));
}
   public void testDivideBigDecimalIntInt1032() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 2));
}
   public void testDivideBigDecimalIntInt1033() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 3));
}
   public void testDivideBigDecimalIntInt1034() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820.600697140157680714794373765790240804089441878486603728799067422180952273406230765272142281448957283104588253356961565193923906596548490581764714131876932339280776672819054636424976476474469934016821834821980305484404905986074075443960732819806095082362128566355394772407583758319664078598513365217443156368163705514869753994013597149383596888184010989403638508885897718207827287681416208232665231672804875130753611630827868366205269578391717093187385607640345830863398349172307240577537520856375700516696804539730328639573594377497347920664344054413331897298544423309827730490403500573622217915775289"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 3));
}
   public void testDivideBigDecimalIntInt1035() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 3));
}
   public void testDivideBigDecimalIntInt1036() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058821"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 4));
}
   public void testDivideBigDecimalIntInt1037() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820.600697140157680714794373765790240804089441878486603728799067422180952273406230765272142281448957283104588253356961565193923906596548490581764714131876932339280776672819054636424976476474469934016821834821980305484404905986074075443960732819806095082362128566355394772407583758319664078598513365217443156368163705514869753994013597149383596888184010989403638508885897718207827287681416208232665231672804875130753611630827868366205269578391717093187385607640345830863398349172307240577537520856375700516696804539730328639573594377497347920664344054413331897298544423309827730490403500573622217915775289"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 4));
}
   public void testDivideBigDecimalIntInt1038() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 4));
}
   public void testDivideBigDecimalIntInt1039() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058821"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 5));
}
   public void testDivideBigDecimalIntInt1040() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820.600697140157680714794373765790240804089441878486603728799067422180952273406230765272142281448957283104588253356961565193923906596548490581764714131876932339280776672819054636424976476474469934016821834821980305484404905986074075443960732819806095082362128566355394772407583758319664078598513365217443156368163705514869753994013597149383596888184010989403638508885897718207827287681416208232665231672804875130753611630827868366205269578391717093187385607640345830863398349172307240577537520856375700516696804539730328639573594377497347920664344054413331897298544423309827730490403500573622217915775289"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 5));
}
   public void testDivideBigDecimalIntInt1041() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 5));
}
   public void testDivideBigDecimalIntInt1042() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058821"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 6));
}
   public void testDivideBigDecimalIntInt1043() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820.600697140157680714794373765790240804089441878486603728799067422180952273406230765272142281448957283104588253356961565193923906596548490581764714131876932339280776672819054636424976476474469934016821834821980305484404905986074075443960732819806095082362128566355394772407583758319664078598513365217443156368163705514869753994013597149383596888184010989403638508885897718207827287681416208232665231672804875130753611630827868366205269578391717093187385607640345830863398349172307240577537520856375700516696804539730328639573594377497347920664344054413331897298544423309827730490403500573622217915775289"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 6));
}
   public void testDivideBigDecimalIntInt1044() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 6));
}
   public void testDivideBigDecimalIntInt1045() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1046() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1047() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1048() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1049() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1050() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1051() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1052() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1053() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1054() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), 0, 0);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1055() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), 600, 0);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1056() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), -500, 0);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1057() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), 0, 1);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1058() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), 600, 1);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1059() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), -500, 1);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1060() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), 0, 2);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1061() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), 600, 2);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1062() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), -500, 2);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1063() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), 0, 3);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1064() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), 600, 3);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1065() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), -500, 3);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1066() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), 0, 4);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1067() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), 600, 4);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1068() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), -500, 4);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1069() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), 0, 5);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1070() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), 600, 5);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1071() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), -500, 5);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1072() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), 0, 6);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1073() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), 600, 6);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1074() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), -500, 6);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1075() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), 0, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1076() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), 600, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1077() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1078() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1079() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1080() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1081() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("1"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1082() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("1"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1083() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("1"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1084() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 0, 0));
}
   public void testDivideBigDecimalIntInt1085() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 0));
}
   public void testDivideBigDecimalIntInt1086() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("9.693584E+506"),bigDec.divide(new BigDecimal("1"), -500, 0));
}
   public void testDivideBigDecimalIntInt1087() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 0, 1));
}
   public void testDivideBigDecimalIntInt1088() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 1));
}
   public void testDivideBigDecimalIntInt1089() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("9.693583E+506"),bigDec.divide(new BigDecimal("1"), -500, 1));
}
   public void testDivideBigDecimalIntInt1090() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 0, 2));
}
   public void testDivideBigDecimalIntInt1091() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 2));
}
   public void testDivideBigDecimalIntInt1092() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("9.693584E+506"),bigDec.divide(new BigDecimal("1"), -500, 2));
}
   public void testDivideBigDecimalIntInt1093() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 0, 3));
}
   public void testDivideBigDecimalIntInt1094() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 3));
}
   public void testDivideBigDecimalIntInt1095() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("9.693583E+506"),bigDec.divide(new BigDecimal("1"), -500, 3));
}
   public void testDivideBigDecimalIntInt1096() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 0, 4));
}
   public void testDivideBigDecimalIntInt1097() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 4));
}
   public void testDivideBigDecimalIntInt1098() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("9.693583E+506"),bigDec.divide(new BigDecimal("1"), -500, 4));
}
   public void testDivideBigDecimalIntInt1099() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 0, 5));
}
   public void testDivideBigDecimalIntInt1100() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 5));
}
   public void testDivideBigDecimalIntInt1101() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("9.693583E+506"),bigDec.divide(new BigDecimal("1"), -500, 5));
}
   public void testDivideBigDecimalIntInt1102() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 0, 6));
}
   public void testDivideBigDecimalIntInt1103() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 6));
}
   public void testDivideBigDecimalIntInt1104() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("9.693583E+506"),bigDec.divide(new BigDecimal("1"), -500, 6));
}
   public void testDivideBigDecimalIntInt1105() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 0, 7));
}
   public void testDivideBigDecimalIntInt1106() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 7));
}
   public void testDivideBigDecimalIntInt1107() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("1"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1108() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("1"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1109() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("1"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1110() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("1"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1111() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-0.1"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1112() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-0.1"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1113() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-0.1"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1114() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 0, 0));
}
   public void testDivideBigDecimalIntInt1115() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 0));
}
   public void testDivideBigDecimalIntInt1116() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9.6935832E+507"),bigDec.divide(new BigDecimal("-0.1"), -500, 0));
}
   public void testDivideBigDecimalIntInt1117() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 0, 1));
}
   public void testDivideBigDecimalIntInt1118() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 1));
}
   public void testDivideBigDecimalIntInt1119() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9.6935831E+507"),bigDec.divide(new BigDecimal("-0.1"), -500, 1));
}
   public void testDivideBigDecimalIntInt1120() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 0, 2));
}
   public void testDivideBigDecimalIntInt1121() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 2));
}
   public void testDivideBigDecimalIntInt1122() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9.6935831E+507"),bigDec.divide(new BigDecimal("-0.1"), -500, 2));
}
   public void testDivideBigDecimalIntInt1123() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 0, 3));
}
   public void testDivideBigDecimalIntInt1124() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 3));
}
   public void testDivideBigDecimalIntInt1125() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9.6935832E+507"),bigDec.divide(new BigDecimal("-0.1"), -500, 3));
}
   public void testDivideBigDecimalIntInt1126() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 0, 4));
}
   public void testDivideBigDecimalIntInt1127() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 4));
}
   public void testDivideBigDecimalIntInt1128() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9.6935832E+507"),bigDec.divide(new BigDecimal("-0.1"), -500, 4));
}
   public void testDivideBigDecimalIntInt1129() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 0, 5));
}
   public void testDivideBigDecimalIntInt1130() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 5));
}
   public void testDivideBigDecimalIntInt1131() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9.6935832E+507"),bigDec.divide(new BigDecimal("-0.1"), -500, 5));
}
   public void testDivideBigDecimalIntInt1132() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 0, 6));
}
   public void testDivideBigDecimalIntInt1133() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 6));
}
   public void testDivideBigDecimalIntInt1134() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9.6935832E+507"),bigDec.divide(new BigDecimal("-0.1"), -500, 6));
}
   public void testDivideBigDecimalIntInt1135() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 0, 7));
}
   public void testDivideBigDecimalIntInt1136() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 7));
}
   public void testDivideBigDecimalIntInt1137() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-0.1"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1138() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-0.1"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1139() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-0.1"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1140() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-0.1"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1141() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1142() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1143() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1144() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 0));
}
   public void testDivideBigDecimalIntInt1145() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 0));
}
   public void testDivideBigDecimalIntInt1146() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 0));
}
   public void testDivideBigDecimalIntInt1147() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 1));
}
   public void testDivideBigDecimalIntInt1148() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 1));
}
   public void testDivideBigDecimalIntInt1149() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 1));
}
   public void testDivideBigDecimalIntInt1150() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 2));
}
   public void testDivideBigDecimalIntInt1151() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 2));
}
   public void testDivideBigDecimalIntInt1152() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 2));
}
   public void testDivideBigDecimalIntInt1153() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 3));
}
   public void testDivideBigDecimalIntInt1154() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 3));
}
   public void testDivideBigDecimalIntInt1155() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 3));
}
   public void testDivideBigDecimalIntInt1156() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 4));
}
   public void testDivideBigDecimalIntInt1157() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 4));
}
   public void testDivideBigDecimalIntInt1158() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 4));
}
   public void testDivideBigDecimalIntInt1159() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 5));
}
   public void testDivideBigDecimalIntInt1160() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 5));
}
   public void testDivideBigDecimalIntInt1161() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 5));
}
   public void testDivideBigDecimalIntInt1162() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 6));
}
   public void testDivideBigDecimalIntInt1163() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 6));
}
   public void testDivideBigDecimalIntInt1164() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 6));
}
   public void testDivideBigDecimalIntInt1165() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 7));
}
   public void testDivideBigDecimalIntInt1166() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 7));
}
   public void testDivideBigDecimalIntInt1167() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 7));
}
   public void testDivideBigDecimalIntInt1168() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1169() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1170() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1171() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1172() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1173() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1174() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 0));
}
   public void testDivideBigDecimalIntInt1175() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 0));
}
   public void testDivideBigDecimalIntInt1176() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 0));
}
   public void testDivideBigDecimalIntInt1177() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 1));
}
   public void testDivideBigDecimalIntInt1178() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 1));
}
   public void testDivideBigDecimalIntInt1179() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 1));
}
   public void testDivideBigDecimalIntInt1180() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 2));
}
   public void testDivideBigDecimalIntInt1181() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 2));
}
   public void testDivideBigDecimalIntInt1182() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 2));
}
   public void testDivideBigDecimalIntInt1183() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 3));
}
   public void testDivideBigDecimalIntInt1184() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 3));
}
   public void testDivideBigDecimalIntInt1185() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 3));
}
   public void testDivideBigDecimalIntInt1186() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 4));
}
   public void testDivideBigDecimalIntInt1187() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 4));
}
   public void testDivideBigDecimalIntInt1188() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 4));
}
   public void testDivideBigDecimalIntInt1189() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 5));
}
   public void testDivideBigDecimalIntInt1190() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 5));
}
   public void testDivideBigDecimalIntInt1191() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 5));
}
   public void testDivideBigDecimalIntInt1192() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 6));
}
   public void testDivideBigDecimalIntInt1193() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 6));
}
   public void testDivideBigDecimalIntInt1194() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 6));
}
   public void testDivideBigDecimalIntInt1195() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 7));
}
   public void testDivideBigDecimalIntInt1196() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 7));
}
   public void testDivideBigDecimalIntInt1197() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1198() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1199() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1200() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1201() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1202() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1203() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1204() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 0));
}
   public void testDivideBigDecimalIntInt1205() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 0));
}
   public void testDivideBigDecimalIntInt1206() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 0));
}
   public void testDivideBigDecimalIntInt1207() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 1));
}
   public void testDivideBigDecimalIntInt1208() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 1));
}
   public void testDivideBigDecimalIntInt1209() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 1));
}
   public void testDivideBigDecimalIntInt1210() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 2));
}
   public void testDivideBigDecimalIntInt1211() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 2));
}
   public void testDivideBigDecimalIntInt1212() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 2));
}
   public void testDivideBigDecimalIntInt1213() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 3));
}
   public void testDivideBigDecimalIntInt1214() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 3));
}
   public void testDivideBigDecimalIntInt1215() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 3));
}
   public void testDivideBigDecimalIntInt1216() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 4));
}
   public void testDivideBigDecimalIntInt1217() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 4));
}
   public void testDivideBigDecimalIntInt1218() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 4));
}
   public void testDivideBigDecimalIntInt1219() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 5));
}
   public void testDivideBigDecimalIntInt1220() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 5));
}
   public void testDivideBigDecimalIntInt1221() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 5));
}
   public void testDivideBigDecimalIntInt1222() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 6));
}
   public void testDivideBigDecimalIntInt1223() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 6));
}
   public void testDivideBigDecimalIntInt1224() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 6));
}
   public void testDivideBigDecimalIntInt1225() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 7));
}
   public void testDivideBigDecimalIntInt1226() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 7));
}
   public void testDivideBigDecimalIntInt1227() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1228() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1229() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1230() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1231() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1232() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1233() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1234() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058821"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 0));
}
   public void testDivideBigDecimalIntInt1235() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820.600697140157680714794373765790240804089441878486603728799067422180952273406230765272142281448957283104588253356961565193923906596548490581764714131876932339280776672819054636424976476474469934016821834821980305484404905986074075443960732819806095082362128566355394772407583758319664078598513365217443156368163705514869753994013597149383596888184010989403638508885897718207827287681416208232665231672804875130753611630827868366205269578391717093187385607640345830863398349172307240577537520856375700516696804539730328639573594377497347920664344054413331897298544423309827730490403500573622217915775290"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 0));
}
   public void testDivideBigDecimalIntInt1236() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 0));
}
   public void testDivideBigDecimalIntInt1237() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 1));
}
   public void testDivideBigDecimalIntInt1238() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820.600697140157680714794373765790240804089441878486603728799067422180952273406230765272142281448957283104588253356961565193923906596548490581764714131876932339280776672819054636424976476474469934016821834821980305484404905986074075443960732819806095082362128566355394772407583758319664078598513365217443156368163705514869753994013597149383596888184010989403638508885897718207827287681416208232665231672804875130753611630827868366205269578391717093187385607640345830863398349172307240577537520856375700516696804539730328639573594377497347920664344054413331897298544423309827730490403500573622217915775289"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 1));
}
   public void testDivideBigDecimalIntInt1239() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 1));
}
   public void testDivideBigDecimalIntInt1240() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 2));
}
   public void testDivideBigDecimalIntInt1241() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820.600697140157680714794373765790240804089441878486603728799067422180952273406230765272142281448957283104588253356961565193923906596548490581764714131876932339280776672819054636424976476474469934016821834821980305484404905986074075443960732819806095082362128566355394772407583758319664078598513365217443156368163705514869753994013597149383596888184010989403638508885897718207827287681416208232665231672804875130753611630827868366205269578391717093187385607640345830863398349172307240577537520856375700516696804539730328639573594377497347920664344054413331897298544423309827730490403500573622217915775289"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 2));
}
   public void testDivideBigDecimalIntInt1242() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 2));
}
   public void testDivideBigDecimalIntInt1243() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058821"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 3));
}
   public void testDivideBigDecimalIntInt1244() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820.600697140157680714794373765790240804089441878486603728799067422180952273406230765272142281448957283104588253356961565193923906596548490581764714131876932339280776672819054636424976476474469934016821834821980305484404905986074075443960732819806095082362128566355394772407583758319664078598513365217443156368163705514869753994013597149383596888184010989403638508885897718207827287681416208232665231672804875130753611630827868366205269578391717093187385607640345830863398349172307240577537520856375700516696804539730328639573594377497347920664344054413331897298544423309827730490403500573622217915775290"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 3));
}
   public void testDivideBigDecimalIntInt1245() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 3));
}
   public void testDivideBigDecimalIntInt1246() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058821"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 4));
}
   public void testDivideBigDecimalIntInt1247() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820.600697140157680714794373765790240804089441878486603728799067422180952273406230765272142281448957283104588253356961565193923906596548490581764714131876932339280776672819054636424976476474469934016821834821980305484404905986074075443960732819806095082362128566355394772407583758319664078598513365217443156368163705514869753994013597149383596888184010989403638508885897718207827287681416208232665231672804875130753611630827868366205269578391717093187385607640345830863398349172307240577537520856375700516696804539730328639573594377497347920664344054413331897298544423309827730490403500573622217915775289"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 4));
}
   public void testDivideBigDecimalIntInt1248() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 4));
}
   public void testDivideBigDecimalIntInt1249() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058821"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 5));
}
   public void testDivideBigDecimalIntInt1250() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820.600697140157680714794373765790240804089441878486603728799067422180952273406230765272142281448957283104588253356961565193923906596548490581764714131876932339280776672819054636424976476474469934016821834821980305484404905986074075443960732819806095082362128566355394772407583758319664078598513365217443156368163705514869753994013597149383596888184010989403638508885897718207827287681416208232665231672804875130753611630827868366205269578391717093187385607640345830863398349172307240577537520856375700516696804539730328639573594377497347920664344054413331897298544423309827730490403500573622217915775289"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 5));
}
   public void testDivideBigDecimalIntInt1251() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 5));
}
   public void testDivideBigDecimalIntInt1252() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058821"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 6));
}
   public void testDivideBigDecimalIntInt1253() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820.600697140157680714794373765790240804089441878486603728799067422180952273406230765272142281448957283104588253356961565193923906596548490581764714131876932339280776672819054636424976476474469934016821834821980305484404905986074075443960732819806095082362128566355394772407583758319664078598513365217443156368163705514869753994013597149383596888184010989403638508885897718207827287681416208232665231672804875130753611630827868366205269578391717093187385607640345830863398349172307240577537520856375700516696804539730328639573594377497347920664344054413331897298544423309827730490403500573622217915775289"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 6));
}
   public void testDivideBigDecimalIntInt1254() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 6));
}
   public void testDivideBigDecimalIntInt1255() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1256() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1257() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1258() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1259() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1260() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1261() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1262() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1263() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1264() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), 0, 0);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1265() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), 600, 0);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1266() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), -500, 0);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}

   public void testDivideBigDecimalIntInt1267() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), 0, 1);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}

   public void testDivideBigDecimalIntInt1268() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), 600, 1);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1269() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), -500, 1);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1270() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), 0, 2);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1271() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), 600, 2);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1272() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), -500, 2);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1273() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), 0, 3);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1274() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), 600, 3);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1275() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), -500, 3);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1276() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), 0, 4);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1277() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), 600, 4);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1278() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), -500, 4);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1279() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), 0, 5);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1280() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), 600, 5);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1281() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), -500, 5);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1282() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), 0, 6);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1283() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), 600, 6);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1284() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), -500, 6);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1285() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), 0, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1286() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), 600, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1287() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1288() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1289() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1290() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1291() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("1"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1292() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("1"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1293() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("1"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1294() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454546"),bigDec.divide(new BigDecimal("1"), 0, 0));
}
   public void testDivideBigDecimalIntInt1295() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.489418974861534118511615741440741711100081450415045057404877897145555048970478904478601045961404506486044156704456048646468889789456123156454897481952603205618400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 0));
}
   public void testDivideBigDecimalIntInt1296() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("1"), -500, 0));
}
   public void testDivideBigDecimalIntInt1297() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545"),bigDec.divide(new BigDecimal("1"), 0, 1));
}
   public void testDivideBigDecimalIntInt1298() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.489418974861534118511615741440741711100081450415045057404877897145555048970478904478601045961404506486044156704456048646468889789456123156454897481952603205618400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 1));
}
   public void testDivideBigDecimalIntInt1299() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, 1));
}
   public void testDivideBigDecimalIntInt1300() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545"),bigDec.divide(new BigDecimal("1"), 0, 2));
}
   public void testDivideBigDecimalIntInt1301() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.489418974861534118511615741440741711100081450415045057404877897145555048970478904478601045961404506486044156704456048646468889789456123156454897481952603205618400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 2));
}
   public void testDivideBigDecimalIntInt1302() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, 2));
}
   public void testDivideBigDecimalIntInt1303() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454546"),bigDec.divide(new BigDecimal("1"), 0, 3));
}
   public void testDivideBigDecimalIntInt1304() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.489418974861534118511615741440741711100081450415045057404877897145555048970478904478601045961404506486044156704456048646468889789456123156454897481952603205618400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 3));
}
   public void testDivideBigDecimalIntInt1305() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("1"), -500, 3));
}
   public void testDivideBigDecimalIntInt1306() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545"),bigDec.divide(new BigDecimal("1"), 0, 4));
}
   public void testDivideBigDecimalIntInt1307() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.489418974861534118511615741440741711100081450415045057404877897145555048970478904478601045961404506486044156704456048646468889789456123156454897481952603205618400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 4));
}
   public void testDivideBigDecimalIntInt1308() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, 4));
}
   public void testDivideBigDecimalIntInt1309() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545"),bigDec.divide(new BigDecimal("1"), 0, 5));
}
   public void testDivideBigDecimalIntInt1310() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.489418974861534118511615741440741711100081450415045057404877897145555048970478904478601045961404506486044156704456048646468889789456123156454897481952603205618400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 5));
}
   public void testDivideBigDecimalIntInt1311() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, 5));
}
   public void testDivideBigDecimalIntInt1312() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545"),bigDec.divide(new BigDecimal("1"), 0, 6));
}
   public void testDivideBigDecimalIntInt1313() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.489418974861534118511615741440741711100081450415045057404877897145555048970478904478601045961404506486044156704456048646468889789456123156454897481952603205618400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 6));
}
   public void testDivideBigDecimalIntInt1314() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("1"), -500, 6));
}
   public void testDivideBigDecimalIntInt1315() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("1"), 0, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1316() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.489418974861534118511615741440741711100081450415045057404877897145555048970478904478601045961404506486044156704456048646468889789456123156454897481952603205618400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), 600, 7));
}
   public void testDivideBigDecimalIntInt1317() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("1"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1318() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("1"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1319() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("1"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1320() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("1"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1321() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-0.1"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1322() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-0.1"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1323() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-0.1"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1324() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545455"),bigDec.divide(new BigDecimal("-0.1"), 0, 0));
}
   public void testDivideBigDecimalIntInt1325() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545454.894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 0));
}
   public void testDivideBigDecimalIntInt1326() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, 0));
}
   public void testDivideBigDecimalIntInt1327() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545454"),bigDec.divide(new BigDecimal("-0.1"), 0, 1));
}
   public void testDivideBigDecimalIntInt1328() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545454.894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 1));
}
   public void testDivideBigDecimalIntInt1329() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, 1));
}
   public void testDivideBigDecimalIntInt1330() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545455"),bigDec.divide(new BigDecimal("-0.1"), 0, 2));
}
   public void testDivideBigDecimalIntInt1331() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545454.894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 2));
}
   public void testDivideBigDecimalIntInt1332() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, 2));
}
   public void testDivideBigDecimalIntInt1333() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545454"),bigDec.divide(new BigDecimal("-0.1"), 0, 3));
}
   public void testDivideBigDecimalIntInt1334() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545454.894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 3));
}
   public void testDivideBigDecimalIntInt1335() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, 3));
}
   public void testDivideBigDecimalIntInt1336() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545455"),bigDec.divide(new BigDecimal("-0.1"), 0, 4));
}
   public void testDivideBigDecimalIntInt1337() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545454.894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 4));
}
   public void testDivideBigDecimalIntInt1338() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, 4));
}
   public void testDivideBigDecimalIntInt1339() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545455"),bigDec.divide(new BigDecimal("-0.1"), 0, 5));
}
   public void testDivideBigDecimalIntInt1340() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545454.894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 5));
}
   public void testDivideBigDecimalIntInt1341() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, 5));
}
   public void testDivideBigDecimalIntInt1342() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545455"),bigDec.divide(new BigDecimal("-0.1"), 0, 6));
}
   public void testDivideBigDecimalIntInt1343() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545454.894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 6));
}
   public void testDivideBigDecimalIntInt1344() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-0.1"), -500, 6));
}
   public void testDivideBigDecimalIntInt1345() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-0.1"), 0, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1346() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545454.894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), 600, 7));
}
   public void testDivideBigDecimalIntInt1347() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-0.1"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1348() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-0.1"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1349() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-0.1"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1350() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-0.1"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1351() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1352() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1353() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1354() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104832"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 0));
}
   public void testDivideBigDecimalIntInt1355() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831.234670010310232080641018922418165747657291206102003862557230423954466053749071109052746961449830706086887987174301346041298269442387249029004750207613352218549424791426670691975678321162685302797300808545755910788399634722354981614992546349465999641366678987834008920484248159251353742322182885688156967105877992558581384513294124864743673409730540288715958165237460026624110163892636258018285514897669873081468259195112260489735506953890338342045799941324779393154801625759578869615975231239708726328681560393271121242811968977022340694259974262284789042293294974032928279705343278159631547388004140"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 0));
}
   public void testDivideBigDecimalIntInt1356() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4.9687833743886942356046508927943349662660903678934112903068506830221887811176600267263057539834034082552694058703603821563011036485984139738284768331846047491279568482630000021506029060538222023839998301549106997527016584630260383404030576260052457429787400116895597065859254362399857284607609595457217248297462250303179E+819"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 0));
}
   public void testDivideBigDecimalIntInt1357() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 1));
}
   public void testDivideBigDecimalIntInt1358() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831.234670010310232080641018922418165747657291206102003862557230423954466053749071109052746961449830706086887987174301346041298269442387249029004750207613352218549424791426670691975678321162685302797300808545755910788399634722354981614992546349465999641366678987834008920484248159251353742322182885688156967105877992558581384513294124864743673409730540288715958165237460026624110163892636258018285514897669873081468259195112260489735506953890338342045799941324779393154801625759578869615975231239708726328681560393271121242811968977022340694259974262284789042293294974032928279705343278159631547388004139"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 1));
}
   public void testDivideBigDecimalIntInt1359() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4.9687833743886942356046508927943349662660903678934112903068506830221887811176600267263057539834034082552694058703603821563011036485984139738284768331846047491279568482630000021506029060538222023839998301549106997527016584630260383404030576260052457429787400116895597065859254362399857284607609595457217248297462250303178E+819"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 1));
}
   public void testDivideBigDecimalIntInt1360() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104832"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 2));
}
   public void testDivideBigDecimalIntInt1361() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831.234670010310232080641018922418165747657291206102003862557230423954466053749071109052746961449830706086887987174301346041298269442387249029004750207613352218549424791426670691975678321162685302797300808545755910788399634722354981614992546349465999641366678987834008920484248159251353742322182885688156967105877992558581384513294124864743673409730540288715958165237460026624110163892636258018285514897669873081468259195112260489735506953890338342045799941324779393154801625759578869615975231239708726328681560393271121242811968977022340694259974262284789042293294974032928279705343278159631547388004140"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 2));
}
   public void testDivideBigDecimalIntInt1362() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4.9687833743886942356046508927943349662660903678934112903068506830221887811176600267263057539834034082552694058703603821563011036485984139738284768331846047491279568482630000021506029060538222023839998301549106997527016584630260383404030576260052457429787400116895597065859254362399857284607609595457217248297462250303179E+819"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 2));
}
   public void testDivideBigDecimalIntInt1363() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 3));
}
   public void testDivideBigDecimalIntInt1364() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831.234670010310232080641018922418165747657291206102003862557230423954466053749071109052746961449830706086887987174301346041298269442387249029004750207613352218549424791426670691975678321162685302797300808545755910788399634722354981614992546349465999641366678987834008920484248159251353742322182885688156967105877992558581384513294124864743673409730540288715958165237460026624110163892636258018285514897669873081468259195112260489735506953890338342045799941324779393154801625759578869615975231239708726328681560393271121242811968977022340694259974262284789042293294974032928279705343278159631547388004139"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 3));
}
   public void testDivideBigDecimalIntInt1365() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4.9687833743886942356046508927943349662660903678934112903068506830221887811176600267263057539834034082552694058703603821563011036485984139738284768331846047491279568482630000021506029060538222023839998301549106997527016584630260383404030576260052457429787400116895597065859254362399857284607609595457217248297462250303178E+819"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 3));
}
   public void testDivideBigDecimalIntInt1366() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 4));
}
   public void testDivideBigDecimalIntInt1367() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831.234670010310232080641018922418165747657291206102003862557230423954466053749071109052746961449830706086887987174301346041298269442387249029004750207613352218549424791426670691975678321162685302797300808545755910788399634722354981614992546349465999641366678987834008920484248159251353742322182885688156967105877992558581384513294124864743673409730540288715958165237460026624110163892636258018285514897669873081468259195112260489735506953890338342045799941324779393154801625759578869615975231239708726328681560393271121242811968977022340694259974262284789042293294974032928279705343278159631547388004139"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 4));
}
   public void testDivideBigDecimalIntInt1368() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4.9687833743886942356046508927943349662660903678934112903068506830221887811176600267263057539834034082552694058703603821563011036485984139738284768331846047491279568482630000021506029060538222023839998301549106997527016584630260383404030576260052457429787400116895597065859254362399857284607609595457217248297462250303178E+819"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 4));
}
   public void testDivideBigDecimalIntInt1369() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 5));
}
   public void testDivideBigDecimalIntInt1370() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831.234670010310232080641018922418165747657291206102003862557230423954466053749071109052746961449830706086887987174301346041298269442387249029004750207613352218549424791426670691975678321162685302797300808545755910788399634722354981614992546349465999641366678987834008920484248159251353742322182885688156967105877992558581384513294124864743673409730540288715958165237460026624110163892636258018285514897669873081468259195112260489735506953890338342045799941324779393154801625759578869615975231239708726328681560393271121242811968977022340694259974262284789042293294974032928279705343278159631547388004139"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 5));
}
   public void testDivideBigDecimalIntInt1371() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4.9687833743886942356046508927943349662660903678934112903068506830221887811176600267263057539834034082552694058703603821563011036485984139738284768331846047491279568482630000021506029060538222023839998301549106997527016584630260383404030576260052457429787400116895597065859254362399857284607609595457217248297462250303178E+819"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 5));
}
   public void testDivideBigDecimalIntInt1372() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 6));
}
   public void testDivideBigDecimalIntInt1373() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831.234670010310232080641018922418165747657291206102003862557230423954466053749071109052746961449830706086887987174301346041298269442387249029004750207613352218549424791426670691975678321162685302797300808545755910788399634722354981614992546349465999641366678987834008920484248159251353742322182885688156967105877992558581384513294124864743673409730540288715958165237460026624110163892636258018285514897669873081468259195112260489735506953890338342045799941324779393154801625759578869615975231239708726328681560393271121242811968977022340694259974262284789042293294974032928279705343278159631547388004139"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 6));
}
   public void testDivideBigDecimalIntInt1374() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4.9687833743886942356046508927943349662660903678934112903068506830221887811176600267263057539834034082552694058703603821563011036485984139738284768331846047491279568482630000021506029060538222023839998301549106997527016584630260383404030576260052457429787400116895597065859254362399857284607609595457217248297462250303178E+819"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 6));
}
   public void testDivideBigDecimalIntInt1375() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1376() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1377() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1378() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1379() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1380() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1381() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1382() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1383() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1384() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 0));
}
   public void testDivideBigDecimalIntInt1385() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4.968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127957E-438"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 0));
}
   public void testDivideBigDecimalIntInt1386() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 0));
}
   public void testDivideBigDecimalIntInt1387() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 1));
}
   public void testDivideBigDecimalIntInt1388() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4.968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956E-438"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 1));
}
   public void testDivideBigDecimalIntInt1389() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 1));
}
   public void testDivideBigDecimalIntInt1390() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 2));
}
   public void testDivideBigDecimalIntInt1391() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4.968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127957E-438"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 2));
}
   public void testDivideBigDecimalIntInt1392() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 2));
}
   public void testDivideBigDecimalIntInt1393() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 3));
}
   public void testDivideBigDecimalIntInt1394() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4.968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956E-438"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 3));
}
   public void testDivideBigDecimalIntInt1395() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 3));
}
   public void testDivideBigDecimalIntInt1396() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 4));
}
   public void testDivideBigDecimalIntInt1397() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4.968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127957E-438"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 4));
}
   public void testDivideBigDecimalIntInt1398() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 4));
}
   public void testDivideBigDecimalIntInt1399() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 5));
}
   public void testDivideBigDecimalIntInt1400() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4.968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127957E-438"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 5));
}
   public void testDivideBigDecimalIntInt1401() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 5));
}
   public void testDivideBigDecimalIntInt1402() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 6));
}
   public void testDivideBigDecimalIntInt1403() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4.968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127957E-438"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 6));
}
   public void testDivideBigDecimalIntInt1404() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 6));
}
   public void testDivideBigDecimalIntInt1405() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1406() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1407() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1408() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1409() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1410() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1411() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1412() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1413() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1414() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 0));
}
   public void testDivideBigDecimalIntInt1415() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4.968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127957E-438"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 0));
}
   public void testDivideBigDecimalIntInt1416() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 0));
}
   public void testDivideBigDecimalIntInt1417() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 1));
}
   public void testDivideBigDecimalIntInt1418() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4.968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956E-438"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 1));
}
   public void testDivideBigDecimalIntInt1419() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 1));
}
   public void testDivideBigDecimalIntInt1420() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 2));
}
   public void testDivideBigDecimalIntInt1421() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4.968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956E-438"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 2));
}
   public void testDivideBigDecimalIntInt1422() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 2));
}
   public void testDivideBigDecimalIntInt1423() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 3));
}
   public void testDivideBigDecimalIntInt1424() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4.968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127957E-438"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 3));
}
   public void testDivideBigDecimalIntInt1425() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-1E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 3));
}
   public void testDivideBigDecimalIntInt1426() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 4));
}
   public void testDivideBigDecimalIntInt1427() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4.968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127957E-438"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 4));
}
   public void testDivideBigDecimalIntInt1428() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 4));
}
   public void testDivideBigDecimalIntInt1429() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 5));
}
   public void testDivideBigDecimalIntInt1430() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4.968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127957E-438"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 5));
}
   public void testDivideBigDecimalIntInt1431() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 5));
}
   public void testDivideBigDecimalIntInt1432() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 6));
}
   public void testDivideBigDecimalIntInt1433() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4.968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127957E-438"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 6));
}
   public void testDivideBigDecimalIntInt1434() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 6));
}
   public void testDivideBigDecimalIntInt1435() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1436() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1437() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1438() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1439() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1440() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1441() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1442() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1443() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, -1);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1444() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 0));
}
   public void testDivideBigDecimalIntInt1445() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 0));
}
   public void testDivideBigDecimalIntInt1446() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 0));
}
   public void testDivideBigDecimalIntInt1447() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 1));
}
   public void testDivideBigDecimalIntInt1448() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 1));
}
   public void testDivideBigDecimalIntInt1449() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 1));
}
   public void testDivideBigDecimalIntInt1450() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 2));
}
   public void testDivideBigDecimalIntInt1451() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 2));
}
   public void testDivideBigDecimalIntInt1452() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 2));
}
   public void testDivideBigDecimalIntInt1453() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 3));
}
   public void testDivideBigDecimalIntInt1454() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 3));
}
   public void testDivideBigDecimalIntInt1455() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 3));
}
   public void testDivideBigDecimalIntInt1456() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 4));
}
   public void testDivideBigDecimalIntInt1457() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 4));
}
   public void testDivideBigDecimalIntInt1458() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 4));
}
   public void testDivideBigDecimalIntInt1459() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 5));
}
   public void testDivideBigDecimalIntInt1460() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 5));
}
   public void testDivideBigDecimalIntInt1461() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 5));
}
   public void testDivideBigDecimalIntInt1462() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 6));
}
   public void testDivideBigDecimalIntInt1463() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 6));
}
   public void testDivideBigDecimalIntInt1464() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E+500"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 6));
}
   public void testDivideBigDecimalIntInt1465() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 7));
}
   public void testDivideBigDecimalIntInt1466() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 7));
}
   public void testDivideBigDecimalIntInt1467() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 7);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
   public void testDivideBigDecimalIntInt1468() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 0, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1469() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), 600, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}
   public void testDivideBigDecimalIntInt1470() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), -500, 8);
  fail(msgRaise+"IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
}

}
