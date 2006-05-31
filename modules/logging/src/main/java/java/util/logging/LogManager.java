/* Copyright 2004, 2006 The Apache Software Foundation or its licensors, as applicable
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

package java.util.logging;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 * <code>LogManager</code> is used to maintain configuration properties of the
 * logging framework, and to manage a hierarchical namespace of all named
 * <code>Logger</code> objects.
 * <p>
 * There is only one global <code>LogManager</code> instance in the
 * application, which can be get by calling static method
 * <code>LogManager.getLogManager()</code>. This instance is created and
 * inited during class initialization and cannot be changed.
 * </p>
 * <p>
 * The <code>LogManager</code> class can be specified by
 * java.util.logging.manager system property, if the property is unavailable or
 * invalid, the default class <code>java.util.logging.LogManager</code> will
 * be used.
 * </p>
 * <p>
 * When initialization, <code>LogManager</code> read its configuration from a
 * properties file, which by default is the "lib/logging.properties" in the JRE
 * directory.
 * </p>
 * <p>
 * However, two optional system properties can be used to customize the initial
 * configuration process of <code>LogManager</code>.
 * <ul>
 * <li>"java.util.logging.config.class"</li>
 * <li>"java.util.logging.config.file"</li>
 * </ul>
 * </p>
 * <p>
 * These two properties can be set in three ways, by the Preferences API, by the
 * "java" command line property definitions, or by system property definitions
 * passed to JNI_CreateJavaVM.
 * </p>
 * <p>
 * The "java.util.logging.config.class" should specifies a class name. If it is
 * set, this given class will be loaded and instantiated during
 * <code>LogManager</code> initialization, so that this object's default
 * constructor can read the initial configuration and define properties for
 * <code>LogManager</code>.
 * </p>
 * <p>
 * If "java.util.logging.config.class" property is not set, or it is invalid, or
 * some exception is throwed during the instantiation, then the
 * "java.util.logging.config.file" system property can be used to specify a
 * properties file. The <code>LogManager</code> will read initial
 * configuration from this file.
 * </p>
 * <p>
 * If neither of these properties is defined, or some exception is throwed
 * during these two properties using, the <code>LogManager</code> will read
 * its initial configuration from default properties file, as described above.
 * </p>
 * <p>
 * The global logging properties may include:
 * <ul>
 * <li>"handlers". This property's valus should be a list of class names for
 * handler classes separated by whitespace, these classes must be subclasses of
 * <code>Handler</code> and each must have a default constructor, these
 * classes will be loaded, instantiated and registered as handlers on the root
 * <code>Logger</code> (the <code>Logger</code> named ""). These
 * <code>Handler</code>s maybe inited lazily.</li>
 * <li>"config". The property defines a list of class names separated by
 * whitespace. Each class must have a default constructor, in which it can
 * update the logging configuration, such as levels, handlers, or filters for
 * some logger, etc. These classes will be loaded and instantiated during
 * <code>LogManager</code> configuration</li>
 * </ul>
 * </p>
 * <p>
 * This class, together with any handler and configuration classes associated
 * with it, <b>must</b> be loaded from the system classpath when
 * <code>LogManager</code> configuration occurs.
 * </p>
 * <p>
 * Besides global properties, the properties for loggers and Handlers can be
 * specified in the property files. The names of these properties will start
 * with the complete dot separated names for the handlers or loggers.
 * </p>
 * <p>
 * In the <code>LogManager</code>'s hierarchical namespace,
 * <code>Loggers</code> are organized based on their dot separated names. For
 * example, "x.y.z" is child of "x.y".
 * </p>
 * <p>
 * Levels for <code>Loggers</code> can be defined by properties whose name end
 * with ".level". Thus "alogger.level" defines a level for the logger named as
 * "alogger" and for all its children in the naming hierarchy. Log levels
 * properties are read and applied in the same order as they are specified in
 * the property file. The root logger's level can be defined by the property
 * named as ".level".
 * </p>
 * <p>
 * All methods on this type can be taken as being thread safe.
 * </p>
 * 
 */
public class LogManager {
    /*
     * -------------------------------------------------------------------
     * Class variables
     * -------------------------------------------------------------------
     */

    // The line separator of the underlying OS
    // Use privileged code to read the line.separator system property
    private static final String lineSeparator = getPrivilegedSystemProperty("line.separator"); //$NON-NLS-1$

    // The file separator of the underlying OS
    private static final String fileSeparator = File.separator;

    // The shared logging permission
    private static final LoggingPermission perm = new LoggingPermission(
            "control", null); //$NON-NLS-1$

    // the singleton instance
    private static LogManager manager;

    /*
     * -------------------------------------------------------------------
     * Instance variables
     * -------------------------------------------------------------------
     */
    private Hashtable<String, Logger> loggers;

    private Logger root;

    // the configuration properties
    private Properties props = null;

    // the property change listener
    private PropertyChangeSupport listeners = null;

    /*
     * -------------------------------------------------------------------
     * Global initialization
     * -------------------------------------------------------------------
     */

    static {
        // init LogManager singleton instance
        String className = getPrivilegedSystemProperty("java.util.logging.manager"); //$NON-NLS-1$
        if (null != className) {
            manager = (LogManager) getInstanceByClass(className);
        }
        if (null == manager) {
            manager = new LogManager();
        }
        // init root logger
        manager.root = new Logger("", null); //$NON-NLS-1$
        manager.loggers.put("", manager.root); //$NON-NLS-1$
        // read configuration
        try {
            manager.readConfigurationImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // if global logger has been inited, set root as its parent
        if (null != Logger.global) {
            Logger.global.setParent(manager.root);
        }
    }

    /*
     * -------------------------------------------------------------------
     * Constructor
     * -------------------------------------------------------------------
     */
    /**
     * 
     * Default constructor. This is not public because there should be only one
     * <code>LogManager</code> instance, which can be get by
     * <code>LogManager.getLogManager(</code>. This is protected so that
     * application can subclass the object.
     */
    protected LogManager() {
        loggers = new Hashtable<String, Logger>();
        props = new Properties();
        listeners = new PropertyChangeSupport(this);
        // add shutdown hook to ensure that the associated resource will be
        // freed when JVM exits
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                Runtime.getRuntime().addShutdownHook(new Thread() {
                    public void run() {
                        reset();
                    }
                });
                return null;
            }
        });
    }

    /*
     * -------------------------------------------------------------------
     * Methods
     * -------------------------------------------------------------------
     */
    /*
     * Package private utilities Returns the line separator of the underlying
     * OS.
     */
    static String getSystemLineSeparator() {
        return lineSeparator;
    }

    /**
     * Check that the caller has <code>LoggingPermission("control")</code> so
     * that it is trusted to modify the configuration for logging framework. If
     * the check passes, just return, otherwise <code>SecurityException</code>
     * will be throwed.
     * 
     * @throws SecurityException
     *             if there is a security manager in operation and the invoker
     *             of this method does not have the required security permission
     *             <code>LoggingPermission("control")</code>
     */
    public void checkAccess() {
        if (null != System.getSecurityManager()) {
            System.getSecurityManager().checkPermission(perm);
        }
    }

    /**
     * Add a given logger into the hierarchical namespace. The
     * <code>Logger.addLogger()</code> factory methods call this method to add
     * newly created Logger. This returns false if a logger with the given name
     * has existed in the namespace
     * <p>
     * Note that the <code>LogManager</code> may only retain weak references
     * to registered loggers. In order to prevent <code>Logger</code> objects
     * from being unexpectedly garbage collected it is necessary for
     * <i>applications</i> to maintain references to them.
     * </p>
     * 
     * @param logger
     *            the logger to be added
     * @return true if the given logger is added into the namespace
     *         successfully, false if the logger of given name has existed in
     *         the namespace
     */
    public synchronized boolean addLogger(Logger logger) {
        String name = logger.getName();
        if (null != loggers.get(name)) {
            return false;
        }
        addToFamilyTree(logger, name);
        loggers.put(name, logger);
        if (name.length() == 0) {
            root = logger;
        }
        return true;
    }

    private void addToFamilyTree(Logger logger, String name) {
        Logger parent = null;
        // find parent
        int lastSeparator;
        String parentName = name;
        while ((lastSeparator = parentName.lastIndexOf('.')) != -1) {
            parentName = parentName.substring(0, lastSeparator);
            if ((parent = loggers.get(parentName)) != null) {
                logger.setParent(parent);
                break;
            }
        }
        if (parent == null && parent != root) {
            parent = root;
            logger.setParent(parent);
        }

        // find children
        Logger[] values = loggers.values().toArray(new Logger[0]);
        for (Logger child : values) {
            Logger oldParent = child.getParent();
            if (parent == oldParent
                    && (name.length() == 0 || child.getName().startsWith(
                            name + '.'))) {
                child.setParent(logger);
            }
        }
    }

    /**
     * Get the logger with the given name
     * 
     * @param name
     *            name of logger
     * @return logger with given name, or null if nothing is found
     */
    public synchronized Logger getLogger(String name) {
        return loggers.get(name);
    }

    /**
     * Get a <code>Enumeration</code> of all registered logger names
     * 
     * @return enumeration of registered logger names
     */
    public synchronized Enumeration<String> getLoggerNames() {
        return loggers.keys();
    }

    /**
     * Get the global <code>LogManager</code> instance
     * 
     * @return the global <code>LogManager</code> instance
     */
    public static LogManager getLogManager() {
        return manager;
    }

    /**
     * Get the value of property with given name
     * 
     * @param name
     *            the name of property
     * @return the value of property
     */
    public synchronized String getProperty(String name) {
        return props.getProperty(name);
    }

    /**
     * Reinitialize the properties and configuration. The initialization process
     * is same as the <code>LogManager</code> instantiation.
     * <p>
     * A <code>PropertyChangeEvent</code> must be fired.
     * </p>
     * 
     * @throws IOException
     *             if any IO related problems happened
     * @throws SecurityException
     *             if security manager exists and it determines that caller does
     *             not have the required permissions to perform this action
     */
    public void readConfiguration() throws IOException {
        readConfigurationImpl();
    }

    // use privilege code to get system property
    static String getPrivilegedSystemProperty(final String key) {
        return AccessController.doPrivileged(new PrivilegedAction<String>() {
            public String run() {
                return System.getProperty(key);
            }
        });
    }

    // get system property
    static String getSystemProperty(final String key) {
        return System.getProperty(key);
    }

    // use SystemClassLoader to load class from system classpath
    static Object getInstanceByClass(final String className) {
        try {
            Class clazz = ClassLoader.getSystemClassLoader().loadClass(
                    className);
            return clazz.newInstance();
        } catch (Exception e) {
            System.err
                    .println("Cannot get instance for class name:" + className); //$NON-NLS-1$
            System.err.println("Caused by " + e.toString()); //$NON-NLS-1$
            return null;
        }

    }

    // actual default initilization process
    private synchronized void readConfigurationImpl() throws IOException {
        checkAccess();
        boolean needInit = true;

        // check config class
        String configClassName = getSystemProperty("java.util.logging.config.class"); //$NON-NLS-1$
        if (null != configClassName) {
            if (null == getInstanceByClass(configClassName)) {
                throw new RuntimeException("Cannot instantiate " //$NON-NLS-1$
                        + configClassName);
            }
            needInit = false;
        }
        // if config class failed, check config file
        if (needInit) {
            String configFile = getSystemProperty("java.util.logging.config.file"); //$NON-NLS-1$
            if (null == configFile) {
                // if cannot find configFile, use default logging.properties
                configFile = new StringBuffer().append(
                        System.getProperty("java.home")).append(fileSeparator) //$NON-NLS-1$
                        .append("lib").append(fileSeparator).append( //$NON-NLS-1$
                                "logging.properties").toString(); //$NON-NLS-1$
            }
            InputStream input = null;
            try {
                input = new BufferedInputStream(new FileInputStream(configFile));
                readConfigurationImpl(input);
            } finally {
                try {
                    input.close();
                } catch (Exception e) {// ignore
                }
            }
        }
    }

    // actual initialization process from a given input stream
    private synchronized void readConfigurationImpl(InputStream ins)
            throws IOException {
        reset();
        props.load(ins);

        // configs
        parseConfigProp();

        // handlers
        parseHandlerProp();

        // set levels for logger
        initLevelForLoggers();
        listeners.firePropertyChange(null, null, null);
    }

    // init "level" properties for all registered loggers
    private void initLevelForLoggers() {
        Enumeration enumeration = props.propertyNames();
        while (enumeration.hasMoreElements()) {
            // search for all properties whose name is ended with ".level"
            String loggerLevel = (String) enumeration.nextElement();
            if (!loggerLevel.endsWith(".level")) { //$NON-NLS-1$
                continue;
            }
            // if find such properties, search for relevant registered logger
            String loggerName = loggerLevel.substring(0,
                    loggerLevel.length() - 6);
            Logger l = loggers.get(loggerName);
            if (null == l) {
                continue;
            }
            // if find relevant registered logger, set level for it
            String levelName = props.getProperty(loggerLevel);
            Level level = Level.parse(levelName);
            if (null == level) {
                continue;
            }
            l.setLevel(level);
        }
    }

    // parse "handlers" property and apply setting
    private void parseHandlerProp() {
        // Logger rootLogger = root.getLogger();
        String handlers = props.getProperty("handlers"); //$NON-NLS-1$
        if (null == root || null == handlers) {
            return;
        }
        StringTokenizer st = new StringTokenizer(handlers, " "); //$NON-NLS-1$
        while (st.hasMoreTokens()) {
            String handlerName = st.nextToken();
            Handler handler = (Handler) getInstanceByClass(handlerName);
            root.addHandler(handler);
            String level = props.getProperty(handlerName + ".level"); //$NON-NLS-1$
            if (null != level) {
                handler.setLevel(Level.parse(level));
            }
        }
    }

    // parse property "config" and apply setting
    private void parseConfigProp() {
        String configs = props.getProperty("config"); //$NON-NLS-1$
        if (null != configs) {
            StringTokenizer st = new StringTokenizer(configs, " "); //$NON-NLS-1$
            while (st.hasMoreTokens()) {
                String configerName = st.nextToken();
                getInstanceByClass(configerName);
            }
        }
    }

    /**
     * Reinitialize the properties and configuration from the given
     * <code>InputStream</code>
     * <p>
     * A <code>PropertyChangeEvent</code> must be fired.
     * </p>
     * 
     * @param ins
     *            the input stream.
     * @throws IOException
     *             if any IO related problems happened
     * @throws SecurityException
     *             if security manager exists and it determines that caller does
     *             not have the required permissions to perform this action
     */
    public void readConfiguration(InputStream ins) throws IOException {
        checkAccess();
        readConfigurationImpl(ins);
    }

    /**
     * Reset configuration.
     * <p>
     * All handlers are closed and removed from any named loggers. All loggers'
     * level is set to null, except the root logger's level is set to
     * <code>Level.INFO</code>.
     * </p>
     * 
     * @throws SecurityException
     *             if security manager exists and it determines that caller does
     *             not have the required permissions to perform this action
     */
    public void reset() {
        checkAccess();
        synchronized (this) {
            props.clear();
            Iterator it = loggers.values().iterator();
            while (it.hasNext()) {
                Logger l = (Logger) it.next();
                l.setLevel(null);
                Handler[] handlers = l.getHandlers();
                for (Handler element : handlers) {
                    l.removeHandler(element);
                    // close all handlers, when unknown exceptions happen,
                    // ignore them and go on
                    try {
                        element.close();
                    } catch (Exception e) {
                        // Ignored.
                    }
                }
            }
            if (null != root) {
                root.setLevel(Level.INFO);
            }
        }
    }

    /**
     * Add a <code>PropertyChangeListener</code>, which will be invoked when
     * the properties are reread.
     * 
     * @param l
     *            the <code>PropertyChangeListener</code> to be added
     * @throws SecurityException
     *             if security manager exists and it determines that caller does
     *             not have the required permissions to perform this action
     */
    public void addPropertyChangeListener(PropertyChangeListener l) {
        checkAccess();
        listeners.addPropertyChangeListener(l);
    }

    /**
     * Remove a <code>PropertyChangeListener</code>, do nothing if the given
     * listener is not found.
     * 
     * @param l
     *            the <code>PropertyChangeListener</code> to be removed
     * @throws SecurityException
     *             if security manager exists and it determines that caller does
     *             not have the required permissions to perform this action
     */
    public void removePropertyChangeListener(PropertyChangeListener l) {
        checkAccess();
        listeners.removePropertyChangeListener(l);
    }
}
