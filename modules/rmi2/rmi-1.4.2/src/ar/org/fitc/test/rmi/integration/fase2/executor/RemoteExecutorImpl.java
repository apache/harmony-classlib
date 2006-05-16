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
package ar.org.fitc.test.rmi.integration.fase2.executor;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.ServerNotActiveException;

import ar.org.fitc.test.rmi.integration.fase2.ITCRemoteUnicast;

/**
 * Implementation of the <code>RemoteExecutor<code>.
 * 
 * @author Jorge Rafael
 * @author Marcelo Arcidiacono
 * 
 * @version 1.0
 */
public class RemoteExecutorImpl extends ITCRemoteUnicast implements
		RemoteExecutor {

	/**
	 * Version number unique identificator.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 * 
	 * @throws RemoteException if the remote operation fails
	 */
	public RemoteExecutorImpl() throws RemoteException {
		super();
	}

	/**
	 * Constructs a <code>RemoteExecutorImpl</code> using 
	 * a particular supplied port.
	 * 
	 * @param port the port number on which the remote object receives 
	 * calls  
	 * @throws RemoteException if failed to export object
	 */
	public RemoteExecutorImpl(int port) throws RemoteException {
		super(port);
	}

	/**
	 * Constructs a <code>RemoteExecutorImpl</code> using 
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
	public RemoteExecutorImpl(int port, RMIClientSocketFactory csf,
			RMIServerSocketFactory ssf) throws RemoteException {
		super(port, csf, ssf);
	}

	/**
	 * Executes an object a specified number of times.
	 *  
	 * @param obj a <code>ServerExecutor</code>
	 * @param times of the executions
	 * @param arguments an arbitrary number of hosts
	 * @return a result of the execution
	 * @throws RemoteException if the remote operation fails
	 */
	public Object execute(ServerExecutor obj, int times, Object... arguments)
			throws RemoteException {
		if (!imInServer()) {
			try {
				System.out.println("\t\tExcecuting execute client: " + getClientHost() + " my: " + ReportIPServer.localHost());
			} catch (ServerNotActiveException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int i = 0; i < times - 1; i++) {
			obj.execute(arguments);
		}
		return obj.execute(arguments);
	}

	/**
	 * Executes an object in a thread.
	 * 
	 * @param ser a <code>ServerExecutor</code>
	 * @param times of the executions 
	 * @param arguments an arbitrary number of hosts
	 * @return a thread
	 * @throws RemoteException if the remote operation fails
	 */
	public Thread threadedExecute(final ServerExecutor ser, final int times,
			final Object... arguments) throws RemoteException {
		Thread t = new Thread() {
			public void run() {
				try {
					execute(ser, times, arguments);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		};
		t.start();
		return t;
	}
}
