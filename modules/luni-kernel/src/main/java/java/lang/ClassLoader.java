/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java.lang;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.security.ProtectionDomain;
import java.util.Enumeration;

/**
 * <p>
 * A ClassLoader is used for loading classes.
 * </p>
 * 
 * <h4>VM Implementors Note</h4>
 * <p>
 * This class must be implemented by the VM. The documented methods and natives
 * must be implemented to support other provided class implementations in this
 * package.
 * </p>
 * 
 * @since 1.0
 * @see Class
 */
public abstract class ClassLoader {

    /**
     * The 'System' ClassLoader; also known as the bootstrap ClassLoader.
     * 
     * @see #getSystemClassLoader()
     */
    static ClassLoader systemClassLoader;

    /**
     * <p>
     * TODO Document this method.
     * </p>
     */
    static final void initializeClassLoaders() {
        return;
    }

    /**
     * Returns the system class loader. This is the parent for new ClassLoader
     * instances, and is typically the class loader used to start the
     * application. If a security manager is present, and the caller's class
     * loader is not null and the caller's class loader is not the same as or an
     * ancestor of the system class loader, then this method calls the security
     * manager's checkPermission method with a
     * RuntimePermission("getClassLoader") permission to ensure it's ok to
     * access the system class loader. If not, a SecurityException will be
     * thrown.
     * 
     * @return The system classLoader.
     * @throws SecurityException
     *             if a security manager exists and it does not allow access to
     *             the system class loader.
     */
    public static ClassLoader getSystemClassLoader() {
        return null;
    }

    /**
     * Answers an URL specifying a resource which can be found by looking up
     * resName using the system class loader's resource lookup algorithm.
     * 
     * @return A URL specifying a system resource or null.
     * @param resName
     *            The name of the resource to find.
     * @see Class#getResource
     */
    public static URL getSystemResource(String resName) {
        return null;
    }

    /**
     * Answers an Enumeration of URLs containing all resources which can be
     * found by looking up resName using the system class loader's resource
     * lookup algorithm.
     * 
     * @return An Enumeration of URLs containing the system resources
     * @param resName
     *            String the name of the resource to find.
     * @throws IOException
     *             if an IO exception occurs
     */
    public static Enumeration<URL> getSystemResources(String resName)
            throws IOException {
        return null;
    }

    /**
     * Answers a stream on a resource found by looking up resName using the
     * system class loader's resource lookup algorithm. Basically, the contents
     * of the java.class.path are searched in order, looking for a path which
     * matches the specified resource.
     * 
     * @return A stream on the resource or null.
     * @param resName
     *            The name of the resource to find.
     * @see Class#getResourceAsStream
     */
    public static InputStream getSystemResourceAsStream(String resName) {
        return null;
    }

    /**
     * Constructs a new instance of this class with the system class loader as
     * its parent.
     * 
     * @throws SecurityException
     *             if a security manager exists and it does not allow the
     *             creation of new ClassLoaders.
     */
    protected ClassLoader() {
        super();
    }

    /**
     * Constructs a new instance of this class with the given class loader as
     * its parent.
     * 
     * @param parentLoader
     *            The ClassLoader to use as the new class loaders parent.
     * @throws SecurityException
     *             if a security manager exists and it does not allow the
     *             creation of new ClassLoaders.
     * @throws NullPointerException
     *             if the parent is null.
     */
    protected ClassLoader(ClassLoader parentLoader) {
        super();
    }

    /**
     * Constructs a new class from an array of bytes containing a class
     * definition in class file format.
     * 
     * @param classRep
     *            A memory image of a class file.
     * @param offset
     *            The offset into the classRep.
     * @param length
     *            The length of the class file.
     * @deprecated Use defineClass(String, byte[], int, int)
     */
    @Deprecated
    protected final Class<?> defineClass(byte[] classRep, int offset, int length)
            throws ClassFormatError {
        return null;
    }

    /**
     * Constructs a new class from an array of bytes containing a class
     * definition in class file format.
     * 
     * @param className
     *            The name of the new class
     * @param classRep
     *            A memory image of a class file
     * @param offset
     *            The offset into the classRep
     * @param length
     *            The length of the class file
     */
    protected final Class<?> defineClass(String className, byte[] classRep,
            int offset, int length) throws ClassFormatError {
        return null;
    }

    /**
     * Constructs a new class from an array of bytes containing a class
     * definition in class file format and assigns the new class to the
     * specified protection domain.
     * 
     * @param className
     *            The name of the new class.
     * @param classRep
     *            A memory image of a class file.
     * @param offset
     *            The offset into the classRep.
     * @param length
     *            The length of the class file.
     * @param protectionDomain
     *            The protection domain this class should belongs to.
     */
    protected final Class<?> defineClass(String className, byte[] classRep,
            int offset, int length, ProtectionDomain protectionDomain)
            throws java.lang.ClassFormatError {
        return null;
    }

    /**
     * <p>
     * Defines a new class for the name, bytecodes in the byte buffer and the
     * protection domain.
     * </p>
     * 
     * @param name
     *            The name of the class to define.
     * @param b
     *            The byte buffer containing the bytecodes of the new class.
     * @param protectionDomain
     *            The protection domain this class belongs to.
     * @return The defined class.
     * @throws ClassFormatError
     *             if an invalid class file is defined.
     * @since 1.5
     */
    protected final Class<?> defineClass(String name, ByteBuffer b,
            ProtectionDomain protectionDomain) throws ClassFormatError {
        byte[] temp = new byte[b.remaining()];
        b.get(temp);
        return defineClass(name, temp, 0, temp.length, protectionDomain);
    }

    /**
     * Overridden by subclasses, by default throws ClassNotFoundException. This
     * method is called by loadClass() after the parent ClassLoader has failed
     * to find a loaded class of the same name.
     * 
     * @return The class or null.
     * @param className
     *            The name of the class to search for.
     * @throws ClassNotFoundException
     *             if the class cannot be found.
     */
    protected Class<?> findClass(String className)
            throws ClassNotFoundException {
        return null;
    }

    /**
     * Attempts to find and return a class which has already been loaded by the
     * virtual machine. Note that the class may not have been linked and the
     * caller should call resolveClass() on the result if necessary.
     * 
     * @return The class or null.
     * @param className
     *            The name of the class to search for.
     */
    protected final Class<?> findLoadedClass(String className) {
        return null;
    }

    /**
     * Attempts to load a class using the system class loader. Note that the
     * class has already been been linked.
     * 
     * @return The class which was loaded.
     * @param className
     *            The name of the class to search for.
     * @throws ClassNotFoundException
     *             if the class cannot be found.
     */
    protected final Class<?> findSystemClass(String className)
            throws ClassNotFoundException {
        return null;
    }

    /**
     * Returns the specified ClassLoader's parent.
     * 
     * @return The class or null.
     * @throws SecurityException
     *             if a security manager exists and it does not allow the parent
     *             loader to be retrieved.
     */
    public final ClassLoader getParent() {
        return null;
    }

    /**
     * Answers an URL which can be used to access the resource described by
     * resName, using the class loader's resource lookup algorithm. The default
     * behavior is just to return null.
     * 
     * @return The location of the resource.
     * @param resName
     *            String the name of the resource to find.
     * @see Class#getResource(String)
     */
    public URL getResource(String resName) {
        return null;
    }

    /**
     * Answers an Enumeration of URL which can be used to access the resources
     * described by resName, using the class loader's resource lookup algorithm.
     * The default behavior is just to return an empty Enumeration.
     * 
     * @return The location of the resources.
     * @param resName
     *            String the name of the resource to find.
     * @throws IOException
     *             if an IO exception occurs
     */
    public Enumeration<URL> getResources(String resName) throws IOException {
        return null;
    }

    /**
     * Answers a stream on a resource found by looking up resName using the
     * class loader's resource lookup algorithm. The default behavior is just to
     * return null.
     * 
     * @return A stream on the resource or null.
     * @param resName
     *            String the name of the resource to find.
     * @see Class#getResourceAsStream
     */
    public InputStream getResourceAsStream(String resName) {
        return null;
    }

    /**
     * Invoked by the Virtual Machine when resolving class references.
     * Equivalent to loadClass(className, false);
     * 
     * @return The Class object.
     * @param className
     *            The name of the class to search for.
     * @throws ClassNotFoundException
     *             if the class could not be found.
     */
    public Class<?> loadClass(String className) throws ClassNotFoundException {
        return null;
    }

    /**
     * Loads the class with the specified name, optionally linking the class
     * after load. Steps are: 1) Call findLoadedClass(className) to determine if
     * class is loaded 2) Call loadClass(className, resolveClass) on the parent
     * loader. 3) Call findClass(className) to find the class
     * 
     * @return The Class object.
     * @param className
     *            The name of the class to search for.
     * @param resolveClass
     *            Indicates if class should be resolved after loading.
     * @throws ClassNotFoundException
     *             if the class could not be found.
     */
    protected Class<?> loadClass(String className, boolean resolveClass)
            throws ClassNotFoundException {
        return null;
    }

    /**
     * Forces a class to be linked (initialized). If the class has already been
     * linked this operation has no effect.
     * 
     * @param clazz
     *            The Class to link.
     * @throws NullPointerException
     *             if clazz is null.
     */
    protected final void resolveClass(Class<?> clazz) {
        return;
    }

    /**
     * This method must be provided by the VM vendor, as it is used by other
     * provided class implementations in this package. A sample implementation
     * of this method is provided by the reference implementation. This method
     * is used by SecurityManager.classLoaderDepth(), currentClassLoader() and
     * currentLoadedClass(). Answers true if the receiver is a system class
     * loader.
     * 
     * Note that this method has package visibility only. It is defined here to
     * avoid the security manager check in getSystemClassLoader, which would be
     * required to implement this method anywhere else.
     *
     * 
     * @return <code>true</code> if the receiver is a system class loader
     * @see Class#getClassLoaderImpl()
     */
    final boolean isSystemClassLoader() {
        return false;
    }

    /**
     * <p>
     * Answers true if the receiver is ancestor of another class loader.
     * </p>
     * <p>
     * Note that this method has package visibility only. It is defined here to
     * avoid the security manager check in getParent, which would be required to
     * implement this method anywhere else.
     * </p>
     * 
     * @param child
     *            A child candidate
     * @return <code>true</code> if the receiver is ancestor of the parameter
     */
    final boolean isAncestorOf(ClassLoader child) {
        return false;
    }

    /**
     * Answers an URL which can be used to access the resource described by
     * resName, using the class loader's resource lookup algorithm. The default
     * behavior is just to return null. This should be implemented by a
     * ClassLoader.
     * 
     * @return The location of the resource.
     * @param resName
     *            The name of the resource to find.
     */
    protected URL findResource(String resName) {
        return null;
    }

    /**
     * Answers an Enumeration of URL which can be used to access the resources
     * described by resName, using the class loader's resource lookup algorithm.
     * The default behavior is just to return an empty Enumeration.
     * 
     * @param resName
     *            The name of the resource to find.
     * 
     * @return The locations of the resources.
     * 
     * @throws IOException
     *             when an error occurs
     */
    protected Enumeration<URL> findResources(String resName) throws IOException {
        return null;
    }

    /**
     * Answers the absolute path of the file containing the library associated
     * with the given name, or null. If null is answered, the system searches
     * the directories specified by the system property "java.library.path".
     * 
     * @return The library file name or null.
     * @param libName
     *            The name of the library to find.
     */
    protected String findLibrary(String libName) {
        return null;
    }

    /**
     * Attempt to locate the requested package. If no package information can be
     * located, null is returned.
     * 
     * @param name
     *            The name of the package to find
     * @return The package requested, or null
     */
    protected Package getPackage(String name) {
        return null;
    }

    /**
     * Return all the packages known to this class loader.
     * 
     * @return All the packages known to this classloader
     */
    protected Package[] getPackages() {
        return null;
    }

    /**
     * Define a new Package using the specified information.
     * 
     * @param name
     *            The name of the package
     * @param specTitle
     *            The title of the specification for the Package
     * @param specVersion
     *            The version of the specification for the Package
     * @param specVendor
     *            The vendor of the specification for the Package
     * @param implTitle
     *            The implementation title of the Package
     * @param implVersion
     *            The implementation version of the Package
     * @param implVendor
     *            The specification vendor of the Package
     * @param sealBase
     *            If sealBase is null, the package is left unsealed. Otherwise,
     *            the the package is sealed using this URL.
     * @return The Package created
     * @throws IllegalArgumentException
     *             if the Package already exists
     */
    protected Package definePackage(String name, String specTitle,
            String specVersion, String specVendor, String implTitle,
            String implVersion, String implVendor, URL sealBase)
            throws IllegalArgumentException {
        return null;
    }

    /**
     * Gets the signers of a class.
     * 
     * @param c
     *            The Class object
     * @return signers The signers for the class
     */
    final Object[] getSigners(Class<?> c) {
        return null;
    }

    /**
     * Sets the signers of a class.
     * 
     * @param c
     *            The Class object
     * @param signers
     *            The signers for the class
     */
    protected final void setSigners(Class<?> c, Object[] signers) {
        return;
    }

    /**
     * <p>
     * This must be provided by the VM vendor. It is used by
     * SecurityManager.checkMemberAccess() with depth = 3. Note that
     * checkMemberAccess() assumes the following stack when called:<br>
     * </p>
     * 
     * <pre>
     *    		&lt; user code &amp;gt; &lt;- want this class
     *    		Class.getDeclared*();
     *    		Class.checkMemberAccess();
     *    		SecurityManager.checkMemberAccess(); &lt;- current frame
     * </pre>
     * 
     * <p>
     * Returns the ClassLoader of the method (including natives) at the
     * specified depth on the stack of the calling thread. Frames representing
     * the VM implementation of java.lang.reflect are not included in the list.
     * </p>
     * Notes:
     * <ul>
     * <li>This method operates on the defining classes of methods on stack.
     * NOT the classes of receivers.</li>
     * <li>The item at depth zero is the caller of this method</li>
     * </ul>
     * 
     * @param depth
     *            the stack depth of the requested ClassLoader
     * @return the ClassLoader at the specified depth
     */
    static final ClassLoader getStackClassLoader(int depth) {
        return null;
    }

    /**
     * This method must be included, as it is used by System.load(),
     * System.loadLibrary(). The reference implementation of this method uses
     * the getStackClassLoader() method. Returns the ClassLoader of the method
     * that called the caller. i.e. A.x() calls B.y() calls callerClassLoader(),
     * A's ClassLoader will be returned. Returns null for the bootstrap
     * ClassLoader.
     * 
     * @return a ClassLoader or null for the bootstrap ClassLoader
     */
    static ClassLoader callerClassLoader() {
        return null;
    }

    /**
     * This method must be provided by the VM vendor, as it is called by
     * java.lang.System.loadLibrary(). System.loadLibrary() cannot call
     * Runtime.loadLibrary() because this method loads the library using the
     * ClassLoader of the calling method. Loads and links the library specified
     * by the argument.
     * 
     * @param libName
     *            the name of the library to load
     * @param loader
     *            the classloader in which to load the library
     * @throws UnsatisfiedLinkError
     *             if the library could not be loaded
     * @throws SecurityException
     *             if the library was not allowed to be loaded
     */
    static void loadLibraryWithClassLoader(String libName, ClassLoader loader) {
        return;
    }

    /**
     * This method must be provided by the VM vendor, as it is called by
     * java.lang.System.load(). System.load() cannot call Runtime.load() because
     * the library is loaded using the ClassLoader of the calling method. Loads
     * and links the library specified by the argument. No security check is
     * done.
     * 
     * @param libName
     *            the name of the library to load
     * @param loader
     *            the classloader in which to load the library
     * @param libraryPath
     *            the library path to search, or null
     * @throws UnsatisfiedLinkError
     *             if the library could not be loaded
     */
    static void loadLibraryWithPath(String libName, ClassLoader loader,
            String libraryPath) {
        return;
    }

    /**
     * Sets the assertion status of a class.
     * 
     * @param cname
     *            Class name
     * @param enable
     *            Enable or disable assertion
     */
    public void setClassAssertionStatus(String cname, boolean enable) {
        return;
    }

    /**
     * Sets the assertion status of a package.
     * 
     * @param pname
     *            Package name
     * @param enable
     *            Enable or disable assertion
     */
    public void setPackageAssertionStatus(String pname, boolean enable) {
        return;
    }

    /**
     * Sets the default assertion status of a classloader
     * 
     * @param enable
     *            Enable or disable assertion
     */
    public void setDefaultAssertionStatus(boolean enable) {
        return;
    }

    /**
     * Clears the default, package and class assertion status of a classloader
     * 
     */
    public void clearAssertionStatus() {
        return;
    }

    /**
     * Answers the assertion status of the named class Returns the assertion
     * status of the class or nested class if it has been set. Otherwise returns
     * the assertion status of its package or superpackage if that has been set.
     * Otherwise returns the default assertion status. Returns 1 for enabled and
     * 0 for disabled.
     * 
     * @return the assertion status.
     * @param cname
     *            the name of class.
     */
    boolean getClassAssertionStatus(String cname) {
        return false;
    }

    /**
     * Answers the assertion status of the named package Returns the assertion
     * status of the named package or superpackage if that has been set.
     * Otherwise returns the default assertion status. Returns 1 for enabled and
     * 0 for disabled.
     * 
     * @return the assertion status.
     * @param pname
     *            the name of package.
     */
    boolean getPackageAssertionStatus(String pname) {
        return false;
    }

    /**
     * Answers the default assertion status
     * 
     * @return boolean the default assertion status.
     */
    boolean getDefaultAssertionStatus() {
        return false;
    }
}
