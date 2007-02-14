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

import java.io.IOException;
import java.math.BigInteger;
import javax.naming.ldap.SortControl;
import javax.naming.ldap.SortKey;
import junit.framework.TestCase;

/**        
 * <p>This Test class is testing the SortControl class.</p>
 * <p>In the next tables we are gonna see the methods that we test in this class:</p>
 * <table class="t" cellspacing="0">
	<tbody><th>Constructors:</th>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="30" name="sas9nt11" readonly="readonly" value="" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="60" name="sas9nt21" readonly="readonly" value="SortControl(SortKey[] sortBy, boolean criticality)" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="30" name="sas9nt11" readonly="readonly" value="" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="60" name="sas9nt21" readonly="readonly" value="SortControl(String[] sortBy, boolean criticality)" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="30" name="sas9nt11" readonly="readonly" value="" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="60" name="sas9nt21" readonly="readonly" value="SortControl(String sortBy, boolean criticality)" id="f10"></td>
			
		</tr>
      
	</tbody></table>
 *
 */
public class TestSortControl extends TestCase {

	/**
	 * <p>This method is not implemted.</p>
	 * @param args Possible parameter to help us initiate all tests.
	 */
	public static void main(String[] args) {
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.SortControl.SortControl(String, boolean)'</p>
	 * <p>Here we are testing if this method constructs a control to sort on a list of sort keys.</p>
	 * <p>The expected result is a null pointer exception.</p>           
	 */
	public void testSortControlSortKeyArrayBoolean001() {

		SortKey[] t=null;
		try {
			SortControl x=new SortControl(t,false);
			fail("The sort keys should not be null.");
		} catch (NullPointerException e) {
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.SortControl.SortControl(String, boolean)'</p>
	 * <p>Here we are testing if this method constructs a control to sort on a list of sort keys.</p>
	 * <p>The expected result is a not null Sort Control.</p>           
	 */
	public void testSortControlSortKeyArrayBoolean002() {

		SortKey[] t={(SortKey)new SortKey("1"),(SortKey)new SortKey("2")};
		try {
			SortControl x=new SortControl(t,false);
			assertNotNull(x);
			assertFalse(x.isCritical());
		} catch (IOException e) {
			fail("Failed with:"+e);
		}
	}

	
	/**
	 * <p>Test method for 'javax.naming.ldap.SortControl.SortControl(String[], boolean)'</p>
	 * <p>Here we are testing if this method constructs a control to sort on a list of attributes in ascending order.</p>
	 * <p>The expected result is a null pointer exception.</p>
	 */
	public void testSortControlStringArrayBoolean001() {

		try {
			String[] x=null;
			SortControl sc=new SortControl(x,false);
			fail("The list of attributes should not be null.");
		} catch (NullPointerException e) {
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.SortControl.SortControl(String[], boolean)'</p>
	 * <p>Here we are testing if this method constructs a control to sort on a list of attributes in ascending order.</p>
	 * <p>The expected result is a not null sort control.</p>
	 */
	public void testSortControlStringArrayBoolean002() {

		try {
			String[] x={"1","2"};
			SortControl sc=new SortControl(x,false);
			assertNotNull(sc);
			assertFalse(sc.isCritical());
		}   catch (IOException e) {
			fail("Failed with:"+e);
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.SortControl.SortControl(String[], boolean)'</p>
	 * <p>Here we are testing if this method constructs a control to sort on a list of attributes in ascending order.</p>
	 * <p>The expected result is a not null sort control.</p>
	 */
	public void testSortControlStringArrayBoolean003() {

		try {
			String[] x={"1","2"};
			SortControl sc=new SortControl(x,true);
			assertNotNull(sc);
			assertTrue(sc.isCritical());
		}   catch (IOException e) {
			fail("Failed with:"+e);
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.SortControl.SortControl(String[], boolean)'</p>
	 * <p>Here we are testing if this method constructs a control to sort on a list of attributes in ascending order.</p>
	 * <p>The expected result is a not null sort control.</p>
	 */
	public void testSortControlStringArrayBoolean004() {

		try {
			String[] x={"",""};
			SortControl sc=new SortControl(x,true);
			assertNotNull(sc);
			assertTrue(sc.isCritical());
		}   catch (IOException e) {
			fail("Failed with:"+e);
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.SortControl.SortControl(SortKey[], boolean)'</p>
	 * <p>Here we are testing if this metho constructs a control to sort on a single attrib.</p>
	 * <p>The expected result is a null pointer exception.</p>
	 */	
	public void testSortControlStringBoolean001() {

		try{
			String t=null;
			SortControl sc=new SortControl(t,false);
			assertNotNull(sc);
			assertFalse(sc.isCritical());
		} catch (NullPointerException e) {
			fail("The attrib can be null."+e);
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.SortControl.SortControl(SortKey[], boolean)'</p>
	 * <p>Here we are testing if this metho constructs a control to sort on a single attrib.</p>
	 * <p>The expected result is a not null sort control.</p>
	 */	
	public void testSortControlStringBoolean002() {

		try{
			String t="anything";
			SortControl sc=new SortControl(t,false);
			assertNotNull(sc);
			assertFalse(sc.isCritical());
		} catch (IOException e) {
			fail("Failed with:"+e);
		} 
		
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.SortControl.SortControl(SortKey[], boolean)'</p>
	 * <p>Here we are testing if this metho constructs a control to sort on a single attrib.</p>
	 * <p>The expected result is a not null sort control.</p>
	 */	
	public void testSortControlStringBoolean003() {

		try{
			String t="anything";
			SortControl sc=new SortControl(t,true);
			assertNotNull(sc);
			assertTrue(sc.isCritical());
		} catch (IOException e) {
			fail("Failed with:"+e);
		} 
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.SortControl.getEncodedValue()'</p>
	 * <p>Here we are testing if this method retrieves the control's ASN.1 BER encoded value. In this case we create a sort
	 * control using an array of sortkey.</p>
	 * <p>The expecting result is the control's ASN.1 BER encoded value.</p>
	 */
	public void testEncodedValueOfSortControl001(){
		SortKey[] sk=null;
		sk=new SortKey[1];
		sk[0]=new SortKey("pepe",false,"leo");
		try {
			SortControl sc=new SortControl(sk,true);
			assertEquals("30 10 30 0e 04 04 70 65 70 65 80 03 6c 65 6f 81 01 ff",toHexString(sc.getEncodedValue()));
		} catch (IOException e) {
			fail("Failed with:"+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.SortControl.getEncodedValue()'</p>
	 * <p>Here we are testing if this method retrieves the control's ASN.1 BER encoded value. In this case we create a sort
	 * control using an array of sortkey.</p>
	 * <p>The expecting result is the control's ASN.1 BER encoded value.</p>
	 */
	public void testEncodedValueOfSortControl002(){
		SortKey[] sk=null;
		sk=new SortKey[1];
		sk[0]=new SortKey("",false,"");
		try {
			SortControl sc=new SortControl(sk,true);
			assertEquals("30 09 30 07 04 00 80 00 81 01 ff",toHexString(sc.getEncodedValue()));
		} catch (IOException e) {
			fail("Failed with:"+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.SortControl.getEncodedValue()'</p>
	 * <p>Here we are testing if this method retrieves the control's ASN.1 BER encoded value. In this case we create a sort
	 * control using an array of sortkey.</p>
	 * <p>The expecting result is the control's ASN.1 BER encoded value.</p>
	 */
	public void testEncodedValueOfSortControl003(){
		SortKey[] sk=null;
		sk=new SortKey[1];
		sk[0]=new SortKey("",true,"");
		try {
			SortControl sc=new SortControl(sk,true);
			assertEquals("30 06 30 04 04 00 80 00",toHexString(sc.getEncodedValue()));
		} catch (IOException e) {
			fail("Failed with:"+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.SortControl.getEncodedValue()'</p>
	 * <p>Here we are testing if this method retrieves the control's ASN.1 BER encoded value. In this case we create a sort
	 * control using an array of sortkey.</p>
	 * <p>The expecting result is the control's ASN.1 BER encoded value.</p>
	 */
	public void testEncodedValueOfSortControl004(){
		SortKey[] sk=null;
		sk=new SortKey[1];
		sk[0]=new SortKey("pepe",true,"laura");
		try {
			SortControl sc=new SortControl(sk,true);
			assertEquals("30 0f 30 0d 04 04 70 65 70 65 80 05 6c 61 75 72 61",toHexString(sc.getEncodedValue()));
		} catch (IOException e) {
			fail("Failed with:"+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.SortControl.getEncodedValue()'</p>
	 * <p>Here we are testing if this method retrieves the control's ASN.1 BER encoded value. In this case we create a sort
	 * control using an array of sortkey.</p>
	 * <p>The expecting result is the control's ASN.1 BER encoded value.</p>
	 */
	public void testEncodedValueOfSortControl005(){
		SortKey[] sk=null;
		sk=new SortKey[1];
		sk[0]=new SortKey("pepe",true,null);
		try {
			SortControl sc=new SortControl(sk,true);
			assertEquals("30 08 30 06 04 04 70 65 70 65",toHexString(sc.getEncodedValue()));
		} catch (IOException e) {
			fail("Failed with:"+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.SortControl.getEncodedValue()'</p>
	 * <p>Here we are testing if this method retrieves the control's ASN.1 BER encoded value. In this case we create a sort
	 * control using an array of strings.</p>
	 * <p>The expecting result is the control's ASN.1 BER encoded value.</p>
	 */
	public void testEncodedValueOfSortControl006(){
		String[] sk={""};
		
		try {
			SortControl sc=new SortControl(sk,true);
			assertEquals("30 04 30 02 04 00",toHexString(sc.getEncodedValue()));
		} catch (IOException e) {
			fail("Failed with:"+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.SortControl.getEncodedValue()'</p>
	 * <p>Here we are testing if this method retrieves the control's ASN.1 BER encoded value. In this case we create a sort
	 * control using an array of strings.</p>
	 * <p>The expecting result is the control's ASN.1 BER encoded value.</p>
	 */
	public void testEncodedValueOfSortControl007(){
		String[] sk={""};
		
		try {
			SortControl sc=new SortControl(sk,false);
			assertEquals("30 04 30 02 04 00",toHexString(sc.getEncodedValue()));
		} catch (IOException e) {
			fail("Failed with:"+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.SortControl.getEncodedValue()'</p>
	 * <p>Here we are testing if this method retrieves the control's ASN.1 BER encoded value. In this case we create a sort
	 * control using an array of strings.</p>
	 * <p>The expecting result is the control's ASN.1 BER encoded value.</p>
	 */
	public void testEncodedValueOfSortControl008(){
		String[] sk={"pepe"};
		
		try {
			SortControl sc=new SortControl(sk,false);
			assertEquals("30 08 30 06 04 04 70 65 70 65",toHexString(sc.getEncodedValue()));
		} catch (IOException e) {
			fail("Failed with:"+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.SortControl.getEncodedValue()'</p>
	 * <p>Here we are testing if this method retrieves the control's ASN.1 BER encoded value. In this case we create a sort
	 * control using an array of strings.</p>
	 * <p>The expecting result is the control's ASN.1 BER encoded value.</p>
	 */
	public void testEncodedValueOfSortControl009(){
		String[] sk={"pepe"};
		
		try {
			SortControl sc=new SortControl(sk,true);
			assertEquals("30 08 30 06 04 04 70 65 70 65",toHexString(sc.getEncodedValue()));
		} catch (IOException e) {
			fail("Failed with:"+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.SortControl.getEncodedValue()'</p>
	 * <p>Here we are testing if this method retrieves the control's ASN.1 BER encoded value. In this case we create a sort
	 * control using an array of strings.</p>
	 * <p>The expecting result is the control's ASN.1 BER encoded value.</p>
	 */
	public void testEncodedValueOfSortControl010(){
		String[] sk={"pepe","","toto"};
		
		try {
			SortControl sc=new SortControl(sk,true);
			assertEquals("30 14 30 06 04 04 70 65 70 65 30 02 04 00 30 06 04 04 74 6f 74 6f",toHexString(sc.getEncodedValue()));
		} catch (IOException e) {
			fail("Failed with:"+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.SortControl.getEncodedValue()'</p>
	 * <p>Here we are testing if this method retrieves the control's ASN.1 BER encoded value. In this case we create a sort
	 * control using an array of strings.</p>
	 * <p>The expecting result is the control's ASN.1 BER encoded value.</p>
	 */
	public void testEncodedValueOfSortControl011(){
		String[] sk={"pepe","","toto"};
		
		try {
			SortControl sc=new SortControl(sk,false);
			assertEquals("30 14 30 06 04 04 70 65 70 65 30 02 04 00 30 06 04 04 74 6f 74 6f",toHexString(sc.getEncodedValue()));
		} catch (IOException e) {
			fail("Failed with:"+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.SortControl.getEncodedValue()'</p>
	 * <p>Here we are testing if this method retrieves the control's ASN.1 BER encoded value. In this case we create a sort
	 * control using a string.</p>
	 * <p>The expecting result is the control's ASN.1 BER encoded value.</p>
	 */
	public void testEncodedValueOfSortControl012(){
		String sk=null;
		
		try {
			SortControl sc=new SortControl(sk,false);
			assertEquals("30 04 30 02 04 00",toHexString(sc.getEncodedValue()));
		} catch (IOException e) {
			fail("Failed with:"+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.SortControl.getEncodedValue()'</p>
	 * <p>Here we are testing if this method retrieves the control's ASN.1 BER encoded value. In this case we create a sort
	 * control using a string.</p>
	 * <p>The expecting result is the control's ASN.1 BER encoded value.</p>
	 */
	public void testEncodedValueOfSortControl013(){
		String sk=null;
		
		try {
			SortControl sc=new SortControl(sk,true);
			assertEquals("30 04 30 02 04 00",toHexString(sc.getEncodedValue()));
		} catch (IOException e) {
			fail("Failed with:"+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.SortControl.getEncodedValue()'</p>
	 * <p>Here we are testing if this method retrieves the control's ASN.1 BER encoded value. In this case we create a sort
	 * control using a string.</p>
	 * <p>The expecting result is the control's ASN.1 BER encoded value.</p>
	 */
	public void testEncodedValueOfSortControl014(){
		String sk="";
		
		try {
			SortControl sc=new SortControl(sk,true);
			assertEquals("30 04 30 02 04 00",toHexString(sc.getEncodedValue()));
		} catch (IOException e) {
			fail("Failed with:"+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.SortControl.getEncodedValue()'</p>
	 * <p>Here we are testing if this method retrieves the control's ASN.1 BER encoded value. In this case we create a sort
	 * control using a string.</p>
	 * <p>The expecting result is the control's ASN.1 BER encoded value.</p>
	 */
	public void testEncodedValueOfSortControl015(){
		String sk="";
		
		try {
			SortControl sc=new SortControl(sk,false);
			assertEquals("30 04 30 02 04 00",toHexString(sc.getEncodedValue()));
		} catch (IOException e) {
			fail("Failed with:"+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.SortControl.getEncodedValue()'</p>
	 * <p>Here we are testing if this method retrieves the control's ASN.1 BER encoded value. In this case we create a sort
	 * control using a string.</p>
	 * <p>The expecting result is the control's ASN.1 BER encoded value.</p>
	 */
	public void testEncodedValueOfSortControl016(){
		String sk="pepe";
		
		try {
			SortControl sc=new SortControl(sk,false);
			assertEquals("30 08 30 06 04 04 70 65 70 65",toHexString(sc.getEncodedValue()));
			sc=new SortControl(sk,true);
			assertEquals("30 08 30 06 04 04 70 65 70 65",toHexString(sc.getEncodedValue()));
		} catch (IOException e) {
			fail("Failed with:"+e);
		}
	}
	
	/*
	 * Method to get the string of a byte array.
	 */
	private static String toHexString(byte[] data) {
		BigInteger bi = new BigInteger(data);
		String s = bi.toString(16);
		StringBuffer hex = new StringBuffer();
		if (s.length() % 2 != 0) {
			s = "0" + s;
		}
		for (int i = 0; i < s.length(); i++) {
			hex.append(s.charAt(i));
			if (i % 2 != 0 && i < s.length() - 1) {
				hex.append(" ");
			}
		}
		return hex.toString();
	}

}
