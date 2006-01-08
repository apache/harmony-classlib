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
/**
* @author Maxim V. Makarov, Stepan M. Mishura
* @version $Revision$
*/

package javax.security.auth.kerberos;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.Principal;

/**
 * @com.intel.drl.spec_ref
 */
public final class KerberosPrincipal implements Principal, Serializable {

    /**
     * @com.intel.drl.spec_ref
     */
    private static final long serialVersionUID = -7374788026156829911L;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int KRB_NT_UNKNOWN = 0;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int KRB_NT_PRINCIPAL = 1;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int KRB_NT_SRV_INST = 2;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int KRB_NT_SRV_HST = 3;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int KRB_NT_SRV_XHST = 4;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int KRB_NT_UID = 5;

    // the full name of principal
    private transient String name;
    // the realm
    private transient String realm;
    // type of the principal 
    private transient int type;

    // TODO: It is gag.
    // When KerberosPrincipla will be realize then this method 
    // should be delete or modify
    private void init(String name) {
        
        // FIXME: correctly implement parsing name according to RFC 1964
        // http://www.ietf.org/rfc/rfc1964.txt
        if (name == null || name.trim().length()==0) {
            throw new IllegalArgumentException("Invalid principal name");
        }
        int pos = name.indexOf('@');
        if (pos != -1) {
            realm = name.substring(pos + 1, name.length());
        } else {
            throw new UnsupportedOperationException();
        }
        this.name = name;
    }

    /**
     * @com.intel.drl.spec_ref 
     */
    public KerberosPrincipal(String name) {
        //TODO: If principal name does't specify then a default realm 
        // should be read from krb.conf file else IllegalArgumentException
        // should be throw
        init(name);
        type = KRB_NT_PRINCIPAL;
    }

    /**
     * @com.intel.drl.spec_ref 
     */
    public KerberosPrincipal(String name, int type) {
        //TODO: If principal name does't specify then a default realm 
        // should be read from krb.conf file else IllegalArgumentException
        // should be throw
        init(name);
        //TODO: it is gag
        if (type > KRB_NT_UID) {
            throw new IllegalArgumentException("Invalid name type");
        }
        this.type = type;
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     * <code>toString</code> in interface <code>Principal</code>
     */
    public String getName() {
        return name;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String getRealm() {
        return realm;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int getNameType() {
        return type;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int hashCode() {
        return getName().hashCode();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof KerberosPrincipal) {
            return false;
        }

        KerberosPrincipal that = (KerberosPrincipal)obj;
        
        return (that.name.equals(this.name) && that.type == this.type);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String toString() {
        return super.toString();
    }

    // TODO: read a object from a stream
    private void readObject(ObjectInputStream s) throws IOException,
        ClassNotFoundException {
        s.defaultReadObject();
    }

    // TODO: write a object to a stream
    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
    }

}