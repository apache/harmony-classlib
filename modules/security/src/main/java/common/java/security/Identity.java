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
* @author Alexander V. Astapchuk
* @version $Revision$
*/

package java.security;

import java.io.Serializable;
import java.util.Vector;
import java.util.Arrays;

/**
 * @com.intel.drl.spec_ref 
 * @deprecated
 */
public abstract class Identity implements Principal, Serializable {
    /**
     * @com.intel.drl.spec_ref 
     */
    private static final long serialVersionUID = 3609922007826600659L;

    /**
     * @com.intel.drl.spec_ref 
     */
    private String name;

    /**
     * @com.intel.drl.spec_ref 
     */
    private PublicKey publicKey;

    /**
     * @com.intel.drl.spec_ref 
     */
    private String info = "no additional info";

    /**
     * @com.intel.drl.spec_ref 
     */
    private IdentityScope scope;

    /**
     * @com.intel.drl.spec_ref 
     */
    private Vector certificates;

    /**
     * @com.intel.drl.spec_ref 
     */
    protected Identity() {
    }

    /**
     * @com.intel.drl.spec_ref 
     */
    public Identity(String name) {
        this.name = name;
    }

    /**
     * @com.intel.drl.spec_ref 
     */
    public Identity(String name, IdentityScope scope)
            throws KeyManagementException {
        this(name);
        if (scope != null) {
            scope.addIdentity(this);
            this.scope = scope;
        }
    }

    /**
     * @com.intel.drl.spec_ref 
     */
    public void addCertificate(Certificate certificate)
            throws KeyManagementException {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkSecurityAccess("addIdentityCertificate");
        }
        PublicKey certPK = certificate.getPublicKey();
        if (publicKey != null) {
            if (!checkKeysEqual(publicKey, certPK)) {
                throw new KeyManagementException("Cert's public key does not"
                        + " match Identity's public key");
            }
        } else {
            publicKey = certPK;
        }
        if (certificates == null) {
            certificates = new Vector();
        }
        certificates.add(certificate);
    }

    /**
     * @com.intel.drl.spec_ref 
     */
    private static boolean checkKeysEqual(PublicKey pk1, PublicKey pk2) {
        // first, they should have the same format
        // second, their encoded form must be the same

        // assert(pk1 != null);
        // assert(pk2 != null);

        String format1 = pk1.getFormat();
        String format2;
        if ((pk2 == null)
                || (((format2 = pk2.getFormat()) != null) ^ (format1 != null))
                || ((format1 != null) && !format1.equals(format2))) {
            return false;
        }

        return Arrays.equals(pk1.getEncoded(), pk2.getEncoded());
    }

    /**
     * @com.intel.drl.spec_ref 
     */
    public void removeCertificate(Certificate certificate)
            throws KeyManagementException {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkSecurityAccess("removeIdentityCertificate");
        }
        if (certificates != null) {
            certificates.removeElement(certificate);
        }
    }

    /**
     * @com.intel.drl.spec_ref 
     */
    public Certificate[] certificates() {
        if (certificates == null) {
            return new Certificate[0];
        }
        Certificate[] ret = new Certificate[certificates.size()];
        certificates.copyInto(ret);
        return ret;
    }

    /**
     * @com.intel.drl.spec_ref 
     */
    protected boolean identityEquals(Identity identity) {
        if (!name.equals(identity.name)) {
            return false;
        }

        if (publicKey == null) {
            return (identity.publicKey == null);
        }

        return checkKeysEqual(publicKey, identity.publicKey);
    }

    /**
     * @com.intel.drl.spec_ref 
     */
    public String toString(boolean detailed) {
        String s = toString();
        if (detailed) {
            s += " " + info;
        }
        return s;
    }

    /**
     * @com.intel.drl.spec_ref 
     */
    public final IdentityScope getScope() {
        return scope;
    }

    /**
     * @com.intel.drl.spec_ref 
     */
    public void setPublicKey(PublicKey key) throws KeyManagementException {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkSecurityAccess("setIdentityPublicKey");
        }
        // this check does not always work  
        if ((scope != null) && (key != null)) {
            Identity i = scope.getIdentity(key);
            //System.out.println("###DEBUG## Identity: "+i);
            if ((i != null) && (i != this)) {
                throw new KeyManagementException("key already used in scope");
            }
        }
        this.publicKey = key;
        certificates = null;
    }

    /**
     * @com.intel.drl.spec_ref 
     */
    public PublicKey getPublicKey() {
        return publicKey;
    }

    /**
     * @com.intel.drl.spec_ref 
     */
    public void setInfo(String info) {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkSecurityAccess("setIdentityInfo");
        }
        this.info = info;
    }

    /**
     * @com.intel.drl.spec_ref 
     */
    public String getInfo() {
        return info;
    }

    /**
     * @com.intel.drl.spec_ref 
     */
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Identity)) {
            return false;
        }
        Identity i = (Identity) obj;
        if (name.equals(i.name) && (scope == i.scope)) {
            return true;
        }
        return identityEquals(i);
    }

    /**
     * @com.intel.drl.spec_ref 
     */
    public final String getName() {
        return name;
    }

    /**
     * @com.intel.drl.spec_ref 
     */
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * @com.intel.drl.spec_ref 
     */
    public String toString() {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkSecurityAccess("printIdentity");
        }
        String s = this.name;
        if (scope != null) {
            s += " [" + scope.getName() + "]";
        }
        return s;
    }
}