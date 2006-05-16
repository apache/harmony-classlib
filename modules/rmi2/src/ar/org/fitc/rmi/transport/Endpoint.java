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

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.server.RMIClientSocketFactory;

import ar.org.fitc.rmi.utils.PropertiesReader;

/**
 * This is a simple representation of an TCP/IP, UDP/IP endpoint.
 * 
 * @author Gustavo Petri
 */
public final class Endpoint {

    /**
     * The address of the host.
     */
    private String host;

    /**
     * The TCP port.
     */
    private int port;

    /**
     * Client factory to use. If null, use default socket factory.
     */
    private RMIClientSocketFactory csf;

    /**
     * Default constructor needed by the
     * {@link java.io.Serializable}
     * interface
     */
    protected Endpoint() {
    }

    /**
     * Constructs a new {@link ar.org.fitc.rmi.transport.Endpoint} with the specified
     * port and csf.
     * 
     * @param port
     *            the TCP/IP port
     * @param csf
     *            {@link java.rmi.server.RMIClientSocketFactory} instance
     */
    public Endpoint(int port, RMIClientSocketFactory csf) {

        this.port = port;
        this.csf = csf;
        /*
         * REVIEW: the exported host according to the java.rmi.server.hostname
         * property.
         */
        this.host = PropertiesReader.readString("java.rmi.server.hostname");
        if (this.host == null) {
            try {
                this.host = 
                    (PropertiesReader.readString("java.rmi.server.useLocalHostname") != null) 
                        ? InetAddress.getLocalHost().getHostName()
                        : InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                // REVIEW: This is the default value.
                this.host = "localhost";
            }
        }
    }

    /**
     * Constructs a new {@link ar.org.fitc.rmi.transport.Endpoint} with the specified
     * hostname, port and csf.
     * 
     * @param host
     *            The TCP/IP hostname
     * @param port
     *            port The TCP/IP port
     * @param csf
     *            {@link java.rmi.server.RMIClientSocketFactory} instance
     */
    public Endpoint(String host, int port, RMIClientSocketFactory csf) {
        this.host = host;
        this.port = port;
        this.csf = csf;
    }

    /**
     * Sets a {@link java.rmi.server.RMIClientSocketFactory} to be used by this
     * {@link ar.org.fitc.rmi.transport.Endpoint}.
     * 
     * @param csf
     *            csf {@link java.rmi.server.RMIClientSocketFactory} instance to
     *            be setted
     */
    public final void setCsf(RMIClientSocketFactory csf) {
        this.csf = csf;
    }

    /**
     * Sets a the hostname to be used by this
     * {@link ar.org.fitc.rmi.transport.Endpoint}.
     * 
     * @param host
     *            The hostname to be setted
     */
    public final void setHost(String host) {
        this.host = host;
    }

    /**
     * Sets the port used by this {@link ar.org.fitc.rmi.transport.Endpoint}.
     * 
     * @param port
     *            The port to be setted
     */
    public final void setPort(int port) {
        this.port = port;
    }

    /**
     * Returns the {@link java.rmi.server.RMIClientSocketFactory} instance used
     * by this {@link ar.org.fitc.rmi.transport.Endpoint}.
     * 
     * @return the {@link java.rmi.server.RMIClientSocketFactory} instance used
     *         by this {@link ar.org.fitc.rmi.transport.Endpoint}
     */
    public final RMIClientSocketFactory getCsf() {
        return csf;
    }

    /**
     * Returns the host used by this {@link ar.org.fitc.rmi.transport.Endpoint}.
     * 
     * @return the host used by this {@link ar.org.fitc.rmi.transport.Endpoint}
     */
    public final String getHost() {
        return host;
    }

    /**
     * Returns the port used by this {@link ar.org.fitc.rmi.transport.Endpoint}.
     * 
     * @return the port used by this {@link ar.org.fitc.rmi.transport.Endpoint}
     */
    public final int getPort() {
        return port;
    }

    /**
     * Returns an {@link ar.org.fitc.rmi.transport.Endpoint} identifying this
     * {@link ar.org.fitc.rmi.transport.Endpoint}.
     * 
     * @return the {@link ar.org.fitc.rmi.transport.Endpoint} identifying this
     *         {@link ar.org.fitc.rmi.transport.Endpoint}
     */
    public final EndpointID getEndpointID() {
        return new EndpointID(host, port);
    }

    /**
     * Test the parameter and this {@link ar.org.fitc.rmi.transport.Endpoint} for
     * equality.
     * 
     * @param obj
     *            the object to test for equality
     * @return
     *            <li><code>true</code>: if and only if the parameter and
     *            this {@link ar.org.fitc.rmi.transport.Endpoint} instance are equal
     *            <li><code>false</code> for every other case
     */
    @Override
    public final boolean equals(Object obj) {
        Endpoint castobj;

        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof Endpoint))
            return false;
        castobj = (Endpoint) obj;
        try {
            return ((castobj.port == this.port && castobj.host.equals(this.host)) 
                    && ((this.csf == null && castobj.csf == null) 
                            || this.csf.equals(castobj.csf)));
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * Returns a hashCode for this instance.
     * 
     * @return the <code>HashCode</code>.
     */
    @Override
    public final int hashCode() {

        if (this.host != null) {
            return this.host.hashCode() ^ this.port;
        }
        return 0;
    }

    /**
     * Returns the <code>String</code> representation of this
     * {@link ar.org.fitc.rmi.transport.Endpoint}.
     * 
     * @return the <code>String</code> representation of this
     *         {@link ar.org.fitc.rmi.transport.Endpoint}
     */
    @Override
    public final String toString() {
        return "[" + this.host + ", " + this.port + "]";
    }
}
