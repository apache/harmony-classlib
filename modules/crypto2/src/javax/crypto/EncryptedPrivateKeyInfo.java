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

package javax.crypto;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

import ar.org.fitc.asn1.ASNEncryptedPrivateKeyInfo;

/**
 * 
 * 
 * @author Diego Raúl Mercado
 * @version 1.2
 * @ar.org.fitc.spec_ref
 */
public class EncryptedPrivateKeyInfo {
	/** encapsulates all the information needed by this object */
    private ASNEncryptedPrivateKeyInfo asnEPKI;
    
    /**
     * It's used as a "private" constructor
     * 
     * @param encryptedData
     *            the encrypted data
     * @param algName
     *            the algorithm's name
     * @param algParams
     *            the algorithm's parameter
     * @throws NoSuchAlgorithmException
     *             if the specified algorithm's name is not supported
     * @throws IllegalArgumentException
     *             if <code>encryptedData</code> is empty
     */
    private void encryptedPrivateKeyInfo(byte[] encryptedData, String algName,
            AlgorithmParameters algParams) throws NoSuchAlgorithmException {
        if (encryptedData.length == 0) {
            throw new IllegalArgumentException("encryptedData cannot be empty");
        }
        asnEPKI = new ASNEncryptedPrivateKeyInfo(encryptedData, algName,
                algParams);
    }
    
    /**
     * It's used by PKCS8EncodedKeySpec's methods
     * 
     * @param decryptKey
     *            A key for decryption
     * @param cipher
     *            The initialized cipher object which will be used for
     *            decrypting the encrypted data
     * @return The PKCS8EncodedKeySpec object
     * @throws InvalidKeyException
     *             If cannot extract encoded key
     * @throws NullPointerException
     *             If <code>decryptKey</code> is null
     */
    private PKCS8EncodedKeySpec getKeySpec(Key decryptKey, Cipher cipher)
            throws InvalidKeyException {
        if (decryptKey == null) {
            throw new NullPointerException("decryptKey cannot be null");
        }
        try {
            if (asnEPKI.getAlgorithmParameters() == null) {
                cipher.init(Cipher.DECRYPT_MODE, decryptKey);
            } else {
                cipher.init(Cipher.DECRYPT_MODE, decryptKey, 
                        asnEPKI.getAlgorithmParameters());
            }
            return new PKCS8EncodedKeySpec(cipher.doFinal(
                    asnEPKI.getEncryptedData()));
        } catch (InvalidAlgorithmParameterException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (BadPaddingException e) {
        }
        throw new InvalidKeyException("Cannot extract encoded key");
    }
    
    /** @ar.org.fitc.spec_ref */
    public EncryptedPrivateKeyInfo(AlgorithmParameters algParams,
            byte[] encryptedData) throws NoSuchAlgorithmException {
        if (algParams == null) {
            throw new NullPointerException("algParams cannot be null");
        }
        encryptedPrivateKeyInfo(encryptedData, algParams.getAlgorithm(),
                algParams);
    }

    /** @ar.org.fitc.spec_ref */
    public EncryptedPrivateKeyInfo(byte[] encoded) throws IOException {
	    asnEPKI = new ASNEncryptedPrivateKeyInfo(new ByteArrayInputStream(
                encoded));
    }

    /** @ar.org.fitc.spec_ref */
    public EncryptedPrivateKeyInfo(String algName, byte[] encryptedData)
            throws NoSuchAlgorithmException {
        encryptedPrivateKeyInfo(encryptedData, algName, null);
    }

    /** @ar.org.fitc.spec_ref */
    public String getAlgName() {
        return asnEPKI.getAlgName();
    }

    /** @ar.org.fitc.spec_ref */
    public AlgorithmParameters getAlgParameters() {
        return asnEPKI.getAlgorithmParameters();
    }

    /** @ar.org.fitc.spec_ref */
    public byte[] getEncoded() throws IOException {
        return asnEPKI.getEncoded();
    }

    /** @ar.org.fitc.spec_ref */
    public byte[] getEncryptedData() {
        return asnEPKI.getEncryptedData();
    }

    /** @ar.org.fitc.spec_ref */
    public PKCS8EncodedKeySpec getKeySpec(Cipher cipher)
            throws InvalidKeySpecException {
        if (cipher == null) {
            throw new NullPointerException("cipher cannot be null");
        }
        try {
            return new PKCS8EncodedKeySpec(cipher.doFinal(asnEPKI
                    .getEncryptedData()));
        } catch (IllegalStateException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (BadPaddingException e) {
        }
        throw new InvalidKeySpecException("Cannot extract encoded key. "
                + "The given cipher is inappropiate");
    }

    /** @ar.org.fitc.spec_ref */
    public PKCS8EncodedKeySpec getKeySpec(Key decryptKey)
            throws NoSuchAlgorithmException, InvalidKeyException {
		try {
            return getKeySpec(decryptKey, Cipher.getInstance
                    (asnEPKI.getAlgName()));
        } catch (NoSuchPaddingException e) {
            throw new InvalidKeyException("Cannot extract encoded key");
        }
    }

    /** @ar.org.fitc.spec_ref */
    public PKCS8EncodedKeySpec getKeySpec(Key decryptKey, Provider provider)
            throws NoSuchAlgorithmException, InvalidKeyException {
        if (provider == null) {
            throw new NullPointerException("provider cannot be null");
        }
        try {
            return getKeySpec(decryptKey, Cipher.getInstance(
                    asnEPKI.getAlgName(), provider));
        } catch (NoSuchPaddingException e) {
            //shouldn't get reaised because not padding can be specified 
            throw new AssertionError(e);
        }
    }

    /** @ar.org.fitc.spec_ref */
    public PKCS8EncodedKeySpec getKeySpec(Key decryptKey, String providerName)
            throws NoSuchProviderException, NoSuchAlgorithmException,
            InvalidKeyException {
		if (providerName == null) {
            throw new NullPointerException("providerName cannot be null");
        }
        try {
            return getKeySpec(decryptKey, Cipher.getInstance(
                    asnEPKI.getAlgName(), providerName));
        } catch (NoSuchPaddingException e) {
            throw new InvalidKeyException("Cannot extract encoded key");
        }
    }
}
