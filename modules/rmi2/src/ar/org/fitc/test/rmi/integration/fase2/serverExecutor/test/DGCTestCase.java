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
package ar.org.fitc.test.rmi.integration.fase2.serverExecutor.test;

import java.lang.ref.WeakReference;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import junit.framework.AssertionFailedError;
import ar.org.fitc.test.rmi.integration.fase2.clientExecutor.executor.ServerExecutor;
import ar.org.fitc.test.rmi.integration.fase2.clientExecutor.interfaces.ITCRemote;
import ar.org.fitc.test.rmi.integration.fase2.serverExecutor.LogRemoteImpl;
import ar.org.fitc.test.rmi.integration.fase2.serverExecutor.MoveRemoteObject;
import ar.org.fitc.test.rmi.integration.fase2.serverExecutor.interfaces.LogRemote;
import ar.org.fitc.test.rmi.integration.fase2.serverExecutor.remoteexport.PortableUnicastRemoteObject;


/**
 * Testing class for Distributed Garbage Collector.
 * 
 * @author Jorge Rafael
 * @author Marcelo Arcidiacono
 * 
 * @version 1.0
 */
public class DGCTestCase extends ExecutorTestCase {

	/**
	 * A <code>LogRemoteImpl</code>.
	 */
	public LogRemoteImpl remote;

	/**
	 * A <code>ServerExecutor</code>.
	 */
	public ServerExecutor serv;

	/**
	 * A <code>WeakReference</code>.
	 */
	public WeakReference wref;

	/**
	 * A <code>LogReRemote</code>.
	 */
	public LogRemote stub;

	
	/**
	 * Default constructor.
	 *
	 */
	public DGCTestCase() {
		super();
	}

	/**
	 * Constructs a <code>DGCTestCase</code> with a name. 
	 * 
	 * @param arg0 a name.
	 */
	public DGCTestCase(String arg0) {
		super(arg0);
	}

	/**
	 * Wait for a reference.
	 * 
	 * @param ref a specified <code>WeakReference</code>
	 */
	@SuppressWarnings("unused")
	private void waitRef(WeakReference ref) {
		System.out.print("waiting");
		try {
			for (int i = 0; i < 40; i++) {
				System.out.print(".");
				try {			
					Thread.sleep(5000);
				} catch (InterruptedException e) {	}
				assertNotNull(wref);
			}
		} catch (AssertionFailedError e) {
		}
		System.out.println();
	}
	
	/**
	 * Exports a remote object. 
	 * 
	 * @throws Exception if any exception occurs
	 */
	@SuppressWarnings("unchecked")
	protected void setUp() throws Exception {
		remote = new LogRemoteImpl();
		stub = (LogRemote) UnicastRemoteObject.exportObject(remote, 0);
		// Container and remote execution
		serv = new MoveRemoteObject(remote);
		wref = new WeakReference(remote);
		super.setUp();
	}

	/**
	 * Unexport a remote object.
	 * 
	 * @throws Exception if any exception occurs 
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		if (wref.get() != null) {
			UnicastRemoteObject.unexportObject((ITCRemote) wref.get(), true);
		}
	}

	/**
	 * This test copies -through all servers- the reference (stub) 
	 * of an exported remote object. The life time of these objects 
	 * is limited, they work and then they are deleted. All local 
	 * strong references to the object are deleted and we expect 
	 * that the DGC collect the remote object.
	 * 
	 * @throws RemoteException if any remote operation fails
	 * @throws NotBoundException if the name is not currently bound 
	 */
	@SuppressWarnings("unchecked")
	public void testMovingReference001() throws RemoteException,
			NotBoundException {

		executor.execute(hosts, serv, 10);

		// Obtains s WeakreRerence and clean all local reference
		remote = null;
		serv = null;
		// a local stub not change the DGC behavior. 
		// stub = null;

		// Force GC in all clean
		// GCRemote.forceRemoteGC();
		waitRef(wref);
		assertNull("the reference is not null",wref);
	}

	/**
	 * Exports a non exported object that has a reference to an 
	 * exported object in an external host. If all local strong 
	 * references are deleted and only the exported object in the 
	 * external host has a reference to the local object, then the 
	 * local object is not collected.
	 * 
	 * @throws RemoteException if any remote operation fails
	 * @throws NotBoundException if the name is not currently bound 
	 */
	public void testMovingReference002() throws RemoteException, NotBoundException {
		{
		LogRemoteImpl remote2 = new LogRemoteImpl();
		remote2.out = remote;
		stub = (LogRemote) PortableUnicastRemoteObject.exportObject(
				hosts[0], remote2, 0);
		}
		remote = null; serv = null;
		assertNotNull("The reference is steal hear",wref);
		stub = null;
		waitRef(wref);
		assertNull("The reference is steal hear",wref);
	}
	
	/**
	 * Makes a cyclic reference using the stub of the remote object. 
	 * The specified objects are in the same host. The DGC will detect 
	 * the cyclic reference and collect them.
	 * 
	 * @throws RemoteException if any remote operation fails
	 * @throws NotBoundException if the name is not currently bound 
	 */
	public void testCyclicReference001() throws RemoteException,
			NotBoundException {
		{
			LogRemoteImpl remote2 = new LogRemoteImpl();

			LogRemoteImpl remote3 = new LogRemoteImpl();
			remote3.out = stub;
			remote2.out = (LogRemote) UnicastRemoteObject.exportObject(remote3, 0);
			remote.out = (LogRemote) UnicastRemoteObject.exportObject(remote2, 0);

		}
		testMovingReference001();
	}

	/**
	 * Makes a cyclic reference to the remote objects that they are 
	 * in different hosts. The DGC not will detect the cyclic reference 
	 * and not collect them.
	 * 
	 * @throws RemoteException if any remote operation fails
	 * @throws NotBoundException if the name is not currently bound
	 */
	public void testCyclicReference002() throws RemoteException,
			NotBoundException {
		{
			LogRemoteImpl remote2 = new LogRemoteImpl();
			LogRemoteImpl remote3 = new LogRemoteImpl();
			remote3.out = remote;
			remote2.out = (LogRemote) PortableUnicastRemoteObject.exportObject(
					hosts[1], remote3, 0);
			remote.out = (LogRemote) PortableUnicastRemoteObject.exportObject(
					hosts[0], remote2, 0);

		}
		executor.execute(hosts, serv, 10);

		// Obtains s WeakreRerence and clean all local reference
		remote = null;
		serv = null;
		// a local stub not change the DGC behavior.  
		// stub = null;

		// Force GC in all clean
		// GCRemote.forceRemoteGC();
		waitRef(wref);
		assertNotNull("the reference mustn't clean",wref);
		
	}
}
