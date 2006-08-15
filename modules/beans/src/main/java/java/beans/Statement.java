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
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Vector;
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
                Method method = findMethod(target.getClass(), methodName,
                        arguments, false);

                // XXX investigate: do we really need this?
//              AccessController.doPrivileged(new PrivilegedAction<Object>() {
//      
//                  public Object run() {
//                      mtd.setAccessible(true);
//                      return null;
//                  }
//              });

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
        } else if (arguments.length > 0
                && arguments[0].getClass() != Integer.class) {
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
            return Array.class.getMethod("get", new Class[] { Object.class,
                    int.class });
        } else {
            return Array.class.getMethod("set", new Class[] { Object.class,
                    int.class, Object.class });
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
     * Searches for best matching method for given name and argument types.
     */
    static Method findMethod(Class<?> targetClass, String methodName,
            Object[] arguments, boolean methodIsStatic)
            throws NoSuchMethodException {

        Class[] argClasses = getClasses(arguments);
        Method[] methods = targetClass.getMethods();
        Vector<Method> foundMethods = new Vector<Method>();
        Method[] foundMethodsArr;

        for (int i = 0; i < methods.length; ++i) {
            Method method = methods[i];
            int mods = method.getModifiers();

            if (method.getName().equals(methodName)
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
                        foundMethods.addElement(method);
                    }
                }
            }
        }

        if (foundMethods.size() == 0) {
            throw new NoSuchMethodException("No method with name " + methodName
                    + " is found");
        }

        foundMethodsArr = foundMethods.toArray(new Method[foundMethods.size()]);
        Arrays.sort(foundMethodsArr, new MethodComparator(methodName,
                argClasses));

        return foundMethodsArr[0];
    }

    static boolean isStaticMethodCall(Statement stmt) {
        Object target = stmt.getTarget();
        String mName = stmt.getMethodName();

        if (!(target instanceof Class)) {
            return false;
        }

        try {
            Statement.findMethod((Class) target, mName,
                                 stmt.getArguments(), true);
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    /**
     * The list of "method signatures" used by persistence delegates to create
     * objects. Not necessary reflects to real methods.
     */
    private static String[][] pdConstructorSignatures = {
        {"java.lang.Class", "new", "java.lang.Boolean", "", "", ""}, 
        {"java.lang.Class", "new", "java.lang.Byte", "", "", ""}, 
        {"java.lang.Class", "new", "java.lang.Character", "", "", ""}, 
        {"java.lang.Class", "new", "java.lang.Double", "", "", ""}, 
        {"java.lang.Class", "new", "java.lang.Float", "", "", ""}, 
        {"java.lang.Class", "new", "java.lang.Integer", "", "", ""}, 
        {"java.lang.Class", "new", "java.lang.Long", "", "", ""}, 
        {"java.lang.Class", "new", "java.lang.Short", "", "", ""}, 
        {"java.lang.Class", "new", "java.lang.String", "", "", ""}, 
        {"java.lang.Class", "forName", "java.lang.String", "", "", ""},
        {"java.lang.Class", "newInstance", "java.lang.Class",
                "java.lang.Integer", "", ""},
        {"java.lang.reflect.Field", "get", "null", "", "", ""},
        {"java.lang.Class", "forName", "java.lang.String", "", "", ""}
    };
    
    static boolean isPDConstructor(Statement stmt) {
        Object target = stmt.getTarget();
        String methodName = stmt.getMethodName();
        Object[] args = stmt.getArguments();
        String[] sig = new String[pdConstructorSignatures[0].length];

        if (target == null || methodName == null || args == null ||
                args.length == 0)
        {
            // not a constructor for sure
            return false;
        }

        sig[0] = target.getClass().getName();
        sig[1] = methodName;

        for (int i = 2; i < sig.length; i++) {
            if (args.length > i - 2) {
                sig[i] = args[i - 2] != null ?
                        args[i - 2].getClass().getName() : "null"; 
            } else {
                sig[i] = "";
            }

        }
        
        for (int i = 0; i < pdConstructorSignatures.length; i++) {
            if (Arrays.equals(sig, pdConstructorSignatures[i])) {
                return true;
            }
        }
        
        return false;
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

    private static Class getPrimitiveWrapper(Class base) {
        Class res = null;

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

    /**
     * Comparator to determine which of two methods is "closer" to the reference
     * method.  
     */
    static class MethodComparator implements Comparator<Method> {

        static int INFINITY = Integer.MAX_VALUE;

        private String referenceMethodName;

        private Class[] referenceMethodArgumentTypes;

        private HashMap<Method, Integer> cache;

        public MethodComparator(String refMethodName, Class[] refArgumentTypes) {
            this.referenceMethodName = refMethodName;
            this.referenceMethodArgumentTypes = refArgumentTypes;
            cache = new HashMap<Method, Integer>();
        }

        public int compare(Method m1, Method m2) {
            Integer norm1 = cache.get(m1);
            Integer norm2 = cache.get(m2);

            if (norm1 == null) {
                norm1 = getNorm(m1);
                cache.put(m1, norm1);
            }
            if (norm2 == null) {
                norm2 = getNorm(m2);
                cache.put(m2, norm2);
            }
            return (norm1 - norm2);
        }

        /**
         * Returns the norm for given method. The norm is the "distance" from
         * the reference method to the given method.
         * @param m the method to calculate the norm for
         * @return norm of given method
         */
        private int getNorm(Method m) {
            String methodName = m.getName();
            Class[] argumentTypes = m.getParameterTypes();
            int totalNorm = 0;

            if (!referenceMethodName.equals(methodName)
                    || referenceMethodArgumentTypes.length != argumentTypes.length) {
                return INFINITY;
            }

            for (int i = 0; i < referenceMethodArgumentTypes.length; i++) {

                if (referenceMethodArgumentTypes[i] == null) {
                    if (argumentTypes[i].isPrimitive()) {
                        return INFINITY;
                    } else {
                        // doesn't affect the norm calculation if null
                        continue;
                    }
                }

                if (referenceMethodArgumentTypes[i].isPrimitive()) {
                    referenceMethodArgumentTypes[i] = getPrimitiveWrapper(referenceMethodArgumentTypes[i]);
                }

                if (argumentTypes[i].isPrimitive()) {
                    argumentTypes[i] = getPrimitiveWrapper(argumentTypes[i]);
                }

                totalNorm += getDistance(referenceMethodArgumentTypes[i],
                        argumentTypes[i]);
            }

            return totalNorm;
        }

        /**
         * Returns a "hierarchy distance" between two classes. 
         * @param clz1
         * @param clz2 should be superclass or superinterface of clz1
         * @return hierarchy distance from clz1 to clz2, Integer.MAX_VALUE if
         * clz2 is not assignable from clz1.
         */
        private static int getDistance(Class<?> clz1, Class<?> clz2) {
            Class superClz;
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
                Class[] interfaces = clz1.getInterfaces();
                int bestDist = INFINITY;

                for (int i = 0; i < interfaces.length; i++) {
                    int curDist = getDistance(interfaces[i], clz2);

                    if (curDist < bestDist) {
                        bestDist = curDist;
                    }
                }

                if (superDist < bestDist) {
                    bestDist = superDist;
                }
                return (bestDist != INFINITY ? bestDist + 1 : INFINITY);
            } else {
                return (superDist != INFINITY ? superDist + 1 : INFINITY);
            }
        }
    }

}
