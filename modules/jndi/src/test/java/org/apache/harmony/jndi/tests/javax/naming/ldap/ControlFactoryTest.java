/* Copyright 2004 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.harmony.jndi.tests.javax.naming.ldap;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.ldap.Control;
import javax.naming.ldap.ControlFactory;
import javax.naming.ldap.LdapContext;

import org.apache.harmony.jndi.tests.javax.naming.ldap.ctx1.MockContextOne;
import org.apache.harmony.jndi.tests.javax.naming.ldap.ctx2.MockContextTwo;

import junit.framework.TestCase;

public class ControlFactoryTest extends TestCase {
	protected void setUp() {

	}

	protected void tearDown() {

	}

	/*
	 * 1. No factories in Hashtable 
	 * 2. No resource files 
	 * 3. Normal input Control
	 * 4. Normal input Context Expected: return value == input control
	 */
	public void testGetControlInstance_NoFactory() throws NamingException {
		Control control = new MockControl("Original control", new byte[] { 1,
				2, 3, 4 }, true);
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.harmony.jndi.tests.javax.naming.spi.mock.MockContextFactory");
		Context context = new InitialContext(env);

		Control newControl = ControlFactory.getControlInstance(control,
				context, new Hashtable());

		assertEquals(control, newControl);
		assertSame(control, newControl);
	}

	/*
	 * 1. Hashtable == null 
	 * 2. No resource files 
	 * 3. Normal input Control 
	 * 4. Normal input Context Expected: return value == input control
	 */
	public void testGetControlInstance_HashtableNull() throws NamingException {
		Control control = new MockControl("Original control", new byte[] { 1,
				2, 3, 4 }, true);
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.harmony.jndi.tests.javax.naming.spi.mock.MockContextFactory");
		Context context = new InitialContext(env);

		Control newControl = ControlFactory.getControlInstance(control,
				context, null);
		assertEquals(control, newControl);
		assertSame(control, newControl);
	}

	/*
	 * 1. No factories in Hashtable 
	 * 2. No resource files 
	 * 3. Normal input Control
	 * 4. Input Context == null Expected: return value == input control
	 */
	public void testGetControlInstance_ContextNull() throws NamingException {
		Control control = new MockControl("Original control", new byte[] { 1,
				2, 3, 4 }, true);
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.harmony.jndi.tests.javax.naming.spi.mock.MockContextFactory");

		Control newControl = ControlFactory.getControlInstance(control, null,
				new Hashtable());

		assertEquals(control, newControl);
		assertSame(control, newControl);
	}

	/*
	 * 1. No factories in Hashtable 
	 * 2. No resource files 
	 * 3. Normal input Control
	 * 4. Input Context == null Expected: return value == input control
	 */
	public void testGetControlInstance_ControlNull() throws NamingException {
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.harmony.jndi.tests.javax.naming.spi.mock.MockContextFactory");

		Context context = new InitialContext(env);
		Control newControl = ControlFactory.getControlInstance(null, context,
				new Hashtable());

		assertEquals(null, newControl);
		assertSame(null, newControl);
	}

	/*
	 * 1. Set a factory in Hashtable 
	 * 2. No resource files 
	 * 3. Normal input Control 
	 * 4. Input Context == null Expected: return value == input control
	 */
	public void testGetControlInstance_Hashtable_factory()
			throws NamingException {
		MockControl control = new MockControl("Original control", new byte[] {
				1, 2, 3, 4 }, true);
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.harmony.jndi.tests.javax.naming.spi.mock.MockContextFactory");
		Context context = new InitialContext(env);

		Hashtable controlEnv = new Hashtable();
		controlEnv.put(LdapContext.CONTROL_FACTORIES,
				"org.apache.harmony.jndi.tests.javax.naming.ldap.MockControlFactory");
		Control newControl = ControlFactory.getControlInstance(control,
				context, controlEnv);

		control.setID(MockControlFactory.ID_PREFIX + control.getID());
		assertEquals(control, newControl);
		assertNotSame(control, newControl);
	}

	/*
	 * 1. Set a factory which will throw exception. 
	 * 2. No resource files 
	 * 3. Normal input Control 
	 * 4. Input Context == null Expected: return value == input control
	 */
	public void testGetControlInstance_factory_throwException()
			throws NamingException {
		MockControl control = new MockControl("Original control", new byte[] {
				1, 2, 3, 4 }, true);
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.harmony.jndi.tests.javax.naming.spi.mock.MockContextFactory");
		Context context = new InitialContext(env);

		Hashtable controlEnv = new Hashtable();
		controlEnv
				.put(LdapContext.CONTROL_FACTORIES,
						"org.apache.harmony.jndi.tests.javax.naming.ldap.ControlFactoryTest$MockInvalidControlFactory");
		try {
			Control newControl = ControlFactory.getControlInstance(control,
					context, controlEnv);
			fail("Should throw a exception as designed.");
		} catch (NamingException e) {
		}

	}

	/*
	 * 1. Set LdapContext.CONTROL_FACTORIES as a invalid value (not refer to a
	 * ControlFactory). 
	 * 2. No resource files 
	 * 3. Normal input Control 
	 * 4. Input Context == null Expected: return value == input control
	 */
	public void testGetControlInstance_InvalidFactory() throws NamingException {
		MockControl control = new MockControl("Original control", new byte[] {
				1, 2, 3, 4 }, true);
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.harmony.jndi.tests.javax.naming.spi.mock.MockContextFactory");
		Context context = new InitialContext(env);

		Hashtable controlEnv = new Hashtable();
		controlEnv.put(LdapContext.CONTROL_FACTORIES, "");
		Control newControl = ControlFactory.getControlInstance(control,
				context, controlEnv);

		assertEquals(control, newControl);
		assertSame(control, newControl);
	}

	/*
	 * 1. Set LdapContext.CONTROL_FACTORIES = multiple factories 
	 * 2. No resource files 
	 * 3. Normal input Control 
	 * 4. Input Context == null Expected: return value == input control
	 */
	public void testGetControlInstance_Multiple_Factory()
			throws NamingException {
		MockControl control = new MockControl("Original control", new byte[] {
				1, 2, 3, 4 }, true);
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.harmony.jndi.tests.javax.naming.spi.mock.MockContextFactory");
		Context context = new InitialContext(env);

		Hashtable controlEnv = new Hashtable();
		controlEnv
				.put(
						LdapContext.CONTROL_FACTORIES,
						"null;javax.naming.ldap.MockControlFactory"
								+ ";javax.naming.ldap.TestControlFactory$MockInvalidControlFactory");
		Control newControl = ControlFactory.getControlInstance(control,
				context, controlEnv);

		control.setID(MockControlFactory.ID_PREFIX + control.getID());
		assertEquals(control, newControl);
		assertSame(control, newControl);
	}

	/*
	 * 1. No factories in Hashtable 
	 * 2. Set factory in Service provider file 
	 * 3. Normal input Control 
	 * 4. Normal input Context Expected: return value == input control
	 */
	public void testGetControlInstance_Spi1() throws NamingException {
		//Comment this test case out because this test case 
		//needs complex configuration about jndi properties.
		
//		MockControl control = new MockControl("Original control", new byte[] {
//				1, 2, 3, 4 }, true);
//		Context context = new MockContextOne();
//
//		Control newControl = ControlFactory.getControlInstance(control,
//				context, new Hashtable());
//		control.setID(MockControlFactory.ID_PREFIX_SPI1 + control.getID());
//
//		assertEquals(control, newControl);
//		assertNotSame(control, newControl);
	}

	/*
	 * 1. No factories in Hashtable 
	 * 2. Set factory in Service provider file 
	 * 3. Normal input Control 
	 * 4. Normal input Context Expected: return value == input control
	 */
	public void testGetControlInstance_Spi2() throws NamingException {
		//Comment this test case out because this test case 
		//needs complex configuration about jndi properties.
		
//		MockControl control = new MockControl("Original control", new byte[] {
//				1, 2, 3, 4 }, true);
//		Context context = new MockContextTwo();
//
//		Control newControl = ControlFactory.getControlInstance(control,
//				context, new Hashtable());
//		control.setID(MockControlFactory.ID_PREFIX_SPI2 + control.getID());
//
//		assertEquals(control, newControl);
//		assertNotSame(control, newControl);
	}

	public static class MockInvalidControlFactory extends ControlFactory {

		public Control getControlInstance(Control c) throws NamingException {
			throw new NamingException(
					"Throw Exception as designed for test ControlFactory.");
		}

	}
}
