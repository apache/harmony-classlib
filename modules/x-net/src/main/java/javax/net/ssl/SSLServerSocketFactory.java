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

package javax.net.ssl;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Security;

import javax.net.ServerSocketFactory;

public abstract class SSLServerSocketFactory extends ServerSocketFactory {
    // TODO EXPORT CONTROL

    // The default SSL socket factory
    private static ServerSocketFactory defaultServerSocketFactory;

    private static String defaultName;

    public static synchronized ServerSocketFactory getDefault() {
        if (defaultServerSocketFactory != null) {
            return defaultServerSocketFactory;
        }
        if (defaultName == null) {
            AccessController.doPrivileged(new PrivilegedAction<Void>() {
                public Void run() {
                    defaultName = Security.getProperty("ssl.ServerSocketFactory.provider");
                    if (defaultName != null) {
                        ClassLoader cl = Thread.currentThread().getContextClassLoader();
                        if (cl == null) {
                            cl = ClassLoader.getSystemClassLoader();
                        }
                        try {
                            final Class<?> ssfc = Class.forName(defaultName, true, cl);
                            defaultServerSocketFactory = (ServerSocketFactory) ssfc.newInstance();
                        } catch (Exception e) {
                        }
                    }
                    return null;
                }
            });
        }
        if (defaultServerSocketFactory == null) {
            // Try to find in providers
            SSLContext context = DefaultSSLContext.getContext();
            if (context != null) {
                defaultServerSocketFactory = context.getServerSocketFactory();
            }
        }
        if (defaultServerSocketFactory == null) {
            // Use internal dummy implementation
            defaultServerSocketFactory = new DefaultSSLServerSocketFactory(
                    "No ServerSocketFactory installed");
        }
        return defaultServerSocketFactory;
    }

    protected SSLServerSocketFactory() {
        super();
    }

    public abstract String[] getDefaultCipherSuites();

    public abstract String[] getSupportedCipherSuites();
}
