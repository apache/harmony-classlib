/* Copyright 1998, 2005 The Apache Software Foundation or its licensors, as applicable
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

package tests.luni;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Test suite that includes all tests for the LUNI project.
 */
public class AllTests {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(AllTests.suite());
	}

	public static Test suite() {
		TestSuite suite = new TestSuite("All LUNI test suites");
		// $JUnit-BEGIN$
		suite.addTest(tests.api.java.io.AllTests.suite());
		suite.addTest(tests.api.java.lang.AllTests.suite());
		suite.addTest(tests.api.java.lang.ref.AllTests.suite());
		suite.addTest(tests.api.java.lang.reflect.AllTests.suite());
		suite.addTest(tests.api.java.net.AllTests.suite());
		suite.addTest(tests.api.java.util.AllTests.suite());
		suite.addTest(tests.impl.com.ibm.misc.util.AllTests.suite());
		// $JUnit-END$
		return suite;
	}
}