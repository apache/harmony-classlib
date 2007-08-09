/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.harmony.jndi.tests.javax.naming;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.naming.NamingException;

import junit.framework.TestCase;
import org.apache.harmony.jndi.tests.javax.naming.util.Log;

public class InitialContextAppTest extends TestCase {
	private static final Log log = new Log(InitialContextAppTest.class);

	public void testConstructor_App() throws NamingException, IOException {
		//Comment this test case out because this test case 
		//needs complex configuration about jndi properties.
		
//		log.setMethod("testConstructor_App");
//		InitialContext context = new InitialContext();
//		Hashtable props = context.getEnvironment();
//		// printHashtable(props);
//		Hashtable expected = TestInitialContextLib.readAllProps(null);
//		assertEquals(expected, props);
	}

	void printHashtable(Hashtable<?, ?> env) {
		// TO DO: Need to remove
		Enumeration<?> keys = env.keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			log.log(key + "=" + env.get(key));
		}
	}
}
