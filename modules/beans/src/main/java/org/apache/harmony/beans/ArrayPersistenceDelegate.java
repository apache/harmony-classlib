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
        Class type = getArrayWrapperClass(oldInstance.getClass());
        int length = Array.getLength(oldInstance);
        return new Expression(oldInstance, type, "new",
                new Object[] { new Integer(length) });
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
    
    private static Class getArrayWrapperClass(Class type) {
        Class result = type;
        if(type == boolean[].class) {
            result = Boolean[].class;
        } else if(type == byte[].class) {
            result = Byte[].class;
        } else if(type == char[].class) {
            result = Character[].class;
        } else if(type == double[].class) {
            result = Double[].class;
        } else if(type == float[].class) {
            result = Float[].class;
        } else if(type == int[].class) {
            result = Integer[].class;
        } else if(type == long[].class) {
            result = Long[].class;
        } else if(type == short[].class) {
            result = Short[].class;
        }
        return result;
    }
}
