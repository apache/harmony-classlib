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
package ar.org.fitc.test.rmi.integration.fase2.serverExecutor.main;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

import ar.org.fitc.test.rmi.integration.fase2.clientExecutor.executor.ReportIPException;
import ar.org.fitc.test.rmi.integration.fase2.clientExecutor.executor.ReportIPServer;
import ar.org.fitc.test.rmi.integration.fase2.serverExecutor.test.DGCTestCase;
import ar.org.fitc.test.rmi.integration.fase2.serverExecutor.test.ITCTestCase;
import ar.org.fitc.test.rmi.integration.fase2.serverExecutor.test.PortableTestCase;
import ar.org.fitc.test.rmi.integration.fase2.serverExecutor.test.PropagableTestCase;
import ar.org.fitc.test.rmi.integration.fase2.serverExecutor.test.RemoteRegistryTestCase;
import ar.org.fitc.test.rmi.integration.fase2.serverExecutor.test.ReportIPTestCase;

/**
 * Sets the properties for the start up.
 * 
 * @author Jorge Rafael
 * @author Marcelo Arcidiacono
 *
 * @version 1.0
 */
public class StartUp extends ITCTestCase {

	/**
	 * Indicates the number of <code>ClientExecutor</code>'s.
	 */
	public static int numberOfClientExecutor = 1;

	public static void main(String[] arg) throws RemoteException  {
		System.setProperty("java.rmi.server.codebase",
						"ftp://proftp:1@10.100.2.230/");
		System.setProperty("java.rmi.server.hostname", "10.100.2.230");
		
		
		Logger.getLogger("ar.org.fitc.test.rmi.integration.fase2.executor").setLevel(Level.OFF);

		try {
			{
				String n = System.getProperty("ar.org.fitc.test.numbreofclientexecutor");
				if (n != null) {
					numberOfClientExecutor = Integer.parseInt(n);
				}
			}
						
			try {//litle test at begins
				URLClassLoader cl = new URLClassLoader(new URL[] {new URL(System.getProperty("java.rmi.server.codebase"))});
				cl.loadClass(StartUp.class.getName());
			} catch (ClassNotFoundException e){
				fail("The java.rmi.server.codebase property is bad set");
			} catch (MalformedURLException e) {
				fail("The java.rmi.server.codebase property is bad set");
			}
			
			
			initRegistry();
			
			try {
				ReportIPServer.setServerHost(System.getProperty("java.rmi.server.hostname"));
			} catch (UnknownHostException e) {
				fail("The java.rmi.server.hostname property is bad set");
			}
			ReportIPServer.main(null);
				
			try {
				do {
					System.out.print(".");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
					}
				} while (ReportIPServer.getit().length < numberOfClientExecutor);
			} catch (ReportIPException e) {
				fail("The ReportIP fail with: " + e);
			}
			System.out.println();

			
			new ReportIPTestCase().run();
			new RemoteRegistryTestCase().run();
			new PropagableTestCase().run();
			new PortableTestCase().run();
			new DGCTestCase().run();
					
		} finally {
			// Forsing finish
			System.exit(0);
		}
	}
}
