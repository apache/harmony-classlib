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
package ar.org.fitc.rmi.runtime;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.rmi.server.RMIClassLoader;
import java.rmi.server.RMIClassLoaderSpi;
import java.util.Arrays;
import java.util.HashMap;

import ar.org.fitc.rmi.utils.Pair;
import ar.org.fitc.rmi.utils.PropertiesReader;

/**
 * This is the default RMI implementation of the
 * {@link java.rmi.server.RMIClassLoaderSpi} interface. The implementation of
 * these method it is specified in the
 * {@link RMIClassLoader}
 * API.
 * 
 * @author Gustavo Petri
 * 
 */
public class RMIDefaultClassLoaderSpi extends RMIClassLoaderSpi {

    /**
     * A <code>String</code> that serves as a cache fot the codebaseProp
     * property.
     */
    private String codebaseProp;

    /**
     * A flag that represents the unexistence of a <code>SecurityManager</code>.
     */
    private boolean noSecurityManager;

    /**
     * The actual <code>SecurityManager</code> instance of the class.
     */
    private SecurityManager securityManager;

    /**
     * This is a mapping between pairs of sorted codebase Strings and
     * <code>ClassLoader</code> instances to <code>WeakReferences</code> to
     * the <code>URLClassLoader</code> cached in this table.
     */
    private HashMap<Pair<String, ClassLoader>, WeakReference<URLClassLoader>> classLoaderMap;

    /**
     * Constructs a default {@link java.rmi.server.RMIClassLoaderSpi}
     * instance and it initializes the private variables.
     */
    public RMIDefaultClassLoaderSpi() {

        super();
        codebaseProp = PropertiesReader.readString("java.rmi.server.codebase");
        securityManager = System.getSecurityManager();
        if (securityManager == null) {
            noSecurityManager = true;
        } else {
            noSecurityManager = false;
        }
        classLoaderMap = 
            new HashMap<Pair<String, ClassLoader>, WeakReference<URLClassLoader>>();
    }

    @Override
    /**
     * Should construct a Proxy Class that implements all the interfaces passed
     * as parameter.
     * 
     * @param codebase
     *            a <code>String</code> that represent the specified directory
     * @param interfaces
     *            a array of <code>Strings</code> interfaces
     * @param defaultLoader
     *            responsible for loading classes
     * @return a Proxy Class that implements all the interfaces
     * @throws MalformedURLException
     *             no legal protocol could be found in a specification string
     * @throws ClassNotFoundException
     *             if there is no Classloader for the specified interfaces
     */
    public final Class<?> loadProxyClass(String codebase, String[] interfaces,
            ClassLoader defaultLoader) throws MalformedURLException,
            ClassNotFoundException {
        Class<?>[] interfaceClasses = new Class[interfaces.length];
        ClassLoader notPublicClassloader = null;

        for (int i = 0; i < interfaces.length; i++) {
            interfaceClasses[i] = loadClass(codebase, interfaces[i],
                    defaultLoader);
            int modifier = interfaceClasses[i].getModifiers();
            if (!Modifier.isPublic(modifier)) {
                if (notPublicClassloader == null) {
                    notPublicClassloader = interfaceClasses[i].getClassLoader();
                } else if (!notPublicClassloader.equals(interfaceClasses[i]
                        .getClassLoader())) {
                    throw new LinkageError(
                            "There is no Classloader for the specified interfaces");
                }
            }
        }
        if (notPublicClassloader != null) {
            try {
                return Proxy.getProxyClass(notPublicClassloader,
                        interfaceClasses);
            } catch (Exception e) {
                throw new LinkageError(
                        "There is no Classloader for the specified interfaces");
            }
        } 
        try {
            ClassLoader cl = getClassLoader(codebase);
            return Proxy.getProxyClass(cl, interfaceClasses);
        } catch (IllegalArgumentException e) {
            try {
                return Proxy.getProxyClass(defaultLoader, interfaceClasses);
            } catch (IllegalArgumentException e1) {
                throw new ClassNotFoundException(
                        "There is no Classloader for the specified interfaces",
                        e);
            }
        }
    }

    @Override
    /**
     * Specified in the
     * {@link java.rmi.server.RMIClassLoader#getDefaultProviderInstance} Method.
     * Returns the
     * {@link <a href="http://java.sun.com/j2se/1.5.0/docs/api/java/lang/ClassLoader.html"><code>ClassLoader</code></a>}
     * which has loaded the Class.
     * 
     * @param cl
     *            the specified Class
     * @return the
     *         {@link <a href="http://java.sun.com/j2se/1.5.0/docs/api/java/lang/ClassLoader.html"><code>ClassLoader</code></a>}
     *         which has loaded the Class.
     */
    public final String getClassAnnotation(Class cl) {

        java.lang.ClassLoader classLoader = cl.getClassLoader();

        String codebase = 
            PropertiesReader.readString("java.rmi.server.codebase");

        if (classLoader == null) {
            return codebase;
        }
        java.lang.ClassLoader cLoader = classLoader;
        while (cLoader != null) {
            if (ClassLoader.getSystemClassLoader().equals(classLoader)) {
                return codebase;
            }
            if (cl != null) {
                cLoader = cLoader.getParent();
            }
        }

        if (classLoader instanceof URLClassLoader
                && !ClassLoader.getSystemClassLoader().equals(classLoader)) {
            try {
                URL urls[] = ((URLClassLoader) classLoader).getURLs();
                String ret = null;

                /*
                 * FIXME HARD: Check Permissions: If the URLClassLoader was
                 * created by this provider to service an invocation of its
                 * loadClass or loadProxyClass methods, then no permissions are
                 * required to get the associated codebase string. If it is an
                 * arbitrary other URLClassLoader instance, then if there is a
                 * security manager, its checkPermission method will be invoked
                 * once for each URL returned by the getURLs method, with the
                 * permission returned by invoking
                 * openConnection().getPermission() on each URL
                 */

                if (urls != null) {
                    for (URL url : urls) {
                        if (ret == null) {
                            ret = new String(url.toExternalForm());
                        } else {
                            ret += " " + url.toExternalForm();
                        }
                    }
                }

                return ret;
            } catch (SecurityException e) {
                return codebase;
            }
        } 
        return codebase;
    }

    @Override
    /**
     * Specified in the {@link java.rmi.server.RMIClassLoader#loadClass(String)}
     * Method. It loads the class directly.
     * 
     * @param codebase
     *            a <code>String</code> that represent the specified directory
     * @param name
     *            of the class.
     * @param defaultLoader
     *            responsible for loading classes
     * @return a Proxy Class
     * @throws MalformedURLException
     *             no legal protocol could be found in a specification string
     * @throws ClassNotFoundException
     *             if there is no Classloader for the specified interfaces
     */
    public final Class<?> loadClass(String codebase, String name,
            ClassLoader defaultLoader) throws MalformedURLException,
            ClassNotFoundException {
        Class<?> ret;

        if (defaultLoader != null) {
            try {
                ret = Class.forName(name, false, defaultLoader);
            } catch (ClassNotFoundException e) {
                try {
                    ret = Class.forName(name, false, getClassLoader(codebase));
                } catch (SecurityException e1) {
                    ret = Class.forName(name, false, Thread.currentThread()
                            .getContextClassLoader());
                }
            }
        } else {
            try {
                ret = Class.forName(name, false, getClassLoader(codebase));
            } catch (SecurityException e1) {
                ret = Class.forName(name, false, Thread.currentThread()
                        .getContextClassLoader());
            }
        }
        return ret;
    }

    @Override
    /**
     * Specified in the
     * {@link java.rmi.server.RMIClassLoader#getClassLoader(String)} Method.
     * Returns the corresponding
     * {@link <a href="http://java.sun.com/j2se/1.5.0/docs/api/java/lang/ClassLoader.html"><code>ClassLoader</code></a>}
     * to the codebase
     * 
     * @param codebase
     *            a <code>String</code> that represent the specified directory
     * @return the corresponding
     *         {@link <a href="http://java.sun.com/j2se/1.5.0/docs/api/java/lang/ClassLoader.html"><code>ClassLoader</code></a>}
     * @throws MalformedURLException
     *             no legal protocol could be found in a specification string
     */
    public final ClassLoader getClassLoader(String codebase)
            throws MalformedURLException {

        if (!noSecurityManager) {
            securityManager.checkPermission(
                    new RuntimePermission("getClassLoader"));
        } else {
            return Thread.currentThread().getContextClassLoader();
        }
        return getClassLoaderFromCodebase(codebase);
    }

    /**
     * Specified in the {@link java.rmi.server.RMIClassLoader}.
     * 
     * @param codebase
     *            a <code>String</code> that represent the specified directory
     * @return the corresponding
     *         {@link ClassLoader}
     * @throws MalformedURLException
     *             no legal protocol could be found in a specification string
     */
    private final ClassLoader getClassLoaderFromCodebase(String codebase)
            throws MalformedURLException {
        ClassLoader classLoader = null;
        
        String tempCodebase = (codebase == null) ? codebaseProp : codebase;
        /*
         * The API Specification always assumes that the property
         * java.rmi.server.codebase is correctly setted, and it does not specify
         * what to do when returns null. In this case we always return
         * Thread.currentThread().getContextClassLoader();
         */

        if (tempCodebase == null) {
            return Thread.currentThread().getContextClassLoader();
        }
        tempCodebase = sortURLs(tempCodebase);
        Pair<String, ClassLoader> key = new Pair<String, ClassLoader>(
                tempCodebase, Thread.currentThread().getContextClassLoader());
        if (classLoaderMap.containsKey(key)) {
            classLoader = classLoaderMap.get(key).get();
        }
        if (classLoader == null) {
            URL[] urls = getURLs(codebase);
            if (urls == null) {
                return null;
            }
            for (URL url : urls) {
                try {
                    securityManager.checkPermission(url.openConnection()
                            .getPermission());
                } catch (IOException e) {
                    throw new SecurityException(e);
                }
            }
            classLoader = new URLClassLoader(urls);
            classLoaderMap.put(key, new WeakReference<URLClassLoader>(
                    (URLClassLoader) classLoader));
        }
        return classLoader;
    }

    /**
     * Takes a <EM>space separated</EM> list of URL's as a String, and sorts
     * it. For that purpose the String must be parsed.
     * 
     * @param urls
     *            a list of URL's
     * @return an alphabetic order list of URL's
     */
    private final static String sortURLs(String urls) {
        String ret = null;

        if (urls != null) {
            String[] codebaseSplit = urls.split("( )+");
            if (codebaseSplit.length > 0) {
                ret = new String();
                Arrays.sort(codebaseSplit);
                for (String url : codebaseSplit) {
                    ret += url + " ";
                }
            }
        }
        return ret;
    }

    /**
     * Takes a <EM>space separated</EM> list of URL's as a <code>String</code>,
     * and returns an array of URL's.
     * 
     * @param urls
     *            a list of URL's as a <code>String</code>
     * @return an array of URL's
     * @throws MalformedURLException
     *             no legal protocol could be found in a specification string
     */
    private final static URL[] getURLs(String urls) throws MalformedURLException {
        URL[] realURLs = null;

        if (urls == null) {
            return realURLs;
        }
        String[] codebaseSplit = urls.split("( )+");
        if (codebaseSplit.length > 0) {
            realURLs = new URL[codebaseSplit.length];
            for (int i = 0; i < codebaseSplit.length; i++) {
                realURLs[i] = new URL(codebaseSplit[i]);
            }
        }
        return realURLs;
    }
}
