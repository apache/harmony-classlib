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

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.naming.Binding;
import javax.naming.CompositeName;
import javax.naming.Name;
import javax.naming.NameClassPair;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.OperationNotSupportedException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SchemaViolationException;

import junit.framework.TestCase;

public class LdapSchemaContextTest extends TestCase {
    private LdapSchemaContextImpl schema;

    @Override
    public void setUp() throws Exception {
        // Construct the schema table.
        Hashtable<String, Object> schemaTable = new Hashtable<String, Object>();

        Hashtable<String, Object> subSchema = new Hashtable<String, Object>();
        subSchema
                .put(
                        "javaclass",
                        "( 1.3.6.1.4.1.18060.0.4.1.3.8 name 'javaclass' sup top "
                                + "structural must ( fullyqualifiedjavaclassname $ javaclassbytecode ) x-schema 'apache' )");

        subSchema
                .put(
                        "extensibleobject",
                        "( 1.3.6.1.4.1.1466.101.120.111 name 'extensibleobject' "
                                + "desc 'rfc2252: extensible object' sup top auxiliary x-schema 'system' )");
        subSchema
                .put(
                        "prefnode",
                        "( 1.3.6.1.4.1.18060.0.4.1.3.1 name "
                                + "'prefnode' sup top structural must prefnodename x-schema 'apache' )");
        schemaTable.put("objectclasses", subSchema);

        subSchema = new Hashtable<String, Object>();
        subSchema
                .put(
                        "integerorderingmatch",
                        "( 2.5.13.15 name 'integerorderingmatch'  syntax 1.3.6.1.4.1.1466.115.121.1.27 x-schema 'system' )");

        subSchema
                .put(
                        "caseexactmatch",
                        "( 2.5.13.5 name 'caseexactmatch'  syntax 1.3.6.1.4.1.1466.115.121.1.15 x-schema 'system' )");
        schemaTable.put("matchingrules", subSchema);

        subSchema = new Hashtable<String, Object>();
        subSchema
                .put(
                        "1.3.6.1.4.1.1466.115.121.1.19",
                        "( 1.3.6.1.4.1.1466.115.121.1.19  x-schema 'system' x-is-human-readable 'true' )");
        schemaTable.put("ldapsyntaxes", subSchema);

        subSchema = new Hashtable<String, Object>();
        subSchema
                .put(
                        "dsaquality",
                        "( 0.9.2342.19200300.100.1.49 name 'dsaquality' desc 'rfc1274: dsa quality'  syntax 1.3.6.1.4.1.1466.115.121.1.19 single-value usage userapplications x-schema 'cosine' )");
        schemaTable.put("attributetypes", subSchema);

        LdapContextImpl context = new LdapContextImpl(new MockLdapClient(),
                null, "");
        Name name = new CompositeName("");
        schema = new LdapSchemaContextImpl(context, null, name, schemaTable,
                LdapSchemaContextImpl.SCHEMA_ROOT_LEVEL);
    }

    public void testList_NullPara() throws Exception {
        String nullString = null;
        Name nullName = null;
        try {
            schema.list(nullString);
            fail("Should throw NullPointerException.");
        } catch (NullPointerException e) {
            // Expected.
        }

        try {
            schema.list(nullName);
            fail("Should throw NullPointerException.");
        } catch (NullPointerException e) {
            // Expected.
        }
    }

    public void testList() throws Exception {
        // "" as parameter.
        NamingEnumeration<NameClassPair> namingEnum = schema.list("");
        NameClassPair pair;
        ArrayList<String> verifyList = new ArrayList<String>();
        verifyList.add("classdefinition");
        verifyList.add("attributedefinition");
        verifyList.add("matchingrule");
        verifyList.add("syntaxdefinition");
        while (namingEnum.hasMore()) {
            pair = namingEnum.next();
            assertTrue(verifyList.remove(pair.getName().toLowerCase()));
        }
        assertEquals(0, verifyList.size());

        // "matchingrule" as parameter.
        namingEnum = schema.list("matchingrule");
        int count = 0;
        while (namingEnum.hasMore()) {
            pair = namingEnum.next();
            count++;
        }
        assertEquals(2, count);

        // "syntaxdefinition" as parameter.
        namingEnum = schema.list("syntaxdefinition");
        count = 0;
        while (namingEnum.hasMore()) {
            pair = namingEnum.next();
            count++;
        }
        assertEquals(1, count);

        // "classdefinition" as parameter.
        namingEnum = schema.list("classdefinition");
        count = 0;
        while (namingEnum.hasMore()) {
            pair = namingEnum.next();
            count++;
        }
        assertEquals(3, count);

        // "attributedefinition" as parameter.
        namingEnum = schema.list("attributedefinition");
        count = 0;
        while (namingEnum.hasMore()) {
            pair = namingEnum.next();
            count++;
        }
        assertEquals(1, count);

        // "classdefinition/javaClass" as parameter.
        namingEnum = schema.list("classdefinition/javaClass");
        assertFalse(namingEnum.hasMore());
    }

    public void testList_Exception() throws Exception {
        NamingEnumeration<NameClassPair> namingEnum;
        try {
            namingEnum = schema.list("invalid");
            fail("Should throw NameNotFoundException.");
        } catch (NameNotFoundException e) {
            // Expected.
        }

        try {
            namingEnum = schema.list("invalid/invalid/invalid");
            fail("Should throw NameNotFoundException.");
        } catch (NameNotFoundException e) {
            // Expected.
        }

        try {
            namingEnum = schema.list("invalid/invalid");
            fail("Should throw NameNotFoundException.");
        } catch (NameNotFoundException e) {
            // Expected.
        }

        try {
            namingEnum = schema.list("classdefinition/invalid");
            fail("Should throw NameNotFoundException.");
        } catch (NameNotFoundException e) {
            // Expected.
        }

        try {
            namingEnum = schema.list("classdefinition/javaClass/name");
            fail("Should throw NameNotFoundException.");
        } catch (NameNotFoundException e) {
            // Expected.
        }

        try {
            namingEnum = schema.list("classdefinition/javaClass/invalid");
            fail("Should throw NameNotFoundException.");
        } catch (NameNotFoundException e) {
            // Expected.
        }
    }

    public void testList_Address() throws Exception {
        NamingEnumeration<NameClassPair> namingEnum;
        try {
            namingEnum = schema.list("ldap://localhost:10389/");
            fail("Should throw NameNotFoundException.");
        } catch (NameNotFoundException e) {
            // Expected.
        }

        try {
            namingEnum = schema
                    .list("ldap://localhost:10389/dc=example,dc=com");
            fail("Should throw NameNotFoundException.");
        } catch (NameNotFoundException e) {
            // Expected.
        }
    }

    public void testGetSchema() throws Exception {
        try {
            schema.getSchema("");
            fail("Should throw OperationNotSupportedException.");
        } catch (OperationNotSupportedException e) {
            // Expected.
        }

        try {
            schema.getSchema(new CompositeName());
            fail("Should throw OperationNotSupportedException.");
        } catch (OperationNotSupportedException e) {
            // Expected.
        }

        try {
            schema.getSchema(new CompositeName("invalid"));
            fail("Should throw OperationNotSupportedException.");
        } catch (OperationNotSupportedException e) {
            // Expected.
        }

        try {
            String nullString = null;
            schema.getSchema(nullString);
            fail("Should throw OperationNotSupportedException.");
        } catch (OperationNotSupportedException e) {
            // Expected.
        }

        try {
            Name nullName = null;
            schema.getSchema(nullName);
            fail("Should throw NullPointerException.");
        } catch (OperationNotSupportedException e) {
            // Expected.
        }

        try {
            schema.getSchema(new CompositeName("ldap://invalid"));
            fail("Should throw OperationNotSupportedException.");
        } catch (OperationNotSupportedException e) {
            // Expected.
        }
    }

    public void testGetSchemaClassDefinition() throws Exception {
        try {
            schema.getSchemaClassDefinition("");
            fail("Should throw OperationNotSupportedException.");
        } catch (OperationNotSupportedException e) {
            // Expected.
        }

        try {
            schema.getSchemaClassDefinition(new CompositeName());
            fail("Should throw OperationNotSupportedException.");
        } catch (OperationNotSupportedException e) {
            // Expected.
        }

        try {
            schema.getSchemaClassDefinition(new CompositeName("invalid"));
            fail("Should throw OperationNotSupportedException.");
        } catch (OperationNotSupportedException e) {
            // Expected.
        }

        try {
            String nullString = null;
            schema.getSchemaClassDefinition(nullString);
            fail("Should throw OperationNotSupportedException.");
        } catch (OperationNotSupportedException e) {
            // Expected.
        }

        try {
            Name nullName = null;
            schema.getSchemaClassDefinition(nullName);
            fail("Should throw NullPointerException.");
        } catch (OperationNotSupportedException e) {
            // Expected.
        }

        try {
            schema
                    .getSchemaClassDefinition(new CompositeName(
                            "ldap://invalid"));
            fail("Should throw OperationNotSupportedException.");
        } catch (OperationNotSupportedException e) {
            // Expected.
        }
    }

    public void testGetAttributes() throws Exception {
        // "" as parameter.
        Attributes attrs = schema.getAttributes("");
        NamingEnumeration<? extends Attribute> namingEnum = attrs.getAll();
        assertFalse(namingEnum.hasMore());

        attrs = schema.getAttributes("classdefinition/javaclass");

        Attributes attrs2 = schema.getAttributes("classdefinition/javaclass");
        assertNotSame(attrs, attrs2);
        assertEquals(attrs.toString(), attrs2.toString());
        namingEnum = attrs.getAll();

        Attribute attr;
        NamingEnumeration<?> attrEnum;
        int count = 0;
        while (namingEnum.hasMore()) {
            attr = namingEnum.next();
            attrEnum = attr.getAll();
            while (attrEnum.hasMore()) {
                Object o = attrEnum.next();
                count++;
            }

        }
        assertEquals(7, count);

        // "matchingrule/integerorderingmatch" as parameter.
        attrs = schema.getAttributes("matchingrule/integerorderingmatch");
        namingEnum = attrs.getAll();

        count = 0;
        while (namingEnum.hasMore()) {
            attr = namingEnum.next();
            attrEnum = attr.getAll();
            while (attrEnum.hasMore()) {
                Object o = attrEnum.next();
                count++;
            }

        }
        assertEquals(4, count);

        // "matchingrule" as parameter.
        attrs = schema.getAttributes("matchingrule");
        namingEnum = attrs.getAll();

        count = 0;
        while (namingEnum.hasMore()) {
            attr = namingEnum.next();
            attrEnum = attr.getAll();
            while (attrEnum.hasMore()) {
                Object o = attrEnum.next();
                count++;
            }

        }
        assertEquals(1, count);

        // "syntaxdefinition/1.3.6.1.4.1.1466.115.121.1.19" as parameter.
        attrs = schema
                .getAttributes("syntaxdefinition/1.3.6.1.4.1.1466.115.121.1.19");
        namingEnum = attrs.getAll();

        count = 0;
        while (namingEnum.hasMore()) {
            attr = namingEnum.next();
            attrEnum = attr.getAll();
            while (attrEnum.hasMore()) {
                Object o = attrEnum.next();
                count++;
            }

        }
        assertEquals(3, count);
    }

    public void testGetAttributes_Exception() throws Exception {
        Attributes attrs;
        Attribute attr;
        NamingEnumeration<? extends Attribute> namingEnum;
        NamingEnumeration<?> attrEnum;

        try {
            attrs = schema.getAttributes("classdefinition/rfc822localpart/may");
            fail("Should throw NameNotFoundException.");
        } catch (NameNotFoundException e) {
            // Expected.
        }

        // Invalid format.
        try {
            attrs = schema.getAttributes("classdefinition\rfc822localpart");
            fail("Should throw NameNotFoundException.");
        } catch (NameNotFoundException e) {
            // Expected.
        }

        try {
            attrs = schema.getAttributes("invalid");
            fail("Should throw NameNotFoundException.");
        } catch (NameNotFoundException e) {
            // Expected.
        }

        try {
            attrs = schema.getAttributes("invalid/invalid");
            fail("Should throw NameNotFoundException.");
        } catch (NameNotFoundException e) {
            // Expected.
        }

        try {
            attrs = schema.getAttributes("classdefinition/invalid");
            fail("Should throw NameNotFoundException.");
        } catch (NameNotFoundException e) {
            // Expected.
        }

        try {
            attrs = schema
                    .getAttributes("ldap://localhost:10389/dc=example,dc=com");
            fail("Should throw NameNotFoundException.");
        } catch (NameNotFoundException e) {
            // Expected.
        }

        try {
            attrs = schema.getAttributes("ldap://localhost:10389");
            fail("Should throw NameNotFoundException.");
        } catch (NameNotFoundException e) {
            // Expected.
        }
    }

    public void testGetAttributes_NullPara() throws Exception {
        Attributes attrs;
        String nullString = null;
        Name nullName = null;

        try {
            attrs = schema.getAttributes(nullString);
            fail("Should throw NullPointerException.");
        } catch (NullPointerException e) {
            // Expected.
        }
        try {
            attrs = schema.getAttributes(nullName);
            fail("Should throw NullPointerException.");
        } catch (NullPointerException e) {
            // Expected.
        }
    }

    public void testListBindings() throws Exception {
        // "" as parameter.
        NamingEnumeration<Binding> bindings = schema.listBindings("");
        Binding binding;
        ArrayList<String> verifyList = new ArrayList<String>();
        verifyList.add("classdefinition");
        verifyList.add("attributedefinition");
        verifyList.add("matchingrule");
        verifyList.add("syntaxdefinition");
        while (bindings.hasMore()) {
            binding = bindings.next();
            assertTrue(verifyList.remove(binding.getName().toLowerCase()));
        }
        assertEquals(0, verifyList.size());

        // "matchingrule" as parameter.
        bindings = schema.listBindings("matchingrule");
        int count = 0;
        while (bindings.hasMore()) {
            binding = bindings.next();
            count++;
        }
        assertEquals(2, count);

        // "syntaxdefinition" as parameter.
        bindings = schema.listBindings("syntaxdefinition");
        count = 0;
        while (bindings.hasMore()) {
            binding = bindings.next();
            count++;
        }
        assertEquals(1, count);

        // "classdefinition" as parameter.
        bindings = schema.listBindings("classdefinition");
        count = 0;
        while (bindings.hasMore()) {
            binding = bindings.next();
            count++;
        }
        assertEquals(3, count);

        // "attributedefinition" as parameter.
        bindings = schema.listBindings("attributedefinition");
        count = 0;
        while (bindings.hasMore()) {
            binding = bindings.next();
            count++;
        }
        assertEquals(1, count);

        // "classdefinition/javaClass" as parameter.
        bindings = schema.listBindings("classdefinition/javaClass");
        assertFalse(bindings.hasMore());
    }

    public void testListBindings_Exception() throws Exception {
        NamingEnumeration<Binding> bindings;
        try {
            bindings = schema.listBindings("invalid");
            fail("Should throw NameNotFoundException.");
        } catch (NameNotFoundException e) {
            // Expected.
        }

        try {
            bindings = schema.listBindings("invalid/invalid/invalid");
            fail("Should throw NameNotFoundException.");
        } catch (NameNotFoundException e) {
            // Expected.
        }

        try {
            bindings = schema.listBindings("invalid/invalid");
            fail("Should throw NameNotFoundException.");
        } catch (NameNotFoundException e) {
            // Expected.
        }

        try {
            bindings = schema.listBindings("classdefinition/invalid");
            fail("Should throw NameNotFoundException.");
        } catch (NameNotFoundException e) {
            // Expected.
        }

        try {
            bindings = schema.listBindings("classdefinition/javaClass/name");
            fail("Should throw NameNotFoundException.");
        } catch (NameNotFoundException e) {
            // Expected.
        }

        try {
            bindings = schema.listBindings("classdefinition/javaClass/invalid");
            fail("Should throw NameNotFoundException.");
        } catch (NameNotFoundException e) {
            // Expected.
        }
    }

    public void testListBindings_NullPara() throws Exception {
        String nullString = null;
        Name nullName = null;
        try {
            schema.listBindings(nullString);
            fail("Should throw NullPointerException.");
        } catch (NullPointerException e) {
            // Expected.
        }

        try {
            schema.listBindings(nullName);
            fail("Should throw NullPointerException.");
        } catch (NullPointerException e) {
            // Expected.
        }
    }

    public void testLookup() throws Exception {
        DirContext subSchema = (DirContext) schema.lookup("");
        assertSame(schema, subSchema);

        subSchema = (DirContext) schema.lookup("classdefinition");
        assertNotSame(schema, subSchema);
        NamingEnumeration<NameClassPair> namingEnum = subSchema.list("");
        NameClassPair pair;
        int count = 0;
        while (namingEnum.hasMore()) {
            pair = namingEnum.next();
            count++;
        }
        assertEquals(3, count);

        assertSame(subSchema, schema.lookup("classdefinition"));

        DirContext oldSchema = subSchema;
        subSchema = (DirContext) schema.lookup("syntaxdefinition");
        assertNotSame(oldSchema, subSchema);
        namingEnum = subSchema.list("");
        count = 0;
        while (namingEnum.hasMore()) {
            pair = namingEnum.next();
            count++;
        }
        assertEquals(1, count);

        subSchema = (DirContext) schema.lookup("classdefinition/jAvaClass");
        namingEnum = subSchema.list("");
        count = 0;
        while (namingEnum.hasMore()) {
            pair = namingEnum.next();
            count++;
        }
        assertEquals(0, count);

    }

    public void testLookup_Exception() throws Exception {
        try {
            schema.lookup("invalid");
            fail("Should throw NameNotFoundException.");
        } catch (NameNotFoundException e) {
            // Expected.
        }

        try {
            schema.lookup("invalid/invalid/invalid");
            fail("Should throw NameNotFoundException.");
        } catch (NameNotFoundException e) {
            // Expected.
        }

        try {
            schema.lookup("invalid/invalid");
            fail("Should throw NameNotFoundException.");
        } catch (NameNotFoundException e) {
            // Expected.
        }

        try {
            schema.lookup("classdefinition/invalid");
            fail("Should throw NameNotFoundException.");
        } catch (NameNotFoundException e) {
            // Expected.
        }

        try {
            schema.lookup("classdefinition/javaClass/name");
            fail("Should throw NameNotFoundException.");
        } catch (NameNotFoundException e) {
            // Expected.
        }

        try {
            schema.lookup("classdefinition/javaClass/invalid");
            fail("Should throw NameNotFoundException.");
        } catch (NameNotFoundException e) {
            // Expected.
        }
    }

    public void testAttributes_SubSchema() throws Exception {
        DirContext subSchema = (DirContext) schema
                .lookup("classdefinition/javaClass");
        Attributes attrs = subSchema.getAttributes("");
        NamingEnumeration<? extends Attribute> namingEnum = attrs.getAll();

        Attribute attr;
        NamingEnumeration<?> attrEnum;
        int count = 0;
        while (namingEnum.hasMore()) {
            attr = namingEnum.next();
            attrEnum = attr.getAll();
            while (attrEnum.hasMore()) {
                Object o = attrEnum.next();
                count++;
            }

        }
        assertEquals(7, count);

        subSchema = (DirContext) schema.lookup("classdefinition");
        attrs = subSchema.getAttributes("");
        namingEnum = attrs.getAll();

        count = 0;
        assertTrue(namingEnum.hasMore());
        while (namingEnum.hasMore()) {
            attr = namingEnum.next();
            assertEquals("objectclass", attr.getID());
            attrEnum = attr.getAll();
            while (attrEnum.hasMore()) {
                Object o = attrEnum.next();
                assertEquals("classdefinition", o.toString().toLowerCase());
                count++;
            }

        }
        assertEquals(1, count);
    }

    public void testCreateAndDeleteSubContext() throws NamingException {
        // Creates the attributes.
        Attributes attrs = new BasicAttributes(false); // Ignore case
        attrs.put("NAME", "ListObjectClass");
        attrs.put("SUP", "top");
        attrs.put("NUMERICOID", "1.3.6.1.4.1.42.2.27.4.2.3.1.88.77");
        attrs.put("DESC", "for test");
        attrs.put("STRUCTURAL", "fds");

        Attribute must = new BasicAttribute("MUST", "cn");
        must.add("objectclass");
        attrs.put(must);

        DirContext dir = schema.createSubcontext(new CompositeName(
                "ClassDefinition/ListObjectClass"), attrs);

        Attributes createdAttrs = schema
                .getAttributes("ClassDefinition/ListObjectClass");

        NamingEnumeration<? extends Attribute> enumeration = createdAttrs
                .getAll();

        int count = 0;
        while (enumeration.hasMore()) {
            Attribute att = enumeration.next();
            count++;
        }
        assertEquals(6, count);

        schema.destroySubcontext("ClassDefinition/ListObjectClass");
        try {
            schema.getAttributes("ClassDefinition/ListObjectClass");
            fail("Should throw NameNotFoundException.");
        } catch (NameNotFoundException e) {
            // Expected.
        }
    }

    public void testDestroySubContext_Exception() throws NamingException {
        // No Exception.
        schema.destroySubcontext("invalid");

        try {
            schema.destroySubcontext("invalid/invalid/invalid");
            fail("Should throw NameNotFoundException.");
        } catch (NameNotFoundException e) {
            // Expected.
        }

        try {
            schema.destroySubcontext("invalid/invalid");
            fail("Should throw NameNotFoundException.");
        } catch (NameNotFoundException e) {
            // Expected.
        }

        // No Exception.
        schema.destroySubcontext("classdefinition/invalid");

        // No Exception.
        schema.destroySubcontext("classdefinition/javaClass/name");

        // No Exception.
        schema.destroySubcontext("classdefinition/javaClass/invalid");

        try {
            schema.destroySubcontext("");
            fail("Should throw ArrayIndexOutOfBoundsException.");
        } catch (ArrayIndexOutOfBoundsException e) {
            // Expected.
        }
        try {
            schema.destroySubcontext("classdefinition");
            fail("Should throw SchemaViolationException.");
        } catch (SchemaViolationException e) {
            // Expected.
        }
    }

    public void testCreateSubContext_Exception() throws NamingException {
        // Creates the attributes.
        Attributes attrs = new BasicAttributes(false); // Ignore case
        attrs.put("NAME", "ListObjectClass");
        attrs.put("SUP", "top");
        attrs.put("NUMERICOID", "1.3.6.1.4.1.42.2.27.4.2.3.1.88.77");
        attrs.put("DESC", "for test");
        attrs.put("STRUCTURAL", "fds");

        Attribute must = new BasicAttribute("MUST", "cn");
        must.add("objectclass");
        attrs.put(must);

        Attributes invalidAttrs = new BasicAttributes();

        try {
            schema.createSubcontext(new CompositeName("invalid"), attrs);
            fail("Should throw SchemaViolationException.");
        } catch (SchemaViolationException e) {
            // Expected.
        }

        try {
            schema.createSubcontext(new CompositeName("invalid"), invalidAttrs);
            fail("Should throw SchemaViolationException.");
        } catch (SchemaViolationException e) {
            // Expected.
        }

        try {
            schema.createSubcontext(
                    new CompositeName("invalid/invalid/invalid"), attrs);
            fail("Should throw NameNotFoundException.");
        } catch (NameNotFoundException e) {
            // Expected.
        }

        try {
            schema.createSubcontext(
                    new CompositeName("invalid/invalid/invalid"), invalidAttrs);
            fail("Should throw NameNotFoundException.");
        } catch (NameNotFoundException e) {
            // Expected.
        }

        try {
            schema
                    .createSubcontext(new CompositeName("Classdefinition"),
                            attrs);
            fail("Should throw SchemaViolationException.");
        } catch (SchemaViolationException e) {
            // Expected.
        }
    }

    public void testSubContext_OnSubSchema() throws NamingException {
        DirContext subSchema = (DirContext) schema.lookup("classdefinition");

        // Creates the attributes.
        Attributes attrs = new BasicAttributes(false); // Ignore case
        attrs.put("NAME", "ListObjectClass");
        attrs.put("SUP", "top");
        attrs.put("NUMERICOID", "1.3.6.1.4.1.42.2.27.4.2.3.1.88.77");
        attrs.put("DESC", "for test");
        attrs.put("STRUCTURAL", "fds");

        Attribute must = new BasicAttribute("MUST", "cn");
        must.add("objectclass");
        attrs.put(must);

        DirContext dir = subSchema.createSubcontext(new CompositeName(
                "ListObjectClass"), attrs);

        Attributes createdAttrs = dir.getAttributes("");

        NamingEnumeration<? extends Attribute> enumeration = createdAttrs
                .getAll();

        int count = 0;
        while (enumeration.hasMore()) {
            Attribute att = enumeration.next();
            count++;
        }
        assertEquals(6, count);

        subSchema.destroySubcontext("ListObjectClass");

        try {
            schema.getAttributes("ClassDefinition/ListObjectClass");
            fail("Should throw NameNotFoundException.");
        } catch (NameNotFoundException e) {
            // Expected.
        }
    }

    public void test_CreateSubcontext_LName_LAttributes()
            throws NamingException {
        try {
            schema.createSubcontext((Name) null, new BasicAttributes());
            fail("Should throw NPE");
        } catch (NullPointerException e) {
            // expected
        }

        try {
            schema.createSubcontext(new CompositeName(""), null);
            fail("Should throw ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException e) {
            // expected
        }
        try {
            schema.createSubcontext(new CompositeName("test"), null);
            fail("Should throw SchemaViolationException");
        } catch (SchemaViolationException e) {
            // expected
        }
        try {
            schema.createSubcontext(new CompositeName("test"),
                    new BasicAttributes());
            fail("Should throw SchemaViolationException");
        } catch (SchemaViolationException e) {
            // expected
        }
    }

    public void test_modifyAttributes_LString_LModificationItem()
            throws NamingException {
        try {
            schema.modifyAttributes((String) null, new ModificationItem[] {});
            fail("Should throw NPE");
        } catch (NullPointerException e) {
            // expected
        }

        try {
            schema.modifyAttributes("AttributeDefinition/dsaquality", null);
            fail("Should throw NPE");
        } catch (NullPointerException e) {
            // expected
        }
    }

    public void test_search_LString_LAttributes_LString()
            throws NamingException {
        try {
            schema
                    .search((String) null, new BasicAttributes(),
                            new String[] {});
            fail("Should throw NPE");
        } catch (NullPointerException e) {
            // expected
        }
    }

    public void testDestroySubcontextString() throws NamingException {
        try {
            schema.destroySubcontext((String) null);
            fail("Should throw NPE");
        } catch (NullPointerException e) {
            // expected
        }
    }

    public void test_getNameInNamespace() throws NamingException {
        try {
            schema.getNameInNamespace();
            fail("Should throw OperationNotSupportedException");
        } catch (OperationNotSupportedException e) {
            // expected
        }
    }

    public void test_getSchemaClassDefinition() throws NamingException {
        try {
            schema.getSchemaClassDefinition(new CompositeName(""));
            fail("Should throw OperationNotSupportedException");
        } catch (OperationNotSupportedException e) {
            // expected
        }
    }
}
