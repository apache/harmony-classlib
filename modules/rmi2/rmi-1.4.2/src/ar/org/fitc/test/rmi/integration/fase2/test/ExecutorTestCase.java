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
package ar.org.fitc.test.rmi.integration.fase2.test;

import ar.org.fitc.test.rmi.integration.fase2.Net;
import ar.org.fitc.test.rmi.integration.fase2.executor.ClientExecutor;
import ar.org.fitc.test.rmi.integration.fase2.executor.PropagableExecutor;
import ar.org.fitc.test.rmi.integration.fase2.executor.ReportIPServer;

/**
 * Testing class for a <code>PropagableExecutor</code>. 
 * 
 * @author Jorge Rafael
 * @author Marcelo Arcidiacono
 * 
 * @version 1.0
 */
public class ExecutorTestCase extends ITCTestCase {

	/**
	 * An arrays of hosts. 
	 */
	public String[] hosts;
	
	/**
	 * A <code>PropagableExecutor</code>.  
	 */
	public PropagableExecutor executor;
	
	/**
	 * Default constructor.
	 *
	 */
	public ExecutorTestCase() {
		super();
	}

	/**
	 * Constructs a <code>ExecutorTestCase</code> with a name. 
	 * 
	 * @param arg0 a name.
	 */
	public ExecutorTestCase(String arg0) {
		super(arg0);
	}

	/**
	 * Obtains a <code>PropagableExecutor</code> for a reported host.
	 * 
	 * @throws Exception if any exception occurs
	 */
	protected void setUp() throws Exception {
		hosts = ReportIPServer.getit();
		for (String host: hosts) { 
			if (!Net.isOwnHost(host)) {
				executor = ClientExecutor.getExecutor(host);
				break;
			}
		}
		
		super.setUp();
	}
	 
	/**
	 * Simple implementation.
	 * 
	 * @throws Exception if any exception occurs
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		hosts = null;
		executor = null;
	}
}
