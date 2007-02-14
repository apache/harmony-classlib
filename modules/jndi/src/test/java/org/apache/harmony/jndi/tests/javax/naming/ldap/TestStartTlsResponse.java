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

import org.apache.harmony.jndi.tests.javax.naming.spi.mock.ldap.MockStartTlsResponse;
import junit.framework.TestCase;

/**
 * <p>This class has all test for the class TestStartTlsResponse.</p>
 * <p>Here we are gonna test all its methods as we see in the next table:</p>
 * <table>
	<tbody><th>Method Summary:</th>
		<tr><TD>Return</TD><TD>Method</TD></tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="30" name="sas9nt11" readonly="readonly" value="byte[]" id="f00"></TD>
			<td class="c0" id="c10"><input class="a0" size="80" name="sas9nt21" readonly="readonly" value="getEncodedValue()" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="30" name="sas9nt11" readonly="readonly" value=" String" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="80" name="sas9nt21" readonly="readonly" value="getID()" id="f10"></td>
			
		</tr>
		
	</tbody>
	</table> 
 *
 */
public class TestStartTlsResponse extends TestCase {


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
	 * <p>Test method for 'javax.naming.ldap.StartTlsResponse.getID()'</p>
	 * <p>Here we are testing if this method retrieves the StartTLS response's object identifier string.</p>
	 * <p>The expected result is "1.3.6.1.4.1.1466.20037".</p>
	 */
	public void testGetID() {

		MockStartTlsResponse x=new MockStartTlsResponse();
		assertEquals("1.3.6.1.4.1.1466.20037",x.getID());
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.StartTlsResponse.getEncodedValue()'</p>
	 * <p>Here we are testing if this method retrieves the StartTLS response's ASN.1 BER encoded value.</p>
	 * <p>The expected result is a null value.</p> 
	 */
	public void testGetEncodedValue() {

		MockStartTlsResponse x=new MockStartTlsResponse();
		assertNull(x.getEncodedValue());
	}
		
	
}
