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

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.util.logging.Logger;

import ar.org.fitc.test.rmi.integration.fase2.clientExecutor.Net;

/**
 * Implementation of <code>PropagableExecutor</code>.
 * 
 * @author Jorge Rafael
 * @author Marcelo Arcidiacono
 * 
 * @version 1.0
 */
public class PropagableExecutorImpl extends RemoteExecutorImpl implements
		PropagableExecutor {

	/**
	 * For log purpose.
	 */
	transient private final static Logger log = Logger.getAnonymousLogger();

	/**
	 * Version number unique identificator.
	 */
	private static final long serialVersionUID = 7253124857830298656L;

	/**
	 * Default constructor.
	 * 
	 * @throws RemoteException
	 *             if the remote operation fails
	 */
	public PropagableExecutorImpl() throws RemoteException {
		super();
	}

	/**
	 * Constructs a <code>PropagableExecutorImpl</code> using 
	 * a particular supplied port.
	 *  
	 * @param port the port number on which the remote object receives 
	 * calls  
	 * @throws RemoteException if failed to export object
	 */
	public PropagableExecutorImpl(int port) throws RemoteException {
		super(port);
	}

	/**
	 * Constructs a <code>PropagableExecutorImpl</code> using 
	 * a particular supplied port and socket factories.
	 * 
	 * @param port the port number on which the remote object receives 
	 * calls 
	 * @param csf the client-side socket factory for making calls to 
	 * the remote object
	 * @param ssf the server-side socket factory for receiving remote 
	 * calls
	 * @throws RemoteException if failed to export object
	 */
	public PropagableExecutorImpl(int port, RMIClientSocketFactory csf,
			RMIServerSocketFactory ssf) throws RemoteException {
		super(port, csf, ssf);
	}

	/**
	 * Executes an object.
	 * 
	 * @param host a string that contains the host 
	 * @param obj a <code>ServerExecutor</code>
	 * @param times of the executions
	 * @param arguments an arbitrary number of hosts 
	 * @return a <code>RemoteExecutor</code>
	 * @throws RemoteException if the remote operation fails
	 */
	public Object execute(String host, ServerExecutor obj, int times,
			Object... arguments) throws RemoteException {
		RemoteExecutor client;
		try {
			client = (RemoteExecutor) LocateRegistry.getRegistry(host,
					Net.getRegistryPort()).lookup(CLIENT_SERVICE_NAME);
			return client.execute(obj, times, arguments);
		} catch (AccessException e) {
			throw new RemoteException(e.getMessage());
		} catch (NotBoundException e) {
			throw new RemoteException(e.getMessage());
		}
	}

	/**
	 * Executes an object.
	 * 
	 * @param host a string array that contains the hosts 
	 * @param obj a <code>ServerExecutor</code>
	 * @param times of the executions
	 * @param arguments an arbitrary number of hosts
	 * @return <code>true</code> if the execution was successful
	 * @throws RemoteException if the remote operation fails
	 */
	public boolean execute(String[] host, ServerExecutor obj, int times,
			Object... arguments) throws RemoteException {
		if (host != null && host.length > 0) {
			PropagableExecutor client;
			try {
				client = (PropagableExecutor) LocateRegistry.getRegistry(
						host[0], Net.getRegistryPort()).lookup(
						CLIENT_SERVICE_NAME);
				return client.execute(host, 1, obj, times, arguments);
			} catch (AccessException e) {
				throw new RemoteException(e.getMessage());
			} catch (NotBoundException e) {
				throw new RemoteException(e.getMessage());
			}
		} else {
			return true;
		}

	}

	/**
	 * Executes an object.
	 * 
	 * @param host a string array that contains the hosts 
	 * @param index of execution
	 * @param obj a <code>ServerExecutor</code>
	 * @param times of the executions
	 * @param arguments an arbitrary number of hosts
	 * @return <code>true</code> if the execution was successful
	 * @throws RemoteException if the remote operation fails
	 */
	public boolean execute(String[] host, int index, ServerExecutor obj,
			int times, Object... arguments) throws RemoteException {

		if (index < host.length) {
			Thread t = threadedExecute(obj, times, arguments);
			PropagableExecutor client;
			try {
				client = (PropagableExecutor) LocateRegistry.getRegistry(
						host[index], Net.getRegistryPort()).lookup(
						CLIENT_SERVICE_NAME);
				client.execute(host, index + 1, obj, times, arguments);
			} catch (AccessException e) {
				throw new RemoteException(e.getMessage());
			} catch (NotBoundException e) {
				throw new RemoteException(e.getMessage());
			}
			if (t.isAlive()) {
				try {
					t.join();
				} catch (InterruptedException e) {
					log
							.info("Execute finish with a InterruptedException, when wait the excute finish");
				}
			}
		} else {
			execute(obj, times, arguments);
		}
		return true;
	}
}
