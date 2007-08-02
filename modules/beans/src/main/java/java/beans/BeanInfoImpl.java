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

package java.beans;

import java.awt.Image;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.harmony.beans.internal.nls.Messages;

class BeanInfoImpl implements BeanInfo {

    private static final int MT_OTHER = 0;

    private static final int MT_SETTER = 1;

    private static final int MT_GETTER = 2;

    private static final int MT_BOOLEAN_GETTER = 3;

    private static final int MT_INDEXED_SETTER = 4;

    private static final int MT_INDEXED_GETTER = 5;

    public BeanInfoImpl(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyDescriptor[] getPropertyDescriptors() {
        if (propertyDescriptors == null) {
            HashMap<String, PropertyDescriptor> result =
                    new HashMap<String, PropertyDescriptor>();

            if (beanClass != null) {
                List<Method> beanClassMethodsArrayList =
                        getPublicMethods(beanClass);

                ArrayList<Method> setters = new ArrayList<Method>();
                ArrayList<Method> getters = new ArrayList<Method>();
                ArrayList<Method> booleanGetters = new ArrayList<Method>();
                ArrayList<Method> indexedSetters = new ArrayList<Method>();
                ArrayList<Method> indexedGetters = new ArrayList<Method>();

                Iterator<Method> iterator = beanClassMethodsArrayList
                        .iterator();
                Object[] values;

                while (iterator.hasNext()) {
                    Method method = iterator.next();

                    int methodType = getMethodType(method);

                    if (methodType == MT_SETTER) {
                        setters.add(method);
                    } else if (methodType == MT_GETTER) {
                        getters.add(method);
                    } else if (methodType == MT_BOOLEAN_GETTER) {
                        booleanGetters.add(method);
                    } else if (methodType == MT_INDEXED_SETTER) {
                        indexedSetters.add(method);
                    } else if (methodType == MT_INDEXED_GETTER) {
                        indexedGetters.add(method);
                    }
                }

                try {
                    addPropertyDescriptorsFromMethods(result, indexedGetters,
                            MT_INDEXED_GETTER);
                    addPropertyDescriptorsFromMethods(result, indexedSetters,
                            MT_INDEXED_SETTER);
                    addPropertyDescriptorsFromMethods(result, booleanGetters,
                            MT_BOOLEAN_GETTER);
                    addPropertyDescriptorsFromMethods(result, getters,
                            MT_GETTER);
                    addPropertyDescriptorsFromMethods(result, setters,
                            MT_SETTER);
                } catch (Exception e) {
                    // empty
                }

                values = result.values().toArray();
                propertyDescriptors = new PropertyDescriptor[values.length];
                for (int k = 0; k < propertyDescriptors.length; ++k) {
                    propertyDescriptors[k] = (PropertyDescriptor) values[k];
                }
            }
        }

        return propertyDescriptors;
    }

    public MethodDescriptor[] getMethodDescriptors() {
        if (methodDescriptors == null) {
            List<Method> beanClassMethodsArrayList = getPublicMethods(beanClass);

            methodDescriptors = new MethodDescriptor[
                    beanClassMethodsArrayList.size()];

            for (int i = 0; i < beanClassMethodsArrayList.size(); ++i) {
                methodDescriptors[i] = new MethodDescriptor(
                        beanClassMethodsArrayList.get(i));
            }
        }

        return methodDescriptors;
    }

    public EventSetDescriptor[] getEventSetDescriptors() {
        if (eventSetDescriptors == null) {
            Map<String, EventSetDescriptor> result =
                    new HashMap<String, EventSetDescriptor>();
            List<Method> beanClassMethodsArrayList = getPublicMethods(beanClass);

            for (Method method : beanClassMethodsArrayList) {
                String methodName = method.getName();

                String listenerCoreName = null;

                if (methodName.endsWith("Listener")) { //$NON-NLS-1$
                    listenerCoreName = methodName.substring(0, methodName
                            .lastIndexOf("Listener")); //$NON-NLS-1$

                    if (methodName.startsWith("add")) { //$NON-NLS-1$
                        listenerCoreName = listenerCoreName.substring(3);
                    } else if (methodName.startsWith("remove")) { //$NON-NLS-1$
                        listenerCoreName = listenerCoreName.substring(6);
                    } else {
                        continue;
                    }

                    if (result.get(listenerCoreName) == null) {
                        Class<?>[] parameterTypes = method.getParameterTypes();

                        if (parameterTypes.length == 1) {
                            Class<?> listenerType = parameterTypes[0];

                            // full and short names of classes
                            String listenerTypeName = listenerType.getName();

                            // check if the listener name extracted from param
                            // name and
                            // listener name extracted from registration method
                            // are the same
                            String listenerNameFromParam =
                                    extractShortClassName(listenerTypeName);
                            
                            String listenerNameFromMethod = listenerCoreName
                                    + "Listener"; //$NON-NLS-1$

                            if (!listenerNameFromParam
                                    .equals(listenerNameFromMethod)) {
                                continue;
                            }

                            // FIXME valid only for events that have the same
                            // package (and encapsulating type if any) 
                            // with listener
                            String eventTypeName = listenerTypeName.substring(
                                    0, listenerTypeName.indexOf(
                                            listenerNameFromParam))
                                    + listenerCoreName + "Event"; //$NON-NLS-1$

                            // classes generated from classes names
                            Class<?> eventType = null;

                            try {
                                eventType = Class.forName(eventTypeName, true,
                                        beanClass.getClassLoader());
                            } catch (ClassNotFoundException cnfe) {
                                System.out.println(Messages.getString(
                                        "beans.3A", eventTypeName)); //$NON-NLS-1$
                            } finally {
                                if (eventType == null) {
                                    continue;
                                }
                            }

                            Method[] methods = listenerType.getMethods();
                            Vector<Method> listenerMethodsVec =
                                    new Vector<Method>();
                            for (Method element : methods) {
                                Class<?>[] listenerMethodParams = element
                                        .getParameterTypes();

                                if (listenerMethodParams.length == 1
                                        && listenerMethodParams[0] == eventType) {
                                    listenerMethodsVec.add(element);
                                }
                            }

                            Method[] listenerMethods =
                                  listenerMethodsVec.toArray(
                                        new Method[listenerMethodsVec.size()]);
                                
                            Method addListenerMethod = null;

                            try {
                                addListenerMethod = beanClass.getMethod(
                                        "add" + listenerCoreName //$NON-NLS-1$
                                        + "Listener", //$NON-NLS-1$
                                        new Class[] { listenerType });
                            } catch (NoSuchMethodException nsme) {
                                // no adder found
                                continue;
                            }

                            Method removeListenerMethod = null;

                            try {
                                removeListenerMethod = beanClass.getMethod(
                                        "remove" + listenerCoreName //$NON-NLS-1$
                                        + "Listener", //$NON-NLS-1$
                                        new Class[] { listenerType });
                            } catch (NoSuchMethodException nsme) {
                                // no remover found
                                continue;
                            }

                            Method getListenerMethod = null;

                            try {
                                getListenerMethod = beanClass.getMethod(
                                        "get" + listenerCoreName //$NON-NLS-1$
                                        + "Listeners", //$NON-NLS-1$
                                        new Class[] {});
                            } catch (NoSuchMethodException nsme) {
                                // no action - getter is not a mandatory method
                                // in event set descriptor pattern
                            }

                            try {
                                listenerCoreName = Introspector
                                        .decapitalize(listenerCoreName);
                                EventSetDescriptor esd = new EventSetDescriptor(
                                        listenerCoreName, listenerType,
                                        listenerMethods, addListenerMethod,
                                        removeListenerMethod, getListenerMethod);
                                result.put(listenerCoreName, esd);
                            } catch (IntrospectionException ie) {
                                System.out.println(Messages.getString(
                                        "beans.39", listenerCoreName)); //$NON-NLS-1$
                            }

                        } // if length of parameters list equals to 1
                    } // if no descriptors for given listener name yet
                } // if method name ends with Lister
            } // methods loop

            String[] eventSetDescriptorNames = result.keySet().toArray(
                    new String[result.size()]);
            
            Arrays.sort(eventSetDescriptorNames);

            eventSetDescriptors =
                    new EventSetDescriptor[eventSetDescriptorNames.length];
            for (int j = 0; j < eventSetDescriptorNames.length; ++j) {
                eventSetDescriptors[j] = result.get(eventSetDescriptorNames[j]);
            }

        }
        return eventSetDescriptors;
    }

    public BeanInfo[] getAdditionalBeanInfo() {
        // no info is obtained through automatic introspection
        return null;
    }

    public BeanDescriptor getBeanDescriptor() {
        return new BeanDescriptor(beanClass, null);
    }

    public Image getIcon(int iconKind) {
        // no info is obtained thru automatic introspection
        return null;
    }

    public int getDefaultPropertyIndex() {
        return -1;
    }

    public int getDefaultEventIndex() {
        return -1;
    }

    private static int getMethodType(Method method) {
        String name = method.getName();
        int result = MT_OTHER;
        try {
            int modifiers = method.getModifiers();

            if (Modifier.isPublic(modifiers) && !Modifier.isStatic(modifiers)) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                Class<?> returnType = method.getReturnType();

                if (name.startsWith("get") && (parameterTypes.length == 0) //$NON-NLS-1$
                        && (returnType != void.class) && (name.length() > 3)) {
                    result = MT_GETTER;
                } else if (name.startsWith("is") //$NON-NLS-1$
                        && (parameterTypes.length == 0)
                        && (returnType == boolean.class) && (name.length() > 2)) {
                    result = MT_BOOLEAN_GETTER;
                } else if (name.startsWith("get") //$NON-NLS-1$
                        && (parameterTypes.length == 1)
                        && (returnType != void.class)
                        && (parameterTypes[0] == int.class)
                        && (name.length() > 3)) {
                    result = MT_INDEXED_GETTER;
                } else if (name.startsWith("set") //$NON-NLS-1$
                        && (parameterTypes.length == 1)
                        && (returnType == void.class) && (name.length() > 3)) {
                    result = MT_SETTER;
                } else if (name.startsWith("set") //$NON-NLS-1$
                        && (parameterTypes.length == 2)
                        && (returnType == void.class)
                        && (parameterTypes[0] == int.class)
                        && (name.length() > 3)) {
                    result = MT_INDEXED_SETTER;
                }
            }
        } catch (Exception e) {
            result = MT_OTHER;
        }

        return result;
    }

    private static String extractPropertyName(String methodName)
            throws Exception {
        String result = null;

        if (methodName.startsWith("set") //$NON-NLS-1$ 
                || methodName.startsWith("get")) {  //$NON-NLS-1$
            result = methodName.substring(3);
            result = Introspector.decapitalize(result);
        } else if (methodName.startsWith("is")) { //$NON-NLS-1$
            result = methodName.substring(2);
            result = Introspector.decapitalize(result);
        }

        return result;
    }

    private static List<Method> getPublicMethods(Class<?> theClass) {
        List<Method> result = new ArrayList<Method>();
        Method[] beanClassMethods = theClass.getDeclaredMethods();

        for (Method element : beanClassMethods) {
            if (Modifier.isPublic(element.getModifiers())) {
                result.add(element);
            }
        }

        return result;
    }

    // XXX
    private void addPropertyDescriptorsFromMethods(
            Map<String, PropertyDescriptor> hmPropertyDescriptors,
            List<Method> methods, int typeOfMethods) throws Exception {
        
        assert typeOfMethods == MT_INDEXED_GETTER ||       
                typeOfMethods == MT_INDEXED_SETTER ||
                typeOfMethods == MT_GETTER ||
                typeOfMethods == MT_SETTER ||
                typeOfMethods == MT_BOOLEAN_GETTER;

        for (Method method : methods) {
            String propertyName = extractPropertyName(method.getName());
            PropertyDescriptor pd = hmPropertyDescriptors.get(propertyName);
            
            if (pd == null) {
                switch (typeOfMethods) {
                case MT_INDEXED_GETTER:
                    pd = new IndexedPropertyDescriptor(propertyName, null,
                            null, method, null);
                    break;
                case MT_INDEXED_SETTER:
                    pd = new IndexedPropertyDescriptor(propertyName, null,
                            null, null, method);
                    break;
                case MT_GETTER:
                case MT_BOOLEAN_GETTER:    
                    pd = new PropertyDescriptor(propertyName, method, null);
                    break;                    
                case MT_SETTER:
                    pd = new PropertyDescriptor(propertyName, null, method);
                    break;                    
                }
                
                hmPropertyDescriptors.put(propertyName, pd);
            } else {
                Method oldM;

                switch (typeOfMethods) {
                case MT_INDEXED_GETTER:
                    assert (pd instanceof IndexedPropertyDescriptor);
                    
                    oldM = ((IndexedPropertyDescriptor) pd)
                            .getIndexedReadMethod();
                    if (oldM == null) {
                        ((IndexedPropertyDescriptor) pd).setIndexedReadMethod(
                                method);
                    }
                    break;
                case MT_INDEXED_SETTER:
                    assert (pd instanceof IndexedPropertyDescriptor);
                    
                    oldM = ((IndexedPropertyDescriptor) pd)
                            .getIndexedWriteMethod();
                    if (oldM == null) {
                        ((IndexedPropertyDescriptor) pd).setIndexedWriteMethod(
                                method);
                    }
                    break;
                case MT_GETTER:
                case MT_BOOLEAN_GETTER:    
                    oldM = pd.getReadMethod();
                    if (oldM == null) {
                        pd.setReadMethod(method);
                    }
                    break;
                case MT_SETTER:
                    oldM = pd.getWriteMethod();
                    if (oldM == null) {
                        pd.setWriteMethod(method);
                    }
                    break;
                }
            } // if pd not null
        } // methods loop
    } // end of method

    /**
     * @param fullClassName full name of the class
     * @return name with package and encapsulating class info omitted 
     */
    static String extractShortClassName(String fullClassName) {
        int k = fullClassName.lastIndexOf('$');
        
        k = (k == -1 ? fullClassName.lastIndexOf('.') : k);
        return fullClassName.substring(k + 1);
    }
    
    private Class<?> beanClass = null;

    private PropertyDescriptor[] propertyDescriptors = null;

    private MethodDescriptor[] methodDescriptors = null;

    private EventSetDescriptor[] eventSetDescriptors = null;

}
