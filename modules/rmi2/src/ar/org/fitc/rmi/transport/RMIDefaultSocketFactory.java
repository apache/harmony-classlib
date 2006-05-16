/* 
*  Copyright 2005 The Apache Software Foundation or its licensors, as applicable. 
* 
*  Licensed under the Apache License, Version 2.0 (the "License"); 
*  you may not use this file except in compliance with the License. 
*  You may obtain a copy of the License at 
* 
*    http://www.apache.org/licenses/LICENSE-2.0 
* 
*  Unless required by applicable law or agreed to in writing, software 
*  distributed under the License is distributed on an "AS IS" BASIS, 
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
*  See the License for the specific language governing permissions and 
*  limitations under the License. 
*/
package ar.org.fitc.rmi.transport;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.NoRouteToHostException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.rmi.server.RMISocketFactory;
import java.util.HashMap;
import java.util.Map;

import ar.org.fitc.rmi.transport.http.HttpSocketClientSide;
import ar.org.fitc.rmi.utils.Pair;
import ar.org.fitc.rmi.utils.PropertiesReader;

/**
 * This is the default {@link java.rmi.server.RMISocketFactory} used by the RMI 
 * Runtime.
 * 
 * @author Gustavo Petri
 * @author Diego Raúl Mercado
 */
public final class RMIDefaultSocketFactory extends RMISocketFactory {

	/**
	 * Is true if the system's property <code>java.rmi.server.disableHttp</code>
	 * and <code>http.proxyHost</code> are true.
	 */
	private static boolean enableHttp;

	/**
	 * Indicates the value returned by the system's property
	 * <code>ar.org.fitc.rmi.transport.proxy.leaseTime</code>. <br>
	 * Indicates the maximum value to wait until trying again the fallback
	 * mechanism.
	 */
	private static long leaseTime;

	/**
	 * Indicates the value returned by the system's property
	 * <code>ar.org.fitc.rmi.transport.proxy.eagerHttpFallback</code>. <br>
	 * If the <code>java.rmi.server.disableHttp</code> is false and this
	 * property is set to true then would try http tunneling if a
	 * <code>SocketException</code> ocurrs
	 */
	private static boolean eagerHttpFallback;

	/**
	 * Indicates the value returned by the system's property
	 * <code>http.proxyHost</code>. <br>
	 * Represents the IP or address name of the host that act as a proxy. Will
	 * be used to forward the remote call invocation
	 */
	private static String proxyHost;

	/**
	 * Indicates the value returned by the system's property
	 * <code>http.proxyPort</code>. <br>
	 * Represents the port of the host that act as a proxy. The default value is
	 * 80. Will be used to forward the remote call invocation
	 */
	private static int proxyPort;

	/**
	 * Indicates the value returned by the system's property
	 * <code>ar.org.fitc.rmi.transport.proxy.connectTimeout</code>. <br>
	 * Sets the maximum time (in milliseconds) that the runtime will wait for a
	 * direct connection attempt to establish, before trying http tunneling.
	 * It's used only if <code>java.rmi.server.disableHttp</code> is false and
	 * <code>http.proxyHost</code> is set.
	 */
	private static int connectTimeout;

	/**
	 * Indicates the value returned by the system's property
	 * <code>ar.org.fitc.rmi.transport.proxy.httpToPortOnly</code>. <br>
	 * Setting this value to true will only try to establish a connection
	 * through the proxy that was specified in <code>http.proxyHost</code>.
	 * <br>
	 * This avoid the default fallback mechanism.
	 */
	private static boolean proxyOnly;

	/**
	 * Indicates the value returned by the system's property
	 * <code>ar.org.fitc.rmi.transport.proxy.httpToCgiOnly</code>. <br>
	 * Setting this value to true will only try to establish a connection
	 * through the proxy that was specified in <code>http.proxyHost</code> and
	 * execute the cgi's script at the web server. <br>
	 * This avoid the default fallback mechanism.
	 */
	private static boolean cgiOnly;

	/**
	 * For each host and port stores the FallBackType and the last time that a
	 * socket has been created for that parameters.
	 */
	private static Map<Pair<String, Integer>, Pair<FallBackType, Long>> connType;

	static {

		/*
		 * READING PROPERTIES
		 */

		leaseTime = PropertiesReader.readLong(
				"ar.org.fitc.rmi.transport.proxy.leaseTime", 3600000L); // 1
		// hour

		connectTimeout = PropertiesReader.readInt(
				"ar.org.fitc.rmi.transport.proxy.connectTimeout", 15000);

		proxyHost = PropertiesReader.readString("http.proxyHost");

		proxyPort = PropertiesReader.readInt("http.proxyPort", 80);

		eagerHttpFallback = PropertiesReader.readBoolean(
				"ar.org.fitc.rmi.transport.proxy.eagerHttpFallback", false);

		proxyOnly = PropertiesReader.readBoolean(
				"ar.org.fitc.rmi.transport.proxy.httpToPortOnly", false);

		cgiOnly = PropertiesReader.readBoolean(
				"ar.org.fitc.rmi.transport.proxy.httpToCgiOnly", false);

		boolean disableHttp = PropertiesReader.readBoolean(
				"java.rmi.server.disableHttp", false);

		enableHttp = proxyHost != null && !disableHttp;

		connType = new HashMap<Pair<String, Integer>, Pair<FallBackType, Long>>();
	}

	/**
	 * Create a socket through a three-tiered approach: first, attempt to create
	 * a default
	 * {@link Socket} with the specified host and port. <br>
	 * If that fails, <code>java.rmi.server.disableHttp</code> is enabled and
	 * <code>http.proxyHost</code> property is set, attempt to create a
	 * {@link HttpSocketClientSide} with the specified
	 * <code>http.proxyHost</code> and <code>http.proxyPort</code>. That
	 * is, specifying the folowing url:
	 * <code>http://&#60;host&#62;:&#60;port&#62;</code> <br>
	 * Finally, attempts to invoke a cgi-script on the specified proxy with the
	 * following url:
	 * <code>http://&#60;host&#62;:80/cgi-bin/java-rmi?forward=&#60;port&#62;</code>
	 * 
	 * @param host
	 *            the host to connect the socket
	 * @param port
	 *            the port to connect the socket
	 * @return a new {@link Socket} instance connected to the host and
	 *         port specified
	 * @throws IOException
	 *             if de I/O operation fails
	 */
	@Override
	public final Socket createSocket(String host, int port) throws IOException {
		Pair<Socket, FallBackType> sockPair = null;
		Pair<String, Integer> inetAddr = new Pair<String, Integer>(host, port);

		// is there a reference ?
		Pair<FallBackType, Long> fallBackPair = connType.get(inetAddr);
		if (fallBackPair != null) {
			if (fallBackPair.getSecond() < System.currentTimeMillis()
					- leaseTime) {
				// the lease time has expired do the fallback ...
				sockPair = fallBack(host, port);
			} else {
				try {
					sockPair = new Pair<Socket, FallBackType>(connect(
							fallBackPair.getFirst(), host, port), fallBackPair
							.getFirst());
				} catch (IOException e) {
					// do the fallback...
					sockPair = fallBack(host, port);
				}
			}
		} else {
			// do the fallback...
			sockPair = fallBack(host, port);
		}
		// put / update reference
		connType.put(inetAddr, new Pair<FallBackType, Long>(sockPair
				.getSecond(), System.currentTimeMillis()));

		return sockPair.getFirst();
	}

	/**
	 * Creates a new {@link RMIServerSocket} bound to the specified port.
	 * 
	 * @param port
	 *            the port to bind the server
	 * @return a new {@link RMIServerSocket} bound to the specified port.
	 * @throws IOException
	 *             if de I/O operation fails
	 */
	@Override
	public final ServerSocket createServerSocket(int port) throws IOException {
		return new RMIServerSocket(port);
	}

	/**
	 * Implements the fallback logic, which will attempt all connection types
	 * until one success.
	 * 
	 * @param host
	 *            The host name of the RMI Server
	 * @param port
	 *            The port of the RMI Server
	 * @return A {@link Pair} object containing a {@link Socket} bound 
	 * 			to the appropriate server, and the {@link FallBackType} 
	 * 			object representing the approach used to establish the connection.
	 * @throws IOException
	 *             If all connection types have failed.
	 */
	private final Pair<Socket, FallBackType> fallBack(String host, int port)
			throws IOException {
		FallBackType fbType = null;
		IOException returnedException = null;
		Socket newSock = null;
		boolean isEstablished = false;
		boolean notFallback = proxyOnly || cgiOnly;

		try {
			if (enableHttp && notFallback) {
				if (proxyOnly) {
					newSock = connect(FallBackType.PROXY, host, port);
					fbType = FallBackType.PROXY;
				} else if (cgiOnly) {
					newSock = connect(FallBackType.PROXY_CGI, host, port);
					fbType = FallBackType.PROXY_CGI;
				}
			} else {
				// DIRECT CONNECTION
				newSock = connect(FallBackType.DIRECT, host, port);
				fbType = FallBackType.DIRECT;
			}
			isEstablished = true;
		} catch (UnknownHostException e) {
			returnedException = e;
		} catch (NoRouteToHostException e) {
			returnedException = e;
		} catch (SocketTimeoutException e) {
			// this property has to be set and is catched
			// when newSock tries to connect with a "connectTimeout"
			returnedException = e;
		} catch (SocketException e) {
			// if disableHttp property has not been set (checks this value in
			// the next if block) and eagerHttpFallback is true...
			if (!(eagerHttpFallback)) {
				throw e;
			}
			returnedException = e;
		}

		// HTTP
		if (!isEstablished && enableHttp && !notFallback) {
			// PROXY_HOST ON PROXY_PORT USING HTTP
			try {
				newSock = connect(FallBackType.PROXY, host, port);
				fbType = FallBackType.PROXY;
				isEstablished = true;
			} catch (NoRouteToHostException e) {
				returnedException = e;
			} catch (UnknownHostException e) {
				returnedException = e;
			}

			if (!isEstablished) {
				// PROXY_CGI ON RMI SERVER ON PORT 80 USING HTTP
				newSock = connect(FallBackType.PROXY_CGI, host, port);
				fbType = FallBackType.PROXY_CGI;
				isEstablished = true;
			}
		}

		if (!isEstablished) {
			throw returnedException;
		}
		return new Pair<Socket, FallBackType>(newSock, fbType);
	}

	/**
	 * Attempts a connection to the appropriate server, depending on the
	 * specified {@link FallBackType} parameter.
	 * 
	 * @param fallBackType
	 *            The type of connection (Direct, HTTPProxy, HTTPCGI) that will
	 *            be used.
	 * @param host
	 *            The host name of the RMI Server
	 * @param port
	 *            The port of the RMI Server
	 * @return A {@link Socket} bound to the appropriate server.
	 * @throws IOException
	 *             If the connection attempt for the specified
	 *             {@link FallBackType} has failed.
	 */
	private final Socket connect(FallBackType fallBackType, String host,
			int port) throws IOException {
		Socket newSock = null;
		if (fallBackType.equals(FallBackType.DIRECT)) {

			// DIRECT CONNECTION with TimeOut
			if (enableHttp) {
				newSock = new Socket();
				newSock.connect(new InetSocketAddress(host, port),
						connectTimeout);
			} else {
				// DIRECT CONNECTION
				newSock = new Socket();
				newSock.connect(new InetSocketAddress(host, port));
			}
		} else if (fallBackType.equals(FallBackType.PROXY)) {

			// PROXY_HOST ON PROXY_PORT USING HTTP
			newSock = new HttpSocketClientSide(new URL("http", proxyHost,
					proxyPort, "http://" + host + ":" + port + "/"));

		} else if (fallBackType.equals(FallBackType.PROXY_CGI)) {

			// PROXY_CGI ON RMI SERVER ON PORT 80 USING HTTP
			newSock = new HttpSocketClientSide(new URL("http", proxyHost,
					proxyPort, "http://" + host
							+ ":80/cgi-bin/java-rmi.cgi?forward=" + port));

		} else {
			throw new AssertionError();
		}
		return newSock;
	}
}
