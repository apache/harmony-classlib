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

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
                "Suite for org.apache.harmony.jndi.tests.javax.naming");
		// $JUnit-BEGIN$
		suite.addTestSuite(InitialContextMockTest.class);
		suite.addTestSuite(ReferenceTest.class);
		suite.addTestSuite(CannotProceedExceptionTest.class);
		suite.addTestSuite(LinkExceptionTest.class);
		suite.addTestSuite(LinkRefTest.class);
		suite.addTestSuite(CompositeNameTest.class);
		suite.addTestSuite(InvalidNameExceptionTest.class);
		suite.addTestSuite(NamingExceptionTest.class);
		suite.addTestSuite(BinaryRefAddrTest.class);
		suite.addTestSuite(MalformedLinkExceptionTest.class);
		suite.addTestSuite(NamingExceptionsTest.class);
		suite.addTestSuite(LinkLoopExceptionTest.class);
		suite.addTestSuite(BindingTest.class);
		suite.addTestSuite(NoInitialContextExceptionTest.class);
		suite.addTestSuite(ReferralExceptionTest.class);
		suite.addTestSuite(NameClassPairTest.class);
		suite.addTestSuite(CompoundNameTest.class);
		suite.addTestSuite(StringRefAddrTest.class);
		suite.addTestSuite(NotContextExceptionTest.class);
		// $JUnit-END$
		return suite;
	}
}
