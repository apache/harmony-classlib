/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.harmony.auth.module;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.apache.harmony.auth.internal.kerberos.v5.KDCRequest;
import org.apache.harmony.auth.internal.kerberos.v5.KerberosErrorMessage;
import org.apache.harmony.auth.internal.kerberos.v5.PrincipalName;
import org.apache.harmony.auth.internal.nls.Messages;
import org.apache.harmony.security.asn1.DerInputStream;

public class Krb5LoginModule implements LoginModule {

    private static final int BUF_SIZE = 1024;

    private static final String PRINCIPAL = "cname"; //$NON-NLS-1$

    private static final String REALM = "realm"; //$NON-NLS-1$

    private static final String KDC = "kdc"; //$NON-NLS-1$

    private Map<String, ?> options;

    public boolean abort() throws LoginException {
        // TODO
        return false;
    }

    public boolean commit() throws LoginException {
        // TODO
        return false;
    }

    public void initialize(Subject subject, CallbackHandler callbackHandler,
            Map<String, ?> sharedState, Map<String, ?> options) {

        // TODO
        this.options = options;
    }

    public boolean login() throws LoginException {
        String kdc = (String) options.get(KDC);
        String name = (String) options.get(PRINCIPAL);
        String realm = (String) options.get(REALM);

        if (name == null || realm == null || kdc == null) {
            throw new LoginException();//FIXME check params
        }

        int port = 88;//default
        int pos = kdc.indexOf(':');
        if (pos != -1) {
            port = Integer.parseInt(kdc.substring(pos + 1));
            kdc = kdc.substring(0, pos);
        }

        PrincipalName cname = new PrincipalName(PrincipalName.NT_UNKNOWN,
                new String[] { name });

        KDCRequest request = KDCRequest.createASRequest(cname, realm);

        try {
            DatagramSocket socket = request.send(InetAddress.getByName(kdc),
                    port);

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

            if (in.tag == 0) { //TODO AS reply
                throw new RuntimeException();//FIXME
            } else if (in.tag == KerberosErrorMessage.ASN1.constrId) {
                KerberosErrorMessage errMsg = KerberosErrorMessage.decode(in);
                // auth.52=Error code: {0}
                throw new LoginException(Messages.getString("auth.52", errMsg.getErrorCode())); //$NON-NLS-1$
            } else {
                new LoginException(); //FIXME message
            }

        } catch (UnknownHostException e) {
            LoginException ex = new LoginException();
            ex.initCause(e);
            throw ex;
        } catch (IOException e) {
            LoginException ex = new LoginException();
            ex.initCause(e);
            throw ex;
        }
        return false;
    }

    public boolean logout() throws LoginException {
        // TODO
        return false;
    }
}
