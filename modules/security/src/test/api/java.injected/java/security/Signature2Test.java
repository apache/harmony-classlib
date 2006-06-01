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
import org.apache.harmony.security.tests.support.MySignature2;

import junit.framework.TestCase;

/**
 * Tests for <code>Signature</code> constructor and methods
 * 
 */
public class Signature2Test extends TestCase {

	/**
	 * Provider
	 */
	Provider p;
	
	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		p = new MyProvider();
		Security.insertProviderAt(p, 1);
	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		Security.removeProvider(p.getName());
	}

	/*
	 * Class under test for Signature getInstance(String)
	 */
	public void testGetInstanceString1() {
		Signature sig = null;
		
		try {
			sig = Signature.getInstance("ABC");		
		} catch (NoSuchAlgorithmException e) {
			fail(e.toString());
		}
		checkSig1(sig, p);
	}
	
	/*
	 * Class under test for Signature getInstance(String)
	 */
	public void testGetInstanceString2() {
		Signature sig = null;
		
		try {
			sig = Signature.getInstance("CBA");
		} catch (NoSuchAlgorithmException e) {
			fail(e.toString());
		}
		checkSig2(sig, p);
	}

	/*
	 * Class under test for Signature getInstance(String, String)
	 */
	public void testGetInstanceStringString1() {
		Signature sig = null;
		
		try {
			sig = Signature.getInstance("ABC", "MyProvider");		
		} catch (NoSuchAlgorithmException e) {
			fail(e.toString());
		} catch (NoSuchProviderException e) {
			fail(e.toString());
		}
		checkSig1(sig, p);
	}
	
	/*
	 * Class under test for Signature getInstance(String, String)
	 */
	public void testGetInstanceStringString2() {
		Signature sig = null;
		
		try {
			sig = Signature.getInstance("CBA", "MyProvider");
		} catch (NoSuchAlgorithmException e) {
			fail(e.toString());
		} catch (NoSuchProviderException e) {
			fail(e.toString());
		}
		checkSig2(sig, p);
		
	}


	/*
	 * Class under test for Signature getInstance(String, Provider)
	 */
	public void testGetInstanceStringProvider1() {
		Provider p1 = new MyProvider();
		Signature sig = null;
		
		try {
			sig = Signature.getInstance("ABC", p1);		
		} catch (NoSuchAlgorithmException e) {
			fail(e.toString());
		}
		checkSig1(sig, p1);
	}
	
	/*
	 * Class under test for Signature getInstance(String, Provider)
	 */
	public void testGetInstanceStringProvider2() {
		Provider p2 = new MyProvider();
		Signature sig = null;
		
		try {
			sig = Signature.getInstance("CBA", p2);
		} catch (NoSuchAlgorithmException e) {
			fail(e.toString());
		}
		checkSig2(sig, p2);
	}

	private void checkSig1(Signature s, Provider p) {
		byte[] b = {1, 2, 3, 4};
		if (!(s instanceof MySignature1)) {
			fail("getInstance() failed");
		}
		if (s.getProvider() != p) {
			System.out.println(p);
			System.out.println(s.getProvider());
			fail("getProvider() failed");
		}

		if (!"ABC".equals(s.getAlgorithm())) {
			fail("getAlgorithm() failed");			
		}
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
		if (((MySignature1)s).getState() != Signature.SIGN) {
			fail("Incorrect state");
		}
		if (!((MySignature1)s).runEngineSign) {
			fail("sign() failed");
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
		if (((MySignature1)s).getState() != Signature.SIGN) {
			fail("Incorrect state");
		}
		if (!((MySignature1)s).runEngineUpdate1) {
			fail("update() failed");
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
		
		if (((MySignature1)s).getState() != Signature.VERIFY) {
			fail("Incorrect state");
		}
		if (!((MySignature1)s).runEngineVerify) {
			fail("verify() failed");
		}
	}
	
	private void checkSig2(Signature s, Provider p) {
		byte[] b = {1, 2, 3, 4};
		if (s.getProvider() != p) {
			fail("getProvider() failed");
		}
		if (!"CBA".equals(s.getAlgorithm())) {
			fail("getAlgorithm() failed");			
		}
		try {
			s.initVerify(new MyCertificate());
		} catch (InvalidKeyException e) {
			fail(e.toString());
		}
		try {
			s.sign(b, 0, 5);
			fail("No expected SignatureException 1");
		} catch (SignatureException e) {		
		}
		
		try {
			s.initSign(new MyPrivateKey());
		} catch (InvalidKeyException e) {
			fail(e.toString());
		}
		
		try {
			s.sign(b, 0, 3);
		} catch (SignatureException e) {
			fail(e.toString());
		}

		if (!MySignature2.runEngineSign) {
			fail("sign() failed");
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

		if (!MySignature2.runEngineUpdate2) {
			fail("update() failed");
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
		}
		
		try {
			s.verify(b, 0, 3);
		} catch (SignatureException e) {
			fail(e.toString());
		}
		
		if (!MySignature2.runEngineVerify) {
			fail("verify() failed");
		}
	}
	
	private class MyProvider extends Provider {
		MyProvider() {
			super("MyProvider", 1.0, "Provider for testing");
			put("Signature.ABC", "org.apache.harmony.security.tests.support.MySignature1");
			put("Signature.CBA", "org.apache.harmony.security.tests.support.MySignature2");
		}
		
		MyProvider(String name, double version, String info) {
			super(name, version, info);
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
