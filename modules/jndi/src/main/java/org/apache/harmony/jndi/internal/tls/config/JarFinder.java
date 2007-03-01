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
import java.io.IOException;
import java.util.Iterator;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.harmony.jndi.internal.nls.Messages;

/**
 * This class finds jars only containing "META-INF/services" directory
 * 
 * @author Osvaldo C. Demo
 */
public class JarFinder extends Finder {

    /**
     * Constructor
     * 
     * @param name
     *            path where to start the search for Jar files
     */
    public JarFinder(String name) {
        super(name);
    }

    protected void doSearch() {
        for (Iterator iter = super.allObjects.iterator(); iter.hasNext();) {
            File element = (File) iter.next();
            if (element.getName().endsWith("jar")) {
                try {
                    findService(element.getAbsoluteFile());
                } catch (Exception e) {
                }
            }
        }
    }

    /**
     * Searchs for the configuration file inside the Jar
     * 
     * @param element
     *            JarFile to search in
     * 
     * @throws Exception
     *             if config file was not found
     */
    public void findService(File element) throws Exception {
        JarFile jarFile = null;
        JarEntry entry = null;
        try {
            jarFile = new JarFile(element);
            entry = jarFile
                    .getJarEntry(Finder.cFile);
        } catch (IOException e) {
        }
        if (entry != null) {
            foundThis(element.getAbsoluteFile());
        } else {
            throw new Exception(
                    Finder.cFile + Messages.getString("ldap.23")
                            + element.getAbsolutePath());
        }
    }
}
