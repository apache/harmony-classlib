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
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

/**
 * 
 * 
 * @author Diego Raúl Mercado
 * @version 1.2
 * @ar.org.fitc.spec_ref
 */
public abstract class CipherSpi {

	/**
     * Transfers bytes from <code>input</code> into a new array which is
     * returned. Additionally, checks if we can process output's remaining
     * bytes, otherwise it would throw a <code>ShortBufferException</code>
     * 
     * @param input
     *            the input buffer
     * @param output
     *            the output buffer
     * @return a new array with input's remaining bytes as content
     * @throws ShortBufferException
     *             if the number of output's remaining bytes are less than the
     *             number's of bytes needed to process input's remaining bytes
     */
    private final byte[] byteBufferOp(ByteBuffer input, ByteBuffer output)
            throws ShortBufferException {
        /* 
         * We cannot delegate this exception to ByteBuffers because position and 
         * limit may change with this operation
         */
        if (output.remaining() < engineGetOutputSize(input.remaining())) {
            throw new ShortBufferException(" output's remaining bytes are " +
                    "less than the number's of bytes needed to process " +
                    "input's remaining bytes");
        }
        byte[] in = new byte[input.remaining()];
        input.get(in);
        return in;
    }

    /** @ar.org.fitc.spec_ref */
    public CipherSpi() {
    }

    /** @ar.org.fitc.spec_ref */
    protected abstract byte[] engineDoFinal(byte[] input, int inputOffset,
            int inputLen) throws IllegalBlockSizeException, BadPaddingException;

    /** @ar.org.fitc.spec_ref */
    protected abstract int engineDoFinal(byte[] input, int inputOffset,
            int inputLen, byte[] output, int outputOffset)
            throws ShortBufferException, IllegalBlockSizeException,
            BadPaddingException;

    /** @ar.org.fitc.spec_ref */
    protected int engineDoFinal(ByteBuffer input, ByteBuffer output)
            throws ShortBufferException, IllegalBlockSizeException,
            BadPaddingException {
        int remaining = input.remaining();
        output.put(engineDoFinal(byteBufferOp(input, output), 0, remaining));
        return remaining;
    }

    /** @ar.org.fitc.spec_ref */
    protected abstract int engineGetBlockSize();

    /** @ar.org.fitc.spec_ref */
    protected abstract byte[] engineGetIV();

    /** @ar.org.fitc.spec_ref */
    protected int engineGetKeySize(Key key) throws InvalidKeyException {
        throw new UnsupportedOperationException("Operation not supported");
    }

    /** @ar.org.fitc.spec_ref */
    protected abstract int engineGetOutputSize(int inputLen);

    /** @ar.org.fitc.spec_ref */
    protected abstract AlgorithmParameters engineGetParameters();

    /** @ar.org.fitc.spec_ref */
    protected abstract void engineInit(int opmode, Key key,
            AlgorithmParameters params, SecureRandom random)
            throws InvalidKeyException, InvalidAlgorithmParameterException;

    /** @ar.org.fitc.spec_ref */
    protected abstract void engineInit(int opmode, Key key,
            AlgorithmParameterSpec params, SecureRandom random)
            throws InvalidKeyException, InvalidAlgorithmParameterException;

    /** @ar.org.fitc.spec_ref */
    protected abstract void engineInit(int opmode, Key key, SecureRandom random)
            throws InvalidKeyException;

    /** @ar.org.fitc.spec_ref */
    protected abstract void engineSetMode(String mode)
            throws NoSuchAlgorithmException;

    /** @ar.org.fitc.spec_ref */
    protected abstract void engineSetPadding(String padding)
            throws NoSuchPaddingException;

    /** @ar.org.fitc.spec_ref */
    protected Key engineUnwrap(byte[] wrappedKey, String wrappedKeyAlgorithm,
            int wrappedKeyType) throws InvalidKeyException,
            NoSuchAlgorithmException {
        throw new UnsupportedOperationException("Operation not supported");
    }

    /** @ar.org.fitc.spec_ref */
    protected abstract byte[] engineUpdate(byte[] input, int inputOffset,
            int inputLen);

    /** @ar.org.fitc.spec_ref */
    protected abstract int engineUpdate(byte[] input, int inputOffset,
            int inputLen, byte[] output, int outputOffset)
            throws ShortBufferException;

    /** @ar.org.fitc.spec_ref */
    protected int engineUpdate(ByteBuffer input, ByteBuffer output)
            throws ShortBufferException {
		int remaining = input.remaining();
        byte[] b = engineUpdate(byteBufferOp(input, output), 0, remaining);
        if (b != null) {
           output.put(b);
        }
        return remaining;
    }

    /** @ar.org.fitc.spec_ref */
    protected byte[] engineWrap(Key key) throws IllegalBlockSizeException,
            InvalidKeyException {
        throw new UnsupportedOperationException("Operation not supported");
    }
}
