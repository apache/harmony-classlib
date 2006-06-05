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

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;
import java.security.Provider.Service;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;

/**
 * 
 * 
 * @author Diego Raúl Mercado
 * @version 1.2
 * @ar.org.fitc.spec_ref
 */
public class ExemptionMechanism {

	/**
     * The requested provider that has been set in any of the
     * <code>getInstance's</code> methods
     */
    private Provider provider;

    /**
     * The service (engine object) that has been set through the requested
     * algorithm and provider
     */
    private ExemptionMechanismSpi exemptionMechanismSpi;

    /**
     * The requested algorithm that has been specified in any of the
     * <code>getInstance's</code> methods
     */
    private String algorithm;

    /** Indicates if this exemption mechanism has been initialized */
    private boolean initialized;

    /** 
     * Indicates if this exemption mechanism has been generated successfully 
     * the blob
     */
    private boolean success;
    
    /** */
    private Key key;
    
    /**
     * It's used as a factory method
     * 
     * @param algorithm
     *            the algorithm
     * @param provider
     *            the provider
     * @return a new ExemptionMechanism object
     * @throws NoSuchAlgorithmException
     *             if the specified algorithm is not available in the default
     *             package or any of the others providers that were searched
     */
    private static final ExemptionMechanism newInstance(String algorithm,
            Provider provider) throws NoSuchAlgorithmException {
        Service service = Util.getService(Types.EXEMPTION_MECHANISM, algorithm,
                provider);
        if (service == null) {
            throw new NoSuchAlgorithmException("No such algorithm: "
                    + algorithm);
        }
        return new ExemptionMechanism((ExemptionMechanismSpi) service
                .newInstance(null), service.getProvider(), algorithm);
    }

    /** @ar.org.fitc.spec_ref */
    public static final ExemptionMechanism getInstance(String algorithm)
            throws NoSuchAlgorithmException {
        return newInstance(algorithm, null);
    }

    /** @ar.org.fitc.spec_ref */
    public static final ExemptionMechanism getInstance(String algorithm,
            Provider provider) throws NoSuchAlgorithmException {
        if (provider == null) {
            throw new IllegalArgumentException("Missing provider");
        }
        return newInstance(algorithm, provider);
    }

    /** @ar.org.fitc.spec_ref */
    public static final ExemptionMechanism getInstance(String algorithm,
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
    protected ExemptionMechanism(ExemptionMechanismSpi exmechSpi,
            Provider provider, String mechanism) {
		this.algorithm = mechanism;
        this.provider = provider;
        this.exemptionMechanismSpi = exmechSpi;
    }

    /** @ar.org.fitc.spec_ref */
    protected void finalize() {
        /*
         * FIXME this implementation is correct ?
         */
        key = null; 
    }

    /** @ar.org.fitc.spec_ref */
    public final byte[] genExemptionBlob() throws IllegalStateException,
            ExemptionMechanismException {
        if (!initialized) {
            throw new IllegalStateException("This EM must be initialized");
        }
        byte[] result = exemptionMechanismSpi.engineGenExemptionBlob();
        success = true;
        return result.clone();
    }

    /** @ar.org.fitc.spec_ref */
    public final int genExemptionBlob(byte[] output)
            throws IllegalStateException, ShortBufferException,
            ExemptionMechanismException {
        if (!initialized) {
            throw new IllegalStateException("This EM must be initialized");
        }
        int result = exemptionMechanismSpi.engineGenExemptionBlob(output, 0);
        success = true;
        return result;
    }

    /** @ar.org.fitc.spec_ref */
    public final int genExemptionBlob(byte[] output, int outputOffset)
            throws IllegalStateException, ShortBufferException,
            ExemptionMechanismException {
        if (!initialized) {
            throw new IllegalStateException("This EM must be initialized");
        }
        int result = exemptionMechanismSpi.engineGenExemptionBlob(output,
                outputOffset);
        success = true;
        return result;
    }

    /** @ar.org.fitc.spec_ref */
    public final String getName() {
        return algorithm;
    }

    /** @ar.org.fitc.spec_ref */
    public final int getOutputSize(int inputLen) throws IllegalStateException {
        return exemptionMechanismSpi.engineGetOutputSize(inputLen);
    }

    /** @ar.org.fitc.spec_ref */
    public final Provider getProvider() {
        return provider;
    }

    /** @ar.org.fitc.spec_ref */
    public final void init(Key key) throws InvalidKeyException,
            ExemptionMechanismException {
        exemptionMechanismSpi.engineInit(key);
        this.key = key;
        initialized = true;
    }

    /** @ar.org.fitc.spec_ref */
    public final void init(Key key, AlgorithmParameters params)
            throws InvalidKeyException, InvalidAlgorithmParameterException,
            ExemptionMechanismException {
        exemptionMechanismSpi.engineInit(key, params);
        this.key = key;
        initialized = true;
    }

    /** @ar.org.fitc.spec_ref */
    public final void init(Key key, AlgorithmParameterSpec params)
            throws InvalidKeyException, InvalidAlgorithmParameterException,
            ExemptionMechanismException {
        exemptionMechanismSpi.engineInit(key, params);
        this.key = key;
        initialized = true;
    }

    /** @ar.org.fitc.spec_ref */
    public final boolean isCryptoAllowed(Key key)
            throws ExemptionMechanismException {
        /*
         * FIXME How can we throw ExemptionMechanismException ??
         */
        if ((key.equals(this.key) 
                || Arrays.equals(key.getEncoded(), this.key.getEncoded())) 
                && success) {
            return true;
        }
        return false;
    }
}
