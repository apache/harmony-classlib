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

import javax.naming.NamingException;
import javax.naming.ldap.StartTlsRequest;
import javax.naming.ldap.StartTlsResponse;
import junit.framework.TestCase;

/**
 * <p>This Test class is testing the class StartTlsRequest in the javax.naming.ldap package.</p>
 * <p>Here in the next tables we are gonna find all methods to be tested:</p>
 *  <table class="t" cellspacing="0">
	<tbody><th>Constructors:</th>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="30" name="sas9nt11" readonly="readonly" value="" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="65" name="sas9nt21" readonly="readonly" value="StartTlsRequest()" id="f10"></td>
			
		</tr>
				
	</tbody>
	<table>
	<tbody><th>Method Summary:</th>
		<tr><TD>Return</TD><TD>Method</TD></tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="30" name="sas9nt11" readonly="readonly" value="ExtendedResponse" id="f00"></TD>
			<td class="c0" id="c10"><input class="a0" size="65" name="sas9nt21" readonly="readonly" value="createExtendedResponse(String id, byte[] berValue, int offset, int length)" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="30" name="sas9nt11" readonly="readonly" value="byte[]" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="65" name="sas9nt21" readonly="readonly" value="getEncodedValue()" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="30" name="sas9nt11" readonly="readonly" value="String" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="65" name="sas9nt21" readonly="readonly" value="getID()" id="f10"></td>
			
		</tr>
		
			
	</tbody>
	</table>
 * <hr>
 * 
 */
public class TestStartTlsRequest extends TestCase {

	/**
	 * <p>This method is not implemted.</p>
	 * @param args Possible parameter to help us initiate all tests.
	 */
	public static void main(String[] args) {
	}

	/**
	 * <p>Constructor method of the test class.</p>
	 * <p>Here in this case we do not do anything else of initiate the inherited constructor.</p>
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.StartTlsRequest.StartTlsRequest()'</p>
	 * <p>Here we are testing if this method constructs a StartTLS extended request.</p>
	 * <p>The expected return is an object Tls.</p>
	 */
	public void testStartTlsRequest() {

		assertNotNull(new StartTlsRequest());
		
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.StartTlsRequest.getID()'</p>
	 * <p>Here we are testing if this method retrieves the StartTLS request's object identifier string.</p>
	 * <p>The expected result is a string : "1.3.6.1.4.1.1466.20037".</p>
	 */
	public void testGetID() {
		StartTlsRequest str = new StartTlsRequest();
		assertEquals("1.3.6.1.4.1.1466.20037",str.getID());
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.StartTlsRequest.getEncodedValue()'</p>
	 * <p>Here we are testing if this method retrieves the StartTLS request's ASN.1 BER encoded value.</p>
	 * <p>The expected result is a null value.</p>
	 */
	public void testGetEncodedValue() {
		
		StartTlsRequest str = new StartTlsRequest();
		assertNull(str.getEncodedValue());

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.StartTlsRequest.createExtendedResponse(String, byte[], int, int)'</p>
	 * <p>Here we are testing if this method creates an extended response object that corresponds to the LDAP StartTLS extended request.</p>
	 * <p>The expected result is NamingException.</p>
	 */
	public void testCreateExtendedResponse001() {
		StartTlsRequest str = new StartTlsRequest();
		String ID="";
		int t1=0,t2=0;
		byte[] t0=new byte[] {0,0,0};
		try {
			str.createExtendedResponse(ID,t0,t1,t2);
		} catch (NamingException e) {
		} catch (Throwable e){
			fail("Failed with:"+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.StartTlsRequest.createExtendedResponse(String, byte[], int, int)'</p>
	 * <p>Here we are testing if this method creates an extended response object that corresponds to the LDAP StartTLS extended request.
	 * In this case we are testing the extended response with the argument ID="1.3.6.1.4.1.1466.20037". In this particular case does
     * not exist an implentation.</p>
     * <p>Notice here that this package does not have a provider so an implementation does not exist, so this test must fail with a provider 
     * and not fail with no provider.</p>
	 * <p>The expected result is an exception because here does not exist an implemntation.</p> 
	 */
	public void testCreateExtendedResponse002() {
		StartTlsRequest str = new StartTlsRequest();
		String ID="1.3.6.1.4.1.1466.20037";
		int t1=0,t2=0;
		byte[] t0=null;
		try {
			str.createExtendedResponse(ID,t0,t1,t2);
            fail("Here does not exist an implementation.");
		} catch (NamingException e) {
			
		} catch (Throwable e){
            e.printStackTrace();
			fail("Failed with:"+e);
		}

		
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.StartTlsRequest.createExtendedResponse(String, byte[], int, int)'</p>
	 * <p>Here we are testing if this method creates an extended response object that corresponds to the LDAP StartTLS extended request.
	 * In this case we are testing the extended response with the argument ID="", and the byte array.</p>
	 * <p>Notice here this test is ok because first the class must check the arguments and then search for the implementation.</p>
	 * <p>The expected result is a Naming exception.</p>
	 */
	public void testCreateExtendedResponse003() {
		
		StartTlsRequest str = new StartTlsRequest();
		String ID="";
		int t1=0,t2=0;
		byte[] t0=null;
		try {
			StartTlsResponse x=(StartTlsResponse) str.createExtendedResponse(ID,t0,t1,t2);
			fail("Should not be posible use an id distinct of \"1.3.6.1.4.1.1466.20037\"");
		} catch (NamingException e) {
						
		} catch (Throwable e){
            e.printStackTrace();
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.StartTlsRequest.createExtendedResponse(String, byte[], int, int)'</p>
	 * <p>Here we are testing if this method creates an extended response object that corresponds to the LDAP StartTLS extended request.
	 * In this case we are testing the extended response with the argument ID="1.3.6.1.4.1.1466.20037" and the others arguments should be ignored.</p>
	 * <p>Notice here that this package does not have a provider so an implementation does not exist, so this test must not fail with a provider 
     * and fail with no provider.</p>
	 * <p>The expected result is a Tls response.</p>
	 */
	public void testCreateExtendedResponse004() {
		
		StartTlsRequest str = new StartTlsRequest();
		String ID="1.3.6.1.4.1.1466.20037";
		int t1=210,t2=650;
		byte[] t0=ID.getBytes();
		try {
			StartTlsResponse x=(StartTlsResponse) str.createExtendedResponse(ID,t0,t1,t2);
			assertNotNull(x);
			assertEquals("1.3.6.1.4.1.1466.20037",x.getID());
			assertEquals(null,x.getEncodedValue());
		} catch (NamingException e) {
			fail("Failed with:"+e);
		} catch (Throwable e){
            e.printStackTrace();
			fail("Failed with:"+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.StartTlsRequest.createExtendedResponse(String, byte[], int, int)'</p>
	 * <p>Here we are testing if this method creates an extended response object that corresponds to the LDAP StartTLS extended request.
	 * In this case we are testing the extended response with the argument ID=""</p>
	 * <p>The expected result is an exception.</p>
	 */
	public void testCreateExtendedResponse005() {
		
		StartTlsRequest str = new StartTlsRequest();
		String ID="1.a.2.b.3.d.4";
		int t1=0,t2=0;
		byte[] t0=null;
		try {
			StartTlsResponse tls = (StartTlsResponse) str.createExtendedResponse(ID,t0,t1,t2);
			fail("Should not be posible use an id distinc of \"1.3.6.1.4.1.1466.20037\"");
		} catch (NamingException e) {
		} catch (Throwable e){
			fail("Failed with:"+e);
		}

	}
	
}
