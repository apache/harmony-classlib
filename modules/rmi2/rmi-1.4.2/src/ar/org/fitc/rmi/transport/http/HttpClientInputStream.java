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
 * Specializes <code>HttpInputStream</code> for the client socket side
 * 
 * @author Diego Raúl Mercado
 */
class HttpClientInputStream extends HttpInputStream {

    /**
     * Constructor. Calls <code>HttpInputStream(InputStream)</code>
     * constructor.
     * 
     * @param is
     *            the socket's input stream
     */
    public HttpClientInputStream(InputStream is) {
        super(is);
    }

    /**
     * Parse the first line (status line) of the http's header.
     * 
     * @param line
     *            the line that has been read from the socket's input stream
     */
    @Override
    protected void readFirstLine(String line) throws IOException {
        // LINE: HTTP_VERSION + " " + STATUS_CODE
        String[] splitLine = line.split(" ");
        if (splitLine.length < 2) {
            throw HEADERS_EXCEPTION;
        }
        int statusCode = 0;
        try {
            statusCode = Integer.valueOf(splitLine[1].trim());
        } catch (NumberFormatException e) {
            throw HEADERS_EXCEPTION;
        }
        if (statusCode != 200) {
            throw new IOException("Unexpected HTTP status code: " + statusCode);
        }
    }
}
