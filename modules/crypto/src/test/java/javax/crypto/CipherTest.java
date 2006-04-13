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

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;

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
	public void testGetInstanceString1() throws NoSuchAlgorithmException,
            NoSuchPaddingException {
		
		Cipher c = Cipher.getInstance("DES");
		assertSame(p2, c.getProvider());
	}

	/*
	 * Class under test for Cipher getInstance(String)
	 */
	public void testGetInstanceString2() throws NoSuchAlgorithmException,
            NoSuchPaddingException {
		
		Cipher c = Cipher.getInstance("DES/CBC/PKCS5Padding");
		assertSame("Case1:", p1, c.getProvider());

		Security.removeProvider(p1.getName());

		c = Cipher.getInstance("DES/CBC/PKCS5Padding");
		assertSame("Case2:", p2, c.getProvider());
	}

	/*
	 * Class under test for Cipher getInstance(String)
	 */
	public void testGetInstanceString3() throws NoSuchAlgorithmException,
            NoSuchPaddingException {
		
		try {
			Cipher.getInstance("DES/CBC/");
			fail("Case1: No expected NoSuchAlgorithmException");
		} catch (NoSuchAlgorithmException e) {
		}

		try {
			Cipher.getInstance("DES//PKCS5Padding");
			fail("Case2: No expected NoSuchAlgorithmException");
		} catch (NoSuchAlgorithmException e) {
		}

		try {
			Cipher.getInstance("DES/CBC/IncorrectPadding");
			fail("No expected NoSuchPaddingException");
		} catch (NoSuchPaddingException e) {
		}
	}	

	
	/*
	 * Class under test for Cipher getInstance(String, String)
	 */
	public void testGetInstanceStringString1() throws NoSuchAlgorithmException,
            NoSuchProviderException, NoSuchPaddingException {

		try {
			Cipher.getInstance("DES/CBC/", "MyProvider2");
			fail("Case1: No expected NoSuchAlgorithmException");
		} catch (NoSuchAlgorithmException e) {
		}

		try {
			Cipher.getInstance("DES//PKCS5Padding", "MyProvider2");
			fail("Case2: No expected NoSuchAlgorithmException");
		} catch (NoSuchAlgorithmException e) {
		}

		try {
			Cipher.getInstance("DES/CBC/IncorrectPadding", "MyProvider2");
			fail("No expected NoSuchPaddingException");
		} catch (NoSuchPaddingException e) {
		}
		
		try {
			Cipher.getInstance("DES/CBC/PKCS5Padding", "IncorrectProvider");
			fail("No expected NoSuchProviderException");
		} catch (NoSuchProviderException e) {
		}		
	}

	/*
	 * Class under test for Cipher getInstance(String, String)
	 */
	public void testGetInstanceStringString2() throws NoSuchAlgorithmException,
            NoSuchProviderException, NoSuchPaddingException {

		Cipher c = Cipher.getInstance("DES", "MyProvider2");
		assertSame("Case1:", p2, c.getProvider());
		
		c = Cipher.getInstance("DES/CBC/PKCS5Padding", "MyProvider2");
		assertSame("Case2:", p2, c.getProvider());
	}
	/*
	 * Class under test for Cipher getInstance(String, Provider)
	 */
	public void testGetInstanceStringProvider1()
            throws NoSuchAlgorithmException, NoSuchPaddingException {

		try {
			Cipher.getInstance("DES/CBC/", p2);
			fail("Case1: No expected NoSuchAlgorithmException");
		} catch (NoSuchAlgorithmException e) {
		}

		try {
			Cipher.getInstance("DES//PKCS5Padding", p2);
			fail("Case2: No expected NoSuchAlgorithmException");
		} catch (NoSuchAlgorithmException e) {
		}

		try {
			Cipher.getInstance("DES/CBC/IncorrectPadding", p2);
			fail("No expected NoSuchProviderException");
		} catch (NoSuchPaddingException e) {
		}
		
		try {
			Cipher.getInstance("DES/CBC/PKCS5Padding", "IncorrectProvider");
			fail("No expected NoSuchProviderException");
		} catch (NoSuchProviderException e) {
		}		
	}

	/*
	 * Class under test for Cipher getInstance(String, Provider)
	 */
	public void testGetInstanceStringProvider2()
            throws NoSuchAlgorithmException, NoSuchPaddingException {

		Cipher c = Cipher.getInstance("DES", p2);
		assertSame("Case1:", p2, c.getProvider());
		
		c = Cipher.getInstance("DES/CBC/PKCS5Padding", p2);
		assertSame("Case2:", p2, c.getProvider());
		assertFalse("getAlgorithm", "DES".equals(c.getAlgorithm()));
	}

	public void testGetBlockSize() throws NoSuchAlgorithmException,
            NoSuchPaddingException {

		Cipher c = Cipher.getInstance("DES");
		assertEquals("getBlockSize", 111, c.getBlockSize());
	}

	public void testGetOutputSize() throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException {

		Cipher c = Cipher.getInstance("DES");
		try {
			c.getOutputSize(111);
			fail("No expected IllegalStateException");
		} catch (IllegalStateException e){
		}

		if (noKey) {
			return;
		}

		c.init(Cipher.DECRYPT_MODE, key);
		assertEquals("getOutputSize", 121, c.getOutputSize(111));
	}

	public void testGetIV() throws NoSuchAlgorithmException,
            NoSuchPaddingException {

		Cipher c = Cipher.getInstance("DES");
		assertEquals(3, c.getIV().length);
	}

	public void testGetParameters() throws NoSuchAlgorithmException,
            NoSuchPaddingException {

		Cipher c = Cipher.getInstance("DES");
		assertNull(c.getParameters());
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
	public void testUpdatebyteArray() throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException {

		Cipher c = Cipher.getInstance("DES");

		byte[] b = {1,2,3,4};
		try {
			c.update(b);
			fail("No expected IllegalStateException");
		} catch (IllegalStateException e){
		}
		if (noKey) {
			return;
		}

		c.init(Cipher.DECRYPT_MODE, key);
		try {
			c.update(null);
			fail("No expected IllegalArgumentException");
		} catch (IllegalArgumentException e){
		}
		assertNull(c.update(new byte[0]));
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

	public void testGetMaxAllowedKeyLength() throws NoSuchAlgorithmException {
		try {
			Cipher.getMaxAllowedKeyLength(null);
			fail("No expected NullPointerException");
		} catch (NullPointerException e) {
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

	public void testGetMaxAllowedParameterSpec()
            throws NoSuchAlgorithmException {
		try {
			Cipher.getMaxAllowedParameterSpec(null);
			fail("No expected NullPointerException");
		} catch (NullPointerException e) {
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