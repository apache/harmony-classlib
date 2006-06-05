package ar.org.fitc.test.crypto;

import java.math.BigInteger;
import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.KeyAgreement;
import javax.crypto.ShortBufferException;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.PBEParameterSpec;

import junit.framework.TestCase;
import ar.org.fitc.test.util.Messages;

public class TestKeyAgreement extends TestCase implements Messages {
	

	private boolean lastPhase = true;

	private byte[] sharedSecret = null;

	private int offset = 0;

	private String algorithm = "";

	private Provider provider = null;

	private String stringProvider = "";

	private SecureRandom random = null;

	private KeyAgreement ka = null;
	
	private KeyFactory kf = null;

	private PublicKey publicKey;

	private String providerToString = "";

	private static KeyPairGenerator kpg = null;

	private static KeyPair keyPair;

	private static Key key = null;
	
	static DHParameterSpec dhparam = null;

	public TestKeyAgreement(String string) {
		super(string); 
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestKeyAgreement.class);
	}

	static {
		try {
			kpg = KeyPairGenerator.getInstance("DH");
			kpg.initialize(1024);
			keyPair = kpg.genKeyPair();
			key = keyPair.getPrivate();
			dhparam = ((DHPublicKey) keyPair.getPublic()).getParams();
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	protected void setUp() throws Exception {
		super.setUp();

		algorithm = "DH";
		stringProvider = "SunJCE";
		kf = KeyFactory.getInstance(algorithm);
		ka = KeyAgreement.getInstance(algorithm);
		provider = ka.getProvider();
		lastPhase = true;
		providerToString = "SunJCE version 1.5";
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		System.gc();
	}

	/*
	 * Test method for 'javax.crypto.KeyAgreement.getAlgorithm()'
	 */

	public final void testGetAlgorithm001() {
		try {
			ka = KeyAgreement.getInstance(algorithm);
			assertNotNull(msgNotNull, ka.getAlgorithm());
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testGetAlgorithm002() {

		try {
			ka = KeyAgreement.getInstance(algorithm);
			assertEquals(algorithm, ka.getAlgorithm());
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	/*
	 * Test method for 'javax.crypto.KeyAgreement.getInstance(String)'
	 */
	public final void testGetInstanceString001() {

		try {

			assertNotNull(msgNotNull, KeyAgreement.getInstance("DiffieHellman"));
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testGetInstanceString002() {
		try {
			assertNotNull(msgNotNull, KeyAgreement.getInstance("ECDHC"));
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testGetInstanceString003() {

		try {
			ka = KeyAgreement.getInstance(null);
			fail("Should raise NullPointerException");
		} catch (NullPointerException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testGetInstanceString004() {

		try {
			ka = KeyAgreement.getInstance("hhatja");
			fail("Should raise NoSuchAlgorithmException");
		} catch (NoSuchAlgorithmException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	/*
	 * Test method for 'javax.crypto.KeyAgreement.getInstance(String, String)'
	 */
	public final void testGetInstanceStringString001() {

		try {
			assertNotNull(msgNotNull, KeyAgreement.getInstance("ECDHC", "BC"));
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testGetInstanceStringString002() {

		try {
			ka = KeyAgreement.getInstance(null, stringProvider);
			fail("Should raise NullPointerException");
		} catch (NullPointerException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testGetInstanceStringString003() {

		try {
			ka = KeyAgreement.getInstance("hhatja", stringProvider);
			fail("Should raise NoSuchAlgorithmException");
		} catch (NoSuchAlgorithmException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testGetInstanceStringString004() {

		try {
			stringProvider = "NotValidProvider";
			ka = KeyAgreement.getInstance(algorithm, stringProvider);
			fail("Should raise NoSuchProviderException");
		} catch (NoSuchProviderException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testGetInstanceStringString005() {

		try {
			stringProvider = "";
			ka = KeyAgreement.getInstance(algorithm, stringProvider);
			fail("Should raise IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testGetInstanceStringString006() {
		try {

			algorithm = "hrgeqj";
			stringProvider = "NotValidProvider";
			ka = KeyAgreement.getInstance(algorithm, stringProvider);
			fail("Should raise NoSuchProviderException");
		} catch (NoSuchProviderException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testGetInstanceStringString007() {

		try {

			KeyAgreement.getInstance("ECDHC", "SunJCE");

			fail("Should raise NoSuchAlgorithmException");
		} catch (NoSuchAlgorithmException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	/*
	 * Test method for 'javax.crypto.KeyAgreement.getInstance(String, Provider)'
	 */
	public final void testGetInstanceStringProvider001() {

		try {
			assertNotNull(msgNotNull, KeyAgreement.getInstance("DH", provider));
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testGetInstanceStringProvider002() {

		try {
			ka = KeyAgreement.getInstance(null, provider);
			fail("Should raise NullPointerException");
		} catch (NullPointerException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testGetInstanceStringProvider003() {

		try {
			ka = KeyAgreement.getInstance("hhatja", provider);
			fail("Should raise NoSuchAlgorithmException");
		} catch (NoSuchAlgorithmException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	/*
	 * Test method for 'javax.crypto.KeyAgreement.getInstance(String,
	 * String)that throws an IllegalArgumentException'
	 */

	public final void testGetInstanceStringProvider004() {

		try {
			provider = null;
			ka = KeyAgreement.getInstance(algorithm, provider);
			fail("Should raise IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testGetInstanceStringProvider005() {

		try {
			provider = null;
			ka = KeyAgreement.getInstance("nyarsk67", provider);
			fail("Should raise IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	/*
	 * Test method for 'javax.crypto.KeyAgreement.getProvider()'
	 */

	public final void testGetProvider() {

		try {
			ka = KeyAgreement.getInstance(algorithm);
			assertNotNull(msgNotNull, ka.getProvider());
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	/*
	 * Test method for 'javax.crypto.KeyAgreement.getProvider()'
	 */

	public final void testGetProvider002() {
		try {
			ka = KeyAgreement.getInstance(algorithm);
			assertEquals(provider.toString(), providerToString);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	/*
	 * Test method for 'javax.crypto.KeyAgreement.init(Key)'
	 */

	public final void testInitKey001() {

		try {
			ka = KeyAgreement.getInstance("DH");
			ka.init(key);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testInitKey002() {

		try {
			kpg = KeyPairGenerator.getInstance("DH");
			kpg.initialize(1024);
			keyPair = kpg.genKeyPair();
			key = keyPair.getPrivate();
			ka = KeyAgreement.getInstance("ECDH");

			ka.init(key);
			fail("Should raise InvalidKeyException");
		} catch (InvalidKeyException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testInitKey003() {

		try {
			ka = KeyAgreement.getInstance("DH");
			ka.init(null);
			fail("Should raise InvalidKeyException");
		} catch (InvalidKeyException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	/*
	 * Test method for 'javax.crypto.KeyAgreement.init(Key, SecureRandom)'
	 */

	public final void testInitKeySecureRandom001() {

		try {
			kpg = KeyPairGenerator.getInstance("DH");
			kpg.initialize(1024);
			keyPair = kpg.genKeyPair();
			key = keyPair.getPrivate();
			ka = KeyAgreement.getInstance("DH");
			random = new SecureRandom();
			ka.init(key, random);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	/*
	 * Test method for 'javax.crypto.KeyAgreement.init(Key, SecureRandom)'
	 */
	public final void testInitKeySecureRandom002() {

		try {
			kpg = KeyPairGenerator.getInstance("DH");
			kpg.initialize(1024);
			keyPair = kpg.genKeyPair();
			key = keyPair.getPrivate();
			ka = KeyAgreement.getInstance("ECDH");

			SecureRandom random = new SecureRandom();
			ka.init(key, random);
			fail("Should raise InvalidKeyException");
		} catch (InvalidKeyException e) {
			assertTrue(true);

		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testInitKeySecureRandom003() {

		try {
			kpg = KeyPairGenerator.getInstance("DH");
			kpg.initialize(1024);
			keyPair = kpg.genKeyPair();
			key = keyPair.getPrivate();
			ka = KeyAgreement.getInstance("DH");
			random = new SecureRandom();
			random = null;
			ka.init(key, random);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testInitKeySecureRandom004() {

		try {
			kpg = KeyPairGenerator.getInstance("DH");
			kpg.initialize(1024);
			keyPair = kpg.genKeyPair();
			key = keyPair.getPrivate();
			ka = KeyAgreement.getInstance("DH");
			byte[] seed = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,
					(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
			ka.init(key, new SecureRandom(seed));
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	/*
	 * Test method for 'javax.crypto.KeyAgreement.init(Key,
	 * AlgorithmParameterSpec)'
	 */

	public final void testInitKeyAlgorithmParameterSpec001() throws Exception {

		kpg = KeyPairGenerator.getInstance("DH");
		kpg.initialize(1024);
		keyPair = kpg.genKeyPair();
		key = keyPair.getPrivate();
		dhparam = ((DHPublicKey) keyPair.getPublic()).getParams();
		ka = KeyAgreement.getInstance("DH");

		try {
			ka.init(key, dhparam);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testInitKeyAlgorithmParameterSpec002() throws Exception {

		AlgorithmParameterGenerator paramGen= AlgorithmParameterGenerator.getInstance("DH");
		paramGen.init(1024);
		AlgorithmParameters params = paramGen.generateParameters();
		DHParameterSpec dhparam = (DHParameterSpec)params.getParameterSpec(DHParameterSpec.class);
		ka = KeyAgreement.getInstance("DH");

		try {
			ka.init(key, dhparam);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	
/*	  public final void testInitKeyAlgorithmParameterSpec002() { try { kpg =
	  KeyPairGenerator.getInstance("DH"); kpg.initialize(1024); keyPair =
	  kpg.genKeyPair(); key=keyPair.getPrivate(); ka =
	  KeyAgreement.getInstance("ECDH");
	  
	  
	  ka.init(key,dhparam); fail("Should raise InvalidKeyException"); } catch
	  (InvalidKeyException e) { assertTrue(true); }catch (Throwable e) {
	  fail("Failed with:" + e); } }*/
	  
	  public final void testInitKeyAlgorithmParameterSpec003() {
	  
	  try { kpg = KeyPairGenerator.getInstance("DH");
	  kpg.initialize(1024);
	  keyPair = kpg.genKeyPair();
	  key=keyPair.getPrivate(); ka =
	  KeyAgreement.getInstance("DH"); 
	  byte[] salt = new byte[] { 0x7d, 0x60,0x43, 0x5f, 0x02, (byte)0xe9, (byte)0xe0, (byte)0xae };
	  ka.init(key,new PBEParameterSpec(salt, 55)); 
	  fail("Should raise InvalidAlgorithmParameterException");
	  } catch  (InvalidAlgorithmParameterException e) 
	  { assertTrue(true); } 
	  catch(Throwable e) { fail("Failed with:" + e); } }
	  
	  public final void testInitKeyAlgorithmParameterSpec004() {
	  
	  try{kpg = KeyPairGenerator.getInstance("DH"); kpg.initialize(1024);
	  keyPair = kpg.genKeyPair();
	  key=keyPair.getPrivate(); ka =
	  KeyAgreement.getInstance("DH"); 
	  DHParameterSpec dhparam = null;
	  ka.init(key,dhparam); } catch (Throwable e) { fail("Failed with:" + e); } }
	  
	/*  public final void testInitKeyAlgorithmParameterSpec005() {	  
	  try{kpg = KeyPairGenerator.getInstance("DH");
	  kpg.initialize(1024);
	  keyPair = kpg.genKeyPair(); 
	  key=null; ka =KeyAgreement.getInstance("DH");
	  ka.init(key,dhparam); 
	  fail("Should raise InvalidKeyException");
	  } catch  (InvalidKeyException e)
	  { assertTrue(true);
	  } catch (Throwable e) 
	  {
	  fail("Failed with:" + e);
	  } 
	  }
	  */
	  
	  
	  public final void testInitKeyAlgorithmParameterSpec006() {
	  
	  try{kpg = KeyPairGenerator.getInstance("DH"); kpg.initialize(1024);
	  keyPair = kpg.genKeyPair(); key=keyPair.getPrivate(); ka =
	  KeyAgreement.getInstance("DH"); BigInteger p=new BigInteger("22");
	  BigInteger q=new BigInteger("66"); ka.init(key,new DHParameterSpec(p,q)); }
	  catch (InvalidAlgorithmParameterException e) { assertTrue(true); } catch
	  (Throwable e) { fail("Failed with:" + e); } }
	  
	   /* Test method for 'javax.crypto.KeyAgreement.init(Key,
	  AlgorithmParameterSpec, SecureRandom)'
	 */

	public final void testInitKeyAlgorithmParameterSpecSecureRandom001() {

		try {
			ka = KeyAgreement.getInstance("DH");

			random = new SecureRandom();
			ka.init(key, dhparam, random);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testInitKeyAlgorithmParameterSpecSecureRandom002() {

		try {
			ka = KeyAgreement.getInstance("ECDH");

			random = new SecureRandom();
			ka.init(key, dhparam, random);
			fail("Should raise InvalidKeyException");
		} catch (InvalidKeyException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testInitKeyAlgorithmParameterSpecSecureRandom003() {

		try {

			ka = KeyAgreement.getInstance("DH");
			byte[] salt = new byte[] { 0x7d, 0x60, 0x43, 0x5f, 0x02,
					(byte) 0xe9, (byte) 0xe0, (byte) 0xae };
			random = new SecureRandom();
			ka.init(key, new PBEParameterSpec(salt, 55), random);
			fail("Should raise InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testInitKeyAlgorithmParameterSpecSecureRandom004() {

		try {
			ka = KeyAgreement.getInstance("DH");
			DHParameterSpec dhparam = null;
			random = new SecureRandom();
			ka.init(key, dhparam, random);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testInitKeyAlgorithmParameterSpecSecureRandom005() {

		try {
			Key key = null;
			ka = KeyAgreement.getInstance("DH");
			random = new SecureRandom();
			ka.init(key, dhparam, random);
			fail("Should raise InvalidKeyException");
		} catch (InvalidKeyException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testInitKeyAlgorithmParameterSpecSecureRandom006() {

		try {
			ka = KeyAgreement.getInstance("DH");
			BigInteger p = new BigInteger("22");
			BigInteger q = new BigInteger("66");
			random = new SecureRandom();
			ka.init(key, new DHParameterSpec(p, q), random);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testInitKeyAlgorithmParameterSpecSecureRandom007() {

		try {
			ka = KeyAgreement.getInstance("DH");

			random = new SecureRandom();
			ka.init(key, dhparam, random);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testInitKeyAlgorithmParameterSpecSecureRandom008()
			throws NoSuchAlgorithmException, InvalidParameterSpecException,
			InvalidAlgorithmParameterException {
		ka = KeyAgreement.getInstance("ECDH");
		byte[] seed = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,
				(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
		random = new SecureRandom(seed);
		try {
			ka.init(key, dhparam, random);
			fail("Should raise InvalidKeyException");
		} catch (InvalidKeyException e) {
			assertTrue(true);
		}
	}

	public final void testInitKeyAlgorithmParameterSpecSecureRandom009()
			throws Exception {
		ka = KeyAgreement.getInstance("DH");
		byte[] salt = new byte[] { 0x7d, 0x60, 0x43, 0x5f, 0x02, (byte) 0xe9,
				(byte) 0xe0, (byte) 0xae };
		byte[] seed = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,
				(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
		random = new SecureRandom(seed);
		try {
			ka.init(key, new PBEParameterSpec(salt, 55), random);
			fail("Should raise InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testInitKeyAlgorithmParameterSpecSecureRandom010()
			throws Exception {

		ka = KeyAgreement.getInstance("DH");
		DHParameterSpec dhparam = null;
		byte[] seed = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,
				(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
		random = new SecureRandom(seed);
		try {
			ka.init(key, dhparam, random);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testInitKeyAlgorithmParameterSpecSecureRandom011()
			throws Exception {
		Key key = null;
		ka = KeyAgreement.getInstance("DH");
		byte[] seed = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,
				(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
		random = new SecureRandom(seed);
		try {
			ka.init(key, dhparam, random);
			fail("Should raise InvalidKeyException");
		} catch (InvalidKeyException e) {
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testInitKeyAlgorithmParameterSpecSecureRandom012()
			throws Exception {
		ka = KeyAgreement.getInstance("DH");
		BigInteger p = new BigInteger("22");
		BigInteger q = new BigInteger("66");
		byte[] seed = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,
				(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
		random = new SecureRandom(seed);
		try {
			ka.init(key, new DHParameterSpec(p, q), random);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testInitKeyAlgorithmParameterSpecSecureRandom013()
			throws Exception {

		ka = KeyAgreement.getInstance("DH");

		random = new SecureRandom();
		random = null;
		try {
			ka.init(key, dhparam, random);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testInitKeyAlgorithmParameterSpecSecureRandom014()
			throws NoSuchAlgorithmException, InvalidParameterSpecException,
			InvalidAlgorithmParameterException {

		ka = KeyAgreement.getInstance("ECDH");

		random = new SecureRandom();
		random = null;
		try {
			ka.init(key, dhparam, random);
			fail("Should raise InvalidKeyException");
		} catch (InvalidKeyException e) {
			assertTrue(true);
		}
	}

	public final void testInitKeyAlgorithmParameterSpecSecureRandom015() {

		try {

			ka = KeyAgreement.getInstance("DH");
			byte[] salt = new byte[] { 0x7d, 0x60, 0x43, 0x5f, 0x02,
					(byte) 0xe9, (byte) 0xe0, (byte) 0xae };
			random = new SecureRandom();
			random = null;
			ka.init(key, new PBEParameterSpec(salt, 55), random);
			fail("Should raise InvalidAlgorithmParameterException");
		} catch (InvalidAlgorithmParameterException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testInitKeyAlgorithmParameterSpecSecureRandom016() {

		try {
			ka = KeyAgreement.getInstance("DH");
			DHParameterSpec dhparam = null;
			random = new SecureRandom();
			random = null;
			ka.init(key, dhparam, random);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testInitKeyAlgorithmParameterSpecSecureRandom017() {

		try {
			Key key = null;
			ka = KeyAgreement.getInstance("DH");

			random = new SecureRandom();
			random = null;
			ka.init(key, dhparam, random);
			fail("Should raise InvalidKeyException");
		} catch (InvalidKeyException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testInitKeyAlgorithmParameterSpecSecureRandom018() {

		try {
			ka = KeyAgreement.getInstance("DH");
			BigInteger p = new BigInteger("22");
			BigInteger q = new BigInteger("66");
			// random= new SecureRandom();
			random = null;
			ka.init(key, new DHParameterSpec(p, q), random);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	/*
	 * Test method for 'javax.crypto.KeyAgreement.doPhase(Key, boolean)'
	 */

	public final void testDoPhase001() {

		try {
			publicKey = keyPair.getPublic();
			ka.init(key);
			lastPhase = true;
			assertNull(ka.doPhase(publicKey, lastPhase));
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testDoPhase002() {
		try {

			@SuppressWarnings("unused") Key key = keyPair.getPublic();
			ka.init(keyPair.getPrivate());

			key = ka.doPhase(keyPair.getPrivate(), lastPhase);
			fail("Should raise InvalidKeyException");
		} catch (InvalidKeyException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testDoPhase003() {

		try {
			Key key = keyPair.getPublic();
			kf = KeyFactory.getInstance("DH");
			X509EncodedKeySpec X509Spec = new X509EncodedKeySpec(key
					.getEncoded());
			publicKey = kf.generatePublic(X509Spec);
			ka.doPhase(publicKey, lastPhase);
			fail("Should raise IllegalStateException");
		} catch (IllegalStateException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testDoPhase004() {

		try {
			Key key = keyPair.getPublic();
			kf = KeyFactory.getInstance("DH");
			X509EncodedKeySpec X509Spec = new X509EncodedKeySpec(key
					.getEncoded());
			publicKey = kf.generatePublic(X509Spec);
			ka.init(keyPair.getPrivate());
			lastPhase = false;

			assertNotNull(msgNotNull, ka.doPhase(publicKey, lastPhase));
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testDoPhase005() {
		try {
			@SuppressWarnings("unused") Key key = keyPair.getPublic();
			ka.init(keyPair.getPrivate());
			key = ka.doPhase(keyPair.getPrivate(), false);
			fail("Should raise InvalidKeyException");
		} catch (InvalidKeyException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testDoPhase006() {

		try {
			Key key = keyPair.getPublic();
			kf = KeyFactory.getInstance("DH");
			X509EncodedKeySpec X509Spec = new X509EncodedKeySpec(key
					.getEncoded());
			publicKey = kf.generatePublic(X509Spec);
			ka.doPhase(publicKey, false);
			fail("Should raise IllegalStateException");
		} catch (IllegalStateException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testDoPhase007() {

		try {

			Key key = keyPair.getPublic();
			kf = KeyFactory.getInstance("DH");
			X509EncodedKeySpec X509Spec = new X509EncodedKeySpec(key
					.getEncoded());
			publicKey = kf.generatePublic(X509Spec);
			ka.init(keyPair.getPrivate());
			Key key1 = ka.doPhase(publicKey, true);
			Key key2 = ka.doPhase(publicKey, true);
			assertEquals(key1, key2);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testDoPhase008() {

		try {

			Key key = keyPair.getPublic();
			kf = KeyFactory.getInstance("DH");
			X509EncodedKeySpec X509Spec = new X509EncodedKeySpec(key
					.getEncoded());
			publicKey = kf.generatePublic(X509Spec);
			ka.init(keyPair.getPrivate());
			Key key1 = ka.doPhase(publicKey, false);
			Key key2 = ka.doPhase(publicKey, true);
			assertNotSame(key1, key2);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testDoPhase009() {

		try {
			Key key = keyPair.getPublic();
			kf = KeyFactory.getInstance("DH");
			DHPublicKey publicKey = (DHPublicKey) key;
			ka.init(keyPair.getPrivate());
			lastPhase = true;
			if (ka.doPhase(publicKey, lastPhase) == null)
				assertTrue(true);
			else {
				assertTrue(false);
			}
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testDoPhase010() {

		try {
			Key key = keyPair.getPublic();
			kf = KeyFactory.getInstance("DH");
			DHPublicKey publicKey = (DHPublicKey) key;
			ka.doPhase(publicKey, lastPhase);
			fail("Should raise IllegalStateException");
		} catch (IllegalStateException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testDoPhase011() {

		try {

			Key key = keyPair.getPublic();
			kf = KeyFactory.getInstance("DH");
			DHPublicKey publicKey = (DHPublicKey) key;
			ka.init(keyPair.getPrivate());
			lastPhase = false;

			assertNotNull(msgNotNull, ka.doPhase(publicKey, lastPhase));
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testDoPhase012() throws Exception {
		ka.init(keyPair.getPrivate());
		try {
			ka.doPhase(keyPair.getPrivate(), lastPhase);
			fail("Should raise InvalidKeyException");
		} catch (InvalidKeyException e) {
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testDoPhase013() throws Exception {
		Key key = keyPair.getPublic();
		kf = KeyFactory.getInstance("DH");
		DHPublicKey publicKey = (DHPublicKey) key;

		try {
			ka.doPhase(publicKey, false);
			fail("Should raise IllegalStateException");
		} catch (IllegalStateException e) {
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testDoPhase014() {

		try {

			Key key = keyPair.getPublic();
			kf = KeyFactory.getInstance("DH");
			DHPublicKey publicKey = (DHPublicKey) key;
			ka.init(keyPair.getPrivate());
			Key key1 = ka.doPhase(publicKey, true);
			Key key2 = ka.doPhase(publicKey, true);
			assertEquals(key1, key2);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testDoPhase015() {

		try {
			Key key = keyPair.getPublic();
			kf = KeyFactory.getInstance("DH");
			DHPublicKey publicKey = (DHPublicKey) key;
			ka.init(keyPair.getPrivate());
			Key key1 = ka.doPhase(publicKey, false);
			Key key2 = ka.doPhase(publicKey, true);
			assertNotSame(key1, key2);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testDoPhase016() {
		try {
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("DH");
			kpg.initialize(512);
			KeyPair keyPair = kpg.genKeyPair();
			ka.init(keyPair.getPrivate());

			kpg.initialize(576);
			keyPair = kpg.genKeyPair();
			Key key = keyPair.getPublic();
			kf = KeyFactory.getInstance("DH");
			X509EncodedKeySpec X509Spec = new X509EncodedKeySpec(key
					.getEncoded());
			publicKey = kf.generatePublic(X509Spec);
			ka.doPhase(publicKey, lastPhase);
			fail("Should raise InvalidKeyException");
		} catch (InvalidKeyException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	/*
	 * Test method for 'javax.crypto.KeyAgreement.generateSecret()'
	 */

	public final void testGenerateSecret001() {

		try {

			Key key = keyPair.getPublic();
			kf = KeyFactory.getInstance("DH");
			X509EncodedKeySpec X509Spec = new X509EncodedKeySpec(key
					.getEncoded());
			publicKey = kf.generatePublic(X509Spec);
			ka.init(keyPair.getPrivate());
			ka.doPhase(publicKey, lastPhase);

			assertNotNull(msgNotNull, ka.generateSecret());
		} catch (Throwable e) {
			fail("Should not raise any Exception...");
		}
	}

	public final void testGenerateSecret002() {

		try {
			Key key = keyPair.getPublic();
			kf = KeyFactory.getInstance("DH");
			X509EncodedKeySpec X509Spec = new X509EncodedKeySpec(key
					.getEncoded());
			publicKey = kf.generatePublic(X509Spec);

			ka.init(keyPair.getPrivate());
			ka.generateSecret();

			ka.init(keyPair.getPrivate());

			fail("Should raise IllegalStateException");
		} catch (IllegalStateException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testGenerateSecret003() {

		try {
			Key key = keyPair.getPublic();
			kf = KeyFactory.getInstance("DH");
			X509EncodedKeySpec X509Spec = new X509EncodedKeySpec(key
					.getEncoded());
			publicKey = kf.generatePublic(X509Spec);
			ka.init(keyPair.getPrivate());
			ka.doPhase(publicKey, false);

			ka.generateSecret();

			fail("Should raise IllegalStateException");
		} catch (IllegalStateException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testGenerateSecret004() {

		try {
			Key key = keyPair.getPublic();
			kf = KeyFactory.getInstance("DH");
			X509EncodedKeySpec X509Spec = new X509EncodedKeySpec(key
					.getEncoded());
			publicKey = kf.generatePublic(X509Spec);
			ka.init(keyPair.getPrivate());
			ka.doPhase(publicKey, lastPhase);

			ka.generateSecret();
			ka.generateSecret();

			fail("Should raise IllegalStateException");
		} catch (IllegalStateException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testGenerateSecret005() {

		try {

			Key key = keyPair.getPublic();
			kf = KeyFactory.getInstance("DH");
			X509EncodedKeySpec X509Spec = new X509EncodedKeySpec(key
					.getEncoded());
			publicKey = kf.generatePublic(X509Spec);
			ka.init(keyPair.getPrivate());
			ka.doPhase(publicKey, true);
			byte[] secret = ka.generateSecret();
			ka.doPhase(publicKey, true);
			byte[] secret2 = ka.generateSecret();
			for (int i = 0; i < 128; i++) {
				assertEquals(secret[i], secret2[i]);
			}
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	/*
	 * Test method for 'javax.crypto.KeyAgreement.generateSecret(byte[], int)'
	 */

	public final void testGenerateSecretByteArrayInt001() {

		try {
			Key key = keyPair.getPublic();
			kf = KeyFactory.getInstance("DH");
			X509EncodedKeySpec X509Spec = new X509EncodedKeySpec(key
					.getEncoded());
			publicKey = kf.generatePublic(X509Spec);
			ka.init(keyPair.getPrivate());
			ka.doPhase(publicKey, lastPhase);
			byte[] sharedSecret = new byte[1024];
			int offset = 3;
			int secret = ka.generateSecret(sharedSecret, offset);
			if (secret == 128) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testGenerateSecretByteArrayInt002() {

		try {
			Key key = keyPair.getPublic();
			kf = KeyFactory.getInstance("DH");
			X509EncodedKeySpec X509Spec = new X509EncodedKeySpec(key
					.getEncoded());
			publicKey = kf.generatePublic(X509Spec);
			ka.init(keyPair.getPrivate());

			ka.generateSecret(sharedSecret, offset);

			fail("Should raise IllegalStateException");
		} catch (IllegalStateException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testGenerateSecretByteArrayInt003() {

		try {
			Key key = keyPair.getPublic();
			kf = KeyFactory.getInstance("DH");
			X509EncodedKeySpec X509Spec = new X509EncodedKeySpec(key
					.getEncoded());
			publicKey = kf.generatePublic(X509Spec);
			ka.init(keyPair.getPrivate());
			ka.doPhase(publicKey, false);

			ka.generateSecret(sharedSecret, offset);

			fail("Should raise IllegalStateException");
		} catch (IllegalStateException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}


	public final void testGenerateSecretByteArrayInt004() {
		try {
			Key key = keyPair.getPublic();
			kf = KeyFactory.getInstance("DH");
			X509EncodedKeySpec X509Spec = new X509EncodedKeySpec(key
					.getEncoded());
			publicKey = kf.generatePublic(X509Spec);
			ka.init(keyPair.getPrivate());

			ka.doPhase(publicKey, lastPhase);
			byte[] sharedSecret = new byte[30];
			int offset = 3;

			ka.generateSecret(sharedSecret, offset);
			ka.generateSecret(sharedSecret, offset);

			fail("Should raise ShortBufferException");
		} catch (ShortBufferException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testGenerateSecretByteArrayInt005() {

		try {
			Key key = keyPair.getPublic();
			kf = KeyFactory.getInstance("DH");
			X509EncodedKeySpec X509Spec = new X509EncodedKeySpec(key
					.getEncoded());
			publicKey = kf.generatePublic(X509Spec);
			ka.init(keyPair.getPrivate());
			ka.doPhase(publicKey, lastPhase);
			byte[] sharedSecret = new byte[1024];

			ka.generateSecret(sharedSecret, 1050);

			fail("Should raise ShortBufferException");
		} catch (ShortBufferException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testGenerateSecretByteArrayInt006() {

		try {
			Key key = keyPair.getPublic();
			kf = KeyFactory.getInstance("DH");
			X509EncodedKeySpec X509Spec = new X509EncodedKeySpec(key
					.getEncoded());
			publicKey = kf.generatePublic(X509Spec);
			ka.init(keyPair.getPrivate());
			ka.doPhase(publicKey, lastPhase);
			byte[] sharedSecret = new byte[1024];

			ka.generateSecret(sharedSecret, -10);

			fail("Should raise ArrayIndexOutOfBoundsException");
		} catch (ArrayIndexOutOfBoundsException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testGenerateSecretByteArrayInt007() {

		try {
			Key key = keyPair.getPublic();
			kf = KeyFactory.getInstance("DH");
			X509EncodedKeySpec X509Spec = new X509EncodedKeySpec(key
					.getEncoded());
			publicKey = kf.generatePublic(X509Spec);
			ka.init(keyPair.getPrivate());
			ka.doPhase(publicKey, true);
			byte[] sharedSecret = new byte[1024];
			byte[] sharedSecret2 = new byte[1024];
			int offset = 0;
			ka.generateSecret(sharedSecret, offset);
			ka.doPhase(publicKey, true);
			sharedSecret2 = ka.generateSecret();
			for (int i = 0; i < 128; i++) {
				assertEquals(sharedSecret[i], sharedSecret2[i]);
			}
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testGenerateSecretByteArrayInt008() {
		try {
			Key key = keyPair.getPublic();
			kf = KeyFactory.getInstance("DH");
			X509EncodedKeySpec X509Spec = new X509EncodedKeySpec(key
					.getEncoded());
			publicKey = kf.generatePublic(X509Spec);
			ka.init(keyPair.getPrivate());
			ka.doPhase(publicKey, true);
			byte[] sharedSecret = new byte[1024];
			byte[] sharedSecret2 = new byte[1024];
			int offset = 3;
			ka.generateSecret(sharedSecret, offset);
			ka.doPhase(publicKey, true);
			sharedSecret2 = ka.generateSecret();
			for (int i = 0; i < (128 - offset); i++) {
				if (i < offset) {
					assertEquals(sharedSecret[i], 0);
				}
				assertEquals(sharedSecret[i + offset], sharedSecret2[i]);
			}
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testGenerateSecretByteArrayInt009() {

		try {
			Key key = keyPair.getPublic();
			kf = KeyFactory.getInstance("DH");
			X509EncodedKeySpec X509Spec = new X509EncodedKeySpec(key
					.getEncoded());
			publicKey = kf.generatePublic(X509Spec);
			ka.init(keyPair.getPrivate());
			ka.doPhase(publicKey, lastPhase);
			byte[] sharedSecret = new byte[1024];
			int offset = 3;
			ka.generateSecret(sharedSecret, offset);
			ka.generateSecret(sharedSecret, offset);
			fail("Should raise IllegalStateException");
		} catch (IllegalStateException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	/*
	 * Test method for 'javax.crypto.KeyAgreement.generateSecret(String)'
	 */
	public final void testGenerateSecretString001() {

		try {
			Key key = keyPair.getPublic();
			kf = KeyFactory.getInstance("DH");
			X509EncodedKeySpec X509Spec = new X509EncodedKeySpec(key
					.getEncoded());
			publicKey = kf.generatePublic(X509Spec);
			ka.init(keyPair.getPrivate());
			ka.doPhase(publicKey, lastPhase);
			assertNotNull(msgNotNull, ka.generateSecret("DES"));
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testGenerateSecretString002() {
		try {
			Key key = keyPair.getPublic();
			kf = KeyFactory.getInstance("DH");
			X509EncodedKeySpec X509Spec = new X509EncodedKeySpec(key
					.getEncoded());
			publicKey = kf.generatePublic(X509Spec);
			ka.init(keyPair.getPrivate());
			// ka.doPhase(publicKey, lastPhase);

			try {
				ka.generateSecret("DES");
				fail("Should raise IllegalStateException");
			} catch (IllegalStateException e) {
				assertTrue(true);
			}
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testGenerateSecretString003() {

		try {
			Key key = keyPair.getPublic();
			kf = KeyFactory.getInstance("DH");
			X509EncodedKeySpec X509Spec = new X509EncodedKeySpec(key
					.getEncoded());
			publicKey = kf.generatePublic(X509Spec);
			ka.init(keyPair.getPrivate());
			ka.doPhase(publicKey, lastPhase);

			try {
				ka.generateSecret("DH");
				fail("Should raise NoSuchAlgorithmException");
			} catch (NoSuchAlgorithmException e) {
				assertTrue(true);
			}
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	public final void testGenerateSecretString005() throws Exception {
		Key key = keyPair.getPublic();
		kf = KeyFactory.getInstance("DH");
		DHPublicKey publicKey = (DHPublicKey) key;
		// publicKey = kf.generatePublic(X509Spec);
		ka.init(keyPair.getPrivate());
		ka.doPhase(publicKey, false);
		try {
			ka.generateSecret("Blowfish");
			fail("Should raise IllegalStateException");
		} catch (IllegalStateException e) {
			assertTrue(true);
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}

	}
}