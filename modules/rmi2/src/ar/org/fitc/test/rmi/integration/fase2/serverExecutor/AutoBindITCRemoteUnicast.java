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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import ar.org.fitc.test.rmi.integration.fase2.clientExecutor.ITCRemoteUnicast;
import ar.org.fitc.test.rmi.integration.fase2.serverExecutor.executor.ServerExecutor;

/**
 * Construct a <code>AutoBindITCRemoteUnicast</code> and automatically 
 * bind it.
 *
 * @author Jorge Rafael
 * @author Marcelo Arcidiacono
 * 
 * @version 1.0
 */
public class AutoBindITCRemoteUnicast extends ITCRemoteUnicast implements ServerExecutor, Serializable {

	/**
	 * Version number unique identificator.
	 */	
	private static final long serialVersionUID = 2L;
	
	/**
	 * A constant string that represents the name of an object to bind.
	 */
	public final static String BIND_NAME ="HIHIHIHI";  
	
	/**
	 * The default constructor.
	 * 
	 * @throws RemoteException if registry could not be contacted 
	 */
	public AutoBindITCRemoteUnicast() throws RemoteException {
		super();
		try {
			Naming.rebind(BIND_NAME, this);
		} catch (MalformedURLException e) {
		}
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
		return unexportObject(this, force);
	}

	/**
	 * Returns a reference, a stub, for the remote object associated 
	 * with the specified name. 
	 * 
	 * @param arguments not used here
	 * @return a reference for a remote object 
	 * @throws RemoteException if registry could not be contacted 
	 */
	public Object execute(Object... arguments) throws RemoteException {
		try {
			return Naming.lookup(BIND_NAME);
		} catch (MalformedURLException e) {
		} catch (RemoteException e) {
		} catch (NotBoundException e) {
		}
		return null;
	}
	
	/**
	 * Writes the non-static and non-transient fields of the current 
	 * class to the stream.
	 * 
	 * @param out the <code>ObjectOutputStream</code>
	 * @throws IOException if an I/O error occurs while writing 
	 * the stream  
	 */
	private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }
	
	/**
	 * Reads the non-static and non-transient fields of the current 
	 * class from the stream.
	 * 
	 * @param in the <code>ObjectInputStream</code>
	 * @throws IOException IOException if an I/O error occurs while 
	 * reading the stream  
	 * @throws ClassNotFoundException if the class of a serialized 
	 * object could not be found
	 */
	private void readObject(ObjectInputStream in) throws IOException,
    	ClassNotFoundException {
		in.defaultReadObject();
		try {
			toStub(this);
		} catch(NoSuchObjectException e) {
			exportObject(this, 10203);
		}
		try {
			Naming.rebind(BIND_NAME, this);
		} catch (MalformedURLException e) {
		}
	}
}
