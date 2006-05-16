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

import java.lang.ref.WeakReference;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import ar.org.fitc.test.rmi.integration.fase2.executor.AbstractServerExecutor;
import ar.org.fitc.test.rmi.integration.fase2.executor.ClientExecutor;
import ar.org.fitc.test.rmi.integration.fase2.executor.ReportIPException;
import ar.org.fitc.test.rmi.integration.fase2.executor.ReportIPServer;
import ar.org.fitc.test.rmi.integration.fase2.interfaces.ITCRemote;

/**
 * Forces the remote execution of garbage collector.
 * 
 * @author Jorge Rafael
 * @author Marcelo Arcidiacono
 * 
 * @version 1.0
 */
public class GCRemote extends AbstractServerExecutor {

	/**
	 * Version number unique identificator.
	 */	
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 *	 
	 * @throws RemoteException if the remote operation fail
	 */
	public GCRemote() throws RemoteException {
		super();	
	}

	/**
	 * Creates a <code>WeakReference</code> and forces garbage 
	 * collection for this <code>WeakReference</code>.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static void forceGC() {
		WeakReference ref = new WeakReference(new Integer(234523));
		forceGC(ref);
	}
	
	/**
	 * Forces garbage collection while the <code>WeakReference</code> 
	 * received will not to be null.
	 *  
	 * @param ref the specified <code>WeakReference</code>
	 */
	public static void forceGC(WeakReference ref) {
		while (ref.get() != null) {
			System.gc();
		}
	}
	
	/**
	 * In each host reported executes a <code>GCRemote</code> 
	 * object. It forces remotely the garbage collection.
	 * 
	 */
	public static void forceRemoteGC() {
		try {
			String[] hosts = ReportIPServer.getit();
			for (final String host: hosts) {	
				ClientExecutor.getExecutor(host).execute(new GCRemote(), 1);
			}
		} catch (ReportIPException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * In each host reported executes a <code>GCRemote</code> 
	 * object. It forces remotely the garbage collection for a 
	 * specified <code>WeakReference</code>.
	 * 
	 * @param ref the specified <code>WeakReference</code>.
	 */
	public static void forceRemoteGC(WeakReference ref) {
		try {
			String[] hosts = ReportIPServer.getit();
			for (final String host: hosts) {
					ClientExecutor.getExecutor(host).execute(new GCRemote(), 0, ref.get());
			}
		} catch (ReportIPException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Forces garbage collection. If an argument is an instance of 
	 * <code>ITCRemote<code> creates a <code>WeakReference</code> 
	 * to it. 
	 * 
	 * @param arguments it can be a <code>ITCRemote</code> object
	 * @return a <code>null</code> value
	 * @throws RemoteException if the remote operation fails 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object execute(Object... arguments) throws RemoteException {
		if (arguments.length > 0 && arguments[0] instanceof ITCRemote) {
			WeakReference ref = new WeakReference(arguments[0]);
			arguments = null;
			forceGC(ref);
		} else {
			forceGC();
		}
		return null;
	}
}
