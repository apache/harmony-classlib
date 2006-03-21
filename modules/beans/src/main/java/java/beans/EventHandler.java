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
 * @version $Revision: 1.7.6.3 $
 */
package java.beans;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.StringTokenizer;

/**
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.7.6.3 $
 */

public class EventHandler implements InvocationHandler {
    
    private Object target;
    private String action;
    private String eventPropertyName;
    private String listenerMethodName;

    /**
     * @com.intel.drl.spec_ref
     */
    public EventHandler(Object target, String action, String eventPropertyName,
            String listenerMethodName) {
        this.target = target;
        this.action = action;
        this.eventPropertyName = eventPropertyName;
        this.listenerMethodName = listenerMethodName;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Object invoke(Object proxy, Method method, Object[] arguments) {
        // workaround if arguments are null - treat them as array with
        // single null element
        if(arguments == null) {
            arguments = new Object[] { null };
        }
        
        Class proxyClass = proxy.getClass();
        // if a proxy
        if(Proxy.isProxyClass(proxyClass)) {
            InvocationHandler handler = Proxy.getInvocationHandler(proxy);
            // if a valid object
            if((handler instanceof EventHandler) && isValidInvocation(method,
                    arguments)) {
                try {
                    Object[] args = getArgs(arguments); // extract value from event property name
                    Method m = getMethod(proxy, method, arguments, args); // extract method to be invoked on target
                    return m.invoke(target, args);
                } catch (Throwable t) {
                    System.out.println(t.getClass() + ": " + t.getMessage());
                    return null;
                }
            } else {
                //  if not a valid call
                return null;
            }
        } else {
            // if not a valid object
            return null;
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String getListenerMethodName() {
        return listenerMethodName; 
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String getEventPropertyName() {
        return eventPropertyName;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String getAction() {
        return action;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Object getTarget() {
        return target;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static Object create(
            Class listenerInterface,
            Object target,
            String action,
            String eventPropertyName,
            String listenerMethodName) {
        return Proxy.newProxyInstance(null, new Class[] { listenerInterface },
               new EventHandler(target, action, eventPropertyName,
                       listenerMethodName));
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static Object create(
            Class listenerInterface,
            Object target,
            String action,
            String eventPropertyName) {
        return Proxy.newProxyInstance(null, new Class[] { listenerInterface },
            new EventHandler(target, action, eventPropertyName, null));
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static Object create(
            Class listenerInterface,
            Object target,
            String action) {
        return Proxy.newProxyInstance(null, new Class[] { listenerInterface },
            new EventHandler(target, action, null, null));
    }
    
    private boolean isValidInvocation(Method method, Object[] arguments) {
        boolean result = false;
        
        if(listenerMethodName == null) { // all listener methods are valid 
            result = true;
        } else if (listenerMethodName.equals(method.getName())) { // method's name matches
            // no arguments in call are valid
            if((eventPropertyName == null) && ((arguments == null)
                    || (arguments.length == 0))) {
                result = true;
            // one-argument call is value
            } else if ((eventPropertyName != null) && (arguments != null)
                    && (arguments.length == 1)){
                result = true;
            } else {
                result = false;
            }
        } else {
            result = false;
        }
        
        return result;
    }
    
    private Object[] getArgs(Object[] arguments) throws Exception {
        if(eventPropertyName == null) {
            return new Object[] {};
        } else if ((arguments == null) || (arguments.length == 0)
                || (arguments[0] == null)) {
            return arguments;
        } else {
            Object arg = arguments[0];
            StringTokenizer st = new StringTokenizer(eventPropertyName, ".");
            
            while(st.hasMoreTokens()) {
                String propertyName = st.nextToken();
                PropertyDescriptor pd = findPropertyDescriptor(arg.getClass(),
                        propertyName);
                
                if(pd != null) {
                    Method getter = pd.getReadMethod();
                    
                    if(getter != null) {
                        arg = getter.invoke(arg, new Object[] {});
                    } else {
                        throw new IntrospectionException(
                            "no getter for property " + propertyName
                            + " found");
                    }
                } else {
                    Method method = findStaticGetter(arg.getClass(),
                            propertyName);
                    
                    if(method != null) {
                        arg = method.invoke(null, new Object[] {});
                    } else {
                        throw new IntrospectionException(
                            "cannot access property " + propertyName
                            + " getter");
                    }
                }
            }
            return new Object[] { arg };
        }
    }
    
    private Method getMethod(
            Object proxy,
            Method method,
            Object[] arguments,
            Object[] args) throws Exception {
        Method result = null;
        
        // filtering - examine if the 'method' could be applied to proxy
        
        boolean found = false;

        // can be invoke with any listener method
        if(listenerMethodName == null) {
            Class[] proxyInterfaces = proxy.getClass().getInterfaces();
            
            for(int i = 0; i < proxyInterfaces.length; ++i) {
                Method[] interfaceMethods = proxyInterfaces[i].getMethods();
                
                for(int k = 0; k < interfaceMethods.length; ++k) {
                    Method listenerMethod = interfaceMethods[k];
                    
                    if(equalNames(listenerMethod, method)
                            && canInvokeWithArguments(listenerMethod,
                                    arguments)) {
                        found = true;
                        break;
                    }
                }
                
                if(found) {
                    break;
                }
                
            }
            
        // can be invoked with a specified listener method    
        } else if(method.getName().equals(listenerMethodName)){
            found = true;
        }
        
        if(found == false) {
            return null;
        }
        
        // 'Method' can be applied to proxy - filtering succeeded
        try {
            result = findMethod(target.getClass(), args);
            if(result == null) {
                PropertyDescriptor pd = findPropertyDescriptor(
                        target.getClass(), action);
                if(pd != null) {
                    result = pd.getWriteMethod();
                    
                    if(result == null) {
                        throw new NoSuchMethodException(
                            "no setter for property " + action + " found");
                    }
                } else {
                    throw new Exception(
                            "Exception while finding property descriptor");
                }
            } else {
                return result;
            }
        } catch (IntrospectionException ie) {
            throw new Exception("Exception while finding property descriptor");
        }
        
        return result;
    }
    
    private PropertyDescriptor findPropertyDescriptor(
        Class theClass, String propertyName) throws IntrospectionException
    {
        PropertyDescriptor result = null;
        BeanInfo beanInfo = Introspector.getBeanInfo(theClass);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for(int i = 0; i < pds.length; ++i) {
            if(pds[i].getName().equals(propertyName)) {
                result = pds[i];
                break;
            }
        }
        return result;
    }
    
    private Method findStaticGetter(
        Class theClass, String propertyName) throws IntrospectionException
    {
        Method result = null;
        
        Method[] methods = theClass.getMethods();
        for(int i = 0; i < methods.length; ++i) {
            int modifiers = methods[i].getModifiers();
            
            if(Modifier.isStatic(modifiers) && Modifier.isPublic(modifiers)) {
                String methodName = methods[i].getName();
                String postfix = null;
                
                if(methodName.startsWith("get")) {
                    postfix = methodName.substring(3);
                } else if(methodName.startsWith("is")) {
                    postfix = methodName.substring(2);
                } else {
                    continue;
                }
                
                if(
                    (methods[i].getParameterTypes().length != 0) ||
                    (methods[i].getReturnType() == void.class)
                ) {
                    continue;
                }
                
                postfix = Introspector.decapitalize(postfix);
                if(postfix.equals(propertyName)) {
                    result = methods[i];
                    break;
                }
            }
        }

        return result;
    }
    
    private Method findMethod(Class type, Object[] args) {
        Method[] methods = type.getMethods();
        
        for(int i = 0; i < methods.length; ++i) {
            if(action.equals(methods[i].getName()) && canInvokeWithArguments(
                    methods[i], args)) {
                return methods[i];
            }
        }
        
        return null;
    }
    
    private static boolean isPrimitiveWrapper(Class wrapper, Class base) {
        return (base == boolean.class) && (wrapper == Boolean.class) ||
            (base == byte.class) && (wrapper == Byte.class) ||
            (base == char.class) && (wrapper == Character.class) ||
            (base == short.class) && (wrapper == Short.class) ||
            (base == int.class) && (wrapper == Integer.class) ||
            (base == long.class) && (wrapper == Long.class) ||
            (base == float.class) && (wrapper == Float.class) ||
            (base == double.class) && (wrapper == Double.class);
    }
    
    private static boolean canInvokeWithArguments(Method m, Object[] arguments) {
        Class[] parameterTypes = m.getParameterTypes();
        
        if(parameterTypes.length == arguments.length) {
            for(int i = 0; i < arguments.length; ++i) {
                Class argumentType = (arguments[i] == null) ? null
                        : arguments[i].getClass();
                if((argumentType == null) || isPrimitiveWrapper(argumentType,
                        parameterTypes[i])) {
                    // ... nothing to do - just not to break the cycle
                } else if(!argumentType.isAssignableFrom(parameterTypes[i])) {
                    return false;
                }
            }
        } else {
            return false;
        }
        
        return true;
        
    }
    
    private static boolean equalNames(Method m1, Method m2) {
        return m1.getName().equals(m2.getName());
    }
}
