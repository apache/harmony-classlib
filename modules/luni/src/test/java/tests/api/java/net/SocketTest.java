/* Copyright 1998, 2005 The Apache Software Foundation or its licensors, as applicable
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
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import tests.support.Support_Configuration;
import tests.support.Support_PortManager;

public class SocketTest extends SocketTestCase {

	ServerSocket ss;

	Socket s;

	Thread t;

	boolean interrupted = false;

	class SServer extends Thread implements Runnable {
		Socket s1 = null;

		public void run() {
			try {
				ss.setSoTimeout(5000);
				s1 = ss.accept();
				ss.close();
				Thread.sleep(4000);
			} catch (java.io.InterruptedIOException x) {
				System.out.println(Thread.currentThread()
						+ ", accept() timeout fired: " + x);
			} catch (InterruptedException x) {
			} catch (Exception e) {
				System.out.println("Unable to accept: " + e.toString());
			} finally {
				try {
					if (s1 != null)
						s1.close();
				} catch (java.io.IOException e) {
				}
			}
		}
	}

	/**
	 * @tests java.net.Socket#Socket()
	 */
	public void test_Constructor() {
		try {
			// create the socket and then validate some basic state
			s = new Socket();
			assertFalse("new socket should not be connected", s.isConnected());
			assertFalse("new socket should not be bound", s.isBound());
			assertFalse("new socket should not be closed", s.isClosed());
			assertFalse("new socket should not be in InputShutdown", s
					.isInputShutdown());
			assertFalse("new socket should not be in OutputShutdown", s
					.isOutputShutdown());
		} catch (Exception e) {
			fail("Exception during Constructor test" + e.toString());
		}
	}

	/**
	 * @tests java.net.Socket#Socket(java.lang.String, int)
	 */
	public void test_ConstructorLjava_lang_StringI() {
		// Test for method java.net.Socket(java.lang.String, int)
		try {
			int sport = startServer("Cons String,I");
			s = new Socket(InetAddress.getLocalHost().getHostName(), sport);
			assertTrue("Failed to create socket", s.getPort() == sport);
		} catch (Exception e) {
			fail("Exception during Constructor test" + e.toString());
		}
	}

	/**
	 * @tests java.net.Socket#Socket(java.lang.String, int,
	 *        java.net.InetAddress, int)
	 */
	public void test_ConstructorLjava_lang_StringILjava_net_InetAddressI() {
		// Test for method java.net.Socket(java.lang.String, int,
		// java.net.InetAddress, int)
		try {
			int sport = startServer("Cons String,I,InetAddress,I");
			int portNumber = Support_PortManager.getNextPort();
			s = new Socket(InetAddress.getLocalHost().getHostName(), sport,
					InetAddress.getLocalHost(), portNumber);
			assertTrue("Failed to create socket", s.getPort() == sport);
		} catch (Exception e) {
			fail("Exception during Constructor test" + e.toString());
		}

		if (("true".equals(System.getProperty("java.net.preferIPv6Addresses")))
				&& !("true".equals(System
						.getProperty("java.net.preferIPv4Stack")))) {

			// ALTERNATE IPv6 TEST
			if ("true".equals(System.getProperty("run.ipv6tests"))) {
				System.out
						.println("Running testConstructorLjava_lang_StringILjava_net_InetAddressI(SocketTest) with IPv6GlobalAddressJcl4: "
								+ Support_Configuration.IPv6GlobalAddressJcl4);
				int testPort = Support_PortManager.getNextPort();
				Socket s1 = null, s2 = null;
				try {
					s1 = new Socket(
							Support_Configuration.IPv6GlobalAddressJcl4, 80,
							InetAddress.getLocalHost(), testPort);
				} catch (IOException e) {
					// check here if InetAddress.getLocalHost() is returning the
					// loopback address.
					// if so that is likely the cause of the failure
					String warning = "";
					try {
						InetAddress returnedLocalHost = InetAddress
								.getLocalHost();
						// don't use isLoopbackAddress for some configurations
						// as they do not have it
						if (returnedLocalHost.isLoopbackAddress()) {
							warning = " - WARNING RETURNED LOCAL HOST IS THE LOOPBACK ADDRESS - MACHINE IS LIKELY NOT CONFIGURED CORRECTLY - THIS LIKELY CAUSED THE FAILURE";

						}
					} catch (Exception ex) {
						warning = " - WARNING COULD NOT GET LOCAL HOST - " + ex;
					}

					fail("Exception creating 1st socket" + warning + ": "
							+ e);
				}
				boolean exception = false;
				try {
					s2 = new Socket(
							Support_Configuration.IPv6GlobalAddressJcl4, 80,
							InetAddress.getLocalHost(), testPort);
				} catch (IOException e) {
					exception = true;
				}
				try {
					s1.close();
					if (!exception)
						s2.close();
				} catch (IOException e) {
				}
				assertTrue("Was able to create two sockets on same port",
						exception);
			}

		} else {
			int testPort = Support_PortManager.getNextPort();
			Socket s1 = null, s2 = null;
			try {
				s1 = new Socket(Support_Configuration.ProxyServerTestHost, 80,
						InetAddress.getLocalHost(), testPort);
			} catch (IOException e) {
				e.printStackTrace();

				// check here if InetAddress.getLocalHost() is returning the
				// loopback address. 
				// if so that is likely the cause of the failure
				String warning = "";
				try {
					InetAddress returnedLocalHost = InetAddress.getLocalHost();
					// don't use isLoopbackAddress for some configurations as
					// they do not have it
					if (returnedLocalHost.isLoopbackAddress()) {
						warning = " - WARNING RETURNED LOCAL HOST IS THE LOOPBACK ADDRESS - MACHINE IS LIKELY NOT CONFIGURED CORRECTLY - THIS LIKELY CAUSED THE FAILURE";

					}
				} catch (Exception ex) {
					warning = " - WARNING COULD NOT GET LOCAL HOST - " + ex;
				}

				fail(
						"Exception creating 1st socket" + warning + ": " + e);
			}
			boolean exception = false;
			try {
				s2 = new Socket(Support_Configuration.ProxyServerTestHost, 80,
						InetAddress.getLocalHost(), testPort);
			} catch (IOException e) {
				exception = true;
			}
			try {
				s1.close();
				if (!exception)
					s2.close();
			} catch (IOException e) {
			}
			assertTrue("Was able to create two sockets on same port", exception);
		}
	}

	/**
	 * @tests java.net.Socket#Socket(java.lang.String, int, boolean)
	 */
	public void test_ConstructorLjava_lang_StringIZ() {
		// Test for method java.net.Socket(java.lang.String, int, boolean)
		int sport = startServer("Cons String,I,Z");
		try {
			s = new Socket(InetAddress.getLocalHost().getHostName(), sport,
					true);
			assertTrue("Failed to create socket", s.getPort() == sport);
		} catch (Exception e) {
			fail("Exception during Constructor test" + e.toString());
		}

		try {
			s = new Socket(InetAddress.getLocalHost().getHostName(), sport,
					false);
		} catch (Exception e) {
			fail("Exception during Constructor test" + e.toString());
		}
	}

	/**
	 * @tests java.net.Socket#Socket(java.net.InetAddress, int)
	 */
	public void test_ConstructorLjava_net_InetAddressI() {
		// Test for method java.net.Socket(java.net.InetAddress, int)
		try {
			int sport = startServer("Cons InetAddress,I");
			s = new Socket(InetAddress.getLocalHost(), sport);
			assertTrue("Failed to create socket", s.getPort() == sport);
		} catch (Exception e) {
			fail("Exception during Constructor test" + e.toString());
		}
	}

	/**
	 * @tests java.net.Socket#Socket(java.net.InetAddress, int,
	 *        java.net.InetAddress, int)
	 */
	public void test_ConstructorLjava_net_InetAddressILjava_net_InetAddressI() {
		// Test for method java.net.Socket(java.net.InetAddress, int,
		// java.net.InetAddress, int)
		try {
			int sport = startServer("Cons InetAddress,I,InetAddress,I");
			int portNumber = Support_PortManager.getNextPort();
			s = new Socket(InetAddress.getLocalHost().getHostName(), sport,
					InetAddress.getLocalHost(), portNumber);
			assertTrue("Failed to create socket",
					s.getLocalPort() == portNumber);
		} catch (Exception e) {
			fail("Exception during Constructor test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.net.Socket#Socket(java.net.InetAddress, int, boolean)
	 */
	public void test_ConstructorLjava_net_InetAddressIZ() {
		// Test for method java.net.Socket(java.net.InetAddress, int, boolean)
		int sport = startServer("Cons InetAddress,I,Z");
		try {
			s = new Socket(InetAddress.getLocalHost(), sport, true);
			assertTrue("Failed to create socket", s.getPort() == sport);
		} catch (Exception e) {
			fail("Exception during Constructor test" + e.toString());
		}

		try {
			s = new Socket(InetAddress.getLocalHost(), sport, false);
		} catch (Exception e) {
			fail("Exception during Constructor test" + e.toString());
		}
	}

	/**
	 * @tests java.net.Socket#close()
	 */
	public void test_close() {
		// Test for method void java.net.Socket.close()
		try {
			int sport = startServer("SServer close");
			int portNumber = Support_PortManager.getNextPort();
			s = new Socket(InetAddress.getLocalHost(), sport, null, portNumber);
			try {
				s.setSoLinger(false, 100);
			} catch (IOException e) {
				handleException(e, SO_LINGER);
			}
			try {
				s.close();
				s.getOutputStream();
			} catch (java.io.IOException e) { // Caught Exception after close.
				return;
			} 
			catch (NullPointerException e) { // Caught Exception after close.
				return;
			}
			fail("Failed to close socket");
		} catch (IOException e) {
			fail("unexpected: " + e);
		}
	}

	/**
	 * @tests java.net.Socket#getInetAddress()
	 */
	public void test_getInetAddress() {
		// Test for method java.net.InetAddress java.net.Socket.getInetAddress()
		try {
			int sport = startServer("SServer getInetAddress");
			int portNumber = Support_PortManager.getNextPort();
			s = new Socket(InetAddress.getLocalHost(), sport, null, portNumber);
			assertTrue("Returned incorrect InetAdrees", s.getInetAddress()
					.equals(InetAddress.getLocalHost()));
		} catch (Exception e) {
			fail("Exception during getInetAddress test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.net.Socket#getInputStream()
	 */
	public void test_getInputStream() {
		// Test for method java.io.InputStream java.net.Socket.getInputStream()
		try {
			int sport = startServer("SServer getInputStream");
			int portNumber = Support_PortManager.getNextPort();
			s = new Socket(InetAddress.getLocalHost(), sport, null, portNumber);
			(t = new SServer()).start();
			java.io.InputStream is = s.getInputStream();
			assertTrue("Failed to get stream", is != null);
			s.setSoTimeout(6000);
			is.read();
			s.close();
			assertEquals("Invalid after close", -1, is.read());
		} catch (Exception e) {
			fail("Exception during getInputStream test " + e.toString());
		}

		try {
			interrupted = false;
			int portNum = Support_PortManager.getNextPort();
			final ServerSocket ss = new ServerSocket(portNum);
			Socket sock = new Socket(InetAddress.getLocalHost(), portNum);
			Runnable runnable = new Runnable() {
				public void run() {
					try {
						Socket as = ss.accept();
						ss.close();
						as.setSoTimeout(12000);
						InputStream in = as.getInputStream();
						in.read();
						in.close();
					} catch (InterruptedIOException e) {
						interrupted = true;
					} catch (IOException e) {
					}
				}
			};
			Thread thread = new Thread(runnable, "Socket.getInputStream");
			thread.start();
			try {
				do {
					Thread.sleep(200);
				} while (!thread.isAlive());
			} catch (InterruptedException e) {
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}
			sock.close();
			int c = 0;
			do {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
				}
				if (interrupted) {
					fail("read interrupted");
				}
				if (++c > 4) {
					fail("read call did not exit");
				}
			} while (thread.isAlive());
		} catch (IOException e) {
			fail("Unexpected IOException : " + e);
		}
	}

	/**
	 * @tests java.net.Socket#getKeepAlive()
	 */
	public void test_getKeepAlive() {
		try {
			int sport = startServer("SServer getKeepAlive");
			int portNumber = Support_PortManager.getNextPort();
			Socket theSocket = new Socket(InetAddress.getLocalHost(), sport,
					null, portNumber);
			theSocket.setKeepAlive(true);
			assertTrue("getKeepAlive false when it should be true", theSocket
					.getKeepAlive());
			theSocket.setKeepAlive(false);
			assertFalse("getKeepAlive true when it should be False", theSocket
					.getKeepAlive());
			ensureExceptionThrownIfOptionIsUnsupportedOnOS(SO_KEEPALIVE);
		} catch (Exception e) {
			handleException(e, SO_KEEPALIVE);
		}
	}

	/**
	 * @tests java.net.Socket#getLocalAddress()
	 */
	public void test_getLocalAddress() {
		// Test for method java.net.InetAddress
		// java.net.Socket.getLocalAddress()
		try {
			int sport = startServer("SServer getLocAddress");
			int portNumber = Support_PortManager.getNextPort();
			s = new Socket(InetAddress.getLocalHost(), sport, null, portNumber);
			assertTrue("Returned incorrect InetAddress", s.getLocalAddress()
					.equals(InetAddress.getLocalHost()));

			// now validate thet behaviour when the any address is returned
			String preferIPv4StackValue = System
					.getProperty("java.net.preferIPv4Stack");
			String preferIPv6AddressesValue = System
					.getProperty("java.net.preferIPv6Addresses");

			s = new Socket();
			s.bind(new InetSocketAddress(InetAddress.getByName("0.0.0.0"), 0));

			if (((preferIPv4StackValue == null) || preferIPv4StackValue
					.equalsIgnoreCase("false"))
					&& (preferIPv6AddressesValue != null)
					&& (preferIPv6AddressesValue.equals("true"))) {
				assertTrue(
						"ANY address not returned correctly (getLocalAddress) with preferIPv6Addresses=true, preferIPv4Stack=false "
								+ s.getLocalSocketAddress(), s
								.getLocalAddress() instanceof Inet6Address);
			} else {
				assertTrue(
						"ANY address not returned correctly (getLocalAddress) with preferIPv6Addresses=true, preferIPv4Stack=true "
								+ s.getLocalSocketAddress(), s
								.getLocalAddress() instanceof Inet4Address);
			}
			s.close();

		} catch (Exception e) {
			fail("Exception during getLocalAddress test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.net.Socket#getLocalPort()
	 */
	public void test_getLocalPort() {
		// Test for method int java.net.Socket.getLocalPort()
		try {
			int sport = startServer("SServer getLocalPort");
			int portNumber = Support_PortManager.getNextPort();
			s = new Socket(InetAddress.getLocalHost().getHostName(), sport,
					InetAddress.getLocalHost(), portNumber);
			assertTrue("Returned incorrect port",
					s.getLocalPort() == portNumber);
		} catch (Exception e) {
			fail("Exception during getLocalPort test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.net.Socket#getOutputStream()
	 */
	public void test_getOutputStream() {
		// Test for method java.io.OutputStream
		// java.net.Socket.getOutputStream()
		try {
			int sport = startServer("SServer getOutputStream");
			int portNumber = Support_PortManager.getNextPort();
			s = new Socket(InetAddress.getLocalHost(), sport, null, portNumber);
			java.io.OutputStream os = s.getOutputStream();
			assertTrue("Failed to get stream", os != null);
			tearDown();
		} catch (Exception e) {
			fail("Exception during getOutputStream test" + e.toString());
		}

		try {
			int portNum = Support_PortManager.getNextPort();
			final ServerSocket ss = new ServerSocket(portNum);
			Socket sock = new Socket(InetAddress.getLocalHost(), portNum);
			Runnable runnable = new Runnable() {
				public void run() {
					try {
						Socket as = ss.accept();
						ss.close();
						InputStream in = as.getInputStream();
						in.read();
						in.close();
					} catch (IOException e) {
						System.out.println(Thread.currentThread() + ": " + e);
					}
				}
			};
			Thread thread = new Thread(runnable, "Socket.getOutputStream");
			thread.start();
			int c = 0;
			do {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
				}
				if (++c > 4)
					fail("thread is not alive");
			} while (!thread.isAlive());
			OutputStream out = sock.getOutputStream();
			byte[] data = new byte[256];
			out.write(data);
			c = 0;
			do {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
				}
				if (++c > 4) {
					fail("read call did not exit");
				}
			} while (thread.isAlive());

			boolean exception = false;
			try {
				for (int i = 0; i < 400; i++)
					out.write(data);
			} catch (IOException e) {
				exception = true;
			}
			out.close();
			assertTrue("write to closed socket did not cause exception",
					exception);
		} catch (IOException e) {
			fail("Unexpected IOException : " + e.getMessage());
		}
	}

	/**
	 * @tests java.net.Socket#getPort()
	 */
	public void test_getPort() {
		// Test for method int java.net.Socket.getPort()
		try {
			int sport = startServer("SServer getPort");
			int portNumber = Support_PortManager.getNextPort();
			s = new Socket(InetAddress.getLocalHost(), sport, null, portNumber);
			assertTrue("Returned incorrect port" + s.getPort(),
					s.getPort() == sport);
		} catch (Exception e) {
			fail("Exception during getPort test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.net.Socket#getSoLinger()
	 */
	public void test_getSoLinger() {
		// Test for method int java.net.Socket.getSoLinger()
		try {
			int sport = startServer("SServer getSoLinger");
			int portNumber = Support_PortManager.getNextPort();
			s = new Socket(InetAddress.getLocalHost(), sport, null, portNumber);
			s.setSoLinger(true, 200);
			assertTrue("Returned incorrect linger", s.getSoLinger() == 200);
			ensureExceptionThrownIfOptionIsUnsupportedOnOS(SO_LINGER);
			s.setSoLinger(false, 0);
		} catch (Exception e) {
			handleException(e, SO_LINGER);
		}
	}

	/**
	 * @tests java.net.Socket#getReceiveBufferSize()
	 */
	public void test_getReceiveBufferSize() {
		try {
			int sport = startServer("SServer getReceiveBufferSize");
			int portNumber = Support_PortManager.getNextPort();
			s = new Socket(InetAddress.getLocalHost().getHostName(), sport,
					null, portNumber);
			s.setReceiveBufferSize(130);
			assertTrue("Incorrect buffer size", s.getReceiveBufferSize() >= 130);
			ensureExceptionThrownIfOptionIsUnsupportedOnOS(SO_RCVBUF);
		} catch (Exception e) {
			handleException(e, SO_RCVBUF);
		}
	}

	/**
	 * @tests java.net.Socket#getSendBufferSize()
	 */
	public void test_getSendBufferSize() {
		try {
			int sport = startServer("SServer setSendBufferSize");
			int portNumber = Support_PortManager.getNextPort();
			s = new Socket(InetAddress.getLocalHost().getHostName(), sport,
					null, portNumber);
			s.setSendBufferSize(134);
			assertTrue("Incorrect buffer size", s.getSendBufferSize() >= 134);
			ensureExceptionThrownIfOptionIsUnsupportedOnOS(SO_SNDBUF);
		} catch (Exception e) {
			handleException(e, SO_SNDBUF);
		}
	}

	/**
	 * @tests java.net.Socket#getSoTimeout()
	 */
	public void test_getSoTimeout() {
		// Test for method int java.net.Socket.getSoTimeout()
		try {
			int sport = startServer("SServer getSoTimeout");
			s = new Socket(InetAddress.getLocalHost(), sport);
			s.setSoTimeout(100);
			assertTrue("Returned incorrect sotimeout", s.getSoTimeout() == 100);
			ensureExceptionThrownIfOptionIsUnsupportedOnOS(SO_TIMEOUT);
		} catch (Exception e) {
			handleException(e, SO_TIMEOUT);
		}
	}

	/**
	 * @tests java.net.Socket#getTcpNoDelay()
	 */
	public void test_getTcpNoDelay() { 
		// Test for method boolean java.net.Socket.getTcpNoDelay()
		try {
			int sport = startServer("SServer getTcpNoDelay");
			int portNumber = Support_PortManager.getNextPort();
			s = new Socket(InetAddress.getLocalHost(), sport, null, portNumber);
			boolean bool = !s.getTcpNoDelay();
			s.setTcpNoDelay(bool);
			assertTrue("Failed to get no delay setting: " + s.getTcpNoDelay(),
					s.getTcpNoDelay() == bool);
			ensureExceptionThrownIfOptionIsUnsupportedOnOS(TCP_NODELAY);
		} catch (Exception e) {
			handleException(e, TCP_NODELAY);
		}
	}

	/**
	 * @tests java.net.Socket#setKeepAlive(boolean)
	 */
	public void test_setKeepAliveZ() {
		// There is not really a good test for this as it is there to detect
		// crashed machines. Just make sure we can set it
		try {
			int sport = startServer("SServer setKeepAlive");
			int portNumber = Support_PortManager.getNextPort();
			Socket theSocket = new Socket(InetAddress.getLocalHost(), sport,
					null, portNumber);
			theSocket.setKeepAlive(true);
			theSocket.setKeepAlive(false);
			ensureExceptionThrownIfOptionIsUnsupportedOnOS(SO_KEEPALIVE);
		} catch (Exception e) {
			handleException(e, SO_KEEPALIVE);
		}
	}

	/**
	 * @tests java.net.Socket#setSocketImplFactory(java.net.SocketImplFactory)
	 */
	public void test_setSocketImplFactoryLjava_net_SocketImplFactory() {
		// Test for method void
		// java.net.Socket.setSocketImplFactory(java.net.SocketImplFactory)
		
		// Cannot test as setting will cause the factory to be changed for
		// all subsequent sockets
	}

	/**
	 * @tests java.net.Socket#setSendBufferSize(int)
	 */
	public void test_setSendBufferSizeI() {
		try {
			int sport = startServer("SServer setSendBufferSizeI");
			int portNumber = Support_PortManager.getNextPort();
			s = new Socket(InetAddress.getLocalHost(), sport, null, portNumber);
			s.setSendBufferSize(134);
			assertTrue("Incorrect buffer size", s.getSendBufferSize() >= 134);
			ensureExceptionThrownIfOptionIsUnsupportedOnOS(SO_SNDBUF);
		} catch (Exception e) {
			handleException(e, SO_SNDBUF);
		}
	}

	/**
	 * @tests java.net.Socket#setReceiveBufferSize(int)
	 */
	public void test_setReceiveBufferSizeI() {
		try {
			int sport = startServer("SServer setReceiveBufferSizeI");
			int portNumber = Support_PortManager.getNextPort();
			s = new Socket(InetAddress.getLocalHost(), sport, null, portNumber);
			s.setReceiveBufferSize(130);
			assertTrue("Incorrect buffer size", s.getReceiveBufferSize() >= 130);
			ensureExceptionThrownIfOptionIsUnsupportedOnOS(SO_RCVBUF);
		} catch (Exception e) {
			handleException(e, SO_RCVBUF);
		}
	}

	/**
	 * @tests java.net.Socket#setSoLinger(boolean, int)
	 */
	public void test_setSoLingerZI() {
		// Test for method void java.net.Socket.setSoLinger(boolean, int)
		try {
			int sport = startServer("SServer setSoLingerZI");
			int portNumber = Support_PortManager.getNextPort();
			s = new Socket(InetAddress.getLocalHost(), sport, null, portNumber);
			s.setSoLinger(true, 500);
			assertTrue("Set incorrect linger", s.getSoLinger() == 500);
			ensureExceptionThrownIfOptionIsUnsupportedOnOS(SO_LINGER);
			s.setSoLinger(false, 0); 
		} catch (Exception e) {
			handleException(e, SO_LINGER);
		}
	}

	/**
	 * @tests java.net.Socket#setSoTimeout(int)
	 */
	public void test_setSoTimeoutI() { 
		// Test for method void java.net.Socket.setSoTimeout(int)
		try {
			int sport = startServer("SServer seSoTimeoutI");
			int portNumber = Support_PortManager.getNextPort();
			s = new Socket(InetAddress.getLocalHost(), sport, null, portNumber);
			s.setSoTimeout(100);
			assertTrue("Set incorrect sotimeout", s.getSoTimeout() == 100);
			ensureExceptionThrownIfOptionIsUnsupportedOnOS(SO_TIMEOUT);
		} catch (Exception e) {
			handleException(e, SO_TIMEOUT);
		}
	}

	/**
	 * @tests java.net.Socket#setTcpNoDelay(boolean)
	 */
	public void test_setTcpNoDelayZ() {
		// Test for method void java.net.Socket.setTcpNoDelay(boolean)
		try {
			int sport = startServer("SServer setTcpNoDelayZ");
			int portNumber = Support_PortManager.getNextPort();
			s = new Socket(InetAddress.getLocalHost(), sport, null, portNumber);
			boolean bool;
			s.setTcpNoDelay(bool = !s.getTcpNoDelay());
			assertTrue("Failed to set no delay setting: " + s.getTcpNoDelay(),
					s.getTcpNoDelay() == bool);
			ensureExceptionThrownIfOptionIsUnsupportedOnOS(TCP_NODELAY);
		} catch (Exception e) {
			handleException(e, TCP_NODELAY);
		}
	}

	/**
	 * @tests java.net.Socket#toString()
	 */
	public void test_toString() {
		// Test for method java.lang.String java.net.Socket.toString()
		try {
			int sport = startServer("SServer toString");
			int portNumber = Support_PortManager.getNextPort();
			s = new Socket(InetAddress.getLocalHost().getHostName(), sport,
					InetAddress.getLocalHost(), portNumber);
			assertTrue("Returned incorrect string: " + s.toString()
					+ " localHost: " + InetAddress.getLocalHost(), s.toString()
					.equals(
							"Socket[addr=" + InetAddress.getLocalHost()
									+ ",port=" + s.getPort() + ",localport="
									+ s.getLocalPort() + "]"));
		} catch (Exception e) {
			fail("Exception during toString test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.net.Socket#shutdownInput()
	 */
	public void test_shutdownInput() {
		try {
			InetAddress addr = InetAddress.getLocalHost();
			int port = Support_PortManager.getNextPort();
			ServerSocket serverSocket = new ServerSocket(port, 5, addr);
			Socket theSocket = new Socket(addr, port);
			Socket servSock = serverSocket.accept();

			InputStream theInput = theSocket.getInputStream();
			OutputStream theOutput = servSock.getOutputStream();

			// shutdown the input
			theSocket.shutdownInput();

			// send the regular data
			String sendString = new String("Test");
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
			assertTrue("We should have received no data on shutdown input:"
					+ receivedString, 0 == totalBytesRead);

			theSocket.close();
			serverSocket.close();
		} catch (Exception e) {
			fail("Got exception during shutdownInput tests"
					+ e.toString());
		}
	}

	/**
	 * @tests java.net.Socket#shutdownOutput()
	 */
	public void test_shutdownOutput() {
		try {
			InetAddress addr = InetAddress.getLocalHost();
			int port = Support_PortManager.getNextPort();
			ServerSocket serverSocket = new ServerSocket(port, 5, addr);
			Socket theSocket = new Socket(addr, port);
			Socket servSock = serverSocket.accept();

			InputStream theInput = theSocket.getInputStream();
			OutputStream theOutput = servSock.getOutputStream();

			// shutdown the output
			servSock.shutdownOutput();

			// send the regular data
			String sendString = new String("Test");
			try {
				theOutput.write(sendString.getBytes());
				theOutput.flush();
				fail(
						"No exception when writing on socket with output shutdown");
			} catch (Exception e) {
			}

			theSocket.close();
			serverSocket.close();
		} catch (Exception e) {
			fail("Got exception during shutdownOutput tests"
					+ e.toString());
		}
	}

	/**
	 * @tests java.net.Socket#getLocalSocketAddress()
	 */
	public void test_getLocalSocketAddress() {
		// set up server connect and then validate that we get the right
		// response for the local address
		try {
			int sport = startServer("SServer getLocSocketAddress");
			int portNumber = Support_PortManager.getNextPort();
			s = new Socket(InetAddress.getLocalHost(), sport, null, portNumber);
			assertTrue("Returned incorrect InetSocketAddress(1):"
					+ s.getLocalSocketAddress().toString()
					+ "Expected: "
					+ (new InetSocketAddress(InetAddress.getLocalHost(),
							portNumber)).toString(), s.getLocalSocketAddress()
					.equals(
							new InetSocketAddress(InetAddress.getLocalHost(),
									portNumber)));
			s.close();

			// now create a socket that is not bound and validate we get the
			// right answer
			Socket theSocket = new Socket();
			assertTrue(
					"Returned incorrect InetSocketAddress -unbound socket- Expected null",
					theSocket.getLocalSocketAddress() == null);

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

			// now validate thet behaviour when the any address is returned
			s = new Socket();
			s.bind(new InetSocketAddress(InetAddress.getByName("0.0.0.0"), 0));

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
								+ s.getLocalSocketAddress(),
						((InetSocketAddress) s.getLocalSocketAddress())
								.getAddress() instanceof Inet6Address);
			} else {
				assertTrue(
						"ANY address not returned correctly with preferIPv6Addresses=true, preferIPv4Stack=true "
								+ s.getLocalSocketAddress(),
						((InetSocketAddress) s.getLocalSocketAddress())
								.getAddress() instanceof Inet4Address);
			}
			s.close();

			// now validate the same for getLocalAddress
			s = new Socket();
			s.bind(new InetSocketAddress(InetAddress.getByName("0.0.0.0"), 0));
			if (((preferIPv4StackValue == null) || preferIPv4StackValue
					.equalsIgnoreCase("false"))
					&& (preferIPv6AddressesValue != null)
					&& (preferIPv6AddressesValue.equals("true"))) {
				assertTrue(
						"ANY address not returned correctly with preferIPv6Addresses=true, preferIPv4Stack=false "
								+ s.getLocalSocketAddress(),
						((InetSocketAddress) s.getLocalSocketAddress())
								.getAddress() instanceof Inet6Address);
			} else {
				assertTrue(
						"ANY address not returned correctly with preferIPv6Addresses=true, preferIPv4Stack=true "
								+ s.getLocalSocketAddress(),
						((InetSocketAddress) s.getLocalSocketAddress())
								.getAddress() instanceof Inet4Address);
			}
			s.close();
		} catch (Exception e) {
			fail("Exception during getLocalSocketAddress test: " + e);
		}
	}

	/**
	 * @tests java.net.Socket#getRemoteSocketAddress()
	 */
	public void test_getRemoteSocketAddress() {
		// set up server connect and then validate that we get the right
		// response for the remote address
		try {
			int sport = startServer("SServer getLocRemoteAddress");
			int portNumber = Support_PortManager.getNextPort();
			s = new Socket(InetAddress.getLocalHost(), sport, null, portNumber);
			assertTrue("Returned incorrect InetSocketAddress(1):"
					+ s.getLocalSocketAddress().toString(), s
					.getRemoteSocketAddress().equals(
							new InetSocketAddress(InetAddress.getLocalHost(),
									sport)));
			s.close();

			// now create one that is not connect and validate that we get the
			// right answer
			Socket theSocket = new Socket();
			portNumber = Support_PortManager.getNextPort();
			theSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(),
					portNumber));

			assertTrue(
					"Returned incorrect InetSocketAddress -unconnected socket:"
							+ "Expected: NULL", theSocket
							.getRemoteSocketAddress() == null);

			// now connect and validate we get the right answer
			theSocket.connect(new InetSocketAddress(InetAddress.getLocalHost(),
					sport));
			assertTrue("Returned incorrect InetSocketAddress(2):"
					+ theSocket.getRemoteSocketAddress().toString(), theSocket
					.getRemoteSocketAddress().equals(
							new InetSocketAddress(InetAddress.getLocalHost(),
									sport)));
			theSocket.close();

		} catch (Exception e) {
			fail("Exception during getRemoteSocketAddress test: " + e);
		}
	}

	/**
	 * @tests java.net.Socket#isBound()
	 */
	public void test_isBound() {
		try {
			InetAddress addr = InetAddress.getLocalHost();
			int port = Support_PortManager.getNextPort();
			ServerSocket serverSocket = new ServerSocket(port, 5, addr);
			Socket theSocket = new Socket(addr, port);
			Socket servSock = serverSocket.accept();
			assertTrue("Socket indicated  not bound when it should be (1)",
					theSocket.isBound());
			theSocket.close();
			serverSocket.close();

			// now do it with the new constructors and revalidate. Connect causes
			// the socket to be bound
			InetSocketAddress theAddress = new InetSocketAddress(InetAddress
					.getLocalHost(), Support_PortManager.getNextPort());
			theSocket = new Socket();
			assertFalse("Socket indicated bound when it was not (2)", theSocket
					.isBound());
			serverSocket = new ServerSocket();
			serverSocket.bind(theAddress);
			theSocket.connect(theAddress);
			servSock = serverSocket.accept();
			assertTrue("Socket indicated not bound when it should be (2)",
					theSocket.isBound());
			theSocket.close();
			serverSocket.close();

			// now test when we bind explicitely
			InetSocketAddress theLocalAddress = new InetSocketAddress(
					InetAddress.getLocalHost(), Support_PortManager
							.getNextPort());
			theSocket = new Socket();
			assertFalse("Socket indicated bound when it was not (3)", theSocket
					.isBound());
			theSocket.bind(theLocalAddress);
			assertTrue("Socket indicated not bound when it should be (3a)",
					theSocket.isBound());
			theSocket.close();
			assertTrue("Socket indicated not bound when it should be (3b)",
					theSocket.isBound());
		} catch (Exception e) {
			fail("Got exception during isBound tests" + e.toString());
		}
	}

	/**
	 * @tests java.net.Socket#isConnected()
	 */
	public void test_isConnected() {
		try {
			InetAddress addr = InetAddress.getLocalHost();
			int port = Support_PortManager.getNextPort();
			ServerSocket serverSocket = new ServerSocket(port, 5, addr);
			Socket theSocket = new Socket(addr, port);
			Socket servSock = serverSocket.accept();
			assertTrue("Socket indicated  not connected when it should be",
					theSocket.isConnected());
			theSocket.close();
			serverSocket.close();

			// now do it with the new constructors and revalidate
			InetSocketAddress theAddress = new InetSocketAddress(InetAddress
					.getLocalHost(), Support_PortManager.getNextPort());
			theSocket = new Socket();
			assertFalse("Socket indicated connected when it was not", theSocket
					.isConnected());
			serverSocket = new ServerSocket();
			serverSocket.bind(theAddress);
			theSocket.connect(theAddress);
			servSock = serverSocket.accept();
			assertTrue("Socket indicated  not connected when it should be",
					theSocket.isConnected());
			theSocket.close();
			serverSocket.close();
		} catch (Exception e) {
			fail("Got exception during isConnected tests" + e.toString());
		}
	}

	/**
	 * @tests java.net.Socket#isClosed()
	 */
	public void test_isClosed() {
		try {
			InetAddress addr = InetAddress.getLocalHost();
			int port = Support_PortManager.getNextPort();
			ServerSocket serverSocket = new ServerSocket(port, 5, addr);
			Socket theSocket = new Socket(addr, port);
			Socket servSock = serverSocket.accept();

			// validate isClosed returns expected values
			assertFalse("Socket should indicate it is not closed(1):",
					theSocket.isClosed());
			theSocket.close();
			assertTrue("Socket should indicate it is closed(1):", theSocket
					.isClosed());

			theSocket = new Socket(addr, port);
			assertFalse("Socket should indicate it is not closed(2):",
					theSocket.isClosed());
			theSocket.close();
			assertTrue("Socket should indicate it is closed(2):", theSocket
					.isClosed());

			// validate that isClosed works ok for sockets returned from
			// ServerSocket.accept()
			assertFalse("Server Socket should indicate it is not closed:",
					servSock.isClosed());
			servSock.close();
			assertTrue("Server Socket should indicate it is closed:", servSock
					.isClosed());
		} catch (Exception e) {
			fail("Got exception during isClosed tests" + e.toString());
		}
	}

	/**
	 * @tests java.net.Socket#bind(java.net.SocketAddress)
	 */
	public void test_bindLjava_net_SocketAddress() {

		class mySocketAddress extends SocketAddress {

			public mySocketAddress() {
			}
		}

		try {
			// Address we cannot bind to
			Socket theSocket = new Socket();
			try {
				theSocket
						.bind(new InetSocketAddress(
								InetAddress
										.getByAddress(Support_Configuration.nonLocalAddressBytes),
								Support_PortManager.getNextPort()));
				assertFalse("No exception when binding to bad address:"
						+ theSocket.getLocalSocketAddress().toString(), true);
			} catch (IOException ex) {
			}
			theSocket.close();

			// now create a socket that is not bound and then bind it
			theSocket = new Socket();
			int portNumber = Support_PortManager.getNextPort();
			theSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(),
					portNumber));

			// validate that the localSocketAddress reflects the address we
			// bound to
			assertTrue("Local address not correct after bind:"
					+ theSocket.getLocalSocketAddress().toString()
					+ "Expected: "
					+ (new InetSocketAddress(InetAddress.getLocalHost(),
							portNumber)).toString(), theSocket
					.getLocalSocketAddress().equals(
							new InetSocketAddress(InetAddress.getLocalHost(),
									portNumber)));

			// make sure we can now connect and that connections appear to come
			// from the address we bound to.
			InetSocketAddress theAddress = new InetSocketAddress(InetAddress
					.getLocalHost(), Support_PortManager.getNextPort());
			ServerSocket serverSocket = new ServerSocket();
			serverSocket.bind(theAddress);
			theSocket.connect(theAddress);
			Socket servSock = serverSocket.accept();
			assertTrue(
					"Returned Remote address from server connected to does not match expected local address:"
							+ servSock.getRemoteSocketAddress().toString()
							+ "Expected: "
							+ (new InetSocketAddress(
									InetAddress.getLocalHost(), portNumber))
									.toString(), servSock
							.getRemoteSocketAddress().equals(
									new InetSocketAddress(InetAddress
											.getLocalHost(), portNumber)));
			theSocket.close();
			servSock.close();
			serverSocket.close();

			// validate if we pass in null that it picks an address for us and
			// all is ok
			theSocket = new Socket();
			theSocket.bind(null);
			assertTrue("Bind with null did not work", theSocket
					.getLocalSocketAddress() != null);
			theSocket.close();

			// now check the error conditions

			// Address that we have allready bound to
			theSocket = new Socket();
			Socket theSocket2 = new Socket();
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

			// unsupported SocketAddress subclass
			theSocket = new Socket();
			try {
				theSocket.bind(new mySocketAddress());
				fail("No exception when binding using unsupported SocketAddress subclass");
			} catch (IllegalArgumentException ex) {
			}
			theSocket.close();
		} catch (Exception e) {
			fail("Unexpected exception during bind test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.net.Socket#connect(java.net.SocketAddress)
	 */
	public void test_connectLjava_net_SocketAddress() {
		// needed for some tests
		class mySocketAddress extends SocketAddress {

			public mySocketAddress() {
			}
		}

		class SocketCloser extends Thread {

			int timeout = 0;

			Socket theSocket = null;

			public void run() {
				try {
					Thread.sleep(timeout);
					theSocket.close();
				} catch (Exception e) {
				}
				;
				return;
			}

			public SocketCloser(int timeout, Socket theSocket) {
				this.timeout = timeout;
				this.theSocket = theSocket;
			}
		}

		// start by validating the error checks
		int portNumber = Support_PortManager.getNextPort();
		Socket theSocket = null;
		ServerSocket serverSocket = null;
		SocketAddress theAddress = null;
		SocketAddress nonConnectableAddress = null;
		SocketAddress nonReachableAddress = null;
		SocketAddress invalidType = null;
		// byte[] theBytes = {-1,-1,-1,-1};
		byte[] theBytes = { 0, 0, 0, 0 };
		try {
			theAddress = new InetSocketAddress(InetAddress.getLocalHost(),
					portNumber);
			nonConnectableAddress = new InetSocketAddress(InetAddress
					.getByAddress(theBytes), portNumber);
			nonReachableAddress = new InetSocketAddress(InetAddress
					.getByName(Support_Configuration.ResolvedNotExistingHost),
					portNumber);

			invalidType = new mySocketAddress();
		} catch (Exception e) {
			fail(
					"Exception when setting up for connect with timeout tests ");
		}

		try {
			theSocket = new Socket();
			theSocket.connect(null);
			fail("No exception after null address passed in");
		} catch (Exception e) {
			assertTrue("Wrong exception null address passed in: "
					+ e.toString(), (e instanceof IllegalArgumentException));
		}

		try {
			theSocket = new Socket();
			theSocket.connect(invalidType);
			fail(
					"No exception when invalid socket address type passed in: ");
		} catch (Exception e) {
			assertTrue(
					"Wrong exception when when invalid socket address type passed in: "
							+ e.toString(),
					(e instanceof IllegalArgumentException));
		}

		try {
			theSocket = new Socket();
			theSocket.connect(nonConnectableAddress);
			fail("No exception when non Connectable Address passed in: ");
		} catch (Exception e) {
			assertTrue(
					"Wrong exception when non Connectable Address passed in: "
							+ e.toString(), (e instanceof ConnectException));
		}

		// now validate that we get a connect exception if we try to connect to
		// an address on which nobody is listening
		try {
			theSocket = new Socket();
			theSocket.connect(theAddress);
			theSocket.close();
			fail(
					"No exception when connecting to address nobody listening on: ");
		} catch (Exception e) {
			assertTrue(
					"Wrong exception when connecting to address nobody listening on: "
							+ e.toString(), (e instanceof ConnectException));
		}

		// now validate that we can acutally connect when sombody is listening
		try {
			theSocket = new Socket();
			serverSocket = new ServerSocket();
			serverSocket.bind(theAddress);
			theSocket.connect(theAddress);
			theSocket.close();
			serverSocket.close();
		} catch (Exception e) {
			fail("Got exception when connect should be successful: "
					+ e.toString());
		}

		// now validate that we can acutally connect when sombody is listening
		try {
			theSocket = new Socket();
			serverSocket = new ServerSocket();
			serverSocket.bind(theAddress);
			theSocket.connect(theAddress);

			// validate that when a socket is connected that it answers
			// correctly to related queries
			assertTrue("Socket did not returned connected when it is: ",
					theSocket.isConnected());
			assertFalse("Socket returned closed when it should be connected ",
					theSocket.isClosed());
			assertTrue("Socket returned not bound when it should be: ",
					theSocket.isBound());
			assertFalse(
					"Socket returned input Shutdown when it should be connected ",
					theSocket.isInputShutdown());
			assertFalse(
					"Socket returned output Shutdown when it should be connected ",
					theSocket.isOutputShutdown());
			assertTrue("Local port on connected socket was 0", theSocket
					.getLocalPort() != 0);
			theSocket.close();
			serverSocket.close();
		} catch (Exception e) {
			fail("Got exception when connect should be successful: "
					+ e.toString());
		}

		// now validate that we get the right exception if we connect when we
		// are already connected
		try {
			theSocket = new Socket();
			serverSocket = new ServerSocket();
			serverSocket.bind(theAddress);
			theSocket.connect(theAddress);
			theSocket.connect(theAddress);
			theSocket.close();
			serverSocket.close();
			fail(
					"No exception when we try to connect on a connected socket: ");

		} catch (Exception e) {
			assertTrue(
					"Wrong exception when connecting on socket that is allready connected"
							+ e.toString(), (e instanceof SocketException));
			assertFalse(
					"Wrong exception when connecting on socket that is allready connected"
							+ e.toString(),
					(e instanceof SocketTimeoutException));
			try {
				theSocket.close();
				serverSocket.close();
			} catch (Exception e2) {
			}

		}

		// now validate that connected socket can be used to read/write
		try {
			theSocket = new Socket();
			serverSocket = new ServerSocket();
			serverSocket.bind(theAddress);
			theSocket.connect(theAddress);
			Socket servSock = serverSocket.accept();
			InputStream theInput = theSocket.getInputStream();
			OutputStream theOutput = servSock.getOutputStream();
			InputStream theInput2 = servSock.getInputStream();
			OutputStream theOutput2 = theSocket.getOutputStream();

			String sendString = new String("Test");
			theOutput.write(sendString.getBytes());
			theOutput.flush();

			Thread.sleep(1000);

			int totalBytesRead = 0;
			byte[] myBytes = new byte[100];
			while (theInput.available() > 0) {
				int bytesRead = theInput.read(myBytes, totalBytesRead,
						myBytes.length - totalBytesRead);
				totalBytesRead = totalBytesRead + bytesRead;
			}

			String receivedString = new String(myBytes, 0, totalBytesRead);
			assertTrue("Could not recv on socket connected with timeout:"
					+ receivedString + ":" + sendString, receivedString
					.equals(sendString));

			sendString = new String("SEND - Test");
			theOutput2.write(sendString.getBytes());
			theOutput2.flush();
			Thread.sleep(1000);

			totalBytesRead = 0;
			myBytes = new byte[100];
			while (theInput2.available() > 0) {
				int bytesRead = theInput2.read(myBytes, totalBytesRead,
						myBytes.length - totalBytesRead);
				totalBytesRead = totalBytesRead + bytesRead;
			}

			receivedString = new String(myBytes, 0, totalBytesRead);
			assertTrue("Could not send on socket connected with timeout:"
					+ receivedString + ":" + sendString, receivedString
					.equals(sendString));

			theSocket.close();
			serverSocket.close();
		} catch (Exception e) {
			fail(
					"Got exception trying to validate that connected socket can be used for read/write"
							+ e.toString());
		}
	}

	/**
	 * @tests java.net.Socket#connect(java.net.SocketAddress, int)
	 */
	public void test_connectLjava_net_SocketAddressI() {

		// needed for some tests
		class mySocketAddress extends SocketAddress {

			public mySocketAddress() {
			}
		}

		class SocketCloser extends Thread {

			int timeout = 0;

			Socket theSocket = null;

			public void run() {
				try {
					Thread.sleep(timeout);
					theSocket.close();
				} catch (Exception e) {
				}
				;
				return;
			}

			public SocketCloser(int timeout, Socket theSocket) {
				this.timeout = timeout;
				this.theSocket = theSocket;
			}
		}

		class SocketConnector extends Thread {

			int timeout = 0;

			Socket theSocket = null;

			SocketAddress address = null;

			public void run() {
				try {
					theSocket.connect(address, timeout);
				} catch (Exception e) {
				}
				;
				return;
			}

			public SocketConnector(int timeout, Socket theSocket,
					SocketAddress address) {
				this.timeout = timeout;
				this.theSocket = theSocket;
				this.address = address;
			}
		}

		// start by validating the error checks
		int portNumber = Support_PortManager.getNextPort();
		Socket theSocket = null;
		ServerSocket serverSocket = null;
		SocketAddress theAddress = null;
		SocketAddress nonConnectableAddress = null;
		SocketAddress nonReachableAddress = null;
		SocketAddress nonListeningAddress = null;
		SocketAddress invalidType = null;
		byte[] theBytes = { 0, 0, 0, 0 };

		try {
			theAddress = new InetSocketAddress(InetAddress.getLocalHost(),
					portNumber);
			nonConnectableAddress = new InetSocketAddress(InetAddress
					.getByAddress(theBytes), portNumber);
			nonReachableAddress = new InetSocketAddress(InetAddress
					.getByName(Support_Configuration.ResolvedNotExistingHost),
					portNumber);
			// make sure we get another port
			Thread.sleep(7000);
			nonListeningAddress = new InetSocketAddress(InetAddress
					.getLocalHost(), Support_PortManager.getNextPort());
			invalidType = new mySocketAddress();
		} catch (Exception e) {
			fail(
					"Exception when setting up for connect with timeout tests ");
		}

		try {
			theSocket = new Socket();
			theSocket.connect(theAddress, -100);
			fail("No exception after negative timeout passed in");
		} catch (Exception e) {
			assertTrue("Wrong exception when negative timeout passed in: "
					+ e.toString(), (e instanceof IllegalArgumentException));
		}

		try {
			theSocket = new Socket();
			theSocket.connect(null, 0);
			fail("No exception after null address passed in");
		} catch (Exception e) {
			assertTrue("Wrong exception null address passed in: "
					+ e.toString(), (e instanceof IllegalArgumentException));
		}

		try {
			theSocket = new Socket();
			theSocket.connect(invalidType, 100000);
			fail(
					"No exception when invalid socket address type passed in: ");
		} catch (Exception e) {
			assertTrue(
					"Wrong exception when when invalid socket address type passed in: "
							+ e.toString(),
					(e instanceof IllegalArgumentException));
		}

		try {
			theSocket = new Socket();
			theSocket.connect(nonConnectableAddress, 100000);
			fail("No exception when non Connectable Address passed in: ");
		} catch (Exception e) {
			assertTrue(
					"Wrong exception when non Connectable Address passed in: "
							+ e.toString(), (e instanceof SocketException));
		}

		// now validate that we get a connect exception if we try to connect to
		// an address on which nobody is listening
		try {
			theSocket = new Socket();
			theSocket.connect(theAddress, 0);
			theSocket.close();
			fail(
					"No timeout:No exception when connecting to address nobody listening on: ");
		} catch (Exception e) {
			assertTrue(
					"No timeout:Wrong exception when connecting to address nobody listening on: "
							+ e.toString(), (e instanceof ConnectException));
		}

		// now validate that we can acutally connect when sombody is listening
		try {
			theSocket = new Socket();
			serverSocket = new ServerSocket();
			serverSocket.bind(theAddress);
			theSocket.connect(theAddress, 0);
			theSocket.close();
			serverSocket.close();
		} catch (Exception e) {
			fail(
					"No timeout:Got exception when connect should be successful: "
							+ e.toString());
		}

		// now validate that we get a connect exception if we try to connect to
		// an address on which nobody is listening
		try {
			theSocket = new Socket();
			theSocket.connect(nonListeningAddress, 100000);
			theSocket.close();
			fail(
					"No exception when connecting to address nobody listening on: ");
		} catch (Exception e) {
			assertTrue(
					"Wrong exception when connecting to address nobody listening on: "
							+ e.toString(), (e instanceof ConnectException));
		}

		// now validate that we get a interrupted exception if we try to connect
		// to an address on which nobody is accepting connections and the
		// timeout expired
		try {
			theSocket = new Socket();
			theSocket.connect(nonReachableAddress, 200);
			theSocket.close();
			fail(
					"No interrupted exception when connecting to address nobody listening on with short timeout 200: ");
		} catch (Exception e) {
			assertTrue(
					"Wrong exception when connecting to address nobody listening on with short timeout 200: "
							+ e.toString(),
					(e instanceof SocketTimeoutException));
		}

		// now validate that we get a interrupted exception if we try to connect
		// to an address on which nobody is accepting connections and the
		// timeout expired
		try {
			theSocket = new Socket();
			theSocket.connect(nonReachableAddress, 40);
			theSocket.close();
			fail(
					"No interrupted exception when connecting to address nobody listening on with short timeout 40: ");
		} catch (Exception e) {
			assertTrue(
					"Wrong exception when connecting to address nobody listening on with short timeout 40: "
							+ e.toString(),
					(e instanceof SocketTimeoutException));
		}

		// now validate that we can acutally connect when sombody is listening
		try {
			new InetSocketAddress(InetAddress.getLocalHost(),
					Support_PortManager.getNextPort());
			theSocket = new Socket();
			serverSocket = new ServerSocket();
			serverSocket.bind(theAddress);
			theSocket.connect(theAddress, 100000);

			// validate that when a socket is connected that it answers
			// correctly to related queries
			assertTrue("Socket did not returned connected when it is: ",
					theSocket.isConnected());
			assertFalse("Socket returned closed when it should be connected ",
					theSocket.isClosed());
			assertTrue("Socket returned not bound when it should be: ",
					theSocket.isBound());
			assertFalse(
					"Socket returned input Shutdown when it should be connected ",
					theSocket.isInputShutdown());
			assertFalse(
					"Socket returned output Shutdown when it should be connected ",
					theSocket.isOutputShutdown());
			assertTrue("Local port on connected socket was 0", theSocket
					.getLocalPort() != 0);
			theSocket.close();
			serverSocket.close();
		} catch (Exception e) {
			fail("Got exception when connect should be successful: "
					+ e.toString());
		}

		// now validate that we get the right exception if we connect when we
		// are already connected
		try {
			new InetSocketAddress(InetAddress.getLocalHost(),
					Support_PortManager.getNextPort());
			theSocket = new Socket();
			serverSocket = new ServerSocket();
			serverSocket.bind(theAddress);
			theSocket.connect(theAddress, 100000);
			theSocket.connect(theAddress, 100000);
			theSocket.close();
			serverSocket.close();
			fail(
					"No exception when we try to connect on a connected socket: ");

		} catch (Exception e) {
			assertTrue(
					"Wrong exception when connecting on socket that is allready connected"
							+ e.toString(), (e instanceof SocketException));
			assertFalse(
					"Wrong exception when connecting on socket that is allready connected"
							+ e.toString(),
					(e instanceof SocketTimeoutException));
			try {
				theSocket.close();
				serverSocket.close();
			} catch (Exception e2) {
			}

		}

		// now validate that connected socket can be used to read/write
		try {
			new InetSocketAddress(InetAddress.getLocalHost(),
					Support_PortManager.getNextPort());
			theSocket = new Socket();
			serverSocket = new ServerSocket();
			serverSocket.bind(theAddress);
			theSocket.connect(theAddress, 100000);
			Socket servSock = serverSocket.accept();
			InputStream theInput = theSocket.getInputStream();
			OutputStream theOutput = servSock.getOutputStream();
			InputStream theInput2 = servSock.getInputStream();
			OutputStream theOutput2 = theSocket.getOutputStream();

			String sendString = new String("Test");
			theOutput.write(sendString.getBytes());
			theOutput.flush();

			Thread.sleep(1000);

			int totalBytesRead = 0;
			byte[] myBytes = new byte[100];
			while (theInput.available() > 0) {
				int bytesRead = theInput.read(myBytes, totalBytesRead,
						myBytes.length - totalBytesRead);
				totalBytesRead = totalBytesRead + bytesRead;
			}

			String receivedString = new String(myBytes, 0, totalBytesRead);
			assertTrue("Could not recv on socket connected with timeout:"
					+ receivedString + ":" + sendString, receivedString
					.equals(sendString));

			sendString = new String("SEND - Test");
			theOutput2.write(sendString.getBytes());
			theOutput2.flush();

			totalBytesRead = 0;
			myBytes = new byte[100];
			Thread.sleep(1000);
			while (theInput2.available() > 0) {
				int bytesRead = theInput2.read(myBytes, totalBytesRead,
						myBytes.length - totalBytesRead);
				totalBytesRead = totalBytesRead + bytesRead;
			}

			receivedString = new String(myBytes, 0, totalBytesRead);
			assertTrue("Could not send on socket connected with timeout:"
					+ receivedString + ":" + sendString, receivedString
					.equals(sendString));

			theSocket.close();
			serverSocket.close();
		} catch (Exception e) {
			fail(
					"Got exception trying to validate that connected socket can be used for read/write"
							+ e.toString());
		}

		// now try to set options while we are connecting
		try {
			theSocket = new Socket();
			SocketConnector connector = new SocketConnector(5000, theSocket,
					nonReachableAddress);
			connector.start();
			theSocket.setSoTimeout(100);
			Thread.sleep(10);
			assertTrue("Socket option not set during connect: 10 ", theSocket
					.getSoTimeout() == 100);
			Thread.sleep(50);
			theSocket.setSoTimeout(200);
			assertTrue("Socket option not set during connect: 50 ", theSocket
					.getSoTimeout() == 200);
			Thread.sleep(5000);
			theSocket.close();
		} catch (Exception e) {
			fail(
					"Unexpected exception while testing set/get socket options during connect:"
							+ e.toString());
		}
	}

	/**
	 * @tests java.net.Socket#isInputShutdown()
	 */
	public void test_isInputShutdown() {
		try {
			InetSocketAddress theAddress = new InetSocketAddress(InetAddress
					.getLocalHost(), Support_PortManager.getNextPort());
			Socket theSocket = new Socket();
			ServerSocket serverSocket = new ServerSocket();
			serverSocket.bind(theAddress);
			theSocket.connect(theAddress);
			Socket servSock = serverSocket.accept();
			InputStream theInput = theSocket.getInputStream();
			OutputStream theOutput = servSock.getOutputStream();

			// make sure we get the right answer with newly connected socket
			assertFalse(
					"Socket indicated input shutdown when it should not have",
					theSocket.isInputShutdown());

			// shutdown the output
			theSocket.shutdownInput();

			// make sure we get the right answer once it is shut down
			assertTrue(
					"Socket indicated input was NOT shutdown when it should have been",
					theSocket.isInputShutdown());

			theSocket.close();
			serverSocket.close();

			// make sure we get the right answer for closed sockets
			assertFalse(
					"Socket indicated input was shutdown when socket was closed",
					servSock.isInputShutdown());

		} catch (Exception e) {
			fail("Got exception during isInputShutdown tests"
					+ e.toString());
		}
	}

	/**
	 * @tests java.net.Socket#isOutputShutdown()
	 */
	public void test_isOutputShutdown() {
		try {
			InetSocketAddress theAddress = new InetSocketAddress(InetAddress
					.getLocalHost(), Support_PortManager.getNextPort());
			Socket theSocket = new Socket();
			ServerSocket serverSocket = new ServerSocket();
			serverSocket.bind(theAddress);
			theSocket.connect(theAddress);
			Socket servSock = serverSocket.accept();
			InputStream theInput = theSocket.getInputStream();
			OutputStream theOutput = servSock.getOutputStream();

			// make sure we get the right answer with newly connected socket
			assertFalse(
					"Socket indicated output shutdown when it should not have",
					servSock.isOutputShutdown());

			// shutdown the output
			servSock.shutdownOutput();

			// make sure we get the right answer once it is shut down
			assertTrue(
					"Socket indicated output was NOT shutdown when it should have been",
					servSock.isOutputShutdown());

			theSocket.close();
			serverSocket.close();

			// make sure we get the right answer for closed sockets
			assertFalse(
					"Socket indicated output was output shutdown when the socket was closed",
					theSocket.isOutputShutdown());
		} catch (Exception e) {
			fail("Got exception during isOutputShutdown tests"
					+ e.toString());
		}
	}

	/**
	 * @tests java.net.Socket#setReuseAddress(boolean)
	 */
	public void test_setReuseAddressZ() {

		try {
			InetAddress allAddresses[] = InetAddress.getAllByName(InetAddress
					.getLocalHost().getHostName());
			if (allAddresses.length > 1) {

				InetSocketAddress theAddress = new InetSocketAddress(
						InetAddress.getLocalHost(), Support_PortManager
								.getNextPort());
				ServerSocket serverSocket = new ServerSocket();
				serverSocket.bind(theAddress);

				// try to bind to port address that is already in use with
				// reuseAddress = false.
				// On windows platforms the bind is allowed even then
				// reUseAddress is false (ONLY IF BOTH SOCKETS
				// ARE IPV4 Sockets) so our test uses the platform to determine
				// what the expected result is. It seems that on linux
				// platforms we also don't get an exception.
				InetSocketAddress theLocalAddress = new InetSocketAddress(
						(InetAddress) allAddresses[1], Support_PortManager
								.getNextPort());
				InetSocketAddress theOtherLocalAddress = new InetSocketAddress(
						(InetAddress) allAddresses[0], theLocalAddress
								.getPort());
				Socket theSocket = new Socket();
				theSocket.setReuseAddress(false);
				theSocket.bind(theLocalAddress);
				Socket theSocket2 = null;
				String platform = System.getProperty("os.name");
				try {
					theSocket2 = new Socket();
					theSocket2.setReuseAddress(false);
					theSocket2.bind(theOtherLocalAddress);

					if ((!platform.startsWith("Linux"))
							&& ((!platform.startsWith("Windows")) ||
							// for windows we don't get an exception with
							// setreuse set to false unless one of the
							// addresses we bind to is an IPv6 address and we
							// are therefore using the IPv6 stack.
							!((((InetAddress) allAddresses[0]) instanceof Inet4Address) && (((InetAddress) allAddresses[1]) instanceof Inet4Address)))) {
						fail(
								"No exception when setReuseAddress is false and we bind:"
										+ theLocalAddress.toString() + ":"
										+ theOtherLocalAddress.toString());
					}
				} catch (IOException ex) {
					if ((platform.startsWith("Linux"))
							|| ((platform.startsWith("Windows")) && ((((InetAddress) allAddresses[0]) instanceof Inet4Address) && (((InetAddress) allAddresses[1]) instanceof Inet4Address)))) {
						fail(
								"Got unexpected exception when binding with setReuseAddress false on windows platform:"
										+ theAddress.toString()
										+ ":"
										+ ex.toString());
					}
				}
				theSocket.close();
				theSocket2.close();

				// try to bind to port that is allready in use with reuseAddress
				// = true
				theLocalAddress = new InetSocketAddress(
						(InetAddress) allAddresses[0], Support_PortManager
								.getNextPort());
				theOtherLocalAddress = new InetSocketAddress(
						(InetAddress) allAddresses[1], theLocalAddress
								.getPort());

				theSocket = new Socket();
				theSocket.setReuseAddress(true);
				theSocket.bind(theLocalAddress);
				try {
					theSocket2 = new Socket();
					theSocket2.setReuseAddress(true);
					theSocket2.bind(theOtherLocalAddress);
					theSocket2.close();
				} catch (IOException ex) {
					fail(
							"IOException when setReuseAddress is true and we bind :"
									+ ex.toString());
				}
				theSocket.close();
				serverSocket.close();

				// try with default behavior which should be the same on all
				// platforms
				theLocalAddress = new InetSocketAddress(
						(InetAddress) allAddresses[0], Support_PortManager
								.getNextPort());
				theOtherLocalAddress = new InetSocketAddress(
						(InetAddress) allAddresses[1], theLocalAddress
								.getPort());

				theSocket = new Socket();
				theSocket.bind(theLocalAddress);
				try {
					theSocket2 = new Socket();
					theSocket2.bind(theOtherLocalAddress);
					theSocket2.close();
					if ((!platform.startsWith("Linux"))
							&& ((!platform.startsWith("Windows")) || !((((InetAddress) allAddresses[0]) instanceof Inet4Address) && (((InetAddress) allAddresses[1]) instanceof Inet4Address)))) {
						fail(
								"No exception when setReuseAddress is default and we bind:"
										+ theLocalAddress.toString() + ":"
										+ theOtherLocalAddress.toString());
					}
				} catch (IOException ex) {
					if ((platform.startsWith("Linux"))
							|| ((platform.startsWith("Windows")) && ((((InetAddress) allAddresses[0]) instanceof Inet4Address) && (((InetAddress) allAddresses[1]) instanceof Inet4Address)))) {
						fail(
								"Got unexpected exception when binding with setReuseAddress default on windows platform:"
										+ theAddress.toString()
										+ ":"
										+ ex.toString());
					}
				}
				theSocket.close();
				serverSocket.close();

				ensureExceptionThrownIfOptionIsUnsupportedOnOS(SO_REUSEADDR);
			}
		} catch (Exception e) {
			handleException(e, SO_REUSEADDR);
		}

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
	 * @tests java.net.Socket#setOOBInline(boolean)
	 */
	public void test_setOOBInlineZ() {
		// mostly tested in getOOBInline. Just set to make sure call works ok
		try {
			new InetSocketAddress(InetAddress.getLocalHost(),
					Support_PortManager.getNextPort());
			Socket theSocket = new Socket();
			theSocket.setOOBInline(true);
			assertTrue("expected OOBIline to be true", theSocket.getOOBInline());
			ensureExceptionThrownIfOptionIsUnsupportedOnOS(SO_OOBINLINE);
		} catch (Exception e) {
			handleException(e, SO_OOBINLINE);
		}
	}

	/**
	 * @tests java.net.Socket#getOOBInline()
	 */
	public void test_getOOBInline() {

		try {
			new InetSocketAddress(InetAddress.getLocalHost(),
					Support_PortManager.getNextPort());
			Socket theSocket = new Socket();

			// validate that value reflects what we set it to true after true,
			// false after false and false after false false
			theSocket.setOOBInline(true);
			assertTrue("expected OOBIline to be true", theSocket.getOOBInline());
			theSocket.setOOBInline(false);
			assertFalse("expected OOBIline to be true", theSocket
					.getOOBInline());
			theSocket.setOOBInline(false);
			assertFalse("expected OOBIline to be true", theSocket
					.getOOBInline());
			ensureExceptionThrownIfOptionIsUnsupportedOnOS(SO_OOBINLINE);

		} catch (Exception e) {
			handleException(e, SO_OOBINLINE);
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

			new InetSocketAddress(InetAddress.getLocalHost(),
					Support_PortManager.getNextPort());
			Socket theSocket = new Socket();

			// validate that value set must be between 0 and 255
			try {
				theSocket.setTrafficClass(256);
				fail("No exception was thrown when traffic class set to 256");
			} catch (IllegalArgumentException e) {
			}

			try {
				theSocket.setTrafficClass(-1);
				fail("No exception was thrown when traffic class set to -1");
			} catch (IllegalArgumentException e) {
			}

			// now validate that we can set it to some good values
			theSocket.setTrafficClass(IPTOS_LOWCOST);
			theSocket.setTrafficClass(IPTOS_THROUGHPUT);
			ensureExceptionThrownIfOptionIsUnsupportedOnOS(IP_TOS);
		} catch (Exception e) {
			handleException(e, IP_TOS);
		}
	}

	/**
	 * @tests java.net.Socket#getTrafficClass()
	 */
	public void test_getTrafficClass() {
		try {
			int IPTOS_LOWCOST = 0x2;
			int IPTOS_RELIABILTY = 0x4;
			int IPTOS_THROUGHPUT = 0x8;
			int IPTOS_LOWDELAY = 0x10;

			new InetSocketAddress(InetAddress.getLocalHost(),
					Support_PortManager.getNextPort());
			Socket theSocket = new Socket();

			/*
			 * we cannot actually check that the values are set as if a platform
			 * does not support the option then it may come back unset even
			 * though we set it so just get the value to make sure we can get it
			 */
			int trafficClass = theSocket.getTrafficClass();
			ensureExceptionThrownIfOptionIsUnsupportedOnOS(IP_TOS);
		} catch (Exception e) {
			handleException(e, IP_TOS);
		}
	}

	/**
	 * @tests java.net.Socket#sendUrgentData(int)
	 */
	public void test_sendUrgentDataI() {

		// Some platforms may not support urgent data in this case we will not
		// run these tests. For now run on all platforms until we find those
		// that do not support urgent data
		String platform = System.getProperty("os.name");
		if (!platform.equals("Dummy")) {
			// validate that when OOBInline is false that any urgent data
			// is silently ignored
			String urgentData = "U";
			try {
				InetSocketAddress theAddress = new InetSocketAddress(
						InetAddress.getLocalHost(), Support_PortManager
								.getNextPort());
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
				assertTrue("Urgent Data seems to have been received:"
						+ receivedString + ":" + sendString, receivedString
						.equals(sendString + sendString));

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
						+ receivedString + ":" + sendString + urgentData
						+ sendString, receivedString.equals(sendString
						+ urgentData + sendString));

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
				assertTrue(
						"Did not get right byte of urgent data when two sent:"
								+ receivedString + ":" + sendString
								+ urgentData + urgentData + sendString,
						receivedString.equals(sendString + urgentData
								+ urgentData + sendString));

				theSocket.close();
				serverSocket.close();

				/*
				 * TODO : These do not currently pass on XP SP2 and Server 2003
				 */
				if (!platform.startsWith("Windows")) {
					// now test the case were we send turn the OOBInline on/off
					theAddress = new InetSocketAddress(InetAddress
							.getLocalHost(), Support_PortManager.getNextPort());
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
					assertTrue(
							"Did not get urgent data when turning on/off(1):"
									+ receivedString + ":" + sendString
									+ urgentData + sendString, receivedString
									.equals(sendString + urgentData
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
					assertTrue(
							"Got unexpected data data when turning on/off(2):"
									+ receivedString + ":" + sendString
									+ sendString, receivedString
									.equals(sendString + sendString));

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
					// So accept either  so long as we get the urgent data from
					// when it was on.
					assertTrue(
							"Did not get urgent data when turning on/off(3) GOT:"
									+ receivedString + ":Expected" + urgentData
									+ sendString + urgentData + sendString
									+ ":OR:" + sendString + urgentData
									+ sendString,
							(receivedString.equals(urgentData + sendString
									+ urgentData + sendString) || receivedString
									.equals(sendString + urgentData
											+ sendString)));

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

			} catch (Exception e) {
				// for platforms that do not support urgent data we expect an
				// exception. For the others report an error.
				// TODO : Need to introduce a better test for the exception
				// so that the failure only occurs on platforms that support
				// urgent data
				fail("Platform:" + platform
						+ ": Got exception during sendUrgent data tests"
						+ e.toString());
			}
		}
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
		} catch (Exception e) {
		}
		try {
			if (ss != null)
				ss.close();
		} catch (Exception e) {
		}
		try {
			if (t != null)
				t.interrupt();
		} catch (Exception e) {
		}
	}

	/**
	 * 
	 */
	protected int startServer(String name) {
		int portNumber = Support_PortManager.getNextPort();
		try {
			ss = new ServerSocket(portNumber, 5);
		} catch (IOException e) {
			fail(name + ": " + e);
		}
		return ss.getLocalPort();
	}
}
