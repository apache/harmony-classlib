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
package ar.org.fitc.test.rmi.integration.fase2.serverExecutor.executor;

import java.rmi.RemoteException;

import ar.org.fitc.test.rmi.integration.fase2.clientExecutor.executor.ServerExecutor;
import ar.org.fitc.test.rmi.integration.fase2.serverExecutor.AbstractITCRemote;

/**
 * Abstract class that define the execute method.
 * 
 * @author Jorge Rafael
 * @author Marcelo Arcidiacono
 * 
 * @version 1.0
 */
public abstract class AbstractServerExecutor extends AbstractITCRemote implements ServerExecutor {

	/**
	 * Default constructor.
	 * 
	 * @throws RemoteException if the remote operation fails
	 */
	public AbstractServerExecutor() throws RemoteException {
		super();
	}

	/**
	 * Receives an arbitrary number of arguments and returns a generic 
	 * object. 
	 * 
	 * @param arguments an arbitrary number of arguments 
	 * @return a generic object
	 * @throws RemoteException if the remote operation fails
	 */
	abstract public Object execute(Object... arguments) throws RemoteException;
}
