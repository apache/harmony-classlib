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

import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;

import tests.support.Support_PortManager;

public class ConnectExceptionTest extends junit.framework.TestCase {

	/**
	 * @tests java.net.ConnectException#ConnectException()
	 */
	public void test_Constructor() {
		// Test for method java.net.ConnectException()

		try {
			int portNumber = Support_PortManager.getNextPort();
			new Socket(InetAddress.getLocalHost().getHostName(), portNumber);
		} catch (ConnectException e) {
			return;
		} catch (Exception e) {
			fail("Exception during Constructor test : " + e.getMessage());
		}
		fail("Failed to generate exception");
	}

	/**
	 * @tests java.net.ConnectException#ConnectException(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		// Test for method java.net.ConnectException(java.lang.String)
		try {
			int portNumber = Support_PortManager.getNextPort();
			new Socket(InetAddress.getLocalHost().getHostName(), portNumber);
		} catch (ConnectException e) {
			return;
		} catch (Exception e) {
			fail("Exception during Constructor test : " + e.getMessage());
		}
		fail("Failed to generate exception");
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
	}
}
