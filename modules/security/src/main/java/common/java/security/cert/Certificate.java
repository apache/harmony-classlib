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
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.util.Arrays;

/**
 * @com.intel.drl.spec_ref
 * 
 */
public abstract class Certificate implements Serializable {
    /**
     * @com.intel.drl.spec_ref
     */
    private static final long serialVersionUID = -3585440601605666277L;

    // The standard name of the certificate type
    private final String type;

    /**
     * @com.intel.drl.spec_ref
     */
    protected Certificate(String type) {
        this.type = type;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public final String getType() {
        return type;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean equals(Object other) {
        // obj equal to itself
        if (this == other) {
            return true;
        }
        if (other instanceof Certificate) {
            try {
                // check that encoded forms match
                return Arrays.equals(this.getEncoded(),
                        ((Certificate)other).getEncoded());
            } catch (CertificateEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int hashCode() {
        try {
            byte[] encoded = getEncoded();
            int hash = 0;
            for (int i=0; i<encoded.length; i++) {
                hash += i*encoded[i];
            }
            return hash;
        } catch (CertificateEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public abstract byte[] getEncoded() throws CertificateEncodingException;

    /**
     * @com.intel.drl.spec_ref
     */
    public abstract void verify(PublicKey key)
        throws CertificateException,
               NoSuchAlgorithmException,
               InvalidKeyException,
               NoSuchProviderException,
               SignatureException;

    /**
     * @com.intel.drl.spec_ref
     */
    public abstract void verify(PublicKey key, String sigProvider)
        throws CertificateException,
               NoSuchAlgorithmException,
               InvalidKeyException,
               NoSuchProviderException,
               SignatureException;

    /**
     * @com.intel.drl.spec_ref
     */
    public abstract String toString();

    /**
     * @com.intel.drl.spec_ref
     */
    public abstract PublicKey getPublicKey();

    /**
     * @com.intel.drl.spec_ref
     */
    protected Object writeReplace() throws ObjectStreamException {
        try {
            return new CertificateRep(getType(), getEncoded());
        } catch (CertificateEncodingException e) {  
            throw new NotSerializableException (
                    "Could not create serialization object: " + e);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     */
    protected static class CertificateRep implements Serializable {
        /**
         * @com.intel.drl.spec_ref
         */
        private static final long serialVersionUID = -8563758940495660020L;
        // The standard name of the certificate type
        private final String type;
        // The certificate data
        private final byte[] data;

        // Force default serialization to use writeUnshared/readUnshared
        // for the certificate data
        private static final ObjectStreamField[] serialPersistentFields = {
             new ObjectStreamField("type", String.class),
             new ObjectStreamField("data", byte[].class, true)
        };

        /**
         * @com.intel.drl.spec_ref
         */
        protected CertificateRep(String type, byte[] data) {
            this.type = type;
            this.data = data;
        }

        /**
         * @com.intel.drl.spec_ref
         */
        protected Object readResolve() throws ObjectStreamException {
            try {
                CertificateFactory cf = CertificateFactory.getInstance(type);
                return cf.generateCertificate(new ByteArrayInputStream(data));
            } catch (Throwable t) {
                throw new NotSerializableException(
                        "Could not resolve certificate: " + t);
            }
        }
    }
}
