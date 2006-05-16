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

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Provides several methods for managing a client-host collection.
 * 
 * @author Jorge Rafael
 * @author Marcelo Arcidiacono
 * 
 * @version 1.0
 */
public interface ReportIP extends Remote {

	/**
	 * A constant string that represents the name of an object to bind.
	 */
	public String BIND_NAME = "REPORT_IP_SERVER";

	/**
	 * Constructs a collection with client-hosts IP´s.
	 * 
	 * @throws RemoteException if the remote operation fails
	 */
	public void report() throws RemoteException;

	/**
	 * Returns a client-host IP of the collection.
	 * 
	 * @return a client-host IP
	 * @throws RemoteException if the remote operation fails
	 */
	public String[] getIP() throws RemoteException;

	/**
	 * Returns the host name if the service is activated.
	 * 
	 * @return the host name
	 * @throws RemoteException if the remote operation fails
	 */
	public String myHostName() throws RemoteException;
}
