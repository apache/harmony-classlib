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

    private static int MT_OTHER = 0;

    private static int MT_SETTER = 1;

    private static int MT_GETTER = 2;

    private static int MT_BOOLEAN_GETTER = 3;

    private static int MT_INDEXED_SETTER = 4;

    private static int MT_INDEXED_GETTER = 5;

    public BeanInfoImpl(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyDescriptor[] getPropertyDescriptors() {
        if (propertyDescriptors == null) {
            HashMap<String, PropertyDescriptor> result = new HashMap<String, PropertyDescriptor>();

            if (beanClass != null) {
                List<Method> beanClassMethodsArrayList = getPublicMethods(beanClass);

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
                    addIndexedPropertyDescriptorsFromMethodList(result,
                            indexedGetters, false);
                    addIndexedPropertyDescriptorsFromMethodList(result,
                            indexedSetters, true);
                    addPropertyDescriptorsFromMethodList(result,
                            booleanGetters, false);
                    addPropertyDescriptorsFromMethodList(result, setters, true);
                    addPropertyDescriptorsFromMethodList(result, getters, true);
                } catch (Exception e) {
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
            List<MethodDescriptor> result = new ArrayList<MethodDescriptor>();
            List<Method> beanClassMethodsArrayList = getPublicMethods(beanClass);

            Iterator<Method> iterator = beanClassMethodsArrayList.iterator();

            while (iterator.hasNext()) {
                Method method = iterator.next();
                result.add(new MethodDescriptor(method));
            }

            methodDescriptors = new MethodDescriptor[result.size()];
            for (int i = 0; i < methodDescriptors.length; ++i) {
                methodDescriptors[i] = result.get(i);
            }

        }

        return methodDescriptors;
    }

    public EventSetDescriptor[] getEventSetDescriptors() {
        if (eventSetDescriptors == null) {
            Map<String, EventSetDescriptor> result = new HashMap<String, EventSetDescriptor>();
            List<Method> beanClassMethodsArrayList = getPublicMethods(beanClass);

            Iterator<Method> iterator = beanClassMethodsArrayList.iterator();

            while (iterator.hasNext()) {
                Method method = iterator.next();
                String methodName = method.getName();

                String listenerName = null;

                if (methodName.endsWith("Listener")) { //$NON-NLS-1$
                    listenerName = methodName.substring(0, methodName
                            .lastIndexOf("Listener")); //$NON-NLS-1$

                    if (methodName.startsWith("add")) { //$NON-NLS-1$
                        listenerName = listenerName.substring(3);
                    } else if (methodName.startsWith("remove")) { //$NON-NLS-1$
                        listenerName = listenerName.substring(6);
                    } else {
                        continue;
                    }

                    if (result.get(listenerName) == null) {
                        Class[] parameterTypes = method.getParameterTypes();

                        if (parameterTypes.length == 1) {
                            Class<?> listenerType = parameterTypes[0];

                            // full and short names of classes
                            String listenerTypeName = listenerType.getName();

                            // check if the listener name extracted from param
                            // name and
                            // listener name extracted from registration method
                            // are the same
                            String listenerNameFromParam = listenerTypeName
                                    .substring(listenerTypeName
                                            .lastIndexOf(".") + 1); //$NON-NLS-1$

                            String listenerNameFromMethod = listenerName
                                    + "Listener"; //$NON-NLS-1$

                            if (!listenerNameFromParam
                                    .equals(listenerNameFromMethod)) {
                                continue;
                            }

                            String eventTypeName = listenerTypeName.substring(
                                    0, listenerTypeName.lastIndexOf(".") + 1) //$NON-NLS-1$
                                    + listenerName + "Event"; //$NON-NLS-1$

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
                            Vector<Method> listenerMethodsVec = new Vector<Method>();
                            for (Method element : methods) {
                                Class[] listenerMethodParams = element
                                        .getParameterTypes();

                                if (listenerMethodParams.length == 1
                                        && listenerMethodParams[0] == eventType) {
                                    listenerMethodsVec.add(element);
                                }
                            }

                            Method[] listenerMethods = new Method[listenerMethodsVec
                                    .size()];
                            Iterator<Method> iter = listenerMethodsVec
                                    .iterator();
                            int idx2 = 0;
                            while (iter.hasNext()) {
                                listenerMethods[idx2] = iter.next();
                                idx2++;
                            }

                            Method addListenerMethod = null;
                            String addListenerMethodName = "add" + listenerName //$NON-NLS-1$
                                    + "Listener"; //$NON-NLS-1$
                            try {
                                addListenerMethod = beanClass.getMethod(
                                        addListenerMethodName,
                                        new Class[] { listenerType });
                            } catch (NoSuchMethodException nsme) {
                                // no adder found
                                continue;
                            }

                            Method removeListenerMethod = null;
                            String removeListenerMethodName = "remove" //$NON-NLS-1$
                                    + listenerName + "Listener"; //$NON-NLS-1$
                            try {
                                removeListenerMethod = beanClass.getMethod(
                                        removeListenerMethodName,
                                        new Class[] { listenerType });
                            } catch (NoSuchMethodException nsme) {
                                // no remover found
                                continue;
                            }

                            Method getListenerMethod = null;
                            String getListenerMethodName = "get" + listenerName //$NON-NLS-1$
                                    + "Listeners"; //$NON-NLS-1$
                            try {
                                getListenerMethod = beanClass.getMethod(
                                        getListenerMethodName, new Class[] {});
                            } catch (NoSuchMethodException nsme) {
                                // no action - getter is not a mandatory method
                                // in event set descriptor pattern
                            }

                            try {
                                listenerName = Introspector
                                        .decapitalize(listenerName);
                                EventSetDescriptor esd = new EventSetDescriptor(
                                        listenerName, listenerType,
                                        listenerMethods, addListenerMethod,
                                        removeListenerMethod, getListenerMethod);
                                result.put(listenerName, esd);
                            } catch (IntrospectionException ie) {
                                System.out.println(Messages.getString(
                                        "beans.39", listenerName)); //$NON-NLS-1$
                            }

                        }
                    }
                }
            }

            String[] eventSetDescriptorNames = new String[result.keySet()
                    .size()];
            Iterator<String> i = result.keySet().iterator();
            int idx = 0;

            while (i.hasNext()) {
                eventSetDescriptorNames[idx++] = result.get(i.next()).getName();
            }

            Arrays.sort(eventSetDescriptorNames);

            eventSetDescriptors = new EventSetDescriptor[eventSetDescriptorNames.length];
            for (int j = 0; j < eventSetDescriptors.length; ++j) {
                eventSetDescriptors[j] = result.get(eventSetDescriptorNames[j]);
            }

        }
        return eventSetDescriptors;
    }

    public BeanInfo[] getAdditionalBeanInfo() {
        // no info is obtained thru automatic introspection
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
        return defaultPropertyIndex;
    }

    public int getDefaultEventIndex() {
        return defaultEventIndex;
    }

    private static int getMethodType(Method method) {
        String name = method.getName();
        int result = MT_OTHER;
        try {
            int modifiers = method.getModifiers();

            if (Modifier.isPublic(modifiers) && !Modifier.isStatic(modifiers)) {
                Class[] parameterTypes = method.getParameterTypes();
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

        if (methodName.startsWith("set") || methodName.startsWith("get")) { //$NON-NLS-1$ //$NON-NLS-2$
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

    private void addPropertyDescriptorsFromMethodList(
            HashMap<String, PropertyDescriptor> hmPropertyDescriptors,
            List<Method> methods, boolean checkExisting) throws Exception {
        for (Method method : methods) {
            String methodName = method.getName();
            String propertyName = extractPropertyName(methodName);

            if ((!checkExisting)
                    || (hmPropertyDescriptors.get(propertyName) == null)) {
                PropertyDescriptor propertyDescriptor = null;

                try {
                    propertyDescriptor = new PropertyDescriptor(propertyName,
                            beanClass);
                } catch (IntrospectionException ie) {
                    // no setter or getter
                    if (methodName.startsWith("set")) { //$NON-NLS-1$
                        try {
                            propertyDescriptor = new PropertyDescriptor(
                                    propertyName, beanClass, null, methodName);
                        } catch (IntrospectionException e) {
                            // no getter
                        }
                    } else if (methodName.startsWith("get") //$NON-NLS-1$
                            || methodName.startsWith("is")) { //$NON-NLS-1$
                        try {
                            propertyDescriptor = new PropertyDescriptor(
                                    propertyName, beanClass, methodName, null);
                        } catch (IntrospectionException e) {
                            // no setter
                        }
                    } else {
                        try {
                            propertyDescriptor = new PropertyDescriptor(
                                    propertyName, beanClass, null, null);
                        } catch (IntrospectionException e) {
                        }
                    }
                }
                if (propertyDescriptor != null) {
                    hmPropertyDescriptors.put(propertyName, propertyDescriptor);
                }
            }
        }
    }

    private void addIndexedPropertyDescriptorsFromMethodList(
            Map<String, PropertyDescriptor> hmPropertyDescriptors,
            List<Method> methods, boolean checkExisting) throws Exception {
        for (Method method : methods) {
            String methodName = method.getName();
            String propertyName = extractPropertyName(methodName);

            if ((!checkExisting)
                    || (hmPropertyDescriptors.get(propertyName) == null)) {
                IndexedPropertyDescriptor indexedPropertyDescriptor = null;

                try {
                    indexedPropertyDescriptor = new IndexedPropertyDescriptor(
                            propertyName, beanClass);
                    hmPropertyDescriptors.put(propertyName,
                            indexedPropertyDescriptor);
                } catch (IntrospectionException ie) {
                    // System.out.println(ie.getClass() + ": " +
                    // ie.getMessage()); //$NON-NLS-1$
                }
            }
        }
    }

    private Class<?> beanClass = null;

    private PropertyDescriptor[] propertyDescriptors = null;

    private MethodDescriptor[] methodDescriptors = null;

    private EventSetDescriptor[] eventSetDescriptors = null;

    private int defaultPropertyIndex = -1;

    private int defaultEventIndex = -1;

}
