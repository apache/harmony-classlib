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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class is used for client and server purposes. <br>
 * Implements a byte array stream in which the data is read. On the other side,
 * the constructor receive a socket's input stream. <br>
 * Fills only once the internal buffer of the wrapped input stream (at the time 
 * that requeries any operation from it) with the http's content and delegates the 
 * all the calls to such input stream.   
 * 
 * @author Diego Raúl Mercado
 */
abstract class HttpInputStream extends InputStream {

    /** Exposed buffer */
    private byte[] buffer;

    /** Available int to read */
    private int available;

    /** Wrapped ByteArrayInputStream */
    private ByteArrayInputStream bais;

    /** Indicates CARRY-RETURN */
    private static final int CR = 0xa;

    /** Indicates LINE-FEEDBACK */
    private static final int LF = 0xd;

    /** Stores the header's value that has been parsed */
    private Map<HttpHeaders, String> headers;

    /** If true, indicates that the buffer has been filled */
    private boolean isBufferFilled;

    /** The socket's input stream */
    private InputStream socketInputStream;

    /** The path that is specified in the request line */
    protected String requestPath;

    /**
     * Statically stores the exception that may thrown if cannot parse the
     * http's headers
     */
    protected static final IOException HEADERS_EXCEPTION = new IOException(
            "Cannot parse http headers");

    /**
     * Initializes the inherit's buffer and the map <code>headers</code>.
     * Then, calls <code>fillBuffer()</code> method
     * 
     * @param is
     *            the socket's input stream
     */
    public HttpInputStream(InputStream is) {
        headers = new ConcurrentHashMap<HttpHeaders, String>();
        isBufferFilled = false;
        socketInputStream = is;
    }

    /**
     * Parses any http's headers and fill the inherits' buffer with the http's
     * content
     * 
     * @throws IOException
     *             if an I/O exception ocurrs or cannot parse the http's header
     */
    private synchronized final void fillBuffer() throws IOException {
        if (isBufferFilled == false) {
            String line;
            String value;
            boolean isCRLFPresent = false;
            int contentLength = 0;

            /*
             * READ REQUEST/STATUS LINE
             */

            line = readLine(socketInputStream).trim();
            readFirstLine(line);
            
            /*
             * READ HEADERS
             */
            while ((line = readLine(socketInputStream).trim()) != null) {
                if (line.equals("")) { // if CRLF
                    isCRLFPresent = true;

                    // contentLength
                    value = headers.get(HttpHeaders.CONTENT_LENGTH_HEADER);
                    if (value == null) {
                        contentLength = socketInputStream.available();
                    } else {
                        contentLength = Integer.parseInt(value);
                        if (contentLength < 0) {
                            throw HEADERS_EXCEPTION;
                        }
                    }

                    break;
                }

                if (!line.contains(":")) {
                    throw HEADERS_EXCEPTION;
                }
                putValueInHeader(headers, line.substring(0,
                        line.indexOf(":")).trim(), // header
                        (line.substring(line.indexOf(":") + 1)).trim()); // value
            }

            if (!isCRLFPresent) {
                throw new IOException("HTTP POST must have content");
            }

            /*
             * READ BODY
             */

            int readCount = contentLength;
            byte[] bytes = new byte[readCount];
            int temp = readCount;
            while (readCount > 0) {
                bytes[temp - readCount] = (byte) socketInputStream.read();
                readCount--;
            }

            // Initializes the buffer of the wrapped input stream
            buffer = new byte[contentLength];
            bais = new ByteArrayInputStream(buffer);

            // copy the content into the buffer
            System.arraycopy(bytes, 0, buffer, 0, bytes.length);
            available = bytes.length;
            isBufferFilled = true;
        }
    }

    /**
     * If the header is a known Enum then inserts into <code>headers</code>
     * its <code>value</code> with the correspondent HttpHeaders' type as key
     * 
     * @param headers
     *            the name of the Enum
     * @param header
     *            the key to add 
     * @param value
     *            the value of the Header
     */
    private static final void putValueInHeader(Map<HttpHeaders, String> headers,
            String header, String value) {
        HttpHeaders hc = HttpHeaders.getEnum(header);
        if (hc != null) {
            // recognized type..
            headers.put(hc, value);
        }
    }

    /**
     * Reads a line from this <code>is<code> and returns it     
     * 
     * @param is 
     *            the socket's input stream
     * @return 
     *            a line from this <code>is<code> 
     * @throws IOException 
     *             If cannot read an CRLF
     */
    private static final String readLine(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        int actual;
        int last = is.read();

        while ((actual = is.read()) != -1) {
            if (last == LF && actual == CR) {
                return sb.toString();
            }
            sb.append((char) last);
            last = actual;
        }
        throw HEADERS_EXCEPTION;
    }

    /**
     * This method must be overriden in order to parse the first line (status/request 
     * line) of the http's header because the behaviour differs if it's a client or 
     * server socket side  
     * 
     * @param line the line that has been read from this inputstream 
     */
    protected abstract void readFirstLine(String line) throws IOException;
    
    /**
     * @return the path that has been specified in the http's status/request
     *         line
     */
    public final String getRequestPath() {
        return requestPath;
    }

    @Override
    public final int available() throws IOException {
        fillBuffer();
        return available;
    }

    @Override
    public final void close() throws IOException {
        fillBuffer();
        bais.close();
    }

    @Override
    public final void mark(int arg0) {
        bais.mark(arg0);
    }

    @Override
    public final boolean markSupported() {
        return bais.markSupported();
    }

    @Override
    public final void reset() throws IOException {
        fillBuffer();
        bais.reset();
    }

    @Override
    public final long skip(long arg0) throws IOException {
        fillBuffer();
        return bais.skip(arg0);
    }

    /**
     * @throws IOException
     * @ar.org.fitc.spec_ref
     */
    @Override
    public synchronized final int read() throws IOException {
        fillBuffer();
        if (available == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        available--;
        if (available > 0) {
            notify();
        }
        return bais.read();
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public final int read(byte[] arg0) throws IOException {
        return read(arg0, 0, arg0.length);
    }

    /**
     * @throws IOException
     * @ar.org.fitc.spec_ref
     */
    @Override
    public synchronized final int read(byte[] arg0, int arg1, int arg2)
            throws IOException {
        fillBuffer();
        if (available == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (available > 0) {
            notify();
        }
        int bytesRead = bais.read(arg0, arg1, arg2);
        available -= bytesRead;
        return bytesRead;
    }
}