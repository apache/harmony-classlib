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
 * @version $Revision: 1.1.2.3 $
 */
package org.apache.harmony.jndi.provider.rmi.registry;

import java.util.Properties;

import javax.naming.CompoundName;
import javax.naming.Name;
import javax.naming.NameParser;
import javax.naming.NamingException;


/**
 * Parser for flat case-sensitive atomic names used by {@link RegistryContext}.
 *
 * @author  Vasily Zakharov
 * @version $Revision: 1.1.2.3 $
 */
public class AtomicNameParser implements NameParser {

    /**
     * Syntax, defines a flat case-sensitive context,
     * initialized in static initialization block.
     */
    private static final Properties syntax = new Properties();

    /**
     * Static initializer for {@link #syntax}.
     */
    static {
        syntax.put("jndi.syntax.direction", "flat");
    }

    /**
     * Creates instance of this class.
     */
    public AtomicNameParser() {}

    /**
     * Returns flat {@link CompoundName} constructed from the specified string.
     *
     * @param   name
     *          Name to parse, cannot be <code>null</code>.
     *
     * @return  Flat {@link CompoundName} constructed from the specified string.
     *
     * @throws  NamingException
     *          If some error occured.
     */
    public Name parse(String name) throws NamingException {
        return new CompoundName(name, syntax);
    }
}
