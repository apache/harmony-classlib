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
 * @version $Revision: 1.1.2.2 $
 */
package org.apache.harmony.rmi.transport.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.rmi.ConnectIOException;
import java.rmi.RemoteException;
import java.security.AccessController;

import org.apache.harmony.rmi.client.ClientConnection;
import org.apache.harmony.rmi.client.ClientConnectionManager;
import org.apache.harmony.rmi.common.GetLongPropAction;
import org.apache.harmony.rmi.common.RMILog;
import org.apache.harmony.rmi.common.RMIProperties;
import org.apache.harmony.rmi.transport.Endpoint;


/**
 * Direct socket connection.
 *
 * @author  Mikhail A. Markov
 * @version $Revision: 1.1.2.2 $
 */
public class TcpConnection extends ClientConnection {

    // status of availability for remote calls
    private boolean available;

    // lock object for synchtonization
    private Object lock = new Object();

    // when this connection could be closed (if it's available)
    private long expiration;

    /** Log for logging tcp connections activity. */
    protected static final RMILog tcpTransportLog = RMILog.getTcpTransportLog();

    /*
     * The time which will be used as a socket read timeout when reading
     * protocol acknowledgement data while establishing a new connection.
     * The default value is 60000 ms (1 minute).
     */
    private static int handshakeTimeout = ((Long) AccessController.doPrivileged(
            new GetLongPropAction(RMIProperties.HANDSHAKETIMEOUT_PROP,
                    60000))).intValue();

    /**
     * @see ClientConnection(Socket, Endpoint)
     */
    public TcpConnection(Socket s, Endpoint ep)throws RemoteException {
        super(s, ep);
        available = false;
    }

    /**
     * Acknowledge protocol with server side.
     *
     * @return acknowledged protocol number
     *
     * @throws RemoteException if any I/O exception occured during protocol
     *         acknowledgement
     */
    protected int serverProtocolAck() throws RemoteException {
        try {
            DataOutputStream dout = new DataOutputStream(out);

            // write RMI header and protocol version
            writeHeader(dout);

            // write protocol type
            dout.writeByte(STREAM_PROTOCOL);
            out.flush();

            if (tcpTransportLog.isLoggable(RMILog.VERBOSE)) {
                tcpTransportLog.log(RMILog.VERBOSE,
                        "Using stream RMI protocol");
            }

            // set handshakeTimeout
            int origTimeout = 0;

            try {
                origTimeout = s.getSoTimeout();
                s.setSoTimeout(handshakeTimeout);
            } catch (Exception ex) {
            }

            // read protocol acknoledgement
            DataInputStream din = new DataInputStream(in);
            int ack = din.readByte();

            if (ack != PROTOCOL_ACK) {
                throw new ConnectIOException((ack == PROTOCOL_NOT_SUPPORTED)
                        ? ("Protocol version " + STREAM_PROTOCOL
                        + " is not supported.") : ("Unknown protocol response: "
                        + ack));
            }

            // read host and port
            String host = din.readUTF();
            int port = din.readInt();

            if (tcpTransportLog.isLoggable(RMILog.VERBOSE)) {
                tcpTransportLog.log(RMILog.VERBOSE,
                        "Server is seeing us as " + host + ":" + port);
            }

            // restore original value of soTimeout
            try {
                s.setSoTimeout(origTimeout);
            } catch (Exception ex) {
            }

            // send our host and port (for Stream protocol they'll be ignored)
            dout.writeUTF(host);
            dout.writeInt(port);
            dout.flush();
        } catch (RemoteException re) {
            close();
            throw re;
        } catch (IOException ioe) {
            close();
            throw new ConnectIOException(
                    "Unable to acknowledge protocol with server", ioe);
        }

        // protocol is agreed
        return STREAM_PROTOCOL;
    }

    /**
     * @see ClientConnection.done()
     */
    public void done() {
        synchronized (lock) {
            available = true;
            expiration = System.currentTimeMillis()
                    + ClientConnectionManager.connTimeout;
        }
    }

    /**
     * @see ClientConnection.reuse()
     */
    public synchronized boolean reuse() {
        synchronized (lock) {
            if (!available) {
                return false;
            }
        }
        int ackResp = 0;

        try {
            out.write(PING_MSG);
            out.flush();
            ackResp = in.read();
        } catch (IOException ioe) {
            if (tcpTransportLog.isLoggable(RMILog.BRIEF)) {
                tcpTransportLog.log(RMILog.BRIEF,
                        "Ping request for " + toString() + " failed.");
            }
            close(false);
            return false;
        }

        if (ackResp != PING_ACK) {
            if (tcpTransportLog.isLoggable(RMILog.BRIEF)) {
                tcpTransportLog.log(RMILog.BRIEF,
                        "Unknown response to ping request for " + toString()
                        + ": " + ackResp);
            }
            close(false);
            return false;
        }

        synchronized (lock) {
            available = false;
        }

        if (tcpTransportLog.isLoggable(RMILog.BRIEF)) {
            tcpTransportLog.log(RMILog.BRIEF,
                    "Reusing " + toString() + "...");
        }
        return true;
    }

    /**
     * @see ClientConnection.isAvailable()
     */
    public boolean isAvailable() {
        synchronized (lock) {
            return available;
        }
    }

    /**
     * Returns true because this connection could be reused.
     *
     * @see ClientConnection.isReusable()
     */
    public boolean isReusable() {
        return true;
    }

    /**
     * @see ClientConnection.getExpiration()
     */
    public long getExpiration() {
        synchronized (lock) {
            if (!available) {
                return -1;
            }
            return expiration;
        }
    }
}
