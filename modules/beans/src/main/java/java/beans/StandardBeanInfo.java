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

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.EventListener;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TooManyListenersException;

import static java.beans.Introspector.decapitalize;

class StandardBeanInfo extends SimpleBeanInfo {
    

    // Prefixes for methods that set or get a Property
    private static final String PREFIX_IS = "is"; //$NON-NLS-1$

    private static final String PREFIX_GET = "get"; //$NON-NLS-1$

    private static final String PREFIX_SET = "set"; //$NON-NLS-1$

    // Prefix and suffix for Event related methods
    private static final String PREFIX_ADD = "add"; //$NON-NLS-1$

    private static final String PREFIX_REMOVE = "remove"; //$NON-NLS-1$

    private static final String SUFFIX_LISTEN = "Listener"; //$NON-NLS-1$

    private boolean explicitMethods = false;

    private boolean explicitProperties = false;

    private boolean explicitEvents = false;

    private BeanInfo explicitBeanInfo = null;

    private EventSetDescriptor[] events = new EventSetDescriptor[0];

    private MethodDescriptor[] methods = new MethodDescriptor[0];

    private PropertyDescriptor[] properties = new PropertyDescriptor[0];
    
    BeanInfo[] additionalBeanInfo = null;

    private Class beanClass;

    private int defaultEventIndex = -1;

    private int defaultPropertyIndex = -1;

    private static PropertyComparator comparator = new PropertyComparator();

    StandardBeanInfo(Class beanClass) {
        super();
        this.beanClass = beanClass;

    }
    
    StandardBeanInfo(Class beanClass,
            BeanInfo explicitBeanInfo) throws IntrospectionException {
        this(beanClass);
        /*--------------------------------------------------------------------------------------
         * There are 3 aspects of BeanInfo that must be supplied:
         * a) PropertyDescriptors
         * b) MethodDescriptors
         * c) EventSetDescriptors
         * Each of these may be optionally provided in the explicitBeanInfo object relating to
         * this bean.  Where the explicitBeanInfo provides one of these aspects, it is used
         * without question and no introspection of the beanClass is performed for that aspect.
         * There are also 3 optional items of BeanInfo that may be provided by the 
         * explicitBeanInfo object:
         * 1) BeanDescriptor
         * 2) DefaultEventIndex
         * 3) DefaultPropertyIndex
         * These aspects of the beanClass cannot be derived through introspection of the class.
         * If they are not provided by the explicitBeanInfo, then they must be left null in the 
         * returned BeanInfo, otherwise they will be copied from the explicitBeanInfo 
         --------------------------------------------------------------------------------------*/
        if (explicitBeanInfo != null){
            this.explicitBeanInfo = explicitBeanInfo;
            events = explicitBeanInfo.getEventSetDescriptors();
            methods = explicitBeanInfo.getMethodDescriptors();
            properties = explicitBeanInfo.getPropertyDescriptors();
            this.defaultEventIndex = explicitBeanInfo.getDefaultEventIndex();
            this.defaultPropertyIndex = explicitBeanInfo.getDefaultPropertyIndex();
            additionalBeanInfo = explicitBeanInfo.getAdditionalBeanInfo();
            
            if (events != null)
                explicitEvents = true;
            if (methods != null)
                explicitMethods = true;
            if (properties != null)
                explicitProperties = true;
        }

        if (!explicitMethods) {
            methods = introspectMethods(beanClass);
        }

        if (!explicitProperties) {
            properties = introspectProperties(beanClass);
        }

        if (!explicitEvents) {
            events = introspectEvents(beanClass);
        }
    }

    public BeanInfo[] getAdditionalBeanInfo() {
        return null;
    }

    public EventSetDescriptor[] getEventSetDescriptors() {
        return events;
    }

    public MethodDescriptor[] getMethodDescriptors() {
        return methods;
    }

    public PropertyDescriptor[] getPropertyDescriptors() {
        PropertyDescriptor[] sortedProperties = properties;

        if (sortedProperties != null) {
            Arrays.sort(sortedProperties, comparator);
        }
        return sortedProperties;
    }

    public BeanDescriptor getBeanDescriptor() {
        if (explicitBeanInfo != null) {
            BeanDescriptor beanDesc = explicitBeanInfo.getBeanDescriptor();
            if (beanDesc != null) {
                return beanDesc;
            }
        }
        return new BeanDescriptor(beanClass);
    }

    public int getDefaultEventIndex() {
        return this.defaultEventIndex;
    }

    public int getDefaultPropertyIndex() {
        return this.defaultPropertyIndex;
    }
    
    
    void mergeBeanInfo(BeanInfo superBeanInfo, boolean force) throws IntrospectionException{
        if(force){
            if (superBeanInfo.getPropertyDescriptors() != null) {
                PropertyDescriptor[] superDescs = superBeanInfo
                        .getPropertyDescriptors();
                if (getPropertyDescriptors() != null) {
                    if (explicitProperties == false)
                        properties = mergeProps(superDescs);
                } else {
                    if (explicitProperties == false)
                        properties = superDescs;
                }
            }

            //merge MethodDescriptors
            if (superBeanInfo.getMethodDescriptors() != null) {
                if (getMethodDescriptors() != null) {
                    if (explicitMethods == false)
                    methods = mergeMethods(superBeanInfo
                                                .getMethodDescriptors(), getMethodDescriptors());
                } else {
                    if (explicitMethods == false)
                        methods = superBeanInfo.getMethodDescriptors();
                }
            }

            //merge EventSetDescriptors
            if (superBeanInfo.getEventSetDescriptors() != null) {
                if (getEventSetDescriptors() != null) {
                    if (explicitEvents == false)
                    events = mergeEvents(superBeanInfo
                                                .getEventSetDescriptors());
                } else {
                    if (explicitEvents == false)
                        events = superBeanInfo.getEventSetDescriptors();
                }
            }           
        }
        mergeBeanInfo(superBeanInfo);
    }

    void mergeBeanInfo(BeanInfo superBeanInfo) throws IntrospectionException{
        //FIXME: the merge principle seems very different with RI's behavior
            //merge PropertyDescriptors
            if ((!explicitProperties)
                    && (superBeanInfo.getPropertyDescriptors() != null)) {
                PropertyDescriptor[] superDescs = superBeanInfo
                        .getPropertyDescriptors();
                if (getPropertyDescriptors() != null) {
                    properties = mergeProps(superDescs);
                } else {
                    properties = superDescs;
                }
            }

            //merge MethodDescriptors
            if ((!explicitMethods)
                    && (superBeanInfo.getMethodDescriptors() != null)) {
                if (getMethodDescriptors() != null) {
                    methods = mergeMethods(superBeanInfo
                                                .getMethodDescriptors(), getMethodDescriptors());
                } else {
                        methods = superBeanInfo.getMethodDescriptors();
                }
            }

            //merge EventSetDescriptors
            if ((!explicitEvents)
                    && (superBeanInfo.getEventSetDescriptors() != null)) {
                if (getEventSetDescriptors() != null) {
                    events = mergeEvents(superBeanInfo
                                                .getEventSetDescriptors());
                } else {
                    events = superBeanInfo.getEventSetDescriptors();
                }
            }

            //merge defaultPropertyIndex, and defaultEventIndex
            if ((getDefaultEventIndex() == -1)
                    && (superBeanInfo.getDefaultEventIndex() != -1)) {
                defaultEventIndex = superBeanInfo.getDefaultEventIndex();
            }

            if ((getDefaultPropertyIndex() == -1)
                    && (superBeanInfo.getDefaultPropertyIndex() != -1)) {
                defaultPropertyIndex = superBeanInfo.getDefaultPropertyIndex();
            }

    }        

    /*
     * merge the PropertyDescriptor with superclass
     */
    private PropertyDescriptor[] mergeProps(
            PropertyDescriptor[] superDescs)
            throws IntrospectionException {
        HashMap<String, PropertyDescriptor> subMap = internalAsMap(getPropertyDescriptors());

        for (int i = 0; i < superDescs.length; i++) {
            PropertyDescriptor superDesc = superDescs[i];
            String propertyName = superDesc.getName();
            if (!subMap.containsKey(propertyName)) {
                subMap.put(propertyName, superDesc);
                continue;
            }

            Object value = subMap.get(propertyName);
            //if sub and super are both PropertyDescriptor
            Method subGet = ((PropertyDescriptor) value).getReadMethod();
            Method subSet = ((PropertyDescriptor) value).getWriteMethod();
            Method superGet = superDesc.getReadMethod();
            Method superSet = superDesc.getWriteMethod();

            Class superType = superDesc.getPropertyType();
            Class superIndexedType = null;
            Class subType = ((PropertyDescriptor) value).getPropertyType();
            Class subIndexedType = null;

            if (value instanceof IndexedPropertyDescriptor) {
                subIndexedType = ((IndexedPropertyDescriptor) value)
                        .getIndexedPropertyType();
            }
            if (superDesc instanceof IndexedPropertyDescriptor) {
                superIndexedType = ((IndexedPropertyDescriptor) superDesc)
                        .getIndexedPropertyType();
            }

            //if superDesc is PropertyDescriptor
            if (superIndexedType == null) {
                PropertyDescriptor subDesc = (PropertyDescriptor) value;
                //Sub is PropertyDescriptor
                if (subIndexedType == null) {
                    //Same property type
                    if (subType.getName().equals(superType.getName())) {
                        if ((subGet == null) && (superGet != null)) {
                            subDesc.setReadMethod(superGet);
                        }
                        if ((subSet == null) && (superSet != null)) {
                            subDesc.setWriteMethod(superSet);
                        }
                    } else { //Different type: type = getMethod
                        if ((subGet == null) && (superGet != null)) {
                            subDesc.setWriteMethod(null);
                            subDesc.setReadMethod(superGet);
                        }
                    }
                } else { //Sub is IndexedPropertyDescriptor
                    if ((superType.isArray())
                            && (superType.getComponentType().getName()
                                    .equals(subIndexedType.getName()))) {
                        //same type
                        if ((subGet == null) && (superGet != null)) {
                            subDesc.setReadMethod(superGet);
                        }
                        if ((subSet == null) && (superSet != null)) {
                            subDesc.setWriteMethod(superSet);
                        }
                    } //different type do nothing
                }
                subMap.put(propertyName, subDesc);
            } else { //Super is IndexedPropertyDescriptor
                if (subIndexedType == null) { //Sub is PropertyDescriptor
                    if (subType.isArray()
                            && (subType.getComponentType().getName()
                                    .equals(superIndexedType.getName()))) {
                        // Same type
                        if (subGet != null) {
                            superDesc.setReadMethod(subGet);
                        }
                        if (subSet != null) {
                            superDesc.setWriteMethod(subSet);
                        }
                        subMap.put(propertyName, superDesc);
                    } else { //Different type do nothing
                        subMap.put(propertyName, (PropertyDescriptor)value);
                    }

                } else if (subIndexedType.getName().equals(
                        superIndexedType.getName())) {
                    //Sub is IndexedPropertyDescriptor and Same type
                    IndexedPropertyDescriptor subDesc = (IndexedPropertyDescriptor) value;
                    if ((subGet == null) && (superGet != null)) {
                        subDesc.setReadMethod(superGet);
                    }
                    if ((subSet == null) && (superSet != null)) {
                        subDesc.setWriteMethod(superSet);
                    }
                    IndexedPropertyDescriptor superIndexedDesc = (IndexedPropertyDescriptor) superDesc;

                    if ((subDesc.getIndexedReadMethod() == null)
                            && (superIndexedDesc.getIndexedReadMethod() != null)) {
                        subDesc.setIndexedReadMethod(superIndexedDesc
                                .getIndexedReadMethod());
                    }

                    if ((subDesc.getIndexedWriteMethod() == null)
                            && (superIndexedDesc.getIndexedWriteMethod() != null)) {
                        subDesc.setIndexedWriteMethod(superIndexedDesc
                                .getIndexedWriteMethod());
                    }
                    
                    subMap.put(propertyName, subDesc);
                } // Different indexed type, do nothing
            }
            mergeAttributes((PropertyDescriptor)value, superDesc);
        }

        PropertyDescriptor[] theDescs = new PropertyDescriptor[subMap.size()];
        subMap.values().toArray(theDescs);

        return theDescs;
    }

    private static void mergeAttributes(PropertyDescriptor subDesc, PropertyDescriptor superDesc) {
        subDesc.hidden |= superDesc.hidden;
        subDesc.expert |= superDesc.expert;
        subDesc.preferred |= superDesc.preferred;
        subDesc.name = superDesc.name;
        if (superDesc.shortDescription != null) {
            subDesc.shortDescription = superDesc.shortDescription;
        }
        if (superDesc.displayName != null) {
            subDesc.displayName = superDesc.displayName;
        }
    }

    /*
     * merge the MethodDescriptor
     */
    private static MethodDescriptor[] mergeMethods(
            MethodDescriptor[] superDescs, MethodDescriptor[] subDescs) {
        HashMap<String, MethodDescriptor> subMap = internalAsMap(subDescs);

        for (int i = 0; i < superDescs.length; i++) {
            MethodDescriptor superDesc = superDescs[i];
            String methodName = getQualifiedName(superDesc.getMethod());
            if (subMap.containsKey(methodName)) {
                continue;
            }
            subMap.put(methodName, superDesc);
        }

        MethodDescriptor[] theMethods = new MethodDescriptor[subMap.size()];
        subMap.values().toArray(theMethods);
        return theMethods;
    }
    
    private EventSetDescriptor[] mergeEvents(
            EventSetDescriptor[] otherEvents) {
        HashMap<String, EventSetDescriptor> subMap = internalAsMap(this.getEventSetDescriptors());

        for (int i = 0; i < otherEvents.length; i++) {
            String eventName = otherEvents[i].getName();
            if (subMap.containsKey(eventName)) {
                continue;
            }
            subMap.put(eventName, otherEvents[i]);
        }

        EventSetDescriptor[] theMethods = new EventSetDescriptor[subMap.size()];
        subMap.values().toArray(theMethods);
        return theMethods;
    }

    private static HashMap<String, PropertyDescriptor> internalAsMap(PropertyDescriptor[] propertyDescs) {
        HashMap<String, PropertyDescriptor> map = new HashMap<String, PropertyDescriptor>();
        for (int i = 0; i < propertyDescs.length; i++) {
            map.put(propertyDescs[i].getName(), propertyDescs[i]);
        }
        return map;
    }

    private static HashMap<String, MethodDescriptor> internalAsMap(MethodDescriptor[] theDescs) {
        HashMap<String, MethodDescriptor> map = new HashMap<String, MethodDescriptor>();
        for (int i = 0; i < theDescs.length; i++) {
            String qualifiedName = getQualifiedName(theDescs[i].getMethod());
            map.put(qualifiedName, theDescs[i]);
        }
        return map;
    }

    private static HashMap<String, EventSetDescriptor> internalAsMap(EventSetDescriptor[] theDescs) {
        HashMap<String, EventSetDescriptor> map = new HashMap<String, EventSetDescriptor>();
        for (int i = 0; i < theDescs.length; i++) {
            map.put(theDescs[i].getName(), theDescs[i]);
        }
        return map;
    }
    
    
    private static String getQualifiedName(Method method) {
        String qualifiedName = method.getName();
        Class[] paramTypes = method.getParameterTypes();
        if (paramTypes != null) {
            for (int i = 0; i < paramTypes.length; i++) {
                qualifiedName += "_" + paramTypes[i].getName(); //$NON-NLS-1$
            }
        }
        return qualifiedName;
    }
    
    /**
     * Introspects the supplied class and returns a list of the public methods
     * of the class
     * 
     * @param beanClass -
     *            the class
     * @return An array of MethodDescriptors with the public methods. null if
     *         there are no public methods
     */
    private static MethodDescriptor[] introspectMethods(Class beanClass) {

        MethodDescriptor[] theMethods = null;

        if (beanClass == null)
            return null;

        // Get the list of methods belonging to this class
        Method[] basicMethods = beanClass.getDeclaredMethods();

        if (basicMethods == null || basicMethods.length == 0)
            return null;

        ArrayList<MethodDescriptor> methodList = new ArrayList<MethodDescriptor>(basicMethods.length);

        // Loop over the methods found, looking for public methods
        for (int i = 0; i < basicMethods.length; i++) {
            int modifiers = basicMethods[i].getModifiers();
            if (Modifier.isPublic(modifiers)) {
                // Allocate a MethodDescriptor for this method
                MethodDescriptor theDescriptor = new MethodDescriptor(
                        basicMethods[i]);
                methodList.add(theDescriptor);
            }
        }

        // Get the list of public methods into the returned array
        int methodCount = methodList.size();
        if (methodCount > 0) {
            theMethods = new MethodDescriptor[methodCount];
            theMethods = (MethodDescriptor[]) methodList.toArray(theMethods);
        }

        return theMethods;
    }

    /**
     * Introspects the supplied class and returns a list of the Properties of
     * the class
     * 
     * @param beanClass -
     *            the Class
     * @return The list of Properties as an array of PropertyDescriptors
     * @throws IntrospectionException
     */
    private static PropertyDescriptor[] introspectProperties(Class beanClass)
            throws IntrospectionException {
        if (beanClass == null)
            return null;

        // Get descriptors for the public methods
        MethodDescriptor[] theMethods = introspectMethods(beanClass);

        if (theMethods == null)
            return null;

        HashMap<String, HashMap> propertyTable = new HashMap<String, HashMap>(theMethods.length);

        // Search for methods that either get or set a Property
        for (int i = 0; i < theMethods.length; i++) {
            introspectGet(theMethods[i].getMethod(), propertyTable);
            introspectSet(theMethods[i].getMethod(), propertyTable);
        }

        // Put the properties found into the PropertyDescriptor array
        ArrayList<PropertyDescriptor> propertyList = new ArrayList<PropertyDescriptor>();

        Iterator keys = propertyTable.keySet().iterator();
        while (keys.hasNext()) {
            String propertyName = (String) keys.next();
            HashMap table = propertyTable.get(propertyName);
            if (table == null) {
                continue;
            }
            String normalTag = (String) table.get("normal"); //$NON-NLS-1$
            String indexedTag = (String) table.get("indexed"); //$NON-NLS-1$

            if ((normalTag == null) && (indexedTag == null)) {
                continue;
            }

            Method get = (Method) table.get("normalget"); //$NON-NLS-1$
            Method set = (Method) table.get("normalset"); //$NON-NLS-1$
            Method indexedGet = (Method) table.get("indexedget"); //$NON-NLS-1$
            Method indexedSet = (Method) table.get("indexedset"); //$NON-NLS-1$
            
            PropertyDescriptor propertyDesc = null;
            if (indexedTag == null) {
                propertyDesc = new PropertyDescriptor(propertyName, get, set);
            } else {
                try {
                    propertyDesc = new IndexedPropertyDescriptor(propertyName,
                            get, set, indexedGet, indexedSet);
                } catch (IntrospectionException e) {
                    // If the getter and the indexGetter is not compatible, try
                    // getter/setter is null;
                    propertyDesc = new IndexedPropertyDescriptor(propertyName,
                            null, null, indexedGet, indexedSet);
                }
            }
            //RI set propretyDescriptor as bound. FIXME
            propertyDesc.setBound(false);
            propertyList.add(propertyDesc);
        }

        PropertyDescriptor[] theProperties = new PropertyDescriptor[propertyList
                .size()];
        propertyList.toArray(theProperties);
        return theProperties;
    }

    private static void introspectGet(Method theMethod, HashMap<String, HashMap> propertyTable) {
        String methodName = theMethod.getName();
        if (methodName == null) {
            return;
        }

        int prefixLength = 0;
        if (methodName.startsWith(PREFIX_GET)) {
            prefixLength = PREFIX_GET.length();
        }

        if (methodName.startsWith(PREFIX_IS)) {
            prefixLength = PREFIX_IS.length();
        }

        if (prefixLength == 0) {
            return;
        }

        String propertyName = decapitalize(methodName.substring(prefixLength));
        //validate property name
        if (!isValidProperty(propertyName)) {
            return;
        }

        Class propertyType = theMethod.getReturnType();

        //check return type getMethod
        if (propertyType.getName().equals(Void.TYPE.getName())) {
            return;
        }

        // isXXX return boolean
        if (prefixLength == 2) {
            if (!propertyType.getName().equals(Boolean.TYPE.getName())) {
                return;
            }
        }

        //indexed get method
        Class[] paramTypes = theMethod.getParameterTypes();

        if (paramTypes.length > 1) {
            return;
        }

        String tag = "normal"; //$NON-NLS-1$

        if (paramTypes.length == 1) {
            if (paramTypes[0].getName().equals(Integer.TYPE.getName())) {
                tag = "indexed"; //$NON-NLS-1$
            } else {
                return;
            }

        }

        HashMap table = propertyTable.get(propertyName);
        if (table == null) {
            table = new HashMap();
        }

        //the "get" propertyType is conflict with "set" propertyType
        Class oldPropertyType = (Class) table.get(tag + "PropertyType"); //$NON-NLS-1$
        if ((oldPropertyType != null)
                && (!oldPropertyType.getName().equals(propertyType.getName()))) {
            table.put(tag, "invalid"); //$NON-NLS-1$
            table.put(tag + "get", theMethod); //$NON-NLS-1$
            table.put(tag + "PropertyType", propertyType); //$NON-NLS-1$
            table.remove(tag + "set"); //$NON-NLS-1$
            return;
        }

        table.put(tag, "valid"); //$NON-NLS-1$
        table.put(tag + "get", theMethod); //$NON-NLS-1$
        table.put(tag + "PropertyType", propertyType); //$NON-NLS-1$

        propertyTable.put(propertyName, table);
    }

    private static void introspectSet(Method theMethod, HashMap<String, HashMap> propertyTable) {
        String methodName = theMethod.getName();
        if (methodName == null) {
            return;
        }

        int prefixLength = 0;
        if (methodName.startsWith(PREFIX_SET)) {
            prefixLength = PREFIX_GET.length();
        }

        if (prefixLength == 0) {
            return;
        }

        String propertyName = decapitalize(methodName.substring(prefixLength));

        //validate property name
        if (!isValidProperty(propertyName)) {
            return;
        }

        Class returnType = theMethod.getReturnType();

        if (!returnType.getName().equals(Void.TYPE.getName())) {
            return;
        }

        //indexed get method
        Class[] paramTypes = theMethod.getParameterTypes();

        if ((paramTypes.length == 0) || (paramTypes.length > 2)) {
            return;
        }

        String tag = "normal"; //$NON-NLS-1$

        Class propertyType = paramTypes[0];

        if (paramTypes.length == 2) {
            if (paramTypes[0].getName().equals(Integer.TYPE.getName())) {
                tag = "indexed"; //$NON-NLS-1$
                propertyType = paramTypes[1];
            } else {
                return;
            }
        }

        HashMap table = propertyTable.get(propertyName);
        if (table == null) {
            table = new HashMap();
        }

        Class oldPropertyType = (Class) table.get(tag + "PropertyType"); //$NON-NLS-1$
        if ((oldPropertyType != null)
                && (!oldPropertyType.getName().equals(propertyType.getName()))) {
            table.put(tag, "invalid"); //$NON-NLS-1$
            return;
        }

        table.put(tag, "valid"); //$NON-NLS-1$
        table.put(tag + "set", theMethod); //$NON-NLS-1$
        table.put(tag + "PropertyType", propertyType); //$NON-NLS-1$

        propertyTable.put(propertyName, table);
    }

    /**
     * Introspects the supplied Bean class and returns a list of the Events of
     * the class
     * 
     * @param beanClass
     * @return the events
     * @throws IntrospectionException
     */
    private static EventSetDescriptor[] introspectEvents(Class beanClass)
            throws IntrospectionException {
        if (beanClass == null)
            return null;

        // Get descriptors for the public methods
        MethodDescriptor[] theMethods = introspectMethods(beanClass);

        if (theMethods == null)
            return null;

        HashMap<String, HashMap> eventTable = new HashMap<String, HashMap>(theMethods.length);

        // Search for methods that add an Event Listener
        for (int i = 0; i < theMethods.length; i++) {
            introspectListenerMethods(PREFIX_ADD, theMethods[i].getMethod(),
                    eventTable);
            introspectListenerMethods(PREFIX_REMOVE, theMethods[i].getMethod(),
                    eventTable);
            introspectGetListenerMethods(theMethods[i].getMethod(), eventTable);

        }

        ArrayList<EventSetDescriptor> eventList = new ArrayList<EventSetDescriptor>();
        Iterator keys = eventTable.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            HashMap table = eventTable.get(key);
            Method add = (Method) table.get(PREFIX_ADD);
            Method remove = (Method) table.get(PREFIX_REMOVE);

            if ((add == null) || (remove == null)) {
                continue;
            }

            Method get = (Method) table.get(PREFIX_GET);
            Class listenerType = (Class) table.get("listenerType"); //$NON-NLS-1$
            Method[] listenerMethods = (Method[]) table.get("listenerMethods"); //$NON-NLS-1$
            EventSetDescriptor eventSetDescriptor = new EventSetDescriptor(
                    decapitalize(key), listenerType, listenerMethods, add,
                    remove, get);

            eventSetDescriptor.setUnicast(table.get("isUnicast") != null); //$NON-NLS-1$
            eventList.add(eventSetDescriptor);
        }

        EventSetDescriptor[] theEvents = new EventSetDescriptor[eventList
                .size()];
        eventList.toArray(theEvents);

        return theEvents;
    }

    /*
     * find the add, remove listener method
     */
    private static void introspectListenerMethods(String type,
            Method theMethod, HashMap methodsTable) {
        String methodName = theMethod.getName();
        if (methodName == null) {
            return;
        }

        if (!((methodName.startsWith(type)) && (methodName
                .endsWith(SUFFIX_LISTEN)))) {
            return;
        }

        String listenerName = methodName.substring(type.length());
        String eventName = listenerName.substring(0, listenerName
                .lastIndexOf(SUFFIX_LISTEN));
        if ((eventName == null) || (eventName.length() == 0)) {
            return;
        }

        Class[] paramTypes = theMethod.getParameterTypes();
        if ((paramTypes == null) || (paramTypes.length != 1)) {
            return;
        }

        Class listenerType = paramTypes[0];

        if (!EventListener.class.isAssignableFrom(listenerType)) {
            return;
        }

        if (!listenerType.getName().endsWith(listenerName)) {
            return;
        }

        HashMap table = (HashMap) methodsTable.get(eventName);
        if (table == null) {
            table = new HashMap();
        }
        //put listener type
        if (table.get("listenerType") == null) { //$NON-NLS-1$
            table.put("listenerType", listenerType); //$NON-NLS-1$
            table.put("listenerMethods", //$NON-NLS-1$
                    introspectListenerMethods(listenerType));
        }
        //put add / remove
        table.put(type, theMethod);

        //determine isUnicast()
        if (type.equals(PREFIX_ADD)) {
            Class[] exceptionTypes = theMethod.getExceptionTypes();
            if (exceptionTypes != null) {
                for (int i = 0; i < exceptionTypes.length; i++) {
                    if (exceptionTypes[i].getName().equals(
                            TooManyListenersException.class.getName())) {
                        table.put("isUnicast", "true"); //$NON-NLS-1$//$NON-NLS-2$
                        break;
                    }
                }
            }
        }

        methodsTable.put(eventName, table);
    }

    private static Method[] introspectListenerMethods(Class listenerType) {
        Method[] methods = listenerType.getDeclaredMethods();
        ArrayList<Method> list = new ArrayList<Method>();
        for (int i = 0; i < methods.length; i++) {
            Class[] paramTypes = methods[i].getParameterTypes();
            if (paramTypes.length != 1) {
                continue;
            }

            if (EventObject.class.isAssignableFrom(paramTypes[0])) {
                list.add(methods[i]);
            }
        }
        Method[] matchedMethods = new Method[list.size()];
        list.toArray(matchedMethods);
        return matchedMethods;
    }

    private static void introspectGetListenerMethods(Method theMethod,
            HashMap methodsTable) {
        String type = PREFIX_GET;

        String methodName = theMethod.getName();
        if (methodName == null) {
            return;
        }

        if (!((methodName.startsWith(type)) && (methodName
                .endsWith(SUFFIX_LISTEN + "s")))) { //$NON-NLS-1$
            return;
        }

        String listenerName = methodName.substring(type.length(), methodName
                .length() - 1);
        String eventName = listenerName.substring(0, listenerName
                .lastIndexOf(SUFFIX_LISTEN));
        if ((eventName == null) || (eventName.length() == 0)) {
            return;
        }

        Class[] paramTypes = theMethod.getParameterTypes();
        if ((paramTypes == null) || (paramTypes.length != 0)) {
            return;
        }

        Class returnType = theMethod.getReturnType();
        if ((returnType.getComponentType() == null)
                || (!returnType.getComponentType().getName().endsWith(
                        listenerName))) {
            return;
        }

        HashMap table = (HashMap) methodsTable.get(eventName);
        if (table == null) {
            table = new HashMap();
        }
        //put add / remove
        table.put(type, theMethod);
        methodsTable.put(eventName, table);
    }
    

    private static boolean isValidProperty(String propertyName) {
        return (propertyName != null) && (propertyName.length() != 0);
    }
    
    private static class PropertyComparator implements Comparator {

        public int compare(Object object1, Object object2) {
            PropertyDescriptor theDesc = (PropertyDescriptor) object1;
            PropertyDescriptor otherDesc = (PropertyDescriptor) object2;

            return theDesc.getName().compareTo(otherDesc.getName());
        }

    }

}

