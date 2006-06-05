/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
/**
 * @author Hugo Beilis
 * @author Osvaldo Demo
 * @author Jorge Rafael
 * @version 1.0
 */

package ar.org.fitc.test.math.biginteger.methods;

import ar.org.fitc.test.util.TestSuiteAcumulable;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Test suit for all BigInteger Methods
 * 
 * 
 */
public class AllTestsBigIntegerMethods {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(AllTestsBigIntegerMethods.suite());
	}

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for ar.org.fitc.test.math.biginteger.methods");
		// $JUnit-BEGIN$
		suite.addTest(new TestSuiteAcumulable(TestAbsNegateSignum.class));
		suite.addTest(new TestSuiteAcumulable(TestAddSubtract.class));
		suite.addTest(new TestSuiteAcumulable(TestMultiply.class));
		suite
				.addTest(new TestSuiteAcumulable(
						TestNumberInterfaceValueOf.class));
		suite
				.addTest(new TestSuiteAcumulable(
						TestCompareToEqualsHashCode.class));
		suite.addTest(new TestSuiteAcumulable(TestModModPow.class));
		suite.addTest(new TestSuiteAcumulable(TestGcdModInverse.class));
		suite.addTest(new TestSuiteAcumulable(TestPow.class));
		suite.addTest(new TestSuiteAcumulable(TestBitLengthBitCount.class));
		suite.addTest(new TestSuiteAcumulable(TestDivideAndRemainder.class));
		suite
				.addTest(new TestSuiteAcumulable(
						TestTestBitGetLowestSetBit.class));
		suite.addTest(new TestSuiteAcumulable(TestMinMax.class));
		suite.addTest(new TestSuiteAcumulable(TestClearSetFlipBit.class));
		suite.addTest(new TestSuiteAcumulable(TestLogical.class));
		suite.addTest(new TestSuiteAcumulable(TestShifts.class));
		suite.addTest(new TestSuiteAcumulable(TestToStringToByteArray.class));
		suite.addTest(new TestSuiteAcumulable(TestProbablePrime.class));
		return suite;
	}

}
