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
 * @version $Revision: 1.18.6.3 $
 */
package java.beans;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.StringTokenizer;

/**
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.18.6.3 $
 */

public class Statement {
    
    private Object target;
    private String methodName;
    private Object[] arguments;

    /**
     * @com.intel.drl.spec_ref
     */
    public Statement(Object target, String methodName, Object[] arguments) {
        this.target = target;
        this.methodName = methodName;
        this.arguments = arguments;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String toString() {
        String targetVar = convertClassName(target.getClass());
        
        String argumentsVar = "";
        if(arguments.length > 0) {
            argumentsVar = convertClassName(arguments[0].getClass());
        }
        for(int i = 1; i < arguments.length; ++i) {
            argumentsVar = argumentsVar + "," + convertClassName(
                    arguments[0].getClass());
        }
        return targetVar + "." + methodName + "(" + argumentsVar +  ")";
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Object[] getArguments() {
        return arguments;
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
    public void execute() throws Exception {
        invokeMethod();
    }
    
    Object invokeMethod() throws Exception {
        Object result = null;
        
        try {
            if(target.getClass().isArray()) {
                Method method = findArrayMethod();
                Object[] ama = getArrayMethodArguments();
                result = method.invoke(null, ama);
            } else if(methodName.equals("new")) {
                if(target instanceof Class) {
                    Class type = (Class) target;
                    if(type.isArray()) {
                        Class componentType = type.getComponentType();
                        int length = ((Integer) arguments[0]).intValue();
                        result = Array.newInstance(componentType, length);
                    } else {
                        Constructor constructor = findConstructor();
                        result = constructor.newInstance(arguments);
                    }
                } else {
                    Constructor constructor = findConstructor();
                    result = constructor.newInstance(arguments);
                }
            } else if(target instanceof Class) {
                Method method = findStaticMethod();
                result = method.invoke(null, arguments);
            } else {
                final Method method = findMethod();
                
                AccessController.doPrivileged(new PrivilegedAction<Object>() {
                    public Object run() {
                        method.setAccessible(true);
                        return null;
                    }
                });
                result = method.invoke(target, arguments);
            }
        } catch (InvocationTargetException ite) {
            Throwable t = ite.getCause();
            throw (t != null) && (t instanceof Exception) ? (Exception) t : ite;
        }
        return result;
    }
    
    private Method findArrayMethod() throws NoSuchMethodException {
        if(!methodName.equals("set") && !methodName.equals("get")) {
            throw new NoSuchMethodException("Unknown method name for array");
        } else if(methodName.equals("get") && (arguments.length != 1) ) {
            throw new NoSuchMethodException(
                    "Illegal number of arguments in array getter");
        } else if(methodName.equals("set") && (arguments.length != 2) ) {
            throw new NoSuchMethodException(
                    "Illegal number of arguments in array setter");
        } else if(arguments[0].getClass() != Integer.class) {
            throw new NoSuchMethodException(
                    "First parameter in array getter(setter) is not of "
                    + "Integer type");
        }
        
        Class[] argClasses = methodName.equals("get") ?
            new Class[] { Object.class, int.class } :
            new Class[] { Object.class, int.class, Object.class };

        return Array.class.getMethod(methodName, argClasses );
    }
    
    private Object[] getArrayMethodArguments() {
        Object[] args = new Object[arguments.length + 1];
        args[0] = target;
        for(int i = 0; i < arguments.length; ++i) {
            args[i + 1] = arguments[i];
        }
        return args;
    }
    
    private Constructor findConstructor() throws NoSuchMethodException {
        Class[] argClasses = getClasses();
        Class targetClass = (Class) target;
        
        Constructor result = null;
        
           Constructor[] constructors = targetClass.getConstructors();
           for(int i = 0; i < constructors.length; ++i) {
               Constructor constructor = constructors[i];
               Class[] parameterTypes = constructor.getParameterTypes();
               
               if(parameterTypes.length == argClasses.length) {
                   boolean found = true;
                   
                   for(int j = 0; j < parameterTypes.length; ++j) {
                       boolean argIsNull = argClasses[j] == null;
                       boolean argIsPrimitiveWrapper = isPrimitiveWrapper(
                               argClasses[j], parameterTypes[j]);
                       boolean paramIsPrimitive =
                               parameterTypes[j].isPrimitive();
                       boolean paramIsAssignable = argIsNull ? false
                               : parameterTypes[j].isAssignableFrom(
                                       argClasses[j]);
                        
                       if(!argIsNull && !paramIsAssignable
                               && !argIsPrimitiveWrapper
                               || argIsNull && paramIsPrimitive) {
                           found = false;
                           break;
                       }
                   }
                   
                   if(found) {
                       result = constructor;
                       break;
                   }
               }
        }
           
           if(result == null) {
               throw new NoSuchMethodException(
                   "No constructor for class " + targetClass.getName()
                   + " found");
           }
           
        return result;
    }
    
    private Method findStaticMethod() throws NoSuchMethodException {
        Class[] argClasses = getClasses();
        Class targetClass = (Class) target;
        
        Method result = null;
        
        Method[] methods = targetClass.getMethods();
        for(int i = 0; i < methods.length; ++i) {
            Method method = methods[i];
            if(!method.getName().equals(methodName) || !Modifier.isStatic(
                    method.getModifiers())) {
                continue;
            }
            Class[] parameterTypes = method.getParameterTypes();
            if(parameterTypes.length == argClasses.length) {
                boolean found = true;
                
                for(int j = 0; j < parameterTypes.length; ++j) {
                    boolean argIsNull = (argClasses[j] == null);
                    boolean argIsPrimitiveWrapper =    isPrimitiveWrapper(
                            argClasses[j], parameterTypes[j]);
                    boolean paramIsPrimitive = parameterTypes[j].isPrimitive();
                    boolean paramIsAssignable = argIsNull ? false
                            : parameterTypes[j].isAssignableFrom(argClasses[j]);
                    
                    if(!argIsNull && !paramIsAssignable
                            && !argIsPrimitiveWrapper
                            || argIsNull && paramIsPrimitive) {
                        found = false;
                        break;
                    }
                }
                
                if(found) {
                    result = method;
                    break;
                }
            }
        }
        
        if(result == null) {
            throw new NoSuchMethodException("No method with name " + methodName
                    + " is found");
        }
        
        return result;
    }
    
    private Method findMethod() throws NoSuchMethodException {
        Class[] argClasses = getClasses();
        Class targetClass = target.getClass();
        
        Method result = null;
        
        Method[] methods = targetClass.getMethods();
        for(int i = 0; i < methods.length; ++i) {
            Method method = methods[i];
            
            if(method.getName().equals(methodName)) {
                Class[] parameterTypes = method.getParameterTypes();
                
                if(parameterTypes.length == argClasses.length) {
                    boolean found = true;
                    
                    for(int j = 0; j < parameterTypes.length; ++j) {
                        boolean argIsNull = argClasses[j] == null;
                        boolean argIsPrimitiveWrapper =    isPrimitiveWrapper(
                                argClasses[j], parameterTypes[j]);
                        boolean paramIsPrimitive =
                                parameterTypes[j].isPrimitive();
                        boolean paramIsAssignable = argIsNull ? false
                                : parameterTypes[j].isAssignableFrom(
                                        argClasses[j]);
                        
                        if(!argIsNull && !paramIsAssignable
                                && !argIsPrimitiveWrapper
                                || argIsNull && paramIsPrimitive) {
                            found = false;
                            break;
                        }
                    }
                    
                    if(found) {
                        result = method;
                        break;
                    }
                }
            }
        }
        
        if(result == null) {
            throw new NoSuchMethodException("No method with name " + methodName
                    + " is found");
        }
        
        return result;
    }
    
    private boolean areAllParamsDefined(Class[] argClasses) {
        boolean result = true;
        for(int i = 0; i < argClasses.length; ++i) {
            if(argClasses[i] == null) {
                result = false;
                break;
            }
        }
        return result;
    }
    
    private static boolean isPrimitiveWrapper(Class wrapper, Class base) {
        return
            (base == boolean.class) && (wrapper == Boolean.class) ||
            (base == byte.class) && (wrapper == Byte.class) ||
            (base == char.class) && (wrapper == Character.class) ||
            (base == short.class) && (wrapper == Short.class) ||
            (base == int.class) && (wrapper == Integer.class) ||
            (base == long.class) && (wrapper == Long.class) ||
            (base == float.class) && (wrapper == Float.class) ||
            (base == double.class) && (wrapper == Double.class);
    }
    
    static String convertClassName(Class type) {
        Class componentType = type.getComponentType();
        Class resultType = (componentType == null) ? type : componentType;
        StringTokenizer st = new StringTokenizer(resultType.getName(), ".");
        String result = st.hasMoreElements() ? (String) st.nextElement() : null;
        if(result == null) return result;
        while(st.hasMoreElements()) {
            result += "_" + (String) st.nextElement();
        }
        if(componentType != null) {
            result += "_array";
        }
        return result;
    }
    
    private Class[] getClasses() {
        Class[] result = new Class[arguments.length];
        for(int i = 0; i < arguments.length; ++i) {
            result[i] = (arguments[i] == null) ? null : arguments[i].getClass();
        }
        return result;
    }
    
    public boolean equals(Object o) {
        if(o instanceof Statement) {
            Statement s = (Statement) o;
            
            Object[] otherArguments = s.getArguments();
            boolean argsEqual = (otherArguments.length == arguments.length);
            if(argsEqual) {
                for(int i = 0; i < arguments.length; ++i) {
                    if(otherArguments[i] != arguments[i]) {
                        argsEqual = false;
                        break;
                    }
                }
            }
            
            if(!argsEqual) {
                return false;
            } else {
                return (s.getTarget() == this.getTarget() &&
                    s.getMethodName().equals(this.getMethodName()));
            }
                
        } else {
            return false;
        }
    }
}
