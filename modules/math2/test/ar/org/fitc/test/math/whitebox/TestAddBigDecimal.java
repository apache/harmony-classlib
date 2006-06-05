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

public class TestAddBigDecimal extends TestCase implements Messages{
	
	public TestAddBigDecimal() {
		super();
		
	}
	
	public TestAddBigDecimal(String name) {
		super(name);
		
	}
	
	public void testAdd001() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			assertEquals("0", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd002() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			assertEquals("1", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd003() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			assertEquals("-1", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd004() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd005() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd006() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			assertEquals("1", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd007() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			assertEquals("2", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd008() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			assertEquals("0", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd009() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741321", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd010() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741319", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd011() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			assertEquals("-1", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd012() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			assertEquals("0", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd013() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			assertEquals("-2", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd014() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741319", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd015() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741321", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd016() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			assertEquals("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd017() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			assertEquals("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741321", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd018() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			assertEquals("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741319", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd019() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals("1794979579482704223794826408270579482704223779482704223794826419482640223794837794827042237948264194827042237948264070422379483794827042237948264064179482640270422379482640", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd020() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals("897489789741343136999515790610000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd021() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			assertEquals("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd022() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			assertEquals("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741319", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd023() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			assertEquals("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741321", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd024() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals("897489789741343136999515790610000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd025() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals("-17949795794827050579482704223779482704223794826419482640223794837794827042237948264194827042237948264070422379483794827042237948264064179482640270422379482640", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd026() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			assertEquals("0", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd027() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			assertEquals("10", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd028() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			assertEquals("-10", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd029() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals("8974897897413521118974132041352897413521118897413521118974132097413201118974188974135211189741320974135211189741320352111897418974135211189741320320897413201352111897413200", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd030() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals("-89748978974135252897413521118897413521118974132097413201118974188974135211189741320974135211189741320352111897418974135211189741320320897413201352111897413200", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd031() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			assertEquals("1", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd032() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			assertEquals("11", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd033() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			assertEquals("-9", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd034() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals("8974897897413521118974132041352897413521118897413521118974132097413201118974188974135211189741320974135211189741320352111897418974135211189741320320897413201352111897413201", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd035() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals("-89748978974135252897413521118897413521118974132097413201118974188974135211189741320974135211189741320352111897418974135211189741320320897413201352111897413199", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd036() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			assertEquals("-1", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd037() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			assertEquals("9", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd038() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			assertEquals("-11", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd039() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals("8974897897413521118974132041352897413521118897413521118974132097413201118974188974135211189741320974135211189741320352111897418974135211189741320320897413201352111897413199", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd040() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals("-89748978974135252897413521118897413521118974132097413201118974188974135211189741320974135211189741320352111897418974135211189741320320897413201352111897413201", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd041() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			assertEquals("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd042() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			assertEquals("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741330", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd043() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			assertEquals("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741310", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd044() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals("9872387687154873230871545245488187154873230787154873230871545307154521230871607871548732308715453071548732308715452387323087160871548732308715452352987154521487323087154520", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd045() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals("897489789741262362918439068882392327830992992327830992923281112328118992923229923278309929232811123278309929232811683099292322923278309929232811711192328118783099292328120", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd046() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			assertEquals("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd047() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			assertEquals("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741310", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd048() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			assertEquals("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741330", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd049() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals("8974897897413512144076234627827607672169007007672169007076718887671881007076770076721690070767188876721690070767188316900707677076721690070767188288807671881216900707671880", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd050() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals("-98723876871548778187154873230787154873230871545307154521230871607871548732308715453071548732308715452387323087160871548732308715452352987154521487323087154520", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd051() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			assertEquals("0E-2147483647", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd052() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			assertEquals("1E-2147483647", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd053() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			assertEquals("-1E-2147483647", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd054() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals("8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E-2147483477", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd055() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals("-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E-2147483491", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd056() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			bi.add(bi2); 
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd057() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			bi.add(bi2); 
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd058() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			bi.add(bi2); 
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd059() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			bi.add(bi2); 
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd060() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			bi.add(bi2); 
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd061() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			bi.add(bi2); 
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd062() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			bi.add(bi2); 
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd063() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			bi.add(bi2); 
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd064() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			bi.add(bi2); 
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd065() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			bi.add(bi2); 
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd066() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			bi.add(bi2); 
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd067() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			bi.add(bi2); 
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd068() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			bi.add(bi2); 
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd069() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			bi.add(bi2); 
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd070() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			bi.add(bi2); 
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd071() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd072() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd073() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd074() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd075() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd076() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483648);  
			assertEquals("0", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd077() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483648);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd078() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483648);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd079() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd080() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd081() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483648);  
			assertEquals("1", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd082() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483648);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd083() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483648);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd084() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd085() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd086() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483648);  
			assertEquals("-1", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd087() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483648);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd088() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483648);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd089() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd090() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd091() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483648);  
			assertEquals("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd092() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483648);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd093() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483648);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd094() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd095() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd096() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483648);  
			assertEquals("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd097() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483648);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd098() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483648);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd099() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd100() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd101() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			assertEquals("0", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd102() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			assertEquals("1", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd103() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			assertEquals("-1", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd104() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd105() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd106() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			assertEquals("10", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd107() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			assertEquals("11", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd108() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			assertEquals("9", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd109() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741330", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd110() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741310", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd111() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			assertEquals("-10", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd112() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			assertEquals("-9", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd113() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			assertEquals("-11", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd114() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741310", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd115() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741330", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd116() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			assertEquals("8974897897413521118974132041352897413521118897413521118974132097413201118974188974135211189741320974135211189741320352111897418974135211189741320320897413201352111897413200", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd117() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			assertEquals("8974897897413521118974132041352897413521118897413521118974132097413201118974188974135211189741320974135211189741320352111897418974135211189741320320897413201352111897413201", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd118() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			assertEquals("8974897897413521118974132041352897413521118897413521118974132097413201118974188974135211189741320974135211189741320352111897418974135211189741320320897413201352111897413199", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd119() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals("9872387687154873230871545245488187154873230787154873230871545307154521230871607871548732308715453071548732308715452387323087160871548732308715452352987154521487323087154520", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd120() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals("8974897897413512144076234627827607672169007007672169007076718887671881007076770076721690070767188876721690070767188316900707677076721690070767188288807671881216900707671880", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd121() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			assertEquals("-89748978974135252897413521118897413521118974132097413201118974188974135211189741320974135211189741320352111897418974135211189741320320897413201352111897413200", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd122() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			assertEquals("-89748978974135252897413521118897413521118974132097413201118974188974135211189741320974135211189741320352111897418974135211189741320320897413201352111897413199", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd123() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			assertEquals("-89748978974135252897413521118897413521118974132097413201118974188974135211189741320974135211189741320352111897418974135211189741320320897413201352111897413201", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd124() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals("897489789741262362918439068882392327830992992327830992923281112328118992923229923278309929232811123278309929232811683099292322923278309929232811711192328118783099292328120", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd125() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals("-98723876871548778187154873230787154873230871545307154521230871607871548732308715453071548732308715452387323087160871548732308715452352987154521487323087154520", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd126() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			assertEquals("0E+1", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd127() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			assertEquals("1E+1", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd128() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			assertEquals("-1E+1", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd129() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals("8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E+171", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd130() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals("-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E+157", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd131() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			assertEquals("1E+1", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd132() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			assertEquals("2E+1", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd133() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			assertEquals("0E+1", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd134() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals("8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741321E+171", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd135() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals("-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741319E+157", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd136() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			assertEquals("-1E+1", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd137() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			assertEquals("0E+1", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd138() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			assertEquals("-2E+1", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd139() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals("8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741319E+171", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd140() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals("-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741321E+157", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd141() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			assertEquals("8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E+171", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd142() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			assertEquals("8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741321E+171", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd143() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			assertEquals("8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741319E+171", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd144() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals("1.794979579482704223794826408270579482704223779482704223794826419482640223794837794827042237948264194827042237948264070422379483794827042237948264064179482640270422379482640E+172", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd145() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals("8.97489789741343136999515790610000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+171", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd146() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			assertEquals("-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E+157", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd147() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			assertEquals("-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741319E+157", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd148() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			assertEquals("-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741321E+157", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd149() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals("8.97489789741343136999515790610000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+171", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd150() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals("-1.7949795794827050579482704223779482704223794826419482640223794837794827042237948264194827042237948264070422379483794827042237948264064179482640270422379482640E+158", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd151() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			assertEquals("0E-2147483647", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd152() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			assertEquals("1E-2147483647", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd153() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			assertEquals("-1E-2147483647", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd154() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals("8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E-2147483477", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd155() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals("-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E-2147483491", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd156() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd157() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd158() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd159() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd160() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd161() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd162() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd163() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd164() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd165() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd166() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd167() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd168() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd169() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd170() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd171() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd172() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd173() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd174() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd175() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd176() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483648);  
			assertEquals("0E+1", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd177() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483648);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd178() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483648);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd179() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd180() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd181() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483648);  
			assertEquals("1E+1", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd182() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483648);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd183() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483648);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd184() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd185() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			bi.add(bi2); 
			
			
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
	public void testAdd186() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483648);  
			assertEquals("-1E+1", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd187() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483648);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd188() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483648);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd189() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd190() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd191() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483648);  
			assertEquals("8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E+171", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd192() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483648);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd193() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483648);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd194() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd195() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd196() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483648);  
			assertEquals("-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E+157", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd197() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483648);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd198() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483648);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd199() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd200() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd201() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			assertEquals("0E-2147483647", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd202() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd203() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd204() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd205() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd206() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			assertEquals("1E-2147483647", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd207() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd208() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd209() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd210() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd211() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			assertEquals("-1E-2147483647", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd212() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd213() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd214() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd215() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd216() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			assertEquals("8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E-2147483477", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd217() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd218() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd219() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd220() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd221() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			assertEquals("-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E-2147483491", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd222() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd223() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd224() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd225() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd226() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			assertEquals("0E-2147483647", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd227() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd228() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd229() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd230() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd231() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			assertEquals("1E-2147483647", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd232() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd233() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd234() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd235() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd236() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			assertEquals("-1E-2147483647", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd237() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd238() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd239() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd240() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd241() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			assertEquals("8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E-2147483477", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd242() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd243() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd244() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd245() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd246() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			assertEquals("-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E-2147483491", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd247() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd248() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd249() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd250() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd251() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			assertEquals("0E-2147483647", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd252() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			assertEquals("1E-2147483647", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd253() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			assertEquals("-1E-2147483647", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd254() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals("8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E-2147483477", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd255() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals("-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E-2147483491", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd256() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			assertEquals("1E-2147483647", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd257() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			assertEquals("2E-2147483647", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd258() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			assertEquals("0E-2147483647", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd259() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals("8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741321E-2147483477", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd260() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals("-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741319E-2147483491", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd261() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			assertEquals("-1E-2147483647", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd262() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			assertEquals("0E-2147483647", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd263() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			assertEquals("-2E-2147483647", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd264() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals("8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741319E-2147483477", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd265() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals("-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741321E-2147483491", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd266() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			assertEquals("8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E-2147483477", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd267() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			assertEquals("8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741321E-2147483477", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd268() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			assertEquals("8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741319E-2147483477", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd269() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals("1.794979579482704223794826408270579482704223779482704223794826419482640223794837794827042237948264194827042237948264070422379483794827042237948264064179482640270422379482640E-2147483476", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd270() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals("8.97489789741343136999515790610000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E-2147483477", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd271() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			assertEquals("-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E-2147483491", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd272() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			assertEquals("-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741319E-2147483491", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd273() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			assertEquals("-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741321E-2147483491", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd274() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals("8.97489789741343136999515790610000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E-2147483477", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd275() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals("-1.7949795794827050579482704223779482704223794826419482640223794837794827042237948264194827042237948264070422379483794827042237948264064179482640270422379482640E-2147483490", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd276() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483648);  
			assertEquals("0E-2147483647", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd277() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483648);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd278() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483648);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd279() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd280() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd281() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483648);  
			assertEquals("1E-2147483647", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd282() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483648);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd283() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483648);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd284() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd285() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd286() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483648);  
			assertEquals("-1E-2147483647", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd287() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483648);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd288() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483648);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd289() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd290() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd291() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483648);  
			assertEquals("8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E-2147483477", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd292() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483648);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd293() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483648);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd294() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd295() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd296() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483648);  
			assertEquals("-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E-2147483491", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd297() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483648);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd298() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483648);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd299() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd300() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd301() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			assertEquals("0", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd302() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			assertEquals("1", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd303() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			assertEquals("-1", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd304() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd305() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			assertEquals("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd306() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd307() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd308() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd309() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd310() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd311() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd312() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd313() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd314() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd315() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd316() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd317() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd318() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd319() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd320() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd321() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd322() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd323() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd324() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd325() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),0);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd326() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			assertEquals("0E+1", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd327() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			assertEquals("1E+1", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd328() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			assertEquals("-1E+1", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd329() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals("8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E+171", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd330() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			assertEquals("-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E+157", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd331() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd332() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd333() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd334() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd335() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd336() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd337() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd338() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd339() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd340() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd341() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd342() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd343() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd344() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd345() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd346() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd347() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd348() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd349() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd350() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-1);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd351() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			assertEquals("0E-2147483647", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd352() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			assertEquals("1E-2147483647", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd353() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			assertEquals("-1E-2147483647", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd354() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals("8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E-2147483477", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd355() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			assertEquals("-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E-2147483491", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd356() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd357() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd358() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd359() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd360() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd361() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd362() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd363() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd364() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd365() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd366() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd367() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd368() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd369() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd370() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd371() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),2147483647);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd372() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),2147483647);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd373() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),2147483647);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd374() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd375() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),2147483647);  
			bi.add(bi2); 
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd376() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483648);  
			assertEquals("0E+2147483648", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd377() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483648);  
			assertEquals("1E+2147483648", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd378() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483648);  
			assertEquals("-1E+2147483648", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd379() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			assertEquals("8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E+2147483818", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd380() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("0"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			assertEquals("-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E+2147483804", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd381() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483648);  
			assertEquals("1E+2147483648", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd382() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483648);  
			assertEquals("2E+2147483648", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd383() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483648);  
			assertEquals("0E+2147483648", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd384() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			assertEquals("8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741321E+2147483818", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd385() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			assertEquals("-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741319E+2147483804", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd386() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483648);  
			assertEquals("-1E+2147483648", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd387() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483648);  
			assertEquals("0E+2147483648", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd388() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483648);  
			assertEquals("-2E+2147483648", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd389() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			assertEquals("8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741319E+2147483818", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd390() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-1"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			assertEquals("-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741321E+2147483804", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd391() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483648);  
			assertEquals("8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E+2147483818", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd392() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483648);  
			assertEquals("8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741321E+2147483818", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd393() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483648);  
			assertEquals("8.97489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741319E+2147483818", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd394() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			assertEquals("1.794979579482704223794826408270579482704223779482704223794826419482640223794837794827042237948264194827042237948264070422379483794827042237948264064179482640270422379482640E+2147483819", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd395() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			assertEquals("8.97489789741343136999515790610000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+2147483818", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd396() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("0"),-2147483648);  
			assertEquals("-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320E+2147483804", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd397() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("1"),-2147483648);  
			assertEquals("-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741319E+2147483804", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd398() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-1"),-2147483648);  
			assertEquals("-8.974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741321E+2147483804", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd399() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("897489789741352111897413204135289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			assertEquals("8.97489789741343136999515790610000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E+2147483818", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
	public void testAdd400() {
		try{
			BigDecimal bi= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			BigDecimal  bi2= new BigDecimal(new BigInteger("-8974897897413525289741352111889741352111897413209741320111897418897413521118974132097413521118974132035211189741897413521118974132032089741320135211189741320"),-2147483648);  
			assertEquals("-1.7949795794827050579482704223779482704223794826419482640223794837794827042237948264194827042237948264070422379483794827042237948264064179482640270422379482640E+2147483805", bi.add(bi2).toString());
		} catch (Throwable e) {
			fail(msgNoException+e);
		}
	}
}
