package org.apache.harmony.xnet.tests.provider.jsse;

import java.io.IOException;

import org.apache.harmony.xnet.provider.jsse.AlertException;
import org.apache.harmony.xnet.provider.jsse.Handshake;
import org.apache.harmony.xnet.provider.jsse.HandshakeIODataStream;
import org.apache.harmony.xnet.provider.jsse.ServerHelloDone;

import junit.framework.TestCase;

/**
 * Tests for <code>ServerHelloDone</code> constructor and methods
 *  
 */
public class ServerHelloDoneTest extends TestCase {

    /*
     * Class under test for void ServerHelloDone()
     */
    public void testServerHelloDone() throws Exception {

        ServerHelloDone message = new ServerHelloDone();
        assertEquals("incorrect type", Handshake.SERVER_HELLO_DONE, message
                .getType());
        assertEquals("incorrect ServerHelloDone", 0, message.length());

        HandshakeIODataStream out = new HandshakeIODataStream();
        message.send(out);
        byte[] encoded = out.getData(1000);
        assertEquals("incorrect out data length", message.length(),
                encoded.length);

        HandshakeIODataStream in = new HandshakeIODataStream();
        in.append(encoded);
        ServerHelloDone message_2 = new ServerHelloDone(in, message.length());
        assertEquals("incorrect message decoding", 0, message_2.length());

        in.append(encoded);
        try {
            new ServerHelloDone(in, message.length() - 1);
            fail("Small length: No expected AlertException");
        } catch (AlertException e) {
        }

        in.append(encoded);
        in.append(new byte[] { 1, 2, 3 });
        try {
            new ServerHelloDone(in, message.length() + 3);
            fail("Extra bytes: No expected AlertException ");
        } catch (AlertException e) {
        }
    }

}
