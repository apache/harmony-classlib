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

package ar.org.fitc.test.math.bigdecimal;

import ar.org.fitc.test.math.whitebox.TestAddBigDecimal;
import ar.org.fitc.test.math.whitebox.TestBigDecimalCobertura;
import ar.org.fitc.test.math.whitebox.TestBigDecimalDivideAndRemainder;
import ar.org.fitc.test.math.whitebox.TestCompareToBigDecimal;
import ar.org.fitc.test.math.whitebox.TestMultiplyBigDecimal;
import ar.org.fitc.test.math.whitebox.TestNullBigDecimal;
import ar.org.fitc.test.math.whitebox.TestSubtractBigDecimal;
import ar.org.fitc.test.util.TestSuiteAcumulable;
import junit.framework.Test;
import junit.framework.TestSuite;
/**
 * TestSuit for all BigDecimal Methods and Constructors
 * 
 *
 */
public class AllTestsBigDecimal {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(AllTestsBigDecimal.suite());
	}

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for ar.org.fitc.test.math.bigdecimal");

		suite.addTest(new TestSuiteAcumulable(TestBigDecimalValueOf.class));
		suite.addTest(new TestSuiteAcumulable(TestBigDecimalMethods.class));
		suite.addTest(new TestSuiteAcumulable(TestBigDecimalPow.class));
		suite.addTest(new TestSuiteAcumulable(TestBigDecimalScale.class));
		suite.addTest(new TestSuiteAcumulable(TestBigDecimalScaleIntRoundingMode.class));
		suite.addTest(new TestSuiteAcumulable(TestBigDecimalScaleIntInt.class));
		suite.addTest(new TestSuiteAcumulable(TestBigDecimalScaleInt.class));
		suite.addTest(new TestSuiteAcumulable(TestBigDecimalSubtract.class));
		suite.addTest(new TestSuiteAcumulable(TestBigDecimalMethods2.class));
		suite.addTest(new TestSuiteAcumulable(TestBigDecimalConstructors.class));
		suite.addTest(new TestSuiteAcumulable(TestBigDecimalDivideMathContext.class));
		suite.addTest(new TestSuiteAcumulable(TestBigDecimalDivideInt.class));
		suite.addTest(new TestSuiteAcumulable(TestBigDecimalDivide.class));
		suite.addTest(new TestSuiteAcumulable(TestBigDecimalDivideIntInt.class));
		suite.addTest(new TestSuiteAcumulable(TestBigDecimalDivideIntRoundingMode.class));
		suite.addTest(new TestSuiteAcumulable(TestBigDecimalDivideRoundingMode.class));
		suite.addTest(new TestSuiteAcumulable(TestBigDecimalDivideToIntegralValue.class));	
       suite.addTestSuite(TestBigDecimalCobertura.class);
       suite.addTestSuite(TestNullBigDecimal.class);
        suite.addTestSuite(TestAddBigDecimal.class);
        suite.addTestSuite(TestCompareToBigDecimal.class);
        suite.addTestSuite(TestSubtractBigDecimal.class);
        suite.addTestSuite(TestMultiplyBigDecimal.class);
        suite.addTestSuite(TestBigDecimalDivideAndRemainder.class);
	
		return suite;
	}
}
