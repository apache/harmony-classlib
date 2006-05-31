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
 * @version $Revision: 1.5.6.3 $
 */
package java.beans;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.5.6.3 $
 */

public class IndexedPropertyDescriptor extends PropertyDescriptor {
    
    private Method indexedGetter = null;
    private Method indexedSetter = null;

    /**
     * @com.intel.drl.spec_ref
     */
    public IndexedPropertyDescriptor(
            String propertyName,
            Class<?> beanClass,
            String getterName,
            String setterName,
            String indexedGetterName,
            String indexedSetterName) throws IntrospectionException {
        super(propertyName, beanClass, getterName, setterName);
        setIndexedReadMethod(beanClass, indexedGetterName);
        setIndexedWriteMethod(beanClass, indexedSetterName);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public IndexedPropertyDescriptor(
            String propertyName,
            Method getter,
            Method setter,
            Method indexedGetter,
            Method indexedSetter) throws IntrospectionException {
        super(propertyName, getter, setter);
        setIndexedReadMethod(indexedGetter);
        setIndexedWriteMethod(indexedGetter);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public IndexedPropertyDescriptor(String propertyName, Class<?> beanClass)
            throws IntrospectionException {
        super(propertyName, beanClass);        
        
        String indexedGetterName = createDefaultMethodName(propertyName, "get");
        if(hasMethod(beanClass, indexedGetterName)) {
            setIndexedReadMethod(beanClass, indexedGetterName);
        }
        
        String indexedSetterName = createDefaultMethodName(propertyName, "set");
        if(hasMethod(beanClass, indexedSetterName)) {
            setIndexedWriteMethod(beanClass, indexedSetterName);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void setIndexedReadMethod(Method indexedGetter)
            throws IntrospectionException {
        if (indexedGetter != null) {
            int modifiers = indexedGetter.getModifiers();
            if (!Modifier.isPublic(modifiers)) {
                throw new IntrospectionException(
                        "Modifier for indexed getter method should be public.");
            }
            
            Class[] parameterTypes = indexedGetter.getParameterTypes();
            if (parameterTypes.length != 1) {
                throw new IntrospectionException(
                        "Number of parameters in getter method is not "
                        + "equal to 1.");
            }
            
            if (!parameterTypes[0].equals(int.class)) {
                throw new IntrospectionException(
                        "Parameter in indexed getter method is not of "
                        + "integer type.");
            }
            
            Class returnType = indexedGetter.getReturnType();
            Class indexedPropertyType = getIndexedPropertyType();
            if((indexedPropertyType != null) && !returnType.equals(
                    indexedPropertyType)) {
                throw new IntrospectionException(
                        "Parameter type in indexed getter method does not "
                        + "correspond to predefined.");
            }
        }
        
        this.indexedGetter = indexedGetter;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void setIndexedWriteMethod(Method indexedSetter)
            throws IntrospectionException {
        if (indexedSetter != null) {
            int modifiers = indexedSetter.getModifiers();
            if (!Modifier.isPublic(modifiers)) {
                throw new IntrospectionException(
                        "Modifier for indexed setter method should be public.");
            }
            
            Class[] parameterTypes = indexedSetter.getParameterTypes();
            if (parameterTypes.length != 2) {
                throw new IntrospectionException(
                        "Number of parameters in indexed setter method is not "
                        + "equal to 2.");
            }
            
            Class firstParameterType = parameterTypes[0];
            if (!firstParameterType.equals(int.class)) {
                throw new IntrospectionException(
                        "First parameter type in indexed setter method "
                        + "should be int.");
            }            
            
            Class secondParameterType = parameterTypes[1];
            if (!secondParameterType.equals(getIndexedPropertyType())) {
                throw new IntrospectionException(
                        "Second parameter type in indexed setter method "
                        + "does not corresponds to predefined.");
            }
        }
        
        this.indexedSetter = indexedSetter;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Method getIndexedWriteMethod() {
        return indexedSetter;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Method getIndexedReadMethod() {
        return indexedGetter;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean equals(Object obj) {
        boolean result = super.equals(obj);
        if(result) {
            IndexedPropertyDescriptor pd = (IndexedPropertyDescriptor) obj;
            result = 
                (this.indexedGetter.equals(pd.getIndexedReadMethod())) &&
                (this.indexedSetter.equals(pd.getIndexedWriteMethod()));
            return result;
        }
        return result;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Class<?> getIndexedPropertyType() {
        Class result = null;
        if (indexedGetter != null) {
            result = indexedGetter.getReturnType();
        } else if (indexedSetter != null) {
            Class[] parameterTypes = indexedSetter.getParameterTypes();
            result = parameterTypes[1];
        }
        return result;        
    }
    
    private void setIndexedReadMethod(Class beanClass,
            String indexedGetterName) {
        Method[] getters = findMethods(beanClass, indexedGetterName);
        boolean result = false;
        for (int i = 0; i < getters.length; ++i) {
            try {
                setIndexedReadMethod(getters[i]);
                result = true;
            } catch (IntrospectionException ie) {}
            if (result) break;
        }
    }
    
    private void setIndexedWriteMethod(Class beanClass,
            String indexedSetterName) {
        Method[] setters = findMethods(beanClass, indexedSetterName);
        boolean result = false;
        for (int i = 0; i < setters.length; ++i) {
            try {
                setIndexedWriteMethod(setters[i]);
                result = true;
            } catch (IntrospectionException ie) {}
            if (result) break;
        }
    }
}
