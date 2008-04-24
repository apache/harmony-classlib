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
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.naming.Binding;
import javax.naming.CompositeName;
import javax.naming.Context;
import javax.naming.InvalidNameException;
import javax.naming.Name;
import javax.naming.NameAlreadyBoundException;
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
import org.apache.harmony.jndi.internal.parser.AttributeTypeAndValuePair;
import org.apache.harmony.jndi.provider.ldap.parser.FilterParser;
import org.apache.harmony.jndi.provider.ldap.parser.ParseException;
import org.apache.harmony.jndi.provider.ldap.parser.SchemaParser;

public class LdapSchemaContextImpl extends LdapContextImpl {

    public static final String CLASS_DEFINITION = "ClassDefinition";

    public static final String ATTRIBUTE_DEFINITION = "AttributeDefinition";

    public static final String SYNTAX_DEFINITION = "SyntaxDefinition";

    public static final String MATCHING_RULE = "MatchingRule";

    public static final String OBJECT_CLASSES = "objectclasses";

    public static final String ATTRIBUTE_TYPES = "attributetypes";

    public static final String LDAP_SYNTAXES = "ldapsyntaxes";

    public static final String MATCHING_RULES = "matchingrules";

    public static final int SCHEMA_ROOT_LEVEL = 3;

    protected String subschemasubentry = null;

    final private static Hashtable<String, String> schemaJndi2Ldap = new Hashtable<String, String>();
    static {
        schemaJndi2Ldap.put(CLASS_DEFINITION.toLowerCase(), OBJECT_CLASSES);
        schemaJndi2Ldap
                .put(ATTRIBUTE_DEFINITION.toLowerCase(), ATTRIBUTE_TYPES);
        schemaJndi2Ldap.put(SYNTAX_DEFINITION.toLowerCase(), LDAP_SYNTAXES);
        schemaJndi2Ldap.put(MATCHING_RULE.toLowerCase(), MATCHING_RULES);
    }

    final private static Hashtable<String, String> schemaLdap2Jndi = new Hashtable<String, String>();
    static {
        schemaLdap2Jndi.put(OBJECT_CLASSES, CLASS_DEFINITION);
        schemaLdap2Jndi.put(ATTRIBUTE_TYPES, ATTRIBUTE_DEFINITION);
        schemaLdap2Jndi.put(LDAP_SYNTAXES, SYNTAX_DEFINITION);
        schemaLdap2Jndi.put(MATCHING_RULES, MATCHING_RULE);
    }

    private LdapContextImpl parent;

    private Hashtable<String, Object> schemaTable;

    private Name rdn = null;

    private int level;

    public LdapSchemaContextImpl(LdapContextImpl ctx,
            Hashtable<Object, Object> env, Name dn) throws InvalidNameException {
        super(ctx, env, dn.getPrefix(0).toString());
        parent = ctx;
        rdn = dn;
    }

    public LdapSchemaContextImpl(LdapContextImpl ctx,
            Hashtable<Object, Object> env, Name dn,
            Hashtable<String, Object> schemaTable, int level)
            throws InvalidNameException {
        super(ctx, env, dn.getPrefix(0).toString());
        parent = ctx;
        rdn = dn;
        this.schemaTable = schemaTable;
        this.level = level;
    }

    @Override
    public DirContext getSchema(Name name) throws NamingException {
        throw new OperationNotSupportedException();
    }

    @Override
    public DirContext getSchema(String name) throws NamingException {
        throw new OperationNotSupportedException();
    }

    @Override
    public DirContext getSchemaClassDefinition(Name name)
            throws NamingException {
        throw new OperationNotSupportedException();
    }

    @Override
    public DirContext getSchemaClassDefinition(String name)
            throws NamingException {
        throw new OperationNotSupportedException();
    }

    @Override
    public String getNameInNamespace() throws NamingException {
        throw new OperationNotSupportedException();
    }

    @Override
    public DirContext createSubcontext(Name name, Attributes attributes)
            throws NamingException {
        int size = name.size();
        Hashtable<String, Object> subSchemaTree = doLookup(name
                .getPrefix(size - 1), size - 1);

        if (null == attributes || attributes.size() == 0) {
            // jndi.8D=Must supply attributes describing schema
            throw new SchemaViolationException(Messages.getString("jndi.8D")); //$NON-NLS-1$
        }

        if (level - size == 2) {
            // jndi.8E=Cannot create new entry under schema root
            throw new SchemaViolationException(Messages.getString("jndi.8E")); //$NON-NLS-1$
        }

        String subSchemaType = name.getSuffix(size - 1).toString();

        if (subSchemaTree.get(subSchemaType.toLowerCase()) != null) {
            throw new NameAlreadyBoundException(subSchemaType);
        }

        String schemaLine = SchemaParser.format(attributes);

        ModifyOp op = new ModifyOp(parent.subschemasubentry);
        Name modifySchemaName = name.getPrefix(size - 1).addAll(rdn);
        BasicAttribute schemaEntry = new LdapAttribute(new BasicAttribute(
                jndi2ldap(modifySchemaName.toString()), schemaLine), parent);
        op.addModification(jndi2ldap[DirContext.ADD_ATTRIBUTE],
                new LdapAttribute(schemaEntry, parent));
        try {
            doBasicOperation(op);
            subSchemaTree.put(subSchemaType.toLowerCase(), schemaLine);
        } catch (ReferralException e) {
            // TODO
        }

        return (DirContext) lookup(name);

    }

    @Override
    public DirContext createSubcontext(String name, Attributes attributes)
            throws NamingException {
        Name n = convertFromStringToName(name);
        return createSubcontext(n, attributes);
    }

    @Override
    public Attributes getAttributes(Name name) throws NamingException {
        int size = name.size();

        Hashtable<String, Object> attributesTable = doLookup(name, size);

        BasicAttributes schemaAttributes = new BasicAttributes(true);

        switch (level - size) {
        case 1:
            Set<String> keyset = attributesTable.keySet();
            for (Iterator<String> i = keyset.iterator(); i.hasNext();) {
                String id = i.next();
                if (id.equals("orig")) { //$NON-NLS-1$
                    continue;
                }
                Object value = attributesTable.get(id);
                BasicAttribute basicAttr = new BasicAttribute(id);

                if (value instanceof List) {
                    List<Object> list = (List<Object>) value;
                    for (int j = 0; j < list.size(); j++) {
                        basicAttr.add(list.get(j));
                    }
                } else {
                    basicAttr.add(value);
                }
                schemaAttributes.put(basicAttr);
            }
            break;

        case 2:
            BasicAttribute basicAttr = new BasicAttribute("objectclass"); //$NON-NLS-1$
            Name allName = name.addAll(rdn);
            basicAttr.add(allName.toString());
            schemaAttributes.put(basicAttr);
            break;

        default:
            // Do nothing.
        }

        return schemaAttributes;
    }

    @Override
    public Attributes getAttributes(Name name, String[] as)
            throws NamingException {
        Attributes attrs = getAttributes(name);
        Attribute attr = null;
        Attributes filteredAttrs = new BasicAttributes(true);
        for (int i = 0; i < as.length; i++) {
            attr = attrs.get(as[i]);
            if (attr != null) {
                filteredAttrs.put(attr);
            }
        }
        return filteredAttrs;
    }

    @Override
    public Attributes getAttributes(String attributeName)
            throws NamingException {
        Name name = new CompositeName(attributeName);
        return getAttributes(name);
    }

    @Override
    public Attributes getAttributes(String name, String[] as)
            throws NamingException {
        return getAttributes(new CompositeName(name), as);
    }

    private void checkName(Name name) {
        if (name == null) {
            // jndi.2E=The name is null
            throw new NullPointerException(Messages.getString("jndi.2E")); //$NON-NLS-1$
        }
    }

    @Override
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

    @Override
    public void modifyAttributes(Name name, ModificationItem[] modificationItems)
            throws NamingException {
        // First get the old schema.
        int size = name.size();
        Hashtable<String, Object> subSchemaTree = doLookup(name
                .getPrefix(size - 1), size - 1);

        String subSchemaType = name.getSuffix(size - 1).toString()
                .toLowerCase();

        Object schema = subSchemaTree.get(jndi2ldap(subSchemaType));
        if (schema == null) {
            throw new NameNotFoundException(name.toString());
        }

        if (level - size == 2) {
            // ldap.38=Can't modify schema root
            throw new SchemaViolationException(Messages.getString("ldap.38")); //$NON-NLS-1$
        }

        if (modificationItems.length == 0) {
            return;
        }

        String schemaLine = schema.toString();
        if (schema instanceof Hashtable) {
            Hashtable table = (Hashtable) schema;
            schemaLine = table.get(SchemaParser.ORIG).toString();
        }

        // Construct the new schema.
        Attributes attributes = getAttributes(name);
        int modifyOperation;
        Attribute modifyAttribute;
        Attribute attribute;
        NamingEnumeration<?> enu;
        for (int i = 0; i < modificationItems.length; i++) {
            modifyOperation = modificationItems[i].getModificationOp();
            modifyAttribute = modificationItems[i].getAttribute();

            switch (modifyOperation) {
            case DirContext.ADD_ATTRIBUTE:
                attribute = attributes.get(modifyAttribute.getID());
                if (attribute == null) {
                    attributes.put(modifyAttribute);
                } else {
                    enu = modifyAttribute.getAll();
                    while (enu.hasMoreElements()) {
                        attribute.add(enu.nextElement());
                    }
                    attributes.put(attribute);
                }
                break;

            case DirContext.REMOVE_ATTRIBUTE:
                attribute = attributes.get(modifyAttribute.getID());
                enu = modifyAttribute.getAll();
                while (enu.hasMoreElements()) {
                    attribute.remove(enu.nextElement());
                }
                if (attribute.size() == 0) {
                    attributes.remove(modifyAttribute.getID());
                }
                break;

            case DirContext.REPLACE_ATTRIBUTE:
                attributes.remove(modifyAttribute.getID());
                attributes.put(modifyAttribute);
                break;
            default:
                // Never reach here.
            }
        }
        String newSchemaLine = SchemaParser.format(attributes);

        // Remove old schema, then add new schema.
        ModifyOp op = new ModifyOp(parent.subschemasubentry);
        Name modifySchemaName = name.getPrefix(size - 1).addAll(rdn);
        BasicAttribute schemaEntry = new LdapAttribute(new BasicAttribute(
                jndi2ldap(modifySchemaName.toString()), schemaLine), parent);
        op.addModification(jndi2ldap[DirContext.REMOVE_ATTRIBUTE],
                new LdapAttribute(schemaEntry, parent));
        BasicAttribute addSchemaEntry = new LdapAttribute(new BasicAttribute(
                jndi2ldap(modifySchemaName.toString()), newSchemaLine), parent);
        op.addModification(jndi2ldap[DirContext.ADD_ATTRIBUTE],
                new LdapAttribute(addSchemaEntry, parent));

        doBasicOperation(op);
        subSchemaTree.remove(subSchemaType);
        subSchemaTree.put(subSchemaType, newSchemaLine);
    }

    @Override
    public void modifyAttributes(String s, int i, Attributes attributes)
            throws NamingException {
        Name name = convertFromStringToName(s);
        modifyAttributes(name, i, attributes);
    }

    @Override
    public void modifyAttributes(String s, ModificationItem[] modificationItems)
            throws NamingException {
        Name name = convertFromStringToName(s);
        modifyAttributes(name, modificationItems);
    }

    @Override
    public Context createSubcontext(Name name) throws NamingException {
        DirContext subContext = createSubcontext(name, null);
        return subContext;
    }

    @Override
    public Context createSubcontext(String name) throws NamingException {
        Name n = convertFromStringToName(name);
        return createSubcontext(n);
    }

    @Override
    public void destroySubcontext(Name name) throws NamingException {
        int size = name.size();
        Hashtable<String, Object> subSchemaTree = doLookup(name
                .getPrefix(size - 1), size - 1);

        String subSchemaType = name.getSuffix(size - 1).toString()
                .toLowerCase();

        Object schema = subSchemaTree.get(jndi2ldap(subSchemaType));
        if (schema == null) {
            // Return silently.
            return;
        }

        if (level - size == 2) {
            // ldap.37=Can't delete schema root
            throw new SchemaViolationException(Messages.getString("ldap.37")); //$NON-NLS-1$
        }

        if (level == size) {
            // Return silently.
            return;
        }

        String schemaLine = schema.toString();
        if (schema instanceof Hashtable) {
            Hashtable table = (Hashtable) schema;
            schemaLine = table.get(SchemaParser.ORIG).toString();
        }

        ModifyOp op = new ModifyOp(parent.subschemasubentry);
        Name modifySchemaName = name.getPrefix(size - 1).addAll(rdn);
        BasicAttribute schemaEntry = new LdapAttribute(new BasicAttribute(
                jndi2ldap(modifySchemaName.toString()), schemaLine), parent);
        op.addModification(jndi2ldap[DirContext.REMOVE_ATTRIBUTE],
                new LdapAttribute(schemaEntry, parent));
        try {
            doBasicOperation(op);
            subSchemaTree.remove(subSchemaType);
        } catch (ReferralException e) {
            // TODO
        }
    }

    @Override
    public void destroySubcontext(String name) throws NamingException {
        destroySubcontext(convertFromStringToName(name));
    }

    private String ldap2jndi(String jndiName) {
        String ldapName = schemaLdap2Jndi.get(jndiName);
        if (null == ldapName) {
            ldapName = jndiName;
        }

        return ldapName;
    }

    private String jndi2ldap(String ldapName) {
        String jndiName = schemaJndi2Ldap.get(ldapName.toLowerCase());
        if (null == jndiName) {
            jndiName = ldapName;
        }

        return jndiName.toLowerCase();
    }

    @Override
    public NamingEnumeration<NameClassPair> list(Name name)
            throws NamingException {
        int size = name.size();

        Hashtable<String, Object> tempSchema = doLookup(name, size);

        LdapNamingEnumeration<NameClassPair> enumeration = new LdapNamingEnumeration<NameClassPair>(
                null, null);

        if (size == level - 1) {
            return enumeration;
        }

        Iterator<String> keys = tempSchema.keySet().iterator();

        while (keys.hasNext()) {
            enumeration.add(new NameClassPair(ldap2jndi(keys.next()), this
                    .getClass().getName()));
        }

        return enumeration;
    }

    @Override
    protected Name convertFromStringToName(String s)
            throws InvalidNameException {
        if (s == null) {
            // jndi.2E=The name is null
            throw new NullPointerException(Messages.getString("jndi.2E")); //$NON-NLS-1$
        }

        CompositeName name = new CompositeName(s);
        return name;
    }

    @Override
    public NamingEnumeration<NameClassPair> list(String name)
            throws NamingException {
        return list(convertFromStringToName(name));
    }

    @Override
    public NamingEnumeration<Binding> listBindings(Name name)
            throws NamingException {
        int size = name.size();

        Hashtable<String, Object> tempSchema = doLookup(name, size);

        LdapNamingEnumeration<Binding> enumeration = new LdapNamingEnumeration<Binding>(
                null, null);

        if (size == level - 1) {
            return enumeration;
        }

        Iterator<String> keys = tempSchema.keySet().iterator();

        while (keys.hasNext()) {
            enumeration.add(new Binding(ldap2jndi(keys.next()), this.getClass()
                    .getName()));
        }

        return enumeration;
    }

    @Override
    public NamingEnumeration<Binding> listBindings(String name)
            throws NamingException {
        return listBindings(convertFromStringToName(name));
    }

    private Hashtable<Name, LdapSchemaContextImpl> cachedSubSchemas = new Hashtable<Name, LdapSchemaContextImpl>();

    @Override
    public Object lookup(Name name) throws NamingException {
        // If cached, directly return cached one.
        Name targetDN = name;
        LdapSchemaContextImpl cachedSchema = cachedSubSchemas.get(targetDN);
        if (cachedSchema != null) {
            return cachedSchema;
        }
        int size = targetDN.size();
        if (size == 0) {
            return this;
        }

        Hashtable<String, Object> newSchemaTable = doLookup(name, size);

        cachedSchema = new LdapSchemaContextImpl(parent, env, targetDN,
                newSchemaTable, level - size);
        cachedSubSchemas.put(targetDN, cachedSchema);

        return cachedSchema;
    }

    // Find the subtree of schematree corresponding to the name.
    private Hashtable<String, Object> doLookup(Name name, int size)
            throws NamingException {
        Name targetDN = name;
        if (size >= level) {
            throw new NameNotFoundException(name.toString());
        }

        Hashtable<String, Object> tempSchema = schemaTable;
        Object tempValue;
        for (int i = 0; i < size; i++) {
            String key = targetDN.get(i);
            tempValue = tempSchema.get(jndi2ldap(key));
            if (tempValue == null) {
                throw new NameNotFoundException(name.toString());
            }

            if (tempValue instanceof String) {
                Hashtable<String, Object> attributesTable = SchemaParser
                        .parseValue(tempValue.toString());
                tempSchema.put(jndi2ldap(key).toLowerCase(), attributesTable);
                tempSchema = attributesTable;
            } else {
                tempSchema = (Hashtable<String, Object>) tempValue;
            }
        }

        return tempSchema;
    }

    @Override
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
        BasicAttributes attrMatcher = new BasicAttributes(true);
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

        LinkedList<String> attrValues = new LinkedList<String>();
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
                        BasicAttributes basicAttributes = new BasicAttributes(
                                true);
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
                    BasicAttributes basicAttributes = new BasicAttributes(true);
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
            if (hasAttributes2Return) {
                attrSel.addAll(Arrays.asList(as));
            }
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
                    boolean matched = true;
                    while (filters.hasMore()) {
                        Attribute filter = filters.next();
                        Object values = schemaDef.get(filter.getID());
                        /*
                         * Attribute definition will only be retrieved when it
                         * is designated in attrFilter
                         */
                        if (values == null || !match(filter, values)) {
                            matched = false;
                            break;
                        }
                    }
                    if (matched) {
                        basicAttributes = new BasicAttributes(true);
                        for (Iterator<String> iterator = schemaDef.keySet()
                                .iterator(); iterator.hasNext();) {
                            String key = iterator.next();
                            if (key.equals("orig")) {
                                continue;
                            }
                            if (hasAttributes2Return && attrSel.contains(key)
                                    || !hasAttributes2Return) {
                                basicAttributes.put(key, schemaDef.get(key));
                            }
                        }
                        SearchResult pair = new SearchResult(id, null,
                                basicAttributes);
                        enumeration.add(pair);
                    }
                }
            } else {
                for (int i = 0; i < attrValues.size(); i++) {
                    Hashtable<String, Object> schemaDef = schemas
                            .get(attrValues.get(i));
                    basicAttributes = new BasicAttributes(true);
                    for (Iterator<String> iterator = schemaDef.keySet()
                            .iterator(); iterator.hasNext();) {
                        String key = iterator.next();
                        if (key.equals("orig")) {
                            continue;
                        }
                        if (hasAttributes2Return && attrSel.contains(key)
                                || !hasAttributes2Return) {
                            basicAttributes.put(key, schemaDef.get(key));
                        }
                    }
                    SearchResult pair = new SearchResult(attrValues.get(i),
                            null, basicAttributes);
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
        ArrayList<Object> v = null;
        if (values instanceof ArrayList) {
            v = (ArrayList<Object>) values;
        } else {
            v = new ArrayList<Object>();
            v.add(values);
        }

        while (attrValues.hasMore()) {
            Object attrValue = attrValues.next();
            for (int i = 0; i < v.size(); i++) {
                if (attrValue.equals("*") || attrValue.equals(v.get(i))) {
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
        if (filter.length() == 0) {
            throw new StringIndexOutOfBoundsException();
        }
        if (!filter.startsWith("(")) {
            StringBuilder filterWrapper = new StringBuilder("(");
            filterWrapper.append(filter).append(")");
            filter = filterWrapper.toString();
        }

        if (null == searchControls) {
            searchControls = new SearchControls();
        }

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

        BasicAttributes matchingAttrs = new BasicAttributes(true);
        extractMatchingAttributes(f, matchingAttrs);

        return search(name, matchingAttrs, searchControls
                .getReturningAttributes());
    }

    private void extractMatchingAttributes(Filter f,
            BasicAttributes matchingAttrs) {
        if (!f.isLeaf()) {
            List<Filter> children = f.getChildren();
            for (Iterator<Filter> iter = children.iterator(); iter.hasNext();) {
                extractMatchingAttributes(iter.next(), matchingAttrs);
            }
        } else {
            Object value = f.getValue();
            if (value instanceof AttributeTypeAndValuePair) {
                AttributeTypeAndValuePair pair = (AttributeTypeAndValuePair) value;
                matchingAttrs.put(pair.getType(), pair.getValue());
            } else {
                matchingAttrs.put((String) value, "*");
            }
        }
    }
}
