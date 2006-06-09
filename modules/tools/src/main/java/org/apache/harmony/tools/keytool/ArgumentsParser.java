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
 * The class to interact with the user - parse the program arguments, ask for
 * confirmations, and necessary parameters which haven't been set in the command
 * line.
 */

class ArgumentsParser {
    /**
     * The method finds known options in args which is usually taken from
     * command line and sets the corresponding fields of the returned
     * KeytoolParameters object to given values.
     */
    static KeytoolParameters parseArgs(String[] args){
        // TODO
        throw new RuntimeException("The method is not implemented yet.");
    }

    /**
     * Checks if the needed values are set and, if not, prompts for them.
     */
    static void getAdditionalParameters(KeytoolParameters param){
        // TODO
        throw new RuntimeException("The method is not implemented yet.");
    }

}
