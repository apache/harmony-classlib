/*
 * Copyright 2006 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.apache.harmony.tools.keytool;

/**
 * The main class that bundles command line parsing, interaction with the user
 * and work with keys and certificates.
 *
 * Class that implements the functionality of the key and certificate management
 * tool.
 */
public class Main {

    /**
     * Does the actual work with keys and certificates, based on the parameter
     * param. If something goes wrong an exception is thrown.
     */
    static void doWork(KeytoolParameters param) throws Exception {
        // TODO
        throw new RuntimeException("The method is not implemented yet.");
    }

    /**
     * The main method to run from another program.
     * 
     * @param args -
     *            command line with options.
     */
    public static void run(String[] args) throws Exception {
        // TODO
        throw new RuntimeException("The method is not implemented yet.");
    }

    /**
     * The main method to run from command line.
     * 
     * @param args -
     *            command line with options.
     */
    public static void main(String[] args) {
        try {
            run(args);
        } catch (Exception e) {
            // System.out.println("Keytool error: " + e);
            e.printStackTrace();
        }
    }
}
