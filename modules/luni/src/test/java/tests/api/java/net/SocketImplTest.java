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
import java.io.OutputStream;
import java.net.Socket;

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

	protected void setUp() {
	}

	protected void tearDown() {
	}

	protected void doneSuite() {
	}
}
