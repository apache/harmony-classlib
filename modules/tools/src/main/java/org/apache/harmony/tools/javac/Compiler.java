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
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.harmony.tools.javac;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * A proxy to the Java source code compiler itself.
 */
class Compiler {

    /* A default encoding for console messages */
    static String CONSOLE_ENCODING = System.getProperty("console.encoding",
            "ISO8859_1");

    /* FIXME: Hard-coded for now, the name of the ECJ JAR file */
    static final String ECJ_JAR_FILE = "ecj_3.2.jar";

    /* The name of the ECJ compiler class */
    static final String MAIN_CLASS_NAME = "org.eclipse.jdt.internal.compiler.batch.Main";

    /*
     * Invokes the compiler with the given command-line arguments. The supported
     * arguments can be determined form the usage message.
     */
    public static void main(String[] args) {
        Compiler myself = new Compiler();
        myself.initialize();
        // If there is a problem invoking the method, simply dump the trace for
        // now
        try {
            myself.staticMainMth.invoke(myself.mainInst, new Object[] { args });
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    // Reference to ECJ 'Main' compiler class.
    Class<?> ecjCompilerClass;

    // An instance of the ECJ compiler
    Object mainInst;

    // The Main#printUsage() method.
    Method printUsageMth;

    // The static Main#main(string[]) method on the ECJ compiler
    Method staticMainMth;

    /**
     * Default constructor. Returns a new initialized instance of the Java
     * compiler.
     */
    public Compiler() {
        super();
        initialize();
    }

    /*
     * Initialize our local variables. Called during type construction.
     */
    protected void initialize() {
        if (!new File(ECJ_JAR_FILE).exists()) {
            System.err.println("javac requires the compiler jar \""
                    + ECJ_JAR_FILE + "\" to run");
        }
        try {
            initializeMainClass();
            initializeInstance();
            initializeMethods();
        } catch (Exception e) {
            // If there is a problem we log it to the console
            e.printStackTrace();
        }
    }

    /*
     * Defines the local instance of the ECJ compiler
     */
    protected void initializeInstance() throws SecurityException,
            NoSuchMethodException, IllegalArgumentException,
            InstantiationException, IllegalAccessException,
            InvocationTargetException {

        // Set up reasonable defaults for the messages
        OutputStreamWriter osw;
        OutputStreamWriter esw;
        try {
            osw = new OutputStreamWriter(System.out, CONSOLE_ENCODING);
            esw = new OutputStreamWriter(System.err, CONSOLE_ENCODING);
        } catch (UnsupportedEncodingException e) {
            osw = new OutputStreamWriter(System.out);
            esw = new OutputStreamWriter(System.err);
        }
        PrintWriter outWriter = new PrintWriter(osw);
        PrintWriter errWriter = new PrintWriter(esw);

        // Create a new instance of the compiler
        Constructor<?> ctor = ecjCompilerClass.getConstructor(new Class[] {
                PrintWriter.class, PrintWriter.class, Boolean.TYPE });

        mainInst = ctor.newInstance(new Object[] { outWriter, errWriter,
                Boolean.TRUE });
    }

    /*
     * Defines the compiler class from the ECJ jar file
     */
    protected void initializeMainClass() throws ClassNotFoundException,
            SecurityException, NoSuchMethodException, MalformedURLException,
            IllegalArgumentException, InstantiationException,
            IllegalAccessException, InvocationTargetException {

        // Load the ECJ main class
        URL ecjURL = new URL("file:" + ECJ_JAR_FILE);
        URLClassLoader loader = new URLClassLoader(new URL[] { ecjURL });
        ecjCompilerClass = loader.loadClass(MAIN_CLASS_NAME);
    }

    /*
     * Initialize our local references to compiler methods we may wish to
     * invoke.
     */
    protected void initializeMethods() throws SecurityException,
            NoSuchMethodException {
        staticMainMth = ecjCompilerClass.getMethod("main",
                new Class[] { String[].class });
        printUsageMth = ecjCompilerClass.getMethod("printUsage", (Class[])null);
    }

    /**
     * Prints the compiler usage message out on the console.
     */
    public void printUsage() {
        // If there is a problem invoking the method, simply dump the trace for
        // now
        try {
            printUsageMth.invoke(mainInst);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
