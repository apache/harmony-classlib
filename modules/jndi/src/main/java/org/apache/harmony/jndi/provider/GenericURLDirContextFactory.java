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
 * @version $Revision: 1.1.2.2 $
 */
package org.apache.harmony.jndi.provider;

import java.util.Hashtable;

import javax.naming.ConfigurationException;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NamingException;

import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;

import javax.naming.spi.DirObjectFactory;
import javax.naming.spi.ObjectFactory;


/**
 * Base class for URL directory context factory implementations.
 *
 * In many cases, subclasses should only override
 * {@link #createURLDirContext(Hashtable)} method.
 * and provide public no-args constructor.
 *
 * @author  Vasily Zakharov
 * @version $Revision: 1.1.2.2 $
 */
public abstract class GenericURLDirContextFactory
        extends GenericURLContextFactory implements DirObjectFactory {

    /**
     * Default constructor for subclasses.
     */
    protected GenericURLDirContextFactory() {
        super();
    }

    /**
     * Lookups the specified object in the underlying context.
     * Underlying context instance is provided by
     * {@link #createURLContext(Hashtable)}.
     *
     * Follows the guidelines for URL context factories described in
     * {@link ObjectFactory#getObjectInstance(Object, Name, Context, Hashtable)}
     * specification.
     *
     * If <code>obj</code> is <code>null</code>,
     * just creates and returns an underlying context.
     *
     * If <code>obj</code> is a proper URL string,
     * lookups and returns an object specified by that string.
     *
     * If <code>obj</code> is an array of URL strings,
     * tries to lookup each of them sequentially until lookup succeeds,
     * then returns the result. If no lookup succeeds, throws
     * {@link NamingException} describing the fail of a last lookup.
     *
     * <code>name</code>, <code>nameCtx</code>, and <code>attrs</code>
     * parameters are ignored.
     *
     * This implementation just calls
     * {@link #getObjectInstance(Object, Name, Context, Hashtable)}.
     *
     * @param   obj
     *          Object to lookup, can be <code>null</code>.
     *
     * @param   name
     *          Ignored.
     *
     * @param   nameCtx
     *          Ignored.
     *
     * @param   environment
     *          Environment to use in creating the underlying context,
     *          can be <code>null</code>.
     *
     * @param   attrs
     *          Ignored.
     *
     * @return  The object created.
     *
     * @throws  ConfigurationException
     *          If <code>obj</code> is neither <code>null</code>
     *          nor a string, nor a string array, or is an empty string array.
     *
     * @throws  NamingException
     *          If lookup attempt failed.
     */
    public Object getObjectInstance(Object obj, Name name, Context nameCtx,
            Hashtable<?, ?> environment, Attributes attrs) throws Exception {
        return getObjectInstance(obj, name, nameCtx, environment);
    }

    /**
     * {@inheritDoc}
     *
     * This implementation just calls {@link #createURLDirContext(Hashtable)}.
     */
    @Override
    protected final Context createURLContext(Hashtable<?, ?> environment) {
        return createURLDirContext(environment);
    }

    /**
     * Returns new instance of the necessary context. Used by
     * {@link #getObjectInstance(Object, Name, Context, Hashtable)} and
     * {@link #getObjectInstance(Object, Name, Context, Hashtable, Attributes)}.
     *
     * Must be overridden by particular URL context factory implementations.
     *
     * @param   environment
     *          Environment.
     *
     * @return  New context instance.
     */
    protected abstract DirContext createURLDirContext(Hashtable<?, ?> environment);
}
