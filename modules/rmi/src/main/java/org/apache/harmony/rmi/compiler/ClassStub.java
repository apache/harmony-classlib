/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author  Vasily Zakharov
 * @version $Revision: 1.1.2.3 $
 */
package org.apache.harmony.rmi.compiler;

import java.lang.reflect.Method;

import java.rmi.Remote;
import java.rmi.RemoteException;

import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Vector;

import org.apache.harmony.rmi.common.ClassList;
import org.apache.harmony.rmi.common.RMIHash;
import org.apache.harmony.rmi.common.RMIHashException;
import org.apache.harmony.rmi.common.RMIUtil;


/**
 * Generates RMI stub code for a particular class.
 *
 * @author  Vasily Zakharov
 * @version $Revision: 1.1.2.3 $
 */
final class ClassStub implements RmicConstants {

    /**
     * Should the stub support v1.1.
     */
    final boolean v11;

    /**
     * Should the stub support v1.2.
     */
    final boolean v12;

    /**
     * Should the stub support both v1.1 and v1.2.
     */
    final boolean vCompat;

    /**
     * Indenter to write source files.
     */
    Indenter indenter;

    /**
     * Name of the class to generate stub for.
     */
    private final String className;

    /**
     * Package of the class to generate stub for.
     */
    private final String packageName;

    /**
     * Stub class name.
     */
    private final String stubName;

    /**
     * Skeleton class name.
     */
    private final String skelName;

    /**
     * List of remote interfaces for the class.
     */
    private final Class[] interfaces;

    /**
     * List of remote interfaces methods for the class.
     */
    private final Vector methods;

    /**
     * <code>true</code> if methods exist in {@link #methods} vector.
     */
    private final boolean methodsExist;

    /**
     * Class interface hash.
     */
    private final long interfaceHash;

    /**
     * Creates <code>ClassStub</code> instance for specified version and class.
     *
     * @param   version
     *          Version of the stub to create.
     *
     * @param   className
     *          Name of the class to load.
     *
     * @throws  RMICompilerException
     *          If version number is incorrect or some other error occurs.
     */
    ClassStub(int version, String className) throws RMICompilerException {
        this(version, getClass(className));
    }

    /**
     * Creates <code>ClassStub</code> instance for specified version and class.
     *
     * @param   version
     *          Version of the stub to create.
     *
     * @param   cls
     *          Class to load.
     *
     * @throws  RMICompilerException
     *          If version number is incorrect or some other error occurs.
     */
    ClassStub(int version, Class cls) throws RMICompilerException {
        // Check version.
        if ((version < MIN_VERSION) || (version > MAX_VERSION)) {
            throw new RMICompilerException("Incorrect version specified.");
        }

        // Set appropriate version flags.
        switch (version) {
        case VERSION_V11:
            v11 = true;
            v12 = false;
            vCompat = false;
            break;
        case VERSION_V12:
            v11 = false;
            v12 = true;
            vCompat = false;
            break;
        case VERSION_VCOMPAT:
            v11 = true;
            v12 = true;
            vCompat = true;
            break;
        default:
            throw new RMICompilerException("Version currently unsupported.");
        }

        className = RMIUtil.getCanonicalName(cls);

        // Check if the specified class is interface.
        if (cls.isInterface()) {
            throw new RMICompilerException("Class " + className
                    + " is interface, and so does not need an RMI stub.");
        }

        // Check if the specified class is remote.
        if (!Remote.class.isAssignableFrom(cls)) {
            throw new RMICompilerException("Class " + className
                    + " does not implement a remote interface,"
                    + " and so does not need an RMI stub.");
        }

        // Check if the specified class implements any remote interfaces.
        if (!new ClassList(cls.getInterfaces()).contains(Remote.class)) {
            throw new RMICompilerException("Class " + className
                    + " does not directly implement a remote interface,"
                    + " and so does not need an RMI stub.");
        }

        // Initialize class variables.
        packageName = RMIUtil.getPackageName(cls);

        String shortClassName = RMIUtil.getShortName(cls);
        stubName = shortClassName + stubSuffix;
        skelName = shortClassName + skelSuffix;

        try {
            interfaces = RMIUtil.getRemoteInterfaces(cls);
        } catch (IllegalArgumentException e) {
            throw new RMICompilerException(e);
        }

        methods = new Vector();

        // Create temporal method stubs table (sorted, no duplicates).
        TreeMap methodMap = new TreeMap();

        // Extract remote methods from remote interfaces of cls and ancestors.
        for (int i = 0; i < interfaces.length; i++) {
            // Add public methods from this interface to the map.
            RMIHash.getSortedMethodMap(methodMap, interfaces[i].getMethods());
        }

        if (v11) {
            try {
                // Calculate interface hash value for the set of methods found.
                interfaceHash = RMIHash.getInterfaceHash(methodMap);
            } catch (RMIHashException e) {
                throw new RMICompilerException(e.getMessage(), e.getCause());
            }
        } else {
            interfaceHash = (-1L);
        }

        // Put the complete MethodStub objects to methods list.
        int n = 0;
        for (Iterator i = methodMap.values().iterator(); i.hasNext(); ) {
            methods.add(new MethodStub((Method) i.next(), n++));
        }

        methodsExist = (n > 0);
    }

    /**
     * Returns the class object for the specified class name.
     *
     * @param   className
     *          Class name.
     *
     * @return  Class object for the specified class name.
     *
     * @throws  RMICompilerException
     *          If class is not found.
     */
    private static Class getClass(String className)
            throws RMICompilerException {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RMICompilerException("Class not found: " + className);
        }
    }

    /**
     * Returns stub class name for loaded class.
     *
     * @return  Stub class name.
     */
    String getStubClassName() {
        return stubName;
    }

    /**
     * Returns skeleton class name for loaded class.
     *
     * @return  Skeleton class name.
     */
    String getSkeletonClassName() {
        return skelName;
    }

    /**
     * Returns stub source code for the loaded class (v1.1/v1.2).
     *
     * @return  String containing the stub source code for loaded class.
     */
    String getStubSource() {
        indenter = new Indenter();

        return (getStubHeader("stub") + getPackageStatement() + EOLN
                + getStubClassDeclaration() + indenter.hIncrease()
                + (v12 ? (EOLN + getSerialVersionUID()) : "")
                + (v11 ? (EOLN + getInterfaceHash() + (methodsExist
                        ? ((vCompat ? EOLN + getNewInvoke() : "")
                        + EOLN + getOperationsArrayDeclaration()) : "")) : "")
                + ((v12 && methodsExist) ? (EOLN
                        + getMethodVariablesDeclaration() + EOLN
                        + getStaticInitializationBlock()) : "") + EOLN
                + getStubConstructors()
                + (methodsExist ? getMethodImplementations() : "")
                + indenter.decrease() + '}' + EOLN + indenter.assertEmpty());
    }

    /**
     * Returns skeleton source code for the loaded class (v1.1).
     *
     * @return  String containing the skeleton source code for loaded class.
     */
    String getSkeletonSource() {
        indenter = new Indenter();

        return (getStubHeader("skeleton") + getPackageStatement() + EOLN
                + getSkeletonClassDeclaration()
                + indenter.hIncrease() + EOLN + getInterfaceHash() + EOLN
                + getOperationsArrayDeclaration() + EOLN
                + getOperationsMethod() + EOLN + getDispatchMethod()
                + indenter.decrease() + '}' + EOLN + indenter.assertEmpty());
    }

    /**
     * Returns stub source code header
     * (Stub/Skeleton v1.1/v1.2/vCompat).
     *
     * @param   type
     *          Header type, <code>"stub"</code> or <code>"skeleton"</code>.
     *
     * @return  Stub/skeleton header.
     */
    private String getStubHeader(String type) {
        return ("/*" + EOLN + " * RMI " + type + " class" + EOLN
                + " * for class " + className + EOLN
                + " * Compatible with stub protocol version "
                + (v11 ? "1.1" : "") + (vCompat ? "/" : "")
                + (v12 ? "1.2" : "") + EOLN + " *" + EOLN
                + " * Generated by DRL RMI Compiler (rmic)." + EOLN
                + " *" + EOLN + " * DO NOT EDIT!!!" + EOLN
                + " * Contents subject to change without notice!" + EOLN
                + " */" + EOLN);
    }

    /**
     * Returns <code>package</code> statement
     * (Stub/Skeleton v1.1/v1.2/vCompat).
     *
     * @return  <code>package</code> statement for stub/skeleton class.
     */
    private String getPackageStatement() {
        return ((packageName == null) ? ""
                : ("package " + packageName + ';' + EOLN + EOLN));
    }

    /**
     * Returns stub class declaration
     * (Stub v1.1/v1.2/vCompat).
     *
     * @return  Stub class declaration statement.
     */
    private String getStubClassDeclaration() {
        StringBuffer buffer = new StringBuffer("public final class "
                + stubName + " extends java.rmi.server.RemoteStub" + EOLN
                + indenter.tIncrease(2) + "implements ");

        // Add implemented interfaces list.
        for (int i = 0; i < interfaces.length; i++) {
            buffer.append(((i > 0) ? ", " : "" ) + interfaces[i].getName());
        }

        buffer.append(" {" + EOLN);

        return buffer.toString();
    }

    /**
     * Returns skeleton class declaration
     * (Skeleton v1.1/vCompat).
     *
     * @return  Skeleton class declaration statement.
     */
    private String getSkeletonClassDeclaration() {
        return ("public final class " + skelName
                + " implements java.rmi.server.Skeleton {" + EOLN);
    }

    /**
     * Returns <code>serialVersionUID</code> declaration
     * (Stub v1.2/vCompat).
     *
     * @return  <code>serialVersionUID</code> declaration statement.
     */
    private String getSerialVersionUID() {
        return (indenter.indent()
                    + "private static final long serialVersionUID = 2;" + EOLN);
    }

    /**
     * Returns <code>interfaceHash</code> declaration
     * (Stub/Skeleton v1.1/vCompat).
     *
     * @return  <code>interfaceHash</code> declaration statement.
     */
    private String getInterfaceHash() {
        return (indenter.indent() + "private static final long "
                + interfaceHashVarName + " = " + interfaceHash + "L;" + EOLN);
    }

    /**
     * Returns <code>useNewInvoke</code> declaration
     * (Stub vCompat).
     *
     * @return  <code>useNewInvoke</code> declaration statement.
     */
    private String getNewInvoke() {
        return (indenter.indent()
                + "private static boolean " + useNewInvoke + ';'+ EOLN);
    }

    /**
     * Returns <code>operations</code> array declaration
     * (Stub/Skeleton v1.1/vCompat).
     *
     * @return  <code>operations</code> array declaration statement.
     */
    private String getOperationsArrayDeclaration() {
        StringBuffer buffer = new StringBuffer(indenter.indent()
                + "private static final java.rmi.server.Operation[]"
                + " operations = {");

        if (methodsExist) {
            buffer.append(EOLN + indenter.hIncrease());

            for (Iterator i = methods.iterator(); i.hasNext(); ) {
                buffer.append(((MethodStub) i.next()).getOpsArrayElement()
                        + (i.hasNext() ? "," : "" ) + EOLN);
            }

            buffer.append(indenter.decrease());
        }

        buffer.append("};" + EOLN);

        return buffer.toString();
    }

    /**
     * Returns <code>getOperations()</code> method declaration
     * (Skeleton v1.1/vCompat).
     *
     * @return  <code>getOperations()</code> declaration statement.
     */
    private String getOperationsMethod() {
        return (indenter.indent()
                + "public java.rmi.server.Operation[] getOperations() {" + EOLN
                + indenter.tIncrease()
                + "return (java.rmi.server.Operation[]) operations.clone();"
                + EOLN + indenter.indent() + '}' + EOLN);
    }

    /**
     * Returns <code>dispatch()</code> method declaration
     * (Skeleton v1.1/vCompat).
     *
     * @return  <code>dispatch()</code> method declaration statement.
     */
    private String getDispatchMethod() {
        StringBuffer buffer = new StringBuffer(indenter.indent()
                + "public void dispatch(java.rmi.Remote obj, "
                + "java.rmi.server.RemoteCall call, int opnum, long hash) "
                + "throws java.lang.Exception {" + EOLN + indenter.hIncrease());

        if (vCompat) {
            buffer.append(indenter.indent() + "if (opnum < 0) {" + EOLN
                    + indenter.increase());

            if (methodsExist) {
                for (Iterator i = methods.iterator(); i.hasNext(); ) {
                    buffer.append(((MethodStub) i.next()).getHashCheck());
                }
                buffer.append('{' + EOLN + indenter.increase());
            }

            buffer.append("throw new java.rmi.UnmarshalException("
                    + "\"Invalid method hash: \" + hash);" + EOLN
                    + indenter.decrease() + '}' + EOLN
                    + (methodsExist ? (indenter.tDecrease() + "} else {") : "")
                    + EOLN);
        }

        buffer.append(indenter.indent() + "if (hash != interfaceHash) {" + EOLN
                + indenter.increase()
                + "throw new java.rmi.server.SkeletonMismatchException(" + EOLN
                + indenter.tIncrease(2)
                + "\"Interface hash mismatch, expected: \" + interfaceHash"
                + " + \", received: \" + hash);" + EOLN
                + indenter.decrease() + '}' + EOLN + ((vCompat && methodsExist)
                        ? (indenter.decrease() + '}' + EOLN) : "") + EOLN
                + indenter.indent() + className + " server = "
                    + '(' + className + ") obj;" + EOLN + EOLN);

        if (methodsExist) {
            buffer.append(indenter.indent() + "switch (opnum) {" + EOLN);

            for (Iterator i = methods.iterator(); i.hasNext(); ) {
                buffer.append(EOLN + ((MethodStub) i.next()).getDispatchCase());
            }

            buffer.append(EOLN + indenter.indent() + "default:" + EOLN);

            indenter.increase();
        }

        buffer.append(indenter.indent()
                + "throw new java.rmi.UnmarshalException("
                + "\"Invalid method number: \" + opnum);" + EOLN
                + (methodsExist ? (indenter.decrease() + '}' + EOLN) : "")
                + indenter.decrease() + '}' + EOLN);

        return buffer.toString();
    }

    /**
     * Returns method variables declaration block
     * (Stub v1.2/vCompat).
     *
     * @return  Variables declaration block.
     */
    private String getMethodVariablesDeclaration() {
        StringBuffer buffer = new StringBuffer();

        for (Iterator i = methods.iterator(); i.hasNext(); ) {
            buffer.append(((MethodStub) i.next()).getVariableDecl());
        }

        return buffer.toString();
    }

    /**
     * Creates static initialization block
     * (Stub v1.2/vCompat).
     *
     * @return  Static initialization declaration block.
     */
    private String getStaticInitializationBlock() {
        StringBuffer buffer = new StringBuffer(indenter.indent()
                + "static {" + EOLN + indenter.increase() + "try {" + EOLN
                + indenter.hIncrease());

        if (vCompat) {
            buffer.append(indenter.indent()
                    + "java.rmi.server.RemoteRef.class.getMethod(\"invoke\", "
                    + "new java.lang.Class[] {java.rmi.Remote.class, "
                    + "java.lang.reflect.Method.class, java.lang.Object[].class"
                    + ", long.class});" + EOLN + EOLN);
        }

        for (Iterator i = methods.iterator(); i.hasNext(); ) {
            buffer.append(((MethodStub) i.next()).getVariableInit());
        }

        buffer.append((vCompat ? (EOLN + indenter.indent() + useNewInvoke
                + " = true;" + EOLN) : "") + indenter.decrease()
                + "} catch (java.lang.NoSuchMethodException e) {" + EOLN
                + indenter.increase()
                + (vCompat ? (useNewInvoke + " = false;")
                        : ("throw new java.lang.NoSuchMethodError(" + EOLN
                                + indenter.tIncrease(2)
                                + "\"Stub class initialization failed: "
                                + ((packageName != null)
                                        ? (packageName + '.') : "")
                                + stubName + "\");")) + EOLN
                + indenter.decrease() + '}' + EOLN
                + indenter.decrease() + '}' + EOLN);

        return buffer.toString();
    }

    /**
     * Returns stub constructors
     * (Stub v1.1/v1.2/vCompat).
     *
     * @return  Stub constructors code.
     */
    private String getStubConstructors() {
        StringBuffer buffer = new StringBuffer();

        if (v11) {
            buffer.append(indenter.indent() + "public " + stubName
                    + "() {" + EOLN + indenter.tIncrease() + "super();" + EOLN
                    + indenter.indent() + '}' + EOLN + EOLN);
        }

        buffer.append(indenter.indent() + "public " + stubName
                + "(java.rmi.server.RemoteRef ref) {" + EOLN
                + indenter.tIncrease() + "super(ref);" + EOLN
                + indenter.indent() + '}' + EOLN);

        return buffer.toString();
    }

    /**
     * Returns remote methods implementation
     * (Stub v1.1/v1.2/vCompat).
     *
     * @return  Stub method implementations code.
     */
    private String getMethodImplementations() {
        StringBuffer buffer = new StringBuffer();

        for (Iterator i = methods.iterator(); i.hasNext(); ) {
            buffer.append(EOLN + ((MethodStub) i.next()).getStubImpl());
        }

        return buffer.toString();
    }

    /**
     * Generates RMI stub code for a particular method.
     */
    private final class MethodStub {

        /**
         * The method name (via {@link Method#getName()}).
         */
        private final String name;

        /**
         * The name of the interface declaring this method.
         */
        private final String interfaceName;

        /**
         * The method parameters (via {@link Method#getParameterTypes()}).
         */
        private final Class[] parameters;

        /**
         * The method parameters class names.
         */
        private final String[] paramClassNames;

        /**
         * The method parameters names.
         */
        private final String[] paramNames;

        /**
         * Number of parameters for this method.
         */
        private final int numParams;

        /**
         * The method return type (via {@link Method#getReturnType()}).
         */
        private final Class retType;

        /**
         * The method return type name.
         */
        private final String retTypeName;

        /**
         * Exceptions that this method throws.
         */
        private final Vector exceptions;

        /**
         * Exceptions that must be caught in method stub.
         */
        private final ClassList catches;

        /**
         * The method long signature
         * (via {@link RMIUtil#getLongMethodSignature(Method)
         * getLongMethodSignature()}).
         */
        private final String longSign;

        /**
         * The method short signature
         * (via {@link RMIUtil#getShortMethodSignature(Method)
         * getShortMethodSignature()}).
         */
        private final String shortSign;

        /**
         * The method hash (via {@link RMIHash#getMethodHash(Method)
         * getMethodHash()}).
         */
        private final long hash;

        /**
         * The method number in the stub.
         */
        private final int number;

        /**
         * Name of the variable containing this method in the stub
         * (<code>$method_<em>name</em>_<em>number</em></code>).
         */
        private final String varName;

        /**
         * Whether this method throws {@link Exception}.
         */
        private final boolean throwsException;

        /**
         * Creates method stub instance.
         *
         * @param   method
         *          Method to process.
         *
         * @param   number
         *          Method number in sorted methods table.
         *
         * @throws  RMICompilerException
         *          If some error occurs.
         */
        MethodStub(Method method, int number) throws RMICompilerException {
            this.name = method.getName();
            this.interfaceName = method.getDeclaringClass().getName();
            this.parameters = method.getParameterTypes();
            this.numParams = parameters.length;
            this.retType = method.getReturnType();
            this.retTypeName = RMIUtil.getCanonicalName(retType);
            this.longSign = RMIUtil.getLongMethodSignature(method);
            this.shortSign = RMIUtil.getShortMethodSignature(method);
            this.number = number;
            this.varName = (methodVarPrefix + name + '_' + number);

            try {
                this.hash = RMIHash.getMethodHash(method);
            } catch (RMIHashException e) {
                throw new RMICompilerException(e.getMessage(), e);
            }

            // Create parameter names array.
            paramClassNames = new String[numParams];
            paramNames = new String[numParams];
            for (int i = 0; i < numParams; i++) {
                Class parameter = parameters[i];
                paramClassNames[i] = RMIUtil.getCanonicalName(parameter);
                paramNames[i] = RmicUtil.getParameterName(parameter, i + 1);
            }

            // Create list of exceptions declared thrown.
            Class[] exceptionsArray = method.getExceptionTypes();
            exceptions = new Vector(exceptionsArray.length);
            exceptions.addAll(Arrays.asList(exceptionsArray));

            // Create list of exceptions to be caught.
            catches = new ClassList(true);

            // Add standard exceptions to make sure they are first in the list.
            catches.add(RuntimeException.class);
            catches.add(RemoteException.class);

            // Add declared thrown exceptions.
            catches.addAll(exceptions);

            throwsException = catches.contains(Exception.class);
        }

        /**
         * Returns <code>operations</code> array element for this method
         * (Stub/Skeleton v1.1/vCompat)
         *
         * @return  <code>operations</code> array element for this method.
         */
        String getOpsArrayElement() {
            return (indenter.indent() +
                        "new java.rmi.server.Operation(\"" + longSign + "\")");
        }

        /**
         * Returns hash checking code for this method
         * (Skeleton vCompat).
         *
         * @return  Hash checking code for this method..
         */
        String getHashCheck() {
            return ("if (hash == " + hash + "L) {" + EOLN
                    + indenter.tIncrease() + "opnum = " + number + ';' + EOLN
                    + indenter.indent() + "} else ");
        }

        /**
         * Returns <code>dispatch()</code> method case for this method
         * (Skeleton v1.1/vCompat).
         *
         * @return  <code>dispatch()</code> method case for this method.
         */
        String getDispatchCase() {
            StringBuffer buffer = new StringBuffer(indenter.indent()
                    + "case " + number + ": {    // " + shortSign + EOLN + EOLN
                    + indenter.hIncrease());

            if (numParams > 0) {
                // Add parameters declaration.
                for (int i = 0; i < numParams; i++) {
                    buffer.append(indenter.indent() + paramClassNames[i]
                            + ' ' + paramNames[i] + ';' + EOLN);
                }

                // Access input stream.
                buffer.append(EOLN + indenter.indent() + "try {" + EOLN
                        + indenter.increase() + "java.io.ObjectInput "
                        + inputStreamName + " = call.getInputStream();" + EOLN);

                boolean objectParametersExist = false;

                // Add parameters initialization, read them from the stream.
                for (int i = 0; i < numParams; i++) {
                    buffer.append(indenter.indent() + paramNames[i] + " = "
                            + RmicUtil.getReadObjectString(parameters[i],
                                    inputStreamName) + ';' + EOLN);

                    if (!parameters[i].isPrimitive()) {
                        objectParametersExist = true;
                    }
                }

                // Add catch block.
                buffer.append(indenter.tDecrease()
                        + "} catch (java.io.IOException e) {" + EOLN
                        + indenter.indent()
                        + "throw new java.rmi.UnmarshalException("
                        + "\"Error unmarshalling arguments\", e);" + EOLN
                        + (objectParametersExist ? (indenter.tDecrease()
                        + "} catch (java.lang.ClassNotFoundException e) {"
                        + EOLN + indenter.indent()
                        + "throw new java.rmi.UnmarshalException("
                        + "\"Error unmarshalling arguments\", e);" + EOLN) : "")
                        + indenter.tDecrease() + "} finally {" + EOLN);
            }
            // Release input stream.
            buffer.append(indenter.indent()
                    + "call.releaseInputStream();" + EOLN);

            if (numParams > 0) {
                buffer.append(indenter.decrease() + '}' + EOLN);
            }

            buffer.append(EOLN + indenter.indent() + ((retType != void.class)
                    ? (retTypeName + ' ' + retVarName + " = ") : "")
                    + "server." + name + '(');

            for (int i = 0; i < numParams; i++) {
                buffer.append(((i > 0) ? ", " : "") + paramNames[i]);
            }

            buffer.append(");" + EOLN + EOLN + indenter.indent() + "try {"
                    + EOLN + indenter.increase() + ((retType != void.class)
                    ? ("java.io.ObjectOutput " + outputStreamName + " = ") : "")
                    + "call.getResultStream(true);" + EOLN
                    + ((retType != void.class) ? (indenter.indent()
                            + RmicUtil.getWriteObjectString(retType, retVarName,
                                    outputStreamName) + ';' + EOLN) : "")
                    + indenter.decrease() + "} catch (java.io.IOException e) {"
                    + EOLN + indenter.tIncrease()
                    + "throw new java.rmi.MarshalException("
                    + "\"Error marshalling return\", e);" + EOLN
                    + indenter.indent() + '}' + EOLN + EOLN
                    + indenter.indent() + "break;" + EOLN
                    + indenter.decrease() + '}' + EOLN);

            return buffer.toString();
        }

        /**
         * Returns source code for the method variable declaration
         * (Stub v1.2/vCompat).
         *
         * @return  Method variable declaration.
         */
        String getVariableDecl() {
            return (indenter.indent()
                    + "private static java.lang.reflect.Method"
                    + ' ' + varName + ';' + EOLN);
        }

        /**
         * Returns source code for the method variable initialization
         * (Stub v1.2/vCompat).
         *
         * @return  Method variable initialization.
         */
        String getVariableInit() {
            StringBuffer buffer = new StringBuffer(indenter.indent()
                    + varName + " = " + interfaceName + ".class.getMethod(\""
                    + name + "\", new java.lang.Class[] {");

            if (numParams > 0) {
                // Write method parameters.
                for (int i = 0; i < numParams; i++) {
                    buffer.append(((i > 0) ? ", " : "")
                            + paramClassNames[i] + ".class");
                }
            }

            buffer.append("});" + EOLN);

            return buffer.toString();
        }

        /**
         * Returns stub imlementation for this method
         * (Stub v1.1/v1.2/vCompat).
         *
         * @return  Stub imlementation for this method.
         */
        String getStubImpl() {
            return (getStubImplHeader()
                    + (vCompat ? (indenter.indent() + "if (" + useNewInvoke
                            + ") {" + EOLN + indenter.hIncrease()) : "")
                    + (v12 ? getStubImplCodeV12() : "") + (vCompat
                            ? (indenter.tDecrease() + "} else {" + EOLN) : "")
                    + (v11 ? getStubImplCodeV11() : "")
                    + (vCompat ? (indenter.decrease() + '}' + EOLN) : "")
                    + (throwsException ? "" : (indenter.hDecrease()
                            + getStubImplCatchBlock()))
                    + indenter.decrease() + '}' + EOLN);
        }

        /**
         * Returns header for the stub implementaton for this method
         * (Stub v1.1/v1.2/vCompat).
         *
         * @return  Stub imlementation header for this method.
         */
        private String getStubImplHeader() {
            StringBuffer buffer = new StringBuffer(indenter.indent()
                    + "// Implementation of " + shortSign + EOLN
                    + indenter.indent() + "public " + retTypeName
                    + ' ' + name + '(');

            // Write method parameters.
            for (int i = 0; i < numParams; i++) {
                buffer.append(((i > 0) ? ", " : "")
                        + paramClassNames[i] + ' ' + paramNames[i]);
            }

            buffer.append(')' + EOLN + indenter.tIncrease(2) + "throws ");

            // Write exceptions declared thrown.
            for (Iterator i = exceptions.iterator(); i.hasNext(); ) {
                buffer.append(((Class) i.next()).getName()
                        + (i.hasNext() ? ", " : "" ));
            }

            buffer.append(" {" + EOLN + indenter.hIncrease()
                    + (throwsException ? "" : (indenter.indent()
                            + "try {" + EOLN + indenter.hIncrease())));

            return buffer.toString();
        }

        /**
         * Returns the stub implementaton code section source for this method
         * (Stub v1.1/vCompat).
         *
         * @return  Stub imlementation code for this method.
         */
        private String getStubImplCodeV11() {
            StringBuffer buffer = new StringBuffer(indenter.indent()
                    + "java.rmi.server.RemoteCall call = "
                    + "ref.newCall((java.rmi.server.RemoteObject) this, "
                    + "operations, " + number + ", " + interfaceHashVarName
                    + ");" + EOLN);

            if (numParams > 0) {
                buffer.append(EOLN + indenter.indent() + "try {" + EOLN
                    + indenter.increase() + "java.io.ObjectOutput "
                    + outputStreamName + " = call.getOutputStream();" + EOLN);

                for (int i = 0; i < numParams; i++) {
                    buffer.append(indenter.indent()
                            + RmicUtil.getWriteObjectString(parameters[i],
                                    paramNames[i], outputStreamName)
                            + ';' + EOLN);
                }

                buffer.append(indenter.decrease()
                        + "} catch (java.io.IOException e) {" + EOLN
                        + indenter.tIncrease()
                        + "throw new java.rmi.MarshalException("
                        + "\"Error marshalling arguments\", e);" + EOLN
                        + indenter.indent() + '}' + EOLN);
            }

            buffer.append(EOLN + indenter.indent()
                    + "ref.invoke(call);" + EOLN);

            if (retType != void.class) {
                buffer.append(EOLN + indenter.indent()
                        + retTypeName + ' ' + retVarName + ';' + EOLN + EOLN
                        + indenter.indent() + "try {" + EOLN
                        + indenter.increase() + "java.io.ObjectInput "
                        + inputStreamName + " = call.getInputStream();" + EOLN
                        + indenter.indent() + retVarName + " = "
                        + RmicUtil.getReadObjectString(retType, inputStreamName)
                        + ';' + EOLN + indenter.decrease()
                        + "} catch (java.io.IOException e) {" + EOLN
                        + indenter.tIncrease()
                        + "throw new java.rmi.UnmarshalException("
                        + "\"Error unmarshalling return value\", e);" + EOLN
                        + (!retType.isPrimitive() ? (indenter.indent()
                        + "} catch (java.lang.ClassNotFoundException e) {"
                        + EOLN + indenter.tIncrease()
                        + "throw new java.rmi.UnmarshalException("
                        + "\"Error unmarshalling return value\", e);" + EOLN)
                        : "") + indenter.indent() + "} finally {" + EOLN
                        + indenter.tIncrease() + "ref.done(call);" + EOLN
                        + indenter.indent() + '}' + EOLN + EOLN
                        + indenter.indent() + "return " + retVarName + ';'
                        + EOLN);
            } else {
                buffer.append(EOLN + indenter.indent()
                        + "ref.done(call);" + EOLN);
            }

            return buffer.toString();
        }

        /**
         * Returns the stub implementaton code section source for this method
         * (Stub v1.2/vCompat).
         *
         * @return  Stub imlementation code for this method.
         */
        private String getStubImplCodeV12() {
            StringBuffer buffer = new StringBuffer(indenter.indent());

            if (retType != void.class) {
                buffer.append("java.lang.Object " + retVarName + " = ");
            }

            buffer.append("ref.invoke(this, " + varName + ", ");

            if (numParams > 0) {
                buffer.append("new java.lang.Object[] {");

                // Write invocation parameters.
                for (int i = 0; i < numParams; i++) {
                    buffer.append(((i > 0) ? ", " : "")
                            + RmicUtil.getObjectParameterString(
                                    parameters[i], paramNames[i]));
                }
                buffer.append('}');
            } else {
                buffer.append("null");
            }

            buffer.append(", " + hash + "L);" + EOLN);

            // Write return statement.
            if (retType != void.class) {
                buffer.append(indenter.indent() + "return "
                        + RmicUtil.getReturnObjectString(retType, retVarName)
                        + ';' + EOLN);
            }

            return buffer.toString();
        }

        /**
         * Returns the stub implementaton catch block for this method
         * (Stub v1.1/v1.2/vCompat).
         *
         * @return  Stub imlementation catch block for this method.
         */
        private String getStubImplCatchBlock() {
            StringBuffer buffer = new StringBuffer();

            for (Iterator i = catches.iterator(); i.hasNext(); ) {
                buffer.append(indenter.indent() + "} catch ("
                        + ((Class) i.next()).getName() + " e) {" + EOLN
                        + indenter.tIncrease() + "throw e;" + EOLN);
            }

            buffer.append(indenter.indent()
                    + "} catch (java.lang.Exception e) {" + EOLN
                    + indenter.tIncrease()
                    + "throw new java.rmi.UnexpectedException("
                    + "\"Undeclared checked exception\", e);" + EOLN
                    + indenter.indent() + '}' + EOLN);

            return buffer.toString();
        }
    }
}
