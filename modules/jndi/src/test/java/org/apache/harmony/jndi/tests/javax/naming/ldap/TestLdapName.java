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

import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;

import junit.framework.TestCase;

/**
 * 
 * <p>Test cases for all methods of the class LdapName.</p>
 * 
 * <p>The next two tables contains a list of the methods to be tested, with the return of each method.</p>
 *  <table class="t" cellspacing="0">
	<tbody><th>Constructors:</th>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="LdapName(List<Rdn> rdns)" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="LdapName(String name)" id="f10"></td>
			
		</tr>
	</tbody>
	<table>
	<tbody><th>Method Summary:</th>
		<tr><TD>Return</TD><TD>Method</TD></tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="Name" id="f00"></TD>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="add(int posn, Rdn comp)" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="Name" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="add(int posn, String comp)" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="Name" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="add(Rdn comp)" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="Name" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="add(String comp)" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="Name" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="addAll(int posn, List<Rdn> suffixRdns)" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="Name" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="addAll(int posn, Name suffix)" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="Name" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="addAll(List<Rdn> suffixRdns)" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="Name" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="addAll(Name suffix)" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="Object" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="clone()" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="int" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="compareTo(Object obj)" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="boolean" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="endsWith(List<Rdn> rdns)" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="boolean" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="endsWith(Name n)" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="boolean" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="equals(Object obj)" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="String" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="get(int posn)" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="Enumeration<String>" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="getAll()" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="Name" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="getPrefix(int posn)" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="Rdn" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="getRdn(int posn)" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="List<Rdn>" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="getRdns()" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="Name" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="getSuffix(int posn)" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="int" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="hashCode()" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="boolean" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="isEmpty()" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="Object" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="remove(int posn)" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="int" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="size()" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="boolean" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="startsWith(List<Rdn> rdns)" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="boolean" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="startsWith(Name n)" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="String" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="40" name="sas9nt21" readonly="readonly" value="toString()" id="f10"></td>
			
		</tr>
	</tbody>
	</table>
 *
 */
public class TestLdapName extends TestCase {

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
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method recive a null String.</p>
	 * <p>The expected result is a null pointer exception.</p>
	 */
	public void testLdapNameString001() {

		try{
			String test=null;
			LdapName ln = new LdapName(test);
			fail("Failed the null argument is invalid.");
		}catch(NullPointerException e){
			
		}catch(Throwable e){
			fail("Failed the null argument is NullPointerException.");
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method recive a non-null String,
	 * this string must be a valid string of the typevalue "CN=commonName".</p>
	 * <p>The expected result is an instance of LdapName.</p>
	 */
	public void testLdapNameString002() {

		try{
			String test="CN=test";
			assertNotNull(new LdapName(test));

		}catch(InvalidNameException e){
			fail("Failed the argument is invalid."+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method recive a non-null String,
	 * this string must be a valid string but here we are including an special character ",", this 
	 * character must work like a separetor.</p>
	 * <p>The expected result is an instance of LdapName.</p>
	 */
	public void testLdapNameString003() {

		try{
			String test="L=test,C=common";
			assertNotNull(new LdapName(test));

		}catch(InvalidNameException e){
			fail("Failed the argument is invalid."+e);
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive a non-null String,
	 * this string must be a valid string but here we are including an special character ";", this 
	 * character must work like a separetor.</p>
	 * <p>The expected result is an instance of LdapName.</p>
	 */
	public void testLdapNameString004() {

		try{
			String test="ST=test;CN=common";
			assertNotNull(new LdapName(test));

		}catch(InvalidNameException e){
			fail("Failed the argument is invalid."+e);
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive a non-null String,
	 * this string must be a valid string but here we are including an special character "+", this 
	 * character must work like between type/value.</p>
	 * <p>The expected result is an instance of LdapName.</p>
	 */
	public void testLdapNameString005() {

		try{
			String test="O=test+CM=common";
			assertNotNull(new LdapName(test));

		}catch(InvalidNameException e){
			fail("Failed the argument is invalid."+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive a non-null String,
	 * this string must be a valid string but here we are including an special character " ", this 
	 * character must work like a separetor between words.</p>
	 * <p>The expected result is an instance of LdapName.</p>
	 */
	public void testLdapNameString006() {

		try{
			String test="OU=test this";
			assertNotNull(new LdapName(test));

		}catch(InvalidNameException e){
			fail("Failed the argument is invalid."+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive a non-null String,
	 * this string must be a valid string but here we are including an special character "\", this 
	 * character must let us to introduce another special character in the name.</p>
	 * <p>The expected result is an instance of LdapName.</p>
	 */
	public void testLdapNameString007() {
    	try{
			String test="C=test\\, this";
			assertNotNull(new LdapName(test));
			
		}catch(InvalidNameException e){
			fail("Failed the argument is invalid."+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive a non-null String,
	 * this string must be a valid string but here we are including an special character "#", this 
	 * is an example name in which an RDN was of an unrecognized type. The value is the BER encoding 
	 * of an OCTET STRING containing two bytes 0x48 and 0x69.</p>
	 * <p>The expected result is an instance of LdapName.</p>
	 */
	public void testLdapNameString008() {

		try{
			String test="S=#04024869";
			assertNotNull(new LdapName(test));

		}catch(InvalidNameException e){
			fail("Failed the argument is invalid."+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive a non-null String,
	 * this string must be a valid string but here we are including a combination of specials characters
	 * like ",", "=", "+", "<", ">", "#" and ";"</p>
	 * <p>The expected result is an instance of LdapName.</p>
	 */
	public void testLdapNameString009() {

		try{
			String test="DC=test,T=time+CM=common V<this C>this,S=#04024869";
			assertNotNull(new LdapName(test));

		}catch(InvalidNameException e){
			fail("Failed the argument is invalid."+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive a non-null String,
	 * this string must be a valid string like SN=Lu\C4\8Di\C4\87.</p>
	 * <p>The expected result is an instance of LdapName.</p>
	 * <p>The String is an example of an RDN surname value consisting of 5 letters like:</p>
		<table class="t" cellspacing="0">
		<tbody>
			<tr><TD>Unicode Letter Description</TD><TD>10646 code</TD><Td>UTF-8</Td><TD>Quoted</TD></tr>
			<tr>
				<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="LATIN CAPITAL LETTER L" id="f00"></TD>
				<td class="c0" id="c10"><input class="a0" size="20" name="sas9nt21" readonly="readonly" value="U0000004C" id="f10"></td>
				<td class="c0" id="c10"><input class="a0" size="10" name="sas9nt21" readonly="readonly" value="0x4C" id="f10"></td>
				<td class="c0" id="c10"><input class="a0" size="10" name="sas9nt21" readonly="readonly" value="L" id="f10"></td>
			</tr>
			<tr>
				<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="LATIN SMALL LETTER U " id="f00"></TD>
				<td class="c0" id="c10"><input class="a0" size="20" name="sas9nt21" readonly="readonly" value="U00000075" id="f10"></td>
				<td class="c0" id="c10"><input class="a0" size="10" name="sas9nt21" readonly="readonly" value="0x75" id="f10"></td>
				<td class="c0" id="c10"><input class="a0" size="10" name="sas9nt21" readonly="readonly" value="u" id="f10"></td>
			</tr>
			<tr>
				<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="LATIN SMALL LETTER C WITH CARON" id="f00"></TD>
				<td class="c0" id="c10"><input class="a0" size="20" name="sas9nt21" readonly="readonly" value="U0000010D" id="f10"></td>
				<td class="c0" id="c10"><input class="a0" size="10" name="sas9nt21" readonly="readonly" value="0xC48D" id="f10"></td>
				<td class="c0" id="c10"><input class="a0" size="10" name="sas9nt21" readonly="readonly" value="\C4\8D" id="f10"></td>
			</tr>
			<tr>
				<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="LATIN SMALL LETTER I" id="f00"></TD>
				<td class="c0" id="c10"><input class="a0" size="20" name="sas9nt21" readonly="readonly" value="U00000069" id="f10"></td>
				<td class="c0" id="c10"><input class="a0" size="10" name="sas9nt21" readonly="readonly" value="0x69 " id="f10"></td>
				<td class="c0" id="c10"><input class="a0" size="10" name="sas9nt21" readonly="readonly" value="i" id="f10"></td>
			</tr>
			<tr>
				<td class="c0" id="c00"><input class="a0" size="40" name="sas9nt11" readonly="readonly" value="LATIN SMALL LETTER C WITH ACUTE" id="f00"></TD>
				<td class="c0" id="c10"><input class="a0" size="20" name="sas9nt21" readonly="readonly" value="U00000107" id="f10"></td>
				<td class="c0" id="c10"><input class="a0" size="10" name="sas9nt21" readonly="readonly" value="0xC487" id="f10"></td>
				<td class="c0" id="c10"><input class="a0" size="10" name="sas9nt21" readonly="readonly" value="\C4\87" id="f10"></td>
			</tr>
		</tbody>  
		</table>
	*/
	public void testLdapNameString010() {
	 
		try{
			String test="SN=Lu\\C4\\8Di\\C4\\87";
			assertNotNull(new LdapName(test));
			
		}catch(InvalidNameException e){
			fail("Failed the argument is invalid."+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive a non-null String,
	 * and the DNs must be case insensitive.</p>
	 * <p>The expected result is an assertion between two names one upper and other lower case.</p>
	 */
	public void testLdapNameString011() {

		try{
			String testUpperCase="UID=TEST";
			String testLowerCase="uid=test";
			assertTrue(new LdapName(testUpperCase).equals(new LdapName(testLowerCase)));

		}catch(InvalidNameException e){
			fail("Failed the argument is invalid."+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive a blank String.</p>
	 * <p>The expected result an empty name, not null, empty.</p>
	 */	
	public void testLdapNameString012() {

		try{
			LdapName ln = new LdapName("");
			assertTrue(ln.isEmpty());
		}catch(Throwable e){
			fail("Failed with: "+e); 
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String with correct
	 * format like "a=b", In this case we are gonna test the special characters "<" and ">".</p>
	 * <p>The expected result is an instance of ldapname.</p>
	 */
	public void testLdapNameString013() {

		try{
			assertNotNull(new LdapName("a=<"));
			assertNotNull(new LdapName("a=<a"));
			assertNotNull(new LdapName("a=a<"));
			assertNotNull(new LdapName("a=a<b"));
			assertNotNull(new LdapName("a=a<b<"));
			assertNotNull(new LdapName("a=>"));
			assertNotNull(new LdapName("a=>a"));
			assertNotNull(new LdapName("a=a>"));
			assertNotNull(new LdapName("a=a>b"));
			assertNotNull(new LdapName("a=a>b>"));
			
		}catch(InvalidNameException e){
			fail("Failed with:"+e);
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String with no correct
	 * format like "test".</p>
	 * <p>The expected result is an InvalidNameException.</p>
	 */
	public void testLdapNameString014() {

		try{
			String test="test";
			LdapName ln = new LdapName(test);
			fail("Failed the null argument is invalid.");
		}catch(InvalidNameException e){
			
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character ",".</p>
	 * <p>The expected result is an Exception.</p>
	 */	
	public void testLdapNameString015() {

		try{
			String test="t=test,control";
			LdapName ln = new LdapName(test);
			fail("Failed the argument is invalid.");
		}catch(InvalidNameException e){
			
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character ",".</p>
	 * <p>The expected result is an instance of the class.</p>
	 */	
	public void testLdapNameString016() {

		try{
			String test="t=test,";
			LdapName ln = new LdapName(test);
			
		}catch(InvalidNameException e){
			fail("Failed the argument is valid."+e);
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character ",".</p>
	 * <p>The expected result is an Exception.</p>
	 */	
	public void testLdapNameString017() {

		try{
			String test=",t=test";
			LdapName ln = new LdapName(test);
			fail("Failed the argument is invalid.");
		}catch(InvalidNameException e){
			
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character ",".</p>
	 * <p>The expected result is an Exception.</p>
	 */	
	public void testLdapNameString018() {

		try{
			String test=",t=test,";
			LdapName ln = new LdapName(test);
			fail("Failed the argument is invalid.");
		}catch(InvalidNameException e){
			
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character "=".</p>
	 * <p>The expected result is an Exception.</p>
	 */	
	public void testLdapNameString019() {

		try{
			String test="t=";
			LdapName ln = new LdapName(test);
			
		}catch(InvalidNameException e){
			fail("Failed the argument is valid."+e);
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character "=".</p>
	 * <p>The expected result is an Exception.</p>
	 */	
	public void testLdapNameString020() {

		try{
			String test="=t";
			LdapName ln = new LdapName(test);
			fail("Failed the argument is invalid.");
		}catch(InvalidNameException e){
			
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character "=".</p>
	 * <p>The expected result is an Exception.</p>
	 */	
	public void testLdapNameString021() {

		try{
			String test="=";
			LdapName ln = new LdapName(test);
			fail("Failed the argument is invalid.");
		}catch(InvalidNameException e){
			
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character "=".</p>
	 * <p>The expected result is an Exception.</p>
	 */	
	public void testLdapNameString022() {

		try{
			String test="=t=t";
			LdapName ln = new LdapName(test);
			fail("Failed the argument is invalid.");
		}catch(InvalidNameException e){
			
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character "=".</p>
	 * <p>The expected result is an instance of the class.</p>
	 */	
	public void testLdapNameString023() {

		try{
			String test="a=t=";
			LdapName ln = new LdapName(test);
			
		}catch(InvalidNameException e){
			fail("Failed the argument is valid."+e);
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character "=".</p>
	 * <p>The expected result is an Exception.</p>
	 */	
	public void testLdapNameString024() {

		try{
			String test="=a=t=";
			LdapName ln = new LdapName(test);
			fail("Failed the argument is invalid.");
		}catch(InvalidNameException e){
			
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character "=".</p>
	 * <p>The expected result is an instance of the class.</p>
	 */	
	public void testLdapNameString025() {

		try{
			String test="a=b=t=z";
			LdapName ln = new LdapName(test);
			
		}catch(InvalidNameException e){
			fail("Failed the argument is valid."+e);
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character ";".</p>
	 * <p>The expected result is an Exception.</p>
	 */	
	public void testLdapNameString026() {

		try{
			String test=";";
			LdapName ln = new LdapName(test);
			fail("Failed the argument is invalid.");
		}catch(InvalidNameException e){
			
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character ";".</p>
	 * <p>The expected result is an instance of the class.</p>
	 */	
	public void testLdapNameString027() {

		try{
			String test="a=b;";
			LdapName ln = new LdapName(test);
			
		}catch(InvalidNameException e){
			fail("Failed the argument is valid."+e);
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character ";".</p>
	 * <p>The expected result is an Exception.</p>
	 */	
	public void testLdapNameString028() {

		try{
			String test=";a=b";
			LdapName ln = new LdapName(test);
			fail("Failed the argument is invalid.");
		}catch(InvalidNameException e){
			
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character ";".</p>
	 * <p>The expected result is an Exception.</p>
	 */	
	public void testLdapNameString029() {

		try{
			String test="a=b;c";
			LdapName ln = new LdapName(test);
			fail("Failed the argument is invalid.");
		}catch(InvalidNameException e){
			
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character ";".</p>
	 * <p>The expected result is an Exception.</p>
	 */	
	public void testLdapNameString030() {

		try{
			String test=";a=b;";
			LdapName ln = new LdapName(test);
			fail("Failed the argument is invalid.");
		}catch(InvalidNameException e){
			
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character "#".</p>
	 * <p>The expected result is an instance of the class.</p>
	 */	
	public void testLdapNameString031() {

		try{
			String test="a=a#";
			LdapName ln = new LdapName(test);
			
		}catch(InvalidNameException e){
			fail("Failed the argument is valid."+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character "#".</p>
	 * <p>The expected result is an Exception.</p>
	 */	
	public void testLdapNameString032() {

		try{
			String test="a=#a";
			LdapName ln = new LdapName(test);
			fail("Failed the argument is invalid.");
		}catch(InvalidNameException e){
			
		}catch (IllegalArgumentException e){
			
		}
	}


	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character "#".</p>
	 * <p>The expected result is an Exception.</p>
	 */	
	public void testLdapNameString033() {

		try{
			String test="#a=a";
			LdapName ln = new LdapName(test);
			fail("Failed the argument is invalid.");
		}catch(InvalidNameException e){
			
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character "#".</p>
	 * <p>The expected result is an Exception.</p>
	 */	
	public void testLdapNameString034() {

		try{
			String test="#a=#a";
			LdapName ln = new LdapName(test);
			fail("Failed the argument is invalid.");
		}catch(InvalidNameException e){
			
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character "#".</p>
	 * <p>The expected result is an Exception.</p>
	 */	
	public void testLdapNameString035() {

		try{
			String test="#a=a#";
			LdapName ln = new LdapName(test);
			fail("Failed the argument is invalid.");
		}catch(InvalidNameException e){
			
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character "<".</p>
	 * <p>The expected result is an Exception.</p>
	 */	
	public void testLdapNameString036() {

		try{
			String test="a<a";
			LdapName ln = new LdapName(test);
			fail("Failed the argument is invalid.");
		}catch(InvalidNameException e){
			
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character "<".</p>
	 * <p>The expected result is an Exception.</p>
	 */	
	public void testLdapNameString037() {

		try{
			String test="<a=a";
			LdapName ln = new LdapName(test);
			fail("Failed the argument is invalid.");
		}catch(InvalidNameException e){
			
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character "<".</p>
	 * <p>The expected result is an Exception.</p>
	 */	
	public void testLdapNameString038() {

		try{
			String test="<a=a<";
			LdapName ln = new LdapName(test);
			fail("Failed the argument is invalid.");
		}catch(InvalidNameException e){
			
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character ">".</p>
	 * <p>The expected result is an Exception.</p>
	 */	
	public void testLdapNameString039() {

		try{
			String test="a>c";
			LdapName ln = new LdapName(test);
			fail("Failed the argument is invalid.");
		}catch(InvalidNameException e){
			
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character ">".</p>
	 * <p>The expected result is an Exception.</p>
	 */	
	public void testLdapNameString040() {

		try{
			String test=">a=c";
			LdapName ln = new LdapName(test);
			fail("Failed the argument is invalid.");
		}catch(InvalidNameException e){
			
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character ">".</p>
	 * <p>The expected result is an Exception.</p>
	 */	
	public void testLdapNameString041() {

		try{
			String test=">a=c>";
			LdapName ln = new LdapName(test);
			fail("Failed the argument is invalid.");
		}catch(InvalidNameException e){
			
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character "\".</p>
	 * <p>The expected result is an Exception.</p>
	 */	
	public void testLdapNameString042() {

		try{
			String test="a=b\\";
			LdapName ln = new LdapName(test);
			fail("Failed the argument is invalid.");
		}catch(InvalidNameException e){
			
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character "\".</p>
	 * <p>The expected result is an Exception.</p>
	 */	
	public void testLdapNameString043() {

		try{
			String test="\\a=b";
			LdapName ln = new LdapName(test);
			fail("Failed the argument is invalid.");
		}catch(InvalidNameException e){
			
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character "\".</p>
	 * <p>The expected result is an Exception.</p>
	 */	
	public void testLdapNameString044() {

		try{
			String test="\\a=b\\";
			LdapName ln = new LdapName(test);
			fail("Failed the argument is invalid.");
		}catch(InvalidNameException e){
			
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character "\".</p>
	 * <p>The expected result is an Exception.</p>
	 */	
	public void testLdapNameString045() {

		try{
			String test="a=b\\s";
			LdapName ln = new LdapName(test);
			fail("Failed the argument is invalid.");
		}catch(InvalidNameException e){
			
		}catch (IllegalArgumentException e){
			
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character "+".</p>
	 * <p>The expected result is an instance of the class.</p>
	 */	
	public void testLdapNameString046() {

		try{
			String test="a=b+";
			LdapName ln = new LdapName(test);
	
		}catch(InvalidNameException e){
			fail("Failed the argument is valid."+e);
			
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character "+".</p>
	 * <p>The expected result is an exception.</p>
	 */	
	public void testLdapNameString047() {

		try{
			String test="+a=b";
			LdapName ln = new LdapName(test);
			fail("Failed the argument is invalid.");	
		}catch(InvalidNameException e){

			
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character "+".</p>
	 * <p>The expected result is an exception.</p>
	 */	
	public void testLdapNameString048() {

		try{
			String test="+a=b+";
			LdapName ln = new LdapName(test);
			fail("Failed the argument is invalid.");	
		}catch(InvalidNameException e){

			
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character "+".</p>
	 * <p>The expected result is an exception.</p>
	 */	
	public void testLdapNameString049() {

		try{
			String test="a=b+s";
			LdapName ln = new LdapName(test);
			fail("Failed the argument is invalid.");	
		}catch(InvalidNameException e){

			
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character "+".</p>
	 * <p>The expected result is an instance of the class.</p>
	 */	
	public void testLdapNameString050() {

		try{
			String test="a=b+s=";
			LdapName ln = new LdapName(test);
				
		}catch(InvalidNameException e){
			fail("Failed the argument is invalid.");
			
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character " ".</p>
	 * <p>The expected result is an exception.</p>
	 */	
	public void testLdapNameString051() {

		try{
			String test=" =b";
			LdapName ln = new LdapName(test);
			fail("Failed the argument is invalid.");	
		}catch(InvalidNameException e){
			
			
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character " ".</p>
	 * <p>The expected result is an instance of the class.</p>
	 */	
	public void testLdapNameString052() {

		try{
			String test="a= ";
			LdapName ln = new LdapName(test);
		}catch(InvalidNameException e){
			fail("Failed the argument is valid."+e);	
			
		}
	}
    
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing if nothing is sended between ','.</p>
	 * <p>The expected result is an exception.</p>
	 */	
    public void testLdapNameString053() {
        try{
            LdapName ln=new LdapName("cn=pucho,,o=fitc");
            fail("Should raise InvalidnameException");
        }catch(InvalidNameException e){
        }
    }
    
    /**
	 * <p>Test method for 'javax.naming.ldap.LdapName(String)'</p>
	 * <p>Here we are testing the constructor method, this method should recive String notice here that here
	 * that we are testing the the special character "#".</p>
	 * <p>The expected result is an exception.</p>
	 */	
    public void testLdapNameString054() {
        try{
            LdapName ln=new LdapName("cn=pucho,o=#fitc");
            fail("Should raise InvalidnameException");
        }catch(InvalidNameException e){
        }
    }

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.LdapName(List<Rdn>)'</p>
	 * <p>Here we are testing the constructor method of LdapName reciving a list of valid names.</p>
	 * <p>The expected result is an instance of an object of LdapName.</p>
	 */
	public void testLdapNameListOfRdn001() {

		LinkedList<Rdn> test=new LinkedList<Rdn>();
		try {
			test.add(new Rdn("CN=commonName"));
			test.add(new Rdn("L=localityName"));
			test.add(new Rdn("ST=stateOrProvinceName"));
			test.add(new Rdn("O=organizationName"));
			test.add(new Rdn("OU=organizationalUnitName"));
		} catch (InvalidNameException e) {
			fail("This list is correct."+e);
		}
		assertNotNull(new LdapName(test));
		
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.LdapName(List<Rdn>)'</p>
	 * <p>Here we are testing the constructor method of LdapName reciving a null list.</p> 
	 * <p>The expected result is an exception of the type NullPointerException.</p>
	 */
	public void testLdapNameListOfRdn002() {

		LinkedList<Rdn> test=null;
		try{
			LdapName ln=new LdapName(test);
			fail("Failed, invalid arguments.");
		}catch(NullPointerException e){
		
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.LdapName(List<Rdn>)'</p>
	 * <p>Here we are testing the constructor method of LdapName reciving a non-null list but empty one.</p>
	 * <p>The expected result is an instance of LdapName.</p>
	 */
	public void testLdapNameListOfRdn003() {

		LinkedList<Rdn> test=new LinkedList<Rdn>();
		try{
			
			assertNotNull(new LdapName(test));
			
		}catch(Throwable e){
			fail("Failed with:"+e);
		}
		
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.LdapName(List<Rdn>)'</p>
	 * <p>Here we are testing the constructor method of LdapName reciving a list of valid names.</p>
	 * <p>The expected result is an instance of an object of LdapName, and also that the indexing is made like the other way around.</p>
	 */
	public void testLdapNameListOfRdn004() {

		LinkedList<Rdn> test=new LinkedList<Rdn>();
		try {
			test.add(new Rdn("CN=commonName"));
			test.add(new Rdn("L=localityName"));
			test.add(new Rdn("ST=stateOrProvinceName"));
			test.add(new Rdn("O=organizationName"));
			test.add(new Rdn("OU=organizationalUnitName"));
		} catch (InvalidNameException e) {
			fail("This list is correct."+e);
		}
		LdapName x=new LdapName(test);
		assertNotNull(x);
		List t=x.getRdns();
		int i=0;
		for (Iterator iter = test.iterator(); iter.hasNext();) {
			Rdn element = (Rdn) iter.next();
			assertEquals(element.toString(),t.get(i).toString());
			i++;
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.LdapName(List<Rdn>)'</p>
	 * <p>Here we are testing the constructor method of LdapName reciving a non-null list but here the list contains Rdns invalids.</p>
	 * <p>The expected result is an instance of LdapName.</p>
	 */
	public void testLdapNameListOfRdn005() {

		LinkedList<Rdn> test=new LinkedList<Rdn>();
		LdapName x=null;
		try {
			test.add(new Rdn("CN",new LinkedList()));
			test.add(new Rdn("L",new LinkedList()));
			test.add(new Rdn("ST",new LinkedList()));
			test.add(new Rdn("O",new LinkedList()));
			test.add(new Rdn("OU",new LinkedList()));
		} catch (InvalidNameException e) {
			fail("This list is correct."+e);
		}
		try{
			x=new LdapName(test);
			assertNotNull(x);
		}catch(Throwable e){
			fail("Failed with:"+e);
		}
		try{
			System.out.println(x.toString());
			fail("Failed, because the list of rdn was incorrect so a class cast exception must be thrown.");
		} catch (ClassCastException e) {
			
		}
		
		
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.hashCode()'</p>
	 * <p>Here we are testing the computed hash code of a blank String and the RDN in the LdapName.
	 * The String is a valid imput.</p>
	 * <p>The expected result is zero.</p>
	 */
	public void testHashCode001() {
		
		String test="";
		try {
			assertEquals(0 , new LdapName(test).hashCode());
			
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.hashCode()'</p>
	 * <p>Here we are testing the computed hash code of a list of RDN and the hashcode of all LdapName.
	 * The list is a valid imput.</p>
	 * <p>The expected result is an assertion between the hashcode of
	 * the all RDNs in the LdapName and the hash of the list, this method returns the sum of all Rdns hashcodes.</p>
	 */
	public void testHashCode002() {
				
		try {
			Rdn t=new Rdn("");
			LinkedList<Rdn> test=new LinkedList<Rdn>();
			test.add(t);
			LdapName ln=new LdapName(test);
			assertEquals(0,t.hashCode()&ln.hashCode());
			assertEquals(t.hashCode() , ln.hashCode()); 
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
			
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.hashCode()'</p>
	 * <p>Here we are testing the computed hash code of a list of RDN and the hashcode of all LdapName.
	 * The list is valid imput.</p>
	 * <p>The expected result is an assertion between the hashcode of
	 * the all RDNs in the LdapName and the list, this method returns the sum of all Rdns hashcodes.</p>
	 */
	public void testHashCode003() {
				
		try {
			Rdn rdn1=new Rdn("CN=commonName");
			Rdn rdn2=new Rdn("t=test");
			LinkedList<Rdn> test=new LinkedList<Rdn>();
			test.add(rdn1);
			test.add(rdn2);
			LdapName ln=new LdapName(test);
			assertEquals(rdn1.hashCode()+rdn2.hashCode() , ln.hashCode()); 
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
			
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.hashCode()'</p>
	 * <p>Here we are testing the computed hash code of a non blank String and the RDN in the LdapName.
	 * The String is a valid imput.</p>
	 * <p>The expected result is the equals hash of two objects.</p>
	 */
	public void testHashCode004() {
		
		String test="t=test,h=asd";
		try {
			LdapName x=new LdapName(test);
			LdapName y=new LdapName(test);
			assertNotSame(0,x.hashCode()&y.hashCode());
			assertEquals(x.hashCode(),y.hashCode());
			
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.equals(Object)'</p>
	 * <p>Here we are testing that two LDAP names are equal. If obj as a parameter is null
	 * false is returned.</p>
	 * <p>The expected result is a false.</p>
	 */
	public void testEquals001() {

		try {
			assertFalse(new LdapName("t=test").equals(null));
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.equals(Object)'</p>
	 * <p>Here we are testing that two LDAP names are equal. If obj as a parameter is not a LdapName
	 * Object, false is returned.</p>
	 * <p>The expected result is a false.</p> 
	 */
	public void testEquals002() {

		try {
			String test="t=test";
			assertFalse(new LdapName("t=test").equals(test));
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.equals(Object)'</p>
	 * <p>Here we are testing that two LDAP names are equal. If obj as a parameter is a LdapName
	 * Object, the expected result is the comparison between the two ldapnames.</p>
	 * <p>The expected result is a false, the name are not eqaul.</p>
	 */
	public void testEquals003() {

		try {
			LdapName ln=new LdapName("o=other");
			assertFalse(new LdapName("t=test").equals(ln));
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.equals(Object)'</p>
	 * <p>Here we are testing that two LDAP names are equal. If obj as a parameter is a LdapName
	 * Object and the same,true is returned.</p>
	 * <p>The expected result is true.</p>
	 */
	public void testEquals004() {

		try {
			
			assertTrue(new LdapName("t=test").equals(new LdapName("t=test")));
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.equals(Object)'</p>
	 * <p>Here we are testing that two LDAP names are equal. If obj as a parameter is a LdapName
	 * Object and the same,true is returned.</p>
	 * <p>In this case the comparison is made with one of the name in uppercase and the other with lowercase, the
	 * expected result still is true.</p>
	 */
	public void testEquals005() {

		try {
			
			assertTrue(new LdapName("t=test").equals(new LdapName("T=TEST")));
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.equals(Object)'</p>
	 * <p>Here we are testing that two LDAP names are equal. If obj as a parameter is a LdapName
	 * Object and the same,true is returned.</p>
	 * <p>In this case the comparison is made with one of the name in uppercase and the other with lowercase, the
	 * expected result still is true.</p>
	 */
	public void testEquals006() {

		try {
			
			assertTrue(new LdapName("t=test").equals(new LdapName("T=  TEST")));
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.clone()'</p>
	 * <p>Here we are testing if a clone of an object of LdapName is equal to the original.</p>
	 * <p>The expected result in this case is true.</p>
	 */
	public void testClone001() {
		
		LdapName ln;
		try {
			ln = new LdapName("t=test");
			LdapName copy=(LdapName) ln.clone();
			assertEquals(ln,copy);
			
		} catch (InvalidNameException e) {
			
		}
		
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.clone()'</p>
	 * <p>Here we are testing if this method generates a new copy of this name.</p>
	 * <p>The expected result in this case is if a change in primary object no affect the clone.</p>
	 */
	public void testClone002() {
		
		LdapName ln;
		try {
			ln = new LdapName("t=test");
			LdapName copy=(LdapName) ln.clone();
			ln.add("ch=change");
			assertNotSame(ln.toString(),copy.toString());
			
		} catch (InvalidNameException e) {
			fail("Failed with: "+e); 
		} catch (Throwable e) { 
			fail("Failed with: "+e); 
		
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.clone()'</p>
	 * <p>Here we are testing if this method generates a new copy of this name.</p>
	 * <p>The expected result in this case is if a change in the clone object no affect the primary.</p>
	 */
	public void testClone003() {
		
		LdapName ln;
		try {
			ln = new LdapName("t=test");
			LdapName copy=(LdapName) ln.clone();
			copy.add("ch=change");
			assertNotSame(ln.toString(),copy.toString());
		} catch (InvalidNameException e) {
			fail("Failed with: "+e); 
		} catch (Throwable e) { 
			fail("Failed with: "+e); 
		
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.clone()'</p>
	 * <p>Here we are testing if this method generates a new copy of this name.</p>
	 * <p>The expected result in this case is if clone of an empty object is equal to its primary.</p>
	 */
	public void testClone004() {
		
		LdapName ln;
		try {
			ln = new LdapName("");
			LdapName copy=(LdapName) ln.clone();
			assertEquals(ln,copy);
		} catch (Throwable e) { 
			fail("The name is correct, they are empty."+e);
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.toString()'</p>
	 * <p>Here we are testing if the method returns a string representation of this LDAP name in a format defined by RFC 2253.</p>
	 * <p>The expected results is a representation of this LDAP as we created it.</p>  
	 */
	public void testToString001() {
		
		try {
			LdapName ln=new LdapName("t=test");
			assertEquals("t=test",ln.toString());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.toString()'</p>
	 * <p>Here we are testing if the method returns a string representation of this LDAP name in a format defined by RFC 2253.</p>
	 * <p>The expected results is a representation of this LDAP as we created it, in this case the in the String are three names, the 
	 * ldapname should return the strings in the LIFO way.</p>  
	 */
	public void testToString002() {
		
		try {
			LinkedList<Rdn> test=new LinkedList<Rdn>();
			test.add(new Rdn("c1=common"));
			test.add(new Rdn("c2=common"));
			test.add(new Rdn("c3=common"));
			LdapName ln=new LdapName(test);
			String comp = "";
			for (Rdn rdn : test) {
				if (test.getFirst()==rdn) {
					comp=rdn.toString();
				} else {
					comp=rdn.toString()+","+comp;
				}
			}
			assertEquals(comp,ln.toString());
		} catch (InvalidNameException e) {
			
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.toString()'</p>
	 * <p>Here we are testing if the method returns a string representation of this LDAP name in a format defined by RFC 2253.</p>
	 * <p>The expected results is that the name returns a blank.</p>  
	 */
	public void testToString003() {
		
		try {
			LdapName ln=new LdapName("");
			assertEquals("",ln.toString());
		} catch (InvalidNameException e) {
			
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.toString()'</p>
	 * <p>Here we are testing if the method returns a string representation of this LDAP name in a format defined by RFC 2253.</p>
	 * <p>The expected results is that the name returns the string just like it recived.</p>  
	 */
	public void testToString004() {
		
		try {
			LdapName ln=new LdapName("t=\\4c\\4c");
			String temp="t=LL";
			assertEquals(0,temp.compareToIgnoreCase(ln.toString()));
		} catch (InvalidNameException e) {
			
		}
		
	}
		
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.toString()'</p>
	 * <p>Here we are testing if the method returns a string representation of this LDAP name in a format defined by RFC 2253.</p>
	 * <p>The expected results is that the name returns the string just like it recived.</p>  
	 */
	public void testToString005() {
		
		try {
			LdapName ln=new LdapName("t=#08");
			assertEquals("t=#08",ln.toString());
		} catch (InvalidNameException e) {
			
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.size()'</p>
	 * <p>Here we are testing if this method retrieves the number of components in this LDAP name.</p>
	 * <p>The expected result is zero because the name is empty.</p>
	 */
	public void testSize001() {
		
		try {
			LdapName ln=new LdapName("");
			assertEquals(0,ln.size());
		} catch (InvalidNameException e) {
			
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.size()'</p>
	 * <p>Here we are testing if this method retrieves the number of components in this LDAP name.</p>
	 * <p>The expected result is the correct number of Rdns.</p>
	 */
	public void testSize002() {
		
		try {
			String test=("CN=commonName,L=localityName,ST=stateOrProvinceName,O=organizationName,OU=organizationalUnitName,C=countryName,STREET=streetAddress,DC=domainComponent,UID=userid");
			LdapName ln=new LdapName(test);
			assertEquals(9,ln.size());
		} catch (InvalidNameException e) {
			
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.size()'</p>
	 * <p>Here we are testing if this method retrieves the number of components in this LDAP name.</p>
	 * <p>The expected result is the correct number of Rdns. In this case we are using distincts special 
	 * characters to create the name. Notice that the special character "+", associates attributes Types And Values.</p> 
	 */
	public void testSize003() {
		
		try {
			LdapName ln=new LdapName("t1=test+t2=test,t3=test;t4=test");
			assertEquals(3,ln.size());
		} catch (InvalidNameException e) {
			
		}
		
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.isEmpty()'</p>
	 * <p>Here we are testing if this method determines whether this LDAP name is empty.</p>
	 * <p>The expected result is a false.</p>
	 */
	public void testIsEmpty001() {
		
		try {
			assertFalse(new LdapName("t=test").isEmpty());
		} catch (InvalidNameException e) {
			
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.isEmpty()'</p>
	 * <p>Here we are testing if this method determines whether this LDAP name is empty.</p>
	 * <p>The expected result is a true.</p>
	 */
	public void testIsEmpty002() {
		
		try {
			LdapName ln=new LdapName("t=test");
			ln.remove(0);
			assertTrue(ln.isEmpty());
		} catch (InvalidNameException e) {
			
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.isEmpty()'</p>
	 * <p>Here we are testing if this method determines whether this LDAP name is empty.</p>
	 * <p>The expected result is a true.</p>
	 */
	public void testIsEmpty003() {
		
		try {
			LdapName ln=new LdapName("");
			assertTrue(ln.isEmpty());
		} catch (InvalidNameException e) {
			
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.getAll()'</p>
	 * <p>Here we are testing if this method retrieves the components of this name as an enumeration of strings.</p>
	 * <p>The expected result is if an empty name returns a non-null enumeration.</p>
	 */
	public void testGetAll001() {

		try {
			LdapName ln=new LdapName("");
			Enumeration<String> x=ln.getAll();
			assertNotNull(x);
						
		} catch (InvalidNameException e) {
			
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.getAll()'</p>
	 * <p>Here we are testing if this method retrieves the components of this name as an enumeration of strings.</p>
	 * <p>The expected result is if a non empty name returns a non-null enumeration, and ordered like it should be.</p>
	 */
	public void testGetAll002() {

		try {
			LdapName ln=new LdapName("t=test");
			Enumeration<String> e=ln.getAll();
			assertEquals("t=test",e.nextElement());
		} catch (InvalidNameException e) {
			
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.getAll()'</p>
	 * <p>Here we are testing if this method retrieves the components of this name as an enumeration of strings.</p>
	 * <p>The expected result is if a non empty name returns a non-null enumeration, and ordered like it should be.</p>
	 */
	public void testGetAll003() {

		LinkedList<Rdn> test= new LinkedList<Rdn>();
		try {
			
			Rdn a=new Rdn("cn","test");
			Rdn b=new Rdn("uid","test");
			Rdn c=new Rdn("l","test");
			Rdn d=new Rdn("st","test");
			test.add(0,a);
			test.add(1,b);
			test.add(2,c);
			test.add(3,d);
			LdapName ln=new LdapName(test);
			Enumeration<String> e=ln.getAll();
			for (Rdn rdn : test) {
				
				assertEquals(rdn.toString(),e.nextElement());
				
			}
			
		} catch (Throwable e){ 
			fail("Failed with:"+e);
		}

	}

	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.get(int)'</p>
	 * <p>Here we are testing if this method retrieves a component of this LDAP name as a string, notice that the index must
	 * be in the range [0,size()).</p>
	 * <p>The expected result is if the returned string by this method is the variable wich we create the Name.</p>
	 */
	public void testGet001() {
		
		String test="t=test";
		try {
			LdapName ln=new LdapName(test);
			assertEquals(test,ln.get(0));
		} catch (Throwable e) {
			fail("Failed with:"+e);
		} 

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.get(int)'</p>
	 * <p>Here we are testing if this method retrieves a component of this LDAP name as a string, notice that the index must
	 * be in the range [0,size()).</p>
	 * <p>The expected result is an index out of bounds exception.</p>
	 */
	public void testGet002() {
		
		String test="t=test";
		try {
			LdapName ln=new LdapName(test);
			String x=ln.get(1);
			fail("Fail, the index is not correct.");
		} catch (InvalidNameException e) {
			fail("Fail, the name is correct.");
		} catch (IndexOutOfBoundsException e) {
			
		}
		

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.get(int)'</p>
	 * <p>Here we are testing if this method retrieves a component of this LDAP name as a string, notice that the index must
	 * be in the range [0,size()).</p>
	 * <p>The expected result is an index out of bounds exception.</p>
	 */
	public void testGet003() {
		
		String test="";
		try {
			LdapName ln=new LdapName(test);
			String x=ln.get(0);
			fail("The name is empty.");
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		} catch (IndexOutOfBoundsException e) {
			
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.get(int)'</p>
	 * <p>Here we are testing if this method retrieves a component of this LDAP name as a string, notice that the index must
	 * be in the range [0,size()).</p>
	 * <p>The expected result is an index out of bounds exception.</p>
	 */
	public void testGet004() {
		
		String test="t=test";
		try {
			LdapName ln=new LdapName(test);
			String x=ln.get(-1);
			fail("Fail, the index is negative.");
		} catch (IndexOutOfBoundsException e){
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		} 
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.get(int)'</p>
	 * <p>Here we are testing if this method retrieves a component of this LDAP name as a string, notice that the index must
	 * be in the range [0,size()).</p>
	 * <p>The expected result is in this case the non null strings of the name that was created with two Rdn.</p>
	 */
	public void testGet005() {
		
		LinkedList<Rdn> test=new LinkedList<Rdn>();
		try {
			test.add(0,new Rdn("t=test"));
			test.add(1,new Rdn("t1=test"));
			LdapName ln=new LdapName(test);
			assertNotNull(ln.get(0));//the range of this name is 0-1
			assertNotNull(ln.get(1));
		} catch (Throwable e) {
			fail("Failed with:"+e);
		} 
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.get(int)'</p>
	 * <p>Here we are testing if this method retrieves a component of this LDAP name as a string, notice that the index must
	 * be in the range [0,size()).</p>
	 * <p>The expected result is an exception like indexoutofbounds.</p>
	 */
	public void testGet006() {
		
		LinkedList<Rdn> test=new LinkedList<Rdn>();
		LdapName ln=null;
		try {
			test.add(0,new Rdn("t=test"));
			test.add(1,new Rdn("t1=test"));
			ln=new LdapName(test);

		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}
		
		try {
			
			String x=ln.get(-1);//the range of this name is 0-1
			fail("Should raise an exception.");
		} catch (IndexOutOfBoundsException e) {
			
		} 
        try {
			
			String x=ln.get(2);//the range of this name is 0-1
			fail("Should raise an exception.");
		} catch (IndexOutOfBoundsException e) {
			
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.getRdn(int)'</p>
	 * <p>Here we are testing if this method retrieves an RDN of this LDAP name as an Rdn.</p>
	 * <p>The expected result is a non null Rdn.</p>
	 */
	public void testGetRdn001() {
		
		LinkedList<Rdn> test=new LinkedList<Rdn>();
		try {
			test.add(new Rdn("t=test"));
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}
		LdapName ln=new LdapName(test); 
		assertNotNull(ln.getRdn(0));
			
		
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.getRdn(int)'</p>
	 * <p>Here we are testing if this method retrieves an RDN of this LDAP name as an Rdn.</p>
	 * <p>The expected result is an exception like IndexOutOfBounds.</p>
	 */
	public void testGetRdn002() {
		
		try{
			LinkedList<Rdn> test=new LinkedList<Rdn>();
			test.add(new Rdn("t=test"));
			LdapName ln=new LdapName(test); 
			Rdn x=(Rdn) ln.getRdn(-1);
			fail("The index is negative.");
		} catch(IndexOutOfBoundsException e){
			
		} catch(InvalidNameException e){
			fail("Failed with:"+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.getRdn(int)'</p>
	 * <p>Here we are testing if this method retrieves an RDN of this LDAP name as an Rdn.</p>
	 * <p>The expected result is an exception like IndexOutOfBounds.</p>
	 */
	public void testGetRdn003() {
		
		try{
			LinkedList<Rdn> test=new LinkedList<Rdn>();
			test.add(new Rdn("t=test"));
			LdapName ln=new LdapName(test); 
			Rdn x=(Rdn) ln.getRdn(1);
			fail("The index is wrong.");
		} catch(IndexOutOfBoundsException e){
			
		} catch(InvalidNameException e){
			fail("Failed with:"+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.getRdn(int)'</p>
	 * <p>Here we are testing if this method retrieves an RDN of this LDAP name as an Rdn.</p>
	 * <p>The expected result is an exception like IndexOutOfBounds.</p>
	 */
	public void testGetRdn004() {
		
		try{
			LdapName ln=new LdapName(""); 
			Rdn x=(Rdn) ln.getRdn(0);
			fail("The index is wrong, the name is empty.");
		} catch(IndexOutOfBoundsException e){
			
		} catch(InvalidNameException e){
			fail("Failed with:"+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.getRdn(int)'</p>
	 * <p>Here we are testing if this method retrieves an RDN of this LDAP name as an Rdn.</p>
	 * <p>The expected result is the rdn in the correct order.</p>
	 */
	public void testGetRdn005() {
		
		try{
			LdapName ln=new LdapName("o=other,t=test,uid=userid");
			assertEquals("uid=userid",ln.getRdn(0).toString());
			assertEquals("t=test",ln.getRdn(1).toString());
			assertEquals("o=other",ln.getRdn(2).toString());
		
		} catch(Throwable e){
			fail("Failed with:"+e);
		}
	}
	
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.getPrefix(int)'</p>
	 * <p>Here we are testing if this method creates a name whose components consist of a prefix of the components of this LDAP name.</p>
	 * <p>The expected result is in the position zero an empty name and in the position one another name like "t=test".</p> 
	 */
	public void testGetPrefix001() {
		
		String test="t=test";
		try {
			LdapName ln=new LdapName(test);
			assertEquals("",ln.getPrefix(0).toString());
			assertEquals("t=test",ln.getPrefix(1).toString());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		} 

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.getPrefix(int)'</p>
	 * <p>Here we are testing if this method creates a name whose components consist of a prefix of the components of this LDAP name.</p>
	 * <p>The expected result is if it suffer any chance it should not affect the original name.</p>
	 */
	public void testGetPrefix002() {
		
		String test="t=test";
		try {
			LdapName ln=new LdapName(test);
			LdapName ln2=null;
			ln2=(LdapName) ln.getPrefix(1);
			ln2.remove(1);
			assertNotSame(ln.toString(),ln2.toString());
		} catch (InvalidNameException e) {
			
		} catch (IndexOutOfBoundsException e) {
			
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.getPrefix(int)'</p>
	 * <p>Here we are testing if this method creates a name whose components consist of a prefix of the components of this LDAP name.</p>
	 * <p>The expected result is an IndexOutOfBaundsException if the range is not in [0,size()].</p>
	 */
	public void testGetPrefix003() {
		
		String test="t=test";
		try {
			LdapName ln=new LdapName(test);
			LdapName ln2=null;
			ln2=(LdapName) ln.getPrefix(-1);
			fail("The index is not correct.");
			
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		} catch (IndexOutOfBoundsException e) {
			
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.getPrefix(int)'</p>
	 * <p>Here we are testing if this method creates a name whose components consist of a prefix of the components of this LDAP name.</p>
	 * <p>The expected result is an IndexOutOfBaundsException if the range is not in [0,size()].</p>
	 */
	public void testGetPrefix004() {
		
		String test="t=test";
		try {
			LdapName ln=new LdapName(test);
			LdapName ln2=null;
			ln2=(LdapName) ln.getPrefix(2);
			fail("The index is not correct.");
			
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		} catch (IndexOutOfBoundsException e) {
			
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.getPrefix(int)'</p>
	 * <p>Here we are testing if this method creates a name whose components consist of a prefix of the components of this LDAP name.</p>
	 * <p>The expected result is if the method recives a correct index like the size(), the expected result is the complete prefix name.</p>
	 */
	public void testGetPrefix005() {
		
		String test="t=test,t1=test,t2=test";
		try {
			LdapName ln=new LdapName(test);
			assertEquals(test,ln.getPrefix(ln.size()).toString());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		} 

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.getPrefix(int)'</p>
	 * <p>Here we are testing if this method creates a name whose components consist of a prefix of the components of this LDAP name.</p>
	 * <p>The expected result is if the method recives a correct index like the size(), the expected result is the complete prefix name, in 
	 * this case blank.</p>
	 */
	public void testGetPrefix006() {
		
		String test="";
		try {
			LdapName ln=new LdapName(test);
			assertEquals(test,ln.getPrefix(ln.size()).toString());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		} 

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.getSuffix(int)'</p>
	 * <p>Here we are testing if this method creates a name whose components consist of a suffix of the components in this LDAP name.</p>
	 * <p>The expected result is a not null name.</p>
	 */
	public void testGetSuffix001() {
		
		String test="t=test";
		try {
			LdapName ln=new LdapName(test);
			assertNotNull(ln.getSuffix(0));
			assertEquals("t=test",ln.getSuffix(0).toString());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		} 

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.getSuffix(int)'</p>
	 * <p>Here we are testing if this method creates a name whose components consist of a suffix of the components in this LDAP name.</p>
	 * <p>The expected result is a not null name but empty.</p>
	 */
	public void testGetSuffix002() {
		
		String test="t=test";
		try {
			LdapName ln=new LdapName(test);
			assertTrue(ln.getSuffix(1).isEmpty());
		} catch (InvalidNameException e) {
			
		} catch (IndexOutOfBoundsException e) {
			
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.getSuffix(int)'</p>
	 * <p>Here we are testing if this method creates a name whose components consist of a suffix of the components in this LDAP name.</p>
	 * <p>The expected result is the suffix with the correct index.</p>
	 */
	public void testGetSuffix003() {
		
		try {
			String test="t1=test,t2=test,t3=test";
			LdapName ln=new LdapName(test);
			assertEquals("t1=test,t2=test",ln.getSuffix(1).toString());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		} 

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.getSuffix(int)'</p>
	 * <p>Here we are testing if this method creates a name whose components consist of a suffix of the components in this LDAP name.</p>
	 * <p>The expected result is an exception like IndexOutOfBounds.</p>
	 */
	public void testGetSuffix004() {
		
		String test="t=test";
		try {
			LdapName ln=new LdapName(test);
			LdapName ln2=null;
			ln2=(LdapName) ln.getSuffix(-1);
			fail("The index is not correct.");
			
		} catch (InvalidNameException e) {
			fail("Failed With:"+e);
		} catch (IndexOutOfBoundsException e) {
			
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.getSuffix(int)'</p>
	 * <p>Here we are testing if this method creates a name whose components consist of a suffix of the components in this LDAP name.</p>
	 * <p>The expected result is an exception like IndexOutOfBounds.</p>
	 */
	public void testGetSuffix005() {
		
		String test="t=test";
		try {
			LdapName ln=new LdapName(test);
			LdapName ln2=null;
			ln2=(LdapName) ln.getSuffix(2);
			fail("The index is not correct.");
			
		} catch (InvalidNameException e) {
			fail("Failed With:"+e);
		} catch (IndexOutOfBoundsException e) {
			
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.getSuffix(int)'</p>
	 * <p>Here we are testing if this method creates a name whose components consist of a suffix of the components in this LDAP name.</p>
	 * <p>The expected result is the complete suffix with the correct index.</p>
	 */
	public void testGetSuffix006() {
		
		try {
			String test="t1=test,t2=test,t3=test";
			LdapName ln=new LdapName(test);
			assertEquals(test,ln.getSuffix(0).toString());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		} 

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.getSuffix(int)'</p>
	 * <p>Here we are testing if this method creates a name whose components consist of a suffix of the components in this LDAP name.</p>
	 * <p>The expected result is the suffix with the correct index.</p>
	 */
	public void testGetSuffix007() {
		
		try {
			String test="t1=test,t2=test,t3=test";
			LdapName ln=new LdapName(test);
			assertEquals("t1=test",ln.getSuffix(2).toString());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		} 

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.getSuffix(int)'</p>
	 * <p>Here we are testing if this method creates a name whose components consist of a suffix of the components in this LDAP name.</p>
	 * <p>The expected result is a not null name but empty.</p>
	 */
	public void testGetSuffix008() {
		
		String test="";
		try {
			LdapName ln=new LdapName(test);
			assertNotNull(ln.getSuffix(0));
			assertTrue(ln.getSuffix(0).isEmpty());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		} 

	}

	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.startsWith(Name)'</p>
	 * <p>Here we are testing if this method determines whether this LDAP name starts with a specified LDAP name prefix.</p>
	 * <p>The expected result is a false.</p>
	 */
	public void testStartsWithName001() {
		
		String test="t=test";
		try {
			LdapName ln=new LdapName(test);
			LdapName t=null;
			assertFalse(ln.startsWith(t));
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.startsWith(Name)'</p>
	 * <p>Here we are testing if this method determines whether this LDAP name starts with a specified LDAP name prefix.</p>
	 * <p>The expected result is a true.</p>
	 */
	public void testStartsWithName002() {
		
		String test="t=test,cn=test";
		try {
			LdapName ln=new LdapName(test);
			LdapName n=new LdapName("cn=test");
			assertTrue(ln.startsWith(n));
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.startsWith(Name)'</p>
	 * <p>Here we are testing if this method determines whether this LDAP name starts with a specified LDAP name prefix.</p>
	 * <p>The expected result is a false.</p>
	 */
	public void testStartsWithName003() {
		
		String test="t=test,cn=test";
		try {
			LdapName ln=new LdapName(test);
			LdapName n=new LdapName("t=test");
			assertFalse(ln.startsWith(n));
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.startsWith(Name)'</p>
	 * <p>Here we are testing if this method determines whether this LDAP name starts with a specified LDAP name prefix.</p>
	 * <p>The expected result is a false.</p>
	 */
	public void testStartsWithName004() {
		
		String test="t=test,cn=test,o=other";
		try {
			LdapName ln=new LdapName(test);
			LdapName n=(LdapName) ln.getPrefix(ln.size());
			assertTrue(ln.startsWith(n));
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.startsWith(Name)'</p>
	 * <p>Here we are testing if this method determines whether this LDAP name starts with a specified LDAP name prefix.</p>
	 * <p>The expected result is a false.</p>
	 */
	public void testStartsWithName005() {
		
		String test="t=test,cn=test";
		try {
			LdapName ln=new LdapName("");
			LdapName n=new LdapName("t=test");
			assertFalse(ln.startsWith(n));
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.startsWith(Name)'</p>
	 * <p>Here we are testing if this method determines whether this LDAP name starts with a specified LDAP name prefix.</p>
	 * <p>The expected result is a false.</p>
	 */
	public void testStartsWithName006() {
		
		try {
			LdapName ln=new LdapName("");
			LdapName n=new LdapName("");
			assertTrue(ln.startsWith(n));
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.startsWith(Name)'</p>
	 * <p>Here we are testing if this method determines whether this LDAP name starts with a specified LDAP name prefix.</p>
	 * <p>The expected result is a false.</p>
	 */
	public void testStartsWithName007() {
		
		try {
			LdapName ln=new LdapName("t=test");
			LdapName n=new LdapName("");
			assertTrue(ln.startsWith(n));
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.startsWith(List<Rdn>)'</p>
	 * <p>Here we are testing if this method determines whether the specified RDN sequence forms a prefix of this LDAP name.</p>
	 * <p>The expected result is false.</p>
	 */
	public void testStartsWithListOfRdn001() {
		
		LinkedList<Rdn> test=null;
		try {
			assertFalse(new LdapName("t=test").startsWith(test));
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.startsWith(List<Rdn>)'</p>
	 * <p>Here we are testing if this method determines whether the specified RDN sequence forms a prefix of this LDAP name.</p>
	 * <p>The expected result is true.</p>
	 */
	public void testStartsWithListOfRdn002() {
		
		LinkedList<Rdn> test=new LinkedList<Rdn>();
		
		try {
			Rdn a=new Rdn("t=test");
			test.add(a);
			assertTrue(new LdapName("t=test").startsWith(test));
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.startsWith(List<Rdn>)'</p>
	 * <p>Here we are testing if this method determines whether the specified RDN sequence forms a prefix of this LDAP name.</p>
	 * <p>The expected result is true.</p>
	 */
	public void testStartsWithListOfRdn003() {
		
		LinkedList<Rdn> test=new LinkedList<Rdn>();
		try {
			assertTrue(new LdapName("t=test").startsWith(test));
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.startsWith(List<Rdn>)'</p>
	 * <p>Here we are testing if this method determines whether the specified RDN sequence forms a prefix of this LDAP name.</p>
	 * <p>The expected result is false.</p>
	 */
	public void testStartsWithListOfRdn004() {
		
		LinkedList<Rdn> test=new LinkedList<Rdn>();
		try {
			test.add(new Rdn("t=test"));
			assertFalse(new LdapName("").startsWith(test));
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.startsWith(List<Rdn>)'</p>
	 * <p>Here we are testing if this method determines whether the specified RDN sequence forms a prefix of this LDAP name.</p>
	 * <p>The expected result is true.</p>
	 */
	public void testStartsWithListOfRdn005() {
		
		LinkedList<Rdn> test=new LinkedList<Rdn>();
		try {
			assertTrue(new LdapName("").startsWith(test));
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.startsWith(List<Rdn>)'</p>
	 * <p>Here we are testing if this method determines whether the specified RDN sequence forms a prefix of this LDAP name.</p>
	 * <p>The expected result is true.</p>
	 */
	public void testStartsWithListOfRdn006() {
		
		LinkedList<Rdn> test=new LinkedList<Rdn>();
		try {
			test.add(new Rdn("t=test"));
			test.add(new Rdn("t1=test"));
			test.add(new Rdn("t2=test"));
			test.add(new Rdn("t3=test"));
			assertTrue(new LdapName("t3=test,t2=test,t1=test,t=test").startsWith(test));
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}

	}

	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.endsWith(Name)'</p>
	 * <p>Here we are testing if this method determines whether this LDAP name ends with a specified LDAP name suffix.</p>
	 * <p>The expected result is a false.</p>
	 */
	public void testEndsWithName001() {
		
		String test="t=test";
		try {
			LdapName ln=new LdapName(test);
			LdapName t=null;
			assertFalse(ln.endsWith(t));
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.endsWith(Name)'</p>
	 * <p>Here we are testing if this method determines whether this LDAP name ends with a specified LDAP name suffix.</p>
	 * <p>The expected result is a true.</p>
	 */
	public void testEndsWithName002() {
		
		String test="t=test,cn=test";
		try {
			LdapName ln=new LdapName(test);
			LdapName n=new LdapName("t=test");
			assertTrue(ln.endsWith(n));
		} catch (InvalidNameException e) {
			
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.endsWith(Name)'</p>
	 * <p>Here we are testing if this method determines whether this LDAP name ends with a specified LDAP name suffix.</p>
	 * <p>The expected result is a true.</p>
	 */
	public void testEndsWithName003() {
		
		String test="t=test,cn=test";
		try {
			LdapName ln=new LdapName(test);
			LdapName n=new LdapName(test);
			assertTrue(ln.endsWith(n));
		} catch (InvalidNameException e) {
			
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.endsWith(Name)'</p>
	 * <p>Here we are testing if this method determines whether this LDAP name ends with a specified LDAP name suffix.</p>
	 * <p>The expected result is a true.</p>
	 */
	public void testEndsWithName004() {
		
		try {
			LdapName ln=new LdapName("");
			LdapName n=new LdapName("");
			assertTrue(ln.endsWith(n));
		} catch (InvalidNameException e) {
			
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.endsWith(Name)'</p>
	 * <p>Here we are testing if this method determines whether this LDAP name ends with a specified LDAP name suffix.</p>
	 * <p>The expected result is a true.</p>
	 */
	public void testEndsWithName005() {
		
		try {
			LdapName ln=new LdapName("t=test");
			LdapName n=new LdapName("");
			assertTrue(ln.endsWith(n));
		} catch (InvalidNameException e) {
			
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.endsWith(Name)'</p>
	 * <p>Here we are testing if this method determines whether this LDAP name ends with a specified LDAP name suffix.</p>
	 * <p>The expected result is a false.</p>
	 */
	public void testEndsWithName006() {
		
		try {
			LdapName ln=new LdapName("");
			LdapName n=new LdapName("t=test");
			assertFalse(ln.endsWith(n));
		} catch (InvalidNameException e) {
			
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.endsWith(Name)'</p>
	 * <p>Here we are testing if this method determines whether this LDAP name ends with a specified LDAP name suffix.</p>
	 * <p>The expected result is a false.</p>
	 */
	public void testEndsWithName007() {
		
		String test="t=test,cn=test";
		String test2="cn=test,t=test";
		try {
			LdapName ln=new LdapName(test);
			LdapName n=new LdapName(test2);
			assertFalse(ln.endsWith(n));
		} catch (InvalidNameException e) {
			
		}
	}

	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.endsWith(List<Rdn>)'</p>
	 * <p>Here we are testing if this method determines whether the specified RDN sequence forms a suffix of this LDAP name.</p>
	 * <p>The expected result is if a null list of Rdns is sended, is a false.</p>
	 */
	public void testEndsWithListOfRdn001() {
		
		LinkedList<Rdn> test=null;
		try {
			assertFalse(new LdapName("t=test").endsWith(test));
		} catch (InvalidNameException e) {
			
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.endsWith(List<Rdn>)'</p>
	 * <p>Here we are testing if this method determines whether the specified RDN sequence forms a suffix of this LDAP name.</p>
	 * <p>The expected result is if a non null list of Rdns is sended, is a true.</p>
	 */
	public void testEndsWithListOfRdn002() {
		
		LinkedList<Rdn> test=new LinkedList<Rdn>();
		try {
			test.add(new Rdn("t","test"));
			assertTrue(new LdapName("t=test").endsWith(test));
		} catch (InvalidNameException e) {
			
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.endsWith(List<Rdn>)'</p>
	 * <p>Here we are testing if this method determines whether the specified RDN sequence forms a suffix of this LDAP name.</p>
	 * <p>The expected result is if a non null list of Rdns is sended, is a true.</p>
	 */
	public void testEndsWithListOfRdn003() {
		
		LinkedList<Rdn> test=new LinkedList<Rdn>();
		try {
			assertTrue(new LdapName("").endsWith(test));
		} catch (InvalidNameException e) {
			
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.endsWith(List<Rdn>)'</p>
	 * <p>Here we are testing if this method determines whether the specified RDN sequence forms a suffix of this LDAP name.</p>
	 * <p>The expected result is if a non null list of Rdns is sended, is a false.</p>
	 */
	public void testEndsWithListOfRdn004() {
		
		LinkedList<Rdn> test=new LinkedList<Rdn>();
		try {
			test.add(new Rdn("t=test"));
			assertFalse(new LdapName("").endsWith(test));
		} catch (InvalidNameException e) {
			
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.endsWith(List<Rdn>)'</p>
	 * <p>Here we are testing if this method determines whether the specified RDN sequence forms a suffix of this LDAP name.</p>
	 * <p>The expected result is if a non null list of Rdns is sended, is a true.</p>
	 */
	public void testEndsWithListOfRdn005() {
		
		LinkedList<Rdn> test=new LinkedList<Rdn>();
		try {
			assertTrue(new LdapName("t=test").endsWith(test));
		} catch (InvalidNameException e) {
			
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.endsWith(List<Rdn>)'</p>
	 * <p>Here we are testing if this method determines whether the specified RDN sequence forms a suffix of this LDAP name.</p>
	 * <p>The expected result is if a non null list of Rdns is sended, is a true.</p>
	 */
	public void testEndsWithListOfRdn006() {
		
		LinkedList<Rdn> test=new LinkedList<Rdn>();
		try {
			test.add(new Rdn("t","test"));
			test.add(new Rdn("t2","test"));
			test.add(new Rdn("t3","test"));
			assertTrue(new LdapName("t3=test,t2=test,t=test").endsWith(test));
		} catch (InvalidNameException e) {
			
		}

	}

	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.addAll(Name)'</p>
	 * <p>Here we are testing if this method adds the components of a name -- in order -- to the end of this name.</p>
	 * <p>The expected result is if a null name is sended to add an NullPointerException is thrown.</p>
	 */
	public void testAddAllName001() {
		
		try {
			LdapName ln=new LdapName("t=test");
			LdapName toadd=null;
			ln.addAll(toadd);
			fail("The name to be added is a null name.");
		} catch (InvalidNameException e) {
			fail("The name is correct."+e);
		} catch (NullPointerException e) {
			
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.addAll(Name)'</p>
	 * <p>Here we are testing if this method adds the components of a name -- in order -- to the end of this name.</p>
	 * <p>The expected result is if a non null name is sended to add, it must be in order.</p>
	 */
	public void testAddAllName002() {
		
		try {
			LdapName ln=new LdapName("t=test");
			LdapName ln2=(LdapName) ln;
			LdapName toadd=new LdapName("cn=common,uid=userid");
			assertEquals(ln,ln2.addAll(toadd));
		} catch (Throwable e) {
			fail("Failed with:"+e);
		} 
		
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.addAll(Name)'</p>
	 * <p>Here we are testing if this method adds the components of a name -- in order -- to the end of this name.</p>
	 * <p>The expected result is if a non null name is sended to add, it must be in order.</p>
	 */
	public void testAddAllName003() {
		
		try {
			LdapName ln=new LdapName("t=test");
			ln.addAll(new LdapName("uid=userid"));
			assertEquals("uid=userid,t=test",ln.toString());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		} 
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.addAll(Name)'</p>
	 * <p>Here we are testing if this method adds the components of a name -- in order -- to the end of this name.</p>
	 * <p>The expected result is if a non null name is sended to add, it must be in order.</p>
	 */
	public void testAddAllName004() {
		
		try {
			LdapName ln=new LdapName("t=test");
			ln.addAll(new LdapName("cn=common,uid=userid"));
			assertEquals("cn=common,uid=userid,t=test",ln.toString());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		} 
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.addAll(Name)'</p>
	 * <p>Here we are testing if this method adds the components of a name -- in order -- to the end of this name.</p>
	 * <p>The expected result is if a non null name is sended to add, it must be in order.</p>
	 */
	public void testAddAllName005() {
		
		try {
			LdapName ln=new LdapName("");
			ln.addAll(new LdapName("cn=common,uid=userid"));
			assertEquals("cn=common,uid=userid",ln.toString());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		} 
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.addAll(Name)'</p>
	 * <p>Here we are testing if this method adds the components of a name -- in order -- to the end of this name.</p>
	 * <p>The expected result is if a non null name is sended to add, it must be in order.</p>
	 */
	public void testAddAllName006() {
		
		try {
			LdapName ln=new LdapName("");
			ln.addAll(new LdapName(""));
			assertEquals("",ln.toString());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		} 
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.addAll(Name)'</p>
	 * <p>Here we are testing if this method adds the components of a name -- in order -- to the end of this name.</p>
	 * <p>The expected result is if a non null name is sended to add, it must be in order.</p>
	 */
	public void testAddAllName007() {
		
		try {
			LdapName ln=new LdapName("cn=common,uid=userid");
			ln.addAll(new LdapName(""));
			assertEquals("cn=common,uid=userid",ln.toString());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		} 
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.addAll(List<Rdn>)'</p>
	 * <p>Here we are testing if this method adds the RDNs of a name -- in order -- to the end of this name.</p>
	 * <p>The expected result is  NullPointerException.</p>
	 */
	public void testAddAllListOfRdn001() {
		
		LinkedList<Rdn> test=null;
		try {
			LdapName ln=new LdapName("t=test");
			ln.addAll(test);
			fail("Should not add a null list");
		} catch (InvalidNameException e) {
			fail("The name is correct.");
		} catch (NullPointerException e) {
			
		}
		
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.addAll(List<Rdn>)'</p>
	 * <p>Here we are testing if this method adds the RDNs of a name -- in order -- to the end of this name.</p>
	 * <p>The expected result is the adding in the especified order.</p>
	 */
	public void testAddAllListOfRdn002() {
			LinkedList<Rdn> test=new LinkedList<Rdn>();
			try {
				LdapName ln=new LdapName("");
				ln.addAll(test);
				assertEquals("",ln.toString());
			} catch (InvalidNameException e) {
				fail("The name is ok."+e);
			}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.addAll(List<Rdn>)'</p>
	 * <p>Here we are testing if this method adds the RDNs of a name -- in order -- to the end of this name.</p>
	 * <p>The expected result is the adding in the especified order.</p>
	 */
	public void testAddAllListOfRdn003() {
			LinkedList<Rdn> test=new LinkedList<Rdn>();
			try {
				LdapName ln=new LdapName("t=test");
				ln.addAll(test);
				assertEquals("t=test",ln.toString());
			} catch (InvalidNameException e) {
				fail("The name is ok."+e);
			}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.addAll(List<Rdn>)'</p>
	 * <p>Here we are testing if this method adds the RDNs of a name -- in order -- to the end of this name.</p>
	 * <p>The expected result is the adding in the especified order.</p>
	 */
	public void testAddAllListOfRdn004() {
			LinkedList<Rdn> test=new LinkedList<Rdn>();
			try {
				test.add(new Rdn("t=test"));
				LdapName ln=new LdapName("");
				ln.addAll(test);
				assertEquals("t=test",ln.toString());
			} catch (InvalidNameException e) {
				fail("The name is ok."+e);
			}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.addAll(List<Rdn>)'</p>
	 * <p>Here we are testing if this method adds the RDNs of a name -- in order -- to the end of this name.</p>
	 * <p>The expected result is the adding in the especified order.</p>
	 */
	public void testAddAllListOfRdn005() {
			LinkedList<Rdn> test=new LinkedList<Rdn>();
			try {
				test.add(new Rdn("t=test"));
				test.add(new Rdn("t2=test"));
				LdapName ln=new LdapName("t3=test");
				ln.addAll(test);
				assertEquals("t2=test,t=test,t3=test",ln.toString());
			} catch (InvalidNameException e) {
				fail("The name is ok."+e);
			}
	}

	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.addAll(int, Name)'</p>
	 * <p>Here we are testing if the method adds the components of a name -- in order -- at a specified position within this name. 
	 * <p>The expected result is an IndexOutOfBounds Exception.</p>
	 */
	public void testAddAllIntName001() {
		
		try {
			LdapName ln = new LdapName("t=test");
			ln.addAll(2,new LdapName("uid=userid"));
			fail("This is an invalid index.");
		} catch (InvalidNameException e) {
			fail("The name is correct."+e);
		} catch (IndexOutOfBoundsException e) {
			
		}
		

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.addAll(int, Name)'</p>
	 * <p>Here we are testing if the method adds the components of a name -- in order -- at a specified position within this name. 
	 * <p>The expected result is an IndexOutOfBounds Exception.</p>
	 */
	public void testAddAllIntName002() {
		
		try {
			LdapName ln = new LdapName("t=test");
			ln.addAll(-1,new LdapName("uid=userid"));
			fail("This is an invalid index.");
		} catch (InvalidNameException e) {
			fail("The name is correct."+e);
		} catch (IndexOutOfBoundsException e) {
			
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.addAll(int, Name)'</p>
	 * <p>Here we are testing if the method adds the components of a name -- in order -- at a specified position within this name. 
	 * <p>The expected result is the adding of the name in the especified order.</p>
	 */
	public void testAddAllIntName003() {
		
		try {
			LdapName ln = new LdapName("t=test");
			ln.addAll(1,new LdapName("uid=userid"));
			assertEquals("uid=userid,t=test",ln.toString());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		} 	

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.addAll(int, Name)'</p>
	 * <p>Here we are testing if the method adds the components of a name -- in order -- at a specified position within this name. 
	 * <p>The expected result is an NullPointer Exception.</p>
	 */
	public void testAddAllIntName004() {
		
		try {
			LdapName ln = new LdapName("t=test");
			LdapName add=null;
			ln.addAll(1,add);
			fail("The name added is a null name.");
		} catch (InvalidNameException e) {
			fail("The name is correct."+e);
		} catch (IndexOutOfBoundsException e) {
			fail("The index is correct."+e);
		} catch (NullPointerException e){
			
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.addAll(int, Name)'</p>
	 * <p>Here we are testing if the method adds the components of a name -- in order -- at a specified position within this name. 
	 * <p>The expected result is the adding of the name in the especified order.</p>
	 */
	public void testAddAllIntName005() {
		
		try {
			LdapName ln = new LdapName("t=test");
			ln.addAll(0,new LdapName("uid=userid"));
			assertEquals("t=test,uid=userid",ln.toString());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		} 
		
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.addAll(int, List<Rdn>)'</p>
	 * <p>Here we are testing if this method adds the RDNs of a name -- in order -- at a specified position within this name.</p>
	 * <p>The expected result is a NullPointerException.</p>
	 */
	public void testAddAllIntListOfRdn001() {
		
		LinkedList<Rdn> test=null;
		try{
			LdapName ln=new LdapName("t=test");
			ln.addAll(0,test);
			fail("The null list should not be added.");
		} catch(NullPointerException e) {
			
		} catch(InvalidNameException e) {
			fail("The name is correct.");
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.addAll(int, List<Rdn>)'</p>
	 * <p>Here we are testing if this method adds the RDNs of a name -- in order -- at a specified position within this name.</p>
	 * <p>The expected result is an IndexOutOfBounds Exception.</p>
	 */
	public void testAddAllIntListOfRdn002() {
		
		LinkedList<Rdn> test=new LinkedList<Rdn>();
		try{
			test.add(new Rdn("t=test"));
			test.add(new Rdn("cn=common"));
			LdapName ln=new LdapName("t=test");
			ln.addAll(-1,test);
			fail("The index is an invalid one.");
		} catch(InvalidNameException e) {
			fail("The name is correct.");
		} catch (IndexOutOfBoundsException e) {
			
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.addAll(int, List<Rdn>)'</p>
	 * <p>Here we are testing if this method adds the RDNs of a name -- in order -- at a specified position within this name.</p>
	 * <p>The expected result is an IndexOutOfBounds Exception.</p>
	 */
	public void testAddAllIntListOfRdn003() {
		
		LinkedList<Rdn> test=new LinkedList<Rdn>();
		try{
			test.add(new Rdn("t=test"));
			test.add(new Rdn("cn=common"));
			LdapName ln=new LdapName("t=test");
			ln.addAll(2,test);
			fail("The index is an invalid one.");
		} catch(InvalidNameException e) {
			fail("The name is correct.");
		} catch (IndexOutOfBoundsException e) {
			
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.addAll(int, List<Rdn>)'</p>
	 * <p>Here we are testing if this method adds the RDNs of a name -- in order -- at a specified position within this name.</p>
	 * <p>The expected result is the adding of the list in the correct order.</p>
	 */
	public void testAddAllIntListOfRdn004() {
		
		LinkedList<Rdn> test=new LinkedList<Rdn>();
		try{
			LdapName ln=new LdapName("");
			ln.addAll(0,test);
			assertEquals("",ln.toString());
		} catch(InvalidNameException e) {
			fail("The name is correct.");
		} catch (IndexOutOfBoundsException e) {
			fail("The index is a valid one.");
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.addAll(int, List<Rdn>)'</p>
	 * <p>Here we are testing if this method adds the RDNs of a name -- in order -- at a specified position within this name.</p>
	 * <p>The expected result is the adding of the list in the correct order.</p>
	 */
	public void testAddAllIntListOfRdn005() {
		
		LinkedList<Rdn> test=new LinkedList<Rdn>();
		try{
			test.add(new Rdn("t=test"));
			LdapName ln=new LdapName("");
			ln.addAll(0,test);
			assertEquals("t=test",ln.toString());
		} catch(InvalidNameException e) {
			fail("The name is correct.");
		} catch (IndexOutOfBoundsException e) {
			fail("The index is a valid one.");
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.addAll(int, List<Rdn>)'</p>
	 * <p>Here we are testing if this method adds the RDNs of a name -- in order -- at a specified position within this name.</p>
	 * <p>The expected result is the adding of the list in the correct order.</p>
	 */
	public void testAddAllIntListOfRdn006() {
		
		LinkedList<Rdn> test=new LinkedList<Rdn>();
		try{
			LdapName ln=new LdapName("t=test");
			ln.addAll(0,test);
			assertEquals("t=test",ln.toString());
		} catch(InvalidNameException e) {
			fail("The name is correct.");
		} catch (IndexOutOfBoundsException e) {
			fail("The index is a valid one.");
		}

	}

	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.add(String)'</p>
	 * <p>Here we are testing if this method adds a single component to the end of this LDAP name.</p>
	 * <p>The expected result is  null pointer exception.</p> 
	 */
	public void testAddString001() {
		
		try {
			LdapName ln = new LdapName("t=test");
			String x=null;
			ln.add(x);
			fail("The name added has an invalid format.");
		} catch (InvalidNameException e) {
			fail("The name is correct."+e);
		} catch (NullPointerException e) {
			
		}
		

	}


	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.add(String)'</p>
	 * <p>Here we are testing if this method adds a single component to the end of this LDAP name.</p>
	 * <p>The expected result is the adding of the single component.</p> 
	 */
	public void testAddString002() {
		
		try {
			LdapName ln = new LdapName("t=test");
			String x="";
			ln.add(x);
			assertEquals(",t=test",ln.toString());
		} catch (InvalidNameException e) {
			fail("The name added has a valid format.");
		} 
		

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.add(String)'</p>
	 * <p>Here we are testing if this method adds a single component to the end of this LDAP name.</p>
	 * <p>The expected result is the adding of the single component.</p> 
	 */
	public void testAddString003() {
		
		try {
			LdapName ln = new LdapName("t=test");
			String x="cn=common";
			ln.add(x);
			assertEquals("cn=common,t=test",ln.toString());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		} 
		

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.add(String)'</p>
	 * <p>Here we are testing if this method adds a single component to the end of this LDAP name.</p>
	 * <p>The expected result is the adding of the single component.</p> 
	 */
	public void testAddString004() {
		
		try {
			LdapName ln = new LdapName("t=test");
			String x="cn=common";
			assertSame(ln,ln.add(x));
		} catch (Throwable e) {
			fail("Failed with:"+e);
		} 
		

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.add(String)'</p>
	 * <p>Here we are testing if this method adds a single component to the end of this LDAP name.</p>
	 * <p>The expected result is the adding of the single component.</p> 
	 */
	public void testAddString005() {
		
		try {
			LdapName ln = new LdapName("");
			String x="";
			ln.add(x);
			assertEquals("",ln.toString());
		} catch (InvalidNameException e) {
			fail("The name added has a valid format.");
		} 
		

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.add(String)'</p>
	 * <p>Here we are testing if this method adds a single component to the end of this LDAP name.</p>
	 * <p>The expected result is the adding of the single component.</p> 
	 */
	public void testAddString006() {
		
		try {
			LdapName ln = new LdapName("");
			String x="t=test";
			ln.add(x);
			assertEquals("t=test",ln.toString());
		} catch (InvalidNameException e) {
			fail("The name added has a valid format.");
		} 
		

	}

	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.add(Rdn)'</p>
	 * <p>Here we are testing if this method adds a single RDN to the end of this LDAP name.</p>
	 * <p>The expected result is the adding in the correct order.</p>
	 */
	public void testAddRdn001() {

		try {
			LdapName ln = new LdapName("t=test");
			Rdn toadd = new Rdn("cn=common");
			assertNotNull(ln.add(toadd));
		} catch (InvalidNameException e) {
			fail("The name is correct.");
		} 
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.add(Rdn)'</p>
	 * <p>Here we are testing if this method adds a single RDN to the end of this LDAP name.</p>
	 * <p>The expected result is the adding in the correct order.</p>
	 */
	public void testAddRdn002() {

		try {
			LdapName ln = new LdapName("t=test");
			Rdn toadd = new Rdn("cn=common");
			assertEquals("cn=common,t=test",ln.add(toadd).toString());
		} catch (Throwable e) { 
			fail("Failed with: "+e); 
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.add(Rdn)'</p>
	 * <p>Here we are testing if this method adds a single RDN to the end of this LDAP name.</p>
	 * <p>The expected result is a nullpointer exception.</p>
	 */
	public void testAddRdn003() {

		try {
			LdapName ln = new LdapName("t=test");
			Rdn toadd = null;
			ln.add(toadd);
			fail("The Rdn is null.");
		} catch (InvalidNameException e) {
			fail("The nameis correct."+e);
		} catch (NullPointerException e) {
			
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.add(Rdn)'</p>
	 * <p>Here we are testing if this method adds a single RDN to the end of this LDAP name.</p>
	 * <p>The expected result is the adding in the correct order.</p>
	 */
	public void testAddRdn004() {

		try {
			LdapName ln = new LdapName("");
			Rdn toadd = new Rdn("");
			ln.add(toadd);
			assertEquals("",ln.toString());
		} catch (InvalidNameException e) {
			fail("The nameis correct."+e);
		} catch (NullPointerException e) {
			fail("The Rdn is not null");
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.add(Rdn)'</p>
	 * <p>Here we are testing if this method adds a single RDN to the end of this LDAP name.</p>
	 * <p>The expected result is the adding in the correct order.</p>
	 */
	public void testAddRdn005() {

		try {
			LdapName ln = new LdapName("t=test");
			Rdn toadd = new Rdn("");
			ln.add(toadd);
			assertEquals(",t=test",ln.toString());
		} catch (InvalidNameException e) {
			fail("The nameis correct."+e);
		} catch (NullPointerException e) {
			fail("The Rdn is not null");
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.add(Rdn)'</p>
	 * <p>Here we are testing if this method adds a single RDN to the end of this LDAP name.</p>
	 * <p>The expected result is the adding in the correct order.</p>
	 */
	public void testAddRdn006() {

		try {
			LdapName ln = new LdapName("");
			Rdn toadd = new Rdn("t=test");
			ln.add(toadd);
			assertEquals("t=test",ln.toString());
		} catch (InvalidNameException e) {
			fail("The nameis correct."+e);
		} catch (NullPointerException e) {
			fail("The Rdn is not null");
		}
	}

	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.add(int, String)'</p>
	 * <p>Here we are testing if this method adds a single component at a specified position within this LDAP name.</p>
	 * <p>The expected result is an index out boundary exception.</p>
	 */
	public void testAddIntString001() {

		try {
			LdapName ln = new LdapName("t=test");
			ln.add(-1,"cn=common");
			fail("The index is not valid.");
		} catch (InvalidNameException e) {
			fail("The name is correct."+e);
		} catch (IndexOutOfBoundsException e) {
			
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.add(int, String)'</p>
	 * <p>Here we are testing if this method adds a single component at a specified position within this LDAP name.</p>
	 * <p>The expected result is an index out boundary exception.</p>
	 */
	public void testAddIntString002() {

		try {
			LdapName ln = new LdapName("t=test");
			ln.add(2,"cn=common");
			fail("The index is not valid.");
		} catch (InvalidNameException e) {
			fail("The name is correct."+e);
		} catch (IndexOutOfBoundsException e) {
			
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.add(int, String)'</p>
	 * <p>Here we are testing if this method adds a single component at a specified position within this LDAP name.</p>
	 * <p>The expected result is a null pointer exception.</p>
	 */
	public void testAddIntString003() {

		try {
			String toadd=null;
			LdapName ln = new LdapName("t=test");
			ln.add(1,toadd);
			fail("The name is a null string.");
		} catch (InvalidNameException e) {
			fail("The exception should be a null pointer exception."+e);
		} catch (NullPointerException e){
			
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.add(int, String)'</p>
	 * <p>Here we are testing if this method adds a single component at a specified position within this LDAP name.</p>
	 * <p>The expected result is the adding in the correct order.</p>
	 */
	public void testAddIntString004() {

		try {
			String toadd="cn=common";
			LdapName ln = new LdapName("t=test");
			ln.add(1,toadd);
			assertEquals("cn=common,t=test",ln.toString());
		} catch (Throwable e) { 
			fail("Failed with: "+e); 
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.add(int, String)'</p>
	 * <p>Here we are testing if this method adds a single component at a specified position within this LDAP name.</p>
	 * <p>The expected result is the adding in the correct order.</p>
	 */
	public void testAddIntString005() {

		try {
			String toadd="cn=common";
			LdapName ln = new LdapName("t=test");
			assertSame(ln,ln.add(1,toadd));
		} catch (Throwable e) { 
			fail("Failed with: "+e); 
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.add(int, String)'</p>
	 * <p>Here we are testing if this method adds a single component at a specified position within this LDAP name.</p>
	 * <p>The expected result is the adding in the correct order.</p>
	 */
	public void testAddIntString006() {

		try {
			String toadd="";
			LdapName ln = new LdapName("");
			ln.add(0,toadd);
			assertEquals("",ln.toString());
		} catch (Throwable e) { 
			fail("Failed with: "+e); 
				
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.add(int, String)'</p>
	 * <p>Here we are testing if this method adds a single component at a specified position within this LDAP name.</p>
	 * <p>The expected result is the adding in the correct order.</p>
	 */
	public void testAddIntString007() {

		try {
			String toadd="";
			LdapName ln = new LdapName("t=test");
			ln.add(1,toadd);
			assertEquals(",t=test",ln.toString());
		} catch (Throwable e) { 
			fail("Failed with: "+e); 
				
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.add(int, String)'</p>
	 * <p>Here we are testing if this method adds a single component at a specified position within this LDAP name.</p>
	 * <p>The expected result is the adding in the correct order.</p>
	 */
	public void testAddIntString008() {

		try {
			String toadd="t=test";
			LdapName ln = new LdapName("");
			ln.add(0,toadd);
			assertEquals("t=test",ln.toString());
		} catch (Throwable e) { 
			fail("Failed with: "+e); 
					
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.add(int, Rdn)'</p>
	 * <p>Here we are testing if this method adds a single RDN at a specified position within this LDAP name.</p>
	 * <p>The expected result is an index out boundary exception.</p>
	 */
	public void testAddIntRdn001() {
		
		try {
			LdapName ln = new LdapName("t=test");
			Rdn toadd = new Rdn("cn=common");
			ln.add(-1,toadd);
			fail("The index is wrong.");
		} catch (IndexOutOfBoundsException e) {
			
		} catch (InvalidNameException e) {
			fail("The name is correct.");
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.add(int, Rdn)'</p>
	 * <p>Here we are testing if this method adds a single RDN at a specified position within this LDAP name.</p>
	 * <p>The expected result is an index out boundary exception.</p>
	 */
	public void testAddIntRdn002() {
		
		try {
			LdapName ln = new LdapName("t=test");
			Rdn toadd = new Rdn("cn=common");
			ln.add(3,toadd);
			fail("The index is wrong.");
		} catch (IndexOutOfBoundsException e) {
			
		} catch (InvalidNameException e) {
			fail("The name is correct.");
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.add(int, Rdn)'</p>
	 * <p>Here we are testing if this method adds a single RDN at a specified position within this LDAP name.</p>
	 * <p>The expected result is a null pointer exception.</p>
	 */
	public void testAddIntRdn003() {
		
		try {
			LdapName ln = new LdapName("t=test");
			Rdn toadd = null;
			ln.add(1,toadd);
			fail("The name is an invalid one.");
		} catch (IndexOutOfBoundsException e) {
			fail("The index is correct.");
		} catch (InvalidNameException e) {
			fail("The name is correct.");
		} catch (NullPointerException e) {
			
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.add(int, Rdn)'</p>
	 * <p>Here we are testing if this method adds a single RDN at a specified position within this LDAP name.</p>
	 * <p>The expected result is the adding in the correct order.</p>
	 */
	public void testAddIntRdn004() {
		
		try {
			LdapName ln = new LdapName("t=test");
			Rdn toadd = new Rdn("cn=common");
			ln.add(1,toadd);
			assertEquals("cn=common,t=test",ln.toString());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.add(int, Rdn)'</p>
	 * <p>Here we are testing if this method adds a single RDN at a specified position within this LDAP name.</p>
	 * <p>The expected result is the adding in the correct order.</p>
	 */
	public void testAddIntRdn005() {
		
		try {
			LdapName ln = new LdapName("t=test");
			Rdn toadd = new Rdn("cn=common");
			ln.add(0,toadd);
			assertEquals("t=test,cn=common",ln.toString());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.add(int, Rdn)'</p>
	 * <p>Here we are testing if this method adds a single RDN at a specified position within this LDAP name.</p>
	 * <p>The expected result is the adding in the correct order.</p>
	 */
	public void testAddIntRdn006() {
		
		try {
			LdapName ln = new LdapName("");
			Rdn toadd = new Rdn("");
			ln.add(0,toadd);
			assertEquals("",ln.toString());
		} catch (Throwable e) { 
			fail("Failed with: "+e); 
		}
		

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.add(int, Rdn)'</p>
	 * <p>Here we are testing if this method adds a single RDN at a specified position within this LDAP name.</p>
	 * <p>The expected result is the adding in the correct order.</p>
	 */
	public void testAddIntRdn007() {
		
		try {
			LdapName ln = new LdapName("");
			Rdn toadd = new Rdn("t=test");
			assertEquals("t=test",ln.add(0,toadd).toString());
		} catch (Throwable e) { 
			fail("Failed with: "+e); 
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.add(int, Rdn)'</p>
	 * <p>Here we are testing if this method adds a single RDN at a specified position within this LDAP name.</p>
	 * <p>The expected result is the adding in the correct order.</p>
	 */
	public void testAddIntRdn008() {
		
		try {
			LdapName ln = new LdapName("t=test");
			Rdn toadd = new Rdn("");
			assertEquals(",t=test",ln.add(1,toadd).toString());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}

	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.remove(int)'</p>
	 * <p>Here we are testing if this method removes a component from this LDAP name.</p>
	 * <p>The expected result is an index out of boundary exception.</p>
	 */
	public void testRemove001() {
		
		try {
			LdapName ln = new LdapName("t=test");
			ln.remove(-1);
			fail("The index is wrong.");
		} catch (IndexOutOfBoundsException e) {
			
		} catch (InvalidNameException e) {
			fail("The name is correct.");
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.remove(int)'</p>
	 * <p>Here we are testing if this method removes a component from this LDAP name.</p>
	 * <p>The expected result is an index out of boundary exception.</p>
	 */
	public void testRemove002() {
		
		try {
			LdapName ln = new LdapName("t=test");
			ln.remove(3);
			fail("The index is wrong.");
		} catch (IndexOutOfBoundsException e) {
			
		} catch (InvalidNameException e) {
			fail("The name is correct.");
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.remove(int)'</p>
	 * <p>Here we are testing if this method removes a component from this LDAP name.</p>
	 * <p>The expected result is that the name remove the name in the given order.</p>
	 */
	public void testRemove003() {
		
		try {
			Rdn x=new Rdn("t=test");
			LdapName ln = new LdapName("t=test");
			assertEquals(x.toString(),ln.remove(0).toString());
		} catch (Throwable e) { 
			fail("Failed with: "+e); 
				
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.remove(int)'</p>
	 * <p>Here we are testing if this method removes a component from this LDAP name.</p>
	 * <p>The expected result is that the name remove the name in the given order.</p>
	 */
	public void testRemove004() {
		
		try {
			LdapName ln = new LdapName("t=test");
			ln.remove(0);
			assertEquals("",ln.toString());
		} catch (Throwable e) { 
				fail("Failed with: "+e); 
					
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.remove(int)'</p>
	 * <p>Here we are testing if this method removes a component from this LDAP name.</p>
	 * <p>The expected result is an index out of boundary exception.</p>
	 */
	public void testRemove005() {
		
		try {
			LdapName ln = new LdapName("");
			ln.remove(0);
			fail("The index is wrong.");
		} catch (IndexOutOfBoundsException e) {
			
		} catch (InvalidNameException e) {
			fail("The name is correct.");
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.getRdns()'</p>
	 * <p>Here we are testing if this method retrieves the list of relative distinguished names.</p>
	 * <p>The expected result is the list of rdns.</p>
	 */
	public void testGetRdns001() {
		
		try {
			LdapName ln = new LdapName("");
			List<Rdn> empty=(List<Rdn>) ln.getRdns();
			assertTrue(empty.isEmpty());
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.getRdns()'</p>
	 * <p>Here we are testing if this method retrieves the list of relative distinguished names.</p>
	 * <p>The expected result is the list of rdns.</p>
	 */
	public void testGetRdns002() {
		
		try {
			LdapName ln = new LdapName("uid=userid,t=test,cn=common");
			LinkedList<Rdn> compare=new LinkedList<Rdn>();
			compare.add(0,new Rdn("cn=common"));
			compare.add(1,new Rdn("t=test"));
			compare.add(2,new Rdn("uid=userid"));
			LinkedList<Rdn> notempty=new LinkedList<Rdn>(ln.getRdns());
			if(compare.size()==notempty.size()){
				for (int j=0;j<notempty.size();j++) {
					assertEquals(compare.get(j),notempty.get(j));
				}
			}else{
				fail("The lists has not an equal size.");
			}
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.compareTo(Object)'</p>
	 * <p>Here we are testing if this method compares this LdapName with the specified Object for order.</p>
	 * <p>The expected result is a classcastException.</p>
	 */
	public void testCompareTo001() {
		
		try {
			LdapName ln = new LdapName("t=test,cn=common");
			Rdn tocomp=null;
			int i;
			i = ln.compareTo(tocomp);
			fail("The string is null.");
		} catch (ClassCastException e) {
			
		} catch (Throwable e) { 
			fail("Failed with: "+e); 
					
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.compareTo(Object)'</p>
	 * <p>Here we are testing if this method compares this LdapName with the specified Object for order.</p>
	 * <p>The expected result is a positive int.</p>
	 */
	public void testCompareTo002() {
		
		try {
			LdapName ln = new LdapName("cn=common,t=test");
			LdapName ln2 = new LdapName("t=test");
			if(ln.compareTo(ln2)<= 0)fail("It should be a positive int");
						
		} catch (Throwable e) { 
			fail("Failed with: "+e); 
		
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.compareTo(Object)'</p>
	 * <p>Here we are testing if this method compares this LdapName with the specified Object for order.</p>
	 * <p>The expected result is a zero.</p>
	 */
	public void testCompareTo003() {
		
		try {
			LdapName ln = new LdapName("t=test,cn=common");
			LdapName ln2 = new LdapName("t=test,cn=common");
			if(ln.compareTo(ln2)!= 0)fail("It should be zero");
			
		} catch (Throwable e) { 
			fail("Failed with: "+e); 
				
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.compareTo(Object)'</p>
	 * <p>Here we are testing if this method compares this LdapName with the specified Object for order.</p>
	 * <p>The expected result is a negative int.</p>
	 */
	public void testCompareTo004() {
		
		try {
			LdapName ln = new LdapName("t=test,cn=common");
			LdapName ln2 = new LdapName("t=test,cn=common,uid=userid");
			if(ln.compareTo(ln2)>= 0)fail("It should be minor to zero");
			
		} catch (Throwable e) { 
			fail("Failed with: "+e); 
				
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.compareTo(Object)'</p>
	 * <p>Here we are testing if this method compares this LdapName with the specified Object for order.</p>
	 * <p>The expected result is a positive int.</p>
	 */
	public void testCompareTo005() {
		
		try {
			LdapName ln = new LdapName("t=test,cn=common");
			LdapName ln2 = new LdapName("");
			if(ln.compareTo(ln2)<= 0)fail("It should be mayor to zero");
			
			
		} catch (Throwable e) { 
			fail("Failed with: "+e); 
				
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.compareTo(Object)'</p>
	 * <p>Here we are testing if this method compares this LdapName with the specified Object for order.</p>
	 * <p>The expected result is a negative int.</p>
	 */
	public void testCompareTo006() {
		
		try {
			LdapName ln = new LdapName("");
			LdapName ln2 = new LdapName("t=test,cn=common");
			if(ln.compareTo(ln2)>= 0)fail("It should be minor to zero");
						
		} catch (Throwable e) { 
			fail("Failed with: "+e); 
				
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.compareTo(Object)'</p>
	 * <p>Here we are testing if this method compares this LdapName with the specified Object for order.</p>
	 * <p>The expected result is a zero.</p>
	 */
	public void testCompareTo007() {
		
		try {
			LdapName ln = new LdapName("");
			LdapName ln2 = new LdapName("");
			if(ln.compareTo(ln2)!= 0)fail("It should be minor to zero");
			
		} catch (Throwable e) { 
			fail("Failed with: "+e); 
				
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.compareTo(Object)'</p>
	 * <p>Here we are testing if this method compares this LdapName with the specified Object for order.</p>
	 * <p>The expected result is a negative int.</p>
	 */
	public void testCompareTo008() {
		
		try {
			LdapName ln = new LdapName("t=test,cn=common");
			LdapName ln2 = new LdapName("cn=common,t=test");
			if(ln.compareTo(ln2)>= 0)fail("It should not be zero");
			
		} catch (Throwable e) { 
			fail("Failed with: "+e); 
				
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.compareTo(Object)'</p>
	 * <p>Here we are testing if this method compares this LdapName with the specified Object for order.</p>
	 * <p>The expected result is a negative int.</p>
	 */
	public void testCompareTo009() {
		
		try {
			LdapName ln = new LdapName("t=test,cn=common");
			LdapName ln2 = new LdapName("t=test");
			if(ln.compareTo(ln2)>= 0)fail("It should be a negative int");
			
		} catch (Throwable e) { 
			fail("Failed with: "+e); 
		
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.compareTo(Object)'</p>
	 * <p>Here we are testing if this method compares this LdapName with the specified Object for order.</p>
	 * <p>The expected result is a negative int.</p>
	 */
	public void testCompareTo010() {
		
		try {
			LdapName ln = new LdapName("t=test1,cn=common");
			LdapName ln2 = new LdapName("t=test,cn=common1");
			if(ln.compareTo(ln2)>= 0)fail("It should be a negative int");
						
		} catch (Throwable e) { 
			fail("Failed with: "+e); 
		
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.compareTo(Object)'</p>
	 * <p>Here we are testing if this method compares this LdapName with the specified Object for order.</p>
	 * <p>The expected result is a positive int.</p>
	 */
	public void testCompareTo011() {
		
		try {
			LdapName ln = new LdapName("t=test1");
			LdapName ln2 = new LdapName("t=tes1t");
			if(ln.compareTo(ln2)<= 0)fail("It should be a positive int");
			
		} catch (Throwable e) { 
			fail("Failed with: "+e); 
		
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.compareTo(Object)'</p>
	 * <p>Here we are testing if this method compares this LdapName with the specified Object for order.</p>
	 * <p>The expected result is a zero.</p>
	 */
	public void testCompareTo012() {
		
		try {
			LdapName ln = new LdapName("T=teSt1");
			LdapName ln2 = new LdapName("t=test1");
			if(ln.compareTo(ln2)!= 0)fail("It should be a positive int");
			
		} catch (Throwable e) { 
			fail("Failed with: "+e); 
		
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.compareTo(Object)'</p>
	 * <p>Here we are testing if this method compares this LdapName with the specified Object for order.</p>
	 * <p>The expected result is a zero.</p>
	 */
	public void testCompareTo013() {
		
		try {
			LdapName ln = new LdapName("T= teSt1");
			LdapName ln2 = new LdapName("t=test1 ");
			if(ln.compareTo(ln2)!= 0)fail("It should be a positive int");
			
		} catch (Throwable e) { 
			fail("Failed with: "+e); 
		
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.compareTo(Object)'</p>
	 * <p>Here we are testing if this method compares this LdapName with the specified Object for order.</p>
	 * <p>The expected result is a zero.</p>
	 */
	public void testCompareTo014() {
		
		try {
			LdapName ln = new LdapName("T= teSt1,d=   here,h =THAT");
			LdapName ln2 = new LdapName("t=teSt1,d=here,h=that");
			if(ln.compareTo(ln2)!= 0)fail("It should be a positive int");
			
		} catch (Throwable e) { 
			fail("Failed with: "+e); 
		
		}

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.LdapName.compareTo(Object)'</p>
	 * <p>Here we are testing if this method compares this LdapName with the specified Object for order.</p>
	 * <p>The expected result is a zero.</p>
	 */
	public void testCompareTo015() {
		
		try {
			LdapName ln = new LdapName("T= teSt1+f   =  anything,d=   here; j=uos<asd,h =THAT,");
			LdapName ln2 = new LdapName("t=test1+f=anything,d=here;j=uos<asd,h=that,");
			if(ln.compareTo(ln2)!= 0)fail("It should be a positive int");
			
		} catch (Throwable e) { 
			fail("Failed with: "+e); 
		
		}

	}
}
