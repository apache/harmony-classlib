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

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.apache.harmony.rmi.internal.utils.PropertiesReader;

/**
 * This class is used for client and server purposes.
 * <br>
 * Implements a byte array stream in which the data is written. On the other
 * side, the constructor receive a socket's output stream.
 * <br>
 * All the data is written in the byte array's buffer until the
 * {@link #flush()} method is invoked. Then, a http's POST/RESPONSE 
 * header is attached at the beginning of the socket's stream and all the buffered 
 * bytes are written as the content of this header.
 * 
 * @author Diego Raúl Mercado
 */
abstract class HttpOutputStream extends ByteArrayOutputStream {
    
    private final static String CRLF = String.valueOf("\r\n");
    
    private final static String DEFAULT_ENCODING_CONTENT_TYPE = 
        String.valueOf("application/octet-stream");
    
    private final static String DEFAULT_ACCEPT = 
        String.valueOf("text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2");
    
    /** The value of the http's tag <code>User-Agent</code> */
    private final static String JAVA_VERSION =
        PropertiesReader.readString("java.version");
    
    /**
     * The value of the http's header field <code>host</code> that is specified 
     * at the constructor of this object
     */
    private String localHostAddress;

    /** The http's status/resquest line that must be set at any of the subclasses */
    protected String header;

    /** The stream of the socket that is specified in this constructor */
    protected OutputStream socketOutput;

    /**
     * Indicates if this object has been flushed. The <code>reset()</code>
     * method initializes this value
     */
    private boolean isFlushed;

    /**
     * Constructor.
     * 
     * @param socketOutput
     *            the socket's output stream
     * @param localHostAddress
     *            the localHostAddress attached in the http's HOST header
     */
    protected HttpOutputStream(OutputStream socketOutput,
            String localHostAddress) {
        this.socketOutput = socketOutput;
        this.localHostAddress = localHostAddress;
    }

    /**
     * @ar.org.fitc.spec_ref
     * Attaches a http's post header at the beginning of the socket's stream and
     * all the buffered bytes are written as the content of this http's 
     * POST/RESPONSE header. Then, invoke the <code>flush</code> method of
     * the socket's stream
     */
    @Override
    public synchronized final void flush() throws IOException {
        if (!isFlushed) {
            writeOnSocket();
            isFlushed = true;
        }
    }

    /**
     * @ar.org.fitc.spec_ref
     * Resets this stream and sets the variable <code>isFlushed</code> to
     * false
     */
    @Override
    public final void reset() {
        super.reset();
        isFlushed = false;
    }

    /**
     * Writes the http's POST/RESPONSE into the socket's output stream
     * @throws IOException if cannot write the header
     */
    private final void writeOnSocket() throws IOException {
        try {
            BufferedOutputStream writer = new BufferedOutputStream(socketOutput);

            writer.write((header
                            + CRLF
                            + HttpHeaders.CONTENT_TYPE_HEADER
                            + ": "
                            + DEFAULT_ENCODING_CONTENT_TYPE
                            + CRLF
                            + HttpHeaders.CACHE_CONTROL_HEADER
                            + ": "
                            + "no-cache"
                            + CRLF
                            + HttpHeaders.PRAGMA_HEADER
                            + ": "
                            + "no-cache"
                            + CRLF
                            + HttpHeaders.USER_AGENT_HEADER
                            + ": Java/"
                            + JAVA_VERSION
                            + CRLF
                            + HttpHeaders.HOST_HEADER
                            + ": "
                            + localHostAddress
                            + CRLF
                            + HttpHeaders.CONTENT_LENGTH_HEADER
                            + ": "
                            + super.toByteArray().length
                            + CRLF
                            + HttpHeaders.ACCEPT_HEADER
                            + ": "
                            + DEFAULT_ACCEPT
                            + CRLF
                            + HttpHeaders.CONNECTION_HEADER + ": "
                            + "keep-alive" 
                            + CRLF 
                            + CRLF)
                            .getBytes());

            writer.write(super.toByteArray());
            writer.flush();
        } catch (UnsupportedEncodingException e) {
            new AssertionError(e);
        }
    }
}
