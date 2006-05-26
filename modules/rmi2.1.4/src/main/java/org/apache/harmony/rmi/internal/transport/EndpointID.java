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
package org.apache.harmony.rmi.internal.transport;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Simple representation of a TCP/IP Endpoint.
 * 
 * @author Gustavo Petri
 */
public final class EndpointID {

    // This class is insipired on
    // http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4674902
    // Probably this should implement a EdpointID interface, and
    // should be called TCPEndpointID.
    /**
     * The host name.
     */
    private String host;

    /**
     * The port.
     */
    private int port;

    /**
     * Constructor needed by the
     * {@link java.io.Serializable}
     * interface.
     */

    public EndpointID() {
        host = ""; // This initialization to "" is because the
        // java.io.ObjectOutputStream.writeUTF
        // method cannot send a null value.
        port = -1;
    }

    /**
     * Constructor of this class.
     * 
     * @param host
     *            the host of the {@link org.apache.harmony.rmi.internal.transport.Endpoint}
     * @param port
     *            the port of the {@link org.apache.harmony.rmi.internal.transport.Endpoint}
     */
    public EndpointID(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * Returns the host of the {@link org.apache.harmony.rmi.internal.transport.Endpoint}.
     * 
     * @return the host of the {@link org.apache.harmony.rmi.internal.transport.Endpoint}.
     */
    public final String getHost() {

        return this.host;
    }

    /**
     * Returns the port of the {@link org.apache.harmony.rmi.internal.transport.Endpoint}.
     * 
     * @return the port of the {@link org.apache.harmony.rmi.internal.transport.Endpoint}.
     */
    public final int getPort() {

        return this.port;
    }

    /**
     * Writes this {@link org.apache.harmony.rmi.internal.transport.EndpointID} to the specified
     * <code>ObjectOutputStream</code>.
     * 
     * @param out
     *            the <code>ObjectOutputStream</code> where this
     *            {@link org.apache.harmony.rmi.internal.transport.EndpointID} will be written.
     * @throws IOException
     *             if the write fails
     */
    public final void write(DataOutput out) throws IOException {

        out.writeUTF(this.host);
        out.writeInt(this.port);
        return;
    }

    /**
     * Reads a new {@link org.apache.harmony.rmi.internal.transport.EndpointID} from the specified
     * <code>ObjectInputStream</code>.
     * 
     * @param in
     *            the <code>ObjectInputStream</code> from where the
     *            {@link org.apache.harmony.rmi.internal.transport.EndpointID} will be read.
     * @return a new {@link org.apache.harmony.rmi.internal.transport.EndpointID}
     * @throws IOException
     *             if the write fails
     */
    public final static EndpointID read(DataInput in) throws IOException {

        EndpointID ep = new EndpointID();
        ep.host = in.readUTF();
        ep.port = in.readInt();
        return ep;
    }
}