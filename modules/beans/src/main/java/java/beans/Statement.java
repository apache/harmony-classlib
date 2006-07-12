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
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.AccessController;
import java.security.PrivilegedAction;

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
        if (arguments != null) {
            this.arguments = arguments;
        } else {
            this.arguments = new Object[0];
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();
        String targetVar = target != null ? convertClassName(target.getClass())
                : "null";

        sb.append(targetVar);
        sb.append('.');
        sb.append(methodName);
        sb.append('(');

        if (arguments != null) {
            for (int i = 0; i < arguments.length; ++i) {
                if (i > 0) {
                    sb.append(", ");
                }

                if (arguments[i] == null) {
                    sb.append("null");
                } else if (arguments[i] instanceof String) {
                    sb.append('"');
                    sb.append(arguments[i].toString());
                    sb.append('"');
                } else {
                    sb.append(convertClassName(arguments[i].getClass()));
                }
            }
        }
        sb.append(')');
        sb.append(';');
        return sb.toString();
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
            if (target.getClass().isArray()) {
                Method method = findArrayMethod();
                Object[] ama = getArrayMethodArguments();

                result = method.invoke(null, ama);
            } else if (methodName.equals("newInstance")
                    && target instanceof Class
                    && ((Class) target).getName().equals(
                            "java.lang.reflect.Array")) {
                Class<?> componentType = (Class) arguments[0];
                int length = ((Integer) arguments[1]).intValue();

                result = Array.newInstance(componentType, length);
            } else if (methodName.equals("new")
                    || methodName.equals("newInstance")) {
                if (target instanceof Class) {
                    Constructor<?> constructor = findConstructor();

                    result = constructor.newInstance(arguments);
                } else {
                    // XXX should be investigated, dead code?
                    Constructor<?> constructor = findConstructor();

                    result = constructor.newInstance(arguments);
                }
            } else if (target instanceof Class) {
                Method method = null;

                try {
                    // try to look for static method at first
                    method = findMethod((Class) target, methodName, arguments,
                            true);
                    result = method.invoke(null, arguments);
                } catch (NoSuchMethodException e) {
                    // static method was not found
                    // try to invoke non-static method of Class object
                    method = findMethod(target.getClass(), methodName,
                            arguments, false);
                    result = method.invoke(target, arguments);
                }
            } else {
                final Method method = findMethod(target.getClass(), methodName,
                        arguments, false);

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
        // the code below reproduces exact RI exception throwing behavior 
        if (!methodName.equals("set") && !methodName.equals("get")) {
            throw new NoSuchMethodException("Unknown method name for array");
        } else if (arguments.length > 0 &&
                   arguments[0].getClass() != Integer.class) {
            throw new ClassCastException(
                    "First parameter in array getter(setter) is not of "
                            + "Integer type");
        } else if (methodName.equals("get") && (arguments.length != 1)) {
            throw new ArrayIndexOutOfBoundsException(
                    "Illegal number of arguments in array getter");
        } else if (methodName.equals("set") && (arguments.length != 2)) {
            throw new ArrayIndexOutOfBoundsException(
                    "Illegal number of arguments in array setter");
        } 

        if (methodName.equals("get")) {
            return Array.class.getMethod("get",
                    new Class[] { Object.class, int.class } );
        } else {
            return Array.class.getMethod("set",
                    new Class[] { Object.class, int.class, Object.class } );
        }
    }

    private Object[] getArrayMethodArguments() {
        Object[] args = new Object[arguments.length + 1];
        args[0] = target;
        for (int i = 0; i < arguments.length; ++i) {
            args[i + 1] = arguments[i];
        }
        return args;
    }

    private Constructor<?> findConstructor() throws NoSuchMethodException {
        Class[] argClasses = getClasses(arguments);
        Class<?> targetClass = (Class) target;

        Constructor<?> result = null;
        Constructor[] constructors = targetClass.getConstructors();

        for (int i = 0; i < constructors.length; ++i) {
            Constructor<?> constructor = constructors[i];
            Class<?>[] parameterTypes = constructor.getParameterTypes();

            if (parameterTypes.length == argClasses.length) {
                boolean found = true;

                for (int j = 0; j < parameterTypes.length; ++j) {
                    boolean argIsNull = argClasses[j] == null;
                    boolean argIsPrimitiveWrapper = isPrimitiveWrapper(
                            argClasses[j], parameterTypes[j]);
                    boolean paramIsPrimitive = parameterTypes[j].isPrimitive();
                    boolean paramIsAssignable = argIsNull ? false
                            : parameterTypes[j].isAssignableFrom(argClasses[j]);

                    if (!argIsNull && !paramIsAssignable
                            && !argIsPrimitiveWrapper || argIsNull
                            && paramIsPrimitive) {
                        found = false;
                        break;
                    }
                }

                if (found) {
                    result = constructor;
                    break;
                }
            }
        }

        if (result == null) {
            throw new NoSuchMethodException("No constructor for class "
                    + targetClass.getName() + " found");
        }

        return result;
    }

    /**
     * Method lookup is performed initially in the current class
     * then in superclass, then in super class of super class and so on.
     */
    private static Method findMethod(Class<?> targetClass, String methodName,
            Object[] arguments, boolean methodIsStatic)
            throws NoSuchMethodException {
        Class[] argClasses = getClasses(arguments);

        Method result = null;
        Method[] methods = targetClass.getDeclaredMethods();

        for (int i = 0; i < methods.length; ++i) {
            Method method = methods[i];
            int mods = method.getModifiers();

            if (method.getName().equals(methodName) && Modifier.isPublic(mods)
                    && (methodIsStatic ? Modifier.isStatic(mods) : true)) {

                Class<?>[] parameterTypes = method.getParameterTypes();

                if (parameterTypes.length == argClasses.length) {
                    boolean found = true;

                    for (int j = 0; j < parameterTypes.length; ++j) {
                        boolean argIsNull = (argClasses[j] == null);
                        boolean argIsPrimitiveWrapper = isPrimitiveWrapper(
                                argClasses[j], parameterTypes[j]);
                        boolean paramIsPrimitive = parameterTypes[j]
                                .isPrimitive();
                        boolean paramIsAssignable = argIsNull ? false
                                : parameterTypes[j]
                                        .isAssignableFrom(argClasses[j]);

                        if (!argIsNull && !paramIsAssignable
                                && !argIsPrimitiveWrapper || argIsNull
                                && paramIsPrimitive) {
                            found = false;
                            break;
                        }
                    }

                    if (found) {
                        result = method;
                        break;
                    }
                }
            }
        }

        if (result == null) {
            // let's look for this method in the super class
            Class<?> parent = targetClass.getSuperclass();

            if (parent != null) {
                result = findMethod(parent, methodName, arguments,
                        methodIsStatic);
            } else {
                throw new NoSuchMethodException("No method with name "
                        + methodName + " is found");
            }
        }

        return result;
    }

    private static boolean isPrimitiveWrapper(Class<?> wrapper, Class<?> base) {
        return (base == boolean.class) && (wrapper == Boolean.class)
                || (base == byte.class) && (wrapper == Byte.class)
                || (base == char.class) && (wrapper == Character.class)
                || (base == short.class) && (wrapper == Short.class)
                || (base == int.class) && (wrapper == Integer.class)
                || (base == long.class) && (wrapper == Long.class)
                || (base == float.class) && (wrapper == Float.class)
                || (base == double.class) && (wrapper == Double.class);
    }

    static String convertClassName(Class<?> type) {
        Class<?> componentType = type.getComponentType();
        Class<?> resultType = (componentType == null) ? type : componentType;
        String result = resultType.getName();
        int k = result.lastIndexOf('.');
        ;

        if (k != -1 && k < result.length()) {
            result = result.substring(k + 1);
        }

        if (componentType != null) {
            result += "Array";
        }

        return result;
    }

    private static Class[] getClasses(Object[] arguments) {
        Class[] result = new Class[arguments.length];

        for (int i = 0; i < arguments.length; ++i) {
            result[i] = (arguments[i] == null) ? null : arguments[i].getClass();
        }
        return result;
    }

    public boolean equals(Object o) {
        if (o instanceof Statement) {
            Statement s = (Statement) o;

            Object[] otherArguments = s.getArguments();
            boolean argsEqual = (otherArguments.length == arguments.length);
            if (argsEqual) {
                for (int i = 0; i < arguments.length; ++i) {
                    if (otherArguments[i] != arguments[i]) {
                        argsEqual = false;
                        break;
                    }
                }
            }

            if (!argsEqual) {
                return false;
            } else {
                return (s.getTarget() == this.getTarget() && s.getMethodName()
                        .equals(this.getMethodName()));
            }

        } else {
            return false;
        }
    }
}
