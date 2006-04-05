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
* @author Boris V. Kuznetsov
* @version $Revision$
*/

package java.security;

import java.io.IOException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;

import org.apache.harmony.security.fortress.Engine;


/**
 * @com.intel.drl.spec_ref
 * 
 */

public class AlgorithmParameters {
    /**
     * The service name.
     */
    private static final String SEVICE = "AlgorithmParameters";

    /**
     * Used to access common engine functionality
     */
    private static Engine engine = new Engine(SEVICE);

    /**
     * The provider
     */
    private Provider provider;

    /**
     * The SPI implementation.
     */
    private AlgorithmParametersSpi spiImpl;

    /**
     * The algorithm.
     */
    private String algorithm;

    /**
     * The initialisation state
     */
    private boolean initialized; // = false;

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    protected AlgorithmParameters(AlgorithmParametersSpi keyFacSpi,
            Provider provider, String algorithm) {
        this.provider = provider;
        this.algorithm = algorithm;
        this.spiImpl = keyFacSpi;
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static AlgorithmParameters getInstance(String algorithm)
            throws NoSuchAlgorithmException {
        if (algorithm == null) {
            throw new NullPointerException("Algorithm is null");
        }
        synchronized (engine) {       
            engine.getInstance(algorithm, null);
            return new AlgorithmParameters((AlgorithmParametersSpi) engine.spi,
                    engine.provider, algorithm);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static AlgorithmParameters getInstance(String algorithm,
            String provider) throws NoSuchAlgorithmException,
            NoSuchProviderException {
        if ((provider == null) || (provider.length() == 0)) {
            throw new IllegalArgumentException(
                    "Provider is null or empty string");
        }
        Provider p = Security.getProvider(provider);
        if (p == null) {
            throw new NoSuchProviderException("Provider " + provider
                    + " is not available");
        }
        return getInstance(algorithm, p);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static AlgorithmParameters getInstance(String algorithm,
            Provider provider) throws NoSuchAlgorithmException {
        if (provider == null) {
            throw new IllegalArgumentException("Provider is null");
        }
        if (algorithm == null) {
            throw new NullPointerException("Algorithm is null");
        }
        synchronized (engine) {
            engine.getInstance(algorithm, provider, null);
            return new AlgorithmParameters((AlgorithmParametersSpi) engine.spi,
                    provider, algorithm);
        }
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
    public final String getAlgorithm() {
        return algorithm;
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final void init(AlgorithmParameterSpec paramSpec)
            throws InvalidParameterSpecException {
        if (initialized) {
            throw new InvalidParameterSpecException(
                    "Parameter has already been initialized");
        }
        spiImpl.engineInit(paramSpec);
        initialized = true;
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final void init(byte[] params) throws IOException {
        if (initialized) {
            throw new IOException("Parameter has already been initialized");
        }
        spiImpl.engineInit(params);
        initialized = true;
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final void init(byte[] params, String format) throws IOException {
        if (initialized) {
            throw new IOException("Parameter has already been initialized");
        }
        spiImpl.engineInit(params, format);
        initialized = true;
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final AlgorithmParameterSpec getParameterSpec(Class paramSpec)
            throws InvalidParameterSpecException {
        if (!initialized) {
            throw new InvalidParameterSpecException(
                    "Parameter has not been initialized");
        }
        return spiImpl.engineGetParameterSpec(paramSpec);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final byte[] getEncoded() throws IOException {
        if (!initialized) {
            throw new IOException("Parameter has not been initialized");
        }
        return spiImpl.engineGetEncoded();
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final byte[] getEncoded(String format) throws IOException {
        if (!initialized) {
            throw new IOException("Parameter has not been initialized");
        }
        return spiImpl.engineGetEncoded(format);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final String toString() {
        if (!initialized) {
            return null;
        }
        return spiImpl.engineToString();
    }
}