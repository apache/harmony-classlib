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
 * @author Alexei Y. Zakharov
 * @version $Revision: 1.1.2.4 $
 */
package org.apache.harmony.jndi.provider.dns;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import java.util.Enumeration;

/**
 * Trivial implementation of <code>NamingEnumeration</code> interface.
 * @author Alexei Zakharov
 * @version $Revision: 1.1.2.4 $
 */
public class BasicNamingEnumerator implements NamingEnumeration {
    
    private Enumeration enum1 = null;
    
    /**
     * Constructs new enumerator from existing <code>Enumeration</code>.
     * @param enum1 enumeration 
     */
    public BasicNamingEnumerator(Enumeration newEnum) {
        this.enum1 = newEnum;
    }

    /**
     * @return next element of enumeration
     * @see javax.naming.NamingEnumeration#next()
     */
    public Object next() {
        return enum1.nextElement();
    }

    /**
     * @return <code>true</code> if the enumeration has more elements
     * @see javax.naming.NamingEnumeration#hasMore()
     */
    public boolean hasMore() {
        return enum1.hasMoreElements();
    }

    /**
     * Do nothing in current implementation.
     * @see javax.naming.NamingEnumeration#close()
     */
    public void close() throws NamingException {}

    /**
     * @return next element of enumeration
     * @see java.util.Enumeration#nextElement()
     */
    public Object nextElement() {
        return enum1.nextElement();
    }

    /**
     * @return <code>true</code> if the enumeration has more elements
     * @see java.util.Enumeration#hasMoreElements()
     */
    public boolean hasMoreElements() {
        return enum1.hasMoreElements();
    }

}
