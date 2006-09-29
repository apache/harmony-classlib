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

/**
 * @author  Mikhail A. Markov, Vasily Zakharov
 * @version $Revision: 1.1.2.2 $
 */
package org.apache.harmony.rmi.transport;

import java.io.IOException;
import java.io.Serializable;
import java.net.NoRouteToHostException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.RMISocketFactory;
import java.security.AccessController;

import org.apache.harmony.rmi.common.GetBooleanPropAction;
import org.apache.harmony.rmi.common.GetLongPropAction;
import org.apache.harmony.rmi.common.RMILog;
import org.apache.harmony.rmi.common.RMIProperties;
import org.apache.harmony.rmi.transport.proxy.HttpProxyRMISocketFactory;
import org.apache.harmony.rmi.transport.proxy.Proxy;
import org.apache.harmony.rmi.transport.proxy.ProxyRMISocketFactory;
import org.apache.harmony.rmi.transport.tcp.DirectRMISocketFactory;


/**
 * Default {@link RMISocketFactory} which is used by RMI runtime to create
 * client and server sockets. First it tries direct sockets and if
 * the attempt fails it tries HTTP-through sockets.
 *
 * @author  Mikhail A. Markov, Vasily Zakharov
 * @version $Revision: 1.1.2.2 $
 */
public class DefaultRMISocketFactory extends RMISocketFactory
        implements Serializable, RMIProperties {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8559677966812417163L;

    // Should we disable direct socket connections or not.
    private static final boolean disableDirectSocket =
            ((Boolean) AccessController.doPrivileged(
                    new GetBooleanPropAction(DISABLE_DIRECT_SOCKET_PROP)))
                            .booleanValue();

    // Should we disable HTTP tunneling or not.
    private static final boolean disableHttp =
            ((Boolean) AccessController.doPrivileged(
                    new GetBooleanPropAction(DISABLEHTTP_PROP)))
                            .booleanValue();

    // Should we try HTTP connections after every SocketException or not.
    private static final boolean eagerHttpFallback =
            ((Boolean) AccessController.doPrivileged(
                    new GetBooleanPropAction(EAGERHTTPFALLBACK_PROP)))
                            .booleanValue();

    // Direct sockets factory.
    private static final DirectRMISocketFactory directRsf =
                                                new DirectRMISocketFactory();

    // Proxy sockets factory.
    private static final ProxyRMISocketFactory proxyRsf =
                                                new HttpProxyRMISocketFactory();

    /*
     * Max period of time (in ms) for trying direct connections before
     * attempting HTTP. Default value is 15000 ms (15 sec.).
     */
    private static long connTimeout = ((Long) AccessController.doPrivileged(
            new GetLongPropAction(CONNECTTIMEOUT_PROP, 15000))).longValue();

    // Log for logging proxy connections activity.
    private static final RMILog proxyTransportLog =
            RMILog.getProxyTransportLog();

    /**
     * {@inheritDoc}
     */
    public Socket createSocket(String host, int port) throws IOException {
        if (proxyTransportLog.isLoggable(RMILog.BRIEF)) {
            proxyTransportLog.log(RMILog.BRIEF,
                    "Creating socket to [" + host + ':' + port + "].");
        }
        Socket s = null;
        Proxy proxy = new Proxy();

        if (disableDirectSocket) {
            // If direct connections are disabled, fallback to proxy connection.
            if (proxyTransportLog.isLoggable(RMILog.VERBOSE)) {
                proxyTransportLog.log(RMILog.VERBOSE,
                        "Direct socket connections disabled, "
                        + "trying proxy connection to ["
                        + host + ':' + port + "].");
            }
        } else {
            if (disableHttp || !(proxy.isSet())) {
                // If HTTP is not available, use simple direct connection.
                s = directRsf.createSocket(host, port);

                if (s == null) {
                    String msg = "Unable to connect to [" + host + ':' + port
                            + ']';
                    if (proxyTransportLog.isLoggable(RMILog.BRIEF)) {
                        proxyTransportLog.log(RMILog.BRIEF, msg);
                    }
                    throw new NoRouteToHostException(msg);
                }
            } else { // Both direct and HTTP connections are available.
                s = null;
                IOException ex = null;

                try {
                    s = directRsf.createSocket(host, port, (int) connTimeout);
                } catch (IOException ioe) {
                    ex = ioe;
                }

                if (s == null) {
                    // Direct socket attempt failed.
                    if (proxyTransportLog.isLoggable(RMILog.BRIEF)) {
                        proxyTransportLog.log(RMILog.BRIEF,
                                "Direct socket connection to ["
                                + host + ':' + port + "] failed.");
                    }

                    if (ex != null) {
                        if ((eagerHttpFallback
                                && !(ex instanceof SocketException))
                                || (!eagerHttpFallback
                                    && !(ex instanceof UnknownHostException)
                                    && !(ex instanceof NoRouteToHostException))) {
                            throw ex;
                        } else {
                            if (proxyTransportLog.isLoggable(RMILog.VERBOSE)) {
                                proxyTransportLog.log(RMILog.VERBOSE,
                                        "Trying proxy connection to ["
                                        + host + ':' + port + "].");
                            } // Falling through to HTTP connection attempt.
                        }
                    } else {
                        throw new NoRouteToHostException("Connection "
                                + "to [" + host + ':' + port + "] timed out");
                    }
                } else if (proxyTransportLog.isLoggable(RMILog.BRIEF)) {
                    proxyTransportLog.log(RMILog.BRIEF,
                            "Direct socket connection to [" + host + ':' + port
                            + "] from port " + s.getLocalPort()+ " succeeded.");
                }
            }
        }

        // Either disableDirectSocket or fallback from direct connection attempt.
        if (s == null) {
            s = proxyRsf.createSocket(proxy, host, port);

            if (s == null) {
                String msg = "Proxy connection to [" + host + ':' + port
                        + "] failed";
                if (proxyTransportLog.isLoggable(RMILog.BRIEF)) {
                    proxyTransportLog.log(RMILog.BRIEF, msg);
                }

                throw new NoRouteToHostException(msg);
            }

            if (proxyTransportLog.isLoggable(RMILog.BRIEF)) {
                proxyTransportLog.log(RMILog.BRIEF,
                        "Proxy connection to [" + host + ':' + port
                        + "] from port " + s.getLocalPort()+ " succeeded.");
            }
        }
        return s;
    }

    /**
     * {@inheritDoc}
     */
    public ServerSocket createServerSocket(int port) throws IOException {
        return ((!disableDirectSocket && disableHttp)
                ? (RMISocketFactory) directRsf : proxyRsf).createServerSocket(
                        port);
    }

    /**
     * Helper method: returns non-null RMIClientSocketFactory.
     * Returns specified RMIClientSocketFactory if it is not null; otherwise
     * returns result of getSocketFactory method call if it is not null;
     * ottherwise returns result of getDefaultSocketFactory method call.
     *
     * @param csf RMIClientSocketFactory to check
     *
     * @return non-null RMIClientSocketFactory
     */
    public static RMIClientSocketFactory getNonNullClientFactory(
            RMIClientSocketFactory csf) {
        RMIClientSocketFactory factory =
                (csf == null) ? getSocketFactory() : csf;
        return (factory == null) ? getDefaultSocketFactory() : factory;
    }

    /**
     * Helper method: returns non-null RMIServerSocketFactory.
     * Returns specified RMIServerSocketFactory if it is not null; otherwise
     * returns result of getSocketFactory method call if it is not null;
     * ottherwise returns result of getDefaultSocketFactory method call.
     *
     * @param ssf RMIServerSocketFactory to check
     *
     * @return non-null RMIServerSocketFactory
     */
     public static RMIServerSocketFactory getNonNullServerFactory(
            RMIServerSocketFactory ssf) {
        RMIServerSocketFactory factory =
                (ssf == null) ? getSocketFactory() : ssf;
        return (factory == null) ? getDefaultSocketFactory() : factory;
    }
}
