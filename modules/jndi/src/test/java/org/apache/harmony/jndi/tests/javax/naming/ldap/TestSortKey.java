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

import javax.naming.ldap.SortKey;
import junit.framework.TestCase;

/**        
 * <p>This Test class is testing the SortKey class.</p>
 * <p>In the next tables we are gonna see the methods that we test in this class:</p>
 * <table class="t" cellspacing="0">
	<tbody><th>Constructors:</th>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="30" name="sas9nt11" readonly="readonly" value="" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="60" name="sas9nt21" readonly="readonly" value="SortKey(String attrID)" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="30" name="sas9nt11" readonly="readonly" value="" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="60" name="sas9nt21" readonly="readonly" value="SortKey(String attrID, boolean ascendingOrder, String matchingRuleID)" id="f10"></td>
			
		</tr>
	</tbody>
	<table>
	<tbody><th>Method Summary:</th>
		<tr><TD>Return</TD><TD>Method</TD></tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="30" name="sas9nt11" readonly="readonly" value="String" id="f00"></TD>
			<td class="c0" id="c10"><input class="a0" size="60" name="sas9nt21" readonly="readonly" value="getAttributeID()" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="30" name="sas9nt11" readonly="readonly" value="String" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="60" name="sas9nt21" readonly="readonly" value="getMatchingRuleID()" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="30" name="sas9nt11" readonly="readonly" value="boolean" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="60" name="sas9nt21" readonly="readonly" value="isAscending()" id="f10"></td>
			
		</tr>
		
	</tbody>
	</table> 
 *
 */
public class TestSortKey extends TestCase {

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
	 * <p>Test method for 'javax.naming.ldap.SortKey.SortKey(String)'</p>
	 * <p>Here we are testing if this method creates the default sort key for an attribute. In this case we send a null ID.</p>
	 * <p>The expected result is a null pointer exception.</p>
	 */
	public void testSortKeyString001() {
		
		try{
			SortKey x=new SortKey(null);
			fail("The id should not be null.");
		}catch (NullPointerException e) {
			
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.SortKey.SortKey(String)'</p>
	 * <p>Here we are testing if this method creates the default sort key for an attribute. In this case we send a blank ID.</p>
	 * <p>The expected result is a not null sort key.</p>
	 */
	public void testSortKeyString002() {
		
		assertNotNull(new SortKey(""));
		
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.SortKey.SortKey(String)'</p>
	 * <p>Here we are testing if this method creates the default sort key for an attribute. In this case we send an ID like "anything".</p>
	 * <p>The expected result is a not null sort key.</p>
	 */
	public void testSortKeyString003() {
		
		assertNotNull(new SortKey("anything"));
		
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.SortKey.SortKey(String, boolean, String)'</p>
	 * <p>Here we are testing if this method creates a sort key for an attribute. In this case we send a null id, a false and another null.</p>
	 * <p>The expected result a null pointer exception.</p>
	 */
	public void testSortKeyStringBooleanString001() {

		try{
			SortKey x=new SortKey(null,false,null);
			fail("The attrID should not be null.");
		} catch (NullPointerException e) {
			
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.SortKey.SortKey(String, boolean, String)'</p>
	 * <p>Here we are testing if this method creates a sort key for an attribute. In this case we send a not null id like "", a false and another null.</p>
	 * <p>The expected result a not null sort key.</p>
	 */
	public void testSortKeyStringBooleanString002() {

		SortKey x=new SortKey("",false,null);
		assertNotNull(x);
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.SortKey.SortKey(String, boolean, String)'</p>
	 * <p>Here we are testing if this method creates a sort key for an attribute. In this case we send a not null id like "anything", a
	 * false and another not null like "".</p>
	 * <p>The expected result a not null sort key.</p>
	 */
	public void testSortKeyStringBooleanString003() {

		SortKey x=new SortKey("anything",false,"");
		assertNotNull(x);
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.SortKey.SortKey(String, boolean, String)'</p>
	 * <p>Here we are testing if this method creates a sort key for an attribute. In this case we send a not null id like "anything", a
	 * true and another not null like "anything".</p>
	 * <p>The expected result a not null sort key.</p>
	 */
	public void testSortKeyStringBooleanString004() {

		SortKey x=new SortKey("anything",true,"anything");
		assertNotNull(x);
	}

	
	/**
	 * <p>Test method for 'javax.naming.ldap.SortKey.getAttributeID()'</p>
	 * <p>Here we are testing if this method retrieves the attribute ID of the sort key.</p>
	 * <p>The expected result is a not null return.</p>
	 */
	public void testGetAttributeID001() {

		SortKey x=new SortKey("test");
		assertEquals("test",x.getAttributeID());
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.SortKey.getAttributeID()'</p>
	 * <p>Here we are testing if this method retrieves the attribute ID of the sort key.</p>
	 * <p>The expected result is a not null return.</p>
	 */
	public void testGetAttributeID002() {

		SortKey x=new SortKey("");
		assertEquals("",x.getAttributeID());
	}

	
	/**
	 * <p>Test method for 'javax.naming.ldap.SortKey.isAscending()'</p>
	 * <p>Here we are testing if this method determines the sort order.</p>
	 * <p>The expected result is the given sort order.</p>
	 */
	public void testIsAscending001() {

		SortKey x=new SortKey("",true,"");
		assertTrue(x.isAscending());
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.SortKey.isAscending()'</p>
	 * <p>Here we are testing if this method determines the sort order.</p>
	 * <p>The expected result is the given sort order.</p>
	 */
	public void testIsAscending002() {

		SortKey x=new SortKey("",false,"");
		assertFalse(x.isAscending());
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.SortKey.isAscending()'</p>
	 * <p>Here we are testing if this method determines the sort order. In this case we do not give a sort order.</p>
	 * <p>The expected result is true.</p>
	 */
	public void testIsAscending003() {

		SortKey x=new SortKey("anything");
		assertTrue(x.isAscending());
	}

	
	/**
	 * <p>Test method for 'javax.naming.ldap.SortKey.getMatchingRuleID()'</p>
	 * <p>Here we are testing if this method retrieves the matching rule ID used to order the attribute values.</p>
	 * <p>The expected result is a null.</p> 
	 */
	public void testGetMatchingRuleID001() {

		SortKey x=new SortKey("anything");
		assertNull(x.getMatchingRuleID());
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.SortKey.getMatchingRuleID()'</p>
	 * <p>Here we are testing if this method retrieves the matching rule ID used to order the attribute values.</p>
	 * <p>The expected result is a blank Id.</p> 
	 */
	public void testGetMatchingRuleID002() {

		SortKey x=new SortKey("anything",false,"");
		assertEquals("",x.getMatchingRuleID());
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.SortKey.getMatchingRuleID()'</p>
	 * <p>Here we are testing if this method retrieves the matching rule ID used to order the attribute values.</p>
	 * <p>The expected result is a blank Id.</p> 
	 */
	public void testGetMatchingRuleID003() {

		SortKey x=new SortKey("anything",false,"anything");
		assertEquals("anything",x.getMatchingRuleID());
	}

}
