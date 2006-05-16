/* 
*  Copyright 2005 The Apache Software Foundation or its licensors, as applicable. 
* 
*  Licensed under the Apache License, Version 2.0 (the "License"); 
*  you may not use this file except in compliance with the License. 
*  You may obtain a copy of the License at 
* 
*    http://www.apache.org/licenses/LICENSE-2.0 
* 
*  Unless required by applicable law or agreed to in writing, software 
*  distributed under the License is distributed on an "AS IS" BASIS, 
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
*  See the License for the specific language governing permissions and 
*  limitations under the License. 
*/
package java.rmi.server;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ClassLoader;
import java.rmi.server.RMIClassLoaderSpi;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.net.MalformedURLException;
import java.net.URL;

import ar.org.fitc.rmi.runtime.RMIDefaultClassLoaderSpi;
import ar.org.fitc.rmi.utils.PropertiesReader;

/**
 * @ar.org.fitc.spec_ref
 * 
 */
public class RMIClassLoader {

    static private RMIClassLoaderSpi provider = null;

    /*
     * This initialization is specified in the ClassLoader API, though it is an
     * awful design.
     */

    static {
        String property = 
            PropertiesReader.readString("java.rmi.server.RMIClassLoaderSpi");
        Class clazz;

        if (property != null) {
            if (property.equals("default")) {
                provider = getDefaultProviderInstance();
            } else {
                try {
                    clazz = ClassLoader.getSystemClassLoader().loadClass(
                            property);
                    if (RMIClassLoaderSpi.class.isAssignableFrom(clazz)) {
                        provider = (RMIClassLoaderSpi) clazz.newInstance();
                    } else {
                        throw new Error("RMIClassLoader Initialization Failed");
                    }
                } catch (ClassNotFoundException e) {
                    throw new Error("RMIClassLoader Initialization Failed");
                } catch (InstantiationException e) {
                    throw new Error("RMIClassLoader Initialization Failed");
                } catch (IllegalAccessException e) {
                    throw new Error("RMIClassLoader Initialization Failed");
                }
            }
        } else {
            InputStream in = ClassLoader
                    .getSystemResourceAsStream("META-INF/services/java.rmi.server.RMIClassLoaderSpi");
            String providerName = null;

            if (in != null) {
                Scanner scanner = new Scanner(in);
                Pattern pat = Pattern.compile("\\b[\\w\\.]*\\b",
                        Pattern.COMMENTS);
                try {
                    while ((in.available() > 0) && provider == null) {
                        if (scanner.hasNext(pat)) {
                            providerName = scanner.next(pat);
                            break;
                        } else if (in.available() > 0) {
                            scanner.nextLine();
                        }
                    }
                } catch (IOException e) {
                    throw new Error("RMIClassLoader Initialization Failed");
                }
            }
            if (providerName != null) {
                try {
                    clazz = ClassLoader.getSystemClassLoader().loadClass(
                            providerName);
                } catch (Exception e) {
                    throw new Error(
                            "RMIClassLoader Initialization Failed from a RMIClassLoaderSpi configuration file");
                }
                if (RMIClassLoaderSpi.class.isAssignableFrom(clazz)) {
                    try {
                        provider = (RMIClassLoaderSpi) clazz.newInstance();
                    } catch (Exception e) {
                        throw new Error(
                                "RMIClassLoader Initialization Failed from a RMIClassLoaderSpi configuration file");
                    }
                } else {
                    throw new Error("RMIClassLoader Initialization Failed");
                }
            } else {
                provider = getDefaultProviderInstance();
            }
        }
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static String getClassAnnotation(Class<?> cl)
            throws NullPointerException {

        return provider.getClassAnnotation(cl);
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static ClassLoader getClassLoader(String codebase)
            throws MalformedURLException, SecurityException {

        return provider.getClassLoader(codebase);
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static RMIClassLoaderSpi getDefaultProviderInstance() {

        if (System.getSecurityManager() != null) {
            System.getSecurityManager().checkPermission(
                    new RuntimePermission("setFactory"));
        }
        if (provider == null) {
            provider = new RMIDefaultClassLoaderSpi();
        }
        return provider;
    }

    @Deprecated
    public static Object getSecurityContext(@SuppressWarnings("unused") ClassLoader loader) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public static Class<?> loadClass(String name) throws MalformedURLException,
            ClassNotFoundException {
        return loadClass((String) null, name);
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static Class<?> loadClass(String codebase, String name)
            throws MalformedURLException, ClassNotFoundException {
        return provider.loadClass(codebase, name, null);
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static Class<?> loadClass(String codebase, String name,
            ClassLoader defaultLoader) throws MalformedURLException,
            ClassNotFoundException {

        return provider.loadClass(codebase, name, defaultLoader);
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static Class<?> loadClass(URL codebase, String name)
            throws MalformedURLException, ClassNotFoundException {
        String codebaseStr =  codebase == null ? null : codebase.toString();
        
        return provider.loadClass(codebaseStr, name, null);
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    public static Class<?> loadProxyClass(String codebase, String[] interfaces,
            ClassLoader defaultLoader) throws MalformedURLException,
            ClassNotFoundException, IllegalArgumentException {

        return provider.loadProxyClass(codebase, interfaces, defaultLoader);
    }
}
