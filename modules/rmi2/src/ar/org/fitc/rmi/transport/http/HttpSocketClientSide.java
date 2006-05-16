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
package ar.org.fitc.rmi.transport.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * For client purposes. Specify other functionality overriding the methods
 * <code>getInputStream()</code> and <code>getOutputStream()</code>.
 * 
 * @author Diego Raúl Mercado
 */
public final class HttpSocketClientSide extends Socket {

    /** The socket's input stream */
    private InputStream input;

    /** The socket's output stream */
    private OutputStream output;

    /**
     * It's required for the constructor of this object and the http's request
     * line
     */
    private URL url;

    /**
     * Constructor.
     * 
     * @param url
     *            it's used for socket connection and http's request line
     * @throws UnknownHostException
     *             indicate that the IP address of a host could not be
     *             determined
     * @throws IOException
     *             if an I/O error occurs when creating this socket
     */
    public HttpSocketClientSide(URL url) throws UnknownHostException,
            IOException {
        super(url.getHost(), url.getPort());
        this.url = url;
    }

    /**
     * @ar.org.fitc.spec_ref returns a <code>HTTPInputStream</code> instance
     *                       with the input stream of the superclass of this
     *                       object as the constructor parameter
     */
    @Override
    public synchronized final InputStream getInputStream() throws IOException {
        if (input == null) {
            input = new HttpInputStream(super.getInputStream());
        }
        return input;
    }

    /**
     * @ar.org.fitc.spec_ref returns a <code>HTTPClientOutputStream</code>
     *                       instance with the input stream of the superclass of
     *                       this object as one of the constructor parameters
     */
    @Override
    public synchronized final OutputStream getOutputStream() throws IOException {
        if (output == null) {
            if (url.getQuery() == null) {
                // proxy... (serverPath: http://90.0.0.1:1099/)
                output = new HttpClientOutputStream(super.getOutputStream(),
                        url.getPath(), getInetAddress().getHostAddress());
            } else {
                // proxy + cgi... (serverPath:
                // http://90.0.0.1:80/cgi-bin/java-rmi?forward=1099)
                output = new HttpClientOutputStream(super.getOutputStream(),
                        url.getFile(), getInetAddress().getHostAddress());
            }
        }
        return output;
    }
}
