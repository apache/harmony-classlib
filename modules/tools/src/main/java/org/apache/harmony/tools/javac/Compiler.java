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
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import org.apache.harmony.tools.toolutils.Util;

/**
 * A proxy to the Java source code compiler itself.
 */
class Compiler {

    /* FIXME: Hard-coded for now, the name of the ECJ JAR file */
    static final String ECJ_JAR_FILE = "ecj_3.2.jar"; //$NON-NLS-1$

    static final String TOOLS_JAR_FILE = "tools.jar"; //$NON-NLS-1$

    /* The name of the ECJ compiler class */
    static final String MAIN_CLASS_NAME = "org.eclipse.jdt.internal.compiler.batch.Main"; //$NON-NLS-1$

    /*
     * Invokes the compiler with the given command-line arguments. The supported
     * arguments can be determined form the usage message.
     * 
     * Answers the result of the compilation from ECJ; i.e. true if the compile
     * succeeded, and false otherwise.
     */
    public static boolean main(String[] args) {
        return main(args, Util.getDefaultWriter(System.out), Util.getDefaultWriter(System.err));
    }

    public static boolean main(String[] args, PrintWriter out, PrintWriter err) {
        Compiler myself = new Compiler(out, err);

        // If there is a problem invoking the method, simply dump the trace for
        // now
        try {
            Object result = myself.staticCompileMth.invoke(myself.mainInst,
                    new Object[] { args });
            return (Boolean) result;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Reference to ECJ 'Main' compiler class.
    Class<?> ecjCompilerClass;

    // An instance of the ECJ compiler
    Object mainInst;

    // The Main#printUsage() method.
    Method printUsageMth;

    // The static Main#compile(string[]) method on the ECJ compiler
    Method staticCompileMth;

    /**
     * Default constructor. Returns a new initialized instance of the Java
     * compiler.
     */
    public Compiler(PrintWriter out, PrintWriter err) {
        super();
        initialize(out, err);
    }

    /*
     * Initialize our local variables. Called during type construction.
     */
    protected void initialize(PrintWriter out, PrintWriter err) {
        try {
            initializeMainClass();
            initializeInstance(out, err);
            initializeMethods();
        } catch (Exception e) {
            // If there is a problem we log it to the console
            e.printStackTrace();
        }
    }

    /*
     * Defines the local instance of the ECJ compiler
     */
    protected void initializeInstance(PrintWriter out, PrintWriter err) throws SecurityException,
            NoSuchMethodException, IllegalArgumentException,
            InstantiationException, IllegalAccessException,
            InvocationTargetException {

        // Create a new instance of the compiler
        Constructor<?> ctor = ecjCompilerClass.getConstructor(new Class[] {
                PrintWriter.class, PrintWriter.class, Boolean.TYPE });

        mainInst = ctor.newInstance(new Object[] { out, err,
                Boolean.FALSE });
    }

    /*
     * Defines the compiler class from the ECJ jar file
     */
    protected void initializeMainClass() throws ClassNotFoundException,
            SecurityException, NoSuchMethodException, MalformedURLException,
            IllegalArgumentException, InstantiationException,
            IllegalAccessException, InvocationTargetException {

        // Find the ECJ JAR file, prefer those found near loaders
        URL ecjURL = searchLoaders();
        if (ecjURL == null) {
            ecjURL = searchPaths();
        }
        if (ecjURL == null) {
            throw new RuntimeException("Cannot find file " + ECJ_JAR_FILE);
        }

        // Load the ECJ main class
        URLClassLoader loader = new URLClassLoader(new URL[] { ecjURL });
        ecjCompilerClass = loader.loadClass(MAIN_CLASS_NAME);
    }

    /*
     * Looks for the ECJ JAR file in the current working directory, and in the
     * jdk/lib of the current runtime. Answers with a URL of the JAR file if
     * found, or null if not found.
     */
    private URL searchPaths() throws MalformedURLException {
        // Search in current working directory
        File cwdFile = new File(ECJ_JAR_FILE);
        if (cwdFile.exists()) {
            return cwdFile.toURL();
        }

        // Look for it via the java.home
        File javaHomeFile = new File(System.getProperty("java.home")); //$NON-NLS-1$
        String pathFromJDK = "lib" + File.separator + ECJ_JAR_FILE; //$NON-NLS-1$

        // Is java.home pointing at a JDK?
        File jdkBasedFile = new File(javaHomeFile, pathFromJDK);
        if (jdkBasedFile.exists()) {
            return jdkBasedFile.toURL();
        }
        // Maybe it is pointing at a JRE.
        File jdkHomeFile = javaHomeFile.getParentFile();
        if (jdkHomeFile == null) {
            return null;
        }
        File jreBasedFile = new File(jdkHomeFile, pathFromJDK);
        if (jreBasedFile.exists()) {
            return jreBasedFile.toURL();
        }

        // We didn't find it
        return null;
    }

    /*
     * Find the ECJ jar by searching for the tools.jar location and figuring
     * that it is alongside that.
     */
    private URL searchLoaders() throws MalformedURLException {
        URLClassLoader bogusLoader = new URLClassLoader(new URL[] {});
        ClassLoader parentLoader = bogusLoader.getParent();
        while (parentLoader instanceof URLClassLoader) {
            URLClassLoader parentURLLoader = (URLClassLoader) parentLoader;
            URL[] uls = parentURLLoader.getURLs();
            for (int i = 0; i < uls.length; i++) {
                URL l = uls[i];
                String filename = new File(l.getFile()).getName();
                if (filename.equals(TOOLS_JAR_FILE)) {
                    return new URL(l, ECJ_JAR_FILE);
                }
            }
            // Not found here, move up a level
            parentLoader = parentLoader.getParent();
        }
        // We didn't find it
        return null;
    }

    /*
     * Initialize our local references to compiler methods we may wish to
     * invoke.
     */
    protected void initializeMethods() throws SecurityException,
            NoSuchMethodException {
        staticCompileMth = ecjCompilerClass.getMethod("compile", //$NON-NLS-1$
                new Class[] { String[].class });
        printUsageMth = ecjCompilerClass
                .getMethod("printUsage", (Class[]) null); //$NON-NLS-1$
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
