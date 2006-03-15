/* Copyright 2004 The Apache Software Foundation or its licensors, as applicable
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
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package java.util.logging;

import java.net.Socket;
import java.io.BufferedOutputStream;
import java.io.IOException;

/**
 * A handler that writes log messages to a sockect connection.
 * <p>
 * This handler reads the following properties from the log manager to
 * initialize itself:
 * <ul>
 * <li>java.util.logging.ConsoleHandler.level specifies the logging level,
 * defaults to <code>Level.ALL</code> if this property is not found or has an
 * invalid value;
 * <li>java.util.logging.SocketHandler.filter specifies the name of the filter
 * class to be associated with this handler, defaults to <code>null</code> if
 * this property is not found or has an invalid value;
 * <li>java.util.logging.SocketHandler.formatter specifies the name of the
 * formatter class to be associated with this handler, defaults to
 * <code>java.util.logging.XMLFormatter</code> if this property is not found
 * or has an invalid value;
 * <li>java.util.logging.SocketHandler.encoding specifies the encoding this
 * handler will use to encode log messages, defaults to <code>null</code> if
 * this property is not found or has an invalid value.
 * <li>java.util.logging.SocketHandler.host specifies the name of the host that
 * this handler should connect to. There's no default value for this property.
 * <li>java.util.logging.SocketHandler.encoding specifies the port number that
 * this handler should connect to. There's no default value for this property.
 * </ul>
 * </p>
 * <p>
 * This handler buffers the outgoing messages, but flushes each time a log
 * record has been published.
 * </p>
 * <p>
 * This class is not thread-safe.
 * </p>
 * 
 */
public class SocketHandler extends StreamHandler {

    /*
     * -------------------------------------------------------------------
     * Constants
     * -------------------------------------------------------------------
     */

    // default level
    private static final String DEFAULT_LEVEL = "ALL"; //$NON-NLS-1$

    // default formatter
    private static final String DEFAULT_FORMATTER = "java.util.logging.XMLFormatter"; //$NON-NLS-1$

    /*
     * -------------------------------------------------------------------
     * Instance variables
     * -------------------------------------------------------------------
     */

    // the socket connection
    private Socket socket;

    /*
     * -------------------------------------------------------------------
     * Constructors
     * -------------------------------------------------------------------
     */

    /**
     * Constructs a <code>SocketHandler</code> object using the properties
     * read by the log manager, including the host name and port number.
     * 
     * @throws IOException
     *             If failed to connect to the specified host and port.
     * @throws IllegalArgumentException
     *             If the host name or port number is illegal.
     * @throws SecurityException
     *             If a security manager determines that the caller does not
     *             have the required permission to control this handler.
     */
    public SocketHandler() throws IOException {
        super(DEFAULT_LEVEL, null, DEFAULT_FORMATTER, null);
        initSocket(LogManager.getLogManager().getProperty(
                "java.util.logging.SocketHandler.host"), LogManager //$NON-NLS-1$
                .getLogManager().getProperty(
                        "java.util.logging.SocketHandler.port")); //$NON-NLS-1$
    }

    /**
     * Constructs a <code>SocketHandler</code> object using the specified host
     * name and port number together with other properties read by the log
     * manager.
     * 
     * @param host
     *            the host name
     * @param port
     *            the port number
     * @throws IOException
     *             If failed to connect to the specified host and port.
     * @throws IllegalArgumentException
     *             If the host name or port number is illegal.
     * @throws SecurityException
     *             If a security manager determines that the caller does not
     *             have the required permission to control this handler.
     */
    public SocketHandler(String host, int port) throws IOException {
        super(DEFAULT_LEVEL, null, DEFAULT_FORMATTER, null);
        initSocket(host, String.valueOf(port));
    }

    /*
     * -------------------------------------------------------------------
     * Methods
     * -------------------------------------------------------------------
     */

    // Initialize the socket connection and prepare the output stream
    private void initSocket(String host, String port) throws IOException {
        // check the validity of the host name
        if (null == host || "".equals(host)) { //$NON-NLS-1$
            throw new IllegalArgumentException("Illegal host argument."); //$NON-NLS-1$
        }
        // check the validity of the port number
        int p = 0;
        try {
            p = Integer.parseInt(port);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Illegal port argument."); //$NON-NLS-1$
        }
        if (p <= 0) {
            throw new IllegalArgumentException("Illegal port argument."); //$NON-NLS-1$
        }
        // establish the network connection
        try {
            this.socket = new Socket(host, p);
        } catch (IOException e) {
            getErrorManager().error(
                    "Failed to establish the network connection.", e, //$NON-NLS-1$
                    ErrorManager.OPEN_FAILURE);
            throw e;
        }
        super.internalSetOutputStream(new BufferedOutputStream(this.socket
                .getOutputStream()));
    }

    /**
     * Closes this handler. The network connection to the host is also closed.
     * 
     * @throws SecurityException
     *             If a security manager determines that the caller does not
     *             have the required permission to control this handler.
     */
    public void close() {
        try {
            super.close();
            if (null != this.socket) {
	            this.socket.close();
	            this.socket = null;
            }
        } catch (Exception e) {
            getErrorManager().error(
                    "Exception occured when closing the socket handler.", e, //$NON-NLS-1$
                    ErrorManager.CLOSE_FAILURE);
        }
    }

    /**
     * Logs a record if necessary. A flush operation will be done afterwards.
     * 
     * @param record
     *            the log record to be logged
     */
    public void publish(LogRecord record) {
        super.publish(record);
        super.flush();
    }

}


