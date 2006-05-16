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
package ar.org.fitc.test.rmi.integration.fase2.serverExecutor;

import java.rmi.RemoteException;

import ar.org.fitc.test.rmi.integration.fase2.serverExecutor.executor.AbstractServerExecutor;
import ar.org.fitc.test.rmi.integration.fase2.serverExecutor.interfaces.LogRemote;

/**
 * Moves references of remote objects.
 * 
 * @author Jorge Rafael
 * @author Marcelo Arcidiacono
 * 
 * @version 1.0
 */
public class MoveRemoteObject extends AbstractServerExecutor {

	/**
	 * Version number unique identificator.
	 */	
	private static final long serialVersionUID = 3L;

	/**
	 * Must initialize a <code>LogRemote</code>.
	 */
	public LogRemote obj;
	
	/**
	 * Constructs a <code>MoveRemoteObject</code> with a <code>
	 * LogRemote</code>.
	 *  
	 * @param containt a <code>LogRemote</code> object
	 * @throws RemoteException if the remote operation fails
	 */
	public MoveRemoteObject(LogRemote containt) throws RemoteException {
		super();
		obj = containt;
	}

	/**
	 * Indicates if is a local o remote execution.
	 * 
	 * @param arguments not uses here
	 * @return a <code>null</code> value
	 * @throws RemoteException if a remote operation fails
	 */
	@Override
	public Object execute(Object... arguments) throws RemoteException {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {}
		if (obj.imInServer()) {
			obj.println("local run");
		} else {
			obj.println("remote run");
		}
		return null;
	}
}
