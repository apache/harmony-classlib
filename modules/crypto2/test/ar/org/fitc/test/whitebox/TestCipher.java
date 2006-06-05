package ar.org.fitc.test.whitebox;

import java.nio.ByteBuffer;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import junit.framework.TestCase;
import ar.org.fitc.test.provider.NonProvider;
import ar.org.fitc.test.util.K;

public class TestCipher extends TestCase {

    static {
        Security.addProvider(new NonProvider());
    }
    
	private Cipher cipher;
	protected void setUp() throws Exception {
		super.setUp();
		cipher = Cipher.getInstance("DES");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testDoFinalWrongState001() throws Exception {
		cipher.init(Cipher.WRAP_MODE, new SecretKeySpec(new byte[]{1,2,3,4,5,6,7,8}, "DES"));
		try {
			cipher.doFinal(new byte[]{1,2,3,4,5,6,7,8});
			fail("java.lang.IllegalStateException: Cipher not initialized for encryption/decryption");
		} catch (IllegalStateException e) {
		}
	}
	
	public void testDoFinalWrongState002() throws Exception {
		cipher.init(Cipher.UNWRAP_MODE, new SecretKeySpec(new byte[]{1,2,3,4,5,6,7,8}, "DES"));
		try {
			cipher.doFinal(new byte[]{1,2,3,4,5,6,7,8});
			fail("java.lang.IllegalStateException: Cipher not initialized for encryption/decryption");
		} catch (IllegalStateException e) {
		}
	}
	public void testDoFinalWrongState003() throws Exception {
		cipher.init(Cipher.WRAP_MODE, new SecretKeySpec(new byte[]{1,2,3,4,5,6,7,8}, "DES"));
		try {
			cipher.doFinal(new byte[]{1,2,3,4,5,6,7,8}, 0, 0, new byte[16]);
			fail("java.lang.IllegalStateException: Cipher not initialized for encryption/decryption");
		} catch (IllegalStateException e) {
		}
	}
	
	public void testDoFinalWrongState004() throws Exception {
		cipher.init(Cipher.UNWRAP_MODE, new SecretKeySpec(new byte[]{1,2,3,4,5,6,7,8}, "DES"));
		try {
			cipher.doFinal(new byte[]{1,2,3,4,5,6,7,8}, 0, 0, new byte[16]);
			fail("java.lang.IllegalStateException: Cipher not initialized for encryption/decryption");
		} catch (IllegalStateException e) {
		}
	}
	public void testUpdatelWrongState001() throws Exception {
		cipher.init(Cipher.WRAP_MODE, new SecretKeySpec(new byte[]{1,2,3,4,5,6,7,8}, "DES"));
		try {
			cipher.update(new byte[]{1,2,3,4,5,6,7,8});
			fail("java.lang.IllegalStateException: Cipher not initialized for encryption/decryption");
		} catch (IllegalStateException e) {
		}
	}
	
	public void testUpdatelWrongState002() throws Exception {
		cipher.init(Cipher.UNWRAP_MODE, new SecretKeySpec(new byte[]{1,2,3,4,5,6,7,8}, "DES"));
		try {
			cipher.update(new byte[]{1,2,3,4,5,6,7,8});
			fail("java.lang.IllegalStateException: Cipher not initialized for encryption/decryption");
		} catch (IllegalStateException e) {
		}
	}
	
	public void testWrapWrongState001() throws Exception {
		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(new byte[]{1,2,3,4,5,6,7,8}, "DES"));
		try {
			cipher.wrap(new SecretKeySpec(new byte[]{1,2,3,4,5,6,7,8}, "DES"));
			fail("java.lang.IllegalStateException: Cipher not initialized for wrapping keys");
		} catch (IllegalStateException e) {
		}
	}
	
	public void testWrapWrongState002() throws Exception {
		cipher.init(Cipher.UNWRAP_MODE, new SecretKeySpec(new byte[]{1,2,3,4,5,6,7,8}, "DES"));
		try {
			cipher.wrap(new SecretKeySpec(new byte[]{1,2,3,4,5,6,7,8}, "DES"));
			fail("java.lang.IllegalStateException: Cipher not initialized for wrapping keys");
		} catch (IllegalStateException e) {
		}
	}
	public void testWrapWrongState003() throws Exception {
		cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(new byte[]{1,2,3,4,5,6,7,8}, "DES"));
		try {
			cipher.wrap(new SecretKeySpec(new byte[]{1,2,3,4,5,6,7,8}, "DES"));
			fail("java.lang.IllegalStateException: Cipher not initialized for wrapping keys");
		} catch (IllegalStateException e) {
		}
	}
	
	public void testUnwrapWrongState001() throws Exception {
		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(new byte[]{1,2,3,4,5,6,7,8}, "DES"));
		try {
			cipher.unwrap(new byte[]{1,2,3,4,5,6,7,8}, "DES",Cipher.SECRET_KEY);
			fail("java.lang.IllegalStateException: Cipher not initialized for unwrapping keys");
		} catch (IllegalStateException e) {
		}
	}
	
	public void testUnwrapWrongState002() throws Exception {
		cipher.init(Cipher.WRAP_MODE, new SecretKeySpec(new byte[]{1,2,3,4,5,6,7,8}, "DES"));
		try {
			cipher.unwrap(new byte[]{1,2,3,4,5,6,7,8}, "DES",Cipher.SECRET_KEY);
			fail("java.lang.IllegalStateException: Cipher not initialized for unwrapping keys");
		} catch (IllegalStateException e) {
		}
	}
	public void testUnwrapWrongState003() throws Exception {
		cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(new byte[]{1,2,3,4,5,6,7,8}, "DES"));
		try {
			cipher.unwrap(new byte[]{1,2,3,4,5,6,7,8}, "DES",Cipher.SECRET_KEY);
			fail("java.lang.IllegalStateException: Cipher not initialized for unwrapping keys");
		} catch (IllegalStateException e) {
		}
	}
	
	public void testDoFinalSameByteArray001() throws Exception {
		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(new byte[]{1,2,3,4,5,6,7,8}, "DES"));
		ByteBuffer b1 = ByteBuffer.allocate(20);
		b1.put((byte) 1);
		try {
			cipher.doFinal(b1, b1);
		} catch(IllegalArgumentException e) {
		}
		
	}

//	public void testDoFinalReadOnlyByteBuffer() throws Exception {
//		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(new byte[]{1,2,3,4,5,6,7,8}, "DES"));
//		ByteBuffer b1 = ByteBuffer.allocate(20);
//		b1.put((byte) 1);
//		ByteBuffer b2 = ByteBuffer.allocate(20).asReadOnlyBuffer();
//		try {
//			cipher.doFinal(b1, b2);
//		} catch(IllegalArgumentException e) {
//			fail("Considerate this a copy-safe result? throw " + e);
//		}
//		
//	}
	
//	public void testUpdateCopySafe001() throws Exception {
//		cipher = Cipher.getInstance("Non", "ITC");
//		byte[] b = new byte[]{1,2,3,4,5,6,7,8};//,1,2,3,4,5,6,7,8};
//		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(b, "DES"));
//		cipher.update(b,0,8,b,0);
//		
//	}
	
	public void testGetInstanceNoSuchPaddingException001() throws Exception {
		try {
			Cipher.getInstance("Non/hola/manola", "ITC");
			fail("Provider throw NoSuchPaddingException");
		} catch (NoSuchPaddingException e) {
		}
	}
	
	public void testGetInstanceNoSuchPaddingException002() throws Exception {
		Cipher.getInstance("Null/Non/Non", "ITC");
	}
	
	public void testGetInstanceNoSuchPaddingException003() throws Exception {
		Cipher.getInstance("Non/Non/Non", "ITC");
	}
	
	public void testGetInstanceNoSuchPaddingException004() throws Exception {
		try {
			cipher = Cipher.getInstance("Non/Non/manola", "ITC");
			fail("Provider throw NoSuchPaddingException");
		} catch (NoSuchPaddingException e) {
		}
	}

	public void testInitEmptyKey() throws Exception {
		cipher = Cipher.getInstance("DES", "ITC");
		cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[0]));			
		
	}
	
	
	public void testInitNotComplite() throws Exception {
		cipher = Cipher.getInstance("Non", "ITC");
		try {
			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(new byte[]{1}, "Non"), (AlgorithmParameters) null);
			fail("Non algorithm not work with null algorithm parameters");
		} catch (InvalidAlgorithmParameterException e) {
		}
		try {
			cipher.doFinal();
			fail("Cipher not initialized");
		} catch (IllegalStateException e) {
		}
	}

}
