/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
 * @author Maxim V. Berkultsev
 * @author Alexei Y. Zakharov
 * @version $Revision: 1.1.2.1 $
 */
package org.apache.harmony.beans;

import java.beans.Encoder;
import java.beans.Expression;
import java.beans.PersistenceDelegate;
import java.beans.Statement;
import java.lang.reflect.Array;

/**
 * @author Maxim V. Berkultsev
 * @author Alexei Y. Zakharov
 * @version $Revision: 1.1.2.1 $
 */

public class ArrayPersistenceDelegate extends PersistenceDelegate {

    private static PersistenceDelegate pd = null;
    
    public static PersistenceDelegate getInstance() {
        if(pd == null) {
            pd = new ArrayPersistenceDelegate();
        }
        return pd;
    }

    protected Expression instantiate(Object oldInstance, Encoder out) {
        int length = Array.getLength(oldInstance);
        Class componentType = oldInstance.getClass().getComponentType();

        return new Expression(oldInstance, Array.class, "newInstance",
                new Object[] { componentType, new Integer(length) });
    }

    protected void initialize(
            Class type, Object oldInstance, Object newInstance, Encoder out) {
        int length = Array.getLength(oldInstance);
        Class componentType = type.getComponentType();
        
        Object nullValue = null;
        if(componentType != null) { // is array
            Object array = Array.newInstance(componentType, 1);
            nullValue = Array.get(array, 0);
        }
        
        for(int i = 0; i < length; ++i) {
            
            Object oldValue = Array.get(oldInstance, i);
            Object newValue = Array.get(newInstance, i);
            
            if(oldValue != null && !oldValue.equals(newValue) ||
                   oldValue == null && newValue != null)
            {
                if(nullValue == null || !nullValue.equals(oldValue)) {
                    Statement s = new Statement(oldInstance, "set",
                            new Object[]{ new Integer(i), oldValue });
                    out.writeStatement(s);
                }
            }
            
        }
    }

    protected boolean mutatesTo(Object oldInstance, Object newInstance) {
        if (oldInstance != null && newInstance != null) {
            Class oldCl = oldInstance.getClass();
            Class newCl = newInstance.getClass();

            if (oldCl.isArray() && !newCl.isArray() ||
                    newCl.isArray() && !oldCl.isArray()) {
                return false;
            } else if (oldCl.isArray() && newCl.isArray()) {
                // both are arrays
                int l1 = Array.getLength(oldInstance);
                int l2 = Array.getLength(newInstance);
                Class cType1 = oldCl.getComponentType();
                Class cType2 = newCl.getComponentType();

                if (l1 == l2 && cType1.equals(cType2)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        // both are nulls or have non-Array type
        return super.mutatesTo(oldInstance, newInstance);
    }
}
