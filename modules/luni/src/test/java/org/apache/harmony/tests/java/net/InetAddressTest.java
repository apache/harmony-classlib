/* Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.harmony.tests.java.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

import junit.framework.TestCase;

public class InetAddressTest extends TestCase {

	/**
	 * @tests java.net.InetAddress#getAllByName(java.lang.String)
	 */
	public void test_getAllByNameLjava_lang_String()
			throws UnknownHostException {
		// Regression for HARMONY-56
		InetAddress[] ia = InetAddress.getAllByName(null);
		assertEquals("Assert 0: No loopback address", 1, ia.length);
		assertTrue("Assert 1: getAllByName(null) not loopback",
				ia[0].isLoopbackAddress());
	}

	/**
	 * @tests java.net.InetAddress#getByAddress(byte[])
	 */
	public void test_getByAddress() {
		// Regression for HARMONY-61
		try {
			InetAddress.getByAddress(null);
			fail("Assert 0: UnknownHostException must be thrown");
		} catch (UnknownHostException e) {
			// Expected
		}
	}
	
	/**
	 * @tests java.net.InetAddress#toString()
	 */
	public void test_toString() throws UnknownHostException {
		// Regression for HARMONY-84
        InetAddress addr = InetAddress.getByName("localhost");
        assertEquals("Assert 0: wrong string from name", "localhost/127.0.0.1", addr.toString());
        InetAddress addr2 = InetAddress.getByAddress(new byte[]{127, 0, 0, 1});
        assertEquals("Assert 1: wrong string from address", "/127.0.0.1", addr2.toString());
	}
}
