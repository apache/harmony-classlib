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
import java.net.BindException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.AlreadyConnectedException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ConnectionPendingException;
import java.nio.channels.IllegalBlockingModeException;
import java.nio.channels.NoConnectionPendingException;
import java.nio.channels.NotYetConnectedException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.UnresolvedAddressException;
import java.nio.channels.UnsupportedAddressTypeException;
import java.nio.channels.spi.SelectorProvider;

import junit.framework.TestCase;
import tests.support.Support_PortManager;

/**
 * Tests for SocketChannel and its default implementation.
 */
public class SocketChannelTest extends TestCase {

    private static final int CAPACITY_NORMAL = 200;

    private InetSocketAddress localAddr1;

    private InetSocketAddress localAddr2;

    private SocketChannel channel1;

    private SocketChannel channel2;

    private ServerSocket server1;

    private ServerSocket server2;

    protected void setUp() throws Exception {
        super.setUp();
        this.localAddr1 = new InetSocketAddress("127.0.0.1",
                Support_PortManager.getNextPort());
        this.localAddr2 = new InetSocketAddress("127.0.0.1",
                Support_PortManager.getNextPort());
        this.channel1 = SocketChannel.open();
        this.channel2 = SocketChannel.open();
        this.server1 = new ServerSocket(localAddr1.getPort());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        if (null != this.channel1) {
            try {
                this.channel1.close();
            } catch (Exception e) {
                //ignore
            }
        }
        if (null != this.channel2) {
            try {
                this.channel2.close();
            } catch (Exception e) {
                //ignore
            }
        }
        if (null != this.server1) {
            try {
                this.server1.close();
            } catch (Exception e) {
                //ignore
            }
        }
        if (null != this.server2) {
            try {
                this.server2.close();
            } catch (Exception e) {
                //ignore
            }
        }
    }

    // -------------------------------------------------------------------
    // Test for methods in abstract class.
    // -------------------------------------------------------------------
    /*
     * Test method for 'java.nio.channels.SocketChannel.validOps()'
     */
    public void testValidOps() {
        MockSocketChannel testMSChannel = new MockSocketChannel(null);
        assertEquals(13, this.channel1.validOps());
        assertEquals(13, testMSChannel.validOps());
    }

    /*
     * Test method for 'java.nio.channels.SocketChannel.open()'
     */
    public void testOpen() throws IOException {
        java.nio.ByteBuffer[] buf = new java.nio.ByteBuffer[1];
        buf[0] = java.nio.ByteBuffer.allocateDirect(CAPACITY_NORMAL);
        MockSocketChannel testMSChannel = new MockSocketChannel(null);
        MockSocketChannel testMSChannelnotnull = new MockSocketChannel(
                SelectorProvider.provider());
        assertNull(testMSChannel.provider());
        assertNotNull(testMSChannelnotnull.provider());
        assertNotNull(this.channel1);
        assertEquals(this.channel1.provider(), testMSChannelnotnull.provider());
        try {
            this.channel1.write(buf);
            fail("Should throw NotYetConnectedException");
        } catch (NotYetConnectedException e) {
            // correct
        }
    }

    /*
     * Test method for 'java.nio.channels.SocketChannel.open(SocketAddress)'
     */
    public void testOpenSocketAddress_Null() throws IOException {
        SocketChannel channel1IP = null;
        try {
            channel1IP = SocketChannel.open(null);
            fail("Should throw IAE");
        } catch (IllegalArgumentException e) {
            // correct
        }
        assertNull(channel1IP);
    }

    /*
     * Test method for 'java.nio.channels.SocketChannel.read(ByteBuffer[])'
     */
    public void testReadByteBufferArray() throws IOException {
        java.nio.ByteBuffer[] byteBuf = null;
        MockSocketChannel testMSChannelnull = new MockSocketChannel(null);
        MockSocketChannel testMSChannel = new MockSocketChannel(
                SelectorProvider.provider());
        ServerSocket testServer = new ServerSocket(Support_PortManager
                .getNextPort());
        try {
            try {
                this.channel1.read(byteBuf);
                fail("Should throw NPE");
            } catch (NullPointerException e) {
                // correct
            }
            byteBuf = new java.nio.ByteBuffer[CAPACITY_NORMAL];
            try {
                this.channel1.read(byteBuf);
                fail("Should throw NotYetConnectedException");
            } catch (NotYetConnectedException e) {
                // correct
            }
            long readNum = CAPACITY_NORMAL;
            readNum = testMSChannel.read(byteBuf);
            assertEquals(0, readNum);
            readNum = CAPACITY_NORMAL;
            readNum = testMSChannelnull.read(byteBuf);
            assertEquals(0, readNum);
        } finally {
            testServer.close();
        }
    }

    /*
     * Test method for 'java.nio.channels.SocketChannel.read(ByteBuffer[])'
     */
    public void testReadByteBufferArray_BufNull() throws IOException {
        java.nio.ByteBuffer[] byteBuf = null;
        MockSocketChannel testMSChannelnull = new MockSocketChannel(null);
        MockSocketChannel testMSChannel = new MockSocketChannel(
                SelectorProvider.provider());
        try {
            this.channel1.read(byteBuf);
            fail("Should throw NPE");
        } catch (NullPointerException e) {
            // correct
        }
        byteBuf = null;
        try {
            this.channel1.read(byteBuf);
            fail("Should throw NPE");
        } catch (NullPointerException e) {
            // correct
        }
        try {
            testMSChannel.read(byteBuf);
            fail("Should throw NPE");
        } catch (NullPointerException e) {
            // correct
        }
        try {
            testMSChannelnull.read(byteBuf);
            fail("Should throw NPE");
        } catch (NullPointerException e) {
            // correct
        }
    }

    /*
     * Test method for 'java.nio.channels.SocketChannel.write(ByteBuffer[])'
     */
    public void testWriteByteBufferArray() throws IOException {
        java.nio.ByteBuffer[] byteBuf = null;
        MockSocketChannel testMSChannelnull = new MockSocketChannel(null);
        MockSocketChannel testMSChannel = new MockSocketChannel(
                SelectorProvider.provider());
        try {
            this.channel1.write(byteBuf);
            fail("Should throw NPE");
        } catch (NullPointerException e) {
            // correct
        }
        byteBuf = new java.nio.ByteBuffer[CAPACITY_NORMAL];
        try {
            this.channel1.write(byteBuf);
            fail("Should throw NotYetConnectedException");
        } catch (NotYetConnectedException e) {
            // correct
        }
        testMSChannel.write(byteBuf);
        testMSChannelnull.write(byteBuf);
    }

    /*
     * Test method for 'java.nio.channels.SocketChannel.write(ByteBuffer[])'
     */
    public void testWriteByteBufferArray_BufNull() throws IOException {
        java.nio.ByteBuffer[] byteBuf = null;
        MockSocketChannel testMSChannelnull = new MockSocketChannel(null);
        MockSocketChannel testMSChannel = new MockSocketChannel(
                SelectorProvider.provider());
        try {
            this.channel1.write(byteBuf);
            fail("Should throw NPE");
        } catch (NullPointerException e) {
            // correct
        }
        try {
            testMSChannel.write(byteBuf);
            fail("Should throw NPE");
        } catch (NullPointerException e) {
            // correct
        }
        try {
            testMSChannelnull.write(byteBuf);
            fail("Should throw NPE");
        } catch (NullPointerException e) {
            // correct
        }
    }

    public void testSocket_BasicStatusBeforeConnect() throws IOException {
        assertFalse(this.channel1.isConnected());// not connected
        Socket s1 = this.channel1.socket();
        assertSocketBeforeConnect(s1);
        Socket s2 = this.channel1.socket();
        // same
        assertSame(s1, s2);
    }

    public void testSocket_Block_BasicStatusAfterConnect() throws IOException {
        assertFalse(this.channel1.isConnected());// not connected
        assertTrue(this.channel1.connect(localAddr1));

        assertTrue(this.channel1.isConnected());
        Socket s1 = this.channel1.socket();

        assertSocketAfterConnect(s1, localAddr1);
        Socket s2 = this.channel1.socket();
        // same
        assertSame(s1, s2);
    }

    public void testSocket_NonBlock_BasicStatusAfterConnect() throws Exception {
        assertFalse(this.channel1.isConnected());// not connected
        this.channel1.configureBlocking(false);
        assertFalse(this.channel1.connect(localAddr1));
        assertFalse(this.channel1.isConnected());
        assertTrue(this.channel1.isConnectionPending());
        Socket s1 = this.channel1.socket();
        // status of not conneted
        assertSocketBeforeConnect(s1);
        Socket s2 = this.channel1.socket();
        // same
        assertSame(s1, s2);

        if (tryFinish()) {
            assertTrue(this.channel1.isConnected());
            s1 = this.channel1.socket();
            assertSocketAfterConnect(s1, localAddr1);
            s2 = this.channel1.socket();
            // same
            assertSame(s1, s2);
        }
    }

    public void testSocket_Block_ActionsBeforeConnect() throws IOException {
        assertFalse(this.channel1.isConnected());// not connected
        Socket s = this.channel1.socket();
        assertSocketAction_Block_BeforeConnect(s);
    }

    public void testSocket_NonBlock_ActionsBeforeConnect() throws IOException {
        assertFalse(this.channel1.isConnected());// not connected
        this.channel1.configureBlocking(false);
        Socket s = this.channel1.socket();
        assertSocketAction_NonBlock_BeforeConnect(s);
    }

    public void testSocket_Block_ActionsAfterConnect() throws IOException {
        assertFalse(this.channel1.isConnected());// not connected
        assertTrue(this.channel1.connect(localAddr1));
        assertTrue(this.channel1.isConnected());
        Socket s = this.channel1.socket();
        assertSocketAction_Block_AfterConnect(s);

    }

    public void testSocket_NonBlock_ActionsAfterConnectBeforeFinish()
            throws IOException {
        assertFalse(this.channel1.isConnected());// not connected
        this.channel1.configureBlocking(false);
        assertFalse(this.channel1.connect(localAddr1));
        assertFalse(this.channel1.isConnected());
        assertTrue(this.channel1.isConnectionPending());
        Socket s1 = this.channel1.socket();
        // Action of not conneted
        assertSocketAction_NonBlock_BeforeConnect(s1);
        Socket s2 = this.channel1.socket();
        // same
        assertSame(s1, s2);
    }

    public void testSocket_NonBlock_ActionsAfterConnectAfterFinish()
            throws Exception {
        assertFalse(this.channel1.isConnected());// not connected
        this.channel1.configureBlocking(false);
        assertFalse(this.channel1.connect(localAddr1));
        if (tryFinish()) {
            Socket s1 = this.channel1.socket();
            assertSocketAction_NonBlock_AfterConnect(s1);
            Socket s2 = this.channel1.socket();
            // same
            assertSame(s1, s2);
        }
    }

    private void assertSocketBeforeConnect(Socket s) throws IOException {
        assertFalse(s.isBound());
        assertFalse(s.isClosed());
        assertFalse(s.isConnected());
        assertFalse(s.getKeepAlive());
        try {
            s.getInputStream();
            fail("Should throw SocketException.");
        } catch (SocketException e) {
            // OK.
        }
        assertFalse(s.getOOBInline());
        try {
            s.getOutputStream();
            fail("Should throw SocketException.");
        } catch (SocketException e) {
            // OK.
        }
        assertEquals(-1, s.getSoLinger());
        assertFalse(s.getTcpNoDelay());

        assertFalse(s.isInputShutdown());
        assertFalse(s.isOutputShutdown());

        assertNull(s.getInetAddress());
        assertEquals(s.getLocalAddress().getHostAddress(), "0.0.0.0");
        // RI fails here. RI returns 0 while spec says unbound socket should
        // return -1.
        assertEquals(s.getLocalPort(), -1);
        assertFalse(s.getReuseAddress());
        assertNull(s.getLocalSocketAddress());

        // not connected
        assertEquals(s.getPort(), 0);
        assertTrue(s.getReceiveBufferSize() >= 8192);
        assertNull(s.getRemoteSocketAddress());
        assertTrue(s.getSendBufferSize() >= 8192);
        assertEquals(s.getSoTimeout(), 0);
        assertEquals(s.getTrafficClass(), 0);

    }

    private void assertSocketAfterConnect(Socket s, InetSocketAddress address)
            throws IOException {
        assertTrue(s.isBound());
        assertFalse(s.isClosed());
        assertTrue(s.isConnected());
        assertFalse(s.getKeepAlive());

        assertNotNull(s.getInputStream());
        assertNotNull(s.getOutputStream());

        assertFalse(s.getOOBInline());
        assertEquals(-1, s.getSoLinger());
        assertFalse(s.getTcpNoDelay());

        assertFalse(s.isInputShutdown());
        assertFalse(s.isOutputShutdown());

        assertSame(s.getInetAddress(), address.getAddress());

        assertEquals(s.getLocalAddress(), this.localAddr1.getAddress());
        assertEquals(s.getPort(), address.getPort());
        assertNotNull(s.getLocalSocketAddress());
        assertTrue(s.getReceiveBufferSize() >= 8192);
        // equal , not same
        assertNotSame(s.getRemoteSocketAddress(), (SocketAddress) address);
        assertEquals(s.getRemoteSocketAddress(), (SocketAddress) address);
        // assertFalse(s.getReuseAddress());
        assertTrue(s.getSendBufferSize() >= 8192);
        assertEquals(s.getSoTimeout(), 0);
        assertEquals(s.getTrafficClass(), 0);
    }

    private void assertSocketAction_Block_BeforeConnect(Socket s)
            throws IOException {
        assertFalse(this.channel1.isConnected());
        this.server2 = new ServerSocket(localAddr2.getPort());
        s.connect(localAddr2);
        assertTrue(this.channel1.isConnected());
        assertTrue(s.isConnected());

        assertSocketAfterConnect(s, localAddr2);

        try {
            s.bind(localAddr2);
            fail("Should throw AlreadyConnectedException");
        } catch (AlreadyConnectedException e) {
            // OK.
        }

        s.close();
        assertTrue(s.isClosed());
        assertFalse(this.channel1.isOpen());
    }

    private void assertSocketAction_NonBlock_BeforeConnect(Socket s)
            throws IOException {
        assertFalse(this.channel1.isConnected());
        this.server2 = new ServerSocket(localAddr2.getPort());
        try {
            s.connect(localAddr2);
            fail("Should throw IllegalBlockingModeException");
        } catch (IllegalBlockingModeException e1) {
            // OK.
        }

        if (this.channel1.isConnectionPending()) {
            try {
                s.bind(localAddr2);
                fail("Should throw BindException");
            } catch (ConnectionPendingException e1) {
                // OK.
            }
        } else {
            try {
                s.bind(localAddr2);
                fail("Should throw BindException");
            } catch (BindException e1) {
                // OK.
            }
        }

        assertFalse(this.channel1.isConnected());
        assertFalse(s.isConnected());

        s.close();
        assertTrue(s.isClosed());
        assertFalse(this.channel1.isOpen());
    }

    private void assertSocketAction_Block_AfterConnect(Socket s)
            throws IOException {
        assertEquals(s.getPort(), localAddr1.getPort());
        assertTrue(this.channel1.isConnected());
        assertTrue(s.isConnected());
        try {
            s.connect(localAddr2);
            fail("Should throw AlreadyConnectedException");
        } catch (AlreadyConnectedException e) {
            // OK.
        }

        try {
            s.bind(localAddr2);
            fail("Should throw AlreadyConnectedException");
        } catch (AlreadyConnectedException e) {
            // OK.
        }

        s.close();
        assertTrue(s.isClosed());
        assertFalse(this.channel1.isOpen());
    }

    private void assertSocketAction_NonBlock_AfterConnect(Socket s)
            throws IOException {
        assertEquals(s.getPort(), localAddr1.getPort());
        assertTrue(this.channel1.isConnected());
        assertTrue(s.isConnected());

        if (this.channel1.isConnectionPending()) {
            try {
                s.connect(localAddr2);
                fail("Should throw AlreadyConnectedException");
            } catch (AlreadyConnectedException e) {
                // OK.
            }
        } else {
            try {
                s.connect(localAddr2);
                fail("Should throw IllegalBlockingModeException");
            } catch (IllegalBlockingModeException e) {
                // OK.
            }
        }

        try {
            s.bind(localAddr2);
            fail("Should throw AlreadyConnectedException");
        } catch (AlreadyConnectedException e) {
            // OK.
        }

        s.close();
        assertTrue(s.isClosed());
        assertFalse(this.channel1.isOpen());
    }

    // -------------------------------------------------------------------
    // Tests for connect(), finishConnect(),isConnected(),isConnectionPending()
    // These methods are very close, so we test them together, call them "CFII".
    // -------------------------------------------------------------------
    /**
     * connect-->finish-->close
     */
    public void testCFII_Norml_NoServer_Block() throws Exception {
        // ensure
        ensureServerClosed();
        assertTrue(this.channel1.isBlocking());
        statusNotConnected_NotPending();
        // connect
        try {
            this.channel1.connect(localAddr1);
            fail("Should throw a ConnectException here.");
        } catch (ConnectException e) {
            // OK.
        }
        statusChannelClosed();
        try {
            this.channel1.finishConnect();
            fail("Should throw a ClosedChannelException here.");
        } catch (ClosedChannelException e) {
            // OK.
        }
    }

    /**
     * connect-->finish-->close
     */
    public void testCFII_Norml_NoServer_NonBlock() throws Exception {
        connectNoServerNonBlock();

        this.channel1.close();
        statusChannelClosed();
    }

    /**
     * connect-->finish-->close
     */
    public void testCFII_Norml_Server_Block() throws Exception {
        connectServerBlock();

        this.channel1.close();
        statusChannelClosed();

    }

    /**
     * connect-->finish-->close
     */
    public void testCFII_Norml_Server_NonBlock() throws Exception {
        connectServerNonBlock();

        this.channel1.close();
        statusChannelClosed();
    }

    /**
     * connect-->server closed-->finish-->close
     */
    public void testCFII_ServerClosed_Block() throws Exception {
        // ensure
        ensureServerOpen();
        assertTrue(this.channel1.isBlocking());
        statusNotConnected_NotPending();
        // connect
        assertTrue(this.channel1.connect(localAddr1));
        statusConnected_NotPending();

        ensureServerClosed();

        tryFinish();

        this.channel1.close();
        statusChannelClosed();

    }

    /**
     * connect-->server closed-->finish-->close
     */
    public void testCFII_ServerClosed_NonBlock() throws Exception {
        // ensure
        ensureServerOpen();
        this.channel1.configureBlocking(false);
        statusNotConnected_NotPending();
        // connect
        assertFalse(this.channel1.connect(localAddr1));
        statusNotConnected_Pending();

        ensureServerClosed();

        tryFinish();

        this.channel1.close();
        statusChannelClosed();
    }

    /**
     * connect-->finish-->server closed-->close
     */
    public void testCFII_ServerClosedAfterFinish_Block() throws Exception {
        connectServerBlock();

        ensureServerClosed();
        assertTrue(this.channel1.isOpen());
        this.channel1.close();
        statusChannelClosed();

    }

    /**
     * connect-->finish-->server closed-->close
     */
    public void testCFII_ServerClosedAfterFinish_NonBlock() throws Exception {
        connectServerNonBlock();

        ensureServerClosed();
        assertTrue(this.channel1.isOpen());
        this.channel1.close();
        statusChannelClosed();
    }

    /**
     * no server-->connect-->server open-->finish-->close
     */
    public void testCFII_ServerStartLater_Block() throws Exception {
        // ensure
        ensureServerClosed();
        assertTrue(this.channel1.isBlocking());
        statusNotConnected_NotPending();
        // connect
        try {
            this.channel1.connect(localAddr1);
            fail("Should throw a ConnectException here.");
        } catch (ConnectException e) {
            // OK.
        }
        statusChannelClosed();
        ensureServerOpen();
        try {
            this.channel1.finishConnect();
            fail("Should throw a ClosedChannelException here.");
        } catch (ClosedChannelException e) {
            // OK.
        }
    }

    /**
     * no server-->connect-->server open-->finish-->close
     */
    public void testCFII_ServerStartLater_NonBlock() throws Exception {
        // ensure
        ensureServerClosed();
        this.channel1.configureBlocking(false);
        statusNotConnected_NotPending();
        // connect
        assertFalse(this.channel1.connect(localAddr1));
        statusNotConnected_Pending();

        ensureServerOpen();

        try {
            assertFalse(this.channel1.finishConnect());
            statusNotConnected_Pending();
            this.channel1.close();
        } catch (ConnectException e) {
            // FIXME: assertEquals(e.getMessage(), "Connection refused");
        }
    }

    /**
     * connect-->finish-->finish-->close
     */
    public void testCFII_FinishTwice_NoServer_NonBlock() throws Exception {
        // ensure
        ensureServerClosed();
        this.channel1.configureBlocking(false);
        statusNotConnected_NotPending();
        // connect
        assertFalse(this.channel1.connect(localAddr1));
        statusNotConnected_Pending();
        try {
            assertFalse(this.channel1.finishConnect());
            statusNotConnected_Pending();
            assertFalse(this.channel1.finishConnect());
            statusNotConnected_Pending();
            this.channel1.close();
        } catch (ConnectException e) {
          // FIXME: assertEquals(e.getMessage(), "Connection refused");
        }
        statusChannelClosed();
    }

    /**
     * connect-->finish-->finish-->close
     */
    public void testCFII_FinishTwice_Server_Block() throws Exception {
        connectServerBlock();
        tryFinish();
        this.channel1.close();
        statusChannelClosed();

    }

    /**
     * connect-->finish-->finish-->close
     */
    public void testCFII_FinishTwice_Server_NonBlock() throws Exception {
        connectServerNonBlock();
        tryFinish();
        this.channel1.close();
        statusChannelClosed();
    }

    /**
     * connect-->finish-->connect-->close
     */
    public void testCFII_ConnectAfterFinish_NoServer_Block() throws Exception {
        // ensure
        ensureServerClosed();
        assertTrue(this.channel1.isBlocking());
        statusNotConnected_NotPending();
        // connect
        try {
            this.channel1.connect(localAddr1);
            fail("Should throw a ConnectException here.");
        } catch (ConnectException e) {
            // OK.
        }
        statusChannelClosed();
        try {
            this.channel1.finishConnect();
            fail("Should throw a ClosedChannelException here.");
        } catch (ClosedChannelException e) {
            // OK.
        }
        statusChannelClosed();
        try {
            this.channel1.connect(localAddr1);
            fail("Should throw a ClosedChannelException here.");
        } catch (ClosedChannelException e) {
            // OK.
        }
        statusChannelClosed();
    }

    /**
     * connect-->finish-->connect-->close
     */
    public void testCFII_ConnectAfterFinish_NoServer_NonBlock()
            throws Exception {
        // ensure
        ensureServerClosed();
        this.channel1.configureBlocking(false);
        statusNotConnected_NotPending();
        // connect
        assertFalse(this.channel1.connect(localAddr1));
        statusNotConnected_Pending();
        try {
            assertFalse(this.channel1.finishConnect());
            statusNotConnected_Pending();
        } catch (ConnectException e) {
            // FIXME: assertEquals(e.getMessage(), "Connection refused");
        }

        if (this.channel1.isOpen()) {

            try {
                this.channel1.connect(localAddr1);
                fail("Should throw a ConnectionPendingException here.");
            } catch (ConnectionPendingException e) {
                // OK.
            }
            statusNotConnected_Pending();

            // connect another addr
            try {
                this.channel1.connect(localAddr2);
                fail("Should throw a ConnectionPendingException here.");
            } catch (ConnectionPendingException e) {
                // OK.
            }
            statusNotConnected_Pending();

            // connect if server closed
            ensureServerClosed();

            try {
                this.channel1.connect(localAddr1);
                fail("Should throw a ConnectionPendingException here.");
            } catch (ConnectionPendingException e) {
                // OK.
            }
            statusNotConnected_Pending();

            this.channel1.close();
        }
        statusChannelClosed();
    }

    /**
     * connect-->finish-->connect-->close
     */
    public void testCFII_ConnectAfterFinish_Server_Block() throws Exception {
        connectServerBlock();

        if (!this.channel1.isConnected()) {
            System.err
                    .println("Connection fail, testCFII_ConnectAfterFinish_Server_Block is not finished.");
            return;
        }

        try {
            this.channel1.connect(localAddr1);
            fail("Should throw a AlreadyConnectedException here.");
        } catch (AlreadyConnectedException e) {
            // OK.
        }
        statusConnected_NotPending();

        // connect another addr
        try {
            this.channel1.connect(localAddr2);
            fail("Should throw a AlreadyConnectedException here.");
        } catch (AlreadyConnectedException e) {
            // OK.
        }
        statusConnected_NotPending();

        // connect if server closed
        ensureServerClosed();

        try {
            this.channel1.connect(localAddr1);
            fail("Should throw a AlreadyConnectedException here.");
        } catch (AlreadyConnectedException e) {
            // OK.
        }
        statusConnected_NotPending();

        this.channel1.close();
        statusChannelClosed();

    }

    /**
     * connect-->finish-->connect-->close
     */
    public void testCFII_ConnectAfterFinish_Server_NonBlock() throws Exception {
        connectServerNonBlock();

        if (!this.channel1.isConnected()) {
            System.err
                    .println("Connection fail, testCFII_ConnectAfterFinish_Server_Block is not finished.");
            return;
        }
        try {
            this.channel1.connect(localAddr1);
            fail("Should throw an AlreadyConnectedException or a ConnectionPendingException here.");
        } catch (AlreadyConnectedException e) {
            // OK.
        }

        statusConnected_NotPending();

        // connect another addr
        try {
            this.channel1.connect(localAddr2);
            fail("Should throw a AlreadyConnectedException here.");
        } catch (AlreadyConnectedException e) {
            // OK.
        }
        statusConnected_NotPending();

        // connect if server closed
        ensureServerClosed();

        try {
            this.channel1.connect(localAddr1);
            fail("Should throw a AlreadyConnectedException here.");
        } catch (AlreadyConnectedException e) {
            // OK.
        }
        statusConnected_NotPending();

        this.channel1.close();
        statusChannelClosed();
    }

    /**
     * connect-->connect-->finish-->close
     */
    public void testCFII_ConnectTwice_NoServer_NonBlock() throws Exception {
        // ensure
        ensureServerClosed();
        this.channel1.configureBlocking(false);
        statusNotConnected_NotPending();
        // connect
        assertFalse(this.channel1.connect(localAddr1));
        statusNotConnected_Pending();

        try {
            this.channel1.connect(localAddr1);
            fail("Should throw a ConnectionPendingException here.");
        } catch (ConnectionPendingException e) {
            // OK.
        }
        statusNotConnected_Pending();

        // connect another addr
        try {
            this.channel1.connect(localAddr2);
            fail("Should throw a ConnectionPendingException here.");
        } catch (ConnectionPendingException e) {
            // OK.
        }
        statusNotConnected_Pending();

        // connect if server closed
        ensureServerClosed();

        try {
            this.channel1.connect(localAddr1);
            fail("Should throw a ConnectionPendingException here.");
        } catch (ConnectionPendingException e) {
            // OK.
        }
        statusNotConnected_Pending();

        try {
            assertFalse(this.channel1.finishConnect());
            statusNotConnected_Pending();
            this.channel1.close();
        } catch (ConnectException e) {
            // FIXME: assertEquals(e.getMessage(), "Connection refused");
        }

        statusChannelClosed();
    }

    /**
     * connect-->connect-->finish-->close
     */
    public void testCFII_ConnectTwice_Server_Block() throws Exception {
        // ensure
        ensureServerOpen();
        assertTrue(this.channel1.isBlocking());
        statusNotConnected_NotPending();
        // connect
        assertTrue(this.channel1.connect(localAddr1));
        statusConnected_NotPending();

        try {
            this.channel1.connect(localAddr1);
            fail("Should throw a AlreadyConnectedException here.");
        } catch (AlreadyConnectedException e) {
            // OK.
        }
        statusConnected_NotPending();

        // connect another addr
        try {
            this.channel1.connect(localAddr2);
            fail("Should throw a AlreadyConnectedException here.");
        } catch (AlreadyConnectedException e) {
            // OK.
        }
        statusConnected_NotPending();

        // connect if server closed
        ensureServerClosed();

        try {
            this.channel1.connect(localAddr1);
            fail("Should throw a AlreadyConnectedException here.");
        } catch (AlreadyConnectedException e) {
            // OK.
        }
        statusConnected_NotPending();

        tryFinish();

        this.channel1.close();
        statusChannelClosed();

    }

    /**
     * connect-->connect-->finish-->close
     */
    public void testCFII_ConnectTwice_Server_NonBlock() throws Exception {
        // ensure
        ensureServerOpen();
        this.channel1.configureBlocking(false);
        statusNotConnected_NotPending();
        // connect
        assertFalse(this.channel1.connect(localAddr1));
        statusNotConnected_Pending();

        try {
            this.channel1.connect(localAddr1);
            fail("Should throw a ConnectionPendingException here.");
        } catch (ConnectionPendingException e) {
            // OK.
        }
        statusNotConnected_Pending();

        // connect another addr
        try {
            this.channel1.connect(localAddr2);
            fail("Should throw a ConnectionPendingException here.");
        } catch (ConnectionPendingException e) {
            // OK.
        }
        statusNotConnected_Pending();

        // connect if server closed
        ensureServerClosed();

        try {
            this.channel1.connect(localAddr1);
            fail("Should throw a ConnectionPendingException here.");
        } catch (ConnectionPendingException e) {
            // OK.
        }
        statusNotConnected_Pending();

        tryFinish();

        this.channel1.close();
        statusChannelClosed();
    }

    /**
     * finish-->connect-->finish-->close
     */
    public void testCFII_FinishFirst_NoServer_Block() throws Exception {
        // ensure
        ensureServerClosed();
        assertTrue(this.channel1.isBlocking());
        statusNotConnected_NotPending();
        // finish
        try {
            this.channel1.finishConnect();
            fail("Should throw NoConnectionPendingException");
        } catch (NoConnectionPendingException e) {
            // OK.
        }
        statusNotConnected_NotPending();
        // connect
        try {
            this.channel1.connect(localAddr1);
            fail("Should throw a ConnectException here.");
        } catch (ConnectException e) {
            // OK.
        }
        statusChannelClosed();
        try {
            this.channel1.finishConnect();
            fail("Should throw a ClosedChannelException here.");
        } catch (ClosedChannelException e) {
            // OK.
        }
        statusChannelClosed();
    }

    /**
     * finish-->connect-->finish-->close
     */
    public void testCFII_FinishFirst_NoServer_NonBlock() throws Exception {
        // ensure
        ensureServerClosed();
        this.channel1.configureBlocking(false);
        statusNotConnected_NotPending();
        // finish
        try {
            this.channel1.finishConnect();
            fail("Should throw NoConnectionPendingException");
        } catch (NoConnectionPendingException e) {
            // OK.
        }
        statusNotConnected_NotPending();
        // connect
        assertFalse(this.channel1.connect(localAddr1));
        statusNotConnected_Pending();

        try {
            assertFalse(this.channel1.finishConnect());
            statusNotConnected_Pending();
            this.channel1.close();
        } catch (ConnectException e) {
            // FIXME: assertEquals(e.getMessage(), "Connection refused");
        }

        statusChannelClosed();
    }

    /**
     * finish-->connect-->finish-->close
     */
    public void testCFII_FinishFirst_Server_Block() throws Exception {
        // ensure
        ensureServerOpen();
        assertTrue(this.channel1.isBlocking());
        statusNotConnected_NotPending();
        // finish
        try {
            this.channel1.finishConnect();
            fail("Should throw NoConnectionPendingException");
        } catch (NoConnectionPendingException e) {
            // OK.
        }
        statusNotConnected_NotPending();
        // connect
        assertTrue(this.channel1.connect(localAddr1));
        statusConnected_NotPending();

        tryFinish();

        this.channel1.close();
        statusChannelClosed();

    }

    /**
     * finish-->connect-->finish-->close
     */
    public void testCFII_FinishFirst_Server_NonBlock() throws Exception {
        // ensure
        ensureServerOpen();
        this.channel1.configureBlocking(false);
        statusNotConnected_NotPending();
        // finish
        try {
            this.channel1.finishConnect();
            fail("Should throw NoConnectionPendingException");
        } catch (NoConnectionPendingException e) {
            // OK.
        }
        statusNotConnected_NotPending();
        // connect
        assertFalse(this.channel1.connect(localAddr1));
        statusNotConnected_Pending();

        tryFinish();

        this.channel1.close();
        statusChannelClosed();
    }

    public void testCFII_Null() throws Exception {
        statusNotConnected_NotPending();
        try {
            this.channel1.connect(null);
            fail("Should throw a IAE here.");
        } catch (IllegalArgumentException e) {
            // OK.
        }
    }

    public void testCFII_UnsupportedType() throws Exception {
        class SubSocketAddress extends SocketAddress {
            private static final long serialVersionUID = 1L;

            //empty
            public SubSocketAddress() {
                super();
            }
        }
        statusNotConnected_NotPending();
        SocketAddress newTypeAddress = new SubSocketAddress();
        try {
            this.channel1.connect(newTypeAddress);
            fail("Should throw a UnsupportedAddressTypeException here.");
        } catch (UnsupportedAddressTypeException e) {
            // OK.
        }
    }

    public void testCFII_Unresolved() throws IOException {
        statusNotConnected_NotPending();
        InetSocketAddress unresolved = new InetSocketAddress(
                "unresolved address", 1080);
        try {
            this.channel1.connect(unresolved);
            fail("Should throw a UnresolvedAddressException here.");
        } catch (UnresolvedAddressException e) {
            // OK.
        }
    }

    public void testCFII_EmptyHost() throws Exception {
        statusNotConnected_NotPending();
        try {
            this.channel1.connect(new InetSocketAddress("", 1081));
            fail("Should throw ConnectException");
        } catch (ConnectException e) {
            // correct
        }
    }

    public void testCFII_CloseFirst() throws Exception {
        this.channel1.close();
        statusChannelClosed();
        ensureServerOpen();
        try {
            this.channel1.connect(localAddr1);
            fail("Should throw ClosedChannelException.");
        } catch (ClosedChannelException e) {
            // OK.
        }
        statusChannelClosed();
        try {
            this.channel1.finishConnect();
            fail("Should throw ClosedChannelException.");
        } catch (ClosedChannelException e) {
            // OK.
        }
        statusChannelClosed();
        try {
            this.channel1.configureBlocking(false);
            fail("Should throw ClosedChannelException.");
        } catch (ClosedChannelException e) {
            // OK.
        }
        statusChannelClosed();
    }

    public void testCFII_StatusAfterFinish() throws Exception {
        // 1. close server, finish must return false, check the status
        ensureServerClosed();

        // 1.1 block mode
        assertTrue(this.channel1.isBlocking());
        try {
            assertFalse(this.channel1.connect(localAddr1));
            fail("Should throw ConnectException");
        } catch (ConnectException e) {
            // OK.
        }
        assertFalse(this.channel1.isOpen());

        assertFalse(this.channel1.isOpen());
        assertTrue(this.channel1.isBlocking());
        assertFalse(this.channel1.isConnectionPending());

        // 1.2 non block mode
        this.channel1 = SocketChannel.open();
        this.channel1.configureBlocking(false);
        assertFalse(this.channel1.connect(localAddr1));
        try {
            assertFalse(this.channel1.finishConnect());
            statusNotConnected_Pending();
            this.channel1.close();
        } catch (ConnectException e) {
            System.out.println(e.getMessage());
        }

        // 2. start server, finish usually return true, check the status
        ensureServerOpen();

        // 2.1 block mode
        this.channel1 = SocketChannel.open();
        assertTrue(this.channel1.isBlocking());
        assertTrue(this.channel1.connect(localAddr1));
        assertTrue(this.channel1.finishConnect());
        statusConnected_NotPending();
        this.channel1.close();

        // 2.2 non block mode
        this.channel1 = SocketChannel.open();
        this.channel1.configureBlocking(false);
        assertFalse(this.channel1.connect(localAddr1));
        tryFinish();
        this.channel1.close();
    }

    private void ensureServerClosed() throws IOException {
        if (null != this.server1) {
            this.server1.close();
            assertTrue(this.server1.isClosed());
        }
        if (null != this.server2) {
            this.server2.close();
            assertTrue(this.server2.isClosed());
        }
    }

    private void ensureServerOpen() throws IOException {
        ensureServerClosed();
        this.server1 = new ServerSocket(localAddr1.getPort());
        this.server2 = new ServerSocket(localAddr2.getPort());
        assertTrue(this.server1.isBound());
        assertTrue(this.server2.isBound());
    }

    private void connectNoServerNonBlock() throws Exception {
        // ensure
        ensureServerClosed();
        this.channel1.configureBlocking(false);
        statusNotConnected_NotPending();
        // connect
        assertFalse(this.channel1.connect(localAddr1));
        statusNotConnected_Pending();
        try {
            assertFalse(this.channel1.finishConnect());
            statusNotConnected_Pending();
        } catch (ConnectException e) {
            // FIXME: assertEquals(e.getMessage(), "Connection refused");
        }
    }

    private void connectServerNonBlock() throws Exception {
        // ensure
        ensureServerOpen();
        this.channel1.configureBlocking(false);
        statusNotConnected_NotPending();
        // connect
        assertFalse(this.channel1.connect(localAddr1));
        statusNotConnected_Pending();

        tryFinish();
    }

    private void connectServerBlock() throws Exception {
        // ensure
        ensureServerOpen();
        assertTrue(this.channel1.isBlocking());
        statusNotConnected_NotPending();
        // connect
        assertTrue(this.channel1.connect(localAddr1));
        statusConnected_NotPending();
        tryFinish();
    }

    private void statusChannelClosed() {
        assertFalse(this.channel1.isConnected());
        assertFalse(this.channel1.isConnectionPending());
        assertFalse(this.channel1.isOpen());
    }

    private void statusNotConnected_NotPending() {
        assertFalse(this.channel1.isConnected());
        assertFalse(this.channel1.isConnectionPending());
        assertTrue(this.channel1.isOpen());
    }

    private void statusNotConnected_Pending() {
        assertFalse(this.channel1.isConnected());
        assertTrue(this.channel1.isConnectionPending());
        assertTrue(this.channel1.isOpen());
    }

    private void statusConnected_NotPending() {
        assertTrue(this.channel1.isConnected());
        assertFalse(this.channel1.isConnectionPending());
        assertTrue(this.channel1.isOpen());
    }

    private boolean tryFinish() throws IOException {
        /*
         * the result of finish will be asserted in multi-thread tests.
         */
        boolean connected = false;
        assertTrue(this.channel1.isOpen());
        try {
            connected = this.channel1.finishConnect();
        } catch (SocketException e) {
            System.err
                    .println("Finish connection failed, probably due to reset by peer error.");
        }
        if (connected) {
            statusConnected_NotPending();
        }
        return connected;
    }

    // -------------------------------------------------------------------
    // Original tests. Test method for CFII with real data.
    // -------------------------------------------------------------------

    /**
     * 
     * 'SocketChannelImpl.connect(SocketAddress)'
     */
    public void testCFII_Data_ConnectWithServer() throws Exception {
        ensureServerOpen();
        java.nio.ByteBuffer writeBuf = java.nio.ByteBuffer
                .allocate(CAPACITY_NORMAL);
        java.nio.ByteBuffer[] writeBufArr = new java.nio.ByteBuffer[1];
        writeBufArr[0] = java.nio.ByteBuffer.allocate(CAPACITY_NORMAL);
        assertFalse(this.channel1.isRegistered());
        assertTrue(this.channel1.isBlocking());

        this.channel1.connect(localAddr1);

        assertTrue(this.channel1.isBlocking());
        assertTrue(this.channel1.isConnected());
        assertFalse(this.channel1.isConnectionPending());
        assertTrue(this.channel1.isOpen());
        assertEquals(CAPACITY_NORMAL, this.channel1.write(writeBuf));
        assertEquals(CAPACITY_NORMAL, this.channel1.write(writeBufArr, 0, 1));

        this.channel1.configureBlocking(false);
        try {
            this.channel1.connect(localAddr1);
            fail("Should throw AlreadyConnectedException");
        } catch (AlreadyConnectedException e) {
            // correct
        }

        assertFalse(this.channel1.isRegistered());
        tryFinish();
    }

    /*
     * Test method for 'SocketChannelImpl.connect(SocketAddress)'
     */
    public void testCFII_Data_ConnectWithServer_nonBlocking() throws Exception {
        ensureServerOpen();
        java.nio.ByteBuffer writeBuf = java.nio.ByteBuffer
                .allocate(CAPACITY_NORMAL);
        java.nio.ByteBuffer[] writeBufArr = new java.nio.ByteBuffer[1];
        writeBufArr[0] = java.nio.ByteBuffer.allocate(CAPACITY_NORMAL);
        assertFalse(this.channel1.isRegistered());
        assertTrue(this.channel1.isBlocking());
        this.channel1.configureBlocking(false);
        this.channel1.connect(localAddr1);

        assertFalse(this.channel1.isBlocking());
        assertFalse(this.channel1.isConnected());
        assertTrue(this.channel1.isConnectionPending());
        assertTrue(this.channel1.isOpen());
        if (tryFinish()) {
            assertEquals(CAPACITY_NORMAL, this.channel1.write(writeBuf));
            assertEquals(CAPACITY_NORMAL, this.channel1.write(writeBufArr, 0, 1));

            this.channel1.configureBlocking(false);
            try {
                this.channel1.connect(localAddr1);
                fail("Should throw AlreadyConnectedException");
            } catch (AlreadyConnectedException e) {
                // correct
            }
        }

        assertFalse(this.channel1.isRegistered());
        tryFinish();
    }

    /*
     * Test method for 'SocketChannelImpl.finishConnect()'
     */
    public void testCFII_Data_FinishConnect_nonBlocking() throws IOException {
        ensureServerOpen();

        java.nio.ByteBuffer writeBuf = java.nio.ByteBuffer
                .allocate(CAPACITY_NORMAL);
        java.nio.ByteBuffer[] writeBufArr = new java.nio.ByteBuffer[1];
        writeBufArr[0] = java.nio.ByteBuffer.allocate(CAPACITY_NORMAL);

        this.channel1.configureBlocking(false);
        try {
            this.channel1.finishConnect();
            fail("Should throw NoConnectionPendingException");
        } catch (NoConnectionPendingException e) {
            // correct
        }
        this.channel1.connect(localAddr1);
        assertFalse(this.channel1.isBlocking());
        assertFalse(this.channel1.isConnected());
        assertTrue(this.channel1.isConnectionPending());
        assertTrue(this.channel1.isOpen());
        this.server1.accept();
        if (tryFinish()) {
            assertEquals(CAPACITY_NORMAL, this.channel1.write(writeBuf));
            assertEquals(CAPACITY_NORMAL, this.channel1.write(writeBufArr, 0, 1));
            try {
                this.channel1.connect(localAddr1);
                fail("Should throw AlreadyConnectedException");
            } catch (AlreadyConnectedException e) {
                // correct
            }
        }
        assertFalse(this.channel1.isRegistered());
        tryFinish();
    }

    public void testCFII_Data_FinishConnect_AddrSetServerStartLater()
            throws IOException, InterruptedException {
        ensureServerClosed();
        java.nio.ByteBuffer[] writeBufArr = new java.nio.ByteBuffer[1];
        writeBufArr[0] = java.nio.ByteBuffer.allocate(CAPACITY_NORMAL);
        this.channel1.configureBlocking(false);
        try {
            this.channel1 = SocketChannel.open(localAddr1);
            fail("Should throw ConnectException");
        } catch (ConnectException e) {
            // correct
        }
        assertTrue(this.channel1.isOpen());
        assertFalse(this.channel1.isBlocking());
        assertFalse(this.channel1.isConnectionPending());
        this.channel1.configureBlocking(true);
        try {
            this.channel1.finishConnect();
            fail("Should throw NoConnectionPendingException");
        } catch (NoConnectionPendingException e) {
            // correct
        }
        try {
            this.channel1.connect(localAddr2);
            fail("Should throw ConnectException");
        } catch (ConnectException e) {
            // correct
        }

        assertTrue(this.channel1.isBlocking());
        try {
            this.channel1.finishConnect();
            fail("Should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // correct
        }
        assertFalse(this.channel1.isConnected());
        // finish after finish OK
        assertFalse(this.channel1.isConnectionPending());
        this.channel1 = SocketChannel.open();
        this.channel1.configureBlocking(false);
        this.channel1.connect(localAddr1);
        assertFalse(this.channel1.isConnected());
        ensureServerOpen();
        // cannot connect?
        try {
            assertFalse(this.channel1.finishConnect());
            assertFalse(this.channel1.isBlocking());
            assertFalse(this.channel1.isConnected());
            assertTrue(this.channel1.isConnectionPending());
            assertTrue(this.channel1.isOpen());
            try {
                this.channel1.connect(localAddr1);
                fail("Should throw ConnectionPendingException");
            } catch (ConnectionPendingException e) {
                // correct
            }
            this.channel1.configureBlocking(true);
            try {
                this.channel1.connect(localAddr1);
                fail("Should throw ConnectionPendingException");
            } catch (ConnectionPendingException e) {
                // correct
            }
            tryFinish();
        } catch (ConnectException e) {
            // FIXME: assertEquals(e.getMessage(), "Connection refused");
        }
    }

    public void testCFII_Data_FinishConnect_ServerStartLater()
            throws IOException {
        ensureServerClosed();
        java.nio.ByteBuffer[] writeBufArr = new java.nio.ByteBuffer[1];
        writeBufArr[0] = java.nio.ByteBuffer.allocate(CAPACITY_NORMAL);
        this.channel1.configureBlocking(true);
        try {
            this.channel1.finishConnect();
            fail("Should throw NoConnectionPendingException");
        } catch (NoConnectionPendingException e) {
            // correct
        }
        try {
            this.channel1.connect(localAddr1);
            fail("Should throw ConnectException");
        } catch (ConnectException e) {
            // correct
        }

        try {
            this.channel1.finishConnect();
            fail("Should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // correct
        }
        assertFalse(this.channel1.isConnected());
        // finish after finish OK
        assertFalse(this.channel1.isConnectionPending());
        this.channel1 = SocketChannel.open();
        this.channel1.configureBlocking(false);
        this.channel1.connect(localAddr1);
        assertFalse(this.channel1.isConnected());
        ensureServerOpen();
        // cannot connect?
        try {
            assertFalse(this.channel1.finishConnect());
            assertFalse(this.channel1.isBlocking());
            assertFalse(this.channel1.isConnected());
            assertTrue(this.channel1.isConnectionPending());
            assertTrue(this.channel1.isOpen());
            try {
                this.channel1.connect(localAddr1);
                fail("Should throw ConnectionPendingException");
            } catch (ConnectionPendingException e) {
                // correct
            }
            this.channel1.configureBlocking(true);
            try {
                this.channel1.connect(localAddr1);
                fail("Should throw ConnectionPendingException");
            } catch (ConnectionPendingException e) {
                // correct
            }
            tryFinish();
        } catch (ConnectException e) {
            // FIXME: assertEquals(e.getMessage(), "Connection refused");
        }
    }

    public void testCFII_Data_FinishConnect_Blocking() throws IOException {
        ensureServerOpen();
        java.nio.ByteBuffer writeBuf = java.nio.ByteBuffer
                .allocate(CAPACITY_NORMAL);
        java.nio.ByteBuffer[] writeBufArr = new java.nio.ByteBuffer[1];
        writeBufArr[0] = java.nio.ByteBuffer.allocate(CAPACITY_NORMAL);
        this.channel1.configureBlocking(true);
        try {
            this.channel1.finishConnect();
            fail("Should throw NoConnectionPendingException");
        } catch (NoConnectionPendingException e) {
            // correct
        }

        this.channel1.connect(localAddr1);

        assertTrue(this.channel1.isConnected());
        assertFalse(this.channel1.isConnectionPending());
        assertTrue(this.channel1.isOpen());
        if (tryFinish()) {
            assertEquals(CAPACITY_NORMAL, this.channel1.write(writeBuf));
            assertEquals(CAPACITY_NORMAL, this.channel1.write(writeBufArr, 0, 1));

            try {
                this.channel1.connect(localAddr1);
                fail("Should throw AlreadyConnectedException");
            } catch (AlreadyConnectedException e) {
                // correct
            }
        }
        assertFalse(this.channel1.isRegistered());
        tryFinish();
    }

    // -------------------------------------------------------------------
    // End of original tests. Test method for CFII with real data.
    // -------------------------------------------------------------------

    public void testConnect_Lock() throws Exception {
        final Socket sock = new Socket();
        new Thread() {
            public void run() {
                try {
                    sock.connect(localAddr2);
                } catch (Exception e) {
                    System.out.println("in thread1" + e.getMessage());
                }
                if (!sock.isClosed())
                    System.out.println("T1 not closed");
            }
        }.start();
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(200);
                    sock.connect(localAddr1);
                } catch (Exception e) {
                    System.out.println("in thread2" + e.getMessage());
                }
                if (!sock.isClosed())
                    System.out.println("T2 not closed");
            }
        }.start();
        Thread.sleep(2000);
        sock.close();
    }

    // -------------------------------------------------
    // Test for read/write but no real data expressed
    // -------------------------------------------------

    public void testReadByteBuffer() throws Exception {
        assertTrue(this.server1.isBound());
        java.nio.ByteBuffer readBuf = java.nio.ByteBuffer
                .allocate(CAPACITY_NORMAL);
        assertFalse(this.channel1.isRegistered());
        assertTrue(this.channel1.isBlocking());
        assertFalse(this.channel1.isConnected());
        assertFalse(this.channel1.isConnectionPending());
        assertTrue(this.channel1.isOpen());
        // note: blocking-mode will make the read process endless!
        this.channel1.configureBlocking(false);
        try {
            assertEquals(CAPACITY_NORMAL, this.channel1.read(readBuf));
            fail("Should throw NotYetConnectedException");
        } catch (NotYetConnectedException e) {
            // correct
        }
        this.channel1.connect(localAddr1);
        assertFalse(this.channel1.isBlocking());
        assertTrue(this.channel1.isConnectionPending());
        assertFalse(this.channel1.isConnected());
        if (tryFinish()) {
            assertEquals(0, this.channel1.read(readBuf));
        }

        this.channel1.close();
        try {
            assertEquals(CAPACITY_NORMAL, this.channel1.read(readBuf));
            fail("Should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // correct
        }

    }

    public void testReadByteBuffer_BufNull() throws Exception {
        assertTrue(this.server1.isBound());
        java.nio.ByteBuffer readBuf = java.nio.ByteBuffer.allocate(0);
        // note: blocking-mode will make the read process endless!
        this.channel1.configureBlocking(false);
        try {
            assertEquals(CAPACITY_NORMAL, this.channel1
                    .read((java.nio.ByteBuffer) null));
            fail("Should throw NPE");
        } catch (NullPointerException e) {
            // correct
        }
        this.channel1.connect(localAddr1);
        if (tryFinish()) {
            try {
                assertEquals(CAPACITY_NORMAL, this.channel1
                        .read((java.nio.ByteBuffer) null));
                fail("Should throw NPE");
            } catch (NullPointerException e) {
                // correct
            }
            assertEquals(0, this.channel1.read(readBuf));
        }
        this.server1.close();
        try {
            assertEquals(CAPACITY_NORMAL, this.channel1
                    .read((java.nio.ByteBuffer) null));
            fail("Should throw NPE");
        } catch (NullPointerException e) {
            // correct
        }
    }

    /*
     * SocketChannelImpl.read(ByteBuffer[], int, int)'
     */
    public void testReadByteBufferArrayIntInt() throws Exception {
        assertTrue(this.server1.isBound());
        java.nio.ByteBuffer[] readBuf = new java.nio.ByteBuffer[2];
        readBuf[0] = java.nio.ByteBuffer.allocate(CAPACITY_NORMAL);
        readBuf[1] = java.nio.ByteBuffer.allocate(CAPACITY_NORMAL);
        assertFalse(this.channel1.isRegistered());
        assertTrue(this.channel1.isBlocking());
        assertFalse(this.channel1.isConnected());
        assertFalse(this.channel1.isConnectionPending());
        assertTrue(this.channel1.isOpen());
        // note: blocking-mode will make the read process endless!
        this.channel1.configureBlocking(false);
        try {
            assertEquals(CAPACITY_NORMAL, this.channel1.read(readBuf, 0, 1));
            fail("Should throw NotYetConnectedException");
        } catch (NotYetConnectedException e) {
            // correct
        }
        this.channel1.connect(localAddr1);
        assertFalse(this.channel1.isBlocking());
        assertTrue(this.channel1.isConnectionPending());
        assertFalse(this.channel1.isConnected());
        if (tryFinish()) {
            assertEquals(0, this.channel1.read(readBuf, 0, 1));
            assertEquals(0, this.channel1.read(readBuf, 0, 2));
        }

        this.channel1.close();
        try {
            assertEquals(CAPACITY_NORMAL, this.channel1.read(readBuf, 0, 1));
            fail("Should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // correct
        }
    }

    public void testReadByteBufferArrayIntInt_BufNull() throws Exception {
        assertTrue(this.server1.isBound());
        java.nio.ByteBuffer[] readBuf = new java.nio.ByteBuffer[2];
        readBuf[0] = java.nio.ByteBuffer.allocate(CAPACITY_NORMAL);
        assertFalse(this.channel1.isRegistered());
        assertTrue(this.channel1.isBlocking());
        assertFalse(this.channel1.isConnected());
        assertFalse(this.channel1.isConnectionPending());
        assertTrue(this.channel1.isOpen());
        // note: blocking-mode will make the read process endless!
        this.channel1.configureBlocking(false);
        try {
            assertEquals(CAPACITY_NORMAL, this.channel1.read(null, 0, 0));
            fail("Should throw NPE");
        } catch (NullPointerException e) {
            // correct
        }
        this.channel1.connect(localAddr1);
        if (tryFinish()) {

            try {
                assertEquals(CAPACITY_NORMAL, this.channel1.read(null, 0, 0));
                fail("Should throw NPE");
            } catch (NullPointerException e) {
                // correct
            }
            try {
                assertEquals(0, this.channel1.read(readBuf, 0, 2));
                fail("Should throw NPE");
            } catch (NullPointerException e) {
                // correct
            }

            assertEquals(0, this.channel1.read(readBuf, 0, 1));
        }
        this.channel1.close();
        try {
            assertEquals(CAPACITY_NORMAL, this.channel1.read(null, 0, 1));
            fail("Should throw NPE");
        } catch (NullPointerException e) {
            // correct
        }
    }

    public void testWriteByteBuffer() throws IOException {
        assertTrue(this.server1.isBound());
        java.nio.ByteBuffer writeBuf = java.nio.ByteBuffer
                .allocate(CAPACITY_NORMAL);
        assertFalse(this.channel1.isRegistered());
        assertTrue(this.channel1.isBlocking());
        assertFalse(this.channel1.isConnected());
        assertFalse(this.channel1.isConnectionPending());
        assertTrue(this.channel1.isOpen());
        try {
            assertEquals(CAPACITY_NORMAL, this.channel1.write(writeBuf));
            fail("Should throw NotYetConnectedException");
        } catch (NotYetConnectedException e) {
            // correct
        }
        this.channel1.connect(localAddr1);
        assertTrue(this.channel1.isBlocking());
        assertTrue(this.channel1.isConnected());
        assertFalse(this.channel1.isConnectionPending());
        assertTrue(this.channel1.isOpen());
        assertEquals(CAPACITY_NORMAL, this.channel1.write(writeBuf));

        this.channel1.close();
        try {
            assertEquals(CAPACITY_NORMAL, this.channel1.write(writeBuf));
            fail("Should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // correct
        }
    }

    public void testWriteByteBuffer_BufNull() throws IOException {
        assertTrue(this.server1.isBound());
        java.nio.ByteBuffer writeBuf = java.nio.ByteBuffer.allocate(0);
        this.channel1.connect(localAddr1);
        assertEquals(this.channel1.write(writeBuf), 0);
        try {
            this.channel1.write((java.nio.ByteBuffer) null);
            fail("Should throw NPE");
        } catch (NullPointerException e) {
            // correct
        }
    }

    /*
     * SocketChannelImpl.write(ByteBuffer[], int, int)'
     */
    public void testWriteByteBufferArrayIntInt() throws IOException {
        java.nio.ByteBuffer[] writeBuf = new java.nio.ByteBuffer[2];
        writeBuf[0] = java.nio.ByteBuffer.allocate(CAPACITY_NORMAL);
        writeBuf[1] = java.nio.ByteBuffer.allocate(CAPACITY_NORMAL);
        assertFalse(this.channel1.isRegistered());
        assertTrue(this.channel1.isBlocking());
        assertFalse(this.channel1.isConnected());
        assertFalse(this.channel1.isConnectionPending());
        assertTrue(this.channel1.isOpen());
        try {
            assertEquals(CAPACITY_NORMAL, this.channel1.write(writeBuf, 0, 1));
            fail("Should throw NotYetConnectedException");
        } catch (NotYetConnectedException e) {
            // correct
        }
        this.channel1.connect(localAddr1);
        assertTrue(this.channel1.isBlocking());
        assertTrue(this.channel1.isConnected());
        assertFalse(this.channel1.isConnectionPending());
        assertTrue(this.channel1.isOpen());
        assertEquals(CAPACITY_NORMAL, this.channel1.write(writeBuf, 0, 1));
        // still writes the same size as above
        assertEquals(CAPACITY_NORMAL, this.channel1.write(writeBuf, 0, 2));
        writeBuf[0].flip();
        writeBuf[1].flip();
        assertEquals(CAPACITY_NORMAL * 2, this.channel1.write(writeBuf, 0, 2));
        this.channel1.close();
        try {
            assertEquals(CAPACITY_NORMAL, this.channel1.write(writeBuf));
            fail("Should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // correct
        }
    }

    public void testWriteByteBufferArrayIntInt_BufNull() throws IOException {
        java.nio.ByteBuffer[] writeBuf = new java.nio.ByteBuffer[0];

        this.channel1.connect(localAddr1);
        try {
            this.channel1.write(null, 0, 1);
            fail("Should throw NPE");
        } catch (NullPointerException e) {
            // correct
        }
        assertEquals(0, this.channel1.write(writeBuf, 0, 0));
        try {
            this.channel1.write(writeBuf, 0, 1);
            fail("Should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // correct
        }
        writeBuf = new java.nio.ByteBuffer[1];
        try {
            this.channel1.write(writeBuf, 0, 1);
            fail("Should throw NPE");
        } catch (NullPointerException e) {
            // correct
        }
        try {
            this.channel1.write(writeBuf, 0, 2);
            fail("Should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // correct
        }
        this.server1.close();
        try {
            assertEquals(CAPACITY_NORMAL, this.channel1.read(null, 0, 1));
            fail("Should throw NPE");
        } catch (NullPointerException e) {
            // correct
        }
    }

    public void testWriteByteBufferArrayIntInt_SizeError() throws IOException {
        java.nio.ByteBuffer[] writeBuf = new java.nio.ByteBuffer[0];

        this.channel1.connect(localAddr1);
        try {
            this.channel1.write(null, -1, 1);
            fail("Should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // correct
        }
        assertEquals(0, this.channel1.write(writeBuf, 0, 0));
        try {
            this.channel1.write(writeBuf, 0, -1);
            fail("Should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // correct
        }
        writeBuf = new java.nio.ByteBuffer[1];
        try {
            this.channel1.write(writeBuf, 2, 1);
            fail("Should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // correct
        }
        try {
            this.channel1.write(writeBuf, 2, -1);
            fail("Should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // correct
        }
        this.server1.close();
        try {
            assertEquals(CAPACITY_NORMAL, this.channel1.read(null, -1, -1));
            fail("Should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // correct
        }
    }

    public void testReadByteBufferArrayIntInt_SizeError() throws IOException {
        java.nio.ByteBuffer[] readBuf = new java.nio.ByteBuffer[0];

        this.channel1.connect(localAddr1);
        try {
            this.channel1.read(null, -1, 1);
            fail("Should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // correct
        }
        assertEquals(0, this.channel1.read(readBuf, 0, 0));
        try {
            this.channel1.read(readBuf, 0, -1);
            fail("Should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // correct
        }
        readBuf = new java.nio.ByteBuffer[1];
        try {
            this.channel1.read(readBuf, 2, 1);
            fail("Should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // correct
        }
        try {
            this.channel1.read(readBuf, 2, -1);
            fail("Should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // correct
        }
        this.server1.close();
        try {
            assertEquals(CAPACITY_NORMAL, this.channel1.read(null, -1, -1));
            fail("Should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // correct
        }
    }

    // -------------------------------------------------
    // Test for read/write wite real data
    // -------------------------------------------------

    /*
     * SocketChannelImpl.read(ByteBuffer)'
     */
    public void testReadByteBufferArray_Blocking_ReadWriteRealData()
            throws IOException {
        byte[] serverWBuf = new byte[CAPACITY_NORMAL * 2];
        byte[] serverRBuf = new byte[CAPACITY_NORMAL * 2];
        for (int i = 0; i < CAPACITY_NORMAL * 2; i++) {
            serverWBuf[i] = (byte) i;
        }
        java.nio.ByteBuffer[] buf = new java.nio.ByteBuffer[2];
        buf[0] = ByteBuffer.allocate(CAPACITY_NORMAL);
        buf[1] = ByteBuffer.allocate(CAPACITY_NORMAL);
        this.channel1.connect(localAddr1);
        Socket serverSocket = this.server1.accept();
        assertTrue(this.channel1.isConnected());
        OutputStream out = serverSocket.getOutputStream();
        InputStream in = serverSocket.getInputStream();
        out.write(serverWBuf);
        // FIXME: RI fail here randomly, possible RI's bug?
        assertEquals(CAPACITY_NORMAL * 2, this.channel1.read(buf));
        buf[0].flip();
        buf[1].flip();
        assertEquals(66051, buf[0].asIntBuffer().get());
        assertEquals(CAPACITY_NORMAL * 2, this.channel1.write(buf));
        in.read(serverRBuf);

        for (int i = 0; i < serverRBuf.length; i++) {
            assertEquals((byte) i, serverRBuf[i]);
        }
        for (int i = CAPACITY_NORMAL; i < CAPACITY_NORMAL * 2; i++) {
            assertEquals((byte) i, serverRBuf[i]);
        }
        this.channel1.close();
        try {
            assertEquals(CAPACITY_NORMAL, this.channel1.read(buf));
            fail("Should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // correct
        }
    }

    /*
     * SocketChannelImpl.read(ByteBuffer)'
     */
    public void testReadByteBufferArray_NonBlocking_ReadWriteRealData()
            throws IOException {
        byte[] serverWBuf = new byte[CAPACITY_NORMAL * 2];
        byte[] serverRBuf = new byte[CAPACITY_NORMAL * 2];
        for (int i = 0; i < CAPACITY_NORMAL * 2; i++) {
            serverWBuf[i] = (byte) i;
        }
        java.nio.ByteBuffer[] buf = new java.nio.ByteBuffer[2];
        buf[0] = ByteBuffer.allocate(CAPACITY_NORMAL);
        buf[1] = ByteBuffer.allocate(CAPACITY_NORMAL);
        this.channel1.configureBlocking(false);
        this.channel1.connect(localAddr1);
        Socket serverSocket = this.server1.accept();

        if (tryFinish()) {
            OutputStream out = serverSocket.getOutputStream();
            InputStream in = serverSocket.getInputStream();
            out.write(serverWBuf);
            long readCount = this.channel1.read(buf);
            if (0 != readCount) {
                assertEquals(CAPACITY_NORMAL * 2, readCount);

                buf[0].flip();
                buf[1].flip();
                assertEquals(66051, buf[0].asIntBuffer().get());
                assertEquals(CAPACITY_NORMAL * 2, this.channel1.write(buf));
                in.read(serverRBuf);

                for (int i = 0; i < serverRBuf.length; i++) {
                    assertEquals((byte) i, serverRBuf[i]);
                }
                for (int i = CAPACITY_NORMAL; i < CAPACITY_NORMAL * 2; i++) {
                    assertEquals((byte) i, serverRBuf[i]);
                }
            } else {
                System.err
                        .println("Read fail, testReadByteBufferArray_NonBlocking_ReadWriteRealData is not finished.");
            }
        }
        this.channel1.close();
        try {
            assertEquals(CAPACITY_NORMAL, this.channel1.read(buf));
            fail("Should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // correct
        }
    }

    /*
     * SocketChannelImpl.read(ByteBuffer)'
     */
    public void testReadByteBufferArrayIntInt_Blocking_ReadWriteRealData()
            throws IOException {
        byte[] serverWBuf = new byte[CAPACITY_NORMAL * 2];
        byte[] serverRBuf = new byte[CAPACITY_NORMAL * 2];
        for (int i = 0; i < CAPACITY_NORMAL * 2; i++) {
            serverWBuf[i] = (byte) i;
        }
        java.nio.ByteBuffer[] buf = new java.nio.ByteBuffer[2];
        buf[0] = ByteBuffer.allocate(CAPACITY_NORMAL);
        buf[1] = ByteBuffer.allocate(CAPACITY_NORMAL);
        this.channel1.connect(localAddr1);
        Socket serverSocket = this.server1.accept();
        assertTrue(this.channel1.isConnected());
        OutputStream out = serverSocket.getOutputStream();
        InputStream in = serverSocket.getInputStream();
        out.write(serverWBuf);
        assertEquals(CAPACITY_NORMAL, this.channel1.read(buf, 0, 1));
        assertEquals(CAPACITY_NORMAL, this.channel1.read(buf, 1, 1));
        buf[0].flip();
        buf[1].flip();
        assertEquals(66051, buf[0].asIntBuffer().get());
        assertEquals(CAPACITY_NORMAL, this.channel1.write(buf, 1, 1));
        in.read(serverRBuf);

        for (int i = 0; i < CAPACITY_NORMAL; i++) {
            assertEquals((byte) (i + CAPACITY_NORMAL), serverRBuf[i]);
        }
        this.channel1.close();
        try {
            assertEquals(CAPACITY_NORMAL, this.channel1.read(buf));
            fail("Should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // correct
        }
    }

    /*
     * SocketChannelImpl.read(ByteBuffer)'
     */
    public void testReadByteBufferArrayIntInt_NonBlocking_ReadWriteRealData()
            throws IOException {
        byte[] serverWBuf = new byte[CAPACITY_NORMAL * 2];
        byte[] serverRBuf = new byte[CAPACITY_NORMAL * 2];
        for (int i = 0; i < CAPACITY_NORMAL * 2; i++) {
            serverWBuf[i] = (byte) i;
        }
        java.nio.ByteBuffer[] buf = new java.nio.ByteBuffer[2];
        buf[0] = ByteBuffer.allocate(CAPACITY_NORMAL);
        buf[1] = ByteBuffer.allocate(CAPACITY_NORMAL);
        this.channel1.configureBlocking(false);
        this.channel1.connect(localAddr1);
        Socket serverSocket = this.server1.accept();
        if (tryFinish()) {
            OutputStream out = serverSocket.getOutputStream();
            InputStream in = serverSocket.getInputStream();
            out.write(serverWBuf);
            long readCount = this.channel1.read(buf, 0, 1);
            if (readCount > 0) {
                assertEquals(CAPACITY_NORMAL, readCount);
                buf[0].flip();
                assertEquals(66051, buf[0].asIntBuffer().get());
                readCount = this.channel1.read(buf, 1, 1);
                if (readCount > 0) {
                    assertEquals(CAPACITY_NORMAL, readCount);
                    buf[1].flip();
                    assertEquals(CAPACITY_NORMAL, this.channel1.write(buf, 1, 1));
                    readCount = in.read(serverRBuf);
                    if (readCount > 0) {
                        assertEquals(CAPACITY_NORMAL, readCount);
                        for (int i = 0; i < CAPACITY_NORMAL; i++) {
                            assertEquals((byte) (i + CAPACITY_NORMAL),
                                    serverRBuf[i]);
                        }
                    } else {
                        System.err
                                .println("fail to read buf[1] again, testReadByteBufferArrayIntInt_NonBlocking_ReadWriteRealData not finish.");
                    }
                } else {
                    System.err
                            .println("fail to read buf[1], testReadByteBufferArrayIntInt_NonBlocking_ReadWriteRealData not finish.");
                }
            } else {
                System.err
                        .println("fail to read buf[0], testReadByteBufferArrayIntInt_NonBlocking_ReadWriteRealData not finish.");
            }

        } else {
            System.err
                    .println("fail to connect, testReadByteBufferArrayIntInt_NonBlocking_ReadWriteRealData not finish.");
        }
        this.channel1.close();
        try {
            assertEquals(CAPACITY_NORMAL, this.channel1.read(buf));
            fail("Should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // correct
        }
    }

    public void testSocket_configureblocking() throws IOException {
        byte[] serverWBuf = new byte[CAPACITY_NORMAL];
        for (int i = 0; i < serverWBuf.length; i++) {
            serverWBuf[i] = (byte) i;
        }
        java.nio.ByteBuffer buf = java.nio.ByteBuffer
                .allocate(CAPACITY_NORMAL + 1);
        channel1.connect(localAddr1);
        server1.accept();
        Socket sock = this.channel1.socket();
        channel1.configureBlocking(false);
        assertFalse(channel1.isBlocking());
        OutputStream channelSocketOut = sock.getOutputStream();
        try {
            // write operation is not allowed in non-blocking mode
            channelSocketOut.write(buf.array());
            fail("Non-Blocking mode should cause IllegalBlockingModeException");
        } catch (IllegalBlockingModeException e) {
            // correct
        }
        channel1.configureBlocking(true);
        assertTrue(channel1.isBlocking());
        // write operation is allowed in blocking mode
        channelSocketOut.write(buf.array());
    }

    public void testConfigureBlockingWhileRead() throws IOException {
        channel1.connect(localAddr1);
        server1.accept();
        assertTrue(this.channel1.isConnected());
        new ReadThread(channel1).start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {}
        channel1.configureBlocking(false);
        assertFalse(channel1.isBlocking());
    }

    private static class ReadThread extends Thread {
        private SocketChannel channel;

        ReadThread(SocketChannel sc) {
            channel = sc;
        }

        public void run() {
            ByteBuffer bf = ByteBuffer.allocate(100);
            try {
                channel.read(bf);
            } catch (IOException e) {
            }
        }
    }

    /**
     * @tests SocketChannel#read(ByteBuffer[], int, int) when remote server
     *        closed
     */
    public void test_socketChannel_read_ByteBufferII_remoteClosed()
            throws Exception {
        // regression 1 for HARMONY-549
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(localAddr2);
        SocketChannel sc = SocketChannel.open();
        sc.connect(localAddr2);
        ssc.accept().close();
        ByteBuffer[] buf = { ByteBuffer.allocate(10) };
        assertEquals(-1, sc.read(buf, 0, 1));
        ssc.close();
        sc.close();
    }

    /**
     * @tests SocketChannel#write(ByteBuffer[], int, int)
     */
    public void test_socketChannel_write_ByteBufferII() throws Exception {
        // regression 2 for HARMONY-549
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(localAddr2);
        SocketChannel sc = SocketChannel.open();
        sc.connect(localAddr2);
        SocketChannel sock = ssc.accept();
        ByteBuffer[] buf = { ByteBuffer.allocate(10), null };
        try {
            sc.write(buf, 0, 2);
            fail("should throw NPE");
        } catch (NullPointerException e) {
            // expected
        }
        ssc.close();
        sc.close();
        ByteBuffer target = ByteBuffer.allocate(10);
        assertEquals(-1, sock.read(target));
    }

    /**
     * @tests SocketChannel#read(ByteBuffer[], int, int) with a null ByteBuffer
     */
    public void test_socketChannel_read_ByteBufferII_bufNULL() throws Exception {
        // regression 3 for HARMONY-549
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(localAddr2);
        SocketChannel sc = SocketChannel.open();
        sc.connect(localAddr2);
        ssc.accept();
        ByteBuffer[] buf = new ByteBuffer[2];
        buf[0] = ByteBuffer.allocate(1);
        // let buf[1] be null
        try {
            sc.read(buf, 0, 2);
            fail("should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }
        ssc.close();
        sc.close();
    }

    /**
     * @tests SocketChannel#write(ByteBuffer) after close
     */
    public void test_socketChannel_write_close() throws Exception {
        // regression 4 for HARMONY-549
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(localAddr2);
        SocketChannel sc = SocketChannel.open();
        sc.connect(localAddr2);
        SocketChannel sock = ssc.accept();
        ByteBuffer buf = null;
        ssc.close();
        sc.close();
        try {
            sc.write(buf);
            fail("should throw NPE");
        } catch (NullPointerException e) {
            // expected
        }
        sock.close();
    }

    /**
     * @tests SocketChannel#write(ByteBuffer) if position is not zero
     */
    public void test_socketChannel_write_ByteBuffer_posNotZero()
            throws Exception {
        // regression 5 for HARMONY-549
        final String testStr = "Hello World";
        ByteBuffer readBuf = ByteBuffer.allocate(11);
        ByteBuffer buf = ByteBuffer.wrap(testStr.getBytes());
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(localAddr2);
        SocketChannel sc = SocketChannel.open();
        sc.connect(localAddr2);
        buf.position(2);
        ssc.accept().write(buf);
        assertEquals(9, sc.read(readBuf));
        buf.flip();
        readBuf.flip();
        byte[] read = new byte[9];
        byte[] write = new byte[11];
        buf.get(write);
        readBuf.get(read);
        for (int i = 0; i < 9; i++) {
            assertEquals(read[i], write[i + 2]);
        }
    }
}
