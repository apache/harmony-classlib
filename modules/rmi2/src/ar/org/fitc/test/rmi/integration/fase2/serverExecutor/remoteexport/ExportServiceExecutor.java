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
package ar.org.fitc.test.rmi.integration.fase2.serverExecutor.remoteexport;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

import ar.org.fitc.test.rmi.integration.fase2.clientExecutor.interfaces.ITCRemote;
import ar.org.fitc.test.rmi.integration.fase2.serverExecutor.executor.AbstractServerExecutor;

/**
 * Particular implementation of <code>AbstractServerExecutor</code>.
 * 
 * @author Jorge Rafael
 * @author Marcelo Arcidiacono
 * 
 * @version 1.0
 */
public class ExportServiceExecutor extends AbstractServerExecutor {
	
	/**
	 * Version number unique identificator.
	 */	
	private static final long serialVersionUID = 2L;

	/**
	 * Default cosntructor.
	 * 
	 * @throws RemoteException if the remote operation fails
	 */
	public ExportServiceExecutor() throws RemoteException {
		super();
	}
	
	/**
	 * Exports an <code>ITCRemote</code> object.
	 * 
	 * @param arguments can be a <code>ITCRemote</code> object, a port 
	 * and socket factories as well in this order
	 * @return return a remote object stub or <code>null</code> value
	 * @throws RemoteException if the exporation fails
	 */
	@Override
	public Object execute(Object... arguments) throws RemoteException {
		int argumentCount = ((Integer) arguments[0]).intValue();
		switch(argumentCount) {
		case (1) :
			return UnicastRemoteObject.exportObject((ITCRemote)arguments[1]);
		case (2) :
			return UnicastRemoteObject.exportObject((ITCRemote)arguments[1], ((Integer) arguments[2]).intValue());
		case (4) :
			return UnicastRemoteObject.exportObject((ITCRemote)arguments[1], ((Integer) arguments[2]).intValue(), (RMIClientSocketFactory)arguments[3], (RMIServerSocketFactory)arguments[4]);
		default:
			return null;
		}
	}
}
