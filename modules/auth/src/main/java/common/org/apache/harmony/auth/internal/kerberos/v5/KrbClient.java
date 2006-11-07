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

package org.apache.harmony.auth.internal.kerberos.v5;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.crypto.SecretKey;

import org.apache.harmony.auth.internal.nls.Messages;
import org.apache.harmony.security.asn1.DerInputStream;

/**
 * @see http://www.ietf.org/rfc/rfc4120.txt
 */
public class KrbClient {

    private static final int BUF_SIZE = 1024;

    private KrbClient() {
        // no objects
    }

    /**
     * Get credentials from Authentication Service.
     * 
     * @param address - service host
     * @param port - service port
     * @param cname - client's principal identifier
     * @param realm - client's realm
     * @return - ticket
     */
    public static KDCReply doAS(InetAddress address, int port,
            PrincipalName cname, String realm, PrincipalName sname,
            SecretKey key) {

        KDCRequest request = new KDCRequest(KDCRequest.AS_REQ, cname, realm,
                sname);

        try {
            DatagramSocket socket = request.send(address, port);

            ByteArrayOutputStream out = new ByteArrayOutputStream(BUF_SIZE);

            byte[] buf = new byte[BUF_SIZE];

            DatagramPacket resp = new DatagramPacket(buf, buf.length);

            int bytesRead = BUF_SIZE;
            while (bytesRead == BUF_SIZE) {
                socket.receive(resp);

                bytesRead = resp.getLength();
                out.write(buf, resp.getOffset(), bytesRead);
            }
            DerInputStream in = new DerInputStream(out.toByteArray());

            if (in.tag == KDCReply.AS_REP_ASN1.constrId) { //TODO AS reply
                KDCReply reply = (KDCReply) KDCReply.AS_REP_ASN1.decode(in);

                reply.decrypt(key);

                return reply;
            } else if (in.tag == KerberosErrorMessage.ASN1.constrId) {
                KerberosErrorMessage errMsg = KerberosErrorMessage.decode(in);
                // auth.52=Error code: {0}
                throw new RuntimeException(Messages.getString(
                        "auth.52", errMsg.getErrorCode())); //$NON-NLS-1$
            } else {
                new RuntimeException(); //FIXME
            }

        } catch (IOException e) {
            new RuntimeException(e); //FIXME 
        }

        return null;
    }
}
