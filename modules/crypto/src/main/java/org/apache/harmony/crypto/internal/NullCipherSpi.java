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

package org.apache.harmony.crypto.internal;

import java.nio.ByteBuffer;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.BadPaddingException;
import javax.crypto.CipherSpi;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;

/**
 * CipherSpi implementation for javax.crypto.NullCipher
 * 
 */
public class NullCipherSpi extends CipherSpi {

    /**
     * See javax.crypto.CipherSpi#engineSetMode(java.lang.String)
     */
    public void engineSetMode(String arg0) throws NoSuchAlgorithmException {
        // Do nothing
    }

    /**
     * See javax.crypto.CipherSpi#engineSetPadding(java.lang.String)
     */
    public void engineSetPadding(String arg0) throws NoSuchPaddingException {
        // Do nothing
    }

    /**
     * See javax.crypto.CipherSpi#engineGetBlockSize()
     */
    public int engineGetBlockSize() {
        return 1;
    }

    /**
     * See javax.crypto.CipherSpi#engineGetOutputSize(int)
     */
    public int engineGetOutputSize(int inputLen) {
        return inputLen;
    }

    /**
     * See javax.crypto.CipherSpi#engineGetIV()
     */
    public byte[] engineGetIV() {
        return new byte[8]; // compatible with RI
    }

    /**
     * See javax.crypto.CipherSpi#engineGetParameters()
     */
    public AlgorithmParameters engineGetParameters() {
        return null;
    }

    /**
     * See javax.crypto.CipherSpi#engineInit(int, java.security.Key,
     * java.security.SecureRandom)
     */
    public void engineInit(int opmode, Key key, SecureRandom random)
            throws InvalidKeyException {
        // Do nothing
    }

    /**
     * See javax.crypto.CipherSpi#engineInit(int, java.security.Key,
     * java.security.spec.AlgorithmParameterSpec, java.security.SecureRandom)
     */
    public void engineInit(int opmode, Key key, AlgorithmParameterSpec params,
            SecureRandom random) throws InvalidKeyException,
            InvalidAlgorithmParameterException {
        // Do nothing
    }

    /**
     * See javax.crypto.CipherSpi#engineInit(int, java.security.Key,
     * java.security.AlgorithmParameters, java.security.SecureRandom)
     */
    public void engineInit(int opmode, Key key, AlgorithmParameters params,
            SecureRandom random) throws InvalidKeyException,
            InvalidAlgorithmParameterException {
        // Do nothing
    }

    /**
     * See javax.crypto.CipherSpi#engineUpdate(byte[], int, int)
     */
    public byte[] engineUpdate(byte[] input, int inputOffset, int inputLen) {
        if (input == null) {
            return null;
        }
        byte[] result = new byte[inputLen];
        System.arraycopy(input, inputOffset, result, 0, inputLen);
        return result;
    }

    /**
     * See javax.crypto.CipherSpi#engineUpdate(byte[], int, int, byte[], int)
     */
    public int engineUpdate(byte[] input, int inputOffset, int inputLen,
            byte[] output, int outputOffset) throws ShortBufferException {
        if (input == null) {
            return 0;
        }
        System.arraycopy(input, inputOffset, output, outputOffset, inputLen);
        return inputLen;
    }

    /**
     * See javax.crypto.CipherSpi#engineUpdate(ByteBuffer, ByteBuffer)
     */
    public int engineUpdate(ByteBuffer input, ByteBuffer output)
            throws ShortBufferException {
        if (input == null || output == null) {
            throw new NullPointerException();
        }
        int result = input.limit() - input.position();
        try {
            output.put(input);
        } catch (java.nio.BufferOverflowException e) {
            throw new ShortBufferException("output buffer is too small " + e);
        }
        return result;
    }

    /**
     * See javax.crypto.CipherSpi#engineDoFinal(byte[], int, int)
     */
    public byte[] engineDoFinal(byte[] input, int inputOffset, int inputLen)
            throws IllegalBlockSizeException, BadPaddingException {
        if (input == null) {
            return null;
        }
        return engineUpdate(input, inputOffset, inputLen);
    }

    /**
     * See javax.crypto.CipherSpi#engineDoFinal(byte[], int, int, byte[], int)
     */
    public int engineDoFinal(byte[] input, int inputOffset, int inputLen,
            byte[] output, int outputOffset) throws ShortBufferException,
            IllegalBlockSizeException, BadPaddingException {
        int result = engineUpdate(input, inputOffset, inputLen, output,
                outputOffset);
        return result;
    }

    /**
     * See javax.crypto.CipherSpi#engineDoFinal(ByteBuffer, ByteBuffer)
     */
    public int engineDoFinal(ByteBuffer input, ByteBuffer output)
            throws ShortBufferException, IllegalBlockSizeException,
            BadPaddingException {
        return engineUpdate(input, output);
    }

    /**
     * See javax.crypto.CipherSpi#engineWrap(Key)
     */
    public byte[] engineWrap(Key key) throws IllegalBlockSizeException,
            InvalidKeyException {
        throw new UnsupportedOperationException("Wrap");
    }

    /**
     * See javax.crypto.CipherSpi#engineUnwrap(byte[], String, int)
     */
    public Key engineUnwrap(byte[] wrappedKey, String wrappedKeyAlgorithm,
            int wrappedKeyType) throws InvalidKeyException,
            NoSuchAlgorithmException {
        throw new UnsupportedOperationException("Unwrap");
    }

    /**
     * See javax.crypto.CipherSpi#engineGetKeySize(Key)
     */
    public int engineGetKeySize(Key key) throws InvalidKeyException {
        throw new UnsupportedOperationException("GetKeySize");
    }
}