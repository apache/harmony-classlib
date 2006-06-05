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
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import javax.crypto.SecretKey;
import java.security.NoSuchProviderException;
import java.security.Provider.Service;
import java.security.spec.KeySpec;
import javax.crypto.SecretKeyFactory;
import java.security.Provider;
import javax.crypto.SecretKeyFactorySpi;
import java.security.spec.InvalidKeySpecException;

/**
 * 
 * 
 * @version 1.2
 * @ar.org.fitc.spec_ref
 */
public class SecretKeyFactory {
	/**
     * The service (engine object) that has been set through the requested
     * algorithm and provider
     */
    private SecretKeyFactorySpi secretKeyFactorySpi;

    /**
     * The requested algorithm that has been specified in any of the
     * <code>getInstance's</code> methods
     */
    private String algorithm;

    /**
     * The requested provider that has been set in any of the
     * <code>getInstance's</code> methods
     */
    private Provider provider;

    /**
     * It's used as a factory method
     * 
     * @param algorithm
     *            the algorithm
     * @param provider
     *            the provider
     * @return a new SecretKeyFactory object
     * @throws NoSuchAlgorithmException
     *             if the specified algorithm is not available in the default
     *             package or any of the others providers that were searched
     */
    private static final SecretKeyFactory newInstance(String algorithm,
            Provider provider) throws NoSuchAlgorithmException {
        Service service = Util.getService(Types.SECRET_KEY_FACTORY, algorithm,
                provider);
        if (service == null) {
            throw new NoSuchAlgorithmException("No such algorithm: "
                    + algorithm);
        }
        return new SecretKeyFactory((SecretKeyFactorySpi) service
                .newInstance(null), service.getProvider(), algorithm);
    }

    /** @ar.org.fitc.spec_ref */
    public static final SecretKeyFactory getInstance(String algorithm)
            throws NoSuchAlgorithmException {
        return newInstance(algorithm, null);
    }

    /** @ar.org.fitc.spec_ref */
    public static final SecretKeyFactory getInstance(String algorithm,
            Provider provider) throws NoSuchAlgorithmException {
        if (provider == null) {
            throw new IllegalArgumentException("Missing provider");
        }
        return newInstance(algorithm, provider);
    }

    /** @ar.org.fitc.spec_ref */
    public static final SecretKeyFactory getInstance(String algorithm,
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
    protected SecretKeyFactory(SecretKeyFactorySpi keyFacSpi,
            Provider provider, String algorithm) {
        this.secretKeyFactorySpi = keyFacSpi;
        this.provider = provider;
        this.algorithm = algorithm;
    }

    /** @ar.org.fitc.spec_ref */
    public final SecretKey generateSecret(KeySpec keySpec)
            throws InvalidKeySpecException {
        return secretKeyFactorySpi.engineGenerateSecret(keySpec);
    }

    /** @ar.org.fitc.spec_ref */
    public final String getAlgorithm() {
        return algorithm;
    }

    /** @ar.org.fitc.spec_ref */
    public final KeySpec getKeySpec(SecretKey key, Class keySpec)
            throws InvalidKeySpecException {
        return secretKeyFactorySpi.engineGetKeySpec(key, keySpec);
    }

    /** @ar.org.fitc.spec_ref */
    public final Provider getProvider() {
        return provider;
    }

    /** @ar.org.fitc.spec_ref */
    public final SecretKey translateKey(SecretKey key)
            throws InvalidKeyException {
        return secretKeyFactorySpi.engineTranslateKey(key);
    }
}
