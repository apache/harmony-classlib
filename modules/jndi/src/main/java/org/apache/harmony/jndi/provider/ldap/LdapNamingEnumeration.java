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
import java.util.Collection;
import java.util.NoSuchElementException;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

/**
 * TODO: dynamic load elements from server
 */
public class LdapNamingEnumeration<T> implements NamingEnumeration<T> {

    private ArrayList<T> values;

    private int currentIndex;

    private NamingException exception;

    /**
     * <code>list</code> and <code>ex</code> both can be <code>null</code>,
     * <code>null</code> of <code>list</code> will be treated as empty List.
     * 
     * @param list
     *            All elements to be enumerate
     * @param ex
     *            exception would be thrown when over iterate
     */
    public LdapNamingEnumeration(Collection<T> list, NamingException ex) {
        if (list == null) {
            values = new ArrayList<T>();
        } else {
            values = new ArrayList<T>(list);
        }

        exception = ex;
        currentIndex = 0;
    }

    /**
     * release all relative resources, current implementation just set
     * enumeration values to <code>null</code>.
     */
    public void close() throws NamingException {
        // no other resources need to release
        values = null;
    }

    public boolean hasMore() throws NamingException {
        if (values == null) {
            return false;
        }

        if (currentIndex < values.size()) {
            return true;
        }
        // no elemnts to iterate, release resource first
        close();
        if (exception != null) {
            throw exception;
        }
        return false;
    }

    /**
     * Retrieves the next element. <code>NoSuchElementException</code> will be
     * thrown, if there is no other elements or <code>close()</code> has been
     * invoked.
     */
    public T next() throws NamingException {
        if (values == null || currentIndex >= values.size()) {
            throw new NoSuchElementException();
        }

        return values.get(currentIndex++);
    }

    public boolean hasMoreElements() {
        if (values == null) {
            return false;
        }

        if (currentIndex < values.size()) {
            return true;
        }
        return false;
    }

    public T nextElement() {
        if (values == null || currentIndex >= values.size()) {
            throw new NoSuchElementException();
        }

        return values.get(currentIndex++);
    }

    protected void setException(NamingException exception) {
        this.exception = exception;
    }

}
