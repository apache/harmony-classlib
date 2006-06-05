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
import java.math.BigInteger;


import ar.org.fitc.test.util.Messages;
import junit.framework.TestCase;
/**
 * Test cases for
 * divide(BigDecimal,int,int) 
 *
 */
public class TestBigDecimalDivide extends TestCase implements Messages{
	BigDecimal bigDec=null;
	
		
	public TestBigDecimalDivide(String name) {
		super(name);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	   public void testDivideBigDecimal001() {		    
		 assertEquals(msgNotSame,new BigDecimal("0E+2147483647"),BigDecimal.ZERO.divide(new BigDecimal(BigInteger.ONE,Integer.MAX_VALUE)));
		}

	   public void testDivideBigDecimal002() {		    
			 assertEquals(msgNotSame,new BigDecimal("0E-2147483647"),BigDecimal.ZERO.divide(new BigDecimal(BigInteger.ONE,Integer.MIN_VALUE)));
			}
	   
	   public void testDivideBigDecimal003() {		    
			 assertEquals(msgNotSame,new BigDecimal("0E+2147483647"),BigDecimal.ZERO.divide(new BigDecimal(new BigInteger("-1"),Integer.MAX_VALUE)));
			}

		   public void testDivideBigDecimal004() {		    
				 assertEquals(msgNotSame,new BigDecimal("0E-2147483647"),BigDecimal.ZERO.divide(new BigDecimal(new BigInteger("-1"),Integer.MIN_VALUE)));
				}
		   
		   public void testDivideBigDecimal005() {		    
				 assertEquals(msgNotSame,new BigDecimal("2147483647"), new BigDecimal("4611686014132420609").divide(new BigDecimal("2147483647")));
				}
		   public void testDivideBigDecimal006() {		    
				 assertEquals(msgNotSame,new BigDecimal("-2147483647"), new BigDecimal("4611686014132420609").divide(new BigDecimal("-2147483647")));
				}
		   
		   public void testDivideBigDecimal007() {		    
				 assertEquals(msgNotSame,new BigDecimal("-2147483647"), new BigDecimal("-4611686014132420609").divide(new BigDecimal("2147483647")));
				}
		   
		   public void testDivideBigDecimal008() {		    
				 assertEquals(msgNotSame,new BigDecimal("2147483647"), new BigDecimal("-4611686014132420609").divide(new BigDecimal("-2147483647")));
				}

		   public void testDivideBigDecimal009() {		    
				 assertEquals(msgNotSame,new BigDecimal("8589934591.9999999995343387126922607421875"), new BigDecimal("18446744073709551615").divide(new BigDecimal("2147483648")));
				}
		   
		   public void testDivideBigDecimal010() {		    
				 assertEquals(msgNotSame,new BigDecimal("-8589934591.9999999995343387126922607421875"), new BigDecimal("-18446744073709551615").divide(new BigDecimal("2147483648")));
				}
		   
		   public void testDivideBigDecimal011() {		    
				 assertEquals(msgNotSame,new BigDecimal("-8589934591.9999999995343387126922607421875"), new BigDecimal("18446744073709551615").divide(new BigDecimal("-2147483648")));
				}
		   public void testDivideBigDecimal012() {		    
				 assertEquals(msgNotSame,new BigDecimal("8589934591.9999999995343387126922607421875"), new BigDecimal("-18446744073709551615").divide(new BigDecimal("-2147483648")));
				}
		   
		 
}
