package ar.org.fitc.test.crypto.cipher;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;

import ar.org.fitc.test.util.K;
import ar.org.fitc.test.util.crypto.Util;

public class TestCipherDinamic extends TestCipher {
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestCipherDinamic.class);
	}
	
	public TestCipherDinamic(String name) {
		super(name);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	/*
	 * tests for 'javax.crypto.Cipher.getInstance(String)'
	 */
	public final void testGetInstanceString004() {
		try {
			cipher = Cipher.getInstance(transformation);
			assertNotNull(msgNotInstance + "Cipher",cipher);
		} catch (Throwable e) {
			fail(msgNoException);
		}
	}
	
	
	/*
	 * tests for 'javax.crypto.Cipher.getInstance(String, String)'
	 */
	public final void testGetInstanceStringString001() {
		try {
			cipher = Cipher.getInstance(transformation,providerString);
			assertNotNull(msgNotInstance + "Cipher",cipher);
		} catch (Throwable e) {
			fail(msgNoException);
		}
	}
	
	public final void testGetInstanceStringString002() {
		try {
			cipher = Cipher.getInstance("asdhg",providerString);
			fail("Should raise NoSuchAlgorithmException");
		} catch (NoSuchAlgorithmException e) {
		} catch (Throwable e) {
			fail("Should raise NoSuchAlgorithmException");
		}
	}
	
	public final void testGetInstanceStringString004() {
		
		try {
			cipher = Cipher.getInstance(transformation,"NotValidProvider");
			fail(msgNoSuchProvider);
		} catch (NoSuchProviderException e) {
		} catch (Throwable e) {
			fail(msgNoSuchProvider);
		}
	}
	
	public final void testGetInstanceStringString005() {
		try {
			cipher = Cipher.getInstance(transformation,(String)null);
			fail(msgIllegalArgument);
		} catch (IllegalArgumentException e) {
		} catch (Throwable e) {
			fail(msgIllegalArgument);
		}
	}
	
	
	/*
	 * tests for 'javax.crypto.Cipher.getInstance(String, Provider)'
	 */
	public final void testGetInstanceStringProvider001() {
		try {
			cipher = Cipher.getInstance(algorithm,provider);
			assertNotNull(msgNotInstance + "Cipher",cipher);
		} catch (Throwable e) {
			fail(msgNoException);
		}
	}
		
	/*
	 * Tests method for 'javax.crypto.Cipher.getProvider()'
	 */
	public final void testGetProvider001() {
		try {
			cipher = Cipher.getInstance(algorithm,provider);
			provider = cipher.getProvider();
			assertNotNull(msgNotInstance + "Provider",provider);
		} catch (Throwable e) {
			fail(msgNoException);
		}
	}
	
	public final void testGetProvider002() {
		try {
			cipher = Cipher.getInstance(algorithm,providerString);
			assertSame("Not the same provider",provider,cipher.getProvider());
		} catch (Throwable e) {
			fail(msgNoException);
		}
	}
	
	/*
	 * Tests for 'javax.crypto.Cipher.getAlgorithm()'
	 */
	public final void testGetAlgorithm001() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			assertEquals("Not the same algorithm",transformation,cipher.getAlgorithm());
		} catch (Throwable e) {
			fail(msgNoException);
		}
	}
	
	public final void testGetAlgorithm002() {
		try {
			cipher = Cipher.getInstance(algorithm,provider);
			assertEquals("Not the same algorithm",algorithm,cipher.getAlgorithm());
		} catch (Throwable e) {
			fail(msgNoException);
		}
	}
	
	/*
	 * Tests for 'javax.crypto.Cipher.getOutputSize(int)'
	 */
	
	public final void testGetOutputSize003() {
		try {
			cipher = Cipher.getInstance(algorithm,provider);
			cipher.getOutputSize(0);
			fail("Cipher not initialized");
		} catch (IllegalStateException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	
	public final void testGetOutputSize007() {
		try {
			cipher = Cipher.getInstance(algorithm,provider);
			cipher.getOutputSize(-1);
			fail("negative length");
		} catch (IllegalStateException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	
	/*
	 * Tests methods for 'javax.crypto.Cipher.getIV()'
	 */
	
	public final void testGetIV004() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			assertNull("Cipher not initialized", cipher.getIV());
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/*
	 * Test methods for 'javax.crypto.Cipher.getParameters()'
	 */
	public final void testGetParameters001() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			assertNull("Cipher not initialized", cipher.getParameters());
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
		
	/*
	 * Test methods for 'javax.crypto.Cipher.getExemptionMechanism()'
	 */
	public final void testGetExemptionMechanism001() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.ENCRYPT_MODE, Util.getInstanceKey(algorithm));
			assertNull("no ExemptionMechanism is used",cipher.getExemptionMechanism());
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKey002() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.WRAP_MODE, Util.getInstanceKey(algorithm));
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKey011() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.ENCRYPT_MODE, Util.getInstanceKey(algorithm));
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
		
	public final void testInitIntKey013() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(15, (Key) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKey014() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(15, Util.getInstanceKey(algorithm));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKey015() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(15, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeySecureRandom003() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.WRAP_MODE, Util.getInstanceKey(algorithm), (SecureRandom) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeySecureRandom004() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.WRAP_MODE, Util.getInstanceKey(algorithm), new SecureRandom());
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeySecureRandom021() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.ENCRYPT_MODE, Util.getInstanceKey(algorithm), (SecureRandom) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeySecureRandom022() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.ENCRYPT_MODE, Util.getInstanceKey(algorithm), new SecureRandom());
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeySecureRandom025() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(15, (Key) null, (SecureRandom) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeySecureRandom026() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(15, (Key) null, new SecureRandom());
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeySecureRandom027() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(15, Util.getInstanceKey(algorithm), (SecureRandom) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeySecureRandom028() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(15, Util.getInstanceKey(algorithm), new SecureRandom());
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeySecureRandom029() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(15, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), (SecureRandom) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeySecureRandom030() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(15, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), new SecureRandom());
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpec037() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(15, (Key) null, (AlgorithmParameterSpec) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
		
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom073() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(15, (Key) null, (AlgorithmParameterSpec) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom076() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(15, (Key) null, (AlgorithmParameterSpec) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testGetMaxAllowedKeyLength002() {
		try {
			assertTrue("Should be greater than zero",Cipher.getMaxAllowedKeyLength(algorithm) > 0);
		} catch (Throwable e) {
			
			fail(msgNoException);
		}
	}
	
	public final void testGetMaxAllowedKeyLength004() {
		try {
			Cipher.getMaxAllowedKeyLength(null);
			fail(msgRaise + "RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail(msgRaise + "RuntimeException");
		}
	}
	
}
