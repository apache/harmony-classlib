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

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import junit.framework.AssertionFailedError;
import ar.org.fitc.test.rmi.integration.fase2.AbstractITCRemote;
import ar.org.fitc.test.rmi.integration.fase2.executor.ClientExecutor;
import ar.org.fitc.test.rmi.integration.fase2.executor.ServerExecutor;
import ar.org.fitc.test.rmi.integration.fase2.interfaces.ITCRemote;
import ar.org.fitc.test.rmi.integration.fase2.remoteexport.ExecutorGetClientHost;
import ar.org.fitc.test.rmi.integration.fase2.remoteexport.PortableUnicastRemoteObject;

/**
 * Testing class for verifies references to exported objects.
 * 
 * @author Jorge Rafael
 * @author Marcelo Arcidiacono
 *
 * @version 1.0
 */
public class PortableTestCase extends ExecutorTestCase {
	
	/**
	 * Default constructor
	 *
	 */
	public PortableTestCase() {
		super();
	}

	/**
	 * Constructs a <code>PortableTestCase</code> with a name. 
	 * 
	 * @param arg0 a name.
	 */
	public PortableTestCase(String arg0) {
		super(arg0);
	}

	/**
	 * Assigns an IP reported.
	 *  
	 * @throws Exception if any exception occurs 
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/**
	 * Simple implementation.
	 * 
	 * @throws Exception if any exception occurs
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	/**
	 * An object is exported to an external host and it is used 
	 * through his reference. Then, the object is unexported and 
	 * we can verify that cannot use the reference
	 * 
	 * @throws RemoteException if the exportation fails
	 * @throws NotBoundException if the name is not currently bound 
	 */
	
	public void testPortar() throws RemoteException, NotBoundException {
		ITCRemote r = new AbstractITCRemote();
		ITCRemote stub = PortableUnicastRemoteObject.exportObject(hosts[0], r, 0);
		r = null;
		PortableUnicastRemoteObject.unexportObject(stub, true);
		try {
			stub.getString();
			fail("object is no longer export, but i can used");
		} catch (RemoteException e) {
		}
	}
	
	/**
	 * The objects are exported in different hosts (one object per 
	 * each host). In each exportation the destiny is verified.
	 * 
	 * @throws RemoteException if the exportation fails
	 */
	
	public void testExportingAndComparing() throws RemoteException {
				
		for (final String host : hosts) {
			ServerExecutor testObject = new ExecutorGetClientHost();
			ServerExecutor stubTestObject = null;
			try {
				stubTestObject = (ServerExecutor) PortableUnicastRemoteObject.exportObject(host, (ITCRemote) testObject, 0);
				try {
					assertEquals("The host must be " + host, host, ClientExecutor.getExecutor(host).execute(stubTestObject, 1));
					System.err.println(host + " OK" );
				} catch (AssertionFailedError e) {
					System.err.println(e);
				}
			} catch (NotBoundException e) {
				log.warning("One host (" + host +") don't work anymore");
			} finally {
				if(stubTestObject != null && stubTestObject.clean(true)) {//PortableUnicastRemoteObject.unexportObject(stubTestObject, true)) {
					log.warning("The ExecutorGetCliengHost can't be unexported");
				}
			}
		}
	}
	
}
