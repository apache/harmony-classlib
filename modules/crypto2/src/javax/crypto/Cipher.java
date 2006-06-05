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

import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Provider.Service;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * @author Diego Raúl Mercado
 * @version 1.2
 * @ar.org.fitc.spec_ref
 */
public class Cipher {
    /** @ar.org.fitc.spec_ref */
    public static final int DECRYPT_MODE = 2;

    /** @ar.org.fitc.spec_ref */
    public static final int ENCRYPT_MODE = 1;

    /** @ar.org.fitc.spec_ref */
    public static final int PRIVATE_KEY = 2;

    /** @ar.org.fitc.spec_ref */
    public static final int PUBLIC_KEY = 1;

    /** @ar.org.fitc.spec_ref */
    public static final int SECRET_KEY = 3;

    /** @ar.org.fitc.spec_ref */
    public static final int UNWRAP_MODE = 4;

    /** @ar.org.fitc.spec_ref */
    public static final int WRAP_MODE = 3;

    /**
     * Second element of the transformation array returned by 
     * <code>parseTransformation()</code> method that represents the algorithm
     * name
     */
    private static final int ALG = 0;

    /**
     * Second element of the transformation array returned by 
     * <code>parseTransformation()</code> method that represents the  
     * algorithm's mode
     */
    private static final int MOD = 1;

    /**
     * Third element of the transformation array returned by 
     * <code>parseTransformation()</code> method that represents the padding 
     * scheme
     */
    private static final int PAD = 2;

    /**
     * Position of the boolean array returned by <code>getKeyUsage()</code>
     * method that represents a certificate's key usage for digital signature
     */
    private static final int DIGITAL_SIGNATURE = 0;

    /**
     * Position of the boolean array returned by <code>getKeyUsage()</code>
     * method that represents a certificate's key usage for key encipherment
     */
    private static final int KEY_ENCIPHERMENT = 2;

    /** KeyUsage's OID */
    private static final String KEY_USAGE = "2.5.29.15";

    /** If this is a null cipher this value is set to <code>true</code> */
    private boolean nullCipher;

    /**
     * The requested provider that has been set in any of the
     * <code>getInstance's</code> methods
     */
    private Provider provider;

    /**
     * The service (engine object) that has been set through the requested 
     * transformation and provider
     */
    private CipherSpi cipherSpi;

    /**
     * The requested transformation that has been specified in any of the
     * <code>getInstance's</code> methods
     */
    private String transformation;

    /** Indicates if this cipher has been successfully initialized */
    private boolean initialized;
    
    /**
     * The mode that has been specified in any of the <code>getInstance's</code>
     * methods
     */
    private int opmode; 

    /**
     * @param transformation
     *            A string that represents a transformation:
     *            (algorithm/mode/padding or algorithm)
     * @return An array with: [0]algorithm [1]mode [2]padding
     * @throws NoSuchAlgorithmException
     *             If <code>transformation</code> is invalid, empty or null
     */
    private static final String[] parseTranformation(String transformation)
            throws NoSuchAlgorithmException {
        if (transformation == null || transformation.equals("")) {
            throw new NoSuchAlgorithmException(
                    "Transformation cannot be empty or null");
        }
        String[] str = transformation.split("/");
        if (str.length == 3 || str.length == 1) {
            return str;
        }
        throw new NoSuchAlgorithmException("Invalid transformation: "
                + transformation);
    }

    /**
     * Gets a service from the provider and, additionally, checks if it's
     * trusted   
     * 
     * @param str
     *            Represents a valid transformation (obtained through the
     *            <code>parseTransformation</code> method)
     * @param provider
     *            A provider that may provide the requested transformation
     * @return The requested service
     * @throws NoSuchAlgorithmException
     *             If <code>transformation</code> is not available from the
     *             specific provider
     * @throws NoSuchPaddingException
     *             If <code>transformation</code> contains a padding scheme
     *             that is not available
     */
    private static final CipherSpi getCipherSpi(String[] str, Provider provider)
            throws NoSuchAlgorithmException, NoSuchPaddingException {
        CipherSpi cspi = null;
        Service s;
        boolean instantiated = false;
//      Transformation == algorithm
        switch (str.length) {
        case 1:
            s = provider.getService(Types.CIPHER.toString(), str[ALG]);
            if (s != null) {
                cspi = (CipherSpi) s.newInstance(null);
                instantiated = true;
            }
            break;
        default:
//          Transformation == algorithm/mode/padding
            s = provider.getService(Types.CIPHER.toString(), str[ALG] + "/"
                    + str[MOD] + "/" + str[PAD]);
            if (s != null) {
                cspi = (CipherSpi) s.newInstance(null);
                instantiated = true;
                break;
            }
            s = provider.getService(Types.CIPHER.toString(), str[ALG] + "/"
                    + str[MOD]);
            if (s != null) {
                try {
                    cspi = (CipherSpi) s.newInstance(null);
                    cspi.engineSetPadding(str[PAD]);
                    instantiated = true;
                    break;
                } catch (NoSuchAlgorithmException e) {
                } catch (NoSuchPaddingException e) {
                }
            }
            s = provider.getService(Types.CIPHER.toString(), str[ALG] + "/"
                    + str[PAD]);
            if (s != null) {
                try {
                    cspi = (CipherSpi) s.newInstance(null);
                    cspi.engineSetMode(str[MOD]);
                    instantiated = true;
                    break;
                } catch (NoSuchAlgorithmException e) {
                }
            }
            s = provider.getService(Types.CIPHER.toString(), str[ALG]);
            if (s != null) {
                try {
                    cspi = (CipherSpi) s.newInstance(null);
                    cspi.engineSetMode(str[MOD]);
                    cspi.engineSetPadding(str[PAD]);
                    instantiated = true;
                } catch (NoSuchAlgorithmException e) {
                }
            } 
        }
        
        if (!instantiated) {
            throw new NoSuchAlgorithmException(
                    "The requested transformation is not provided");
        } else {
            Util.checkProvider(provider);
            return cspi;
        }
    }

    /**
     * This method checks a byte buffer's operation
     * 
     * @param input
     *            An input buffer
     * @param output
     *            An output buffer
     * @throws IllegalStateException
     *             If opmode is 0 and this is not a null cipher. Or
     *             <code>input</code> and <code>output</code> are the same
     *             object
     * @throws ReadOnlyBufferException
     *             If output is read-only
     */
    private final void checkByteBufferOp(ByteBuffer input, ByteBuffer output) {
        if (opmode == WRAP_MODE || opmode == UNWRAP_MODE) {
            throw new IllegalStateException("this cipher has not been " +
                    "initialized for encryption or decryption");
        }
        if (!initialized && !nullCipher) {
            throw new IllegalStateException(
                    "this cipher has not been initialized");
        }
        if (input == null || output == null) {
            throw new IllegalArgumentException("buffers cannot be null");
        }
        if (input == output) {
            throw new IllegalArgumentException(
                    "Input and output buffers cannot be the same object");
        }
        /*
         * ReadOnlyBufferException it's verified here and not delegated to
         * ByteBuffers because input's property changes before invoking output's
         * write (which may throw that exception)
         */
        if (output.isReadOnly()) {
            //cannot indicate exception's message because the string constructor 
            //is not supported
            throw new ReadOnlyBufferException(); 
        }
    }


    private final void checkArrayOp(byte[] input, int inputOffset, 
            int inputLen, int outputOffset) {
        if (opmode == WRAP_MODE || opmode == UNWRAP_MODE) {
            throw new IllegalStateException("this cipher has not been " +
                    "initialized for encryption or decryption");
        }
        if (!initialized && !nullCipher) {
            throw new IllegalStateException(
                    "cannot recognize the requested opmode");
        }
        if (input == null) {
            throw new IllegalArgumentException("Input buffer cannot be null");
        }
        if (inputOffset < 0 || inputLen < 0 || outputOffset < 0) {
            throw new IllegalArgumentException("Bad parameters");
        }
        if (input.length - inputOffset < inputLen) {
            throw new IllegalArgumentException("Invalid offset/size");
        }
    }
    
    /** @ar.org.fitc.spec_ref */
    public static final Cipher getInstance(String transformation)
            throws NoSuchAlgorithmException, NoSuchPaddingException {
        String str[] = parseTranformation(transformation);
        for (Provider provider : Security.getProviders()) {
            try {
                return new Cipher(getCipherSpi(str, provider), provider,
                        transformation);
            } catch (NoSuchAlgorithmException e1) {
            } catch (NoSuchPaddingException e2) {
            }
        }
        throw new NoSuchAlgorithmException(transformation + " not provided");
    }

    /** @ar.org.fitc.spec_ref */
    public static final Cipher getInstance(String transformation,
            String provider) throws NoSuchAlgorithmException,
            NoSuchProviderException, NoSuchPaddingException {
        Provider p = null;
        if (provider != null) {
            p = Security.getProvider(provider);
            if (p == null) {
                throw new NoSuchProviderException(
                        "Invalid or uninstalled provider");
            }
        }
        return getInstance(transformation, p);
    }

    /** @ar.org.fitc.spec_ref */
    public static final Cipher getInstance(String transformation,
            Provider provider) throws NoSuchAlgorithmException,
            NoSuchPaddingException {
        if (provider == null) {
            throw new IllegalArgumentException("Provider cannot be null");
        }
        String str[] = parseTranformation(transformation);
        return new Cipher(getCipherSpi(str, provider), provider, transformation);
    }

    /**
     * @ar.org.fitc.spec_ref if <code>transformation</code> is valid, this 
     * method always returns <code>Integer.MAX_VALUE</code>
     */
    public static final int getMaxAllowedKeyLength(String transformation)
            throws NoSuchAlgorithmException {
        String[] str = transformation.split("/");
        if (str.length == 3 || str.length == 1) {
            return Integer.MAX_VALUE;
        }
        throw new NoSuchAlgorithmException("The transformation "
                + transformation + " is invalid");
    }

    /**
     * @ar.org.fitc.spec_ref if <code>transformation</code> is valid, this 
     * method always returns null
     */
    public static final AlgorithmParameterSpec getMaxAllowedParameterSpec(
            String transformation) throws NoSuchAlgorithmException {
        String[] str = transformation.split("/");
        if (str.length == 3 || str.length == 1) {
            return null;
        }
        throw new NoSuchAlgorithmException("The transformation "
                + transformation + " is invalid");
    }

    protected Cipher(CipherSpi cipherSpi, Provider provider,
            String transformation) {
        this.cipherSpi = cipherSpi;
        if (provider == null && transformation == null) {
            nullCipher = true;
        } else {
            this.provider = provider;
            this.transformation = transformation;
        }
    }

    /**
     * @ar.org.fitc.spec_ref 
     * If it's an instance of NullCipher the <code>IllegalStateException</code> 
     * is not thrown
     */
    public final byte[] doFinal() throws IllegalBlockSizeException,
            BadPaddingException {
        return doFinal(new byte[] {}, 0, 0);
    }
    
    /**
     * @ar.org.fitc.spec_ref 
     * If it's an instance of NullCipher the <code>IllegalStateException</code> 
     * is not thrown
     * <br>
     * <br>
     * <code>IllegalArgumentException</code> is thrown if <code>input</code> is 
     * null
     */
    public final byte[] doFinal(byte[] input) throws IllegalBlockSizeException,
            BadPaddingException {
        // if input is null in the next call IllegalArgumentException should be
        // raised
        return doFinal(input, 0, input != null ? input.length : 0);
    }

    /**
     * @ar.org.fitc.spec_ref 
     * If it's an instance of NullCipher the <code>IllegalStateException</code> 
     * is not thrown
     * <br>
     * <br>
     * <code>IllegalArgumentException</code> is thrown if <code>output</code> is 
     * null
     */
    public final int doFinal(byte[] output, int outputOffset)
            throws IllegalBlockSizeException, ShortBufferException,
            BadPaddingException {
        if (output == null) {
            throw new IllegalArgumentException("output cannot be null");
        }
        return doFinal(new byte[] {}, 0, 0, output, outputOffset);
    }

    /**
     * @ar.org.fitc.spec_ref 
     * If it's an instance of NullCipher the <code>IllegalStateException</code> 
     * is not thrown
     * <br>
     * <br>
     * <code>IllegalArgumentException</code> is thrown if <code>input</code> is 
     * null
     */
    public final byte[] doFinal(byte[] input, int inputOffset, int inputLen)
            throws IllegalBlockSizeException, BadPaddingException {
        checkArrayOp(input, inputOffset, inputLen, 0);
        return cipherSpi.engineDoFinal(input, inputOffset, inputLen);
    }

    /**
     * @ar.org.fitc.spec_ref 
     * If it's an instance of NullCipher the <code>IllegalStateException</code> 
     * is not thrown
     * <br>
     * <br>
     * <code>IllegalArgumentException</code> is thrown if <code>input</code> is 
     * null
     */
    public final int doFinal(byte[] input, int inputOffset, int inputLen,
            byte[] output) throws ShortBufferException,
            IllegalBlockSizeException, BadPaddingException {
        return doFinal(input, inputOffset, inputLen, output, 0);
    }

    /**
     * @ar.org.fitc.spec_ref 
     * If it's an instance of NullCipher the <code>IllegalStateException</code> 
     * is not thrown
     * <br>
     * <br>
     * <code>IllegalArgumentException</code> is thrown if <code>input</code> is 
     * null
     */
    public final int doFinal(byte[] input, int inputOffset, int inputLen,
            byte[] output, int outputOffset) throws ShortBufferException,
            IllegalBlockSizeException, BadPaddingException {
        checkArrayOp(input, inputOffset, inputLen, outputOffset);
        return cipherSpi.engineDoFinal(input, inputOffset, inputLen, output,
                outputOffset);
    }

    /**
     * @ar.org.fitc.spec_ref 
     * If it's an instance of NullCipher the <code>IllegalStateException</code> 
     * is not thrown
     * <br>
     * <br>
     * <code>IllegalArgumentException</code> is thrown if <code>input</code> or 
     * <code>output</code> are null
     */
    public final int doFinal(ByteBuffer input, ByteBuffer output)
            throws ShortBufferException, IllegalBlockSizeException,
            BadPaddingException {
        checkByteBufferOp(input, output);
        return cipherSpi.engineDoFinal(input, output);
    }

    /**
     * @ar.org.fitc.spec_ref 
     * Returns the complete transformation specified at either of the 
     * getInstance's methods, not only the algorithm name as seems to be
     */
    public final String getAlgorithm() {
        return transformation;
    }

    /** @ar.org.fitc.spec_ref */
    public final int getBlockSize() {
        return cipherSpi.engineGetBlockSize();
    }

    /**
     * @ar.org.fitc.spec_ref this method always returns null because its ignore
     * any jurisdiction policy installed
     */
    public final ExemptionMechanism getExemptionMechanism() {
        return null;
    }

    /** @ar.org.fitc.spec_ref */
    public final byte[] getIV() {
        return cipherSpi.engineGetIV() != null ? 
                cipherSpi.engineGetIV().clone() : null;
    }

    /** @ar.org.fitc.spec_ref */
    public final int getOutputSize(int inputLen) {
        if (!initialized && !nullCipher) {
            throw new IllegalStateException();
        }
        return cipherSpi.engineGetOutputSize(inputLen);
    }

    /** @ar.org.fitc.spec_ref */
    public final AlgorithmParameters getParameters() {
        return cipherSpi.engineGetParameters();
    }

    /** @ar.org.fitc.spec_ref */
    public final Provider getProvider() {
        return provider;
    }

    /**
     * @ar.org.fitc.spec_ref this method ignore any juridiction policy installed  
     */
    public final void init(int opmode, Certificate certificate)
            throws InvalidKeyException {
        init(opmode, certificate, new SecureRandom());
    }

    /**
     * @ar.org.fitc.spec_ref this method ignore any juridiction policy installed  
     */
    public final void init(int opmode, Certificate certificate,
            SecureRandom random) throws InvalidKeyException {
        if (certificate instanceof X509Certificate) {
            X509Certificate cert = (X509Certificate) certificate;
            Set critSet = cert.getCriticalExtensionOIDs();
            if (critSet != null && !critSet.isEmpty()) {
                for (Iterator i = critSet.iterator(); i.hasNext();) {
                    if ((String) i.next() != KEY_USAGE) {
                        if ((opmode == WRAP_MODE && cert.getKeyUsage()[KEY_ENCIPHERMENT])
                                || (opmode == ENCRYPT_MODE && cert
                                        .getKeyUsage()[DIGITAL_SIGNATURE])) {
                            throw new InvalidKeyException(
                                    "Invalid certificate's key usage for "
                                            + "the requested operation");
                        }
                        break;
                    }
                }
            }
        }
        init(opmode, certificate != null ? certificate.getPublicKey() : null,
                random);
    }
    
    /**
     * @ar.org.fitc.spec_ref this method ignore any jurisdiction policy installed  
     */
    public final void init(int opmode, Key key) throws InvalidKeyException {
        init(opmode, key, new SecureRandom());
    }

    /**
     * @ar.org.fitc.spec_ref this method ignore any jurisdiction policy installed  
     */
    public final void init(int opmode, Key key, AlgorithmParameters params)
            throws InvalidKeyException, InvalidAlgorithmParameterException {
        init(opmode, key, params, new SecureRandom());
    }

    /**
     * @ar.org.fitc.spec_ref this method ignore any jurisdiction policy installed  
     */
    public final void init(int opmode, Key key, AlgorithmParameters params,
            SecureRandom random) throws InvalidKeyException,
            InvalidAlgorithmParameterException {
        if (!nullCipher && (opmode < 1 || opmode > 4)) {
            throw new InvalidParameterException(
                    "cannot recognize the requested opmode");
        }
        cipherSpi.engineInit(opmode, key, params, random);
        this.opmode = opmode; 
        initialized = true;
    }

    /**
     * @ar.org.fitc.spec_ref this method ignore any jurisdiction policy installed  
     */
    public final void init(int opmode, Key key, AlgorithmParameterSpec params)
            throws InvalidKeyException, InvalidAlgorithmParameterException {
        init(opmode, key, params, new SecureRandom());
    }

    /**
     * @ar.org.fitc.spec_ref this method ignore any jurisdiction policy installed  
     */
    public final void init(int opmode, Key key, AlgorithmParameterSpec params,
            SecureRandom random) throws InvalidKeyException,
            InvalidAlgorithmParameterException {
        if (!nullCipher && (opmode < 1 || opmode > 4)) {
            throw new InvalidParameterException(
                    "cannot recognize the requested opmode");
        }
        cipherSpi.engineInit(opmode, key, params, random);
        this.opmode = opmode;
        initialized = true;
    }

    /**
     * @ar.org.fitc.spec_ref this method ignore any jurisdiction policy installed  
     */
    public final void init(int opmode, Key key, SecureRandom random)
            throws InvalidKeyException {
        if (!nullCipher && (opmode < 1 || opmode > 4)) {
            throw new InvalidParameterException(
                    "cannot recognize the requested opmode");
        }
        cipherSpi.engineInit(opmode, key, random);
        this.opmode = opmode;
        initialized = true;
    }

    /** @ar.org.fitc.spec_ref */
    public final Key unwrap(byte[] wrappedKey, String wrappedKeyAlgorithm,
            int wrappedKeyType) throws InvalidKeyException,
            NoSuchAlgorithmException {
        if (opmode != UNWRAP_MODE && !nullCipher) {
            throw new IllegalStateException("this cipher has not been " +
            "initialized for unwrapping");
        }
        if (wrappedKeyType < 1 || wrappedKeyType > 3) {
            throw new IllegalArgumentException("wrappedKeyType invalid");
        }
        return cipherSpi.engineUnwrap(wrappedKey, wrappedKeyAlgorithm,
                wrappedKeyType);
    }

    /** 
     * @ar.org.fitc.spec_ref 
     * If it's an instance of NullCipher the <code>IllegalStateException</code> 
     * is not thrown
     * <br>
     * <br>
     * <code>IllegalArgumentException</code> is thrown if <code>input</code> is 
     * null
     */
    public final byte[] update(byte[] input) {
        // if input is null in the next call IllegalArgumentException should be
        // raised
        return update(input, 0, input != null ? input.length : 0);
    }

    /** 
     * @ar.org.fitc.spec_ref 
     * If it's an instance of NullCipher the <code>IllegalStateException</code> 
     * is not thrown
     * <br>
     * <br>
     * <code>IllegalArgumentException</code> is thrown if <code>input</code> is 
     * null
     */
    public final byte[] update(byte[] input, int inputOffset, int inputLen) {
        checkArrayOp(input, inputOffset, inputLen, 0);
        return cipherSpi.engineUpdate(input, inputOffset, inputLen);
    }

    /** 
     * @ar.org.fitc.spec_ref 
     * If it's an instance of NullCipher the <code>IllegalStateException</code> 
     * is not thrown
     * <br>
     * <br>
     * <code>IllegalArgumentException</code> is thrown if <code>input</code> is 
     * null
     */
    public final int update(byte[] input, int inputOffset, int inputLen,
            byte[] output) throws ShortBufferException {
        checkArrayOp(input, inputOffset, inputLen, 0);
        return update(input, inputOffset, inputLen, output, 0);
    }

    /** 
     * @ar.org.fitc.spec_ref 
     * If it's an instance of NullCipher the <code>IllegalStateException</code> 
     * is not thrown 
     * <br>
     * <br>
     * <code>IllegalArgumentException</code> is thrown if <code>input</code> is 
     * null
     */
    public final int update(byte[] input, int inputOffset, int inputLen,
            byte[] output, int outputOffset) throws ShortBufferException {
        checkArrayOp(input, inputOffset, inputLen, outputOffset);
        return cipherSpi.engineUpdate(input, inputOffset, inputLen, output,
                outputOffset);
    }

    /** 
     * @ar.org.fitc.spec_ref 
     * If it's an instance of NullCipher the <code>IllegalStateException</code> 
     * is not thrown
     * <br>
     * <br>
     * <code>IllegalArgumentException</code> is thrown if <code>input</code> or 
     * <code>output</code> are null
     */
    public final int update(ByteBuffer input, ByteBuffer output)
            throws ShortBufferException {
        checkByteBufferOp(input, output);
        return cipherSpi.engineUpdate(input, output);
    }

    /** @ar.org.fitc.spec_ref */
    public final byte[] wrap(Key key) throws IllegalBlockSizeException,
            InvalidKeyException {
        if (opmode != WRAP_MODE && !nullCipher) {
            throw new IllegalStateException("this cipher has not been " +
                    "initialized for wrapping");
        }
        return cipherSpi.engineWrap(key);
    }
}
