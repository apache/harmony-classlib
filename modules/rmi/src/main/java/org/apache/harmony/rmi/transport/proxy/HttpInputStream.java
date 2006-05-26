/*
 * Copyright 2005-2006 The Apache Software Foundation or its licensors, as applicable
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author  Mikhail A. Markov, Vasily Zakharov
 * @version $Revision: 1.1.2.2 $
 */
package org.apache.harmony.rmi.transport.proxy;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.harmony.rmi.common.RMILog;


/**
 * InputStream for HTTP connections. Unwraps data from HTTP packets.
 *
 * @author  Mikhail A. Markov, Vasily Zakharov
 * @version $Revision: 1.1.2.2 $
 */
public class HttpInputStream extends FilterInputStream
        implements ProxyConstants {

    /**
     * If this is inbound connection stream.
     */
    private boolean inbound;

    /**
     * Amount of data available in the stream.
     */
    private int available;

    /**
     * Constructs this stream from the given input stream.
     * The stream is considered operational after <code>expect</code>
     * string is received from the stream.
     * If <code>expect</code> is <code>null</code>,
     * the stream is considered operational immediately.
     *
     * @param   in
     *          Input stream.
     *
     * @param   inbound
     *          If this is inbound connection stream.
     */
    public HttpInputStream(InputStream in, boolean inbound) {
        super(new DataInputStream(in));
        this.inbound = inbound;
        this.available = (-1);
    }

    /**
     * Always returns <code>false</code> (mark operations are not supported).
     *
     * @return  <code>false</code>
     */
    public final boolean markSupported() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public final int available() throws IOException {
        if (available < 0) {
            readHeader();
        }

        return available;
    }

    /**
     * {@inheritDoc}
     */
    public final int read() throws IOException {
        if (available < 0) {
            readHeader();
        }

        if (available < 1) {
            return (-1);
        }

        int data = in.read();

        if (data != (-1)) {
            available--;

            if (proxyTransportLog.isLoggable(RMILog.VERBOSE)) {
                proxyTransportLog.log(RMILog.VERBOSE,
                        "Read 1 byte, " + available + " remaining.");
            }
        }

        return data;
    }

    /**
     * {@inheritDoc}
     */
    public final int read(byte[] b, int off, int len) throws IOException {
        if (available < 0) {
            readHeader();
        }

        if (available < 1) {
            return (-1);
        }

        if (len > available) {
            len = available;
        }

        int readSize = in.read(b, off, len);

        assert (readSize <= len) : "readSize is greater than len";

        available -= readSize;

        if (proxyTransportLog.isLoggable(RMILog.VERBOSE)) {
            proxyTransportLog.log(RMILog.VERBOSE,
                    "Read " + readSize + " bytes, " + available + " remaining.");
        }

        return readSize;
    }

    /**
     * Reads the next line of text from the stream
     * using {@link DataInputStream#readLine()}.
     *
     * @return  Next line of text from the input stream,
     *          or <code>null</code> if end of file is encountered
     *          before even one byte can be read.
     *
     * @throws  IOException
     *          If I/O error occurs.
     */
    public final String readLine() throws IOException {
        if (available < 0) {
            readHeader();
        }

        return ((DataInputStream) in).readLine();
    }

    /**
     * Reads HTTP header, sets {@link #available} amount of data.
     *
     * @throws  IOException
     *          If I/O error occurs.
     */
    private void readHeader() throws IOException {
        String expectName;
        String expectHeader;

        if (inbound) {
            expectName = "POST request";
            expectHeader = HTTP_REQUEST_SIGNATURE;
        } else {
            expectName = "HTTP response";
            expectHeader = HTTP_RESPONSE_HEADER_SIGNATURE;
        }

        String[] errorMessages = {
                ("Unable to read header data, couldn't find " + expectName),
                "Unable to read header data, Content-Length not specified",
                "Unable to read input stream data, no data found"
        };

        // Looking for headers phases sequentially.
        for (int phase = 0; ; phase++) {
            assert ((phase >= 0) && (phase <= 2))
                    : ("Incorrect phase: " + phase);

            String expectSubject;
            String expectString;
            int expectStringLength;

            switch (phase) {
            case 0:
                expectSubject = expectName;
                expectString = expectHeader;
                expectStringLength = expectHeader.length();
                break;
            case 1:
                expectSubject = "Content-Length specification";
                expectString = CONTENT_LENGTH_SIGNATURE;
                expectStringLength = CONTENT_LENGTH_SIGNATURE_LENGTH;
                break;
            default: // 2
                expectSubject = null;
                expectString = null;
                expectStringLength = 0;
                break;
            }

            // Searching for the expected string for this phase.
            //
            // Note: here we ignore the following for simplicity:
            //          Header fields can be extended over multiple lines
            //          by preceding extra lines with spaces(s) or tab(s).
            //          (See RFC 2616 4.2)
            while (true) {
                String line = ((DataInputStream) in).readLine();

                // Checking for EOF.
                if (line == null) {
                    throw new EOFException(errorMessages[phase]);
                }

                // Diagnostic print.
                if (proxyTransportLog.isLoggable(RMILog.VERBOSE)) {
                    proxyTransportLog.log(RMILog.VERBOSE,
                            "Header line received: [" + line + "].");
                }

                // Checking for empty line.
                if (line.length() < 1) {
                    if (phase < 2) {
                        throw new EOFException(errorMessages[phase]);
                    } else { // phase == 2
                        // Empty line found, end of headers, everything's fine.
                        if (proxyTransportLog.isLoggable(RMILog.VERBOSE)) {
                            proxyTransportLog.log(RMILog.VERBOSE,
                                    "Input stream data found, stream ready.");
                        }
                        return;
                    }
                }

                if (phase > 1) {
                    // Just skip non-empty lines after Content-Length is found.
                    continue;
                }

                // Checking for expected line, using case sensitive comparison
                // for phase 0 and case insensitive comparison for phase 1.
                //
                // Note:    The reason phrase should be ignored.
                //          (See RFC 2616 6.1.1)
                //
                // Note: here we ignore codes other than 200 for simplicity.
                //          (See RFC 2616 6.1.1)
                if (line.regionMatches(
                        (phase == 1), 0, expectString, 0, expectStringLength)) {
                    if (proxyTransportLog.isLoggable(RMILog.VERBOSE)) {
                        proxyTransportLog.log(RMILog.VERBOSE,
                                expectSubject + " found.");
                    }

                    if (phase == 1) {
                        // Found Content-Length specification.
                        try {
                            available = Integer.parseInt(
                                    line.substring(expectStringLength).trim());
                        } catch (NumberFormatException e) {
                            throw new IOException(
                                    "Content-Length specified incorrectly: "
                                    + line);
                        }

                        if (available < 0) {
                            throw new IOException(
                                    "Invalid Content-Length: " + available);
                        }

                        if (proxyTransportLog.isLoggable(RMILog.VERBOSE)) {
                            proxyTransportLog.log(RMILog.VERBOSE,
                                    "Content-Length received: " + available);
                        }
                    }
                    // Move to the next phase.
                    break;
                }
                // Skipping this line.
            }
        }
    }
}
