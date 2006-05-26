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

import java.io.IOException;
import java.net.Socket;
import java.rmi.ConnectException;
import java.rmi.ConnectIOException;
import java.rmi.UnknownHostException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMISocketFactory;

import org.apache.harmony.rmi.internal.transport.http.HttpSocketClientSide;
import org.apache.harmony.rmi.internal.utils.PropertiesReader;

/**
 * 
 * @author Gustavo Petri
 */
public final class ClientConnectionFactory {

    private static Integer SO_TIME_OUT;

    static {
        SO_TIME_OUT = PropertiesReader.readInt(
                "org.apache.harmony.rmi.internal.transport.readTimeout", 0);
    }

    static final AbstractClientConnection getClientConnection(Endpoint ep)
            throws IOException, UnknownHostException, ConnectException,
            ConnectIOException {
        Socket sock = null;
        AbstractClientConnection ret;
        
        RMIClientSocketFactory csf = (ep.getCsf() != null) ? ep.getCsf()
                : RMISocketFactory.getSocketFactory();
        if (csf == null) {
            csf = RMISocketFactory.getDefaultSocketFactory();
        }
        sock = csf.createSocket(ep.getEndpointID().getHost(), ep.getPort());
        if (SO_TIME_OUT != 0) {
            sock.setSoTimeout(SO_TIME_OUT);
        }
        sock.setTcpNoDelay(true);
        if (sock instanceof HttpSocketClientSide) {
            ret = new SingleOpClientConnection(sock, ep);
        } else {
            ret = new StreamClientConnection(sock, ep);
        }
        return ret;
    }
}
