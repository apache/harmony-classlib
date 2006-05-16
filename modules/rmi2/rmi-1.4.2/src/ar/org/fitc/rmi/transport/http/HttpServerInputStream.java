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

/**
 * Specializes <code>HttpInputStream</code> for the server socket side
 *
 * @author Diego Raúl Mercado
 */
class HttpServerInputStream extends HttpInputStream {

    /**
     * Constructor. Calls <code>HttpInputStream(InputStream)</code>
     * constructor.
     * 
     * @param is
     *            the socket's input stream
     */
    public HttpServerInputStream(InputStream is) {
        super(is);
    }

    /**
     * Parse the first line (request line) of the http's header. 
     * Sets {@link HttpInputStream#requestPath requestPath} value
     * 
     * @param line
     *            the line that has been read from the socket's input stream
     */
    @Override
    protected void readFirstLine(String line) throws IOException {
        //LINE: REQUEST_PATH + " " + HTTP_VERSION
        String[] splitLine = line.split(" ");
        if (splitLine[0].contains("/")) {
            requestPath = splitLine[0].trim(); 
        } else {
            throw HEADERS_EXCEPTION;
        }
    }
    
}
