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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.spec.IvParameterSpec;

/**
 * 
 * 
 * @author Diego Raul Mercado 
 * @version 1.2
 * @ar.org.fitc.spec_ref
 */
public class SealedObject implements Serializable {
    private static final long serialVersionUID = 4482838265551344752L;

    /** The algorithm used in the cipher's getInstance method */
    private String algorithm;

    private byte[] encodedObject;

    private boolean isEncoded;
    
    /** @ar.org.fitc.spec_ref */
    protected byte[] encodedParams;

    /** @ar.org.fitc.spec_ref */
    protected SealedObject(SealedObject so) {
        encodedObject = so.encodedObject.clone();
        encodedParams = so.encodedParams.clone();
        algorithm = so.algorithm;
        isEncoded = so.isEncoded;
    }

    /**
     * Writes the <code>object</code> into a stream and returns the result in
     * a new array
     * 
     * @param object
     *            the object to serialize
     * @return a new serialized array
     * @throws IOException
     *             if an I/O error happens
     */
    private final byte[] serialization(Object object) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(object);
        oos.close();
        return baos.toByteArray();
    }

    /**
     * Reads an object from a stream that encapsulate <code>b</code> and
     * returns the result as an object
     * 
     * @param b
     *            the buffer that contains the serialized object
     * @return an object that has been deserialized
     * @throws IOException
     *             if an I/O error happens
     * @throws ClassNotFoundException
     *             if cannot deserialize the object
     */
    private final Object deserialization(byte[] b) throws IOException,
            ClassNotFoundException {
        ObjectInputStream ois = 
            new ObjectInputStream(new ByteArrayInputStream(b));
        Object result = ois.readObject();
        ois.close();
        return result;
    }

    /**
     * Initializes the cipher <code>c</code> with the given key <code>k</code>
     * and, if <code>encodedParams</code> is present, with the corresponding
     * IV or algorithm's parameters
     * 
     * @param c
     *            the cipher to initialize
     * @param k
     *            the key for decryption
     * @throws InvalidKeyException
     *             if the key <code>k</code> is inappropiate for initializing
     *             the cipher <code>c</code>
     * @throws IOException
     *             if an I/O error happens
     * @throws NoSuchAlgorithmException
     *             if cannot get the corresponding algorithm's parameters
     */
    private final void initCipher(Cipher c, Key k) throws InvalidKeyException,
            IOException, NoSuchAlgorithmException {
        if (encodedParams != null) {
            if (isEncoded) { //=> params != null
                String[] transformation = algorithm.split("/");
                AlgorithmParameters params = 
                    AlgorithmParameters.getInstance(transformation[0]);
                if (params != null) {
                    params.init(encodedParams);
                    try {
                        c.init(Cipher.DECRYPT_MODE, k, params);
                    } catch (InvalidAlgorithmParameterException e) {
                        // should not get raised
                        throw new AssertionError(e);
                    }
                }
            } else { // => c.getIV() != null
                IvParameterSpec ivParamSpec = new IvParameterSpec(encodedParams);
                try {
                    c.init(Cipher.DECRYPT_MODE, k, ivParamSpec);
                } catch (InvalidAlgorithmParameterException e) {
                    // should not get raisedparams != null
                    throw new AssertionError(e);
                }
            }
        } else {
            c.init(Cipher.DECRYPT_MODE, k);    
        }
    }

    /**
     * Decrypts <code>encodedObject</code> calling doFinal(byte) cipher's method
     * and returns the result in a new array 
     * 
     * @param c the given cipher 
     * @return the result of decrypting <code>encodedObject</code> with  
     * <code>cipher</code>
     */
    private byte[] decryptObject(Cipher c) {
        byte[] result = null;

        try {
            result = c.doFinal(encodedObject);
        } catch (IllegalBlockSizeException e) {
            // because the cipher is initialize for decryption this exception
            // is not raised
            throw new AssertionError(e);
        } catch (BadPaddingException e) {
            // should not get raised
            throw new AssertionError(e);
        }

        return result;
    }

    /**
     * @ar.org.fitc.spec_ref <code>IllegalStateException</code> is thrown if
     *                       this cipher has not been initialized
     */
    public SealedObject(Serializable object, Cipher c) throws IOException,
            IllegalBlockSizeException {
        algorithm = c.getAlgorithm();
        try {
            encodedObject = c.doFinal(serialization(object));
        } catch (BadPaddingException e) {
            throw new IllegalBlockSizeException("Input length is not multiple "
                    + "of the cipher's block size");
        }

        AlgorithmParameters params = c.getParameters();

        if (params != null) {
            encodedParams = params.getEncoded();
            isEncoded = true;
        } else {
            encodedParams = c.getIV();
        }
    }

    /** @ar.org.fitc.spec_ref */
    public final String getAlgorithm() {
        return algorithm;
    }

    /** @ar.org.fitc.spec_ref */
    public final Object getObject(Cipher c) throws IOException,
            ClassNotFoundException, IllegalBlockSizeException,
            BadPaddingException {
        byte[] b = c.doFinal(encodedObject);
        return deserialization(b);
    }

    /**
     * @ar.org.fitc.spec_ref <code>NullPointerException</code> is thrown if
     *                       <code>key</code> is null
     */
    public final Object getObject(Key key) throws IOException,
            ClassNotFoundException, NoSuchAlgorithmException,
            InvalidKeyException {
        Cipher c = null;

        try {
            c = Cipher.getInstance(algorithm);
        } catch (NoSuchPaddingException e) {
            // should not get raised
            throw new AssertionError(e);
        }

        initCipher(c, key);
        return deserialization(decryptObject(c));
    }

    /**
     * @ar.org.fitc.spec_ref <code>NullPointerException</code> is thrown if
     *                       <code>key</code> is null
     */
    public final Object getObject(Key key, String provider) throws IOException,
            ClassNotFoundException, NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException {
        if (provider == null || provider.equals("")) {
            throw new IllegalArgumentException("provider cannot be null");
        }

        Cipher c = null;
        try {
            c = Cipher.getInstance(algorithm, provider);
        } catch (NoSuchPaddingException e) {
            throw new NoSuchAlgorithmException(e);
        }

        initCipher(c, key);
        return deserialization(decryptObject(c));
    }
}
