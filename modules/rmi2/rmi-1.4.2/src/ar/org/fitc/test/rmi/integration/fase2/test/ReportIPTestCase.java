/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the License);
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an AS IS BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package ar.org.fitc.test.rmi.integration.fase2.test;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;

import ar.org.fitc.test.rmi.integration.fase2.executor.ClientExecutor;
import ar.org.fitc.test.rmi.integration.fase2.executor.ReportIP;

/**
 * Testing class for <code>ReportIP</code>.
 * 
 * @author Jorge Rafael
 * @author Marcelo Arcidiacono
 *
 * @version 1.0
 */
public class ReportIPTestCase extends ITCTestCase {

	/**
	 * A <code>ReportIP</code>.
	 */
	public ReportIP reporter;

	/**
	 * Default constructor.
	 *
	 */
	public ReportIPTestCase() {
		super();
	}

	/**
	 * Constructs a <code>ReportIPTestCase</code> with a name. 
	 * 
	 * @param arg0 a name.
	 */
	public ReportIPTestCase(String arg0) {
		super(arg0);
	}

	/**
	 * Creates a <code>ReportIP</code>
	 * 
	 */
	protected void setUp() throws Exception {
		reporter = (ReportIP) java.rmi.Naming.lookup(ReportIP.BIND_NAME);
		super.setUp();
	}

	/**
	 * Verifies the host name of the current <code>ReportIP</code>.
	 * 
	 * @throws RemoteException if the remote operation fails
	 * @throws UnknownHostException if the IP address of a host could 
	 * not be determined
	 */
	public void testMyHostName001() throws RemoteException,
			UnknownHostException {
		String hostName = reporter.myHostName();
		for (InetAddress address : InetAddress.getAllByName(hostName)) {
			if (address.isLoopbackAddress() || address.isSiteLocalAddress()) {
				return;
			}
		}
		fail("The reporter.myHostName is not my");
	}

	/**
	 * Verifies that the <code>ReportIPServer</code> returns a string 
	 * of valid IP addresses. 
	 * 
	 * @throws RemoteException if the remote operation fails
	 * @throws UnknownHostException if the IP address of a host could 
	 * not be determined
	 */
	public void testGetIt001() throws RemoteException, UnknownHostException {
		String[] hosts = reporter.getIP();
		for (String host : hosts) {
			assertNotNull(InetAddress.getAllByName(host));
		}
	}
	
	/**
	 * Reports a new host and wait that the <code>ReportIP</code> adds 
	 * the host
	 * 
	 * @throws RemoteException if the remote operation fails
	 * @throws MalformedURLException if the name is not an appropriately 
	 * formatted URL 
	 */
	public void testReport001() throws RemoteException, MalformedURLException {
        String hostName = reporter.myHostName();
		ClientExecutor.exportExecutor();
		String[] initialhosts = reporter.getIP();
		reporter.report();
		String[] hosts = reporter.getIP();
		for (int i = 0, j = 0; i < hosts.length; i++, j++) {
			if (hosts[i].equals(hostName)) {
				j--;
			} else if (hosts[i].equals(initialhosts[j])) {
                
			} else {
				fail("I report and i don't see me in the list");
			}
		}
	}
}
