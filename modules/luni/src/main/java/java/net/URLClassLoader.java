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

package java.net;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilePermission;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.CodeSource;
import java.security.PermissionCollection;
import java.security.PrivilegedAction;
import java.security.SecureClassLoader;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import org.apache.harmony.luni.util.InvalidJarIndexException;
import org.apache.harmony.luni.util.Msg;

/**
 * This class loader is responsible for loading classes and resources from a
 * list of URLs which can refer to either directories or JAR files. Classes
 * loaded by this <code>URLClassLoader</code> are granted permission to access
 * the URLs contained in the URL search list.
 */
public class URLClassLoader extends SecureClassLoader {

    ArrayList<URL> originalUrls;

    List<URL> searchList;
    ArrayList<URLHandler> handlerList;
    Map<URL, URLHandler> handlerMap = new HashMap<URL, URLHandler>();

    private URLStreamHandlerFactory factory;

    private AccessControlContext currentContext;

    static class SubURLClassLoader extends URLClassLoader {
        // The subclass that overwrites the loadClass() method
        private boolean checkingPackageAccess = false;

        SubURLClassLoader(URL[] urls) {
            super(urls, ClassLoader.getSystemClassLoader());
        }

        SubURLClassLoader(URL[] urls, ClassLoader parent) {
            super(urls, parent);
        }

        /**
         * Overrides the loadClass() of <code>ClassLoader</code>. It calls
         * the security manager's <code>checkPackageAccess()</code> before
         * attempting to load the class.
         *
         * @param className    String the name of the class to search for.
         * @param resolveClass boolean indicates if class should be resolved after
         *                     loading.
         * @return the Class object.
         * @throws ClassNotFoundException If the class could not be found.
         */
        @Override
        protected synchronized Class<?> loadClass(String className,
                                                  boolean resolveClass) throws ClassNotFoundException {
            SecurityManager sm = System.getSecurityManager();
            if (sm != null && !checkingPackageAccess) {
                int index = className.lastIndexOf('.');
                if (index > 0) { // skip if class is from a default package
                    try {
                        checkingPackageAccess = true;
                        sm.checkPackageAccess(className.substring(0, index));
                    } finally {
                        checkingPackageAccess = false;
                    }
                }
            }
            return super.loadClass(className, resolveClass);
        }
    }

    static class IndexFile {

        private HashMap<String, ArrayList<URL>> map;
        //private URLClassLoader host;


        static IndexFile readIndexFile(JarFile jf, JarEntry indexEntry, URL url) {
            BufferedReader in = null;
            InputStream is = null;
            try {
                // Add mappings from resource to jar file
                String parentURLString = getParentURL(url).toExternalForm();
                String prefix = "jar:" //$NON-NLS-1$
                        + parentURLString + "/"; //$NON-NLS-1$
                is = jf.getInputStream(indexEntry);
                in = new BufferedReader(new InputStreamReader(is, "UTF8"));
                HashMap<String, ArrayList<URL>> pre_map = new HashMap<String, ArrayList<URL>>();
                // Ignore the 2 first lines (index version)
                if (in.readLine() == null) return null;
                if (in.readLine() == null) return null;
                TOP_CYCLE:
                while (true) {
                    String line = in.readLine();
                    if (line == null) {
                        break;
                    }
                    URL jar = new URL(prefix + line + "!/"); //$NON-NLS-1$
                    while (true) {
                        line = in.readLine();
                        if (line == null) {
                            break TOP_CYCLE;
                        }
                        if ("".equals(line)) {
                            break;
                        }
                        ArrayList<URL> list;
                        if (pre_map.containsKey(line)) {
                            list = pre_map.get(line);
                        } else {
                            list = new ArrayList<URL>();
                            pre_map.put(line, list);
                        }
                        list.add(jar);
                    }
                }
                if (!pre_map.isEmpty()) {
                    return new IndexFile(pre_map);
                }
            } catch (MalformedURLException e) {
                // Ignore this jar's index
            } catch (IOException e) {
                // Ignore this jar's index
            }
            finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                    }
                }
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                    }
                }
            }
            return null;
        }

        private static URL getParentURL(URL url) throws IOException {
            URL fileURL = ((JarURLConnection) url.openConnection()).getJarFileURL();
            String file = fileURL.getFile();
            String parentFile = new File(file).getParent();
            parentFile = parentFile.replace(File.separatorChar, '/');
            if (parentFile.charAt(0) != '/') {
                parentFile = "/" + parentFile; //$NON-NLS-1$
            }
            URL parentURL = new URL(fileURL.getProtocol(), fileURL
                    .getHost(), fileURL.getPort(), parentFile);
            return parentURL;
        }

        public IndexFile(HashMap<String, ArrayList<URL>> map) {
            this.map = map;
        }

        ArrayList<URL> get(String name) {
            return map.get(name);
        }
    }

    class URLHandler {
        URL url;
        URL codeSourceUrl;

        public URLHandler(URL url) {
            this.url = url;
            this.codeSourceUrl = url;
        }

        void findResources(String name, ArrayList<URL> resources) {
            URL res = findResource(name);
            if (res != null && !resources.contains(res)) {
                resources.add(res);
            }
        }

        Class<?> findClass(String packageName, String name, String origName) {
            URL resURL = targetURL(url, name);
            if (resURL != null) {
                try {
                    InputStream is = resURL.openStream();
                    return createClass(is, packageName, origName);
                } catch (IOException e) {
                }
            }
            return null;
        }


        Class<?> createClass(InputStream is, String packageName, String origName) {
            if (is == null) {
                return null;
            }
            byte[] clBuf = null;
            try {
                clBuf = getBytes(is);
            } catch (IOException e) {
                return null;
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
            if (packageName != null) {
                String packageDotName = packageName.replace('/', '.');
                Package packageObj = getPackage(packageDotName);
                if (packageObj == null) {
                    definePackage(packageDotName, null, null,
                            null, null, null, null, null);
                } else {
                    if (packageObj.isSealed()) {
                        throw new SecurityException(Msg
                                .getString("K004c")); //$NON-NLS-1$
                    }
                }
            }
            return defineClass(origName, clBuf, 0, clBuf.length, new CodeSource(codeSourceUrl, (Certificate[]) null));
        }

        URL findResource(String name) {
            URL resURL = targetURL(url, name);
            if (resURL != null) {
                try {
                    URLConnection uc = resURL.openConnection();
                    uc.getInputStream().close();
                    // HTTP can return a stream on a non-existent file
                    // So check for the return code;
                    if (!resURL.getProtocol().equals("http")) { //$NON-NLS-1$
                        return resURL;
                    }
                    int code;
                    if ((code = ((HttpURLConnection) uc).getResponseCode()) >= 200
                            && code < 300) {
                        return resURL;
                    }
                } catch (SecurityException e) {
                    return null;
                } catch (IOException e) {
                    return null;
                }
            }
            return null;
        }

        URL targetURL(URL base, String name) {
            try {
                String file = base.getFile() + URIEncoderDecoder.quoteIllegal(name,
                        "/@" + URI.someLegal);

                return new URL(base.getProtocol(), base.getHost(), base.getPort(),
                        file, null);
            } catch (UnsupportedEncodingException e) {
                return null;
            } catch (MalformedURLException e) {
                return null;
            }
        }

    }

    class URLJarHandler extends URLHandler {
        JarFile jf;
        String prefixName;
        IndexFile index = null;
        Map<URL, URLHandler> subHandlers = new HashMap<URL, URLHandler>();

        public URLJarHandler(URL url, URL jarURL, JarFile jf, String prefixName) {
            super(url);
            this.jf = jf;
            this.prefixName = prefixName;
            this.codeSourceUrl = jarURL;
            JarEntry je = jf.getJarEntry("META-INF/INDEX.LIST"); //$NON-NLS-1$
            if (je != null) {
                index = IndexFile.readIndexFile(jf, je, url);
            }
        }

        public URLJarHandler(URL url, URL jarURL, JarFile jf, String prefixName, IndexFile index) {
            super(url);
            this.jf = jf;
            this.prefixName = prefixName;
            this.index = index;
            this.codeSourceUrl = jarURL;
        }

        IndexFile getIndex() {
            return index;
        }

        void findResources(String name, ArrayList<URL> resources) {
            URL res = findResourceInOwn(name);
            if (res != null && !resources.contains(res)) {
                resources.add(res);
            }
            if (index != null) {
                int pos = name.lastIndexOf("/"); //$NON-NLS-1$
                // only keep the directory part of the resource
                // as index.list only keeps track of directories and root files
                String indexedName = (pos > 0) ? name.substring(0, pos) : name;
                ArrayList<URL> urls = index.get(indexedName);
                if (urls != null) {
                    for (URL url : urls) {
                        URLHandler h = getSubHandler(url);
                        if (h != null) {
                            h.findResources(name, resources);
                        }
                    }
                }
            }

        }

        Class<?> findClass(String packageName, String name, String origName) {
            String entryName = prefixName + name;
            JarEntry entry = jf.getJarEntry(entryName);
            if (entry != null) {
                /**
                 * Avoid recursive load class, especially the class
                 * is an implementation class of security provider
                 * and the jar is signed.
                 */
                try {
                    Manifest manifest = jf.getManifest();
                    return createClass(entry, manifest, packageName, origName);
                } catch (IOException e) {
                }
            }
            if (index != null) {
                ArrayList<URL> urls;
                if (packageName == null) {
                    urls = index.get(name);
                } else {
                    urls = index.get(packageName);
                }
                if (urls != null) {
                    for (URL url : urls) {
                        URLHandler h = getSubHandler(url);
                        if (h != null) {
                            Class<?> res = h.findClass(packageName, name, origName);
                            if (res != null) {
                                return res;
                            }
                        }
                    }
                }
            }
            return null;
        }

        private Class<?> createClass(JarEntry entry, Manifest manifest, String packageName, String origName) {
            InputStream is = null;
            byte[] clBuf = null;
            try {
                is = jf.getInputStream(entry);
                clBuf = getBytes(is);
            } catch (IOException e) {
                return null;
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                    }
                }
            }
            if (packageName != null) {
                String packageDotName = packageName.replace('/', '.');
                Package packageObj = getPackage(packageDotName);
                if (packageObj == null) {
                    if (manifest != null) {
                        definePackage(packageDotName, manifest,
                                codeSourceUrl);
                    } else {
                        definePackage(packageDotName, null, null,
                                null, null, null, null, null);
                    }
                } else {
                    boolean exception = false;
                    if (manifest != null) {
                        if (isSealed(manifest, packageName + "/")) {
                            exception = !packageObj
                                    .isSealed(codeSourceUrl);
                        }
                    } else {
                        exception = packageObj.isSealed();
                    }
                    if (exception) {
                        throw new SecurityException(Msg
                                .getString("K004c")); //$NON-NLS-1$
                    }
                }
            }
            CodeSource codeS = new CodeSource(codeSourceUrl, entry.getCertificates());
            return defineClass(origName, clBuf, 0, clBuf.length, codeS);
        }

        URL findResourceInOwn(String name) {
            String entryName = prefixName + name;
            if (jf.getEntry(entryName) != null) {
                return targetURL(url, name);
            }
            return null;
        }

        URL findResource(String name) {
            URL res = findResourceInOwn(name);
            if (res != null) {
                return res;
            }
            if (index != null) {
                int pos = name.lastIndexOf("/"); //$NON-NLS-1$
                // only keep the directory part of the resource
                // as index.list only keeps track of directories and root files
                String indexedName = (pos > 0) ? name.substring(0, pos) : name;
                ArrayList<URL> urls = index.get(indexedName);
                if (urls != null) {
                    for (URL url : urls) {
                        URLHandler h = getSubHandler(url);
                        if (h != null) {
                            res = h.findResource(name);
                            if (res != null) {
                                return res;
                            }
                        }
                    }
                }
            }
            return null;
        }

        private synchronized URLHandler getSubHandler(URL url) {
            URLHandler sub = subHandlers.get(url);
            if (url != null) {
                return sub;
            }
            String protocol = url.getProtocol();
            if (protocol.equals("jar")) { //$NON-NLS-1$
                sub = createURLJarHandler(url);
            } else if (protocol.equals("file")) { //$NON-NLS-1$
                sub = createURLSubJarHandler(url);
            } else {
                sub = createURLHandler(url);
            }
            if (sub != null) {
                subHandlers.put(url, sub);
            }
            return sub;
        }

        private URLHandler createURLSubJarHandler(URL url) {
            String prefixName;
            String file = url.getFile();
            if (url.getFile().endsWith("!/")) { //$NON-NLS-1$
                prefixName = "";
            } else {
                int sepIdx = file.lastIndexOf("!/"); //$NON-NLS-1$
                if (sepIdx == -1) {
                    // Invalid URL, don't look here again
                    return null;
                }
                sepIdx += 2;
                prefixName = file.substring(sepIdx);
            }
            try {
                URL jarURL = ((JarURLConnection) url
                        .openConnection()).getJarFileURL();
                JarURLConnection juc = (JarURLConnection) new URL(
                        "jar", "", //$NON-NLS-1$ //$NON-NLS-2$
                        jarURL.toExternalForm() + "!/").openConnection(); //$NON-NLS-1$
                JarFile jf = juc.getJarFile();
                URLJarHandler jarH = new URLJarHandler(url, jarURL, jf, prefixName, null);
                // TODO : to think what we should do with indexes & manifest.class file here
                return jarH;
            } catch (IOException e) {
            }
            return null;
        }

    }

    class URLFileHandler extends URLHandler {
        private String prefix;

        public URLFileHandler(URL url) {
            super(url);
            String baseFile = url.getFile();
            String host = url.getHost();
            int hostLength = 0;
            if (host != null) {
                hostLength = host.length();
            }
            StringBuilder buf = new StringBuilder(2 + hostLength
                    + baseFile.length());
            if (hostLength > 0) {
                buf.append("//").append(host); //$NON-NLS-1$
            }
            // baseFile always ends with '/'
            buf.append(baseFile);
            prefix = buf.toString();
        }

        Class<?> findClass(String packageName, String name, String origName) {
            String filename = prefix + name;
            try {
                filename = URLDecoder.decode(filename, "UTF-8"); //$NON-NLS-1$
            } catch (IllegalArgumentException e) {
                return null;
            } catch (UnsupportedEncodingException e) {
                return null;
            }

            File file = new File(filename);
            if (file.exists()) {
                try {
                    InputStream is = new FileInputStream(file);
                    return createClass(is, packageName, origName);
                } catch (FileNotFoundException e) {
                }
            }
            return null;
        }

        URL findResource(String name) {
            int idx = 0;
            String filename;

            // Do not create a UNC path, i.e. \\host
            while (idx < name.length() && ((name.charAt(idx) == '/') //$NON-NLS-1$
                    || (name.charAt(idx) == '\\'))) { //$NON-NLS-1$
                idx++;
            }

            if (idx > 0) {
                name = name.substring(idx);
            }

            try {
                filename = URLDecoder.decode(prefix, "UTF-8") + name; //$NON-NLS-1$

                if (new File(filename).exists()) {
                    return targetURL(url, name);
                } else {
                    return null;
                }
            } catch (IllegalArgumentException e) {
                return null;
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        }

    }


    /**
     * Constructs a new instance of this class. The newly created instance will
     * have the system ClassLoader as its parent. URLs that end with "/" are
     * assumed to be directories, otherwise they are assumed to be Jar files.
     * 
     * @param urls
     *            the URLs to search
     * 
     * @throws SecurityException
     *             if a security manager exists and its checkCreateClassLoader
     *             method doesn't allow creation of new ClassLoaders
     */
    public URLClassLoader(URL[] urls) {
        this(urls, ClassLoader.getSystemClassLoader(), null);
    }

    /**
     * Constructs a new instance of this class. The newly created instance will
     * have the specified ClassLoader as its parent. URLs that end with "/" are
     * assumed to be directories, otherwise they are assumed to be Jar files.
     * 
     * @param urls
     *            the URLs to search
     * 
     * @param parent
     *            the ClassLoader to assign as this loader's parent.
     * 
     * @throws SecurityException
     *             if a security manager exists and its checkCreateClassLoader
     *             method doesn't allow creation of new ClassLoaders
     */
    public URLClassLoader(URL[] urls, ClassLoader parent) {
        this(urls, parent, null);
    }

    /**
     * Adds the specified URL to the search list.
     *
     * @param url java.net.URL the new URL
     */
    protected void addURL(URL url) {
        try {
            originalUrls.add(url);
            searchList.add(createSearchURL(url));
        } catch (MalformedURLException e) {
        }
    }

    /**
     * Answers an enumeration of URLs that contain the specified resource.
     *
     * @param name java.lang.String the name of the requested resource
     * @return Enumeration the enumeration of URLs that contain the specified
     *         resource.
     * @throws IOException
     *             thrown if an IO Exception occurs while attempting to connect
     */
    @Override
    public Enumeration<URL> findResources(final String name) throws IOException {
        if (name == null) {
            return null;
        }
        ArrayList<URL> result = AccessController.doPrivileged(
                new PrivilegedAction<ArrayList<URL>>() {
                    public ArrayList<URL> run() {
                        ArrayList<URL> results = new ArrayList<URL>();
                        findResourcesImpl(name, results);
                        return results;
                    }
                }, currentContext);
        SecurityManager sm;
        int length = result.size();
        if (length > 0 && (sm = System.getSecurityManager()) != null) {
            ArrayList<URL> reduced = new ArrayList<URL>(length);
            for (int i = 0; i < length; i++) {
                URL url = result.get(i);
                try {
                    sm.checkPermission(url.openConnection().getPermission());
                    reduced.add(url);
                } catch (IOException e) {
                } catch (SecurityException e) {
                }
            }
            result = reduced;
        }
        return Collections.enumeration(result);
    }

    void findResourcesImpl(String name, ArrayList<URL> result) {
        int n = 0;
        while (true) {
            URLHandler handler = getHandler(n++);
            if (handler == null) {
                break;
            }
            handler.findResources(name, result);
        }
    }


    /**
     * Converts an input stream into a byte array.
     *
     * @param is the input stream
     * @return byte[] the byte array
     */
    private static byte[] getBytes(InputStream is)
            throws IOException {
        byte[] buf = new byte[4096];
        ByteArrayOutputStream bos = new ByteArrayOutputStream(4096);
        int count;
        while ((count = is.read(buf)) > 0) {
            bos.write(buf, 0, count);
        }
        return bos.toByteArray();
    }

    /**
     * Answers the permissions for the given code source. First this method
     * retrieves the permissions from the system policy. If the protocol is
     * "file:/" then a new permission, <code>FilePermission</code>, granting
     * the read permission to the file is added to the permission collection.
     * Otherwise, connecting to and accepting connections from the URL is
     * granted.
     *
     * @param codesource CodeSource
     * @return PermissionCollection
     */
    @Override
    protected PermissionCollection getPermissions(final CodeSource codesource) {
        PermissionCollection pc = super.getPermissions(codesource);
        URL u = codesource.getLocation();
        if (u.getProtocol().equals("jar")) { //$NON-NLS-1$
            try {
                // Create a URL for the resource the jar refers to
                u = ((JarURLConnection) u.openConnection()).getJarFileURL();
            } catch (IOException e) {
                // This should never occur. If it does continue using the jar
                // URL
            }
        }
        if (u.getProtocol().equals("file")) { //$NON-NLS-1$
            String path = u.getFile();
            String host = u.getHost();
            if (host != null && host.length() > 0) {
                path = "//" + host + path; //$NON-NLS-1$
            }

            if (File.separatorChar != '/') {
                path = path.replace('/', File.separatorChar);
            }
            if (isDirectory(u)) {
                pc.add(new FilePermission(path + "-", "read")); //$NON-NLS-1$ //$NON-NLS-2$
            } else {
                pc.add(new FilePermission(path, "read")); //$NON-NLS-1$
            }
        } else {
            String host = u.getHost();
            if (host.length() == 0) {
                host = "localhost"; //$NON-NLS-1$
            }
            pc.add(new SocketPermission(host, "connect, accept")); //$NON-NLS-1$
        }
        return pc;
    }

    /**
     * Answers the search list of this URLClassLoader
     *
     * @return java.net.URL[]
     */
    public URL[] getURLs() {
        return originalUrls.toArray(new URL[originalUrls.size()]);
    }

    /**
     * Determines if the URL is pointing to a directory.
     *
     * @param url java.net.URL
     * @return boolean
     */
    private static boolean isDirectory(URL url) {
        String file = url.getFile();
        return (file.length() > 0 && file.charAt(file.length() - 1) == '/');
    }

    /**
     * Answers an instance of <code>URLClassLoader</code>.
     * <code>loadClass()</code> of the new instance will call the
     * SecurityManager's <code>checkPackageAccess()</code> before loading a
     * class.
     *
     * @param urls java.net.URL[] a list of URLs that is passed to the new
     *             URLClassloader
     * @return java.net.URLClassLoader the new instance of
     *         <code>URLClassLoader</code>
     */
    public static URLClassLoader newInstance(final URL[] urls) {
        URLClassLoader sub = AccessController
                .doPrivileged(new PrivilegedAction<URLClassLoader>() {
                    public URLClassLoader run() {
                        return new SubURLClassLoader(urls);
                    }
                });
        sub.currentContext = AccessController.getContext();
        return sub;
    }

    /**
     * Answers an instance of <code>URLClassLoader</code>.
     * <code>loadClass()</code> of the new instance will call security
     * manager's <code>checkPackageAccess()</code> before loading a class.
     *
     * @param urls     URL[] the list of URLs that is passed to the new
     *                 <code>URLClassloader</code>
     * @param parentCl ClassLoader the parent class loader that is passed to
                       the new <code>URLClassloader</code>
     * @return URLClassLoader the new instance of <code>URLClassLoader</code>
     */
    public static URLClassLoader newInstance(final URL[] urls,
                                             final ClassLoader parentCl) {
        URLClassLoader sub = AccessController
                .doPrivileged(new PrivilegedAction<URLClassLoader>() {
                    public URLClassLoader run() {
                        return new SubURLClassLoader(urls, parentCl);
                    }
                });
        sub.currentContext = AccessController.getContext();
        return sub;
    }

    /**
     * Constructs a new instance of this class. The newly created instance will
     * have the specified ClassLoader as its parent and use the specified
     * factory to create stream handlers. URLs that end with "/" are assumed to
     * be directories, otherwise they are assumed to be Jar files.
     * 
     * @param searchUrls
     *            java.net.URL[] the URLs that will be searched in the order
     *            they were specified for resource
     * 
     * @param parent
     *            ClassLoader the ClassLoader name of the resource to find.
     * 
     * @param factory
     *            java.net.URLStreamHandlerFactory the factory that will used to
     *            create stream (protocol) handlers
     * @throws SecurityException
     *             if a security manager exists and its checkCreateClassLoader
     *             method doesn't allow creation of new ClassLoaders
     */
    public URLClassLoader(URL[] searchUrls, ClassLoader parent,
                          URLStreamHandlerFactory factory) {
        super(parent);
        // Required for pre-v1.2 security managers to work
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkCreateClassLoader();
        }
        this.factory = factory;
        // capture the context of the thread that creates this URLClassLoader
        currentContext = AccessController.getContext();
        int nbUrls = searchUrls.length;
        originalUrls = new ArrayList<URL>(nbUrls);
        handlerList = new ArrayList<URLHandler>(nbUrls);
        searchList = Collections.synchronizedList(new ArrayList<URL>(nbUrls));
        for (int i = 0; i < nbUrls; i++) {
            originalUrls.add(searchUrls[i]);
            try {
                searchList.add(createSearchURL(searchUrls[i]));
            } catch (MalformedURLException e) {
            }
        }
    }

    /**
     * Locates and loads the specified class, searching this URLClassLoader's
     * list of URLS.
     *
     * @param clsName String the name of the class.
     * @return Class the class that has been loaded.
     * @throws java.lang.ClassNotFoundException
     *          if the class cannot be loaded
     */
    @Override
    protected Class<?> findClass(final String clsName)
            throws ClassNotFoundException {
        Class<?> cls = AccessController.doPrivileged(
                new PrivilegedAction<Class<?>>() {
                    public Class<?> run() {
                        return findClassImpl(clsName);
                    }
                }, currentContext);
        if (cls != null) {
            return cls;
        }
        throw new ClassNotFoundException(clsName);
    }

    /**
     * Answers an URL that will be checked if it contains the class or resource.
     * If the file component of the URL is not a directory, a Jar URL will be
     * created.
     *
     * @return java.net.URL a test URL
     */
    private URL createSearchURL(URL url) throws MalformedURLException {
        if (url == null) {
            return url;
        }

        String protocol = url.getProtocol();

        if (isDirectory(url) || protocol.equals("jar")) { //$NON-NLS-1$
            return url;
        }
        if (factory == null) {
            return new URL("jar", "", //$NON-NLS-1$ //$NON-NLS-2$
                    -1, url.toString() + "!/"); //$NON-NLS-1$
        }
        return new URL("jar", "", //$NON-NLS-1$ //$NON-NLS-2$
                -1, url.toString() + "!/", //$NON-NLS-1$
                factory.createURLStreamHandler(protocol));
    }

    /**
     * Answers a URL referencing the specified resource or null if no resource
     * could be found.
     *
     * @param name java.lang.String the name of the requested resource
     * @return URL URL for the resource.
     */
    @Override
    public URL findResource(final String name) {
        if (name == null) {
            return null;
        }
        URL result = AccessController.doPrivileged(new PrivilegedAction<URL>() {
            public URL run() {
                return findResourceImpl(name);
            }
        }, currentContext);
        SecurityManager sm;
        if (result != null && (sm = System.getSecurityManager()) != null) {
            try {
                sm.checkPermission(result.openConnection().getPermission());
            } catch (IOException e) {
                return null;
            } catch (SecurityException e) {
                return null;
            }
        }
        return result;
    }

    /**
     * Answers a URL among the given ones referencing the specified resource or
     * null if no resource could be found.
     *
     * @param resName java.lang.String the name of the requested resource
     * @return URL URL for the resource.
     */
    URL findResourceImpl(String resName) {
        int n = 0;

        while (true) {
            URLHandler handler = getHandler(n++);
            if (handler == null) {
                break;
            }
            URL res = handler.findResource(resName);
            if (res != null) {
                return res;
            }
        }
        return null;
    }

    URLHandler getHandler(int num) {
        if (num < handlerList.size()) {
            return handlerList.get(num);
        }
        makeNewHandler();
        if (num < handlerList.size()) {
            return handlerList.get(num);
        }
        return null;
    }

    private synchronized void makeNewHandler() {
        while (!searchList.isEmpty()) {
            URL nextCandidate = searchList.remove(0);
            if (nextCandidate == null) {  // KA024=One of urls is null
                throw new NullPointerException(Msg.getString("KA024")); //$NON-NLS-1$
            }
            if (!handlerMap.containsKey(nextCandidate)) {
                URLHandler result;
                String protocol = nextCandidate.getProtocol();
                if (protocol.equals("jar")) { //$NON-NLS-1$
                    result = createURLJarHandler(nextCandidate);
                } else if (protocol.equals("file")) { //$NON-NLS-1$
                    result = createURLFileHandler(nextCandidate);
                } else {
                    result = createURLHandler(nextCandidate);
                }
                if (result != null) {
                    handlerMap.put(nextCandidate, result);
                    handlerList.add(result);
                    return;
                }
            }
        }
    }

    private URLHandler createURLHandler(URL url) {
        return new URLHandler(url);
    }

    private URLHandler createURLFileHandler(URL url) {
        return new URLFileHandler(url);
    }

    private URLHandler createURLJarHandler(URL url) {
        String prefixName;
        String file = url.getFile();
        if (url.getFile().endsWith("!/")) { //$NON-NLS-1$
            prefixName = "";
        } else {
            int sepIdx = file.lastIndexOf("!/"); //$NON-NLS-1$
            if (sepIdx == -1) {
                // Invalid URL, don't look here again
                return null;
            }
            sepIdx += 2;
            prefixName = file.substring(sepIdx);
        }
        try {
            URL jarURL = ((JarURLConnection) url
                    .openConnection()).getJarFileURL();
            JarURLConnection juc = (JarURLConnection) new URL(
                    "jar", "", //$NON-NLS-1$ //$NON-NLS-2$
                    jarURL.toExternalForm() + "!/").openConnection(); //$NON-NLS-1$
            JarFile jf = juc.getJarFile();
            URLJarHandler jarH = new URLJarHandler(url, jarURL, jf, prefixName);
            if (jarH.getIndex() == null) {
                try {
                    Manifest manifest = jf.getManifest();
                    if (manifest != null) {
                        String classpath = manifest.getMainAttributes().getValue(
                                Attributes.Name.CLASS_PATH);
                        if (classpath != null) {
                            searchList.addAll(0, getInternalURLs(url, classpath));
                        }
                    }
                } catch (IOException e) {
                }
            }
            return jarH;
        } catch (IOException e) {
        }
        return null;
    }

    /**
     * Define a new Package using information extracted from the specified
     * Manifest.
     *
     * @param packageName The name of the package
     * @param manifest    The Manifest for the Package
     * @param url         The code source for the Package
     * @return The Package created
     * @throws IllegalArgumentException if the Package already exists
     */
    protected Package definePackage(String packageName, Manifest manifest,
                                    URL url) throws IllegalArgumentException {
        Attributes mainAttributes = manifest.getMainAttributes();
        String dirName = packageName.replace('.', '/') + "/"; //$NON-NLS-1$
        Attributes packageAttributes = manifest.getAttributes(dirName);
        boolean noEntry = false;
        if (packageAttributes == null) {
            noEntry = true;
            packageAttributes = mainAttributes;
        }
        String specificationTitle = packageAttributes
                .getValue(Attributes.Name.SPECIFICATION_TITLE);
        if (specificationTitle == null && !noEntry) {
            specificationTitle = mainAttributes
                    .getValue(Attributes.Name.SPECIFICATION_TITLE);
        }
        String specificationVersion = packageAttributes
                .getValue(Attributes.Name.SPECIFICATION_VERSION);
        if (specificationVersion == null && !noEntry) {
            specificationVersion = mainAttributes
                    .getValue(Attributes.Name.SPECIFICATION_VERSION);
        }
        String specificationVendor = packageAttributes
                .getValue(Attributes.Name.SPECIFICATION_VENDOR);
        if (specificationVendor == null && !noEntry) {
            specificationVendor = mainAttributes
                    .getValue(Attributes.Name.SPECIFICATION_VENDOR);
        }
        String implementationTitle = packageAttributes
                .getValue(Attributes.Name.IMPLEMENTATION_TITLE);
        if (implementationTitle == null && !noEntry) {
            implementationTitle = mainAttributes
                    .getValue(Attributes.Name.IMPLEMENTATION_TITLE);
        }
        String implementationVersion = packageAttributes
                .getValue(Attributes.Name.IMPLEMENTATION_VERSION);
        if (implementationVersion == null && !noEntry) {
            implementationVersion = mainAttributes
                    .getValue(Attributes.Name.IMPLEMENTATION_VERSION);
        }
        String implementationVendor = packageAttributes
                .getValue(Attributes.Name.IMPLEMENTATION_VENDOR);
        if (implementationVendor == null && !noEntry) {
            implementationVendor = mainAttributes
                    .getValue(Attributes.Name.IMPLEMENTATION_VENDOR);
        }

        return definePackage(packageName, specificationTitle,
                specificationVersion, specificationVendor, implementationTitle,
                implementationVersion, implementationVendor, isSealed(manifest,
                dirName) ? url : null);
    }

    private boolean isSealed(Manifest manifest, String dirName) {
        Attributes mainAttributes = manifest.getMainAttributes();
        String value = mainAttributes.getValue(Attributes.Name.SEALED);
        boolean sealed = value != null && value.toLowerCase().equals("true"); //$NON-NLS-1$
        Attributes attributes = manifest.getAttributes(dirName);
        if (attributes != null) {
            value = attributes.getValue(Attributes.Name.SEALED);
            if (value != null) {
                sealed = value.toLowerCase().equals("true"); //$NON-NLS-1$
            }
        }
        return sealed;
    }

    /**
     * returns URLs referenced in the string classpath.
     *
     * @param root      the jar URL that classpath is related to
     * @param classpath the relative URLs separated by spaces
     * @return URL[] the URLs contained in the string classpath.
     */
    private ArrayList<URL> getInternalURLs(URL root, String classpath) {
        // Class-path attribute is composed of space-separated values.
        StringTokenizer tokenizer = new java.util.StringTokenizer(classpath);
        ArrayList<URL> addedURLs = new ArrayList<URL>();
        String file = root.getFile();
        int jarIndex = file.lastIndexOf("!/") - 1; //$NON-NLS-1$
        int index = file.lastIndexOf("/", jarIndex) + 1; //$NON-NLS-1$
        if (index == 0) {
            index = file.lastIndexOf(
                    System.getProperty("file.separator"), jarIndex) + 1; //$NON-NLS-1$
        }
        file = file.substring(0, index);
        String protocol = root.getProtocol();
        String host = root.getHost();
        int port = root.getPort();
        while (tokenizer.hasMoreElements()) {
            String element = tokenizer.nextToken();
            if (!element.equals("")) { //$NON-NLS-1$
                try {
                    URL newURL = new URL(protocol, host, port, file + element
                            + "!/"); //$NON-NLS-1$
                    addedURLs.add(newURL);
                } catch (MalformedURLException e) {
                    // Nothing is added
                }
            }
        }
        return addedURLs;
    }

    Class<?> findClassImpl(String className) {
        Class loadedClass = findLoadedClass(className);
        if (null != loadedClass) {
            return loadedClass;
        }
        String partialName = className.replace('.', '/');
        final String classFileName = new StringBuilder(partialName).append(".class").toString(); //$NON-NLS-1$
        String packageName = null;
        int position = partialName.lastIndexOf('/');
        if ((position = partialName.lastIndexOf('/')) != -1) {
            packageName = partialName.substring(0, position);
        }
        int n = 0;
        while (true) {
            URLHandler handler = getHandler(n++);
            if (handler == null) {
                break;
            }
            Class<?> res = handler.findClass(packageName, classFileName, className);
            if (res != null) {
                return res;
            }
        }
        return null;

    }

}
