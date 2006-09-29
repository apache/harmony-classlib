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
package org.apache.harmony.rmi.transport.proxy;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.rmi.ConnectIOException;
import java.rmi.RemoteException;

import org.apache.harmony.rmi.client.ClientConnection;
import org.apache.harmony.rmi.common.RMILog;
import org.apache.harmony.rmi.transport.Endpoint;


/**
 * HTTP proxy connection.
 *
 * @author  Mikhail A. Markov
 * @version $Revision: 1.1.2.2 $
 */
public class HttpConnection extends ClientConnection
        implements ProxyConstants {

    /**
     * @see ClientConnection(Socket, Endpoint)
     */
    public HttpConnection(Socket s, Endpoint ep) throws RemoteException {
        super(s, ep);
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
            dout.writeByte(SINGLEOP_PROTOCOL);
            dout.flush();

            if (proxyTransportLog.isLoggable(RMILog.VERBOSE)) {
                proxyTransportLog.log(RMILog.VERBOSE,
                        "Using singleop RMI protocol");
            }
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
        return SINGLEOP_PROTOCOL;
    }

    /**
     * @see ClientConnection.done()
     */
    public void done() {
        close();
    }

    /**
     * Closes output stream and read protocol ack data.
     */
    public void releaseOutputStream() throws IOException {
        out.close();
    }

    /**
     * Always throws error because this connection is not reusable.
     */
    public boolean reuse() {
        throw new Error(toString() + " is not reusable.");
    }

    /**
     * @see ClientConnection.isAvailable()
     */
    public boolean isAvailable() {
        return false;
    }

    /**
     * Returns false because this connection could not be reused.
     *
     * @see ClientConnection.isReusable()
     */
    public boolean isReusable() {
        return false;
    }

    /**
     * Always throws error because this connection is not reusable.
     */
    public long getExpiration() {
        throw new Error(toString() + " is not reusable.");
    }
}
