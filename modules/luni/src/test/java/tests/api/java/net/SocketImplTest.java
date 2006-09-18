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

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketImpl;

import tests.support.Support_Configuration;

public class SocketImplTest extends junit.framework.TestCase {

	/**
	 * @tests java.net.SocketImpl#SocketImpl()
	 */
	public void test_Constructor() {
		try {
			try {
				System.setProperty("socksProxyHost",
						Support_Configuration.SocksServerTestHost);
				System.setProperty("socksProxyPort", String
						.valueOf(Support_Configuration.SocksServerTestPort));
				Socket s = new Socket(Support_Configuration.HomeAddress, 80);
				OutputStream os = s.getOutputStream();
				os.write("GET / HTTP/1.0\r\n\r\n".getBytes());
				s.getInputStream();
			} catch (IOException e) {
				fail("Could not open socket: "
						+ Support_Configuration.HomeAddress + " " + e);
			}
			boolean exception = false;
			try {
				System.setProperty("socksProxyHost",
						Support_Configuration.SocksServerTestHost);
				System
						.setProperty(
								"socksProxyPort",
								String
										.valueOf(Support_Configuration.SocksServerTestPort + 1));
				Socket s = new Socket(Support_Configuration.HomeAddress, 80);
				OutputStream os = s.getOutputStream();
				os.write("GET / HTTP/1.0\r\n\r\n".getBytes());
				s.getInputStream();
			} catch (IOException e) {
				exception = true;
			}
			assertTrue("Exception should have been thrown", exception);
		} finally {
			System.setProperties(null);
		}
	}
	
    public void test_Constructor_fd() throws Exception {
        // regression test for Harmony-1117
        MockSocketImpl mockSocketImpl = new MockSocketImpl();
        assertNull(mockSocketImpl.getFileDescriptor());
    }
    
	/*
	* @tests java.net.SocketImpl#setPerformancePreference()
	*/
	public void test_setPerformancePreference_Int_Int_Int() throws Exception {
		MockSocketImpl theSocket = new MockSocketImpl();
		theSocket.setPerformancePreference(1,1,1);
	}
	
	
	// the mock class for test, leave all method empty
	class MockSocketImpl extends SocketImpl{
		
		protected void accept(SocketImpl newSocket) throws IOException {
		}

		protected int available() throws IOException {
			return 0;
		}

		protected void bind(InetAddress address, int port) throws IOException {
		}

		protected void close() throws IOException {
		}

		protected void connect(String host, int port) throws IOException {
		}

		protected void connect(InetAddress address, int port) throws IOException {
		}

		protected void create(boolean isStreaming) throws IOException {
		}

		protected InputStream getInputStream() throws IOException {
			return null;
		}

		public Object getOption(int optID) throws SocketException {
			return null;
		}

		protected OutputStream getOutputStream() throws IOException {
			return null;
		}

		protected void listen(int backlog) throws IOException {
		}

		public void setOption(int optID, Object val) throws SocketException {
		}

		protected void connect(SocketAddress remoteAddr, int timeout) throws IOException {
		}

		protected void sendUrgentData(int value) throws IOException {
		}
		
		public void setPerformancePreference(int connectionTime,
                int latency,
                int bandwidth){
			super.setPerformancePreferences(connectionTime, latency, bandwidth);
		}

        public FileDescriptor getFileDescriptor() {
            return super.getFileDescriptor();
        }
	}

	protected void setUp() {
	}

	protected void tearDown() {
	}

	protected void doneSuite() {
	}
}
