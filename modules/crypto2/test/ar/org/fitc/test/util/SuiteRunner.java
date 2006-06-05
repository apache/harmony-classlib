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
 * @author Hugo Beilis
 * @author Osvaldo Demo
 * @author Jorge Rafael
 * @version 1.0
 */

package ar.org.fitc.test.util;

/**
 * 
 * @author Osvaldo C. Demo
 *
 */

public class SuiteRunner {

    private Configuration conf = Configuration.getInstance();
    
    private String[] params;
   

    /**
     * Constructor
     * 
     * @param clase the name of the test class (full qualified name)
     * @param gui true for loading the graphical user interface
     * @param c the file for saving the results of the test execution
     */
    public SuiteRunner(String clase,boolean gui,Configuration c) {

        if (gui) {
            params = new String[] {
                    "-p",
                    conf.getUnitTestingProject()+" "+
                    conf.getUtilitiesProject() +" ",
                    "-r", "ar.org.fitc.test.util.XMLReporter",
                    "-g",
                    "-s", clase };
        } else {
            params = new String[] {
                    "-p",
                    conf.getUnitTestingProject()+" "+
                    conf.getUtilitiesProject() +" ",
                    "-r", "ar.org.fitc.test.util.XMLReporter",
                    "-s", clase };
        }        
    }
    
    public String[] getParameters() {
        return params;
    }
}
