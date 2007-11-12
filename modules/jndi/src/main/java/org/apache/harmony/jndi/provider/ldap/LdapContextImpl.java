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
import java.util.Hashtable;
import java.util.List;

import javax.naming.Binding;
import javax.naming.CannotProceedException;
import javax.naming.CommunicationException;
import javax.naming.CompositeName;
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
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.Control;
import javax.naming.ldap.ControlFactory;
import javax.naming.ldap.ExtendedRequest;
import javax.naming.ldap.ExtendedResponse;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.ManageReferralControl;
import javax.naming.ldap.Rdn;
import javax.naming.spi.DirectoryManager;
import javax.naming.spi.NamingManager;

import org.apache.harmony.jndi.internal.Util;
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

    private Control[] requestControls;

    private Control[] responseControls;

    /**
     * environment properties for this context
     */
    private Hashtable<Object, Object> env;

    /**
     * name parser for this context
     */
    private NameParser parser;

    private static final Control NON_CRITICAL_MANAGE_REF_CONTROL = new ManageReferralControl(
            Control.NONCRITICAL);

    private static final String LDAP_DELETE_RDN = "java.naming.ldap.deleteRDN"; //$NON-NLS-1$

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
        // TODO do ldap bind operation
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
            Attribute attr = new LdapAttribute("objectClass");
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
            la.add(new LdapAttribute(enu.next()));
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

        Attribute attr = new LdapAttribute(first.getID());
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
                op.addModification(0, new LdapAttribute(item.getAttribute()));
                break;
            case DirContext.REMOVE_ATTRIBUTE:
                op.addModification(1, new LdapAttribute(item.getAttribute()));
                break;
            case DirContext.REPLACE_ATTRIBUTE:
                op.addModification(2, new LdapAttribute(item.getAttribute()));
                break;
            default:
                throw new IllegalArgumentException(Messages.getString(
                        "jndi.14", item.getModificationOp()));
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
        // TODO not yet implemented
        throw new NotYetImplementedException();
    }

    public NamingEnumeration<NameClassPair> list(String s)
            throws NamingException {
        return list(convertFromStringToName(s));
    }

    private String getTargetDN(Name name, Name prefix) throws NamingException,
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

    private Context findNnsContext(Name name) throws NamingException {
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
    private void doBasicOperation(LdapOperation op) throws NamingException {
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

        if (Util.getExceptionFromErrorCode(result.getResultCode()) != null) {
            throw Util.getExceptionFromErrorCode(result.getResultCode());
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
}
