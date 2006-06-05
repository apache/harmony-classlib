package ar.org.fitc.test.whitebox;

import javax.crypto.Mac;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;

import junit.framework.TestCase;

public class TestMac extends TestCase {
	
	protected void setUp() throws Exception {
		super.setUp();
	}
	

	public void testReset001() throws Exception {
		Mac m = Mac.getInstance("DES");
		m.reset();
	}

	public void testDoFinal001() throws Exception {
		Mac m = Mac.getInstance("DES");
		
		m.init(new SecretKeySpec(new byte[]{1,2,3,4,5,6,7,8}, "DES"));
		m.update(new byte[]{1,2,3,4,5,6,7,8});
		try {
			m.doFinal(new byte[0], 0);
		} catch (ShortBufferException e) {
		}

	}
	
}
