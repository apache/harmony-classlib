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
 * @author  Mikhail A. Markov
 * @version $Revision: 1.1.2.3 $
 */
package org.apache.harmony.rmi.transport.proxy;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.UnmarshalException;
import java.rmi.server.UID;

import org.apache.harmony.rmi.common.RMILog;
import org.apache.harmony.rmi.server.ServerConnection;
import org.apache.harmony.rmi.server.ServerConnectionManager;


/**
 * Http extension of ServerConnection.
 *
 * @author  Mikhail A. Markov
 * @version $Revision: 1.1.2.3 $
 */
public class HttpServerConnection extends ServerConnection
        implements ProxyConstants {

    // If true then this connection was closed.
    private boolean isClosed = false;

    /**
     * Constructs HttpServerConnection working through socket specified.
     *
     * @param s Socket connected to the client
     * @param mgr ConnectionManager managing this connection
     *
     * @throws IOException if an I/O error occured during getting
     *         input/output streams from specified socket
     */
    public HttpServerConnection(Socket s, ServerConnectionManager mgr)
            throws IOException {
        super(s, mgr);
    }

    /**
     * @see ServerConnection.clientProtocolAck()
     */
    protected int clientProtocolAck() throws IOException {
        byte data;
        DataInputStream din = new DataInputStream(in);

        try {
            // read RMI header
            int header = din.readInt();

            if (header != RMI_HEADER) {
                throw new UnmarshalException("Unknown header: " + header);
            }

            // read RMI protocol version
            short ver = din.readShort();

            if (ver != PROTOCOL_VER) {
                throw new UnmarshalException("Unknown RMI protocol version: "
                        + ver);
            }
        } catch (IOException ioe) {
            throw new UnmarshalException("Unable to read RMI protocol header",
                    ioe);
        }

        if (proxyTransportLog.isLoggable(RMILog.VERBOSE)) {
            proxyTransportLog.log(RMILog.VERBOSE, "Using protocol version "
                    + PROTOCOL_VER);
        }

        // read protocol type
        if (din.readByte() == SINGLEOP_PROTOCOL) {

            if (proxyTransportLog.isLoggable(RMILog.VERBOSE)) {
                proxyTransportLog.log(RMILog.VERBOSE,
                        "Using singleop RMI protocol");
            }
        } else {
            return -1;
        }

        // protocol is agreed
        return SINGLEOP_PROTOCOL;
    }

    /**
     * @see ServerConnection.waitCallMsg()
     */
    protected int waitCallMsg() throws IOException {
        if (isClosed) {
            return -1;
        }
        int data;

        try {
            data = in.read();
        } catch (IOException ioe) {
            data = -1;
        }

        if (data == -1) {
            if (proxyTransportLog.isLoggable(RMILog.VERBOSE)) {
                proxyTransportLog.log(RMILog.VERBOSE,
                        "Connection [" + toString() + "] is closed");
            }
            return -1;
        }
        DataOutputStream dout = new DataOutputStream(out);

        if (data == PING_MSG) {
            if (proxyTransportLog.isLoggable(RMILog.VERBOSE)) {
                proxyTransportLog.log(RMILog.VERBOSE,
                        "Got ping request");
            }
            releaseInputStream();

            // send ping ack
            dout.writeByte(PING_ACK);
            dout.close();
            return -1;
        } else if (data == DGCACK_MSG) {
            if (proxyTransportLog.isLoggable(RMILog.VERBOSE)) {
                proxyTransportLog.log(RMILog.VERBOSE,
                        "Got DGC ack request");
            }
            dgcUnregisterUID(UID.read(new DataInputStream(in)));
            releaseInputStream();
            dout.close();
            return -1;
        } else if (data == CALL_MSG) {
            if (proxyTransportLog.isLoggable(RMILog.VERBOSE)) {
                proxyTransportLog.log(RMILog.VERBOSE,
                        "Got call request");
            }
            return data;
        } else {
            if (proxyTransportLog.isLoggable(RMILog.VERBOSE)) {
                proxyTransportLog.log(RMILog.VERBOSE,
                        "Unknown request got: " + data);
            }
            throw new RemoteException("Unknown message got: " + data);
        }
    }

    /**
     * Closes output stream. After that call this connection is treated as
     * closed and could be reused.
     */
    public synchronized void releaseOutputStream() throws IOException {
        if (isClosed) {
            return;
        }
        isClosed = true;
        out.close();
    }

    /**
     * Returns string representation of this connection.
     *
     * @return string representation of this connection
     */
    public String toString() {
        return "HttpServerConnection: remote endpoint:" + ep;
    }
}
