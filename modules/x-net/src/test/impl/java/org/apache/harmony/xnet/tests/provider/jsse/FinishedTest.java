package org.apache.harmony.xnet.tests.provider.jsse;

import java.util.Arrays;

import org.apache.harmony.xnet.provider.jsse.AlertException;
import org.apache.harmony.xnet.provider.jsse.Finished;
import org.apache.harmony.xnet.provider.jsse.Handshake;
import org.apache.harmony.xnet.provider.jsse.HandshakeIODataStream;

import junit.framework.TestCase;

/**
 * Tests for <code>Finished</code> constructor and methods
 *  
 */
public class FinishedTest extends TestCase {

    public void testFinished() throws Exception {
        byte[] bytes = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2 };
        Finished message = new Finished(bytes);
        assertEquals("incorrect type", Handshake.FINISHED, message.getType());
        assertTrue("incorrect CertificateVerify", Arrays.equals(message
                .getData(), bytes));

        HandshakeIODataStream out = new HandshakeIODataStream();
        message.send(out);
        byte[] encoded = out.getData(1000);
        assertEquals("incorrect out data length", message.length(),
                encoded.length);

        HandshakeIODataStream in = new HandshakeIODataStream();
        in.append(encoded);
        Finished message_2 = new Finished(in, message.length());
        assertTrue("incorrect message decoding", Arrays.equals(message
                .getData(), message_2.getData()));

        in.append(encoded);
        try {
            message_2 = new Finished(in, message.length() - 1);
            fail("Small length: No expected AlertException");
        } catch (AlertException e) {
        }

        in.append(encoded);
        in.append(new byte[] { 1, 2, 3 });
        try {
            message_2 = new Finished(in, message.length() + 3);
            fail("Extra bytes: No expected AlertException ");
        } catch (AlertException e) {
        }
    }

}
