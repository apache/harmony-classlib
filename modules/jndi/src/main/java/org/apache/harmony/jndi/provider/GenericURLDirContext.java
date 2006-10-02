/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author  Vasily Zakharov
 * @version $Revision: 1.1.2.3 $
 */
package org.apache.harmony.jndi.provider;

import java.util.Hashtable;

import javax.naming.CannotProceedException;
import javax.naming.CompositeName;
import javax.naming.InvalidNameException;
import javax.naming.Name;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import javax.naming.spi.DirectoryManager;
import javax.naming.spi.ResolveResult;

import org.apache.harmony.jndi.internal.nls.Messages;


/**
 * Base class for URL directory context implementations.
 *
 * In many cases, subclasses should only override
 * {@link GenericURLContext#getRootURLContext(String, Hashtable)} method
 * and provide a public constructor calling
 * {@link GenericURLDirContext#GenericURLDirContext(Hashtable) super(environment)}.
 *
 * @author  Vasily Zakharov
 * @version $Revision: 1.1.2.3 $
 */
public abstract class GenericURLDirContext
        extends GenericURLContext implements DirContext {

    /**
     * Creates instance of this context with empty environment.
     */
    protected GenericURLDirContext() {
        super(null);
    }

    /**
     * Creates instance of this context with specified environment.
     *
     * @param   environment
     *          Environment to copy.
     */
    protected GenericURLDirContext(Hashtable<?, ?> environment) {
        super(environment);
    }

    /**
     * {@inheritDoc}
     */
    public void bind(Name name, Object obj, Attributes attrs)
            throws NamingException {
        if (!(name instanceof CompositeName)) {
            // jndi.26=URL context can't accept non-composite name: {0}
            throw new InvalidNameException(
                    Messages.getString("jndi.26", name)); //$NON-NLS-1$
        }

        if (name.size() == 1) {
            bind(name.get(0), obj, attrs);
        } else {
            DirContext context = getContinuationDirContext(name);

            try {
                context.bind(name.getSuffix(1), obj, attrs);
            } finally {
                context.close();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void bind(String name, Object obj, Attributes attrs)
            throws NamingException {
        ResolveResult result = getRootURLContext(name, environment);
        DirContext context = (DirContext) result.getResolvedObj();

        try {
            context.bind(result.getRemainingName(), obj, attrs);
        } finally {
            context.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void rebind(Name name, Object obj, Attributes attrs)
            throws NamingException {
        if (!(name instanceof CompositeName)) {
            // jndi.26=URL context can't accept non-composite name: {0}
            throw new InvalidNameException(
                    Messages.getString("jndi.26", name)); //$NON-NLS-1$
        }

        if (name.size() == 1) {
            rebind(name.get(0), obj, attrs);
        } else {
            DirContext context = getContinuationDirContext(name);

            try {
                context.rebind(name.getSuffix(1), obj, attrs);
            } finally {
                context.close();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void rebind(String name, Object obj, Attributes attrs)
            throws NamingException {
        ResolveResult result = getRootURLContext(name, environment);
        DirContext context = (DirContext) result.getResolvedObj();

        try {
            context.rebind(result.getRemainingName(), obj, attrs);
        } finally {
            context.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    public DirContext createSubcontext(Name name, Attributes attrs)
            throws NamingException {
        if (!(name instanceof CompositeName)) {
            // jndi.26=URL context can't accept non-composite name: {0}
            throw new InvalidNameException(
                    Messages.getString("jndi.26", name)); //$NON-NLS-1$
        }

        if (name.size() == 1) {
            return createSubcontext(name.get(0), attrs);
        }
        DirContext context = getContinuationDirContext(name);
        try {
            return context.createSubcontext(name.getSuffix(1), attrs);
        } finally {
            context.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    public DirContext createSubcontext(String name, Attributes attrs)
            throws NamingException {
        ResolveResult result = getRootURLContext(name, environment);
        DirContext context = (DirContext) result.getResolvedObj();

        try {
            return context.createSubcontext(result.getRemainingName(), attrs);
        } finally {
            context.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    public Attributes getAttributes(Name name) throws NamingException {
        if (!(name instanceof CompositeName)) {
            // jndi.26=URL context can't accept non-composite name: {0}
            throw new InvalidNameException(
                    Messages.getString("jndi.26", name)); //$NON-NLS-1$
        }

        if (name.size() == 1) {
            return getAttributes(name.get(0));
        }
        DirContext context = getContinuationDirContext(name);
        try {
            return context.getAttributes(name.getSuffix(1));
        } finally {
            context.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    public Attributes getAttributes(String name) throws NamingException {
        ResolveResult result = getRootURLContext(name, environment);
        DirContext context = (DirContext) result.getResolvedObj();

        try {
            return context.getAttributes(result.getRemainingName());
        } finally {
            context.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    public Attributes getAttributes(Name name, String[] attrIds)
            throws NamingException {
        if (!(name instanceof CompositeName)) {
            // jndi.26=URL context can't accept non-composite name: {0}
            throw new InvalidNameException(
                    Messages.getString("jndi.26", name)); //$NON-NLS-1$
        }

        if (name.size() == 1) {
            return getAttributes(name.get(0), attrIds);
        }
        DirContext context = getContinuationDirContext(name);
        try {
            return context.getAttributes(name.getSuffix(1), attrIds);
        } finally {
            context.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    public Attributes getAttributes(String name, String[] attrIds)
            throws NamingException {
        ResolveResult result = getRootURLContext(name, environment);
        DirContext context = (DirContext) result.getResolvedObj();

        try {
            return context.getAttributes(result.getRemainingName(), attrIds);
        } finally {
            context.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void modifyAttributes(Name name, int mod_op, Attributes attrs)
            throws NamingException {
        if (!(name instanceof CompositeName)) {
            // jndi.26=URL context can't accept non-composite name: {0}
            throw new InvalidNameException(
                    Messages.getString("jndi.26", name)); //$NON-NLS-1$
        }

        if (name.size() == 1) {
            modifyAttributes(name.get(0), mod_op, attrs);
        } else {
            DirContext context = getContinuationDirContext(name);

            try {
                context.modifyAttributes(name.getSuffix(1), mod_op, attrs);
            } finally {
                context.close();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void modifyAttributes(String name, int mod_op, Attributes attrs)
            throws NamingException {
        ResolveResult result = getRootURLContext(name, environment);
        DirContext context = (DirContext) result.getResolvedObj();

        try {
            context.modifyAttributes(result.getRemainingName(), mod_op, attrs);
        } finally {
            context.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void modifyAttributes(Name name, ModificationItem[] mods)
            throws NamingException {
        if (!(name instanceof CompositeName)) {
            // jndi.26=URL context can't accept non-composite name: {0}
            throw new InvalidNameException(
                    Messages.getString("jndi.26", name)); //$NON-NLS-1$
        }

        if (name.size() == 1) {
            modifyAttributes(name.get(0), mods);
        } else {
            DirContext context = getContinuationDirContext(name);

            try {
                context.modifyAttributes(name.getSuffix(1), mods);
            } finally {
                context.close();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void modifyAttributes(String name, ModificationItem[] mods)
            throws NamingException {
        ResolveResult result = getRootURLContext(name, environment);
        DirContext context = (DirContext) result.getResolvedObj();

        try {
            context.modifyAttributes(result.getRemainingName(), mods);
        } finally {
            context.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    public DirContext getSchema(Name name) throws NamingException {
        if (!(name instanceof CompositeName)) {
            // jndi.26=URL context can't accept non-composite name: {0}
            throw new InvalidNameException(
                    Messages.getString("jndi.26", name)); //$NON-NLS-1$
        }

        if (name.size() == 1) {
            return getSchema(name.get(0));
        }
        DirContext context = getContinuationDirContext(name);
        try {
            return context.getSchema(name.getSuffix(1));
        } finally {
            context.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    public DirContext getSchema(String name) throws NamingException {
        ResolveResult result = getRootURLContext(name, environment);
        DirContext context = (DirContext) result.getResolvedObj();

        try {
            return context.getSchema(result.getRemainingName());
        } finally {
            context.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    public DirContext getSchemaClassDefinition(Name name)
            throws NamingException {
        if (!(name instanceof CompositeName)) {
            // jndi.26=URL context can't accept non-composite name: {0}
            throw new InvalidNameException(
                    Messages.getString("jndi.26", name)); //$NON-NLS-1$
        }

        if (name.size() == 1) {
            return getSchemaClassDefinition(name.get(0));
        }
        DirContext context = getContinuationDirContext(name);
        try {
            return context.getSchemaClassDefinition(name.getSuffix(1));
        } finally {
            context.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    public DirContext getSchemaClassDefinition(String name)
            throws NamingException {
        ResolveResult result = getRootURLContext(name, environment);
        DirContext context = (DirContext) result.getResolvedObj();

        try {
            return context.getSchemaClassDefinition(result.getRemainingName());
        } finally {
            context.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    public NamingEnumeration<SearchResult> search(Name name, Attributes matchingAttributes)
            throws NamingException {
        if (!(name instanceof CompositeName)) {
            // jndi.26=URL context can't accept non-composite name: {0}
            throw new InvalidNameException(
                    Messages.getString("jndi.26", name)); //$NON-NLS-1$
        }

        if (name.size() == 1) {
            return search(name.get(0), matchingAttributes);
        }
        DirContext context = getContinuationDirContext(name);

        try {
            return context.search(name.getSuffix(1), matchingAttributes);
        } finally {
            context.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    public NamingEnumeration<SearchResult> search(String name, Attributes matchingAttributes)
            throws NamingException {
        ResolveResult result = getRootURLContext(name, environment);
        DirContext context = (DirContext) result.getResolvedObj();

        try {
            return context.search(result.getRemainingName(),
                    matchingAttributes);
        } finally {
            context.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    public NamingEnumeration<SearchResult> search(Name name, Attributes matchingAttributes,
            String[] attributesToReturn) throws NamingException {
        if (!(name instanceof CompositeName)) {
            // jndi.26=URL context can't accept non-composite name: {0}
            throw new InvalidNameException(
                    Messages.getString("jndi.26", name)); //$NON-NLS-1$
        }

        if (name.size() == 1) {
            return search(name.get(0), matchingAttributes, attributesToReturn);
        }
        DirContext context = getContinuationDirContext(name);

        try {
            return context.search(name.getSuffix(1),
                    matchingAttributes, attributesToReturn);
        } finally {
            context.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    public NamingEnumeration<SearchResult> search(String name, Attributes matchingAttributes,
            String[] attributesToReturn) throws NamingException {
        ResolveResult result = getRootURLContext(name, environment);
        DirContext context = (DirContext) result.getResolvedObj();

        try {
            return context.search(result.getRemainingName(),
                    matchingAttributes, attributesToReturn);
        } finally {
            context.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    public NamingEnumeration<SearchResult> search(Name name, String filter,
            SearchControls cons) throws NamingException {
        if (!(name instanceof CompositeName)) {
            // jndi.26=URL context can't accept non-composite name: {0}
            throw new InvalidNameException(
                    Messages.getString("jndi.26", name)); //$NON-NLS-1$
        }

        if (name.size() == 1) {
            return search(name.get(0), filter, cons);
        }
        DirContext context = getContinuationDirContext(name);
        try {
            return context.search(name.getSuffix(1), filter, cons);
        } finally {
            context.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    public NamingEnumeration<SearchResult> search(String name, String filter,
            SearchControls cons) throws NamingException {
        ResolveResult result = getRootURLContext(name, environment);
        DirContext context = (DirContext) result.getResolvedObj();

        try {
            return context.search(result.getRemainingName(), filter, cons);
        } finally {
            context.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    public NamingEnumeration<SearchResult> search(Name name, String filterExpr,
            Object[] filterArgs, SearchControls cons) throws NamingException {
        if (!(name instanceof CompositeName)) {
            // jndi.26=URL context can't accept non-composite name: {0}
            throw new InvalidNameException(
                    Messages.getString("jndi.26", name)); //$NON-NLS-1$
        }

        if (name.size() == 1) {
            return search(name.get(0), filterExpr, filterArgs, cons);
        }
        DirContext context = getContinuationDirContext(name);
        try {
            return context.search(name.getSuffix(1),
                    filterExpr, filterArgs, cons);
        } finally {
            context.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    public NamingEnumeration<SearchResult> search(String name, String filterExpr,
            Object[] filterArgs, SearchControls cons) throws NamingException {
        ResolveResult result = getRootURLContext(name, environment);
        DirContext context = (DirContext) result.getResolvedObj();

        try {
            return context.search(result.getRemainingName(),
                    filterExpr, filterArgs, cons);
        } finally {
            context.close();
        }
    }

    /**
     * Lookups the first component (considered a URL)
     * of the specified name using {@link #lookup(String)},
     * wraps it into {@link CannotProceedException}, passes it to
     * {@link DirectoryManager#getContinuationDirContext(CannotProceedException)}
     * method and returns the result.
     *
     * This method is used by {@link #getAttributes(Name)}
     * and other public methods taking {@link Name} as a parameter.
     *
     * This method uses
     * {@link GenericURLContext#createCannotProceedException(Name)} method.
     *
     * @param   name
     *          Name to parse.
     *
     * @return  Continuation context.
     *
     * @throws  NamingException
     *          If some naming error occurs.
     */
    protected DirContext getContinuationDirContext(Name name)
            throws NamingException {
        return DirectoryManager.getContinuationDirContext(
                createCannotProceedException(name));
    }
}
