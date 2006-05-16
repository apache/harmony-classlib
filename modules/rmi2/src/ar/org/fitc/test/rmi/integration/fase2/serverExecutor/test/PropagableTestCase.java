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
package ar.org.fitc.test.rmi.integration.fase2.serverExecutor.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.util.Arrays;

import ar.org.fitc.test.rmi.integration.fase2.serverExecutor.PropagableTestRemote;
import ar.org.fitc.test.rmi.integration.fase2.clientExecutor.executor.ServerExecutor;


/**
 * Testing class for executing object on remote hosts. 
 * 
 * @author Jorge Rafael
 * @author Marcelo Arcidiacono
 *
 * @version 1.0
 */
public class PropagableTestCase extends ExecutorTestCase {

	/**
	 * A <code>ServerExecutor</code>.
	 */
	public ServerExecutor serv;
	
	/**
	 * Default constructor.
	 *
	 */
	public PropagableTestCase() {
		super();
	}

	/**
	 * Constructs a <code>PropagableTestCase</code> with a name. 
	 * 
	 * @param arg0 a name.
	 */
	public PropagableTestCase(String arg0) {
		super(arg0);
	}
	
	/**
	 * Creates a <code>PropagableTestRemote</code>.
	 * 
	 */
	protected void setUp() throws Exception {
		serv = (ServerExecutor) new PropagableTestRemote();
		super.setUp();
	}

	/**
	 * Forces the garbage collector.
	 * 
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		forceGC();
	}

	/**
	 * Simply executes an object on a host. This procedure is 
	 * repeated on all hosts.
	 * 
	 */
	public void testPropagableOneHost001() {
		for (final String host : hosts) {
			try {
				assertTrue("The execute host must be " + host,
						(Boolean) executor.execute(host, serv, 1,1, host));
			} catch (RemoteException e) {
				fail("Non work: " + e);
			}
		}
	}

	/**
	 * Executes an object on a unitary list of remote hosts. 
	 * This procedure is repeated on all reported hosts.
	 * 
	 */
	public void testPropagableOneHost002() {
		for (final String host : hosts) {
			try {
				assertTrue("The execute host must be " + host,
						(Boolean) executor.execute(new String[] { host }, serv,
								1,1, host));
			} catch (RemoteException e) {
				fail("Non work: " + e);
				
			}
		}
	}

	/**
	 * Simply executes an object on a list of remote hosts.
	 * 
	 * @throws SecurityException if occurs a security violation
	 * @throws NoSuchMethodException if a particular method cannot 
	 * be found
	 * @throws IllegalArgumentException if a method has been passed 
	 * an illegal or inappropriate argument
	 * @throws IllegalAccessException if tries to reflectively create 
	 * an instance 
	 * @throws InvocationTargetException wraps an exception thrown 
	 * by an invoked method or constructor
	 */
	public void testPropagableAtOnes001() throws SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {

		Method m = executor.getClass().getMethod("execute", String[].class,
				ServerExecutor.class, int.class, Object[].class);
		Object[] hostsP = new Object[hosts.length+1];
		hostsP[0] = hosts.length;
		System.arraycopy(hosts, 0, hostsP, 1, hosts.length);
		Object[] param = new Object[4];
		param[0] = hosts;
		param[1] = serv;
		param[2] = 1;
		param[3] = hostsP;
		assertTrue("The execute hosts must be " + Arrays.toString(hosts),
				(Boolean) m.invoke(executor, param));
	}
}
