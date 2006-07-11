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

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;

import org.apache.harmony.security.fortress.Engine;


/**
 * @com.intel.drl.spec_ref
 * 
 */
public class ExemptionMechanism {

    // Store spi implementation service name
    private static final String SERVICE = "ExemptionMechanism";

    // Used to access common engine functionality
    private static Engine engine = new Engine(SERVICE);

    // Warning for reporting about not initializes ExemptionMechanism
    private static final String NOTINITEMECH = "ExemptionMechanism is not initialized";

    // Store used provider
    private final Provider provider;

    // Store used spi implementation
    private final ExemptionMechanismSpi spiImpl;

    // Store mechanism name
    private final String mechanism;

    // Store state (initialized or not)
    private boolean isInit;

    // Store initKey value
    private Key initKey;

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    protected ExemptionMechanism(ExemptionMechanismSpi exmechSpi,
            Provider provider, String mechanism) {
        this.mechanism = mechanism;
        this.spiImpl = exmechSpi;
        this.provider = provider;
        isInit = false;
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final String getName() {
        return mechanism;
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static final ExemptionMechanism getInstance(String algorithm)
            throws NoSuchAlgorithmException {
        if (algorithm == null) {
            throw new NullPointerException("Algorithm is null");
        }
        synchronized (engine) {
            engine.getInstance(algorithm, null);
            return new ExemptionMechanism((ExemptionMechanismSpi) engine.spi,
                    engine.provider, algorithm);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static final ExemptionMechanism getInstance(String algorithm,
            String provider) throws NoSuchAlgorithmException,
            NoSuchProviderException {
        if (provider == null) {
            throw new IllegalArgumentException("Provider is null");
        }
        Provider impProvider = Security.getProvider(provider);
        if (impProvider == null) {
            throw new NoSuchProviderException(provider);
        }
        if (algorithm == null) {
            throw new NullPointerException("Algorithm is null");
        }
        return getInstance(algorithm, impProvider);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static final ExemptionMechanism getInstance(String algorithm,
            Provider provider) throws NoSuchAlgorithmException {
        if (algorithm == null) {
            throw new NullPointerException("Algorithm is null");
        }
        if (provider == null) {
            throw new IllegalArgumentException("Provider is null");
        }
        synchronized (engine) {
            engine.getInstance(algorithm, provider, null);
            return new ExemptionMechanism((ExemptionMechanismSpi) engine.spi,
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
     * FIXME: check this method
     */
    public final boolean isCryptoAllowed(Key key)
            throws ExemptionMechanismException {
        Key initK = initKey;
        try {
            initKey = key;
            spiImpl.engineInit(key);
            spiImpl.engineGenExemptionBlob();
            if (initK != null) {
                spiImpl.engineInit(initK);
            }
            return true;
        } catch (InvalidKeyException e) {
            return false;
        } finally {
            initKey = initK;
        }
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final int getOutputSize(int inputLen) throws IllegalStateException {
        if (!isInit) {
            throw new IllegalStateException(NOTINITEMECH);
        }
        return spiImpl.engineGetOutputSize(inputLen);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final void init(Key key) throws InvalidKeyException,
            ExemptionMechanismException {
        spiImpl.engineInit(key);
        initKey = key;
        isInit = true;
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final void init(Key key, AlgorithmParameters param)
            throws InvalidKeyException, InvalidAlgorithmParameterException,
            ExemptionMechanismException {
        spiImpl.engineInit(key, param);
        initKey = key;
        isInit = true;
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final void init(Key key, AlgorithmParameterSpec param)
            throws InvalidKeyException, InvalidAlgorithmParameterException,
            ExemptionMechanismException {
        spiImpl.engineInit(key, param);
        initKey = key;
        isInit = true;
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final byte[] genExemptionBlob() throws IllegalStateException,
            ExemptionMechanismException {
        if (!isInit) {
            throw new IllegalStateException(NOTINITEMECH);
        }
        return spiImpl.engineGenExemptionBlob();
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     * FIXME: about ShortBufferException (output: null, byte[0],...
     */
    public final int genExemptionBlob(byte[] output)
            throws IllegalStateException, ShortBufferException,
            ExemptionMechanismException {
        if (!isInit) {
            throw new IllegalStateException(NOTINITEMECH);
        }
        byte[] result = spiImpl.engineGenExemptionBlob();
        if (output.length == 0) {
            throw new ShortBufferException(
                    "Output buffer is too small to hold the result");
        }
        int len = (result.length < output.length ? result.length
                : output.length);
        for (int i = 0; i < len; i++) {
            output[i] = result[i];
        }
        return len;
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     * 
     * FIXME: about ShortBufferException (output: null, byte[0],... and
     * outputOffset
     */
    public final int genExemptionBlob(byte[] output, int outputOffset)
            throws IllegalStateException, ShortBufferException,
            ExemptionMechanismException {
        if (!isInit) {
            throw new IllegalStateException(NOTINITEMECH);
        }
        byte[] result = spiImpl.engineGenExemptionBlob();
        if ((output.length == 0) || (outputOffset < 0)
                || (outputOffset >= output.length)) {
            throw new ShortBufferException(
                    "Output buffer is too small to hold the result");
        }
        int len = (result.length < (output.length - outputOffset) ? result.length
                : (output.length - outputOffset));
        for (int i = 0; i < len; i++) {
            output[i] = result[i];
        }
        return len;
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     * FIXME: check this method
     */
    protected void finalize() {
        try {
            initKey = null;
            super.finalize();
        } catch (Throwable e) {
            new RuntimeException(e);
        }
    }
}