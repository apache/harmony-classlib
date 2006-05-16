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

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.RemoteStub;

import ar.org.fitc.test.rmi.integration.fase2.clientExecutor.executor.ClientExecutor;
import ar.org.fitc.test.rmi.integration.fase2.clientExecutor.executor.PropagableExecutor;
import ar.org.fitc.test.rmi.integration.fase2.clientExecutor.executor.ServerExecutor;
import ar.org.fitc.test.rmi.integration.fase2.clientExecutor.interfaces.ITCRemote;

/**
 * Implements static methods for export an unexport objects.
 * 
 * @author Jorge Rafael
 * @author Marcelo Arcidiacono
 * 
 * @version 1.0
 */
public class PortableUnicastRemoteObject {

	/**
	 * Default constructor.
	 */
	public PortableUnicastRemoteObject() {
		super();
	}

	/**
	 * Defines a <code>PropagableExecutor</code> object and assigns it
	 * a <code>ClentExecutor</code>.
	 *  
	 * @param serverHost the host of the Server
	 * @param obj an <code>ITCRemote</code> object
	 * @return a <code>RemoteStub</code> 
	 * @throws RemoteException if the remote operation fails
	 * @throws NotBoundException if name is not currently bound 
	 */
	static public RemoteStub exportObject(String serverHost, ITCRemote obj) throws RemoteException, NotBoundException {
		PropagableExecutor client;
		client = ClientExecutor.getExecutor(serverHost);
		ServerExecutor serv = new ExportServiceExecutor();		
		return (RemoteStub) client.execute(serv, 0, (Integer) 1, obj);
	}
	
	/**
	 * Defines a <code>PropagableExecutor</code> object and assigns it
	 * a <code>ClentExecutor</code>.
	 *  
	 * @param serverHost the host of the Server
	 * @param obj an <code>ITCRemote</code> object
	 * @param port the specified port
	 * @return an <code>ITCRemote</code> object
	 * @throws RemoteException if the remote operation fails
	 * @throws NotBoundException if name is not currently bound 
	 */
	static public ITCRemote exportObject(String serverHost, ITCRemote obj, int port) throws RemoteException, NotBoundException {
		PropagableExecutor client;
		client = ClientExecutor.getExecutor(serverHost);
		ServerExecutor serv = new ExportServiceExecutor();		
		return (ITCRemote) client.execute(serv, 0, (Integer) 2, obj, port);
	}

	/**
	 * Defines a <code>PropagableExecutor</code> object and assigns it
	 * a <code>ClentExecutor</code>.
	 * 
	 * @param serverHost the host of the Server
	 * @param obj an <code>ITCRemote</code> object
	 * @param port the specified port
	 * @param csf the specified <code>ClientSocketFactory</code>
	 * @param ssf the specified <code>ServerSocketFactory</code>
	 * @return an <code>ITCRemote</code> object
	 * @throws RemoteException if the remote operation fails
	 * @throws NotBoundException if name is not currently bound 
	 */
	static public ITCRemote exportObject(String serverHost, ITCRemote obj, int port, RMIClientSocketFactory csf,
            RMIServerSocketFactory ssf) throws RemoteException, NotBoundException {
		PropagableExecutor client;
		client = ClientExecutor.getExecutor(serverHost);
		ServerExecutor serv = new ExportServiceExecutor();
		return (ITCRemote) client.execute(serv, 0, (Integer) 4, obj, port, csf, ssf);
	}

	/**
	 * Unexport a specified <code>ITCRemote</code> object. 
	 * 
	 * @param obj the specified <code>ITCRemote</code> object
	 * @param force if <code>true</code>, unexports the object even 
	 * if there are pending or in-progress calls; if <code>false</code>, 
	 * only unexports the object if there are no pending or in-progress 
	 * calls 
	 * @return true if operation is successful, false otherwise
	 * @throws RemoteException if the remote operation fails
	 */
	static public boolean unexportObject(ITCRemote obj, boolean force) throws RemoteException {
		return obj.clean(force);
	}
}
