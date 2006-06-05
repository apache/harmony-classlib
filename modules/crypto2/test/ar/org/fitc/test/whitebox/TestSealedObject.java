package ar.org.fitc.test.whitebox;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SealedObject;
import javax.crypto.spec.SecretKeySpec;

import junit.framework.TestCase;

import ar.org.fitc.test.provider.NonProvider;

public class TestSealedObject extends TestCase {
 
	private SealedObject seaObj;
	private Cipher cipher;
	private Key key;
	
	protected void setUp() throws Exception {
//		PBEParameterSpec paramSpec = new PBEParameterSpec(new byte[] {1,2,3,4,5,6,7,8},5);
//		AlgorithmParameters param = AlgorithmParameters.getInstance("PBEWithMD5anddes");
//		param.init(paramSpec);
//		KeyPairGenerator kgen= KeyPairGenerator.getInstance("DH");
//		kgen.initialize(1024);
//		KeyPair kp = kgen.generateKeyPair();
		key = new SecretKeySpec(new byte[] {1,2,4,4,6,6,7,8}, "des");
		cipher = Cipher.getInstance("Des/CFB/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		seaObj = new SealedObject(key,cipher);
        System.out.print(Security.addProvider((NonProvider) new NonProvider()));
		super.setUp();
	}

	
	/*
	 * Test method for 'javax.crypto.SealedObject.getObject(Cipher)'
	 */
	public void testGetObjectCipher() {
	    for (int i = 0; i < Security.getProviders().length; i++) {
            System.out.println(Security.getProviders()[i].getName()); 
        }
	}

	/*
	 * Test method for 'javax.crypto.SealedObject.getObject(Key, String)'
	 */
	public void testGetObjectKeyString() throws Exception {
		try {
			seaObj.getObject(key, "ITC");
			fail("ITC provider throw NoSuchPaddingException");
		} catch (NoSuchAlgorithmException e) {
		}
	}

	/*
	 * Test method for 'javax.crypto.SealedObject.getObject(Key)'
	 */
//	public void testGetObjectKey() throws Exception {
//		for (Provider p : Security.getProviders().clone()) {
//			if (!p.getName().equals("ITC"))
//				Security.removeProvider(p.getName());
//		}
//		assertEquals(1,Security.getProviders().length);
//		assertEquals("ITC",Security.getProviders()[0].getName());
//		}
//		try {
//			seaObj.getObject(key);
//			fail("ITC provider throw NoSuchPaddingException");
//		} catch (NoSuchAlgorithmException e) {
//		}
//	}

}
