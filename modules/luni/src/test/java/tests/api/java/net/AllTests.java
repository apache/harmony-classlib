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

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Test suite for java.net package.
 */
public class AllTests {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}

	public static Test suite() {
		TestSuite suite = new TestSuite("Tests for java.net");
		// $JUnit-BEGIN$
		suite.addTestSuite(BindExceptionTest.class);
		suite.addTestSuite(ConnectExceptionTest.class);
		suite.addTestSuite(DatagramPacketTest.class);
		suite.addTestSuite(DatagramSocketTest.class);
		suite.addTestSuite(HttpURLConnectionTest.class);
		suite.addTestSuite(Inet4AddressTest.class);
		suite.addTestSuite(Inet6AddressTest.class);
		suite.addTestSuite(InetAddressTest.class);
		suite.addTestSuite(InetSocketAddressTest.class);
		suite.addTestSuite(JarURLConnectionTest.class);
		suite.addTestSuite(MalformedURLExceptionTest.class);
		suite.addTestSuite(MulticastSocketTest.class);
		suite.addTestSuite(NetPermissionTest.class);
		suite.addTestSuite(NetworkInterfaceTest.class);
		suite.addTestSuite(NoRouteToHostExceptionTest.class);
		suite.addTestSuite(PasswordAuthenticationTest.class);
		suite.addTestSuite(ProtocolExceptionTest.class);
		suite.addTestSuite(ResponseCacheTest.class);
		suite.addTestSuite(ServerSocketTest.class);
		suite.addTestSuite(SocketTest.class);
		suite.addTestSuite(SocketExceptionTest.class);
		suite.addTestSuite(SocketImplTest.class);
		suite.addTestSuite(SocketPermissionTest.class);
		suite.addTestSuite(UnknownHostExceptionTest.class);
		suite.addTestSuite(UnknownServiceExceptionTest.class);
		suite.addTestSuite(URITest.class);
		suite.addTestSuite(URISyntaxExceptionTest.class);
		suite.addTestSuite(URLTest.class);
		suite.addTestSuite(URLClassLoaderTest.class);
		suite.addTestSuite(URLConnectionTest.class);
		suite.addTestSuite(URLDecoderTest.class);
		suite.addTestSuite(URLEncoderTest.class);
		// $JUnit-END$
		return suite;
	}
}
