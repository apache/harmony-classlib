/*
 * Copyright 2005-2006 The Apache Software Foundation or its licensors, as applicable
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
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
package org.apache.harmony.jndi.provider.rmi.registry;

import java.rmi.registry.Registry;

import java.util.NoSuchElementException;

import javax.naming.Binding;
import javax.naming.Name;
import javax.naming.NamingException;


/**
 * Enumeration of {@link Binding} objects,
 * used by {@link RegistryContext#listBindings(Name)} method.
 *
 * @author  Vasily Zakharov
 * @version $Revision: 1.1.2.2 $
 */
class BindingEnumeration extends NameClassPairEnumeration {

    /**
     * Registry context.
     */
    protected RegistryContext context;

    /**
     * Creates this enumeration.
     *
     * @param   names
     *          Binding names returned from {@link Registry#list()} method.
     *
     * @param   context
     *          RegistryContext to extract bindings from.
     */
    public BindingEnumeration(String[] names, RegistryContext context) {
        super(names);
        this.context = context.cloneContext();
    }

    /**
     * {@inheritDoc}
     */
    public boolean hasMore() {
        if (super.hasMore()) {
            return true;
        } else {
            close();
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    public Object next() throws NoSuchElementException, NamingException {
        if (!hasMore()) {
            throw new NoSuchElementException();
        }

        String name = names[index++];
        Binding binding = new Binding(name, context.lookup(name));
        binding.setNameInNamespace(name);
        return binding;
    }

    /**
     * {@inheritDoc}
     */
    public void close() {
        super.close();
        finalize();
    }

    /**
     * {@inheritDoc}
     */
    protected void finalize() {
        context.close();
    }
}
