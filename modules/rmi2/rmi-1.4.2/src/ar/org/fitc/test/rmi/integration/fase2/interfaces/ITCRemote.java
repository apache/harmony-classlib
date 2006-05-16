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
package ar.org.fitc.test.rmi.integration.fase2.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Provides several methods for the implementation of objects used 
 * in the series of tests.
 * 
 * @author Jorge Rafael
 * @author Marcelo Arcidiacono
 * 
 * @version 1.0
 */
public interface ITCRemote extends Remote {

	/**
	 * Removes the remote object from the RMI runtime.
	 * 
	 * @param force if <code>true</code>, unexports the object even 
	 * if there are pending or in-progress calls
	 * @return <code>truez/code> if operation is successful
	 * @throws RemoteException if failed to unexport object
	 */
	public boolean clean(boolean force) throws RemoteException;

	/**
	 * Returns a clone of the remote object that is distinct 
	 * from the original.
	 * 
	 * @return the new remote object 
	 * @throws RemoteException if the remote operation fails
	 */
	public ITCRemote myClone() throws RemoteException;

	/**
	 * Inidicates if is working in the Server.
	 * 
	 * @return <code>true</code> if is working in the Server 
	 * @throws RemoteException if the remote operation fails
	 */
	public boolean imInServer() throws RemoteException;

	/**
	 * Returns a string that represents the value of this remote object.
	 * 
	 * @return a string representation of the object
	 * @throws RemoteException if the remote operation fails
	 */
	public String getString() throws RemoteException;
}
