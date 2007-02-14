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

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.NoInitialContextException;
import javax.naming.ldap.*;

import org.apache.harmony.jndi.tests.javax.naming.spi.mock.ldap.MockControl;
import org.apache.harmony.jndi.tests.javax.naming.spi.mock.ldap.MockInitialLdapContext;
import org.apache.harmony.jndi.tests.javax.naming.spi.mock.ldap.MockStartTlsResponse;


import junit.framework.TestCase;

/**
 * <p>This Test class is testing the class InitialLdapContext in the javax.naming.ldap package.</p>
 * <p>Here in the next tables we are gonna find all methods to be tested:</p>
 *  <table class="t" cellspacing="0">
	<tbody><th>Constructors:</th>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="30" name="sas9nt11" readonly="readonly" value="" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="65" name="sas9nt21" readonly="readonly" value="InitialLdapContext()" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="30" name="sas9nt11" readonly="readonly" value="" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="65" name="sas9nt21" readonly="readonly" value="InitialLdapContext(Hashtable<?,?> environment, Control[] connCtls)" id="f10"></td>
			
		</tr>
		
	</tbody>
	<table>
	<tbody><th>Method Summary:</th>
		<tr><TD>Return</TD><TD>Method</TD></tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="30" name="sas9nt11" readonly="readonly" value="ExtendedResponse" id="f00"></TD>
			<td class="c0" id="c10"><input class="a0" size="65" name="sas9nt21" readonly="readonly" value="extendedOperation(ExtendedRequest request)" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="30" name="sas9nt11" readonly="readonly" value="Control[]" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="65" name="sas9nt21" readonly="readonly" value="getConnectControls()" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="30" name="sas9nt11" readonly="readonly" value="Control[]" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="65" name="sas9nt21" readonly="readonly" value="getRequestControls()" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="30" name="sas9nt11" readonly="readonly" value="Control[]" id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="65" name="sas9nt21" readonly="readonly" value="getResponseControls()" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="30" name="sas9nt11" readonly="readonly" value="LdapContext " id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="65" name="sas9nt21" readonly="readonly" value="newInstance(Control[] reqCtls)" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="30" name="sas9nt11" readonly="readonly" value="void " id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="65" name="sas9nt21" readonly="readonly" value="reconnect(Control[] connCtls)" id="f10"></td>
			
		</tr>
		<tr>
			<td class="c0" id="c00"><input class="a0" size="30" name="sas9nt11" readonly="readonly" value="void " id="f00"></td>
			<td class="c0" id="c10"><input class="a0" size="65" name="sas9nt21" readonly="readonly" value="setRequestControls(Control[] requestControls)" id="f10"></td>
			
		</tr>
			
	</tbody>
	</table>
 * <hr>
 * 
 */
public class TestInitialLdapContext extends TestCase {

	/**
	 * <p>This method is not implemted.</p>
	 * @param args Possible parameter to help us initiate all tests.
	 */
	public static void main(String[] args) {
	}

	/**
	 * <p>Constructor method of the test class.</p>
	 * <p>Here in this case we initiate the inherited constructor.</p>
	 */
	protected void setUp() throws Exception {
		super.setUp();
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.InitialLdapContext()'
	 * <p>Here we are testing if this method constructs an initial context using no environment properties
	 * or connection request controls.</p>
	 * <p>The expected result is a not null instance of the class.</p> 
	 *
	 */
	public void testInitilLdapContext001(){
		
		try {
			assertNotNull(new InitialLdapContext());
		} catch (NamingException e) {
			fail("Failed with:"+e);
		} 
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.InitialLdapContext()'
	 * <p>Here we are testing if this method constructs an initial context using no environment properties
	 * or connection request controls.</p>
	 * <p>The expected result is an exception when we try to get the environment properties or connections controls.</p> 
	 *
	 */
	public void testInitilLdapContext002(){
		
		System.setProperty(Context.INITIAL_CONTEXT_FACTORY,"org.apache.harmony.jndi.tests.javax.naming.spi.mock.ldap.MockContextFactory");
		InitialLdapContext lctx=null;
		try {
			lctx=new InitialLdapContext();
			assertNull(lctx.getConnectControls());
			
		} catch (NamingException e) {
			fail("Failed with:"+e);
		} 
		try {
			assertNull(lctx.getEnvironment());
			
		} catch (NamingException e) {
			fail("Failed with:"+e);
		}
		try {
			lctx.close();
		} catch (NamingException e) {
			fail("Failed with:"+e);
		}
		
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.InitialLdapContext.InitialLdapContext(Hashtable<?, ?>, Control[])'</p>
	 * <p>Here we are testing the method that constructs an initial context using environment properties and connection
	 * request controls, in this case we use two null arguments.</p>
	 * <p>The expected result is an instance of this class.</p< 
	 */
	public void testInitialLdapContextHashtableOfQQControlArray001() {
		
		try {
			InitialLdapContext ilc = new InitialLdapContext(null,null);
			assertNotNull(ilc);
			ilc.close();
		} catch (NamingException e) {
			fail("Failed with:"+e);
		}

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.InitialLdapContext.InitialLdapContext(Hashtable<?, ?>, Control[])'</p>
	 * <p>Here we are testing the method that constructs an initial context using environment properties and connection
	 * request controls, in this case we use two not null arguments.</p>
	 * <p>The expected result is an instance of this class.</p>
	 */
	public void testInitialLdapContextHashtableOfQQControlArray002() {
		
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,"org.apache.harmony.jndi.tests.javax.naming.spi.mock.ldap.MockContextFactory");
		Control[] cs = { new MockControl("c1", false, new byte[] { 1, 2, 3, 4 }), 
				new MockControl("c1", true, new byte[] { 'a', 'b', 'c', 'd' }), };
		try {
			InitialLdapContext ilc=new InitialLdapContext(env, cs);
			assertNotNull(ilc);
			ilc.close();
		} catch (NamingException e) {
			fail("Failed with:"+e);
		}
				
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.InitialLdapContext.InitialLdapContext(Hashtable<?, ?>, Control[])'</p>
	 * <p>Here we are testing the method that constructs an initial context using environment properties and connection
	 * request controls, in this case we use two not null arguments, and INITIAL_CONTEXT_FACTORY constant not reference to 
	 * a correct class.</p>
	 * <p>The expected result is an exception like "NoInitialContextException".</p>
	 */
	public void testInitialLdapContextHashtableOfQQControlArray003() {
		
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,"ThisNotExist");
		Control[] cs = { new MockControl("c1", false, new byte[] { 1, 2, 3, 4 }), 
				new MockControl("c1", true, new byte[] { 'a', 'b', 'c', 'd' }), };
		try {
			InitialLdapContext x=new InitialLdapContext(env, cs);
			fail("Failed the INITIAL_CONTEXT_FACTORY not exist, an exception must be thrown.");
		} catch (NoInitialContextException e) {
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
				
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.InitialLdapContext.InitialLdapContext(Hashtable<?, ?>, Control[])'</p>
	 * <p>Here we are testing the method that constructs an initial context using environment properties and connection
	 * request controls, in this case we use and INITIAL_CONTEXT_FACTORY constant reference to 
	 * a correct class, but with no controls.</p>
	 * <p>The expected result is an exception of the class".</p>
	 */
	public void testInitialLdapContextHashtableOfQQControlArray004() {
		
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,"org.apache.harmony.jndi.tests.javax.naming.spi.mock.ldap.MockContextFactory");
		Control[] cs = null;
		try {
			InitialLdapContext x=new InitialLdapContext(env, cs);
			assertNotNull(x);
			x.close();
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
				
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.InitialLdapContext.InitialLdapContext(Hashtable<?, ?>, Control[])'</p>
	 * <p>Here we are testing the method that constructs an initial context using environment properties and connection
	 * request controls, in this case we use two not null arguments, and here the hashtable is empty.</p>
	 * <p>The expected result is an instance of the context.</p>
	 */
	public void testInitialLdapContextHashtableOfQQControlArray005() {
		
		Hashtable env = new Hashtable();
		Control[] cs = { new MockControl("c1", false, new byte[] { 1, 2, 3, 4 }), 
				new MockControl("c1", true, new byte[] { 'a', 'b', 'c', 'd' }), };
		try {
			InitialLdapContext x=new InitialLdapContext(env, cs);
			assertNotNull(x);
			x.close();
		}catch (Throwable e) {
			fail("Failed with:"+e);
		}
				
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.InitialLdapContext.InitialLdapContext(Hashtable<?, ?>, Control[])'</p>
	 * <p>Here we are testing the method that constructs an initial context using environment properties and connection
	 * request controls, in this case we use two not null arguments, and here the hashtable is not empty but with data unsuitable and 
	 * the controls is not empty.</p>
	 * <p>The expected result is an instance of the context.</p>
	 */
	public void testInitialLdapContextHashtableOfQQControlArray006() {
		
		Control[] cf = { new MockControl("c1", false, new byte[] { 1, 2, 3, 4 }), 
				new MockControl("c1", true, new byte[] { 'a', 'b', 'c', 'd' }), };
		Hashtable ht = new Hashtable();
	    ht.put("one", new Integer(1));
	    ht.put("two", new Integer(2));
	    ht.put("three", new Integer(3));
	    try {
			InitialLdapContext ilc = new InitialLdapContext(ht , cf);
			assertNotNull(ilc);
			ilc.close();
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.InitialLdapContext.InitialLdapContext(Hashtable<?, ?>, Control[])'</p>
	 * <p>Here we are testing the method that constructs an initial context using environment properties and connection
	 * request controls, in this case we use two not null arguments, and here the hashtable is not empty but with data unsuitable.</p>
	 * <p>The expected result is an instance of the context.</p>
	 */
	public void testInitialLdapContextHashtableOfQQControlArray007() {
		
		Control[] cf = null;
		Hashtable ht = new Hashtable();
	    ht.put("one", new Integer(1));
	    ht.put("two", new Integer(2));
	    ht.put("three", new Integer(3));
	    try {
			InitialLdapContext ilc = new InitialLdapContext(ht , cf);
			assertNotNull(ilc);
			ilc.close();
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.InitialLdapContext.extendedOperation(ExtendedRequest)'</p>
	 * <p>Here we are testing if this method performs an extended operation. Here we send a null extended operation.</p>
	 * <p>The expected result is a "NullpointerException".</p>
	 */
	public void testExtendedOperation001() {
		
		try {
			System.setProperty(Context.INITIAL_CONTEXT_FACTORY,"org.apache.harmony.jndi.tests.javax.naming.spi.mock.ldap.MockContextFactory");
			InitialLdapContext ilc=new InitialLdapContext();
			MockStartTlsResponse x=(MockStartTlsResponse) ilc.extendedOperation(null);
			fail("Should not nitialize a ldap context with null.");
		}  catch (NullPointerException e) {
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.InitialLdapContext.extendedOperation(ExtendedRequest)'</p>
	 * <p>Here we are testing if this method performs an extended operation. Here we send a not null extended operation.</p>
	 * <p>The expected result is an Extended Response.</p>
	 */
	public void testExtendedOperation002() {
		
		try {
			System.setProperty(Context.INITIAL_CONTEXT_FACTORY,"org.apache.harmony.jndi.tests.javax.naming.spi.mock.ldap.MockContextFactory");
			InitialLdapContext x=new InitialLdapContext();
			MockStartTlsResponse f=(MockStartTlsResponse) x.extendedOperation(new StartTlsRequest());
			assertNotNull(f);
			f.close();
			x.close();
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.InitialLdapContext.newInstance(Control[])'</p>
	 * <p>Here we are testing if this method creates a new instance of this context initialized using request controls. In this
	 * case we are using as a parameters null controls.</p>
	 * <p>The expected result is a not null initial ladp context.</p>
	 */
	public void testNewInstance001() {
		
		try {
			Hashtable env = new Hashtable();
			env.put(Context.INITIAL_CONTEXT_FACTORY,"org.apache.harmony.jndi.tests.javax.naming.spi.mock.ldap.MockContextFactory");
			InitialLdapContext x=new InitialLdapContext(env,null);
			InitialLdapContext t=(InitialLdapContext) x.newInstance(null);
			assertNotNull(t);
			assertNotSame(x,t);
			x.close();
			t.close();
		} catch (NamingException e) {
			fail("Failed with:"+e);
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.InitialLdapContext.newInstance(Control[])'</p>
	 * <p>Here we are testing if this method creates a new instance of this context initialized using request controls. In this
	 * case we are using as a parameters null controls.</p>
	 * <p>The expected result is a not null initial ladp context.</p>
	 */
	public void testNewInstance002() {
		
		try {
			Hashtable env = new Hashtable();
			env.put(Context.INITIAL_CONTEXT_FACTORY,"org.apache.harmony.jndi.tests.javax.naming.spi.mock.ldap.MockContextFactory");
			Control[] cs = { new MockControl("c1", false, new byte[] { 1, 2, 3, 4 }), 
					new MockControl("c1", true, new byte[] { 'a', 'b', 'c', 'd' }), };
			InitialLdapContext x=new InitialLdapContext(env,null);
			InitialLdapContext t=(InitialLdapContext) x.newInstance(cs);
			assertNotNull(x);
			assertNotSame(x,t);
			x.close();
			t.close();
		} catch (NamingException e) {
			fail("Failed with:"+e);
		}
	}

	
	/**
	 * <p>Test method for 'javax.naming.ldap.InitialLdapContext.reconnect(Control[])'</p>
	 * <p>Here we are testing if this method reconnects to the LDAP server using the supplied controls and this context's environment.
	 * In this case we are sending a set of null controls.</p>
	 * <p>The expected result is a reconection with no controls.</p>
	 */
	public void testReconnect001() {
		
		try {
			System.setProperty(Context.INITIAL_CONTEXT_FACTORY,"org.apache.harmony.jndi.tests.javax.naming.spi.mock.ldap.MockContextFactory");
			InitialLdapContext ilc = new InitialLdapContext();
			ilc.reconnect(null);
			assertNull(ilc.getConnectControls());
			ilc.close();
		} catch (NamingException e) {
			fail("Failed with:"+e);
		}
		

	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.InitialLdapContext.reconnect(Control[])'</p>
	 * <p>Here we are testing if this method reconnects to the LDAP server using the supplied controls and this context's environment.
	 * In this case we are sending a set not null of controls.</p>
	 * <p>The expected result is a reconection with the controls sended.</p>
	 */
	public void testReconnect002() {
		
		try {
			System.setProperty(Context.INITIAL_CONTEXT_FACTORY,"org.apache.harmony.jndi.tests.javax.naming.spi.mock.ldap.MockContextFactory");
			Control[] cs = { new MockControl("c1", false, new byte[] { 1, 2, 3, 4 }), 
					new MockControl("c1", true, new byte[] { 'a', 'b', 'c', 'd' }), };
			InitialLdapContext ilc = new InitialLdapContext();
			ilc.reconnect(cs);
			assertEquals(cs,ilc.getConnectControls());
			ilc.close();
		} catch (NamingException e) {
			fail("Failed with:"+e);
		}
		

	}

	/**
	 * <p>Test method for 'javax.naming.ldap.InitialLdapContext.reconnect(Control[])'</p>
	 * <p>Here we are testing if this method reconnects to the LDAP server using the supplied controls and this context's environment.
	 * In this case we are sending a new set of controls to reconection.</p>
	 * <p>The expected result is a reconection with the new set of controls.</p>
	 */
	public void testReconnect003() {
		
		try {
			System.setProperty(Context.INITIAL_CONTEXT_FACTORY,"org.apache.harmony.jndi.tests.javax.naming.spi.mock.ldap.MockContextFactory");
			Control[] cs = { new MockControl("c1", false, new byte[] { 1, 2, 3, 4 }), 
					new MockControl("c1", true, new byte[] { 'a', 'b', 'c', 'd' }), };
			Control[] cs2 = { new MockControl("c2", false, new byte[] { 1, 2, 3, 4 }), 
					new MockControl("c2", true, new byte[] { 'a', 'b', 'c', 'd' }), };
			InitialLdapContext ilc = new InitialLdapContext(null,cs);
			ilc.reconnect(cs2);
			assertEquals(cs2,ilc.getConnectControls());
			ilc.close();
		} catch (NamingException e) {
			fail("Failed with:"+e);
		}
		

	}

	
	/**
	 * <p>Test method for 'javax.naming.ldap.InitialLdapContext.getConnectControls()'</p>
	 * <p>Here we are testing if this method retrieves the connection request controls in effect for 
	 * this context. In this case we use a null array control.</p>
	 * <p>The expected result is the controls that we use to create the context.</p> 
	 */
	public void testGetConnectControls001() {
			
		try {
			Hashtable env = new Hashtable();
			env.put(Context.INITIAL_CONTEXT_FACTORY,"org.apache.harmony.jndi.tests.javax.naming.spi.mock.ldap.MockContextFactory");
			Control[] cs = { new MockControl("c1", false,new byte[] { 1, 2, 3, 4 }),
					new MockControl("c2", true, new byte[] { 'a', 'b', 'c', 'd' }), };
			MyInitialLdapContext x=new MyInitialLdapContext(env,cs);
			MockInitialLdapContext defaultCtx = (MockInitialLdapContext) x.getDefaultContext();
			Object objCs = defaultCtx.getProps().get("java.naming.ldap.control.connect");
			Control[] cs2 = (Control[]) objCs;
			for (int i = 0; i < cs.length; i++) {
				assertEquals(cs2[i], cs[i]);
			}
			
		} catch (NamingException e) {
			fail("Failed with:"+e);
		}
		
		
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.InitialLdapContext.getConnectControls()'</p>
	 * <p>Here we are testing if this method retrieves the connection request controls in effect for 
	 * this context. In this case we use a null array control.</p>
	 * <p>The expected result is the controls that we use to create the context.</p> 
	 */
	public void testGetConnectControls002() {
			
		try {
			Hashtable env = new Hashtable();
			env.put(Context.INITIAL_CONTEXT_FACTORY,"org.apache.harmony.jndi.tests.javax.naming.spi.mock.ldap.MockContextFactory");
			InitialLdapContext x=new InitialLdapContext(env,null);
			assertNull(x.getConnectControls());
			x.close();
		} catch (NamingException e) {
			fail("Failed with:"+e);
		}
		
		
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.InitialLdapContext.setRequestControls(Control[])'</p>
	 * <p>Here we are testing if this method sets the request controls for methods subsequently invoked on this context.</p>
	 * <p>The expected result is that the context keep the new request controls.</p> 
	 */
	public void testSetRequestControls001() {

		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,"org.apache.harmony.jndi.tests.javax.naming.spi.mock.ldap.MockContextFactory");
		InitialLdapContext x;
		try {
			x = new InitialLdapContext(env,null);
			Control[] cs = { new MockControl("c1", false,new byte[] { 1, 2, 3, 4 }),
					new MockControl("c2", true, new byte[] { 'a', 'b', 'c', 'd' }), };
			assertNull(x.getRequestControls());
			x.setRequestControls(cs);
			assertNotNull(x.getRequestControls());
			x.close();
		} catch (NamingException e) {
			fail("Failed with:"+e);
		}
			
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.InitialLdapContext.setRequestControls(Control[])'</p>
	 * <p>Here we are testing if this method sets the request controls for methods subsequently invoked on this context.</p>
	 * <p>The expected result is that the context keep the new request controls.</p> 
	 */
	public void testSetRequestControls002() {

		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,"org.apache.harmony.jndi.tests.javax.naming.spi.mock.ldap.MockContextFactory");
		InitialLdapContext x;
		try {
			x = new InitialLdapContext(env,null);
			Control[] cs = null;
			Control[] cs2 = { new MockControl("c1", false,new byte[] { 1, 2, 3, 4 }),
					new MockControl("c2", true, new byte[] { 'a', 'b', 'c', 'd' }), };
			
			x.setRequestControls(cs2);
			assertNotNull(x.getRequestControls());
			x.setRequestControls(cs);
			assertNull(x.getRequestControls());
			x.close();
		} catch (NamingException e) {
			fail("Failed with:"+e);
		}
			
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.InitialLdapContext.getRequestControls()'</p>
	 * <p>Here we are testing if this method retrieves the request controls in effect for this context.</p>
	 * <p>The expected result is a null set of controls.</p>
	 */
	public void testGetRequestControls001() {

		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,"org.apache.harmony.jndi.tests.javax.naming.spi.mock.ldap.MockContextFactory");
		InitialLdapContext x;
		try {
			x = new InitialLdapContext(env,null);
			assertNull(x.getRequestControls());
			x.close();
		} catch (NamingException e) {
			fail("Failed with:"+e);
		}
	}

	/**
	 * <p>Test method for 'javax.naming.ldap.InitialLdapContext.getRequestControls()'</p>
	 * <p>Here we are testing if this method retrieves the request controls in effect for this context.</p>
	 * <p>The expected result is a not null set of controls and also in the same order.</p>
	 */
	public void testGetRequestControls002() {

		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,"org.apache.harmony.jndi.tests.javax.naming.spi.mock.ldap.MockContextFactory");
		InitialLdapContext x;
		try {
			x = new InitialLdapContext(env,null);
			Control[] cs = { new MockControl("c1", false,new byte[] { 1, 2, 3, 4 }),
					new MockControl("c2", true, new byte[] { 'a', 'b', 'c', 'd' }), };
			x.setRequestControls(cs);
			for(int i=0;i<x.getRequestControls().length;i++){
				assertEquals(cs[i],x.getRequestControls()[i]);
			}
			
			x.close();
		} catch (NamingException e) {
			fail("Failed with:"+e);
		}
	}

	
	/**
	 * <p>Test method for 'javax.naming.ldap.InitialLdapContext.getResponseControls()'</p>
	 * <p>Here we are testing if this method retrieves the response controls produced as a 
	 * result of the last method invoked on this context.</p>
	 * <p>The expected result is a null set of controls.</p>
	 */
	public void testGetResponseControls001() {

		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,"org.apache.harmony.jndi.tests.javax.naming.spi.mock.ldap.MockContextFactory");
		InitialLdapContext x;
		try {
			x = new InitialLdapContext(env,null);
			assertNull(x.getResponseControls());
			x.close();
		} catch (NamingException e) {
			fail("Failed with:"+e);
		}
	}
	
	/**
	 * <p>Test method for 'javax.naming.ldap.InitialLdapContext.getResponseControls()'</p>
	 * <p>Here we are testing if this method retrieves the response controls produced as a result of the last method invoked on this context.</p>
	 * <p>The expected result is a not null set of controls.</p>
	 */
	public void testGetResponseControls002() {

		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,"org.apache.harmony.jndi.tests.javax.naming.spi.mock.ldap.MockContextFactory");
		Control[] cs = { new MockControl("c1", false,new byte[] { 1, 2, 3, 4 }),
				new MockControl("c2", true, new byte[] { 'a', 'b', 'c', 'd' }), };
		InitialLdapContext x;
		try {
			x = new InitialLdapContext(env,cs);
			Control[] temp=x.getConnectControls();
			assertEquals(temp,x.getResponseControls());
			x.close();
		} catch (NamingException e) {
			fail("Failed with:"+e);
		}
	}
	
	/**
	 * <p>This class is use to get the default context.</p>
	 *
	 */
	class MyInitialLdapContext extends InitialLdapContext {

		/**
		 * Constructor to the Initial Ldap Context.
		 * @param h The environment.
		 * @param cs The Controls to be use.
		 * @throws NamingException This is thrown in case of an error.
		 */
		public MyInitialLdapContext(Hashtable h, Control[] cs)
				throws NamingException {
			super(h, cs);
		}

		/**
		 * <p>Method to get the default context.</p>
		 * @return The default context.
		 */
		public Context getDefaultContext() {
			return super.defaultInitCtx;
		}
	}

}
