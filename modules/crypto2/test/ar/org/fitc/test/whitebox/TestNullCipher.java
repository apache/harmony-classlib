package ar.org.fitc.test.whitebox;

import javax.crypto.Cipher;
import javax.crypto.NullCipher;

import junit.framework.TestCase;

public class TestNullCipher extends TestCase {

	protected void setUp() throws Exception {
		
		super.setUp();
	}
	
	public void testGetTranformation() {
		assertNull(new NullCipher().getAlgorithm());
	}

	public void testGetProvider() {
		assertNull(new NullCipher().getProvider());
	}
	
	public void testUpdateShortBufferException() throws Exception {
		Cipher ci = new NullCipher();
		ci.update(new byte[8], 0,8, new byte[8], 0);
	}
}
