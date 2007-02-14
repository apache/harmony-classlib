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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.jar.JarFile;

import org.apache.harmony.jndi.internal.nls.Messages;

/**
 * In charge to look for in the configuration file
 * "META-INF/services/javax.naming.ldap.StartTlsResponse" the classes that
 * correspond to a service provider implementation.
 * 
 * @author Osvaldo C. Demo
 * @author Juan Giannuzzo
 */
public class ClassFinder {

    /**
     * relative path to the Configuration File
     */
    public static String cFile = "META-INF" + File.separator + "services"
            + File.separator + "javax.naming.ldap.StartTlsResponse";

    private List jars = new ArrayList();

    private List dirs = new ArrayList();

    private Set classlist = new HashSet();;

    /**
     * It gives back one lists with the classes that were found in the
     * configuration file
     * 
     * @return a String array containing the full qualified name of the classes
     * @throws ConfigFileNotFound
     *             if configuration file does not exists
     */
    public String[] getClassList() throws ConfigFileNotFound {
        if (classlist.isEmpty()) {
            throw new ConfigFileNotFound(cFile + Messages.getString("ldap.21"));
        }
        String[] ret = new String[classlist.size()];

        for (int i = 0; i < ret.length; i++) {
            ret[i] = (String) classlist.toArray()[i];
        }
        return ret;
    }

    /**
     * Constructor
     * 
     * @param name
     *            String array containing paths in where the configuration file
     *            will look for
     */
    public ClassFinder(String[] name) {
        for (int i = 0; i < name.length; i++) {
            if ((new File(name[i]).isDirectory())) {
                DirFinder d = new DirFinder(name[i]);
                JarFinder j = new JarFinder(name[i]);
                dirs.addAll(d.getObjects()); // TODO check type safety here
                jars.addAll(j.getObjects()); // TODO check type safety here
            } else {
                jars.add(new File(name[i]));
            }
        }
        try {

            fillList();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    /**
     * In charge of filling the list of classes found within Jar archives and
     * directories
     * 
     * @throws IOException
     *             when the file was not found
     */
    private void fillList() throws IOException {
        BufferedReader reader = null;
        JarFile jarFile = null;

        for (Iterator iter = dirs.iterator(); iter.hasNext();) {
            File element = (File) iter.next();
            reader = new BufferedReader(new FileReader(element));
            StringTokenizer tk = new StringTokenizer(parseConfigFile(reader),
                    System.getProperty("line.separator"));
            while (tk.hasMoreTokens()) {
                classlist.add((String) tk.nextElement());
            }
        }

        for (Iterator iter = jars.iterator(); iter.hasNext();) {
            File element = (File) iter.next();
            jarFile = new JarFile(element);
            try {
                reader = new BufferedReader(new InputStreamReader(jarFile
                        .getInputStream(jarFile.getJarEntry(cFile))));
                StringTokenizer tk = new StringTokenizer(
                        parseConfigFile(reader), System
                                .getProperty("line.separator"));
                while (tk.hasMoreTokens()) {
                    classlist.add((String) tk.nextElement());
                }
            } catch (NullPointerException e) {
                // Ignore jar files without a
                // META-INF/services/javax.naming.ldap.StartTlsResponse file
            }
        }
    }

    /**
     * Parses the content of a configuration file (plain text)
     * 
     * @param reader
     *            a BufferedReader with the contents of the text file
     * @return a string with the clases separated by the "line.separator"
     *         character
     */
    private String parseConfigFile(BufferedReader reader) {
        StringBuffer sb = new StringBuffer();
        String line = null;
        String[] l = null;
        try {
            while ((line = reader.readLine()) != null) {
                l = line.trim().split("#");
                if (!(l[0].equals(""))) {
                    sb.append(l[0].trim());
                    sb.append(System.getProperty("line.separator"));
                }
            }
            reader.close();
        } catch (IOException e) {
        }
        return sb.toString();
    }
}
