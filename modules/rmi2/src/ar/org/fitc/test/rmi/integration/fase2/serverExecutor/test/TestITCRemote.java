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
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import ar.org.fitc.test.rmi.integration.fase2.clientExecutor.interfaces.ITCRemote;

/**
 * Testing class for <code>ITCRemote</code>.
 * 
 * @author Jorge Rafael
 * @author Marcelo Arcidiacono
 * 
 * @version 1.0
 */
abstract public class TestITCRemote extends ITCTestCase {
	
	/**
	 * A simple <code>ITCRemote</code> object.
	 */
	protected ITCRemote ret;
	
	/**
	 * A simple <code>ITCRemote</code> object.
	 */
	private ITCRemote retBind;
	
	/**
	 * Binds a copy of an <code>ITCRemote</code> object.
	 * 
	 * @throws Exception if the bind operation fails
	 */
	protected void setUp() throws Exception {
		initRegistry();
		retBind = ret;
		ret = retBind.myClone();
//		ret = null;
		Naming.bind("ITCREMOTE", retBind);
		super.setUp();
	}
	
	/**
	 * Unbinds an <code>ITCRemote<code> object.  
	 * 
	 * @throws Exception if the unbind operation fails
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		ret = null;
		retBind = null;
		Naming.unbind("ITCREMOTE");
		forceGC();
	}
	
	/**
	 * Forces the unexport of an <code>ITCRemote</code> object. 
	 * 
	 * @throws RemoteException if the unexport operation fails
	 */
	@SuppressWarnings("unchecked")
	public void testClean() throws RemoteException {
		retBind.clean(true);
		WeakReference weak = new WeakReference(retBind);
		retBind = null;
		assertNull(weak);
	}
	
	@SuppressWarnings("unchecked")
	public void testClean2() throws RemoteException {
		ret.clean(true);
		WeakReference weak = new WeakReference(ret);
		ret = null;
		assertNull(weak);
	}

	/**
	 * Verifies the <code>WeakReference</code>.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void testGCBindObject() {
		WeakReference weak = new WeakReference(retBind);
		retBind = null;
		assertNotNull(weak);
	}
	
	/**
	 * Verifies a <code>WeakReference</code> by forcing the 
	 * unexport of an <code>ITCRemote</code> object. 
	 * 
	 * @throws RemoteException if the operation fail
	 * @throws MalformedURLException if the name is not an appropriately 
	 * formatted URL 
	 * @throws NotBoundException if name is not currently bound  
	 */
	@SuppressWarnings("unchecked")
	public void testGCCleanBind() throws RemoteException, MalformedURLException, NotBoundException {
		retBind.clean(true);
		WeakReference weak = new WeakReference(retBind);
		retBind = null;
		assertNull(weak);
		try {
			((ITCRemote)Naming.lookup("ITCREMOTE")).getString();
		} catch (NoSuchObjectException e) {
		}
	}
	
	/**
	 * Verifies a lookup of an <code>ITCRemote</code> object.
	 *  
	 * @throws RemoteException if the lookup operation fails
	 * @throws MalformedURLException if the name is not an appropriately 
	 * formatted URL 
	 * @throws NotBoundException if name is not currently bound  
	 */
	public void test001() throws RemoteException, MalformedURLException, NotBoundException {
		assertNotNull(Naming.lookup("ITCREMOTE"));		
	}
	
	/**
	 * Verifies the <code>WeakReference</code> of an export object.
	 * 
	 * @throws RemoteException if the exportation fails
	 */
	@SuppressWarnings("unchecked")
	public void testDGCExport() throws RemoteException {
		UnicastRemoteObject.exportObject(ret, 10000);
		WeakReference weak = new WeakReference(ret);
		ret = null;
		assertNull(weak);
	}
	
	/**
	 * Verifies the <code>imInServer</code> method.
	 * 
	 * @throws RemoteException if the remote operation fails
	 */
	public void testImInServer() throws RemoteException {
		assertTrue(ret.imInServer());
	}
	
	/**
	 * Verifies the <code>imInServer</code> method on a remote 
	 * reference. 
	 * 
	 * @throws RemoteException if the remote operation fails
	 * @throws MalformedURLException if the name is not an appropriately 
	 * formatted URL 
	 * @throws NotBoundException if name is not currently bound  
	 */
	public void testImInServerRef() throws RemoteException, MalformedURLException, NotBoundException {
		assertTrue(((ITCRemote)Naming.lookup("ITCREMOTE")).imInServer());
	}
}
