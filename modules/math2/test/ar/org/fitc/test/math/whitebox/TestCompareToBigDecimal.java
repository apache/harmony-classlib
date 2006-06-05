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
import ar.org.fitc.test.util.Messages;
import junit.framework.TestCase;

public class TestCompareToBigDecimal extends TestCase implements Messages{
	
	public TestCompareToBigDecimal() {
		super();
		
	}
	
	public TestCompareToBigDecimal(String name) {
		super(name);
		
	}
	
	
	public void testCompareToBigDecimal001() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			assertEquals(0, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal002() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal003() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal004() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal005() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal006() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal007() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			assertEquals(0, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal008() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal009() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal010() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal011() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal012() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal013() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			assertEquals(0, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal014() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal015() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal016() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal017() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal018() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal019() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(0, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal020() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal021() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal022() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal023() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal024() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal025() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(0, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal026() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			assertEquals(0, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal027() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal028() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal029() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal030() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal031() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal032() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal033() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal034() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal035() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal036() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal037() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal038() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal039() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal040() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal041() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal042() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal043() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal044() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal045() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal046() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal047() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal048() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal049() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal050() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal051() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			assertEquals(0, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal052() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal053() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal054() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal055() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal056() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal057() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal058() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal059() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal060() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal061() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal062() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal063() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal064() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal065() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal066() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal067() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal068() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal069() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal070() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal071() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal072() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal073() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal074() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal075() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal076() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483647);  
			assertEquals(0, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal077() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal078() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal079() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal080() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal081() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal082() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal083() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal084() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal085() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal086() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal087() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal088() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal089() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal090() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal091() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal092() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal093() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal094() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal095() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal096() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal097() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal098() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal099() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal100() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal101() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			assertEquals(0, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal102() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal103() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal104() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal105() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal106() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal107() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal108() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal109() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal110() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal111() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal112() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal113() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal114() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal115() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal116() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal117() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal118() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal119() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal120() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal121() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal122() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal123() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal124() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal125() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal126() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			assertEquals(0, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal127() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal128() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal129() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal130() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal131() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal132() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			assertEquals(0, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal133() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal134() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal135() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal136() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal137() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal138() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			assertEquals(0, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal139() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal140() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal141() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal142() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal143() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal144() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(0, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal145() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal146() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal147() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal148() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal149() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal150() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(0, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal151() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			assertEquals(0, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal152() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal153() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal154() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal155() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal156() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal157() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal158() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal159() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal160() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal161() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal162() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal163() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal164() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal165() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal166() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal167() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal168() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal169() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal170() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal171() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal172() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal173() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal174() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal175() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal176() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483647);  
			assertEquals(0, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal177() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal178() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal179() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal180() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal181() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal182() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal183() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal184() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal185() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal186() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal187() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal188() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal189() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal190() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal191() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal192() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal193() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal194() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal195() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal196() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal197() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal198() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal199() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal200() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal201() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			assertEquals(0, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal202() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal203() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal204() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal205() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal206() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal207() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal208() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal209() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal210() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal211() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal212() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal213() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal214() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal215() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal216() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal217() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal218() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal219() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal220() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal221() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal222() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal223() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal224() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal225() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal226() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			assertEquals(0, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal227() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal228() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal229() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal230() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal231() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal232() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal233() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal234() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal235() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal236() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal237() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal238() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal239() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal240() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal241() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal242() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal243() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal244() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal245() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal246() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal247() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal248() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal249() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal250() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal251() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			assertEquals(0, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal252() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal253() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal254() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal255() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal256() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal257() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			assertEquals(0, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal258() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal259() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal260() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal261() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal262() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal263() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			assertEquals(0, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal264() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal265() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal266() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal267() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal268() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal269() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(0, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal270() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal271() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal272() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal273() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal274() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal275() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(0, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal276() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483647);  
			assertEquals(0, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal277() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal278() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal279() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal280() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal281() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal282() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal283() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal284() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal285() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal286() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal287() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal288() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal289() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal290() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal291() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal292() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal293() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal294() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal295() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal296() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal297() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal298() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal299() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal300() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal301() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			assertEquals(0, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal302() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal303() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal304() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal305() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal306() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal307() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal308() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal309() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal310() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal311() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal312() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal313() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal314() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal315() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal316() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal317() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal318() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal319() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal320() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal321() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal322() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal323() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal324() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal325() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal326() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			assertEquals(0, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal327() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal328() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal329() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal330() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal331() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal332() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal333() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal334() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal335() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal336() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal337() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal338() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal339() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal340() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal341() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal342() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal343() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal344() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal345() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal346() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal347() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal348() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal349() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal350() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal351() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			assertEquals(0, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal352() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal353() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal354() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal355() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal356() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal357() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal358() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal359() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal360() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal361() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal362() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal363() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal364() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal365() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal366() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal367() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal368() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal369() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal370() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal371() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal372() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal373() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal374() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal375() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal376() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483647);  
			assertEquals(0, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal377() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal378() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal379() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal380() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal381() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal382() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483647);  
			assertEquals(0, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal383() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal384() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal385() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal386() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal387() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal388() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483647);  
			assertEquals(0, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal389() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal390() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal391() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal392() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal393() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal394() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(0, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal395() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal396() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal397() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal398() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal399() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(-1, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testCompareToBigDecimal400() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483647);  
			assertEquals(0, bi.compareTo(bi2));
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
}
