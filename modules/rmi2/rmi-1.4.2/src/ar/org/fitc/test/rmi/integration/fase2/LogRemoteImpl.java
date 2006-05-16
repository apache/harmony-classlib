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

import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;

import ar.org.fitc.test.rmi.integration.fase2.interfaces.LogRemote;

/**
 * Implementation of <code>LogRemote</code> interface.
 * 
 * @author Jorge Rafael
 * @author Marcelo Arcidiacono
 *
 * @version 1.0
 */
public class LogRemoteImpl extends AbstractITCRemote implements LogRemote {

	/**
	 * Version number unique identificator.
	 */	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Simple cyclic reference.
	 */
	public LogRemote out;
	
	/**
	 * Simple int to number the funtion calls.
	 */
	int i = 0;

	/**
	 * Default constructor. 
	 *
	 */
	public LogRemoteImpl() {
		super();
	}
	
	/**
	 * Constructs a <code>LogRemoteImpl</code> with a cyclic reference. 
	 * 
	 * @param stub a reference for the remote object 
	 */
	public LogRemoteImpl(LogRemote stub) {
		super();
		out = stub;
	}

	/**
	 * Prints a string representation of the client host. Indicates if 
	 * is a local o remote execution.
	 * 
	 * @param arg a string that indicates if is a local or remote
	 * execution
	 * @throws RemoteException if the remote operation fails 
	 */
	public void println(String arg) throws RemoteException {
		i++;
		try {
			System.out.println("\t\t" + i + " " + UnicastRemoteObject.getClientHost() + ": " + arg);
			
		} catch (ServerNotActiveException e) {
			System.out.println("\t\t" + i + " Localhost: " + arg);
		}
	}
}
