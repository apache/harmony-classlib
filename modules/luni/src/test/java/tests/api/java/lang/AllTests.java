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

package tests.api.java.lang;

import org.apache.harmony.luni.tests.java.lang.BooleanTest;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(AllTests.suite());
	}

	public static Test suite() {
		TestSuite suite = new TestSuite("Tests for java.lang");
		// $JUnit-BEGIN$
		suite.addTestSuite(ArrayCopyTest.class);
		suite.addTestSuite(HelloWorldTest.class);

		suite.addTestSuite(BooleanTest.class);
		suite.addTestSuite(ByteTest.class);

		suite.addTestSuite(ClassTest.class);
		suite.addTestSuite(ClassLoaderTest.class);
		suite.addTestSuite(CompilerTest.class);
		suite.addTestSuite(DoubleTest.class);
		suite.addTestSuite(FloatTest.class);
		suite.addTestSuite(InheritableThreadLocalTest.class);
		suite.addTestSuite(IntegerTest.class);
		suite.addTestSuite(LongTest.class);
		suite.addTestSuite(MathTest.class);
		suite.addTestSuite(NumberTest.class);
		suite.addTestSuite(ObjectTest.class);
		suite.addTestSuite(PackageTest.class);
		suite.addTestSuite(ProcessTest.class);
		suite.addTestSuite(RuntimeTest.class);
		suite.addTestSuite(RuntimePermissionTest.class);
		suite.addTestSuite(SecurityManagerTest.class);
		suite.addTestSuite(ShortTest.class);
		suite.addTestSuite(StringTest.class);
		suite.addTestSuite(StringBufferTest.class);
		suite.addTestSuite(SystemTest.class);
		suite.addTestSuite(ThreadTest.class);
		suite.addTestSuite(ThreadDeathTest.class);
		suite.addTestSuite(ThreadGroupTest.class);
		suite.addTestSuite(ThreadLocalTest.class);
		// $JUnit-END$

		return suite;
	}
}
