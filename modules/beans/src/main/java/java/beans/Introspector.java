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
 * @version $Revision$
 */
package java.beans;

import java.awt.Image;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author Maxim V. Berkultsev
 * @version $Revision$
 */

public class Introspector {

    public static final int USE_ALL_BEANINFO = 1;

    public static final int IGNORE_IMMEDIATE_BEANINFO = 2;

    public static final int IGNORE_ALL_BEANINFO = 3;
    
    private Introspector() {}

    /**
     * @com.intel.drl.spec_ref
     */
    public static String decapitalize(String name) {
        if((name != null) && (name.length() > 0)) {
            // first two letters are capital
            if((name.length() > 1) && name.substring(0,2).equals(
                    name.substring(0,2).toUpperCase())) {
                return name;
            }
            String result = name.substring(0,1).toLowerCase();
            if(name.length() > 1) {
                result += name.substring(1);
            }
            return result;
           }
        return name;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static BeanInfo getBeanInfo(Class beanClass, int flags)
        throws IntrospectionException 
    {
        switch (flags) {
            case USE_ALL_BEANINFO:
                return getBeanInfo(beanClass, null, false, false);    
            case IGNORE_IMMEDIATE_BEANINFO:
                return getBeanInfo(beanClass, null, true, false);
            case IGNORE_ALL_BEANINFO:
                return getBeanInfo(beanClass, null, true, true);
            default:
                //TODO: verify that default beahvior complies with RI
                return getBeanInfo(beanClass, null, false, false);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static BeanInfo getBeanInfo(Class beanClass)
            throws IntrospectionException {
        return getBeanInfo(beanClass, null, false, false);    
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public static BeanInfo getBeanInfo(Class beanClass, Class stopClass)
       throws IntrospectionException
    {
        return getBeanInfo(beanClass, stopClass, false, false);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static synchronized void setBeanInfoSearchPath(String[] searchPath) {
        if (searchPath != null) {
            SecurityManager sm = System.getSecurityManager();
            if (sm != null) {
                sm.checkPropertiesAccess();
            }
            path = searchPath;
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static synchronized String[] getBeanInfoSearchPath() {
        return path;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static void flushFromCaches(Class clz) {
        removeBeanInfoClassFromCache(clz);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static void flushCaches() {
        removeAllBeanInfoClassesFromCache();
    }

    // private methods

    private static BeanInfoWrapper getBeanInfo(Class beanClass, Class stopClass,
        boolean ignoreBeanClassBeanInfo, boolean ignoreSuperClassBeanInfo)
    {
        if (beanClass == null) {
            throw new java.lang.NullPointerException();
        }

        BeanInfoWrapper beanInfoWrapper = findBeanInfoClassInCache(beanClass,
                stopClass, ignoreBeanClassBeanInfo, ignoreSuperClassBeanInfo);

        if (beanInfoWrapper != null) {
            return beanInfoWrapper;
        }

        // find bean info as a separate class
        
        BeanInfo beanInfo = null;
        if (!ignoreBeanClassBeanInfo) {
            try {
                Class beanInfoClass = findBeanInfoClass(beanClass);
                if(beanInfoClass != null) {
                    beanInfo = (BeanInfo) beanInfoClass.newInstance();
                }
            } catch (Exception e) {
            }
        }
        
        //...
        
        // generate bean info automatically
        
        BeanInfoImpl beanInfoImpl = new BeanInfoImpl(beanClass);
        
        //...
        
        BeanInfoWrapper wrapper = new BeanInfoWrapper(beanInfo, beanInfoImpl);
    
        Class parent = beanClass.getSuperclass();
        if ((parent != null) && (parent != stopClass)) {
            BeanInfoWrapper parentBeanInfo = getBeanInfo(parent, stopClass,
                ignoreSuperClassBeanInfo, ignoreSuperClassBeanInfo);
            
            wrapper.merge(parentBeanInfo);
        }
        
        //...
        
        storeBeanInfoClassInCache(wrapper, beanClass, stopClass,
            ignoreBeanClassBeanInfo, ignoreSuperClassBeanInfo);
        
        return wrapper;
    }

    private static Class findBeanInfoClass(Class beanClass) {
        String beanClassName = beanClass.getName();
        int idx = beanClassName.lastIndexOf(".");
        String shortBeanInfoClassName = beanClassName.substring(idx + 1,
            beanClassName.length()) + "BeanInfo";
        String fullBeanInfoClassName = beanClassName + "BeanInfo";

        Class beanInfoClass = null;
        try {
            beanInfoClass = Class.forName(
                fullBeanInfoClassName, true, beanClass.getClassLoader());
            
        } catch (ClassNotFoundException cnfe) {
            for (int i = 0; i < path.length; ++i) {
                try {
                    fullBeanInfoClassName = path[i] + "."
                            + shortBeanInfoClassName;
                    beanInfoClass = Class.forName(fullBeanInfoClassName, true,
                            beanClass.getClassLoader());
                    break;
                } catch (ClassNotFoundException cnfe2) {
                }
            }
        }
        
        return beanInfoClass;
    }

    private static BeanInfoWrapper findBeanInfoClassInCache(
            Class beanClass,
            Class stopClass,
            boolean ignoreBeanClassBeanInfo,
            boolean ignoreSuperClassBeanInfo) {
        BeanInfoWrapper result = null;
        ArrayList beanInfoDatas = (ArrayList) beanInfos.get(
                beanClass.getName());
        if (beanInfoDatas != null) {
            Iterator iterator = beanInfoDatas.iterator();
            while(iterator.hasNext()) {
                BeanInfoData beanInfoData = (BeanInfoData) iterator.next();
                if ((beanInfoData.getStopClass() == stopClass) &&
                    (beanInfoData.getIgnoreBeanClassBeanInfo()
                            ==  ignoreBeanClassBeanInfo)
                    && (beanInfoData.getIgnoreSuperClassBeanInfo()
                            ==  ignoreSuperClassBeanInfo))
                {
                    result = beanInfoData.getBeanInfoWrapper();
                }
                if (result != null) break;
            }
        }
        return result;
    }
    
    private static void storeBeanInfoClassInCache(
            BeanInfoWrapper beanInfoWrapper,
            Class beanClass,
            Class stopClass,
            boolean ignoreBeanClassBeanInfo,
            boolean ignoreSuperClassBeanInfo) {
        ArrayList beanInfoDatas = (ArrayList) beanInfos.get(
                beanClass.getName());
        if(beanInfoDatas == null) {
            beanInfoDatas = new ArrayList();
            beanInfos.put(beanClass.getName(), beanInfoDatas);
        }
        beanInfoDatas.add(new BeanInfoData(stopClass, ignoreBeanClassBeanInfo,
            ignoreSuperClassBeanInfo, beanInfoWrapper));
    }

    private static void removeBeanInfoClassFromCache(Class beanClass) {
        beanInfos.remove(beanClass.getName());
    }

    private static void removeAllBeanInfoClassesFromCache() {
        beanInfos.clear();
    }

    // private fields

    private static String[] path = {"sun.beans.infos"};
    private static HashMap beanInfos = new HashMap();

}

class BeanInfoData {

    private Class stopClass;
    private boolean ignoreBeanClassBeanInfo;
    private boolean ignoreSuperClassBeanInfo;
    private BeanInfoWrapper beanInfoWrapper;

    public BeanInfoData(BeanInfoWrapper beanInfo) {
        this.stopClass = null;
        this.ignoreBeanClassBeanInfo = false;
        this.ignoreSuperClassBeanInfo = false;
        this.beanInfoWrapper = beanInfo;
    }

    public BeanInfoData(Class stopClass, boolean ignoreBeanClassBeanInfo,
        boolean ignoreSuperClassBeanInfo, BeanInfoWrapper beanInfoWrapper)
    {
        this.stopClass = stopClass;
        this.ignoreBeanClassBeanInfo = ignoreBeanClassBeanInfo;
        this.ignoreSuperClassBeanInfo = ignoreSuperClassBeanInfo;
        this.beanInfoWrapper = beanInfoWrapper;
    }

    public Class getStopClass() {
        return stopClass;
    }

    public boolean getIgnoreBeanClassBeanInfo() {
        return ignoreBeanClassBeanInfo;
    }

    public boolean getIgnoreSuperClassBeanInfo() {
        return ignoreSuperClassBeanInfo;
    }

    public BeanInfoWrapper getBeanInfoWrapper() {
        return beanInfoWrapper;
    }
}

class BeanInfoImpl implements BeanInfo {

    private static int MT_OTHER = 0;
    private static int MT_SETTER = 1;
    private static int MT_GETTER = 2;
    private static int MT_BOOLEAN_GETTER = 3;
    private static int MT_INDEXED_SETTER = 4;
    private static int MT_INDEXED_GETTER = 5;

    public BeanInfoImpl(Class beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyDescriptor[] getPropertyDescriptors() {
        if (propertyDescriptors == null) {
            HashMap result = new HashMap();
            
            if (beanClass != null) {
                ArrayList beanClassMethodsArrayList = getPublicMethods(
                        beanClass);
                
                ArrayList setters = new ArrayList();
                ArrayList getters = new ArrayList();
                ArrayList booleanGetters = new ArrayList();
                ArrayList indexedSetters = new ArrayList();
                ArrayList indexedGetters = new ArrayList();
                
                Iterator iterator = beanClassMethodsArrayList.iterator();
                while (iterator.hasNext()) {
                    Method method = (Method) iterator.next();
                    
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
                    addPropertyDescriptorsFromMethodList(result, booleanGetters,
                            false);
                    addPropertyDescriptorsFromMethodList(result, setters, true);
                    addPropertyDescriptorsFromMethodList(result, getters, true);
                } catch (Exception e) {
                }
                
                Object[] values = result.values().toArray();
                propertyDescriptors = new PropertyDescriptor[values.length];
                for(int k = 0; k < propertyDescriptors.length; ++k) {
                    propertyDescriptors[k] = (PropertyDescriptor) values[k];
                }
            }
        }
        
        return propertyDescriptors;
    }

    public MethodDescriptor[] getMethodDescriptors() {
        if(methodDescriptors == null) {
            ArrayList result = new ArrayList();
            ArrayList beanClassMethodsArrayList = getPublicMethods(beanClass);
            
            Iterator iterator = beanClassMethodsArrayList.iterator();
            while (iterator.hasNext()) {
                Method method = (Method) iterator.next();
                result.add(new MethodDescriptor(method));
            }
            
            methodDescriptors = new MethodDescriptor[result.size()];
            for(int i = 0; i < methodDescriptors.length; ++i) {
                methodDescriptors[i] = (MethodDescriptor) result.get(i);
            }
            
        }
        
        return methodDescriptors;
    }

    public EventSetDescriptor[] getEventSetDescriptors() {
        if(eventSetDescriptors == null) {
            HashMap result = new HashMap();
            ArrayList beanClassMethodsArrayList = getPublicMethods(beanClass);
            
            Iterator iterator = beanClassMethodsArrayList.iterator();
            while(iterator.hasNext()) {
                Method method = (Method) iterator.next();
                String methodName = method.getName();
                
                String listenerName = null;
                if(methodName.endsWith("Listener")) {
                    listenerName = methodName.substring(0,
                            methodName.lastIndexOf("Listener"));
                    
                    if(methodName.startsWith("add")) {
                        listenerName = listenerName.substring(3);
                    } else if(methodName.startsWith("remove")) {
                        listenerName = listenerName.substring(6);
                    } else {
                        continue;
                    }
                    
                    if(result.get(listenerName) == null) {
                        Class[] parameterTypes = method.getParameterTypes();
                        
                        if(parameterTypes.length == 1) {
                            Class listenerType = parameterTypes[0];
                            
                            // full and short names of classes
                            String listenerTypeName = listenerType.getName();

                            // check if the listener name extracted from param name and
                            // listener name extracted from registration method are the same
                            String listenerNameFromParam =
                                listenerTypeName.substring(
                                        listenerTypeName.lastIndexOf(".") + 1);
                            
                            String listenerNameFromMethod = listenerName
                                    + "Listener";
                            if(!listenerNameFromParam.equals(
                                    listenerNameFromMethod)) {
                                continue;
                            }
                            
                            String eventTypeName = listenerTypeName.substring(0,
                                    listenerTypeName.lastIndexOf(".") + 1)
                                    + listenerName + "Event";
                            
                            // classes generated from classes names
                            Class eventType = null;
                            try {
                                eventType = Class.forName(eventTypeName, true,
                                        beanClass.getClassLoader());
                            } catch (ClassNotFoundException cnfe) {
                                System.out.println("Event type with name "
                                        + eventTypeName + " is not found.");
                            } finally {
                                if(eventType == null) {
                                    continue;
                                }
                            }
                            
                            Method[] methods = listenerType.getMethods();
                            Vector listenerMethodsVec = new Vector();
                            for(int i = 0; i < methods.length; ++i) {
                                Class[] listenerMethodParams =
                                    methods[i].getParameterTypes();
                                if(listenerMethodParams.length == 1
                                        && listenerMethodParams[0] == eventType) {
                                    listenerMethodsVec.add(methods[i]);
                                }
                            }
                            
                            Method[] listenerMethods =
                                    new Method[listenerMethodsVec.size()];
                            Iterator iter = listenerMethodsVec.iterator();
                            int idx2 = 0;
                            while(iter.hasNext()) {
                                listenerMethods[idx2] = (Method) iter.next();
                                idx2++;
                            }
                            
                            Method addListenerMethod = null;
                            String addListenerMethodName = "add" + listenerName
                                    + "Listener";
                            try {
                                addListenerMethod = beanClass.getMethod(
                                    addListenerMethodName,
                                    new Class[] { listenerType });
                            } catch (NoSuchMethodException nsme) {
                                // no adder found
                                continue;
                            }
                            
                            Method removeListenerMethod = null;
                            String removeListenerMethodName = "remove"
                                    + listenerName + "Listener";
                            try {
                                removeListenerMethod = beanClass.getMethod(
                                    removeListenerMethodName,
                                    new Class[] { listenerType });
                            } catch (NoSuchMethodException nsme) {
                                // no remover found
                                continue;
                            }
                            
                            Method getListenerMethod = null;
                            String getListenerMethodName = "get" + listenerName
                                    + "Listeners";
                            try {
                                getListenerMethod = beanClass.getMethod(
                                    getListenerMethodName, new Class[] {});
                            } catch (NoSuchMethodException nsme) {
                                // no action - getter is not a mandatory method in event set descriptor pattern
                            }

                            try {
                                listenerName = Introspector.decapitalize(
                                        listenerName);
                                EventSetDescriptor esd = new EventSetDescriptor(
                                        listenerName,
                                        listenerType,
                                        listenerMethods,
                                        addListenerMethod,
                                        removeListenerMethod,
                                        getListenerMethod);
                                result.put(listenerName, esd);
                            } catch (IntrospectionException ie) {
                                System.out.println(
                                        "Cannot generate event set descriptor "
                                        + "for name" + listenerName + ".");
                            }
                            
                        }
                    }
                }
            }
            
            String[] eventSetDescriptorNames =
                    new String[result.keySet().size()];
            Iterator i = result.keySet().iterator();
            int idx = 0;
            while(i.hasNext()) {
                eventSetDescriptorNames[idx++] =
                    ((EventSetDescriptor) result.get(
                            (String) i.next())).getName();
            }
            
            Arrays.sort(eventSetDescriptorNames);

            eventSetDescriptors =
                    new EventSetDescriptor[eventSetDescriptorNames.length];
            for(int j = 0; j < eventSetDescriptors.length; ++j) {
                eventSetDescriptors[j] = (EventSetDescriptor) result.get(
                        eventSetDescriptorNames[j]);
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
            
            if(Modifier.isPublic(modifiers) && !Modifier.isStatic(modifiers)) {
                Class[] parameterTypes = method.getParameterTypes();
                Class returnType = method.getReturnType();

                if(name.startsWith("get") && (parameterTypes.length == 0)
                        && (returnType != void.class) && (name.length() > 3)) {
                    result = MT_GETTER;
                } else if(name.startsWith("is") && (parameterTypes.length == 0)
                        && (returnType == boolean.class)
                        && (name.length() > 2)) {
                    result = MT_BOOLEAN_GETTER;
                } else if(name.startsWith("get") && (parameterTypes.length == 1)
                        && (returnType != void.class)
                        && (parameterTypes[0] == int.class)
                        && (name.length() > 3)) {
                    result = MT_INDEXED_GETTER;
                } else if(name.startsWith("set") && (parameterTypes.length == 1)
                        && (returnType == void.class) && (name.length() > 3)) {
                    result = MT_SETTER;
                } else if(name.startsWith("set") && (parameterTypes.length == 2)
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
        
        if ( methodName.startsWith("set") || methodName.startsWith("get") ) {
            result = methodName.substring(3);
            result = Introspector.decapitalize(result);
        } else if (methodName.startsWith("is")) {
            result = methodName.substring(2);
            result = Introspector.decapitalize(result);
        }
        
        return result;
    }

    private static ArrayList getPublicMethods(Class theClass) {
        ArrayList result = new ArrayList();

        Method[] beanClassMethods = theClass.getDeclaredMethods();
        for (int i = 0; i < beanClassMethods.length; ++i) {
            if(Modifier.isPublic(beanClassMethods[i].getModifiers())) {
                result.add(beanClassMethods[i]);
            }
        }
        
        return result;
    }
    
    private void addPropertyDescriptorsFromMethodList(
            HashMap hmPropertyDescriptors,
            ArrayList methods,
            boolean checkExisting) throws Exception
    {
        Iterator iterator = methods.iterator();
        while(iterator.hasNext()) {
            Method method = (Method) iterator.next();
            String methodName = method.getName();
            String propertyName = extractPropertyName(methodName);
            if ((!checkExisting) || (checkExisting && (hmPropertyDescriptors.get(propertyName) == null))) {
                PropertyDescriptor propertyDescriptor = null;
                try {
                    propertyDescriptor = new PropertyDescriptor(propertyName, beanClass);
                    hmPropertyDescriptors.put(propertyName, propertyDescriptor);
                } catch (IntrospectionException ie) {
                    System.out.println(ie.getClass() + ": " + ie.getMessage());
                }
            }
        }
    }
    
    private void addIndexedPropertyDescriptorsFromMethodList(
            HashMap hmPropertyDescriptors,
            ArrayList methods,
            boolean checkExisting) throws Exception
    {
        Iterator iterator = methods.iterator();
        while(iterator.hasNext()) {
            Method method = (Method) iterator.next();
            String methodName = method.getName();
            String propertyName = extractPropertyName(methodName);
            if ((!checkExisting) || (checkExisting
                    && (hmPropertyDescriptors.get(propertyName) == null))) {
                IndexedPropertyDescriptor indexedPropertyDescriptor = null;
                try {
                    indexedPropertyDescriptor = new IndexedPropertyDescriptor(
                            propertyName, beanClass);
                    hmPropertyDescriptors.put(propertyName,
                            indexedPropertyDescriptor);
                } catch (IntrospectionException ie) {
                    System.out.println(ie.getClass() + ": " + ie.getMessage());
                }
            }
        }
    }
    
    private Class beanClass = null;
    private PropertyDescriptor[] propertyDescriptors = null; 
    private MethodDescriptor[] methodDescriptors = null;
    private EventSetDescriptor[] eventSetDescriptors = null;
    private int defaultPropertyIndex = -1;
    private int defaultEventIndex = -1;
    
}

class BeanInfoWrapper implements BeanInfo {
    
    private BeanInfo info;
    private BeanInfoImpl impl;
    private BeanInfoWrapper parentBeanInfoWrapper;
    
    public BeanInfoWrapper(BeanInfo info, BeanInfoImpl impl) {
        this.info = info;
        this.impl = impl;
    }
    
    public PropertyDescriptor[] getPropertyDescriptors() {
        PropertyDescriptor[] result = null;
        PropertyDescriptor[] infoResult = null;
        
        if(info != null) {
            infoResult = info.getPropertyDescriptors();
            
            BeanInfo[] infos = info.getAdditionalBeanInfo();
            if(infos != null) {
                for(int i = 0; i < infos.length; ++i) {
                    BeanInfo additionalInfo = infos[i];
                    
                    if(infoResult == null) {
                        infoResult = additionalInfo.getPropertyDescriptors();
                    } else {
                        infoResult = concatArraysToOneArray(infoResult,
                                additionalInfo.getPropertyDescriptors());
                    }
                }
            }
        }
        
        if(info == null || infoResult == null) {
            PropertyDescriptor[] implResult = impl.getPropertyDescriptors();
            
            // merge with parent info
            if(parentBeanInfoWrapper != null) {
                PropertyDescriptor[] parentResult =
                        parentBeanInfoWrapper.getPropertyDescriptors();
                HashMap hm = concatArraysUniqueByName(implResult, parentResult);
                
                Collection values = hm.values();
                
                result = new PropertyDescriptor[values.size()];
                int idx = 0;
                Iterator iterator = values.iterator();
                while(iterator.hasNext()) {
                    result[idx++] = (PropertyDescriptor) iterator.next();
                }
                
                Arrays.sort(result, new Comparator() {
                    public int compare(Object o1, Object o2) {
                        PropertyDescriptor pd1 = (PropertyDescriptor) o1;
                        PropertyDescriptor pd2 = (PropertyDescriptor) o2;
                        return pd1.getName().compareTo(pd2.getName());
                    }
                    public boolean equals(Object o) {
                        return false;
                    }
                });
            } else {
                result = implResult;
            }
        } else {
            result = infoResult;
        }
        
        return result;
    }

    public MethodDescriptor[] getMethodDescriptors() {
        MethodDescriptor[] result = null;
        MethodDescriptor[] infoResult = null;
        
        if(info != null) {
            infoResult = info.getMethodDescriptors();
            
            BeanInfo[] infos = info.getAdditionalBeanInfo();
            if(infos != null) {
                for(int i = 0; i < infos.length; ++i) {
                    BeanInfo additionalInfo = infos[i];
                    
                    if(infoResult == null) {
                        infoResult = additionalInfo.getMethodDescriptors();
                    } else {
                        infoResult = concatArraysToOneArray(infoResult,
                                additionalInfo.getMethodDescriptors());
                    }
                }
            }
        }
        
        if(info == null || infoResult == null) {
            MethodDescriptor[] implResult = impl.getMethodDescriptors();
            
            // merge with parent info
            if(parentBeanInfoWrapper != null) {
                MethodDescriptor[] parentResult =
                        parentBeanInfoWrapper.getMethodDescriptors();
                result = concatArraysToOneArray(implResult, parentResult);
            } else {
                result = implResult;
            }
        } else {
            result = infoResult;
        }

        if(result != null) {
            Arrays.sort(result, new Comparator() {
                public int compare(Object o1, Object o2) {
                    MethodDescriptor md1 = (MethodDescriptor) o1;
                    MethodDescriptor md2 = (MethodDescriptor) o2;
                    return md1.getName().compareTo(md2.getName());
                }
                public boolean equals(Object o) {
                    return false;
                }
            });
        }
        
        return result;
    }

    public EventSetDescriptor[] getEventSetDescriptors() {
        EventSetDescriptor[] result = null;
        EventSetDescriptor[] infoResult = null;
        
        if(info != null) {
            infoResult = info.getEventSetDescriptors();
            
            BeanInfo[] infos = info.getAdditionalBeanInfo();
            if(infos != null) {
                for(int i = 0; i < infos.length; ++i) {
                    BeanInfo additionalInfo = infos[i];
                    
                    if(infoResult == null) {
                        infoResult = additionalInfo.getEventSetDescriptors();
                    } else {
                        infoResult = concatArraysToOneArray(infoResult,
                                additionalInfo.getEventSetDescriptors());
                    }
                }
            }
        }
        
        if(info == null || infoResult == null) {
            EventSetDescriptor[] implResult = impl.getEventSetDescriptors();
            
            // merge with parent info
            if(parentBeanInfoWrapper != null) {
                EventSetDescriptor[] parentResult =
                        parentBeanInfoWrapper.getEventSetDescriptors();
                HashMap hm = concatArraysUniqueByName(implResult, parentResult);
                
                Collection values = hm.values();
                
                result = new EventSetDescriptor[values.size()];
                int idx = 0;
                Iterator iterator = values.iterator();
                while(iterator.hasNext()) {
                    result[idx++] = (EventSetDescriptor) iterator.next();
                }
                
                Arrays.sort(result, new Comparator() {
                    public int compare(Object o1, Object o2) {
                        EventSetDescriptor esd1 = (EventSetDescriptor) o1;
                        EventSetDescriptor esd2 = (EventSetDescriptor) o2;
                        return esd1.getName().compareTo(esd2.getName());
                    }
                    public boolean equals(Object o) {
                        return false;
                    }
                });
            } else {
                result = implResult;
            }
        } else {
            result = infoResult;
        }
        
        return result;
    }

    public BeanInfo[] getAdditionalBeanInfo() {
        BeanInfo[] result = null;
        
        if(info != null) {
            result = info.getAdditionalBeanInfo();
        }
        
        return result;
    }

    public BeanDescriptor getBeanDescriptor() {
        BeanDescriptor result = null;
        
        if(info != null) {
            result = info.getBeanDescriptor();
        }
        
        if(info == null || result == null) {
            result = impl.getBeanDescriptor();
        }
        
        return result;
    }

    public Image getIcon(int iconKind) {
        Image result = null;
        
        if(info != null) {
            result = info.getIcon(iconKind);
        }
        
        return result;
    }

    public int getDefaultPropertyIndex() {
        int result = -1;
        
        if(info != null) {
            result = info.getDefaultPropertyIndex();
        }
        
        if(info == null || result == -1) {
            result = impl.getDefaultPropertyIndex();
        }
        
        return result;
    }

    public int getDefaultEventIndex() {
        int result = -1;
        
        if(info != null) {
            result = info.getDefaultEventIndex();
        }
        
        if(info == null || result == -1) {
            result = impl.getDefaultEventIndex();
        }
        
        return result;
    }
    
    void merge(BeanInfoWrapper parentBeanInfoWrapper) {
        this.parentBeanInfoWrapper = parentBeanInfoWrapper;
    }
    
    private static HashMap concatArraysUniqueByName(FeatureDescriptor[] childs,
            FeatureDescriptor[] parents) {
        HashMap result = new HashMap();
        
        if(childs != null) {
            for(int i = 0; i < childs.length; ++i) {
                result.put(childs[i].getName(), childs[i]);
            }
        }
        
        if(parents != null) {
            for(int j = 0; j < parents.length; ++j) {
                if(result.get(parents[j].getName()) == null) {
                    result.put(parents[j].getName(), parents[j]);
                }
            }
        }
        
        return result;
    }
    
    private static PropertyDescriptor[] concatArraysToOneArray(
            PropertyDescriptor[] childs, PropertyDescriptor[] parents) {
        if(childs != null || parents != null) {
            HashMap hm = concatArraysUniqueByName(childs, parents);
            PropertyDescriptor[] result = new PropertyDescriptor[hm.size()];
            
            Iterator iterator = hm.keySet().iterator();
            int idx = 0;
            
            while(iterator.hasNext()) {
                String name = (String) iterator.next();
                result[idx++] = (PropertyDescriptor) hm.get(name);
            }
            
            return result;
        } else {
            return null;
        }
    }
    
    private static MethodDescriptor[] concatArraysToOneArray(
            MethodDescriptor[] childs, MethodDescriptor[] parents) {
        MethodDescriptor[] result = null;
        
        if(childs != null || parents != null) {
            int resultLength = 0;
            
            if(childs != null) {
                resultLength = childs.length; 
            }
            
            if(parents != null) {
                resultLength += parents.length;
            }
            
            result = new MethodDescriptor[resultLength];

            if(childs != null) {
                for(int i = 0; i < childs.length; ++i) {
                    result[i] = childs[i];
                }
            }
            
            if(parents != null) {
                int shift = (childs == null) ? 0 : childs.length; 
                for(int i = 0; i < parents.length; ++i) {
                    result[shift + i] = parents[i];
                }
            }
            
        }
        
        return result;
    }
    
    private static EventSetDescriptor[] concatArraysToOneArray(
            EventSetDescriptor[] childs, EventSetDescriptor[] parents) {
        if(childs != null || parents != null) {
            HashMap hm = concatArraysUniqueByName(childs, parents);
            EventSetDescriptor[] result = new EventSetDescriptor[hm.size()];
            
            Iterator iterator = hm.keySet().iterator();
            int idx = 0;
            
            while(iterator.hasNext()) {
                String name = (String) iterator.next();
                result[idx++] = (EventSetDescriptor) hm.get(name);
            }
            
            return result;
        } else {
            return null;
        }
    }
}
