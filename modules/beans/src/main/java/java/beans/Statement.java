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

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import org.apache.harmony.beans.internal.nls.Messages;

public class Statement {
    private Object target;

    private String methodName;

    private Object[] arguments;

    public Statement(Object target, String methodName, Object[] arguments) {
        this.target = target;
        this.methodName = methodName;
        if (arguments != null) {
            this.arguments = arguments;
        } else {
            this.arguments = new Object[0];
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String targetVar = target != null ? convertClassName(target.getClass()) : "null"; //$NON-NLS-1$

        sb.append(targetVar);
        sb.append('.');
        sb.append(methodName);
        sb.append('(');

        if (arguments != null) {
            for (int i = 0; i < arguments.length; ++i) {
                if (i > 0) {
                    sb.append(", "); //$NON-NLS-1$
                }
                if (arguments[i] == null) {
                    sb.append("null"); //$NON-NLS-1$
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

    public String getMethodName() {
        return methodName;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public Object getTarget() {
        return target;
    }

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
            } else if (methodName.equals("newInstance") //$NON-NLS-1$
                    && target == Array.class) {
                Class<?> componentType = (Class) arguments[0];
                int length = (Integer) arguments[1];
                result = Array.newInstance(componentType, length);
            } else if (target instanceof Class &&
                       (methodName.equals("new") || //$NON-NLS-1$
                        methodName.equals("newInstance"))) { //$NON-NLS-1$
                Constructor<?> constructor;
                Class<?> clazz = (Class <?>) target;
                
                if (clazz.isArray()) {
                    // special form for constructing arrays, 
                    // can be passed from decoder
                    result = Array.newInstance(clazz.getComponentType(),
                            (Integer) arguments[0]);
                } else {
                    constructor = findConstructor();
                    result = constructor.newInstance(arguments);
                }
            } else if (target instanceof Class) {
                Method method = null;
                boolean found = false;

                try {
                    /*
                     * Try to look for a static method of class described by the
                     * given Class object at first process only if the class
                     * differs from Class itself
                     */
                    if (target != Class.class) {
                        method = findMethod((Class) target, methodName, arguments, true);
                        result = method.invoke(null, arguments);
                        found = true;
                    }
                } catch (NoSuchMethodException e) {
                }

                if (!found) {
                    // static method was not found
                    // try to invoke method of Class object
                    if (methodName.equals("forName") //$NON-NLS-1$
                            && arguments.length == 1 && arguments[0] instanceof String) {
                        // special handling of Class.forName(String)
                        try {
                            result = Class.forName((String) arguments[0]);
                        } catch (ClassNotFoundException e2) {
                            result = Class.forName((String) arguments[0], true, Thread
                                    .currentThread().getContextClassLoader());
                        }
                    } else {
                        method = findMethod(target.getClass(), methodName, arguments, false);
                        result = method.invoke(target, arguments);
                    }
                }
            } else {
                Method method = findMethod(target.getClass(), methodName, arguments, false);
                // XXX investigate: do we really need this?
                // AccessController.doPrivileged(new PrivilegedAction<Object>()
                // {
                //      
                // public Object run() {
                // mtd.setAccessible(true);
                // return null;
                // }
                // });
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
        if (!methodName.equals("set") && !methodName.equals("get")) { //$NON-NLS-1$ //$NON-NLS-2$
            throw new NoSuchMethodException(Messages.getString("beans.3C")); //$NON-NLS-1$
        } else if (arguments.length > 0 && arguments[0].getClass() != Integer.class) {
            throw new ClassCastException(Messages.getString("beans.3D")); //$NON-NLS-1$
        } else if (methodName.equals("get") && (arguments.length != 1)) { //$NON-NLS-1$
            throw new ArrayIndexOutOfBoundsException(Messages.getString("beans.3E")); //$NON-NLS-1$
        } else if (methodName.equals("set") && (arguments.length != 2)) { //$NON-NLS-1$
            throw new ArrayIndexOutOfBoundsException(Messages.getString("beans.3F")); //$NON-NLS-1$
        }
        if (methodName.equals("get")) { //$NON-NLS-1$
            return Array.class.getMethod("get", new Class[] { Object.class, //$NON-NLS-1$
                    int.class });
        }
        return Array.class.getMethod("set", new Class[] { Object.class, //$NON-NLS-1$
                int.class, Object.class });
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
        Class<?>[] argClasses = getClasses(arguments);
        Class<?> targetClass = (Class) target;
        Constructor<?> result = null;
        Constructor<?>[] constructors = targetClass.getConstructors();

        for (Constructor<?> constructor : constructors) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();

            if (parameterTypes.length == argClasses.length) {
                boolean found = true;

                for (int j = 0; j < parameterTypes.length; ++j) {
                    boolean argIsNull = argClasses[j] == null;
                    boolean argIsPrimitiveWrapper = isPrimitiveWrapper(argClasses[j],
                            parameterTypes[j]);
                    boolean paramIsPrimitive = parameterTypes[j].isPrimitive();
                    boolean paramIsAssignable = argIsNull ? false : parameterTypes[j]
                            .isAssignableFrom(argClasses[j]);

                    if (!argIsNull && !paramIsAssignable && !argIsPrimitiveWrapper || argIsNull
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
            throw new NoSuchMethodException(Messages.getString(
                    "beans.40", targetClass.getName())); //$NON-NLS-1$
        }
        return result;
    }

    /**
     * Searches for best matching method for given name and argument types.
     */
    static Method findMethod(Class<?> targetClass, String methodName, Object[] arguments,
            boolean methodIsStatic) throws NoSuchMethodException {
        Class<?>[] argClasses = getClasses(arguments);
        Method[] methods = targetClass.getMethods();
        Vector<Method> foundMethods = new Vector<Method>();
        Method[] foundMethodsArr;

        for (Method method : methods) {
            int mods = method.getModifiers();

            if (method.getName().equals(methodName)
                    && (methodIsStatic ? Modifier.isStatic(mods) : true)) {
                Class<?>[] parameterTypes = method.getParameterTypes();

                if (parameterTypes.length == argClasses.length) {
                    boolean found = true;

                    for (int j = 0; j < parameterTypes.length; ++j) {
                        boolean argIsNull = (argClasses[j] == null);
                        boolean argIsPrimitiveWrapper = isPrimitiveWrapper(argClasses[j],
                                parameterTypes[j]);
                        boolean paramIsPrimitive = parameterTypes[j].isPrimitive();
                        boolean paramIsAssignable = argIsNull ? false : parameterTypes[j]
                                .isAssignableFrom(argClasses[j]);

                        if (!argIsNull && !paramIsAssignable && !argIsPrimitiveWrapper
                                || argIsNull && paramIsPrimitive) {
                            found = false;
                            break;
                        }
                    }
                    if (found) {
                        foundMethods.addElement(method);
                    }
                }
            }
        }
        if (foundMethods.size() == 0) {
            throw new NoSuchMethodException(Messages.getString("beans.41", methodName)); //$NON-NLS-1$
        }
        foundMethodsArr = foundMethods.toArray(new Method[foundMethods.size()]);
        Arrays.sort(foundMethodsArr, new MethodComparator(methodName, argClasses));
        return foundMethodsArr[0];
    }

    static boolean isStaticMethodCall(Statement stmt) {
        Object target = stmt.getTarget();
        String mName = stmt.getMethodName();

        if (!(target instanceof Class)) {
            return false;
        }

        try {
            Statement.findMethod((Class) target, mName, stmt.getArguments(), true);
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    /*
     * The list of "method signatures" used by persistence delegates to create
     * objects. Not necessary reflects to real methods.
     */
    private static final String[][] pdConstructorSignatures = {
            { "java.lang.Class", "new", "java.lang.Boolean", "", "", "" }, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
            { "java.lang.Class", "new", "java.lang.Byte", "", "", "" }, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
            { "java.lang.Class", "new", "java.lang.Character", "", "", "" }, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
            { "java.lang.Class", "new", "java.lang.Double", "", "", "" }, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
            { "java.lang.Class", "new", "java.lang.Float", "", "", "" }, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
            { "java.lang.Class", "new", "java.lang.Integer", "", "", "" }, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
            { "java.lang.Class", "new", "java.lang.Long", "", "", "" }, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
            { "java.lang.Class", "new", "java.lang.Short", "", "", "" }, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
            { "java.lang.Class", "new", "java.lang.String", "", "", "" }, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
            { "java.lang.Class", "forName", "java.lang.String", "", "", "" }, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
            { "java.lang.Class", "newInstance", "java.lang.Class", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                    "java.lang.Integer", "", "" }, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            { "java.lang.reflect.Field", "get", "null", "", "", "" }, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
            { "java.lang.Class", "forName", "java.lang.String", "", "", "" } //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
    };

    static boolean isPDConstructor(Statement stmt) {
        Object target = stmt.getTarget();
        String methodName = stmt.getMethodName();
        Object[] args = stmt.getArguments();
        String[] sig = new String[pdConstructorSignatures[0].length];
        if (target == null || methodName == null || args == null || args.length == 0) {
            // not a constructor for sure
            return false;
        }
        sig[0] = target.getClass().getName();
        sig[1] = methodName;
        for (int i = 2; i < sig.length; i++) {
            if (args.length > i - 2) {
                sig[i] = args[i - 2] != null ? args[i - 2].getClass().getName() : "null"; //$NON-NLS-1$
            } else {
                sig[i] = ""; //$NON-NLS-1$
            }
        }
        for (String[] element : pdConstructorSignatures) {
            if (Arrays.equals(sig, element)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isPrimitiveWrapper(Class<?> wrapper, Class<?> base) {
        return (base == boolean.class) && (wrapper == Boolean.class) || (base == byte.class)
                && (wrapper == Byte.class) || (base == char.class)
                && (wrapper == Character.class) || (base == short.class)
                && (wrapper == Short.class) || (base == int.class)
                && (wrapper == Integer.class) || (base == long.class)
                && (wrapper == Long.class) || (base == float.class) && (wrapper == Float.class)
                || (base == double.class) && (wrapper == Double.class);
    }

    private static Class<?> getPrimitiveWrapper(Class<?> base) {
        Class<?> res = null;
        if (base == boolean.class) {
            res = Boolean.class;
        } else if (base == byte.class) {
            res = Byte.class;
        } else if (base == char.class) {
            res = Character.class;
        } else if (base == short.class) {
            res = Short.class;
        } else if (base == int.class) {
            res = Integer.class;
        } else if (base == long.class) {
            res = Long.class;
        } else if (base == float.class) {
            res = Float.class;
        } else if (base == double.class) {
            res = Double.class;
        }
        return res;
    }

    static String convertClassName(Class<?> type) {
        Class<?> componentType = type.getComponentType();
        Class<?> resultType = (componentType == null) ? type : componentType;
        String result = resultType.getName();
        int k = result.lastIndexOf('.');
        if (k != -1 && k < result.length()) {
            result = result.substring(k + 1);
        }
        if (componentType != null) {
            result += "Array"; //$NON-NLS-1$
        }
        return result;
    }

    private static Class<?>[] getClasses(Object[] arguments) {
        Class<?>[] result = new Class[arguments.length];
        for (int i = 0; i < arguments.length; ++i) {
            result[i] = (arguments[i] == null) ? null : arguments[i].getClass();
        }
        return result;
    }

    @Override
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
            }
            return (s.getTarget() == this.getTarget() && s.getMethodName().equals(
                    this.getMethodName()));
        }
        return false;
    }

    /**
     * Comparator to determine which of two methods is "closer" to the reference
     * method.
     */
    static class MethodComparator implements Comparator<Method> {
        static int INFINITY = Integer.MAX_VALUE;

        private String referenceMethodName;

        private Class<?>[] referenceMethodArgumentTypes;

        private final Map<Method, Integer> cache;

        public MethodComparator(String refMethodName, Class<?>[] refArgumentTypes) {
            this.referenceMethodName = refMethodName;
            this.referenceMethodArgumentTypes = refArgumentTypes;
            cache = new HashMap<Method, Integer>();
        }

        public int compare(Method m1, Method m2) {
            Integer norm1 = cache.get(m1);
            Integer norm2 = cache.get(m2);
            if (norm1 == null) {
                norm1 = Integer.valueOf(getNorm(m1));
                cache.put(m1, norm1);
            }
            if (norm2 == null) {
                norm2 = Integer.valueOf(getNorm(m2));
                cache.put(m2, norm2);
            }
            return (norm1.intValue() - norm2.intValue());
        }

        /**
         * Returns the norm for given method. The norm is the "distance" from
         * the reference method to the given method.
         * 
         * @param m
         *            the method to calculate the norm for
         * @return norm of given method
         */
        private int getNorm(Method m) {
            String methodName = m.getName();
            Class<?>[] argumentTypes = m.getParameterTypes();
            int totalNorm = 0;
            if (!referenceMethodName.equals(methodName)
                    || referenceMethodArgumentTypes.length != argumentTypes.length) {
                return INFINITY;
            }
            for (int i = 0; i < referenceMethodArgumentTypes.length; i++) {
                if (referenceMethodArgumentTypes[i] == null) {
                    if (argumentTypes[i].isPrimitive()) {
                        return INFINITY;
                    }
                    // doesn't affect the norm calculation if null
                    continue;
                }
                if (referenceMethodArgumentTypes[i].isPrimitive()) {
                    referenceMethodArgumentTypes[i] = getPrimitiveWrapper(referenceMethodArgumentTypes[i]);
                }
                if (argumentTypes[i].isPrimitive()) {
                    argumentTypes[i] = getPrimitiveWrapper(argumentTypes[i]);
                }
                totalNorm += getDistance(referenceMethodArgumentTypes[i], argumentTypes[i]);
            }
            return totalNorm;
        }

        /**
         * Returns a "hierarchy distance" between two classes.
         * 
         * @param clz1
         * @param clz2
         *            should be superclass or superinterface of clz1
         * @return hierarchy distance from clz1 to clz2, Integer.MAX_VALUE if
         *         clz2 is not assignable from clz1.
         */
        private static int getDistance(Class<?> clz1, Class<?> clz2) {
            Class<?> superClz;
            int superDist = INFINITY;
            if (!clz2.isAssignableFrom(clz1)) {
                return INFINITY;
            }
            if (clz1.getName().equals(clz2.getName())) {
                return 0;
            }
            superClz = clz1.getSuperclass();
            if (superClz != null) {
                superDist = getDistance(superClz, clz2);
            }
            if (clz2.isInterface()) {
                Class<?>[] interfaces = clz1.getInterfaces();
                int bestDist = INFINITY;
                for (Class<?> element : interfaces) {
                    int curDist = getDistance(element, clz2);
                    if (curDist < bestDist) {
                        bestDist = curDist;
                    }
                }
                if (superDist < bestDist) {
                    bestDist = superDist;
                }
                return (bestDist != INFINITY ? bestDist + 1 : INFINITY);
            }
            return (superDist != INFINITY ? superDist + 1 : INFINITY);
        }
    }
}
