/* Copyright 1998, 2006 The Apache Software Foundation or its licensors, as applicable
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

package tests.api.java.net;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Date;
import java.util.Properties;

import tests.support.Support_Configuration;
import tests.support.Support_PortManager;

public class ServerSocketTest extends SocketTestCase {

	boolean interrupted;

	ServerSocket s;

	Socket sconn;

	Thread t;

	static class SSClient implements Runnable {
		Socket cs;

		int port;

		public SSClient(int prt) {
			port = prt;
		}

		public void run() {
			try {
				 // Go to sleep so the server can setup and wait for connection
				Thread.sleep(1000);
				cs = new Socket(InetAddress.getLocalHost().getHostName(), port);
				// Sleep again to allow server side processing. Thread is
				// stopped by server.
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				return;
			} catch (Throwable e) {
				System.out
						.println("Error establishing client: " + e.toString());
			} finally {
				try {
					if (cs != null)
						cs.close();
				} catch (Exception e) {
				}
			}
		}
	}

	/**
	 * @tests java.net.ServerSocket#ServerSocket()
	 */
	public void test_Constructor() {
		// Test for method java.net.ServerSocket(int)
		assertTrue("Used during tests", true);
	}

	/**
	 * @tests java.net.ServerSocket#ServerSocket(int)
	 */
	public void test_ConstructorI() {
		// Test for method java.net.ServerSocket(int)
		assertTrue("Used during tests", true);
	}

    /**
     * @tests java.net.ServerSocket#ServerSocket(int)
     */
    public void test_ConstructorI_SocksSet() throws IOException {
        //Harmony-623 regression test
        ServerSocket ss = null;
        Properties props = (Properties) System.getProperties().clone();
        try {
            System.setProperty("socksProxyHost", "127.0.0.1");
            System.setProperty("socksProxyPort", "12345");
            ss = new ServerSocket(Support_PortManager.getNextPort());
        } finally {
            System.setProperties(props);
            if (null != ss) {
                ss.close();
            }
        }
    }
    
	/**
	 * @tests java.net.ServerSocket#ServerSocket(int, int)
	 */
	public void test_ConstructorII() {
		// Test for method java.net.ServerSocket(int, int)
		try {
			int portNumber = Support_PortManager.getNextPort();
			s = new ServerSocket(portNumber, 10);
			s.setSoTimeout(2000);
			startClient(s.getLocalPort());
			sconn = s.accept();
		} catch (java.io.InterruptedIOException e) {
			return;
		} catch (Exception e) {
			fail("Exception during accept test : " + e.getMessage());
		}

		int testPort = Support_PortManager.getNextPort();
		ServerSocket s1 = null, s2 = null;
		try {
			s1 = new ServerSocket(testPort);
		} catch (IOException e) {
			fail("IOException creating 1st server socket : " + e.getMessage());
		}
		boolean exception = false;
		try {
			s2 = new ServerSocket(testPort);
		} catch (IOException e) {
			exception = true;
		}
		try {
			s1.close();
			if (!exception)
				s2.close();
		} catch (IOException e) {
		}
		assertTrue("Was able to create two serversockets on same port",
				exception);

		int testPort2 = Support_PortManager.getNextPort();
		try {
			s1 = new ServerSocket(testPort2);
			s1.close();
			s2 = new ServerSocket(testPort2);
			s2.close();
		} catch (IOException e) {
			fail("IOException testing server socket reopen : " + e.getMessage());
		}
	}

	/**
	 * @tests java.net.ServerSocket#ServerSocket(int, int, java.net.InetAddress)
	 */
	public void test_ConstructorIILjava_net_InetAddress() {
		// Test for method java.net.ServerSocket(int, int, java.net.InetAddress)
		try {
			int portNumber = Support_PortManager.getNextPort();
			s = new ServerSocket(portNumber, 10, InetAddress.getLocalHost());
			s.setSoTimeout(5000);
			startClient(s.getLocalPort());
			sconn = s.accept();
		} catch (Exception e) {
			fail("Exception during accept test : " + e.getMessage());
		}
		assertTrue("Was unable to accept connection", !(sconn == null));
	}

	/**
	 * @tests java.net.ServerSocket#accept()
	 */
	public void test_accept() {
		// Test for method java.net.Socket java.net.ServerSocket.accept()
		try {
			int portNumber = Support_PortManager.getNextPort();
			s = new ServerSocket(portNumber);
			s.setSoTimeout(5000);
			startClient(s.getLocalPort());
			sconn = s.accept();
			assertTrue("Bad local port value", sconn.getLocalPort() == s
					.getLocalPort());

		} catch (Exception e) {
			fail("Exception during accept test : " + e.getMessage());
		}
		assertTrue("Was unable to accept connection", !(sconn == null));

		try {
			interrupted = false;
			int portNum = Support_PortManager.getNextPort();
			final ServerSocket ss = new ServerSocket(portNum);
			ss.setSoTimeout(12000);
			Runnable runnable = new Runnable() {
				public void run() {
					try {
						ss.accept();
					} catch (InterruptedIOException e) {
						interrupted = true;
					} catch (IOException e) {
					}
				}
			};
			Thread thread = new Thread(runnable, "ServerSocket.accept");
			thread.start();
			try {
				do {
					Thread.sleep(500);
				} while (!thread.isAlive());
			} catch (InterruptedException e) {
			}
			ss.close();
			int c = 0;
			do {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
				}
				if (interrupted) {
					fail("accept interrupted");
				}
				if (++c > 4) {
					fail("accept call did not exit");
				}
			} while (thread.isAlive());

			interrupted = false;
			portNum = Support_PortManager.getNextPort();
			ServerSocket ss2 = new ServerSocket(portNum);
			ss2.setSoTimeout(500);
			Date start = new Date();
			try {
				ss2.accept();
			} catch (InterruptedIOException e) {
				interrupted = true;
			}
			assertTrue("accept not interrupted", interrupted);
			Date finish = new Date();
			int delay = (int) (finish.getTime() - start.getTime());
			assertTrue("timeout too soon: " + delay + " " + start.getTime()
					+ " " + finish.getTime(), delay >= 490);
			ss2.close();
		} catch (IOException e) {
			fail("Unexpected IOException : " + e.getMessage());
		}
	}

	/**
	 * @tests java.net.ServerSocket#close()
	 */
	public void test_close() {
		// Test for method void java.net.ServerSocket.close()
		try {
			int portNumber = Support_PortManager.getNextPort();
			s = new ServerSocket(portNumber);
			s.close();
			s.accept();
			fail("Close test failed");
			// We throw SocketException on an accept on a closed socket.
			// Correct ?
		} catch (SocketException e) {
			return;
		} catch (Exception e) {
			fail("Exception during close test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.net.ServerSocket#getInetAddress()
	 */
	public void test_getInetAddress() {
		// Test for method java.net.InetAddress
		// java.net.ServerSocket.getInetAddress()
		try {
			int portNumber = Support_PortManager.getNextPort();
			s = new ServerSocket(portNumber, 10, InetAddress.getLocalHost());
			assertTrue("Returned incorrect InetAdrees", s.getInetAddress()
					.equals(InetAddress.getLocalHost()));
		} catch (Exception e) {
			fail("Exception during getInetAddress test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.net.ServerSocket#getLocalPort()
	 */
	public void test_getLocalPort() {
		// Test for method int java.net.ServerSocket.getLocalPort()
		try {
			int portNumber = Support_PortManager.getNextPort();
			s = new ServerSocket(portNumber);
			assertTrue("Returned incorrect port",
					s.getLocalPort() == portNumber);
		} catch (Exception e) {
			fail("Exception during getLocalPort test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.net.ServerSocket#getSoTimeout()
	 */
	public void test_getSoTimeout() {
		// Test for method int java.net.ServerSocket.getSoTimeout()
		try {
			int portNumber = Support_PortManager.getNextPort();
			s = new ServerSocket(portNumber);
			s.setSoTimeout(100);
			assertEquals("Returned incorrect sotimeout", 100, s.getSoTimeout());
		} catch (Exception e) {
			fail("Exception during getSoTimeout test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.net.ServerSocket#setSocketFactory(java.net.SocketImplFactory)
	 */
	public void test_setSocketFactoryLjava_net_SocketImplFactory() {
		// Test for method void
		// java.net.ServerSocket.setSocketFactory(java.net.SocketImplFactory)
		
		// TODO : Implementation
	}

	/**
	 * @tests java.net.ServerSocket#setSoTimeout(int)
	 */
	public void test_setSoTimeoutI() {
		// Test for method void java.net.ServerSocket.setSoTimeout(int)
		// Timeout should trigger and throw InterruptedIOException
		try {
			int portNumber = Support_PortManager.getNextPort();
			s = new ServerSocket(portNumber);
			s.setSoTimeout(100);
			s.accept();
		} catch (java.io.InterruptedIOException e) {
			try {
				assertEquals("Set incorrect sotimeout", 100, s.getSoTimeout());
				return;
			} catch (Exception x) {
				fail("Exception during setSOTimeout: " + e.toString());
			}
		} catch (IOException iox) {
			fail("IOException during setSotimeout: " + iox.toString());
		}

		// Timeout should not trigger in this case
		try {
			int portNumber = Support_PortManager.getNextPort();
			s = new ServerSocket(portNumber);
			startClient(s.getLocalPort());
			s.setSoTimeout(10000);
			sconn = s.accept();
		} catch (java.io.InterruptedIOException ex) {
			fail("Set incorrect sotimeout : " + ex.getMessage());
			return;
		} catch (IOException iox) {
			fail("IOException during setSotimeout : " + iox.getMessage());
		} catch (Exception x) {
			fail("Exception during setSoTimeout test : " + x.getMessage());
		}

	}

	/**
	 * @tests java.net.ServerSocket#toString()
	 */
	public void test_toString() throws Exception {
        // Test for method java.lang.String java.net.ServerSocket.toString()
        int portNumber = Support_PortManager.getNextPort();
        s = new ServerSocket(portNumber);
        assertEquals("ServerSocket[addr=0.0.0.0/0.0.0.0,port=0,localport="
                + portNumber + "]", s.toString());
    }

	/**
	 * @tests java.net.ServerSocket#bind(java.net.SocketAddress)
	 */
	public void test_bindLjava_net_SocketAddress() {
		class mySocketAddress extends SocketAddress {

			public mySocketAddress() {
			}
		}
		try {
			// create servers socket, bind it and then validate basic state
			ServerSocket theSocket = new ServerSocket();
			int portNumber = Support_PortManager.getNextPort();
			InetSocketAddress theAddress = new InetSocketAddress(InetAddress
					.getLocalHost(), portNumber);
			theSocket.bind(theAddress);
			assertTrue("Returned incorrect InetSocketAddress(2):"
					+ theSocket.getLocalSocketAddress().toString()
					+ "Expected: "
					+ (new InetSocketAddress(InetAddress.getLocalHost(),
							portNumber)).toString(), theSocket
					.getLocalSocketAddress().equals(
							new InetSocketAddress(InetAddress.getLocalHost(),
									portNumber)));
			assertTrue("Server socket not bound when it should be:", theSocket
					.isBound());

			// now make sure that it is actually bound and listening on the
			// address we provided
			Socket clientSocket = new Socket();
			clientSocket.connect(theAddress);
			Socket servSock = theSocket.accept();

			assertTrue(clientSocket.getRemoteSocketAddress().equals(theAddress));
			theSocket.close();
			servSock.close();
			clientSocket.close();

			// validate we can specify null for the address in the bind and all
			// goes ok
			theSocket = new ServerSocket();
			theSocket.bind(null);
			theSocket.close();

			// Address that we have allready bound to
			theSocket = new ServerSocket();
			ServerSocket theSocket2 = new ServerSocket();
			try {
				theAddress = new InetSocketAddress(InetAddress.getLocalHost(),
						Support_PortManager.getNextPort());
				theSocket.bind(theAddress);
				theSocket2.bind(theAddress);
				assertFalse(
						"No exception binding to address that is not available",
						true);
			} catch (IOException ex) {
			}
			theSocket.close();
			theSocket2.close();

			// validate we get io address when we try to bind to address we
			// cannot bind to
			theSocket = new ServerSocket();
			try {
				theSocket
						.bind(new InetSocketAddress(
								InetAddress
										.getByAddress(Support_Configuration.nonLocalAddressBytes),
								0));
				fail("No exception was thrown when binding to bad address");
			} catch (IOException ex) {
			}
			theSocket.close();

			// now validate case where we pass in an unsupported subclass of
			// SocketAddress
			theSocket = new ServerSocket();
			try {
				theSocket.bind(new mySocketAddress());
				assertFalse(
						"No exception when binding using unsupported SocketAddress subclass",
						true);
			} catch (IllegalArgumentException ex) {
			}
			theSocket.close();
		} catch (Exception e) {
			fail("Exception during getLocalSocketAddress test: " + e);
		}
	}

	/**
	 * @tests java.net.ServerSocket#bind(java.net.SocketAddress,int)
	 */
	public void test_bindLjava_net_SocketAddressI() {
		class mySocketAddress extends SocketAddress {

			public mySocketAddress() {
			}
		}

		try {
			// create servers socket, bind it and then validate basic state
			ServerSocket theSocket = new ServerSocket();
			int portNumber = Support_PortManager.getNextPort();
			InetSocketAddress theAddress = new InetSocketAddress(InetAddress
					.getLocalHost(), portNumber);
			theSocket.bind(theAddress, 5);
			assertTrue("Returned incorrect InetSocketAddress(2):"
					+ theSocket.getLocalSocketAddress().toString()
					+ "Expected: "
					+ (new InetSocketAddress(InetAddress.getLocalHost(),
							portNumber)).toString(), theSocket
					.getLocalSocketAddress().equals(
							new InetSocketAddress(InetAddress.getLocalHost(),
									portNumber)));
			assertTrue("Server socket not bound when it should be:", theSocket
					.isBound());

			// now make sure that it is actually bound and listening on the
			// address we provided
			Socket clientSocket = new Socket();
			clientSocket.connect(theAddress);
			Socket servSock = theSocket.accept();

			assertTrue(clientSocket.getRemoteSocketAddress().equals(theAddress));
			theSocket.close();
			servSock.close();
			clientSocket.close();

			// validate we can specify null for the address in the bind and all
			// goes ok
			theSocket = new ServerSocket();
			theSocket.bind(null, 5);
			theSocket.close();

			// Address that we have allready bound to
			theSocket = new ServerSocket();
			ServerSocket theSocket2 = new ServerSocket();
			try {
				theAddress = new InetSocketAddress(InetAddress.getLocalHost(),
						Support_PortManager.getNextPort());
				theSocket.bind(theAddress, 5);
				theSocket2.bind(theAddress, 5);
				assertFalse(
						"No exception binding to address that is not available",
						true);
			} catch (IOException ex) {
			}
			theSocket.close();
			theSocket2.close();

			// validate we get ioException when we try to bind to address we
			// cannot bind to
			theSocket = new ServerSocket();
			try {
				theSocket
						.bind(
								new InetSocketAddress(
										InetAddress
												.getByAddress(Support_Configuration.nonLocalAddressBytes),
										0), 5);
				fail("No exception was thrown when binding to bad address");
			} catch (IOException ex) {
			}
			theSocket.close();

			// now validate case where we pass in an unsupported subclass of
			// SocketAddress
			theSocket = new ServerSocket();
			try {
				theSocket.bind(new mySocketAddress(), 5);
				fail("Binding using unsupported SocketAddress subclass should have thrown exception");
			} catch (IllegalArgumentException ex) {
			}
			theSocket.close();

			// now validate that backlog is respected. We have to do a test that
			// checks if it is a least a certain number as some platforms make
			// it higher than we request. Unfortunately non-server versions of
			// windows artificially limit the backlog to 5 and 5 is the
			// historical default so it it not a great test.
			theSocket = new ServerSocket();
			portNumber = Support_PortManager.getNextPort();
			theAddress = new InetSocketAddress(InetAddress.getLocalHost(),
					portNumber);
			theSocket.bind(theAddress, 4);
			Socket theSockets[] = new Socket[4];
			int i = 0;
			try {
				for (i = 0; i < 4; i++) {
					theSockets[i] = new Socket();
					theSockets[i].connect(theAddress);
				}
				;
			} catch (ConnectException ex) {
				fail("Backlog does not seem to be respected in bind:" + i
						+ ":" + ex.toString());
			}

			for (i = 0; i < 4; i++) {
				theSockets[i].close();
			}
			;

			theSocket.close();
			servSock.close();
		} catch (Exception e) {
			fail("Exception during getLocalSocketAddress test: " + e);
		}
	}

	/**
	 * @tests java.net.ServerSocket#getLocalSocketAddress()
	 */
	public void test_getLocalSocketAddress() {
		// set up server connect and then validate that we get the right
		// response for the local address
		try {
			int portNumber = Support_PortManager.getNextPort();
			ServerSocket theSocket = new ServerSocket(portNumber, 5,
					InetAddress.getLocalHost());
			assertTrue("Returned incorrect InetSocketAddress(1):"
					+ theSocket.getLocalSocketAddress().toString()
					+ "Expected: "
					+ (new InetSocketAddress(InetAddress.getLocalHost(),
							portNumber)).toString(), theSocket
					.getLocalSocketAddress().equals(
							new InetSocketAddress(InetAddress.getLocalHost(),
									portNumber)));
			theSocket.close();

			// now create a socket that is not bound and validate we get the
			// right answer
			theSocket = new ServerSocket();
			assertNull(
					"Returned incorrect InetSocketAddress -unbound socket- Expected null",
					theSocket.getLocalSocketAddress());

			// now bind the socket and make sure we get the right answer
			portNumber = Support_PortManager.getNextPort();
			theSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(),
					portNumber));
			assertTrue("Returned incorrect InetSocketAddress(2):"
					+ theSocket.getLocalSocketAddress().toString()
					+ "Expected: "
					+ (new InetSocketAddress(InetAddress.getLocalHost(),
							portNumber)).toString(), theSocket
					.getLocalSocketAddress().equals(
							new InetSocketAddress(InetAddress.getLocalHost(),
									portNumber)));
			theSocket.close();
		} catch (Exception e) {
			fail("Exception during getLocalSocketAddress test: " + e);
		}
	}

	/**
	 * @tests java.net.ServerSocket#isBound()
	 */
	public void test_isBound() {
		try {
			InetAddress addr = InetAddress.getLocalHost();
			int port = Support_PortManager.getNextPort();
			ServerSocket serverSocket = new ServerSocket();
			assertFalse("Socket indicated bound when it should be (1)",
					serverSocket.isBound());

			// now bind and validate bound ok
			serverSocket.bind(new InetSocketAddress(addr, port));
			assertTrue("Socket indicated  not bound when it should be (1)",
					serverSocket.isBound());
			serverSocket.close();

			// now do with some of the other constructors
			serverSocket = new ServerSocket(port);
			assertTrue("Socket indicated  not bound when it should be (2)",
					serverSocket.isBound());
			serverSocket.close();

			serverSocket = new ServerSocket(Support_PortManager.getNextPort(),
					5, addr);
			assertTrue("Socket indicated  not bound when it should be (3)",
					serverSocket.isBound());
			serverSocket.close();

			serverSocket = new ServerSocket(Support_PortManager.getNextPort(),
					5);
			assertTrue("Socket indicated  not bound when it should be (4)",
					serverSocket.isBound());
			serverSocket.close();
		} catch (Exception e) {
			fail("Got exception during isBound tests" + e.toString());
		}
	}

	/**
	 * @tests java.net.ServerSocket#isClosed()
	 */
	public void test_isClosed() {
		try {
			InetAddress addr = InetAddress.getLocalHost();
			int port = Support_PortManager.getNextPort();
			ServerSocket serverSocket = new ServerSocket(port, 5, addr);

			// validate isClosed returns expected values
			assertFalse("Socket should indicate it is not closed(1):",
					serverSocket.isClosed());
			serverSocket.close();
			assertTrue("Socket should indicate it is closed(1):", serverSocket
					.isClosed());

			// now do with some of the other constructors
			serverSocket = new ServerSocket(port);
			assertFalse("Socket should indicate it is not closed(1):",
					serverSocket.isClosed());
			serverSocket.close();
			assertTrue("Socket should indicate it is closed(1):", serverSocket
					.isClosed());

			serverSocket = new ServerSocket(Support_PortManager.getNextPort(),
					5, addr);
			assertFalse("Socket should indicate it is not closed(1):",
					serverSocket.isClosed());
			serverSocket.close();
			assertTrue("Socket should indicate it is closed(1):", serverSocket
					.isClosed());

			serverSocket = new ServerSocket(Support_PortManager.getNextPort(),
					5);
			assertFalse("Socket should indicate it is not closed(1):",
					serverSocket.isClosed());
			serverSocket.close();
			assertTrue("Socket should indicate it is closed(1):", serverSocket
					.isClosed());
		} catch (Exception e) {
			fail("Got exception during isClosed tests" + e.toString());
		}
	}

	/**
	 * @tests java.net.ServerSocket#setReuseAddress(boolean)
	 */
	public void test_setReuseAddressZ() {
		try {
			// set up server and connect
			InetSocketAddress theAddress = new InetSocketAddress(InetAddress
					.getLocalHost(), Support_PortManager.getNextPort());
			ServerSocket serverSocket = new ServerSocket();
			serverSocket.setReuseAddress(false);
			serverSocket.bind(theAddress);

			// make a connection to the server, then close the server
			Socket theSocket = new Socket();
			theSocket.connect(theAddress);
			Socket stillActiveSocket = serverSocket.accept();
			serverSocket.close();

			// now try to rebind the server which should fail with
			// setReuseAddress to false. On windows platforms the bind is
			// allowed even then reUseAddress is false so our test uses
			// the platform to determine what the expected result is.
			String platform = System.getProperty("os.name");
			try {
				serverSocket = new ServerSocket();
				serverSocket.setReuseAddress(false);
				serverSocket.bind(theAddress);
				if ((!platform.startsWith("Windows"))) {
					fail(
							"No exception when setReuseAddress is false and we bind:"
									+ theAddress.toString());
				}
			} catch (IOException ex) {
				if (platform.startsWith("Windows")) {
					fail(
							"Got unexpected exception when binding with setReuseAddress false on windows platform:"
									+ theAddress.toString()
									+ ":"
									+ ex.toString());
				}
			}
			stillActiveSocket.close();
			theSocket.close();

			// now test case were we set it to true
			theAddress = new InetSocketAddress(InetAddress.getLocalHost(),
					Support_PortManager.getNextPort());
			serverSocket = new ServerSocket();
			serverSocket.setReuseAddress(true);
			serverSocket.bind(theAddress);

			// make a connection to the server, then close the server
			theSocket = new Socket();
			theSocket.connect(theAddress);
			stillActiveSocket = serverSocket.accept();
			serverSocket.close();

			// now try to rebind the server which should pass with
			// setReuseAddress to true
			try {
				serverSocket = new ServerSocket();
				serverSocket.setReuseAddress(true);
				serverSocket.bind(theAddress);
			} catch (IOException ex) {
				fail(
						"Unexpected exception when setReuseAddress is true and we bind:"
								+ theAddress.toString() + ":" + ex.toString());
			}
			stillActiveSocket.close();
			theSocket.close();
			ensureExceptionThrownIfOptionIsUnsupportedOnOS(SO_REUSEADDR);

			// now test default case were we expect this to work regardless of
			// the value set
			theAddress = new InetSocketAddress(InetAddress.getLocalHost(),
					Support_PortManager.getNextPort());
			serverSocket = new ServerSocket();
			serverSocket.bind(theAddress);

			// make a connection to the server, then close the server
			theSocket = new Socket();
			theSocket.connect(theAddress);
			stillActiveSocket = serverSocket.accept();
			serverSocket.close();

			// now try to rebind the server which should pass
			try {
				serverSocket = new ServerSocket();
				serverSocket.bind(theAddress);
			} catch (IOException ex) {
				fail(
						"Unexpected exception when setReuseAddress is the default case and we bind:"
						+ theAddress.toString() + ":"
						+ ex.toString());
			}
			stillActiveSocket.close();
			theSocket.close();

			ensureExceptionThrownIfOptionIsUnsupportedOnOS(SO_REUSEADDR);
		} catch (Exception e) {
			handleException(e, SO_REUSEADDR);
		}
	}

	/**
	 * @tests java.net.ServerSocket#getReuseAddress()
	 */
	public void test_getReuseAddress() {
		try {
			ServerSocket theSocket = new ServerSocket();
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
	 * @tests java.net.ServerSocket#setReceiveBufferSize(int)
	 */
	public void test_setReceiveBufferSizeI() {
		try {
			// now validate case where we try to set to 0
			ServerSocket theSocket = new ServerSocket();
			try {
				theSocket.setReceiveBufferSize(0);
				fail("No exception when receive buffer size set to 0");
			} catch (IllegalArgumentException ex) {
			}
			theSocket.close();

			// now validate case where we try to set to a negative value
			theSocket = new ServerSocket();
			try {
				theSocket.setReceiveBufferSize(-1000);
				fail(
						"No exception when receive buffer size set to -1000");
			} catch (IllegalArgumentException ex) {
			}
			theSocket.close();

			// now just try to set a good value to make sure it is set and there
			// are not exceptions
			theSocket = new ServerSocket();
			theSocket.setReceiveBufferSize(1000);
			theSocket.close();
			ensureExceptionThrownIfOptionIsUnsupportedOnOS(SO_RCVBUF);
		} catch (Exception e) {
			handleException(e, SO_RCVBUF);
		}

	}

	/*
	 * @tests java.net.ServerSocket#getReceiveBufferSize()
	 */
	public void test_getReceiveBufferSize() {
		try {
			ServerSocket theSocket = new ServerSocket();

			// since the value returned is not necessary what we set we are
			// limited in what we can test
			// just validate that it is not 0 or negative
			assertFalse("get Buffer size returns 0:", 0 == theSocket
					.getReceiveBufferSize());
			assertFalse("get Buffer size returns  a negative value:",
					0 > theSocket.getReceiveBufferSize());
			ensureExceptionThrownIfOptionIsUnsupportedOnOS(SO_RCVBUF);
		} catch (Exception e) {
			handleException(e, SO_RCVBUF);
		}
	}
	
	/**
	 * @tests java.net.ServerSocket#getChannel()
	 */
	public void test_getChannel() throws Exception {
		assertNull(new ServerSocket().getChannel());
	}
	
	/*
	* @tests java.net.ServerSocket#setPerformancePreference()
	*/
	public void test_setPerformancePreference_Int_Int_Int() throws Exception {
		ServerSocket theSocket = new ServerSocket();
		theSocket.setPerformancePreferences(1,1,1);
	}

	/**
	 * Sets up the fixture, for example, open a network connection. This method
	 * is called before a test is executed.
	 */
	protected void setUp() {
	}

	/**
	 * Tears down the fixture, for example, close a network connection. This
	 * method is called after a test is executed.
	 */
	protected void tearDown() {

		try {
			if (s != null)
				s.close();
			if (sconn != null)
				sconn.close();
			if (t != null)
				t.interrupt();
		} catch (Exception e) {
		}
	}

	/**
	 * Sets up the fixture, for example, open a network connection. This method
	 * is called before a test is executed.
	 */
	protected void startClient(int port) {
		t = new Thread(new SSClient(port), "SSClient");
		t.start();
		try {
			Thread.sleep(1000);
		} catch (java.lang.InterruptedException e) {
			System.out.println("Exception during startClinet()" + e.toString());
		}
	}
}
