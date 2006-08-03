/* Copyright 2006 The Apache Software Foundation or its licensors, as applicable
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

import java.net.InetSocketAddress;

import tests.util.SerializationTester;

import junit.framework.TestCase;

public class InetSocketAddressTest extends TestCase {

	private static final String SERIALIZATION_FILE_NAME = "serialization/java/net/InetSocketAddress.golden.1.ser";
    
    private static final String SERIALIZATION_FILE_NAME_UNRESOLVED = "serialization/java/net/InetSocketAddress.golden.2.ser";

    /**
     * @tests java.net.InetSocketAddress#InetSocketAddress(String, int)
     */
    public void test_ConstructorLjava_lang_StringI() throws Exception {
        // regression test for Harmony-1042
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 0);
        assertNotNull(address.getHostName());
    }
    
    /**
	 * @tests java.net.InetSocketAddress#createUnresolved(String, int)
	 */
	public void test_createUnresolvedLjava_lang_StringI() {
		HostPortPair[] legalHostPortPairs = { new HostPortPair("127.0.0.1", 1234),
				new HostPortPair("192.168.0.1", 10000), new HostPortPair("127.0.0", 0),
				new HostPortPair("127.0.0", 65535),
				new HostPortPair("strange host", 65535) };
		for (int i = 0; i < legalHostPortPairs.length; i++) {
			InetSocketAddress isa = InetSocketAddress.createUnresolved(
					legalHostPortPairs[i].host, legalHostPortPairs[i].port);
			assertTrue(isa.isUnresolved());
			assertNull(isa.getAddress());
			assertEquals(isa.getHostName(), legalHostPortPairs[i].host);
			assertEquals(isa.getPort(), legalHostPortPairs[i].port);
		}
	}

	/**
	 * @tests java.net.InetSocketAddress#createUnresolved(String, int)
	 */
	public void test_createUnresolvedLjava_lang_StringI_IllegalArgumentException() {
		HostPortPair[] illegalHostPortPairs = { new HostPortPair(null, 1),
				new HostPortPair("host", -1), new HostPortPair("host", 65536) };
		for (int i = 0; i < illegalHostPortPairs.length; i++) {
			try {
				InetSocketAddress.createUnresolved(
						illegalHostPortPairs[i].host,
						illegalHostPortPairs[i].port);
				fail("should throw IllegalArgumentException, host = "
						+ illegalHostPortPairs[i].host + ",port = "
						+ illegalHostPortPairs[i].port);
			} catch (IllegalArgumentException e) {
				// expected
			}
		}
	}

	/*
	 * inner class for createUnresolved test convenience.
	 */
	class HostPortPair {
		String host;

		int port;

		public HostPortPair(String host, int port) {
			this.host = host;
			this.port = port;
		}
	};
    
    /**
     * @tests serialization/deserialization.
     */
    public void test_serialization_unresolved() throws Exception {
        InetSocketAddress ia= InetSocketAddress.createUnresolved("badhost",1000);
        InetSocketAddress deIA = (InetSocketAddress) SerializationTester
                .getDeserilizedObject(ia);
        assertEquals(ia.getHostName(),deIA.getHostName());
        assertEquals(ia.getPort(), deIA.getPort());
        assertEquals(ia.getAddress(), deIA.getAddress());
    }
    
    /**
     * @tests serialization/deserialization.
     */
    public void test_serialization() throws Exception {
        InetSocketAddress ia= InetSocketAddress.createUnresolved("badhost",1000);
        InetSocketAddress deIA = (InetSocketAddress) SerializationTester
                .getDeserilizedObject(ia);
        assertEquals(ia.getHostName(),deIA.getHostName());
        assertEquals(ia.getPort(), deIA.getPort());
        assertEquals(ia.getAddress(), deIA.getAddress());
    }

    /**
     * @tests serialization/deserialization.
     */
    public void test_serializationCompatibility_unresolved() throws Exception {
        InetSocketAddress ia= InetSocketAddress.createUnresolved("badhost",1000);
        InetSocketAddress deIA = (InetSocketAddress) SerializationTester
                .readObject(ia,
                        SERIALIZATION_FILE_NAME_UNRESOLVED);
        assertEquals(ia.getHostName(),deIA.getHostName());
        assertEquals(ia.getPort(), deIA.getPort());
        assertEquals(ia.getAddress(), deIA.getAddress());
    }
    
    /**
     * @tests serialization/deserialization.
     */
    public void test_serializationCompatibility() throws Exception {
        InetSocketAddress ia= new InetSocketAddress("Localhost",1000);
        InetSocketAddress deIA = (InetSocketAddress) SerializationTester
                .readObject(ia,
                        SERIALIZATION_FILE_NAME);
        assertEquals(ia.getAddress(), deIA.getAddress());
        assertEquals(ia.getPort(), deIA.getPort());
        assertEquals(ia.getHostName().toLowerCase(),deIA.getHostName().toLowerCase());
    }
}
