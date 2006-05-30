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

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;

import org.apache.harmony.security.fortress.Engine;


/**
 * @com.intel.drl.spec_ref
 * 
 */

public class KeyGenerator {

    // Store spi implementation service name
    private static final String SERVICE = "KeyGenerator";

    // Used to access common engine functionality
    private static Engine engine = new Engine(SERVICE);

    // Store SecureRandom
    private static SecureRandom rndm = new SecureRandom();

    // Store used provider
    private final Provider provider;

    // Store used spi implementation
    private final KeyGeneratorSpi spiImpl;

    // Store used algorithm name
    private final String algorithm;

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    protected KeyGenerator(KeyGeneratorSpi keyGenSpi, Provider provider,
            String algorithm) {
        this.provider = provider;
        this.algorithm = algorithm;
        this.spiImpl = keyGenSpi;
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
    public static final KeyGenerator getInstance(String algorithm)
            throws NoSuchAlgorithmException {
        if (algorithm == null) {
            throw new NullPointerException("Algorithm is null");
        }
        synchronized (engine) {
            engine.getInstance(algorithm, null);
            return new KeyGenerator((KeyGeneratorSpi) engine.spi, engine.provider,
                    algorithm);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static final KeyGenerator getInstance(String algorithm,
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
    public static final KeyGenerator getInstance(String algorithm,
            Provider provider) throws NoSuchAlgorithmException {
        if (provider == null) {
            throw new IllegalArgumentException("Provider is null");
        }
        if (algorithm == null) {
            throw new NullPointerException("Algorithm is null");
        }
        synchronized (engine) {
            engine.getInstance(algorithm, provider, null);
            return new KeyGenerator((KeyGeneratorSpi) engine.spi, provider,
                    algorithm);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final SecretKey generateKey() {
        return spiImpl.engineGenerateKey();
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final void init(AlgorithmParameterSpec params)
            throws InvalidAlgorithmParameterException {
        spiImpl.engineInit(params, rndm);//new SecureRandom());
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final void init(AlgorithmParameterSpec params, SecureRandom random)
            throws InvalidAlgorithmParameterException {
        spiImpl.engineInit(params, random);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final void init(int keysize) {
        spiImpl.engineInit(keysize, rndm);//new SecureRandom());
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final void init(int keysize, SecureRandom random) {
        spiImpl.engineInit(keysize, random);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final void init(SecureRandom random) {
        spiImpl.engineInit(random);
    }
}