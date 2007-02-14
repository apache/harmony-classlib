/* 
 *  Licensed to the Apache Software Foundation (ASF) under one or more 
 *  contributor license agreements.  See the NOTICE file distributed with 
 *  this work for additional information regarding copyright ownership. 
 *  The ASF licenses this file to You under the Apache License, Version 2.0 
 *  (the "License"); you may not use this file except in compliance with 
 *  the License.  You may obtain a copy of the License at 
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
 * @author Leonardo Soler
 * @author Gabriel Miretti
 * @version 1.0
 */
package org.apache.harmony.jndi.tests.javax.naming.ldap;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicIntegerArray;

import javax.naming.InvalidNameException;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.ldap.BasicControl;
import javax.naming.ldap.Rdn;

import junit.framework.TestCase;

/**
 * <p>Test cases for all methods of the class Rdn.</p>
 * <p>The next two tables contains a list of the methods to be tested, with the return of each method.</p>
 *  <table class="t" cellspacing="0">
	<tbody><th>Constructors:</th>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="Rdn(Attributes attrSet)" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="Rdn(Rdn rdn)" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="Rdn(String rdnString)" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="Rdn(String type, Object value)" id="f10"></td>
			
		</tr>
	</tbody>
	<table>
	<tbody><th>Method Summary:</th>
		<tr><TD>Return</TD><TD>Method</TD></tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="int" id="f00"></TD>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="compareTo(Object obj)" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="boolean" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="equals(Object obj)" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="static String" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="escapeValue(Object val)" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="String " id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="getType()" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="Object " id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="getValue()" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="int" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="hashCode()" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="int" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="size()" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="Attributes" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="toAttributes()" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="String" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="toString()" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="static Object" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="unescapeValue(String val)" id="f10"></td>
			
		</tr>
		
	</tbody>
	</table>
 *
 */
public class TestRdn extends TestCase {
	
	/**
	 * <p>This method is not implemted.</p>
	 * @param args Possible parameter to help us initiate all tests.
	 */
	public static void main(String[] args) {
	}

	/**
	 * <p>Constructor method of the class this class.</p>
	 * <p>Here in this case we do not do anything else of initiate the inherited constructor.</p>
	 * @param name This parameter is used to initiate the inherited constructor. 
	 */
	public TestRdn(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(Attributes)'</p>
	 * <p>Here in this case we are testing to construct an Rdn from the given attribute set. 
	 * Here we are testing if we send a null attribute set.</p>
	 * <p>The expected result is a null pointer exception.</p> 
	 */
	public void testRdnAttributes001() {
		
		
		Attributes set=null;
		try {
			Rdn x=new Rdn(set);
			fail("The attribute set is null");
		} catch (NullPointerException e) {
			
		}
		catch (Throwable e) {
			fail("Failed with:"+e);
		} 
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(Attributes)'</p>
	 * <p>Here in this case we are testing to construct an Rdn from the given attribute set. 
	 * Here we are testing if we send an empty attribute set.</p>
	 * <p>The expected result is an invalid name exception.</p> 
	 */
	public void testRdnAttributes002() {
		
		
		BasicAttributes set=new BasicAttributes();
		try {
			Rdn x=new Rdn(set);
			fail("Failed, the attribute set is empty");
		} catch (InvalidNameException e) {
			
		}
		catch (Throwable e) {
			fail("Failed with:"+e);
		} 
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(Attributes)'</p>
	 * <p>Here in this case we are testing to construct an Rdn from the given attribute set. 
	 * Here we are testing if we send a not empty attribute set, but here we are testing if the values are used literally 
	 * (not parsed) and assumed to be unescaped.</p>
	 * <p>The expected result is an instance of Rdn.</p>
	 */
	public void testRdnAttributes003() {
		
		String x="anything";
		BasicAttributes set=new BasicAttributes("t",x);
		try {
			Rdn rdn=new Rdn(set);
			
		}catch (Throwable e) {
			fail("Failed with:"+e);
		} 
		
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(Attributes)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given attribute set. Here we are testing if we send a not empty attribute set, but here we are testing
	 * if the values are used literally (not parsed) and assumed to be unescaped.</p>
	 * <p>The expected result is an instance of Rdn.</p>
	 */
	public void testRdnAttributes004() {
		
		String x ="as \\, asd"; 
		BasicAttributes set=new BasicAttributes("t",x);
		try {
			Rdn rdn=new Rdn(set);
			
		}catch (Throwable e) {
			fail("Failed with:"+e);
		} 
		
	}
		
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(Attributes)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given attribute set. Here we are testing if we send a not empty attribute set but here we are testing 
	 * if the values are used literally (not parsed) and assumed to be unescaped.</p>
	 * <p>The expected result is an instance of Rdn.</p>
	 */
	public void testRdnAttributes005() {
		
		String x ="asd,asd"; 
		BasicAttributes set=new BasicAttributes("t",x);
		try {
			Rdn rdn=new Rdn(set);
			
		}catch (Throwable e) {
			fail("Failed with:"+e);
		} 
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(Attributes)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given attribute set. Here we are testing if we send a not empty attribute set but here we are testing 
	 * if the values are used literally (not parsed) and assumed to be unescaped.</p>
	 * <p>The expected result is an instance of Rdn.</p>
	 */
	public void testRdnAttributes006() {
		
		BasicAttributes set=new BasicAttributes("t",null);
		try {
			Rdn rdn=new Rdn(set);
			
		}catch (Throwable e) {
			fail("Failed with:"+e);
		} 
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(Attributes)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given attribute set. Here we are testing if we send a not empty attribute set.</p>
	 * <p>The expected result is an exception.</p>
	 */
	public void testRdnAttributes007() {
		
		try {
			BasicAttributes x=new BasicAttributes();
			x.put(new BasicAttribute("t"));
			Rdn rdn=new Rdn(x);
			
			fail("Should raise an exception.");
			
		} catch (NoSuchElementException e) {
			
		}catch (Throwable e) {
			fail("Failed with:"+e);
		}
		
	}
		
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String. Here we are testing if we send a null String.</p>
	 * <p>The expected result is a null pointer exception.</p>
	 */
	public void testRdnString001() {
		
		String x=null;
		try {
			Rdn rdn=new Rdn(x);
			fail("The String is null.");
		} catch (NullPointerException e) {
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String. Here we are testing if we send an empty String.</p>
	 * <p>The expected result is an instance of the class.</p>
	 */
	public void testRdnString002() {
		
		String x = new String(); //Here we create an empty String.
		try {
			Rdn rdn=new Rdn(x);
			assertNotNull(rdn);
		} 
		catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String. Here we are testing if we send a non empty String but with no valid format.</p>
	 * <p>The expected result is invalid name exception.</p>
	 */
	public void testRdnString003() {
		
		String x="wrong";
		try {
			Rdn rdn=new Rdn(x);
			fail("Failed, the string is wrong.");
		} catch (InvalidNameException e) {
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String. Here we are testing if we send a non empty String but with no valid format, in this case we
	 * use the especial character "=", and we are given here no type.</p>
	 * <p>The expected result is invalid name exception.</p>
	 */
	public void testRdnString004() {
		
		String x="=wrong";
		try {
			Rdn rdn=new Rdn(x);
			fail("Failed, the string is wrong.");
		} catch (InvalidNameException e) {
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String. Here we are testing if we send a non empty String but with valid format, in this case we 
	 * use the especial character "=", and we are given here a type with no value.</p>
	 * <p>The expected result is an instance not null of Rdn.</p>
	 */
	public void testRdnString005() {
		
		String x="fine=";
		try {
			assertNotNull(new Rdn(x));
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String. Here we are testing if we send a non empty String with a valid format, in this case we use 
	 * the especial character "+", and we are given here a type with no value.</p>
	 * <p>The expected result is an instance not null of Rdn.</p>
	 */
	public void testRdnString006() {
		
		String x="type=test+value=";
		try {
			assertNotNull(new Rdn(x));
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String. Here we are testing if we send a non empty String with a valid format, in this case we use 
	 * the especial character "+", and we are given here an empty type with an empty value.</p>
	 * <p>The expected result is an instance not null of Rdn.</p>
	 */
	public void testRdnString007() {
		
		String x="type=+value=";
		try {
			assertNotNull(new Rdn(x));
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String. Here we are testing if we send a non empty String but with valid format, in this case we 
	 * use the especial character "=", and we are given here a type with no value, and type is quoted..</p>
	 * <p>The expected result is an exception like Invalid name exception.</p>
	 */
	public void testRdnString008() {
		
		String x="\\4C=";
		try {
			Rdn y=new Rdn(x);
			fail("Failed, the type must not be quoted.");
		} catch (InvalidNameException e) {
			
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String. Here we are testing if we send a non empty String but with valid format, in this case we 
	 * use the especial character "=".</p>
	 * <p>The expected result is an instance of rdn.</p>
	 */
	public void testRdnString009() {
		
		String x="t==t";
		try {
			Rdn y=new Rdn(x);
			assertNotNull(y);		
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String. Here we are testing if we send a non empty String but with valid format, in this case we 
	 * use the especial character "=".</p>
	 * <p>The expected result is an instance of rdn.</p>
	 */
	public void testRdnString010() {
		
		String x="t=t=t";
		try {
			Rdn y=new Rdn(x);
			assertNotNull(y);
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}

	}
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String. Here we are testing if we send a non empty String but with valid format, in this case we 
	 * use the especial character "=".</p>
	 * <p>The expected result is an instance of rdn.</p>
	 */
	public void testRdnString011() {
		
		String x="t=t=t=";
		try {
			Rdn y=new Rdn(x);
			assertNotNull(y);
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String. Here we are testing if we send a non empty String but with valid format, in this case we 
	 * use the especial character "=".</p>
	 * <p>The expected result is an exception.</p>
	 */
	public void testRdnString012() {
		
		String x="t=+=t";
		try {
			Rdn y=new Rdn(x);
			fail("Failed wrong attributes.");
		} catch (InvalidNameException e) {
			
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String. Here we are testing if we send a non empty String but with valid format, in this case we 
	 * use the especial character "=".</p>
	 * <p>The expected result is an instance of rdn.</p>
	 */
	public void testRdnString013() {
		
		String x="t=+t=t=";
		try {
			Rdn y=new Rdn(x);
			assertNotNull(y);
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String. Here we are testing if we send a non empty String but with valid format, in this case we 
	 * use the especial character "=".</p>
	 * <p>The expected result is an instance of rdn.</p>
	 */
	public void testRdnString014() {
		
		String x="t=+t=t=s<asd";
		try {
			Rdn y=new Rdn(x);
			assertNotNull(y);		
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String. Here we are testing if we send a non empty String but with valid format, in this case we 
	 * use the especial character "=".</p>
	 * <p>The expected result is an instance of rdn.</p>
	 */
	public void testRdnString015() {
		
		String x="t=>asd+t=t+t=t=s<asd";
		try {
			Rdn y=new Rdn(x);
			assertNotNull(y);
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String. Here we are testing if we send a non empty String but with valid format.</p>
	 * <p>The expected result is an instance of rdn.</p>
	 */
	public void testRdnString016() {
		
		String x="t=\\#0FA3TA";
		try {
			Rdn y=new Rdn(x);
			assertNotNull(y);
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String. Here we are testing if we send a non empty String but with valid format.</p>
	 * <p>The expected result is an instance of rdn.</p>
	 */
	public void testRdnString017() {
		
		String x="t.2.f.4=\\<0FA3TA\\>";
		try {
			Rdn y=new Rdn(x);
			assertNotNull(y);
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String. Here we are testing if we send a non empty String but with valid format.</p>
	 * <p>The expected result is an instance of rdn.</p>
	 */
	public void testRdnString018() {
		
		String x="t.2.f.4=\\<0FA3TA\\>+h    =\\#0FA3T A+f=#080808";
		try {
			Rdn y=new Rdn(x);
			assertNotNull(y);
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String. Here we are testing if we send a non empty String but with valid format.</p>
	 * <p>The expected result is an instance of rdn.</p>
	 */
	public void testRdnString019() {
		
		String x="t.2.f.4=\\<0FA3TA\\>+h    =\\#0FA3T A+f=#080808+p=p=p+f=\\4c\\4c#34=#080808+f=f=f=f=\\4c>>\\4c=f=\\4c\\,4c<<<<\\#=+f=\\,";
		try {
			Rdn y=new Rdn(x);
			assertNotNull(y);
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String. Here we are testing if we send a non empty String but with valid format.</p>
	 * <p>The expected result is an instance of rdn.</p>
	 */
	public void testRdnString020() {
		
		String x="asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+" +
				"asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+" +
				"asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+" +
				"asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+" +
				"a=<z>=at+a=<z>=at+a=<z>=at+a=<z>=at+a=>z<=at+a=>z<=at+a=>z<=at" +
				"+v================================+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=" +
				"+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a========a";
		try {
			Rdn y=new Rdn(x);
			assertNotNull(y);
			
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String. Here we are testing if we send a non empty String but with invalid format.</p>
	 * <p>The expected result is an invalid name exception.</p>
	 */
	public void testRdnString021() {
		
		String x="asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+" +
				"asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+" +
				"asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+" +
				"asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+" +
				"a=<z>=at+a=<z>=at+a=<z>=at+a=<z>=at+a=>z<=at+a=>z<=at+a=>z<=at" +
				"+v================================+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=" +
				"+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a========a++";
		try {
			Rdn y=new Rdn(x);
			fail("Should fail name is wrong.");
		} catch (InvalidNameException e) {
			
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String. Here we are testing if we send a non empty String but with valid format.</p>
	 * <p>The expected result is an instance of rdn.</p>
	 */
	public void testRdnString022() {
		
		String x="asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+" +
				"asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+" +
				"asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+" +
				"asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+" +
				"a=<z>=at+a=<z>=at+a=<z>=at+a=<z>=at+a=>z<=at+a=>z<=at+a=>z<=at" +
				"+v================================+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=" +
				"+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a========a+";
		try {
			Rdn y=new Rdn(x);
			assertNotNull(y);
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String. Here we are testing if we send a non empty String but with valid format.</p>
	 * <p>The expected result is an instance of rdn.</p>
	 */
	public void testRdnString023() {
		
		String x="asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+" +
				"asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+" +
				"asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+" +
				"asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+" +
				"a=<z>=at+a=<z>=at+a=<z>=at+a=<z>=at+a=>z<=at+a=>z<=at+a=>z<=at" +
				"+v================================+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=" +
				"+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a========a+s=s<>ss";
		try {
			Rdn y=new Rdn(x);
			assertNotNull(y);
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String. Here we are testing if we send a non empty String but with invalid format.</p>
	 * <p>The expected result is an invalid name exception.</p>
	 */
	public void testRdnString024() {
		
		String x="asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+" +
				"asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+" +
				"asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+" +
				"asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+" +
				"a=<z>=at+a=<z>=at+a=<z>=at+a=<z>=at+a=>z<=at+a=>z<=at+a=>z<=at" +
				"+v================================+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=" +
				"+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a========a+s=<>;s=s";
		try {
			Rdn y=new Rdn(x);
			fail("Should fail.");
		} catch (InvalidNameException e) {
			
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String. Here we are testing if we send a non empty String but with invalid format.</p>
	 * <p>The expected result is an invalid name exception.</p>
	 */
	public void testRdnString025() {
		
		String x="asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+" +
				"asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+" +
				"asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+" +
				"asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+" +
				"a=<z>=at+a=<z>=at+a=<z>=at+a=<z>=at+a=>z<=at+a=>z<=at+a=>z<=at" +
				"+v================================+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=" +
				"+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a========a+s=<>,s=s";
		try {
			Rdn y=new Rdn(x);
			fail("Should fail.");
		} catch (InvalidNameException e) {
			
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String. Here we are testing if we send a non empty String but with invalid format.</p>
	 * <p>The expected result is an invalid name exception.</p>
	 */
	public void testRdnString026() {
		
		String x="=t=t";
		try {
			Rdn y=new Rdn(x);
			fail("Should fail.");
		} catch (InvalidNameException e) {
			
		}

	}


	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(Rdn)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given Rdn. Here we are testing if we send a null Rdn.</p>
	 * <p>The expected result is a null pointer exception.</p>
	 */
	public void testRdnRdn001() {

		Rdn x=null;
		try{
			Rdn rdn=new Rdn(x);
			fail("The parameter is null.");
		}catch (NullPointerException e) {
			
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(Rdn)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given Rdn. Here we are testing if we send a non null Rdn.</p>
	 * <p>The expected result is another Rdn with a copy of the given one.</p>
	 */
	public void testRdnRdn002() {

		
		try{
			Rdn x=new Rdn("t=test");
			assertNotNull(new Rdn(x));
			Rdn y=new Rdn(x);
			assertEquals(0,x.compareTo(y));
			
		}catch (Throwable e) {
			fail("Failed with:"+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(Rdn)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given Rdn. Here we are testing if we send a non null Rdn but empty.</p>
	 * <p>The expected result is another Rdn with a copy of the given one.</p>
	 */
	public void testRdnRdn003() {
		
		try{
			Rdn x=new Rdn("");
			assertNotNull(new Rdn(x));
			Rdn y=new Rdn(x);
			assertEquals(0,x.compareTo(y));
			
		}catch (Throwable e) {
			fail("Failed with:"+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(Rdn)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given Rdn. Here we are testing if we send a non null Rdn and non empty.</p>
	 * <p>The expected result is another Rdn with a copy of the given one.</p>
	 */
	public void testRdnRdn004() {
		
		try{
			Rdn x=new Rdn("t=test+y=this");
			assertNotNull(new Rdn(x));
			Rdn y=new Rdn(x);
			assertEquals(0,x.compareTo(y));
			
		}catch (Throwable e) {
			fail("Failed with:"+e);
		}
	}

	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String, Object)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String and Object. Here we are testing if we send a null String and null object as the parameters.</p>
	 * <p>The expected result is a null pointer exception.</p>
	 */
	public void testRdnStringObject001() {
		
		String x=null;
		Object o=null;
		try{
			Rdn rdn=new Rdn(x,o);
			fail("The parameters are null.");
		} catch (NullPointerException e) {
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String, Object)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String and Object. Here we are testing if we send a non null String with a null object.</p>
	 * <p>The expected result is a null pointer exception.</p>
	 */
	public void testRdnStringObject002() {
		
		String x="type";
		Object o=null;
		try{
			Rdn rdn=new Rdn(x,o);
			fail("The parameters are null.");
		} catch (NullPointerException e) {
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String, Object)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String and Object. Here we are testing if we send a null String with a non null object.</p>
	 * <p>The expected result is a null pointer exception.</p>
	 */
	public void testRdnStringObject003() {
		
		String x=null;
		String o="test";
		try{
			Rdn rdn=new Rdn(x,o);
			fail("The parameters are null.");
		} catch (NullPointerException e) {
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String, Object)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String and Object. Here we are testing if we send a non null String with a non null object but empty 
	 * both of them.</p>
	 * <p>The expected result is an invalid name exception.</p>
	 */
	public void testRdnStringObject004() {
		
		String x=new String();
		String o=new String();
		try{
			Rdn rdn=new Rdn(x,o);
			fail("The parameters are null.");
		} catch (InvalidNameException e) {
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String, Object)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String and Object. Here we are testing if we send a non null String with a non null object but 
	 * empty the type.</p>
	 * <p>The expected result is an invalid name exception.</p>
	 */
	public void testRdnStringObject005() {
		
		String x=new String();
		String o="value";
		try{
			Rdn rdn=new Rdn(x,o);
			fail("The parameters are null.");
		} catch (InvalidNameException e) {
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String, Object)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String and Object. Here we are testing if we send a non null String with a non null object but 
	 * empty the value.</p>
	 * <p>The expected result is an invalid name exception.</p>
	 */
	public void testRdnStringObject006() {
		
		String x="type";
		String o=new String();
		try{
			Rdn rdn=new Rdn(x,o);
			fail("The parameters are null.");
		} catch (InvalidNameException e) {
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String, Object)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String and Object. Here we are testing if we send a non null String with a non null object but  
	 * the type here has a special character like "+" this must be permited.</p>
	 * <p>The expected result is an instance not null of Rdn.</p>
	 */
	public void testRdnStringObject007() {
		
		String x="y=asd+t=test";
		String o="a=asd";
		try{
			assertNotNull(new Rdn(x,o));
		
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String, Object)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String and Object. Here we are testing if we send a non null String with a non null object but  
	 * the value here has a special character like "+" this must be permited.</p>
	 * <p>The expected result is an instance not null of Rdn.</p>
	 */
	public void testRdnStringObject008() {
		
		String x="y=asd";
		String o="a=asd+t=test";
		try{
			assertNotNull(new Rdn(x,o));
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String, Object)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String and Object. Here we are testing if we send a null String with an empty object.</p>
	 * <p>The expected result is a null pointer exception.</p>
	 */
	public void testRdnStringObject009() {
		
		String x=null;
		Object o=new Object();
		try{
			Rdn rdn=new Rdn(x,o);
			fail("An argument can not be null.");
		} catch (NullPointerException e) {
				
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String, Object)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String and Object. Here we are testing if we send a null String with non empty object and also this one is not a
	 * string.</p>
	 * <p>The expected result is a null pointer exception.</p>
	 */
	public void testRdnStringObject010() {
		
		String x=null;
		
		try{
			Object o=new Rdn("");
			Rdn rdn=new Rdn(x,o);
			fail("An argument can not be null.");
		} catch (NullPointerException e) {
				
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String, Object)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String and Object. Here we are testing if we send an empty String with a null object.</p>
	 * <p>The expected result is a null pointer exception.</p>
	 */
	public void testRdnStringObject011() {
		
		String x=new String();
		try{
			Object o=null;
			Rdn rdn=new Rdn(x,o);
			fail("An argument can not be null.");
		} catch (NullPointerException e) {
				
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String, Object)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String and Object. Here we are testing if we send an empty String with a not ok object.</p>
	 * <p>The expected result is a invalid name exception.</p>
	 */
	public void testRdnStringObject012() {
		
		String x=new String();
		try{
			Object o=new ArrayList();
			Rdn rdn=new Rdn(x,o);
			fail("The arguments are not ok.");
		} catch (InvalidNameException e) {
				
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String, Object)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String and Object. Here we are testing if we send a non empty String and ok this one with a not ok object.</p>
	 * <p>The expected result is an instance of the calss because the object is not parsed.</p>
	 */
	public void testRdnStringObject013() {
		
		String x="t";
		try{
			Object o=new ArrayList();
			Rdn rdn=new Rdn(x,o);
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String, Object)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String and Object. Here we are testing if we send a non empty String and not ok this one with a null object.</p>
	 * <p>The expected result is a null pointer exception,the string is not parsed but the arguemnts can not be null.</p>
	 */
	public void testRdnStringObject014() {
		
		String x="t=t=t";
		try{
			Object o=null;
			Rdn rdn=new Rdn(x,o);
			fail("The arguments can not be null.");
		} catch (NullPointerException e) {
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String, Object)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String and Object. Here we are testing if we send a non empty String and not ok this one with an empty object.</p>
	 * <p>The expected result is an instance of the class because the arguments are not parsed.</p>
	 */
	public void testRdnStringObject015() {
		
		String x="t=t=t";
		try{
			Object o=new Object();
			Rdn rdn=new Rdn(x,o);
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String, Object)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String and Object. Here we are testing if we send a non empty String and not ok this one with a non empty object.</p>
	 * <p>The expected result is an instance of the class because the arguments are not parsed.</p>
	 */
	public void testRdnStringObject016() {
		
		String x="t=t=t";
		try{
			Object o=new String("test");
			Rdn rdn=new Rdn(x,o);
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String, Object)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String and Object. Here we are testing if we send a non empty String with diferents objects.</p>
	 * <p>The expected result is an instance of the class with the diferents arguments.</p>
	 */
	public void testRdnStringObject017() {
		
		String x="t";
		try{
			assertNotNull(new Rdn(x,new ArrayList()));
			assertNotNull(new Rdn(x,new Object()));
			assertNotNull(new Rdn(x,new BasicControl("test")));
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String, Object)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String and Object. Here we are testing if we send a non empty String and this one not ok with diferents objects.</p>
	 * <p>The expected result is an instance of the class with the diferents arguments because the arguments are not parsed.</p>
	 */
	public void testRdnStringObject018() {
		
		try{
			assertNotNull(new Rdn(new String("t===T"),new ArrayList()));
			assertNotNull(new Rdn(new String("t=+=T"),new Object()));
			assertNotNull(new Rdn(new String("t=,33,=T"),new BasicControl("test")));
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String, Object)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String and Object. Here we are testing if we send a non empty String and this one not ok with diferents objects.</p>
	 * <p>The expected result is an instance of the class with the diferents arguments because the arguments are not parsed.</p>
	 */
	public void testRdnStringObject019() {
		
		try{
			assertNotNull(new Rdn(new String("t===T"),new char[]{'a','v'}));
			assertNotNull(new Rdn(new String("t=+=T"),new int[]{1,2,3}));
			assertNotNull(new Rdn(new String("t=,33,=T"),new BasicControl("test")));
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String, Object)'</p>
	 * <p>This is the test method for the constructor of the class Rdn, in this case we are testing to construct an Rdn 
	 * from the given String and Object. Here we are testing if we send a non empty String and a non empty object.</p>
	 * <p>The expected result is an instance of the class because the arguments are not parsed.</p>
	 */
	public void testRdnStringObject020() {
		
		String x="t=t=t\\<0FA3TA\\>+h    =\\#0FA3T A+f=#080808+p=p=p+f=\\4c\\4c#34=#080808+f=f=f=f=\\4c>>\\4c=f=\\4c\\,4c<<<<\\#=+f=\\,";
		try{
			Object o=new String("\\<0FA3TA\\>+h    =\\#0FA3T A+f=#080808+p=p=p+f=\\4c\\4c#34=#080808+f=f=f=f=\\4c>>\\4c=f=\\4c\\,4c<<<<\\#=+f=\\,");
			Rdn rdn=new Rdn(x,o);
			assertNotNull(rdn);
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.hashCode()'</p>
	 * <p>Here we are testing if this method returns the hash code of this RDN, in this case we are testing if the 
	 * hashcode returned by this method is the correct one, the only hash that we know something is of the Rdn empty, this hash 
	 * has to be zero.</p>
	 * <p>The expected result is the hashcode of the rdn.</p>
	 */
	public void testHashCode001() {
		
		try {
			Rdn rdn=new Rdn("");
			assertEquals(0,rdn.hashCode());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.hashCode()'</p>
	 * <p>Here we are testing if this method returns the hash code of this RDN, in this case we are testing if the 
	 * hashcode returned by this method is the correct one, the test works like this first we take the hash of two diferents intances
	 * of rdns but equals then we check if one of them are equal to 0 (notice that here they should not be) then if they are equals
	 * between them (in this case they must be) all of this 1000 times (notice that during an execution of a Java application, the 
	 * hashCode method must consistently return the same integer).</p>
	 * <p>The expected result is the hashcode of the rdn.</p>
	 */
	public void testHashCode002() {
		
		try {
			for (int i = 0; i < 1000; i++) {
				int x=new Rdn("T=TEST").hashCode();
				int y=new Rdn("t=test").hashCode();
				assertNotSame(0,x&y);
				assertEquals(x,y);
			}
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.hashCode()'</p>
	 * <p>Here we are testing if this method returns the hash code of this RDN, in this case we are testing if the 
	 * hashcode returned by this method is the correct one, the test works like this first we take the hash of two diferents intances
	 * of rdns but equals then we check if one of them are equal to 0 (notice that here they should not be) then if they are equals
	 * between them (in this case they must be) all of this 1000 times (notice that during an execution of a Java application, the 
	 * hashCode method must consistently return the same integer).</p>
	 * <p>The expected result is the hashcode of the rdn.</p>
	 */
	public void testHashCode003() {
		
		try {
			for (int i = 0; i < 1000; i++) {
				int z=new Rdn("t=test+s=this").hashCode();
				int h=new Rdn("T=TEST+S=THIS").hashCode();
				assertNotSame(0,z&h);
				assertEquals(z,h);
			}
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.hashCode()'</p>
	 * <p>Here we are testing if this method returns the hash code of this RDN, in this case we are testing if the 
	 * hashcode returned by this method is the correct one, the test works like this first we take the hash of two diferents intances
	 * of rdns but equals then we check if one of them are equal to 0 (notice that here they should not be) then if they are equals
	 * between them (in this case they must be) all of this 1000 times (notice that during an execution of a Java application, the 
	 * hashCode method must consistently return the same integer).</p>
	 * <p>The expected result is the hashcode of the rdn.</p>
	 */
	public void testHashCode004() {
		
		try {
			for (int i = 0; i < 1000; i++) {
				int z=new Rdn("t=test\\, that+s=this").hashCode();
				int h=new Rdn("T=TEST\\, THAT+S=THIS").hashCode();
				assertNotSame(0,z&h);
				assertEquals(z,h);
			}
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.hashCode()'</p>
	 * <p>Here we are testing if this method returns the hash code of this RDN, in this case we are testing if the 
	 * hashcode returned by this method is the correct one, the test works like this first we take the hash of two diferents intances
	 * of rdns but equals then we check if one of them are equal to 0 (notice that here they should not be) then if they are equals
	 * between them (in this case they must be) all of this 1000 times (notice that during an execution of a Java application, the 
	 * hashCode method must consistently return the same integer).</p>
	 * <p>The expected result is the hashcode of the rdn.</p>
	 */
	public void testHashCode005() {
		
		try {
			for (int i = 0; i < 1000; i++) {
				int z=new Rdn("t= test\\, that+s= this").hashCode();
				int h=new Rdn("T=TEST\\, THAT+S=THIS").hashCode();
				assertNotSame(0,z&h);
				assertEquals(z,h);
			}
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.hashCode()'</p>
	 * <p>Here we are testing if this method returns the hash code of this RDN, in this case we are testing if the 
	 * hashcode returned by this method is the correct one, the test works like this first we take the hash of two diferents intances
	 * of rdns but equals then we check if one of them are equal to 0 (notice that here they should not be) then if they are equals
	 * between them (in this case they must be) all of this 1000 times (notice that during an execution of a Java application, the 
	 * hashCode method must consistently return the same integer).</p>
	 * <p>The expected result is the hashcode of the rdn.</p>
	 */
	public void testHashCode006() {
		
		try {
			for (int i = 0; i < 1000; i++) {
				int x=new Rdn("T= \\54\\45\\53\\54").hashCode();//This should be the same like "T= TEST".
				int y=new Rdn("t=test").hashCode();
				assertNotSame(0,x&y);
				assertEquals(x,y);
			}
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.hashCode()'</p>
	 * <p>Here we are testing if this method returns the hash code of this RDN, in this case we are testing if the 
	 * hashcode returned by this method is the correct one, the test works like this first we take the hash of two diferents intances
	 * of rdns but equals then we check if one of them are equal to 0 (notice that here they should not be) then if they are equals
	 * between them (in this case they must be) all of this 1000 times (notice that during an execution of a Java application, the 
	 * hashCode method must consistently return the same integer).</p>
	 * <p>The expected result is the hashcode of the rdn.</p>
	 */
	public void testHashCode007() {
		
		try {
			for (int i = 0; i < 1000; i++) {
				int x=new Rdn("t=       tes\\54                ").hashCode();//This should be the same like "T= TEST".
				int y=new Rdn("t=test").hashCode();
				assertNotSame(0,x&y);
				assertEquals(x,y);
			}
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
		
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.equals(Object)'</p>
	 * <p>Here we are testing if this method compares the specified Object with this Rdn for equality.
	 * In this case we are sending a null object.</p> 
	 * <p>The expected result is false.</p>
	 */
	public void testEquals001() {
		
		try {
			Rdn rdn=new Rdn("t=test");
			Rdn x=null;
			assertFalse(rdn.equals(x));
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.equals(Object)'</p>
	 * <p>Here we are testing if this method compares the specified Object with this Rdn for equality.
	 * In this case we are sending not an instance of Rdns.</p> 
	 * <p>The expected result is false.</p>
	 */
	public void testEquals002() {
		
		try {
			Rdn rdn=new Rdn("t=test");
			Object x=new Object();
			assertFalse(rdn.equals(x));
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.equals(Object)'</p>
	 * <p>Here we are testing if this method compares the specified Object with this Rdn for equality.
	 * In this case we are sending equals rdns.</p> 
	 * <p>The expected result is true.</p>
	 */
	public void testEquals003() {
		
		try {
			
			assertTrue(new Rdn("t=test").equals(new Rdn("t=test")));
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.equals(Object)'</p>
	 * <p>Here we are testing if this method compares the specified Object with this Rdn for equality.
	 * In this case we are sending an equal rdn but this is upper case, this should not affect the comparision.</p> 
	 * <p>The expected result is true.</p>
	 */
	public void testEquals004() {
		
		try {
			
			assertTrue(new Rdn("t=test").equals(new Rdn("T=TEST")));
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.equals(Object)'</p>
	 * <p>Here we are testing if this method compares the specified Object with this Rdn for equality.
	 * In this case we are sending an equal rdn but this is lower case, this should not affect the comparision.</p> 
	 * <p>The expected result is true.</p>
	 */
	public void testEquals005() {
		
		try {
			
			assertTrue(new Rdn("T=TEST").equals(new Rdn("t=test")));
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.equals(Object)'</p>
	 * <p>Here we are testing if this method compares the specified Object with this Rdn for equality.
	 * In this case we are sending an equal multi-value rdn but this has some diferences in some letters, some are
	 * in lower case, this should not affect the comparision.</p> 
	 * <p>The expected result is true.</p>
	 */
	public void testEquals006() {
		
		try {
			
			assertTrue(new Rdn("T=TEST+v=test").equals(new Rdn("t=test+V=test")));
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.equals(Object)'</p>
	 * <p>Here we are testing if this method compares the specified Object with this Rdn for equality.
	 * In this case we are sending an equal multi-value rdn but this has some diferences in some letters, some are
	 * in lower case and also the order is not the same, this should not affect the comparision.</p> 
	 * <p>The expected result is true.</p>
	 */
	public void testEquals007() {
		
		try {
			
			assertTrue(new Rdn("v=test+T=TEST").equals(new Rdn("t=test+V=test")));
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.equals(Object)'</p>
	 * <p>Here we are testing if this method compares the specified Object with this Rdn for equality.
	 * In this case we are sending a non equal multi-value rdn, some letters are in lower case, this should not
	 * affect the comparision.</p> 
	 * <p>The expected result is false.</p>
	 */
	public void testEquals008() {
		
		try {
			
			assertFalse(new Rdn("v=test+T=TEST").equals(new Rdn("v=test+T=TEST1")));
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.equals(Object)'</p>
	 * <p>Here we are testing if this method compares the specified Object with this Rdn for equality.
	 * In this case we are sending a non equal rdn.</p> 
	 * <p>The expected result is false.</p>
	 */
	public void testEquals009() {
		
		try {
			
			assertFalse(new Rdn("v=test").equals(new Rdn("v=testt")));
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.equals(Object)'</p>
	 * <p>Here we are testing if this method compares the specified Object with this Rdn for equality.
	 * In this case we are sending an equal rdn.</p> 
	 * <p>The expected result is true.</p>
	 */
	public void testEquals010() {
		
		try {
			
			assertTrue(new Rdn("").equals(new Rdn("")));
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.equals(Object)'</p>
	 * <p>Here we are testing if this method compares the specified Object with this Rdn for equality.
	 * In this case we are sending an equal rdn.</p> 
	 * <p>The expected result is true.</p>
	 */
	public void testEquals011() {
		
		try {
			
			assertTrue(new Rdn("t=test+t=that").equals(new Rdn("t=that+t=test")));
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.equals(Object)'</p>
	 * <p>Here we are testing if this method compares the specified Object with this Rdn for equality.
	 * In this case we are sending an equal rdn.</p> 
	 * <p>The expected result is true.</p>
	 */
	public void testEquals012() {
		
		try {
			
			assertTrue(new Rdn("t=test+t=that+t=here").equals(new Rdn("t=that+t=test+t=here")));
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.equals(Object)'</p>
	 * <p>Here we are testing if this method compares the specified Object with this Rdn for equality.
	 * In this case we are sending an equal rdn.</p> 
	 * <p>The expected result is true.</p>
	 */
	public void testEquals013() {
		
		try {
			
			assertTrue(new Rdn("t=test+t=that+t=\\4c").equals(new Rdn("t=that+t=test+t=\\4c")));
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.equals(Object)'</p>
	 * <p>Here we are testing if this method compares the specified Object with this Rdn for equality.
	 * In this case we are sending an equal rdn.</p> 
	 * <p>The expected result is true.</p>
	 */
	public void testEquals014() {
		
		try {
			
			assertTrue(new Rdn("t=test+t=\\4c+t=that").equals(new Rdn("t=that+t=test+t=\\4c")));
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.equals(Object)'</p>
	 * <p>Here we are testing if this method compares the specified Object with this Rdn for equality.
	 * In this case we are sending equals and non equals rdn.</p> 
	 * <p>The expected result is true or false depending the case.</p>
	 */
	public void testEquals015() {
		
		try {
			
			assertTrue(new Rdn("t=test+t=asd+t=that").equals(new Rdn("t=that+t=test+t=asd+")));
			assertFalse(new Rdn("t=test+t=asd+t=that").equals(new Rdn("t=that+t=test+t=asd=")));
			assertFalse(new Rdn("t=test+t=asd+t=that").equals(new Rdn("t=that+t=test+t=asd=+")));
			assertFalse(new Rdn("t=test+t=asd+t=that").equals(new Rdn("t=that+t=test+t=asd+t=")));
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.equals(Object)'</p>
	 * <p>Here we are testing if this method compares the specified Object with this Rdn for equality.
	 * In this case we are sending equals and non equals rdn.</p> 
	 * <p>The expected result is true or false depending the case.</p>
	 */
	public void testEquals016() {
		
		try {
			assertTrue(new Rdn("t",new byte[]{00,01,02}).equals(new Rdn("t",new byte[]{00,01,02})));
			assertFalse(new Rdn("t",new byte[]{00,01}).equals(new Rdn("t",new byte[]{00,01,02})));
			assertFalse(new Rdn("t",new byte[]{00,01,02}).equals(new Rdn("t",new byte[]{00,01})));
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.equals(Object)'</p>
	 * <p>Here we are testing if this method compares the specified Object with this Rdn for equality.
	 * In this case we are sending equals rdns.</p> 
	 * <p>The expected result is exception here.</p>
	 */
	public void testEquals017() {
		
		
		try {
			boolean x=(boolean)new Rdn("t",new char[]{'a','v'}).equals(new Rdn("t",new char[]{'a','v'}));
			fail("Should raise an exception.");
		} catch (ClassCastException e) {
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

		try {
			boolean y=(boolean)new Rdn("t",new int[]{00}).equals(new Rdn("t",new int[]{00}));
			fail("Should raise an exception.");
		} catch (ClassCastException e) {
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

		try {
			boolean z=(boolean)new Rdn("t",new BasicControl("t")).equals(new Rdn("t",new BasicControl("t")));
			fail("Should raise an exception.");
		} catch (ClassCastException e) {
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}


	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.toString()'</p>
	 * <p>Here we are testing if this method give us the correct string of a Rdn that we create, notice here that Rdn created by us
	 * is empty so the string returned must be also empty.</p>
	 * <p>The expected result is an empty string.</p>
	 */
	public void testToString001() {
		try {
			Rdn rdn=new Rdn("");
			assertEquals("",rdn.toString());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
		
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.toString()'</p>
	 * <p>Here we are testing if this method give us the correct string of a Rdn that we create, notice here that Rdn created by us
	 * is not empty so the string returned must be also not empty.</p>
	 * <p>The expected result is an non empty string an eqaul to the one used by to create the rdn.</p>
	 */
	public void testToString002() {
		try {
			String t="t=test";
			Rdn rdn=new Rdn(t);
			assertEquals(t,rdn.toString());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.toString()'</p>
	 * <p>Here we are testing if this method give us the correct string of a Rdn that we create, notice here that Rdn created by us
	 * is not empty so the string returned must be also not empty. Here is the paricularity that the rdn contains multi-values so the
	 * string must be a concatenation of the strings.</p>
	 * <p>The expected result is an non empty string an eqaul to the one used by to create the rdn.</p>
	 */
	public void testToString003() {
		try {
			String t="t=test+v=value";
			Rdn rdn=new Rdn(t);
			assertEquals(t,rdn.toString());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
		
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.toString()'</p>
	 * <p>Here we are testing if this method give us the correct string of a Rdn that we create, notice here that Rdn created by us
	 * is not empty so the string returned must be also not empty. Here is the paricularity that the rdn contains multi-values and
	 * quoted values so the string must be a concatenation of strings and values quoted must in ascii.</p>
	 * <p>The expected result is an non empty string an eqaul to the one used by to create the rdn.</p>
	 */
	public void testToString004() {
		try {
			String t="t=test+v=LL";
			Rdn rdn=new Rdn("t=test+v=\\4C\\4C");
			assertEquals(t,rdn.toString());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.toString()'</p>
	 * <p>Here we are testing if this method give us the correct string of a Rdn that we create, notice here that Rdn created by us
	 * is not empty so the string returned must be also not empty. Here is the paricularity that the rdn contains quoted values so 
	 * the string must be must in ascii.</p>
	 * <p>The expected result is an non empty string an eqaul to the one used by to create the rdn.</p>
	 */
	public void testToString005() {
		try {
			String t="v=LL";
			Rdn rdn=new Rdn("v=\\4C\\4C");
			assertEquals(t,rdn.toString());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.toString()'</p>
	 * <p>Here we are testing if this method give us the correct string of a Rdn that we create, notice here that Rdn created by us
	 * is not empty so the string returned must be also not empty. Here is the paricularity that the rdn contains quoted values so 
	 * the string must be must in ascii also contains two special characters but like character so they must be in the string.</p>
	 * <p>The expected result is an non empty string an eqaul to the one used by to create the rdn.</p>
	 */
	public void testToString006() {
		try {
			String t="v=L\\,L";
			Rdn rdn=new Rdn("v=\\4C\\,\\4C");
			assertEquals(t,rdn.toString());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.toString()'</p>
	 * <p>Here we are testing if this method give us the correct string of a Rdn that we create, notice here that Rdn created by us
	 * is not empty so the string returned must be also not empty. Here is the paricularity that the rdn is created with an object that
	 * contains an array of primitives.</p>
	 * <p>The expected result is the string with the object parsed.</p>
	 */
	public void testToString007() {
		try {
			String t="v=#080100";
			Rdn rdn=new Rdn("v",new byte[]{8,01,0});
			assertEquals(t,rdn.toString());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.toString()'</p>
	 * <p>Here we are testing if this method give us the correct string of a Rdn that we create, notice here that Rdn created by us
	 * is not empty so the string returned must be also not empty. Here is the paricularity that the rdn is created with an object that
	 * contains an array of primitives.</p>
	 * <p>The expected result is an exception.</p>
	 */
	public void testToString008() {
		try {
			int[] t=new int[]{1,2,3,4,5};
			Rdn rdn=new Rdn("t",t);
			rdn.toString();
			fail("Should raise an exception.");
		} catch (Throwable e) {
			
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.toString()'</p>
	 * <p>Here we are testing if this method give us the correct string of a Rdn that we create, notice here that Rdn created by us
	 * is not empty so the string returned must be also not empty. Here is the paricularity that the rdn contains multi-values and
	 * quoted values so the string must be a concatenation of strings and values quoted must in ascii.</p>
	 * <p>The expected result is an non empty string an eqaul to the one used by to create the rdn.</p>
	 */
	public void testToString009() {
		try {
			String t="t=test+t=test+v=LL";
			Rdn rdn=new Rdn("t=test+v=\\4C\\4C+t=test");
			assertEquals(t,rdn.toString());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.toString()'</p>
	 * <p>Here we are testing if this method give us the correct string of a Rdn that we create, notice here that Rdn created by us
	 * is not empty so the string returned must be also not empty. Here is the paricularity that the rdn contains multi-values and
	 * quoted values so the string must be a concatenation of strings and values quoted must in ascii.</p>
	 * <p>The expected result is an non empty string an eqaul to the one used by to create the rdn.</p>
	 */
	public void testToString010() {
		try {
			String t="a=\\<a+a=a\\=a+t=test+t=test+v=asdasd+v=LL";
			Rdn rdn=new Rdn("t=test+v=\\4C\\4C+t=test+a=a=a+v=asdasd+a=<a");
			assertEquals(t,rdn.toString());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
		
	}
	
	public void testToString011() {
		try {
			String t="a=";
			Rdn rdn=new Rdn("a=");
			assertEquals(t,rdn.toString());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
		
	}
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.getValue()'</p>
	 * <p>Here we are testing if this method retrieves one of this Rdn's value. In this case should raise an exception
	 * because the rdn is empty.</p>
	 * <p>The expected result is an exception.</p>
	 */
	public void testGetValue001() {
		try {
			Rdn rdn=new Rdn("");
			rdn.getValue();
			fail("Should raise an exception.");
		} catch (Throwable e) {
			
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.getValue()'</p>
	 * <p>Here we are testing if this method retrieves one of this Rdn's value. In this case if returns an 
	 * object not null when a not empty name is created.</p>
	 * <p>The expected result is a not null and not empty object.</p>
	 */
	public void testGetValue002() {
		try {
			Rdn rdn=new Rdn("t=test");
			assertNotNull(rdn.getValue());
			assertEquals("test",rdn.getValue());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.getValue()'</p>
	 * <p>Here we are testing if this method retrieves one of this Rdn's value. In this case if returns an 
	 * object not null when a not empty name is created. Here with the particularity that a multivalue object is created.</p>
	 * <p>The expected result is the value of the type "d".</p>
	 */
	public void testGetValue003() {
		try {
			Rdn rdn=new Rdn("t=this+d=test");
			assertEquals("test",rdn.getValue());
		
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.getValue()'</p>
	 * <p>Here we are testing if this method retrieves one of this Rdn's value. In this case if returns an 
	 * object not null when a not empty name is created. Here with the particularity that the value is quoted so the result must 
	 * be in ascii.</p>
	 * <p>The expected result is the value of the type in ascii like "LL".</p>
	 */
	public void testGetValue004() {
		try {
			Rdn rdn=new Rdn("t=\\4C\\4C");
			assertEquals("LL",rdn.getValue());
		
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
		
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.getValue()'</p>
	 * <p>Here we are testing if this method retrieves one of this Rdn's value. In this case if returns an 
	 * object not null when a not empty name is created. Here with the particularity that a multivalue object is created
	 * and value is quoted so the result must be in ascii.</p>
	 * <p>The expected result is the value of the type "d".</p>
	 */
	public void testGetValue005() {
		try {
			Rdn rdn=new Rdn("t=\\4C\\4C+d=test+f=asd");
			assertEquals("test",rdn.getValue());
		
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.getValue()'</p>
	 * <p>Here we are testing if this method retrieves one of this Rdn's value. In this case if returns an 
	 * object not null when a not empty name is created. Here with the particularity that a multivalue object is created
	 * and value is quoted so the result must be in ascii.</p>
	 * <p>The expected result is the value of the type "d".</p>
	 */
	public void testGetValue006() {
		try {
			Rdn rdn=new Rdn("t=\\4C\\4C+d=+f=asd");
			assertEquals("",rdn.getValue());
		
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.getValue()'</p>
	 * <p>Here we are testing if this method retrieves one of this Rdn's value. In this case if returns an 
	 * object not null when a not empty name is created. Here with the particularity that a multivalue object is created
	 * with an array of primitive like AtomicIntegerArray.</p>
	 * <p>The expected result is the array like AtomicIntegerArray.</p>
	 */
	public void testGetValue007(){
		try {
			int[] t=new int[]{1,2,3,4,5};
			AtomicIntegerArray x=new AtomicIntegerArray(t);
			Rdn rdn=new Rdn("t",x);
			if(rdn.getValue() instanceof AtomicIntegerArray){
				AtomicIntegerArray m=(AtomicIntegerArray) rdn.getValue();
				for (int i = 0; i < m.length() ; i++) {
					assertEquals(t[i],m.get(i));
				}
			}else {
				fail("The object value was an AtomicIntegerArray");
			}
		
        } catch (Throwable e) {
            if (e.getCause() instanceof UnsupportedOperationException) {
                fail("UNSUPPORTED - Failed with:"+e.getCause());    
            }
            fail("Failed with:"+e);
        }
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.getValue()'</p>
	 * <p>Here we are testing if this method retrieves one of this Rdn's value. In this case if returns an 
	 * object not null when a not empty name is created. Here with the particularity that a multivalue object is created
	 * with an array of primitive.</p>
	 * <p>The expected result is the array with was created.</p>
	 */
	public void testGetValue008(){
		try {
			int[] t=new int[]{1,2,3,4,5};
			Rdn rdn=new Rdn("t",t);
			for (int i = 0; i < t.length ; i++) {
					assertEquals(t[i],Array.getInt(rdn.getValue(),i));
			}
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.getValue()'</p>
	 * <p>Here we are testing if this method retrieves one of this Rdn's value. In this case if returns an 
	 * object not null when a not empty name is created. Here with the particularity that a multivalue object is created
	 * with an array of primitive.</p>
	 * <p>The expected result is the array with was created.</p>
	 */
	public void testGetValue009(){
		try {
			char[] t=new char[]{'\u0124','\u0105','\u0123','\u0124'};
			Rdn rdn=new Rdn("t",t);
			for (int i = 0; i < t.length ; i++) {
					assertEquals(t[i],Array.getInt(rdn.getValue(),i));
			}
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.getValue()'</p>
	 * <p>Here we are testing if this method retrieves one of this Rdn's value. In this case if returns an 
	 * object not null when a not empty name is created. Here with the particularity that a multivalue object is created
	 * with an array of primitive.</p>
	 * <p>The expected result is the array with was created.</p>
	 */
	public void testGetValue010(){
		try {
			byte[] t=new byte[]{02,03,06,0,80};
			Rdn rdn=new Rdn("t",t);
			for (int i = 0; i < t.length ; i++) {
					assertEquals(t[i],Array.getInt(rdn.getValue(),i));
			}
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.getValue()'</p>
	 * <p>Here we are testing if this method retrieves one of this Rdn's value. In this case if returns an 
	 * object not null when a not empty name is created. Here with the particularity that a multivalue object is created
	 * with a wrapper of a primitive.</p>
	 * <p>The expected result is the primitive with was created.</p>
	 */
	public void testGetValue011(){
		try {
			char b='\u0124';
			Character x=new Character(b);
			
			Rdn rdn=new Rdn("t",x);
			assertEquals(b,rdn.getValue());
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.getValue()'</p>
	 * <p>Here we are testing if this method retrieves one of this Rdn's value. In this case if returns an 
	 * object not null when a not empty name is created. Here with the particularity that a multivalue object is created
	 * with a wrapper of a primitive.</p>
	 * <p>The expected result is the primitive with was created.</p>
	 */
	public void testGetValue012(){
		try {
			byte b=8;
			Byte x=new Byte(b);
			
			Rdn rdn=new Rdn("t",x);
			assertEquals(b,rdn.getValue());
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.getValue()'</p>
	 * <p>Here we are testing if this method retrieves one of this Rdn's value. In this case if returns an 
	 * object not null when a not empty name is created. Here with the particularity that a multivalue object is created
	 * with a wrapper of a primitive.</p>
	 * <p>The expected result is the primitive with was created.</p>
	 */
	public void testGetValue013(){
		try {
			int b=8;
			Integer x=new Integer(b);
			
			Rdn rdn=new Rdn("t",x);
			assertEquals(b,rdn.getValue());
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.getValue()'</p>
	 * <p>Here we are testing if this method retrieves one of this Rdn's value. In this case if returns an 
	 * object not null when a not empty name is created. Here with the particularity that a multivalue object is created
	 * with a primitive.</p>
	 * <p>The expected result is the primitive with was created.</p>
	 */
	public void testGetValue014(){
		try {
			int b=8;
			Rdn rdn=new Rdn("t",b);
			assertEquals(b,rdn.getValue());
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.getValue()'</p>
	 * <p>Here we are testing if this method retrieves one of this Rdn's value. In this case if returns an 
	 * object not null when a not empty name is created. Here with the particularity that a multivalue object is created
	 * with a primitive.</p>
	 * <p>The expected result is the primitive with was created.</p>
	 */
	public void testGetValue015(){
		try {
			char b='\u0124';
			Rdn rdn=new Rdn("t",b);
			assertEquals(b,rdn.getValue());
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.getValue()'</p>
	 * <p>Here we are testing if this method retrieves one of this Rdn's value. In this case if returns an 
	 * object not null when a not empty name is created. Here with the particularity that a multivalue object is created
	 * with a primitive.</p>
	 * <p>The expected result is the primitive with was created.</p>
	 */
	public void testGetValue016(){
		try {
			byte b=8;
			Rdn rdn=new Rdn("t",b);
			assertEquals(b,rdn.getValue());
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.getValue()'</p>
	 * <p>Here we are testing if this method retrieves one of this Rdn's value. In this case if returns an 
	 * object not null when a not empty name is created. Here with the particularity that a multivalue object is created
	 * with a wrapper of a wrapper of a primitive.</p>
	 * <p>The expected result is that object.</p>
	 */
	public void testGetValue017(){
		try {
			byte b=8;
			AuxiliaryData temp=new AuxiliaryData(new Byte(b));
			Rdn rdn=new Rdn("t",temp);
			assertNotNull(rdn.getValue());
			if(rdn.getValue().getClass()!=temp.getClass()) fail("The object must be like AuxiliaryDate");
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.getValue()'</p>
	 * <p>Here we are testing if this method retrieves one of this Rdn's value. In this case if returns an 
	 * object not null when a not empty name is created. Here with the particularity that a multivalue object is created
	 * and value is null.</p>
	 * <p>The expected result is null.</p>
	 */
	public void testGetValue018() {
		try {
			BasicAttributes x=new BasicAttributes("t",null);
			Rdn rdn=new Rdn(x);
			assertNull(rdn.getValue());
						
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
		
	}
	
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.getValue()'</p>
	 * <p>Here we are testing if this method retrieves one of this Rdn's value. In this case should raise an exception
	 * because the rdn is empty.</p>
	 * <p>The expected result is an exception.</p>
	 */
	public void testGetType001() {
		try {
			Rdn x=new Rdn("");
		    x.getType();
			fail("Should raise an exception.");
		} catch (Throwable e) {
		
		}
		

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.getType()'</p>
	 * <p>Here we are testing if the method retrieves one of this Rdn's type. In this case the Rdn is
	 * created not empty so the result must be not null and not empty.</p>
	 * <p>The expected result is a not null and not empty String.</p> 
	 */
	public void testGetType002() {
		try {
			Rdn x=new Rdn("t=test");
			assertEquals("t",x.getType());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
		

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.getType()'</p>
	 * <p>Here we are testing if the method retrieves one of this Rdn's type. In this case the Rdn is
	 * created not empty so the result must be not null and not empty, also here we include a multivalue name.</p>
	 * <p>The expected result is a not null and not empty String.</p> 
	 */
	public void testGetType003() {
		try {
			Rdn x=new Rdn("t=test+t2=this");
			assertEquals("t",x.getType());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
		

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.getType()'</p>
	 * <p>Here we are testing if the method retrieves one of this Rdn's type. In this case the Rdn is
	 * created not empty so the result must be not null and not empty, also here we include a multivalue name.</p>
	 * <p>The expected result is a not null and not empty String.</p> 
	 */
	public void testGetType004() {
		try {
			Rdn x=new Rdn("t2=test+t=this");
			assertEquals("t",x.getType());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
		

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.getType()'</p>
	 * <p>Here we are testing if the method retrieves one of this Rdn's type. In this case the Rdn is
	 * created not empty so the result must be not null and not empty, also here we include a multivalue name.</p>
	 * <p>The expected result is a not null and not empty String.</p> 
	 */
	public void testGetType005() {
		try {
			Rdn x=new Rdn("t=test+d=this+a=asd");
			assertEquals("a",x.getType());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
		

	}

	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.compareTo(Object)'</p>
	 * <p>Here we are testing if this method compares this Rdn with the specified Object for order. In this case we
	 * are comparing two empty Rdn.</p>
	 * <p>The expected result is a zero.</p>
	 */
	public void testCompareTo001() {
		
		try {
			Rdn rdn1=new Rdn("");
			Rdn rdn2=new Rdn("");
			assertEquals(0,rdn1.compareTo(rdn2));
		} catch (Throwable e) {
			fail("Failed With:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.compareTo(Object)'</p>
	 * <p>Here we are testing if this method compares this Rdn with the specified Object for order. In this case we
	 * are comparing two Rdn one of them is empty in this case the second one.</p>
	 * <p>The expected result is a positive number.</p>
	 */
	public void testCompareTo002() {
		
		try {
			Rdn rdn1=new Rdn("t=test");
			Rdn rdn2=new Rdn("");
			if(rdn1.compareTo(rdn2)<=0)fail("The comparision is worng.");
		} catch (Throwable e) {
			fail("Failed With:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.compareTo(Object)'</p>
	 * <p>Here we are testing if this method compares this Rdn with the specified Object for order. In this case we
	 * are comparing two Rdn one of them is empty in this case the first one.</p>
	 * <p>The expected result is a negative number.</p>
	 */
	public void testCompareTo003() {
		
		try {
			Rdn rdn1=new Rdn("");
			Rdn rdn2=new Rdn("t=test");
			if(rdn1.compareTo(rdn2)>=0)fail("The comparision is worng.");
		} catch (Throwable e) {
			fail("Failed With:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.compareTo(Object)'</p>
	 * <p>Here we are testing if this method compares this Rdn with the specified Object for order. In this case we
	 * are comparing two Rdn one of them is null in this case the second one.</p>
	 * <p>The expected result is a ClassCastException.</p>
	 */
	public void testCompareTo004() {
		
		try {
			Rdn rdn1=new Rdn("");
			Rdn rdn2=null;
			int i=rdn1.compareTo(rdn2);
	        fail("The comparision is worng.");
		} catch (ClassCastException e) {
			
		} catch (Throwable e) {
			fail("Failed With:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.compareTo(Object)'</p>
	 * <p>Here we are testing if this method compares this Rdn with the specified Object for order. In this case we
	 * are comparing two Rdn one of them has two values, the other one has two values but one is empty.</p>
	 * <p>The expected result is a positive number.</p>
	 */
	public void testCompareTo005() {
		
		try {
			Rdn rdn1=new Rdn("t=test+m=test");
			Rdn rdn2=new Rdn("t=test+m=");
			if(rdn1.compareTo(rdn2)<=0)fail("The comparision is worng.");			
		} catch (Throwable e) {
			fail("Failed With:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.compareTo(Object)'</p>
	 * <p>Here we are testing if this method compares this Rdn with the specified Object for order. In this case we
	 * are comparing two Rdn one of them has two values, the other one has two values but one is empty.</p>
	 * <p>The expected result is a negative number.</p>
	 */
	public void testCompareTo006() {
		
		try {
			Rdn rdn1=new Rdn("t=test+m=");
			Rdn rdn2=new Rdn("t=test+m=test");
			if(rdn1.compareTo(rdn2)>=0)fail("The comparision is worng.");			
		} catch (Throwable e) {
			fail("Failed With:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.compareTo(Object)'</p>
	 * <p>Here we are testing if this method compares this Rdn with the specified Object for order. In this case we
	 * are comparing with an object that is not an instance of Rdn.</p>
	 * <p>The expected result is a ClassCastException.</p>
	 */
	public void testCompareTo007() {
		
		try {
			Rdn rdn1=new Rdn("t=test");
			Object rdn2=new Object();
			rdn1.compareTo(rdn2);
			fail("The comparision is worng.");			
		} catch (ClassCastException e) {
			
		} catch (Throwable e) {
			fail("Failed With:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.compareTo(Object)'</p>
	 * <p>Here we are testing if this method compares this Rdn with the specified Object for order. In this case we
	 * are comparing with an Rdn that is Upper case.</p>
	 * <p>The expected result is a 0.</p>
	 */
	public void testCompareTo008() {
		
		try {
			Rdn rdn1=new Rdn("t=test");
			Rdn rdn2=new Rdn("T=TEST");
			assertEquals(0,rdn1.compareTo(rdn2));
					
		} catch (Throwable e) {
			fail("Failed With:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.compareTo(Object)'</p>
	 * <p>Here we are testing if this method compares this Rdn with the specified Object for order.</p>
	 * <p>The expected result is a 0.</p>
	 */
	public void testCompareTo009() {
		
		try {
			Rdn rdn1=new Rdn("t=test+a=anything");
			Rdn rdn2=new Rdn("A=ANYTHING+T=TEST");
			assertEquals(0,rdn1.compareTo(rdn2));
					
		} catch (Throwable e) {
			fail("Failed With:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.compareTo(Object)'</p>
	 * <p>Here we are testing if this method compares this Rdn with the specified Object for order.</p>
	 * <p>The expected result is a 0.</p>
	 */
	public void testCompareTo010() {
		
		try {
			Rdn rdn1=new Rdn("t=test+a=   anything+A=\\#080808<a+c=c=#0808");
			Rdn rdn2=new Rdn("A=\\#080808<a+c=c=#0808+t=test+a=   anything");
			assertEquals(0,rdn1.compareTo(rdn2));
					
		} catch (Throwable e) {
			fail("Failed With:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.compareTo(Object)'</p>
	 * <p>Here we are testing if this method compares this Rdn with the specified Object for order.</p>
	 * <p>The expected result is a 0.</p>
	 */
	public void testCompareTo011() {
		
		try {
			String x1="t=test+a=   anything+A=\\#080808<a+c=c=#0808+"+
			"asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+" +
			"asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+" +
			"asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+" +
			"asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+" +
			"a=<z>=at+a=<z>=at+a=<z>=at+a=<z>=at+a=>z<=at+a=>z<=at+a=>z<=at" +
			"+v================================+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=" +
			"+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a========a+s=s<>ss";
			String x2="asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+" +
			"asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+" +
			"asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+" +
			"asd=asd=asd=asd=asd=asd=asd=Asd<asd<asd>asd>asd>asd+" +
			"a=<z>=at+a=<z>=at+a=<z>=at+a=<z>=at+a=>z<=at+a=>z<=at+a=>z<=at" +
			"+v================================+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=" +
			"+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a=+a========a+s=s<>ss+" +
			"A=\\#080808<a+c=c=#0808+t=test+a=   anything";
			Rdn rdn1=new Rdn(x1);
			Rdn rdn2=new Rdn(x2);
			assertEquals(0,rdn1.compareTo(rdn2));
					
		} catch (Throwable e) {
			fail("Failed With:"+e);
		}

	}

	/**
	 * <p>Method to test 'javax.naming.ldap.Rdn.toAttributes()', we use this method to compare 
	 * each attribute of an enumeration.</p>
	 * @param ne The naming enumeration that returns the rdn.
	 * @param att The set of attributes with we create the rdn.
	 * @return true if the two enumerations are equals.
	 */
	public static boolean compareAttributes(NamingEnumeration ne, Attributes att) {

		if(ne ==null && att == null) fail("Attributes can not be null.");
		while( !( ne.hasMoreElements() && att.getAll().hasMoreElements() )) {
			return false;
		}
		NamingEnumeration comp = att.getAll();
		while(ne.hasMoreElements()) {
			Attribute attrib = (Attribute) ne.nextElement();
			Attribute attrib2 = (Attribute) comp.nextElement();
			if(!attrib.equals(attrib2))return false;
		}
		
		return true;
	}
	
	
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.toAttributes()'</p>
	 * <p>Here we are testing if this method retrieves the Attributes view of the type/value mappings 
	 * contained in given Rdn wich is sended in the next form: "t=\\4C\\4c", notice that here the values
	 * are quoted.</p>
	 * <p>The expected result is the map of the Rdn."</p>
	 */
	public void testToAttributes001() {
		
		try {
			BasicAttributes t=new BasicAttributes("t","\\4C\\4C");
			Rdn rdn=new Rdn(t);
			BasicAttributes ba=(BasicAttributes) rdn.toAttributes();
			assertNotNull(ba);
			NamingEnumeration ne = ba.getAll();
			assertTrue(compareAttributes(ne,t));

		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.toAttributes()'</p>
	 * <p>Here we are testing if this method retrieves the Attributes view of the type/value mappings 
	 * contained in given Rdn wich is sended in the next form: "", notice that here the name is empty.</p>
	 * <p>The expected result is the map of the Rdn not null but empty.</p>
	 */
	public void testToAttributes002() {
		
		try {
			Rdn rdn=new Rdn("");
			BasicAttributes ba=(BasicAttributes) rdn.toAttributes();
			assertNotNull(ba);
			assertEquals(0,ba.size());
		} catch (Throwable e) {
			fail("Failed with:"+e);
			
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.toAttributes()'</p>
	 * <p>Here we are testing if this method retrieves the Attributes view of the type/value mappings 
	 * contained in given Rdn wich is sended in the next form: "t=hola", notice that here the values
	 * are not quoted.</p>
	 * <p>The expected result is the map of the Rdn."</p>
	 */
	public void testToAttributes003() {
		
		try {
			BasicAttributes t=new BasicAttributes("t","hola");
			Rdn rdn=new Rdn(t);
			BasicAttributes ba=(BasicAttributes) rdn.toAttributes();
			assertNotNull(ba);
			NamingEnumeration ne = ba.getAll();
			assertTrue(compareAttributes(ne,t));
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.toAttributes()'</p>
	 * <p>Here we are testing if this method retrieves the Attributes view of the type/value mappings 
	 * contained in given Rdn wich is sended in the next form: "t=", notice that here the values
	 * are not quoted.</p>
	 * <p>The expected result is the map of the Rdn."</p>
	 */
	public void testToAttributes004() {
		
		try {
			BasicAttributes t=new BasicAttributes("t","");
			Rdn rdn=new Rdn(t);
			BasicAttributes ba=(BasicAttributes) rdn.toAttributes();
			assertNotNull(ba);
			NamingEnumeration ne = ba.getAll();
			assertTrue(compareAttributes(ne,t));
			

		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.toAttributes()'</p>
	 * <p>Here we are testing if this method retrieves the Attributes view of the type/value mappings 
	 * contained in given Rdn wich is sended in the next form: "t=test+a=test2+b=test3", notice that here the values
	 * are not quoted.</p>
	 * <p>The expected result is the map of the Rdn."</p>
	 */
	public void testToAttributes005() {
		
		try {
			BasicAttributes t=new BasicAttributes("t","test+a=test2+b=test3");
			Rdn rdn=new Rdn(t);
			BasicAttributes ba=(BasicAttributes) rdn.toAttributes();
			assertNotNull(ba);
			NamingEnumeration ne = ba.getAll();
			assertTrue(compareAttributes(ne,t));
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.toAttributes()'</p>
	 * <p>Here we are testing if this method retrieves the Attributes view of the type/value mappings 
	 * contained in given Rdn wich is sended in the multivalue form.</p>
	 * <p>The expected result is the map of the Rdn.</p>
	 */
	public void testToAttributes006() {
		
		try {
			BasicAttributes t=new BasicAttributes("t","test+a=test2");
			Rdn rdn=new Rdn(t);
			BasicAttributes ba=(BasicAttributes) rdn.toAttributes();
			assertNotNull(ba);
			NamingEnumeration ne = ba.getAll();
			assertTrue(compareAttributes(ne,t));
			
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.toAttributes()'</p>
	 * <p>Here we are testing if this method retrieves the Attributes view of the type/value mappings 
	 * contained in given Rdn wich is sended in the multivalue form.</p>
	 * <p>The expected result is the map of the Rdn.</p>
	 */
	public void testToAttributes007() {
		
		try {
			
			BasicAttributes t=new BasicAttributes("a","test+a=test2");
			Rdn rdn=new Rdn(t);
			BasicAttributes ba=(BasicAttributes) rdn.toAttributes();
			assertNotNull(ba);
			NamingEnumeration ne = ba.getAll();
			assertTrue(compareAttributes(ne,t));
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.toAttributes()'</p>
	 * <p>Here we are testing if this method retrieves the Attributes view of the type/value mappings 
	 * contained in given Rdn wich is sended in the multivalue form.</p>
	 * <p>The expected result is the map of the Rdn.</p>
	 */
	public void testToAttributes008() {
		
		try {
			BasicAttributes t=new BasicAttributes("a",">asd+t=t+t=t=s<asd");
			Rdn rdn=new Rdn(t);
			BasicAttributes ba=(BasicAttributes) rdn.toAttributes();
			assertNotNull(ba);
			NamingEnumeration ne = ba.getAll();
			assertTrue(compareAttributes(ne,t));
						
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
		
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.toAttributes()'</p>
	 * <p>Here we are testing if this method retrieves the Attributes view of the type/value mappings 
	 * contained in given Rdn wich is sended in the multivalue form.</p>
	 * <p>The expected result is the map of the Rdn.</p>
	 */
	public void testToAttributes009() {
		
		try {
			BasicAttributes t=new BasicAttributes("a","+t=t=");
			Rdn rdn=new Rdn(t);
			BasicAttributes ba=(BasicAttributes) rdn.toAttributes();
			assertNotNull(ba);
			NamingEnumeration ne = ba.getAll();
			assertTrue(compareAttributes(ne,t));
			
						
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.toAttributes()'</p>
	 * <p>Here we are testing if this method retrieves the Attributes view of the type/value mappings 
	 * contained in given Rdn wich is sended in the multivalue form.</p>
	 * <p>The expected result is the map of the Rdn.</p>
	 */
	public void testToAttributes010() {
		
		try {
			BasicAttributes t=new BasicAttributes("a","t=t");
			Rdn rdn=new Rdn(t);
			BasicAttributes ba=(BasicAttributes) rdn.toAttributes();
			assertNotNull(ba);
			NamingEnumeration ne = ba.getAll();
			assertTrue(compareAttributes(ne,t));
			
						
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}

	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.size()'</p>
	 * <p>Here we are testing if the return of this method is the correct size of the given Rdn wich has
	 * one names.</p>
	 * <p>The expected result is an integer with the value of one.</p>
	 */
	public void testSize001() {

		try {
			Rdn rdn=new Rdn("t1","test");
			assertEquals(1,rdn.size());
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.size()'</p>
	 * <p>Here we are testing if the return of this method is the correct size of the given empty Rdn.</p>
	 * <p>The expected result is an integer with the value of zero.</p>
	 */
	public void testSize002() {

		try {
			Rdn rdn=new Rdn("");
			assertEquals(0,rdn.size());
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.size()'</p>
	 * <p>Here we are testing if the return of this method is the correct size of the given Rdn wich has
	 * three names.</p>
	 * <p>The expected result is an integer with the value of three.</p>
	 */
	public void testSize003() {

		try {
			Rdn rdn=new Rdn("t=test+d=asd+s=anything");
			assertEquals(3,rdn.size());
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}
	}

	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.escapeValue(Object)'</p>
	 * <p>Here we are testing if this method given the value of an attribute, 
	 * returns a string escaped. In this case the method must be static so there is no need to
	 * create a previously instance of Rdn, the value could be like this "this, that" here the 
	 * special character is ",".</p>
	 * <p>The expected result is like this "this\, that".</p>
	 */
	public void testEscapeValue001() {
		
		assertEquals("this\\, that",Rdn.escapeValue("this, that"));

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.escapeValue(Object)'</p>
	 * <p>Here we are testing if this method given the value of an attribute, 
	 * returns a string escaped. In this case the method must be static so there is no need to
	 * create a previously instance of Rdn, the value could be like this "this "that"", here the 
	 * special character is """.</p>
	 * <p>The expected result is like this "this \"that\"".</p>
	 */
	public void testEscapeValue002() {
		
		assertEquals("this \\\"that\\\"",Rdn.escapeValue("this \"that\""));

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.escapeValue(Object)'</p>
	 * <p>Here we are testing if this method given the value of an attribute, 
	 * returns a string escaped. In this case the method must be static so there is no need to
	 * create a previously instance of Rdn, the value could be like this "this+that", here the 
	 * special character is "+".</p>
	 * <p>The expected result is like this "this+that".</p>
	 */
	public void testEscapeValue003() {
		
		assertEquals("this\\+that",Rdn.escapeValue("this+that"));

	}

	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.escapeValue(Object)'</p>
	 * <p>Here we are testing if this method given the value of an attribute, 
	 * returns a string escaped. In this case the method must be static so there is no need to
	 * create a previously instance of Rdn, the value could be like this "this;that", here the 
	 * special character is ";".</p>
	 * <p>The expected result is like this "this\;that".</p>
	 */
	public void testEscapeValue004() {
		
		assertEquals("this\\;that",Rdn.escapeValue("this;that"));

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.escapeValue(Object)'</p>
	 * <p>Here we are testing if this method given the value of an attribute, 
	 * returns a string escaped. In this case the method must be static so there is no need to
	 * create a previously instance of Rdn, the value could be like this "this&lt;that", here the 
	 * special character is "&lt;".</p> 
	 * <p>The expected result is like this "this&lt;that".</p>
	 */
	public void testEscapeValue005() {
		
		assertEquals("this\\<that",Rdn.escapeValue("this<that"));

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.escapeValue(Object)'</p>
	 * <p>Here we are testing if this method given the value of an attribute, 
	 * returns a string escaped. In this case the method must be static so there is no need to
	 * create a previously instance of Rdn, the value could be like this "this&gt;that", here the 
	 * special character is "&gt;".</p> 
	 * <p>The expected result is like this "this&gt;that".</p>
	 */
	public void testEscapeValue006() {
		
		assertEquals("this\\>that",Rdn.escapeValue("this>that"));

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.escapeValue(Object)'</p>
	 * <p>Here we are testing if this method given the value of an attribute, 
	 * returns a string escaped. In this case the method must be static so there is no need to
	 * create a previously instance of Rdn, the value in this case is null.</p> 
	 * <p>The expected result is a null pointer exception.</p>
	 */
	public void testEscapeValue007() {
		
		try{
			Rdn.escapeValue(null);
			fail("The argument is wrong.");
		}catch (NullPointerException e) {
			
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.escapeValue(Object)'</p>
	 * <p>Here we are testing if this method given the value of an attribute, 
	 * returns a string escaped. In this case the method must be static so there is no need to
	 * create a previously instance of Rdn, the value in this case is not a string but a diferent object.</p> 
	 * <p>The expected result is a class cast exception.</p>
	 */
	public void testEscapeValue008() {
		
		try{
			Object x =new Object();
			Rdn.escapeValue(x);
			fail("The argument is wrong.");
		}catch (ClassCastException e) {
			
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.Rdn.escapeValue(Object)'</p>
	 * <p>Here we are testing if this method given the value of an attribute, 
	 * returns a string escaped. In this case the method must be static so there is no need to
	 * create a previously instance of Rdn, the value in this case is a byte array.</p> 
	 * <p>The expected result is the escape value for that array.</p>
	 */
	public void testEscapeValue009() {
		
	    byte[] x ={(byte)4,(byte) 8f};
		assertEquals("#0408",Rdn.escapeValue(x));
		
	}

	
	/**
	 *<p> Test method for 'javax.naming.ldap.Rdn.unescapeValue(String)'</p>
	 *<p> Here we are testing if a given attribute value string formated, returns the unformated
	 * value. In this case we are testing the special character "," in the form "this\,that".</p>
	 * <p>The expected result is "this,that".</p>
	 */
	public void testUnescapeValue001() {

		assertEquals("this,that",Rdn.unescapeValue("this\\,that"));
	}
	
	/**
	 *<p> Test method for 'javax.naming.ldap.Rdn.unescapeValue(String)'</p>
	 *<p> Here we are testing if a given attribute value string formated, returns the unformated
	 * value. In this case we are testing the special character "+" in the form "this\+that".</p>
	 * <p>The expected result is "this+that".</p>
	 */
	public void testUnescapeValue002() {

		assertEquals("this+that",Rdn.unescapeValue("this\\+that"));
	}
	
	/**
	 *<p> Test method for 'javax.naming.ldap.Rdn.unescapeValue(String)'</p>
	 *<p> Here we are testing if a given attribute value string formated, returns the unformated
	 * value. In this case we are testing the special character ";" in the form "this\;that".</p>
	 * <p>The expected result is "this;that".</p>
	 */
	public void testUnescapeValue003() {

		assertEquals("this;that",Rdn.unescapeValue("this\\;that"));
	}

	/**
	 *<p> Test method for 'javax.naming.ldap.Rdn.unescapeValue(String)'</p>
	 *<p> Here we are testing if a given attribute value string formated, returns the unformated
	 * value. In this case we are testing the special character "&lt;" in the form "this\&lt;that".</p>
	 * <p>The expected result is "this&lt;that".</p>
	 */
	public void testUnescapeValue004() {

		assertEquals("this<that",Rdn.unescapeValue("this\\<that"));
	}

	/**
	 *<p> Test method for 'javax.naming.ldap.Rdn.unescapeValue(String)'</p>
	 *<p> Here we are testing if a given attribute value string formated, returns the unformated
	 * value. In this case we are testing the special character "&gt;" in the form "this\&gt;that".</p>
	 * <p>The expected result is "this&gt;that".</p>
	 */
	public void testUnescapeValue005() {

		assertEquals("this>that",Rdn.unescapeValue("this\\>that"));
	}

	/**
	 *<p> Test method for 'javax.naming.ldap.Rdn.unescapeValue(String)'</p>
	 *<p> Here we are testing if a given attribute value string formated, returns the unformated
	 * value. In this case we are testing the special character "\" in the form "this\that".</p>
	 * <p>The expected result is an illegal argument ecpetion.</p>
	 */
	public void testUnescapeValue006() {

		try{
		Rdn.unescapeValue("this\\that");
		fail("The arguments are wrong.");
		}catch (IllegalArgumentException e) {
			
		}
	}
	
	/**
	 *<p> Test method for 'javax.naming.ldap.Rdn.unescapeValue(String)'</p>
	 *<p> Here we are testing if a given attribute value string formated, returns the unformated
	 * value. In this case we are testing the special character "\" in the form "this\4C".</p>
	 * <p>The expected result is "thisL".</p>
	 */
	public void testUnescapeValue007() {

		assertEquals("thisL",Rdn.unescapeValue("this\\4C"));
		
	}

	/**
	 *<p> Test method for 'javax.naming.ldap.Rdn.unescapeValue(String)'</p>
	 *<p> Here we are testing if a given attribute value string formated, returns the unformated
	 * value. In this case we are testing if send a null.</p>
	 * <p>The expected result is a null pointer exception.</p>
	 */
	public void testUnescapeValue008() {

		try{
			Rdn.unescapeValue(null);
			fail("The argument is worng.");
		}catch (NullPointerException e) {
			
		}
		
	}

	/**
	 *<p> Test method for 'javax.naming.ldap.Rdn.unescapeValue(String)'</p>
	 *<p> Here we are testing if a given attribute value string formated, returns the unformated
	 * value. In this case we are testing the special character "#" in the form "#04".</p>
	 * <p>The expected result is an object with the implementation of the byte that represents.</p>
	 */
	public void testUnescapeValue009() {
		assertEquals("#04",Rdn.escapeValue(Rdn.unescapeValue("#04")));
	}
    
	/**
	 *<p> Test method for 'javax.naming.ldap.Rdn.unescapeValue(String)'</p>
	 *<p> Here we are testing if a given attribute value string formated, returns the unformated
	 * value. In this case we are testing the special character "#" in the form "#GOFJMOII".</p>
	 * <p>The expected result is an exception.</p>
	 */
    public void testUnescapeValue010() {
        try {
            Rdn.unescapeValue("#GOFJMOII");
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e ) {
            
        }
    }

    /*
     * Class to help us test.
     */
	class AuxiliaryData {
		Byte temp;
		AuxiliaryData(Byte x){
			this.temp=x;
		}
		Byte getByte(){
			System.out.println("aca");
			return temp;
		}
		
	}
}
