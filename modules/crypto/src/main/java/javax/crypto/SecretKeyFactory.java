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
* @author Vera Y. Petrashkova
* @version $Revision$
*/

package javax.crypto;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import org.apache.harmony.security.fortress.Engine;


/**
 * @com.intel.drl.spec_ref
 * 
 */
public class SecretKeyFactory {

    // Store spi implementation service name
    private static final String SERVICE = "SecretKeyFactory";

    // Used to accesess common engine functionality
    private static Engine engine = new Engine(SERVICE);

    // Store used provider
    private final Provider provider;

    // Store used spi implementation
    private final SecretKeyFactorySpi spiImpl;

    // Store used algorithm name
    private final String algorithm;

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    protected SecretKeyFactory(SecretKeyFactorySpi keyFacSpi,
            Provider provider, String algorithm) {
        this.provider = provider;
        this.algorithm = algorithm;
        this.spiImpl = keyFacSpi;
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final String getAlgorithm() {
        return algorithm;
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final Provider getProvider() {
        return provider;
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static final SecretKeyFactory getInstance(String algorithm)
            throws NoSuchAlgorithmException {
        if (algorithm == null) {
            throw new NullPointerException("Algorithm is null");
        }
        synchronized (engine) {
            engine.getInstance(algorithm, null);
            return new SecretKeyFactory((SecretKeyFactorySpi) engine.spi,
                    engine.provider, algorithm);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static final SecretKeyFactory getInstance(String algorithm,
            String provider) throws NoSuchAlgorithmException,
            NoSuchProviderException {
        if ((provider == null) || (provider.length() == 0)) {
            throw new IllegalArgumentException("Provider is null or empty");
        }
        Provider impProvider = Security.getProvider(provider);
        if (impProvider == null) {
            throw new NoSuchProviderException(provider);
        }
        return getInstance(algorithm, impProvider);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static final SecretKeyFactory getInstance(String algorithm,
            Provider provider) throws NoSuchAlgorithmException {
        if (provider == null) {
            throw new IllegalArgumentException("Provider is null");
        }
        if (algorithm == null) {
            throw new NullPointerException("Algorithm is null");
        }
        synchronized (engine) {
            engine.getInstance(algorithm, provider, null);
            return new SecretKeyFactory((SecretKeyFactorySpi) engine.spi, provider,
                    algorithm);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final SecretKey generateSecret(KeySpec keySpec)
            throws InvalidKeySpecException {
        return spiImpl.engineGenerateSecret(keySpec);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final KeySpec getKeySpec(SecretKey key, Class keySpec)
            throws InvalidKeySpecException {
        return spiImpl.engineGetKeySpec(key, keySpec);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final SecretKey translateKey(SecretKey key)
            throws InvalidKeyException {
        return spiImpl.engineTranslateKey(key);

    }
}