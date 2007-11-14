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

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.naming.CompositeName;
import javax.naming.Context;
import javax.naming.InvalidNameException;
import javax.naming.Name;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InvalidSearchFilterException;
import javax.naming.directory.SearchControls;
import javax.naming.ldap.BasicControl;
import javax.naming.ldap.Control;
import javax.naming.ldap.LdapName;

import org.apache.harmony.jndi.internal.parser.AttributeTypeAndValuePair;

import junit.framework.TestCase;

public class LdapContextImplTest extends TestCase {
    private LdapContextImpl context;

    public void test_composeName_LNameLName() throws Exception {
        context = new LdapContextImpl(new MockLdapClient(), null, "");
        Name name = new LdapName("cn=happy,dc=test");
        Name prefix = new LdapName("o=harmony");
        Name result = context.composeName(name, prefix);
        assertTrue(result instanceof LdapName);
        assertEquals("cn=happy,dc=test,o=harmony", result.toString());

        try {
            context.composeName(null, prefix);
            fail("Should throws NPE");
        } catch (NullPointerException e) {
            // expected
        }

        try {
            context.composeName(name, null);
            fail("Should throws NPE");
        } catch (NullPointerException e) {
            // expected
        }

        CompositeName compositeName = new CompositeName("usr/bin");
        result = context.composeName(compositeName, prefix);
        assertTrue(result instanceof CompositeName);
        assertEquals("o=harmony/usr/bin", result.toString());

        result = context.composeName(name, compositeName);
        assertTrue(result instanceof CompositeName);
        assertEquals("usr/bin/cn=happy,dc=test", result.toString());

        compositeName = new CompositeName("usr");
        CompositeName cName = new CompositeName("bin/cn=ok");
        result = context.composeName(compositeName, cName);
        assertTrue(result instanceof CompositeName);
        assertEquals("bin/cn=ok/usr", result.toString());
    }

    public void test_list_LName() throws Exception {
        MockLdapClient client = new MockLdapClient();
        context = new LdapContextImpl(client, new Hashtable<Object, Object>(),
                "");

        Name name = new LdapName("cn=test,o=harmony");
        context.list(name);

        SearchOp op = (SearchOp) client.getRequest();
        assertEquals(name.toString(), op.getBaseObject());
        assertFalse(op.isTypesOnly());
        SearchControls controls = op.getControls();
        assertEquals(SearchControls.ONELEVEL_SCOPE, controls.getSearchScope());
        Filter filter = op.getFilter();
        assertEquals(Filter.PRESENT_FILTER, filter.getType());
        assertEquals("objectClass", (String) filter.getValue());

        name = new CompositeName("usr/bin");
        try {
            context.list(name);
            fail("should throws InvalidNameException");
        } catch (InvalidNameException e) {
            // expected
        }
    }

    public void test_createSubcontext_LName() throws Exception {
        MockLdapClient client = new MockLdapClient();
        context = new LdapContextImpl(client, new Hashtable<Object, Object>(),
                "cn=test");

        Name name = new LdapName("cn=add+dc=hello,o=harmony");
        context.createSubcontext(name);

        AddOp op = (AddOp) client.getRequest();
        assertEquals("cn=add+dc=hello,o=harmony,cn=test", op.getEntry());
        List<LdapAttribute> list = op.getAttributeList();

        assertEquals(3, list.size());
        Map<String, LdapAttribute> map = new HashMap<String, LdapAttribute>();
        for (LdapAttribute attribute : list) {
            map.put(attribute.getID(), attribute);
        }

        assertTrue(map.containsKey("objectClass"));
        Attribute attr = map.get("objectClass");
        assertEquals(2, attr.size());
        assertTrue(attr.get(0) instanceof String);
        assertTrue(attr.get(1) instanceof String);
        assertTrue(attr.get(0).equals("top")
                || attr.get(0).equals("javaContainer"));
        assertTrue(attr.get(1).equals("top")
                || attr.get(1).equals("javaContainer"));

        assertTrue(map.containsKey("cn"));
        attr = map.get("cn");
        assertEquals(1, attr.size());
        assertEquals("add", attr.get(0));

        assertTrue(map.containsKey("dc"));
        attr = map.get("dc");
        assertEquals(1, attr.size());
        assertEquals("hello", attr.get(0));
    }

    public void test_createSubcontext_LNameLAttributes() throws Exception {
        MockLdapClient client = new MockLdapClient();
        context = new LdapContextImpl(client, new Hashtable<Object, Object>(),
                "cn=test");

        Name name = new LdapName("cn=add+dc=hello,o=harmony");
        Attributes attrs = new BasicAttributes();
        Attribute attr = new BasicAttribute("ou");
        attr.add("harmony");
        attr.add("apache");
        attrs.put(attr);

        context.createSubcontext(name, attrs);

        AddOp op = (AddOp) client.getRequest();
        assertEquals("cn=add+dc=hello,o=harmony,cn=test", op.getEntry());
        List<LdapAttribute> list = op.getAttributeList();

        assertEquals(3, list.size());
        Map<String, LdapAttribute> map = new HashMap<String, LdapAttribute>();
        for (LdapAttribute attribute : list) {
            map.put(attribute.getID(), attribute);
        }

        assertTrue(map.containsKey("ou"));
        attr = map.get("ou");
        assertEquals(2, attr.size());
        assertTrue(attr.get(0) instanceof String);
        assertTrue(attr.get(1) instanceof String);
        assertTrue(attr.get(0).equals("harmony")
                || attr.get(0).equals("apache"));
        assertTrue(attr.get(1).equals("harmony")
                || attr.get(1).equals("apache"));

        assertTrue(map.containsKey("cn"));
        attr = map.get("cn");
        assertEquals(1, attr.size());
        assertEquals("add", attr.get(0));

        assertTrue(map.containsKey("dc"));
        attr = map.get("dc");
        assertEquals(1, attr.size());
        assertEquals("hello", attr.get(0));
    }

    public void test_getAttributes() throws Exception {
        MockLdapClient client = new MockLdapClient();
        context = new LdapContextImpl(client, new Hashtable<Object, Object>(),
                "cn=test");

        Attributes attrs = context.getAttributes("o=apache");
        SearchOp op = (SearchOp) client.getRequest();
        assertEquals("o=apache,cn=test", op.getBaseObject());
        SearchControls controls = op.getControls();
        assertEquals(SearchControls.OBJECT_SCOPE, controls.getSearchScope());
        assertNull(controls.getReturningAttributes());

        Filter filter = op.getFilter();
        assertEquals(Filter.PRESENT_FILTER, filter.getType());
        assertEquals("objectClass", filter.getValue());

        attrs = context.getAttributes("o=apache", new String[0]);
        op = (SearchOp) client.getRequest();
        assertEquals("o=apache,cn=test", op.getBaseObject());
        controls = op.getControls();
        assertEquals(SearchControls.OBJECT_SCOPE, controls.getSearchScope());
        assertEquals(1, controls.getReturningAttributes().length);
        assertEquals("1.1", controls.getReturningAttributes()[0]);
        filter = op.getFilter();
        assertEquals(Filter.PRESENT_FILTER, filter.getType());
        assertEquals("objectClass", filter.getValue());
    }

    public void test_modifyAttributes() throws Exception {
        MockLdapClient client = new MockLdapClient();
        context = new LdapContextImpl(client, new Hashtable<Object, Object>(),
                "cn=test");

        Attributes attributes = new BasicAttributes();
        Attribute attr = new BasicAttribute("cn");
        attr.add("hello");
        attr.add("world");
        attributes.put(attr);

        context.modifyAttributes("o=apache", DirContext.ADD_ATTRIBUTE,
                attributes);
        try {
            context.modifyAttributes("", null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }

        try {
            context.modifyAttributes("", -1, attributes);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    // TODO: add test for names not in same namespace
    public void test_rename() throws Exception {
        MockLdapClient client = new MockLdapClient();
        context = new LdapContextImpl(client, new Hashtable<Object, Object>(),
                "cn=test");

        context.rename("cn=what", "cn=how");

        ModifyDNOp op = (ModifyDNOp) client.getRequest();
        assertEquals("cn=what,cn=test", op.getEntry());
        assertEquals("cn=how", op.getNewrdn());
        assertEquals("cn=test", op.getNewSuperior());
        assertEquals(true, op.isDeleteoldrdn());

        Hashtable<Object, Object> env = new Hashtable<Object, Object>();
        env.put("java.naming.ldap.deleteRDN", "false");
        context = new LdapContextImpl(client, env, "cn=test");

        context.rename("cn=what,o=harmony", "o=apache");

        op = (ModifyDNOp) client.getRequest();
        assertEquals("cn=what,o=harmony,cn=test", op.getEntry());
        assertEquals("o=apache", op.getNewrdn());
        assertEquals("cn=test", op.getNewSuperior());
        assertEquals(false, op.isDeleteoldrdn());

        try {
            context.rename(null, "cn=hello");
            fail("should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }

        try {
            context.rename("cn=hello", null);
            fail("should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }
    }

    public void test_destroySubcontext() throws Exception {
        MockLdapClient client = new MockLdapClient();
        context = new LdapContextImpl(client, new Hashtable<Object, Object>(),
                "cn=test");
        context.destroySubcontext("cn=bad");

        DeleteOp op = (DeleteOp) client.getRequest();
        assertEquals("cn=bad,cn=test", op.getDn());

        try {
            String name = null;
            context.destroySubcontext(name);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }
    }

    public void test_setRequestControls() throws Exception {
        MockLdapClient client = new MockLdapClient();
        Hashtable<Object, Object> env = new Hashtable<Object, Object>();
        env.put(Context.REFERRAL, "follow");
        context = new LdapContextImpl(client, env, "cn=test");

        context.setRequestControls(null);
        assertNull(context.getRequestControls());

        Control[] controls = new Control[] { new BasicControl("0"),
                new BasicControl("1"), new BasicControl("2"),
                new BasicControl("3") };

        context.setRequestControls(controls);

        Control[] actual = context.getRequestControls();

        assertEquals(controls.length, actual.length);
        assertNotSame(controls, actual);

        // Context.REFERRAL is 'ignore' add ManageDsaIT Control
        env.put(Context.REFERRAL, "ignore");
        context = new LdapContextImpl(client, env, "cn=test");
        context.setRequestControls(null);
        actual = context.getRequestControls();
        assertEquals(1, actual.length);
        assertEquals("2.16.840.1.113730.3.4.2", actual[0].getID());
        assertNull(actual[0].getEncodedValue());
        assertFalse(actual[0].isCritical());

        context.setRequestControls(controls);
        actual = context.getRequestControls();
        assertEquals(controls.length + 1, actual.length);

        // Context.REFERRAL is 'ignore', add ManageDsaIT Control
        context = new LdapContextImpl(client, new Hashtable<Object, Object>(),
                "cn=test");
        context.setRequestControls(null);
        actual = context.getRequestControls();
        assertEquals(1, actual.length);
        assertEquals("2.16.840.1.113730.3.4.2", actual[0].getID());
        assertNull(actual[0].getEncodedValue());
        assertFalse(actual[0].isCritical());

        context.setRequestControls(controls);
        actual = context.getRequestControls();
        assertEquals(controls.length + 1, actual.length);
    }

    public void test_search_LAttribute() throws Exception {
        MockLdapClient client = new MockLdapClient();
        context = new LdapContextImpl(client, new Hashtable<Object, Object>(),
                "cn=test");

        Attributes attrs = new BasicAttributes();
        attrs.put("cn", "hello");
        Attribute attr = new BasicAttribute("o");
        attr.add("harmony");
        attr.add("apache");
        attrs.put(attr);

        context.search("cn=hello", attrs);

        SearchOp op = (SearchOp) client.getRequest();
        assertEquals("cn=hello,cn=test", op.getBaseObject());
        SearchControls controls = op.getControls();
        assertEquals(SearchControls.ONELEVEL_SCOPE, controls.getSearchScope());
        assertEquals(0, controls.getCountLimit());
        assertEquals(false, controls.getDerefLinkFlag());
        assertEquals(false, controls.getReturningObjFlag());
        assertEquals(null, controls.getReturningAttributes());

        Filter filter = op.getFilter();
        assertEquals(Filter.AND_FILTER, filter.getType());
        List<Filter> children = filter.getChildren();

        for (Filter f : children) {
            assertEquals(Filter.EQUALITY_MATCH_FILTER, f.getType());
            AttributeTypeAndValuePair pair = (AttributeTypeAndValuePair) f
                    .getValue();
            if (pair.getType().equals("cn")) {
                assertEquals("hello", pair.getValue());
            } else {
                assertEquals("o", pair.getType());
                assertTrue(pair.getValue().equals("harmony")
                        || pair.getValue().equals("apache"));
            }
        }

        String[] rtAttributes = new String[] { "cn", "o" };
        context.search("cn=hello", attrs, rtAttributes);
        op = (SearchOp) client.getRequest();
        assertEquals("cn=hello,cn=test", op.getBaseObject());
        controls = op.getControls();
        String[] actual = controls.getReturningAttributes();

        for (String attribute : rtAttributes) {
            boolean found = false;
            for (String s : actual) {
                if (s.equals(attribute)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                fail("Attribute " + attribute + " not found.");
            }
        }
    }

    public void test_search_with_filter() throws Exception {
        MockLdapClient client = new MockLdapClient();
        context = new LdapContextImpl(client, new Hashtable<Object, Object>(),
                "cn=test");
        SearchControls controls = new SearchControls();
        controls.setCountLimit(100);
        controls.setDerefLinkFlag(true);
        controls.setReturningObjFlag(true);
        controls.setTimeLimit(5);

        context.search("test=search", "(objectClass=*)", controls);

        SearchOp op = (SearchOp) client.getRequest();
        assertEquals("test=search,cn=test", op.getBaseObject());
        Filter filter = op.getFilter();
        assertEquals(Filter.PRESENT_FILTER, filter.getType());
        assertEquals("objectClass", filter.getValue());
        assertEquals(controls.getCountLimit(), op.getControls().getCountLimit());
        assertEquals(controls.getDerefLinkFlag(), op.getControls()
                .getDerefLinkFlag());
        assertEquals(controls.getReturningObjFlag(), op.getControls()
                .getReturningObjFlag());
        assertEquals(controls.getSearchScope(), op.getControls()
                .getSearchScope());
        assertEquals(controls.getTimeLimit(), op.getControls().getTimeLimit());

        try {
            context.search("test=search", "objectClass=*", controls);
            fail("Should throw InvalidSearchFilterException");
        } catch (InvalidSearchFilterException e) {
            // expected
        }

        context.search("test=search", "(objectClass=*)", null);
        op = (SearchOp) client.getRequest();
        assertEquals(0, op.getControls().getCountLimit());
        assertEquals(false, op.getControls().getDerefLinkFlag());
        assertEquals(false, op.getControls().getReturningObjFlag());
        assertEquals(SearchControls.ONELEVEL_SCOPE, op.getControls()
                .getSearchScope());
        assertEquals(0, op.getControls().getTimeLimit());
        assertNull(controls.getReturningAttributes());
    }

    public void test_search_invalid_properties() throws Exception {
        MockLdapClient client = new MockLdapClient();

        Hashtable<Object, Object> env = new Hashtable<Object, Object>();
        env.put("java.naming.ldap.derefAliases", "error");
        context = new LdapContextImpl(client, env, "cn=test");
        try {
            context.search("", null);
            fail("Should throws IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }

        env = new Hashtable<Object, Object>();
        env.put("java.naming.ldap.typesOnly", "error");
        context = new LdapContextImpl(client, env, "cn=test");
        try {
            context.getAttributes("", null);
            fail("Should throws IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

}
