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

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.ServerNotActiveException;
import java.security.Permission;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.TreeSet;
import java.util.logging.Logger;

import ar.org.fitc.test.rmi.integration.fase2.ITCRemoteUnicast;
import ar.org.fitc.test.rmi.integration.fase2.Net;
import ar.org.fitc.test.rmi.integration.fase2.interfaces.ITCRemote;

class Aleatoriator implements Comparator<String> {

	Random rnd = new Random();
	
	public int compare(String o1, String o2) {
		if (rnd.nextBoolean()) {
			return 1;
		} else {
			return -1;
		}
	}
	
}
/**
 * Manage the reporting of IP´s and host name of the servers.
 * 
 * @author Jorge Rafael
 * @author Marcelo Arcidiacono
 * 
 * @version 1.0
 */

public class ReportIPServer extends ITCRemoteUnicast implements ReportIP {
	
	public static String arrayToString(Object[] arg) {
		if (arg.length == 0) {
			return "[]";
		} else {
			String result = "[" + arg[0].toString();
			int i = 1;
			while(i < arg.length) {
				result += ", " + arg[i].toString();
			} 
			result += "]";
			return result;
		}
	}
	
	private static Comparator<String> compar = new Aleatoriator();
	/**
	 * For log purpose. 
	 */
	transient private final static Logger log = Logger
			.getLogger("ar.org.fitc.test.rmi.integration.fase2.executor");

	/**
	 * Version number unique identificator.
	 */
	private static final long serialVersionUID = 8197377575540620278L;

	/**
	 * The server-host IP. 
	 */
	protected static String SERVER_HOST = "10.100.2.230";

	/**
	 * A <code>TreeSet</code> of client-host IP´s. 
	 */
	protected AbstractCollection<String> listIP = new TreeSet<String>();

	/**
	 * Default constructor.
	 * 
	 * @throws RemoteException if the exportation fails
	 */
	public ReportIPServer() throws RemoteException {
		super();
	}

	/**
	 * Constructs a <code>ReportIPServer</code> using a particular 
	 * supplied port.
	 * 
	 * @param port the port number on which the remote object receives 
	 * calls  
	 * @throws RemoteException if failed to export object
	 */
	public ReportIPServer(int port) throws RemoteException {
		super(port);
	}

	/**
	 * Constructs a <code>ReportIPServer</code> using a particular 
	 * supplied port and socket factories.
	 * 
	 * @param port the port number on which the remote object receives 
	 * calls 
	 * @param csf the client-side socket factory for making calls to 
	 * the remote object
	 * @param ssf the server-side socket factory for receiving remote 
	 * calls
	 * @throws RemoteException if failed to export object
	 */
	public ReportIPServer(int port, RMIClientSocketFactory csf,
			RMIServerSocketFactory ssf) throws RemoteException {
		super(port, csf, ssf);
	}

	/**
	 * Constructs a collection with client-hosts IP´s.
	 * 
	 * @throws RemoteException if the remote operation fails
	 */
	public void report() throws RemoteException {
		try {
			synchronized (listIP) {
				if (listIP.add(getClientHost())) {
					System.out.println("add client:" + getClientHost());
				}
			}
		} catch (ServerNotActiveException e) {
			log.info("Report IP isn't working");
		}
	}

	/**
	 * Returns the host name if the service is activated.
	 * 
	 * @return the host name
	 * @throws RemoteException if the remote operation fails
	 */
	public String myHostName() throws RemoteException {
		try {
			return getClientHost();
		} catch (ServerNotActiveException e) {
			return SERVER_HOST;
		}
	}

	/**
	 * Returns a client-host IP of the collection.
	 * 
	 * @return a client-host IP
	 * @throws RemoteException if the remote operation fails
	 */
	public String[] getIP() throws RemoteException {
		String[] result;

		synchronized (listIP) {
			result = listIP.toArray(new String[0]);
		}
		Arrays.sort(result, compar);
		log.info("list of hosts: " + arrayToString(result));
		return result;
	}
	/**
	 * Sets the server-host with the specified name.
	 * 
	 * @param host the specified name
	 * @throws UnknownHostException if the host cannot to be determined
	 */
	public static void setServerHost(String host) throws UnknownHostException {
		InetAddress.getByName(host);
		SERVER_HOST = host;
	}

	/**
	 * For each client-host in the collection obtains a remote 
	 * reference if fails removes the IP from the collection.   
	 *
	 * @throws InterruptedException if another thread interrupts the 
	 * wait
	 */
	public void checkIP() throws InterruptedException {
		synchronized (listIP) {
			AbstractCollection<String> removeIP = new TreeSet<String>();

			for (String ip : listIP) {
				try {
					((ITCRemote) ClientExecutor.getExecutor(ip)).getString();
				} catch (Exception e) {
					removeIP.add(ip);
				}
			}
			for (String ip : removeIP) {
				listIP.remove(ip);
			}
		}
	}

	/**
	 * Obtains the <code>Registry</code> and a stub of the remote 
	 * object bounded in the <code>Registry</code>. Puts the host name 
	 * of the remote object in the collection.
	 * 
	 */
	public static void doit() {
		try {
			Registry r = LocateRegistry.getRegistry(SERVER_HOST, Net
					.getRegistryPort());

			((ReportIP) r.lookup(BIND_NAME)).report();
		} catch (RemoteException e) {
			log.info("report has same problem: " + e.getMessage());
		} catch (NotBoundException e) {
			log.warning("report is not bound.");
		}
	}

	/**
	 * Obtains the <code>Registry</code> and a stub of the remote 
	 * object bounded in the <code>Registry</code>. Returns the IP 
	 * address.
	 * 
	 * @return the IP address
	 * @throws ReportIPException if a non-catched exception occurs
	 */
	public static String[] getit() throws ReportIPException {
		try {
			Registry r = LocateRegistry.getRegistry(SERVER_HOST, Net
					.getRegistryPort());
			return ((ReportIP) r.lookup(BIND_NAME)).getIP();
		} catch (RemoteException e) {
			log.info("report has same problem: " + e.getMessage());
			throw new ReportIPException("Can't found report server", e);
		} catch (NotBoundException e) {
			log.warning("report is not bound.");
			throw new ReportIPException("Can't found report server", e);
		}
		
	}

	/**
	 * Obtains the <code>Registry</code> and a stub of the remote 
	 * object bounded in the <code>Registry</code>. Returns the host 
	 * name.
	 * 
	 * @return the host name
	 * @throws ReportIPException if a non-catched exception occurs
	 */
	public static String localHost() throws ReportIPException {
		try {
			Registry r = LocateRegistry.getRegistry(SERVER_HOST, Net
					.getRegistryPort());
			return ((ReportIP) r.lookup(BIND_NAME)).myHostName();
		} catch (RemoteException e) {
			log.info("report has same problem: " + e.getMessage());
			throw new ReportIPException("Can't found report server", e);
		} catch (NotBoundException e) {
			log.warning("report is not bound.");
			throw new ReportIPException("Can't found report server", e);
		}
		
	}
	
	public static Registry getServerRegistry() throws RemoteException {
		return LocateRegistry.getRegistry(SERVER_HOST, Net.getRegistryPort());
	}
	
	static Registry reg;
	public static void main(String[] argv) {
		
		System.setSecurityManager(new RMISecurityManager() {
			public void checkPermission(Permission perm) {
			};
		});
		System.setProperty("ar.org.fitc.rmi.server.threadPoolKeepAliveTime", "1000");
		System.setProperty("ar.org.fitc.rmi.server.threadPoolCoreSize", "1");
//		System.setProperty("java.rmi.server.codebase",
//		                   "ftp://proftp:1@10.100.2.230/");
		System.setProperty("java.rmi.server.hostname", "10.100.2.230");
		final ReportIPServer rep;
		try {
			setServerHost(System.getProperty("java.rmi.server.hostname"));
			if (!Net.isOwnHost(SERVER_HOST)) {
				System.out.println("Report IP can't start in hostname "
						+ SERVER_HOST + " because this host is not localhost");
			} else {

				try {
					LocateRegistry.createRegistry(Net.getRegistryPort());
				} catch (RemoteException e1) {
				}
				
				reg = LocateRegistry.getRegistry(SERVER_HOST, Net.getRegistryPort());
				
				for (String service : reg.list()) {
					if (service.equals(BIND_NAME)) {
						throw new Exception(
								"ReportIP is running in other place");
					}
				}
				rep = new ReportIPServer();
				reg.bind(BIND_NAME, rep);

				System.out.print("ReportIPServer Start...");
				new Thread() {
					public void run() {
						while (true) {
							try {
								Thread.sleep(10 * 6000);
								rep.checkIP();
							} catch (InterruptedException e) {
								log.info("cheaking ip fail");
							} 
							
						}
					}
				}.start();
			}
		} catch (Exception e) {
			System.out.print("ReportIPServer no work: " + e.getMessage());
		}
	}
}
