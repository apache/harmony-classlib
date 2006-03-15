/* Copyright 2004 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tests.jndi;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Test suite that includes all tests for the JNDI project.
 */
public class AllTests {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(AllTests.suite());
	}

	public static Test suite() {
		TestSuite suite = new TestSuite("All JNDI test suites");
		// $JUnit-BEGIN$
		suite.addTest(tests.api.javax.naming.AllTests.suite());
		suite.addTest(tests.api.javax.naming.directory.AllTests.suite());
		suite.addTest(tests.api.javax.naming.event.AllTests.suite());
		suite.addTest(tests.api.javax.naming.ldap.AllTests.suite());
		suite.addTest(tests.api.javax.naming.spi.AllTests.suite());
		// $JUnit-END$
		return suite;
	}
}