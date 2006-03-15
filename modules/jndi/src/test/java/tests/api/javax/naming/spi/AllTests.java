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

package tests.api.javax.naming.spi;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for tests.api.javax.naming.spi");
		// $JUnit-BEGIN$
		suite.addTestSuite(TestDirectoryManager.class);
		suite.addTestSuite(TestDirStateFactoryResult.class);
		// suite.addTestSuite(TestNamingManagerExplore.class); // run in seperate vm
		// suite.addTestSuite(TestDirectoryManagerBuilder.class); // run in seperate vm
		// suite.addTestSuite(TestNamingManagerBuilder.class); // run in seperate vm
		suite.addTestSuite(TestNamingManager.class);
		suite.addTestSuite(TestResolveResult.class);
		suite.addTestSuite(TestDirectoryManagerJCK.class);
		// $JUnit-END$
		return suite;
	}
}
