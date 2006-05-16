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
package ar.org.fitc.test.rmi.integration.fase2.remoteexport;

import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;

import ar.org.fitc.test.rmi.integration.fase2.executor.AbstractServerExecutor;

/**
 * Particular implementation of <code>AbstractServerExecutor</code>.
 * 
 * @author Jorge Rafael
 * @author Marcelo Arcidiacono
 *
 * @version 1.0 
 */
public class ExecutorGetClientHost extends AbstractServerExecutor {

	/**
	 * Version number unique identificator.
	 */	
	private static final long serialVersionUID = 1L;

	/**
	 * Default cosntructor.
	 * 
	 * @throws RemoteException if the remote operation fails
	 */
	public ExecutorGetClientHost() throws RemoteException {
		super();
	}

	/**
	 * Returns a host address. 
	 * 
	 * @param arguments not used here
	 * @return an host address 
	 * @throws RemoteException if the remote operation fails 
	 */
	@Override
	public Object execute(Object... arguments) throws RemoteException {
		try {
			return UnicastRemoteObject.getClientHost();
		} catch (ServerNotActiveException e) {
			return "127.0.0.1";
		}
	}
}
