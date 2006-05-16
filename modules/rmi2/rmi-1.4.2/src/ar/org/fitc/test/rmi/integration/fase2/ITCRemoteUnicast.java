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
package ar.org.fitc.test.rmi.integration.fase2;

import java.io.Serializable;
import java.net.SocketException;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;

import ar.org.fitc.test.rmi.integration.fase2.interfaces.ITCRemote;

/**
 * Used for exporting a remote object and obtaining a stub that 
 * communicates to the remote object. 
 *  
 * @author Jorge Rafael
 * @author Marcelo Arcidiacono
 * 
 * @version 1.0
 */
public class ITCRemoteUnicast extends UnicastRemoteObject implements ITCRemote,
		Serializable, Cloneable {
	/**
	 * Version number unique identificator.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates and exports a new <code>ITCRemoteUnicast</code> object 
	 * using an anonymous port.
	 * 
	 * @throws RemoteException if failed to export object
	 */
	public ITCRemoteUnicast() throws RemoteException {
		super();
	}

	/**
	 * Creates and exports a new <code>ITCRemoteUnicast</code> object 
	 * using the particular supplied port.
	 * 
	 * @param port the port number on which the remote object receives 
	 * calls 
	 * @throws RemoteException if failed to export object
	 */
	public ITCRemoteUnicast(int port) throws RemoteException {
		super(port);
	}

	/**
	 * Creates and exports a new <code>ITCRemoteUnicast</code> object 
	 * using the particular supplied port and socket factories.
	 *  
	 * @param port the port number on which the remote object receives 
	 * calls 
	 * @param csf the client-side socket factory for making calls to 
	 * the remote object
	 * @param ssf the server-side socket factory for receiving remote 
	 * calls 
	 * @throws RemoteException if failed to export object
	 */
	public ITCRemoteUnicast(int port, RMIClientSocketFactory csf,
			RMIServerSocketFactory ssf) throws RemoteException {
		super(port, csf, ssf);
	}

	/**
	 * Removes the remote object from the RMI runtime.
	 * 
	 * @param force if <code>true</code>, unexports the object even 
	 * if there are pending or in-progress calls
	 * @return <code>true</code> if operation is successful
	 * @throws RemoteException if failed to unexport object
	 */
	public boolean clean(boolean force) throws RemoteException {
		return unexportObject(this, force);
	}

	/**
	 * Returns a clone of the remote object that is distinct 
	 * from the original.
	 * 
	 * @return the new remote object 
	 * @throws RemoteException if the remote operation fails
	 */
	public ITCRemote myClone() throws RemoteException {
		try {
			return (ITCRemote) clone();
		} catch (CloneNotSupportedException e) {
			throw new RemoteException(e.getMessage());
		}
	}

	/**
	 * Inidicates if is working in the Server.
	 * 
	 * @return <code>true</code> if is working in the Server 
	 * @throws RemoteException if the remote operation fails
	 */
	public boolean imInServer() throws RemoteException {
		try {
			return Net.isOwnHost(UnicastRemoteObject.getClientHost());
		} catch (ServerNotActiveException e) {
			// server no work, so i'm in the server. 
			return true;
		} catch (SocketException e) {
			throw new RemoteException("fail looking if imInServel", e);
		}
	}

	/**
	 * Returns a string that represents the value of this remote object.
	 * 
	 * @return a string representation of the object
	 * @throws RemoteException if the remote operation fails
	 */
	public String getString() throws RemoteException {
		return toString();
	}
}
