/* 
 *  Licensed to the Apache Software Foundation (ASF) under one or more 
 *  contributor license agreements.  See the NOTICE file distributed with 
 *  this work for additional information regarding copyright ownership. 
 *  The ASF licenses this file to You under the Apache License, Version 2.0 
 *  (the "License"); you may not use this file except in compliance with 
 *  the License.  You may obtain a copy of the License at 
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0 
 * 
 *  Unless required by applicable law or agreed to in writing, software 
 *  distributed under the License is distributed on an "AS IS" BASIS, 
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 *  See the License for the specific language governing permissions and 
 *  limitations under the License. 
 */ 

package org.apache.harmony.jndi.internal.tls.config;

import java.io.File;
import java.util.Iterator;

/**
 * This class finds the file
 * "META-INF/services/javax.naming.ldap.StartTlsResponse" in a directory
 * 
 * @author Osvaldo C. Demo
 */
public class DirFinder extends Finder {

    /**
     * Class constructor
     * 
     * @param name
     *            path where to start the search for the config file
     */
    protected DirFinder(String name) {
        super(name);
    }

    /**
     * Begins the search process
     */
    protected void doSearch() {
        for (Iterator iter = super.allObjects.iterator(); iter.hasNext();) {
            File element = (File) iter.next();
            if (element.getAbsolutePath().endsWith(ClassFinder.cFile)) {
                foundThis(element.getAbsoluteFile());
            }
        }
    }
}
