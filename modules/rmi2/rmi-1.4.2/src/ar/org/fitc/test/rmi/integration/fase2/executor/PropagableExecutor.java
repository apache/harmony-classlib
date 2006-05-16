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

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Provides a mechanisms for propagate an executor. 
 * 
 * @author Jorge Rafael
 * @author Marcelo Arcidiacono
 * 
 * @version 1.0
 */
public interface PropagableExecutor extends Remote, RemoteExecutor {

	/**
	 * A constant string that represents the name of an object to bind.
	 */
	public final String CLIENT_SERVICE_NAME = "ClientExcecutor";

	/**
	 * A generic method that receives a host name, a <code>
	 * ServerExecutor</code>, number of executions and an arbitrary 
	 * number of arguments and return a generic object.
	 * 
	 * @param host a string that contains the host 
	 * @param obj a <code>ServerExecutor</code>
	 * @param times of the executions
	 * @param arguments an arbitrary number of arguments 
	 * @return a generic object
	 * @throws RemoteException if the remote operation fails
	 */
	public Object execute(String host, ServerExecutor obj, int times,
			Object... arguments) throws RemoteException;

	/**
	 * A generic method that receives an array of host names, a <code>
	 * ServerExecutor</code>, number of executions and an arbitrary 
	 * number of arguments and return boolean value.
	 * 
	 * @param host a string array that contains the hosts 
	 * @param obj a <code>ServerExecutor</code>
	 * @param times of the executions
	 * @param arguments an arbitrary number of arguments 
	 * @return <code>true</code> if the execution was successful
	 * @throws RemoteException if the remote operation fails
	 */
	public boolean execute(String[] host, ServerExecutor obj, int times,
			Object... arguments) throws RemoteException;

	/**
 	 * A generic method that receives an array of host names, an index 
 	 * of execution a <code>ServerExecutor</code>, number of executions 
 	 * and an arbitrary number of arguments and return boolean value.
 	 * 
 	 * @param host a string array that contains the hosts 
	 * @param index of execution
	 * @param obj a <code>ServerExecutor</code>
	 * @param times of the executions
	 * @param arguments an arbitrary number of arguments
	 * @return <code>true</code> if the execution was successful
	 * @throws RemoteException if the remote operation fails
	 */
	public boolean execute(String[] host, int index, ServerExecutor obj,
			int times, Object... arguments) throws RemoteException;
}
