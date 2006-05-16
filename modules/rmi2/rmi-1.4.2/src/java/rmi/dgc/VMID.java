/* 
*  Copyright 2005 The Apache Software Foundation or its licensors, as applicable. 
* 
*  Licensed under the Apache License, Version 2.0 (the "License"); 
*  you may not use this file except in compliance with the License. 
*  You may obtain a copy of the License at 
* 
*    http://www.apache.org/licenses/LICENSE-2.0 
* 
*  Unless required by applicable law or agreed to in writing, software 
*  distributed under the License is distributed on an "AS IS" BASIS, 
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
*  See the License for the specific language governing permissions and 
*  limitations under the License. 
*/
package java.rmi.dgc;

import java.io.Serializable;
import java.rmi.server.UID;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.SecureRandom;

/**
 * @ar.org.fitc.spec_ref
 * 
 */
public final class VMID implements Serializable {

    private static final long serialVersionUID = -538642295484486218L;

    private UID uid;

    // Address should be unique and constant for the lifetime of this object.
    private static String address;

    private static boolean isUnique;

    private String instanceAddress;

    // Obtains the localhost address, if not, obtains a random value.
    static {
        try {
            address = InetAddress.getLocalHost().getHostAddress();
            isUnique = true;
        } catch (UnknownHostException e) {
            /*
             * REVIEW: We are using a Random Long when we cannot get the local
             * host address.
             */
            address = Long.valueOf(new SecureRandom().nextLong()).toString();
            isUnique = false;
        }
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public VMID() {
        uid = new UID();
        instanceAddress = address;
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    @Override
    public int hashCode() {
        return instanceAddress.hashCode() ^ uid.hashCode();
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof VMID))
            return false;
        return (this.instanceAddress.equals(((VMID) obj).instanceAddress) && this.uid
                .equals(((VMID) obj).uid));
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    @Override
    public String toString() {
        return "[VMID: " + uid + ", " + instanceAddress + "]";
    }

    /**
     * @ar.org.fitc.spec_ref
     * 
     */
    @Deprecated
    public static boolean isUnique() {
        return isUnique;
    }
}
