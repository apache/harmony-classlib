/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package ar.org.fitc.test.rmi.integration.fase2.clientExecutor.executor;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.security.Permission;

import ar.org.fitc.test.rmi.integration.fase2.clientExecutor.Net;
import ar.org.fitc.test.rmi.integration.fase2.clientExecutor.interfaces.ITCRemote;

/**
 * Execution in the client side.
 * 
 * @author Jorge Rafael
 * @author Marcelo Arcidiacono
 * 
 * @version 1.0
 */
public class ClientExecutor extends PropagableExecutorImpl implements
		PropagableExecutor, RemoteExecutor, ITCRemote {

	/**
	 * Version number unique identificator.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The sleep time.
	 */
	private final static int SLEEP_MINUTES = 2;

	/**
	 * The remote interface to a simple remote object.
	 */
	private static Registry registry;

	/**
	 * Default constructor.
	 * 
	 * @throws RemoteException if failed to export object
	 */
	protected ClientExecutor() throws RemoteException {
		super();
	}

	/**
	 * Obtains a <code>PropagableExecutor</code> for a particular host 
	 * name.
	 * 
	 * @param host a specified host name
	 * @return a <code>PropagableExecutor</code>
	 * @throws RemoteException if registry could not be contacted 
	 * @throws NotBoundException if name is not currently bound
	 */
	public static PropagableExecutor getExecutor(String host)
			throws RemoteException, NotBoundException {
		Registry reg = LocateRegistry.getRegistry(host, Net.getRegistryPort());
		PropagableExecutor client;
		client = (PropagableExecutor) reg.lookup(CLIENT_SERVICE_NAME);
		return client;
	}

	/**
	 * Exports a <code>ClientExecutor</code>.
	 * 
	 * @throws RemoteException if registry could not be contacted 
	 * @throws MalformedURLException if the name is not an 
	 * appropriately formatted URL
	 */
	public static void exportExecutor() throws RemoteException,
			MalformedURLException {
		ClientExecutor client = new ClientExecutor();
		Naming.rebind(CLIENT_SERVICE_NAME, client);
	}

	/**
	 * Initializes the <code>Registry</code>. If registry is null, 
	 * creates it.
	 * 
	 */
	public synchronized static void initRegistry() {
		if (registry == null) {
			try {
				try {
					registry = LocateRegistry.createRegistry(Net
							.getRegistryPort());
				} catch (ExportException e) {
					registry = LocateRegistry
							.getRegistry(Net.getRegistryPort());
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws RemoteException,
			MalformedURLException {
		System.setSecurityManager(new RMISecurityManager() {
			public void checkPermission(Permission perm) {
			};
		});
		try {
			ReportIPServer.setServerHost(System
					.getProperty("java.rmi.server.hostname"));
		} catch (UnknownHostException e2) {
			e2.printStackTrace();
			System.exit(1);
		}
		initRegistry();
		exportExecutor();
		/*
		 * This is just to avoid the Registry from being Garbage Collected.
		 */
		while (true) {
			try {
				ReportIPServer.doit();
				Thread.sleep(SLEEP_MINUTES * 6000);
			} catch (InterruptedException e) {
			}
		}
	}
}