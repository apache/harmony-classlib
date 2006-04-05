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
* @author Alexander Y. Kleymenov
* @version $Revision$
*/

package org.apache.harmony.security;

import java.security.PublicKey;
import java.util.Arrays;

import org.apache.harmony.crypto.utils.AlgNameMapper;

/**
 * PublicKeyImpl
 */
public class PublicKeyImpl implements PublicKey {
    
    private final byte[] encoding;
    private final String algorithm;
    
    /**
     * Constructs the PublicKey instance with specified algorithm oid
     * and ASN.1 DER encoded form of SubjectPublicKeyInfo structure
     * which is ASN.1 data format for public keys in X.509 standard.
     * @param   oid :   String
     * @param   encoding    :   byte[]
     */
    public PublicKeyImpl(String oid, byte[] encoding) {
        this.encoding = new byte[encoding.length];
        System.arraycopy(encoding, 0, this.encoding, 0, encoding.length);
        String alg = AlgNameMapper.map2AlgName(oid);
        algorithm = (alg == null) ? oid : alg;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public String getFormat() {
        return "X.509";
    }

    public byte[] getEncoded() {
        byte[] result = new byte[encoding.length];
        System.arraycopy(encoding, 0, result, 0, encoding.length);
        return result;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof PublicKey)) {
            return false;
        }
        PublicKey obj = (PublicKey) other;
        return Arrays.equals(encoding, obj.getEncoded());
    }
}

