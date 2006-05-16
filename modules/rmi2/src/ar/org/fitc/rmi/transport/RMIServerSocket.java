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
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import ar.org.fitc.rmi.transport.http.HttpSocketServerSide;
import ar.org.fitc.rmi.transport.jrmp.ProtocolHeader;

/**
 * The purpose of this class is to be aware of the socket that has to be
 * returned in the {@link #accept()} method. Because of that, it has to
 * read the first 4 bytes of the socket's input stream returned by the 
 * {@link java.net.ServerSocket#accept()} method of the superclass
 * 
 * @author Diego Raúl Mercado
 */
final class RMIServerSocket extends ServerSocket {

    /**
     * Constructor. Before establishing the connection set the socket's address
     * as reusable
     * 
     * @param port
     *            the port of this serverSocket
     * @throws IOException
     *             if an I/O error occurs when creating this serverSocket
     */
    public RMIServerSocket(int port) throws IOException {
        super();
        super.bind(new InetSocketAddress(port));
    }

    /**
     * @ar.org.fitc.spec_ref
     * Read the first 4 bytes of the socket's input stream returned at the 
     * {@link java.net.ServerSocket#accept()} method of the superclass. Then, 
     * determine which socket has to be returned or throw an IOException if 
     * cannot recognize the stream
     */
    @Override
    public final Socket accept() throws IOException {
        Socket sock;
        InputStream in;
        byte[] protocol = new byte[4];
        ProtocolHeader protHeader;
        
        sock = super.accept();
        in = sock.getInputStream();

        for (int i = 0; i < 4; i++) {
        	protocol[i] = (byte)in.read();
		}
        protHeader = ProtocolHeader.createProtocolHeader(protocol);
        if (protHeader.equals(ProtocolHeader.JRMI_PROTOCOL_HEADER)) {
        	return sock;
        } else if (protHeader.equals(ProtocolHeader.HTTP_PROTOCOL_HEADER)) {
        	return new HttpSocketServerSide(sock);
        } else {
        	//TODO LOG
            throw new IOException("Unrecognized Header Protocol");
        }
    }
}