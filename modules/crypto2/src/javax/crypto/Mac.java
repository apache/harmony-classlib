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
import java.security.NoSuchAlgorithmException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Security;
import java.nio.ByteBuffer;
import javax.crypto.MacSpi;
import javax.crypto.ShortBufferException;

import java.security.Provider.Service;
import java.security.spec.AlgorithmParameterSpec;
import java.security.NoSuchProviderException;
import javax.crypto.Mac;
import java.security.Provider;


/**
 * 
 * 
 * @author Diego Raúl Mercado
 * @version 1.2
 * @ar.org.fitc.spec_ref
 */
public class Mac implements Cloneable {
	/**
     * The service (engine object) that has been set through the requested 
     * algorithm and provider
     */
    private MacSpi macSpi;

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

    /** If it's true indicates that this object has been initialized */
    private boolean initialized = false;

    /**
     * It's used as a factory method
     * 
     * @param algorithm
     *            the algorithm
     * @param provider
     *            the provider
     * @return A new Mac object
     * @throws NoSuchAlgorithmException
     *             If the specified algorithm is not available in the default
     *             package or any of the others providers that were searched
     */
    private static final Mac newInstance(String algorithm, Provider provider)
            throws NoSuchAlgorithmException {
        Service service = Util.getService(Types.MAC, algorithm, provider);

        if (service == null) {
            throw new NoSuchAlgorithmException("No such algorithm: "
                    + algorithm);
        }
        return new Mac((MacSpi) service.newInstance(null), 
                service.getProvider(), algorithm);
    }

    /** @ar.org.fitc.spec_ref */
    public static final Mac getInstance(String algorithm)
            throws NoSuchAlgorithmException {
        return newInstance(algorithm, null);
    }

    /** @ar.org.fitc.spec_ref */
    public static final Mac getInstance(String algorithm, Provider provider)
            throws NoSuchAlgorithmException {
        if (provider == null) {
            throw new IllegalArgumentException("Missing provider");
        }
        return newInstance(algorithm, provider);
    }

    /** @ar.org.fitc.spec_ref */
    public static final Mac getInstance(String algorithm, String provider)
            throws NoSuchAlgorithmException, NoSuchProviderException {
        if (provider == null) {
            throw new IllegalArgumentException("Missing provider");
        }
        if (provider.length() == 0) {
            throw new IllegalArgumentException("Provider is empty");
        }
        Provider prov = Security.getProvider(provider);

        if (prov == null) {
            throw new NoSuchProviderException("Missing provider");
        }

        return newInstance(algorithm, prov);
    }

    /** @ar.org.fitc.spec_ref */
    protected Mac(MacSpi macSpi, Provider provider, String algorithm) {
    	this.macSpi = macSpi;
        this.provider = provider;
        this.algorithm = algorithm;
    }

    /** @ar.org.fitc.spec_ref */
    public final Object clone() throws CloneNotSupportedException {
        return new Mac((MacSpi) macSpi.clone(), provider, algorithm);
    }

    /** @ar.org.fitc.spec_ref */
    public final byte[] doFinal() throws IllegalStateException {
        if (!initialized) {
            throw new IllegalStateException("Mac not initialized");
        }
        return macSpi.engineDoFinal();
    }

    /**
     * @ar.org.fitc.spec_ref
     * <code>NullPointerException</code> is thrown if <code>input</code> is null
     */
    public final byte[] doFinal(byte[] input) throws IllegalStateException {
        update(input);
        return macSpi.engineDoFinal();
    }

    /**
     * @ar.org.fitc.spec_ref
     * <code>NullPointerException</code> is thrown if <code>output</code> is 
     * null
     */
    public final void doFinal(byte[] output, int outOffset)
            throws ShortBufferException, IllegalStateException {
        if (!initialized) {
            throw new IllegalStateException("Mac not initialized");
        }
        byte[] mac = macSpi.engineDoFinal();
        if (output.length - outOffset < mac.length) {
            throw new ShortBufferException("Cannot store MAC in output buffer");
        }
        System.arraycopy(mac, 0, output, outOffset, mac.length);
            
    }

    /** @ar.org.fitc.spec_ref */
    public final String getAlgorithm() {
        return algorithm;
    }

    /** @ar.org.fitc.spec_ref */
    public final int getMacLength() {
        return macSpi.engineGetMacLength();
    }

    /** @ar.org.fitc.spec_ref */
    public final Provider getProvider() {
        return provider;
    }

    /** @ar.org.fitc.spec_ref */
    public final void init(Key key) throws InvalidKeyException {
	    try {
            init(key, (AlgorithmParameterSpec) null);
        } catch (InvalidAlgorithmParameterException e) {
            throw new InvalidKeyException(e);
        }
    }

    /** @ar.org.fitc.spec_ref */
    public final void init(Key key, AlgorithmParameterSpec params)
            throws InvalidKeyException, InvalidAlgorithmParameterException {
	    macSpi.engineInit(key, params);
        initialized = true;
    }

    /** @ar.org.fitc.spec_ref */
    public final void reset() {
	    macSpi.engineReset();
    }

    /** @ar.org.fitc.spec_ref */
    public final void update(byte input) throws IllegalStateException {
		if (!initialized) {
            throw new IllegalStateException("Mac not initialized");
        }
        macSpi.engineUpdate(input);
    }

    /**
     * @ar.org.fitc.spec_ref 
     * <code>NullPointerException</code> is thrown if <code>input</code> is null
     */
    public final void update(byte[] input) throws IllegalStateException {
	    update(input, 0, input != null ? input.length : 0);
    }

    /**
     * @ar.org.fitc.spec_ref  
     * <code>IllegalArgumentException</code> is thrown if <code>input</code> is 
     * null, <code>off</code> or <code>len</code> are negative or 
     * <code>off</code> + <code>len</code> > <code>b.length</code>  
     */
    public final void update(byte[] input, int offset, int len)
            throws IllegalStateException {
        if (!initialized) {
            throw new IllegalStateException("Mac not initialized");
        }
        if (input != null) {
            macSpi.engineUpdate(input, offset, len);
        } else {
            macSpi.engineUpdate(new byte[]{}, 0, 0);    
        }
    }

    /** @ar.org.fitc.spec_ref */
    public final void update(ByteBuffer input) {
    	if (!initialized) {
            throw new IllegalStateException("Mac not initialized");
        }
        macSpi.engineUpdate(input);
    }
}
