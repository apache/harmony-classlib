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

import javax.naming.Binding;
import javax.naming.CompositeName;
import javax.naming.Context;
import javax.naming.InvalidNameException;
import javax.naming.Name;
import javax.naming.NameClassPair;
import javax.naming.NameParser;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.Control;
import javax.naming.ldap.ExtendedRequest;
import javax.naming.ldap.ExtendedResponse;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.LdapName;

import org.apache.harmony.jndi.internal.nls.Messages;
import org.apache.harmony.jndi.internal.parser.LdapNameParser;

/**
 * This context implements LdapContext, it's main entry point of all JNDI ldap
 * operations.
 */
public class LdapContextImpl implements LdapContext {

    /**
     * ldap connection
     */
    private LdapClient client;

    /**
     * name of the context
     */
    private Name contextDn;

    /**
     * environment properties for this context
     */
    private Hashtable<Object, Object> env;

    /**
     * name parser for this context
     */
    private NameParser parser;

    /**
     * construct a new inherit <code>LdapContextImpl</code>
     * 
     * @param context
     * @param env
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
        //TODO do ldap bind operation
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
        // TODO not yet implemented
        throw new NotYetImplementedException();
    }

    public Control[] getResponseControls() throws NamingException {
        // TODO not yet implemented
        throw new NotYetImplementedException();
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
        // TODO not yet implemented
        throw new NotYetImplementedException();
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
        // TODO not yet implemented
        throw new NotYetImplementedException();
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
        // TODO not yet implemented
        throw new NotYetImplementedException();
    }

    public Attributes getAttributes(String s) throws NamingException {
        return getAttributes(convertFromStringToName(s));
    }

    public Attributes getAttributes(String s, String[] as)
            throws NamingException {
        return getAttributes(convertFromStringToName(s), as);
    }

    public DirContext getSchema(Name name) throws NamingException {
        // TODO not yet implemented
        throw new NotYetImplementedException();
    }

    public DirContext getSchema(String s) throws NamingException {
        return getSchema(convertFromStringToName(s));
    }

    public DirContext getSchemaClassDefinition(Name name)
            throws NamingException {
        // TODO not yet implemented
        throw new NotYetImplementedException();
    }

    public DirContext getSchemaClassDefinition(String s) throws NamingException {
        return getSchemaClassDefinition(convertFromStringToName(s));
    }

    public void modifyAttributes(Name name, int i, Attributes attributes)
            throws NamingException {
        // TODO not yet implemented
        throw new NotYetImplementedException();
    }

    public void modifyAttributes(Name name, ModificationItem[] modificationItems)
            throws NamingException {
        // TODO not yet implemented
        throw new NotYetImplementedException();
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
        // TODO not yet implemented
        throw new NotYetImplementedException();
    }

    public NamingEnumeration<SearchResult> search(Name name, String filter,
            Object[] objs, SearchControls searchControls)
            throws NamingException {
        // TODO not yet implemented
        throw new NotYetImplementedException();
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

    public Object addToEnvironment(String s, Object o) throws NamingException {
        // TODO not yet implemented
        throw new NotYetImplementedException();
    }

    public void bind(Name n, Object o) throws NamingException {
        bind(n, o, null);
    }

    public void bind(String s, Object o) throws NamingException {
        bind(convertFromStringToName(s), o);
    }

    public void close() throws NamingException {
        // TODO not yet implemented
        throw new NotYetImplementedException();
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

    public Context createSubcontext(String s) throws NamingException {
        return createSubcontext(convertFromStringToName(s));
    }

    public void destroySubcontext(Name name) throws NamingException {
        // TODO not yet implemented
        throw new NotYetImplementedException();
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
        return parser;
    }

    public NameParser getNameParser(String s) throws NamingException {
        return getNameParser(convertFromStringToName(s));
    }

    public NamingEnumeration<NameClassPair> list(Name name)
            throws NamingException {
        // TODO not yet implemented
        throw new NotYetImplementedException();
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
    private Name convertFromStringToName(String s) throws InvalidNameException {
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
        // TODO not yet implemented
        throw new NotYetImplementedException();
    }

    public void rename(Name nOld, Name nNew) throws NamingException {
        // TODO not yet implemented
        throw new NotYetImplementedException();
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
}
