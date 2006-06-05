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
import java.math.MathContext;
import java.math.RoundingMode;

import junit.framework.TestCase;
import ar.org.fitc.test.util.Messages;
/**
 * This class verifies that a NullPointerException is thrown when null is passed as a parameter in BigDecimal methods.  
 * 
 *
 */
public class TestNullBigDecimal extends TestCase implements Messages{		
	
	public TestNullBigDecimal(String args) {
		super(args);
	}
	
	public static void main(String args[]){
		junit.textui.TestRunner.run( TestNullBigDecimal.class );
	}
	
	
	
	public void testNullBigInteger001() {
		try {
			BigInteger bi=null;
			new BigDecimal(bi);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullBigIntegerInt001() {
		try {
			BigInteger bi=null;
			new BigDecimal(bi,0);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullBigIntegerInt002() {
		try {
			BigInteger bi=null;
			new BigDecimal(bi,Integer.MAX_VALUE);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullBigIntegerInt003() {
		try {
			BigInteger bi=null;
			new BigDecimal(bi,Integer.MIN_VALUE);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	
	public void testNullBigIntegerIntMathContext001() {
		try {
			BigInteger bi=null;
			new BigDecimal(bi,0, new MathContext(0));
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullBigIntegerIntMathContext002() {
		try {
			BigInteger bi=null;
			new BigDecimal(bi,Integer.MAX_VALUE,new MathContext(0, RoundingMode.UP) );
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullBigIntegerIntMathContext003() {
		try {
			BigInteger bi=null;
			new BigDecimal(bi,Integer.MIN_VALUE, new MathContext("precision=0 roundingMode=FLOOR"));
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullBigIntegerIntMathContext004() {
		try {
			BigInteger bi=null;
			new BigDecimal(bi,0, null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullBigIntegerIntMathContext005() {
		try {
			BigInteger bi=null;
			new BigDecimal(bi,Integer.MAX_VALUE,null );
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullBigIntegerIntMathContext006() {
		try {
			BigInteger bi=null;
			new BigDecimal(bi,Integer.MIN_VALUE, null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	public void testNullBigIntegerIntMathContext007() {
		try {
			BigInteger bi=new BigInteger("0");
			new BigDecimal(bi,0, null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullBigIntegerIntMathContext008() {
		try {
			BigInteger bi=new BigInteger("0");
			new BigDecimal(bi,Integer.MAX_VALUE,null );
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullBigIntegerIntMathContext009() {
		try {
			BigInteger bi=new BigInteger("0");
			new BigDecimal(bi,Integer.MIN_VALUE, null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullBigIntegerMathContext001() {
		try {
			BigInteger bi=null;
			new BigDecimal(bi, new MathContext(0));
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	
	
	public void testNullBigIntegerMathContext002() {
		try {
			BigInteger bi=null;
			new BigDecimal(bi,null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullBigIntegerMathContext007() {
		try {
			BigInteger bi=new BigInteger("0");
			new BigDecimal(bi,null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullCharArray001() {
		try {
			char[] c=null;
			new BigDecimal(c);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	
	public void testNullCharArrayMathContext001() {
		try {
			char[] c=null;
			new BigDecimal(c,null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullCharArrayMathContext002() {
		try {
			char[] c = {'0'};
			new BigDecimal(c,null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	public void testNullCharArrayMathContext003() {
		try {
			char[] c=null;
			new BigDecimal(c,new MathContext("precision=0 roundingMode=DOWN"));
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}				
	
	public void testNullCharArrayMathContext004() {
		try {
			char[] c=null;
			new BigDecimal(c,new MathContext(0));
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}	
	
	public void testNullCharArrayMathContext005() {
		try {
			char[] c=null;
			new BigDecimal(c,new MathContext(0, RoundingMode.CEILING));
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}	
	public void testNullCharArrayIntInt001() {
		try {
			char[] c=null;
			new BigDecimal(c,0,0);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullCharArrayIntInt002() {
		try {
			char[] c=null;
			new BigDecimal(c,Integer.MAX_VALUE,0);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullCharArrayIntInt003() {
		try {
			char[] c=null;
			new BigDecimal(c,Integer.MIN_VALUE,0);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullCharArrayIntInt004() {
		try {
			char[] c=null;
			new BigDecimal(c,0,Integer.MAX_VALUE);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullCharArrayIntInt005() {
		try {
			char[] c=null;
			new BigDecimal(c,Integer.MAX_VALUE,Integer.MAX_VALUE);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullCharArrayIntInt006() {
		try {
			char[] c=null;
			new BigDecimal(c,Integer.MIN_VALUE,Integer.MAX_VALUE);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullCharArrayIntInt007() {
		try {
			char[] c=null;
			new BigDecimal(c,0,Integer.MIN_VALUE);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullCharArrayIntInt008() {
		try {
			char[] c=null;
			new BigDecimal(c,Integer.MAX_VALUE,Integer.MIN_VALUE);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullCharArrayIntInt009() {
		try {
			char[] c=null;
			new BigDecimal(c,Integer.MIN_VALUE,Integer.MIN_VALUE);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullCharArrayIntIntMathContext001() {
		try {
			char[] c=null;
			new BigDecimal(c,0,0,null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullCharArrayIntIntMathContext002() {
		try {
			char[] c=null;
			new BigDecimal(c,Integer.MAX_VALUE,0,null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullCharArrayIntIntMathContext003() {
		try {
			char[] c=null;
			new BigDecimal(c,Integer.MIN_VALUE,0,null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullCharArrayIntIntMathContext004() {
		try {
			char[] c=null;
			new BigDecimal(c,0,Integer.MAX_VALUE,null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullCharArrayIntIntMathContext005() {
		try {
			char[] c=null;
			new BigDecimal(c,Integer.MAX_VALUE,Integer.MAX_VALUE,null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullCharArrayIntIntMathContext006() {
		try {
			char[] c=null;
			new BigDecimal(c,Integer.MIN_VALUE,Integer.MAX_VALUE,null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullCharArrayIntIntMathContext007() {
		try {
			char[] c=null;
			new BigDecimal(c,0,Integer.MIN_VALUE,null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullCharArrayIntIntMathContext008() {
		try {
			char[] c=null;
			new BigDecimal(c,Integer.MAX_VALUE,Integer.MIN_VALUE,null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullCharArrayIntIntMathContext009() {
		try {
			char[] c=null;
			new BigDecimal(c,Integer.MIN_VALUE,Integer.MIN_VALUE,null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}					
	
	public void testNullCharArrayIntIntMathContext010() {
		try {
			char[] c={'0'};			
			new BigDecimal(c,0,1,null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	
	
	public void testNullCharArrayIntIntMathContext011() {
		try {
			char[] c=null;
			new BigDecimal(c,0,0,new MathContext(0));
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullCharArrayIntIntMathContext012() {
		try {
			char[] c=null;
			new BigDecimal(c,Integer.MAX_VALUE,0,new MathContext(0));
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullCharArrayIntIntMathContext013() {
		try {
			char[] c=null;
			new BigDecimal(c,Integer.MIN_VALUE,0,new MathContext(0));
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullCharArrayIntIntMathContext014() {
		try {
			char[] c=null;
			new BigDecimal(c,0,Integer.MAX_VALUE,new MathContext(0));
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullCharArrayIntIntMathContext015() {
		try {
			char[] c=null;
			new BigDecimal(c,Integer.MAX_VALUE,Integer.MAX_VALUE,new MathContext(0));
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullCharArrayIntIntMathContext016() {
		try {
			char[] c=null;
			new BigDecimal(c,Integer.MIN_VALUE,Integer.MAX_VALUE,new MathContext(0));
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullCharArrayIntIntMathContext017() {
		try {
			char[] c=null;
			new BigDecimal(c,0,Integer.MIN_VALUE,new MathContext(0));
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullCharArrayIntIntMathContext018() {
		try {
			char[] c=null;
			new BigDecimal(c,Integer.MAX_VALUE,Integer.MIN_VALUE,new MathContext(0));
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullCharArrayIntIntMathContext019() {
		try {
			char[] c=null;
			new BigDecimal(c,Integer.MIN_VALUE,Integer.MIN_VALUE,new MathContext(0));
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}								
	
	
	public void testNullDoubleMathContext001() {
		try {
			double d=0;
			new BigDecimal(d,null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}		
	public void testNullDoubleMathContext002() {
		try {
			double d=Double.MAX_VALUE;
			new BigDecimal(d,null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}						public void testNullDoubleMathContext003() {
		try {
			double d=Double.MIN_VALUE;
			new BigDecimal(d,null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}	
	
	
	
	
	
	public void testNullIntMathContext001() {
		try {
			int d=0;
			new BigDecimal(d,null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}		
	public void testNullIntMathContext002() {
		try {
			int d=Integer.MAX_VALUE;
			new BigDecimal(d,null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}						public void testNullIntMathContext003() {
		try {
			int d=Integer.MIN_VALUE;
			new BigDecimal(d,null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}	
	
	public void testNullString001() {
		try {
			String s=null;
			new BigDecimal(s);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}	
	public void testNullStringMathContext001() {
		try {
			String s=null;
			new BigDecimal(s, new MathContext(0));
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	public void testNullStringMathContext002() {
		try {
			String s="0";
			new BigDecimal(s, null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public void testNullStringMathContext003() {
		try {
			String s=null;
			new BigDecimal(s, null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public final void testNullAbsMathContext001() {
		try {							        
			BigDecimal.ZERO.abs(null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}	
	
	public final void testNullAbsMathContext002() {
		try {							        
			new BigDecimal("48847845153781354897643216574.158764531867432187451").abs(null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	public final void testNullAbsMathContext003() {
		try {							        
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").abs(null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}								 
	
	
	public final void testNullAddBigDecimal001() {
		try {							        
			BigDecimal.ZERO.add(null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}	
	
	public final void testNullAddBigDecimal002() {
		try {							        
			new BigDecimal("48847845153781354897643216574.158764531867432187451").add(null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	public final void testNullAddBigDecimal003() {
		try {							        
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").add(null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}									 
	
	
	public final void testNullAddBigDecimalMathContext001() {
		try {							        
			BigDecimal.ZERO.add(null,null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}	
	
	public final void testNullAddBigDecimalMathContext002() {
		try {							        
			new BigDecimal("48847845153781354897643216574.158764531867432187451").add(null,null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	public final void testNullAddBigDecimalMathContext003() {
		try {							        
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").add(null,null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}									 							 					
	
	public final void testNullAddBigDecimalMathContext004() {
		try {							        
			BigDecimal.ZERO.add(BigDecimal.ZERO,null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}	
	
	public final void testNullAddBigDecimalMathContext005() {
		try {							        
			new BigDecimal("48847845153781354897643216574.158764531867432187451").add(BigDecimal.ZERO,null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	public final void testNullAddBigDecimalMathContext006() {
		try {							        
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").add(BigDecimal.ZERO,null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}									 							 				
	
	public final void testNullAddBigDecimalMathContext007() {
		try {							        
			BigDecimal.ZERO.add(new BigDecimal("48847845153781354897643216574.158764531867432187451"),null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}	
	
	public final void testNullAddBigDecimalMathContext008() {
		try {							        
			new BigDecimal("48847845153781354897643216574.158764531867432187451").add(new BigDecimal("48847845153781354897643216574.158764531867432187451"),null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	public final void testNullAddBigDecimalMathContext009() {
		try {							        
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").add(new BigDecimal("48847845153781354897643216574.158764531867432187451"),null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}									 							 				
	
	public final void testNullAddBigDecimalMathContext010() {
		try {							        
			BigDecimal.ZERO.add(new BigDecimal("-48847845153781354897643216574.158764531867432187451"),null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}	
	
	public final void testNullAddBigDecimalMathContext011() {
		try {							        
			new BigDecimal("48847845153781354897643216574.158764531867432187451").add(new BigDecimal("-48847845153781354897643216574.158764531867432187451"),null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	public final void testNullAddBigDecimalMathContext012() {
		try {							        
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").add(new BigDecimal("-48847845153781354897643216574.158764531867432187451"),null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}					
	
	public final void testNullAddBigDecimalMathContext013() {
		try {							        
			BigDecimal.ZERO.add(null,new MathContext(0));
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}	
	
	public final void testNullAddBigDecimalMathContext014() {
		try {							        
			new BigDecimal("48847845153781354897643216574.158764531867432187451").add(null,new MathContext(0));
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	public final void testNullAddBigDecimalMathContext015() {
		try {							        
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").add(null,new MathContext(0));
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}									 							 					
	
	public final void testNullCompareToBigDecimal002() {
		try {	
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").compareTo(null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public final void testNullCompareToBigDecimal003() {
		try {	
			new BigDecimal("48847845153781354897643216574.158764531867432187451").compareTo(null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	public final void testNullCompareToBigDecimal001() {
		try {	
			BigDecimal.ZERO.compareTo(null);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}
	
	public final void testNullDivideBigDecimal001() {
		try {	
			BigDecimal.ZERO.divide(null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullDivideBigDecimal002() {
		try {	
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").divide(null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideBigDecimal003() {
		try {	
			new BigDecimal("48847845153781354897643216574.158764531867432187451").divide(null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideIntBigDecimal001() {
		try {	
			BigDecimal.ZERO.divide(null,0);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullDivideIntBigDecimal002() {
		try {	
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").divide(null,0);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideIntBigDecimal003() {
		try {	
			new BigDecimal("48847845153781354897643216574.158764531867432187451").divide(null,0);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideIntBigDecimal004() {
		try {	
			BigDecimal.ZERO.divide(null,7);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullDivideIntBigDecimal005() {
		try {	
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").divide(null,7);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideIntBigDecimal006() {
		try {	
			new BigDecimal("48847845153781354897643216574.158764531867432187451").divide(null,7);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideIntIntBigDecimal001() {
		try {	
			BigDecimal.ZERO.divide(null,0,0);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullDivideIntIntBigDecimal002() {
		try {	
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").divide(null,0,0);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideIntIntBigDecimal003() {
		try {	
			new BigDecimal("48847845153781354897643216574.158764531867432187451").divide(null,0,0);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideIntIntBigDecimal004() {
		try {	
			BigDecimal.ZERO.divide(null,0,7);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullDivideIntIntBigDecimal005() {
		try {	
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").divide(null,0,7);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideIntIntBigDecimal006() {
		try {	
			new BigDecimal("48847845153781354897643216574.158764531867432187451").divide(null,0,7);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}							  
	public final void testNullDivideIntIntBigDecimal007() {
		try {	
			BigDecimal.ZERO.divide(null,Integer.MAX_VALUE,0);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullDivideIntIntBigDecimal008() {
		try {	
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").divide(null,Integer.MAX_VALUE,0);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideIntIntBigDecimal009() {
		try {	
			new BigDecimal("48847845153781354897643216574.158764531867432187451").divide(null,Integer.MAX_VALUE,0);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideIntIntBigDecimal010() {
		try {	
			BigDecimal.ZERO.divide(null,Integer.MAX_VALUE,7);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullDivideIntIntBigDecimal011() {
		try {	
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").divide(null,Integer.MAX_VALUE,7);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideIntIntBigDecimal012() {
		try {	
			new BigDecimal("48847845153781354897643216574.158764531867432187451").divide(null,Integer.MAX_VALUE,7);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}					
	
	public final void testNullDivideIntIntBigDecimal013() {
		try {	
			BigDecimal.ZERO.divide(null,Integer.MIN_VALUE,0);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullDivideIntIntBigDecimal014() {
		try {	
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").divide(null,Integer.MIN_VALUE,0);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideIntIntBigDecimal015() {
		try {	
			new BigDecimal("48847845153781354897643216574.158764531867432187451").divide(null,Integer.MIN_VALUE,0);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideIntIntBigDecimal016() {
		try {	
			BigDecimal.ZERO.divide(null,Integer.MIN_VALUE,7);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullDivideIntIntBigDecimal017() {
		try {	
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").divide(null,Integer.MIN_VALUE,7);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideIntIntBigDecimal018() {
		try {	
			new BigDecimal("48847845153781354897643216574.158764531867432187451").divide(null,Integer.MIN_VALUE,7);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}		
	
	public final void testNullDivideIntRoundingModeBigDecimal001() {
		try {	
			BigDecimal.ZERO.divide(null,0,RoundingMode.HALF_EVEN);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullDivideIntRoundingModeBigDecimal002() {
		try {	
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").divide(null,0,RoundingMode.HALF_EVEN);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideIntRoundingModeBigDecimal003() {
		try {	
			new BigDecimal("48847845153781354897643216574.158764531867432187451").divide(null,0,RoundingMode.HALF_EVEN);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideIntRoundingModeBigDecimal004() {
		try {	
			BigDecimal.ZERO.divide(null,0,RoundingMode.CEILING);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullDivideIntRoundingModeBigDecimal005() {
		try {	
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").divide(null,0,RoundingMode.CEILING);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideIntRoundingModeBigDecimal006() {
		try {	
			new BigDecimal("48847845153781354897643216574.158764531867432187451").divide(null,0,RoundingMode.CEILING);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}							  
	public final void testNullDivideIntRoundingModeBigDecimal007() {
		try {	
			BigDecimal.ZERO.divide(null,Integer.MAX_VALUE,RoundingMode.HALF_EVEN);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullDivideIntRoundingModeBigDecimal008() {
		try {	
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").divide(null,Integer.MAX_VALUE,RoundingMode.HALF_EVEN);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideIntRoundingModeBigDecimal009() {
		try {	
			new BigDecimal("48847845153781354897643216574.158764531867432187451").divide(null,Integer.MAX_VALUE,RoundingMode.HALF_EVEN);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideIntRoundingModeBigDecimal010() {
		try {	
			BigDecimal.ZERO.divide(null,Integer.MAX_VALUE,RoundingMode.CEILING);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullDivideIntRoundingModeBigDecimal011() {
		try {	
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").divide(null,Integer.MAX_VALUE,RoundingMode.CEILING);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideIntRoundingModeBigDecimal012() {
		try {	
			new BigDecimal("48847845153781354897643216574.158764531867432187451").divide(null,Integer.MAX_VALUE,RoundingMode.CEILING);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}					
	
	public final void testNullDivideIntRoundingModeBigDecimal013() {
		try {	
			BigDecimal.ZERO.divide(null,Integer.MIN_VALUE,RoundingMode.HALF_EVEN);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullDivideIntRoundingModeBigDecimal014() {
		try {	
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").divide(null,Integer.MIN_VALUE,RoundingMode.HALF_EVEN);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideIntRoundingModeBigDecimal015() {
		try {	
			new BigDecimal("48847845153781354897643216574.158764531867432187451").divide(null,Integer.MIN_VALUE,RoundingMode.HALF_EVEN);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideIntRoundingModeBigDecimal016() {
		try {	
			BigDecimal.ZERO.divide(null,Integer.MIN_VALUE,RoundingMode.CEILING);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullDivideIntRoundingModeBigDecimal017() {
		try {	
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").divide(null,Integer.MIN_VALUE,RoundingMode.CEILING);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideIntRoundingModeBigDecimal018() {
		try {	
			new BigDecimal("48847845153781354897643216574.158764531867432187451").divide(null,Integer.MIN_VALUE,RoundingMode.CEILING);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}		
	
	public final void testNullDivideRoundingModeBigDecimal001() {
		try {	
			BigDecimal.ZERO.divide(null,RoundingMode.HALF_EVEN);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullDivideRoundingModeBigDecimal002() {
		try {	
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").divide(null,RoundingMode.HALF_EVEN);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideRoundingModeBigDecimal003() {
		try {	
			new BigDecimal("48847845153781354897643216574.158764531867432187451").divide(null,RoundingMode.HALF_EVEN);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideRoundingModeBigDecimal004() {
		try {	
			BigDecimal.ZERO.divide(null,RoundingMode.CEILING);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullDivideRoundingModeBigDecimal005() {
		try {	
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").divide(null,RoundingMode.CEILING);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideRoundingModeBigDecimal006() {
		try {	
			new BigDecimal("48847845153781354897643216574.158764531867432187451").divide(null,RoundingMode.CEILING);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}	
	
	public final void testNullDivideBigDecimalMathContext001() {
		try {	
			MathContext mc=null;
			BigDecimal.ZERO.divide(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullDivideBigDecimalMathContext002() {
		try {	
			MathContext mc=null;
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").divide(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideBigDecimalMathContext003() {
		try {	
			MathContext mc=null;
			new BigDecimal("48847845153781354897643216574.158764531867432187451").divide(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideBigDecimalMathContext004() {
		try {	
			MathContext mc=null;
			BigDecimal.ZERO.divide(new BigDecimal("48616743.158768"),mc);
			fail(msgRaise+"NullPointerException");
		} catch (NullPointerException e) {           
		} catch (Throwable e) {
			fail(msgNullPointer + e);
		}
	}				
	
	public final void testNullDivideBigDecimalMathContext005() {
		try {	
			MathContext mc=null;
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").divide(new BigDecimal("48616743.158768"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideBigDecimalMathContext006() {
		try {	
			MathContext mc=null;
			new BigDecimal("48847845153781354897643216574.158764531867432187451").divide(new BigDecimal("48616743.158768"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideBigDecimalMathContext007() {
		try {	
			MathContext mc=null;
			BigDecimal.ZERO.divide(new BigDecimal("-48616743.158768"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullDivideBigDecimalMathContext008() {
		try {	
			MathContext mc=null;
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").divide(new BigDecimal("-48616743.158768"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideBigDecimalMathContext009() {
		try {	
			MathContext mc=null;
			new BigDecimal("48847845153781354897643216574.158764531867432187451").divide(new BigDecimal("-48616743.158768"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideBigDecimalMathContext010() {
		try {	
			MathContext mc=null;
			BigDecimal.ZERO.divide(BigDecimal.ONE,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullDivideBigDecimalMathContext011() {
		try {	
			MathContext mc=null;
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").divide(BigDecimal.ONE,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideBigDecimalMathContext012() {
		try {	
			MathContext mc=null;
			new BigDecimal("48847845153781354897643216574.158764531867432187451").divide(BigDecimal.ONE,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideBigDecimalMathContext013() {
		try {	
			MathContext mc=new MathContext(0, RoundingMode.UNNECESSARY);
			BigDecimal.ZERO.divide(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullDivideBigDecimalMathContext014() {
		try {	
			MathContext mc=new MathContext(0, RoundingMode.UNNECESSARY);
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").divide(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideBigDecimalMathContext015() {
		try {	
			MathContext mc=new MathContext(0, RoundingMode.UNNECESSARY);
			new BigDecimal("48847845153781354897643216574.158764531867432187451").divide(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}			  
	
	
	
	public final void testNullDivideAndRemainderBigDecimal001() {
		try {	
			BigDecimal.ZERO.divideAndRemainder(null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullDivideAndRemainderBigDecimal002() {
		try {	
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").divideAndRemainder(null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideAndRemainderBigDecimal003() {
		try {	
			new BigDecimal("48847845153781354897643216574.158764531867432187451").divideAndRemainder(null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	
	public final void testNullDivideAndRemainderBigDecimalMathContext001() {
		try {	
			MathContext mc=null;
			BigDecimal.ZERO.divideAndRemainder(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullDivideAndRemainderBigDecimalMathContext002() {
		try {	
			MathContext mc=null;
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").divideAndRemainder(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideAndRemainderBigDecimalMathContext003() {
		try {	
			MathContext mc=null;
			new BigDecimal("48847845153781354897643216574.158764531867432187451").divideAndRemainder(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideAndRemainderBigDecimalMathContext004() {
		try {	
			MathContext mc=null;
			BigDecimal.ZERO.divideAndRemainder(new BigDecimal("48616743.158768"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullDivideAndRemainderBigDecimalMathContext005() {
		try {	
			MathContext mc=null;
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").divideAndRemainder(new BigDecimal("48616743.158768"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideAndRemainderBigDecimalMathContext006() {
		try {	
			MathContext mc=null;
			new BigDecimal("48847845153781354897643216574.158764531867432187451").divideAndRemainder(new BigDecimal("48616743.158768"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideAndRemainderBigDecimalMathContext007() {
		try {	
			MathContext mc=null;
			BigDecimal.ZERO.divideAndRemainder(new BigDecimal("-48616743.158768"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullDivideAndRemainderBigDecimalMathContext008() {
		try {	
			MathContext mc=null;
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").divideAndRemainder(new BigDecimal("-48616743.158768"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideAndRemainderBigDecimalMathContext009() {
		try {	
			MathContext mc=null;
			new BigDecimal("48847845153781354897643216574.158764531867432187451").divideAndRemainder(new BigDecimal("-48616743.158768"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideAndRemainderBigDecimalMathContext010() {
		try {	
			MathContext mc=null;
			BigDecimal.ZERO.divideAndRemainder(BigDecimal.ONE,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullDivideAndRemainderBigDecimalMathContext011() {
		try {	
			MathContext mc=null;
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").divideAndRemainder(BigDecimal.ONE,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideAndRemainderBigDecimalMathContext012() {
		try {	
			MathContext mc=null;
			new BigDecimal("48847845153781354897643216574.158764531867432187451").divideAndRemainder(BigDecimal.ONE,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideAndRemainderBigDecimalMathContext013() {
		try {	
			MathContext mc=new MathContext(0, RoundingMode.UNNECESSARY);
			BigDecimal.ZERO.divideAndRemainder(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullDivideAndRemainderBigDecimalMathContext014() {
		try {	
			MathContext mc=new MathContext(0, RoundingMode.UNNECESSARY);
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").divideAndRemainder(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideAndRemainderBigDecimalMathContext015() {
		try {	
			MathContext mc=new MathContext(0, RoundingMode.UNNECESSARY);
			new BigDecimal("48847845153781354897643216574.158764531867432187451").divideAndRemainder(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}			  
	
	
	public final void testNullDivideToIntegralValueBigDecimal001() {
		try {	
			BigDecimal.ZERO.divideToIntegralValue(null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullDivideToIntegralValueBigDecimal002() {
		try {	
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").divideToIntegralValue(null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideToIntegralValueBigDecimal003() {
		try {	
			new BigDecimal("48847845153781354897643216574.158764531867432187451").divideToIntegralValue(null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	
	public final void testNullDivideToIntegralValueBigDecimalMathContext001() {
		try {	
			MathContext mc=null;
			BigDecimal.ZERO.divideToIntegralValue(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullDivideToIntegralValueBigDecimalMathContext002() {
		try {	
			MathContext mc=null;
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").divideToIntegralValue(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideToIntegralValueBigDecimalMathContext003() {
		try {	
			MathContext mc=null;
			new BigDecimal("48847845153781354897643216574.158764531867432187451").divideToIntegralValue(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideToIntegralValueBigDecimalMathContext004() {
		try {	
			MathContext mc=null;
			BigDecimal.ZERO.divideToIntegralValue(new BigDecimal("48616743.158768"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullDivideToIntegralValueBigDecimalMathContext005() {
		try {	
			MathContext mc=null;
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").divideToIntegralValue(new BigDecimal("48616743.158768"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideToIntegralValueBigDecimalMathContext006() {
		try {	
			MathContext mc=null;
			new BigDecimal("48847845153781354897643216574.158764531867432187451").divideToIntegralValue(new BigDecimal("48616743.158768"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideToIntegralValueBigDecimalMathContext007() {
		try {	
			MathContext mc=null;
			BigDecimal.ZERO.divideToIntegralValue(new BigDecimal("-48616743.158768"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullDivideToIntegralValueBigDecimalMathContext008() {
		try {	
			MathContext mc=null;
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").divideToIntegralValue(new BigDecimal("-48616743.158768"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideToIntegralValueBigDecimalMathContext009() {
		try {	
			MathContext mc=null;
			new BigDecimal("48847845153781354897643216574.158764531867432187451").divideToIntegralValue(new BigDecimal("-48616743.158768"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideToIntegralValueBigDecimalMathContext010() {
		try {	
			MathContext mc=null;
			BigDecimal.ZERO.divideToIntegralValue(BigDecimal.ONE,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullDivideToIntegralValueBigDecimalMathContext011() {
		try {	
			MathContext mc=null;
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").divideToIntegralValue(BigDecimal.ONE,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideToIntegralValueBigDecimalMathContext012() {
		try {	
			MathContext mc=null;
			new BigDecimal("48847845153781354897643216574.158764531867432187451").divideToIntegralValue(BigDecimal.ONE,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideToIntegralValueBigDecimalMathContext013() {
		try {	
			MathContext mc=new MathContext(0, RoundingMode.UNNECESSARY);
			BigDecimal.ZERO.divideToIntegralValue(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullDivideToIntegralValueBigDecimalMathContext014() {
		try {	
			MathContext mc=new MathContext(0, RoundingMode.UNNECESSARY);
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").divideToIntegralValue(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullDivideToIntegralValueBigDecimalMathContext015() {
		try {	
			MathContext mc=new MathContext(0, RoundingMode.UNNECESSARY);
			new BigDecimal("48847845153781354897643216574.158764531867432187451").divideToIntegralValue(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}		
	
	
	public void testNullMaxBigDecimal001() {
		try {
			
			BigDecimal.ZERO.max(null);
			fail(msgRaise+"NullPointerException");
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer);
			}
	}
	
	public void testNullMaxBigDecimal002() {
		try {
			
			new BigDecimal("1891623486132.18945167945138674518").max(null);
			fail(msgRaise+"NullPointerException");
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer);
			}
	}
	
	public void testNullMaxBigDecimal003() {
		try {
			
			new BigDecimal("-1891623486132.18945167945138674518").max(null);
			fail(msgRaise+"NullPointerException");
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer);
			}
	}
	
	public void testNullMaxBigDecimal004() {
		try {
			
			new BigDecimal("1891623486132E+506").max(null);
			fail(msgRaise+"NullPointerException");
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer);
			}
	}
	
	public void testNullMaxBigDecimal005() {
		try {
			
			new BigDecimal("-1891623486132.18945167945138674518E-456").max(null);
			fail(msgRaise+"NullPointerException");
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer);
			}
	}							
	public void testNullMinBigDecimal001() {
		try {
			
			BigDecimal.ZERO.min(null);
			fail(msgRaise+"NullPointerException");
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer);
			}
	}
	
	public void testNullMinBigDecimal002() {
		try {
			
			new BigDecimal("1891623486132.18945167945138674518").min(null);
			fail(msgRaise+"NullPointerException");
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer);
			}
	}
	
	public void testNullMinBigDecimal003() {
		try {
			
			new BigDecimal("-1891623486132.18945167945138674518").min(null);
			fail(msgRaise+"NullPointerException");
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer);
			}
	}
	
	public void testNullMinBigDecimal004() {
		try {
			
			new BigDecimal("1891623486132E+506").min(null);
			fail(msgRaise+"NullPointerException");
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer);
			}
	}
	
	public void testNullMinBigDecimal005() {
		try {
			
			new BigDecimal("-1891623486132.18945167945138674518E-456").min(null);
			fail(msgRaise+"NullPointerException");
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer);
			}
	}		
	
	
	public final void testNullMultiplyBigDecimal001() {
		try {	
			BigDecimal.ZERO.multiply(null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullMultiplyBigDecimal002() {
		try {	
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").multiply(null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullMultiplyBigDecimal003() {
		try {	
			new BigDecimal("48847845153781354897643216574.158764531867432187451").multiply(null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	
	public final void testNullMultiplyBigDecimalMathContext001() {
		try {	
			MathContext mc=null;
			BigDecimal.ZERO.multiply(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullMultiplyBigDecimalMathContext002() {
		try {	
			MathContext mc=null;
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").multiply(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullMultiplyBigDecimalMathContext003() {
		try {	
			MathContext mc=null;
			new BigDecimal("48847845153781354897643216574.158764531867432187451").multiply(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullMultiplyBigDecimalMathContext004() {
		try {	
			MathContext mc=null;
			BigDecimal.ZERO.multiply(new BigDecimal("48616743.158768"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullMultiplyBigDecimalMathContext005() {
		try {	
			MathContext mc=null;
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").multiply(new BigDecimal("48616743.158768"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullMultiplyBigDecimalMathContext006() {
		try {	
			MathContext mc=null;
			new BigDecimal("48847845153781354897643216574.158764531867432187451").multiply(new BigDecimal("48616743.158768"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullMultiplyBigDecimalMathContext007() {
		try {	
			MathContext mc=null;
			BigDecimal.ZERO.multiply(new BigDecimal("-48616743.158768"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullMultiplyBigDecimalMathContext008() {
		try {	
			MathContext mc=null;
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").multiply(new BigDecimal("-48616743.158768"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullMultiplyBigDecimalMathContext009() {
		try {	
			MathContext mc=null;
			new BigDecimal("48847845153781354897643216574.158764531867432187451").multiply(new BigDecimal("-48616743.158768"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullMultiplyBigDecimalMathContext010() {
		try {	
			MathContext mc=null;
			BigDecimal.ZERO.multiply(BigDecimal.ONE,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullMultiplyBigDecimalMathContext011() {
		try {	
			MathContext mc=null;
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").multiply(BigDecimal.ONE,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullMultiplyBigDecimalMathContext012() {
		try {	
			MathContext mc=null;
			new BigDecimal("48847845153781354897643216574.158764531867432187451").multiply(BigDecimal.ONE,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullMultiplyBigDecimalMathContext013() {
		try {	
			MathContext mc=new MathContext(0, RoundingMode.UNNECESSARY);
			BigDecimal.ZERO.multiply(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullMultiplyBigDecimalMathContext014() {
		try {	
			MathContext mc=new MathContext(0, RoundingMode.UNNECESSARY);
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").multiply(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullMultiplyBigDecimalMathContext015() {
		try {	
			MathContext mc=new MathContext(0, RoundingMode.UNNECESSARY);
			new BigDecimal("48847845153781354897643216574.158764531867432187451").multiply(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}		
	
	
	public final void testNullMultiplyBigDecimalMathContext016() {
		try {	
			MathContext mc=new MathContext(0, RoundingMode.UNNECESSARY);
			new BigDecimal(new BigInteger("1"),Integer.MAX_VALUE).multiply(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	
	
	public final void testNullMultiplyBigDecimalMathContext018() {
		try {	
			MathContext mc=new MathContext(Integer.MAX_VALUE, RoundingMode.UNNECESSARY);
			new BigDecimal(new BigInteger("1"),Integer.MAX_VALUE).multiply(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}		
	
	public final void testNullMultiplyBigDecimalMathContext019() {
		try {	
			MathContext mc=new MathContext(0, RoundingMode.UNNECESSARY);
			new BigDecimal(new BigInteger("-1"),Integer.MAX_VALUE).multiply(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	
	
	public final void testNullMultiplyBigDecimalMathContext020() {
		try {	
			MathContext mc=new MathContext(Integer.MAX_VALUE, RoundingMode.UNNECESSARY);
			new BigDecimal(new BigInteger("-1"),Integer.MAX_VALUE).multiply(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}		
	
	public final void testNullMultiplyBigDecimalMathContext021() {
		try {	
			MathContext mc=new MathContext(0, RoundingMode.UNNECESSARY);
			new BigDecimal(new BigInteger("1"),Integer.MAX_VALUE).multiply(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	
	
	public final void testNullMultiplyBigDecimalMathContext022() {
		try {	
			MathContext mc=new MathContext(Integer.MAX_VALUE, RoundingMode.UNNECESSARY);
			new BigDecimal(new BigInteger("1"),Integer.MAX_VALUE).multiply(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}		
	
	public final void testNullMultiplyBigDecimalMathContext023() {
		try {	
			MathContext mc=new MathContext(0, RoundingMode.UNNECESSARY);
			new BigDecimal(new BigInteger("-1"),Integer.MIN_VALUE).multiply(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	
	
	public final void testNullMultiplyBigDecimalMathContext024() {
		try {	
			MathContext mc=new MathContext(Integer.MAX_VALUE, RoundingMode.UNNECESSARY);
			new BigDecimal(new BigInteger("-1"),Integer.MIN_VALUE).multiply(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}	
	
	
	public final void testNullMultiplyBigDecimalMathContext025() {
		try {	
			MathContext mc=null;
			new BigDecimal(new BigInteger("-1"),Integer.MIN_VALUE).multiply(BigDecimal.ONE,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullMultiplyBigDecimalMathContext026() {
		try {	
			MathContext mc=null;
			new BigDecimal(new BigInteger("1"),Integer.MAX_VALUE).multiply(BigDecimal.ZERO,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullMultiplyBigDecimalMathContext027() {
		try {	
			MathContext mc=null;
			BigDecimal.ZERO.multiply(BigDecimal.ZERO,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullNegateMathContext001() {
		try {	
			MathContext mc=null;
			BigDecimal.ZERO.negate(mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	
	public final void testNullNegateMathContext002() {
		try {	
			MathContext mc=null;
			new BigDecimal("158674531.4897413E2598").negate(mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullNegateMathContext003() {
		try {	
			MathContext mc=null;
			new BigDecimal("-158674531.4897413E2598").negate(mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}					
	
	
	public final void testNullPlusMathContext001() {
		try {	
			MathContext mc=null;
			BigDecimal.ZERO.plus(mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	
	public final void testNullPlusMathContext002() {
		try {	
			MathContext mc=null;
			new BigDecimal("158674531.4897413E2598").plus(mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullPlusMathContext003() {
		try {	
			MathContext mc=null;
			new BigDecimal("-158674531.4897413E2598").plus(mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	
	
	
	
	public final void testNullPowMathContext001() {
		try {	
			MathContext mc=null;
			BigDecimal.ZERO.pow(999999999,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	
	public final void testNullPowMathContext002() {
		try {	
			MathContext mc=null;
			new BigDecimal("158674531.4897413E2598").pow(999999999,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullPowMathContext003() {
		try {	
			MathContext mc=null;
			new BigDecimal("-158674531.4897413E2598").pow(999999999,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}								
	
	public final void testNullPowMathContext004() {
		try {	
			MathContext mc=null;
			BigDecimal.ZERO.pow(0,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	
	public final void testNullPowMathContext005() {
		try {	
			MathContext mc=null;
			new BigDecimal("158674531.4897413E2598").pow(0,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullPowMathContext006() {
		try {	
			MathContext mc=null;
			new BigDecimal("-158674531.4897413E2598").pow(0,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}							
	
	public final void testNullPowMathContext007() {
		try {	
			MathContext mc=null;
			BigDecimal.ZERO.pow(-999999999,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	
	public final void testNullPowMathContext008() {
		try {	
			MathContext mc=null;
			new BigDecimal("158674531.4897413E2598").pow(-999999999,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullPowMathContext009() {
		try {	
			MathContext mc=null;
			new BigDecimal("-158674531.4897413E2598").pow(-999999999,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}							

	
	public final void testNullRemainderBigDecimal001() {
		try {	
			BigDecimal.ZERO.remainder(null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullRemainderBigDecimal002() {
		try {	
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").remainder(null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullRemainderBigDecimal003() {
		try {	
			new BigDecimal("48847845153781354897643216574.158764531867432187451").remainder(null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	
	public final void testNullRemainderBigDecimalMathContext001() {
		try {	
			MathContext mc=null;
			BigDecimal.ZERO.remainder(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullRemainderBigDecimalMathContext002() {
		try {	
			MathContext mc=null;
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").remainder(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullRemainderBigDecimalMathContext003() {
		try {	
			MathContext mc=null;
			new BigDecimal("48847845153781354897643216574.158764531867432187451").remainder(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullRemainderBigDecimalMathContext004() {
		try {	
			MathContext mc=null;
			BigDecimal.ZERO.remainder(new BigDecimal("48616743.158768"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullRemainderBigDecimalMathContext005() {
		try {	
			MathContext mc=null;
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").remainder(new BigDecimal("48616743.158768"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullRemainderBigDecimalMathContext006() {
		try {	
			MathContext mc=null;
			new BigDecimal("48847845153781354897643216574.158764531867432187451").remainder(new BigDecimal("48616743.158768"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullRemainderBigDecimalMathContext007() {
		try {	
			MathContext mc=null;
			BigDecimal.ZERO.remainder(new BigDecimal("-48616743.158768"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullRemainderBigDecimalMathContext008() {
		try {	
			MathContext mc=null;
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").remainder(new BigDecimal("-48616743.158768"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullRemainderBigDecimalMathContext009() {
		try {	
			MathContext mc=null;
			new BigDecimal("48847845153781354897643216574.158764531867432187451").remainder(new BigDecimal("-48616743.158768"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullRemainderBigDecimalMathContext010() {
		try {	
			MathContext mc=null;
			BigDecimal.ZERO.remainder(BigDecimal.ONE,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullRemainderBigDecimalMathContext011() {
		try {	
			MathContext mc=null;
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").remainder(BigDecimal.ONE,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullRemainderBigDecimalMathContext012() {
		try {	
			MathContext mc=null;
			new BigDecimal("48847845153781354897643216574.158764531867432187451").remainder(BigDecimal.ONE,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullRemainderBigDecimalMathContext013() {
		try {	
			MathContext mc=new MathContext(0, RoundingMode.UNNECESSARY);
			BigDecimal.ZERO.remainder(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullRemainderBigDecimalMathContext014() {
		try {	
			MathContext mc=new MathContext(0, RoundingMode.UNNECESSARY);
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").remainder(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullRemainderBigDecimalMathContext015() {
		try {	
			MathContext mc=new MathContext(0, RoundingMode.UNNECESSARY);
			new BigDecimal("48847845153781354897643216574.158764531867432187451").remainder(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}		
	
	
	public final void testNullRemainderBigDecimalMathContext016() {
		try {	
			MathContext mc=new MathContext(0, RoundingMode.UNNECESSARY);
			new BigDecimal(new BigInteger("1"),Integer.MAX_VALUE).remainder(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	
	
	public final void testNullRemainderBigDecimalMathContext018() {
		try {	
			MathContext mc=new MathContext(Integer.MAX_VALUE, RoundingMode.UNNECESSARY);
			new BigDecimal(new BigInteger("1"),Integer.MAX_VALUE).remainder(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}		
	
	public final void testNullRemainderBigDecimalMathContext019() {
		try {	
			MathContext mc=new MathContext(0, RoundingMode.UNNECESSARY);
			new BigDecimal(new BigInteger("-1"),Integer.MAX_VALUE).remainder(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	
	
	public final void testNullRemainderBigDecimalMathContext020() {
		try {	
			MathContext mc=new MathContext(Integer.MAX_VALUE, RoundingMode.UNNECESSARY);
			new BigDecimal(new BigInteger("-1"),Integer.MAX_VALUE).remainder(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}		
	
	public final void testNullRemainderBigDecimalMathContext021() {
		try {	
			MathContext mc=new MathContext(0, RoundingMode.UNNECESSARY);
			new BigDecimal(new BigInteger("1"),Integer.MAX_VALUE).remainder(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	
	
	public final void testNullRemainderBigDecimalMathContext022() {
		try {	
			MathContext mc=new MathContext(Integer.MAX_VALUE, RoundingMode.UNNECESSARY);
			new BigDecimal(new BigInteger("1"),Integer.MAX_VALUE).remainder(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}		
	
	public final void testNullRemainderBigDecimalMathContext023() {
		try {	
			MathContext mc=new MathContext(0, RoundingMode.UNNECESSARY);
			new BigDecimal(new BigInteger("-1"),Integer.MIN_VALUE).remainder(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	
	
	public final void testNullRemainderBigDecimalMathContext024() {
		try {	
			MathContext mc=new MathContext(Integer.MAX_VALUE, RoundingMode.UNNECESSARY);
			new BigDecimal(new BigInteger("-1"),Integer.MIN_VALUE).remainder(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}	
	
	
	
	
	public final void testNullRemainderBigDecimalMathContext026() {
		try {	
			MathContext mc=null;
			new BigDecimal(new BigInteger("1"),Integer.MAX_VALUE).remainder(new BigDecimal("5.5"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullRemainderBigDecimalMathContext027() {
		try {	
			MathContext mc=null;
			BigDecimal.ZERO.remainder(new BigDecimal("-5.5"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}									  
	
	
	public final void testNullRound001() {
		try {	
			MathContext mc=null;
			BigDecimal.ZERO.round(mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}												  
	
	public final void testNullRound002() {
		try {	
			MathContext mc=null;
			new BigDecimal(new BigInteger("-1"),Integer.MIN_VALUE).round(mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullRound003() {
		try {	
			MathContext mc=null;
			new BigDecimal(new BigInteger("1"),Integer.MAX_VALUE).round(mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}									  
	
	public final void testNullRound004() {
		try {	
			MathContext mc=null;
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").round(mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullRound005() {
		try {	
			MathContext mc=null;
			new BigDecimal("48847845153781354897643216574.158764531867432187451").round(mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}											  
	
	
	public final void testNullSubtractBigDecimal001() {
		try {	
			BigDecimal.ZERO.subtract(null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullSubtractBigDecimal002() {
		try {	
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").subtract(null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSubtractBigDecimal003() {
		try {	
			new BigDecimal("48847845153781354897643216574.158764531867432187451").subtract(null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	
	public final void testNullSubtractBigDecimalMathContext001() {
		try {	
			MathContext mc=null;
			BigDecimal.ZERO.subtract(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullSubtractBigDecimalMathContext002() {
		try {	
			MathContext mc=null;
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").subtract(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSubtractBigDecimalMathContext003() {
		try {	
			MathContext mc=null;
			new BigDecimal("48847845153781354897643216574.158764531867432187451").subtract(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSubtractBigDecimalMathContext004() {
		try {	
			MathContext mc=null;
			BigDecimal.ZERO.subtract(new BigDecimal("48616743.158768"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullSubtractBigDecimalMathContext005() {
		try {	
			MathContext mc=null;
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").subtract(new BigDecimal("48616743.158768"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSubtractBigDecimalMathContext006() {
		try {	
			MathContext mc=null;
			new BigDecimal("48847845153781354897643216574.158764531867432187451").subtract(new BigDecimal("48616743.158768"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSubtractBigDecimalMathContext007() {
		try {	
			MathContext mc=null;
			BigDecimal.ZERO.subtract(new BigDecimal("-48616743.158768"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullSubtractBigDecimalMathContext008() {
		try {	
			MathContext mc=null;
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").subtract(new BigDecimal("-48616743.158768"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSubtractBigDecimalMathContext009() {
		try {	
			MathContext mc=null;
			new BigDecimal("48847845153781354897643216574.158764531867432187451").subtract(new BigDecimal("-48616743.158768"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSubtractBigDecimalMathContext010() {
		try {	
			MathContext mc=null;
			BigDecimal.ZERO.subtract(BigDecimal.ONE,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullSubtractBigDecimalMathContext011() {
		try {	
			MathContext mc=null;
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").subtract(BigDecimal.ONE,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSubtractBigDecimalMathContext012() {
		try {	
			MathContext mc=null;
			new BigDecimal("48847845153781354897643216574.158764531867432187451").subtract(BigDecimal.ONE,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSubtractBigDecimalMathContext013() {
		try {	
			MathContext mc=new MathContext(0, RoundingMode.UNNECESSARY);
			BigDecimal.ZERO.subtract(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullSubtractBigDecimalMathContext014() {
		try {	
			MathContext mc=new MathContext(0, RoundingMode.UNNECESSARY);
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").subtract(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSubtractBigDecimalMathContext015() {
		try {	
			MathContext mc=new MathContext(0, RoundingMode.UNNECESSARY);
			new BigDecimal("48847845153781354897643216574.158764531867432187451").subtract(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}		
	
	
	public final void testNullSubtractBigDecimalMathContext016() {
		try {	
			MathContext mc=new MathContext(0, RoundingMode.UNNECESSARY);
			new BigDecimal(new BigInteger("1"),Integer.MAX_VALUE).subtract(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	
	
	public final void testNullSubtractBigDecimalMathContext018() {
		try {	
			MathContext mc=new MathContext(Integer.MAX_VALUE, RoundingMode.UNNECESSARY);
			new BigDecimal(new BigInteger("1"),Integer.MAX_VALUE).subtract(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}		
	
	public final void testNullSubtractBigDecimalMathContext019() {
		try {	
			MathContext mc=new MathContext(0, RoundingMode.UNNECESSARY);
			new BigDecimal(new BigInteger("-1"),Integer.MAX_VALUE).subtract(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	
	
	public final void testNullSubtractBigDecimalMathContext020() {
		try {	
			MathContext mc=new MathContext(Integer.MAX_VALUE, RoundingMode.UNNECESSARY);
			new BigDecimal(new BigInteger("-1"),Integer.MAX_VALUE).subtract(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}		
	
	public final void testNullSubtractBigDecimalMathContext021() {
		try {	
			MathContext mc=new MathContext(0, RoundingMode.UNNECESSARY);
			new BigDecimal(new BigInteger("1"),Integer.MAX_VALUE).subtract(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	
	
	public final void testNullSubtractBigDecimalMathContext022() {
		try {	
			MathContext mc=new MathContext(Integer.MAX_VALUE, RoundingMode.UNNECESSARY);
			new BigDecimal(new BigInteger("1"),Integer.MAX_VALUE).subtract(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}		
	
	public final void testNullSubtractBigDecimalMathContext023() {
		try {	
			MathContext mc=new MathContext(0, RoundingMode.UNNECESSARY);
			new BigDecimal(new BigInteger("-1"),Integer.MIN_VALUE).subtract(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	
	
	public final void testNullSubtractBigDecimalMathContext024() {
		try {	
			MathContext mc=new MathContext(Integer.MAX_VALUE, RoundingMode.UNNECESSARY);
			new BigDecimal(new BigInteger("-1"),Integer.MIN_VALUE).subtract(null,mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}	
	
	
	
	
	public final void testNullSubtractBigDecimalMathContext026() {
		try {	
			MathContext mc=null;
			new BigDecimal(new BigInteger("1"),99999).subtract(new BigDecimal("5.5"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSubtractBigDecimalMathContext027() {
		try {	
			MathContext mc=null;
			BigDecimal.ZERO.subtract(new BigDecimal("-5.5"),mc);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}														  
	
	public final void testNullSetScaleIntRoundingMode001() {
		try {	
			BigDecimal.ZERO.setScale(0,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullSetScaleIntRoundingMode002() {
		try {	
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").setScale(0,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSetScaleIntRoundingMode003() {
		try {	
			new BigDecimal("48847845153781354897643216574.158764531867432187451").setScale(0,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSetScaleIntRoundingMode004() {
		try {	
			new BigDecimal(new BigInteger("1"), 999).setScale(0,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSetScaleIntRoundingMode005() {
		try {	
			new BigDecimal(new BigInteger("1"), -999).setScale(0,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSetScaleIntRoundingMode006() {
		try {	
			new BigDecimal(new BigInteger("-1"),999).setScale(0,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSetScaleIntRoundingMode007() {
		try {	
			new BigDecimal(new BigInteger("-1"), -999).setScale(0,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSetScaleIntRoundingMode008() {
		try {	
			BigDecimal.ZERO.setScale(0,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullSetScaleIntRoundingMode009() {
		try {	
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").setScale(0,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSetScaleIntRoundingMode010() {
		try {	
			new BigDecimal("48847845153781354897643216574.158764531867432187451").setScale(0,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSetScaleIntRoundingMode011() {
		try {	
			new BigDecimal(new BigInteger("1"), 999).setScale(0,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSetScaleIntRoundingMode012() {
		try {	
			new BigDecimal(new BigInteger("1"), -999).setScale(0,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSetScaleIntRoundingMode013() {
		try {	
			new BigDecimal(new BigInteger("-1"), 999).setScale(0,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSetScaleIntRoundingMode014() {
		try {	
			new BigDecimal(new BigInteger("-1"), -999).setScale(0,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	public final void testNullSetScaleIntRoundingMode015() {
		try {	
			BigDecimal.ZERO.setScale(999,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullSetScaleIntRoundingMode016() {
		try {	
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").setScale(999,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSetScaleIntRoundingMode017() {
		try {	
			new BigDecimal("48847845153781354897643216574.158764531867432187451").setScale(999,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSetScaleIntRoundingMode018() {
		try {	
			new BigDecimal(new BigInteger("1"), 999).setScale(999,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSetScaleIntRoundingMode019() {
		try {	
			new BigDecimal(new BigInteger("1"), -999).setScale(999,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSetScaleIntRoundingMode020() {
		try {	
			new BigDecimal(new BigInteger("-1"),999).setScale(999,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSetScaleIntRoundingMode021() {
		try {	
			new BigDecimal(new BigInteger("-1"), -999).setScale(999,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSetScaleIntRoundingMode022() {
		try {	
			BigDecimal.ZERO.setScale(999,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullSetScaleIntRoundingMode023() {
		try {	
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").setScale(999,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSetScaleIntRoundingMode024() {
		try {	
			new BigDecimal("48847845153781354897643216574.158764531867432187451").setScale(999,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSetScaleIntRoundingMode025() {
		try {	
			new BigDecimal(new BigInteger("1"), 999).setScale(999,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSetScaleIntRoundingMode026() {
		try {	
			new BigDecimal(new BigInteger("1"), -999).setScale(999,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSetScaleIntRoundingMode027() {
		try {	
			new BigDecimal(new BigInteger("-1"), 999).setScale(999,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSetScaleIntRoundingMode028() {
		try {	
			new BigDecimal(new BigInteger("-1"), -999).setScale(999,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	public final void testNullSetScaleIntRoundingMode029() {
		try {	
			BigDecimal.ZERO.setScale(-999,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullSetScaleIntRoundingMode030() {
		try {	
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").setScale(-999,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSetScaleIntRoundingMode031() {
		try {	
			new BigDecimal("48847845153781354897643216574.158764531867432187451").setScale(-999,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSetScaleIntRoundingMode032() {
		try {	
			new BigDecimal(new BigInteger("1"), 999).setScale(-999,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSetScaleIntRoundingMode033() {
		try {	
			new BigDecimal(new BigInteger("1"), -999).setScale(-999,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSetScaleIntRoundingMode034() {
		try {	
			new BigDecimal(new BigInteger("-1"),999).setScale(-999,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSetScaleIntRoundingMode035() {
		try {	
			new BigDecimal(new BigInteger("-1"), -999).setScale(-999,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSetScaleIntRoundingMode036() {
		try {	
			BigDecimal.ZERO.setScale(-999,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}				
	
	public final void testNullSetScaleIntRoundingMode037() {
		try {	
			new BigDecimal("-48847845153781354897643216574.158764531867432187451").setScale(-999,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSetScaleIntRoundingMode038() {
		try {	
			new BigDecimal("48847845153781354897643216574.158764531867432187451").setScale(-999,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSetScaleIntRoundingMode039() {
		try {	
			new BigDecimal(new BigInteger("1"), 999).setScale(-999,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSetScaleIntRoundingMode040() {
		try {	
			new BigDecimal(new BigInteger("1"), -999).setScale(-999,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSetScaleIntRoundingMode041() {
		try {	
			new BigDecimal(new BigInteger("-1"), 999).setScale(-999,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}
	
	public final void testNullSetScaleIntRoundingMode042() {
		try {	
			new BigDecimal(new BigInteger("-1"), -999).setScale(-999,null);
			fail(msgRaise+"NullPointerException");} catch (NullPointerException e) {           
			} catch (Throwable e) {
				fail(msgNullPointer + e);
			}
	}									  
}
