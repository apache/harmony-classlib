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

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * TODO: Cannot test NetworkInterface.isPointToPoint(), isUp() and
 * supportsMulticast() in current environment.
 * 
 */
public class NetworkInterfaceTest extends junit.framework.TestCase {

	// private member variables used for tests	
	Enumeration<NetworkInterface> theInterfaces = null;
	
	// security manager that allows us to check that we only return the
	// addresses that we should
	private static class CustomizedSecurityManager extends SecurityManager {

		ArrayList<String> disallowedNames = new ArrayList<String>();

		@SuppressWarnings("unused")
		public CustomizedSecurityManager(ArrayList<InetAddress> addresses) {
			for (int i = 0; i < addresses.size(); i++) {
				disallowedNames.add(addresses.get(i).getHostName());
				disallowedNames.add(addresses.get(i).getHostAddress());
			}
		}

		@Override
		public void checkConnect(String host, int port) {
			if (host == null) {
				throw new NullPointerException();
			}
			
			if(disallowedNames.contains(host)) {
				throw new SecurityException();				
			}
		}
	}
	
	private class MockSecurityManager extends SecurityManager {
		@Override
		public void checkConnect(String host, int port) {
			throw new SecurityException();
		}
	}
	
	/**
	 * @tests java.net.NetworkInterface#getName()
	 */
	public void test_getName() throws SocketException{
		if (theInterfaces != null) {
			while (theInterfaces.hasMoreElements()) {
				NetworkInterface netif = theInterfaces.nextElement();
				assertNotNull(netif.getName());
				NetworkInterface anotherNetIf = NetworkInterface.getByName(netif.getName());				
				assertEquals(netif.getName(), anotherNetIf.getName());
			}
		}
	}

	/**
	 * @tests java.net.NetworkInterface#getInetAddresses()
	 */
	public void test_getInetAddresses() throws SocketException {		
		if (theInterfaces != null) {
			// create the list of allowed and disallowed ok addresses to return
			ArrayList<InetAddress> allowedAddrs = new ArrayList<InetAddress>();
			ArrayList<InetAddress> disallowedAddrs = new ArrayList<InetAddress>();
			while (theInterfaces.hasMoreElements()) {
				NetworkInterface netif = theInterfaces.nextElement();
				Enumeration<InetAddress> addrs = netif.getInetAddresses();
				int index = 0;				
				while (addrs != null && addrs.hasMoreElements()) {
					InetAddress addr = addrs.nextElement();
					assertNotNull(addr);
					if (index == 0) {
						allowedAddrs.add(addr);
					} else {
						disallowedAddrs.add(addr);
					}
					index++;
				}
			}

			// set the security manager that will make the first address
			// invisible
			SecurityManager oldSM = System.getSecurityManager();
			System.setSecurityManager(new CustomizedSecurityManager(
					disallowedAddrs));
			
			theInterfaces = NetworkInterface.getNetworkInterfaces();
			while (theInterfaces != null && theInterfaces.hasMoreElements()) {
				NetworkInterface netif = theInterfaces.nextElement();
				Enumeration<InetAddress> addrs = netif.getInetAddresses();
					while (addrs != null && addrs.hasMoreElements()) {
						InetAddress addr = addrs.nextElement();
						assertTrue(allowedAddrs.contains(addr));
						assertFalse(disallowedAddrs.contains(addr));					
				}
			}

			// validate that we can get the interface by specifying the address.
			// This is to be compatible
			for (InetAddress addr : disallowedAddrs) {				
				assertNotNull(NetworkInterface.getByInetAddress(addr));
			}

			// validate that we can get the network interface for the good
			// addresses			
			for (InetAddress addr : allowedAddrs) {
				assertNotNull(NetworkInterface.getByInetAddress(addr));				
			}
			
			System.setSecurityManager(oldSM);
		}
	}

	/** 
	 * @tests java.net.NetworkInterface#getDisplayName()
	 */
	public void test_getDisplayName() throws SocketException {
		if (theInterfaces != null) {
			while (theInterfaces.hasMoreElements()) {
				NetworkInterface netif = theInterfaces.nextElement();
				NetworkInterface anotherNetIf = NetworkInterface.getByName(netif.getName());
				assertNotNull(netif.getDisplayName());
				assertEquals(netif.getDisplayName(), anotherNetIf.getDisplayName());
			}
		}
	}

	/**
	 * @tests java.net.NetworkInterface#getByName(java.lang.String)
	 */
	public void test_getByNameLjava_lang_String() throws Exception {
		try {
			assertNull(NetworkInterface.getByName(null));
			fail("should throw NullPointerException");
		} catch (NullPointerException e) {
			// expected
		}
		
		assertNull(NetworkInterface.getByName("8not a name4"));
		
		if (theInterfaces != null) {
			while (theInterfaces.hasMoreElements()) {
				NetworkInterface netif = theInterfaces.nextElement();
				String name = netif.getName();
				if (name != null) {
					assertTrue(NetworkInterface.getByName(name).equals(netif));
				}
			}
		}
	}

	/**
	 * @tests java.net.NetworkInterface#getByInetAddress(java.net.InetAddress)
	 */
	public void test_getByInetAddressLjava_net_InetAddress() throws Exception {
		try {
			assertNull(NetworkInterface.getByInetAddress(null));
			fail("should throw NullPointerException");
		} catch (NullPointerException e) {
			// expected
		}
		
		byte addressBytes[] = new byte[4];
		for (int i = 0; i < addressBytes.length; i++) {
			addressBytes[i] = 0;
		}
		assertNull(NetworkInterface.getByInetAddress(InetAddress
				.getByAddress(addressBytes)));
		
		// for each address in an interface validate that we get the right
		// interface for that address
		if (theInterfaces != null) {
			while (theInterfaces.hasMoreElements()) {
				NetworkInterface netif = theInterfaces.nextElement();
				Enumeration<InetAddress> addresses = netif.getInetAddresses();
				while (addresses.hasMoreElements()) {
					InetAddress addr = addresses.nextElement();
					assertTrue(NetworkInterface.getByInetAddress(addr).equals(netif));					
				}
			}
		}
	}

	/**
	 * 
	 * @tests java.net.NetworkInterface#getNetworkInterfaces()
	 */
	public void test_getNetworkInterfaces() throws SocketException {
		if (theInterfaces != null) {
			Enumeration<NetworkInterface> anotherInterfaces = NetworkInterface
					.getNetworkInterfaces();
			while (theInterfaces.hasMoreElements()) {
				NetworkInterface netIf = theInterfaces.nextElement(); 
				NetworkInterface anotherNetIf = anotherInterfaces.nextElement(); 
				assertEquals(anotherNetIf, netIf);
				assertNotSame(anotherNetIf, netIf);
			}
		}
	}

	/** 
	 * @tests java.net.NetworkInterface#equals(java.lang.Object)
	 */
	public void test_equalsLjava_lang_Object() throws SocketException {
		if (theInterfaces != null) {
			while (theInterfaces.hasMoreElements()) {
				NetworkInterface netif = theInterfaces.nextElement();
				Enumeration<InetAddress> addresses = netif.getInetAddresses();
				if (addresses != null && addresses.hasMoreElements()) {
					NetworkInterface sameAsNetworkInterface = NetworkInterface
							.getByInetAddress(addresses.nextElement());
					assertEquals(netif.getName(), sameAsNetworkInterface.getName());
					assertEquals(netif, sameAsNetworkInterface);
				}
				assertFalse(netif.equals(null));
			}
		}
	}

	/**
	 * @tests java.net.NetworkInterface#hashCode()
	 */
	public void test_hashCode() throws SocketException {
		if (theInterfaces != null) {
			while (theInterfaces.hasMoreElements()) {
				NetworkInterface netif = theInterfaces.nextElement();
				Enumeration<InetAddress> addresses = netif
						.getInetAddresses();
				if (addresses != null && addresses.hasMoreElements()) {
					NetworkInterface sameAsNetworkInterface = NetworkInterface
							.getByInetAddress(addresses.nextElement());
					assertEquals(netif.hashCode(), sameAsNetworkInterface.hashCode());
				}
			}
		}
	}

	/**
	 * @tests java.net.NetworkInterface#toString()
	 */
	public void test_toString() {
		if (theInterfaces != null) {
			while (theInterfaces.hasMoreElements()) {
				NetworkInterface netif = theInterfaces.nextElement();
				assertNotNull("validate that non null string is generated",
						netif.toString());
				assertTrue(netif.toString().contains(netif.getName()));
			}
		}
	}
			
	/**
	 * 
	 * @tests java.net.NetworkInterface#getInterfaceAddresses()
	 * 
	 * @since 1.6
	 */
	public void test_getInterfaceAddresses() throws SocketException {
		if (theInterfaces != null) {
			SecurityManager oldSM = System.getSecurityManager();
			System.setSecurityManager(new MockSecurityManager());
			
			while (theInterfaces.hasMoreElements()) {
				NetworkInterface netif = theInterfaces.nextElement();
				assertEquals(netif.getName()
						+ " getInterfaceAddresses should contain no element", 0,
						netif.getInterfaceAddresses().size());
			}
			System.setSecurityManager(oldSM);
			
			theInterfaces = NetworkInterface.getNetworkInterfaces();
			while (theInterfaces.hasMoreElements()) {
				NetworkInterface netif = theInterfaces.nextElement();
				List<InterfaceAddress> interfaceAddrs = netif.getInterfaceAddresses();
				assertTrue(interfaceAddrs instanceof ArrayList);
				for (InterfaceAddress addr : interfaceAddrs) {
					assertNotNull(addr);					
				}
				
				List<InterfaceAddress> interfaceAddrs2 = netif.getInterfaceAddresses();
				// RI fails on this since it cannot tolerate null broadcast address. 
				assertEquals(interfaceAddrs, interfaceAddrs2);				
			}
		}
	}	
	
	/**
	 * @tests java.net.NetworkInterface#isLoopback()
	 * 
	 * @since 1.6
	 */
	public void test_isLoopback() throws SocketException {	
		if (theInterfaces != null) {
			while (theInterfaces.hasMoreElements()) {
				NetworkInterface netif = theInterfaces.nextElement();
				boolean loopback = false;
				Enumeration<InetAddress> addrs = netif.getInetAddresses();
				while(addrs != null && addrs.hasMoreElements()){
					if(addrs.nextElement().isLoopbackAddress()){
						loopback = true;
						break;
					}
				}
				assertEquals(loopback, netif.isLoopback());
			}
		}
	}
	
	/**
	 * @tests java.net.NetworkInterface#getHardwareAddress()
	 * 
	 * @since 1.6
	 */
	public void test_getHardwareAddress() throws SocketException {
		if (theInterfaces != null) {
			while (theInterfaces.hasMoreElements()) {
				NetworkInterface netif = theInterfaces.nextElement();
				byte[] hwAddr = netif.getHardwareAddress();
				if (netif.isLoopback()) {
					assertTrue(hwAddr == null || hwAddr.length == 0);
				} else {
					assertTrue(hwAddr.length >= 0);
				}
			}
		}
	}
	
	/**
	 * 
	 * @tests java.net.NetworkInterface#getHardwareAddress()
	 * 
	 * @since 1.6
	 */
	public void test_getMTU() throws SocketException {		
		if (theInterfaces != null) {
			while (theInterfaces.hasMoreElements()) {
				NetworkInterface netif = theInterfaces.nextElement();
				assertTrue(netif.getName() + "has non-positive MTU", netif.getMTU() >= 0);
			}			
		}
	}
		
	@Override
	protected void setUp() throws Exception{
		super.setUp();		
		theInterfaces = NetworkInterface.getNetworkInterfaces();
	}

	@Override
	protected void tearDown() throws Exception {
		theInterfaces = null;
		super.tearDown();
	}
}
