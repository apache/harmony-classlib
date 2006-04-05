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

import javax.crypto.spec.SecretKeySpec;

import junit.framework.TestCase;

import java.security.*;

/**
 *
 * Tests for NullCipher
 */
public class NullCipherTest extends TestCase {

	private Cipher c;
	
    protected void setUp() throws Exception {
        super.setUp();
        c = new NullCipher();
    }

	public void testGetAlgorithm() {
		c.getAlgorithm();
	}

	public void testGetBlockSize() {
		if (c.getBlockSize() != 1) {
			fail("getBlockSize() failed");
		}
	}

	public void testGetOutputSize() {
		if (c.getOutputSize(111) != 111) {
			fail("getOutputSize() failed");
		}
	}

	public void testGetIV() {
		if (c.getIV() != null) {
			fail("getIV() failed");
		}
	}

	public void testGetParameters() {
		if (c.getParameters() != null) {
			fail("getParameters() failed");	
		}
	}

	public void testGetExemptionMechanism() {
		if (c.getExemptionMechanism() != null) {
			fail("getExemptionMechanism() failed");	
		}	
	}

	/*
	 * Class under test for void init(int, Key)
	 */
	public void testInitintKey() {
		try {
			c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(new byte[1], "algorithm"));		
		} catch (InvalidKeyException e) {
			fail(e.toString());
		}	
	}

	/*
	 * Class under test for void init(int, Key, SecureRandom)
	 */
	public void testInitintKeySecureRandom() {
		try {
			c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(new byte[1], "algorithm"), new SecureRandom());		
		} catch (InvalidKeyException e) {
			fail(e.toString());
		}
	}

	/*
	 * Class under test for void init(int, Key, AlgorithmParameterSpec)
	 */
	public void testInitintKeyAlgorithmParameterSpec() {
		class myAlgorithmParameterSpec implements java.security.spec.AlgorithmParameterSpec {}
		try {
			c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(new byte[1], "algorithm"), new myAlgorithmParameterSpec());
		} catch (InvalidKeyException e) {
			fail(e.toString());
		} catch (InvalidAlgorithmParameterException  e) {
			fail(e.toString());
		}
	}

	/*
	 * Class under test for void init(int, Key, AlgorithmParameterSpec, SecureRandom)
	 */
	public void testInitintKeyAlgorithmParameterSpecSecureRandom() {
	}

	/*
	 * Class under test for void init(int, Key, AlgorithmParameters)
	 */
	public void testInitintKeyAlgorithmParameters() {
	}

	/*
	 * Class under test for void init(int, Key, AlgorithmParameters, SecureRandom)
	 */
	public void testInitintKeyAlgorithmParametersSecureRandom() {
	}

	/*
	 * Class under test for void init(int, Certificate)
	 */
	public void testInitintCertificate() {		
	}

	/*
	 * Class under test for void init(int, Certificate, SecureRandom)
	 */
	public void testInitintCertificateSecureRandom() {
	}

	/*
	 * Class under test for byte[] update(byte[])
	 */
	public void testUpdatebyteArray() {
		byte [] b = {1, 2, 3, 4, 5};
		byte [] r = c.update(b);
		if (b.length != r.length) {
			fail("different length");
		}
		for (int i = 0; i < b.length; i++) {
			if (b[i] != r[i]) {
				fail("different content");
			}
		}		
	}

	/*
	 * Class under test for byte[] update(byte[], int, int)
	 */
	public void testUpdatebyteArrayintint() {
		byte [] b = {1, 2, 3, 4, 5};
		byte [] r = c.update(b, 0, 5);
		if (b.length != r.length) {
			fail("different length");
		}
		for (int i = 0; i < b.length; i++) {
			if (b[i] != r[i]) {
				fail("different content");
			}
		}
		
		r = c.update(b, 1, 3);
		if (r.length != 3) {
			fail("different length");
		}
		for (int i = 0; i < 3; i++) {
			if (b[i + 1] != r[i]) {
				fail("different content");
			}
		}
	}

	/*
	 * Class under test for int update(byte[], int, int, byte[])
	 */
	public void testUpdatebyteArrayintintbyteArray() {
		byte [] b = {1, 2, 3, 4, 5};
		byte [] r = new byte[5]; 
		try {
			c.update(b, 0, 5, r);			
		} catch (ShortBufferException e) {
			fail(e.toString());
		}

		for (int i = 0; i < b.length; i++) {
			if (b[i] != r[i]) {
				fail("different content");
			}
		}		
	}

	/*
	 * Class under test for int update(byte[], int, int, byte[], int)
	 */
	public void testUpdatebyteArrayintintbyteArrayint() {
		byte [] b = {1, 2, 3, 4, 5};
		byte [] r = new byte[5]; 
		try {
			c.update(b, 0, 5, r, 0);			
		} catch (ShortBufferException e) {
			fail(e.toString());
		}

		for (int i = 0; i < b.length; i++) {
			if (b[i] != r[i]) {
				fail("different content");
			}
		}	
	}

	/*
	 * Class under test for int update(ByteBuffer, ByteBuffer)
	 */
	public void testUpdateByteBufferByteBuffer() {
	}

	/*
	 * Class under test for byte[] doFinal()
	 */
	public void testDoFinal() {
		try {
			if (c.doFinal() != null) {
				fail("doFinal failed");
			}			
		} catch (BadPaddingException e) {
			fail(e.toString());
		} catch (IllegalBlockSizeException  e) {
			fail(e.toString());
		}

	}

	/*
	 * Class under test for int doFinal(byte[], int)
	 */
	public void testDoFinalbyteArrayint() {
		byte [] r = new byte[5];
		try {
			if (c.doFinal(r, 0) != 0) {
				fail("doFinal failed");
			}			
		} catch (BadPaddingException e) {
			fail(e.toString());
		} catch (IllegalBlockSizeException  e) {
			fail(e.toString());
		} catch (ShortBufferException e) {
			fail(e.toString());
		}
	}

	/*
	 * Class under test for byte[] doFinal(byte[])
	 */
	public void testDoFinalbyteArray() {
		byte [] b = {1, 2, 3, 4, 5};
		byte [] r = null; 
		try {
			r = c.doFinal(b);
		} catch (BadPaddingException e) {
			fail(e.toString());
		} catch (IllegalBlockSizeException  e) {
			fail(e.toString());
		}
		if (b.length != r.length) {
			fail("different length");
		}
		for (int i = 0; i < b.length; i++) {
			if (b[i] != r[i]) {
				fail("different content");
			}
		}		
	}

	/*
	 * Class under test for byte[] doFinal(byte[], int, int)
	 */
	public void testDoFinalbyteArrayintint() {
		byte [] b = {1, 2, 3, 4, 5};
		byte [] r = null;
		try {
			r = c.doFinal(b, 0, 5);
		} catch (BadPaddingException e) {
			fail(e.toString());
		} catch (IllegalBlockSizeException  e) {
			fail(e.toString());
		}
		if (b.length != r.length) {
			fail("different length");
		}
		for (int i = 0; i < b.length; i++) {
			if (b[i] != r[i]) {
				fail("different content");
			}
		}
		
		try {
			r = c.doFinal(b, 1, 3);
		} catch (BadPaddingException e) {
			fail(e.toString());
		} catch (IllegalBlockSizeException  e) {
			fail(e.toString());
		}
		if (r.length != 3) {
			fail("different length");
		}
		for (int i = 0; i < 3; i++) {
			if (b[i + 1] != r[i]) {
				fail("different content");
			}
		}
	}

	/*
	 * Class under test for int doFinal(byte[], int, int, byte[])
	 */
	public void testDoFinalbyteArrayintintbyteArray() {
		byte [] b = {1, 2, 3, 4, 5};
		byte [] r = new byte[5]; 
		try {
			c.doFinal(b, 0, 5, r);			
		} catch (BadPaddingException e) {
			fail(e.toString());
		}  catch (ShortBufferException e) {
			fail(e.toString());
		} catch (IllegalBlockSizeException  e) {
			fail(e.toString());
		}

		for (int i = 0; i < b.length; i++) {
			if (b[i] != r[i]) {
				fail("different content");
			}
		}
	}

	/*
	 * Class under test for int doFinal(byte[], int, int, byte[], int)
	 */
	public void testDoFinalbyteArrayintintbyteArrayint() {
		byte [] b = {1, 2, 3, 4, 5};
		byte [] r = new byte[5]; 
		try {
			c.doFinal(b, 0, 5, r, 0);			
		} catch (BadPaddingException e) {
			fail(e.toString());
		}  catch (ShortBufferException e) {
			fail(e.toString());
		} catch (IllegalBlockSizeException  e) {
			fail(e.toString());
		}

		for (int i = 0; i < b.length; i++) {
			if (b[i] != r[i]) {
				fail("different content");
			}
		}
	}

	/*
	 * Class under test for int doFinal(ByteBuffer, ByteBuffer)
	 */
	public void testDoFinalByteBufferByteBuffer() {
	}

	public void testWrap() {
	}

	public void testUnwrap() {
	}

}
