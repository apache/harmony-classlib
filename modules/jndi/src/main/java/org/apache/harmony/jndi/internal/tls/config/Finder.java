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
import java.util.ArrayList;
import java.util.List;

import org.apache.harmony.jndi.internal.nls.Messages;

/**
 * It is used as it bases to look for archives within a given relative path
 * 
 * @author Osvaldo C. Demo
 */
public abstract class Finder {

    /**
     * relative path to the Configuration File
     */
    public static final String cFile = "META-INF" + File.separator + "services"
            + File.separator + "javax.naming.ldap.StartTlsResponse";

    /**
     * In order to store the found objects
     */
    protected List foundObjects;

    /**
     * In order to store all the objects
     */
    protected List allObjects;

    private Finder() {
        foundObjects = new ArrayList();
        allObjects = new ArrayList();
    }

    /**
     * Constructor
     * 
     * @param name
     *            the path to start scanning
     */
    protected Finder(String name) {
        this();
        fillList(new File(name));
        doSearch();
    }

    /**
     * This returns a list with the found objects
     * 
     * @return the found objects list
     */
    public List getObjects() {
        if (foundObjects == null) {
            throw new NullPointerException(Messages.getString("ldap.22"));
        }
        return foundObjects;
    }

    /**
     * Used to add an object to the found list
     * 
     * @param name
     *            the object to add
     */
    protected void foundThis(Object name) {
        foundObjects.add((File) name);
    }

    /**
     * Fills the lists with all the objects from the entered relative route
     * 
     * @param dir
     *            the relative route to start
     */
    private void fillList(File dir) {
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; ++i) {
            allObjects.add(files[i]);
            if (files[i].isDirectory())
                fillList(files[i]);
        }
    }

    /**
     * Write this method to search based on the desired criteria
     */
    protected abstract void doSearch();
}
