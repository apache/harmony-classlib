package ar.org.fitc.test.crypto.cipher;

import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.CipherSpi;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.RC2ParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import bwbunit.whitebox.PrivateProxy;
import bwbunit.whitebox.PrivateProxyFactory;

import ar.org.fitc.test.util.K;
import ar.org.fitc.test.util.Messages;
import ar.org.fitc.test.util.crypto.Util;

import junit.framework.TestCase;

@SuppressWarnings("unused")
public class TestCipherStatic extends TestCase implements Messages {
		
	protected Cipher cipher = null;
	protected Provider provider = null;
	protected String providerString = "BC";
	protected String algorithm = "DES";
	public String mode = "CBC";
	public String padding = "NoPadding";
	protected String transformation = algorithm + "/" + mode + "/" + padding;
	private Key key = null;
	private AlgorithmParameterGenerator aps = null;	
	private X509Certificate certificate = null;
	public byte[] input = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestCipherStatic.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		
		algorithm = "DES";
		mode = "CBC";
		padding = "NoPadding";
		providerString = "BC";
		transformation = algorithm + "/" + mode + "/" + padding;
		provider = Util.getInstanceProvider("BC");
		/*
		 cipher = Cipher.getInstance(transformation);
		 key = Util.getInstanceKey(algorithm);
		 
		 */
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	   public TestCipherStatic(String arg0) {
	        super(arg0);
	        
	    }
	public final void testGetInstanceString001() {
		try {
			cipher = Cipher.getInstance(null);
			fail("Should raise NoSuchAlgorithmException");
		} catch (NoSuchAlgorithmException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testGetInstanceString002() {
		try {
			cipher = Cipher.getInstance("asdaD");
			fail("Should raise NoSuchAlgorithmException");
		} catch (NoSuchAlgorithmException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	
	public final void testGetInstanceString003() {
		/*
		 * TODO figure out how to raise NoSuchPaddingException because its raising
		 *      NoSuchAlgorithmException probably an inconsistence from sun
		 */
		padding = "NoValidPadding";
		transformation = algorithm + "/" + mode + "/" + padding;
		try {
			cipher = Cipher.getInstance(transformation);
			fail("Should raise NoSuchAlgorithmException");
		} catch (NoSuchAlgorithmException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testGetInstanceString005() {
		try {
			transformation = mode + "/" + algorithm + "/" + padding;
			cipher = Cipher.getInstance(transformation);
			fail("Should raise NoSuchAlgorithmException");
		} catch (NoSuchAlgorithmException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testGetInstanceString006() {
		try {
			transformation = algorithm + "/" + "hooallo" + "/" + padding;
			cipher = Cipher.getInstance(transformation);
			fail("Should raise NoSuchAlgorithmException");
		} catch (NoSuchAlgorithmException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/*
	 * tests for 'javax.crypto.Cipher.getInstance(String, String)'
	 */
	public final void testGetInstanceStringString002() {
		try {
			cipher = Cipher.getInstance("asdhg",providerString);
			fail("Should raise NoSuchAlgorithmException");
		} catch (NoSuchAlgorithmException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testGetInstanceStringString003() {
		padding = "NoValidPadding";
		transformation = algorithm + "/" + mode + "/" + padding;
		providerString = "BC";
		try {
			cipher = Cipher.getInstance(transformation,providerString);
			fail("Should raise NoSuchPaddingException");
		} catch (NoSuchPaddingException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testGetInstanceStringString004() {
		providerString = "NotValidProvider";
		try {
			cipher = Cipher.getInstance(transformation,providerString);
			fail("Should raise NoSuchProviderException");
		} catch (NoSuchProviderException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testGetInstanceStringString005() {
		providerString = null;
		try {
			cipher = Cipher.getInstance(transformation,providerString);
			fail("Should raise IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/*
	 * tests for 'javax.crypto.Cipher.getInstance(String, Provider)'
	 */
	public final void testGetInstanceStringProvider002() {
		try {
			cipher = Cipher.getInstance(null,provider);
			assertNotNull(msgNotInstance + "Cipher",cipher);
			fail("Should raise NoSuchAlgorithmException");
		} catch (NoSuchAlgorithmException e) {
		} catch (Throwable e) {
			fail("Should raise NoSuchAlgorithmException");
		}
	}
	
	public final void testGetInstanceStringProvider003() {
		try {
			cipher = Cipher.getInstance("aadklj",provider);
			fail("Should raise NoSuchAlgorithmException");
		} catch (NoSuchAlgorithmException e) {
		} catch (Throwable e) {
			fail("Should raise NoSuchAlgorithmException");
		}
	}
	
	public final void testGetInstanceStringProvider004() {
		try {
			provider = null;
			cipher = Cipher.getInstance(algorithm,provider);
			fail(msgIllegalArgument);
		} catch (IllegalArgumentException e) {
		} catch (Throwable e) {
			fail(msgIllegalArgument);
		}
	}
	
	public final void testGetInstanceStringProvider005() {
		try {
			padding = "NOTBlockCipherPadding";
			transformation = algorithm + "/" + mode + "/" + padding;
			cipher = Cipher.getInstance(transformation,provider);
			fail(msgNoSuchPadding);
		} catch (NoSuchPaddingException e) {
		} catch (Throwable e) {
			fail(msgNoSuchPadding);
		}
		
	}
	/*
	 * Tests method for 'javax.crypto.Cipher.getProvider()'
	 */
	
	
	/*
	 * Tests for 'javax.crypto.Cipher.getBlockSize()'
	 */
	
	public final void testGetBlockSize002() {
		try {
			cipher = Cipher.getInstance("RC4","BC");
			cipher.init(Cipher.ENCRYPT_MODE, Util.getInstanceKey("RC4", "BC"));
			assertEquals("This cipher is not block Cipher", 0,cipher.getBlockSize());
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testGetBlockSize003() {
		try {
			cipher = Cipher.getInstance("DES","BC");
			cipher = Cipher.getInstance(algorithm,provider);
			cipher.init(Cipher.ENCRYPT_MODE, Util.getInstanceKey("DES"));
			assertNotSame("This cipher is not block Cipher", 0,cipher.getBlockSize());
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/*
	 * Tests for 'javax.crypto.Cipher.getOutputSize(int)'
	 */
	public final void testGetOutputSize001() {
		try {
			cipher = Cipher.getInstance("DES",provider);
			cipher.init(Cipher.ENCRYPT_MODE, Util.getInstanceKey("DES"));
			assertEquals("The less OutputSize is a BlockSize",cipher.getOutputSize(0), cipher.getBlockSize());
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testGetOutputSize002() {
		try {
			cipher = Cipher.getInstance("RC4","BC");
			cipher.init(Cipher.ENCRYPT_MODE, Util.getInstanceKey("RC4", "BC"));
			assertEquals("The less OutputSize is a BlockSize",cipher.getOutputSize(0), cipher.getBlockSize());
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testGetOutputSize004() {
		try {
			cipher = Cipher.getInstance("RC4","BC");
			cipher.init(Cipher.ENCRYPT_MODE, Util.getInstanceKey("RC4", "BC"));
			assertEquals("The stream cipher make getOutputSize a identity function",7, cipher.getOutputSize(7));
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testGetOutputSize005() {
		try {
			cipher = Cipher.getInstance("RC4","BC");
			cipher.init(Cipher.ENCRYPT_MODE, Util.getInstanceKey("RC4", "BC"));
			assertEquals("The stream cipher make getOutputSize a identity function",23, cipher.getOutputSize(23));
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testGetOutputSize006() {
		try {
			cipher = Cipher.getInstance("DES",provider);
			cipher.init(Cipher.ENCRYPT_MODE, Util.getInstanceKey("DES"));
			for (int i= cipher.getBlockSize()-1; i >= 0; i--) {
				assertSame("Not enough update to increase the block size  ", cipher.getBlockSize(), cipher.getOutputSize(i));
				cipher.update(new byte[] {1});
				assertNotSame("Enough update to increase the block size", cipher.getBlockSize(), cipher.getOutputSize(i));
				
			}
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	/*
	 * Tests methods for 'javax.crypto.Cipher.getIV()'
	 */
	public void testGetIV001() throws Exception {
		cipher = Cipher.getInstance("DES/CBC/NoPadding");
		byte[] iv = new byte[cipher.getBlockSize()];
		for (int i=iv.length;--i>= 0; iv[i] = (byte)i);
		IvParameterSpec params = new IvParameterSpec(iv);
		assertNull(cipher.getIV());
		cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(iv, "Des"), params);
		assertNotNull(cipher.getIV());
		assertTrue(Arrays.equals(cipher.getIV(), iv));
	}
	
	public void testGetIV002() throws Exception {
		cipher = Cipher.getInstance("RC2/CBC/NoPadding");
		byte[] iv = new byte[cipher.getBlockSize()];
		for (int i=iv.length;--i>= 0; iv[i] = (byte)i);
		RC2ParameterSpec params = new RC2ParameterSpec(4,iv);
		assertNull(cipher.getIV());
		cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(iv, "RC2"), params);
		assertNotNull(cipher.getIV());
		assertTrue(Arrays.equals(cipher.getIV(), iv));
	}

	public void testGetIV003() throws Exception {
		cipher = Cipher.getInstance("RC2/CBC/NoPadding");
		byte[] iv = new byte[cipher.getBlockSize()];
		for (int i=iv.length;--i>= 0; iv[i] = (byte)i);
		RC2ParameterSpec paramSpec = new RC2ParameterSpec(4,iv);
		AlgorithmParameters params = AlgorithmParameters.getInstance("RC2");
		params.init(paramSpec);
		assertNull(cipher.getIV());
		cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(iv, "RC2"), params);
		assertNotNull(cipher.getIV());
		assertTrue(Arrays.equals(cipher.getIV(), iv));
	}
	
	public void testGetIV004() throws Exception {
		Cipher cipher = Cipher.getInstance("RC2");
//		byte[] iv = new byte[cipher.getBlockSize()];
//		for (int i=iv.length;--i>= 0; iv[i] = (byte)i);
		RC2ParameterSpec params = new RC2ParameterSpec(12, new byte[] {1,2,3,4,5,6,7,8});
		assertNotNull(params.getIV());
		assertNull(cipher.getIV());
		try {
			cipher.init(Cipher.UNWRAP_MODE, new K("RC2", new byte[] {1,2,3,4,5,6,7,8}), params);
			fail("This algorithm cannot use IV");
		} catch (InvalidAlgorithmParameterException e) {
		}
//		assertNotNull(cipher.getIV());
//		assertTrue(Arrays.equals(cipher.getIV(), iv));
	}
	
	public void testGetIV005() throws Exception {
		
			cipher = Cipher.getInstance("RC2");
			byte[] iv = new byte[8];
			for (int i=iv.length;--i>= 0; iv[i] = (byte)i);
			
			RC2ParameterSpec ps = new RC2ParameterSpec(12, iv);
			assertNotNull(ps.getIV());
			//assertNull(cipher.getIV());
		try {
			cipher.init(Cipher.UNWRAP_MODE, new SecretKeySpec(iv, "RC2"), ps);
			fail("This algorithm cannot use IV");
		} catch (InvalidAlgorithmParameterException e) {
		}
	}
	public void testGetIV006() throws Exception {
		
			cipher = Cipher.getInstance("RC2");
			byte[] iv = new byte[] {1,2,3,4,5,6,7,8};

			RC2ParameterSpec ps = new RC2ParameterSpec(12, iv);
			AlgorithmParameters params = AlgorithmParameters.getInstance("RC2");
			params.init(ps);
			assertNotNull(ps.getIV());
		try {
			cipher.init(Cipher.UNWRAP_MODE, new SecretKeySpec(iv, "RC2"), params);
			fail("This algorithm cannot use IV");
		} catch (InvalidAlgorithmParameterException e) {
		}
	}


	public final void testGetIV007() {
		try {
			cipher = Cipher.getInstance("2.16.840.1.101.3.4.1.4", "BC");
			SecretKeySpec k = (SecretKeySpec) Util.getInstanceKey("2.16.840.1.101.3.4.1.4");
			
			cipher.init(Cipher.ENCRYPT_MODE, k);
			assertNotNull("This key has IV", cipher.getIV());
			
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testGetIV008() {
		try {
			cipher = Cipher.getInstance("DES",provider);
			cipher.init(Cipher.ENCRYPT_MODE, Util.getInstanceKey("DES"));
			assertNull("This key has not IV", cipher.getIV());
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/*
	 * Test methods for 'javax.crypto.Cipher.getParameters()'
	 */
	public final void testGetParameters005() {
		try {
			cipher = Cipher.getInstance("DES/CBC/NoPadding");
			cipher.init(Cipher.ENCRYPT_MODE, Util.getInstanceKey("DES"));
			assertNotNull("DES not use Parameters", cipher.getParameters());
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testGetParameters003() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.ENCRYPT_MODE, Util.getInstanceKey(algorithm));
			assertSame("May be same Parameters", cipher.getParameters(), cipher.getParameters());
			
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	
	/*
	 * Test methods for 'javax.crypto.Cipher.getExemptionMechanism()'
	 */
	/*
	 * Test methods for 'javax.crypto.Cipher.init(int, Key)'
	 */
	/*
	 * Test for 'javax.crypto.Cipher.init(int, Key, SecureRandom)'
	 */
	/*
	 * Test method for 'javax.crypto.Cipher.init(int, Key, AlgorithmParameters)'
	 */
	/*
	 * Test methods for 'javax.crypto.Cipher.init(int, Key, AlgorithmParameterSpec)'
	 */
	
	/*
	 * Test method for 'javax.crypto.Cipher.init(int, Key, AlgorithmParameterSpec)'
	 */
	
	public final void testInitIntKeyAlgorithmParametersSpec001() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.WRAP_MODE, (Key) null, (AlgorithmParameterSpec) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpec002() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.WRAP_MODE, (Key) null, new RC2ParameterSpec(12351));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpec003() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.WRAP_MODE, (Key) null, Cipher.getMaxAllowedParameterSpec("RC2"));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpec004() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.WRAP_MODE, Util.getInstanceKey("RC2"), (AlgorithmParameterSpec) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	
	public final void testInitIntKeyAlgorithmParametersSpec006() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.WRAP_MODE, Util.getInstanceKey("RC2"), Cipher.getMaxAllowedParameterSpec("RC2"));
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpec007() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.WRAP_MODE, new K("RC2", new byte[] {1,2,3,4,5,6,7,8}), (AlgorithmParameterSpec) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpec009() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.WRAP_MODE, new K("RC2", new byte[] {1,2,3,4,5,6,7,8}), Cipher.getMaxAllowedParameterSpec("RC2"));
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpec010() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.UNWRAP_MODE, (Key) null, (AlgorithmParameterSpec) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpec011() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.UNWRAP_MODE, (Key) null, new RC2ParameterSpec(12351));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpec012() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.UNWRAP_MODE, (Key) null, Cipher.getMaxAllowedParameterSpec("RC2"));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpec013() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.UNWRAP_MODE, Util.getInstanceKey("RC2"), (AlgorithmParameterSpec) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpec015() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.UNWRAP_MODE, Util.getInstanceKey("RC2"), Cipher.getMaxAllowedParameterSpec("RC2"));
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpec016() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.UNWRAP_MODE, new K("RC2", new byte[] {1,2,3,4,5,6,7,8}), (AlgorithmParameterSpec) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpec018() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.UNWRAP_MODE, new K("RC2", new byte[] {1,2,3,4,5,6,7,8}), Cipher.getMaxAllowedParameterSpec("RC2"));
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpec019() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.DECRYPT_MODE, (Key) null, (AlgorithmParameterSpec) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpec020() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.DECRYPT_MODE, (Key) null, new RC2ParameterSpec(12351));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpec021() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.DECRYPT_MODE, (Key) null, Cipher.getMaxAllowedParameterSpec("RC2"));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpec022() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.DECRYPT_MODE, Util.getInstanceKey("RC2"), (AlgorithmParameterSpec) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpec024() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.DECRYPT_MODE, Util.getInstanceKey("RC2"), Cipher.getMaxAllowedParameterSpec("RC2"));
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpec025() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.DECRYPT_MODE, new K("RC2", new byte[] {1,2,3,4,5,6,7,8}), (AlgorithmParameterSpec) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpec027() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.DECRYPT_MODE, new K("RC2", new byte[] {1,2,3,4,5,6,7,8}), Cipher.getMaxAllowedParameterSpec("RC2"));
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpec028() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.ENCRYPT_MODE, (Key) null, (AlgorithmParameterSpec) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpec029() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.ENCRYPT_MODE, (Key) null, new RC2ParameterSpec(12351));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpec030() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.ENCRYPT_MODE, (Key) null, Cipher.getMaxAllowedParameterSpec("RC2"));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpec031() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.ENCRYPT_MODE, Util.getInstanceKey("RC2"), (AlgorithmParameterSpec) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpec033() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.ENCRYPT_MODE, Util.getInstanceKey("RC2"), Cipher.getMaxAllowedParameterSpec("RC2"));
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpec034() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.ENCRYPT_MODE, new K("RC2", new byte[] {1,2,3,4,5,6,7,8}), (AlgorithmParameterSpec) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpec036() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.ENCRYPT_MODE, new K("RC2", new byte[] {1,2,3,4,5,6,7,8}), Cipher.getMaxAllowedParameterSpec("RC2"));
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpec038() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(15, (Key) null, new RC2ParameterSpec(12351));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpec039() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(15, (Key) null, Cipher.getMaxAllowedParameterSpec("RC2"));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpec040() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(15, Util.getInstanceKey("RC2"), (AlgorithmParameterSpec) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpec041() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(15, Util.getInstanceKey("RC2"), new RC2ParameterSpec(12351));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpec042() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(15, Util.getInstanceKey("RC2"), Cipher.getMaxAllowedParameterSpec("RC2"));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpec043() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(15, new K("RC2", new byte[] {1,2,3,4,5,6,7,8}), (AlgorithmParameterSpec) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpec044() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(15, new K("RC2", new byte[] {1,2,3,4,5,6,7,8}), new RC2ParameterSpec(12351));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpec045() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(15, new K("RC2", new byte[] {1,2,3,4,5,6,7,8}), Cipher.getMaxAllowedParameterSpec("RC2"));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	
	/*
	 * Test method for 'javax.crypto.Cipher.init(int, Key, AlgorithmParameterSpec, SecureRandom)'
	 */	
	
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom001() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.WRAP_MODE, (Key) null, (AlgorithmParameterSpec) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom002() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.WRAP_MODE, (Key) null, new RC2ParameterSpec(12351));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom003() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.WRAP_MODE, (Key) null, Cipher.getMaxAllowedParameterSpec("RC2"));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom004() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.WRAP_MODE, (Key) null, (AlgorithmParameterSpec) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom005() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.WRAP_MODE, (Key) null, new RC2ParameterSpec(12351));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom006() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.WRAP_MODE, (Key) null, Cipher.getMaxAllowedParameterSpec("RC2"));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom007() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.WRAP_MODE, Util.getInstanceKey("RC2"), (AlgorithmParameterSpec) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom009() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.WRAP_MODE, Util.getInstanceKey("RC2"), Cipher.getMaxAllowedParameterSpec("RC2"));
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom010() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.WRAP_MODE, Util.getInstanceKey("RC2"), (AlgorithmParameterSpec) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom012() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.WRAP_MODE, Util.getInstanceKey("RC2"), Cipher.getMaxAllowedParameterSpec("RC2"));
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom013() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.WRAP_MODE, new K("RC2", new byte[] {1,2,3,4,5,6,7,8}), (AlgorithmParameterSpec) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom015() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.WRAP_MODE, new K("RC2", new byte[] {1,2,3,4,5,6,7,8}), Cipher.getMaxAllowedParameterSpec("RC2"));
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom016() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.WRAP_MODE, new K("RC2", new byte[] {1,2,3,4,5,6,7,8}), (AlgorithmParameterSpec) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom018() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.WRAP_MODE, new K("RC2", new byte[] {1,2,3,4,5,6,7,8}), Cipher.getMaxAllowedParameterSpec("RC2"));
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom019() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.UNWRAP_MODE, (Key) null, (AlgorithmParameterSpec) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom020() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.UNWRAP_MODE, (Key) null, new RC2ParameterSpec(12351));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom021() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.UNWRAP_MODE, (Key) null, Cipher.getMaxAllowedParameterSpec("RC2"));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom022() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.UNWRAP_MODE, (Key) null, (AlgorithmParameterSpec) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom023() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.UNWRAP_MODE, (Key) null, new RC2ParameterSpec(12351));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom024() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.UNWRAP_MODE, (Key) null, Cipher.getMaxAllowedParameterSpec("RC2"));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom025() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.UNWRAP_MODE, Util.getInstanceKey("RC2"), (AlgorithmParameterSpec) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom027() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.UNWRAP_MODE, Util.getInstanceKey("RC2"), Cipher.getMaxAllowedParameterSpec("RC2"));
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom028() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.UNWRAP_MODE, Util.getInstanceKey("RC2"), (AlgorithmParameterSpec) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom030() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.UNWRAP_MODE, Util.getInstanceKey("RC2"), Cipher.getMaxAllowedParameterSpec("RC2"));
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom031() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.UNWRAP_MODE, new K("RC2", new byte[] {1,2,3,4,5,6,7,8}), (AlgorithmParameterSpec) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom033() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.UNWRAP_MODE, new K("RC2", new byte[] {1,2,3,4,5,6,7,8}), Cipher.getMaxAllowedParameterSpec("RC2"));
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom034() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.UNWRAP_MODE, new K("RC2", new byte[] {1,2,3,4,5,6,7,8}), (AlgorithmParameterSpec) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom036() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.UNWRAP_MODE, new K("RC2", new byte[] {1,2,3,4,5,6,7,8}), Cipher.getMaxAllowedParameterSpec("RC2"));
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom037() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.DECRYPT_MODE, (Key) null, (AlgorithmParameterSpec) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom038() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.DECRYPT_MODE, (Key) null, new RC2ParameterSpec(12351));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom039() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.DECRYPT_MODE, (Key) null, Cipher.getMaxAllowedParameterSpec("RC2"));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom040() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.DECRYPT_MODE, (Key) null, (AlgorithmParameterSpec) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom041() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.DECRYPT_MODE, (Key) null, new RC2ParameterSpec(12351));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom042() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.DECRYPT_MODE, (Key) null, Cipher.getMaxAllowedParameterSpec("RC2"));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom043() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.DECRYPT_MODE, Util.getInstanceKey("RC2"), (AlgorithmParameterSpec) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom045() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.DECRYPT_MODE, Util.getInstanceKey("RC2"), Cipher.getMaxAllowedParameterSpec("RC2"));
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom046() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.DECRYPT_MODE, Util.getInstanceKey("RC2"), (AlgorithmParameterSpec) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom048() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.DECRYPT_MODE, Util.getInstanceKey("RC2"), Cipher.getMaxAllowedParameterSpec("RC2"));
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom049() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.DECRYPT_MODE, new K("RC2", new byte[] {1,2,3,4,5,6,7,8}), (AlgorithmParameterSpec) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom051() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.DECRYPT_MODE, new K("RC2", new byte[] {1,2,3,4,5,6,7,8}), Cipher.getMaxAllowedParameterSpec("RC2"));
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom052() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.DECRYPT_MODE, new K("RC2", new byte[] {1,2,3,4,5,6,7,8}), (AlgorithmParameterSpec) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom054() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.DECRYPT_MODE, new K("RC2", new byte[] {1,2,3,4,5,6,7,8}), Cipher.getMaxAllowedParameterSpec("RC2"));
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom055() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.ENCRYPT_MODE, (Key) null, (AlgorithmParameterSpec) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom056() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.ENCRYPT_MODE, (Key) null, new RC2ParameterSpec(12351));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom057() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.ENCRYPT_MODE, (Key) null, Cipher.getMaxAllowedParameterSpec("RC2"));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom058() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.ENCRYPT_MODE, (Key) null, (AlgorithmParameterSpec) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom059() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.ENCRYPT_MODE, (Key) null, new RC2ParameterSpec(12351));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom060() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.ENCRYPT_MODE, (Key) null, Cipher.getMaxAllowedParameterSpec("RC2"));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom061() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.ENCRYPT_MODE, Util.getInstanceKey("RC2"), (AlgorithmParameterSpec) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom063() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.ENCRYPT_MODE, Util.getInstanceKey("RC2"), Cipher.getMaxAllowedParameterSpec("RC2"));
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom064() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.ENCRYPT_MODE, Util.getInstanceKey("RC2"), (AlgorithmParameterSpec) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom066() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.ENCRYPT_MODE, Util.getInstanceKey("RC2"), Cipher.getMaxAllowedParameterSpec("RC2"));
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom067() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.ENCRYPT_MODE, new K("RC2", new byte[] {1,2,3,4,5,6,7,8}), (AlgorithmParameterSpec) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom069() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.ENCRYPT_MODE, new K("RC2", new byte[] {1,2,3,4,5,6,7,8}), Cipher.getMaxAllowedParameterSpec("RC2"));
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom070() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.ENCRYPT_MODE, new K("RC2", new byte[] {1,2,3,4,5,6,7,8}), (AlgorithmParameterSpec) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom072() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(Cipher.ENCRYPT_MODE, new K("RC2", new byte[] {1,2,3,4,5,6,7,8}), Cipher.getMaxAllowedParameterSpec("RC2"));
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom074() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(15, (Key) null, new RC2ParameterSpec(12351));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom075() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(15, (Key) null, Cipher.getMaxAllowedParameterSpec("RC2"));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom077() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(15, (Key) null, new RC2ParameterSpec(12351));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom078() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(15, (Key) null, Cipher.getMaxAllowedParameterSpec("RC2"));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom079() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(15, Util.getInstanceKey("RC2"), (AlgorithmParameterSpec) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom080() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(15, Util.getInstanceKey("RC2"), new RC2ParameterSpec(12351));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom081() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(15, Util.getInstanceKey("RC2"), Cipher.getMaxAllowedParameterSpec("RC2"));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom082() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(15, Util.getInstanceKey("RC2"), (AlgorithmParameterSpec) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom083() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(15, Util.getInstanceKey("RC2"), new RC2ParameterSpec(12351));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom084() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(15, Util.getInstanceKey("RC2"), Cipher.getMaxAllowedParameterSpec("RC2"));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom085() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(15, new K("RC2", new byte[] {1,2,3,4,5,6,7,8}), (AlgorithmParameterSpec) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom086() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(15, new K("RC2", new byte[] {1,2,3,4,5,6,7,8}), new RC2ParameterSpec(12351));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom087() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(15, new K("RC2", new byte[] {1,2,3,4,5,6,7,8}), Cipher.getMaxAllowedParameterSpec("RC2"));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom088() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(15, new K("RC2", new byte[] {1,2,3,4,5,6,7,8}), (AlgorithmParameterSpec) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom089() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(15, new K("RC2", new byte[] {1,2,3,4,5,6,7,8}), new RC2ParameterSpec(12351));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeyAlgorithmParametersSpecSecureRandom090() {
		try {
			cipher = Cipher.getInstance("RC2",provider);
			cipher.init(15, new K("RC2", new byte[] {1,2,3,4,5,6,7,8}), Cipher.getMaxAllowedParameterSpec("RC2"));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/*
	 * Test method for 'javax.crypto.Cipher.update(byte[])'
	 */
	public final void testUpdateByteArray007() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			AlgorithmParameters al = null;
			al = AlgorithmParameters.getInstance(algorithm);
			al.init(new byte[] {4, 8, 109, -126, -97, 5, 56, -34, -19, 120});
			cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
			byte[] output = cipher.update(input);
			assertEquals("This is the result expected", 117, output[0]);
			assertEquals("This is the result expected", -8, output[1]);
			assertEquals("This is the result expected", 0, output[2]);
			assertEquals("This is the result expected", -55, output[3]);
			assertEquals("This is the result expected", -22, output[4]);
			assertEquals("This is the result expected", -58, output[5]);
			assertEquals("This is the result expected", 73, output[6]);
			assertEquals("This is the result expected", -91, output[7]);
			assertEquals("This is the result expected", 96, output[8]);
			assertEquals("This is the result expected", -28, output[9]);
			assertEquals("This is the result expected", 36, output[10]);
			assertEquals("This is the result expected", -57, output[11]);
			assertEquals("This is the result expected", -100, output[12]);
			assertEquals("This is the result expected", -38, output[13]);
			assertEquals("This is the result expected", -16, output[14]);
			assertEquals("This is the result expected", 92, output[15]);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testUpdateByteArray010() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			AlgorithmParameters al = null;
			al = AlgorithmParameters.getInstance(algorithm);
			al.init(new byte[] {4, 8, 109, -126, -97, 5, 56, -34, -19, 120});
			cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
			byte[] output = cipher.update(input);
			assertEquals("This is the result expected", -122, output[0]);
			assertEquals("This is the result expected", -124, output[1]);
			assertEquals("This is the result expected", -66, output[2]);
			assertEquals("This is the result expected", 46, output[3]);
			assertEquals("This is the result expected", 66, output[4]);
			assertEquals("This is the result expected", 70, output[5]);
			assertEquals("This is the result expected", 107, output[6]);
			assertEquals("This is the result expected", 20, output[7]);
			assertEquals("This is the result expected", 4, output[8]);
			assertEquals("This is the result expected", -92, output[9]);
			assertEquals("This is the result expected", 22, output[10]);
			assertEquals("This is the result expected", 31, output[11]);
			assertEquals("This is the result expected", 25, output[12]);
			assertEquals("This is the result expected", 6, output[13]);
			assertEquals("This is the result expected", -22, output[14]);
			assertEquals("This is the result expected", 91, output[15]);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	/*
	 * Test method for 'javax.crypto.Cipher.update(byte[], int, int)'
	 */
	public final void testUpdateByteArrayIntInt080() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			AlgorithmParameters al = null;
			al = AlgorithmParameters.getInstance(algorithm);
			al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
			cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
			byte[] output = cipher.update(input, 0, 8);
			assertEquals("This is the result expected", 101, output[0]);
			assertEquals("This is the result expected", 54, output[1]);
			assertEquals("This is the result expected", 42, output[2]);
			assertEquals("This is the result expected", -90, output[3]);
			assertEquals("This is the result expected", -105, output[4]);
			assertEquals("This is the result expected", 79, output[5]);
			assertEquals("This is the result expected", -55, output[6]);
			assertEquals("This is the result expected", 5, output[7]);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testUpdateByteArrayIntIntByteArray348() {
		try {
			byte[] output = new byte[20];
			cipher = Cipher.getInstance(transformation,provider);
			AlgorithmParameters al = null;
			al = AlgorithmParameters.getInstance(algorithm);
			al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
			cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
			int read = cipher.update(input, 0, 8, output);
			assertTrue("Mustn't read more than asked for", read <= 8);
			assertEquals("This is the result expected", -94, output[0]);
			assertEquals("This is the result expected", -102, output[1]);
			assertEquals("This is the result expected", 20, output[2]);
			assertEquals("This is the result expected", -118, output[3]);
			assertEquals("This is the result expected", -42, output[4]);
			assertEquals("This is the result expected", -104, output[5]);
			assertEquals("This is the result expected", -34, output[6]);
			assertEquals("This is the result expected", 87, output[7]);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/*
	 * Tests for 'javax.crypto.Cipher.getBlockSize()'
	 */
	public final void testGetBlockSize001() throws Exception {
		
		Provider.Service s = provider.getService("Cipher", algorithm);
		PrivateProxy cipherSpi = PrivateProxyFactory.createPrivateProxy((CipherSpi) s.newInstance(null));
		cipher = Cipher.getInstance(algorithm,provider);
		cipher.init(Cipher.ENCRYPT_MODE, Util.getInstanceKey());
		try {
			assertEquals("Must be same blockSize",cipherSpi.call("engineGetBlockSize", new Object[0]),cipher.getBlockSize());
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/*
	 * Test method for 'javax.crypto.Cipher.init(int, Key, AlgorithmParameters, SecureRandom)'
	 */
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom001</b>() Se pasa como parametros: Cipher.WRAP_MODE, nulo,  AlgorithmParameters nulo y nulo ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom001() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(Cipher.WRAP_MODE, k, (AlgorithmParameters) null, (SecureRandom) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom002</b>() Se pasa como parametros: Cipher.WRAP_MODE, nulo, una instancia de AlgorithmParameters y nulo ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom002() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(Cipher.WRAP_MODE, k, al, (SecureRandom) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom003</b>() Se pasa como parametros: Cipher.WRAP_MODE, nulo, una instancia no funcional y nulo ; se espera que arroje InvalidAlgorithmParameterException o RunTimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom003() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(Cipher.WRAP_MODE, k, AlgorithmParameters.getInstance(algorithm, providerString), (SecureRandom) null);
			fail("must throw InvalidAlgorithmParameterException o RuntimeException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom004</b>() Se pasa como parametros: Cipher.WRAP_MODE, nulo,  AlgorithmParameters nulo y una instancia del SercureRandom ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom004() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(Cipher.WRAP_MODE, k, (AlgorithmParameters) null, new SecureRandom());
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom005</b>() Se pasa como parametros: Cipher.WRAP_MODE, nulo, una instancia de AlgorithmParameters y una instancia del SercureRandom ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom005() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(Cipher.WRAP_MODE, k, al, new SecureRandom());
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom006</b>() Se pasa como parametros: Cipher.WRAP_MODE, nulo, una instancia no funcional y una instancia del SercureRandom ; se espera que arroje InvalidAlgorithmParameterException o RunTimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom006() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(Cipher.WRAP_MODE, k, AlgorithmParameters.getInstance(algorithm, providerString), new SecureRandom());
			fail("must throw InvalidAlgorithmParameterException o RuntimeException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom007</b>() Se pasa como parametros: Cipher.WRAP_MODE, KeyGenertor of the algorithm set in cipher,  AlgorithmParameters nulo y nulo ; se espera que termine normalmente (el cipher quede inicialisado).</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom007() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(Cipher.WRAP_MODE, k, (AlgorithmParameters) null, (SecureRandom) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom008</b>() Se pasa como parametros: Cipher.WRAP_MODE, KeyGenertor of the algorithm set in cipher, una instancia de AlgorithmParameters y nulo ; se espera que termine normalmente (el cipher quede inicialisado).</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom008() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(Cipher.WRAP_MODE, k, al, (SecureRandom) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom009</b>() Se pasa como parametros: Cipher.WRAP_MODE, KeyGenertor of the algorithm set in cipher, una instancia no funcional y nulo ; se espera que arroje InvalidAlgorithmParameterException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom009() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(Cipher.WRAP_MODE, k, AlgorithmParameters.getInstance(algorithm, providerString), (SecureRandom) null);
			fail("must throw InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom010</b>() Se pasa como parametros: Cipher.WRAP_MODE, KeyGenertor of the algorithm set in cipher,  AlgorithmParameters nulo y una instancia del SercureRandom ; se espera que termine normalmente (el cipher quede inicialisado).</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom010() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(Cipher.WRAP_MODE, k, (AlgorithmParameters) null, new SecureRandom());
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom011</b>() Se pasa como parametros: Cipher.WRAP_MODE, KeyGenertor of the algorithm set in cipher, una instancia de AlgorithmParameters y una instancia del SercureRandom ; se espera que termine normalmente (el cipher quede inicialisado).</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom011() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(Cipher.WRAP_MODE, k, al, new SecureRandom());
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom012</b>() Se pasa como parametros: Cipher.WRAP_MODE, KeyGenertor of the algorithm set in cipher, una instancia no funcional y una instancia del SercureRandom ; se espera que arroje InvalidAlgorithmParameterException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom012() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(Cipher.WRAP_MODE, k, AlgorithmParameters.getInstance(algorithm, providerString), new SecureRandom());
			fail("must throw InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom013</b>() Se pasa como parametros: Cipher.WRAP_MODE, own key,  AlgorithmParameters nulo y nulo ; se espera que termine normalmente (el cipher quede inicialisado).</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom013() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(Cipher.WRAP_MODE, k, (AlgorithmParameters) null, (SecureRandom) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom014</b>() Se pasa como parametros: Cipher.WRAP_MODE, own key, una instancia de AlgorithmParameters y nulo ; se espera que termine normalmente (el cipher quede inicialisado).</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom014() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(Cipher.WRAP_MODE, k, al, (SecureRandom) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom015</b>() Se pasa como parametros: Cipher.WRAP_MODE, own key, una instancia no funcional y nulo ; se espera que arroje InvalidAlgorithmParameterException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom015() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(Cipher.WRAP_MODE, k, AlgorithmParameters.getInstance(algorithm, providerString), (SecureRandom) null);
			fail("must throw InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom016</b>() Se pasa como parametros: Cipher.WRAP_MODE, own key,  AlgorithmParameters nulo y una instancia del SercureRandom ; se espera que termine normalmente (el cipher quede inicialisado).</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom016() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(Cipher.WRAP_MODE, k, (AlgorithmParameters) null, new SecureRandom());
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom017</b>() Se pasa como parametros: Cipher.WRAP_MODE, own key, una instancia de AlgorithmParameters y una instancia del SercureRandom ; se espera que termine normalmente (el cipher quede inicialisado).</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom017() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(Cipher.WRAP_MODE, k, al, new SecureRandom());
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom018</b>() Se pasa como parametros: Cipher.WRAP_MODE, own key, una instancia no funcional y una instancia del SercureRandom ; se espera que arroje InvalidAlgorithmParameterException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom018() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(Cipher.WRAP_MODE, k, AlgorithmParameters.getInstance(algorithm, providerString), new SecureRandom());
			fail("must throw InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom019</b>() Se pasa como parametros: Cipher.UNWRAP_MODE, nulo,  AlgorithmParameters nulo y nulo ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom019() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(Cipher.UNWRAP_MODE, k, (AlgorithmParameters) null, (SecureRandom) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom020</b>() Se pasa como parametros: Cipher.UNWRAP_MODE, nulo, una instancia de AlgorithmParameters y nulo ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom020() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(Cipher.UNWRAP_MODE, k, al, (SecureRandom) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom021</b>() Se pasa como parametros: Cipher.UNWRAP_MODE, nulo, una instancia no funcional y nulo ; se espera que arroje InvalidAlgorithmParameterException o RunTimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom021() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(Cipher.UNWRAP_MODE, k, AlgorithmParameters.getInstance(algorithm, providerString), (SecureRandom) null);
			fail("must throw InvalidAlgorithmParameterException o RuntimeException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom022</b>() Se pasa como parametros: Cipher.UNWRAP_MODE, nulo,  AlgorithmParameters nulo y una instancia del SercureRandom ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom022() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(Cipher.UNWRAP_MODE, k, (AlgorithmParameters) null, new SecureRandom());
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom023</b>() Se pasa como parametros: Cipher.UNWRAP_MODE, nulo, una instancia de AlgorithmParameters y una instancia del SercureRandom ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom023() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(Cipher.UNWRAP_MODE, k, al, new SecureRandom());
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom024</b>() Se pasa como parametros: Cipher.UNWRAP_MODE, nulo, una instancia no funcional y una instancia del SercureRandom ; se espera que arroje InvalidAlgorithmParameterException o RunTimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom024() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(Cipher.UNWRAP_MODE, k, AlgorithmParameters.getInstance(algorithm, providerString), new SecureRandom());
			fail("must throw InvalidAlgorithmParameterException o RuntimeException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom025</b>() Se pasa como parametros: Cipher.UNWRAP_MODE, KeyGenertor of the algorithm set in cipher,  AlgorithmParameters nulo y nulo ; se espera que arroje InvalidAlgorithmParameterException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom025() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(Cipher.UNWRAP_MODE, k, (AlgorithmParameters) null, (SecureRandom) null);
			fail("must throw InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom026</b>() Se pasa como parametros: Cipher.UNWRAP_MODE, KeyGenertor of the algorithm set in cipher, una instancia de AlgorithmParameters y nulo ; se espera que termine normalmente (el cipher quede inicialisado).</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom026() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(Cipher.UNWRAP_MODE, k, al, (SecureRandom) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom027</b>() Se pasa como parametros: Cipher.UNWRAP_MODE, KeyGenertor of the algorithm set in cipher, una instancia no funcional y nulo ; se espera que arroje InvalidAlgorithmParameterException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom027() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(Cipher.UNWRAP_MODE, k, AlgorithmParameters.getInstance(algorithm, providerString), (SecureRandom) null);
			fail("must throw InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom028</b>() Se pasa como parametros: Cipher.UNWRAP_MODE, KeyGenertor of the algorithm set in cipher,  AlgorithmParameters nulo y una instancia del SercureRandom ; se espera que arroje InvalidAlgorithmParameterException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom028() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(Cipher.UNWRAP_MODE, k, (AlgorithmParameters) null, new SecureRandom());
			fail("must throw InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom029</b>() Se pasa como parametros: Cipher.UNWRAP_MODE, KeyGenertor of the algorithm set in cipher, una instancia de AlgorithmParameters y una instancia del SercureRandom ; se espera que termine normalmente (el cipher quede inicialisado).</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom029() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(Cipher.UNWRAP_MODE, k, al, new SecureRandom());
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom030</b>() Se pasa como parametros: Cipher.UNWRAP_MODE, KeyGenertor of the algorithm set in cipher, una instancia no funcional y una instancia del SercureRandom ; se espera que arroje InvalidAlgorithmParameterException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom030() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(Cipher.UNWRAP_MODE, k, AlgorithmParameters.getInstance(algorithm, providerString), new SecureRandom());
			fail("must throw InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom031</b>() Se pasa como parametros: Cipher.UNWRAP_MODE, own key,  AlgorithmParameters nulo y nulo ; se espera que arroje InvalidAlgorithmParameterException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom031() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(Cipher.UNWRAP_MODE, k, (AlgorithmParameters) null, (SecureRandom) null);
			fail("must throw InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom032</b>() Se pasa como parametros: Cipher.UNWRAP_MODE, own key, una instancia de AlgorithmParameters y nulo ; se espera que termine normalmente (el cipher quede inicialisado).</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom032() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(Cipher.UNWRAP_MODE, k, al, (SecureRandom) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom033</b>() Se pasa como parametros: Cipher.UNWRAP_MODE, own key, una instancia no funcional y nulo ; se espera que arroje InvalidAlgorithmParameterException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom033() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(Cipher.UNWRAP_MODE, k, AlgorithmParameters.getInstance(algorithm, providerString), (SecureRandom) null);
			fail("must throw InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom034</b>() Se pasa como parametros: Cipher.UNWRAP_MODE, own key,  AlgorithmParameters nulo y una instancia del SercureRandom ; se espera que arroje InvalidAlgorithmParameterException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom034() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(Cipher.UNWRAP_MODE, k, (AlgorithmParameters) null, new SecureRandom());
			fail("must throw InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom035</b>() Se pasa como parametros: Cipher.UNWRAP_MODE, own key, una instancia de AlgorithmParameters y una instancia del SercureRandom ; se espera que termine normalmente (el cipher quede inicialisado).</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom035() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(Cipher.UNWRAP_MODE, k, al, new SecureRandom());
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom036</b>() Se pasa como parametros: Cipher.UNWRAP_MODE, own key, una instancia no funcional y una instancia del SercureRandom ; se espera que arroje InvalidAlgorithmParameterException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom036() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(Cipher.UNWRAP_MODE, k, AlgorithmParameters.getInstance(algorithm, providerString), new SecureRandom());
			fail("must throw InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom037</b>() Se pasa como parametros: Cipher.DECRYPT_MODE, nulo,  AlgorithmParameters nulo y nulo ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom037() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(Cipher.DECRYPT_MODE, k, (AlgorithmParameters) null, (SecureRandom) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom038</b>() Se pasa como parametros: Cipher.DECRYPT_MODE, nulo, una instancia de AlgorithmParameters y nulo ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom038() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(Cipher.DECRYPT_MODE, k, al, (SecureRandom) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom039</b>() Se pasa como parametros: Cipher.DECRYPT_MODE, nulo, una instancia no funcional y nulo ; se espera que arroje InvalidAlgorithmParameterException o RunTimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom039() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(Cipher.DECRYPT_MODE, k, AlgorithmParameters.getInstance(algorithm, providerString), (SecureRandom) null);
			fail("must throw InvalidAlgorithmParameterException o RuntimeException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom040</b>() Se pasa como parametros: Cipher.DECRYPT_MODE, nulo,  AlgorithmParameters nulo y una instancia del SercureRandom ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom040() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(Cipher.DECRYPT_MODE, k, (AlgorithmParameters) null, new SecureRandom());
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom041</b>() Se pasa como parametros: Cipher.DECRYPT_MODE, nulo, una instancia de AlgorithmParameters y una instancia del SercureRandom ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom041() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(Cipher.DECRYPT_MODE, k, al, new SecureRandom());
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom042</b>() Se pasa como parametros: Cipher.DECRYPT_MODE, nulo, una instancia no funcional y una instancia del SercureRandom ; se espera que arroje InvalidAlgorithmParameterException o RunTimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom042() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(Cipher.DECRYPT_MODE, k, AlgorithmParameters.getInstance(algorithm, providerString), new SecureRandom());
			fail("must throw InvalidAlgorithmParameterException o RuntimeException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom043</b>() Se pasa como parametros: Cipher.DECRYPT_MODE, KeyGenertor of the algorithm set in cipher,  AlgorithmParameters nulo y nulo ; se espera que arroje InvalidAlgorithmParameterException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom043() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(Cipher.DECRYPT_MODE, k, (AlgorithmParameters) null, (SecureRandom) null);
			fail("must throw InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom044</b>() Se pasa como parametros: Cipher.DECRYPT_MODE, KeyGenertor of the algorithm set in cipher, una instancia de AlgorithmParameters y nulo ; se espera que termine normalmente (el cipher quede inicialisado).</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom044() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(Cipher.DECRYPT_MODE, k, al, (SecureRandom) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom045</b>() Se pasa como parametros: Cipher.DECRYPT_MODE, KeyGenertor of the algorithm set in cipher, una instancia no funcional y nulo ; se espera que arroje InvalidAlgorithmParameterException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom045() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(Cipher.DECRYPT_MODE, k, AlgorithmParameters.getInstance(algorithm, providerString), (SecureRandom) null);
			fail("must throw InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom046</b>() Se pasa como parametros: Cipher.DECRYPT_MODE, KeyGenertor of the algorithm set in cipher,  AlgorithmParameters nulo y una instancia del SercureRandom ; se espera que arroje InvalidAlgorithmParameterException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom046() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(Cipher.DECRYPT_MODE, k, (AlgorithmParameters) null, new SecureRandom());
			fail("must throw InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom047</b>() Se pasa como parametros: Cipher.DECRYPT_MODE, KeyGenertor of the algorithm set in cipher, una instancia de AlgorithmParameters y una instancia del SercureRandom ; se espera que termine normalmente (el cipher quede inicialisado).</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom047() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(Cipher.DECRYPT_MODE, k, al, new SecureRandom());
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom048</b>() Se pasa como parametros: Cipher.DECRYPT_MODE, KeyGenertor of the algorithm set in cipher, una instancia no funcional y una instancia del SercureRandom ; se espera que arroje InvalidAlgorithmParameterException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom048() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(Cipher.DECRYPT_MODE, k, AlgorithmParameters.getInstance(algorithm, providerString), new SecureRandom());
			fail("must throw InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom049</b>() Se pasa como parametros: Cipher.DECRYPT_MODE, own key,  AlgorithmParameters nulo y nulo ; se espera que arroje InvalidAlgorithmParameterException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom049() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(Cipher.DECRYPT_MODE, k, (AlgorithmParameters) null, (SecureRandom) null);
			fail("must throw InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom050</b>() Se pasa como parametros: Cipher.DECRYPT_MODE, own key, una instancia de AlgorithmParameters y nulo ; se espera que termine normalmente (el cipher quede inicialisado).</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom050() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(Cipher.DECRYPT_MODE, k, al, (SecureRandom) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom051</b>() Se pasa como parametros: Cipher.DECRYPT_MODE, own key, una instancia no funcional y nulo ; se espera que arroje InvalidAlgorithmParameterException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom051() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(Cipher.DECRYPT_MODE, k, AlgorithmParameters.getInstance(algorithm, providerString), (SecureRandom) null);
			fail("must throw InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom052</b>() Se pasa como parametros: Cipher.DECRYPT_MODE, own key,  AlgorithmParameters nulo y una instancia del SercureRandom ; se espera que arroje InvalidAlgorithmParameterException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom052() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(Cipher.DECRYPT_MODE, k, (AlgorithmParameters) null, new SecureRandom());
			fail("must throw InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom053</b>() Se pasa como parametros: Cipher.DECRYPT_MODE, own key, una instancia de AlgorithmParameters y una instancia del SercureRandom ; se espera que termine normalmente (el cipher quede inicialisado).</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom053() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(Cipher.DECRYPT_MODE, k, al, new SecureRandom());
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom054</b>() Se pasa como parametros: Cipher.DECRYPT_MODE, own key, una instancia no funcional y una instancia del SercureRandom ; se espera que arroje InvalidAlgorithmParameterException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom054() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(Cipher.DECRYPT_MODE, k, AlgorithmParameters.getInstance(algorithm, providerString), new SecureRandom());
			fail("must throw InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom055</b>() Se pasa como parametros: Cipher.ENCRYPT_MODE, nulo,  AlgorithmParameters nulo y nulo ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom055() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(Cipher.ENCRYPT_MODE, k, (AlgorithmParameters) null, (SecureRandom) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom056</b>() Se pasa como parametros: Cipher.ENCRYPT_MODE, nulo, una instancia de AlgorithmParameters y nulo ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom056() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(Cipher.ENCRYPT_MODE, k, al, (SecureRandom) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom057</b>() Se pasa como parametros: Cipher.ENCRYPT_MODE, nulo, una instancia no funcional y nulo ; se espera que arroje InvalidAlgorithmParameterException o RunTimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom057() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(Cipher.ENCRYPT_MODE, k, AlgorithmParameters.getInstance(algorithm, providerString), (SecureRandom) null);
			fail("must throw InvalidAlgorithmParameterException o RuntimeException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom058</b>() Se pasa como parametros: Cipher.ENCRYPT_MODE, nulo,  AlgorithmParameters nulo y una instancia del SercureRandom ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom058() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(Cipher.ENCRYPT_MODE, k, (AlgorithmParameters) null, new SecureRandom());
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom059</b>() Se pasa como parametros: Cipher.ENCRYPT_MODE, nulo, una instancia de AlgorithmParameters y una instancia del SercureRandom ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom059() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(Cipher.ENCRYPT_MODE, k, al, new SecureRandom());
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom060</b>() Se pasa como parametros: Cipher.ENCRYPT_MODE, nulo, una instancia no funcional y una instancia del SercureRandom ; se espera que arroje InvalidAlgorithmParameterException o RunTimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom060() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(Cipher.ENCRYPT_MODE, k, AlgorithmParameters.getInstance(algorithm, providerString), new SecureRandom());
			fail("must throw InvalidAlgorithmParameterException o RuntimeException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom061</b>() Se pasa como parametros: Cipher.ENCRYPT_MODE, KeyGenertor of the algorithm set in cipher,  AlgorithmParameters nulo y nulo ; se espera que termine normalmente (el cipher quede inicialisado).</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom061() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(Cipher.ENCRYPT_MODE, k, (AlgorithmParameters) null, (SecureRandom) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom062</b>() Se pasa como parametros: Cipher.ENCRYPT_MODE, KeyGenertor of the algorithm set in cipher, una instancia de AlgorithmParameters y nulo ; se espera que termine normalmente (el cipher quede inicialisado).</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom062() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(Cipher.ENCRYPT_MODE, k, al, (SecureRandom) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom063</b>() Se pasa como parametros: Cipher.ENCRYPT_MODE, KeyGenertor of the algorithm set in cipher, una instancia no funcional y nulo ; se espera que arroje InvalidAlgorithmParameterException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom063() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(Cipher.ENCRYPT_MODE, k, AlgorithmParameters.getInstance(algorithm, providerString), (SecureRandom) null);
			fail("must throw InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom064</b>() Se pasa como parametros: Cipher.ENCRYPT_MODE, KeyGenertor of the algorithm set in cipher,  AlgorithmParameters nulo y una instancia del SercureRandom ; se espera que termine normalmente (el cipher quede inicialisado).</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom064() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(Cipher.ENCRYPT_MODE, k, (AlgorithmParameters) null, new SecureRandom());
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom065</b>() Se pasa como parametros: Cipher.ENCRYPT_MODE, KeyGenertor of the algorithm set in cipher, una instancia de AlgorithmParameters y una instancia del SercureRandom ; se espera que termine normalmente (el cipher quede inicialisado).</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom065() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(Cipher.ENCRYPT_MODE, k, al, new SecureRandom());
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom066</b>() Se pasa como parametros: Cipher.ENCRYPT_MODE, KeyGenertor of the algorithm set in cipher, una instancia no funcional y una instancia del SercureRandom ; se espera que arroje InvalidAlgorithmParameterException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom066() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(Cipher.ENCRYPT_MODE, k, AlgorithmParameters.getInstance(algorithm, providerString), new SecureRandom());
			fail("must throw InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom067</b>() Se pasa como parametros: Cipher.ENCRYPT_MODE, own key,  AlgorithmParameters nulo y nulo ; se espera que termine normalmente (el cipher quede inicialisado).</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom067() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(Cipher.ENCRYPT_MODE, k, (AlgorithmParameters) null, (SecureRandom) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom068</b>() Se pasa como parametros: Cipher.ENCRYPT_MODE, own key, una instancia de AlgorithmParameters y nulo ; se espera que termine normalmente (el cipher quede inicialisado).</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom068() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(Cipher.ENCRYPT_MODE, k, al, (SecureRandom) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom069</b>() Se pasa como parametros: Cipher.ENCRYPT_MODE, own key, una instancia no funcional y nulo ; se espera que arroje InvalidAlgorithmParameterException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom069() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(Cipher.ENCRYPT_MODE, k, AlgorithmParameters.getInstance(algorithm, providerString), (SecureRandom) null);
			fail("must throw InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom070</b>() Se pasa como parametros: Cipher.ENCRYPT_MODE, own key,  AlgorithmParameters nulo y una instancia del SercureRandom ; se espera que termine normalmente (el cipher quede inicialisado).</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom070() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(Cipher.ENCRYPT_MODE, k, (AlgorithmParameters) null, new SecureRandom());
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom071</b>() Se pasa como parametros: Cipher.ENCRYPT_MODE, own key, una instancia de AlgorithmParameters y una instancia del SercureRandom ; se espera que termine normalmente (el cipher quede inicialisado).</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom071() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(Cipher.ENCRYPT_MODE, k, al, new SecureRandom());
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom072</b>() Se pasa como parametros: Cipher.ENCRYPT_MODE, own key, una instancia no funcional y una instancia del SercureRandom ; se espera que arroje InvalidAlgorithmParameterException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom072() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(Cipher.ENCRYPT_MODE, k, AlgorithmParameters.getInstance(algorithm, providerString), new SecureRandom());
			fail("must throw InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom073</b>() Se pasa como parametros: 15, nulo,  AlgorithmParameters nulo y nulo ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom073() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(15, k, (AlgorithmParameters) null, (SecureRandom) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom074</b>() Se pasa como parametros: 15, nulo, una instancia de AlgorithmParameters y nulo ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom074() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(15, k, al, (SecureRandom) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom075</b>() Se pasa como parametros: 15, nulo, una instancia no funcional y nulo ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom075() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(15, k, AlgorithmParameters.getInstance(algorithm, providerString), (SecureRandom) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom076</b>() Se pasa como parametros: 15, nulo,  AlgorithmParameters nulo y una instancia del SercureRandom ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom076() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(15, k, (AlgorithmParameters) null, new SecureRandom());
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom077</b>() Se pasa como parametros: 15, nulo, una instancia de AlgorithmParameters y una instancia del SercureRandom ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom077() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(15, k, al, new SecureRandom());
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom078</b>() Se pasa como parametros: 15, nulo, una instancia no funcional y una instancia del SercureRandom ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom078() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(15, k, AlgorithmParameters.getInstance(algorithm, providerString), new SecureRandom());
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom079</b>() Se pasa como parametros: 15, KeyGenertor of the algorithm set in cipher,  AlgorithmParameters nulo y nulo ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom079() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(15, k, (AlgorithmParameters) null, (SecureRandom) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom080</b>() Se pasa como parametros: 15, KeyGenertor of the algorithm set in cipher, una instancia de AlgorithmParameters y nulo ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom080() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(15, k, al, (SecureRandom) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom081</b>() Se pasa como parametros: 15, KeyGenertor of the algorithm set in cipher, una instancia no funcional y nulo ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom081() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(15, k, AlgorithmParameters.getInstance(algorithm, providerString), (SecureRandom) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom082</b>() Se pasa como parametros: 15, KeyGenertor of the algorithm set in cipher,  AlgorithmParameters nulo y una instancia del SercureRandom ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom082() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(15, k, (AlgorithmParameters) null, new SecureRandom());
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom083</b>() Se pasa como parametros: 15, KeyGenertor of the algorithm set in cipher, una instancia de AlgorithmParameters y una instancia del SercureRandom ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom083() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(15, k, al, new SecureRandom());
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom084</b>() Se pasa como parametros: 15, KeyGenertor of the algorithm set in cipher, una instancia no funcional y una instancia del SercureRandom ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom084() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(15, k, AlgorithmParameters.getInstance(algorithm, providerString), new SecureRandom());
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom085</b>() Se pasa como parametros: 15, own key,  AlgorithmParameters nulo y nulo ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom085() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(15, k, (AlgorithmParameters) null, (SecureRandom) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom086</b>() Se pasa como parametros: 15, own key, una instancia de AlgorithmParameters y nulo ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom086() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(15, k, al, (SecureRandom) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom087</b>() Se pasa como parametros: 15, own key, una instancia no funcional y nulo ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom087() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(15, k, AlgorithmParameters.getInstance(algorithm, providerString), (SecureRandom) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom088</b>() Se pasa como parametros: 15, own key,  AlgorithmParameters nulo y una instancia del SercureRandom ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom088() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(15, k, (AlgorithmParameters) null, new SecureRandom());
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom089</b>() Se pasa como parametros: 15, own key, una instancia de AlgorithmParameters y una instancia del SercureRandom ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom089() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(15, k, al, new SecureRandom());
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParametersSecureRandom090</b>() Se pasa como parametros: 15, own key, una instancia no funcional y una instancia del SercureRandom ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParametersSecureRandom090() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(15, k, AlgorithmParameters.getInstance(algorithm, providerString), new SecureRandom());
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/*
	 * Test method for 'javax.crypto.Cipher.init(int, Key, AlgorithmParameters)'
	 */
	
	/** <p><b>testInitIntKeyAlgorithmParameters001</b>() Se pasa como parametros: Cipher.WRAP_MODE, nulo y  AlgorithmParameters nulo ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters001() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(Cipher.WRAP_MODE, k, (AlgorithmParameters) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters002</b>() Se pasa como parametros: Cipher.WRAP_MODE, nulo y una instancia de AlgorithmParameters ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters002() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(Cipher.WRAP_MODE, k, al);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters003</b>() Se pasa como parametros: Cipher.WRAP_MODE, nulo y una instancia no funcional ; se espera que arroje InvalidAlgorithmParameterException o RunTimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters003() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(Cipher.WRAP_MODE, k, AlgorithmParameters.getInstance(algorithm, providerString));
			fail("must throw InvalidAlgorithmParameterException o RuntimeException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters004</b>() Se pasa como parametros: Cipher.WRAP_MODE, KeyGenertor of the algorithm set in cipher y  AlgorithmParameters nulo ; se espera que termine normalmente (el cipher quede inicialisado).</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters004() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(Cipher.WRAP_MODE, k, (AlgorithmParameters) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters005</b>() Se pasa como parametros: Cipher.WRAP_MODE, KeyGenertor of the algorithm set in cipher y una instancia de AlgorithmParameters ; se espera que termine normalmente (el cipher quede inicialisado).</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters005() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(Cipher.WRAP_MODE, k, al);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters006</b>() Se pasa como parametros: Cipher.WRAP_MODE, KeyGenertor of the algorithm set in cipher y una instancia no funcional ; se espera que arroje InvalidAlgorithmParameterException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters006() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(Cipher.WRAP_MODE, k, AlgorithmParameters.getInstance(algorithm, providerString));
			fail("must throw InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters007</b>() Se pasa como parametros: Cipher.WRAP_MODE, own key y  AlgorithmParameters nulo ; se espera que termine normalmente (el cipher quede inicialisado).</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters007() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(Cipher.WRAP_MODE, k, (AlgorithmParameters) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters008</b>() Se pasa como parametros: Cipher.WRAP_MODE, own key y una instancia de AlgorithmParameters ; se espera que termine normalmente (el cipher quede inicialisado).</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters008() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(Cipher.WRAP_MODE, k, al);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters009</b>() Se pasa como parametros: Cipher.WRAP_MODE, own key y una instancia no funcional ; se espera que arroje InvalidAlgorithmParameterException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters009() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(Cipher.WRAP_MODE, k, AlgorithmParameters.getInstance(algorithm, providerString));
			fail("must throw InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters010</b>() Se pasa como parametros: Cipher.UNWRAP_MODE, nulo y  AlgorithmParameters nulo ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters010() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(Cipher.UNWRAP_MODE, k, (AlgorithmParameters) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters011</b>() Se pasa como parametros: Cipher.UNWRAP_MODE, nulo y una instancia de AlgorithmParameters ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters011() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(Cipher.UNWRAP_MODE, k, al);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters012</b>() Se pasa como parametros: Cipher.UNWRAP_MODE, nulo y una instancia no funcional ; se espera que arroje InvalidAlgorithmParameterException o RunTimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters012() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(Cipher.UNWRAP_MODE, k, AlgorithmParameters.getInstance(algorithm, providerString));
			fail("must throw InvalidAlgorithmParameterException o RuntimeException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters013</b>() Se pasa como parametros: Cipher.UNWRAP_MODE, KeyGenertor of the algorithm set in cipher y  AlgorithmParameters nulo ; se espera que arroje InvalidAlgorithmParameterException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters013() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(Cipher.UNWRAP_MODE, k, (AlgorithmParameters) null);
			fail("must throw InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters014</b>() Se pasa como parametros: Cipher.UNWRAP_MODE, KeyGenertor of the algorithm set in cipher y una instancia de AlgorithmParameters ; se espera que termine normalmente (el cipher quede inicialisado).</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters014() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(Cipher.UNWRAP_MODE, k, al);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters015</b>() Se pasa como parametros: Cipher.UNWRAP_MODE, KeyGenertor of the algorithm set in cipher y una instancia no funcional ; se espera que arroje InvalidAlgorithmParameterException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters015() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(Cipher.UNWRAP_MODE, k, AlgorithmParameters.getInstance(algorithm, providerString));
			fail("must throw InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters016</b>() Se pasa como parametros: Cipher.UNWRAP_MODE, own key y  AlgorithmParameters nulo ; se espera que arroje InvalidAlgorithmParameterException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters016() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(Cipher.UNWRAP_MODE, k, (AlgorithmParameters) null);
			fail("must throw InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters017</b>() Se pasa como parametros: Cipher.UNWRAP_MODE, own key y una instancia de AlgorithmParameters ; se espera que termine normalmente (el cipher quede inicialisado).</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters017() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(Cipher.UNWRAP_MODE, k, al);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters018</b>() Se pasa como parametros: Cipher.UNWRAP_MODE, own key y una instancia no funcional ; se espera que arroje InvalidAlgorithmParameterException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters018() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(Cipher.UNWRAP_MODE, k, AlgorithmParameters.getInstance(algorithm, providerString));
			fail("must throw InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters019</b>() Se pasa como parametros: Cipher.DECRYPT_MODE, nulo y  AlgorithmParameters nulo ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters019() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(Cipher.DECRYPT_MODE, k, (AlgorithmParameters) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters020</b>() Se pasa como parametros: Cipher.DECRYPT_MODE, nulo y una instancia de AlgorithmParameters ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters020() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(Cipher.DECRYPT_MODE, k, al);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters021</b>() Se pasa como parametros: Cipher.DECRYPT_MODE, nulo y una instancia no funcional ; se espera que arroje InvalidAlgorithmParameterException o RunTimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters021() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(Cipher.DECRYPT_MODE, k, AlgorithmParameters.getInstance(algorithm, providerString));
			fail("must throw InvalidAlgorithmParameterException o RuntimeException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters022</b>() Se pasa como parametros: Cipher.DECRYPT_MODE, KeyGenertor of the algorithm set in cipher y  AlgorithmParameters nulo ; se espera que arroje InvalidAlgorithmParameterException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters022() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(Cipher.DECRYPT_MODE, k, (AlgorithmParameters) null);
			fail("must throw InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters023</b>() Se pasa como parametros: Cipher.DECRYPT_MODE, KeyGenertor of the algorithm set in cipher y una instancia de AlgorithmParameters ; se espera que termine normalmente (el cipher quede inicialisado).</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters023() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(Cipher.DECRYPT_MODE, k, al);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters024</b>() Se pasa como parametros: Cipher.DECRYPT_MODE, KeyGenertor of the algorithm set in cipher y una instancia no funcional ; se espera que arroje InvalidAlgorithmParameterException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters024() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(Cipher.DECRYPT_MODE, k, AlgorithmParameters.getInstance(algorithm, providerString));
			fail("must throw InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters025</b>() Se pasa como parametros: Cipher.DECRYPT_MODE, own key y  AlgorithmParameters nulo ; se espera que arroje InvalidAlgorithmParameterException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters025() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(Cipher.DECRYPT_MODE, k, (AlgorithmParameters) null);
			fail("must throw InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters026</b>() Se pasa como parametros: Cipher.DECRYPT_MODE, own key y una instancia de AlgorithmParameters ; se espera que termine normalmente (el cipher quede inicialisado).</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters026() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(Cipher.DECRYPT_MODE, k, al);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters027</b>() Se pasa como parametros: Cipher.DECRYPT_MODE, own key y una instancia no funcional ; se espera que arroje InvalidAlgorithmParameterException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters027() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(Cipher.DECRYPT_MODE, k, AlgorithmParameters.getInstance(algorithm, providerString));
			fail("must throw InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters028</b>() Se pasa como parametros: Cipher.ENCRYPT_MODE, nulo y  AlgorithmParameters nulo ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters028() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(Cipher.ENCRYPT_MODE, k, (AlgorithmParameters) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters029</b>() Se pasa como parametros: Cipher.ENCRYPT_MODE, nulo y una instancia de AlgorithmParameters ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters029() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(Cipher.ENCRYPT_MODE, k, al);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters030</b>() Se pasa como parametros: Cipher.ENCRYPT_MODE, nulo y una instancia no funcional ; se espera que arroje InvalidAlgorithmParameterException o RunTimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters030() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(Cipher.ENCRYPT_MODE, k, AlgorithmParameters.getInstance(algorithm, providerString));
			fail("must throw InvalidAlgorithmParameterException o RuntimeException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters031</b>() Se pasa como parametros: Cipher.ENCRYPT_MODE, KeyGenertor of the algorithm set in cipher y  AlgorithmParameters nulo ; se espera que termine normalmente (el cipher quede inicialisado).</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters031() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(Cipher.ENCRYPT_MODE, k, (AlgorithmParameters) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters032</b>() Se pasa como parametros: Cipher.ENCRYPT_MODE, KeyGenertor of the algorithm set in cipher y una instancia de AlgorithmParameters ; se espera que termine normalmente (el cipher quede inicialisado).</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters032() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(Cipher.ENCRYPT_MODE, k, al);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters033</b>() Se pasa como parametros: Cipher.ENCRYPT_MODE, KeyGenertor of the algorithm set in cipher y una instancia no funcional ; se espera que arroje InvalidAlgorithmParameterException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters033() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(Cipher.ENCRYPT_MODE, k, AlgorithmParameters.getInstance(algorithm, providerString));
			fail("must throw InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters034</b>() Se pasa como parametros: Cipher.ENCRYPT_MODE, own key y  AlgorithmParameters nulo ; se espera que termine normalmente (el cipher quede inicialisado).</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters034() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(Cipher.ENCRYPT_MODE, k, (AlgorithmParameters) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters035</b>() Se pasa como parametros: Cipher.ENCRYPT_MODE, own key y una instancia de AlgorithmParameters ; se espera que termine normalmente (el cipher quede inicialisado).</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters035() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(Cipher.ENCRYPT_MODE, k, al);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters036</b>() Se pasa como parametros: Cipher.ENCRYPT_MODE, own key y una instancia no funcional ; se espera que arroje InvalidAlgorithmParameterException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters036() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(Cipher.ENCRYPT_MODE, k, AlgorithmParameters.getInstance(algorithm, providerString));
			fail("must throw InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters037</b>() Se pasa como parametros: 15, nulo y  AlgorithmParameters nulo ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters037() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(15, k, (AlgorithmParameters) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters038</b>() Se pasa como parametros: 15, nulo y una instancia de AlgorithmParameters ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters038() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(15, k, al);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters039</b>() Se pasa como parametros: 15, nulo y una instancia no funcional ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters039() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = (Key) null;
		
		try {
			cipher.init(15, k, AlgorithmParameters.getInstance(algorithm, providerString));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters040</b>() Se pasa como parametros: 15, KeyGenertor of the algorithm set in cipher y  AlgorithmParameters nulo ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters040() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(15, k, (AlgorithmParameters) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters041</b>() Se pasa como parametros: 15, KeyGenertor of the algorithm set in cipher y una instancia de AlgorithmParameters ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters041() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(15, k, al);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters042</b>() Se pasa como parametros: 15, KeyGenertor of the algorithm set in cipher y una instancia no funcional ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters042() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = Util.getInstanceKey(algorithm);
		
		try {
			cipher.init(15, k, AlgorithmParameters.getInstance(algorithm, providerString));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters043</b>() Se pasa como parametros: 15, own key y  AlgorithmParameters nulo ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters043() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(15, k, (AlgorithmParameters) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters044</b>() Se pasa como parametros: 15, own key y una instancia de AlgorithmParameters ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters044() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(15, k, al);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/** <p><b>testInitIntKeyAlgorithmParameters045</b>() Se pasa como parametros: 15, own key y una instancia no funcional ; se espera que arroje RuntimeException.</p> */
	@SuppressWarnings("unused")
	public final void testInitIntKeyAlgorithmParameters045() throws Exception{
		cipher = Cipher.getInstance(transformation,provider);
		AlgorithmParameterGenerator agen = AlgorithmParameterGenerator.getInstance(algorithm);
		agen.init(7);
		AlgorithmParameters al = agen.generateParameters();
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {
			cipher.init(15, k, AlgorithmParameters.getInstance(algorithm, providerString));
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKey003() throws NoSuchAlgorithmException, NoSuchPaddingException {
		cipher = Cipher.getInstance(transformation,provider);
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {		
			cipher.init(Cipher.WRAP_MODE, k);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKey006() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}));
			fail("must throw InvalidKeyException");
		} catch (InvalidKeyException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKey009() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}));
			fail("must throw InvalidKeyException");
		} catch (InvalidKeyException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKey012() throws NoSuchAlgorithmException, NoSuchPaddingException {
		cipher = Cipher.getInstance(transformation,provider);
		try {			
			cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}));
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeySecureRandom005() throws NoSuchAlgorithmException, NoSuchPaddingException {		
		cipher = Cipher.getInstance(transformation,provider);
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {	
			cipher.init(Cipher.WRAP_MODE, k, (SecureRandom) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeySecureRandom006() throws NoSuchAlgorithmException, NoSuchPaddingException {
		
		cipher = Cipher.getInstance(transformation,provider);
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		try {	
			cipher.init(Cipher.WRAP_MODE, k, new SecureRandom());
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeySecureRandom011() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), (SecureRandom) null);
			fail("must throw InvalidKeyException");
		} catch (InvalidKeyException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeySecureRandom012() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), new SecureRandom());
			fail("must throw InvalidKeyException");
		} catch (InvalidKeyException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeySecureRandom017() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), (SecureRandom) null);
			fail("must throw InvalidKeyException");
		} catch (InvalidKeyException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeySecureRandom018() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), new SecureRandom());
			fail("must throw InvalidKeyException");
		} catch (InvalidKeyException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeySecureRandom023() throws NoSuchAlgorithmException, NoSuchPaddingException {
		cipher = Cipher.getInstance(transformation,provider);
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {	
			cipher.init(Cipher.ENCRYPT_MODE, k, (SecureRandom) null);
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeySecureRandom024() throws NoSuchAlgorithmException, NoSuchPaddingException {		
		cipher = Cipher.getInstance(transformation,provider);
		Key k = new K(algorithm, new byte[] {1,2,3,4,5,6,7,8});
		
		try {	
			cipher.init(Cipher.ENCRYPT_MODE, k, new SecureRandom());
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/*
	 * tests for 'javax.crypto.Cipher.getInstance(String)'
	 */
	
	public final void testGetInstanceStringString006() {
		
		transformation = algorithm + "/" + mode + "/" +"NoValidPadding";
		try {
			cipher = Cipher.getInstance(transformation,providerString);
			fail(msgNoSuchPadding);
		} catch (NoSuchPaddingException e) {
		} catch (Throwable e) {
			fail(msgNoSuchPadding);
		}
	}
	
	public final void testGetInstanceStringProvider006() {
		try {
			
			transformation = algorithm + "/" + mode + "/" + "NOTBlockCipherPadding";
			cipher = Cipher.getInstance(transformation,provider);
			fail(msgNoSuchPadding);
		} catch (NoSuchPaddingException e) {
		} catch (Throwable e) {
			fail(msgNoSuchPadding + " " + e);
		}
		
	}
	
	public final void testGetParameters004() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.ENCRYPT_MODE, Util.getInstanceKey(algorithm));
			AlgorithmParameters ap = cipher.getParameters();
			
			assertEquals("May be same Parameters", ap.getAlgorithm(), algorithm);
			
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/*
	 * tests for 'javax.crypto.Cipher.getMaxAllowedKeyLength(String)'
	 */
	public final void testGetMaxAllowedKeyLength001() {
		try {
			assertEquals("Sould return: " + Integer.MAX_VALUE,Integer.MAX_VALUE,Cipher.getMaxAllowedKeyLength(algorithm));
		} catch (Throwable e) {
			fail(msgNoException +", Throwned: " + e);
		}
	}
	
	public final void testGetMaxAllowedKeyLength003() {
		
		try {
			Cipher.getMaxAllowedKeyLength("NotValidAlgorithm/ and bad transformation");
			fail(msgRaise + "NoSuchAlgorithmException");
		} catch (NoSuchAlgorithmException e) {
		} catch (Throwable e) {
			fail(msgRaise + "NoSuchAlgorithmException " + e);
		}
	}
	
	public final void testGetParameters002() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.ENCRYPT_MODE, Util.getInstanceKey(algorithm));
			assertNotNull("Cipher initialized", cipher.getParameters());
			
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/*
	 * Test methods for 'javax.crypto.Cipher.init(int, Key)'
	 */
	public final void testInitIntKey001() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.WRAP_MODE, (Key) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKey004() throws NoSuchAlgorithmException, NoSuchPaddingException {
		cipher = Cipher.getInstance(transformation,provider);
		try {			
			cipher.init(Cipher.UNWRAP_MODE, (Key) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKey005() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.UNWRAP_MODE, Util.getInstanceKey(algorithm));
			fail("must throw InvalidKeyException");
		} catch (InvalidKeyException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKey007() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.DECRYPT_MODE, (Key) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKey008() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.DECRYPT_MODE, Util.getInstanceKey(algorithm));
			fail("must throw InvalidKeyException");
		} catch (InvalidKeyException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKey010() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.ENCRYPT_MODE, (Key) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	/*
	 * Test for 'javax.crypto.Cipher.init(int, Key, SecureRandom)'
	 */
	public final void testInitIntKeySecureRandom001() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.WRAP_MODE, (Key) null, (SecureRandom) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeySecureRandom002() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.WRAP_MODE, (Key) null, new SecureRandom());
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeySecureRandom007() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.UNWRAP_MODE, (Key) null, (SecureRandom) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeySecureRandom008() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.UNWRAP_MODE, (Key) null, new SecureRandom());
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeySecureRandom009() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.UNWRAP_MODE, Util.getInstanceKey(algorithm), (SecureRandom) null);
			fail("must throw InvalidKeyException");
		} catch (InvalidKeyException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeySecureRandom010() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.UNWRAP_MODE, Util.getInstanceKey(algorithm), new SecureRandom());
			fail("must throw InvalidKeyException");
		} catch (InvalidKeyException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeySecureRandom013() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.DECRYPT_MODE, (Key) null, (SecureRandom) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeySecureRandom014() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.DECRYPT_MODE, (Key) null, new SecureRandom());
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeySecureRandom015() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.DECRYPT_MODE, Util.getInstanceKey(algorithm), (SecureRandom) null);
			fail("must throw InvalidKeyException");
		} catch (InvalidKeyException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	public final void testInitIntKeySecureRandom016() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.DECRYPT_MODE, Util.getInstanceKey(algorithm), new SecureRandom());
			fail("must throw InvalidKeyException");
		} catch (InvalidKeyException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeySecureRandom019() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.ENCRYPT_MODE, (Key) null, (SecureRandom) null);
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
	
	public final void testInitIntKeySecureRandom020() {
		try {
			cipher = Cipher.getInstance(transformation,provider);
			cipher.init(Cipher.ENCRYPT_MODE, (Key) null, new SecureRandom());
			fail("must throw RuntimeException");
		} catch (RuntimeException e) {
		} catch (Throwable e) {
			fail("fail with: " + e);
		}
	}
}
