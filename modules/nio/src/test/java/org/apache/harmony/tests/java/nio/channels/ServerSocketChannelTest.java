/* Copyright 2005, 2006 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.harmony.tests.java.nio.channels;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.NotYetBoundException;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;

import junit.framework.TestCase;
import tests.support.Support_PortManager;

/*
 * test for ServerSocketChannel
 */
public class ServerSocketChannelTest extends TestCase {

    private static final int CAPACITY_NORMAL = 200;

    private static final int CAPACITY_64KB = 65536;

    private static final int TIME_UNIT = 200;

    private InetSocketAddress localAddr1;

    private ServerSocketChannel serverChannel;

    private SocketChannel clientChannel;

    protected void setUp() throws Exception {
        super.setUp();
        this.localAddr1 = new InetSocketAddress(
                "127.0.0.1", Support_PortManager 
                        .getNextPort());
        this.serverChannel = ServerSocketChannel.open();
        this.clientChannel = SocketChannel.open();
    }

    protected void tearDown() throws Exception {
        if (null != this.serverChannel) {
            try {
                this.serverChannel.close();
            } catch (Exception e) {
                //ignore
            }

        }
        if (null != this.clientChannel) {
            try {
                this.clientChannel.close();
            } catch (Exception e) {
                //ignore
            }
        }
        super.tearDown();
    }

    // -------------------------------------------------------------------
    // Test for methods in abstract class.
    // -------------------------------------------------------------------

    /*
     * Test method for 'java.nio.channels.ServerSocketChannel.validOps()'
     */
    public void testValidOps() {
        MockServerSocketChannel testMSChnlnull = new MockServerSocketChannel(
                null);
        MockServerSocketChannel testMSChnl = new MockServerSocketChannel(
                SelectorProvider.provider());
        assertEquals(SelectionKey.OP_ACCEPT, this.serverChannel.validOps());
        assertEquals(SelectionKey.OP_ACCEPT, testMSChnl.validOps());
        assertEquals(SelectionKey.OP_ACCEPT, testMSChnlnull.validOps());

    }

    /*
     * Test method for 'java.nio.channels.ServerSocketChannel.open()'
     */
    public void testOpen() {
        MockServerSocketChannel testMSChnl = new MockServerSocketChannel(null);
        MockServerSocketChannel testMSChnlnotnull = new MockServerSocketChannel(
                SelectorProvider.provider());
        assertEquals(SelectionKey.OP_ACCEPT, testMSChnlnotnull.validOps());
        assertNull(testMSChnl.provider());
        assertNotNull(testMSChnlnotnull.provider());
        assertNotNull(this.serverChannel.provider());
        assertEquals(testMSChnlnotnull.provider(), this.serverChannel
                .provider());
    }

    // -------------------------------------------------------------------
    // Test for socket()
    // -------------------------------------------------------------------

    /*
     * Test method for 'com.ibm.io.nio.ServerSocketChannelImpl.socket()'
     */
    public void testSocket_Block_BeforeClose() throws Exception {
        assertTrue(this.serverChannel.isOpen());
        assertTrue(this.serverChannel.isBlocking());
        ServerSocket s1 = this.serverChannel.socket();
        assertFalse(s1.isClosed());
        assertSocketNotAccepted(s1);
        ServerSocket s2 = this.serverChannel.socket();
        // same
        assertSame(s1, s2);

        // socket close makes the channel close
        s1.close();
        assertFalse(this.serverChannel.isOpen());

    }

    public void testSocket_NonBlock_BeforeClose() throws Exception {
        assertTrue(this.serverChannel.isOpen());
        this.serverChannel.configureBlocking(false);
        ServerSocket s1 = this.serverChannel.socket();
        assertFalse(s1.isClosed());
        assertSocketNotAccepted(s1);
        ServerSocket s2 = this.serverChannel.socket();
        // same
        assertSame(s1, s2);

        // socket close makes the channel close
        s1.close();
        assertFalse(this.serverChannel.isOpen());

    }

    public void testSocket_Block_Closed() throws Exception {
        this.serverChannel.close();
        assertFalse(this.serverChannel.isOpen());
        assertTrue(this.serverChannel.isBlocking());
        ServerSocket s1 = this.serverChannel.socket();
        assertTrue(s1.isClosed());
        assertSocketNotAccepted(s1);
        ServerSocket s2 = this.serverChannel.socket();
        // same
        assertSame(s1, s2);
    }

    public void testSocket_NonBlock_Closed() throws Exception {
        this.serverChannel.configureBlocking(false);
        this.serverChannel.close();
        assertFalse(this.serverChannel.isBlocking());
        assertFalse(this.serverChannel.isOpen());
        ServerSocket s1 = this.serverChannel.socket();
        assertTrue(s1.isClosed());
        assertSocketNotAccepted(s1);
        ServerSocket s2 = this.serverChannel.socket();
        // same
        assertSame(s1, s2);
    }

    private void assertSocketNotAccepted(ServerSocket s) throws IOException {
        assertFalse(s.isBound());
        assertNull(s.getInetAddress());
        assertEquals(-1, s.getLocalPort());
        assertNull(s.getLocalSocketAddress());
        assertEquals(0, s.getSoTimeout());
    }

    public void testChannelBasicStatus() {
        ServerSocket gotSocket = this.serverChannel.socket();
        assertFalse(gotSocket.isClosed());
        assertTrue(this.serverChannel.isBlocking());
        assertFalse(this.serverChannel.isRegistered());
        assertEquals(SelectionKey.OP_ACCEPT, this.serverChannel.validOps());
        assertEquals(SelectorProvider.provider(), this.serverChannel.provider());
    }

    // -------------------------------------------------------------------
    // Test for accept()
    // -------------------------------------------------------------------

    /*
     * Test method for 'com.ibm.io.nio.ServerSocketChannelImpl.accept()'
     */

    public void testAccept_Block_NotYetBound() throws IOException {
        assertTrue(this.serverChannel.isOpen());
        assertTrue(this.serverChannel.isBlocking());
        try {
            this.serverChannel.accept();
            fail("Should throw NotYetBoundException"); 
        } catch (NotYetBoundException e) {
            // correct
        }
    }

    public void testAccept_NonBlock_NotYetBound() throws IOException {
        assertTrue(this.serverChannel.isOpen());
        this.serverChannel.configureBlocking(false);
        try {
            this.serverChannel.accept();
            fail("Should throw NotYetBoundException"); 
        } catch (NotYetBoundException e) {
            // correct
        }
    }

    public void testAccept_ClosedChannel() throws Exception {
        this.serverChannel.close();
        assertFalse(this.serverChannel.isOpen());
        try {
            this.serverChannel.accept();
            fail("Should throw ClosedChannelException"); 
        } catch (ClosedChannelException e) {
            // OK.
        }
    }

    public void testAccept_Block_NoConnect() throws IOException {
        assertTrue(this.serverChannel.isBlocking());
        ServerSocket gotSocket = this.serverChannel.socket();
        gotSocket.bind(localAddr1);         
        // blocking mode , will block and wait for ever...
        // so must close the server channel with another thread.
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(TIME_UNIT);
                    ServerSocketChannelTest.this.serverChannel.close();
                } catch (Exception e) {
                    fail("Fail to close the server channel because of"
                            + e.getClass().getName());
                }
            }
        }.start();
        try {
            this.serverChannel.accept();
            fail("Should throw a AsynchronousCloseException");
        } catch (AsynchronousCloseException e) {
            // OK.
        }
    }

    public void testAccept_NonBlock_NoConnect() throws IOException {
        ServerSocket gotSocket = this.serverChannel.socket();
        gotSocket.bind(localAddr1); 
        this.serverChannel.configureBlocking(false);
        // non-blocking mode , will immediately return
        assertNull(this.serverChannel.accept());
    }

    public void testReadWrite_Blocking_RealData() throws IOException {

        assertTrue(this.serverChannel.isBlocking());
        ServerSocket serverSocket = this.serverChannel.socket();
        serverSocket.bind(localAddr1);

        byte[] serverWBuf = new byte[CAPACITY_NORMAL];
        byte[] serverRBuf = new byte[CAPACITY_NORMAL];
        for (int i = 0; i < serverWBuf.length; i++) {
            serverWBuf[i] = (byte) i;
        }
        java.nio.ByteBuffer buf = java.nio.ByteBuffer.allocate(CAPACITY_NORMAL);

        this.clientChannel.connect(localAddr1); 
        Socket clientSocket = this.serverChannel.accept().socket();
        assertTrue(this.clientChannel.isConnected());
        assertTrue(clientSocket.isConnected());
        OutputStream out = clientSocket.getOutputStream();
        InputStream in = clientSocket.getInputStream();
        out.write(serverWBuf);
        assertEquals(CAPACITY_NORMAL, this.clientChannel.read(buf));
        buf.flip();
        assertEquals(66051, buf.asIntBuffer().get());
        assertEquals(CAPACITY_NORMAL, this.clientChannel.write(buf));
        in.read(serverRBuf);
        for (int i = 0; i < serverRBuf.length; i++) {
            assertEquals((byte) i, serverRBuf[i]);
        }
        this.clientChannel.close();
        try {
            assertEquals(CAPACITY_NORMAL, this.clientChannel.read(buf));
            fail("Should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // correct
        }
    }

    public void testReadWrite_NonBlocking_RealData() throws Exception {

        this.serverChannel.configureBlocking(false);
        ServerSocket serverSocket = this.serverChannel.socket();
        serverSocket.bind(localAddr1);

        byte[] serverWBuf = new byte[CAPACITY_NORMAL];
        byte[] serverRBuf = new byte[CAPACITY_NORMAL];
        for (int i = 0; i < serverWBuf.length; i++) {
            serverWBuf[i] = (byte) i;
        }
        java.nio.ByteBuffer buf = java.nio.ByteBuffer.allocate(CAPACITY_NORMAL);
        this.clientChannel.connect(localAddr1); 
        Socket clientSocket = this.serverChannel.accept().socket();
        if (this.clientChannel.isConnected()) {
            OutputStream out = clientSocket.getOutputStream();
            InputStream in = clientSocket.getInputStream();
            out.write(serverWBuf);
            int readCount = this.clientChannel.read(buf);
            if (readCount != 0) {
                assertEquals(CAPACITY_NORMAL, readCount);
                buf.flip();
                assertEquals(66051, buf.asIntBuffer().get());
                assertEquals(CAPACITY_NORMAL, this.clientChannel.write(buf));
                in.read(serverRBuf);
                for (int i = 0; i < serverRBuf.length; i++) {
                    assertEquals((byte) i, serverRBuf[i]);
                }
            } else {
                System.err
                        .println("Read fail,testReadByteBuffer_NonBlocking_ReadWriteRealData is not finished.");
            }
        } else {
            System.err
                    .println("Connection fail, testReadByteBuffer_NonBlocking_ReadWriteRealData is not finished.");
        }

        this.clientChannel.close();
        try {
            assertEquals(CAPACITY_NORMAL, this.clientChannel.read(buf));
            fail("Should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // correct
        }
    }

    public void testReadByteBuffer_Blocking_ReadWriteRealLargeData()
            throws IOException {

        assertTrue(this.serverChannel.isBlocking());
        ServerSocket serverSocket = this.serverChannel.socket();
        serverSocket.bind(localAddr1);

        byte[] serverWBuf = new byte[CAPACITY_64KB];
        byte[] serverRBuf = new byte[CAPACITY_64KB];
        for (int i = 0; i < serverWBuf.length; i++) {
            serverWBuf[i] = (byte) i;
        }
        java.nio.ByteBuffer buf = java.nio.ByteBuffer
                .allocateDirect(CAPACITY_64KB);
        this.clientChannel.connect(localAddr1); 
        Socket clientSocket = this.serverChannel.accept().socket();
        assertTrue(this.clientChannel.isConnected());
        OutputStream out = clientSocket.getOutputStream();
        InputStream in = clientSocket.getInputStream();
        out.write(serverWBuf);
        int count = 0;
        int total = 0;
        while ((count = this.clientChannel.read(buf)) != 0){
            total = total + count;
        }
        if (0 != total){
            assertEquals(total, CAPACITY_64KB);
        }
        buf.flip();
        assertEquals(66051, buf.asIntBuffer().get());
        assertEquals(CAPACITY_64KB, this.clientChannel.write(buf));
        count = in.read(serverRBuf);
        for (int i = 0; i < count; i++) {
            assertEquals((byte) i, serverRBuf[i]);
        }
        this.clientChannel.close();
        try {
            assertEquals(CAPACITY_NORMAL, this.clientChannel.read(buf));
            fail("Should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // correct
        }
    }

    public void testReadByteBuffer_NonBlocking_ReadWriteRealLargeData()
            throws Exception {

        this.serverChannel.configureBlocking(false);
        ServerSocket serverSocket = this.serverChannel.socket();
        serverSocket.bind(localAddr1);

        byte[] serverWBuf = new byte[CAPACITY_64KB];
        byte[] serverRBuf = new byte[CAPACITY_64KB];
        for (int i = 0; i < serverWBuf.length; i++) {
            serverWBuf[i] = (byte) i;
        }
        java.nio.ByteBuffer buf = java.nio.ByteBuffer.allocate(CAPACITY_64KB);
        this.clientChannel.connect(localAddr1); 
        Socket clientSocket = this.serverChannel.accept().socket();
        if (this.clientChannel.isConnected()) {
            OutputStream out = clientSocket.getOutputStream();
            InputStream in = clientSocket.getInputStream();
            out.write(serverWBuf);
            int count = 0;
            int total = 0;
            while ((count = this.clientChannel.read(buf)) != 0){
                total = total + count;
            }
            assertEquals(total, CAPACITY_64KB);
            buf.flip();
            assertEquals(66051, buf.asIntBuffer().get());
            assertEquals(CAPACITY_64KB, this.clientChannel.write(buf));
            count = in.read(serverRBuf);
            for (int i = 0; i < count; i++) {
                assertEquals((byte) i, serverRBuf[i]);
            }
        } else {
            System.err
                    .println("Connection fail, testReadByteBuffer_NonBlocking_ReadWriteRealLargeData is not finished.");
        }
        this.clientChannel.close();
        try {
            assertEquals(CAPACITY_NORMAL, this.clientChannel.read(buf));
            fail("Should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // correct
        }
    }
    
    public void test_accept_SOTIMEOUT() throws IOException {
        // regression test for Harmony-707        
        final int SO_TIMEOUT = 10;
        ServerSocketChannel sc = ServerSocketChannel.open();
        try {
            ServerSocket ss = sc.socket();
            ss.bind(localAddr1);
            sc.configureBlocking(false);
            ss.setSoTimeout(SO_TIMEOUT);
            SocketChannel client = sc.accept();
            // non blocking mode, returns null since there are no pending connections.
            assertNull(client);
            int soTimeout = ss.getSoTimeout();
            // Harmony fails here.
            assertEquals(SO_TIMEOUT, soTimeout);
        } finally {
            sc.close();
        }
    }
}
