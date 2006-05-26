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
 * @author  Mikhail A. Markov
 * @version $Revision: 1.1.2.2 $
 */
package org.apache.harmony.rmi.transport.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.UnmarshalException;
import java.rmi.server.UID;
import java.security.AccessController;

import org.apache.harmony.rmi.common.GetLongPropAction;
import org.apache.harmony.rmi.common.RMILog;
import org.apache.harmony.rmi.common.RMIProperties;
import org.apache.harmony.rmi.server.ServerConnection;
import org.apache.harmony.rmi.server.ServerConnectionManager;


/**
 * Tcp extension of ServerConnection.
 *
 * @author  Mikhail A. Markov
 * @version $Revision: 1.1.2.2 $
 */
public class TcpServerConnection extends ServerConnection {

    /** Log for logging tcp connections activity. */
    protected static final RMILog tcpTransportLog = RMILog.getTcpTransportLog();

    /*
     * The time used as an idle timeout for incomping connections (in ms).
     * Default value is 2 * 3600 * 1000 ms (2 hours).
     */
    private static int readTimeout = ((Long) AccessController.doPrivileged(
            new GetLongPropAction(RMIProperties.READTIMEOUT_PROP,
                    2 * 3600 * 1000))).intValue();

    /**
     * Constructs TcpServerConnection working through socket specified.
     *
     * @param s Socket connected to the client
     * @param mgr ConnectionManager managing this connection
     *
     * @throws IOException if an I/O error occured during getting
     *         input/output streams from specified socket
     */
    public TcpServerConnection(Socket s, ServerConnectionManager mgr)
            throws IOException {
        super(s, mgr);
        s.setSoTimeout(readTimeout);
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

        if (tcpTransportLog.isLoggable(RMILog.VERBOSE)) {
            tcpTransportLog.log(RMILog.VERBOSE, "Using protocol version "
                    + PROTOCOL_VER);
        }
        DataOutputStream dout = new DataOutputStream(out);

        // read protocol type
        if (din.readByte() == STREAM_PROTOCOL) {
            if (tcpTransportLog.isLoggable(RMILog.VERBOSE)) {
                tcpTransportLog.log(RMILog.VERBOSE,
                        "Using stream RMI protocol");
            }
        } else {
            dout.writeByte(PROTOCOL_NOT_SUPPORTED);
            dout.flush();
            return -1;
        }

        // send ack msg
        dout.writeByte(PROTOCOL_ACK);

        // send client's host and port
        String host = s.getInetAddress().getHostAddress();
        int port = s.getPort();
        dout.writeUTF(host);
        dout.writeInt(port);
        dout.flush();

        if (tcpTransportLog.isLoggable(RMILog.VERBOSE)) {
            tcpTransportLog.log(RMILog.VERBOSE,
                    "Server is seeing client as " + host + ":" + port);
        }

        // read host and port
        din.readUTF();
        din.readInt();

        // protocol is agreed
        return STREAM_PROTOCOL;
    }

    /**
     * @see ServerConnection.waitCallMsg()
     */
    protected int waitCallMsg() throws IOException {
        int data;

        while (true) {
            try {
                data = in.read();
            } catch (IOException ioe) {
                data = -1;
            }

            if (data == -1) {
                if (tcpTransportLog.isLoggable(RMILog.BRIEF)) {
                    tcpTransportLog.log(RMILog.BRIEF,
                            "Connection [" + toString() + "] is closed");
                }
                return -1;
            }
            DataOutputStream dout = new DataOutputStream(out);

            if (data == PING_MSG) {
                if (tcpTransportLog.isLoggable(RMILog.VERBOSE)) {
                    tcpTransportLog.log(RMILog.VERBOSE,
                            "Got ping request");
                }

                // send ping ack
                dout.writeByte(PING_ACK);
                dout.flush();
            } else if (data == DGCACK_MSG) {
                if (tcpTransportLog.isLoggable(RMILog.VERBOSE)) {
                    tcpTransportLog.log(RMILog.VERBOSE,
                            "Got DGC ack request");
                }
                dgcUnregisterUID(UID.read(new DataInputStream(in)));
            } else if (data == CALL_MSG) {
                if (tcpTransportLog.isLoggable(RMILog.VERBOSE)) {
                    tcpTransportLog.log(RMILog.VERBOSE,
                            "Got call request");
                }
                return data;
            } else {
                if (tcpTransportLog.isLoggable(RMILog.BRIEF)) {
                    tcpTransportLog.log(RMILog.BRIEF,
                            "Unknown request got: " + data);
                }
                throw new RemoteException("Unknown message got: " + data);
            }
        }
    }

    /**
     * Returns string representation of this connection.
     *
     * @return string representation of this connection
     */
    public String toString() {
        return "TcpServerConnection: remote endpoint:" + ep;
    }
}
