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

package tests.api.java.net;

import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.Permission;

import tests.support.Support_Configuration;
import tests.util.SerializationTester;

public class InetAddressTest extends junit.framework.TestCase {
    
    private static final String SERIALIZATION_FILE_NAME = "serialization/java/net/InetAddressTest.golden.ser";

	private static boolean someoneDone[] = new boolean[2];

	protected static boolean threadedTestSucceeded;

	protected static String threadedTestErrorString;

	/**
	 * This class is used to test inet_ntoa, gethostbyaddr and gethostbyname
	 * functions in the VM to make sure they're threadsafe. getByName will cause
	 * the gethostbyname function to be called. getHostName will cause the
	 * gethostbyaddr to be called. getHostAddress will cause inet_ntoa to be
	 * called.
	 */
	static class threadsafeTestThread extends Thread {
		private String lookupName;

		private InetAddress testAddress;

		private int testType;

		/*
		 * REP_NUM can be adjusted if desired. Since this error is
		 * non-deterministic it may not always occur. Setting REP_NUM higher,
		 * increases the chances of an error being detected, but causes the test
		 * to take longer. Because the Java threads spend a lot of time
		 * performing operations other than running the native code that may not
		 * be threadsafe, it is quite likely that several thousand iterations
		 * will elapse before the first error is detected.
		 */
		private static final int REP_NUM = 20000;

		public threadsafeTestThread(String name, String lookupName,
				InetAddress testAddress, int type) {
			super(name);
			this.lookupName = lookupName;
			this.testAddress = testAddress;
			testType = type;
		}

		public void run() {
			try {
				String correctName = testAddress.getHostName();
				String correctAddress = testAddress.getHostAddress();
				long startTime = System.currentTimeMillis();

				synchronized (someoneDone) {
				}

				for (int i = 0; i < REP_NUM; i++) {
					if (someoneDone[testType]) {
						break;
					} else if ((i % 25) == 0
							&& System.currentTimeMillis() - startTime > 240000) {
						System.out
								.println("Exiting due to time limitation after "
										+ i + " iterations");
						break;
					}

					InetAddress ia = InetAddress.getByName(lookupName);
					String hostName = ia.getHostName();
					String hostAddress = ia.getHostAddress();

                    // Intentionally not looking for exact name match so that 
                    // the test works across different platforms that may or 
                    // may not include a domain suffix on the hostname
                    if (!hostName.startsWith(correctName)) {
						threadedTestSucceeded = false;
						threadedTestErrorString = (testType == 0 ? "gethostbyname"
								: "gethostbyaddr")
								+ ": getHostName() returned "
								+ hostName
								+ " instead of " + correctName;
						break;
					}
                    // IP addresses should match exactly
					if (!correctAddress.equals(hostAddress)) {
						threadedTestSucceeded = false;
						threadedTestErrorString = (testType == 0 ? "gethostbyname"
								: "gethostbyaddr")
								+ ": getHostName() returned "
								+ hostAddress
								+ " instead of " + correctAddress;
						break;
					}

				}
				someoneDone[testType] = true;
			} catch (Exception e) {
				threadedTestSucceeded = false;
				threadedTestErrorString = e.toString();
			}
		}
	}

	/**
	 * @tests java.net.InetAddress#equals(java.lang.Object)
	 */
	public void test_equalsLjava_lang_Object() {
		// Test for method boolean java.net.InetAddress.equals(java.lang.Object)
		try {
			InetAddress ia1 = InetAddress
					.getByName(Support_Configuration.InetTestAddress);
			InetAddress ia2 = InetAddress
					.getByName(Support_Configuration.InetTestIP);
			assertTrue("Equals returned incorrect result - " + ia1 + " != "
					+ ia2, ia1.equals(ia2));
		} catch (Exception e) {
			fail("Exception during equals test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.net.InetAddress#getAddress()
	 */
	public void test_getAddress() {
		// Test for method byte [] java.net.InetAddress.getAddress()
		try {
			InetAddress ia = InetAddress
					.getByName(Support_Configuration.InetTestIP);
			byte[] caddr = Support_Configuration.InetTestCaddr;
			byte[] addr = ia.getAddress();
			for (int i = 0; i < addr.length; i++)
				assertTrue("Incorrect address returned", caddr[i] == addr[i]);
		} catch (java.net.UnknownHostException e) {
		}
	}

	/**
	 * @tests java.net.InetAddress#getAllByName(java.lang.String)
	 */
	public void test_getAllByNameLjava_lang_String() throws Exception {
		// Test for method java.net.InetAddress []
        // java.net.InetAddress.getAllByName(java.lang.String)
        InetAddress[] all = InetAddress
                .getAllByName(Support_Configuration.SpecialInetTestAddress);
        assertNotNull(all);
        // Number of aliases depends on individual test machine
        assertTrue(all.length >= 1);
        for (InetAddress alias : all) {
            // Check that each alias has the same hostname. Intentionally not
            // checking for exact string match.
            assertTrue(alias.getHostName().startsWith(
                    Support_Configuration.SpecialInetTestAddress));
        }// end for all aliases
        
        // check the getByName if there is a security manager.
        SecurityManager oldman = System.getSecurityManager();
        System.setSecurityManager(new MockSecurityManager());
        try {
            boolean exception = false;
            try {
                InetAddress.getByName("3d.com");
            } catch (SecurityException ex) {
                exception = true;
            } catch (Exception ex) {
                fail("getByName threw wrong exception : " + ex.getMessage());
            }
            assertTrue("expected SecurityException", exception);
        } finally {
            System.setSecurityManager(oldman);
        }
	}

	/**
	 * @tests java.net.InetAddress#getByName(java.lang.String)
	 */
	public void test_getByNameLjava_lang_String() throws Exception {
        // Test for method java.net.InetAddress
        // java.net.InetAddress.getByName(java.lang.String)
        InetAddress ia2 = InetAddress
                .getByName(Support_Configuration.InetTestIP);
        
        // Intentionally not testing for exact string match
        /* FIXME: comment the assertion below because it is platform/configuration dependent
         * Please refer to HARMONY-1664 (https://issues.apache.org/jira/browse/HARMONY-1664)
         * for details
         */
//        assertTrue(
//		"Expected " + Support_Configuration.InetTestAddress + "*",
//	        ia2.getHostName().startsWith(Support_Configuration.InetTestAddress));

        // TODO : Test to ensure all the address formats are recognized
    }

	/**
	 * @tests java.net.InetAddress#getHostAddress()
	 */
	public void test_getHostAddress() {
		// Test for method java.lang.String
		// java.net.InetAddress.getHostAddress()
		try {
			InetAddress ia2 = InetAddress
					.getByName(Support_Configuration.InetTestAddress);
			assertTrue("getHostAddress returned incorrect result: "
					+ ia2.getHostAddress() + " != "
					+ Support_Configuration.InetTestIP, ia2.getHostAddress()
					.equals(Support_Configuration.InetTestIP));
		} catch (Exception e) {
			fail("Exception during getHostAddress test : " + e.getMessage());
		}
	}

    /**
     * @tests java.net.InetAddress#getHostName()
     */
    public void test_getHostName() throws Exception {
        // Test for method java.lang.String java.net.InetAddress.getHostName()
        InetAddress ia = InetAddress
                .getByName(Support_Configuration.InetTestIP);
        
        // Intentionally not testing for exact string match
        /* FIXME: comment the assertion below because it is platform/configuration dependent
         * Please refer to HARMONY-1664 (https://issues.apache.org/jira/browse/HARMONY-1664)
         * for details
         */
//        assertTrue(
//		"Expected " + Support_Configuration.InetTestAddress + "*",
//		ia.getHostName().startsWith(Support_Configuration.InetTestAddress));

        // Test for any of the host lookups, where the default SecurityManager
        // is installed.

        SecurityManager oldman = System.getSecurityManager();
        try {
            String exp = Support_Configuration.InetTestIP;
            System.setSecurityManager(new MockSecurityManager());
            ia = InetAddress.getByName(exp);
            String ans = ia.getHostName();
        /* FIXME: comment the assertion below because it is platform/configuration dependent
         * Please refer to HARMONY-1664 (https://issues.apache.org/jira/browse/HARMONY-1664)
         * for details
         */
        //    assertEquals(Support_Configuration.InetTestIP, ans);
        } finally {
            System.setSecurityManager(oldman);
        }

        // Make sure there is no caching
        String originalPropertyValue = System
                .getProperty("networkaddress.cache.ttl");
        System.setProperty("networkaddress.cache.ttl", "0");

        // Test for threadsafety
        try {
            InetAddress lookup1 = InetAddress
                    .getByName(Support_Configuration.InetTestAddress);
            assertTrue(lookup1 + " expected "
                    + Support_Configuration.InetTestIP,
                    Support_Configuration.InetTestIP.equals(lookup1
                            .getHostAddress()));
            InetAddress lookup2 = InetAddress
                    .getByName(Support_Configuration.InetTestAddress2);
            assertTrue(lookup2 + " expected "
                    + Support_Configuration.InetTestIP2,
                    Support_Configuration.InetTestIP2.equals(lookup2
                            .getHostAddress()));
            threadsafeTestThread thread1 = new threadsafeTestThread("1",
                    lookup1.getHostName(), lookup1, 0);
            threadsafeTestThread thread2 = new threadsafeTestThread("2",
                    lookup2.getHostName(), lookup2, 0);
            threadsafeTestThread thread3 = new threadsafeTestThread("3",
                    lookup1.getHostAddress(), lookup1, 1);
            threadsafeTestThread thread4 = new threadsafeTestThread("4",
                    lookup2.getHostAddress(), lookup2, 1);

            // initialize the flags
            threadedTestSucceeded = true;
            synchronized (someoneDone) {
                thread1.start();
                thread2.start();
                thread3.start();
                thread4.start();
            }
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            /* FIXME: comment the assertion below because it is platform/configuration dependent
             * Please refer to HARMONY-1664 (https://issues.apache.org/jira/browse/HARMONY-1664)
             * for details
             */
//            assertTrue(threadedTestErrorString, threadedTestSucceeded);
        } finally {
            // restore the old value of the property
            if (originalPropertyValue == null)
                // setting the property to -1 has the same effect as having the
                // property be null
                System.setProperty("networkaddress.cache.ttl", "-1");
            else
                System.setProperty("networkaddress.cache.ttl",
                        originalPropertyValue);
        }
    }

	/**
	 * @tests java.net.InetAddress#getLocalHost()
	 */
	public void test_getLocalHost() {
		// Test for method java.net.InetAddress
		// java.net.InetAddress.getLocalHost()
		try {
			// We don't know the host name or ip of the machine
			// running the test, so we can't build our own address
			DatagramSocket dg = new DatagramSocket(0, InetAddress
					.getLocalHost());
			assertTrue("Incorrect host returned", InetAddress.getLocalHost()
					.equals(dg.getLocalAddress()));
			dg.close();
		} catch (Exception e) {
			fail("Exception during getLocalHost test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.net.InetAddress#hashCode()
	 */
	public void test_hashCode() {
		// Test for method int java.net.InetAddress.hashCode()
		try {
			InetAddress host = InetAddress
					.getByName(Support_Configuration.InetTestAddress);
			int hashcode = host.hashCode();
			assertTrue("Incorrect hash returned: " + hashcode + " from host: "
					+ host, hashcode == Support_Configuration.InetTestHashcode);
		} catch (java.net.UnknownHostException e) {
			fail("Exception during test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.net.InetAddress#isMulticastAddress()
	 */
	public void test_isMulticastAddress() {
		// Test for method boolean java.net.InetAddress.isMulticastAddress()
		try {
			InetAddress ia2 = InetAddress.getByName("239.255.255.255");
			assertTrue("isMulticastAddress returned incorrect result", ia2
					.isMulticastAddress());
		} catch (Exception e) {
			fail("Exception during isMulticastAddress test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.net.InetAddress#toString()
	 */
	public void test_toString() throws Exception {
        // Test for method java.lang.String java.net.InetAddress.toString()
        InetAddress ia2 = InetAddress
                .getByName(Support_Configuration.InetTestIP);
        assertEquals("/" + Support_Configuration.InetTestIP, ia2.toString());
    }

	/**
	 * @tests java.net.InetAddress#getByAddress(java.lang.String, byte[])
	 */
	public void test_getByAddressLjava_lang_String$B() {
		// Check an IPv4 address with an IPv6 hostname
		byte ipAddress[] = { 127, 0, 0, 1 };
		String addressStr = "::1";
		try {
			InetAddress addr = InetAddress.getByAddress(addressStr, ipAddress);
			addr = InetAddress.getByAddress(ipAddress);
		} catch (UnknownHostException e) {
			fail("Unexpected problem creating IP Address "
					+ ipAddress.length);
		}

		byte ipAddress2[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, 127, 0, 0,
				1 };
		addressStr = "::1";
		try {
			InetAddress addr = InetAddress.getByAddress(addressStr, ipAddress2);
			addr = InetAddress.getByAddress(ipAddress);
		} catch (UnknownHostException e) {
			fail("Unexpected problem creating IP Address "
					+ ipAddress.length);
		}
	}

	/**
	 * @tests java.net.InetAddress#getCanonicalHostName()
	 */
	public void test_getCanonicalHostName() throws Exception {
        InetAddress theAddress = null;
        theAddress = InetAddress.getLocalHost();
        assertTrue("getCanonicalHostName returned a zero length string ",
                theAddress.getCanonicalHostName().length() != 0);
        assertTrue("getCanonicalHostName returned an empty string ",
                !theAddress.equals(""));

        // test against an expected value
        InetAddress ia = InetAddress
                .getByName(Support_Configuration.InetTestIP);
        
        // Intentionally not testing for exact string match
        /* FIXME: comment the assertion below because it is platform/configuration dependent
         * Please refer to HARMONY-1664 (https://issues.apache.org/jira/browse/HARMONY-1664)
         * for details
         */
//        assertTrue(
//           "Expected " + Support_Configuration.InetTestAddress + "*", 
//           ia.getCanonicalHostName().startsWith(Support_Configuration.InetTestAddress));
    }
	
	/**
     * @tests java.net.InetAddress#isReachableI
     */
    public void test_isReachableI() throws Exception {
        InetAddress ia = Inet4Address.getByName("127.0.0.1");
        assertTrue(ia.isReachable(10000));
        ia = Inet4Address.getByName("127.0.0.1");
        try {
            ia.isReachable(-1);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // correct
        }
    }

    /**
     * @tests java.net.InetAddress#isReachableLjava_net_NetworkInterfaceII
     */
    public void test_isReachableLjava_net_NetworkInterfaceII() throws Exception {
        // tests local address
        InetAddress ia = Inet4Address.getByName("127.0.0.1");
        assertTrue(ia.isReachable(null, 0, 10000));
        ia = Inet4Address.getByName("127.0.0.1");
        try {
            ia.isReachable(null, -1, 10000);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // correct
        }
        try {
            ia.isReachable(null, 0, -1);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // correct
        }
        try {
            ia.isReachable(null, -1, -1);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // correct
        }
        // tests nowhere
        ia = Inet4Address.getByName("1.1.1.1");
        assertFalse(ia.isReachable(1000));
        assertFalse(ia.isReachable(null, 0, 1000));
    } 

    /*
     * Test serialization/deserilazation.
     */
    public void testSerialization() throws Exception {
        InetAddress ia= InetAddress.getByName("localhost");
        InetAddress deIA = (InetAddress) SerializationTester
                .getDeserilizedObject(ia);
        byte[] iaAddresss= ia.getAddress();
        byte[] deIAAddresss= deIA.getAddress();
        for (int i = 0; i < iaAddresss.length; i++) {
            assertEquals(iaAddresss[i], deIAAddresss[i]);
        }        
        assertEquals(ia.getHostName(), deIA.getHostName());
    }

    /*
     * Test serialization/deserilazation compatibility with RI.
     */
    public void testSerializationCompatibility() throws Exception {
        InetAddress ia= InetAddress.getByName("localhost");
        // the ser file was serialized by InetAddress.getByName("localhost");
        InetAddress deIA = (InetAddress) SerializationTester
                .readObject(ia,
                        SERIALIZATION_FILE_NAME);
        byte[] iaAddresss= ia.getAddress();
        byte[] deIAAddresss= deIA.getAddress();
        for (int i = 0; i < iaAddresss.length; i++) {
            assertEquals(iaAddresss[i], deIAAddresss[i]);
        } 
        assertEquals(ia.getHostName(), deIA.getHostName());
    }
    
    
    class MockSecurityManager extends SecurityManager {        
        public void checkPermission(Permission permission) {
            if (permission.getName().equals("setSecurityManager")){
                return;
            }
            if (permission.getName().equals("3d.com")){
                throw new SecurityException();
            }
            super.checkPermission(permission);
        }
    }
}
