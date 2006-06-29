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

		suite.addTestSuite(ArithmeticExceptionTest.class);
		suite.addTestSuite(ArrayIndexOutOfBoundsExceptionTest.class);
		suite.addTestSuite(ArrayStoreExceptionTest.class);
		suite.addTestSuite(BooleanTest.class);
		suite.addTestSuite(ByteTest.class);
		suite.addTestSuite(CharacterTest.class);
		suite.addTestSuite(ClassTest.class);
		suite.addTestSuite(ClassCastExceptionTest.class);
		suite.addTestSuite(ClassLoaderTest.class);
		suite.addTestSuite(ClassNotFoundExceptionTest.class);
		suite.addTestSuite(CloneNotSupportedExceptionTest.class);
		suite.addTestSuite(CompilerTest.class);
		suite.addTestSuite(DoubleTest.class);
		suite.addTestSuite(ErrorTest.class);
		suite.addTestSuite(ExceptionTest.class);
		suite.addTestSuite(ExceptionInInitializerErrorTest.class);
		suite.addTestSuite(FloatTest.class);
		suite.addTestSuite(IllegalAccessErrorTest.class);
		suite.addTestSuite(IllegalAccessExceptionTest.class);
		suite.addTestSuite(IllegalArgumentExceptionTest.class);
		suite.addTestSuite(IllegalMonitorStateExceptionTest.class);
		suite.addTestSuite(IllegalStateExceptionTest.class);
		suite.addTestSuite(IllegalThreadStateExceptionTest.class);
		suite.addTestSuite(IncompatibleClassChangeErrorTest.class);
		suite.addTestSuite(IndexOutOfBoundsExceptionTest.class);
		suite.addTestSuite(InheritableThreadLocalTest.class);
		suite.addTestSuite(InstantiationErrorTest.class);
		suite.addTestSuite(InstantiationExceptionTest.class);
		suite.addTestSuite(IntegerTest.class);
		suite.addTestSuite(InternalErrorTest.class);
		suite.addTestSuite(InterruptedExceptionTest.class);
		suite.addTestSuite(LinkageErrorTest.class);
		suite.addTestSuite(LongTest.class);
		suite.addTestSuite(MathTest.class);
		suite.addTestSuite(NegativeArraySizeExceptionTest.class);
		suite.addTestSuite(NoClassDefFoundErrorTest.class);
		suite.addTestSuite(NoSuchFieldErrorTest.class);
		suite.addTestSuite(NoSuchFieldExceptionTest.class);
		suite.addTestSuite(NoSuchMethodErrorTest.class);
		suite.addTestSuite(NoSuchMethodExceptionTest.class);
		suite.addTestSuite(NullPointerExceptionTest.class);
		suite.addTestSuite(NumberTest.class);
		suite.addTestSuite(NumberFormatExceptionTest.class);
		suite.addTestSuite(ObjectTest.class);
		suite.addTestSuite(OutOfMemoryErrorTest.class);
		suite.addTestSuite(PackageTest.class);
		suite.addTestSuite(ProcessTest.class);
		suite.addTestSuite(RuntimeTest.class);
		suite.addTestSuite(RuntimeExceptionTest.class);
		suite.addTestSuite(RuntimePermissionTest.class);
		suite.addTestSuite(SecurityExceptionTest.class);
		suite.addTestSuite(SecurityManagerTest.class);
		suite.addTestSuite(ShortTest.class);
		suite.addTestSuite(StackOverflowErrorTest.class);
		suite.addTestSuite(StrictMathTest.class);
		suite.addTestSuite(StringTest.class);
		suite.addTestSuite(StringBufferTest.class);
		suite.addTestSuite(StringIndexOutOfBoundsExceptionTest.class);
		suite.addTestSuite(SystemTest.class);
		suite.addTestSuite(ThreadTest.class);
		suite.addTestSuite(ThreadDeathTest.class);
		suite.addTestSuite(ThreadGroupTest.class);
		suite.addTestSuite(ThreadLocalTest.class);
		suite.addTestSuite(ThrowableTest.class);
		suite.addTestSuite(UnknownErrorTest.class);
		suite.addTestSuite(UnsatisfiedLinkErrorTest.class);
		suite.addTestSuite(UnsupportedOperationExceptionTest.class);
		suite.addTestSuite(VerifyErrorTest.class);
		suite.addTestSuite(VirtualMachineErrorTest.class);
		// $JUnit-END$

		return suite;
	}
}
