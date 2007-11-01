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

import javax.naming.ldap.Control;
import javax.net.SocketFactory;

import org.apache.harmony.jndi.provider.ldap.asn1.ASN1Decodable;
import org.apache.harmony.jndi.provider.ldap.asn1.ASN1Encodable;
import org.apache.harmony.jndi.provider.ldap.asn1.LdapASN1Constant;
import org.apache.harmony.security.asn1.ASN1Integer;

/**
 * LdapClient is the actual class used to communicate with Ldap Server.
 * 
 */
final public class LdapClient {
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
        socket = factory.createSocket(address, port);
        in = socket.getInputStream();
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
}
