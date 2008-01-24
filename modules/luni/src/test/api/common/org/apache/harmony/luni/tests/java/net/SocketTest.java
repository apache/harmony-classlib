/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.harmony.luni.tests.java.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketImpl;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.Permission;

import tests.support.Support_Configuration;
import tests.support.Support_PortManager;

public class SocketTest extends SocketTestCase {
    private class ClientThread implements Runnable {

        public void run() {
            try {
                Socket socket = new Socket();
                InetSocketAddress addr = new InetSocketAddress(host, port);
                socket.connect(addr);

                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class ServerThread implements Runnable {
        private static final int FIRST_TIME = 1;

        private static final int SECOND_TIME = 2;

        private int backlog = 10;

        public boolean ready = false;

        private int serverSocketConstructor = 0;

        public void run() {
            try {

                ServerSocket socket = null;
                switch (serverSocketConstructor) {
                case FIRST_TIME:
                    socket = new ServerSocket(port, backlog,
                            new InetSocketAddress(host, port).getAddress());
                    port = socket.getLocalPort();
                    break;
                case SECOND_TIME:
                    socket = new ServerSocket(port, backlog);
                    host = socket.getInetAddress().getHostName();
                    port = socket.getLocalPort();
                    break;
                default:
                    socket = new ServerSocket();
                    break;
                }

                synchronized (this) {
                    ready = true;
                    this.notifyAll();
                }

                socket.setSoTimeout(5000);
                socket.accept();

                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        public synchronized void waitCreated() throws Exception {
            while (!ready) {
                this.wait();
            }
        }
    }

    boolean interrupted;

    String host = "localhost";
    int port;

    Thread t;

    private void connectTestImpl(int ssConsType) throws Exception {
        ServerThread server = new ServerThread();
        server.serverSocketConstructor = ssConsType;
        Thread serverThread = new Thread(server);
        serverThread.start();
        server.waitCreated();

        ClientThread client = new ClientThread();
        Thread clientThread = new Thread(client);
        clientThread.start();
        try {
            serverThread.join();
            clientThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void tearDown() {
        try {
            if (t != null) {
                t.interrupt();
            }
        } catch (Exception e) {
        }
        this.t = null;
        this.interrupted = false;
    }

    /**
     * @tests java.net.Socket#bind(java.net.SocketAddress)
     */
    public void test_bindLjava_net_SocketAddress() throws IOException {

        @SuppressWarnings("serial")
        class UnsupportedSocketAddress extends SocketAddress {
            public UnsupportedSocketAddress() {
            }
        }

        // Address we cannot bind to
        Socket theSocket = new Socket();
        InetSocketAddress bogusAddress = new InetSocketAddress(InetAddress
                .getByAddress(Support_Configuration.nonLocalAddressBytes), 42);
        try {
            theSocket.bind(bogusAddress);
            fail("No exception when binding to bad address");
        } catch (IOException ex) {
            // Expected
        }
        theSocket.close();

        // Now create a socket that is not bound and then bind it
        theSocket = new Socket();
        theSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(), 0));
        int portNumber = theSocket.getLocalPort();

        // Validate that the localSocketAddress reflects the address we
        // bound to
        assertEquals("Local address not correct after bind",
                new InetSocketAddress(InetAddress.getLocalHost(), portNumber),
                theSocket.getLocalSocketAddress());

        // Make sure we can now connect and that connections appear to come
        // from the address we bound to.
        InetSocketAddress theAddress = new InetSocketAddress(InetAddress
                .getLocalHost(), 0);
        ServerSocket server = new ServerSocket();
        server.bind(theAddress);
        int sport = server.getLocalPort();
        InetSocketAddress boundAddress = new InetSocketAddress(InetAddress
                .getLocalHost(), sport);

        theSocket.connect(boundAddress);
        Socket worker = server.accept();
        assertEquals(
                "Returned Remote address from server connected to does not match expected local address",
                new InetSocketAddress(InetAddress.getLocalHost(), portNumber),
                worker.getRemoteSocketAddress());
        theSocket.close();
        worker.close();
        server.close();

        // Validate if we pass in null that it picks an address for us and
        // all is ok
        theSocket = new Socket();
        theSocket.bind(null);
        assertNotNull("Bind with null did not work", theSocket
                .getLocalSocketAddress());
        theSocket.close();

        // now check the error conditions

        // Address that we have already bound to
        theSocket = new Socket();
        theAddress = new InetSocketAddress(InetAddress.getLocalHost(), 0);
        theSocket.bind(theAddress);

        Socket theSocket2 = new Socket();
        try {
            theSocket2.bind(theSocket.getLocalSocketAddress());
            fail("No exception binding to address that is not available");
        } catch (IOException ex) {
            // Expected
        }
        theSocket.close();
        theSocket2.close();

        // Unsupported SocketAddress subclass
        theSocket = new Socket();
        try {
            theSocket.bind(new UnsupportedSocketAddress());
            fail("No exception when binding using unsupported SocketAddress subclass");
        } catch (IllegalArgumentException ex) {
            // Expected
        }
        theSocket.close();
    }

    /**
     * @tests java.net.Socket#bind(java.net.SocketAddress)
     */
    public void test_bindLjava_net_SocketAddress_Proxy() throws IOException {
        // The Proxy will not impact on the bind operation. It can be assigned
        // with any address.
        Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(
                "127.0.0.1", 0));
        Socket socket = new Socket(proxy);

        InetAddress address = InetAddress.getByName("localhost");
        socket.bind(new InetSocketAddress(address, 0));

        assertEquals(address, socket.getLocalAddress());
        assertTrue(0 != socket.getLocalPort());

        socket.close();
    }

    /**
     * @tests java.net.Socket#close()
     */
    public void test_close() throws IOException {
        ServerSocket server = new ServerSocket(0);
        Socket client = new Socket(InetAddress.getLocalHost(), server
                .getLocalPort());

        try {
            client.setSoLinger(false, 100);
        } catch (IOException e) {
            handleException(e, SO_LINGER);
        }

        client.close();
        try {
            client.getOutputStream();
            fail("Failed to close socket");
        } catch (IOException e) { // Caught Exception after close.
            // Expected
        }

        server.close();
    }

    /**
     * @tests Socket#connect(SocketAddress) try an unknownhost
     */
    public void test_connect_unknownhost() throws Exception {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress("unknownhost.invalid", 12345));
            fail("Should throw UnknownHostException");
        } catch (UnknownHostException e) {
            // expected
        }
    }

    /**
     * @tests Socket#connect(SocketAddress)
     */
    public void test_connect_unresolved() throws IOException {
        Socket socket = new Socket();

        // Try a known host created by createUnresolved()
        try {
            socket.connect(InetSocketAddress.createUnresolved("www.apache.org",
                    80));
            fail("Should throw UnknownHostException");
        } catch (UnknownHostException e) {
            // expected
        }

        // Try an unknown host created by createUnresolved()
        try {
            socket.connect(InetSocketAddress.createUnresolved(
                    "unknownhost.invalid", 12345));
            fail("Should throw UnknownHostException");
        } catch (UnknownHostException e) {
            // expected
        }
    }

    /**
     * @tests java.net.Socket#connect(java.net.SocketAddress)
     */
    public void test_connectLjava_net_SocketAddress() throws Exception {

        @SuppressWarnings("serial")
        class UnsupportedSocketAddress extends SocketAddress {
            public UnsupportedSocketAddress() {
            }
        }

        Socket theSocket = new Socket();
        try {
            theSocket.connect(null);
            fail("No exception for null arg");
        } catch (IllegalArgumentException e) {
            // Expected
        }

        try {
            theSocket.connect(new UnsupportedSocketAddress());
            fail("No exception for invalid socket address");
        } catch (IllegalArgumentException e) {
            // Expected
        }

        try {
            theSocket.connect(new InetSocketAddress(InetAddress
                    .getByAddress(new byte[] { 0, 0, 0, 0 }), 42));
            fail("No exception with non-connectable address");
        } catch (ConnectException e) {
            // Expected
        }

        // now validate that we get a connect exception if we try to connect to
        // an address on which nobody is listening
        theSocket = new Socket();
        try {
            theSocket.connect(new InetSocketAddress(InetAddress.getLocalHost(),
                    0));
            fail("No exception when connecting to address nobody listening on");
        } catch (ConnectException e) {
            // Expected
        }

        // Now validate that we can actually connect when somebody is listening
        ServerSocket server = new ServerSocket(0);
        InetSocketAddress boundAddress = new InetSocketAddress(InetAddress
                .getLocalHost(), server.getLocalPort());
        Socket client = new Socket();
        client.connect(boundAddress);

        // validate that when a socket is connected that it answers
        // correctly to related queries
        assertTrue("Wrong connected status", client.isConnected());
        assertFalse("Wrong closed status", client.isClosed());
        assertTrue("Wrong bound status", client.isBound());
        assertFalse("Wrong input shutdown status", client.isInputShutdown());
        assertFalse("Wrong output shutdown status", client.isOutputShutdown());
        assertTrue("Local port was 0", client.getLocalPort() != 0);

        client.close();
        server.close();

        // Now validate that we get the right exception if we connect when we
        // are already connected
        server = new ServerSocket(0);
        boundAddress = new InetSocketAddress(InetAddress.getLocalHost(), server
                .getLocalPort());
        client = new Socket();
        client.connect(boundAddress);

        try {
            client.connect(boundAddress);
            fail("No exception when we try to connect on a connected socket: ");
        } catch (SocketException e) {
            // Expected
        }
        client.close();
        server.close();
    }

    /**
     * Regression for Harmony-2503
     */
    public void test_connectLjava_net_SocketAddress_AnyAddress()
            throws Exception {
        connectTestImpl(ServerThread.FIRST_TIME);
        connectTestImpl(ServerThread.SECOND_TIME);
    }

    /**
     * @tests java.net.Socket#connect(java.net.SocketAddress, int)
     */
    public void test_connectLjava_net_SocketAddressI() throws Exception {

        @SuppressWarnings("serial")
        class UnsupportedSocketAddress extends SocketAddress {
            public UnsupportedSocketAddress() {
            }
        }

        // Start by validating the error checks
        Socket theSocket = new Socket();
        try {
            theSocket.connect(new InetSocketAddress(0), -100);
            fail("No exception for negative timeout");
        } catch (IllegalArgumentException e) {
            // Expected
        }

        try {
            theSocket.connect(null, 0);
            fail("No exception for null address");
        } catch (IllegalArgumentException e) {
            // Expected
        }

        try {
            theSocket.connect(new UnsupportedSocketAddress(), 1000);
            fail("No exception for invalid socket address type");
        } catch (IllegalArgumentException e) {
            // Expected
        }

        SocketAddress nonConnectableAddress = new InetSocketAddress(InetAddress
                .getByAddress(new byte[] { 0, 0, 0, 0 }), 0);
        try {
            theSocket.connect(nonConnectableAddress, 1000);
            fail("No exception when non Connectable Address passed in: ");
        } catch (SocketException e) {
            // Expected
        }

        // Now validate that we get a connect exception if we try to connect to
        // an address on which nobody is listening
        theSocket = new Socket();
        try {
            theSocket.connect(new InetSocketAddress(InetAddress.getLocalHost(),
                    0), 0);
            fail("No exception when connecting to address nobody listening on");
        } catch (ConnectException e) {
            // Expected
        }
        theSocket.close();

        // Now validate that we can actually connect when somebody is listening
        ServerSocket server = new ServerSocket(0);
        InetSocketAddress boundAddress = new InetSocketAddress(InetAddress
                .getLocalHost(), server.getLocalPort());
        Socket client = new Socket();
        client.connect(boundAddress, 0);

        // Validate that when a socket is connected that it answers
        // correctly to related queries
        assertTrue("Wrong connected status", client.isConnected());
        assertFalse("Wrong closed status", client.isClosed());
        assertTrue("Wrong bound status", client.isBound());
        assertFalse("Wrong input shutdown status", client.isInputShutdown());
        assertFalse("Wrong output shutdown status", client.isOutputShutdown());
        assertTrue("Local port was 0", client.getLocalPort() != 0);

        client.close();
        server.close();

        // Now validate that we get a connect exception if we try to connect to
        // an address on which nobody is listening
        theSocket = new Socket();
        SocketAddress nonListeningAddress = new InetSocketAddress(InetAddress
                .getLocalHost(), 42);
        try {
            theSocket.connect(nonListeningAddress, 1000);
            fail("No exception when connecting to address nobody listening on");
        } catch (ConnectException e) {
            // Expected
        }
        theSocket.close();

        // Now validate that we get a interrupted exception if we try to connect
        // to an address on which nobody is accepting connections and the
        // timeout expired
        theSocket = new Socket();
        try {
            theSocket.connect(new InetSocketAddress(InetAddress.getLocalHost(),
                    1), 200);
            fail("No interrupted exception when connecting to address nobody listening on with short timeout 200");
        } catch (SocketTimeoutException e) {
            // Expected
        }
        theSocket.close();

        // Now validate that we get the right exception if we connect when we
        // are already connected
        server = new ServerSocket(0);
        boundAddress = new InetSocketAddress(InetAddress.getLocalHost(), server
                .getLocalPort());
        client = new Socket();
        client.connect(boundAddress, 10000);

        try {
            client.connect(boundAddress, 10000);
            fail("No exception when we try to connect on a connected socket: ");
        } catch (SocketException e) {
            // Expected
        }
        client.close();
        server.close();
    }

    /**
     * @tests java.net.Socket#Socket()
     */
    public void test_Constructor() {
        // create the socket and then validate some basic state
        Socket s = new Socket();
        assertFalse("new socket should not be connected", s.isConnected());
        assertFalse("new socket should not be bound", s.isBound());
        assertFalse("new socket should not be closed", s.isClosed());
        assertFalse("new socket should not be in InputShutdown", s
                .isInputShutdown());
        assertFalse("new socket should not be in OutputShutdown", s
                .isOutputShutdown());
    }

    /**
     * @tests java.net.Socket#Socket(java.lang.String, int)
     */
    public void test_ConstructorLjava_lang_StringI() throws IOException {
        ServerSocket server = new ServerSocket(0);
        Socket client = new Socket(InetAddress.getLocalHost(), server
                .getLocalPort());

        assertEquals("Failed to create socket", server.getLocalPort(), client
                .getPort());

        // Regression for HARMONY-946
        ServerSocket ss = new ServerSocket(0);
        Socket s = new Socket("0.0.0.0 ", ss.getLocalPort());
        ss.close();
        s.close();
    }

    /**
     * @tests java.net.Socket#Socket(java.lang.String, int,
     *        java.net.InetAddress, int)
     */
    public void test_ConstructorLjava_lang_StringILjava_net_InetAddressI()
            throws IOException {

        ServerSocket server = new ServerSocket(0);
        int serverPort = server.getLocalPort();
        Socket client = new Socket(InetAddress.getLocalHost().getHostName(),
                serverPort, InetAddress.getLocalHost(), 0);
        assertTrue("Failed to create socket", client.getPort() == serverPort);
        client.close();

        Socket theSocket = null;
        try {
            theSocket = new Socket("127.0.0.1", serverPort, InetAddress
                    .getLocalHost(), 0);
        } catch (IOException e) {
            // check here if InetAddress.getLocalHost() is returning the
            // loopback address, if so that is likely the cause of the failure
            assertFalse(
                    "Misconfiguration - local host is the loopback address",
                    InetAddress.getLocalHost().isLoopbackAddress());
            throw e;
        }

        assertTrue(theSocket.isConnected());

        try {
            new Socket("127.0.0.1", serverPort, theSocket.getLocalAddress(),
                    theSocket.getLocalPort());
            fail("Was able to create two sockets on same port");
        } catch (IOException e) {
            // Expected
        }

        theSocket.close();
        server.close();
    }

    /**
     * @tests java.net.Socket#Socket(java.lang.String, int,
     *        java.net.InetAddress, int)
     */
    public void test_ConstructorLjava_lang_StringILjava_net_InetAddressI_ipv6()
            throws IOException {

        boolean preferIPv6 = "true".equals(System
                .getProperty("java.net.preferIPv6Addresses"));
        boolean preferIPv4 = "true".equals(System
                .getProperty("java.net.preferIPv4Stack"));
        boolean runIPv6 = "true".equals(System.getProperty("run.ipv6tests"));

        if (!runIPv6 || !preferIPv6 || preferIPv4) {
            // This test is not for me
            return;
        }

        ServerSocket server = new ServerSocket(0);
        int serverPort = server.getLocalPort();
        Socket client = new Socket(InetAddress.getLocalHost().getHostName(),
                serverPort, InetAddress.getLocalHost(), 0);
        assertTrue("Failed to create socket", client.getPort() == serverPort);
        client.close();

        Socket theSocket = null;
        try {
            theSocket = new Socket(Support_Configuration.IPv6GlobalAddressJcl4,
                    serverPort, InetAddress.getLocalHost(), 0);
        } catch (IOException e) {
            // check here if InetAddress.getLocalHost() is returning the
            // loopback address, if so that is likely the cause of the failure
            assertFalse(
                    "Misconfiguration - local host is the loopback address",
                    InetAddress.getLocalHost().isLoopbackAddress());
            throw e;
        }

        assertTrue(theSocket.isConnected());

        try {
            new Socket(Support_Configuration.IPv6GlobalAddressJcl4, serverPort,
                    theSocket.getLocalAddress(), theSocket.getLocalPort());
            fail("Was able to create two sockets on same port");
        } catch (IOException e) {
            // Expected
        }

        theSocket.close();
        server.close();
    }

    /**
     * @tests java.net.Socket#Socket(java.lang.String, int, boolean)
     */
    @SuppressWarnings("deprecation")
    public void test_ConstructorLjava_lang_StringIZ() throws IOException {
        ServerSocket server = new ServerSocket(0);
        int serverPort = server.getLocalPort();
        Socket client = new Socket(InetAddress.getLocalHost().getHostAddress(),
                serverPort, true);

        assertEquals("Failed to create socket", serverPort, client.getPort());
        client.close();

        client = new Socket(InetAddress.getLocalHost().getHostName(),
                serverPort, false);
        client.close();
        server.close();
    }

    /**
     * @tests java.net.Socket#Socket(java.net.InetAddress, int)
     */
    public void test_ConstructorLjava_net_InetAddressI() throws IOException {
        ServerSocket server = new ServerSocket(0);
        Socket client = new Socket(InetAddress.getLocalHost(), server
                .getLocalPort());

        assertEquals("Failed to create socket", server.getLocalPort(), client
                .getPort());

        client.close();
        server.close();
    }

    /**
     * @tests java.net.Socket#Socket(java.net.InetAddress, int,
     *        java.net.InetAddress, int)
     */
    public void test_ConstructorLjava_net_InetAddressILjava_net_InetAddressI()
            throws IOException {
        ServerSocket server = new ServerSocket(0);
        Socket client = new Socket(InetAddress.getLocalHost(), server
                .getLocalPort(), InetAddress.getLocalHost(), 0);
        assertNotSame("Failed to create socket", 0, client.getLocalPort());
    }

    /**
     * @tests java.net.Socket#Socket(java.net.InetAddress, int, boolean)
     */
    @SuppressWarnings("deprecation")
    public void test_ConstructorLjava_net_InetAddressIZ() throws IOException {
        ServerSocket server = new ServerSocket(0);
        int serverPort = server.getLocalPort();

        Socket client = new Socket(InetAddress.getLocalHost(), serverPort, true);
        assertEquals("Failed to create socket", serverPort, client.getPort());

        client = new Socket(InetAddress.getLocalHost(), serverPort, false);
        client.close();
    }

    /**
     * @tests java.net.Socket#Socket(Proxy)
     */
    public void test_ConstructorLjava_net_Proxy_Exception() {

        class MockSecurityManager extends SecurityManager {

            public void checkConnect(String host, int port) {
                if ("127.0.0.1".equals(host)) {
                    throw new SecurityException("permission is not allowed");
                }
            }

            public void checkPermission(Permission permission) {
                return;
            }
        }

        SocketAddress addr1 = InetSocketAddress.createUnresolved("127.0.0.1",
                80);
        SocketAddress addr2 = new InetSocketAddress("localhost", 80);

        Proxy proxy1 = new Proxy(Proxy.Type.HTTP, addr1);
        // IllegalArgumentException test
        try {
            new Socket(proxy1);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }

        Proxy proxy2 = new Proxy(Proxy.Type.SOCKS, addr1);
        // should not throw any exception
        new Socket(proxy2);
        new Socket(Proxy.NO_PROXY);

        // SecurityException test
        SecurityManager originalSecurityManager = System.getSecurityManager();
        try {
            System.setSecurityManager(new MockSecurityManager());
        } catch (SecurityException e) {
            System.err
                    .println("No permission to setSecurityManager, security related test in test_ConstructorLjava_net_Proxy_Security is ignored");
            return;
        }

        Proxy proxy3 = new Proxy(Proxy.Type.SOCKS, addr1);
        Proxy proxy4 = new Proxy(Proxy.Type.SOCKS, addr2);
        try {
            try {
                new Socket(proxy3);
                fail("should throw SecurityException");
            } catch (SecurityException e) {
                // expected
            }
            try {
                new Socket(proxy4);
                fail("should throw SecurityException");
            } catch (SecurityException e) {
                // expected
            }
        } finally {
            System.setSecurityManager(originalSecurityManager);
        }

    }

    /**
     * @tests java.net.Socket#getChannel()
     */
    public void test_getChannel() {
        assertNull(new Socket().getChannel());
    }

    /**
     * @tests java.net.Socket#getInetAddress()
     */
    public void test_getInetAddress() throws IOException {
        ServerSocket server = new ServerSocket(0);
        Socket client = new Socket(InetAddress.getLocalHost(), server
                .getLocalPort());

        assertTrue("Returned incorrect InetAdrees", client.getInetAddress()
                .equals(InetAddress.getLocalHost()));

        client.close();
        server.close();
    }

    /**
     * @tests java.net.Socket#getInputStream()
     */
    public void test_getInputStream() throws IOException {
        // Simple fetch test
        ServerSocket server = new ServerSocket(0);
        Socket client = new Socket(InetAddress.getLocalHost(), server
                .getLocalPort());
        InputStream is = client.getInputStream();
        assertNotNull("Failed to get stream", is);
        is.close();
        client.close();
        server.close();

        // Simple read/write test over the IO streams
        final ServerSocket pingServer = new ServerSocket(0);
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    Socket worker = pingServer.accept();
                    pingServer.close();
                    InputStream in = worker.getInputStream();
                    in.read();
                    OutputStream out = worker.getOutputStream();
                    out.write(new byte[42]);
                    worker.close();
                } catch (IOException e) {
                    fail(e.getMessage());
                }
            }
        };
        Thread thread = new Thread(runnable, "Socket.getInputStream");
        thread.start();

        Socket pingClient = new Socket(InetAddress.getLocalHost(), pingServer
                .getLocalPort());

        // Busy wait until the client is connected.
        int c = 0;
        while (!pingClient.isConnected()) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
            if (++c > 4) {
                fail("thread is not alive");
            }
        }

        // Write some data to the server to provoke it
        OutputStream out = pingClient.getOutputStream();
        out.write(new byte[256]);

        InputStream in = pingClient.getInputStream();
        in.read(new byte[42]);

        // Check EOF
        assertEquals(-1, in.read());

        in.close();

        // No exception when reading a closed stream
        assertEquals(-1, in.read());

        pingClient.close();
        pingServer.close();
    }

    /**
     * @tests java.net.Socket#getKeepAlive()
     */
    public void test_getKeepAlive() {
        try {
            ServerSocket server = new ServerSocket(0);
            Socket client = new Socket(InetAddress.getLocalHost(), server
                    .getLocalPort(), null, 0);

            client.setKeepAlive(true);
            assertTrue("getKeepAlive false when it should be true", client
                    .getKeepAlive());

            client.setKeepAlive(false);
            assertFalse("getKeepAlive true when it should be False", client
                    .getKeepAlive());
            ensureExceptionThrownIfOptionIsUnsupportedOnOS(SO_KEEPALIVE);
        } catch (Exception e) {
            handleException(e, SO_KEEPALIVE);
        }
    }

    /**
     * @tests java.net.Socket#getLocalAddress()
     */
    public void test_getLocalAddress() throws IOException {
        ServerSocket server = new ServerSocket(0);
        Socket client = new Socket(InetAddress.getLocalHost(), server
                .getLocalPort());

        assertTrue("Returned incorrect InetAddress", client.getLocalAddress()
                .equals(InetAddress.getLocalHost()));

        // now validate that behaviour when the any address is returned
        String preferIPv4StackValue = System
                .getProperty("java.net.preferIPv4Stack");
        String preferIPv6AddressesValue = System
                .getProperty("java.net.preferIPv6Addresses");

        client = new Socket();
        client.bind(new InetSocketAddress(InetAddress.getByName("0.0.0.0"), 0));

        if (((preferIPv4StackValue == null) || preferIPv4StackValue
                .equalsIgnoreCase("false"))
                && (preferIPv6AddressesValue != null)
                && (preferIPv6AddressesValue.equals("true"))) {
            assertTrue(
                    "ANY address not returned correctly (getLocalAddress) with preferIPv6Addresses=true, preferIPv4Stack=false "
                            + client.getLocalSocketAddress(), client
                            .getLocalAddress() instanceof Inet6Address);
        } else {
            assertTrue(
                    "ANY address not returned correctly (getLocalAddress) with preferIPv6Addresses=true, preferIPv4Stack=true "
                            + client.getLocalSocketAddress(), client
                            .getLocalAddress() instanceof Inet4Address);
        }
        client.close();
        server.close();
    }

    /**
     * @tests java.net.Socket#getLocalPort()
     */
    public void test_getLocalPort() throws IOException {
        ServerSocket server = new ServerSocket(0);
        Socket client = new Socket(InetAddress.getLocalHost(), server
                .getLocalPort());

        assertNotSame("Returned incorrect port", 0, client.getLocalPort());

        client.close();
        server.close();
    }

    /**
     * @tests java.net.Socket#getLocalSocketAddress()
     */
    public void test_getLocalSocketAddress() throws IOException {
        // set up server connect and then validate that we get the right
        // response for the local address
        ServerSocket server = new ServerSocket(0);
        Socket client = new Socket(InetAddress.getLocalHost(), server
                .getLocalPort());
        int clientPort = client.getLocalPort();

        assertEquals("Returned incorrect InetSocketAddress(1):",
                new InetSocketAddress(InetAddress.getLocalHost(), clientPort),
                client.getLocalSocketAddress());
        client.close();
        server.close();

        // now create a socket that is not bound and validate we get the
        // right answer
        client = new Socket();
        assertNull(
                "Returned incorrect InetSocketAddress -unbound socket- Expected null",
                client.getLocalSocketAddress());

        // now bind the socket and make sure we get the right answer
        client.bind(new InetSocketAddress(InetAddress.getLocalHost(), 0));
        clientPort = client.getLocalPort();
        assertEquals("Returned incorrect InetSocketAddress(2):",
                new InetSocketAddress(InetAddress.getLocalHost(), clientPort),
                client.getLocalSocketAddress());
        client.close();

        // now validate the behaviour when the any address is returned
        client = new Socket();
        client.bind(new InetSocketAddress(InetAddress.getByName("0.0.0.0"), 0));

        String preferIPv4StackValue = System
                .getProperty("java.net.preferIPv4Stack");
        String preferIPv6AddressesValue = System
                .getProperty("java.net.preferIPv6Addresses");
        if (((preferIPv4StackValue == null) || preferIPv4StackValue
                .equalsIgnoreCase("false"))
                && (preferIPv6AddressesValue != null)
                && (preferIPv6AddressesValue.equals("true"))) {
            assertTrue(
                    "ANY address not returned correctly with preferIPv6Addresses=true, preferIPv4Stack=false "
                            + client.getLocalSocketAddress(),
                    ((InetSocketAddress) client.getLocalSocketAddress())
                            .getAddress() instanceof Inet6Address);
        } else {
            assertTrue(
                    "ANY address not returned correctly with preferIPv6Addresses=true, preferIPv4Stack=true "
                            + client.getLocalSocketAddress(),
                    ((InetSocketAddress) client.getLocalSocketAddress())
                            .getAddress() instanceof Inet4Address);
        }
        client.close();

        // now validate the same for getLocalAddress
        client = new Socket();
        client.bind(new InetSocketAddress(InetAddress.getByName("0.0.0.0"), 0));
        if (((preferIPv4StackValue == null) || preferIPv4StackValue
                .equalsIgnoreCase("false"))
                && (preferIPv6AddressesValue != null)
                && (preferIPv6AddressesValue.equals("true"))) {
            assertTrue(
                    "ANY address not returned correctly with preferIPv6Addresses=true, preferIPv4Stack=false "
                            + client.getLocalSocketAddress(),
                    ((InetSocketAddress) client.getLocalSocketAddress())
                            .getAddress() instanceof Inet6Address);
        } else {
            assertTrue(
                    "ANY address not returned correctly with preferIPv6Addresses=true, preferIPv4Stack=true "
                            + client.getLocalSocketAddress(),
                    ((InetSocketAddress) client.getLocalSocketAddress())
                            .getAddress() instanceof Inet4Address);
        }
        client.close();
    }

    /**
     * @tests java.net.Socket#getOOBInline()
     */
    public void test_getOOBInline() {
        try {
            Socket theSocket = new Socket();

            theSocket.setOOBInline(true);
            assertTrue("expected OOBIline to be true", theSocket.getOOBInline());

            theSocket.setOOBInline(false);
            assertFalse("expected OOBIline to be false", theSocket
                    .getOOBInline());

            theSocket.setOOBInline(false);
            assertFalse("expected OOBIline to be false", theSocket
                    .getOOBInline());

            ensureExceptionThrownIfOptionIsUnsupportedOnOS(SO_OOBINLINE);
        } catch (Exception e) {
            handleException(e, SO_OOBINLINE);
        }
    }

    /**
     * @tests java.net.Socket#getOutputStream()
     */
    @SuppressWarnings("deprecation")
    public void test_getOutputStream() throws IOException {
        // Simple fetch test
        ServerSocket server = new ServerSocket(0);
        Socket client = new Socket(InetAddress.getLocalHost(), server
                .getLocalPort());
        OutputStream os = client.getOutputStream();
        assertNotNull("Failed to get stream", os);
        os.close();
        client.close();
        server.close();

        // Simple read/write test over the IO streams
        final ServerSocket sinkServer = new ServerSocket(0);
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    Socket worker = sinkServer.accept();
                    sinkServer.close();
                    InputStream in = worker.getInputStream();
                    in.read();
                    in.close();
                    worker.close();
                } catch (IOException e) {
                    fail();
                }
            }
        };
        Thread thread = new Thread(runnable, "Socket.getOutputStream");
        thread.start();

        Socket pingClient = new Socket(InetAddress.getLocalHost(), sinkServer
                .getLocalPort());

        // Busy wait until the client is connected.
        int c = 0;
        while (!pingClient.isConnected()) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
            if (++c > 4) {
                fail("thread is not alive");
            }
        }

        // Write some data to the server
        OutputStream out = pingClient.getOutputStream();
        out.write(new byte[256]);

        // Wait for the server to finish
        Thread.yield();
        c = 0;
        while (thread.isAlive()) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
            if (++c > 4) {
                fail("read call did not exit");
            }
        }

        // Subsequent writes should throw an exception
        try {
            // The output buffer may remain valid until the close completes
            for (int i = 0; i < 400; i++) {
                out.write(new byte[256]);
            }
            fail("write to closed socket did not cause exception");
        } catch (IOException e) {
            // Expected
        }

        out.close();
        pingClient.close();
        sinkServer.close();

        // Regression test for HARMONY-2934
        Socket socket = new Socket("127.0.0.1", 0, false);
        OutputStream o = socket.getOutputStream();
        o.write(1);
        socket.close();

        // Regression test for HARMONY-873
        ServerSocket ss2 = new ServerSocket(0);
        Socket s = new Socket("127.0.0.1", ss2.getLocalPort());
        ss2.accept();
        s.shutdownOutput();
        try {
            s.getOutputStream();
            fail("should throw SocketException");
        } catch (SocketException e) {
            // expected
        }
    }

    /**
     * @tests java.net.Socket#getPort()
     */
    public void test_getPort() throws IOException {
        ServerSocket server = new ServerSocket(0);
        int serverPort = server.getLocalPort();
        Socket client = new Socket(InetAddress.getLocalHost(), serverPort);

        assertEquals("Returned incorrect port", serverPort, client.getPort());

        client.close();
        server.close();
    }

    /**
     * @tests java.net.Socket#getReceiveBufferSize()
     */
    public void test_getReceiveBufferSize() throws IOException {
        ServerSocket server = new ServerSocket(0);
        Socket client = new Socket(InetAddress.getLocalHost(), server
                .getLocalPort());

        try {
            client.setReceiveBufferSize(130);
            assertTrue("Incorrect buffer size",
                    client.getReceiveBufferSize() >= 130);

            ensureExceptionThrownIfOptionIsUnsupportedOnOS(SO_RCVBUF);
        } catch (Exception e) {
            handleException(e, SO_RCVBUF);
        } finally {
            client.close();
            server.close();
        }
    }

    /**
     * @tests java.net.Socket#getRemoteSocketAddress()
     */
    public void test_getRemoteSocketAddress() throws IOException {
        // set up server connect and then validate that we get the right
        // response for the remote address
        ServerSocket server = new ServerSocket(0);
        int serverPort = server.getLocalPort();
        Socket client = new Socket(InetAddress.getLocalHost(), serverPort);

        assertEquals("Returned incorrect InetSocketAddress(1):",
                new InetSocketAddress(InetAddress.getLocalHost(), serverPort),
                client.getRemoteSocketAddress());
        client.close();

        // now create one that is not connected and validate that we get the
        // right answer
        Socket theSocket = new Socket();
        theSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(), 0));
        assertNull("Returned incorrect InetSocketAddress -unconnected socket:",
                theSocket.getRemoteSocketAddress());

        // now connect and validate we get the right answer
        theSocket.connect(new InetSocketAddress(InetAddress.getLocalHost(),
                serverPort));
        assertEquals("Returned incorrect InetSocketAddress(2):",
                new InetSocketAddress(InetAddress.getLocalHost(), serverPort),
                theSocket.getRemoteSocketAddress());
        theSocket.close();

        server.close();
    }

    /**
     * @tests java.net.Socket#getReuseAddress()
     */
    public void test_getReuseAddress() {
        try {
            Socket theSocket = new Socket();
            theSocket.setReuseAddress(true);
            assertTrue("getReuseAddress false when it should be true",
                    theSocket.getReuseAddress());
            theSocket.setReuseAddress(false);
            assertFalse("getReuseAddress true when it should be False",
                    theSocket.getReuseAddress());

            ensureExceptionThrownIfOptionIsUnsupportedOnOS(SO_REUSEADDR);
        } catch (Exception e) {
            handleException(e, SO_REUSEADDR);
        }
    }

    /**
     * @tests java.net.Socket#getSendBufferSize()
     */
    public void test_getSendBufferSize() throws IOException {
        ServerSocket server = new ServerSocket(0);
        Socket client = new Socket(InetAddress.getLocalHost(), server
                .getLocalPort());

        try {
            client.setSendBufferSize(134);
            assertTrue("Incorrect buffer size",
                    client.getSendBufferSize() >= 134);

            ensureExceptionThrownIfOptionIsUnsupportedOnOS(SO_SNDBUF);
        } catch (Exception e) {
            handleException(e, SO_SNDBUF);
        } finally {
            client.close();
            server.close();
        }
    }

    /**
     * @tests java.net.Socket#getSoLinger()
     */
    public void test_getSoLinger() throws IOException {
        ServerSocket server = new ServerSocket(0);
        Socket client = new Socket(InetAddress.getLocalHost(), server
                .getLocalPort());

        try {
            client.setSoLinger(true, 200);
            assertEquals("Returned incorrect linger", 200, client.getSoLinger());
            client.setSoLinger(false, 0);

            ensureExceptionThrownIfOptionIsUnsupportedOnOS(SO_LINGER);
        } catch (Exception e) {
            handleException(e, SO_LINGER);
        } finally {
            client.close();
            server.close();
        }
    }

    /**
     * @tests java.net.Socket#getSoTimeout()
     */
    public void test_getSoTimeout() throws IOException {
        ServerSocket server = new ServerSocket(0);
        Socket client = new Socket(InetAddress.getLocalHost(), server
                .getLocalPort());

        try {
            client.setSoTimeout(100);
            assertEquals("Returned incorrect sotimeout", 100, client
                    .getSoTimeout());

            ensureExceptionThrownIfOptionIsUnsupportedOnOS(SO_TIMEOUT);
        } catch (Exception e) {
            handleException(e, SO_TIMEOUT);
        } finally {
            client.close();
            server.close();
        }
    }

    /**
     * @tests java.net.Socket#getTcpNoDelay()
     */
    public void test_getTcpNoDelay() throws IOException {
        ServerSocket server = new ServerSocket(0);
        Socket client = new Socket(InetAddress.getLocalHost(), server
                .getLocalPort());

        try {
            boolean bool = !client.getTcpNoDelay();
            client.setTcpNoDelay(bool);
            assertTrue("Failed to get no delay setting: "
                    + client.getTcpNoDelay(), client.getTcpNoDelay() == bool);

            ensureExceptionThrownIfOptionIsUnsupportedOnOS(TCP_NODELAY);
        } catch (Exception e) {
            handleException(e, TCP_NODELAY);
        } finally {
            client.close();
            server.close();
        }
    }

    /**
     * @tests java.net.Socket#getTrafficClass()
     */
    public void test_getTrafficClass() {
        try {
            /*
             * We cannot actually check that the values are set as if a platform
             * does not support the option then it may come back unset even
             * though we set it so just get the value to make sure we can get it
             */
            int trafficClass = new Socket().getTrafficClass();
            assertTrue(0 <= trafficClass);
            assertTrue(trafficClass <= 255);

            ensureExceptionThrownIfOptionIsUnsupportedOnOS(IP_TOS);
        } catch (Exception e) {
            handleException(e, IP_TOS);
        }
    }

    /**
     * @tests java.net.Socket#isBound()
     */
    public void test_isBound() throws IOException {
        ServerSocket server = new ServerSocket(0);
        Socket client = new Socket(InetAddress.getLocalHost(), server
                .getLocalPort());
        Socket worker = server.accept();

        assertTrue("Socket indicated  not bound when it should be (1)", client
                .isBound());
        worker.close();
        client.close();
        server.close();

        client = new Socket();
        assertFalse("Socket indicated bound when it was not (2)", client
                .isBound());

        server = new ServerSocket();
        server.bind(new InetSocketAddress(InetAddress.getLocalHost(), 0));
        InetSocketAddress boundAddress = new InetSocketAddress(server
                .getInetAddress(), server.getLocalPort());
        client.connect(boundAddress);
        worker = server.accept();
        assertTrue("Socket indicated not bound when it should be (2)", client
                .isBound());
        worker.close();
        client.close();
        server.close();

        // now test when we bind explicitly
        InetSocketAddress theLocalAddress = new InetSocketAddress(InetAddress
                .getLocalHost(), 0);
        client = new Socket();
        assertFalse("Socket indicated bound when it was not (3)", client
                .isBound());
        client.bind(theLocalAddress);
        assertTrue("Socket indicated not bound when it should be (3a)", client
                .isBound());
        client.close();
        assertTrue("Socket indicated not bound when it should be (3b)", client
                .isBound());
    }

    /**
     * @tests java.net.Socket#isClosed()
     */
    public void test_isClosed() throws IOException {
        ServerSocket server = new ServerSocket(0);
        Socket client = new Socket(InetAddress.getLocalHost(), server
                .getLocalPort());
        Socket worker = server.accept();

        // validate isClosed returns expected values
        assertFalse("Socket should indicate it is not closed(1):", client
                .isClosed());
        client.close();
        assertTrue("Socket should indicate it is closed(1):", client.isClosed());

        // validate that isClosed works ok for sockets returned from
        // ServerSocket.accept()
        assertFalse("Accepted Socket should indicate it is not closed:", worker
                .isClosed());
        worker.close();
        assertTrue("Accepted Socket should indicate it is closed:", worker
                .isClosed());

        // and finally for the server socket
        assertFalse("Server Socket should indicate it is not closed:", server
                .isClosed());
        server.close();
        assertTrue("Server Socket should indicate it is closed:", server
                .isClosed());
    }

    /**
     * @tests java.net.Socket#isConnected()
     */
    public void test_isConnected() throws IOException {
        ServerSocket server = new ServerSocket(0);
        Socket client = new Socket(InetAddress.getLocalHost(), server
                .getLocalPort());
        Socket worker = server.accept();

        assertTrue("Socket indicated  not connected when it should be", client
                .isConnected());
        client.close();
        worker.close();
        server.close();

        // now do it with the new constructors and revalidate
        InetSocketAddress theAddress = new InetSocketAddress(InetAddress
                .getLocalHost(), 0);
        client = new Socket();
        assertFalse("Socket indicated connected when it was not", client
                .isConnected());

        server = new ServerSocket();
        server.bind(theAddress);
        InetSocketAddress boundAddress = new InetSocketAddress(server
                .getInetAddress(), server.getLocalPort());
        client.connect(boundAddress);
        worker = server.accept();
        assertTrue("Socket indicated  not connected when it should be", client
                .isConnected());
        client.close();
        worker.close();
        server.close();
    }

    /**
     * @tests java.net.Socket#isInputShutdown()
     */
    public void test_isInputShutdown() throws IOException {
        ServerSocket server = new ServerSocket(0);
        Socket client = new Socket(InetAddress.getLocalHost(), server
                .getLocalPort());

        Socket worker = server.accept();
        InputStream theInput = client.getInputStream();
        OutputStream theOutput = worker.getOutputStream();

        // make sure we get the right answer with newly connected socket
        assertFalse("Socket indicated input shutdown when it should not have",
                client.isInputShutdown());

        // shutdown the output
        client.shutdownInput();

        // make sure we get the right answer once it is shut down
        assertTrue(
                "Socket indicated input was NOT shutdown when it should have been",
                client.isInputShutdown());

        client.close();
        worker.close();
        server.close();

        // make sure we get the right answer for closed sockets
        assertFalse(
                "Socket indicated input was shutdown when socket was closed",
                worker.isInputShutdown());

        theInput.close();
        theOutput.close();
    }

    /**
     * @tests java.net.Socket#isOutputShutdown()
     */
    public void test_isOutputShutdown() throws IOException {
        ServerSocket server = new ServerSocket(0);
        Socket client = new Socket(InetAddress.getLocalHost(), server
                .getLocalPort());

        Socket worker = server.accept();
        InputStream theInput = client.getInputStream();
        OutputStream theOutput = worker.getOutputStream();

        // make sure we get the right answer with newly connected socket
        assertFalse("Socket indicated output shutdown when it should not have",
                worker.isOutputShutdown());

        // shutdown the output
        worker.shutdownOutput();

        // make sure we get the right answer once it is shut down
        assertTrue(
                "Socket indicated output was NOT shutdown when it should have been",
                worker.isOutputShutdown());

        client.close();
        worker.close();
        server.close();

        // make sure we get the right answer for closed sockets
        assertFalse(
                "Socket indicated output was output shutdown when the socket was closed",
                client.isOutputShutdown());

        theInput.close();
        theOutput.close();
    }

    /**
     * @tests java.net.Socket#sendUrgentData(int)
     */
    public void test_sendUrgentDataI() throws Exception {
        // Some platforms may not support urgent data in this case we will not
        // run these tests. For now run on all platforms until we find those
        // that do not support urgent data
        String platform = System.getProperty("os.name");
        if (platform.equals("Dummy")) {
            return;
        }
        // validate that when OOBInline is false that any urgent data
        // is silently ignored
        String urgentData = "U";
        InetSocketAddress theAddress = new InetSocketAddress(InetAddress
                .getLocalHost(), Support_PortManager.getNextPort());
        Socket theSocket = new Socket();
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(theAddress);
        theSocket.connect(theAddress);
        Socket servSock = serverSocket.accept();
        InputStream theInput = theSocket.getInputStream();
        OutputStream theOutput = servSock.getOutputStream();

        // send the regular data
        String sendString = new String("Test");
        theOutput.write(sendString.getBytes());
        theOutput.flush();

        // send the urgent data which should not be received
        theSocket.setOOBInline(false);
        servSock.sendUrgentData(urgentData.getBytes()[0]);
        theOutput.write(sendString.getBytes());
        theOutput.flush();

        // give things some time to settle
        Thread.sleep(1000);

        int totalBytesRead = 0;
        byte[] myBytes = new byte[100];
        while (theInput.available() > 0) {
            int bytesRead = theInput.read(myBytes, totalBytesRead,
                    myBytes.length - totalBytesRead);
            totalBytesRead = totalBytesRead + bytesRead;
        }

        String receivedString = new String(myBytes, 0, totalBytesRead);
        assertTrue("Urgent Data seems to have been received:" + receivedString
                + ":" + sendString, receivedString.equals(sendString
                + sendString));

        theSocket.close();
        serverSocket.close();

        // now validate that urgent data is received as expected. Expect
        // that it should be between the two writes.
        theAddress = new InetSocketAddress(InetAddress.getLocalHost(),
                Support_PortManager.getNextPort());
        theSocket = new Socket();
        serverSocket = new ServerSocket();
        serverSocket.bind(theAddress);
        theSocket.connect(theAddress);
        servSock = serverSocket.accept();
        theInput = theSocket.getInputStream();
        theOutput = servSock.getOutputStream();

        // send the regular data
        sendString = new String("Test - Urgent Data");
        theOutput.write(sendString.getBytes());
        theOutput.flush();

        // send the urgent data which should be received
        theSocket.setOOBInline(true);
        servSock.sendUrgentData(urgentData.getBytes()[0]);

        theOutput.write(sendString.getBytes());
        theOutput.flush();

        Thread.sleep(1000);

        totalBytesRead = 0;
        myBytes = new byte[100];
        while (theInput.available() > 0) {
            int bytesRead = theInput.read(myBytes, totalBytesRead,
                    myBytes.length - totalBytesRead);
            totalBytesRead = totalBytesRead + bytesRead;
        }

        receivedString = new String(myBytes, 0, totalBytesRead);
        assertTrue("Urgent Data was not received with one urgent byte:"
                + receivedString + ":" + sendString + urgentData + sendString,
                receivedString.equals(sendString + urgentData + sendString));

        theSocket.close();
        serverSocket.close();

        // now test case where we try to send two urgent bytes.
        theAddress = new InetSocketAddress(InetAddress.getLocalHost(),
                Support_PortManager.getNextPort());
        theSocket = new Socket();
        serverSocket = new ServerSocket();
        serverSocket.bind(theAddress);
        theSocket.connect(theAddress);
        servSock = serverSocket.accept();
        theInput = theSocket.getInputStream();
        theOutput = servSock.getOutputStream();

        // send the regular data
        sendString = new String("Test - Urgent Data");
        theOutput.write(sendString.getBytes());
        theOutput.flush();

        // send the urgent data which should not be received
        theSocket.setOOBInline(true);
        servSock.sendUrgentData(urgentData.getBytes()[0]);
        servSock.sendUrgentData(urgentData.getBytes()[0]);

        theOutput.write(sendString.getBytes());
        theOutput.flush();

        Thread.sleep(1000);

        totalBytesRead = 0;
        myBytes = new byte[100];
        while (theInput.available() > 0) {
            int bytesRead = theInput.read(myBytes, totalBytesRead,
                    myBytes.length - totalBytesRead);
            totalBytesRead = totalBytesRead + bytesRead;
        }

        receivedString = new String(myBytes, 0, totalBytesRead);
        assertTrue("Did not get right byte of urgent data when two sent:"
                + receivedString + ":" + sendString + urgentData + urgentData
                + sendString, receivedString.equals(sendString + urgentData
                + urgentData + sendString));

        theSocket.close();
        serverSocket.close();

        /*
         * TODO : These do not currently pass on XP SP2 and Server 2003
         */
        if (!platform.startsWith("Windows")) {
            // now test the case were we send turn the OOBInline on/off
            theAddress = new InetSocketAddress(InetAddress.getLocalHost(),
                    Support_PortManager.getNextPort());
            theSocket = new Socket();
            serverSocket = new ServerSocket();
            serverSocket.bind(theAddress);
            theSocket.connect(theAddress);
            servSock = serverSocket.accept();
            theInput = theSocket.getInputStream();
            theOutput = servSock.getOutputStream();

            // send the regular data
            sendString = new String("Test - Urgent Data");
            theOutput.write(sendString.getBytes());
            theOutput.flush();

            // send the urgent data which should be received
            theSocket.setOOBInline(true);
            servSock.sendUrgentData(urgentData.getBytes()[0]);

            theOutput.write(sendString.getBytes());
            theOutput.flush();

            Thread.sleep(1000);

            totalBytesRead = 0;
            myBytes = new byte[100];
            while (theInput.available() > 0) {
                int bytesRead = theInput.read(myBytes, totalBytesRead,
                        myBytes.length - totalBytesRead);
                totalBytesRead = totalBytesRead + bytesRead;
            }

            receivedString = new String(myBytes, 0, totalBytesRead);
            assertTrue("Did not get urgent data when turning on/off(1):"
                    + receivedString + ":" + sendString + urgentData
                    + sendString, receivedString.equals(sendString + urgentData
                    + sendString));

            // send the regular data
            sendString = new String("Test - Urgent Data");
            theOutput.write(sendString.getBytes());
            theOutput.flush();

            // send the urgent data which should not be received
            theSocket.setOOBInline(false);
            servSock.sendUrgentData(urgentData.getBytes()[0]);

            // send trailing data
            theOutput.write(sendString.getBytes());
            theOutput.flush();

            Thread.sleep(1000);

            totalBytesRead = 0;
            myBytes = new byte[100];
            while (theInput.available() > 0) {
                int bytesRead = theInput.read(myBytes, totalBytesRead,
                        myBytes.length - totalBytesRead);
                totalBytesRead = totalBytesRead + bytesRead;
            }

            receivedString = new String(myBytes, 0, totalBytesRead);
            assertTrue("Got unexpected data data when turning on/off(2):"
                    + receivedString + ":" + sendString + sendString,
                    receivedString.equals(sendString + sendString));

            // now turn back on and get data. Here we also
            // get the previously sent byte of urgent data as it is
            // still in the urgent buffer

            // send the regular data
            sendString = new String("Test - Urgent Data");
            theOutput.write(sendString.getBytes());
            theOutput.flush();

            // send the urgent data which should be received again
            theSocket.setOOBInline(true);
            servSock.sendUrgentData(urgentData.getBytes()[0]);

            theOutput.write(sendString.getBytes());
            theOutput.flush();

            Thread.sleep(1000);

            totalBytesRead = 0;
            myBytes = new byte[100];
            while (theInput.available() > 0) {
                int bytesRead = theInput.read(myBytes, totalBytesRead,
                        myBytes.length - totalBytesRead);
                totalBytesRead = totalBytesRead + bytesRead;
            }

            receivedString = new String(myBytes, 0, totalBytesRead);
            // depending on the platform we may get the previously sent
            // urgent data or not (examples windows-yes, Linux-no).
            // So accept either so long as we get the urgent data from
            // when it was on.
            assertTrue("Did not get urgent data when turning on/off(3) GOT:"
                    + receivedString + ":Expected" + urgentData + sendString
                    + urgentData + sendString + ":OR:" + sendString
                    + urgentData + sendString,
                    (receivedString.equals(urgentData + sendString + urgentData
                            + sendString) || receivedString.equals(sendString
                            + urgentData + sendString)));

            theSocket.close();
            serverSocket.close();
        }

        // now test the case where there is only urgent data
        theAddress = new InetSocketAddress(InetAddress.getLocalHost(),
                Support_PortManager.getNextPort());
        theSocket = new Socket();
        serverSocket = new ServerSocket();
        serverSocket.bind(theAddress);
        theSocket.connect(theAddress);
        servSock = serverSocket.accept();
        theInput = theSocket.getInputStream();
        theOutput = servSock.getOutputStream();

        // send the urgent data which should not be received.
        theSocket.setOOBInline(true);
        servSock.sendUrgentData(urgentData.getBytes()[0]);

        Thread.sleep(1000);

        totalBytesRead = 0;
        myBytes = new byte[100];
        while (theInput.available() > 0) {
            int bytesRead = theInput.read(myBytes, totalBytesRead,
                    myBytes.length - totalBytesRead);
            totalBytesRead = totalBytesRead + bytesRead;
        }

        receivedString = new String(myBytes, 0, totalBytesRead);
        assertTrue("Did not get urgent data only urgent data sent:"
                + receivedString + ":" + urgentData, receivedString
                .equals(urgentData));
    }

    /**
     * @tests java.net.Socket#setKeepAlive(boolean)
     */
    public void test_setKeepAliveZ() throws IOException {

        class TestSocket extends Socket {
            public TestSocket(SocketImpl impl) throws SocketException {
                super(impl);
            }
        }

        // There is not really a good test for this as it is there to detect
        // crashed machines. Just make sure we can set it
        ServerSocket server = new ServerSocket(0);
        Socket client = new Socket(InetAddress.getLocalHost(), server
                .getLocalPort());

        try {
            client.setKeepAlive(true);
            client.setKeepAlive(false);
            ensureExceptionThrownIfOptionIsUnsupportedOnOS(SO_KEEPALIVE);
        } catch (Exception e) {
            handleException(e, SO_KEEPALIVE);
        } finally {
            client.close();
            server.close();
        }

        // Regression test for HARMONY-1136
        new TestSocket((SocketImpl) null).setKeepAlive(true);
    }

    /**
     * @tests java.net.Socket#setOOBInline(boolean)
     */
    public void test_setOOBInlineZ() {
        try {
            Socket theSocket = new Socket();
            theSocket.setOOBInline(true);
            assertTrue("expected OOBIline to be true", theSocket.getOOBInline());

            ensureExceptionThrownIfOptionIsUnsupportedOnOS(SO_OOBINLINE);
        } catch (Exception e) {
            handleException(e, SO_OOBINLINE);
        }
    }

    /**
     * @tests java.net.Socket#setPerformancePreference()
     */
    public void test_setPerformancePreference_Int_Int_Int() throws IOException {
        Socket theSocket = new Socket();
        theSocket.setPerformancePreferences(1, 1, 1);
    }

    /**
     * @tests java.net.Socket#setReceiveBufferSize(int)
     */
    public void test_setReceiveBufferSizeI() throws IOException {
        ServerSocket server = new ServerSocket(0);
        Socket client = new Socket(InetAddress.getLocalHost(), server
                .getLocalPort());

        try {
            client.setReceiveBufferSize(130);
            assertTrue("Incorrect buffer size",
                    client.getReceiveBufferSize() >= 130);

            ensureExceptionThrownIfOptionIsUnsupportedOnOS(SO_RCVBUF);
        } catch (Exception e) {
            handleException(e, SO_RCVBUF);
        } finally {
            client.close();
            server.close();
        }
    }

    /**
     * @tests java.net.Socket#setReuseAddress(boolean)
     */
    public void test_setReuseAddressZ() throws UnknownHostException {

        try {
            Socket theSocket = new Socket();
            theSocket.setReuseAddress(false);
            // Bind to any available port on the given address
            theSocket
                    .bind(new InetSocketAddress(InetAddress.getLocalHost(), 0));
            InetSocketAddress localAddress1 = new InetSocketAddress(theSocket
                    .getLocalAddress(), theSocket.getLocalPort());

            Socket theSocket2 = new Socket();
            theSocket2.setReuseAddress(false);

            /*
             * Try to invoke a bind while the port is busy (TIME_WAIT). Note
             * that we may not succeed, which will cause the test to pass
             * without testing the reuseaddr behavior.
             */
            theSocket.close();
            theSocket2.bind(localAddress1);

            theSocket2.close();

            ensureExceptionThrownIfOptionIsUnsupportedOnOS(SO_REUSEADDR);
        } catch (Exception e) {
            handleException(e, SO_REUSEADDR);
        }
    }

    /**
     * @tests java.net.Socket#setSendBufferSize(int)
     */
    public void test_setSendBufferSizeI() throws IOException {
        ServerSocket server = new ServerSocket(0);
        Socket client = new Socket(InetAddress.getLocalHost(), server
                .getLocalPort());

        try {
            client.setSendBufferSize(134);
            assertTrue("Incorrect buffer size",
                    client.getSendBufferSize() >= 134);
            ensureExceptionThrownIfOptionIsUnsupportedOnOS(SO_SNDBUF);
        } catch (Exception e) {
            handleException(e, SO_SNDBUF);
        } finally {
            client.close();
            server.close();
        }
    }

    /**
     * @tests java.net.Socket#setSocketImplFactory(java.net.SocketImplFactory)
     */
    public void test_setSocketImplFactoryLjava_net_SocketImplFactory() {
        // Cannot test as setting will cause the factory to be changed for
        // all subsequent sockets
    }

    /**
     * @tests java.net.Socket#setSoLinger(boolean, int)
     */
    public void test_setSoLingerZI() throws IOException {
        ServerSocket server = new ServerSocket(0);
        Socket client = new Socket(InetAddress.getLocalHost(), server
                .getLocalPort());

        try {
            client.setSoLinger(true, 500);
            assertEquals("Set incorrect linger", 500, client.getSoLinger());
            ensureExceptionThrownIfOptionIsUnsupportedOnOS(SO_LINGER);
            client.setSoLinger(false, 0);
        } catch (Exception e) {
            handleException(e, SO_LINGER);
        } finally {
            client.close();
            server.close();
        }
    }

    /**
     * @tests java.net.Socket#setSoTimeout(int)
     */
    public void test_setSoTimeoutI() throws IOException {
        ServerSocket server = new ServerSocket(0);
        Socket client = new Socket(InetAddress.getLocalHost(), server
                .getLocalPort());

        try {
            client.setSoTimeout(100);
            assertEquals("Set incorrect sotimeout", 100, client.getSoTimeout());
            ensureExceptionThrownIfOptionIsUnsupportedOnOS(SO_TIMEOUT);
        } catch (Exception e) {
            handleException(e, SO_TIMEOUT);
        } finally {
            client.close();
            server.close();
        }
    }

    /**
     * @tests java.net.Socket#setTcpNoDelay(boolean)
     */
    public void test_setTcpNoDelayZ() throws IOException {
        ServerSocket server = new ServerSocket(0);
        Socket client = new Socket(InetAddress.getLocalHost(), server
                .getLocalPort());

        try {
            boolean bool;
            client.setTcpNoDelay(bool = !client.getTcpNoDelay());
            assertTrue("Failed to set no delay setting: "
                    + client.getTcpNoDelay(), client.getTcpNoDelay() == bool);

            ensureExceptionThrownIfOptionIsUnsupportedOnOS(TCP_NODELAY);
        } catch (Exception e) {
            handleException(e, TCP_NODELAY);
        } finally {
            client.close();
            server.close();
        }
    }

    /**
     * @tests java.net.Socket#setTrafficClass(int)
     */
    public void test_setTrafficClassI() {
        try {
            int IPTOS_LOWCOST = 0x2;
            int IPTOS_RELIABILTY = 0x4;
            int IPTOS_THROUGHPUT = 0x8;
            int IPTOS_LOWDELAY = 0x10;

            Socket theSocket = new Socket();

            // validate that value set must be between 0 and 255
            try {
                theSocket.setTrafficClass(256);
                fail("No exception was thrown when traffic class set to 256");
            } catch (IllegalArgumentException e) {
                // Expected
            }

            try {
                theSocket.setTrafficClass(-1);
                fail("No exception was thrown when traffic class set to -1");
            } catch (IllegalArgumentException e) {
                // Expected
            }

            // now validate that we can set it to some good values
            theSocket.setTrafficClass(IPTOS_LOWCOST);
            theSocket.setTrafficClass(IPTOS_RELIABILTY);
            theSocket.setTrafficClass(IPTOS_THROUGHPUT);
            theSocket.setTrafficClass(IPTOS_LOWDELAY);

            ensureExceptionThrownIfOptionIsUnsupportedOnOS(IP_TOS);
        } catch (Exception e) {
            handleException(e, IP_TOS);
        }
    }

    /**
     * @tests java.net.Socket#shutdownInput()
     */
    @SuppressWarnings("deprecation")
    public void test_shutdownInput() throws IOException {
        ServerSocket server = new ServerSocket(0);
        Socket client = new Socket(InetAddress.getLocalHost(), server
                .getLocalPort());

        Socket worker = server.accept();
        worker.setTcpNoDelay(true);

        InputStream theInput = client.getInputStream();
        OutputStream theOutput = worker.getOutputStream();

        // shutdown the input
        client.shutdownInput();

        // send the regular data
        String sendString = new String("Test");
        theOutput.write(sendString.getBytes());
        theOutput.flush();

        // RI fails here. It is a RI bug not to return 0 to indicate EOF
        assertEquals(0, theInput.available());

        client.close();
        server.close();

        // Regression test for HARMONY-2944
        Socket s = new Socket("0.0.0.0", 0, false);
        s.shutdownInput();
        try {
            s.shutdownInput();
            fail("should throw SocketException");
        } catch (SocketException se) {
            // Expected
        }
        s.close();
    }

    /**
     * @tests java.net.Socket#shutdownOutput()
     */
    @SuppressWarnings("deprecation")
    public void test_shutdownOutput() throws IOException {
        ServerSocket server = new ServerSocket(0);
        Socket client = new Socket(InetAddress.getLocalHost(), server
                .getLocalPort());

        Socket worker = server.accept();
        OutputStream theOutput = worker.getOutputStream();

        // shutdown the output
        worker.shutdownOutput();

        // send the regular data
        String sendString = new String("Test");
        try {
            theOutput.write(sendString.getBytes());
            theOutput.flush();
            fail("No exception when writing on socket with output shutdown");
        } catch (IOException e) {
            // Expected
        }

        client.close();
        server.close();

        // Regression test for HARMONY-2944
        Socket s = new Socket("0.0.0.0", 0, false);
        s.shutdownOutput();
        try {
            s.shutdownOutput();
            fail("should throw SocketException");
        } catch (SocketException se) {
            // Expected
        }
        s.close();
    }

    /**
     * @tests java.net.Socket#toString()
     */
    public void test_toString() throws IOException {
        ServerSocket server = new ServerSocket(0);
        Socket client = new Socket(InetAddress.getLocalHost(), server
                .getLocalPort());

        String expected = "Socket[addr=" + InetAddress.getLocalHost()
                + ",port=" + client.getPort() + ",localport="
                + client.getLocalPort() + "]";
        assertEquals("Returned incorrect string", expected, client.toString());
        client.close();
        server.close();
    }
}
