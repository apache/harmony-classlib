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
package ar.org.fitc.test.rmi.integration.fase2;

import java.io.PrintStream;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

import ar.org.fitc.test.rmi.integration.fase2.executor.ServerExecutor;

/**
 * For tests and reports purpose.
 * 
 * @author Jorge Rafael
 * @author Marcelo Arcidiacono
 *
 * @version 1.0
 */
public class PropagableTestRemote extends ITCRemoteUnicast implements ServerExecutor {
	
	/**
	 * Version number unique identificator.
	 */	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Adds functionality to the <code>System.out</code>.
	 */	
	transient PrintStream out = System.out;
	
	/**
	 * Simple counter.
	 */
	int i = 0;
	
	/**
	 * Default constructor.
	 * 
	 * @throws RemoteException if the remote operation fail
	 */
	public PropagableTestRemote() throws RemoteException {
		super();
	}

	/**
	 * Notices the status of the ClientHost. Used in the ok case.
	 * 
	 * @return a <code>true</code> value
	 */
	public Object ok() {
		try {
			out.println("\t\tHost " + getClientHost() + " work well");
		} catch (ServerNotActiveException e) {
		}
		return true;
	}
	
	/**
	 * Notices the status of the ClientHost. Used in the failed case.
	 * 
	 * @param host the specified host name
	 * @return a <code>false</code> value
	 */
	public Object fail(String host) {
		try {
			out.println("\t\tHost " + getClientHost() + " don't work, this host wasn't " + host);
		} catch (ServerNotActiveException e) {
		}
		return false;
	}
		
	/**
	 * Tests the hosts status.
	 * 
	 * @param arguments an arbitrary number of hosts
	 * @return a boolean value
	 * @throws RemoteException if the remote operation fails
	 */
	public Object execute(Object... arguments) throws RemoteException {
		String host;
		String client;
		
		if ((Integer)arguments[0] > 1) {
			i++;
			host = (String) arguments[i];
		} else {
			host = (String) arguments[1];
		}
		try {
			client = getClientHost();
		} catch (ServerNotActiveException e) {
			client = System.getProperty("java.rmi.server.hostname");
		}
		if (host.equals(client)) {
			out.println("\t\tHost " + client + " work well");
			return true;
		} else {
			out.println("\t\tHost " + client + " don't work, this host wasn't " + host);
			return false;
		}
	}
}
