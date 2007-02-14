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

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Used to treat correctly the path elements.
 * 
 * @author Osvaldo C. Demo
 * 
 */
public class MergePath {

    private List pathnames = new ArrayList();

    /**
     * Constructor
     * 
     * @param path
     *            Array of string with the path names
     */
    public MergePath(String[] path) {
        super();
        for (int i = 0; i < path.length; i++) {
            addPathNames(path[i]);
        }
    }

    /**
     * add a path to the list
     * 
     * @param path
     *            the string with the path to add, or multiple path separated by
     *            "path.separator".
     */
    private void addPathNames(String path) {
        if (path != null) {
            StringTokenizer st = new StringTokenizer(path, System
                    .getProperty("path.separator"));
            while (st.hasMoreTokens()) {
                pathnames.add(st.nextElement().toString());
            }
        }
    }

    /**
     * Returns an array of strings containing the list of paths
     * 
     * @return array of string with the path names
     */
    public String[] getPathNames() {
        String[] ret = new String[pathnames.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = (String) pathnames.get(i);
        }
        return ret;
    }

}
