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

package javax.crypto;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

/**
 *
 * Cipher implementation for testing
 */
public class MyCipher extends CipherSpi {

	public MyCipher() {
		super();
	}

	protected void engineSetMode(String mode) throws NoSuchAlgorithmException {
	}

	protected void engineSetPadding(String padding)
			throws NoSuchPaddingException {
		if (!"PKCS5Padding".equals(padding)) {
			throw new  NoSuchPaddingException(padding);
		}
	}

	protected int engineGetBlockSize() {
		return 111;
	}

	protected int engineGetOutputSize(int inputLen) {
		return inputLen + 10;
	}

	protected byte[] engineGetIV() {
		byte[] b = {1,2,3};
		return b;
	}

	protected AlgorithmParameters engineGetParameters() {
		return null;
	}

	protected void engineInit(int opmode, Key key, SecureRandom random)
			throws InvalidKeyException {
	}

	protected void engineInit(int opmode, Key key,
			AlgorithmParameterSpec params, SecureRandom random)
			throws InvalidKeyException, InvalidAlgorithmParameterException {
	}

	protected void engineInit(int opmode, Key key, AlgorithmParameters params,
			SecureRandom random) throws InvalidKeyException,
			InvalidAlgorithmParameterException {
	}

	protected byte[] engineUpdate(byte[] input, int inputOffset, int inputLen) {
		return null;
	}

	protected int engineUpdate(byte[] input, int inputOffset, int inputLen,
			byte[] output, int outputOffset) throws ShortBufferException {
		return 0;
	}

	protected byte[] engineDoFinal(byte[] input, int inputOffset, int inputLen)
			throws IllegalBlockSizeException, BadPaddingException {
		return null;
	}

	protected int engineDoFinal(byte[] input, int inputOffset, int inputLen,
			byte[] output, int outputOffset) throws ShortBufferException,
			IllegalBlockSizeException, BadPaddingException {
		return 0;
	}

}
