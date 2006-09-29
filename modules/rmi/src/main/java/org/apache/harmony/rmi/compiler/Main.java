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
 * @version $Revision: 1.1.2.5 $
 */
package org.apache.harmony.rmi.compiler;


/**
 * Launcher engine for RMI Compiler.
 *
 * @author  Vasily Zakharov
 * @version $Revision: 1.1.2.5 $
 */
public final class Main implements RmicConstants {

    /**
     * Command line startup method for RMI Compiler.
     *
     * @param   args
     *          Command line arguments.
     */
    public static void main(String[] args) {
        try {
            new RMICompiler(args).run();
        } catch (RMICompilerException e) {
            String message = e.getMessage();

            if ((message != null) && message.startsWith(EOLN)) {
                System.out.println(message);
            } else {
                System.out.println("RMIC Error: "
                        + ((message != null) ? message : ""));
                e.printStackTrace(System.out);
                System.exit(-1);
            }
        } catch (Throwable e) {
            System.out.println("RMIC Error: Unexpected exception:" + e);
            e.printStackTrace(System.out);
            System.exit(-1);
        }
    }
}
