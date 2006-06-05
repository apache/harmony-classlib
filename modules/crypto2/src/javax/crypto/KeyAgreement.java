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

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchProviderException;
import java.security.Security;

import javax.crypto.KeyAgreement;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidAlgorithmParameterException;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;

import java.security.Provider.Service;
import java.security.spec.AlgorithmParameterSpec;
import java.security.Provider;
import javax.crypto.KeyAgreementSpi;
import java.security.SecureRandom;

/**
 * 
 * 
 * @author Diego Raúl Mercado
 * @version 1.2
 * @ar.org.fitc.spec_ref
 */
public class KeyAgreement {
	/**
     * The requested provider that has been set in any of the
     * <code>getInstance's</code> methods
     */
    private Provider provider;

    /**
     * The requested algorithm that has been specified in any of the
     * <code>getInstance's</code> methods
     */
    private String algorithm;

    /**
     * The service (engine object) that has been set through the requested
     * algorithm and provider
     */
    private KeyAgreementSpi keyAgreementSpi;

    /**
     * It's used as a factory method
     * 
     * @param algorithm
     *            the algorithm
     * @param provider
     *            the provider
     * @return a new KeyAgreement object
     * @throws NoSuchAlgorithmException
     *             if the specified algorithm is not available in the default
     *             package or any of the others providers that were searched
     */
    private static final KeyAgreement newInstance(String algorithm,
            Provider provider) throws NoSuchAlgorithmException {
        Service service = Util.getService(Types.KEY_AGREEMENT, algorithm,
                provider);
        if (service == null) {
            throw new NoSuchAlgorithmException("No such algorithm: "
                    + algorithm);
        }
        return new KeyAgreement((KeyAgreementSpi) service.newInstance(null),
                service.getProvider(), algorithm);
    }

    /** @ar.org.fitc.spec_ref */
    public static final KeyAgreement getInstance(String algorithm)
            throws NoSuchAlgorithmException {
        return newInstance(algorithm, null);
    }

    /** @ar.org.fitc.spec_ref */
    public static final KeyAgreement getInstance(String algorithm,
            Provider provider) throws NoSuchAlgorithmException {
        if (provider == null) {
            throw new IllegalArgumentException("Missing provider");
        }
        return newInstance(algorithm, provider);
    }

    /** @ar.org.fitc.spec_ref */
    public static final KeyAgreement getInstance(String algorithm,
            String provider) throws NoSuchAlgorithmException,
            NoSuchProviderException {
        if (provider == null || provider.length() == 0) {
            throw new IllegalArgumentException();
        }

        Provider prov = Security.getProvider(provider);

        if (prov == null) {
            throw new NoSuchProviderException("Missing provider");
        }

        return newInstance(algorithm, prov);
    }

    /** @ar.org.fitc.spec_ref */
    protected KeyAgreement(KeyAgreementSpi keyAgreeSpi, Provider provider,
            String algorithm) {
        this.keyAgreementSpi = keyAgreeSpi;
        this.provider = provider;
        this.algorithm = algorithm;
    }

    /** @ar.org.fitc.spec_ref */
    public final Key doPhase(Key key, boolean lastPhase)
            throws InvalidKeyException, IllegalStateException {
        return keyAgreementSpi.engineDoPhase(key, lastPhase);
    }

    /** @ar.org.fitc.spec_ref */
    public final byte[] generateSecret() throws IllegalStateException {
        return keyAgreementSpi.engineGenerateSecret();
    }

    /** @ar.org.fitc.spec_ref */
    public final int generateSecret(byte[] sharedSecret, int offset)
            throws IllegalStateException, ShortBufferException {
        return keyAgreementSpi.engineGenerateSecret(sharedSecret, offset);
    }

    /** @ar.org.fitc.spec_ref */
    public final SecretKey generateSecret(String algorithm)
            throws IllegalStateException, NoSuchAlgorithmException,
            InvalidKeyException {
        return keyAgreementSpi.engineGenerateSecret(algorithm);
    }

    /** @ar.org.fitc.spec_ref */
    public final String getAlgorithm() {
        return algorithm;
    }

    /** @ar.org.fitc.spec_ref */
    public final Provider getProvider() {
        return provider;
    }

    /** @ar.org.fitc.spec_ref */
    public final void init(Key key) throws InvalidKeyException {
	    keyAgreementSpi.engineInit(key, new SecureRandom());
    }

    /** @ar.org.fitc.spec_ref */
    public final void init(Key key, AlgorithmParameterSpec params)
            throws InvalidKeyException, InvalidAlgorithmParameterException {
        keyAgreementSpi.engineInit(key, params, new SecureRandom());
    }

    /** @ar.org.fitc.spec_ref */
    public final void init(Key key, AlgorithmParameterSpec params,
            SecureRandom random) throws InvalidKeyException,
            InvalidAlgorithmParameterException {
        keyAgreementSpi.engineInit(key, params, random);
    }

    /** @ar.org.fitc.spec_ref */
    public final void init(Key key, SecureRandom random)
            throws InvalidKeyException {
        keyAgreementSpi.engineInit(key, random);
    }
}
