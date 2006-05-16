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
package ar.org.fitc.test.rmi.integration.fase2.main;

import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import ar.org.fitc.test.rmi.integration.fase2.executor.ReportIPException;
import ar.org.fitc.test.rmi.integration.fase2.executor.ReportIPServer;
import ar.org.fitc.test.rmi.integration.fase2.test.BigReturnComunicationPerformanceTestCase;
import ar.org.fitc.test.rmi.integration.fase2.test.ComunicationPerformanceTestCase;
import ar.org.fitc.test.rmi.integration.fase2.test.DGCTestCase;
import ar.org.fitc.test.rmi.integration.fase2.test.ExportationPerformanceTestCase;
import ar.org.fitc.test.rmi.integration.fase2.test.ITCTestCase;
import ar.org.fitc.test.rmi.integration.fase2.test.OverheadComunicationPerformanceTestCase;
import ar.org.fitc.test.rmi.integration.fase2.test.PortableTestCase;
import ar.org.fitc.test.rmi.integration.fase2.test.PropagableTestCase;
import ar.org.fitc.test.rmi.integration.fase2.test.RemoteRegistryTestCase;
import ar.org.fitc.test.rmi.integration.fase2.test.ReportIPTestCase;

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
	
	/**
	 * @param arg
	 * @throws Exception
	 */
	public static void main(String[] arg) throws Exception {

		
		
		Logger.getLogger("ar.org.fitc.test.rmi.integration.fase2.executor")
		.setLevel(Level.OFF);
		System.setProperty("ar.org.fitc.rmi.server.threadPoolKeepAliveTime", "1000");
		System.setProperty("ar.org.fitc.rmi.server.threadPoolMaxSize", "10");
		System.setProperty("ar.org.fitc.rmi.server.threadPoolCoreSize", "1");
		
		
		try {
			{
				String n = System.getProperty("ar.org.fitc.test.numbreofclientexecutor");
				if (n != null) {
					numberOfClientExecutor = Integer.parseInt(n);
				}
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
					System.out.println(arrayToString(ReportIPServer.getit()));
				} while (ReportIPServer.getit().length < numberOfClientExecutor);
			} catch (ReportIPException e) {
				fail("The ReportIP fail with: " + e);
			}
			System.out.println();

			
			
			new ReportIPTestCase().run();
			new RemoteRegistryTestCase().run();
			new PropagableTestCase().run();
			new PortableTestCase().run();
//			new RMISocketFactoryTestCase().run();
			new ComunicationPerformanceTestCase().run();
			new OverheadComunicationPerformanceTestCase().run();
			new ExportationPerformanceTestCase().run();
			new BigReturnComunicationPerformanceTestCase().run();
			new DGCTestCase().run();
			
			registry = null;


		} finally {
			// Forcing finish
			System.exit(0);
		}
	}
}
