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

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.Enumeration;

import org.apache.harmony.security.TestKeyPair;


import junit.framework.TestCase;

/**
 * Tests for <code>Cipher</code> class constructors and methods.
 * 
 */
public class CipherTest extends TestCase {

	private Provider p1;
	private Provider p2;
	private TestKeyPair tkp = null;
	private Key key = null;
	
	private boolean noKey = false;
	
	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		p1 = new MyProvider1();
		p2 = new MyProvider2();
		Security.insertProviderAt(p1, 1);
		Security.insertProviderAt(p2, 1);
		try {
			tkp = new TestKeyPair("DSA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			noKey = true;
			return;
		}
		
		try {
			key = tkp.getPrivate();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
			noKey = true;
			return;
		}

	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		Security.removeProvider("MyProvider1");
		Security.removeProvider("MyProvider2");
	}

	/*
	 * Class under test for Cipher getInstance(String)
	 */
	public void testGetInstanceString1() {
		
		Cipher c = null;
		try {
			c = Cipher.getInstance("DES");
		} catch (NoSuchAlgorithmException e) {
			fail(e.toString());
		} catch (NoSuchPaddingException e) {
			fail(e.toString());
		}
		if (c.getProvider() != p2) {
			fail("Case1: getInstance() failed");
		}	
	}

	/*
	 * Class under test for Cipher getInstance(String)
	 */
	public void testGetInstanceString2() {
		
		Cipher c = null;
		try {
			c = Cipher.getInstance("DES/CBC/PKCS5Padding");
		} catch (NoSuchAlgorithmException e) {
			fail(e.toString());
		} catch (NoSuchPaddingException e) {
			fail(e.toString());
		}

		if (c.getProvider() != p1) {
			fail("Case1: getInstance() failed");
		}
		Security.removeProvider(p1.getName());
		try {
			c = Cipher.getInstance("DES/CBC/PKCS5Padding");
		} catch (NoSuchAlgorithmException e) {
			fail(e.toString());
		} catch (NoSuchPaddingException e) {
			fail(e.toString());
		}
		if (c.getProvider() != p2) {
			fail("Case2: getInstance() failed");
		}	
	}

	/*
	 * Class under test for Cipher getInstance(String)
	 */
	public void testGetInstanceString3() {
		
		try {
			Cipher.getInstance("DES/CBC/");
			fail("Case1: No excpected NoSuchAlgorithmException");
		} catch (NoSuchAlgorithmException e) {
		} catch (NoSuchPaddingException e) {
			fail(e.toString());
		}

		try {
			Cipher.getInstance("DES//PKCS5Padding");
			fail("Case2: No excpected NoSuchAlgorithmException");
		} catch (NoSuchAlgorithmException e) {
		} catch (NoSuchPaddingException e) {
			fail(e.toString());
		}

		try {
			Cipher.getInstance("DES/CBC/IncorrectPadding");
			fail("No excpected NoSuchPaddingException");
		} catch (NoSuchAlgorithmException e) {
			fail(e.toString());
		} catch (NoSuchPaddingException e) {
		}
	}	

	
	/*
	 * Class under test for Cipher getInstance(String, String)
	 */
	public void testGetInstanceStringString1() {
		try {
			Cipher.getInstance("DES/CBC/", "MyProvider2");
			fail("Case1: No excpected NoSuchAlgorithmException");
		} catch (NoSuchAlgorithmException e) {
		} catch (NoSuchPaddingException e) {
			fail(e.toString());
		} catch (NoSuchProviderException e) {
			fail(e.toString());
		}

		try {
			Cipher.getInstance("DES//PKCS5Padding", "MyProvider2");
			fail("Case2: No excpected NoSuchAlgorithmException");
		} catch (NoSuchAlgorithmException e) {
		} catch (NoSuchPaddingException e) {
			fail(e.toString());
		} catch (NoSuchProviderException e) {
			fail(e.toString());
		}

		try {
			Cipher.getInstance("DES/CBC/IncorrectPadding", "MyProvider2");
			fail("No excpected NoSuchProviderException");
		} catch (NoSuchAlgorithmException e) {
			fail(e.toString());
		} catch (NoSuchPaddingException e) {
		} catch (NoSuchProviderException e) {
			fail(e.toString());
		}
		
		try {
			Cipher.getInstance("DES/CBC/PKCS5Padding", "IncorrectProvider");
			fail("No excpected NoSuchProviderException");
		} catch (NoSuchAlgorithmException e) {
			fail(e.toString());
		} catch (NoSuchPaddingException e) {
			fail(e.toString());
		} catch (NoSuchProviderException e) {
		}		
	}

	/*
	 * Class under test for Cipher getInstance(String, String)
	 */
	public void testGetInstanceStringString2() {
		Cipher c = null;
		try {
			c = Cipher.getInstance("DES", "MyProvider2");
		} catch (NoSuchAlgorithmException e) {
			fail(e.toString());
		} catch (NoSuchPaddingException e) {
			fail(e.toString());
		} catch (NoSuchProviderException e) {
			fail(e.toString());
		}
		if (c.getProvider() != p2) {
			fail("Case2: getInstance() failed");
		}
		
		try {
			c = Cipher.getInstance("DES/CBC/PKCS5Padding", "MyProvider2");
		} catch (NoSuchAlgorithmException e) {
			fail(e.toString());
		} catch (NoSuchPaddingException e) {
			fail(e.toString());
		} catch (NoSuchProviderException e) {
			fail(e.toString());
		}
		if (c.getProvider() != p2) {
			fail("Case2: getInstance() failed");
		}
	}
	/*
	 * Class under test for Cipher getInstance(String, Provider)
	 */
	public void testGetInstanceStringProvider1() {
		try {
			Cipher.getInstance("DES/CBC/", p2);
			fail("Case1: No excpected NoSuchAlgorithmException");
		} catch (NoSuchAlgorithmException e) {
		} catch (NoSuchPaddingException e) {
			fail(e.toString());
		}

		try {
			Cipher.getInstance("DES//PKCS5Padding", p2);
			fail("Case2: No excpected NoSuchAlgorithmException");
		} catch (NoSuchAlgorithmException e) {
		} catch (NoSuchPaddingException e) {
			fail(e.toString());
		}

		try {
			Cipher.getInstance("DES/CBC/IncorrectPadding", p2);
			fail("No excpected NoSuchProviderException");
		} catch (NoSuchAlgorithmException e) {
			fail(e.toString());
		} catch (NoSuchPaddingException e) {
		}
		
		try {
			Cipher.getInstance("DES/CBC/PKCS5Padding", "IncorrectProvider");
			fail("No excpected NoSuchProviderException");
		} catch (NoSuchAlgorithmException e) {
			fail(e.toString());
		} catch (NoSuchPaddingException e) {
			fail(e.toString());
		} catch (NoSuchProviderException e) {
		}		
	}

	/*
	 * Class under test for Cipher getInstance(String, Provider)
	 */
	public void testGetInstanceStringProvider2() {
		Cipher c = null;
		try {
			c = Cipher.getInstance("DES", p2);
		} catch (NoSuchAlgorithmException e) {
			fail(e.toString());
		} catch (NoSuchPaddingException e) {
			fail(e.toString());
		} 
		if (c.getProvider() != p2) {
			fail("Case2: getInstance() failed");
		}
		
		try {
			c = Cipher.getInstance("DES/CBC/PKCS5Padding", p2);
		} catch (NoSuchAlgorithmException e) {
			fail(e.toString());
		} catch (NoSuchPaddingException e) {
			fail(e.toString());
		} 
		if (c.getProvider() != p2) {
			fail("Case2: getInstance() failed");
		}	
		if ("DES".equals(c.getAlgorithm())) {
			fail("getAlgorithm() failed");
		}
	}

	public void testGetBlockSize() {
		Cipher c = null;
		try {
			c = Cipher.getInstance("DES");
		} catch (NoSuchAlgorithmException e) {
			fail(e.toString());
		} catch (NoSuchPaddingException e) {
			fail(e.toString());
		} 
		if (c.getBlockSize() != 111) {
			fail("getBlockSize() failed");
		}
	}

	public void testGetOutputSize() {
		Cipher c = null;
		try {
			c = Cipher.getInstance("DES");
		} catch (NoSuchAlgorithmException e) {
			fail(e.toString());
		} catch (NoSuchPaddingException e) {
			fail(e.toString());
		} 
		
		try {
			c.getOutputSize(111);
			fail("No excpected IllegalStateException");
		} catch (IllegalStateException e){
		}
		if (noKey) {
			return;
		}
		try {
			c.init(Cipher.DECRYPT_MODE, key);
		} catch (java.security.InvalidKeyException e) {
			fail(e.toString());
		}
		if (c.getOutputSize(111) != 121) {
			fail("getOutputSize() failed");
		}
	}

	public void testGetIV() {
		Cipher c = null;
		try {
			c = Cipher.getInstance("DES");
		} catch (NoSuchAlgorithmException e) {
			fail(e.toString());
		} catch (NoSuchPaddingException e) {
			fail(e.toString());
		} 
		if (c.getIV().length != 3) {
			fail("incorrect length");
		}
	}

	public void testGetParameters() {
		Cipher c = null;
		try {
			c = Cipher.getInstance("DES");
		} catch (NoSuchAlgorithmException e) {
			fail(e.toString());
		} catch (NoSuchPaddingException e) {
			fail(e.toString());
		} 
		if (c.getParameters() != null) {
			fail("getParameters() failed");
		}
	}

	public void testGetExemptionMechanism() {
//FIXME implement testGetExemptionMechanism
	}

	/*
	 * Class under test for void init(int, Key)
	 */
	public void testInitintKey() {
	}

	/*
	 * Class under test for void init(int, Key, SecureRandom)
	 */
	public void testInitintKeySecureRandom() {
	}

	/*
	 * Class under test for void init(int, Key, AlgorithmParameterSpec)
	 */
	public void testInitintKeyAlgorithmParameterSpec() {
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
		Cipher c = null;
		try {
			c = Cipher.getInstance("DES");
		} catch (NoSuchAlgorithmException e) {
			fail(e.toString());
		} catch (NoSuchPaddingException e) {
			fail(e.toString());
		}
		byte[] b = {1,2,3,4};
		try {
			c.update(b);
			fail("No excpected IllegalStateException");
		} catch (IllegalStateException e){
		}
		if (noKey) {
			return;
		}
		try {
			c.init(Cipher.DECRYPT_MODE, key);
		} catch (java.security.InvalidKeyException e) {
			fail(e.toString());
		}
		try {
			c.update(null);
			fail("No excpected IllegalArgumentException");
		} catch (IllegalArgumentException e){
		}
		if (c.update(new byte[0]) != null) {
			fail("Incorrect result");
		}
	}

	/*
	 * Class under test for byte[] update(byte[], int, int)
	 */
	public void testUpdatebyteArrayintint() {
	}

	/*
	 * Class under test for int update(byte[], int, int, byte[])
	 */
	public void testUpdatebyteArrayintintbyteArray() {
	}

	/*
	 * Class under test for int update(byte[], int, int, byte[], int)
	 */
	public void testUpdatebyteArrayintintbyteArrayint() {
//		try {
//			spi.engineUpdate(b, 3, 6, b1, 5);
//			fail("No expected ShortBufferException");
//		} catch (ShortBufferException e) {
//		}
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
	}

	/*
	 * Class under test for int doFinal(byte[], int)
	 */
	public void testDoFinalbyteArrayint() {
	}

	/*
	 * Class under test for byte[] doFinal(byte[])
	 */
	public void testDoFinalbyteArray() {
	}

	/*
	 * Class under test for byte[] doFinal(byte[], int, int)
	 */
	public void testDoFinalbyteArrayintint() {
	}

	/*
	 * Class under test for int doFinal(byte[], int, int, byte[])
	 */
	public void testDoFinalbyteArrayintintbyteArray() {
	}

	/*
	 * Class under test for int doFinal(byte[], int, int, byte[], int)
	 */
	public void testDoFinalbyteArrayintintbyteArrayint() {
//		try {
//			spi.engineDoFinal(b, 3, 6, b1, 5);
//			fail("No expected ShortBufferException");
//		} catch (ShortBufferException e) {
//		} catch (Exception e) {
//			fail(e.toString());
//		}
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

	public void testGetMaxAllowedKeyLength() {
		try {
			Cipher.getMaxAllowedKeyLength(null);
			fail("No expected NullPointerException");
		} catch (NullPointerException e) {
		} catch (NoSuchAlgorithmException e) {
			fail(e.toString());
		}
		try {
			Cipher.getMaxAllowedKeyLength("//CBC/PKCS5Paddin");
			fail("No expected NoSuchAlgorithmException");
		} catch (NoSuchAlgorithmException e) {
		}
		try {
			Cipher.getMaxAllowedKeyLength("/DES/CBC/PKCS5Paddin/1");
			fail("No expected NoSuchAlgorithmException");
		} catch (NoSuchAlgorithmException e) {
		}
	}

	public void testGetMaxAllowedParameterSpec() {
		try {
			Cipher.getMaxAllowedParameterSpec(null);
			fail("No expected NullPointerException");
		} catch (NullPointerException e) {
		} catch (NoSuchAlgorithmException e) {
			fail(e.toString());
		}
		try {
			Cipher.getMaxAllowedParameterSpec("/DES//PKCS5Paddin");
			fail("No expected NoSuchAlgorithmException");
		} catch (NoSuchAlgorithmException e) {
		}
		try {
			Cipher.getMaxAllowedParameterSpec("/DES/CBC/ /1");
			fail("No expected NoSuchAlgorithmException");
		} catch (NoSuchAlgorithmException e) {
		}
	}
	
	private class MyProvider1 extends Provider {
		MyProvider1() {
			super("MyProvider1", 1.0, "Provider1 for testing");
			put("Cipher.DES/CBC/PKCS5Padding", "javax.crypto.MyCipher");
			put("Cipher.DES/ECB/PKCS5Padding", "javax.crypto.MyCipher");
		}	
	}
	private class MyProvider2 extends Provider {
		MyProvider2() {
			super("MyProvider2", 1.0, "Provider2 for testing");
			put("Cipher.DES", "javax.crypto.MyCipher");
		}	
		
	}
}