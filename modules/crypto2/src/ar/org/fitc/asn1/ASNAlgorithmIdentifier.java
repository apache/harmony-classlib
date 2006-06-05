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

package ar.org.fitc.asn1;

import java.io.IOException;
import java.io.InputStream;
import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;

/**
 * Represents the ASN.1 element's AlgoritmIdentifier
 * 
 * @author Diego Raúl Mercado
 * @version 1.2
 */
class ASNAlgorithmIdentifier extends ASNAbstractElement {
    /** Encapsulates the algorithm (object identifier) */
    private ASNObjectIdentifier oid;

    /**
     * Encapsulates the algorithm's name that has been mapped, or otherwise, the
     * oid's decimal notation
     */
    private String algName;

    /** Encapsulates ANY defined by <code>oid</code> */
    private ASNAlgParams params;

    /**
     * Constructor. Encodes the parameters.
     * 
     * @param algName
     *            the name of the algorithm or its OID
     * @param algParams
     *            the parameters of the algorithm or null
     * @throws NoSuchAlgorithmException
     *             if cannot get the OID from <code>algName</code>
     */
    public ASNAlgorithmIdentifier(String algName, AlgorithmParameters algParams)
            throws NoSuchAlgorithmException {
        tag = Tag.SEQUENCE;
        /*
         * algName could be: - "RSA" - "1.2.840....." - "OID.1.2.840....." -
         * other...
         */
        try {
            oid = new ASNObjectIdentifier(algName);
        } catch (NumberFormatException e) {
            // => is "RSA" or other...
            oid = AlgMap.getOID(algName);
            if (oid == null) {
                // => is other...
                throw new NoSuchAlgorithmException(
                        "Requested algorithm not supported");
            }
            // => is "RSA"
            this.algName = algName;
        }
        // => is "1.2.840....." or "OID.1.2.840....."
        this.algName = AlgMap.getAlgName(oid);
        if (this.algName == null) {
            // => if algName "OID.1.2.840.....",
            // then oid.toString() will return "1.2.840..."
            this.algName = oid.toString();
        }
        params = new ASNAlgParams(algParams);
    }

    /**
     * Constructor. Decodes the input stream <code>is</code>
     * 
     * @param is
     *            the input stream to consume
     * @throws IOException
     *             if cannot parse <code>is</code>
     */
    public ASNAlgorithmIdentifier(InputStream is) throws IOException {
        super(is);
        if (tag != Tag.SEQUENCE) {
            throw new IOException("Unexpected TAG");
        }

        // set OID
        oid = new ASNObjectIdentifier(is);

        // set algName
        algName = AlgMap.getAlgName(oid);
        if (algName == null) {
            algName = oid.toString();
        }

        // set algorithm parameters
        params = new ASNAlgParams(is, algName, contentLength - oid.length);
    }

    @Override
    public final byte[] getEncoded() throws IOException {
        return makeASNEncoded(concat(oid.getEncoded(), params.getEncoded()));
    }

    /**
     * @return algorithm's name (if an OID has been passed to the constructor
     *         and it has been mapped at AlgMap class, then the algorithm's name
     *         will be returned, otherwise the OID)
     */
    public final String getAlgName() {
        return algName;
    }

    public final AlgorithmParameters getAlgorithmParameters() {
        return params.getAlgorithmParameters();
    }
}
