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

import javax.naming.ldap.BasicControl;
import junit.framework.TestCase;

/**
 * <p>This Test class is testing the Basic Control class.</p>
 * <p>In the next tables we are gonna see the methods that we test in this class:</p>
 * <table class="t" cellspacing="0">
	<tbody><th>Constructors:</th>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="30" name="sas9nt11" readonly="readonly" value="" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="60" name="sas9nt21" readonly="readonly" value="BasicControl(String id)" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="30" name="sas9nt11" readonly="readonly" value="" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="60" name="sas9nt21" readonly="readonly" value="BasicControl(String id, boolean criticality, byte[] value)" id="f10"></td>
			
		</tr>
	</tbody>
	<table>
	<tbody><th>Method Summary:</th>
		<tr><TD>Return</TD><TD>Method</TD></tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="30" name="sas9nt11" readonly="readonly" value="byte[]" id="f00"></TD>
			<td class="c0" id="c10"><input class="a0" size="60" name="sas9nt21" readonly="readonly" value="getEncodedValue()" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="30" name="sas9nt11" readonly="readonly" value="String" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="60" name="sas9nt21" readonly="readonly" value="getID()" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="30" name="sas9nt11" readonly="readonly" value="boolean" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="60" name="sas9nt21" readonly="readonly" value="isCritical()" id="f10"></td>
			
		</tr>
		
	</tbody>
	</table> 
 *
 */
public class TestBasicControl extends TestCase {

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
	public TestBasicControl(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.BasicControl.BasicControl(String)'</p>
	 * <p>Here we are testing if we can create a BasicControl of an id null.</p>
	 * <p>The expected result is an instance of BasicControl.</p>
	 */
	public void testBasicControlString001() {
		
		assertNotNull(new BasicControl(null));
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.BasicControl.BasicControl(String)'</p>
	 * <p>Here we are testing if we can create a BasicControl of an id blank.</p>
	 * <p>The expected result has to be an instance of BasicControl.</p>
	 */
	public void testBasicControlString002() {
		
		assertNotNull(new BasicControl(""));
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.BasicControl.BasicControl(String)'</p>
	 * <p>Here we are testing if we can create a BasicControl of an id like "1.2.3.333".</p>
	 * <p>The expected result has to be an instance of BasicControl.</p>
	 */
	public void testBasicControlString003() {
		
		assertNotNull(new BasicControl("1.2.3.333"));
		
	}
	
	
	/**
	 * <p>Test method for 'javax.naming.ldap.BasicControl.BasicControl(String, boolean, byte[])'</p>
	 * <p>Here we are testing to constructs a control using the supplied arguments. This control's value can 
	 * be null, and it's depend on the ANS.1 BER</p>
	 * <p>The expected result has to be an instance of BasicControl.</p>
	 */
	public void testBasicControlStringBooleanByteArray001() {
		
		byte[] test=null;
		assertNotNull(new BasicControl("",true,test));
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.BasicControl.BasicControl(String, boolean, byte[])'</p>
	 * <p>Here we are testing to constructs a control using the supplied arguments. This control's encoded is not null,
	 * is an encoded value of a string into the constructor of the BasicControl.</p>
	 * <p>The expected result has to be an instance of BasicControl.</p>
	 */
	public void testBasicControlStringBooleanByteArray002() {
		
		byte[] test=new String("").getBytes();
		assertNotNull(new BasicControl("",false,test));
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.BasicControl.BasicControl(String, boolean, byte[])'</p>
	 * <p>Here we are testing how does the constructor comport if all arguments are null</p>
	 * <p>The expected result is a null pointer exception.</p>
	 */
	public void testBasicControlStringBooleanByteArray003() {
		
		try{
			Boolean b=null;
			byte[] c=null;
			String a=null;
			BasicControl bc=new BasicControl(a,b,c);
			fail("All Arguments can not be null.");
		}catch (NullPointerException e) {
			
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.BasicControl.BasicControl(String, boolean, byte[])'</p>
	 * <p>Here we are testing to constructs a control using the supplied arguments. This control's encoded is null,
	 * the id string is also null.</p>
	 * <p>The expected result has to be an instance of BasicControl.</p>
	 */
	public void testBasicControlStringBooleanByteArray004() {
		
		byte[] test=null;
		assertNotNull(new BasicControl(null,false,test));
		
	}
	
	/**
	 *<p>Test method for 'javax.naming.ldap.BasicControl.getID()'</p>
	 *<p>Here we are testing the return of a basic id of a BasicControl.</p>
	 *<p>The Expected result is an Id of the form "Control".</p> 
	 */
	public void testGetID001() {
		
		String ID="Control";
		BasicControl bc = new BasicControl(ID);
		assertEquals(ID,bc.getID());
		
	}

	/**
	 *<p>Test method for 'javax.naming.ldap.BasicControl.getID()'</p>
	 *<p>Here we are testing the return of a basic id of a BasicControl.</p>
	 *<p>The Expected result is an Id null.</p> 
	 */
	public void testGetID002() {
		
		String ID=null;
		BasicControl bc = new BasicControl(ID);
		assertEquals(ID,bc.getID());
		
	}
	
	/**
	 *<p>Test method for 'javax.naming.ldap.BasicControl.getID()'</p>
	 *<p>Here we are testing the return of a basic id of a BasicControl.</p>
	 *<p>The Expected result is an Id blank.</p> 
	 */
	public void testGetID003() {
		
		String ID="";
		BasicControl bc = new BasicControl(ID);
		assertEquals(ID,bc.getID());
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.BasicControl.isCritical()'</p>
	 * <p>Here we are testing a false criticality of a BasicControl. Because when we create a BasicControl with no criticality
	 * defined a default false is set.</p>
	 * <p>The expected result is a false criticality.</p>
	 */
	public void testIsCritical001() {
		
		BasicControl bc = new BasicControl("control");
		assertEquals(false,bc.isCritical());
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.BasicControl.isCritical()'</p>
	 * <p>Here we are testing a false criticality of a BasicControl. Because when we create a BasicControl with no criticality
	 * defined a default false is set.</p>
	 * <p>The expected result is a false criticality.</p>
	 */
	public void testIsCritical002() {
		
		BasicControl bc = new BasicControl("");
		assertEquals(false,bc.isCritical());
		
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.BasicControl.isCritical()'</p>
	 * <p>Here we are testing a false criticality of a BasicControl. Because when we create a BasicControl with no criticality
	 * defined a default false is set.</p>
	 * <p>The expected result is a false criticality.</p>
	 */
	public void testIsCritical003() {
		
		BasicControl bc = new BasicControl(null);
		assertEquals(false,bc.isCritical());
		
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.BasicControl.isCritical()'</p>
	 * <p>Here we are testing a true criticality of a BasicControl. In this case we create a BasicControl with a true 
	 * criticity.</p>
	 * <p>The expected result is true criticality.</p>
	 */
	public void testIsCritical004() {
		
		byte[] test = null;
		BasicControl bc = new BasicControl("control", true, test );
		assertEquals(true,bc.isCritical());
		
	}
	/**
	 * <p>Test method for 'javax.naming.ldap.BasicControl.isCritical()'</p>
	 * <p>Here we are testing a true criticality of a BasicControl. In this case we create a BasicControl with a false 
	 * criticity.</p>
	 * <p>The expected result is false criticality.</p>
	 */
	public void testIsCritical005() {
		
		byte[] test = null;
		BasicControl bc = new BasicControl("control", false, test );
		assertEquals(false,bc.isCritical());
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.BasicControl.getEncodedValue()'</p>
	 * <p>Here we are testing the return method of the encoded value of BasicControl. In this case we send an encoded value
	 * null.</p>
	 * <p>The expected result is a null encoded value.</p>
	 */
	public void testGetEncodedValue001() {
		
		byte[] test=null;
		BasicControl bc=new BasicControl("control",true,test);
		assertEquals(null,bc.getEncodedValue());
		
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.BasicControl.getEncodedValue()'</p>
	 * <p>Here we are testing the return method of the encoded value of BasicControl. In this case we send an encoded value
	 * of this originaly date "4:45:40 p.m. Pacific Daylight Time on May 6, 1991" this coded in ASN.1 BER.</p>
	 * <p>The expected result is same encoded value.</p>
	 */
	public void testGetEncodedValue002() {
		
		byte[] test={(byte)17,(byte) 11,(byte) 39,(byte) 31,(byte) 30,(byte) 35,(byte) 30,(byte) 36,(byte) 31,(byte) 36,(byte) 34,
				(byte)35,(byte) 34,(byte) 30, (byte) 2D,(byte) 30,(byte) 37,(byte) 30,(byte) 30};
		BasicControl bc=new BasicControl("control",true,test);
		assertEquals(test,bc.getEncodedValue());
		
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.BasicControl.getEncodedValue()'</p>
	 * <p>Here we are testing the return method of a not null encoded value of BasicControl. In this case we send the bytes of
	 * a string.</p>
	 * <p>The expected result is same encoded value.</p>
	 */
	public void testGetEncodedValue003() {
				
		byte test[]=new String("control").getBytes();
		BasicControl bc=new BasicControl("control",true,test);
		assertNotNull(bc.getEncodedValue());
		
	}

}
