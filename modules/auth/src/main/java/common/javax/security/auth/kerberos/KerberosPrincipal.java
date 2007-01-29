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

package javax.security.auth.kerberos;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.Principal;
import java.util.StringTokenizer;

import org.apache.harmony.auth.internal.kerberos.v5.KerberosException;
import org.apache.harmony.auth.internal.kerberos.v5.KrbClient;
import org.apache.harmony.auth.internal.kerberos.v5.PrincipalName;
import org.apache.harmony.auth.internal.nls.Messages;
import org.apache.harmony.security.asn1.ASN1StringType;

public final class KerberosPrincipal implements Principal, Serializable {

    private static final long serialVersionUID = -7374788026156829911L;

    public static final int KRB_NT_UNKNOWN = 0;

    public static final int KRB_NT_PRINCIPAL = 1;

    public static final int KRB_NT_SRV_INST = 2;

    public static final int KRB_NT_SRV_HST = 3;

    public static final int KRB_NT_SRV_XHST = 4;

    public static final int KRB_NT_UID = 5;

    // the full name of principal
    private transient String name;

    // the realm
    private transient String realm;

    // type of the principal
    private transient int type;

    private void init(String name) {

        // FIXME: correctly implement parsing name according to RFC 1964
        // http://www.ietf.org/rfc/rfc1964.txt
        if (name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException(Messages.getString("auth.23")); //$NON-NLS-1$
        }

        int pos = name.indexOf('@');
        if (pos != -1) {
            realm = name.substring(pos + 1, name.length());

            // verify realm name according to RFC 1964(2.1.1 (2))
            // check invalid chars '/', ':' and null
            if (realm.indexOf('/') != -1 || realm.indexOf(':') != -1
                    || realm.indexOf(0) != -1) {
                throw new IllegalArgumentException(Messages
                        .getString("auth.24")); //$NON-NLS-1$
            }

            this.name = name;
        } else {
            // look for default realm name
            try {
                realm = KrbClient.getRealm();
            } catch (KerberosException e) {
                throw new IllegalArgumentException(e);
            }

            if (realm != null) {
                this.name = name + '@' + realm;
            } else {
                this.name = name;
            }
        }
    }

    public KerberosPrincipal(String name) {
        init(name);
        type = KRB_NT_PRINCIPAL;
    }

    public KerberosPrincipal(String name, int type) {
        init(name);
        if (type < 0 || type > KRB_NT_UID) {
            throw new IllegalArgumentException(Messages.getString("auth.25")); //$NON-NLS-1$
        }
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getRealm() {
        return realm;
    }

    public int getNameType() {
        return type;
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof KerberosPrincipal)) {
            return false;
        }

        KerberosPrincipal that = (KerberosPrincipal) obj;

        return (that.name.equals(this.name) && that.type == this.type);
    }

    @Override
    public String toString() {
        return name;
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {

        s.defaultReadObject();

        PrincipalName principalName = (PrincipalName) PrincipalName.ASN1.decode((byte[]) s
                .readObject());
        realm = (String) ASN1StringType.GENERALSTRING.decode((byte[]) s.readObject());

        String[] nameString = principalName.getName();
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < (nameString.length - 1); i++) {
            buf.append(nameString[i]);
            buf.append('/');
        }
        // append last name element
        buf.append(nameString[nameString.length - 1]);

        // append realm
        buf.append('@');
        buf.append(realm);

        name = buf.toString();

        type = principalName.getType();

        //FIXME: verify serialized values
    }

    private void writeObject(ObjectOutputStream s) throws IOException {

        s.defaultWriteObject();

        String[] nameString;

        // FIXME: ignores escaped '/','@' chars
        int pos = name.indexOf('@');
        String str = name.substring(0, pos);
        if (name.indexOf('/') == -1) {
            //there is only one component in principal name
            nameString = new String[] { str };
        } else {
            StringTokenizer strTknzr = new StringTokenizer(str, "/"); //$NON-NLS-1$
            nameString = new String[strTknzr.countTokens()];
            for (int i = 0; i < nameString.length; i++) {
                nameString[i] = strTknzr.nextToken();
            }
        }

        byte[] enc = PrincipalName.ASN1.encode(new PrincipalName(type, nameString));
        s.writeObject(enc);

        enc = ASN1StringType.GENERALSTRING.encode(realm);
        s.writeObject(enc);
    }
}
