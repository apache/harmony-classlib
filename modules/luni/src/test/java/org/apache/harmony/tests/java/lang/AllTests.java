/* Copyright 2006 The Apache Software Foundation or its licensors, as applicable
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

package org.apache.harmony.tests.java.lang;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(AllTests.suite());
	}

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for org.apache.harmony.tests.java.lang");
		//$JUnit-BEGIN$
		suite.addTestSuite(CharacterTest.class);
		suite.addTestSuite(ByteTest.class);
		suite.addTestSuite(FloatTest.class);
		suite.addTestSuite(StringBuilderTest.class);
		suite.addTestSuite(BooleanTest.class);
		suite.addTestSuite(StringBufferTest.class);
		suite.addTestSuite(SecurityManagerTest.class);
		suite.addTestSuite(DoubleTest.class);
		suite.addTestSuite(IntegerTest.class);
		suite.addTestSuite(ShortTest.class);
		suite.addTestSuite(LongTest.class);
		suite.addTestSuite(TypeNotPresentExceptionTest.class);
		suite.addTestSuite(StringTest.class);
		//$JUnit-END$
		return suite;
	}

}
