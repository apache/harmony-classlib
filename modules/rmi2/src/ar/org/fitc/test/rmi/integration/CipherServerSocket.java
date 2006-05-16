/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
/**
 * @author Hugo Beilis
 * @author Osvaldo Demo
 * @author Jorge Rafael
 * @version 1.0
 */
package ar.org.fitc.test.rmi.integration;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class CipherServerSocket extends ServerSocket {

    public CipherServerSocket() throws IOException {
        super();
    }

    public CipherServerSocket(int port) throws IOException {
        super(port);
    }

    public CipherServerSocket(int port, int backlog) throws IOException {
        super(port, backlog);
    }

    public CipherServerSocket(int port, int backlog, InetAddress bindAddr)
            throws IOException {
        super(port, backlog, bindAddr);
    }

    public Socket accept() throws IOException {
        Socket s = new CipherSocket();
        implAccept(s);
        return s;
    }
}
