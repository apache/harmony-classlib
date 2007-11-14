/* 
 *  Licensed to the Apache Software Foundation (ASF) under one or more 
 *  contributor license agreements.  See the NOTICE file distributed with 
 *  this work for additional information regarding copyright ownership. 
 *  The ASF licenses this file to You under the Apache License, Version 2.0 
 *  (the "License"); you may not use this file except in compliance with 
 *  the License.  You may obtain a copy of the License at 
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0 
 * 
 *  Unless required by applicable law or agreed to in writing, software 
 *  distributed under the License is distributed on an "AS IS" BASIS, 
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 *  See the License for the specific language governing permissions and 
 *  limitations under the License. 
 */

package org.apache.harmony.jndi.provider.ldap;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Hashtable;

import javax.naming.CommunicationException;
import javax.naming.ConfigurationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.Control;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

import org.apache.harmony.jndi.internal.nls.Messages;
import org.apache.harmony.jndi.provider.ldap.asn1.ASN1Decodable;
import org.apache.harmony.jndi.provider.ldap.asn1.ASN1Encodable;
import org.apache.harmony.jndi.provider.ldap.asn1.LdapASN1Constant;
import org.apache.harmony.security.asn1.ASN1Integer;

/**
 * LdapClient is the actual class used to communicate with Ldap Server.
 * 
 */
public class LdapClient {
    /*
     * Socket used to communicate with Ldap Server.
     */
    private Socket socket;

    /*
     * Input stream of socket.
     */
    private InputStream in;

    /*
     * Output stream of socket.
     */
    private OutputStream out;

    /*
     * Address of connection
     */
    private String address;

    // constructor for test
    public LdapClient() {
        // do nothing
    }

    /**
     * Constructor for LdapClient.
     * 
     * @param factory
     *            used to construct socket through its factory method
     * @param address
     *            the Internet Protocol (IP) address of ldap server
     * @param port
     *            the port number of ldap server
     * @throws UnknownHostException
     *             if the host cannot be resolved
     * @throws IOException
     *             if an error occurs while instantiating the socket
     */
    public LdapClient(SocketFactory factory, String address, int port)
            throws UnknownHostException, IOException {
        this.address = address;
        socket = factory.createSocket(address, port);
        // FIXME: Use of InputStreamWrap here is to deal with a potential bug of
        // RI.
        in = new InputStreamWrap(socket.getInputStream());
        // in = socket.getInputStream();
        out = socket.getOutputStream();
    }

    /**
     * Carry out the ldap operation encapsulated in operation with controls.
     * 
     * @param operation
     *            the ldap operation
     * @param controls
     *            extra controls for some ldap operations
     * @return the encapsulated response message from ldap server
     * @throws IOException
     */
    public LdapMessage doOperation(LdapOperation operation, Control[] controls)
            throws IOException {
        return doOperation(operation.getRequestId(), operation.getRequest(),
                operation.getResponse(), controls);
    }

    /**
     * Send out the ldap operation in request with controls, and decode response
     * into LdapMessage.
     * 
     * @param opIndex
     * @param request
     *            the ldap request
     * @param response
     *            the ldap response
     * @param controls
     *            extra controls for some ldap operations
     * @return the encapsulated response message from ldap server
     * @throws IOException
     */
    public LdapMessage doOperation(int opIndex, ASN1Encodable request,
            ASN1Decodable response, Control[] controls) throws IOException {

        LdapMessage requestMsg = new LdapMessage(opIndex, request, controls);
        out.write(requestMsg.encode());
        out.flush();
        LdapMessage responseMsg = new LdapMessage(response);
        responseMsg.decode(in);

        if (opIndex == LdapASN1Constant.OP_SEARCH_REQUEST
                && responseMsg.getOperationIndex() != LdapASN1Constant.OP_SEARCH_RESULT_DONE) {
            responseMsg = new LdapMessage(response);
            responseMsg.decode(in);
        }
        return responseMsg;
    }

    public void abandon(final int messageId, Control[] controls)
            throws IOException {
        doOperationWithoutResponse(LdapASN1Constant.OP_ABANDON_REQUEST,
                new ASN1Encodable() {

                    public void encodeValues(Object[] values) {
                        values[0] = ASN1Integer.fromIntValue(messageId);
                    }

                }, controls);
    }

    public void doOperationWithoutResponse(int opIndex, ASN1Encodable op,
            Control[] controls) throws IOException {
        LdapMessage request = new LdapMessage(opIndex, op, controls);
        out.write(request.encode());
        out.flush();
    }

    public void close() throws IOException {
        socket.close();
    }

    /**
     * Get new instance of LdapClient according environment variable
     * 
     * @param envmt
     * @return
     * @throws NamingException
     */
    public static LdapClient newInstance(String host, int port,
            Hashtable<?, ?> envmt) throws NamingException {
        String factoryName = (String) envmt
                .get("java.naming.ldap.factory.socket");

        SocketFactory factory = null;
        if (factoryName == null || "".equals(factoryName)) {
            if ("ssl".equalsIgnoreCase((String) envmt
                    .get(Context.SECURITY_PROTOCOL))) {
                factory = SSLSocketFactory.getDefault();
            } else {
                factory = SocketFactory.getDefault();
            }
        } else {

            try {
                factory = (SocketFactory) classForName(factoryName)
                        .newInstance();
            } catch (Exception e) {
                ConfigurationException ex = new ConfigurationException();
                ex.setRootCause(e);
                throw ex;
            }
        }
        // TODO: get LdapClient from pool first.

        try {
            return new LdapClient(factory, host, port);

        } catch (IOException e) {
            CommunicationException ex = new CommunicationException();
            ex.setRootCause(e);
            throw ex;
        }
    }

    private static Class<?> classForName(final String className)
            throws ClassNotFoundException {

        Class<?> cls = null;
        // try thread context class loader first
        try {
            cls = Class.forName(className, true, Thread.currentThread()
                    .getContextClassLoader());
        } catch (ClassNotFoundException e) {
            // Ignored.
        }
        // try system class loader second
        try {
            cls = Class.forName(className, true, ClassLoader
                    .getSystemClassLoader());
        } catch (ClassNotFoundException e1) {
            // Ignored.
        }

        if (cls == null) {
            // jndi.1C=class {0} not found
            throw new ClassNotFoundException(Messages.getString(
                    "jndi.1C", className)); //$NON-NLS-1$
        }

        return cls;
    }

    // TODO: This class is used to deal with a potential bug of RI, may be
    // removed in the future.
    /**
     * When use <code>InputStream</code> from SSL Socket, if invoke
     * <code>InputStream.read(byte[])</code> with byte array of zero length,
     * the method will be blocked. Seems it's bug of ri.
     * 
     * This wrap class delegate all request to wrapped instance, except
     * returning immediately when the invoke
     * <code>InputStream.read(byte[])</code> with byte array of zero length.
     */
    static class InputStreamWrap extends InputStream {
        InputStream in;

        public InputStreamWrap(InputStream in) {
            this.in = in;
        }

        @Override
        public int read() throws IOException {
            return in.read();
        }

        @Override
        public int read(byte[] bs, int offset, int len) throws IOException {
            if (len == 0) {
                return 0;
            }
            return in.read(bs, offset, len);
        }

        @Override
        public void reset() throws IOException {
            in.reset();

        }

        @Override
        public int available() throws IOException {
            return in.available();
        }

        @Override
        public void close() throws IOException {
            in.close();
        }

        @Override
        public void mark(int readlimit) {
            in.mark(readlimit);
        }

        @Override
        public boolean markSupported() {
            return in.markSupported();
        }

        @Override
        public int read(byte[] b) throws IOException {
            return in.read(b);
        }

        @Override
        public long skip(long n) throws IOException {
            return in.skip(n);
        }
    }

    public String getAddress() {
        return address;
    }
}
