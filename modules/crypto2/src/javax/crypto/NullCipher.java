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
import java.security.Provider;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

/**
 * 
 * 
 * @author Diego Raúl Mercado
 * @version 1.2
 * @ar.org.fitc.spec_ref
 */
public class NullCipher extends Cipher { 
    private static final Provider PROVIDER = null;

    private static final String TRANSFORMATION = null;

    private static final CipherSpi CIPHER_SPI = new NullCipherSpi();

    /** @ar.org.fitc.spec_ref */
    public NullCipher() {
    	super(CIPHER_SPI, PROVIDER, TRANSFORMATION);
    }

    /**
     * The null cipher spi's engine
     * 
     * @author Diego Raúl Mercado
     * @author Sergio Daniel Liendo
     * @version 1.2
     */
    private static class NullCipherSpi extends CipherSpi {
        private static final int BLOCK_SIZE = 1;

        private static final AlgorithmParameters PARAMS = null;

        protected byte[] engineDoFinal(byte[] input, int inputOffset,
                int inputLen) throws IllegalBlockSizeException,
                BadPaddingException {
            return engineUpdate(input, inputOffset, inputLen);
        }

        protected int engineDoFinal(byte[] input, int inputOffset,
                int inputLen, byte[] output, int outputOffset)
                throws ShortBufferException, IllegalBlockSizeException,
                BadPaddingException {
            return engineUpdate(input, inputOffset, inputLen, output,
                    outputOffset);
        }

        protected int engineGetBlockSize() {
            return BLOCK_SIZE;
        }

        protected byte[] engineGetIV() {
            return new byte[] { 0, 0, 0, 0, 0, 0, 0, 0 };
        }

        protected int engineGetOutputSize(int inputLen) {
            return inputLen;
        }

        protected AlgorithmParameters engineGetParameters() {
            return PARAMS;
        }

        protected void engineInit(int opmode, Key key,
                AlgorithmParameters params, SecureRandom random)
                throws InvalidKeyException, InvalidAlgorithmParameterException {
            // do nothing...
        }

        protected void engineInit(int opmode, Key key,
                AlgorithmParameterSpec params, SecureRandom random)
                throws InvalidKeyException, InvalidAlgorithmParameterException {
            // do nothing...
        }

        protected void engineInit(int opmode, Key key, SecureRandom random)
                throws InvalidKeyException {
            // do nothing...
        }

        protected void engineSetMode(String mode)
                throws NoSuchAlgorithmException {
            // do nothing...
        }

        protected void engineSetPadding(String padding)
                throws NoSuchPaddingException {
            // do nothing...
        }

        protected byte[] engineUpdate(byte[] input, int inputOffset,
                int inputLen) {
            if (input != null) {
                byte[] copy = new byte[inputLen];
                System.arraycopy(input, inputOffset, copy, 0, inputLen);
                return copy;
            }
            return null;
        }

        /*
         * TO BEAR IN MIND... When we call the cipher's method doFinal(byte[]
         * output, int outputOffset) internally this invoke engineDoFinal(new
         * byte[]{}, 0, 0, output, outputOffset);
         */
        protected int engineUpdate(byte[] input, int inputOffset, int inputLen,
                byte[] output, int outputOffset) throws ShortBufferException {
            if (input.length != 0) {
                if (!(output.length - outputOffset < inputLen)) {
                    System.arraycopy(input, inputOffset, output, outputOffset,
                            inputLen);
                } else {
                    throw new ShortBufferException();
                }
            }
            return inputLen;
        }
    }
}
