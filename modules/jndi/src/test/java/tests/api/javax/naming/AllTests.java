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

package tests.api.javax.naming;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for tests.api.javax.naming");
		// $JUnit-BEGIN$
		suite.addTestSuite(TestInitialContextMock.class);
		suite.addTestSuite(TestReference.class);
		suite.addTestSuite(TestCannotProceedException.class);
		suite.addTestSuite(TestLinkException.class);
		suite.addTestSuite(TestLinkRef.class);
		suite.addTestSuite(TestCompositeName.class);
		suite.addTestSuite(TestInvalidNameException.class);
		suite.addTestSuite(TestNamingException.class);
		suite.addTestSuite(TestBinaryRefAddr.class);
		suite.addTestSuite(TestMalformedLinkException.class);
		suite.addTestSuite(TestNamingExceptions.class);
		suite.addTestSuite(TestLinkLoopException.class);
		suite.addTestSuite(TestBinding.class);
		suite.addTestSuite(TestNoInitialContextException.class);
		suite.addTestSuite(TestReferralException.class);
		suite.addTestSuite(TestNameClassPair.class);
		suite.addTestSuite(TestCompoundName.class);
		suite.addTestSuite(TestStringRefAddr.class);
		suite.addTestSuite(TestNotContextException.class);
		// $JUnit-END$
		return suite;
	}
}
