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

import java.io.PrintWriter;
import org.apache.harmony.tools.toolutils.Util;

/**
 * This is the entry point for the javac tool.
 */
public final class Main {

    /*
     * Command-line tool invokes this method.
     */
    public static void main(String[] args) {
        new Main().compile(args);
    }

    /**
     * Default constructor.
     */
    public Main() {
        super();
    }

    /**
     * Invokes the ECJ compiler with the given arguments.
     * 
     * @param args
     *            the arguments passed through to the compiler
     * @return true on compilation success, false on failure
     */
    public boolean compile(String[] args) {

        return compile(args,Util.getDefaultWriter(System.out), Util.getDefaultWriter(System.err));
    }

    /**
     * Invokes the ECJ compiler with the given arguments.
     * 
     * @param args
     *            the arguments passed through to the compiler
     * @param out
     *            get the output from System.out
     * @param err
     *            get the output from System.err
     * @return true on compilation success, false on failure
     */
    public boolean compile(String[] args, PrintWriter out, PrintWriter err) {

        /* Give me something to do */
        if (args == null || args.length == 0) {
            new Compiler(out, err).printUsage();
            return false;
        }

        /* Add in the base class library code to compile against */
        String[] newArgs = addBootclasspath(args);

        /* Invoke the compiler */
        return Compiler.main(newArgs, out, err);
    }

    /*
     * Set up the compiler option to compile against the running JRE class
     * libraries.
     */
    private String[] addBootclasspath(String[] args) {
        String[] result = new String[args.length + 2];
        System.arraycopy(args, 0, result, 0, args.length);
        result[args.length] = "-classpath"; //$NON-NLS-1$
        result[args.length + 1] = System.getProperty(
                "org.apache.harmony.boot.class.path", "."); //$NON-NLS-1$ //$NON-NLS-2$
        return result;
    }
}
