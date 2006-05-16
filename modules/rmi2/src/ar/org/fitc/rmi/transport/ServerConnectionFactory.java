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

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import ar.org.fitc.rmi.transport.http.HttpSocketServerSide;

/**
 * Factory for Server Connections. 
 * @author Gustavo Petri
 */
public final class ServerConnectionFactory {

    /** Prevents instantiation */ 
    private ServerConnectionFactory() {}
    
    /**
     * Creates a Connection acording to the type of the {@link Socket} argument 
     * @param in the {@link InputStream} for the Connection to be created
     * @param out the {@link OutputStream} for the Connection to be created
     * @param clientEP the {@link Endpoint} of the client for this Connection
     * @param sock the {@link Socket} for this connection
     * @return a Connection to serve client requests  
     */
    public final static AbstractServerConnection getServerConnection(InputStream in,
            OutputStream out, EndpointID clientEP, Socket sock) {
        AbstractServerConnection ret;

        if (sock instanceof HttpSocketServerSide) {
            ret = new SingleOpServerConnection(in, out, clientEP, sock);
        } else {
            ret = new StreamServerConnection(in, out, clientEP, sock);
        }
        return ret;
    }
}
