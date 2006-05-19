/* 
*  Copyright 2005 The Apache Software Foundation or its licensors, as applicable. 
* 
*  Licensed under the Apache License, Version 2.0 (the "License"); 
*  you may not use this file except in compliance with the License. 
*  You may obtain a copy of the License at 
* 
*    http://www.apache.org/licenses/LICENSE-2.0 
* 
*  Unless required by applicable law or agreed to in writing, software 
*  distributed under the License is distributed on an "AS IS" BASIS, 
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
*  See the License for the specific language governing permissions and 
*  limitations under the License. 
*/
package org.apache.harmony.rmi.internal.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * This class constructs a generic structure by which is possible to relate one
 * element "A1" with a list of elements "L11, L12, ..., L1n". This structure
 * also maintains a list of these relationships "A1, A2, …, Am" with the
 * corresponding series "L11, L12, ..., L1n, ...", "L21, L22, ..., L2n", ...,
 * "Lm1, Lm2, ..., Lmn". Thus, if an element "Ai" is known, this class provides
 * a method that returns a corresponding list of elements "Li". On the other
 * hand, if an element "Lij" of the list is known, this class provides a method
 * that return the element "Ai" which contains "Lij".
 * 
 * @author Gustavo Petri
 */

public final class ReversibleHashSet<E, T> {

    private HashMap<E, Set<T>> oneToMany;

    private HashMap<T, E> oneToOne;

    /**
     * Constructor of the class
     */
    public ReversibleHashSet() {
        this.oneToMany = new HashMap<E, Set<T>>();
        this.oneToOne = new HashMap<T, E>();
    }

    /**
     * This method inserts a value
     * 
     * @param key
     *            the value of the series's identifier
     * @param value
     *            of the an element of the series
     */
    public final void insert(E key, T value) {

        if (oneToMany.get(key) != null) {
            oneToMany.get(key).add(value);
        } else {
            HashSet<T> newHashSet = new HashSet<T>();
            newHashSet.add(value);
            oneToMany.put(key, newHashSet);
        }
        oneToOne.put(value, key);

        return;
    }

    /**
     * This method returns a list of the elements of the series
     * 
     * @param key
     *            the value of the series's identifier
     * @return a list of the elements of the series
     */
    public final Set<T> getValues(E key) {
        return oneToMany.get(key);
    }

    /**
     * This method returns the value of the series's identifier
     * 
     * @param value
     *            of the an element of the series
     * @return the key of the series, the value of the series's identifier
     */
    public final E getKeyFromValue(T value) {
        return oneToOne.get(value);
    }

    /**
     * This method validate if exists an element in the series
     * 
     * @param key
     *            the value of the series's identifier
     * @return <code>true</code> if exists an element in the list, else
     *         <code>false</code>
     */
    public final  boolean hasValues(E key) {
        return !(oneToMany.get(key) == null);
    }

    /**
     * This method remove a value of the series
     * 
     * @param value
     *            of the an element of the series
     */
    public final void removeValue(T value) {
        E key = oneToOne.get(value);
        if (key != null) {
            oneToMany.get(key).remove(value);
            oneToOne.remove(value);
            if (oneToMany.get(key).isEmpty())
                oneToMany.remove(key);
        }
    }
}
