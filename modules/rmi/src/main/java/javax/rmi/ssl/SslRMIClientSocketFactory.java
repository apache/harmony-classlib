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
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package javax.rmi.ssl;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.rmi.server.RMIClientSocketFactory;
import java.security.AccessController;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class SslRMIClientSocketFactory implements RMIClientSocketFactory,
        Serializable {

    private static final long serialVersionUID = -8310631444933958385L;

    private static SSLSocketFactory factory;

    private static String enabledCipherSuites;

    private static String enabledProtocols;

    private static boolean isEnabledInitialized;

    public SslRMIClientSocketFactory() {
        if (factory == null) {
            factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        }
    }

    public Socket createSocket(String host, int port) throws IOException {

        SSLSocket soc = (SSLSocket) factory.createSocket(host, port);
        if (!isEnabledInitialized) {
            isEnabledInitialized = true;
            AccessController.doPrivileged(new java.security.PrivilegedAction() {

                public Object run() {
                    enabledCipherSuites = System
                            .getProperty("javax.rmi.ssl.client.enabledCipherSuites");
                    enabledProtocols = System
                            .getProperty("javax.rmi.ssl.client.enabledProtocols");
                    return null;
                }
            });
        }
        if (enabledCipherSuites != null) {
            soc.setEnabledCipherSuites(enabledCipherSuites.split(","));
        }

        if (enabledProtocols != null) {
            soc.setEnabledProtocols(enabledProtocols.split(","));
        }

        return soc;
    }

    public boolean equals(Object obj) {
        return this.getClass().equals(obj.getClass());
    }

    public int hashCode() {
        return "javax.rmi.ssl.SslRMIClientSocketFactory".hashCode();
    }

}
