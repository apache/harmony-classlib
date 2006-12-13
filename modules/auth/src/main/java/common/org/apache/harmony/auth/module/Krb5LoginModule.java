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

package org.apache.harmony.auth.module;

import java.net.InetAddress;
import java.util.Map;

import javax.security.auth.DestroyFailedException;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.kerberos.KerberosKey;
import javax.security.auth.kerberos.KerberosPrincipal;
import javax.security.auth.kerberos.KerberosTicket;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.apache.harmony.auth.internal.kerberos.v5.KDCReply;
import org.apache.harmony.auth.internal.kerberos.v5.KrbClient;
import org.apache.harmony.auth.internal.kerberos.v5.PrincipalName;

public class Krb5LoginModule implements LoginModule {

    // default kdc server
    private static final String DEFAULT_KDC = "java.security.krb5.kdc"; //$NON-NLS-1$

    // default realm
    private static final String DEFAULT_REALM = "java.security.krb5.realm"; //$NON-NLS-1$

    // client's principal identifier name
    private static final String PRINCIPAL = "principal";//$NON-NLS-1$

    private Subject subject;

    private Map<String, ?> options;

    private CallbackHandler callbackHandler;

    private KerberosPrincipal client;
    
    private KerberosTicket krbTicket;
    
    public boolean abort() throws LoginException {
        
        try {
            krbTicket.destroy();
        } catch (DestroyFailedException e) {
            throw new LoginException();
        }

        client = null;
        krbTicket = null;
        
        return true;
    }

    public boolean commit() throws LoginException {
        subject.getPrincipals().add(client);
        subject.getPrivateCredentials().add(krbTicket);
        return true;
    }

    public void initialize(Subject subject, CallbackHandler callbackHandler,
            Map<String, ?> sharedState, Map<String, ?> options) {
        // TODO
        this.subject = subject;
        this.options = options;
        this.callbackHandler = callbackHandler;
    }

    public boolean login() throws LoginException {
        //TODO put in doPrivileged
        String kdc = System.getProperty(DEFAULT_KDC);
        String realm = System.getProperty(DEFAULT_REALM);
        if (kdc == null && realm != null || kdc != null && realm == null) {
            // both properties should be set or unset together
            throw new LoginException();//FIXME message
        } else if (kdc == null && realm == null) {
            // reading config from configuration file 'krb5.conf'
            throw new LoginException();//FIXME not yet implemented
        }

        String name = (String) options.get(PRINCIPAL);

        if (name == null) {
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

        PrincipalName krbtgt = new PrincipalName(PrincipalName.NT_SRV_XHST,
                new String[] { "krbtgt", realm }); //$NON-NLS-1$

        try {
            // get client's password
            PasswordCallback callback = new PasswordCallback("Password for "
                    + name, false);
            callbackHandler.handle(new Callback[] { callback });

            KerberosKey key = new KerberosKey(new KerberosPrincipal(name + '@'
                    + realm, KerberosPrincipal.KRB_NT_UNKNOWN), callback
                    .getPassword(), "DES");

            KDCReply reply = KrbClient.doAS(InetAddress.getByName(kdc), port,
                    cname, realm, krbtgt, key);

            // add principal to subject
            String[] pName = reply.getCname().getName();
            StringBuilder buf = new StringBuilder();
            for (int i = 0; i < pName.length - 1; i++) {
                buf.append(pName[i]);
                buf.append('/');
            }
            buf.append(pName[pName.length - 1]);
            buf.append('@');
            buf.append(reply.getCrealm());

            client = new KerberosPrincipal(buf.toString(),
                    reply.getCname().getType());

            // add ticket to private credentials
            byte[] ticket = reply.getTicket().getEncoded();

            String[] sName = reply.getSname().getName();
            buf = new StringBuilder();
            for (int i = 0; i < sName.length - 1; i++) {
                buf.append(sName[i]);
                buf.append('/');
            }
            buf.append(sName[sName.length - 1]);
            buf.append('@');
            buf.append(reply.getSrealm());

            KerberosPrincipal server = new KerberosPrincipal(buf.toString(),
                    reply.getSname().getType());

            int keyType = reply.getKey().getType();
            byte[] sessionKey = reply.getKey().getValue();

            boolean[] flags = reply.getFlags().toBooleanArray();

            krbTicket = new KerberosTicket(ticket, client,
                    server, sessionKey, keyType, flags, reply.getAuthtime(),
                    reply.getStarttime(), reply.getEndtime(), reply
                            .getRenewtill(),
                    //TODO InetAddress[] clientAddresses
                    null);

            return true; //FIXME 
        } catch (Exception e) {
            LoginException ex = new LoginException();
            ex.initCause(e);
            throw ex;
        }
    }

    public boolean logout() throws LoginException {

        subject.getPrincipals().remove(client);
        subject.getPrivateCredentials().remove(krbTicket);
        
        try {
            krbTicket.destroy();
        } catch (DestroyFailedException e) {
            throw new LoginException();
        }

        client = null;
        krbTicket = null;

        return true;
    }
}
