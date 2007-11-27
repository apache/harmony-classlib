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
package org.apache.harmony.jndi.provider.ldap;

import java.util.Hashtable;

import javax.naming.CompositeName;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.OperationNotSupportedException;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SchemaViolationException;

import junit.framework.TestCase;

public class LdapSchemaContextImplTest extends TestCase {
	public LdapSchemaContextImpl context;

	protected void setUp() throws Exception {
		super.setUp();
		LdapContextImpl impl = new LdapContextImpl(
				new MockLdapClient(), null, "");
		CompositeName name = new CompositeName("ou=mock");
		context = new LdapSchemaContextImpl(impl,
				new Hashtable<Object, Object>(), name);
		LdapSchemaContextImpl.schemaTree = new Hashtable<String, Hashtable<String, Hashtable<String, Object>>>();
		LdapSchemaContextImpl.schemaTree.put(LdapSchemaContextImpl.ATTRIBUTE_TYPES,
				new Hashtable<String, Hashtable<String, Object>>());
		LdapSchemaContextImpl.schemaTree.put(LdapSchemaContextImpl.OBJECT_CLASSES,
				new Hashtable<String, Hashtable<String, Object>>());
		LdapSchemaContextImpl.schemaTree.put(LdapSchemaContextImpl.LDAP_SYNTAXES,
				new Hashtable<String, Hashtable<String, Object>>());
		LdapSchemaContextImpl.schemaTree.put(LdapSchemaContextImpl.MATCHING_RULES,
				new Hashtable<String, Hashtable<String, Object>>());
		context = new LdapSchemaContextImpl(context,
				new Hashtable<Object, Object>(), name);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		
	}

	public void test_CreateSubcontext_LName_LAttributes()
			throws NamingException {
		try {
			context.createSubcontext((Name) null, new BasicAttributes());
			fail("Should throw NPE");
		} catch (NullPointerException e) {
			// expected
		}

		try {
			context.createSubcontext(new CompositeName(""), null);
			fail("Should throw ArrayIndexOutOfBoundsException");
		} catch (ArrayIndexOutOfBoundsException e) {
			// expected
		}
		try {
			context.createSubcontext(new CompositeName("test"), null);
			fail("Should throw SchemaViolationException");
		} catch (SchemaViolationException e) {
			// expected
		}
		try {
			context.createSubcontext(new CompositeName("test"),
					new BasicAttributes());
			fail("Should throw SchemaViolationException");
		} catch (SchemaViolationException e) {
			// expected
		}

		// Specify attributes for the schema object
	}

	public void test_modifyAttributes_LString_LModificationItem()
			throws NamingException {
		try {
			context.modifyAttributes((String) null, new ModificationItem[] {});
			fail("Should throw NPE");
		} catch (NullPointerException e) {
			// expected
		}

		try {
			context.modifyAttributes("AttributeDefinition/cn", null);
			fail("Should throw NPE");
		} catch (NullPointerException e) {
			// expected
		}
	}

	public void test_search_LString_LAttributes_LString()
			throws NamingException {
		try {
			context.search((String) null, new BasicAttributes(),
					new String[] {});
			fail("Should throw NPE");
		} catch (NullPointerException e) {
			// expected
		}
	}

	public void testDestroySubcontextString() throws NamingException {
		try {
			context.destroySubcontext((String) null);
			fail("Should throw NPE");
		} catch (NullPointerException e) {
			// expected
		}
	}

    public void test_getNameInNamespace() throws NamingException {
        try {
            context.getNameInNamespace();
            fail("Should throw OperationNotSupportedException");
        } catch (OperationNotSupportedException e) {
            // expected
        }
    }

}
