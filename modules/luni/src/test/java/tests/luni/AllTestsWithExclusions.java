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
 * Test suite that includes only some of the tests for the LUNI project. 
 */
public class AllTestsWithExclusions {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(AllTestsWithExclusions.suite());
	}

	public static Test suite() {
		TestSuite suite = new TestSuite("Some LUNI test suites");
		suite.addTest(AllTests.suite());
		tests.util.SomeTests some = new tests.util.SomeTests(suite);
		return some;
	}
}