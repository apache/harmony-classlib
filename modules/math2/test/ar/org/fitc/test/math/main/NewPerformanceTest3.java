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

package ar.org.fitc.test.math.main;

import ar.org.fitc.test.math.bigdecimal.TestBigDecimalDivideIntInt;
import junit.extensions.RepeatedTest;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import ar.org.fitc.test.integration.math.TestIntegration;
import ar.org.fitc.test.math.bigdecimal.TestBigDecimalConstructors;
import ar.org.fitc.test.math.bigdecimal.TestBigDecimalDivide;
import ar.org.fitc.test.math.bigdecimal.TestBigDecimalDivideInt;
import ar.org.fitc.test.math.bigdecimal.TestBigDecimalDivideIntRoundingMode;
import ar.org.fitc.test.math.bigdecimal.TestBigDecimalDivideMathContext;
import ar.org.fitc.test.math.bigdecimal.TestBigDecimalDivideRoundingMode;
import ar.org.fitc.test.math.bigdecimal.TestBigDecimalDivideToIntegralValue;
import ar.org.fitc.test.math.bigdecimal.TestBigDecimalMethods;
import ar.org.fitc.test.math.bigdecimal.TestBigDecimalMethods2;
import ar.org.fitc.test.math.bigdecimal.TestBigDecimalPow;
import ar.org.fitc.test.math.bigdecimal.TestBigDecimalScale;
import ar.org.fitc.test.math.bigdecimal.TestBigDecimalScaleIntInt;
import ar.org.fitc.test.math.bigdecimal.TestBigDecimalScaleIntRoundingMode;
import ar.org.fitc.test.math.bigdecimal.TestBigDecimalSubtract;
import ar.org.fitc.test.math.bigdecimal.TestBigDecimalValueOf;
import ar.org.fitc.test.math.biginteger.TestBigIntegerConstructors;
import ar.org.fitc.test.math.biginteger.methods.TestAbsNegateSignum;
import ar.org.fitc.test.math.biginteger.methods.TestAddSubtract;
import ar.org.fitc.test.math.biginteger.methods.TestBitLengthBitCount;
import ar.org.fitc.test.math.biginteger.methods.TestClearSetFlipBit;
import ar.org.fitc.test.math.biginteger.methods.TestCompareToEqualsHashCode;
import ar.org.fitc.test.math.biginteger.methods.TestDivideAndRemainder;

import ar.org.fitc.test.math.biginteger.methods.TestGcdModInverse;
import ar.org.fitc.test.math.biginteger.methods.TestLogical;
import ar.org.fitc.test.math.biginteger.methods.TestMinMax;
import ar.org.fitc.test.math.biginteger.methods.TestModModPow;
import ar.org.fitc.test.math.biginteger.methods.TestMultiply;
import ar.org.fitc.test.math.biginteger.methods.TestNumberInterfaceValueOf;
import ar.org.fitc.test.math.biginteger.methods.TestPow;
import ar.org.fitc.test.math.biginteger.methods.TestProbablePrime;
import ar.org.fitc.test.math.biginteger.methods.TestShifts;
import ar.org.fitc.test.math.biginteger.methods.TestTestBitGetLowestSetBit;
import ar.org.fitc.test.math.biginteger.methods.TestToStringToByteArray;
import ar.org.fitc.test.math.mathcontext.TestMathContext;

public class NewPerformanceTest3 extends TestCase {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(NewPerformanceTest3.suite());
	}

	public static Test suite() {

		int i = 10000;

		TestSuite suite = new TestSuite("Performance Math");

		   suite.addTest(new RepeatedTest(new TestBigDecimalConstructors(
				"testBigDecimalBigInteger001"), i));
				suite.addTest(new RepeatedTest(new TestBigDecimalConstructors(
				"testBigDecimalBigInteger003"), i));

				suite.addTest(new RepeatedTest(new TestBigDecimalConstructors(
				"testBigDecimalBigIntegerMathContext003"), i));
				suite.addTest(new RepeatedTest(new TestBigDecimalConstructors(
				"testBigDecimalBigIntegerMathContext005"), i));		
				suite.addTest(new RepeatedTest(new TestBigDecimalConstructors(
				"testBigDecimalBigIntegerInt001"), i));
				suite.addTest(new RepeatedTest(new TestBigDecimalConstructors(
				"testBigDecimalBigIntegerInt002"), i));

				suite.addTest(new RepeatedTest(new TestBigDecimalConstructors(
				"testBigDecimalBigIntegerIntMathContext001"), i));

			suite.addTest(new RepeatedTest(new TestBigDecimalConstructors(
			"testBigDecimalBigIntegerIntMathContext002"), i));

				suite.addTest(new RepeatedTest(new TestBigDecimalConstructors(
				"testBigDecimalInt001"), i));
				suite.addTest(new RepeatedTest(new TestBigDecimalConstructors(
				"testBigDecimalInt002"), i));

				suite.addTest(new RepeatedTest(new TestBigDecimalConstructors(
				"testBigDecimalIntMathContext001"), i));
				suite.addTest(new RepeatedTest(new TestBigDecimalConstructors(
				"testBigDecimalIntMathContext003"), i));

				suite.addTest(new RepeatedTest(new TestBigDecimalConstructors(
				"testBigDecimalLong001"), i));
				suite.addTest(new RepeatedTest(new TestBigDecimalConstructors(
				"testBigDecimalLong005"), i));

				suite.addTest(new RepeatedTest(new TestBigDecimalConstructors(
				"testBigDecimalLongMathContext001"), i));
				suite.addTest(new RepeatedTest(new TestBigDecimalConstructors(
				"testBigDecimalLongMathContext005"), i));

				suite.addTest(new RepeatedTest(new TestBigDecimalConstructors(
				"testBigDecimalString001"), i));
				suite.addTest(new RepeatedTest(new TestBigDecimalConstructors(
				"testBigDecimalString003"), i));

				suite.addTest(new RepeatedTest(new TestBigDecimalConstructors(
				"testBigDecimalStringMathContext001"), i));
				suite.addTest(new RepeatedTest(new TestBigDecimalConstructors(
				"testBigDecimalStringMathContext006"), i));

				suite.addTest(new RepeatedTest(new TestBigDecimalConstructors(
				"testBigDecimalDouble001"), i));
				suite.addTest(new RepeatedTest(new TestBigDecimalConstructors(
				"testBigDecimalDouble002"), i));

				suite.addTest(new RepeatedTest(new TestBigDecimalConstructors(
				"testBigDecimalDoubleMathContext001"), i));
				suite.addTest(new RepeatedTest(new TestBigDecimalConstructors(
				"testBigDecimalDoubleMathContext005"), i));

				suite.addTest(new RepeatedTest(new TestBigDecimalConstructors(
				"testBigDecimalCharArray001"), i));
				suite.addTest(new RepeatedTest(new TestBigDecimalConstructors(
				"testBigDecimalCharArray002"), i));

			suite.addTest(new RepeatedTest(new TestBigDecimalConstructors(
			"testBigDecimalCharArrayMathContext002"), i));
			suite.addTest(new RepeatedTest(new TestBigDecimalConstructors(
			"testBigDecimalCharArrayMathContext005"), i));

			suite.addTest(new RepeatedTest(new TestBigDecimalConstructors(
			"testBigDecimalCharArrayIntInt001"), i));
			suite.addTest(new RepeatedTest(new TestBigDecimalConstructors(
			"testBigDecimalCharArrayIntInt002"), i));

			suite.addTest(new RepeatedTest(new TestBigDecimalConstructors(
			"testBigDecimalCharArrayIntIntMathContext001"), i));
			suite.addTest(new RepeatedTest(new TestBigDecimalConstructors(
			"testBigDecimalCharArrayIntIntMathContext007"), i));                

		         		suite.addTest(new RepeatedTest(new TestBigDecimalMethods(
					"testFloatValue002"), i));
			suite.addTest(new RepeatedTest(new TestBigDecimalMethods(
					"testIntValue002"), i));
			suite.addTest(new RepeatedTest(new TestBigDecimalValueOf(
			"testValueOfLongInt001"), i));
			suite.addTest(new RepeatedTest(new TestBigDecimalValueOf(
			"testValueOfLongInt006"), i));

			suite.addTest(new RepeatedTest(new TestBigDecimalValueOf(
			"testValueOfLong001"), i));
			suite.addTest(new RepeatedTest(new TestBigDecimalValueOf(
			"testValueOfLong006"), i));

			suite.addTest(new RepeatedTest(new TestBigDecimalValueOf(
			"testValueOfDouble001"), i));
			suite.addTest(new RepeatedTest(new TestBigDecimalValueOf(
			"testValueOfDouble007"), i));

			suite.addTest(new RepeatedTest(new TestBigDecimalMethods(
			"testHashCode001"), i));
			suite.addTest(new RepeatedTest(new TestBigDecimalMethods(
			"testHashCode005"), i));

			suite.addTest(new RepeatedTest(new TestBigDecimalMethods(
			"testEqualsObject001"), i));
			suite.addTest(new RepeatedTest(new TestBigDecimalMethods(
			"testEqualsObject004"), i));

			suite.addTest(new RepeatedTest(new TestBigDecimalMethods(
			"testToString001"), i));
			suite.addTest(new RepeatedTest(new TestBigDecimalMethods(
			"testToString007"), i));

			suite.addTest(new RepeatedTest(new TestBigDecimalMethods(
			"testLongValue001"), i));
			suite.addTest(new RepeatedTest(new TestBigDecimalMethods(
		      "testLongValue005"), i));

			suite.addTest(new RepeatedTest(new TestBigDecimalMethods(
			"testDoubleValue001"), i));
			suite.addTest(new RepeatedTest(new TestBigDecimalMethods(
		"testDoubleValue008"), i));

			suite.addTest(new RepeatedTest(new TestBigDecimalMethods(
			"testAddBigDecimal001"), i));
			suite.addTest(new RepeatedTest(new TestBigDecimalMethods(
			"testAddBigDecimal009"), i));

			suite.addTest(new RepeatedTest(new TestBigDecimalMethods(
			"testAddBigDecimalMathContext001"), i));
			suite.addTest(new RepeatedTest(new TestBigDecimalMethods(
			"testAddBigDecimalMathContext009"), i));

			suite.addTest(new RepeatedTest(new TestBigDecimalMethods(
			"testSubtractBigDecimal001"), i));
			suite.addTest(new RepeatedTest(new TestBigDecimalMethods(
			"testSubtractBigDecimal005"), i));

			suite.addTest(new RepeatedTest(new TestBigDecimalMethods(
			"testMultiplyBigDecimal001"), i));
			suite.addTest(new RepeatedTest(new TestBigDecimalMethods(
			"testMultiplyBigDecimal007"), i));

			suite.addTest(new RepeatedTest(new TestBigDecimalMethods(
			"testMultiplyBigDecimalMathContext001"), i));
			suite.addTest(new RepeatedTest(new TestBigDecimalMethods(
			"testMultiplyBigDecimalMathContext006"), i));

		       suite.addTest(new RepeatedTest(new TestBigDecimalDivide(
			"testDivideBigDecimal015"), i));
			suite.addTest(new RepeatedTest(new TestBigDecimalDivideMathContext(
			"testDivideBigDecimal006"), i));

			suite.addTest(new RepeatedTest(new TestBigDecimalDivideIntInt(
			"testDivideBigDecimalIntInt1358"), i));
			suite.addTest(new RepeatedTest(new TestBigDecimalDivideIntInt(
		 "testDivideBigDecimalIntInt1470"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalDivideIntRoundingMode(
		 "testDivideBigDecimalIntRoundingMode1358"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalDivideIntRoundingMode(
		 "testDivideBigDecimalIntRoundingMode368"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalDivideInt(
		 "testDivideBigDecimalInt806"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalDivideMathContext(
		 "testDivideBigDecimalInt002"), i));
		 
		 
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalDivide(
		 "testDivideBigDecimalRoundingMode001"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalDivide(
		 "testDivideBigDecimalRoundingMode002"), i));		
		 suite.addTest(new RepeatedTest(new TestBigDecimalDivideMathContext(
		 "testDivideBigDecimalMathContext207"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalDivideRoundingMode(
		 "testDivideBigDecimalRoundingMode367"), i));	
		 
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalDivideToIntegralValue(
		 "testdivideToIntegralValueBigDecimal026"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalDivideToIntegralValue(
		 "testdivideToIntegralValueBigDecimal017"), i));		
		 suite.addTest(new RepeatedTest(new TestBigDecimalDivideToIntegralValue(
		 "testDivideToIntegralValueBigDecimalMathContext015"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalDivideToIntegralValue(
		 "testDivideToIntegralValueBigDecimalMathContext019"), i));				
		 
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods(
		 "testRemainderBigDecimal001"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods(
		 "testRemainderBigDecimal005"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods(
		 "testRemainderBigDecimalMathContext001"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods(
		 "testRemainderBigDecimalMathContext006"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods(
		 "testDivideAndRemainderBigDecimal001"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods(
		 "testDivideAndRemainderBigDecimal007"), i));
		 
		 //	suite.addTest(new RepeatedTest(new TestBigDecimalPow("testPowInt001"),
		 //			i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalPow("testPowInt005"),
		 i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalPow(
		 "testPowIntMathContext001"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalPow(
		 "testPowIntMathContext003"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalScale("testScale001"),
		 i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalScale("testScale004"),
		 i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalScale(
		 "testUnscaledValue001"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalScale(
		 "testUnscaledValue003"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalScale(
		 "testScaleByPowerOfTen001"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalScale(
		 "testScaleByPowerOfTen005"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalScaleIntRoundingMode(
		 "testSetScaleIntRoundingMode001"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalScaleIntRoundingMode(
		 "testSetScaleIntRoundingMode004"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalScaleIntInt(
		 "testSetScaleIntInt001"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalScaleIntInt(
		 "testSetScaleIntInt007"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalSubtract(
		 "testSubtractBigDecimalMathContext2200"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalSubtract(
		 "testSubtractBigDecimalMathContext2199"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalSubtract(
		 "testSubtractMathContext003"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalSubtract(
		 "testSubtractMathContext004"), i));
		 
		 suite.addTest(new RepeatedTest(
		 new TestBigDecimalMethods2("testAbs001"), i));
		 suite.addTest(new RepeatedTest(
		 new TestBigDecimalMethods2("testAbs007"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testAbsMathContext001"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testAbsMathContext002"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testNegate001"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testNegate004"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testNegateMathContext001"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testNegateMathContext006"), i));
		 
		 suite.addTest(new RepeatedTest(
		 new TestBigDecimalMethods2("testPlus001"), i));
		 suite.addTest(new RepeatedTest(
		 new TestBigDecimalMethods2("testPlus004"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testPlusMathContext001"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testPlusMathContext004"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testSignum002"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testSignum003"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testPrecision001"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testPrecision007"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testRoundMathContext001"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testRoundMathContext003"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testMovePointLeftInt001"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testMovePointLeftInt004"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testMovePointRightInt001"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testMovePointRightInt003"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testStripTrailingZeros002"), i));
		 	suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 	"testStripTrailingZeros013"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testCompareToBigDecimal004"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testCompareToBigDecimal005"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testMinBigDecimal003"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testMinBigDecimal004"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testMaxBigDecimal001"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testMaxBigDecimal002"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testToEngineeringString001"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testToEngineeringString007"), i));
		 
		 	suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 	"testtoPlainString002"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testtoPlainString003"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testToBigInteger001"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testToBigInteger008"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testToBigIntegerExact001"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testToBigIntegerExact006"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testLongValueExact001"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testLongValueExact003"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testIntValueExact004"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testIntValueExact006"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testShortValueExact001"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testShortValueExact003"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testByteValueExact004"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalMethods2(
		 "testByteValueExact005"), i));
		 
		 suite.addTest(new RepeatedTest(
		 new TestBigDecimalMethods2("testUlp001"), i));
		 suite.addTest(new RepeatedTest(
		 new TestBigDecimalMethods2("testUlp003"), i));
		 
		
		 suite.addTest(new RepeatedTest(new TestBigDecimalDivideMathContext(
		 "testDivideBigDecimalIntInt001"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalDivideMathContext(
		 "testDivideBigDecimalIntInt002"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalDivideMathContext(
		 "testDivideBigDecimalIntRoundingMode001"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalDivideMathContext(
		 "testDivideBigDecimalIntRoundingMode002"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalDivideMathContext(
		 "testDivideBigDecimalInt001"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalDivideMathContext(
		 "testDivideBigDecimalInt002"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalDivideMathContext(
		 "testDivideBigDecimal001"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalDivideMathContext(
		 "testDivideBigDecimal002"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalDivideMathContext(
		 "testDivideBigDecimalRoundingMode001"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalDivideMathContext(
		 "testDivideBigDecimalRoundingMode002"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalDivideMathContext(
		 "testDivideBigDecimalMathContext004"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalDivideMathContext(
		 "testDivideBigDecimalMathContext005"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalDivideToIntegralValue(
		 "testdivideToIntegralValueBigDecimal001"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalDivideToIntegralValue(
		 "testdivideToIntegralValueBigDecimal003"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalDivideToIntegralValue(
		 "testDivideToIntegralValueBigDecimalMathContext003"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalDivideToIntegralValue(
		 "testDivideToIntegralValueBigDecimalMathContext004"), i));
		 
		 suite.addTest(new RepeatedTest(new TestBigDecimalDivideToIntegralValue(
		 "testDivideToIntegralValueBigDecimalMathContext001"), i));
		 suite.addTest(new RepeatedTest(new TestBigDecimalDivideToIntegralValue(
		 "testDivideToIntegralValueBigDecimalMathContext002"), i));
		
		 
		 
		 suite.addTest(new RepeatedTest(new TestBigIntegerConstructors(
		 "testBigIntegerByteArray015"), i));
		 suite.addTest(new RepeatedTest(new TestBigIntegerConstructors(
		 "testBigIntegerByteArray003"), i));
		 suite.addTest(new RepeatedTest(new TestBigIntegerConstructors(
		 "testBigIntegerIntByteArray028"), i));
		 suite.addTest(new RepeatedTest(new TestBigIntegerConstructors(
		 "testBigIntegerIntByteArray010"), i));
		 suite.addTest(new RepeatedTest(new TestBigIntegerConstructors(
		 "testBigIntegerString003"), i));
		 suite.addTest(new RepeatedTest(new TestBigIntegerConstructors(
		 "testBigIntegerString004"), i));
		 suite.addTest(new RepeatedTest(new TestBigIntegerConstructors(
		 "testBigIntegerStringInt020"), i));
		 suite.addTest(new RepeatedTest(new TestBigIntegerConstructors(
		 "testBigIntegerStringInt038"), i));
		 suite.addTest(new RepeatedTest(new TestBigIntegerConstructors(
		 "testBigIntegerIntRandom002"), i));
		 suite.addTest(new RepeatedTest(new TestBigIntegerConstructors(
		 "testBigIntegerIntRandom005"), i));
		 suite.addTest(new RepeatedTest(new TestBigIntegerConstructors(
		 "testBigIntegerIntIntRandom154"), i));
		 suite.addTest(new RepeatedTest(new TestBigIntegerConstructors(
		 "testBigIntegerIntIntRandom006"), i));
		 suite
		 .addTest(new RepeatedTest(
		 new TestAbsNegateSignum("testAbs038"), i));
		 suite.addTest(new RepeatedTest(
		 new TestAbsNegateSignum("testNegate002"), i));
		 suite.addTest(new RepeatedTest(
		 new TestAbsNegateSignum("testSignum002"), i));
		 suite.addTest(new RepeatedTest(new TestAddSubtract("testAdd012"), i));
		 suite.addTest(new RepeatedTest(new TestAddSubtract("testSubtract009"),
		 i));
		 suite.addTest(new RepeatedTest(new TestBitLengthBitCount(
		 "testBitLength012"), i));
		 	suite.addTest(new RepeatedTest(new TestBitLengthBitCount(
		 	"testBitCount013"), i));
		 suite.addTest(new RepeatedTest(
		 new TestClearSetFlipBit("testSetBit046"), i));
		 suite.addTest(new RepeatedTest(new TestClearSetFlipBit(
		 "testClearBit050"), i));
		 suite.addTest(new RepeatedTest(
		 new TestClearSetFlipBit("testFlipBit023"), i));
		 suite.addTest(new RepeatedTest(new TestCompareToEqualsHashCode(
		 "testCompareTo009"), i));
		 suite.addTest(new RepeatedTest(new TestCompareToEqualsHashCode(
		 "testCompareTo010"), i));
		 suite.addTest(new RepeatedTest(new TestCompareToEqualsHashCode(
		 "testCompareTo011"), i));
		 suite.addTest(new RepeatedTest(new TestCompareToEqualsHashCode(
		 "testEquals009"), i));
		 suite.addTest(new RepeatedTest(new TestCompareToEqualsHashCode(
		 "testEquals089"), i));
		 suite.addTest(new RepeatedTest(new TestCompareToEqualsHashCode(
		 "testHashCode008"), i));
		 suite.addTest(new RepeatedTest(new TestCompareToEqualsHashCode(
		 "testHashCode021"), i));
		 suite.addTest(new RepeatedTest(new TestDivideAndRemainder(
		 "testDivide056"), i));
		 suite.addTest(new RepeatedTest(new TestDivideAndRemainder(
		 "testDivide059"), i));
		 suite.addTest(new RepeatedTest(new TestDivideAndRemainder(
		 "testRemainder014"), i));
		 suite.addTest(new RepeatedTest(new TestDivideAndRemainder(
		 "testRemainder017"), i));
		 suite.addTest(new RepeatedTest(new TestDivideAndRemainder(
		 "testDivideAndRemainder058"), i));
		 suite.addTest(new RepeatedTest(new TestDivideAndRemainder(
		 "testDivideAndRemainder027"), i));
		 suite.addTest(new RepeatedTest(new TestGcdModInverse("testGcd023"), i));
		 suite.addTest(new RepeatedTest(new TestGcdModInverse(
		 "testModInverse014"), i));
		 suite.addTest(new RepeatedTest(new TestGcdModInverse(
		 "testModInverse059"), i));
		 suite.addTest(new RepeatedTest(new TestLogical("testAnd037"), i));
		 suite.addTest(new RepeatedTest(new TestLogical("testOr010"), i));
		 suite.addTest(new RepeatedTest(new TestLogical("testXor042"), i));
		 suite.addTest(new RepeatedTest(new TestLogical("testNot007"), i));
		 suite.addTest(new RepeatedTest(new TestLogical("testAndNot014"), i));
		 suite.addTest(new RepeatedTest(new TestMinMax("testMax001"), i));
		 suite.addTest(new RepeatedTest(new TestMinMax("testMin001"), i));
		 suite.addTest(new RepeatedTest(new TestPow("testPow048"), i));
		 suite.addTest(new RepeatedTest(new TestPow("testPow049"), i));
		 suite.addTest(new RepeatedTest(new TestNumberInterfaceValueOf(
		 "testIntValue002"), i));
		 suite.addTest(new RepeatedTest(new TestNumberInterfaceValueOf(
		 "testLongValue002"), i));
		 suite.addTest(new RepeatedTest(new TestNumberInterfaceValueOf(
		 "testDoubleValue002"), i));
		 suite.addTest(new RepeatedTest(new TestNumberInterfaceValueOf(
		 "testFloatValue002"), i));
		 suite.addTest(new RepeatedTest(new TestNumberInterfaceValueOf(
		 "testValueOf003"), i));
		 suite.addTest(new RepeatedTest(new TestMultiply("testMultiply063"), i));
		 suite.addTest(new RepeatedTest(new TestModModPow("testMod014"), i));
		 suite.addTest(new RepeatedTest(new TestModModPow("testMod059"), i));
		 suite.addTest(new RepeatedTest(new TestModModPow("testModPow514"), i));
		 suite.addTest(new RepeatedTest(new TestModModPow("testModPow559"), i));
		 suite.addTest(new RepeatedTest(new TestProbablePrime(
		 "testProbablePrime024"), i));
		 suite.addTest(new RepeatedTest(new TestProbablePrime(
		 "testNextProbablePrime008"), i));
		 suite.addTest(new RepeatedTest(new TestProbablePrime(
		 "testIsProbablePrime152"), i));
		 suite.addTest(new RepeatedTest(new TestProbablePrime(
		 "testIsProbablePrime119"), i));
		 suite.addTest(new RepeatedTest(new TestShifts("testShiftLeft010"), i));
		 suite.addTest(new RepeatedTest(new TestShifts("testShiftRight010"), i));
		 suite.addTest(new RepeatedTest(new TestTestBitGetLowestSetBit(
		 "testTestBit012"), i));
		 suite.addTest(new RepeatedTest(new TestTestBitGetLowestSetBit(
		 "testTestBit046"), i));
		 suite.addTest(new RepeatedTest(new TestTestBitGetLowestSetBit(
		 "testGetLowestSetBit012"), i));
		 suite.addTest(new RepeatedTest(new TestToStringToByteArray(
		 "testToString028"), i));
		 suite.addTest(new RepeatedTest(new TestToStringToByteArray(
		 "testToStringInt209"), i));
		 suite.addTest(new RepeatedTest(new TestToStringToByteArray(
		 "testToByteArray014"), i));
		 
		//		MathContext Methods to profile
				suite.addTest(new RepeatedTest(new TestMathContext(
				"testMathContextInt003"), i));
				suite.addTest(new RepeatedTest(new TestMathContext(
				"testMathContextInt002"), i));
				suite.addTest(new RepeatedTest(new TestMathContext(
				"testMathContextIntRoundingMode003"), i));
				suite.addTest(new RepeatedTest(new TestMathContext(
				"testMathContextIntRoundingMode005"), i));
				suite.addTest(new RepeatedTest(new TestMathContext(
				"testMathContextString003"), i));
				suite.addTest(new RepeatedTest(new TestMathContext("testHashCode001"),
				i));
				suite.addTest(new RepeatedTest(new TestMathContext("testHashCode002"),
				i));
				suite
				.addTest(new RepeatedTest(new TestMathContext("testEquals001"),
				i));
				suite
				.addTest(new RepeatedTest(new TestMathContext("testEquals002"),
				i));	
			suite.addTest(new RepeatedTest(new TestMathContext("testToString002"),
					i));

					suite.addTest(new TestIntegration("testIntegration"));
		
	//	 $JUnit-END$
		return suite;
	}

}