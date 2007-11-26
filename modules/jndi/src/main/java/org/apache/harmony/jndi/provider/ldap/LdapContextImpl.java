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

import java.io.IOException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.naming.AuthenticationNotSupportedException;
import javax.naming.Binding;
import javax.naming.CannotProceedException;
import javax.naming.CommunicationException;
import javax.naming.CompositeName;
import javax.naming.ConfigurationException;
import javax.naming.Context;
import javax.naming.InvalidNameException;
import javax.naming.Name;
import javax.naming.NameClassPair;
import javax.naming.NameNotFoundException;
import javax.naming.NameParser;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.RefAddr;
import javax.naming.Reference;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InvalidSearchFilterException;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.event.EventContext;
import javax.naming.event.EventDirContext;
import javax.naming.event.NamespaceChangeListener;
import javax.naming.event.NamingEvent;
import javax.naming.event.NamingExceptionEvent;
import javax.naming.event.NamingListener;
import javax.naming.event.ObjectChangeListener;
import javax.naming.ldap.Control;
import javax.naming.ldap.ControlFactory;
import javax.naming.ldap.ExtendedRequest;
import javax.naming.ldap.ExtendedResponse;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.ManageReferralControl;
import javax.naming.ldap.Rdn;
import javax.naming.ldap.UnsolicitedNotificationEvent;
import javax.naming.ldap.UnsolicitedNotificationListener;
import javax.naming.spi.DirectoryManager;
import javax.naming.spi.NamingManager;

import org.apache.harmony.jndi.internal.nls.Messages;
import org.apache.harmony.jndi.internal.parser.AttributeTypeAndValuePair;
import org.apache.harmony.jndi.internal.parser.LdapNameParser;
import org.apache.harmony.jndi.provider.ldap.asn1.Utils;
import org.apache.harmony.jndi.provider.ldap.event.ECNotificationControl;
import org.apache.harmony.jndi.provider.ldap.event.PersistentSearchResult;
import org.apache.harmony.jndi.provider.ldap.parser.FilterParser;
import org.apache.harmony.jndi.provider.ldap.parser.ParseException;
import org.apache.harmony.jndi.provider.ldap.sasl.SaslBind;

/**
 * This context implements LdapContext, it's main entry point of all JNDI ldap
 * operations.
 */
public class LdapContextImpl implements LdapContext, EventDirContext {

    /**
     * ldap connection
     */
    private LdapClient client;
    
    private boolean isClosed;

    /**
     * name of the context
     */
    protected Name contextDn;

    private Control[] requestControls;

    private Control[] responseControls;

    /**
     * environment properties for this context
     */
    protected Hashtable<Object, Object> env;

    /**
     * name parser for this context
     */
    private NameParser parser;

    /**
     * connection controls for this context
     */
    private Control[] connCtls;

    private HashMap<NamingListener, List<Integer>> listeners;

    private List<UnsolicitedNotificationListener> unls;

    private static final Control NON_CRITICAL_MANAGE_REF_CONTROL = new ManageReferralControl(
            Control.NONCRITICAL);

    private static final String LDAP_DELETE_RDN = "java.naming.ldap.deleteRDN"; //$NON-NLS-1$

    private static final String LDAP_DEREF_ALIASES = "java.naming.ldap.derefAliases"; //$NON-NLS-1$

    private static final String LDAP_TYPES_ONLY = "java.naming.ldap.typesOnly"; //$NON-NLS-1$

    /**
     * Some properties, such as 'java.naming.security.authentication', changed
     * by <code>Context.addToEnvironment</code> or
     * <code>Context.removeFromEnvironment</code> may affect connection with
     * LDAP server. This variable contains all such properties, which need
     * re-communication with LDAP server after changing.
     */
    private static final HashSet<String> connectionProperties = new HashSet<String>();

    static {
        connectionProperties.add(Context.SECURITY_AUTHENTICATION);
        connectionProperties.add(Context.SECURITY_CREDENTIALS);
        connectionProperties.add(Context.SECURITY_PRINCIPAL);
        connectionProperties.add(Context.SECURITY_PROTOCOL);
        connectionProperties.add("java.naming.ldap.factory.socket");
    }

    /**
     * construct a new inherit <code>LdapContextImpl</code>
     * 
     * @param context
     * @param environment
     * @param dn
     * @throws InvalidNameException
     */
    public LdapContextImpl(LdapContextImpl context,
            Hashtable<Object, Object> environment, String dn)
            throws InvalidNameException {
        initial(context.client, environment, dn);
    }

    /**
     * construct a new <code>LdapContextImpl</code> with a fresh
     * <code>LdapClient</code> which didn't do ldap bind operation yet.
     * 
     * @param client
     * @param environment
     * @param dn
     * @throws NamingException
     */
    public LdapContextImpl(LdapClient client,
            Hashtable<Object, Object> environment, String dn)
            throws NamingException {
        initial(client, environment, dn);

        try {
            doBindOperation(connCtls);
        } catch (IOException e) {
            CommunicationException ex = new CommunicationException();
            ex.setRootCause(e);
            throw ex;
        }
    }

    private void initial(LdapClient ldapClient,
            Hashtable<Object, Object> environment, String dn)
            throws InvalidNameException {
        this.client = ldapClient;
        if (environment == null) {
            this.env = new Hashtable<Object, Object>();
        } else {
            this.env = (Hashtable<Object, Object>) environment.clone();
        }

        contextDn = new LdapName(dn);
        parser = new LdapNameParser(dn);
    }

    /**
     * Perform a LDAP Bind operation.
     * 
     * @param env
     * @throws IOException
     * @throws IOException
     * @throws NamingException
     * @throws ParseException
     */
    private void doBindOperation(Control[] connCtsl) throws IOException,
            NamingException {

        SaslBind saslBind = new SaslBind();
        LdapResult result = null;

        SaslBind.AuthMech authMech = saslBind.valueAuthMech(env);
        if (authMech == SaslBind.AuthMech.None) {
            BindOp bind = new BindOp("", "", null, null);
            client.doOperation(bind, connCtsl);
            result = bind.getResult();
        } else if (authMech == SaslBind.AuthMech.Simple) {
            String principal = (String) env.get(Context.SECURITY_PRINCIPAL);
            String credential = Utils.getString(env
                    .get(Context.SECURITY_CREDENTIALS));
            BindOp bind = new BindOp(principal, credential, null, null);
            client.doOperation(bind, connCtsl);
            result = bind.getResult();
        } else if (authMech == SaslBind.AuthMech.SASL) {
            result = saslBind.doSaslBindOperation(env, client, connCtsl);
        }

        if (LdapUtils.getExceptionFromResult(result) != null) {
            throw LdapUtils.getExceptionFromResult(result);
        }
    }

    public ExtendedResponse extendedOperation(ExtendedRequest request)
            throws NamingException {
        // TODO not yet implemented
        throw new NotYetImplementedException();
    }

    public Control[] getConnectControls() throws NamingException {
        // TODO not yet implemented
        throw new NotYetImplementedException();
    }

    public Control[] getRequestControls() throws NamingException {
        return copyControls(requestControls);
    }

    public Control[] getResponseControls() throws NamingException {
        return copyControls(responseControls);
    }

    private Control[] copyControls(Control[] controls) {
        if (controls == null) {
            return null;
        }

        Control[] rtValue = new Control[controls.length];
        System.arraycopy(controls, 0, rtValue, 0, controls.length);
        return rtValue;
    }

    public LdapContext newInstance(Control[] ac) throws NamingException {
        // TODO not yet implemented
        throw new NotYetImplementedException();
    }

    public void reconnect(Control[] ac) throws NamingException {
        // TODO not yet implemented
        throw new NotYetImplementedException();
    }

    public void setRequestControls(Control[] controls) throws NamingException {
        boolean hasManageDsaITConntrol = false;

        if (env.get(Context.REFERRAL) == null
                || env.get(Context.REFERRAL).equals("ignore")) {
            hasManageDsaITConntrol = true;
        }

        if (controls == null) {
            if (hasManageDsaITConntrol) {
                requestControls = new Control[] { NON_CRITICAL_MANAGE_REF_CONTROL };
            } else {
                requestControls = null;
            }
            return;
        }

        if (hasManageDsaITConntrol) {
            requestControls = new Control[controls.length + 1];
            System.arraycopy(controls, 0, requestControls, 0, controls.length);
            requestControls[controls.length] = NON_CRITICAL_MANAGE_REF_CONTROL;
        } else {
            requestControls = new Control[controls.length];
            System.arraycopy(controls, 0, requestControls, 0, controls.length);
        }
    }

    public void bind(Name name, Object obj, Attributes attributes)
            throws NamingException {
        // TODO not yet implemented
        throw new NotYetImplementedException();
    }

    public void bind(String s, Object obj, Attributes attributes)
            throws NamingException {
        bind(convertFromStringToName(s), obj, attributes);
    }

    public DirContext createSubcontext(Name name, Attributes attributes)
            throws NamingException {
        checkName(name);

        if (name instanceof CompositeName && name.size() > 1) {
            /*
             * multi ns, find next ns context, delegate operation to the next
             * context
             */
            DirContext nns = (DirContext) findNnsContext(name);
            Name remainingName = name.getSuffix(1);
            return nns.createSubcontext(remainingName, attributes);
        }

        /*
         * there is only one ldap ns
         */

        if (attributes == null) {
            attributes = new BasicAttributes();
            Attribute attr = new LdapAttribute("objectClass", this);
            attr.add("top");
            attr.add("javaContainer");
            attributes.put(attr);
        }

        // get absolute dn name
        String targetDN = getTargetDN(name, contextDn);
        // merge attributes from dn and args
        Attributes attrs = mergeAttributes(attributes,
                getAttributesFromDN(name));

        // convert to LdapAttribute
        List<LdapAttribute> la = new ArrayList<LdapAttribute>(attrs.size());
        NamingEnumeration<? extends Attribute> enu = attrs.getAll();
        while (enu.hasMore()) {
            la.add(new LdapAttribute(enu.next(), this));
        }

        // do add operation
        AddOp op = new AddOp(targetDN, la);

        doBasicOperation(op);

        LdapResult result = op.getResult();
        return new LdapContextImpl(this, env, result.getMachedDN());
    }

    /**
     * merge two instanceof <code>Attributes</code> to one
     * 
     * @param first
     * @param second
     * @return
     * @throws NamingException
     */
    private Attributes mergeAttributes(Attributes first, Attributes second)
            throws NamingException {
        if (first == null) {
            return second;
        }

        if (second == null) {
            return first;
        }

        BasicAttributes attrs = new BasicAttributes();
        NamingEnumeration<? extends Attribute> enu = first.getAll();
        while (enu.hasMore()) {
            attrs.put(enu.next());
        }

        enu = second.getAll();
        while (enu.hasMore()) {
            Attribute element = enu.next();
            element = mergeAttribute(element, attrs.get(element.getID()));
            attrs.put(element);
        }

        return attrs;
    }

    /**
     * merge two instance of <code>Attribute</code> to one
     * 
     * @param first
     * @param second
     * @return
     * @throws NamingException
     */
    private Attribute mergeAttribute(Attribute first, Attribute second)
            throws NamingException {
        if (first == null) {
            return second;
        }

        if (second == null) {
            return first;
        }

        Attribute attr = new LdapAttribute(first.getID(), this);
        NamingEnumeration<?> enu = first.getAll();
        while (enu.hasMore()) {
            attr.add(enu.next());
        }

        enu = second.getAll();
        while (enu.hasMore()) {
            attr.add(enu.next());
        }

        return attr;
    }

    public DirContext createSubcontext(String s, Attributes attributes)
            throws NamingException {
        return createSubcontext(convertFromStringToName(s), attributes);
    }

    public Attributes getAttributes(Name name) throws NamingException {
        return getAttributes(name, null);
    }

    public Attributes getAttributes(Name name, String[] as)
            throws NamingException {
        checkName(name);

        if (name instanceof CompositeName && name.size() > 1) {
            if (!(name.getPrefix(0) instanceof LdapName)) {
                throw new InvalidNameException(Messages.getString("ldap.26")); //$NON-NLS-1$
            }
            /*
             * multi ns, find next ns context, delegate operation to the next
             * context
             */
            DirContext nns = (DirContext) findNnsContext(name);
            Name remainingName = name.getSuffix(1);
            return nns.getAttributes(remainingName, as);
        }

        /*
         * there is only one ldap ns
         */
        // absolute dn name to list
        String targetDN = getTargetDN(name, contextDn);

        // construct one-level search using filter "(objectclass=*)"
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.OBJECT_SCOPE);

        /*
         * none should be retrieved, send OID "1.1" to server, according to RFC
         * 2251, 4.5.1
         */
        if (as != null && as.length == 0) {
            // "1.1" means no attributes should return
            as = new String[] { "1.1" }; //$NON-NLS-1$
        }
        controls.setReturningAttributes(as);

        Filter filter = new Filter(Filter.PRESENT_FILTER);
        filter.setValue("objectClass");

        LdapSearchResult result = doSearch(targetDN, filter, controls);
        Iterator<Attributes> it = result.getEntries().values().iterator();
        if (it.hasNext()) {
            // FIXME: there must be only one Attributes?
            return it.next();
        } else if (result.getException() != null) {
            throw result.getException();
        }

        // no attribute retrieved from server, return a empty Attributes
        return new BasicAttributes();
    }

    public Attributes getAttributes(String s) throws NamingException {
        return getAttributes(convertFromStringToName(s));
    }

    public Attributes getAttributes(String s, String[] as)
            throws NamingException {
        return getAttributes(convertFromStringToName(s), as);
    }


    public static Hashtable<String, Hashtable<String, Hashtable<String, Object>>> schemaTree = new Hashtable<String, Hashtable<String, Hashtable<String, Object>>>();

    private LdapSchemaContextImpl ldapSchemaCtx = null;

    protected String subschemasubentry = null;

    public DirContext getSchema(Name name) throws NamingException {
        checkName(name);
        if (null != ldapSchemaCtx)
            return ldapSchemaCtx;

        SearchControls searchControls = new SearchControls();
        SearchOp search = null;
        Filter filter = null;
        FilterParser filterParser = null;
        LdapSearchResult sre = null;
        Map<String, Attributes> names = null;
        Set<String> keyset = null;

        if (name.size() != 0) {
            subschemasubentry = name.toString() + "," + contextDn.toString();
        }
        if (null == subschemasubentry) {
            filterParser = new FilterParser("(objectClass=*)");
            try {
                filter = filterParser.parse();
            } catch (ParseException e) {
                InvalidSearchFilterException ex = new InvalidSearchFilterException(
                        Messages.getString("ldap.29")); //$NON-NLS-1$
                ex.setRootCause(e);
                throw ex;
            }

            searchControls.setSearchScope(SearchControls.OBJECT_SCOPE);
            searchControls.setReturningAttributes(new String[] {
                    "namingContexts", "subschemaSubentry", "altServer", });
            search = new SearchOp("", searchControls, filter);

            try {
                client.doOperation(search, requestControls);
            } catch (IOException e) {
                CommunicationException ex = new CommunicationException(e
                        .getMessage());
                ex.setRootCause(e);
                if (search.getSearchResult().isEmpty()) {
                    throw ex;
                }
                search.getSearchResult().setException(ex);
            }

            sre = search.getSearchResult();
            names = sre.getEntries();

            keyset = names.keySet();
            schemaRoot: for (Iterator<String> iterator = keyset.iterator(); iterator
                    .hasNext();) {
                String key = iterator.next();
                Attributes as = names.get(key);
                NamingEnumeration<String> ids = as.getIDs();
                while (ids.hasMore()) {
                    String id = ids.next();
                    if (id.equalsIgnoreCase("subschemasubentry")) {
                        subschemasubentry = (String) as.get(id).get();
                        break schemaRoot;
                    }
                }
            }
        }

        searchControls.setSearchScope(SearchControls.OBJECT_SCOPE);
        searchControls.setReturningAttributes(new String[] { "objectclasses",
                "attributetypes", "matchingrules", "ldapsyntaxes" });
        searchControls.setReturningObjFlag(true);
        filterParser = new FilterParser("(objectClass=subschema)");
        try {
            filter = filterParser.parse();
        } catch (ParseException e) {
            InvalidSearchFilterException ex = new InvalidSearchFilterException(
                    Messages.getString("ldap.29")); //$NON-NLS-1$
            ex.setRootCause(e);
            throw ex;
        }
        search = new SearchOp(subschemasubentry, searchControls, filter);

        try {
            client.doOperation(search, requestControls);
        } catch (IOException e) {
            CommunicationException ex = new CommunicationException(e
                    .getMessage());
            ex.setRootCause(e);
            if (search.getSearchResult().isEmpty()) {
                throw ex;
            }
            search.getSearchResult().setException(ex);
        }
        if (search.getResult().getResultCode() == LdapResult.INVALID_DN_SYNTAX) {
            throw new InvalidNameException(Messages.getString("ldap.34"));
        }
        sre = search.getSearchResult();
        names = sre.getEntries();

        keyset = names.keySet();
        for (Iterator<String> iterator = keyset.iterator(); iterator.hasNext();) {
            String key = iterator.next();
            Attributes as = names.get(key);
            NamingEnumeration<String> ids = as.getIDs();

            while (ids.hasMoreElements()) {
                String schemaType = ids.nextElement();
                if (!schemaTree.contains(schemaType.toLowerCase())) {
                    schemaTree.put(schemaType.toLowerCase(),
                            new Hashtable<String, Hashtable<String, Object>>());
                }
                Hashtable<String, Hashtable<String, Object>> schemaDefs = schemaTree
                        .get(schemaType.toLowerCase());
                LdapAttribute attribute = (LdapAttribute) as.get(schemaType);
                for (int i = 0; i < attribute.size(); i++) {
                    String value = (String) attribute.get(i);
                    parseValue(schemaType, value.toLowerCase(), schemaDefs);
                }
            }
        }
        ldapSchemaCtx = new LdapSchemaContextImpl(this, env, name);
        return ldapSchemaCtx;
    }

    Hashtable<String, Object> findSchemaDefInfo(String schemaType,
            String className) {
        Hashtable<String, Hashtable<String, Object>> schemaDefs = schemaTree
                .get(schemaType);
        Hashtable<String, Object> schemaDef = schemaDefs.get(className);
        return schemaDef;
    }

    /*
     * Sample schema value from Openldap server is ( 2.5.13.8 NAME
     * 'numericStringMatch' SYNTAX 1.3.6.1.4.1.1466.115.121.1.36 ) TODO check
     * with RFC to see whether all the schema definition has been catered for
     */
    private static void parseValue(String schemaType,
            String value,
            Hashtable<String, Hashtable<String, Object>> schemaDefs) {
        StringTokenizer st = new StringTokenizer(value);
        // Skip (
        st.nextToken();

        String oid = st.nextToken();

        Hashtable<String, Object> schemaDef = new Hashtable<String, Object>();
        schemaDef.put("orig", value);
        schemaDef.put("numericoid", oid);
        String token = null;
        ArrayList<String> values = null;
        StringBuilder desc = new StringBuilder();
        while (st.hasMoreTokens()) {
            String attrName = st.nextToken();
            if (attrName.startsWith("x-")) {
                token = st.nextToken();
                // remove the ending ' symbol
                token = token.substring(0, token.length() - 1);
                schemaDef.put(attrName, token);
            }
            if (attrName.equals("usage") || attrName.equals("equality")
                    || attrName.equals("syntax") || attrName.equals("ordering")
                    || attrName.equals("substr")) {
                token = st.nextToken();
                schemaDef.put(attrName, token);
            }
            if (attrName.equals("desc")) {
                token = st.nextToken();

                // remove the leading ' symbol
                if (token.startsWith("'"))
                    token = token.substring(1);
                while (!token.endsWith("'")) {
                    desc.append(token).append(" ");
                    token = st.nextToken();
                }

                // remove the ending ' symbol
                desc.append(token.substring(0, token.length() - 1));
                schemaDef.put(attrName, desc.toString());
                desc.delete(0, desc.length());
            }
            if (attrName.equals("name")) {
                token = st.nextToken();
                values = new ArrayList<String>();
                // Name has multiple values
                if (token.startsWith("(")) {
                    token = st.nextToken();
                    while (!token.equals(")")) {
                        // remove the leading ' symbol
                        if (token.startsWith("'"))
                            token = token.substring(1);
                        while (!token.endsWith("'")) {
                            desc.append(token).append(" ");
                            token = st.nextToken();
                        }

                        // remove the ending ' symbol
                        desc.append(token.substring(0, token.length() - 1));
                        values.add(desc.toString());
                        desc.delete(0, desc.length());

                        token = st.nextToken();
                    }
                } else {
                    // remove the leading ' symbol
                    if (token.startsWith("'"))
                        token = token.substring(1);
                    while (!token.endsWith("'")) {
                        desc.append(token).append(" ");
                        token = st.nextToken();
                    }

                    // remove the ending ' symbol
                    desc.append(token.substring(0, token.length() - 1));
                    values.add(desc.toString());
                    desc.delete(0, desc.length());
                }
                schemaDef.put(attrName, values);
                if (schemaType
                        .equalsIgnoreCase(LdapSchemaContextImpl.LDAP_SYNTAXES)) {
                    schemaDefs.put(oid, schemaDef);
                } else {
                    schemaDefs.put(values.get(0), schemaDef);
                }
            }
            if (attrName.equals("must") || attrName.equals("sup")
                    || attrName.equals("may")) {
                token = st.nextToken();
                values = new ArrayList<String>();
                // has multiple values
                if (token.startsWith("(")) {
                    token = st.nextToken();
                    while (!token.equals(")")) {
                        if (!token.equals("$"))
                            values.add(token);
                        token = st.nextToken();
                    }
                } else {
                    values.add(token);
                }
                schemaDef.put(attrName, values);
            }
            if (attrName.equals("abstract") || attrName.equals("structual")
                    || attrName.equals("auxiliary")
                    || attrName.equals("single-value")
                    || attrName.equals("no-user-modification")) {
                schemaDef.put(attrName, "true");
            }
        }
        if (!schemaDef.keySet().contains("name")) {
            schemaDefs.put(oid, schemaDef);
        }
    }

    public DirContext getSchema(String s) throws NamingException {
        return getSchema(new CompositeName(s));
    }

    DirContext getSchemaAttributeDefinition(String name) throws NamingException {
        if (null == ldapSchemaCtx) {
            getSchema("");
        }
        Hashtable<String, Object> attrDef = findSchemaDefInfo(
                LdapSchemaContextImpl.ATTRIBUTE_TYPES, name);

        return new LdapSchemaAttrDefContextImpl(new CompositeName(name), env,
                attrDef, this);
    }
    public DirContext getSchemaClassDefinition(Name name)
            throws NamingException {
        if (null == ldapSchemaCtx) {
            getSchema(name);
        }

        Hashtable<String, ArrayList<String>> classTree = new Hashtable<String, ArrayList<String>>();

        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.OBJECT_SCOPE);
        searchControls.setReturningAttributes(new String[] { "objectClass", });
        searchControls.setReturningObjFlag(false);
        FilterParser parser = new FilterParser("(objectClass=*)");
        Filter filter = null;
        try {
            filter = parser.parse();
        } catch (ParseException e1) {
            // Should not throw this excption
        }
        String targetDN = getTargetDN(name, contextDn);
        SearchOp search = new SearchOp(targetDN, searchControls, filter);

        try {
            client.doOperation(search, requestControls);
        } catch (IOException e) {
            CommunicationException ex = new CommunicationException(e
                    .getMessage());
            ex.setRootCause(e);
            if (search.getSearchResult().isEmpty()) {
                throw ex;
            }
            search.getSearchResult().setException(ex);
        }
        LdapSearchResult sre = search.getSearchResult();
        Map<String, Attributes> names = sre.getEntries();

        Set<String> keyset = names.keySet();
        for (Iterator<String> iterator = keyset.iterator(); iterator.hasNext();) {
            String key = iterator.next();
            Attributes as = names.get(key);
            NamingEnumeration<String> ids = as.getIDs();

            while (ids.hasMoreElements()) {
                String schemaType = ids.nextElement();
                if (!classTree.contains(schemaType)) {
                    classTree.put(schemaType, new ArrayList());
                }
                ArrayList<String> classDefs = classTree.get(schemaType);
                LdapAttribute attribute = (LdapAttribute) as.get(schemaType);
                for (int i = 0; i < attribute.size(); i++) {
                    String value = (String) attribute.get(i);
                    classDefs.add(value);
                }
            }
        }

        return new LdapSchemaClassDefContextImpl(new CompositeName(targetDN),
                env, classTree, this);
    }

    public DirContext getSchemaClassDefinition(String s) throws NamingException {
        return getSchemaClassDefinition(convertFromStringToName(s));
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

    public void modifyAttributes(Name name, ModificationItem[] modificationItems)
            throws NamingException {
        checkName(name);

        if (modificationItems == null) {
            // FIXME: spec say ModificationItem may not be null, but ri
            // silence in this case
            throw new NullPointerException(Messages.getString("ldap.27")); //$NON-NLS-1$
        }

        if (name instanceof CompositeName && name.size() > 1) {
            /*
             * multi ns, find next ns context, delegate operation to the next
             * context
             */
            DirContext nns = (DirContext) findNnsContext(name);
            Name remainingName = name.getSuffix(1);
            nns.modifyAttributes(remainingName, modificationItems);
            return;
        }

        /*
         * there is only one ldap ns
         */
        // get absolute dn name
        String targetDN = getTargetDN(name, contextDn);
        ModifyOp op = new ModifyOp(targetDN);
        for (ModificationItem item : modificationItems) {
            switch (item.getModificationOp()) {
            case DirContext.ADD_ATTRIBUTE:
                op.addModification(0, new LdapAttribute(item.getAttribute(), this));
                break;
            case DirContext.REMOVE_ATTRIBUTE:
                op.addModification(1, new LdapAttribute(item.getAttribute(), this));
                break;
            case DirContext.REPLACE_ATTRIBUTE:
                op.addModification(2, new LdapAttribute(item.getAttribute(), this));
                break;
            default:
                throw new IllegalArgumentException(Messages.getString(
                        "jndi.14", item.getModificationOp())); //$NON-NLS-1$
            }
        }

        doBasicOperation(op);
    }

    public void modifyAttributes(String s, int i, Attributes attributes)
            throws NamingException {
        modifyAttributes(convertFromStringToName(s), i, attributes);
    }

    public void modifyAttributes(String s, ModificationItem[] modificationItems)
            throws NamingException {
        modifyAttributes(convertFromStringToName(s), modificationItems);
    }

    public void rebind(Name name, Object obj, Attributes attributes)
            throws NamingException {
        // TODO not yet implemented
        throw new NotYetImplementedException();
    }

    public void rebind(String s, Object obj, Attributes attributes)
            throws NamingException {
        rebind(convertFromStringToName(s), obj, attributes);
    }

    public NamingEnumeration<SearchResult> search(Name name,
            Attributes attributes) throws NamingException {
        return search(name, attributes, null);
    }

    public NamingEnumeration<SearchResult> search(Name name,
            Attributes attributes, String[] as) throws NamingException {
        checkName(name);

        if (name instanceof CompositeName && name.size() > 1) {
            /*
             * multi ns, find next ns context, delegate operation to the next
             * context
             */
            DirContext nns = (DirContext) findNnsContext(name);
            Name remainingName = name.getSuffix(1);
            return nns.search(remainingName, attributes, as);
        }

        /*
         * there is only one ldap ns
         */
        // get absolute dn name
        String targetDN = getTargetDN(name, contextDn);
        Filter filter = null;

        // construct filter
        if (attributes == null || attributes.size() == 0) {
            filter = new Filter(Filter.PRESENT_FILTER);
            filter.setValue("objectClass");
        } else {
            NamingEnumeration<? extends Attribute> attrs = attributes.getAll();
            filter = new Filter(Filter.AND_FILTER);
            while (attrs.hasMore()) {
                Attribute attr = attrs.next();
                String type = attr.getID();
                NamingEnumeration<?> enuValues = attr.getAll();
                while (enuValues.hasMore()) {
                    Object value = enuValues.next();
                    Filter child = new Filter(Filter.EQUALITY_MATCH_FILTER);
                    child.setValue(new AttributeTypeAndValuePair(type, value));
                    filter.addChild(child);
                }
            }
        }

        SearchControls controls = new SearchControls();
        controls.setReturningAttributes(as);
        LdapSearchResult result = doSearch(targetDN, filter, controls);

        List<SearchResult> list = new ArrayList<SearchResult>();
        Map<String, Attributes> entries = result.getEntries();
        Name tempName = new LdapName(contextDn.toString());
        tempName.addAll(name);
        String baseDN = tempName.toString();
        for (String dn : entries.keySet()) {
            String relativeName = convertToRelativeName(dn, baseDN);
            SearchResult sr = new SearchResult(relativeName, null, entries
                    .get(dn));
            sr.setNameInNamespace(dn);
            list.add(sr);
        }

        return new LdapNamingEnumeration<SearchResult>(list, result
                .getException());
    }

    public NamingEnumeration<SearchResult> search(Name name, String filter,
            Object[] objs, SearchControls searchControls)
            throws NamingException {
        checkName(name);

        if (name instanceof CompositeName && name.size() > 1) {
            /*
             * multi ns, find next ns context, delegate operation to the next
             * context
             */
            DirContext nns = (DirContext) findNnsContext(name);
            Name remainingName = name.getSuffix(1);
            return nns.search(remainingName, filter, objs, searchControls);
        }

        /*
         * there is only one ldap ns
         */
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
        Name tempName = new LdapName(contextDn.toString());
        tempName.addAll(name);
        String baseDN = tempName.toString();
        for (String dn : entries.keySet()) {
            String relativeName = convertToRelativeName(dn, baseDN);
            SearchResult sr = new SearchResult(relativeName, null, entries
                    .get(dn));
            sr.setNameInNamespace(dn);
            list.add(sr);
        }

        return new LdapNamingEnumeration<SearchResult>(list, result
                .getException());
    }

    public NamingEnumeration<SearchResult> search(Name name, String filter,
            SearchControls searchControls) throws NamingException {
        return search(name, filter, new Object[0], searchControls);
    }

    public NamingEnumeration<SearchResult> search(String name,
            Attributes attributes) throws NamingException {
        return search(convertFromStringToName(name), attributes);
    }

    public NamingEnumeration<SearchResult> search(String name,
            Attributes attributes, String[] as) throws NamingException {
        return search(convertFromStringToName(name), attributes, as);
    }

    public NamingEnumeration<SearchResult> search(String name, String filter,
            Object[] objs, SearchControls searchControls)
            throws NamingException {
        return search(convertFromStringToName(name), filter, objs,
                searchControls);
    }

    public NamingEnumeration<SearchResult> search(String name, String filter,
            SearchControls searchControls) throws NamingException {
        return search(convertFromStringToName(name), filter, searchControls);
    }

    LdapSearchResult doSearch(SearchOp op) throws NamingException {
        if (env.get(LDAP_DEREF_ALIASES) != null) {
            String derefAliases = (String) env.get(LDAP_DEREF_ALIASES);
            if (derefAliases.equals("always")) {
                op.setDerefAliases(0);
            } else if (derefAliases.equals("never")) {
                op.setDerefAliases(1);
            } else if (derefAliases.equals("finding")) {
                op.setDerefAliases(2);
            } else if (derefAliases.equals("searching")) {
                op.setDerefAliases(3);
            } else {
                throw new IllegalArgumentException(Messages.getString(
                        "ldap.30", new Object[] { env.get(LDAP_DEREF_ALIASES), //$NON-NLS-1$
                                LDAP_DEREF_ALIASES }));
            }

        }

        if (env.containsKey(LDAP_TYPES_ONLY)) {
            String typesOnly = (String) env.get(LDAP_TYPES_ONLY);
            if ("true".equals(typesOnly)) {
                op.setTypesOnly(true);
            } else if ("false".equals(typesOnly)) {
                op.setTypesOnly(false);
            } else {
                throw new IllegalArgumentException(Messages.getString(
                        "ldap.30", new Object[] { env.get(LDAP_TYPES_ONLY), //$NON-NLS-1$
                                LDAP_TYPES_ONLY }));
            }
        }

        LdapMessage message = null;
        try {
            message = client.doOperation(op, requestControls);
        } catch (IOException e) {
            CommunicationException ex = new CommunicationException(e
                    .getMessage());
            ex.setRootCause(e);
            if (op.getSearchResult() == null || op.getSearchResult().isEmpty()) {
                throw ex;
            }
            op.getSearchResult().setException(ex);
            // occurs errors, just return, doesn't deal with referral and
            // controls
            return op.getSearchResult();
        }

        // TODO: assume response controls returned in last message, it may
        // be not correct
        Control[] rawControls = message.getControls();
        responseControls = narrowingControls(rawControls);

        LdapResult result = op.getResult();

        op.getSearchResult().setException(
                LdapUtils.getExceptionFromResult(result));

        // has error, not deal with referrals
        if (op.getSearchResult().getException() != null) {
            return op.getSearchResult();
        }

        // baseObject is not located at the server
        if (result.getResultCode() == 10) {
            // TODO deal with referrals
            throw new NotYetImplementedException();
        }

        // there are SearchResultReference in search result
        if (op.getSearchResult().getRefURLs() != null
                && op.getSearchResult().getRefURLs().size() != 0) {
            // TODO deal with referrals
            throw new NotYetImplementedException();
        }

        return op.getSearchResult();
    }

    LdapSearchResult doSearch(String dn, Filter filter, SearchControls controls)
            throws NamingException {
        SearchOp op = new SearchOp(dn, controls, filter);
        return doSearch(op);
    }

    public Object addToEnvironment(String s, Object o) throws NamingException {
        Object preValue = env.put(s, o);

        // if preValue equals o, do nothing
        if ((preValue != null && preValue.equals(o))
                || (preValue == null && o == null)) {
            return preValue;
        }

        updateEnvironment(s);

        return preValue;
    }

    public void bind(Name n, Object o) throws NamingException {
        bind(n, o, null);
    }

    public void bind(String s, Object o) throws NamingException {
        bind(convertFromStringToName(s), o);
    }

    public void close() throws NamingException {
        if (!isClosed) {
            isClosed = true;
            client = null;
        }
    }

    /**
     * Only instance of LdapName or CompositeName are acceptable. If both
     * <code>name</code> and <code>prefix</code> are LdapName, a new
     * LdapName instance composed of the two name will be return , else a
     * CompositeName will be return.
     */
    public Name composeName(Name name, Name prefix) throws NamingException {
        if (name == null || prefix == null) {
            // jndi.2E=The name is null
            throw new NullPointerException(Messages.getString("jndi.2E")); //$NON-NLS-1$
        }

        Name result = null;
        if (name instanceof LdapName && prefix instanceof LdapName) {
            result = (LdapName) prefix.clone();
            result.addAll(name);
        } else if (name instanceof LdapName && prefix instanceof CompositeName) {
            result = new CompositeName();
            result.addAll(prefix);
            result.add(name.toString());
        } else if (prefix instanceof LdapName && name instanceof CompositeName) {
            result = new CompositeName();
            result.add(prefix.toString());
            result.addAll(name);
        } else if (prefix instanceof CompositeName
                && name instanceof CompositeName) {
            result = new CompositeName();
            result.addAll(prefix);
            result.addAll(name);
        } else {
            throw new NamingException(Messages.getString("ldap.26")); //$NON-NLS-1$
        }
        return result;
    }

    public String composeName(String s, String pfx) throws NamingException {
        return composeName(convertFromStringToName(s),
                convertFromStringToName(pfx)).toString();
    }

    public Context createSubcontext(Name name) throws NamingException {
        return createSubcontext(name, null);
    }

    private Attributes getAttributesFromDN(Name name)
            throws InvalidNameException {
        if (name instanceof LdapName) {
            Rdn rdn = ((LdapName) name).getRdn(name.size() - 1);
            return rdn.toAttributes();
        }

        if (name instanceof CompositeName) {
            LdapName lname = new LdapName(name.get(0));
            Rdn rdn = lname.getRdn(lname.size() - 1);
            return rdn.toAttributes();
        }

        throw new InvalidNameException(Messages.getString("ldap.26")); //$NON-NLS-1$
    }

    public Context createSubcontext(String s) throws NamingException {
        return createSubcontext(convertFromStringToName(s));
    }

    public void destroySubcontext(Name name) throws NamingException {
        checkName(name);

        if (name instanceof CompositeName && name.size() > 1) {
            if (!(name.getPrefix(0) instanceof LdapName)) {
                throw new InvalidNameException(Messages.getString("ldap.26")); //$NON-NLS-1$
            }
            /*
             * multi ns, find next ns context, delegate operation to the next
             * context
             */
            Context nns = findNnsContext(name);
            Name remainingName = name.getSuffix(1);
            nns.destroySubcontext(remainingName);
            return;
        }

        /*
         * there is only one ldap ns
         */
        // absolute dn name to list
        String targetDN = getTargetDN(name, contextDn);
        DeleteOp op = new DeleteOp(targetDN);
        try {
            doBasicOperation(op);
        } catch (NameNotFoundException e) {
            // target dn doesn't exist, do nothing
        }
    }

    public void destroySubcontext(String s) throws NamingException {
        destroySubcontext(convertFromStringToName(s));
    }

    public Hashtable<?, ?> getEnvironment() throws NamingException {
        return (Hashtable<?, ?>) env.clone();
    }

    public String getNameInNamespace() throws NamingException {
        return contextDn.toString();
    }

    public NameParser getNameParser(Name name) throws NamingException {
        if (name instanceof CompositeName && name.size() > 1) {
            if (!(name.getPrefix(0) instanceof LdapName)) {
                throw new InvalidNameException(Messages.getString("ldap.26")); //$NON-NLS-1$
            }
            /*
             * multi ns, find next ns context, delegate operation to the next
             * context
             */
            Context nns = findNnsContext(name);
            Name remainingName = name.getSuffix(1);
            return nns.getNameParser(remainingName);
        }

        return parser;
    }

    public NameParser getNameParser(String s) throws NamingException {
        return getNameParser(convertFromStringToName(s));
    }

    public NamingEnumeration<NameClassPair> list(Name name)
            throws NamingException {
        checkName(name);

        if (name instanceof CompositeName && name.size() > 1) {
            if (!(name.getPrefix(0) instanceof LdapName)) {
                throw new InvalidNameException(Messages.getString("ldap.26")); //$NON-NLS-1$
            }
            /*
             * multi ns, find next ns context, delegate operation to the next
             * context
             */
            Context nns = findNnsContext(name);
            Name remainingName = name.getSuffix(1);
            return nns.list(remainingName);
        }

        /*
         * there is only one ldap ns
         */
        // absolute dn name to list
        String targetDN = getTargetDN(name, contextDn);

        // construct one-level search using filter "(objectclass=*)"
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.ONELEVEL_SCOPE);
        Filter filter = new Filter(Filter.PRESENT_FILTER);
        filter.setValue("objectClass");

        LdapSearchResult result = doSearch(targetDN, filter, controls);

        List<NameClassPair> list = new ArrayList<NameClassPair>();
        Map<String, Attributes> entries = result.getEntries();
        Name tempName = new LdapName(contextDn.toString());
        tempName.addAll(name);
        String baseDN = tempName.toString();
        for (String dn : entries.keySet()) {
            String relativeName = convertToRelativeName(dn, baseDN);
            Attributes attrs = entries.get(dn);
            Attribute attrClass = attrs.get("javaClassName");
            String className = null;
            if (attrClass != null) {
                className = (String) attrClass.get(0);
            } else {
                className = DirContext.class.getName();
            }
            NameClassPair pair = new NameClassPair(relativeName, className,
                    true);
            pair.setNameInNamespace(dn);
            list.add(pair);
        }

        return new LdapNamingEnumeration<NameClassPair>(list, result
                .getException());
    }

    /**
     * convert absolute dn to the dn relatived to the dn of
     * <code>targetContextDN</code>.
     * 
     * @param dn
     *            absolute dn
     * @param base
     *            base dn of the relative name
     * @return dn relatived to the <code>dn</code> of <code>base</code>
     */
    protected String convertToRelativeName(String dn, String base) {

        if (base.equals("")) {
            return dn;
        }

        int index = dn.lastIndexOf(base);
        if (index == 0) {
            return "";
        }

        return dn.substring(0, index - 1);
    }

    protected String getTargetDN(Name name, Name prefix) throws NamingException,
            InvalidNameException {
        Name target = null;
        if (name.size() == 0) {
            target = prefix;
        } else if (name instanceof LdapName) {
            target = composeName(name, prefix);
        } else if (name instanceof CompositeName) {
            LdapName alt = new LdapName(name.get(0));
            target = composeName(alt, prefix);
        } else {
            throw new InvalidNameException(Messages.getString("ldap.26")); //$NON-NLS-1$
        }
        return target.toString();
    }

    protected Context findNnsContext(Name name) throws NamingException {
        CannotProceedException cpe = null;
        if (env.containsKey(NamingManager.CPE)) {
            cpe = (CannotProceedException) env.get(NamingManager.CPE);
        } else {
            cpe = new CannotProceedException();
        }

        Name remainingName = name.getSuffix(1);
        Name altName = name.getPrefix(0);
        Name targetName = composeName(altName, contextDn);

        Name resolvedName = cpe.getResolvedName();
        if (resolvedName == null) {
            resolvedName = new CompositeName();

        } else if (resolvedName.size() >= 2
                && resolvedName.get(resolvedName.size() - 1).equals("")) {
            // remove the last component if it is ""
            // (the sign of the next naming system), so there must be at least
            // one name before "".
            resolvedName.remove(resolvedName.size() - 1);
        }

        resolvedName.add(targetName.toString());
        // add empty component name to indicate nns pointer
        resolvedName.add("");

        cpe.setAltName(altName);
        cpe.setAltNameCtx(this);
        cpe.setEnvironment((Hashtable<Object, Object>) env.clone());
        cpe.setRemainingName(remainingName);
        cpe.setResolvedName(resolvedName);

        final LdapContextImpl context = new LdapContextImpl(this, env,
                composeName(altName, contextDn).toString());

        RefAddr addr = new RefAddr("nns") { //$NON-NLS-1$

            private static final long serialVersionUID = -5428706819217461955L;

            @Override
            public Object getContent() {
                return context;
            }

        };

        Reference ref = new Reference(context.getClass().getName(), addr);
        cpe.setResolvedObj(ref);

        return DirectoryManager.getContinuationDirContext(cpe);
    }

    public NamingEnumeration<NameClassPair> list(String s)
            throws NamingException {
        return list(convertFromStringToName(s));
    }

    public NamingEnumeration<Binding> listBindings(Name name)
            throws NamingException {
        // TODO not yet implemented
        throw new NotYetImplementedException();
    }

    public NamingEnumeration<Binding> listBindings(String s)
            throws NamingException {
        return listBindings(convertFromStringToName(s));
    }

    public Object lookup(Name name) throws NamingException {
        // TODO not yet implemented
        throw new NotYetImplementedException();
    }

    public Object lookup(String s) throws NamingException {
        return lookup(convertFromStringToName(s));
    }

    /**
     * convert <code>String</code> name to <code>Name</code> instance, we
     * assume the <code>String</code> name parameter is using composite name
     * syntax (see LDAP service providers guidlines, part 4).
     * 
     * @param s
     *            <code>String</code> name to be converted
     * @return <code>Name</code> instance equivalent to <code>s</code>
     * @throws InvalidNameException
     *             occurs error while converting
     */
    protected Name convertFromStringToName(String s) throws InvalidNameException {
        if (s == null) {
            // jndi.2E=The name is null
            throw new NullPointerException(Messages.getString("jndi.2E")); //$NON-NLS-1$
        }

        CompositeName name = new CompositeName(s);
        if (name.size() == 0) {
            // return empty name
            return new LdapName(""); //$NON-NLS-1$
        }

        return name;
    }

    public Object lookupLink(Name name) throws NamingException {
        return lookup(name);
    }

    public Object lookupLink(String s) throws NamingException {
        return lookupLink(convertFromStringToName(s));
    }

    public void rebind(Name n, Object o) throws NamingException {
        rebind(n, o, null);
    }

    public void rebind(String s, Object o) throws NamingException {
        rebind(convertFromStringToName(s), o);
    }

    public Object removeFromEnvironment(String s) throws NamingException {
        Object preValue = env.remove(s);

        // if s doesn't exist in env
        if (preValue == null) {
            return preValue;
        }

        updateEnvironment(s);

        return preValue;
    }

    private void updateEnvironment(String propName) throws NamingException,
            AuthenticationNotSupportedException, CommunicationException,
            ConfigurationException {
        if (connectionProperties.contains(propName)) {
            if (propName.equals("java.naming.ldap.factory.socket")) {
                // use new socket factory to connect server
                String address = client.getAddress();
                int port = client.getPort();

                client = LdapClient.newInstance(address, port, env);
                try {
                    doBindOperation(connCtls);
                } catch (IOException e) {
                    CommunicationException ex = new CommunicationException();
                    ex.setRootCause(e);
                    throw ex;
                }
            } else {

                reconnect(connCtls);
            }
        }
    }

    public void rename(Name nOld, Name nNew) throws NamingException {
        checkName(nOld);
        checkName(nNew);

        if (!isInSameNamespace(nOld, nNew)) {
            throw new InvalidNameException(Messages.getString("ldap.2A")); //$NON-NLS-1$
        }

        if (nOld instanceof CompositeName && nOld.size() > 1
                && nNew instanceof CompositeName && nNew.size() > 1) {
            Context context = findNnsContext(nOld);
            context.rename(nOld.getSuffix(1), nNew.getSuffix(1));
            return;
        }

        // get absolute dn name
        String oldTargetDN = getTargetDN(nOld, contextDn);
        String newTargetDN = getTargetDN(nNew, contextDn);
        LdapName name = new LdapName(newTargetDN);
        Rdn rdn = name.getRdn(name.size() - 1);
        String value = (String) env.get(LDAP_DELETE_RDN);
        // true is default value
        boolean isDeleteRdn = true;
        if (value != null) {
            isDeleteRdn = Boolean.getBoolean(value);
        }

        ModifyDNOp op = new ModifyDNOp(oldTargetDN, rdn.toString(),
                isDeleteRdn, name.getPrefix(name.size() - 1).toString());

        doBasicOperation(op);
    }

    private boolean isInSameNamespace(Name first, Name second) {
        if (first instanceof CompositeName && second instanceof CompositeName) {
            // TODO need more test in detail
            return first.size() == second.size();
        }

        if (first instanceof LdapName && second instanceof LdapName) {
            return true;
        }

        return false;
    }

    public void rename(String sOld, String sNew) throws NamingException {
        rename(convertFromStringToName(sOld), convertFromStringToName(sNew));
    }

    public void unbind(Name n) throws NamingException {
        // unbind and destroySubcontext do the same thing
        destroySubcontext(n);
    }

    public void unbind(String s) throws NamingException {
        unbind(convertFromStringToName(s));
    }

    /**
     * Do operations with one request and one response which contains
     * LdapResult. This is the convenience way to do the most of ldap operation
     * except search opeartion which has multi-response, bind operation, abandon
     * and unbind operations which have no response.
     * 
     * @param op
     * @throws NamingException
     */
    protected void doBasicOperation(LdapOperation op) throws NamingException {
        LdapMessage message = null;
        try {
            message = client.doOperation(op, requestControls);
        } catch (IOException e) {
            CommunicationException ex = new CommunicationException();
            ex.setRootCause(e);
            // operation failed, clear responseControls
            responseControls = null;
            throw ex;
        }

        Control[] rawControls = message.getControls();
        responseControls = narrowingControls(rawControls);

        LdapResult result = op.getResult();

        // TODO deal with referrals

        NamingException ex = LdapUtils.getExceptionFromResult(result);
        if (ex != null) {
            throw ex;
        }
    }

    /**
     * convert raw controls to particular type of controls using
     * <code>getControlInstance(Control, Context,
     Hashtable<?, ?>)</code>
     * 
     * @param rawControls
     *            raw controls
     * @return particular type of controls
     * 
     * @throws NamingException
     */
    private Control[] narrowingControls(Control[] rawControls)
            throws NamingException {
        if (rawControls == null) {
            return null;
        }

        Control[] controls = new Control[rawControls.length];
        for (int i = 0; i < rawControls.length; ++i) {
            controls[i] = ControlFactory.getControlInstance(rawControls[i],
                    this, env);
        }

        return controls;
    }

    private void checkName(Name name) {
        if (name == null) {
            // jndi.2E=The name is null
            throw new NullPointerException(Messages.getString("jndi.2E")); //$NON-NLS-1$
        }
    }

    @Override
    protected void finalize() {
        try {
            close();
        } catch (NamingException e) {
            // ignore
        }
    }

    public void addNamingListener(Name name, String filter,
            Object[] filterArgs, SearchControls searchControls,
            NamingListener namingListener) throws NamingException {
        checkName(name);

        if (namingListener == null) {
            return;
        }

        if (!(name instanceof LdapName)) {
            if (name instanceof CompositeName && name.size() == 1) {
                name = name.getPrefix(1);
            } else {
                // FIXME: read message from file
                throw new InvalidNameException(
                        "Target cannot span multiple namespaces: "
                                + name.toString());
            }
        }

        if (namingListener instanceof UnsolicitedNotificationListener) {
            if (unls == null) {
                unls = new ArrayList<UnsolicitedNotificationListener>();
                addUnsolicitedListener();
            }

            unls.add((UnsolicitedNotificationListener) namingListener);

            if (!(namingListener instanceof NamespaceChangeListener)
                    && !(namingListener instanceof ObjectChangeListener)) {
                return;
            }
        }

        if (filter == null) {
            throw new NullPointerException(Messages.getString("ldap.28")); //$NON-NLS-1$
        }

        if (filterArgs == null) {
            filterArgs = new Object[0];
        }

        if (searchControls == null) {
            searchControls = new SearchControls();
        }

        FilterParser filterParser = new FilterParser(filter);
        filterParser.setArgs(filterArgs);
        Filter f = null;
        try {
            f = filterParser.parse();
        } catch (ParseException e) {
            InvalidSearchFilterException ex = new InvalidSearchFilterException(
                    Messages.getString("ldap.29")); //$NON-NLS-1$
            ex.setRootCause(e);
            throw ex;
        }

        String targetDN = getTargetDN(name, contextDn);

        Name tempName = new LdapName(contextDn.toString());
        tempName.addAll(name);
        String baseDN = tempName.toString();

        int messageId = doPersistentSearch(targetDN, baseDN, f, searchControls,
                namingListener);

        if (listeners == null) {
            listeners = new HashMap<NamingListener, List<Integer>>();
        }

        List<Integer> idList = listeners.get(namingListener);
        if (idList == null) {
            idList = new ArrayList<Integer>();
        }

        idList.add(Integer.valueOf(messageId));
    }

    public void addNamingListener(Name name, String filter,
            SearchControls searchControls, NamingListener namingListener)
            throws NamingException {
        addNamingListener(name, filter, new Object[0], searchControls,
                namingListener);
    }

    public void addNamingListener(String name, String filter,
            Object[] filterArgs, SearchControls searchControls,
            NamingListener namingListener) throws NamingException {
        addNamingListener(convertFromStringToName(name), filter, filterArgs,
                searchControls, namingListener);
    }

    public void addNamingListener(String name, String filter,
            SearchControls searchControls, NamingListener namingListener)
            throws NamingException {
        addNamingListener(convertFromStringToName(name), filter,
                searchControls, namingListener);
    }

    public static interface UnsolicitedListener {
        public void receiveNotification(UnsolicitedNotificationImpl un,
                Control[] cs);
    }

    public void addNamingListener(Name name, int scope,
            NamingListener namingListener) throws NamingException {
        checkName(name);

        if (namingListener == null) {
            return;
        }

        // only ldap name is supportted
        if (!(name instanceof LdapName)) {
            if (name instanceof CompositeName && name.size() == 1) {
                name = name.getPrefix(0);
            } else {
                // ldap.32=Target cannot span multiple namespaces: {0}
                throw new InvalidNameException(Messages.getString("ldap.32", //$NON-NLS-1$
                        new Object[] { name.toString() }));
            }
        }

        if (namingListener instanceof UnsolicitedNotificationListener) {
            if (unls == null) {
                unls = new ArrayList<UnsolicitedNotificationListener>();
                addUnsolicitedListener();
            }

            unls.add((UnsolicitedNotificationListener) namingListener);

            if (!(namingListener instanceof NamespaceChangeListener)
                    && !(namingListener instanceof ObjectChangeListener)) {
                return;
            }
        }

        // ri is silent in this case
        if (scope != EventContext.OBJECT_SCOPE
                && scope != EventContext.ONELEVEL_SCOPE
                && scope != EventContext.SUBTREE_SCOPE) {
            // ldap.33=Scope should be one of 'OBJECT_SCOPE', 'ONELEVEL_SCOPE'
            // or 'SUBTREE_SCOPE'
            throw new IllegalArgumentException(Messages.getString("ldap.33")); //$NON-NLS-1$
        }

        String targetDN = getTargetDN(name, contextDn);

        Filter filter = new Filter(Filter.PRESENT_FILTER);
        filter.setValue("objectClass");

        SearchControls controls = new SearchControls();
        controls.setSearchScope(scope);

        Name tempName = new LdapName(contextDn.toString());
        tempName.addAll(name);
        String baseDN = tempName.toString();

        int messageId = doPersistentSearch(targetDN, baseDN, filter, controls,
                namingListener);

        if (listeners == null) {
            listeners = new HashMap<NamingListener, List<Integer>>();
        }

        List<Integer> idList = listeners.get(namingListener);
        if (idList == null) {
            idList = new ArrayList<Integer>();
            listeners.put(namingListener, idList);
        }

        idList.add(Integer.valueOf(messageId));

    }

    private void addUnsolicitedListener() {
        client.addUnsolicitedListener(new UnsolicitedListener() {

            public void receiveNotification(UnsolicitedNotificationImpl un,
                    Control[] cs) {
                EventObject event = null;
                try {
                    un.setControls(narrowingControls(cs));
                    event = new UnsolicitedNotificationEvent(this, un);
                } catch (NamingException e) {
                    event = new NamingExceptionEvent(LdapContextImpl.this, e);
                }

                for (UnsolicitedNotificationListener listener : unls) {
                    notifyNamingListener(listener, event);
                }

            }

        });
    }

    private int doPersistentSearch(String targetDN, final String baseDN,
            Filter filter, SearchControls controls,
            NamingListener namingListener) throws CommunicationException {

        SearchOp op = new SearchOp(targetDN, controls, filter);

        final NamingListener listener = namingListener;
        op.setSearchResult(new PersistentSearchResult() {

            @Override
            public void receiveNotificationHook(Object obj) {
                EventObject event = null;
                // construct event
                if (obj instanceof ECNotificationControl) {
                    ECNotificationControl control = (ECNotificationControl) obj;
                    event = constructNamingEvent(this, control, baseDN);
                }

                if (obj instanceof LdapResult) {
                    LdapResult ldapResult = (LdapResult) obj;
                    NamingException ex = LdapUtils
                            .getExceptionFromResult(ldapResult);
                    // may not happen
                    if (ex == null) {
                        return;
                    }

                    event = new NamingExceptionEvent(LdapContextImpl.this, ex);
                }

                // notify listener
                notifyNamingListener(listener, event);
            }

        });

        try {
            return client.addPersistentSearch(op);
        } catch (IOException e) {
            CommunicationException ex = new CommunicationException();
            ex.setRootCause(e);
            throw ex;
        }
    }

    private void notifyNamingListener(final NamingListener listener,
            final EventObject event) {
        /*
         * start new thread to notify listener, so user code may not affect
         * dispatcher thread
         */
        Thread thread = new Thread(new Runnable() {

            public void run() {
                if (event instanceof NamingEvent) {
                    NamingEvent namingEvent = (NamingEvent) event;
                    namingEvent.dispatch(listener);
                } else if (event instanceof NamingExceptionEvent) {
                    NamingExceptionEvent exceptionEvent = (NamingExceptionEvent) event;
                    listener.namingExceptionThrown(exceptionEvent);
                } else if (event instanceof UnsolicitedNotificationEvent) {
                    UnsolicitedNotificationEvent namingEvent = (UnsolicitedNotificationEvent) event;
                    namingEvent
                            .dispatch((UnsolicitedNotificationListener) listener);
                }

            }

        });

        thread.start();
    }

    public void addNamingListener(String s, int i, NamingListener namingListener)
            throws NamingException {
        addNamingListener(convertFromStringToName(s), i, namingListener);
    }

    public void removeNamingListener(NamingListener namingListener)
            throws NamingException {
        if (listeners == null || !listeners.containsKey(namingListener)) {
            return;
        }

        if (namingListener instanceof UnsolicitedNotificationListener) {
            unls.remove(namingListener);
        }

        List<Integer> idList = listeners.remove(namingListener);
        if (idList == null) {
            return;
        }

        try {
            for (Integer id : idList) {
                client.removePersistentSearch(id.intValue(), requestControls);
            }
        } catch (IOException e) {
            CommunicationException ex = new CommunicationException();
            ex.setRootCause(e);
        }
    }

    public boolean targetMustExist() throws NamingException {
        // FIXME
        return false;
    }

    private NamingEvent constructNamingEvent(PersistentSearchResult result,
            ECNotificationControl control, String baseDN) {
        Binding newBinding = null;
        Binding oldBinding = null;

        switch (control.getChangeType()) {
        case ECNotificationControl.ADD:
            String newName = convertToRelativeName(result.getDn(), baseDN);
            newBinding = new Binding(newName, null);
            newBinding.setNameInNamespace(result.getDn());
            break;
        case ECNotificationControl.DELETE:
            String deleteName = convertToRelativeName(result.getDn(), baseDN);
            oldBinding = new Binding(deleteName, null);
            oldBinding.setNameInNamespace(result.getDn());
            break;
        case ECNotificationControl.MODIFY_DN:
            if (result.getDn() != null) {
                newBinding = new Binding(convertToRelativeName(result.getDn(),
                        baseDN), null);
                newBinding.setNameInNamespace(result.getDn());
            }

            if (control.getPreviousDN() != null) {
                oldBinding = new Binding(convertToRelativeName(control
                        .getPreviousDN(), baseDN), null);
                oldBinding.setNameInNamespace(control.getPreviousDN());
            }
            break;
        case ECNotificationControl.MODIFY:
            String relativeName = convertToRelativeName(result.getDn(), baseDN);
            newBinding = new Binding(relativeName, null);
            newBinding.setNameInNamespace(result.getDn());
            // FIXME: how to get old binding?
            oldBinding = new Binding(relativeName, null);
            oldBinding.setNameInNamespace(result.getDn());
        }

        NamingEvent event = new NamingEvent(this, control.getJNDIChangeType(),
                newBinding, oldBinding, Integer.valueOf(control
                        .getChangeNumber()));

        return event;
    }
}
