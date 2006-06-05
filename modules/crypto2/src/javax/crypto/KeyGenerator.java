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

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Provider.Service;
import java.security.spec.AlgorithmParameterSpec;

/**
 * 
 * 
 * @author Diego Raúl Mercado
 * @version 1.2
 * @ar.org.fitc.spec_ref
 */
public class KeyGenerator {
	/**
     * The requested provider that has been set in any of the
     * <code>getInstance's</code> methods
     */
    private Provider provider;

    /**
     * The service (engine object) that has been set through the requested
     * algorithm and provider
     */
    private KeyGeneratorSpi keyGeneratorSpi;

    /**
     * The requested algorithm that has been specified in any of the
     * <code>getInstance's</code> methods
     */
    private String algorithm;

    /**
     * It's used as a factory method
     * 
     * @param algorithm
     *            the algorithm
     * @param provider
     *            the provider
     * @return a new KeyGenerator object
     * @throws NoSuchAlgorithmException
     *             if the specified algorithm is not available in the default
     *             package or any of the others providers that were searched
     */
    private static final KeyGenerator newInstance(String algorithm,
            Provider provider) throws NoSuchAlgorithmException {
        Service service = Util.getService(Types.KEY_GENERATOR, algorithm,
                provider);
        if (service == null) {
            throw new NoSuchAlgorithmException("No such algorithm: "
                    + algorithm);
        }
        return new KeyGenerator((KeyGeneratorSpi) service.newInstance(null),
                service.getProvider(), algorithm);
    }

    /** @ar.org.fitc.spec_ref */
    public static final KeyGenerator getInstance(String algorithm)
            throws NoSuchAlgorithmException {
        return newInstance(algorithm, null);
    }

    /** @ar.org.fitc.spec_ref */
    public static final KeyGenerator getInstance(String algorithm,
            Provider provider) throws NoSuchAlgorithmException {
        if (provider == null) {
            throw new IllegalArgumentException("Missing provider");
        }
        return newInstance(algorithm, provider);
    }

    /** @ar.org.fitc.spec_ref */
    public static final KeyGenerator getInstance(String algorithm,
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
    protected KeyGenerator(KeyGeneratorSpi keyGenSpi, Provider provider,
            String algorithm) {
        this.provider = provider;
        this.keyGeneratorSpi = keyGenSpi;
        this.algorithm = algorithm;
    }

    /** @ar.org.fitc.spec_ref */
    public final SecretKey generateKey() {
        return keyGeneratorSpi.engineGenerateKey();
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
    public final void init(AlgorithmParameterSpec params)
            throws InvalidAlgorithmParameterException {
        keyGeneratorSpi.engineInit(params, new SecureRandom());
    }

    /** @ar.org.fitc.spec_ref */
    public final void init(AlgorithmParameterSpec params, SecureRandom random)
            throws InvalidAlgorithmParameterException {
        keyGeneratorSpi.engineInit(params, random);
    }

    /** @ar.org.fitc.spec_ref */
    public final void init(int keysize) {
	    keyGeneratorSpi.engineInit(keysize, new SecureRandom());
    }

    /** @ar.org.fitc.spec_ref */
    public final void init(int keysize, SecureRandom random) {
	    keyGeneratorSpi.engineInit(keysize, random);
    }

    /** @ar.org.fitc.spec_ref */
    public final void init(SecureRandom random) {
	    keyGeneratorSpi.engineInit(random);
    }
}
