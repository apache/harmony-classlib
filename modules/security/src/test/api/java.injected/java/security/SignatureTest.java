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

package java.security;

import org.apache.harmony.security.tests.support.MySignature1;

import junit.framework.TestCase;

/**
 * Tests for <code>Signature</code> constructor and methods
 * 
 */
public class SignatureTest extends TestCase {

	/*
	 * Class under test for Object clone()
	 */
	public void testClone() {
		MySignature1 s = new MySignature1("ABC");
		try {
			s.clone();
			fail("No expected CloneNotSupportedException");
		} catch (CloneNotSupportedException e) {	
		}	
	}

	public void testGetProvider() {
		MySignature1 s = new MySignature1("ABC");
		if (s.getState() != Signature.UNINITIALIZED) {
			fail("Incorrect state");
		}
		if (s.getProvider() != null) {
			fail("getProvider() failed");
		}
	}

	public void testGetAlgorithm() {
		MySignature1 s = new MySignature1("ABC");
		if (s.getState() != Signature.UNINITIALIZED) {
			fail("Incorrect state");
		}
		if (!"ABC".equals(s.getAlgorithm())) {
			fail("getAlgorithm() failed");
		}
	}

	/*
	 * Class under test for void initVerify(PublicKey)
	 */
	public void testInitVerifyPublicKey() {
		MySignature1 s = new MySignature1("ABC");
		try {
			s.initVerify(new MyPublicKey());
		} catch (InvalidKeyException e) {
			fail(e.toString());
		}
		if (s.getState() != Signature.VERIFY) {
			fail("Incorrect state");
		}
		if (!s.runEngineInitVerify) {
			fail("initVerify() failed");
		}
	}

	/*
	 * Class under test for void initVerify(Certificate)
	 */
	public void testInitVerifyCertificate() {
		MySignature1 s = new MySignature1("ABC");
		try {
			s.initVerify(new MyCertificate());
		} catch (InvalidKeyException e) {
			fail(e.toString());
		}
		if (s.getState() != Signature.VERIFY) {
			fail("Incorrect state");
		}
		if (!s.runEngineInitVerify) {
			fail("initVerify() failed");
		}
	}

	/*
	 * Class under test for void initSign(PrivateKey)
	 */
	public void testInitSignPrivateKey() {
		MySignature1 s = new MySignature1("ABC");
		try {
			s.initSign(new MyPrivateKey());
		} catch (InvalidKeyException e) {
			fail(e.toString());
		}
		if (s.getState() != Signature.SIGN) {
			fail("Incorrect state");
		}
		if (!s.runEngineInitSign) {
			fail("initSign() failed");
		}
	}

	/*
	 * Class under test for void initSign(PrivateKey, SecureRandom)
	 */
	public void testInitSignPrivateKeySecureRandom() {
		MySignature1 s = new MySignature1("ABC");
		try {
			s.initSign(new MyPrivateKey(), new SecureRandom());
		} catch (InvalidKeyException e) {
			fail(e.toString());
		}
		if (s.getState() != Signature.SIGN) {
			fail("Incorrect state");
		}
		if (!s.runEngineInitSign) {
			fail("initSign() failed");
		}
	}

	/*
	 * Class under test for byte[] sign()
	 */
	public void testSign() {
		MySignature1 s = new MySignature1("ABC");
		try {
			s.sign();
			fail("No expected SignatureException");
		} catch (SignatureException e) {		
		}

		try {
			s.initVerify(new MyPublicKey());
		} catch (InvalidKeyException e) {
			fail(e.toString());
		}
		
		try {
			s.sign();
			fail("No expected SignatureException");
		} catch (SignatureException e) {		
		}
		
		try {
			s.initSign(new MyPrivateKey());
		} catch (InvalidKeyException e) {
			fail(e.toString());
		}
		try {
			s.sign();
		} catch (SignatureException e) {
			fail(e.toString());
		}
		if (s.getState() != Signature.SIGN) {
			fail("Incorrect state");
		}
		if (!s.runEngineSign) {
			fail("sign() failed");
		}
	}

	/*
	 * Class under test for boolean verify(byte[])
	 */
	public void testVerifybyteArray() {
		MySignature1 s = new MySignature1("ABC");
		byte[] b = {1, 2, 3, 4};
		try {
			s.verify(b);
			fail("No expected SignatureException");
		} catch (SignatureException e) {		
		}

		try {
			s.initSign(new MyPrivateKey());
		} catch (InvalidKeyException e) {
			fail(e.toString());
		}
		try {
			s.verify(b);
			fail("No expected SignatureException");
		} catch (SignatureException e) {		
		}
		
		try {
			s.initVerify(new MyPublicKey());
		} catch (InvalidKeyException e) {
			fail(e.toString());
		}
		
		try {
			s.verify(b);
		} catch (SignatureException e) {
			fail(e.toString());
		}
		
		if (s.getState() != Signature.VERIFY) {
			fail("Incorrect state");
		}
		if (!s.runEngineVerify) {
			fail("verify() failed");
		}
	}

	/*
	 * Class under test for boolean verify(byte[], int, int)
	 */
	public void testVerifybyteArrayintint() {
		MySignature1 s = new MySignature1("ABC");
		byte[] b = {1, 2, 3, 4};
		try {
			s.verify(b, 0, 3);
			fail("No expected SignatureException");
		} catch (SignatureException e) {		
		}

		try {
			s.initSign(new MyPrivateKey());
		} catch (InvalidKeyException e) {
			fail(e.toString());
		}
		try {
			s.verify(b, 0, 3);
			fail("No expected SignatureException");
		} catch (SignatureException e) {		
		}
		
		try {
			s.initVerify(new MyPublicKey());
		} catch (InvalidKeyException e) {
			fail(e.toString());
		}
		
		try {
			s.verify(b, 0, 5);
			fail("No expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {		
		} catch (SignatureException e) {
			fail(e.toString());
		}
		
		try {
			s.verify(b, 0, 3);
		} catch (SignatureException e) {
			fail(e.toString());
		}
		
		if (s.getState() != Signature.VERIFY) {
			fail("Incorrect state");
		}
		if (!s.runEngineVerify) {
			fail("verify() failed");
		}
	}

	/*
	 * Class under test for void update(byte)
	 */
	public void testUpdatebyte() {
		MySignature1 s = new MySignature1("ABC");
		try {
			s.update((byte)1);
			fail("No expected SignatureException");
		} catch (SignatureException e) {		
		}

		try {
			s.initVerify(new MyPublicKey());
		} catch (InvalidKeyException e) {
			fail(e.toString());
		}
		
		try {
			s.update((byte)1);
		} catch (SignatureException e) {
			fail(e.toString());
		}
		
		try {
			s.initSign(new MyPrivateKey());
		} catch (InvalidKeyException e) {
			fail(e.toString());
		}
		try {
			s.update((byte)1);
		} catch (SignatureException e) {
			fail(e.toString());
		}
		if (s.getState() != Signature.SIGN) {
			fail("Incorrect state");
		}
		if (!s.runEngineUpdate1) {
			fail("update() failed");
		}
	}

	/*
	 * Class under test for void update(byte[])
	 */
	public void testUpdatebyteArray() {
		MySignature1 s = new MySignature1("ABC");
		byte[] b = {1, 2, 3, 4};
		try {
			s.update(b);
			fail("No expected SignatureException");
		} catch (SignatureException e) {		
		}

		try {
			s.initVerify(new MyPublicKey());
		} catch (InvalidKeyException e) {
			fail(e.toString());
		}
		
		try {
			s.update(b);
		} catch (SignatureException e) {
			fail(e.toString());
		}
		
		try {
			s.initSign(new MyPrivateKey());
		} catch (InvalidKeyException e) {
			fail(e.toString());
		}
		try {
			s.update(b);
		} catch (SignatureException e) {
			fail(e.toString());
		}
		if (s.getState() != Signature.SIGN) {
			fail("Incorrect state");
		}
		if (!s.runEngineUpdate2) {
			fail("update() failed");
		}
	}

	/*
	 * Class under test for void update(byte[], int, int)
	 */
	public void testUpdatebyteArrayintint() {
		MySignature1 s = new MySignature1("ABC");
		byte[] b = {1, 2, 3, 4};
		try {
			s.update(b, 0, 3);
			fail("No expected SignatureException");
		} catch (SignatureException e) {		
		}

		try {
			s.initVerify(new MyPublicKey());
		} catch (InvalidKeyException e) {
			fail(e.toString());
		}
		
		try {
			s.update(b, 0, 3);
		} catch (SignatureException e) {
			fail(e.toString());
		}
			
		try {
			s.initSign(new MyPrivateKey());
		} catch (InvalidKeyException e) {
			fail(e.toString());
		}
		try {
			s.update(b, 0, 3);
		} catch (SignatureException e) {
			fail(e.toString());
		}
		if (s.getState() != Signature.SIGN) {
			fail("Incorrect state");
		}
		if (!s.runEngineUpdate2) {
			fail("update() failed");
		}
	}

	/*
	 * Class under test for void setParameter(String, Object)
	 */
	public void testSetParameterStringObject() {
		MySignature1 s = new MySignature1("ABC");
		s.setParameter("aaa", new Object());
	}

	/*
	 * Class under test for void setParameter(AlgorithmParameterSpec)
	 */
	public void testSetParameterAlgorithmParameterSpec() {
		MySignature1 s = new MySignature1("ABC");
		try {
			s.setParameter((java.security.spec.AlgorithmParameterSpec)null);
			fail("No expected UnsupportedOperationException");
		} catch (UnsupportedOperationException e){	
		} catch (Exception e){
			fail(e.toString());
		}
	}

	public void testGetParameter() {
		MySignature1 s = new MySignature1("ABC");
		try {
			s.getParameter("aaa");
		} catch (InvalidParameterException e){
			fail(e.toString());
		}
	}
	
	private class MyKey implements Key {
		public String getFormat() {
			return "123";
		}
		public byte[] getEncoded() {
			return null;
		}
		public String getAlgorithm() {
			return "aaa";
		}		
	}
	
	private class MyPublicKey extends MyKey implements PublicKey {}

	private class MyPrivateKey extends MyKey implements PrivateKey {}
	
	private class MyCertificate extends java.security.cert.Certificate {	
		public  MyCertificate() {
			super("MyCertificateType");
		}
		
		public PublicKey getPublicKey() {
			return new MyPublicKey();
		}
		
		public byte[] getEncoded() {
			return null;
		}
		public void verify(PublicKey key) {}
		
		public void verify(PublicKey key, String sigProvider) {}
		
		public String toString() {
			return "MyCertificate";
		}
	}
}
