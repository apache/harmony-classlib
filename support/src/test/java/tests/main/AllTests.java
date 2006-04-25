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

package tests.main;

import junit.extensions.TestSetup;
import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestSuite;
import tests.util.SomeTests;

public class AllTests {

	protected final static String TEST_COMPLETE_PROP_NAME = "confirmVMAlive";
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(AllTests.suite());
	}

	public static Test suite() {
		TestSuite suite = new TestSuite("All test suites");
		// $JUnit-BEGIN$
		suite.addTest(tests.archive.AllTests.suite());
		suite.addTest(tests.luni.AllTests.suite());
		suite.addTest(tests.nio_char.AllTests.suite());
		suite.addTest(org.apache.harmony.text.tests.AllTests.suite());
		// $JUnit-END$

		// Wrap all tests in the exclusion decorator
		SomeTests st = new SomeTests(suite);

		// Wrap the exclusion decorator with another decorator that helps
		// identify if the test run made it all the way to the end.
		TestSetup wrapper = new TestSetup(st) {
			protected void tearDown() {
				/*
				 * Only carry out the check to see if the VM survived the tests
				 * when requested to do so.
				 */
				String confirmCompletion = System.getProperty(TEST_COMPLETE_PROP_NAME);
				if (confirmCompletion != null && confirmCompletion.equals("true")) {
					confirmTestCompletion();
				}
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see junit.framework.Test#countTestCases()
			 */
			public int countTestCases() {
				int result = this.getTest().countTestCases();
				String confirmCompletion = System.getProperty(TEST_COMPLETE_PROP_NAME);
				if (confirmCompletion != null && confirmCompletion.equals("true")) {
					// This wrapper adds an extra test and so affects
					// the total number of tests that get run.
					result++;
				}
				return result;
			}
		};

		return wrapper;
	}

	/*
	 * This method creates a file at ${user.home}/junitCompleted, and is run
	 * only after all junit tests have completed. The existence of this file
	 * indicates that the vm being tested has not died before completing.
	 */
	public static void confirmTestCompletion() {
		String fileLocation = System.getProperty("user.home")
				+ "/junitCompleted";
		java.io.File fileHandle = new java.io.File(fileLocation);
		try {
			// Create the empty file - assert if we cant create or it already
			// exists
			Assert.assertTrue(fileHandle.createNewFile());
		} catch (java.io.IOException e) {
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
}
