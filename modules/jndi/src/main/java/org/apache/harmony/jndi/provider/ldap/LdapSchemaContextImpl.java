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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.naming.Binding;
import javax.naming.ConfigurationException;
import javax.naming.Context;
import javax.naming.InvalidNameException;
import javax.naming.Name;
import javax.naming.NameClassPair;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.OperationNotSupportedException;
import javax.naming.ReferralException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InvalidSearchFilterException;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SchemaViolationException;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.apache.harmony.jndi.internal.nls.Messages;
import org.apache.harmony.jndi.provider.ldap.parser.FilterParser;
import org.apache.harmony.jndi.provider.ldap.parser.ParseException;

public class LdapSchemaContextImpl extends LdapContextImpl {

    public static final String CLASS_DEFINITION = "classdefinition";

    public static final String ATTRIBUTE_DEFINITION = "attributedefinition";

    public static final String SYNTAX_DEFINITION = "syntaxdefinition";

    public static final String MATCHING_RULE = "matchingrule";

    public static final String OBJECT_CLASSES = "objectclasses";

    public static final String ATTRIBUTE_TYPES = "attributetypes";

    public static final String LDAP_SYNTAXES = "ldapsyntaxes";

    public static final String MATCHING_RULES = "matchingrules";
    
    protected String subschemasubentry = null;

    final private static Hashtable<String, String> schemaJndi2Ldap = new Hashtable<String, String>();
    static {
        schemaJndi2Ldap.put(CLASS_DEFINITION, OBJECT_CLASSES);
        schemaJndi2Ldap.put(ATTRIBUTE_DEFINITION, ATTRIBUTE_TYPES);
        schemaJndi2Ldap.put(SYNTAX_DEFINITION, LDAP_SYNTAXES);
        schemaJndi2Ldap.put(MATCHING_RULE, MATCHING_RULES);
    }

    final private static Hashtable<String, String> schemaLdap2Jndi = new Hashtable<String, String>();
    static {
        schemaLdap2Jndi.put(OBJECT_CLASSES, CLASS_DEFINITION);
        schemaLdap2Jndi.put(ATTRIBUTE_TYPES, ATTRIBUTE_DEFINITION);
        schemaLdap2Jndi.put(LDAP_SYNTAXES, SYNTAX_DEFINITION);
        schemaLdap2Jndi.put(MATCHING_RULES, MATCHING_RULE);
    }

    private LdapContextImpl parent;

    private Name rdn = null;

    private BasicAttributes schemaAttributes;

    public LdapSchemaContextImpl(LdapContextImpl ctx, Hashtable<Object, Object> env,
            Name dn) throws InvalidNameException {
        super(ctx, env, dn.getPrefix(0).toString());
        parent = ctx;
        rdn = dn;
    }

    public DirContext getSchema(Name name) throws NamingException {
        throw new OperationNotSupportedException();
    }

    public DirContext getSchema(String name) throws NamingException {
        throw new OperationNotSupportedException();
    }

    public DirContext getSchemaClassDefinition(Name name)
            throws NamingException {
        throw new OperationNotSupportedException();
    }

    public DirContext getSchemaClassDefinition(String name)
            throws NamingException {
        throw new OperationNotSupportedException();
    }

    public String getNameInNamespace() throws NamingException {
        throw new OperationNotSupportedException();
    }

    public DirContext createSubcontext(Name name, Attributes attributes)
            throws NamingException {
        checkName(name);

        Name schemaType = name.getPrefix(name.size() - 1);

        if (null == attributes || attributes.size() == 0) {
            throw new SchemaViolationException(Messages.getString("jndi.8D"));
        }

        if (!LdapContextImpl.schemaTree.keySet().contains(
                schemaJndi2Ldap.get(schemaType.toString().toLowerCase()))) {
            throw new SchemaViolationException(Messages.getString("jndi.8E"));
        }
        String targetDN = rdn.toString() + parent.subschemasubentry;
        StringBuilder builder = new StringBuilder("( ");
        NamingEnumeration<String> ids = attributes.getIDs();

        // FIXME what if attribute value has \' or \", or can Attributes be used
        // directly
        boolean hasNOID = false;
        while (ids.hasMoreElements()) {
            String id = ids.nextElement();
            Attribute attribute = attributes.get(id);
            if (id.equalsIgnoreCase("NUMERICOID")) {
                builder.append(attribute);
                builder.append(" ");
                hasNOID = true;
            } else {
                builder.append(id);
                builder.append(" ");
                builder.append(attribute);
                builder.append(" ");
            }
        }
        builder.append(")");
        if (!hasNOID) {
            throw new ConfigurationException(Messages.getString("jndi.8F"));
        }

        ModifyOp op = new ModifyOp(targetDN);
        BasicAttribute schemaEntry = new LdapAttribute(new BasicAttribute(
                schemaJndi2Ldap.get(schemaType.toString().toLowerCase()),
                builder.toString()), parent);
        op.addModification(jndi2ldap[DirContext.ADD_ATTRIBUTE],
                new LdapAttribute(schemaEntry, parent));

        try {
            doBasicOperation(op);
        } catch (ReferralException e) {
            // TODO
        }
        return new LdapSchemaContextImpl(parent, parent.env, name);
    }

    public DirContext createSubcontext(String name, Attributes attributes)
            throws NamingException {
        return createSubcontext(convertFromStringToName(name), attributes);
    }

    public Attributes getAttributes(Name name) throws NamingException {
        if (null != schemaAttributes) {
            return schemaAttributes;
        }
        Name schemaName = name;
        if (schemaName.size() == 0) {
            schemaName = rdn;
        }
        if (schemaName.size() != 2) {
            throw new NameNotFoundException(name.toString());
        }
        Hashtable<String, Object> schemaDef = parent.findSchemaDefInfo(
                schemaJndi2Ldap.get(schemaName.get(0).toLowerCase()),
                schemaName.get(1).toLowerCase());
        if (null == schemaDef) {
            throw new NameNotFoundException(name.toString());
        }
        schemaAttributes = new BasicAttributes();
        Enumeration<String> keys = schemaDef.keys();
        while (keys.hasMoreElements()) {
            String id = keys.nextElement();
            if (id.equals("orig")) {
                continue;
            }
            Object value = schemaDef.get(id);
            schemaAttributes.put(new LdapAttribute(
                    new BasicAttribute(id, value), parent));
        }
        return schemaAttributes;
    }

    public Attributes getAttributes(Name name, String[] as)
            throws NamingException {
        Attributes attrs = getAttributes(name);
        Attribute attr = null;
        Attributes filteredAttrs = new BasicAttributes();
        for (int i = 0; i < as.length; i++) {
            attr = attrs.get(as[i]);
            if (attr != null) {
                filteredAttrs.put(attr);
            }
        }
        return filteredAttrs;
    }

    public Attributes getAttributes(String attributeName)
            throws NamingException {
        Name name = convertFromStringToName(attributeName);
        return getAttributes(name);
    }

    public Attributes getAttributes(String name, String[] as)
            throws NamingException {
        return getAttributes(convertFromStringToName(name), as);
    }

    private void checkName(Name name) {
        if (name == null) {
            // jndi.2E=The name is null
            throw new NullPointerException(Messages.getString("jndi.2E")); //$NON-NLS-1$
        }
    }

    public void modifyAttributes(Name name, int i, Attributes attributes)
            throws NamingException {
        checkName(name);
        if (attributes == null) {
            // jndi.13=Non-null attribute is required for modification
            throw new NullPointerException(Messages.getString("jndi.13")); //$NON-NLS-1$
        }

        if (i != DirContext.ADD_ATTRIBUTE && i != DirContext.REMOVE_ATTRIBUTE
                && i != DirContext.REPLACE_ATTRIBUTE) {
            /*
             * jndi.14=Modification code {0} must be one of
             * DirContext.ADD_ATTRIBUTE, DirContext.REPLACE_ATTRIBUTE and
             * DirContext.REMOVE_ATTRIBUTE
             */
            throw new IllegalArgumentException(Messages.getString("jndi.14", i)); //$NON-NLS-1$
        }

        NamingEnumeration<? extends Attribute> enu = attributes.getAll();
        ModificationItem[] items = new ModificationItem[attributes.size()];
        int index = 0;
        while (enu.hasMore()) {
            items[index++] = new ModificationItem(i, enu.next());
        }

        modifyAttributes(name, items);

    }

    private static final int jndi2ldap[] = { -1, 0, 2, 1, };

    public void modifyAttributes(Name name, ModificationItem[] modificationItems)
            throws NamingException {
        checkName(name);

        if (modificationItems == null) {
            // FIXME: spec say ModificationItem may not be null, but ri
            // silence in this case
            throw new NullPointerException(Messages.getString("ldap.27")); //$NON-NLS-1$
        }

        String targetDN = rdn.toString() + parent.subschemasubentry;
        ModifyOp op = new ModifyOp(targetDN);
        Hashtable<String, Object> classDef = parent.findSchemaDefInfo(
                schemaJndi2Ldap.get(name.get(0).toLowerCase()), name.get(1));
        if(null ==classDef) {
            throw new NameNotFoundException(name.toString());
        }
        String oldValue = (String) classDef.get("orig");
        BasicAttribute oldAttr = new LdapAttribute(new BasicAttribute(
                OBJECT_CLASSES, oldValue), parent);
        StringBuilder addValue = new StringBuilder();
        for (ModificationItem item : modificationItems) {
            Attribute attr = item.getAttribute();
            addValue.append(attr.getID()).append(" ").append(attr.get());
        }
        addValue.append(" )");
        BasicAttribute newAttr = new LdapAttribute(new BasicAttribute(
                OBJECT_CLASSES, oldValue.replace(")", addValue.toString())),
                this);
        op.addModification(jndi2ldap[DirContext.REMOVE_ATTRIBUTE],
                new LdapAttribute(oldAttr, parent));
        op.addModification(jndi2ldap[DirContext.ADD_ATTRIBUTE],
                new LdapAttribute(newAttr, parent));

        try {
            doBasicOperation(op);
        } catch (ReferralException e) {
            // TODO handle referral in the future
        }

    }

    public void modifyAttributes(String s, int i, Attributes attributes)
            throws NamingException {
        modifyAttributes(convertFromStringToName(s), i, attributes);
    }

    public void modifyAttributes(String s, ModificationItem[] modificationItems)
            throws NamingException {
        modifyAttributes(convertFromStringToName(s), modificationItems);

    }

    public Context createSubcontext(Name name) throws NamingException {
        return createSubcontext(name, null);
    }

    public Context createSubcontext(String name) throws NamingException {
        return createSubcontext(convertFromStringToName(name));
    }

    public void destroySubcontext(Name name) throws NamingException {
        checkName(name);

        // Name schemaType = name.getPrefix(name.size() - 1);
        String targetDN = rdn.toString() + parent.subschemasubentry;
        ModifyOp op = new ModifyOp(targetDN);
        String schemaType = null;
        try {
            schemaType = schemaJndi2Ldap.get(name.getPrefix(name.size() - 1)
                    .toString().toLowerCase());
        } catch (IndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException();
        }

        Hashtable<String, Object> classDef = parent.findSchemaDefInfo(
                schemaType, name.get(1));
        if (null == classDef) {
            return;
        }
        String oldValue = (String) classDef.get("orig");
        LdapAttribute oldAttr = new LdapAttribute(new BasicAttribute(
                schemaType, oldValue), parent);
        op.addModification(jndi2ldap[DirContext.REMOVE_ATTRIBUTE], oldAttr);

        try {
            doBasicOperation(op);
        } catch(Exception e) {
            // TODO need to handle referal exception in the future
        }
    }

    public void destroySubcontext(String name) throws NamingException {
        destroySubcontext(convertFromStringToName(name));
    }

    public NamingEnumeration<NameClassPair> list(Name n) throws NamingException {
        Set<String> keyset = LdapContextImpl.schemaTree.keySet();
        LdapNamingEnumeration<NameClassPair> enumeration = new LdapNamingEnumeration<NameClassPair>(
                null, null);
        for (Iterator<String> i = keyset.iterator(); i.hasNext();) {
            String schemaType = i.next();
            NameClassPair pair = new NameClassPair(schemaLdap2Jndi
                    .get(schemaType.toLowerCase()), this.getClass().getName());
            enumeration.add(pair);
        }
        return enumeration;
    }

    public NamingEnumeration<NameClassPair> list(String name)
            throws NamingException {
        // TODO name supposed to be "" string, what about the situation when
        // name is not ""
        return list(convertFromStringToName(name));
    }

    public NamingEnumeration<Binding> listBindings(Name n)
            throws NamingException {
        // TODO Auto-generated method stub
        return null;
    }

    public NamingEnumeration<Binding> listBindings(String s)
            throws NamingException {
        // TODO Auto-generated method stub
        return null;
    }

    public Object lookup(Name n) throws NamingException {
        if (n.size() == 0) {
            return this;
        }
        if (n.size() == 1) {
            throw new NameNotFoundException(n.toString());
        }
        Hashtable<String, Object> schemaDef = parent.findSchemaDefInfo(
        // mapping jndi schema type name to ldap native name
                schemaJndi2Ldap.get(
                // get [0, 1) as the jndi schema type name
                        n.getPrefix(1).toString().toLowerCase()),
                // schema name
                n.getSuffix(1).toString().toLowerCase()); // find SchemaDef
        // Info.

        if (null == schemaDef) {
            throw new NameNotFoundException(n.toString());
        }
        return new LdapSchemaContextImpl(parent, env, n.addAll(rdn));
    }

    public Object lookup(String name) throws NamingException {
        return lookup(convertFromStringToName(name));
    }

    public void rename(Name nOld, Name nNew) throws NamingException {
        throw new SchemaViolationException(Messages.getString("jndi.err.01"));
    }

    public void rename(String sOld, String sNew) throws NamingException {
        throw new SchemaViolationException(Messages.getString("jndi.err.01"));
    }

    public NamingEnumeration<SearchResult> search(Name name,
            Attributes attributes) throws NamingException {
        return search(name, attributes, null);
    }

    public NamingEnumeration<SearchResult> search(Name name,
    // Used to filter attribute value
            Attributes attributes,
            // Used to filter attribute name
            String[] as) throws NamingException {
        checkName(name);

        Name targetDN = name.addAll(rdn);

        /*
         * Formalize attributes, change all ids to lowercase, if attributes is
         * non-null
         */

        boolean hasMatchingAttributes = (null != attributes && 0 != attributes
                .size());
        boolean hasAttributes2Return = (null != as && 0 != as.length);

        // Attribute matcher
        BasicAttributes attrMatcher = new BasicAttributes();
        if (hasMatchingAttributes) {
            NamingEnumeration<? extends Attribute> attrEnum = attributes
                    .getAll();
            while (attrEnum.hasMore()) {
                Attribute old = attrEnum.next();
                BasicAttribute newAttr = new BasicAttribute(old.getID()
                        .toLowerCase());
                for (int i = 0; i < old.size(); i++) {
                    if (old.get(i) instanceof String) {
                        newAttr.add(((String) old.get(i)).toLowerCase());
                    } else {
                        newAttr.add(old.get(i));
                    }
                }
                attrMatcher.put(newAttr);
            }
        }
        
        // Attribute selector
        TreeSet<String> attrSel = new TreeSet<String>();

        // Construct result NamingEnumeration
        LdapNamingEnumeration<SearchResult> enumeration = new LdapNamingEnumeration<SearchResult>(
                null, null);
        String schemaType = null;

        LinkedList<String> attrValues = new LinkedList<String>();;
        int size = targetDN.size();
        switch (size) {
        case 0:
            /*
             * Name is a empty string, search against root, may return schema
             * types: (classdefinition, attributedefinition, syntaxdefinition,
             * matchingrule)
             */
            attrValues.addAll(LdapContextImpl.schemaTree.keySet());
            /*
             * Filter attribute names - whether the single attribute name
             * 'objectclass' is chosen.
             */
            int objectclassIndex = -1;
            if (hasAttributes2Return) {
                for (int i = 0; i < as.length; i++) {
                    if (as[i].equalsIgnoreCase("objectclass")) {
                        objectclassIndex = i;
                        break;
                    }
                }
            }
            attrSel.add("objectclass");

            /*
             * Filter attribute values - choose from (classdefinition,
             * attributedefinition, syntaxdefinition, matchingrule)
             */
            if (hasMatchingAttributes) {
                Attribute attribute = attrMatcher.get("objectclass");
                if (null == attribute) {
                    return enumeration;
                }
                for (int i = 0; i < attrValues.size(); i++) {
                    schemaType = schemaLdap2Jndi.get(attrValues.get(i));
                    /*
                     * RI's behavior is odd here - it only retrieves the first
                     * encountered attribute value,
                     */
                    if (attribute.contains(schemaType)) {
                        BasicAttributes basicAttributes = new BasicAttributes();
                        /*
                         * if(objectclassIndex == -1), then No name was choose,
                         * which means SearchResult will have empty
                         * BasicAttributes.
                         */
                        if (objectclassIndex != -1) {
                            basicAttributes.put("objectclass", schemaType);
                        }
                        SearchResult pair = new SearchResult(schemaType, null,
                                basicAttributes);
                        enumeration.add(pair);
                        break;
                    }
                }
            } else {
                for (int i = 0; i < attrValues.size(); i++) {
                    schemaType = schemaLdap2Jndi.get(attrValues.get(i));
                    BasicAttributes basicAttributes = new BasicAttributes();
                    /*
                     * if(objectclassIndex == -1), then No name was choose,
                     * which means SearchResult will have empty BasicAttributes.
                     */
                    if (objectclassIndex != -1) {
                        basicAttributes.put("objectclass", schemaType);
                    }
                    SearchResult pair = new SearchResult(schemaType, null,
                            basicAttributes);
                    enumeration.add(pair);
                }
            }
            break;
        case 1:
            schemaType = schemaJndi2Ldap.get(name.get(0).toLowerCase());
            if (null == schemaType) {
                throw new NameNotFoundException(name.toString());
            }
            Hashtable<String, Hashtable<String, Object>> schemas = LdapContextImpl.schemaTree
                    .get(schemaType);
            attrValues.addAll(schemas.keySet());
            BasicAttributes basicAttributes = null;
            if (hasMatchingAttributes) {
                for (int i = 0; i < attrValues.size(); i++) {
                    NamingEnumeration<Attribute> filters = attrMatcher.getAll();
                    String id = attrValues.get(i);
                    Hashtable<String, Object> schemaDef = schemas.get(id);
                    while (filters.hasMore()) {
                        Attribute filter = filters.next();
                        Object values = schemaDef.get(filter.getID());
                        /* 
                         * Attribute definition will only be retrieved when it is
                         * designated in attrFilter
                         */ 
                        if (match(filter, values)) {
                            basicAttributes = new BasicAttributes();
                            for (Iterator<String> iterator = schemaDef.keySet()
                                    .iterator(); iterator.hasNext();) {
                                String key = iterator.next();
                                if (key.equals("orig")) {
                                    continue;
                                }
                                Object value = schemaDef.get(key);
                                basicAttributes.put(key, value);
                            }
                            SearchResult pair = new SearchResult(id, null,
                                    basicAttributes);
                            enumeration.add(pair);
                        }
                    }
                }
            } else {
                for (int i = 0; i < attrValues.size(); i++) {
                    Hashtable<String, Object> schemaDef = schemas.get(attrValues.get(i));
                    basicAttributes = new BasicAttributes();
                    for (Iterator<String> iterator = schemaDef.keySet()
                            .iterator(); iterator.hasNext();) {
                        String key = iterator.next();
                        if (key.equals("orig")) {
                            continue;
                        }
                        Object value = schemaDef.get(key);
                        basicAttributes.put(key, value);
                    }
                    SearchResult pair = new SearchResult(attrValues.get(i), null,
                            basicAttributes);
                    enumeration.add(pair);
                }
            }
            break;

        default:
            schemaType = schemaJndi2Ldap.get(name.getPrefix(1).toString()
                    .toLowerCase());
            if (null == schemaType) {
                throw new NameNotFoundException(name.toString());
            }
            search(name.getSuffix(1), attributes, as);
        }
        return enumeration;

    }

    private boolean match(Attribute filter, Object values)
            throws NamingException {
        NamingEnumeration<?> attrValues = filter.getAll();
        ArrayList v = null;
        if (values instanceof ArrayList) {
            v = (ArrayList) values;
        } else {
            v = new ArrayList();
            v.add(values);
        }

        while (attrValues.hasMore()) {
            Object attrValue = attrValues.next();
            for (int i = 0; i < v.size(); i++) {
                if (attrValue.equals(v.get(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    public NamingEnumeration<SearchResult> search(Name name, String filter,
            Object[] objs, SearchControls searchControls)
            throws NamingException {
        checkName(name);

        if (filter == null) {
            throw new NullPointerException(Messages.getString("ldap.28")); //$NON-NLS-1$
        }

        if (objs == null) {
            objs = new Object[0];
        }

        if (searchControls == null) {
            searchControls = new SearchControls();
        }

        // get absolute dn name
        String targetDN = getTargetDN(name, contextDn);
        FilterParser filterParser = new FilterParser(filter);
        filterParser.setArgs(objs);
        Filter f = null;
        try {
            f = filterParser.parse();
        } catch (ParseException e) {
            InvalidSearchFilterException ex = new InvalidSearchFilterException(
                    Messages.getString("ldap.29")); //$NON-NLS-1$
            ex.setRootCause(e);
            throw ex;
        }

        LdapSearchResult result = doSearch(targetDN, f, searchControls);

        List<SearchResult> list = new ArrayList<SearchResult>();
        Map<String, Attributes> entries = result.getEntries();
        for (String dn : entries.keySet()) {
            String relativeName = convertToRelativeName(dn, rdn.toString());
            SearchResult sr = new SearchResult(relativeName, null, entries
                    .get(dn));
            sr.setNameInNamespace(dn);
            list.add(sr);
        }

        return new LdapNamingEnumeration<SearchResult>(list, result
                .getException());
    }

}
