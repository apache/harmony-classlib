/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.apache.harmony.tools.jarsigner;

import java.io.OutputStream;


/**
 * The main class that bundles command line parsing, interaction with the user,
 * exception handling and JAR signing and verification work.
 */
public class Main {
    /**
     * Does the actual work on JAR signing and verification, based on the
     * parameter param. If something goes wrong an exception is thrown.
     * 
     * @param param
     * @throws Exception
     */
    static void doWork(JSParameters param) throws Exception {
        // TODO
    }
    
    /**
     * The main method to run from another program.
     * 
     * @param args -
     *            command line with options.
     */
    public static void run(String[] args, OutputStream out) throws Exception {
        // TODO
        if (out != System.out){
            UserInteractor.setOutputStream(out);
        }
        
    }

    
    /**
     * The main method to run from command line.
     * 
     * @param args -
     *            command line with options.
     */
    public static void main(String[] args) {
        try {
            run(args, System.out);
        } catch (Exception e) {
            // System.out.println("JarSigner error: " + e);
            e.printStackTrace();
        }
    }

}

