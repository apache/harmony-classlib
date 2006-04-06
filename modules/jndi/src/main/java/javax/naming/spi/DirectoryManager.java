/* Copyright 2004 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package javax.naming.spi;

import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;

import javax.naming.CannotProceedException;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NameParser;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.RefAddr;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.naming.StringRefAddr;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.spi.DirStateFactory.Result;

import org.apache.harmony.jndi.internal.EnvironmentReader;
import org.apache.harmony.jndi.internal.UrlParser;

/**
 * The <code>DirectoryManager</code> class cannot be instantiated. All its
 * methods are static. The methods are used by service providers for accessing
 * object and state factories and for determining continuation contexts.
 * <p>
 * The <code>Name</code> and <code>Hashtable</code> arguments passed to the
 * <code>DirectoryManager</code> methods remain owned purely by the calling
 * method. They must not be changed or referenced.</p>
 * <p>
 * Multithreaded access to this class must be safe.</p>
 *
 */
public class DirectoryManager extends NamingManager {

    /*
     * -------------------------------------------------------------------
     * Class variables
     * -------------------------------------------------------------------
     */
    /*
     * -------------------------------------------------------------------
     * Constructors
     * -------------------------------------------------------------------
     */
    private DirectoryManager(){//private to prevent it being instanced        
    }

    /*
     * -------------------------------------------------------------------
     * Methods
     * -------------------------------------------------------------------
     */

    /**
     * Create an object using an object factory.
     * Returns a new <code>Object</code> or the supplied <code>Object o</code>
     * if one cannot be created.
     *
     * The behaviour is like that for the
     * <code>getObjectInstance</code> method of <code>NamingManager</code>
     * however it should be noted that the intermediate object factory may be
     * either of type <code>DirObjectFactory</code> or of type
     * <code>ObjectFactory</code>; in the former case, the supplied
     * <code>Attributes</code> must be passed when getting the object,
     * otherwise the supplied <code>Attributes</code> are ignored.
     *
     * @param o An object which may provide reference or location information.
     *          May be null.
     * @param n The name of the <code>Object</code> relative to the default
     *          initial context (or relative to the Context c if it is supplied)
     * @param c The <code>Context</code> to which the Name is relative
     * @param h a <code>Hashtable</code> containing environment properties and
     *          values - may be null
     * @param a <code>Attributes</code> - if some or all of the attributes of
     *          <code>Object o</code> are already known they can be supplied so
     *          that the factory does not have to do the work of looking them up.
     * @return  the created object
     * @throws NamingException if one is encountered
     * @throws Exception if any other exception is encountered
     */
    public static Object getObjectInstance(
        Object o,
        Name n,
        Context c,
        Hashtable h,
        Attributes a)
        throws NamingException, Exception {

        // 1. try ObjectFactoryBuilder, if it is set
        if (null != ofb) {
            // use the builder to create an object factory
            ObjectFactory factory = ofb.createObjectFactory(o, h);
            // get object instance using the factory and return
            return getObjectInstanceFromGivenFactory(factory, o, n, c, h, a);
        }

        // 2. see whether o is a Referenceable or a Reference
        Reference ref = null;
        if (o instanceof Referenceable) {
            ref = ((Referenceable) o).getReference();
        }
        if (o instanceof Reference) {
            ref = (Reference) o;
        }
        // if o is a Referenceable or a Reference
        if (null != ref) {
            // if a factory class name is supplied by the reference, use it to create
            if (null != ref.getFactoryClassName()) {
                return getObjectInstanceByFactoryInReference(ref, o, n, c, h, a);
            }
            // see if ref has any StringRefAddrs of address type URL, 
            Object result = getObjectInstanceByUrlRefAddr(n, c, h, ref);
            // if success, return it
            if (null != result) {
                return result;
            }
        }

        // 3. try Context.OBJECT_FACTORIES
        Object result = getObjectInstanceByObjectFactory(o, n, c, h, a);
        if (null != result) {
            return result;
        }

        // all failed, just return o
        return o;
    }

    /*
     * Check the type of factory, DirObjectFactory or ObjectFactory, and
     * call getObjectInstance() on the property type.
     */
    private static Object getObjectInstanceFromGivenFactory(
        ObjectFactory factory,
        Object o,
        Name n,
        Context c,
        Hashtable h,
        Attributes a) throws Exception {
        if (factory instanceof DirObjectFactory) {
            return ((DirObjectFactory) factory).getObjectInstance(o, n, c, h, a);
        }
		return factory.getObjectInstance(o, n, c, h);
    }

    private static Object getObjectInstanceByObjectFactory(
        Object o,
        Name n,
        Context c,
        Hashtable h,
        Attributes a)
        throws NamingException, Exception {
        // obtain object factories from hashtable and service provider resource file
        String fnames[] =
            EnvironmentReader.getFactoryNamesFromEnvironmentAndProviderResource(
                h,
                c,
                Context.OBJECT_FACTORIES);
        // for each object factory
        for (int i = 0; i < fnames.length; i++) {
            // new factory instance by its class name
            ObjectFactory factory = null;
            try {
                factory = (ObjectFactory) classForName(fnames[i]).newInstance();
            } catch (Exception e) {
                continue;
            }
            // create object using factory
            Object obj = getObjectInstanceFromGivenFactory(factory, o, n, c, h, a);
            if (null != obj) {
                return obj;
            }
        }
        // no object factory succeeded, return null
        return null;
    }

    private static Object getObjectInstanceByUrlRefAddr(
        Name n,
        Context c,
        Hashtable h,
        Reference ref)
        throws NamingException {
        // obtain pkg prefixes from hashtable and service provider resource file
        String pkgPrefixes[] =
            EnvironmentReader.getFactoryNamesFromEnvironmentAndProviderResource(
                h,
                c,
                Context.URL_PKG_PREFIXES);
        // for each RefAddr
        Enumeration enumeration = ref.getAll();
        while (enumeration.hasMoreElements()) {
            RefAddr addr = (RefAddr) enumeration.nextElement();
            // if it is StringRefAddr and type is URL
            if (addr instanceof StringRefAddr
                && addr.getType().equalsIgnoreCase("URL")) { //$NON-NLS-1$
                // get the url address
                String url = (String) ((StringRefAddr) addr).getContent();
                // try create using url context factory
                Object obj =
                    getObjectInstanceByUrlContextFactory(
                        url,
                        n,
                        c,
                        h,
                        pkgPrefixes,
                        UrlParser.getScheme(url));
                // if success, return the created obj
                if (null != obj) {
                    return obj;
                }
            }
        }
        // failed to create using any StringRefAddr of address type URL, return null
        return null;
    }

    private static Object getObjectInstanceByUrlContextFactory(
        String url,
        Name n,
        Context c,
        Hashtable h,
        String pkgPrefixes[],
        String schema)
        throws NamingException {
        // if schema is empty or null, fail, return null
        if (null == schema || 0 == schema.length()) {
            return null;
        }

        // for each pkg prefix
        for (int i = 0; i < pkgPrefixes.length; i++) {
            ObjectFactory factory = null;
            try {
                // create url context factory instance
                String clsName =
                    pkgPrefixes[i] + "." + schema + "." + schema + "URLContextFactory"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                factory = (ObjectFactory) classForName(clsName).newInstance();
            } catch (Exception e) {
                // failed to create factory, continue trying
                continue;
            }
            try {
                // create obj using url context factory
                /*
                 * Unit test shows it is ObjectFactory here, not DirObjectFactory
                 * // Object obj = factory.getObjectInstance(url, n, c, h, a);
                 */
                Object obj = factory.getObjectInstance(url, n, c, h);
                // if create success, return it
                if (null != obj) {
                    return obj;
                }
            } catch (Exception e) {
                // throw NamingException, if factory fails
                if (e instanceof NamingException) {
                    throw (NamingException) e;
                }
                NamingException nex =
                    new NamingException("Failed to create object instance"); //$NON-NLS-1$
                nex.setRootCause(e);
                throw nex;
            }
        }
        // fail to create using url context factory, return null
        return null;
    }

    private static Object getObjectInstanceByFactoryInReference(
        Reference ref,
        Object o,
        Name n,
        Context c,
        Hashtable h,
        Attributes a)
        throws Exception {
        ObjectFactory factory = null;

        // try load the factory by its class name
        try {
            factory =
                (ObjectFactory) classForName(ref.getFactoryClassName()).newInstance();
        } catch (ClassNotFoundException e) {
        	// Ignored.
        }

        // try load the factory from its class location
        if (null == factory && null != ref.getFactoryClassLocation()) {
            factory =
                (ObjectFactory) loadFactoryFromLocation(ref.getFactoryClassName(),
                    ref.getFactoryClassLocation());
        }
        // if factory cannot be loaded
        if (null == factory) {
            // return o
            return o;
        }

        // get object instance using the factory and return it
        return getObjectInstanceFromGivenFactory(factory, ref, n, c, h, a);
    }

    /*
     * If cannot load class, return null.
     * Throws any exceptions except ClassNotFoundException
     */
    private static Object loadFactoryFromLocation(String clsName, String location)
        throws Exception {

        // convert location into an array of URL, separated by ' '
        StringTokenizer st = new StringTokenizer(location, " "); //$NON-NLS-1$
        URL urls[] = new URL[st.countTokens()];
        for (int i = 0; i < urls.length; i++) {
            urls[i] = new URL(st.nextToken());
        }

        // new a URLClassLoader from the URLs
        URLClassLoader l = new URLClassLoader(urls);

        // try load factory by URLClassLoader
        try {
            // return the new instance
            return l.loadClass(clsName).newInstance();
        } catch (ClassNotFoundException e) {
            // return null if class loading failed
            return null;
        }
    }

    /**
     * Get the state of an Object.
     * Returns a <code>DirStateFactory</code>. Result which cannot be null. It
     * contains the attributes and object to be bound, either of which may be
     * null. Once returned the caller is the owner of it.
     * The behaviour is like that for the <code>getStateToBind</code> method of
     * <code>NamingManager</code> however it should be noted that the
     * intermediate state factory may be of type <code>DirStateFactory</code>
     * rather than just <code>StateFactory</code> in which case it should also
     * use the supplied <code>Attributes</code> when getting the state.
     *
     * @param o An object which may provide reference or location information.
     *          May be null.
     * @param n The name of the <code>Object</code> relative to the default
     *          initial context (or relative to the Context c if it is supplied)
     * @param c The <code>Context</code> to which the <code>Name</code> is
     *          relative
     * @param h a <code>Hashtable</code> containing environment properties and
     *          values - may be null
     * @param a <code>Attributes</code> - if some or all of the attributes of
     *          <code>Object o</code> are already known they can be supplied so
     *          that the factory does not have to do the work of looking them up.
     * @return  the state of the object
     * @throws NamingException if one is encountered
     */
    public static DirStateFactory.Result getStateToBind(
        Object o,
        Name n,
        Context c,
        Hashtable h,
        Attributes a)
        throws NamingException {

        // obtain state factories from hashtable and service provider resource file
        String fnames[] =
            EnvironmentReader.getFactoryNamesFromEnvironmentAndProviderResource(
                h,
                c,
                Context.STATE_FACTORIES);

        // for each state factory
        for (int i = 0; i < fnames.length; i++) {
            // new factory instance by its class name
            StateFactory factory = null;
            try {
                factory = (StateFactory) classForName(fnames[i]).newInstance();
            } catch (Exception e) {
                continue;
            }
            if (factory instanceof DirStateFactory) {
                // try obtain state using the DirStateFactory
                Result r = ((DirStateFactory) factory).getStateToBind(o, n, c, h, a);
                // if the result is not null, return it
                if (null != r) {
                    return r;
                }
            } else {
                // try obtain state using the StateFactory
                Object state = factory.getStateToBind(o, n, c, h);
                // if a state obtained successfully, return it
                if (null != state) {
                    return new Result(state, a);
                }
            }
        }

        // all factories failed, return the input argument o
        return new Result(o, a);
    }

    /**
     * Create the next <code>DirContext</code> when using federation so that 
     * the <code>DirContext</code> operation can be reinvoked.
     * This should work similarly to 
     * <code>NamingManager.getContinuationContext</code> except that a 
     * reference to a <code>DirContext</code> is returned.
     * <p>
     * This method is also responsible for setting the property denoted by the
     * <code>CPE</code> string to be the supplied
     * <code>CannotProceedException</code> for that environment.</p>
     *
     * @param cpe   the <code>CannotProceedException</code> generated by the
     *              <code>DirContext</code> of the previous naming system when
     *              it can proceed no further.
     * @return      the next <code>DirContext</code> when using federation
     * @throws  NamingException if the resolved object is not set or if a
     *          <code>DirContext</code>  cannot be obtained from it either
     *          directly or indirectly.
     */
    public static DirContext getContinuationDirContext(CannotProceedException cpe)
        throws NamingException {
        // obtain next context using NamingManager
        Context nextContext = null;
        try {
            nextContext = NamingManager.getContinuationContext(cpe);
        } catch (CannotProceedException e) {
            // tolerate CannotProceedException here
        }
        
        // if it is a DirContext
        if (nextContext instanceof DirContext) {
            // return as DirContext
            return (DirContext) nextContext;
        } else if (nextContext instanceof Context) {
            // in case it's Context but not DirContext, wrap it as DirContext and return
            return new Context2DirContextWrapper(nextContext, cpe);
        } else {
            // return a dummy DirContext, even if no context is obtained
            return new Context2DirContextWrapper(null, cpe);
        }
    }

    private static Class classForName(final String className)
        throws ClassNotFoundException {

        Class cls = (Class) AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                // try thread context class loader first
                try {
                    return Class.forName(
                        className,
                        true,
                        Thread.currentThread().getContextClassLoader());
                } catch (ClassNotFoundException e) {
                	// Could happen.
                }
                // try system class loader second
                try {
                    return Class.forName(
                        className,
                        true,
                        ClassLoader.getSystemClassLoader());
                } catch (ClassNotFoundException e1) {
                	// Not found here either.
                }
                // return null, if fail to load class
                return null;
            }
        });

        if (cls == null) {
            throw new ClassNotFoundException("class " + className + " not found"); //$NON-NLS-1$ //$NON-NLS-2$
        }

        return cls;

    }
    
    /**
     * An inner class that transforms a Context instance into DirContext.
     */
    private static class Context2DirContextWrapper implements DirContext {
        private Context ctx;
        private CannotProceedException cpe;
        
        public Context2DirContextWrapper(Context ctx, CannotProceedException cpe) {
            this.ctx = ctx;
            this.cpe = cpe;
        }
        
        private Context getContext() throws CannotProceedException {
            if (ctx instanceof Context) {
                return ctx;
            }
            cpe.fillInStackTrace();
            throw cpe;
        }
        
        private DirContext getDirContext() throws CannotProceedException {
            if (ctx instanceof DirContext) {
                return (DirContext) ctx;
            }
            cpe.fillInStackTrace();
            throw cpe;
        }
        
        /**
         * @param s
         * @return
         * @throws NamingException
         */
        public NamingEnumeration listBindings(String s) throws NamingException {
            return getContext().listBindings(s);
        }

        /**
         * @param s
         * @return
         * @throws NamingException
         */
        public Object removeFromEnvironment(String s) throws NamingException {
            return getContext().removeFromEnvironment(s);
        }

        /**
         * @param s
         * @return
         * @throws NamingException
         */
        public Object lookupLink(String s) throws NamingException {
            return getContext().lookupLink(s);
        }

        /**
         * @param n
         * @return
         * @throws NamingException
         */
        public NamingEnumeration list(Name n) throws NamingException {
            return getContext().list(n);
        }

        /**
         * @param s
         * @return
         * @throws NamingException
         */
        public Object lookup(String s) throws NamingException {
            return getContext().lookup(s);
        }

        /**
         * @param s
         * @param o
         * @return
         * @throws NamingException
         */
        public Object addToEnvironment(String s, Object o) throws NamingException {
            return getContext().addToEnvironment(s, o);
        }

        /* (non-Javadoc)
         * @see java.lang.Object#toString()
         */
        public String toString() {
            try {
                return getContext().toString();
            } catch (CannotProceedException e) {
                return super.toString();
            }
        }

        /**
         * @param s
         * @return
         * @throws NamingException
         */
        public Context createSubcontext(String s) throws NamingException {
            return getContext().createSubcontext(s);
        }

        /**
         * @param nOld
         * @param nNew
         * @throws NamingException
         */
        public void rename(Name nOld, Name nNew) throws NamingException {
            getContext().rename(nOld, nNew);
        }

        /* (non-Javadoc)
         * @see java.lang.Object#hashCode()
         */
        public int hashCode() {
            try {
                return getContext().hashCode();
            } catch (CannotProceedException e) {
                return super.hashCode();
            }
        }

        /**
         * @param n
         * @param o
         * @throws NamingException
         */
        public void rebind(Name n, Object o) throws NamingException {
            getContext().rebind(n, o);
        }

        /**
         * @param sOld
         * @param sNew
         * @throws NamingException
         */
        public void rename(String sOld, String sNew) throws NamingException {
            getContext().rename(sOld, sNew);
        }

        /**
         * @param n
         * @return
         * @throws NamingException
         */
        public Context createSubcontext(Name n) throws NamingException {
            return getContext().createSubcontext(n);
        }

        /**
         * @param s
         * @return
         * @throws NamingException
         */
        public NameParser getNameParser(String s) throws NamingException {
            return getContext().getNameParser(s);
        }

        /**
         * @param s
         * @param o
         * @throws NamingException
         */
        public void rebind(String s, Object o) throws NamingException {
            getContext().rebind(s, o);
        }

        /**
         * @param n
         * @return
         * @throws NamingException
         */
        public NamingEnumeration listBindings(Name n) throws NamingException {
            return getContext().listBindings(n);
        }

        /**
         * @param n
         * @return
         * @throws NamingException
         */
        public NameParser getNameParser(Name n) throws NamingException {
            return getContext().getNameParser(n);
        }

        /**
         * @param s
         * @return
         * @throws NamingException
         */
        public NamingEnumeration list(String s) throws NamingException {
            return getContext().list(s);
        }

        /**
         * @return
         * @throws NamingException
         */
        public String getNameInNamespace() throws NamingException {
            return getContext().getNameInNamespace();
        }

        /**
         * @param n
         * @throws NamingException
         */
        public void unbind(Name n) throws NamingException {
            getContext().unbind(n);
        }

        /**
         * @param n
         * @param pfx
         * @return
         * @throws NamingException
         */
        public Name composeName(Name n, Name pfx) throws NamingException {
            return getContext().composeName(n, pfx);
        }

        /**
         * @param n
         * @param o
         * @throws NamingException
         */
        public void bind(Name n, Object o) throws NamingException {
            getContext().bind(n, o);
        }

        /**
         * @param s
         * @throws NamingException
         */
        public void unbind(String s) throws NamingException {
            getContext().unbind(s);
        }

        /**
         * @throws NamingException
         */
        public void close() throws NamingException {
            getContext().close();
        }

        /**
         * @param n
         * @return
         * @throws NamingException
         */
        public Object lookupLink(Name n) throws NamingException {
            return getContext().lookupLink(n);
        }

        /**
         * @param n
         * @throws NamingException
         */
        public void destroySubcontext(Name n) throws NamingException {
            getContext().destroySubcontext(n);
        }

        /**
         * @param s
         * @param pfx
         * @return
         * @throws NamingException
         */
        public String composeName(String s, String pfx) throws NamingException {
            return getContext().composeName(s, pfx);
        }

        /**
         * @param s
         * @param o
         * @throws NamingException
         */
        public void bind(String s, Object o) throws NamingException {
            getContext().bind(s, o);
        }

        /**
         * @param n
         * @return
         * @throws NamingException
         */
        public Object lookup(Name n) throws NamingException {
            return getContext().lookup(n);
        }

        /* (non-Javadoc)
         * @see java.lang.Object#equals(java.lang.Object)
         */
        public boolean equals(Object arg0) {
            try {
                return getContext().equals(arg0);
            } catch (CannotProceedException e) {
                return super.equals(arg0);
            }
        }

        /**
         * @param s
         * @throws NamingException
         */
        public void destroySubcontext(String s) throws NamingException {
            getContext().destroySubcontext(s);
        }

        /**
         * @return
         * @throws NamingException
         */
        public Hashtable getEnvironment() throws NamingException {
            return getContext().getEnvironment();
        }

        /**
         * @param name
         * @param obj
         * @param attributes
         * @throws NamingException
         */
        public void bind(Name name, Object obj, Attributes attributes)
            throws NamingException {
            getDirContext().bind(name, obj, attributes);
        }

        /**
         * @param s
         * @param obj
         * @param attributes
         * @throws NamingException
         */
        public void bind(String s, Object obj, Attributes attributes)
            throws NamingException {
            getDirContext().bind(s, obj, attributes);
        }

        /**
         * @param name
         * @param attributes
         * @return
         * @throws NamingException
         */
        public DirContext createSubcontext(Name name, Attributes attributes)
            throws NamingException {
            return getDirContext().createSubcontext(name, attributes);
        }

        /**
         * @param s
         * @param attributes
         * @return
         * @throws NamingException
         */
        public DirContext createSubcontext(String s, Attributes attributes)
            throws NamingException {
            return getDirContext().createSubcontext(s, attributes);
        }

        /**
         * @param name
         * @return
         * @throws NamingException
         */
        public Attributes getAttributes(Name name) throws NamingException {
            return getDirContext().getAttributes(name);
        }

        /**
         * @param name
         * @param as
         * @return
         * @throws NamingException
         */
        public Attributes getAttributes(Name name, String[] as) throws NamingException {
            return getDirContext().getAttributes(name, as);
        }

        /**
         * @param s
         * @return
         * @throws NamingException
         */
        public Attributes getAttributes(String s) throws NamingException {
            return getDirContext().getAttributes(s);
        }

        /**
         * @param s
         * @param as
         * @return
         * @throws NamingException
         */
        public Attributes getAttributes(String s, String[] as) throws NamingException {
            return getDirContext().getAttributes(s, as);
        }

        /**
         * @param name
         * @return
         * @throws NamingException
         */
        public DirContext getSchema(Name name) throws NamingException {
            return getDirContext().getSchema(name);
        }

        /**
         * @param s
         * @return
         * @throws NamingException
         */
        public DirContext getSchema(String s) throws NamingException {
            return getDirContext().getSchema(s);
        }

        /**
         * @param name
         * @return
         * @throws NamingException
         */
        public DirContext getSchemaClassDefinition(Name name) throws NamingException {
            return getDirContext().getSchemaClassDefinition(name);
        }

        /**
         * @param s
         * @return
         * @throws NamingException
         */
        public DirContext getSchemaClassDefinition(String s) throws NamingException {
            return getDirContext().getSchemaClassDefinition(s);
        }

        /**
         * @param name
         * @param i
         * @param attributes
         * @throws NamingException
         */
        public void modifyAttributes(Name name, int i, Attributes attributes)
            throws NamingException {
            getDirContext().modifyAttributes(name, i, attributes);
        }

        /**
         * @param name
         * @param modificationItems
         * @throws NamingException
         */
        public void modifyAttributes(Name name, ModificationItem[] modificationItems)
            throws NamingException {
            getDirContext().modifyAttributes(name, modificationItems);
        }

        /**
         * @param s
         * @param i
         * @param attributes
         * @throws NamingException
         */
        public void modifyAttributes(String s, int i, Attributes attributes)
            throws NamingException {
            getDirContext().modifyAttributes(s, i, attributes);
        }

        /**
         * @param s
         * @param modificationItems
         * @throws NamingException
         */
        public void modifyAttributes(String s, ModificationItem[] modificationItems)
            throws NamingException {
            getDirContext().modifyAttributes(s, modificationItems);
        }

        /**
         * @param name
         * @param obj
         * @param attributes
         * @throws NamingException
         */
        public void rebind(Name name, Object obj, Attributes attributes)
            throws NamingException {
            getDirContext().rebind(name, obj, attributes);
        }

        /**
         * @param s
         * @param obj
         * @param attributes
         * @throws NamingException
         */
        public void rebind(String s, Object obj, Attributes attributes)
            throws NamingException {
            getDirContext().rebind(s, obj, attributes);
        }

        /**
         * @param name
         * @param attributes
         * @return
         * @throws NamingException
         */
        public NamingEnumeration search(Name name, Attributes attributes)
            throws NamingException {
            return getDirContext().search(name, attributes);
        }

        /**
         * @param name
         * @param attributes
         * @param as
         * @return
         * @throws NamingException
         */
        public NamingEnumeration search(Name name, Attributes attributes, String[] as)
            throws NamingException {
            return getDirContext().search(name, attributes, as);
        }

        /**
         * @param name
         * @param filter
         * @param objs
         * @param searchControls
         * @return
         * @throws NamingException
         */
        public NamingEnumeration search(
            Name name,
            String filter,
            Object[] objs,
            SearchControls searchControls)
            throws NamingException {
            return getDirContext().search(name, filter, objs, searchControls);
        }

        /**
         * @param name
         * @param filter
         * @param searchControls
         * @return
         * @throws NamingException
         */
        public NamingEnumeration search(
            Name name,
            String filter,
            SearchControls searchControls)
            throws NamingException {
            return getDirContext().search(name, filter, searchControls);
        }

        /**
         * @param name
         * @param attributes
         * @return
         * @throws NamingException
         */
        public NamingEnumeration search(String name, Attributes attributes)
            throws NamingException {
            return getDirContext().search(name, attributes);
        }

        /**
         * @param name
         * @param attributes
         * @param as
         * @return
         * @throws NamingException
         */
        public NamingEnumeration search(String name, Attributes attributes, String[] as)
            throws NamingException {
            return getDirContext().search(name, attributes, as);
        }

        /**
         * @param name
         * @param filter
         * @param objs
         * @param searchControls
         * @return
         * @throws NamingException
         */
        public NamingEnumeration search(
            String name,
            String filter,
            Object[] objs,
            SearchControls searchControls)
            throws NamingException {
            return getDirContext().search(name, filter, objs, searchControls);
        }

        /**
         * @param name
         * @param filter
         * @param searchControls
         * @return
         * @throws NamingException
         */
        public NamingEnumeration search(
            String name,
            String filter,
            SearchControls searchControls)
            throws NamingException {
            return getDirContext().search(name, filter, searchControls);
        }

    }

}


