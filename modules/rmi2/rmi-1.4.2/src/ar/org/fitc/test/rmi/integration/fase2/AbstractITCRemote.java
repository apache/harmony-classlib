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

import java.io.Serializable;
import java.net.SocketException;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;

import ar.org.fitc.test.rmi.integration.fase2.interfaces.ITCRemote;

/**
 * Implentation of an <code>ITCRemote</code> object.
 * 
 * @author Jorge Rafael
 * @author Marcelo Arcidiacono
 * 
 * @version 1.0
 */
public class AbstractITCRemote implements ITCRemote, Cloneable, Serializable {
	
	/**
	 * Version number unique identificator.
	 */	
	private static final long serialVersionUID = 1324L;
	
	/**
	 * Default constructor.
	 *
	 */
	public AbstractITCRemote() {
		super();
	}
	
	/**
	 * Removes the remote object from the RMI runtime.
	 * 
	 * @param force if <code>true</code>, unexports the object even 
	 * if there are pending or in-progress calls
	 * @return true if operation is successful
	 * @throws RemoteException if failed to unexport object
	 */
	public boolean clean(boolean force) throws RemoteException {
		return UnicastRemoteObject.unexportObject(this, force);
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
