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
 * divide(BigDecimal , RoundingMode ) 
 *
 */
public class TestBigDecimalDivideRoundingMode extends TestCase implements Messages{
	BigDecimal bigDec=null;
	
		
	public TestBigDecimalDivideRoundingMode(String name) {
		super(name);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	/*
	 * Test method for 'java.math.BigDecimal.divide(BigDecimal, RoundingMode)'
	 */
	
    
   public void testDivideBigDecimalRoundingMode001() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.UP);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode002() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.DOWN);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode003() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.CEILING);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode004() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.FLOOR);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode005() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.HALF_UP);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode006() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.HALF_DOWN);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode007() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.HALF_EVEN);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode008() {
	try {
    bigDec= new BigDecimal("0");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.UNNECESSARY);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode009() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode010() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode011() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode012() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode013() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode014() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode015() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode016() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("1"), RoundingMode.UNNECESSARY));
}
    
   public void testDivideBigDecimalRoundingMode017() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode018() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode019() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode020() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode021() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode022() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode023() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode024() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.UNNECESSARY));
}
    
   public void testDivideBigDecimalRoundingMode025() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode026() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode027() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode028() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode029() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode030() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode031() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode032() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.UNNECESSARY));
}
    
   public void testDivideBigDecimalRoundingMode033() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode034() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode035() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode036() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode037() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode038() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode039() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode040() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.UNNECESSARY));
}
    
   public void testDivideBigDecimalRoundingMode041() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode042() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode043() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode044() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode045() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode046() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode047() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode048() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.UNNECESSARY));
}
    
   public void testDivideBigDecimalRoundingMode049() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode050() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode051() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode052() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode053() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode054() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode055() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode056() {
    bigDec= new BigDecimal("0");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.UNNECESSARY));
}
    
   public void testDivideBigDecimalRoundingMode057() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.UP);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode058() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.DOWN);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode059() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.CEILING);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode060() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.FLOOR);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode061() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.HALF_UP);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode062() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.HALF_DOWN);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode063() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.HALF_EVEN);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode064() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.UNNECESSARY);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode065() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("1"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode066() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("1"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode067() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("1"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode068() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("1"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode069() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("1"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode070() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("1"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode071() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("1"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode072() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("1"), RoundingMode.UNNECESSARY));
}
    
   public void testDivideBigDecimalRoundingMode073() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-10"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode074() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-10"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode075() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-10"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode076() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-10"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode077() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-10"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode078() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-10"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode079() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-10"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode080() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-10"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.UNNECESSARY));
}
    
   public void testDivideBigDecimalRoundingMode081() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001430"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode082() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001429"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode083() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001429"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode084() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001430"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode085() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001429"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode086() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001429"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode087() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1031610276058968771219731407443355605003686889829332772587749101907972502192835468759847875738451954325290153033568400013511958496972019972654759087647639299601804161597744184548662402311334381036783357068102977808571794837849186835378355015832023777375194866878241426725819792640840137373936804157207604169734679621339696698947747350443885747703422714404349270616171792039611494454827818686178103119073174503345417999060938319828245853850132194464256263761856664118514513682918388902681888852922777673964665205330994254157170883813611505826399032162482319752182738349504861528508658079789638458005852224311091551793473682610313223913213939506539542334174732162743726045512931807763073213000282941270421587651488128750680169003592420820663527060001429"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode088() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.UNNECESSARY);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode089() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode090() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode091() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode092() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode093() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode094() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode095() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode096() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.UNNECESSARY);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode097() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode098() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode099() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode100() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode101() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode102() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode103() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode104() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.UNNECESSARY);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode105() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode106() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode107() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode108() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode109() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode110() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode111() {
    bigDec= new BigDecimal("1");  
 assertEquals(msgNotSame,new BigDecimal("0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode112() {
	try {
    bigDec= new BigDecimal("1");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.UNNECESSARY);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode113() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.UP);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode114() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.DOWN);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode115() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.CEILING);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode116() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.FLOOR);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode117() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.HALF_UP);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode118() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.HALF_DOWN);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode119() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.HALF_EVEN);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode120() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.UNNECESSARY);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode121() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-0.1"),bigDec.divide(new BigDecimal("1"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode122() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-0.1"),bigDec.divide(new BigDecimal("1"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode123() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-0.1"),bigDec.divide(new BigDecimal("1"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode124() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-0.1"),bigDec.divide(new BigDecimal("1"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode125() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-0.1"),bigDec.divide(new BigDecimal("1"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode126() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-0.1"),bigDec.divide(new BigDecimal("1"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode127() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-0.1"),bigDec.divide(new BigDecimal("1"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode128() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-0.1"),bigDec.divide(new BigDecimal("1"), RoundingMode.UNNECESSARY));
}
    
   public void testDivideBigDecimalRoundingMode129() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.0"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode130() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.0"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode131() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.0"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode132() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.0"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode133() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.0"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode134() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.0"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode135() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.0"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode136() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("1.0"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.UNNECESSARY));
}
    
   public void testDivideBigDecimalRoundingMode137() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000143.0"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode138() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000142.9"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode139() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000143.0"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode140() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000142.9"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode141() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000142.9"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode142() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000142.9"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode143() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("103161027605896877121973140744335560500368688982933277258774910190797250219283546875984787573845195432529015303356840001351195849697201997265475908764763929960180416159774418454866240231133438103678335706810297780857179483784918683537835501583202377737519486687824142672581979264084013737393680415720760416973467962133969669894774735044388574770342271440434927061617179203961149445482781868617810311907317450334541799906093831982824585385013219446425626376185666411851451368291838890268188885292277767396466520533099425415717088381361150582639903216248231975218273834950486152850865807978963845800585222431109155179347368261031322391321393950653954233417473216274372604551293180776307321300028294127042158765148812875068016900359242082066352706000142.9"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode144() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.UNNECESSARY);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode145() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode146() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode147() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode148() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode149() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode150() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode151() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.0"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode152() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.UNNECESSARY);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode153() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-0.1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode154() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode155() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode156() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("-0.1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode157() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode158() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode159() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.0"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode160() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.UNNECESSARY);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode161() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.1"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode162() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode163() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.1"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode164() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode165() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode166() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode167() {
    bigDec= new BigDecimal("-0.1");  
 assertEquals(msgNotSame,new BigDecimal("0.0"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode168() {
	try {
    bigDec= new BigDecimal("-0.1");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.UNNECESSARY);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode169() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.UP);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode170() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.DOWN);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode171() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.CEILING);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode172() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.FLOOR);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode173() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.HALF_UP);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode174() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.HALF_DOWN);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode175() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.HALF_EVEN);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode176() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.UNNECESSARY);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode177() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583160302274182E-751"),bigDec.divide(new BigDecimal("1"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode178() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583160302274182E-751"),bigDec.divide(new BigDecimal("1"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode179() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583160302274182E-751"),bigDec.divide(new BigDecimal("1"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode180() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583160302274182E-751"),bigDec.divide(new BigDecimal("1"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode181() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583160302274182E-751"),bigDec.divide(new BigDecimal("1"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode182() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583160302274182E-751"),bigDec.divide(new BigDecimal("1"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode183() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583160302274182E-751"),bigDec.divide(new BigDecimal("1"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode184() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583160302274182E-751"),bigDec.divide(new BigDecimal("1"), RoundingMode.UNNECESSARY));
}
    
   public void testDivideBigDecimalRoundingMode185() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("9.6935831603022741820E-750"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode186() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("9.6935831603022741820E-750"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode187() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("9.6935831603022741820E-750"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode188() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("9.6935831603022741820E-750"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode189() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("9.6935831603022741820E-750"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode190() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("9.6935831603022741820E-750"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode191() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("9.6935831603022741820E-750"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode192() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("9.6935831603022741820E-750"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.UNNECESSARY));
}
    
   public void testDivideBigDecimalRoundingMode193() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode194() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode195() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode196() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode197() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode198() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode199() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode200() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.UNNECESSARY));
}
    
   public void testDivideBigDecimalRoundingMode201() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1E-769"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode202() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-769"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode203() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1E-769"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode204() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-769"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode205() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-769"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode206() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-769"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode207() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-769"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode208() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.UNNECESSARY);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode209() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("-1E-769"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode210() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-769"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode211() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-769"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode212() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("-1E-769"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode213() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-769"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode214() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-769"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode215() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-769"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode216() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.UNNECESSARY);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode217() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1E-769"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode218() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-769"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode219() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("1E-769"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode220() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-769"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode221() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-769"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode222() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-769"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode223() {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 assertEquals(msgNotSame,new BigDecimal("0E-769"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode224() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E-751");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.UNNECESSARY);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode225() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.UP);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode226() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.DOWN);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode227() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.CEILING);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode228() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.FLOOR);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode229() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.HALF_UP);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode230() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.HALF_DOWN);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode231() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.HALF_EVEN);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode232() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.UNNECESSARY);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode233() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583160302274182E+506"),bigDec.divide(new BigDecimal("1"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode234() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583160302274182E+506"),bigDec.divide(new BigDecimal("1"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode235() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583160302274182E+506"),bigDec.divide(new BigDecimal("1"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode236() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583160302274182E+506"),bigDec.divide(new BigDecimal("1"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode237() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583160302274182E+506"),bigDec.divide(new BigDecimal("1"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode238() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583160302274182E+506"),bigDec.divide(new BigDecimal("1"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode239() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583160302274182E+506"),bigDec.divide(new BigDecimal("1"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode240() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-9.693583160302274182E+506"),bigDec.divide(new BigDecimal("1"), RoundingMode.UNNECESSARY));
}
    
   public void testDivideBigDecimalRoundingMode241() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9.6935831603022741820E+507"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode242() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9.6935831603022741820E+507"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode243() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9.6935831603022741820E+507"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode244() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9.6935831603022741820E+507"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode245() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9.6935831603022741820E+507"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode246() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9.6935831603022741820E+507"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode247() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9.6935831603022741820E+507"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode248() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("9.6935831603022741820E+507"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.UNNECESSARY));
}
    
   public void testDivideBigDecimalRoundingMode249() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode250() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode251() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode252() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode253() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode254() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode255() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode256() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+1257"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.UNNECESSARY));
}
    
   public void testDivideBigDecimalRoundingMode257() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1E+488"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode258() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+488"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode259() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1E+488"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode260() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+488"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode261() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+488"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode262() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+488"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode263() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+488"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode264() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.UNNECESSARY);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode265() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-1E+488"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode266() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+488"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode267() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+488"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode268() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("-1E+488"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode269() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+488"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode270() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+488"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode271() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+488"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode272() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.UNNECESSARY);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode273() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1E+488"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode274() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+488"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode275() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("1E+488"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode276() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+488"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode277() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+488"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode278() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+488"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode279() {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 assertEquals(msgNotSame,new BigDecimal("0E+488"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode280() {
	try {
    bigDec= new BigDecimal("-9.693583160302274182E+506");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.UNNECESSARY);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode281() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.UP);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode282() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.DOWN);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode283() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.CEILING);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode284() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.FLOOR);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode285() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.HALF_UP);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode286() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.HALF_DOWN);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode287() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.HALF_EVEN);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode288() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.UNNECESSARY);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode289() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode290() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode291() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode292() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode293() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode294() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode295() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode296() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("1"), RoundingMode.UNNECESSARY));
}
    
   public void testDivideBigDecimalRoundingMode297() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode298() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode299() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode300() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode301() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode302() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode303() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode304() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-9693583160302274182000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.UNNECESSARY));
}
    
   public void testDivideBigDecimalRoundingMode305() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode306() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode307() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode308() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode309() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode310() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode311() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode312() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.UNNECESSARY));
}
    
   public void testDivideBigDecimalRoundingMode313() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode314() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode315() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode316() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode317() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode318() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode319() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode320() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-1"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.UNNECESSARY));
}
    
   public void testDivideBigDecimalRoundingMode321() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode322() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode323() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode324() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode325() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode326() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode327() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode328() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("1"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.UNNECESSARY));
}
    
   public void testDivideBigDecimalRoundingMode329() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058821"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode330() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode331() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058820"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode332() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058821"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode333() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058821"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode334() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058821"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode335() {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 assertEquals(msgNotSame,new BigDecimal("-201256509823801538656810347754624154041032874005943823710343855098615567288730208064607943844048012947505487300651122304663212880289461238880519861851738712717350423501918444390526884732796192023947096366141435554554758698859305626925656025391284467480534771616745950493811014829071390472135334587070775175339044241023393619726582139181948474713001947476739318150378611121592032445648657656252904613792527253372799120282070520794781058821"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode336() {
	try {
    bigDec= new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");  
 bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.UNNECESSARY);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode337() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.UP);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode338() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.DOWN);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode339() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.CEILING);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode340() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.FLOOR);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode341() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.HALF_UP);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode342() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.HALF_DOWN);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode343() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.HALF_EVEN);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode344() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("0"), RoundingMode.UNNECESSARY);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode345() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"),bigDec.divide(new BigDecimal("1"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode346() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"),bigDec.divide(new BigDecimal("1"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode347() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"),bigDec.divide(new BigDecimal("1"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode348() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"),bigDec.divide(new BigDecimal("1"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode349() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"),bigDec.divide(new BigDecimal("1"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode350() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"),bigDec.divide(new BigDecimal("1"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode351() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"),bigDec.divide(new BigDecimal("1"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode352() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"),bigDec.divide(new BigDecimal("1"), RoundingMode.UNNECESSARY));
}
    
   public void testDivideBigDecimalRoundingMode353() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545454.8941897486153411851161574144074171110008145041504505740487789714555504897047890447860104596140450648604415670445604864646888978945612315645489748195260320561840"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode354() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545454.8941897486153411851161574144074171110008145041504505740487789714555504897047890447860104596140450648604415670445604864646888978945612315645489748195260320561840"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode355() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545454.8941897486153411851161574144074171110008145041504505740487789714555504897047890447860104596140450648604415670445604864646888978945612315645489748195260320561840"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode356() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545454.8941897486153411851161574144074171110008145041504505740487789714555504897047890447860104596140450648604415670445604864646888978945612315645489748195260320561840"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode357() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545454.8941897486153411851161574144074171110008145041504505740487789714555504897047890447860104596140450648604415670445604864646888978945612315645489748195260320561840"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode358() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545454.8941897486153411851161574144074171110008145041504505740487789714555504897047890447860104596140450648604415670445604864646888978945612315645489748195260320561840"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode359() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545454.8941897486153411851161574144074171110008145041504505740487789714555504897047890447860104596140450648604415670445604864646888978945612315645489748195260320561840"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode360() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("48165314845164156666674858612185641896411118965411856418965418974545454.8941897486153411851161574144074171110008145041504505740487789714555504897047890447860104596140450648604415670445604864646888978945612315645489748195260320561840"),bigDec.divide(new BigDecimal("-0.1"), RoundingMode.UNNECESSARY));
}
    
   public void testDivideBigDecimalRoundingMode361() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831.2346700103102320806410189224181657476572912061020038625572304239544660537490711090527469614498307060868879871743013460412982694423872490290047502076133522185495"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode362() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831.2346700103102320806410189224181657476572912061020038625572304239544660537490711090527469614498307060868879871743013460412982694423872490290047502076133522185494"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode363() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831.2346700103102320806410189224181657476572912061020038625572304239544660537490711090527469614498307060868879871743013460412982694423872490290047502076133522185495"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode364() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831.2346700103102320806410189224181657476572912061020038625572304239544660537490711090527469614498307060868879871743013460412982694423872490290047502076133522185494"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode365() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831.2346700103102320806410189224181657476572912061020038625572304239544660537490711090527469614498307060868879871743013460412982694423872490290047502076133522185494"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode366() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831.2346700103102320806410189224181657476572912061020038625572304239544660537490711090527469614498307060868879871743013460412982694423872490290047502076133522185494"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode367() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("4968783374388694235604650892794334966266090367893411290306850683022188781117660026726305753983403408255269405870360382156301103648598413973828476833184604749127956848263000002150602906053822202383999830154910699752701658463026038340403057626005245742978740011689559706585925436239985728460760959545721724829746225030317826309479223970703944394516410507997753302208094397029678906923316741699784447423649313034695665255692243957175666929967337511182982595580192875097876643509143957005715510968583280261172222848107017217508117103945886798792836486650649083979996631954503933271378389078018856489488672831900935569161767132949104695586407300240178650615823632636090998902479843132556362950273406208681145668524618650326189366555215798659060968599237317403747104600886095892522236213932091663370317647216193879550682104831.2346700103102320806410189224181657476572912061020038625572304239544660537490711090527469614498307060868879871743013460412982694423872490290047502076133522185494"),bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode368() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E-751"), RoundingMode.UNNECESSARY);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode369() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1E-160"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode370() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E-160"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode371() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1E-160"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode372() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E-160"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode373() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E-160"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode374() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E-160"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode375() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E-160"),bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode376() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("-9.693583160302274182E+506"), RoundingMode.UNNECESSARY);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode377() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-1E-160"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode378() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E-160"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode379() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E-160"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode380() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("-1E-160"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode381() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E-160"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode382() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E-160"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode383() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("0E-160"),bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode384() {
	try {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 bigDec.divide(new BigDecimal("969358316030227418200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), RoundingMode.UNNECESSARY);
  fail(msgRaise+"ArithmeticException");
} catch (ArithmeticException e) {
}
}
    
   public void testDivideBigDecimalRoundingMode385() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.UP));
}
    
   public void testDivideBigDecimalRoundingMode386() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.DOWN));
}
    
   public void testDivideBigDecimalRoundingMode387() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.CEILING));
}
    
   public void testDivideBigDecimalRoundingMode388() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.FLOOR));
}
    
   public void testDivideBigDecimalRoundingMode389() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.HALF_UP));
}
    
   public void testDivideBigDecimalRoundingMode390() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.HALF_DOWN));
}
    
   public void testDivideBigDecimalRoundingMode391() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.HALF_EVEN));
}
    
   public void testDivideBigDecimalRoundingMode392() {
    bigDec= new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184");  
 assertEquals(msgNotSame,new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),bigDec.divide(new BigDecimal("-4816531484516415666667485861218564189641111896541185641896541897454545.4894189748615341185116157414407417111000814504150450574048778971455550489704789044786010459614045064860441567044560486464688897894561231564548974819526032056184"), RoundingMode.UNNECESSARY));
}


}

