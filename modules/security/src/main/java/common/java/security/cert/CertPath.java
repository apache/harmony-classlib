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
* @author Vladimir N. Molotkov
* @version $Revision$
*/

package java.security.cert;

import java.io.ByteArrayInputStream;
import java.io.NotSerializableException;
import java.io.ObjectStreamException;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * @com.intel.drl.spec_ref
 * 
 */
public abstract class CertPath implements Serializable {
    /**
     * @com.intel.drl.spec_ref
     */
    private static final long serialVersionUID = 6068470306649138683L;
    // Standard name of the type of certificates in this path
    private final String type;

    /**
     * @com.intel.drl.spec_ref
     */
    protected CertPath(String type) {
        this.type = type;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String getType() {
        return type;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof CertPath) {
            CertPath o = (CertPath)other;
            if (getType().equals(o.getType())) {
                if (getCertificates().equals(o.getCertificates())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int hashCode() {
        int hash = getType().hashCode();
        hash = hash*31 + getCertificates().hashCode();
        return hash;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String toString() {
        StringBuffer sb = new StringBuffer(getType());
        sb.append(" Cert Path, len=");
        sb.append(getCertificates().size());
        sb.append(": [\n");
        int n=1;
        for (Iterator i=getCertificates().iterator();
                      i.hasNext(); n++) {
            sb.append("---------------certificate ");
            sb.append(n);
            sb.append("---------------\n");
            sb.append(((Certificate)i.next()).toString());
        }
        sb.append("\n]");
        return sb.toString();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public abstract List<? extends Certificate> getCertificates();

    /**
     * @com.intel.drl.spec_ref
     */
    public abstract byte[] getEncoded()
        throws CertificateEncodingException;

    /**
     * @com.intel.drl.spec_ref
     */
    public abstract byte[] getEncoded(String encoding)
        throws CertificateEncodingException;

    /**
     * @com.intel.drl.spec_ref
     */
    public abstract Iterator<String> getEncodings();

    /**
     * @com.intel.drl.spec_ref
     */
    protected Object writeReplace() throws ObjectStreamException {
        try {
            return new CertPathRep(getType(), getEncoded());
        } catch (CertificateEncodingException e) {
            throw new NotSerializableException (
                    "Could not create serialization object: " + e);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    protected static class CertPathRep implements Serializable {
        /**
         * @com.intel.drl.spec_ref
         */
        private static final long serialVersionUID = 3015633072427920915L;
        // Standard name of the type of certificates in this path
        private final String type;
        // cert path data
        private final byte[] data;

        // Force default serialization to use writeUnshared/readUnshared
        // for cert path data
        private static final ObjectStreamField[] serialPersistentFields = {
             new ObjectStreamField("type", String.class),
             new ObjectStreamField("data", byte[].class, true)
        };

        /**
         * @com.intel.drl.spec_ref
         */
        protected CertPathRep(String type, byte[] data) {
            this.type = type;
            this.data = data;
        }

        /**
         * @com.intel.drl.spec_ref
         */
        protected Object readResolve() throws ObjectStreamException {
            try {
                CertificateFactory cf = CertificateFactory.getInstance(type);
                return cf.generateCertPath(new ByteArrayInputStream(data));
            } catch (Throwable t) {
                throw new NotSerializableException(
                        "Could not resolve cert path: " + t);
            }
        }
    }
}
