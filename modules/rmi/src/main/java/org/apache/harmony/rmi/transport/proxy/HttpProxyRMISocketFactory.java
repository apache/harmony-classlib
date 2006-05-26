/*
 * Copyright 2005-2006 The Apache Software Foundation or its licensors, as applicable
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
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author  Mikhail A. Markov, Vasily Zakharov
 * @version $Revision: 1.1.2.2 $
 */
package org.apache.harmony.rmi.transport.proxy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.AccessController;

import org.apache.harmony.rmi.common.GetBooleanPropAction;
import org.apache.harmony.rmi.common.RMILog;


/**
 * Socket factory for HTTP proxy connections. Returns {@link HttpOutboundSocket}
 * for client and {@link HttpServerSocket} for server sockets.
 *
 * @author  Mikhail A. Markov, Vasily Zakharov
 * @version $Revision: 1.1.2.2 $
 */
public class HttpProxyRMISocketFactory extends ProxyRMISocketFactory
        implements ProxyConstants  {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8113740863920118588L;

    /**
     * {@inheritDoc}
     */
    public Socket createSocket(Proxy proxy, String host, int port)
            throws IOException {
        Socket s;

        // Check if plain HTTP is disabled.
        if (((Boolean) AccessController.doPrivileged(new GetBooleanPropAction(
                DISABLE_PLAIN_HTTP_PROP))).booleanValue()) {
            if (proxyTransportLog.isLoggable(RMILog.VERBOSE)) {
                proxyTransportLog.log(RMILog.VERBOSE,
                        "Plain HTTP connections disabled, "
                        + "trying CGI connection.");
            }
        } else {
            try {
                // Try plain HTTP connection.
                s = new HttpOutboundSocket(proxy, host, port, false);

                if (proxyTransportLog.isLoggable(RMILog.VERBOSE)) {
                    proxyTransportLog.log(RMILog.VERBOSE,
                            "Plain HTTP connection to [" + host + ':' + port
                            + "] from port " + s.getLocalPort()+ " succeeded.");
                }

                return s;
            } catch (IOException e) {
                if (proxyTransportLog.isLoggable(RMILog.VERBOSE)) {
                    proxyTransportLog.log(RMILog.VERBOSE,
                            "Plain HTTP connection to ["
                            + host + ':' + port + "] failed: " + e
                            + ". Trying CGI connection.");
                }
            }
        }

        try {
            // Try CGI HTTP connection.
            s = new HttpOutboundSocket(proxy, host, port, true);

            if (proxyTransportLog.isLoggable(RMILog.VERBOSE)) {
                proxyTransportLog.log(RMILog.VERBOSE,
                        "CGI HTTP connection to [" + host + ':' + port
                        + "] from port " + s.getLocalPort()+ " succeeded.");
            }
            return s;
        } catch (IOException e) {
            if (proxyTransportLog.isLoggable(RMILog.VERBOSE)) {
                proxyTransportLog.log(RMILog.VERBOSE,
                        "CGI HTTP connection to ["
                        + host + ':' + port + "] failed: " + e);
            }
            throw e;
        }
    }

    /**
     * {@inheritDoc}
     */
    public ServerSocket createServerSocket(int port) throws IOException {
        return new HttpServerSocket(port);
    }
}
