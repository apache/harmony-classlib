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
 * @version $Revision: 1.9.4.3 $
 */
package java.beans;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Vector;

import org.apache.harmony.beans.internal.nls.Messages;

/**
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.9.4.3 $
 */

public class PropertyDescriptor extends FeatureDescriptor {

    Class<?> beanClass = null;
    String propertyName = null;
    
    Method getter = null;
    Method setter = null;
    
    Class<?> propertyEditorClass = null;
    
    boolean constrained = false;
    boolean bound = false;

    /**
     * @com.intel.drl.spec_ref
     */
    public PropertyDescriptor(String propertyName, Class<?> beanClass,
            String getterName, String setterName) throws IntrospectionException {

        super();
        if (beanClass == null) {
            throw new IntrospectionException(Messages.getString("beans.03")); //$NON-NLS-1$
        }
        if (propertyName == null || propertyName.length() == 0) {
            throw new IntrospectionException(Messages.getString("beans.04")); //$NON-NLS-1$
        }

        this.beanClass = beanClass;
        this.propertyName = propertyName;

        this.setName(propertyName);
        this.setDisplayName(propertyName);

        if (setterName != null) {
            if (hasMethod(beanClass, setterName)) {
                setWriteMethod(beanClass, setterName);
            } else {
                throw new IntrospectionException(Messages.getString("beans.20"));
            }
        }
        if (getterName != null) {
            if (hasMethod(beanClass, getterName)) {
                setReadMethod(beanClass, getterName);
            } else {
                throw new IntrospectionException(Messages.getString("beans.1F"));
            }
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public PropertyDescriptor(String propertyName, Method getter, Method setter)
            throws IntrospectionException {
        super();
        if (propertyName == null || propertyName.length() == 0) {
            throw new IntrospectionException(Messages.getString("beans.04")); //$NON-NLS-1$
        }

        this.propertyName = propertyName;

        this.setName(propertyName);
        this.setDisplayName(propertyName);

        setWriteMethod(setter);
        setReadMethod(getter);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public PropertyDescriptor(String propertyName, Class<?> beanClass)
            throws IntrospectionException {
        super();
        if (beanClass == null) {
            throw new IntrospectionException(Messages.getString("beans.03")); //$NON-NLS-1$
        }
        if (propertyName == null || propertyName.length() == 0) {
            throw new IntrospectionException(Messages.getString("beans.04")); //$NON-NLS-1$
        }

        this.propertyName = propertyName;
        
        this.setName(propertyName);
        this.setDisplayName(propertyName);
        
        String getterName = createDefaultMethodName(propertyName, "is"); //$NON-NLS-1$
        if(hasMethod(beanClass, getterName)) {
            setReadMethod(beanClass, getterName);
        } else {
            getterName = createDefaultMethodName(propertyName, "get"); //$NON-NLS-1$
            if(hasMethod(beanClass, getterName)) {
                setReadMethod(beanClass, getterName);
            } else {
                throw new IntrospectionException(Messages.getString("beans.1F"));
            }
        }
        String setterName = createDefaultMethodName(propertyName, "set"); //$NON-NLS-1$
        if(hasMethod(beanClass, setterName)) {
            setWriteMethod(beanClass, setterName);
        } else {
            throw new IntrospectionException(Messages.getString("beans.20"));
        }
    }
        
    /**
     * @com.intel.drl.spec_ref
     */
    public void setWriteMethod(Method setter) throws IntrospectionException {
        if (setter != null) {
            int modifiers = setter.getModifiers();
            if (!Modifier.isPublic(modifiers)) {
                throw new IntrospectionException(
                        Messages.getString("beans.05")); //$NON-NLS-1$
            }
            
            Class[] parameterTypes = setter.getParameterTypes();
            if (parameterTypes.length != 1) {
                throw new IntrospectionException(
                        Messages.getString("beans.06")); //$NON-NLS-1$
            }
            
            Class<?> parameterType = parameterTypes[0];
            Class<?> propertyType = getPropertyType();
            if(propertyType != null && !propertyType.equals(parameterType)) {
                throw new IntrospectionException(
                        Messages.getString("beans.07")); //$NON-NLS-1$
            }
        }
        
        this.setter = setter;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void setReadMethod(Method getter) throws IntrospectionException {
        if (getter != null) {
            int modifiers = getter.getModifiers();
            if (!Modifier.isPublic(modifiers)) {
                throw new IntrospectionException(
                        Messages.getString("beans.0A")); //$NON-NLS-1$
            }
            
            Class[] parameterTypes = getter.getParameterTypes();
            if (parameterTypes.length != 0) {
                throw new IntrospectionException(
                        Messages.getString("beans.08")); //$NON-NLS-1$
            }
            
            Class<?> returnType = getter.getReturnType();
            if (returnType.equals(Void.TYPE)) {
                throw new IntrospectionException(Messages.getString("beans.33")); //$NON-NLS-1$
            }
            Class<?> propertyType = getPropertyType();
            if((propertyType != null) && !returnType.equals(propertyType)) {
                throw new IntrospectionException(
                        Messages.getString("beans.09")); //$NON-NLS-1$
            }
        }
        
        this.getter = getter;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Method getWriteMethod() {
        return setter;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Method getReadMethod() {
        return getter;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean equals(Object object) {
        boolean result = (object != null);
        if (result) {
            PropertyDescriptor pd = (PropertyDescriptor) object;
            
            boolean gettersAreEqual = (this.getter == null)
                    && (pd.getReadMethod() == null) || (this.getter != null)
                    && (this.getter.equals(pd.getReadMethod()));
            
            boolean settersAreEqual = (this.setter == null)
                    && (pd.getWriteMethod() == null) || (this.setter != null)
                    && (this.setter.equals(pd.getWriteMethod()));
            
            boolean propertyTypesAreEqual =
                    this.getPropertyType() == pd.getPropertyType();
            boolean propertyEditorClassesAreEqual =
                    this.getPropertyEditorClass() == pd.getPropertyEditorClass();
            boolean boundPropertyAreEqual = this.isBound() == pd.isBound();
            boolean constrainedPropertyAreEqual =
                    this.isConstrained() == pd.isConstrained();
            
            result = gettersAreEqual
                    && settersAreEqual
                    && propertyTypesAreEqual
                    && propertyEditorClassesAreEqual
                    && boundPropertyAreEqual
                    && constrainedPropertyAreEqual;
        };
        return result;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void setPropertyEditorClass(Class<?> propertyEditorClass) {
        this.propertyEditorClass = propertyEditorClass;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Class<?> getPropertyType() {
        Class<?> result = null;
        if (getter != null) {
            result = getter.getReturnType();
        } else if (setter != null) {
            Class[] parameterTypes = setter.getParameterTypes();
            result = parameterTypes[0];
        }
        return result;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Class<?> getPropertyEditorClass() {
        return propertyEditorClass;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void setConstrained(boolean constrained) {
        this.constrained = constrained;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void setBound(boolean bound) {
        this.bound = bound;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean isConstrained() {
        return constrained;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean isBound() {
        return bound;
    }
    
    boolean hasMethod(Class<?> beanClass, String methodName) {
        Method[] methods = findMethods(beanClass, methodName);
        return (methods.length > 0);
    }
    
    String createDefaultMethodName(String propertyName, String prefix) {
        String result = null;
        if (propertyName != null) {
            String bos = propertyName.substring(0, 1).toUpperCase();
            String eos = propertyName.substring(1, propertyName.length());
            result = prefix + bos + eos;
        }
        return result;
    }
    
    Method[] findMethods(Class<?> aClass, String methodName) {
        Method[] allMethods = aClass.getMethods();
        Vector<Method> matchedMethods = new Vector<Method>();
        for (int i = 0; i < allMethods.length; ++i) {
            Method method = allMethods[i];
            if (method.getName().equals(methodName)) {
                matchedMethods.add(method);
            }
        }
        
        Method[] result = new Method[matchedMethods.size()];
        for(int j = 0; j < matchedMethods.size(); ++j) {
            result[j] = (Method) matchedMethods.elementAt(j);
        }
        return result;
    }
    
    private void setReadMethod(Class<?> beanClass, String getterName) {
        boolean result = false;
        
        Method[] getters = findMethods(beanClass, getterName);
        
        for (int i = 0; i < getters.length; ++i) {
            try {
                setReadMethod(getters[i]);
                result = true;
            } catch (IntrospectionException ie) {
            }
            
            if (result) {
                break;
            }
        }
    }

    private void setWriteMethod(Class<?> beanClass, String setterName)
            throws IntrospectionException {
        boolean result = false;
        
        Method[] setters = findMethods(beanClass, setterName);
        
        for (int i = 0; i < setters.length; ++i) {
            try {
                setWriteMethod(setters[i]);
                result = true;
            } catch (IntrospectionException ie) {
            }
            
            if (result) {
                break;
            }
        }
    }
}
