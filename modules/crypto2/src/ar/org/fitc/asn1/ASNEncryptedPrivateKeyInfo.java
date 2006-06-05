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
 * Represents the ASN.1 element's EncryptedPrivateKeyInfo
 * 
 * @author Diego Raúl Mercado
 * @version 1.2
 */
public class ASNEncryptedPrivateKeyInfo extends ASNAbstractElement {
    /** Encapsulates algorithmIdentifier (oid + params) */
    private ASNAlgorithmIdentifier algorithmIdentifier;

    /**
     * Encapsulates the encrypted data that has been passed in any of the
     * constructors
     */
    private ASNOctetString encryptedKey;

    /**
     * Constructor. Encodes the parameters.
     * 
     * @param encrypted
     *            the encrypted data
     * @param algName
     *            the algorithm's name
     * @param algParams
     *            the algorithm's parameters or null
     * @throws NoSuchAlgorithmException
     *             if the specified <code>algName</code> is not supported
     */
    public ASNEncryptedPrivateKeyInfo(byte[] encrypted, String algName,
            AlgorithmParameters algParams) throws NoSuchAlgorithmException {
        tag = Tag.SEQUENCE;
        algorithmIdentifier = new ASNAlgorithmIdentifier(algName, algParams);
        encryptedKey = new ASNOctetString(encrypted);
    }

    /**
     * Constructor. Decodes the input stream <code>is</code>
     * 
     * @param is
     *            the input stream to consume
     * @throws IOException
     *             if cannot parse <code>is</code>
     */
    public ASNEncryptedPrivateKeyInfo(InputStream is) throws IOException {
        super(is);
        if (tag != Tag.SEQUENCE) {
            throw new IOException("Unexpected TAG");
        }

        // encryptionAlgorithm... (ALGORITHM IDENTIFIER)
        algorithmIdentifier = new ASNAlgorithmIdentifier(is);

        // encryptedData... (OCTECT STRING)
        encryptedKey = new ASNOctetString(is);

        // EncryptedPrivateKeyInfo... (SEQUENCE)

        if (algorithmIdentifier.length + encryptedKey.length != contentLength) {
            throw new IOException("Invalid size");
        }

        // final verification - close stream
        if (is.available() > 0) {
            throw new IOException("Unrecognized encoded format");
        }
        is.close();
    }

    @Override
    public final byte[] getEncoded() throws IOException {
        return makeASNEncoded(concat(algorithmIdentifier.getEncoded(),
                encryptedKey.getEncoded()));
    }

    /**
     * @return algorithm's name (for example, "RSA")
     */
    public final String getAlgName() {
        return algorithmIdentifier.getAlgName();
    }

    /**
     * @return the encrypted data. 
     */
    public final byte[] getEncryptedData() {
        return encryptedKey.getContent();
    }

    public final AlgorithmParameters getAlgorithmParameters() {
        return algorithmIdentifier.getAlgorithmParameters();
    }
}
