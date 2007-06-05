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
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.ObjectInstance;
import javax.management.MalformedObjectNameException;

import org.apache.harmony.logging.internal.nls.Messages;

/**
 * <code>LogManager</code> is used to manage named <code>Logger</code>s and
 * any shared logging properties.
 * <p>
 * There is one global <code>LogManager</code> instance in the application,
 * which can be obtained by calling the static method
 * <code>LogManager.getLogManager()</code>.
 * </p>
 * <p>
 * All methods on this type can be taken as being thread safe.
 * </p>
 * <p>
 * The <code>LogManager</code> class can be specified by the
 * "java.util.logging.manager" system property. If the property is unavailable
 * or invalid <code>java.util.logging.LogManager</code> will be used by
 * default.
 * </p>
 * <p>
 * On initialization, <code>LogManager</code> reads its configuration data
 * from a properties file, which by default is the "lib/logging.properties" file
 * in the JRE directory.
 * </p>
 * <p>
 * However, two system properties can be used instead to customize the
 * initialization of the <code>LogManager</code>:
 * <ul>
 * <li>"java.util.logging.config.class"</li>
 * <li>"java.util.logging.config.file"</li>
 * </ul>
 * </p>
 * <p>
 * These properties can be set either by using the Preferences API, as a command
 * line option or by passing the appropriate system property definitions to
 * JNI_CreateJavaVM.
 * </p>
 * <p>
 * The "java.util.logging.config.class" property should specify a class name. If
 * it is set, this class will be loaded and instantiated during
 * <code>LogManager</code>'s initialization, so that this object's default
 * constructor can read the initial configuration and define properties for the
 * <code>LogManager</code>.
 * </p>
 * <p>
 * The "java.util.logging.config.file" system property can be used to specify a
 * properties file if the "java.util.logging.config.class" property has not been
 * used. This file will be read instead of the default properties file.
 * </p>
 * <p>
 * Some global logging properties are as follows:
 * <ul>
 * <li>"handlers" - a list of handler classes, separated by whitespace. These
 * classes must be subclasses of <code>Handler</code> and must have a public
 * no-argument constructor. They will be registered with the root
 * <code>Logger</code>.</li>
 * <li>"config" - a list of configuration classes, separated by whitespace.
 * These classes should also have a public no-argument default constructor,
 * which should contain all the code for applying that configuration to the
 * logging system.
 * </ul>
 * </p>
 * <p>
 * Besides global properties, properties for individual <code>Loggers</code>
 * and <code>Handlers</code> can be specified in the property files. The names
 * of these properties will start with the fully qualified name of the handler
 * or logger.
 * </p>
 * <p>
 * The <code>LogManager</code> organizes <code>Loggers</code> based on their
 * fully qualified names. For example, "x.y.z" is child of "x.y".
 * </p>
 * <p>
 * Levels for <code>Loggers</code> can be defined by properties whose name end
 * with ".level". For example, "alogger.level = 4" sets the level for the logger
 * "alogger" to 4, Any children of "alogger" will also be given the level 4
 * unless specified lower down in the properties file. The property ".level"
 * will set the log level for the root logger.
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
    private static final String lineSeparator =
            getPrivilegedSystemProperty("line.separator"); //$NON-NLS-1$

    // The shared logging permission
    private static final LoggingPermission perm = new LoggingPermission(
            "control", null); //$NON-NLS-1$

    // the singleton instance
    static LogManager manager;
    
    /**
     * <p>The String value of the {@link LoggingMXBean}'s ObjectName.</p>
     */
    public static final String LOGGING_MXBEAN_NAME =
            "java.util.logging:type=Logging"; //$NON-NLS-1$

    /**
     * Get the <code>LoggingMXBean</code> instance
     * 
     * @return the <code>LoggingMXBean</code> instance
     */
    public static LoggingMXBean getLoggingMXBean() {
        try {
            ObjectName loggingMXBeanName = new ObjectName(LOGGING_MXBEAN_NAME);
            MBeanServer platformBeanServer =
                    ManagementFactory.getPlatformMBeanServer();
            Set loggingMXBeanSet = platformBeanServer.queryMBeans(
                    loggingMXBeanName, null);

            if (loggingMXBeanSet.size() != 1) {
                // logging.21=There Can Be Only One logging MX bean.
                throw new AssertionError(Messages.getString("logging.21"));
            }

            Iterator i = loggingMXBeanSet.iterator();
            ObjectInstance loggingMXBeanOI = (ObjectInstance) i.next();
            String lmxbcn = loggingMXBeanOI.getClassName();
            Class lmxbc = Class.forName(lmxbcn);
            Method giMethod = lmxbc.getDeclaredMethod("getInstance");
            giMethod.setAccessible(true);
            LoggingMXBean lmxb = (LoggingMXBean)
                    giMethod.invoke(null, new Object[] {});

            return lmxb;
        } catch (Exception e) {
            //TODO
            //e.printStackTrace();
        }
        // logging.22=Exception occurred while getting the logging MX bean.
        throw new AssertionError(Messages.getString("logging.22")); //$NON-NLS-1$
    }

    /*
     * -------------------------------------------------------------------
     * Instance variables
     * -------------------------------------------------------------------
     */
    //FIXME: use weak reference to avoid heap memory leak    
    private Hashtable<String, Logger> loggers;

    // the configuration properties
    private Properties props;

    // the property change listener
    private PropertyChangeSupport listeners;

    /*
     * -------------------------------------------------------------------
     * Global initialization
     * -------------------------------------------------------------------
     */

    static {
		// init LogManager singleton instance
		AccessController.doPrivileged(new PrivilegedAction<Object>() {
			public Object run() {
				String className = System.getProperty(
                        "java.util.logging.manager"); //$NON-NLS-1$
                
				if (null != className) {
					manager = (LogManager) getInstanceByClass(className);
				}
				if (null == manager) {
					manager = new LogManager();
				}

				// read configuration
				try {
					manager.readConfiguration();
				} catch (Exception e) {
					e.printStackTrace();
				}

				// if global logger has been initialized, set root as its parent
                Logger root = new Logger("", null); //$NON-NLS-1$
                root.setLevel(Level.INFO);
                Logger.global.setParent(root);
                
                manager.addLogger(root);
                manager.addLogger(Logger.global);
                return null;
			}
		});
	}

    /**
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
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                Runtime.getRuntime().addShutdownHook(new Thread() {
                    @Override
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
     * will be thrown.
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
        logger.setManager(this);
        return true;
    }


    private void addToFamilyTree(Logger logger, String name) {
        Logger parent = null;
        // find parent
        int lastSeparator;
        String parentName = name;
        while ((lastSeparator = parentName.lastIndexOf('.')) != -1) {
            parentName = parentName.substring(0, lastSeparator);
            parent = loggers.get(parentName);
            if (parent != null) {
                logger.internalSetParent(parent);
                break;
            } else if (getProperty(parentName+".level") != null || //$NON-NLS-1$
                    getProperty(parentName+".handlers") != null) { //$NON-NLS-1$
                parent = Logger.getLogger(parentName);
                logger.internalSetParent(parent);
                break;
            }
        }
        if (parent == null && null != (parent = loggers.get(""))) { //$NON-NLS-1$
            logger.internalSetParent(parent);
        }

        // find children
        //TODO: performance can be improved here?
        Collection<Logger> allLoggers = loggers.values();
        for (Logger child : allLoggers) {
            Logger oldParent = child.getParent();
            if (parent == oldParent
                    && (name.length() == 0 || child.getName().startsWith(
                            name + '.'))) {
                child.setParent(logger);
                if (null != oldParent) {
                    //-- remove from old parent as the parent has been changed
                    oldParent.removeChild(child);
                }
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
    public String getProperty(String name) {
        return props.getProperty(name);
    }

    /**
     * Re-initialize the properties and configuration. The initialization process
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
        checkAccess();
        // check config class
        String configClassName = System.getProperty(
                "java.util.logging.config.class"); //$NON-NLS-1$
        if (null == configClassName || null == getInstanceByClass(configClassName)) {
            // if config class failed, check config file       
            String configFile = System.getProperty(
                    "java.util.logging.config.file"); //$NON-NLS-1$

            if (null == configFile) {
                // if cannot find configFile, use default logging.properties
                configFile = new StringBuilder().append(
                        System.getProperty("java.home")).append(File.separator) //$NON-NLS-1$
                        .append("lib").append(File.separator).append( //$NON-NLS-1$
                        "logging.properties").toString(); //$NON-NLS-1$
            }

            InputStream input = null;
            try {
                input = new BufferedInputStream(new FileInputStream(configFile));
                readConfigurationImpl(input);
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (Exception e) {// ignore
                    }
                }
            }
        }
    }

    // use privilege code to get system property
    static String getPrivilegedSystemProperty(final String key) {
        return AccessController.doPrivileged(new PrivilegedAction<String>() {
            public String run() {
                return System.getProperty(key);
            }
        });
    }

    // use SystemClassLoader to load class from system classpath
    static Object getInstanceByClass(final String className) {
        try {
            Class<?> clazz = ClassLoader.getSystemClassLoader().loadClass(
                    className);
            return clazz.newInstance();
        } catch (Exception e) {
            try {
                Class<?> clazz = Thread.currentThread()
                        .getContextClassLoader().loadClass(className);
                return clazz.newInstance();
            } catch (Exception innerE) {
                //logging.20=Loading class "{0}" failed
                System.err.println(Messages.getString(
                        "logging.20", className)); //$NON-NLS-1$
                System.err.println(innerE);
                return null;
            }
        }

    }

    // actual initialization process from a given input stream
    private synchronized void readConfigurationImpl(InputStream ins)
            throws IOException {
        reset();
        props.load(ins);
        
        // parse property "config" and apply setting
        String configs = props.getProperty("config"); //$NON-NLS-1$
        if (null != configs) {
            StringTokenizer st = new StringTokenizer(configs, " "); //$NON-NLS-1$
            while (st.hasMoreTokens()) {
                String configerName = st.nextToken();
                getInstanceByClass(configerName);
            }
        }
        
        // set levels for logger
        Collection<Logger> allLoggers = loggers.values();
        for(Logger logger : allLoggers){
            String property = props.getProperty(
                    logger.getName()+".level"); //$NON-NLS-1$
            if(null != property){
                logger.setLevel(Level.parse(property));
            }
        }
        listeners.firePropertyChange(null, null, null);
    }


    /**
     * Re-initialize the properties and configuration from the given
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
        props = new Properties();
        Enumeration<String> names = getLoggerNames();
        while(names.hasMoreElements()){
            String name = names.nextElement();
            Logger logger = getLogger(name);
            if(logger != null){
                logger.reset();
            }
        }
        Logger root = loggers.get(""); //$NON-NLS-1$
        if (null != root) {
            root.setLevel(Level.INFO);
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
        if(l == null){
            throw new NullPointerException();
        }
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
