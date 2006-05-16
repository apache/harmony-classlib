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

import java.rmi.RemoteException;

import ar.org.fitc.test.rmi.integration.fase2.ITCRemoteUnicast;

/**
 * Testing class for <code>ITCRemoteUnicast</code>.
 * 
 * @author Jorge Rafael 
 * @author Marcelo Arcidiacono
 *
 * @version 1.0
 */
public class TestITCRemoteUnicast extends TestITCRemote {
	
	/**
	 * Creates a new <code>ITCRemoteUnicast</code>.
	 * 
	 * @throws RemoteException if failed to export object
	 */
	protected void setUp() throws Exception {
		ret = new ITCRemoteUnicast();
		super.setUp();
	}
}
