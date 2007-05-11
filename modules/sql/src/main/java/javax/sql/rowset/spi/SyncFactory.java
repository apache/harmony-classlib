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

package javax.sql.rowset.spi;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import org.apache.harmony.sql.internal.nls.Messages;

/**
 * A singleton factory class that generates SyncProvider instances. There are
 * three places to search SyncProviders: system properties, resource files and
 * the JNDI context.
 * 
 * Applications can also use it to add and remove SyncProviders at runtime. By
 * default there are two providers offered by RI:
 * com.sun.rowset.providers.RIOptimisticProvider and
 * com.sun.rowset.providers.RIXMLProvider. The former is the default provider
 * for RowSet that does not specify any provider while the latter is usually
 * used for WebRowSet instances.
 * 
 */
public class SyncFactory {
    public static String ROWSET_SYNC_PROVIDER = "rowset.provider.classname"; //$NON-NLS-1$

    public static String ROWSET_SYNC_VENDOR = "rowset.provider.vendor"; //$NON-NLS-1$

    public static String ROWSET_SYNC_PROVIDER_VERSION = "rowset.provider.version"; //$NON-NLS-1$

    private static SyncFactory instance = new SyncFactory();

    private static final String ROWSET_PROPERTIES = "rowset.properties"; //$NON-NLS-1$

    private static Hashtable<String, SyncProvider> providers = new Hashtable<String, SyncProvider>();

    private static Context ctx;

    private static String resLocation;

    private static boolean initialized;

    static {
        // the properties file is located at ${java.home}/lib/rowset.properties
        // In RI, it is in ${java.home}/lib/resources.jar/rowset.properties
        resLocation = new StringBuilder(System.getProperty("java.home")).append( //$NON-NLS-1$
                        System.getProperty("file.separator")).append("lib").append( //$NON-NLS-1$ //$NON-NLS-2$
                        System.getProperty("file.separator")).append(ROWSET_PROPERTIES) //$NON-NLS-1$
                .toString();
    }

    private static String getProviderProperties(Properties prop, String inital,
            int index) {
        StringBuilder builder = new StringBuilder(inital);
        builder.append("."); //$NON-NLS-1$
        builder.append(index);

        return prop.getProperty(builder.toString());
    }

    private static Enumeration<SyncProvider> getRegisteredProvidersImpl() {
        if (!initialized) {
            // 1. load from System property
            String sysProvider = System.getProperty(ROWSET_SYNC_PROVIDER);
            if (sysProvider != null) {
                providers.put(sysProvider, new ProviderImpl(sysProvider));
            }

            // 2. looks in the resource file
            Properties rowsetProp = new Properties();
            try {
                FileInputStream resInput = new FileInputStream(resLocation);
                rowsetProp.load(resInput);
                resInput.close();
            } catch (IOException e) {
                // ignore
            }
            int index = 0;
            while (true) {
                String className = getProviderProperties(rowsetProp,
                        ROWSET_SYNC_PROVIDER, index);
                if (null == className) {
                    break;
                }

                String vendor = getProviderProperties(rowsetProp,
                        ROWSET_SYNC_VENDOR, index);
                String version = getProviderProperties(rowsetProp,
                        ROWSET_SYNC_PROVIDER_VERSION, index);
                providers.put(className, new ProviderImpl(className, vendor,
                        version));
                index++;
            }

            // 3. checks the JNDI context that has been set
            if (ctx != null) {
                try {
                    NamingEnumeration<Binding> bindings = ctx.listBindings("");
                    while (bindings.hasMore()) {
                        Binding bind = bindings.next();
                        providers.put(bind.getName(), (SyncProvider) bind
                                .getObject());
                    }
                } catch (NamingException e) {
                    // ignore
                }
            }
            initialized = true;
        }
        return providers.elements();
    }

    // This class does not have public constructor
    private SyncFactory() {
        // do nothing
    }

    public static void registerProvider(String providerID)
            throws SyncFactoryException {
        throw new UnsupportedOperationException();
    }

    /**
     * Answers the singleton instance of the SyncFactory.
     * 
     * @return - the singleton instance of the SyncFactory.
     */
    public static SyncFactory getSyncFactory() {
        return instance;
    }

    public static void unregisterProvider(String providerID)
            throws SyncFactoryException {
        throw new UnsupportedOperationException();
    }

    public static SyncProvider getInstance(String providerID)
            throws SyncFactoryException {
        throw new UnsupportedOperationException();
    }

    /**
     * Answers the collection of SyncProvider instances that can be retrieved.
     * RowSet implementation is able to use any member in this enumeration.
     * 
     * @return - the collection of SyncProvider registered in SyncFactory.
     * @throws SyncFactoryException
     */
    public static Enumeration<SyncProvider> getRegisteredProviders()
            throws SyncFactoryException {
        return getRegisteredProvidersImpl();
    }

    public static void setLogger(Logger logger) {
        throw new UnsupportedOperationException();
    }

    public static void setLogger(Logger logger, Level level) {
        throw new UnsupportedOperationException();
    }

    public static Logger getLogger() throws SyncFactoryException {
        throw new UnsupportedOperationException();
    }

    /**
     * Sets the JNDI context from which the implementation of SyncProvider can
     * be got.
     * 
     * @param ctx -
     *            the JNDI context
     * @throws SyncFactoryException -
     *             if the given JNDI context is null
     */
    public static void setJNDIContext(Context ctx) throws SyncFactoryException {
        if (null == ctx) {
            throw new SyncFactoryException(Messages.getString("sql.41")); //$NON-NLS-1$
        }
        SyncFactory.ctx = ctx;
    }
}
