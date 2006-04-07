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
import java.net.SocketAddress;
import java.nio.ByteBuffer;
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

    private static final int capacityNormal = 200;

    private static final int capacity64KB = 65536;

    private static final int TIME_UNIT = 200;

    private InetSocketAddress localAddr1;

    private ServerSocketChannel serverChannel;

    private SocketChannel clientChannel;

    private static final String MSG_CLIENT = "this is the msg from client to server."; //$NON-NLS-1$

    private static final String MSG_SERVER = "the msg server send to client."; //$NON-NLS-1$

    protected void setUp() throws Exception {
        super.setUp();
        this.localAddr1 = new InetSocketAddress(
                "127.0.0.1", Support_PortManager //$NON-NLS-1$
                        .getNextPort());
        this.serverChannel = ServerSocketChannel.open();
        this.clientChannel = SocketChannel.open();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        if (null != this.serverChannel) {
            this.serverChannel.close();
        }
        if (null != this.clientChannel) {
            this.clientChannel.close();
        }
    }

    // TODO un-comment testAccept_Security

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

    // /////////
    private void assertSocketNotAccepted(ServerSocket s) throws IOException {
        assertFalse(s.isBound());
        assertNull(s.getInetAddress());
        assertEquals(-1, s.getLocalPort());
        assertNull(s.getLocalSocketAddress());
        assertEquals(0, s.getSoTimeout());
    }

    private void assertSocketAccepted(ServerSocket s, SocketAddress addr)
            throws IOException {
        assertTrue(s.isBound());
        assertEquals(((InetSocketAddress) addr).getAddress().getHostAddress(),
                s.getInetAddress().getHostAddress());
        assertEquals(((InetSocketAddress) addr).getPort(), s.getLocalPort());
        assertEquals((InetSocketAddress) addr, s.getLocalSocketAddress());
        assertEquals(0, s.getSoTimeout());
        assertTrue(8192 <= s.getReceiveBufferSize());
        // This function depends on OS
        // assertFalse(s.getReuseAddress());
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
    // Test for Security()
    // -------------------------------------------------------------------

    // public void testAccept_Security() throws Exception {
    // final SecurityManager sm = System.getSecurityManager();
    // class mySecurityManager extends SecurityManager {
    // public void checkPermission(Permission perm) {
    // if (perm.getName().equals("setSecurityManager")) {
    // return;
    // }
    // // super.checkPermission(perm);
    // }
    //
    // public void checkAccept(String host, int port) {
    // // we consider that the only correct addr is 127.0.0.1
    // if ("127.0.0.1".equals(host)) {
    // return;
    // } else {
    // // System.out.println("badhost = "+host);
    // throw new SecurityException();
    // }
    // }
    // }
    // try {
    // SocketAddress correctAddr = new InetSocketAddress("127.0.0.1", 1081);
    // SocketAddress badAddr = new InetSocketAddress("127.0.0.2", 1082);
    // // SocketAddress remoteAddr = new InetSocketAddress("9.181.106.225",
    // // 22 );
    // this.serverChannel.socket().bind(correctAddr);
    // this.clientChannel.connect(correctAddr);
    //
    // System.setSecurityManager(new mySecurityManager());
    //
    // // no problem.
    // this.serverChannel.accept();
    //
    // // reset the server and the client
    // this.serverChannel.close();
    // this.serverChannel = ServerSocketChannel.open();
    // this.clientChannel.close();
    // this.clientChannel = SocketChannel.open();
    //
    // // let the setup pass
    // System.setSecurityManager(sm);
    // this.serverChannel.socket().bind(badAddr);
    // // this.clientChannel.socket().bind(correctAddr);
    // this.clientChannel.connect(badAddr);
    // // System.out.println(this.clientChannel.socket().getLocalAddress());
    // // System.out.println(this.clientChannel.socket().getInetAddress());
    // System.setSecurityManager(new mySecurityManager());
    //
    // try {
    // SocketChannel sock = this.serverChannel.accept();
    // fail("Should throw SecurityException");
    // } catch (SecurityException ex) {
    // // OK.
    // }
    // } finally {
    // System.setSecurityManager(sm);
    // }
    // }

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
            fail("Should throw NotYetBoundException"); //$NON-NLS-1$
        } catch (NotYetBoundException e) {
            // correct
        }
    }

    public void testAccept_NonBlock_NotYetBound() throws IOException {
        assertTrue(this.serverChannel.isOpen());
        this.serverChannel.configureBlocking(false);
        try {
            this.serverChannel.accept();
            fail("Should throw NotYetBoundException"); //$NON-NLS-1$
        } catch (NotYetBoundException e) {
            // correct
        }
    }

    public void testAccept_ClosedChannel() throws Exception {
        this.serverChannel.close();
        assertFalse(this.serverChannel.isOpen());
        try {
            this.serverChannel.accept();
            fail("Should throw ClosedChannelException"); //$NON-NLS-1$
        } catch (ClosedChannelException e) {
            // OK.
        }
    }

    // -------------------------------------------------------------------
    // Test for data read/write with client and server.
    // -------------------------------------------------------------------
    // After reading this inner class, I found that serverChannel is initialized
    // and started out side this class, but used insided this class.
    // Conceptually,
    // this design dissever a whole process in a factitous way. I strongly argue
    // that
    // we put them into one class, which will also increase readability.

    public static class SocketChannelClient extends Thread {
        SocketChannel clientChannel;

        SocketAddress addr;

        public SocketChannelClient(SocketAddress serverAddr) {
            this.addr = serverAddr;
        }

        public void run() {
            try {
                Thread.currentThread().sleep(TIME_UNIT); // Go to sleep so
                // the server can setup and wait for connection
                clientChannel = SocketChannel.open();
                clientChannel.connect(this.addr);
                Thread.currentThread().sleep(TIME_UNIT);

                // Sleep again to allow server side processing. Thread is
                // stopped by server
                if (!clientChannel.finishConnect()) {
                     System.err.println("Client connect fail!"); //$NON-NLS-1$
                } else {
                    ByteBuffer sendBuf = ByteBuffer.wrap(MSG_CLIENT.getBytes());

                    Thread.currentThread().sleep(TIME_UNIT);
                    ByteBuffer receiveBuf = ByteBuffer.allocate(capacityNormal);
                    clientChannel.read(receiveBuf);
                    assertEquals(new String(receiveBuf.array()).trim(),
                            MSG_SERVER);
                }
            } catch (Throwable e) {
                System.err
                        .println("Error establishing client: " + e.toString()); //$NON-NLS-1$
            } finally {
                try {
                    if (null != clientChannel) {
                       clientChannel.close();
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    public void testAccept_Block_ClientThread() throws Exception {
        assertTrue(this.serverChannel.isBlocking());
        ServerSocket serverSocket = this.serverChannel.socket();
        serverSocket.bind(localAddr1);
        new SocketChannelClient(localAddr1).start();
        // Thread.sleep(2 * TIME_UNIT);
        SocketChannel sc = this.serverChannel.accept();

        // differ from nonblock mode
        assertTrue(this.serverChannel.isBlocking());
        // and sc id blocked by default.
        assertTrue(sc.isBlocking());
        assertGotSocketChannel(sc);
        assertSocketAccepted(serverSocket, localAddr1);
    }

    public void testAccept_NonBlock_ClientThread() throws Exception {
        this.serverChannel.configureBlocking(false);
        ServerSocket serverSocket = this.serverChannel.socket();
        serverSocket.bind(localAddr1);
        new SocketChannelClient(localAddr1).start();

        Thread.sleep(2 * TIME_UNIT);
        SocketChannel sc = this.serverChannel.accept();
        assertNotNull(sc);
        // differ from block mode
        assertFalse(this.serverChannel.isBlocking());
        // and sc id blocked by default.
        assertTrue(sc.isBlocking());
        assertGotSocketChannel(sc);
        assertSocketAccepted(serverSocket, localAddr1);
    }

    private void assertGotSocketChannel(SocketChannel sc) throws IOException {
        // host name is equal, but port is different
        assertTrue(sc.socket().getPort() != localAddr1.getPort());
        assertEquals(sc.socket().getInetAddress().getHostAddress(), localAddr1
                .getAddress().getHostAddress());
        // System.out.println(sc.socket().getInetAddress().getHostAddress());
        // System.out.println(sc.socket().getPort());
        ByteBuffer receiveBuf = ByteBuffer.allocate(capacityNormal);
        int count = sc.read(receiveBuf);
        if (count > 0) {
            assertEquals(new String(receiveBuf.array()).trim(), MSG_CLIENT);
        } else {
            sc.read(receiveBuf);
            assertEquals(new String(receiveBuf.array()).trim(), MSG_CLIENT);
        }
        ByteBuffer sendBuf = ByteBuffer.wrap(MSG_SERVER.getBytes());
        sc.write(sendBuf);
    }

    public void testAccept_Block_NoConnect() throws IOException {
        assertTrue(this.serverChannel.isBlocking());
        ServerSocket gotSocket = this.serverChannel.socket();
        gotSocket.bind(localAddr1); //$NON-NLS-1$        
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
                    e.printStackTrace();
                }
            }
        }.start();
        long start = System.currentTimeMillis();
        try {
            this.serverChannel.accept();
            fail("Should throw a AsynchronousCloseException");
        } catch (AsynchronousCloseException e) {
            // OK.
        }
        long end = System.currentTimeMillis();
        // 20% off to deal with some casual timing error
        assertTrue(end - start >= TIME_UNIT - TIME_UNIT * 0.2);
        // System.out.println(end - start);
    }

    public void testAccept_NonBlock_NoConnect() throws IOException {
        ServerSocket gotSocket = this.serverChannel.socket();
        gotSocket.bind(localAddr1); //$NON-NLS-1$
        this.serverChannel.configureBlocking(false);
        // non-blocking mode , will immediately return
        assertNull(this.serverChannel.accept());
    }

    public void testReadWrite_Blocking_RealData() throws IOException {

        assertTrue(this.serverChannel.isBlocking());
        ServerSocket serverSocket = this.serverChannel.socket();
        serverSocket.bind(localAddr1);

        byte[] serverWBuf = new byte[capacityNormal];
        byte[] serverRBuf = new byte[capacityNormal];
        for (int i = 0; i < serverWBuf.length; i++) {
            serverWBuf[i] = (byte) i;
        }
        java.nio.ByteBuffer buf = java.nio.ByteBuffer.allocate(capacityNormal);

        this.clientChannel.connect(localAddr1); //$NON-NLS-1$
        Socket clientSocket = this.serverChannel.accept().socket();
        assertTrue(this.clientChannel.isConnected());
        assertTrue(clientSocket.isConnected());
        OutputStream out = clientSocket.getOutputStream();
        InputStream in = clientSocket.getInputStream();
        out.write(serverWBuf);
        assertEquals(capacityNormal, this.clientChannel.read(buf));
        buf.flip();
        assertEquals(66051, buf.asIntBuffer().get());
        assertEquals(capacityNormal, this.clientChannel.write(buf));
        in.read(serverRBuf);
        for (int i = 0; i < serverRBuf.length; i++) {
            assertEquals((byte) i, serverRBuf[i]);
        }
        this.clientChannel.close();
        try {
            assertEquals(capacityNormal, this.clientChannel.read(buf));
            fail("Should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // correct
        }
    }

    public void testReadWrite_NonBlocking_RealData() throws Exception {

        this.serverChannel.configureBlocking(false);
        ServerSocket serverSocket = this.serverChannel.socket();
        serverSocket.bind(localAddr1);

        byte[] serverWBuf = new byte[capacityNormal];
        byte[] serverRBuf = new byte[capacityNormal];
        for (int i = 0; i < serverWBuf.length; i++) {
            serverWBuf[i] = (byte) i;
        }
        java.nio.ByteBuffer buf = java.nio.ByteBuffer.allocate(capacityNormal);
        this.clientChannel.connect(localAddr1); //$NON-NLS-1$
        Socket clientSocket = this.serverChannel.accept().socket();
        if (this.clientChannel.isConnected()) {
            OutputStream out = clientSocket.getOutputStream();
            InputStream in = clientSocket.getInputStream();
            out.write(serverWBuf);
            int readCount = this.clientChannel.read(buf);
            if (readCount != 0) {
                assertEquals(capacityNormal, readCount);
                buf.flip();
                assertEquals(66051, buf.asIntBuffer().get());
                assertEquals(capacityNormal, this.clientChannel.write(buf));
                in.read(serverRBuf);
                for (int i = 0; i < serverRBuf.length; i++) {
                    assertEquals((byte) i, serverRBuf[i]);
                }
            } else {
                // FIXME: err output for net fail, used in debug time.
                // System.err
                // .println("Read fail,
                // testReadByteBuffer_NonBlocking_ReadWriteRealData is not
                // finished.");
            }
        } else {
            // FIXME: err output for net fail, used in debug time.
            // System.err
            // .println("Connection fail,
            // testReadByteBuffer_NonBlocking_ReadWriteRealData is not
            // finished.");
        }

        this.clientChannel.close();
        try {
            assertEquals(capacityNormal, this.clientChannel.read(buf));
            fail("Should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // correct
        }
    }
//	  FIXME: unstable test
//    public void testReadByteBufferArray_Blocking_ReadWriteRealData()
//            throws IOException {
//
//        assertTrue(this.serverChannel.isBlocking());
//        ServerSocket serverSocket = this.serverChannel.socket();
//        serverSocket.bind(localAddr1);
//
//        byte[] serverWBuf = new byte[capacityNormal * 2];
//        byte[] serverRBuf = new byte[capacityNormal * 2];
//        for (int i = 0; i < capacityNormal * 2; i++) {
//            serverWBuf[i] = (byte) i;
//        }
//        java.nio.ByteBuffer[] buf = new java.nio.ByteBuffer[2];
//        buf[0] = ByteBuffer.allocate(capacityNormal);
//        buf[1] = ByteBuffer.allocate(capacityNormal);
//        this.clientChannel.connect(localAddr1); //$NON-NLS-1$
//        Socket clientSocket = this.serverChannel.accept().socket();
//        assertTrue(this.clientChannel.isConnected());
//        OutputStream out = clientSocket.getOutputStream();
//        InputStream in = clientSocket.getInputStream();
//        out.write(serverWBuf);
//        assertEquals(capacityNormal * 2, this.clientChannel.read(buf));
//        buf[0].flip();
//        buf[1].flip();
//        assertEquals(66051, buf[0].asIntBuffer().get());
//        assertEquals(capacityNormal * 2, this.clientChannel.write(buf));
//        int readCount = in.read(serverRBuf);
//
//        for (int i = 0; i < readCount; i++) {
//            assertEquals((byte) i, serverRBuf[i]);
//        }
//
//        this.clientChannel.close();
//        try {
//            assertEquals(capacityNormal, this.clientChannel.read(buf));
//            fail("Should throw ClosedChannelException");
//        } catch (ClosedChannelException e) {
//            // correct
//        }
//    }

    public void testReadByteBuffer_Blocking_ReadWriteRealLargeData()
            throws IOException {

        assertTrue(this.serverChannel.isBlocking());
        ServerSocket serverSocket = this.serverChannel.socket();
        serverSocket.bind(localAddr1);

        byte[] serverWBuf = new byte[capacity64KB];
        byte[] serverRBuf = new byte[capacity64KB];
        for (int i = 0; i < serverWBuf.length; i++) {
            serverWBuf[i] = (byte) i;
        }
        java.nio.ByteBuffer buf = java.nio.ByteBuffer
                .allocateDirect(capacity64KB);
        this.clientChannel.connect(localAddr1); //$NON-NLS-1$
        Socket clientSocket = this.serverChannel.accept().socket();
        assertTrue(this.clientChannel.isConnected());
        OutputStream out = clientSocket.getOutputStream();
        InputStream in = clientSocket.getInputStream();
        out.write(serverWBuf);
        int count = 0;
        int total = 0;
        while ((count = this.clientChannel.read(buf)) != 0)
            total = total + count;
        if (0 != total)
            assertEquals(total, capacity64KB);
        buf.flip();
        assertEquals(66051, buf.asIntBuffer().get());
        assertEquals(capacity64KB, this.clientChannel.write(buf));
        count = in.read(serverRBuf);
        for (int i = 0; i < count; i++) {
            assertEquals((byte) i, serverRBuf[i]);
        }
        this.clientChannel.close();
        try {
            assertEquals(capacityNormal, this.clientChannel.read(buf));
            fail("Should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
        }
        // correct
    }

    public void testReadByteBuffer_NonBlocking_ReadWriteRealLargeData()
            throws Exception {

        this.serverChannel.configureBlocking(false);
        ServerSocket serverSocket = this.serverChannel.socket();
        serverSocket.bind(localAddr1);

        byte[] serverWBuf = new byte[capacity64KB];
        byte[] serverRBuf = new byte[capacity64KB];
        for (int i = 0; i < serverWBuf.length; i++) {
            serverWBuf[i] = (byte) i;
        }
        java.nio.ByteBuffer buf = java.nio.ByteBuffer.allocate(capacity64KB);
        this.clientChannel.connect(localAddr1); //$NON-NLS-1$
        Socket clientSocket = this.serverChannel.accept().socket();
        if (this.clientChannel.isConnected()) {
            OutputStream out = clientSocket.getOutputStream();
            InputStream in = clientSocket.getInputStream();
            out.write(serverWBuf);
            int count = 0;
            int total = 0;
            while ((count = this.clientChannel.read(buf)) != 0)
                total = total + count;
            assertEquals(total, capacity64KB);
            buf.flip();
            assertEquals(66051, buf.asIntBuffer().get());
            assertEquals(capacity64KB, this.clientChannel.write(buf));
            count = in.read(serverRBuf);
            for (int i = 0; i < count; i++) {
                assertEquals((byte) i, serverRBuf[i]);
            }
        } else {
            // FIXME: err output for net fail, used in debug time.
            // System.err
            // .println("Connection fail,
            // testReadByteBuffer_NonBlocking_ReadWriteRealLargeData is not
            // finished.");
        }
        this.clientChannel.close();
        try {
            assertEquals(capacityNormal, this.clientChannel.read(buf));
            fail("Should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // correct
        }
    }

}
