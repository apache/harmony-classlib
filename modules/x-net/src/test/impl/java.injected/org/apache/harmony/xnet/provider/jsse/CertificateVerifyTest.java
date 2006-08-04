package org.apache.harmony.xnet.provider.jsse;

import java.util.Arrays;

import junit.framework.TestCase;

/**
 * Tests for <code>CertificateVerify</code> constructor and methods
 * 
 */
public class CertificateVerifyTest extends TestCase {

	public void testCertificateVerify() throws Exception {
		byte[] anonHash = new byte[0];
		byte[] RSAHash = new byte[] {
				1, 2, 3, 4, 5, 6, 7, 8, 9, 0,
				1, 2, 3, 4, 5, 6, 7, 8, 9, 0,
				1, 2, 3, 4, 5, 6, 7, 8, 9, 0,
				1, 2, 3, 4, 5, 6};
		byte[] DSAHash  = new byte[] {
				1, 2, 3, 4, 5, 6, 7, 8, 9, 0,
				1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
		byte[][] signatures = new byte[][] { anonHash, RSAHash, DSAHash };
		for (int i = 0; i < 3; i++) {
			CertificateVerify message = new CertificateVerify(signatures[i]);
            assertEquals("incorrect type", Handshake.CERTIFICATE_VERIFY,
                    message.getType());
            assertTrue("incorrect CertificateVerify", 
                    Arrays.equals(message.signedHash, signatures[i]));

			HandshakeIODataStream out = new HandshakeIODataStream();
			message.send(out);
			byte[] encoded = out.getData(1000);
            assertEquals("incorrect out data length", message.length(),
                    encoded.length);

			HandshakeIODataStream in = new HandshakeIODataStream();
			in.append(encoded);
			CertificateVerify message_2 = new CertificateVerify(in, message.length());
            assertTrue("incorrect message decoding", 
                    Arrays.equals(message.signedHash, message_2.signedHash));

			in.append(encoded);
			try {
				message_2 = new CertificateVerify(in, message.length() - 1);
				fail("Small length: No expected AlertException");
			} catch (AlertException e) {
			}

			in.append(encoded);
			in.append(new byte[] { 1, 2, 3 });
			try {
				message_2 = new CertificateVerify(in, message.length() + 3);
				fail("Extra bytes: No expected AlertException ");
			} catch (AlertException e) {
			}
		}
	}

}
