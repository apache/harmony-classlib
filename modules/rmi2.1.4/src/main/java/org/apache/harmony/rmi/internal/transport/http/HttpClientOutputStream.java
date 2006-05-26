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
package org.apache.harmony.rmi.internal.transport.http;

import java.io.OutputStream;

/**
 * Concrete class that additionally sets the <code>header</code> required for
 * the http's request line (POST)
 * 
 * @author Diego Raúl Mercado
 */
final class HttpClientOutputStream extends HttpOutputStream {

    /**
     * Calls the inherit constructor with the parameters
     * <code>socketOutput</code> and <code>localHostAddress</code>. <br>
     * Then, sets <code>header</code> required for the http's request line
     * (POST)
     * 
     * @param socketOutput
     *            the socket's output stream
     * @param serverPath
     *            the path location of the server
     * @param localHostAddress
     *            the localHostAddress attached in the http's host header
     */
    protected HttpClientOutputStream(OutputStream socketOutput,
            String serverPath, String localHostAddress) {
        super(socketOutput, localHostAddress);
        header = "POST " + serverPath + " HTTP/1.0";
    }
}
