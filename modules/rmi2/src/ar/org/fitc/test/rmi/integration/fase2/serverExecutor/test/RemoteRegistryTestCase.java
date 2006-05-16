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

import java.net.MalformedURLException;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.ServerException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import ar.org.fitc.test.rmi.integration.fase2.clientExecutor.ITCRemoteUnicast;
import ar.org.fitc.test.rmi.integration.fase2.clientExecutor.Net;
import ar.org.fitc.test.rmi.integration.fase2.clientExecutor.executor.ReportIPServer;
import ar.org.fitc.test.rmi.integration.fase2.clientExecutor.interfaces.ITCRemote;
import ar.org.fitc.test.rmi.integration.fase2.serverExecutor.AutoBindITCRemoteUnicast;

/**
 * Testing class for a remote <code>Registry</code>.
 * 
 * @author Jorge Rafael
 * @author Marcelo Arcidiacono
 *
 * @version 1.0
 */
public class RemoteRegistryTestCase extends ITCTestCase {

	/**
	 * A simple <code>Registry</code>. 
	 */
	public Registry reg;

	/**
	 * An <code>ITCRemote</code> for exporting.
	 */
	public ITCRemote exportObj;

	/**
	 * Default constructor.
	 *
	 */
	public RemoteRegistryTestCase() {
		super();
	}

	/**
	 * Constructs a <code>RemoteRegistryTestCase</code> with a name. 
	 * 
	 * @param arg0 a name.
	 */
	public RemoteRegistryTestCase(String arg0) {
		super(arg0);
	}

	/**
	 * Assigns a <code>Registry</code> for each reported server and 
	 * exports an object on them. 
	 * 
	 * @throws Exception if a failure occurs during exportation
	 */
	protected void setUp() throws Exception {
		String[] hosts = ReportIPServer.getit();
		reg = LocateRegistry.getRegistry(hosts[0], Net.getRegistryPort());
		exportObj = new ITCRemoteUnicast();
		super.setUp();
	}

	/**
	 * Tries to bind a remote object in a <code>Registry</code> of a 
	 * non-local host. 
	 * 
	 * @throws RemoteException if remote communication with the 
	 * registry failed 
	 * @throws AlreadyBoundException if the name is already bound 
	 */
	public void testBind001() throws RemoteException, AlreadyBoundException {

		try {
			reg.bind("echo", exportObj);
			fail("Stub of registry can't chanch registry");
		} catch (ServerException e) {
		}
	}

	/**
	 * This test verifies that all elements in a non-local <code>
	 * Registry</code> are functional. 
	 * 
	 * @throws AccessException if this registry is local and it 
	 * denies the caller access to perform this operation 
	 * @throws RemoteException if remote communication with the 
	 * registry failed 
	 * @throws NotBoundException if the name is not currently bound 
	 */
	public void testLookup001() throws AccessException, RemoteException,
			NotBoundException {
		for (String bindName : reg.list() ) {
			ITCRemote o =(ITCRemote) reg.lookup(bindName);
			o.getString();
		}
	}

	/**
	 * This test tries lookup a non-bounded remote object. 
	 * 
	 * @throws RemoteException if remote communication with the 
	 * registry failed 
	 * @throws MalformedURLException if the name is not an 
	 * appropriately formatted URL
	 * @throws NotBoundException if the name is not currently bound
	 */
	public void testLookup002() throws RemoteException, MalformedURLException,
			NotBoundException {
		try {
			reg.lookup("echo123413");
			fail("Mal formed URL");
		} catch (NotBoundException e) {
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	/**
	 * This test tries lookup a remote object with a non-existing name. 
	 * 
	 * @throws RemoteException if remote communication with the 
	 * registry failed  
	 * @throws MalformedURLException if the name is not an 
	 * appropriately formatted URL
	 * @throws NotBoundException if the name is not currently bound
	 */
	public void testLookup003() {
		try {
			reg.lookup("#$%$%echo");
			fail("Mal formed URL");
		} catch (NotBoundException e) {
		} catch (Throwable e) {
			fail("Failed with:" + e);
		}
	}

	/**
	 * This test tries to re-bind a remote object with a same name 
	 * in a Registry. 
	 * 
	 * @throws RemoteException if remote communication with the 
	 * registry failed  
	 * @throws AlreadyBoundException if the name is already bound 
	 */
	public void testRebind001() throws RemoteException, MalformedURLException,
			NotBoundException {
		try {
			reg.rebind("echo", exportObj);
			fail("Stub of registry can't chanch registry");
		} catch (ServerException e) {
		}
	}

	/**
	 * This test tries to un-bind remote objects from a non-local 
	 * <code>Registry</code>. The object must to be registred.
	 * 
	 * @throws AccessException if this registry is local and it 
	 * denies the caller access to perform this operation 
	 * @throws RemoteException if remote communication with the 
	 * registry failed  
	 * @throws NotBoundException if the name is not currently bound
	 */
	@SuppressWarnings("unchecked")
	public void testUnbind() throws AccessException, RemoteException,
			NotBoundException {
		for (String bindName : reg.list() ) {
			try {
				reg.unbind(bindName);
				fail("Stub of registry can't chanch registry");
			} catch (ServerException e) {
				ITCRemote o =(ITCRemote) reg.lookup(bindName);
				o.getString();
			}
		}
	}

	/**
	 * This case makes a test on a non-exported object. If this 
	 * object is deserialized, it is exported and bounded. This object 
	 * is sent as parameter to a non-local <code>Registry</code>. The 
	 * binding fails. However the object will be bounded because 
	 * will be deserialized.
	 * 
	 * @throws RemoteException if remote communication with the 
	 * registry failed  
	 */
	public void testAutoBindAndExportionUsingRemoteRegistry() throws RemoteException {
		exportObj = new AutoBindITCRemoteUnicast();
		exportObj.clean(true);
		
		try {
			reg.rebind(AutoBindITCRemoteUnicast.BIND_NAME, exportObj);
			fail("can't bind remotelly");
		} catch (ServerException e) {
		}
		
		try {
			reg.lookup(AutoBindITCRemoteUnicast.BIND_NAME);
			fail("Object binded");
		} catch (NotBoundException e) {
		}
	}
}
