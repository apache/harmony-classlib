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
 * @version $Revision: 1.9.6.3 $
 */
package java.beans;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TooManyListenersException;

public class EventSetDescriptor extends FeatureDescriptor {

    //XXX: never read
    //private String eventSetName = null;

    private Class<?> listenerType = null;

    private ArrayList<MethodDescriptor> listenerMethodDescriptors = new ArrayList<MethodDescriptor>();

    private Method getListenerMethod = null;

    private Method addListenerMethod = null;

    private Method removeListenerMethod = null;

    private boolean unicast = false;

    private boolean inDefaultEventSet = true;

    /**
     * @com.intel.drl.spec_ref
     */
    public EventSetDescriptor(Class<?> sourceClass, String eventSetName,
            Class<?> listenerType, String listenerMethodName)
            throws IntrospectionException {

        super();

        setName(eventSetName);
        setDisplayName(eventSetName);

        //XXX: never read
        //this.eventSetName = eventSetName;

        this.listenerType = listenerType;

        this.listenerMethodDescriptors.add(new MethodDescriptor(
                findMethodByName(listenerType, listenerMethodName)));

        this.addListenerMethod = findMethodByPrefix(sourceClass, "add", "",
                listenerType);
        this.removeListenerMethod = findMethodByPrefix(sourceClass, "remove",
                "", listenerType);

        if (addListenerMethod == null && removeListenerMethod == null) {
            throw new IntrospectionException(
                    "Add and remove methods are not available");
        }

        this.getListenerMethod = findMethodByPrefix(sourceClass, "get", "s",
                listenerType);

        this.unicast = isUnicastByDefault(addListenerMethod);

    }

    /**
     * @com.intel.drl.spec_ref
     */
    public EventSetDescriptor(Class<?> sourceClass, String eventSetName,
            Class<?> listenerType, String[] listenerMethodNames,
            String addListenerMethodName, String removeListenerMethodName)
            throws IntrospectionException {
        super();

        setName(eventSetName);
        setDisplayName(eventSetName);
        
        //XXX: never read
        //this.eventSetName = eventSetName;

        this.listenerType = listenerType;

        if (listenerMethodNames != null) {
            for (int i = 0; i < listenerMethodNames.length; ++i) {
                try {
                    listenerMethodDescriptors.add(new MethodDescriptor(
                            findMethodByName(listenerType,
                                    listenerMethodNames[i])));
                } catch (IntrospectionException ie) {
                    listenerMethodDescriptors.clear();
                    throw ie;
                }
            }
        }

        this.addListenerMethod = findMethodByName(listenerType,
                addListenerMethodName);
        this.removeListenerMethod = findMethodByName(listenerType,
                removeListenerMethodName);
        this.getListenerMethod = null;

        this.unicast = isUnicastByDefault(addListenerMethod);

    }

    /**
     * @com.intel.drl.spec_ref
     */
    public EventSetDescriptor(Class<?> sourceClass, String eventSetName,
            Class<?> listenerType, String[] listenerMethodNames,
            String addListenerMethodName, String removeListenerMethodName,
            String getListenerMethodName) throws IntrospectionException {
        super();

        setName(eventSetName);
        setDisplayName(eventSetName);
        
        //XXX: never read
        //this.eventSetName = eventSetName;

        this.listenerType = listenerType;

        if (listenerMethodNames != null) {
            for (int i = 0; i < listenerMethodNames.length; ++i) {
                try {
                    listenerMethodDescriptors.add(new MethodDescriptor(
                            findMethodByName(listenerType,
                                    listenerMethodNames[i])));
                } catch (IntrospectionException ie) {
                    listenerMethodDescriptors.clear();
                    throw ie;
                }
            }
        }

        this.addListenerMethod = findMethodByName(listenerType,
                addListenerMethodName);
        this.removeListenerMethod = findMethodByName(listenerType,
                removeListenerMethodName);
        this.getListenerMethod = findMethodByName(listenerType,
                getListenerMethodName);

        this.unicast = isUnicastByDefault(addListenerMethod);

    }

    /**
     * @com.intel.drl.spec_ref
     */
    public EventSetDescriptor(String eventSetName, Class<?> listenerType,
            Method[] listenerMethods, Method addListenerMethod,
            Method removeListenerMethod) throws IntrospectionException {
        super();

        setName(eventSetName);
        setDisplayName(eventSetName);

        //XXX: never read
        //this.eventSetName = eventSetName;

        this.listenerType = listenerType;

        if (listenerMethods != null) {
            for (int i = 0; i < listenerMethods.length; ++i) {
                if (checkMethod(listenerType, listenerMethods[i])) {
                    this.listenerMethodDescriptors.add(new MethodDescriptor(
                            listenerMethods[i]));
                }
            }
        }

        this.addListenerMethod = checkRegistrationMethod(listenerType,
                addListenerMethod);
        this.removeListenerMethod = checkRegistrationMethod(listenerType,
                removeListenerMethod);
        this.getListenerMethod = null;

        this.unicast = isUnicastByDefault(addListenerMethod);

    }

    /**
     * @com.intel.drl.spec_ref
     */
    public EventSetDescriptor(String eventSetName, Class<?> listenerType,
            Method[] listenerMethods, Method addListenerMethod,
            Method removeListenerMethod, Method getListenerMethod)
            throws IntrospectionException {

        super();

        setName(eventSetName);
        setDisplayName(eventSetName);
        
        //XXX: never read
        //this.eventSetName = eventSetName;

        this.listenerType = listenerType;

        if (listenerMethods != null) {
            for (int i = 0; i < listenerMethods.length; ++i) {
                if (checkMethod(listenerType, listenerMethods[i])) {
                    this.listenerMethodDescriptors.add(new MethodDescriptor(
                            listenerMethods[i]));
                }
            }
        }

        this.addListenerMethod = checkRegistrationMethod(listenerType,
                addListenerMethod);
        this.removeListenerMethod = checkRegistrationMethod(listenerType,
                removeListenerMethod);
        this.getListenerMethod = checkGetListenerMethod(listenerType,
                getListenerMethod);

        this.unicast = isUnicastByDefault(addListenerMethod);

    }

    /**
     * @com.intel.drl.spec_ref
     */
    public EventSetDescriptor(String eventSetName, Class<?> listenerType,
            MethodDescriptor[] listenerMethodDescriptors,
            Method addListenerMethod, Method removeListenerMethod)
            throws IntrospectionException {
        super();

        setName(eventSetName);
        setDisplayName(eventSetName);
        
        //XXX: never read
        //this.eventSetName = eventSetName;

        this.listenerType = listenerType;

        for (int i = 0; i < listenerMethodDescriptors.length; ++i) {
            Method listenerMethod = listenerMethodDescriptors[i].getMethod();

            if (checkMethod(listenerType, listenerMethod)) {
                this.listenerMethodDescriptors
                        .add(listenerMethodDescriptors[i]);
            }
        }

        this.addListenerMethod = checkRegistrationMethod(listenerType,
                addListenerMethod);
        this.removeListenerMethod = checkRegistrationMethod(listenerType,
                removeListenerMethod);
        this.getListenerMethod = null;

        this.unicast = isUnicastByDefault(addListenerMethod);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Method[] getListenerMethods() {
        Method[] result = new Method[listenerMethodDescriptors.size()];
        Iterator<MethodDescriptor> i = listenerMethodDescriptors.iterator();
        int idx = 0;

        while (i.hasNext()) {
            MethodDescriptor md = i.next();

            result[idx] = md.getMethod();
            idx++;
        }
        return result;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public MethodDescriptor[] getListenerMethodDescriptors() {
        MethodDescriptor[] result = new MethodDescriptor[listenerMethodDescriptors
                .size()];
        Iterator<MethodDescriptor> i = listenerMethodDescriptors.iterator();
        int idx = 0;

        while (i.hasNext()) {
            result[idx] = i.next();
            idx++;
        }
        return result;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Method getRemoveListenerMethod() {
        return removeListenerMethod;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Method getGetListenerMethod() {
        return getListenerMethod;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Method getAddListenerMethod() {
        return addListenerMethod;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Class<?> getListenerType() {
        return listenerType;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void setUnicast(boolean unicast) {
        this.unicast = unicast;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void setInDefaultEventSet(boolean inDefaultEventSet) {
        this.inDefaultEventSet = inDefaultEventSet;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean isUnicast() {
        return unicast;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean isInDefaultEventSet() {
        return inDefaultEventSet;
    }

    private Class<?> getEventType(Class<?> listenerType)
            throws ClassNotFoundException {
        String listenerTypeName = listenerType.getName();
        int idx = listenerTypeName.lastIndexOf("Listener");
        String eventTypeName = listenerTypeName.substring(0, idx) + "Event";

        return Class
                .forName(eventTypeName, true, listenerType.getClassLoader());
    }

    private boolean checkMethod(Class<?> listenerType, Method listenerMethod)
            throws IntrospectionException {
        if (listenerMethod != null
                && !listenerMethod.getDeclaringClass().isAssignableFrom(
                        listenerType)) {
            throw new IntrospectionException("No method \""
                    + listenerMethod.getName() + "\" for "
                    + listenerType.getName() + " found.");
        } else {
            return true;
        }
    }

    private Method findMethodByName(Class<?> listenerType,
            String listenerMethodName) throws IntrospectionException {
        try {
            return listenerType.getMethod(listenerMethodName,
                    new Class[] { getEventType(listenerType) });
        } catch (NoSuchMethodException nsme) {
            throw new IntrospectionException("No method \""
                    + listenerMethodName + "\" for " + listenerType.getName()
                    + " found.");
        } catch (ClassNotFoundException cnfe) {
            throw new IntrospectionException("Cannot acquire event type from "
                    + listenerType.getName() + " listener.");
        }
    }

    private Method findMethodByPrefix(Class<?> sourceClass, String prefix,
            String postfix, Class<?> listenerType) {

        // com.drl.beans.SmthListener
        String fullName = listenerType.getName();
        int idx = fullName.lastIndexOf(".");
        // prefix(e.g., add) + SmthListener
        String methodName = prefix + fullName.substring(idx + 1) + postfix;

        try {
            if (prefix.equals("get")) {
                return sourceClass.getMethod(methodName, new Class[] {});
            } else {
                return sourceClass.getMethod(methodName,
                        new Class[] { listenerType });
            }
        } catch (NoSuchMethodException nsme) {
            return null;
        }

    }

    private static boolean isUnicastByDefault(Method addMethod) {
        if (addMethod != null) {
            Class[] exceptionTypes = addMethod.getExceptionTypes();

            for (int i = 0; i < exceptionTypes.length; ++i) {
                if (exceptionTypes[i].equals(TooManyListenersException.class)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static Method checkRegistrationMethod(Class<?> listenerType,
            Method registrationMethod) throws IntrospectionException {
        if (registrationMethod == null) {
            return null;
        } else {
            Class<?> returnType = registrationMethod.getReturnType();
            Class[] parameterTypes;

            if (returnType != void.class) {
                throw new IntrospectionException(registrationMethod.getName()
                        + " does not return <void>");
            }

            parameterTypes = registrationMethod.getParameterTypes();
            if (parameterTypes == null || parameterTypes.length != 1) {
                throw new IntrospectionException(registrationMethod.getName()
                        + " should have a single input parameter");
            } else if (parameterTypes[0] != listenerType) {
                throw new IntrospectionException(
                        "Single parameter does not match to "
                                + listenerType.getName() + " class");
            } else {
                return registrationMethod;
            }
        }
    }

    private static Method checkGetListenerMethod(Class<?> listenerType,
            Method getListenerMethod) throws IntrospectionException {
        if (getListenerMethod == null) {
            return null;
        } else {
            Class[] parameterTypes = getListenerMethod.getParameterTypes();
            Class<?> returnType;

            if (parameterTypes.length != 0) {
                throw new IntrospectionException(
                        "No input params are allowed for getListenerMethod");
            }

            returnType = getListenerMethod.getReturnType();
            if (returnType.isArray()
                    && returnType.getComponentType() == listenerType) {
                return getListenerMethod;
            } else {
                throw new IntrospectionException(
                        "Return type of getListenerMethod is not an array "
                                + "of listeners");
            }
        }
    }
}
