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

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.apache.harmony.auth.internal.kerberos.v5.KrbClient;
import org.apache.harmony.auth.internal.kerberos.v5.PrincipalName;
import org.apache.harmony.auth.internal.kerberos.v5.Ticket;

public class Krb5LoginModule implements LoginModule {

    // default kdc server
    private static final String DEFAULT_KDC = "java.security.krb5.kdc"; //$NON-NLS-1$

    // default realm
    private static final String DEFAULT_REALM = "java.security.krb5.realm"; //$NON-NLS-1$

    // client's principal identifier name
    private static final String PRINCIPAL = "principal";//$NON-NLS-1$

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
            Ticket ticket = KrbClient.doAS(InetAddress.getByName(kdc), port,
                    cname, realm, krbtgt);

            return true; //FIXME 
        } catch (Exception e) {
            LoginException ex = new LoginException();
            ex.initCause(e);
            throw ex;
        }
    }

    public boolean logout() throws LoginException {
        // TODO
        return false;
    }
}
