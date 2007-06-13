/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.harmony.luni.tests.java.lang;

public class MathTest extends junit.framework.TestCase {

	double HYP = Math.sqrt(2.0);

	double OPP = 1.0;

	double ADJ = 1.0;

	/* Required to make previous preprocessor flags work - do not remove */
	int unused = 0;

	/**
	 * @tests java.lang.Math#abs(double)
	 */
	public void test_absD() {
		// Test for method double java.lang.Math.abs(double)

		assertTrue("Incorrect double abs value",
				(Math.abs(-1908.8976) == 1908.8976));
		assertTrue("Incorrect double abs value",
				(Math.abs(1908.8976) == 1908.8976));
	}

	/**
	 * @tests java.lang.Math#abs(float)
	 */
	public void test_absF() {
		// Test for method float java.lang.Math.abs(float)
		assertTrue("Incorrect float abs value",
				(Math.abs(-1908.8976f) == 1908.8976f));
		assertTrue("Incorrect float abs value",
				(Math.abs(1908.8976f) == 1908.8976f));
	}

	/**
	 * @tests java.lang.Math#abs(int)
	 */
	public void test_absI() {
		// Test for method int java.lang.Math.abs(int)
		assertTrue("Incorrect int abs value", (Math.abs(-1908897) == 1908897));
		assertTrue("Incorrect int abs value", (Math.abs(1908897) == 1908897));
	}

	/**
	 * @tests java.lang.Math#abs(long)
	 */
	public void test_absJ() {
		// Test for method long java.lang.Math.abs(long)
		assertTrue("Incorrect long abs value",
				(Math.abs(-19088976000089L) == 19088976000089L));
		assertTrue("Incorrect long abs value",
				(Math.abs(19088976000089L) == 19088976000089L));
	}

	/**
	 * @tests java.lang.Math#acos(double)
	 */
	public void test_acosD() {
		// Test for method double java.lang.Math.acos(double)
		double r = Math.cos(Math.acos(ADJ / HYP));
		long lr = Double.doubleToLongBits(r);
		long t = Double.doubleToLongBits(ADJ / HYP);
		assertTrue("Returned incorrect arc cosine", lr == t || (lr + 1) == t
				|| (lr - 1) == t);
	}

	/**
	 * @tests java.lang.Math#asin(double)
	 */
	public void test_asinD() {
		// Test for method double java.lang.Math.asin(double)
		double r = Math.sin(Math.asin(OPP / HYP));
		long lr = Double.doubleToLongBits(r);
		long t = Double.doubleToLongBits(OPP / HYP);
		assertTrue("Returned incorrect arc sine", lr == t || (lr + 1) == t
				|| (lr - 1) == t);
	}

	/**
	 * @tests java.lang.Math#atan(double)
	 */
	public void test_atanD() {
		// Test for method double java.lang.Math.atan(double)
		double answer = Math.tan(Math.atan(1.0));
		assertTrue("Returned incorrect arc tangent: " + answer, answer <= 1.0
				&& answer >= 9.9999999999999983E-1);
	}

	/**
	 * @tests java.lang.Math#atan2(double, double)
	 */
	public void test_atan2DD() {
		// Test for method double java.lang.Math.atan2(double, double)
		double answer = Math.atan(Math.tan(1.0));
		assertTrue("Returned incorrect arc tangent: " + answer, answer <= 1.0
				&& answer >= 9.9999999999999983E-1);
	}
    
     /**
     * @tests java.lang.Math#cbrt(double)
     */
    public void test_cbrt_D() {
        //Test for special situations
        assertTrue("Should return Double.NaN", Double.isNaN(Math
                .cbrt(Double.NaN)));
        assertEquals("Should return Double.POSITIVE_INFINITY",
                Double.POSITIVE_INFINITY, Math
                        .cbrt(Double.POSITIVE_INFINITY), 0D);
        assertEquals("Should return Double.NEGATIVE_INFINITY",
                Double.NEGATIVE_INFINITY, Math
                        .cbrt(Double.NEGATIVE_INFINITY), 0D);
        assertEquals(Double.doubleToLongBits(0.0), Double.doubleToLongBits(Math
				.cbrt(0.0)));
		assertEquals(Double.doubleToLongBits(+0.0), Double.doubleToLongBits(Math
				.cbrt(+0.0)));
		assertEquals(Double.doubleToLongBits(-0.0), Double.doubleToLongBits(Math
				.cbrt(-0.0)));

        assertEquals("Should return 3.0", 3.0, Math.cbrt(27.0), 0D);
        assertEquals("Should return 23.111993172558684", 23.111993172558684,
                Math.cbrt(12345.6), 0D);
        assertEquals("Should return 5.643803094122362E102",
                5.643803094122362E102, Math.cbrt(Double.MAX_VALUE), 0D);
        assertEquals("Should return 0.01", 0.01, Math.cbrt(0.000001), 0D);

        assertEquals("Should return -3.0", -3.0, Math.cbrt(-27.0), 0D);
        assertEquals("Should return -23.111993172558684", -23.111993172558684,
                Math.cbrt(-12345.6), 0D);
        assertEquals("Should return 1.7031839360032603E-108",
                1.7031839360032603E-108, Math.cbrt(Double.MIN_VALUE), 0D);
        assertEquals("Should return -0.01", -0.01, Math.cbrt(-0.000001), 0D);
    }

	/**
	 * @tests java.lang.Math#ceil(double)
	 */
	public void test_ceilD() {
		// Test for method double java.lang.Math.ceil(double)
                assertEquals("Incorrect ceiling for double",
                             79, Math.ceil(78.89), 0);
		assertEquals("Incorrect ceiling for double",
                             -78, Math.ceil(-78.89), 0);
	}
	
	/**
     * cases for test_copySign_DD in MathTest/StrictMathTest
     */
    static final double[] COPYSIGN_DD_CASES = new double[] {
            Double.POSITIVE_INFINITY, Double.MAX_VALUE, 3.4E302, 2.3,
            Double.MIN_NORMAL, Double.MIN_NORMAL / 2, Double.MIN_VALUE, +0.0,
            0.0, -0.0, -Double.MIN_VALUE, -Double.MIN_NORMAL / 2,
            -Double.MIN_NORMAL, -4.5, -3.4E102, -Double.MAX_VALUE,
            Double.NEGATIVE_INFINITY };

    /**
     * @tests {@link java.lang.Math#copySign(double, double)}
     * @since 1.6
     * 
     */
    @SuppressWarnings("boxing")
    public void test_copySign_DD() {
        for (int i = 0; i < COPYSIGN_DD_CASES.length; i++) {
            final double magnitude = COPYSIGN_DD_CASES[i];
            final long absMagnitudeBits = Double.doubleToLongBits(Math
                    .abs(magnitude));
            final long negMagnitudeBits = Double.doubleToLongBits(-Math
                    .abs(magnitude));

            // cases for NaN
            assertEquals("If the sign is NaN, the result should be positive.",
                    absMagnitudeBits, Double.doubleToLongBits(Math.copySign(
                            magnitude, Double.NaN)));
            assertTrue("The result should be NaN.", Double.isNaN(Math.copySign(
                    Double.NaN, magnitude)));

            for (int j = 0; j < COPYSIGN_DD_CASES.length; j++) {
                final double sign = COPYSIGN_DD_CASES[j];
                final long resultBits = Double.doubleToLongBits(Math.copySign(
                        magnitude, sign));

                if (sign > 0 || Double.valueOf(+0.0).equals(sign)
                        || Double.valueOf(0.0).equals(sign)) {
                    assertEquals(
                            "If the sign is positive, the result should be positive.",
                            absMagnitudeBits, resultBits);
                }
                if (sign < 0 || Double.valueOf(-0.0).equals(sign)) {
                    assertEquals(
                            "If the sign is negative, the result should be negative.",
                            negMagnitudeBits, resultBits);
                }
            }
        }

        assertTrue("The result should be NaN.", Double.isNaN(Math.copySign(
                Double.NaN, Double.NaN)));

        try {
            Math.copySign((Double) null, 2.3);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
        try {
            Math.copySign(2.3, (Double) null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
        try {
            Math.copySign((Double) null, (Double) null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }

    /**
     * cases for test_copySign_FF in MathTest/StrictMathTest
     */
    static final float[] COPYSIGN_FF_CASES = new float[] {
            Float.POSITIVE_INFINITY, Float.MAX_VALUE, 3.4E12f, 2.3f,
            Float.MIN_NORMAL, Float.MIN_NORMAL / 2, Float.MIN_VALUE, +0.0f,
            0.0f, -0.0f, -Float.MIN_VALUE, -Float.MIN_NORMAL / 2,
            -Float.MIN_NORMAL, -4.5f, -5.6442E21f, -Float.MAX_VALUE,
            Float.NEGATIVE_INFINITY };

    /**
     * @tests {@link java.lang.Math#copySign(float, float)}
     * @since 1.6
     */
    @SuppressWarnings("boxing")
    public void test_copySign_FF() {
        for (int i = 0; i < COPYSIGN_FF_CASES.length; i++) {
            final float magnitude = COPYSIGN_FF_CASES[i];
            final int absMagnitudeBits = Float.floatToIntBits(Math
                    .abs(magnitude));
            final int negMagnitudeBits = Float.floatToIntBits(-Math
                    .abs(magnitude));

            // cases for NaN
            assertEquals("If the sign is NaN, the result should be positive.",
                    absMagnitudeBits, Float.floatToIntBits(Math.copySign(
                            magnitude, Float.NaN)));
            assertTrue("The result should be NaN.", Float.isNaN(Math.copySign(
                    Float.NaN, magnitude)));

            for (int j = 0; j < COPYSIGN_FF_CASES.length; j++) {
                final float sign = COPYSIGN_FF_CASES[j];
                final int resultBits = Float.floatToIntBits(Math.copySign(
                        magnitude, sign));
                if (sign > 0 || Float.valueOf(+0.0f).equals(sign)
                        || Float.valueOf(0.0f).equals(sign)) {
                    assertEquals(
                            "If the sign is positive, the result should be positive.",
                            absMagnitudeBits, resultBits);
                }
                if (sign < 0 || Float.valueOf(-0.0f).equals(sign)) {
                    assertEquals(
                            "If the sign is negative, the result should be negative.",
                            negMagnitudeBits, resultBits);
                }
            }
        }

        assertTrue("The result should be NaN.", Float.isNaN(Math.copySign(
                Float.NaN, Float.NaN)));

        try {
            Math.copySign((Float) null, 2.3f);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
        try {
            Math.copySign(2.3f, (Float) null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
        try {
            Math.copySign((Float) null, (Float) null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }

	/**
	 * @tests java.lang.Math#cos(double)
	 */
	public void test_cosD() {
		// Test for method double java.lang.Math.cos(double)
		assertEquals("Incorrect answer", 1.0, Math.cos(0), 0D);
		assertEquals("Incorrect answer", 0.5403023058681398, Math.cos(1), 0D);
	}

    /**
     * @tests java.lang.Math#cosh(double)
     */
    public void test_cosh_D() {
        // Test for special situations
        assertTrue(Double.isNaN(Math.cosh(Double.NaN)));
        assertEquals("Should return POSITIVE_INFINITY",
                Double.POSITIVE_INFINITY, Math.cosh(Double.POSITIVE_INFINITY), 0D);
        assertEquals("Should return POSITIVE_INFINITY",
                Double.POSITIVE_INFINITY, Math.cosh(Double.NEGATIVE_INFINITY), 0D);
        assertEquals("Should return 1.0", 1.0, Math.cosh(+0.0), 0D);
        assertEquals("Should return 1.0", 1.0, Math.cosh(-0.0), 0D);

        assertEquals("Should return POSITIVE_INFINITY",
                Double.POSITIVE_INFINITY, Math.cosh(1234.56), 0D);
        assertEquals("Should return POSITIVE_INFINITY",
                Double.POSITIVE_INFINITY, Math.cosh(-1234.56), 0D);
        assertEquals("Should return 1.0000000000005", 1.0000000000005, Math
                .cosh(0.000001), 0D);
        assertEquals("Should return 1.0000000000005", 1.0000000000005, Math
                .cosh(-0.000001), 0D);
        assertEquals("Should return 5.212214351945598", 5.212214351945598, Math
                .cosh(2.33482), 0D);

        assertEquals("Should return POSITIVE_INFINITY",
                Double.POSITIVE_INFINITY, Math.cosh(Double.MAX_VALUE), 0D);
        assertEquals("Should return 1.0", 1.0, Math.cosh(Double.MIN_VALUE), 0D);
    }
    
	/**
	 * @tests java.lang.Math#exp(double)
	 */
	public void test_expD() {
		// Test for method double java.lang.Math.exp(double)
		assertTrue("Incorrect answer returned for simple power", Math.abs(Math
				.exp(4D)
				- Math.E * Math.E * Math.E * Math.E) < 0.1D);
		assertTrue("Incorrect answer returned for larger power", Math.log(Math
				.abs(Math.exp(5.5D)) - 5.5D) < 10.0D);
	}
    
    /**
     * @tests java.lang.Math#expm1(double)
     */
    public void test_expm1_D() {
        // Test for special cases
        assertTrue("Should return NaN", Double.isNaN(Math.expm1(Double.NaN)));
        assertEquals("Should return POSITIVE_INFINITY",
                Double.POSITIVE_INFINITY, Math.expm1(Double.POSITIVE_INFINITY), 0D);
        assertEquals("Should return -1.0", -1.0, Math
                .expm1(Double.NEGATIVE_INFINITY), 0D);
        assertEquals(Double.doubleToLongBits(0.0), Double.doubleToLongBits(Math
				.expm1(0.0)));
		assertEquals(Double.doubleToLongBits(+0.0), Double
				.doubleToLongBits(Math.expm1(+0.0)));
		assertEquals(Double.doubleToLongBits(-0.0), Double
				.doubleToLongBits(Math.expm1(-0.0)));

        assertEquals("Should return -9.999950000166666E-6",
                -9.999950000166666E-6, Math.expm1(-0.00001), 0D);
        assertEquals("Should return 1.0145103074469635E60",
                1.0145103074469635E60, Math.expm1(138.16951162), 0D);
        assertEquals("Should return POSITIVE_INFINITY",
                Double.POSITIVE_INFINITY, Math
                        .expm1(123456789123456789123456789.4521584223), 0D);
        assertEquals("Should return POSITIVE_INFINITY",
                Double.POSITIVE_INFINITY, Math.expm1(Double.MAX_VALUE), 0D);
        assertEquals("Should return MIN_VALUE", Double.MIN_VALUE, Math
                .expm1(Double.MIN_VALUE), 0D);
    }

    /**
     * @tests java.lang.Math#floor(double)
     */
	public void test_floorD() {
		// Test for method double java.lang.Math.floor(double)
                assertEquals("Incorrect floor for double",
                             78, Math.floor(78.89), 0);
		assertEquals("Incorrect floor for double",
                             -79, Math.floor(-78.89), 0);
	}
	
	/**
     * cases for test_getExponent_D in MathTest/StrictMathTest
     */
    static final double GETEXPONENT_D_CASES[] = new double[] {
            Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY,
            Double.MAX_VALUE, -Double.MAX_VALUE, 2.342E231, -2.342E231, 2800.0,
            -2800.0, 5.323, -5.323, 1.323, -1.323, 0.623, -0.623, 0.323,
            -0.323, Double.MIN_NORMAL * 24, -Double.MIN_NORMAL * 24,
            Double.MIN_NORMAL, -Double.MIN_NORMAL, Double.MIN_NORMAL / 2,
            -Double.MIN_NORMAL / 2, Double.MIN_VALUE, -Double.MIN_VALUE, +0.0,
            0.0, -0.0, Double.NaN };

    /**
     * result for test_getExponent_D in MathTest/StrictMathTest
     */
    static final int GETEXPONENT_D_RESULTS[] = new int[] {
            Double.MAX_EXPONENT + 1, Double.MAX_EXPONENT + 1,
            Double.MAX_EXPONENT, Double.MAX_EXPONENT, 768, 768, 11, 11, 2, 2,
            0, 0, -1, -1, -2, -2, -1018, -1018, Double.MIN_EXPONENT,
            Double.MIN_EXPONENT, Double.MIN_EXPONENT - 1,
            Double.MIN_EXPONENT - 1, Double.MIN_EXPONENT - 1,
            Double.MIN_EXPONENT - 1, Double.MIN_EXPONENT - 1,
            Double.MIN_EXPONENT - 1, Double.MIN_EXPONENT - 1,
            Double.MAX_EXPONENT + 1 };

    /**
     * @tests {@link java.lang.Math#getExponent(double)}
     * @since 1.6
     */
    @SuppressWarnings("boxing")
    public void test_getExponent_D() {
        for (int i = 0; i < GETEXPONENT_D_CASES.length; i++) {
            final double number = GETEXPONENT_D_CASES[i];
            final int result = GETEXPONENT_D_RESULTS[i];
            assertEquals("Wrong result of getExponent(double).", result, Math
                    .getExponent(number));
        }

        try {
            Math.getExponent((Double) null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }

    /**
     * cases for test_getExponent_F in MathTest/StrictMathTest
     */
    static final float GETEXPONENT_F_CASES[] = new float[] {
            Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.MAX_VALUE,
            -Float.MAX_VALUE, 3.4256E23f, -3.4256E23f, 2800.0f, -2800.0f,
            5.323f, -5.323f, 1.323f, -1.323f, 0.623f, -0.623f, 0.323f, -0.323f,
            Float.MIN_NORMAL * 24, -Float.MIN_NORMAL * 24, Float.MIN_NORMAL,
            -Float.MIN_NORMAL, Float.MIN_NORMAL / 2, -Float.MIN_NORMAL / 2,
            Float.MIN_VALUE, -Float.MIN_VALUE, +0.0f, 0.0f, -0.0f, Float.NaN,1,Float.MIN_NORMAL * 1.5f };

    /**
     * result for test_getExponent_F in MathTest/StrictMathTest
     */
    static final int GETEXPONENT_F_RESULTS[] = new int[] {
            Float.MAX_EXPONENT + 1, Float.MAX_EXPONENT + 1, Float.MAX_EXPONENT,
            Float.MAX_EXPONENT, 78, 78, 11, 11, 2, 2, 0, 0, -1, -1, -2, -2,
            -122, -122, Float.MIN_EXPONENT, Float.MIN_EXPONENT,
            Float.MIN_EXPONENT - 1, Float.MIN_EXPONENT - 1,
            Float.MIN_EXPONENT - 1, Float.MIN_EXPONENT - 1,
            Float.MIN_EXPONENT - 1, Float.MIN_EXPONENT - 1,
            Float.MIN_EXPONENT - 1, Float.MAX_EXPONENT + 1,0,Float.MIN_EXPONENT };
    
    /**
     * @tests {@link java.lang.Math#getExponent(float)}
     * @since 1.6
     */
    @SuppressWarnings("boxing")
    public void test_getExponent_F() {
        for (int i = 0; i < GETEXPONENT_F_CASES.length; i++) {
            final float number = GETEXPONENT_F_CASES[i];
            final int result = GETEXPONENT_F_RESULTS[i];
            assertEquals("Wrong result of getExponent(float).", result, Math
                    .getExponent(number));
        }
        try {
            Math.getExponent((Float) null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }
    
    /**
     * @tests java.lang.Math#hypot(double, double)
     */
    public void test_hypot_DD() {
        // Test for special cases
        assertEquals("Should return POSITIVE_INFINITY",
                Double.POSITIVE_INFINITY, Math.hypot(Double.POSITIVE_INFINITY,
                        1.0), 0D);
        assertEquals("Should return POSITIVE_INFINITY",
                Double.POSITIVE_INFINITY, Math.hypot(Double.NEGATIVE_INFINITY,
                        123.324), 0D);
        assertEquals("Should return POSITIVE_INFINITY",
                Double.POSITIVE_INFINITY, Math.hypot(-758.2587,
                        Double.POSITIVE_INFINITY), 0D);
        assertEquals("Should return POSITIVE_INFINITY",
                Double.POSITIVE_INFINITY, Math.hypot(5687.21,
                        Double.NEGATIVE_INFINITY), 0D);
        assertEquals("Should return POSITIVE_INFINITY",
                Double.POSITIVE_INFINITY, Math.hypot(Double.POSITIVE_INFINITY,
                        Double.NEGATIVE_INFINITY), 0D);
        assertEquals("Should return POSITIVE_INFINITY",
                Double.POSITIVE_INFINITY, Math.hypot(Double.NEGATIVE_INFINITY,
                        Double.POSITIVE_INFINITY), 0D);        
        assertTrue("Should be NaN", Double.isNaN(Math.hypot(Double.NaN,
                2342301.89843)));
        assertTrue("Should be NaN", Double.isNaN(Math.hypot(-345.2680,
                Double.NaN)));        

        assertEquals("Should return 2396424.905416697", 2396424.905416697, Math
                .hypot(12322.12, -2396393.2258), 0D);
        assertEquals("Should return 138.16958070558556", 138.16958070558556,
                Math.hypot(-138.16951162, 0.13817035864), 0D);
        assertEquals("Should return 1.7976931348623157E308",
                1.7976931348623157E308, Math.hypot(Double.MAX_VALUE, 211370.35), 0D);
        assertEquals("Should return 5413.7185", 5413.7185, Math.hypot(
                -5413.7185, Double.MIN_VALUE), 0D);
    }

	/**
	 * @tests java.lang.Math#IEEEremainder(double, double)
	 */
	public void test_IEEEremainderDD() {
		// Test for method double java.lang.Math.IEEEremainder(double, double)
		assertEquals("Incorrect remainder returned",
				0.0, Math.IEEEremainder(1.0, 1.0), 0D);
		assertTrue("Incorrect remainder returned", Math.IEEEremainder(1.32,
				89.765) >= 1.4705063220631647E-2
				|| Math.IEEEremainder(1.32, 89.765) >= 1.4705063220631649E-2);
	}

	/**
	 * @tests java.lang.Math#log(double)
	 */
	public void test_logD() {
		// Test for method double java.lang.Math.log(double)
		for (double d = 10; d >= -10; d -= 0.5) {
			double answer = Math.log(Math.exp(d));
			assertTrue("Answer does not equal expected answer for d = " + d
					+ " answer = " + answer, Math.abs(answer - d) <= Math
					.abs(d * 0.00000001));
		}
	}
    
    /**
     * @tests java.lang.Math#log10(double)
     */
    @SuppressWarnings("boxing")
    public void test_log10_D() {
        // Test for special cases
        assertTrue(Double.isNaN(Math.log10(Double.NaN)));
        assertTrue(Double.isNaN(Math.log10(-2541.05745687234187532)));
        assertTrue(Double.isNaN(Math.log10(-0.1)));
        assertEquals(Double.POSITIVE_INFINITY, Math.log10(Double.POSITIVE_INFINITY));
        assertEquals(Double.NEGATIVE_INFINITY, Math.log10(0.0));
        assertEquals(Double.NEGATIVE_INFINITY, Math.log10(+0.0));
        assertEquals(Double.NEGATIVE_INFINITY, Math.log10(-0.0));
        
        assertEquals(3.0, Math.log10(1000.0));
        assertEquals(14.0, Math.log10(Math.pow(10, 14)));
        assertEquals(3.7389561269540406, Math.log10(5482.2158));
        assertEquals(14.661551142893833, Math.log10(458723662312872.125782332587));
        assertEquals(-0.9083828622192334, Math.log10(0.12348583358871));
        assertEquals(308.25471555991675, Math.log10(Double.MAX_VALUE));
        assertEquals(-323.3062153431158, Math.log10(Double.MIN_VALUE));
    }
    
    /**
     * @tests java.lang.Math#log1p(double)
     */
    public void test_log1p_D() {
        // Test for special cases
        assertTrue("Should return NaN", Double.isNaN(Math.log1p(Double.NaN)));
        assertTrue("Should return NaN", Double.isNaN(Math.log1p(-32.0482175)));
        assertEquals("Should return POSITIVE_INFINITY",
                Double.POSITIVE_INFINITY, Math.log1p(Double.POSITIVE_INFINITY), 0D);
        assertEquals(Double.doubleToLongBits(0.0), Double.doubleToLongBits(Math
				.log1p(0.0)));
		assertEquals(Double.doubleToLongBits(+0.0), Double
				.doubleToLongBits(Math.log1p(+0.0)));
		assertEquals(Double.doubleToLongBits(-0.0), Double
				.doubleToLongBits(Math.log1p(-0.0)));

        assertEquals("Should return -0.2941782295312541", -0.2941782295312541,
                Math.log1p(-0.254856327), 0D);
        assertEquals("Should return 7.368050685564151", 7.368050685564151, Math
                .log1p(1583.542), 0D);
        assertEquals("Should return 0.4633708685409921", 0.4633708685409921,
                Math.log1p(0.5894227), 0D);
        assertEquals("Should return 709.782712893384", 709.782712893384, Math
                .log1p(Double.MAX_VALUE), 0D);
        assertEquals("Should return Double.MIN_VALUE", Double.MIN_VALUE, Math
                .log1p(Double.MIN_VALUE), 0D);
    }

	/**
	 * @tests java.lang.Math#max(double, double)
	 */
	public void test_maxDD() {
		// Test for method double java.lang.Math.max(double, double)
		assertEquals("Incorrect double max value", 1908897.6000089, Math.max(-1908897.6000089,
				1908897.6000089), 0D);
		assertEquals("Incorrect double max value",
				1908897.6000089, Math.max(2.0, 1908897.6000089), 0D);
		assertEquals("Incorrect double max value", -2.0, Math.max(-2.0,
				-1908897.6000089), 0D);

	}

	/**
	 * @tests java.lang.Math#max(float, float)
	 */
	public void test_maxFF() {
		// Test for method float java.lang.Math.max(float, float)
		assertTrue("Incorrect float max value", Math.max(-1908897.600f,
				1908897.600f) == 1908897.600f);
		assertTrue("Incorrect float max value",
				Math.max(2.0f, 1908897.600f) == 1908897.600f);
		assertTrue("Incorrect float max value",
				Math.max(-2.0f, -1908897.600f) == -2.0f);
	}

	/**
	 * @tests java.lang.Math#max(int, int)
	 */
	public void test_maxII() {
		// Test for method int java.lang.Math.max(int, int)
		assertEquals("Incorrect int max value",
				19088976, Math.max(-19088976, 19088976));
		assertEquals("Incorrect int max value",
				19088976, Math.max(20, 19088976));
		assertEquals("Incorrect int max value", -20, Math.max(-20, -19088976));
	}

	/**
	 * @tests java.lang.Math#max(long, long)
	 */
	public void test_maxJJ() {
		// Test for method long java.lang.Math.max(long, long)
		assertEquals("Incorrect long max value", 19088976000089L, Math.max(-19088976000089L,
				19088976000089L));
		assertEquals("Incorrect long max value",
				19088976000089L, Math.max(20, 19088976000089L));
		assertEquals("Incorrect long max value",
				-20, Math.max(-20, -19088976000089L));
	}

	/**
	 * @tests java.lang.Math#min(double, double)
	 */
	public void test_minDD() {
		// Test for method double java.lang.Math.min(double, double)
		assertEquals("Incorrect double min value", -1908897.6000089, Math.min(-1908897.6000089,
				1908897.6000089), 0D);
		assertEquals("Incorrect double min value",
				2.0, Math.min(2.0, 1908897.6000089), 0D);
		assertEquals("Incorrect double min value", -1908897.6000089, Math.min(-2.0,
				-1908897.6000089), 0D);
	}

	/**
	 * @tests java.lang.Math#min(float, float)
	 */
	public void test_minFF() {
		// Test for method float java.lang.Math.min(float, float)
		assertTrue("Incorrect float min value", Math.min(-1908897.600f,
				1908897.600f) == -1908897.600f);
		assertTrue("Incorrect float min value",
				Math.min(2.0f, 1908897.600f) == 2.0f);
		assertTrue("Incorrect float min value",
				Math.min(-2.0f, -1908897.600f) == -1908897.600f);
	}

	/**
	 * @tests java.lang.Math#min(int, int)
	 */
	public void test_minII() {
		// Test for method int java.lang.Math.min(int, int)
		assertEquals("Incorrect int min value",
				-19088976, Math.min(-19088976, 19088976));
		assertEquals("Incorrect int min value", 20, Math.min(20, 19088976));
		assertEquals("Incorrect int min value",
				-19088976, Math.min(-20, -19088976));

	}

	/**
	 * @tests java.lang.Math#min(long, long)
	 */
	public void test_minJJ() {
		// Test for method long java.lang.Math.min(long, long)
		assertEquals("Incorrect long min value", -19088976000089L, Math.min(-19088976000089L,
				19088976000089L));
		assertEquals("Incorrect long min value",
				20, Math.min(20, 19088976000089L));
		assertEquals("Incorrect long min value",
				-19088976000089L, Math.min(-20, -19088976000089L));
	}
	
	/**
     * start number cases for test_nextAfter_DD in MathTest/StrictMathTest
     * NEXTAFTER_DD_START_CASES[i][0] is the start number
     * NEXTAFTER_DD_START_CASES[i][1] is the nextUp of start number
     * NEXTAFTER_DD_START_CASES[i][2] is the nextDown of start number
     */
    static final double NEXTAFTER_DD_START_CASES[][] = new double[][] {
            { 3.4, 3.4000000000000004, 3.3999999999999995 },
            { -3.4, -3.3999999999999995, -3.4000000000000004 },
            { 3.4233E109, 3.4233000000000005E109, 3.4232999999999996E109 },
            { -3.4233E109, -3.4232999999999996E109, -3.4233000000000005E109 },
            { +0.0, Double.MIN_VALUE, -Double.MIN_VALUE },
            { 0.0, Double.MIN_VALUE, -Double.MIN_VALUE },
            { -0.0, Double.MIN_VALUE, -Double.MIN_VALUE },
            { Double.MIN_VALUE, 1.0E-323, +0.0 },
            { -Double.MIN_VALUE, -0.0, -1.0E-323 },
            { Double.MIN_NORMAL, 2.225073858507202E-308, 2.225073858507201E-308 },
            { -Double.MIN_NORMAL, -2.225073858507201E-308,
                    -2.225073858507202E-308 },
            { Double.MAX_VALUE, Double.POSITIVE_INFINITY,
                    1.7976931348623155E308 },
            { -Double.MAX_VALUE, -1.7976931348623155E308,
                    Double.NEGATIVE_INFINITY },
            { Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY,
                    Double.MAX_VALUE },
            { Double.NEGATIVE_INFINITY, -Double.MAX_VALUE,
                    Double.NEGATIVE_INFINITY } };

    /**
     * direction number cases for test_nextAfter_DD/test_nextAfter_FD in
     * MathTest/StrictMathTest
     */
    static final double NEXTAFTER_DD_FD_DIRECTION_CASES[] = new double[] {
            Double.POSITIVE_INFINITY, Double.MAX_VALUE, 8.8, 3.4, 1.4,
            Double.MIN_NORMAL, Double.MIN_NORMAL / 2, Double.MIN_VALUE, +0.0,
            0.0, -0.0, -Double.MIN_VALUE, -Double.MIN_NORMAL / 2,
            -Double.MIN_NORMAL, -1.4, -3.4, -8.8, -Double.MAX_VALUE,
            Double.NEGATIVE_INFINITY };

    /**
     * @tests {@link java.lang.Math#nextAfter(double, double)}
     * @since 1.6
     */
    @SuppressWarnings("boxing")
    public void test_nextAfter_DD() {
        // test for most cases without exception
        for (int i = 0; i < NEXTAFTER_DD_START_CASES.length; i++) {
            final double start = NEXTAFTER_DD_START_CASES[i][0];
            final long nextUpBits = Double
                    .doubleToLongBits(NEXTAFTER_DD_START_CASES[i][1]);
            final long nextDownBits = Double
                    .doubleToLongBits(NEXTAFTER_DD_START_CASES[i][2]);

            for (int j = 0; j < NEXTAFTER_DD_FD_DIRECTION_CASES.length; j++) {
                final double direction = NEXTAFTER_DD_FD_DIRECTION_CASES[j];
                final long resultBits = Double.doubleToLongBits(Math.nextAfter(
                        start, direction));
                final long directionBits = Double.doubleToLongBits(direction);
                if (direction > start) {
                    assertEquals("Result should be next up-number.",
                            nextUpBits, resultBits);
                } else if (direction < start) {
                    assertEquals("Result should be next down-number.",
                            nextDownBits, resultBits);
                } else {
                    assertEquals("Result should be direction.", directionBits,
                            resultBits);
                }
            }
        }

        // test for cases with NaN
        for (int i = 0; i < NEXTAFTER_DD_START_CASES.length; i++) {
            assertTrue("The result should be NaN.", Double.isNaN(Math
                    .nextAfter(NEXTAFTER_DD_START_CASES[i][0], Double.NaN)));
        }
        for (int i = 0; i < NEXTAFTER_DD_FD_DIRECTION_CASES.length; i++) {
            assertTrue("The result should be NaN.", Double.isNaN(Math
                    .nextAfter(Double.NaN, NEXTAFTER_DD_FD_DIRECTION_CASES[i])));
        }
        assertTrue("The result should be NaN.", Double.isNaN(Math.nextAfter(
                Double.NaN, Double.NaN)));

        // test for exception
        try {
            Math.nextAfter((Double) null, 2.3);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
        try {
            Math.nextAfter(2.3, (Double) null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
        try {
            Math.nextAfter((Double) null, (Double) null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }

    /**
     * start number cases for test_nextAfter_FD in MathTest/StrictMathTest
     * NEXTAFTER_FD_START_CASES[i][0] is the start number
     * NEXTAFTER_FD_START_CASES[i][1] is the nextUp of start number
     * NEXTAFTER_FD_START_CASES[i][2] is the nextDown of start number
     */
    static final float NEXTAFTER_FD_START_CASES[][] = new float[][] {
            { 3.4f, 3.4000003f, 3.3999999f },
            { -3.4f, -3.3999999f, -3.4000003f },
            { 3.4233E19f, 3.4233002E19f, 3.4232998E19f },
            { -3.4233E19f, -3.4232998E19f, -3.4233002E19f },
            { +0.0f, Float.MIN_VALUE, -Float.MIN_VALUE },
            { 0.0f, Float.MIN_VALUE, -Float.MIN_VALUE },
            { -0.0f, Float.MIN_VALUE, -Float.MIN_VALUE },
            { Float.MIN_VALUE, 2.8E-45f, +0.0f },
            { -Float.MIN_VALUE, -0.0f, -2.8E-45f },
            { Float.MIN_NORMAL, 1.1754945E-38f, 1.1754942E-38f },
            { -Float.MIN_NORMAL, -1.1754942E-38f, -1.1754945E-38f },
            { Float.MAX_VALUE, Float.POSITIVE_INFINITY, 3.4028233E38f },
            { -Float.MAX_VALUE, -3.4028233E38f, Float.NEGATIVE_INFINITY },
            { Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.MAX_VALUE },
            { Float.NEGATIVE_INFINITY, -Float.MAX_VALUE,
                    Float.NEGATIVE_INFINITY } };

    /**
     * @tests {@link java.lang.Math#nextAfter(float, double)}
     * @since 1.6
     */
    @SuppressWarnings("boxing")
    public void test_nextAfter_FD() {
        // test for most cases without exception
        for (int i = 0; i < NEXTAFTER_FD_START_CASES.length; i++) {
            final float start = NEXTAFTER_FD_START_CASES[i][0];
            final int nextUpBits = Float
                    .floatToIntBits(NEXTAFTER_FD_START_CASES[i][1]);
            final int nextDownBits = Float
                    .floatToIntBits(NEXTAFTER_FD_START_CASES[i][2]);

            for (int j = 0; j < NEXTAFTER_DD_FD_DIRECTION_CASES.length; j++) {
                final double direction = NEXTAFTER_DD_FD_DIRECTION_CASES[j];
                final int resultBits = Float.floatToIntBits(Math.nextAfter(
                        start, direction));
                if (direction > start) {
                    assertEquals("Result should be next up-number.",
                            nextUpBits, resultBits);
                } else if (direction < start) {
                    assertEquals("Result should be next down-number.",
                            nextDownBits, resultBits);
                } else {
                    final int equivalentBits = Float.floatToIntBits(new Float(
                            direction));
                    assertEquals(
                            "Result should be a number equivalent to direction.",
                            equivalentBits, resultBits);
                }
            }
        }

        // test for cases with NaN
        for (int i = 0; i < NEXTAFTER_FD_START_CASES.length; i++) {
            assertTrue("The result should be NaN.", Float.isNaN(Math.nextAfter(
                    NEXTAFTER_FD_START_CASES[i][0], Float.NaN)));
        }
        for (int i = 0; i < NEXTAFTER_DD_FD_DIRECTION_CASES.length; i++) {
            assertTrue("The result should be NaN.", Float.isNaN(Math.nextAfter(
                    Float.NaN, NEXTAFTER_DD_FD_DIRECTION_CASES[i])));
        }
        assertTrue("The result should be NaN.", Float.isNaN(Math.nextAfter(
                Float.NaN, Float.NaN)));

        // test for exception
        try {
            Math.nextAfter((Float) null, 2.3);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
        try {
            Math.nextAfter(2.3, (Float) null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
        try {
            Math.nextAfter((Float) null, (Float) null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }

    /**
     * @tests {@link java.lang.Math#nextUp(double)}
     * @since 1.6
     */
    @SuppressWarnings("boxing")
    public void test_nextUp_D() {
        // This method is semantically equivalent to nextAfter(d,
        // Double.POSITIVE_INFINITY),
        // so we use the data of test_nextAfter_DD
        for (int i = 0; i < NEXTAFTER_DD_START_CASES.length; i++) {
            final double start = NEXTAFTER_DD_START_CASES[i][0];
            final long nextUpBits = Double
                    .doubleToLongBits(NEXTAFTER_DD_START_CASES[i][1]);
            final long resultBits = Double.doubleToLongBits(Math.nextUp(start));
            assertEquals("Result should be next up-number.", nextUpBits,
                    resultBits);
        }

        // test for cases with NaN
        assertTrue("The result should be NaN.", Double.isNaN(Math
                .nextUp(Double.NaN)));

        // test for exception
        try {
            Math.nextUp((Double) null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }

    /**
     * @tests {@link java.lang.Math#nextUp(float)}
     * @since 1.6
     */
    @SuppressWarnings("boxing")
    public void test_nextUp_F() {
        // This method is semantically equivalent to nextAfter(f,
        // Float.POSITIVE_INFINITY),
        // so we use the data of test_nextAfter_FD
        for (int i = 0; i < NEXTAFTER_FD_START_CASES.length; i++) {
            final float start = NEXTAFTER_FD_START_CASES[i][0];
            final int nextUpBits = Float
                    .floatToIntBits(NEXTAFTER_FD_START_CASES[i][1]);
            final int resultBits = Float.floatToIntBits(Math.nextUp(start));
            assertEquals("Result should be next up-number.", nextUpBits,
                    resultBits);
        }

        // test for cases with NaN
        assertTrue("The result should be NaN.", Float.isNaN(Math
                .nextUp(Float.NaN)));

        // test for exception
        try {
            Math.nextUp((Float) null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }

	/**
	 * @tests java.lang.Math#pow(double, double)
	 */
	public void test_powDD() {
		// Test for method double java.lang.Math.pow(double, double)
		assertTrue("pow returned incorrect value",
				(long) Math.pow(2, 8) == 256l);
		assertTrue("pow returned incorrect value",
				Math.pow(2, -8) == 0.00390625d);
		assertEquals("Incorrect root returned1",
                             2, Math.sqrt(Math.pow(Math.sqrt(2), 4)), 0);
	}

	/**
	 * @tests java.lang.Math#rint(double)
	 */
	public void test_rintD() {
		// Test for method double java.lang.Math.rint(double)
		assertEquals("Failed to round properly - up to odd",
				3.0, Math.rint(2.9), 0D);
		assertTrue("Failed to round properly - NaN", Double.isNaN(Math
				.rint(Double.NaN)));
		assertEquals("Failed to round properly down  to even",
				2.0, Math.rint(2.1), 0D);
		assertTrue("Failed to round properly " + 2.5 + " to even", Math
				.rint(2.5) == 2.0);
	}

	/**
	 * @tests java.lang.Math#round(double)
	 */
	public void test_roundD() {
		// Test for method long java.lang.Math.round(double)
		assertEquals("Incorrect rounding of a float", -91, Math.round(-90.89d));
	}

	/**
	 * @tests java.lang.Math#round(float)
	 */
	public void test_roundF() {
		// Test for method int java.lang.Math.round(float)
		assertEquals("Incorrect rounding of a float", -91, Math.round(-90.89f));
	}
    
    /**
     * @tests java.lang.Math#signum(double)
     */
    public void test_signum_D() {
        assertTrue(Double.isNaN(Math.signum(Double.NaN)));
        assertTrue(Double.isNaN(Math.signum(Double.NaN)));
        assertEquals(Double.doubleToLongBits(0.0), Double.doubleToLongBits(Math
                .signum(0.0)));
        assertEquals(Double.doubleToLongBits(+0.0), Double
                .doubleToLongBits(Math.signum(+0.0)));
        assertEquals(Double.doubleToLongBits(-0.0), Double
                .doubleToLongBits(Math.signum(-0.0)));

        assertEquals(1.0, Math.signum(253681.2187962), 0D);
        assertEquals(-1.0, Math.signum(-125874693.56), 0D);
        assertEquals(1.0, Math.signum(1.2587E-308), 0D);
        assertEquals(-1.0, Math.signum(-1.2587E-308), 0D);

        assertEquals(1.0, Math.signum(Double.MAX_VALUE), 0D);
        assertEquals(1.0, Math.signum(Double.MIN_VALUE), 0D);
        assertEquals(-1.0, Math.signum(-Double.MAX_VALUE), 0D);
        assertEquals(-1.0, Math.signum(-Double.MIN_VALUE), 0D);
        assertEquals(1.0, Math.signum(Double.POSITIVE_INFINITY), 0D);
        assertEquals(-1.0, Math.signum(Double.NEGATIVE_INFINITY), 0D);
    }

    /**
     * @tests java.lang.Math#signum(float)
     */
    public void test_signum_F() {
        assertTrue(Float.isNaN(Math.signum(Float.NaN)));
        assertEquals(Float.floatToIntBits(0.0f), Float
                .floatToIntBits(Math.signum(0.0f)));
        assertEquals(Float.floatToIntBits(+0.0f), Float
                .floatToIntBits(Math.signum(+0.0f)));
        assertEquals(Float.floatToIntBits(-0.0f), Float
                .floatToIntBits(Math.signum(-0.0f)));

        assertEquals(1.0f, Math.signum(253681.2187962f), 0f);
        assertEquals(-1.0f, Math.signum(-125874693.56f), 0f);
        assertEquals(1.0f, Math.signum(1.2587E-11f), 0f);
        assertEquals(-1.0f, Math.signum(-1.2587E-11f), 0f);

        assertEquals(1.0f, Math.signum(Float.MAX_VALUE), 0f);
        assertEquals(1.0f, Math.signum(Float.MIN_VALUE), 0f);
        assertEquals(-1.0f, Math.signum(-Float.MAX_VALUE), 0f);
        assertEquals(-1.0f, Math.signum(-Float.MIN_VALUE), 0f);
        assertEquals(1.0f, Math.signum(Float.POSITIVE_INFINITY), 0f);
        assertEquals(-1.0f, Math.signum(Float.NEGATIVE_INFINITY), 0f);
    }

	/**
     * @tests java.lang.Math#sin(double)
     */
	public void test_sinD() {
		// Test for method double java.lang.Math.sin(double)
		assertEquals("Incorrect answer", 0.0, Math.sin(0), 0D);
		assertEquals("Incorrect answer", 0.8414709848078965, Math.sin(1), 0D);
	}
    
    /**
     * @tests java.lang.Math#sinh(double)
     */
    public void test_sinh_D() {
        // Test for special situations
        assertTrue("Should return NaN", Double.isNaN(Math.sinh(Double.NaN)));
        assertEquals("Should return POSITIVE_INFINITY",
                Double.POSITIVE_INFINITY, Math.sinh(Double.POSITIVE_INFINITY), 0D);
        assertEquals("Should return NEGATIVE_INFINITY",
                Double.NEGATIVE_INFINITY, Math.sinh(Double.NEGATIVE_INFINITY), 0D);
        assertEquals(Double.doubleToLongBits(0.0), Double.doubleToLongBits(Math
				.sinh(0.0)));
		assertEquals(Double.doubleToLongBits(+0.0), Double
				.doubleToLongBits(Math.sinh(+0.0)));
		assertEquals(Double.doubleToLongBits(-0.0), Double
				.doubleToLongBits(Math.sinh(-0.0)));

        assertEquals("Should return POSITIVE_INFINITY",
                Double.POSITIVE_INFINITY, Math.sinh(1234.56), 0D);
        assertEquals("Should return NEGATIVE_INFINITY",
                Double.NEGATIVE_INFINITY, Math.sinh(-1234.56), 0D);
        assertEquals("Should return 1.0000000000001666E-6",
                1.0000000000001666E-6, Math.sinh(0.000001), 0D);
        assertEquals("Should return -1.0000000000001666E-6",
                -1.0000000000001666E-6, Math.sinh(-0.000001), 0D);
        assertEquals("Should return 5.115386441963859", 5.115386441963859, Math
                .sinh(2.33482), 0D);
        assertEquals("Should return POSITIVE_INFINITY",
                Double.POSITIVE_INFINITY, Math.sinh(Double.MAX_VALUE), 0D);
        assertEquals("Should return 4.9E-324", 4.9E-324, Math
                .sinh(Double.MIN_VALUE), 0D);
    }
    
	/**
	 * @tests java.lang.Math#sqrt(double)
	 */
	public void test_sqrtD() {
		// Test for method double java.lang.Math.sqrt(double)
                assertEquals("Incorrect root returned2", 7, Math.sqrt(49), 0);
	}

	/**
	 * @tests java.lang.Math#tan(double)
	 */
	public void test_tanD() {
		// Test for method double java.lang.Math.tan(double)
		assertEquals("Incorrect answer", 0.0, Math.tan(0), 0D);
		assertEquals("Incorrect answer", 1.5574077246549023, Math.tan(1), 0D);

	}

    /**
     * @tests java.lang.Math#tanh(double)
     */
    public void test_tanh_D() {
        // Test for special situations
        assertTrue("Should return NaN", Double.isNaN(Math.tanh(Double.NaN)));
        assertEquals("Should return +1.0", +1.0, Math
                .tanh(Double.POSITIVE_INFINITY), 0D);
        assertEquals("Should return -1.0", -1.0, Math
                .tanh(Double.NEGATIVE_INFINITY), 0D);
        assertEquals(Double.doubleToLongBits(0.0), Double.doubleToLongBits(Math
				.tanh(0.0)));
		assertEquals(Double.doubleToLongBits(+0.0), Double
				.doubleToLongBits(Math.tanh(+0.0)));
		assertEquals(Double.doubleToLongBits(-0.0), Double
				.doubleToLongBits(Math.tanh(-0.0)));

        assertEquals("Should return 1.0", 1.0, Math.tanh(1234.56), 0D);
        assertEquals("Should return -1.0", -1.0, Math.tanh(-1234.56), 0D);
        assertEquals("Should return 9.999999999996666E-7",
                9.999999999996666E-7, Math.tanh(0.000001), 0D);
        assertEquals("Should return 0.981422884124941", 0.981422884124941, Math
                .tanh(2.33482), 0D);
        assertEquals("Should return 1.0", 1.0, Math.tanh(Double.MAX_VALUE), 0D);
        assertEquals("Should return 4.9E-324", 4.9E-324, Math
                .tanh(Double.MIN_VALUE), 0D);
    }
    
	/**
	 * @tests java.lang.Math#random()
	 */
	public void test_random() {
		// There isn't a place for these tests so just stick them here
		assertEquals("Wrong value E",
				4613303445314885481L, Double.doubleToLongBits(Math.E));
		assertEquals("Wrong value PI",
				4614256656552045848L, Double.doubleToLongBits(Math.PI));

		for (int i = 500; i >= 0; i--) {
			double d = Math.random();
			assertTrue("Generated number is out of range: " + d, d >= 0.0
					&& d < 1.0);
		}
	}

	/**
	 * @tests java.lang.Math#toRadians(double)
	 */
	public void test_toRadiansD() {
		for (double d = 500; d >= 0; d -= 1.0) {
			double converted = Math.toDegrees(Math.toRadians(d));
			assertTrue("Converted number not equal to original. d = " + d,
					converted >= d * 0.99999999 && converted <= d * 1.00000001);
		}
	}

	/**
	 * @tests java.lang.Math#toDegrees(double)
	 */
	public void test_toDegreesD() {
		for (double d = 500; d >= 0; d -= 1.0) {
			double converted = Math.toRadians(Math.toDegrees(d));
			assertTrue("Converted number not equal to original. d = " + d,
					converted >= d * 0.99999999 && converted <= d * 1.00000001);
		}
	}
	
	/**
     * @tests java.lang.Math#ulp(double)
     */
    @SuppressWarnings("boxing")
    public void test_ulp_D() {
		// Test for special cases
		assertTrue("Should return NaN", Double.isNaN(Math.ulp(Double.NaN)));
		assertEquals("Returned incorrect value", Double.POSITIVE_INFINITY, Math
				.ulp(Double.POSITIVE_INFINITY), 0D);
		assertEquals("Returned incorrect value", Double.POSITIVE_INFINITY, Math
				.ulp(Double.NEGATIVE_INFINITY), 0D);
		assertEquals("Returned incorrect value", Double.MIN_VALUE, Math
				.ulp(0.0), 0D);
		assertEquals("Returned incorrect value", Double.MIN_VALUE, Math
				.ulp(+0.0), 0D);
		assertEquals("Returned incorrect value", Double.MIN_VALUE, Math
				.ulp(-0.0), 0D);
		assertEquals("Returned incorrect value", Math.pow(2, 971), Math
				.ulp(Double.MAX_VALUE), 0D);
		assertEquals("Returned incorrect value", Math.pow(2, 971), Math
				.ulp(-Double.MAX_VALUE), 0D);

		assertEquals("Returned incorrect value", Double.MIN_VALUE, Math
				.ulp(Double.MIN_VALUE), 0D);
		assertEquals("Returned incorrect value", Double.MIN_VALUE, Math
				.ulp(-Double.MIN_VALUE), 0D);

		assertEquals("Returned incorrect value", 2.220446049250313E-16, Math
				.ulp(1.0), 0D);
		assertEquals("Returned incorrect value", 2.220446049250313E-16, Math
				.ulp(-1.0), 0D);
		assertEquals("Returned incorrect value", 2.2737367544323206E-13, Math
				.ulp(1153.0), 0D);
	}

	/**
	 * @tests java.lang.Math#ulp(float)
	 */
	@SuppressWarnings("boxing")
	public void test_ulp_f() {
		// Test for special cases
		assertTrue("Should return NaN", Float.isNaN(Math.ulp(Float.NaN)));
		assertEquals("Returned incorrect value", Float.POSITIVE_INFINITY, Math
				.ulp(Float.POSITIVE_INFINITY), 0f);
		assertEquals("Returned incorrect value", Float.POSITIVE_INFINITY, Math
				.ulp(Float.NEGATIVE_INFINITY), 0f);
		assertEquals("Returned incorrect value", Float.MIN_VALUE, Math
				.ulp(0.0f), 0f);
		assertEquals("Returned incorrect value", Float.MIN_VALUE, Math
				.ulp(+0.0f), 0f);
		assertEquals("Returned incorrect value", Float.MIN_VALUE, Math
				.ulp(-0.0f), 0f);
		assertEquals("Returned incorrect value", 2.028241E31f, Math
				.ulp(Float.MAX_VALUE), 0f);
		assertEquals("Returned incorrect value", 2.028241E31f, Math
				.ulp(-Float.MAX_VALUE), 0f);

		assertEquals("Returned incorrect value", 1.4E-45f, Math
				.ulp(Float.MIN_VALUE), 0f);
		assertEquals("Returned incorrect value", 1.4E-45f, Math
				.ulp(-Float.MIN_VALUE), 0f);

		assertEquals("Returned incorrect value", 1.1920929E-7f, Math.ulp(1.0f),
				0f);
		assertEquals("Returned incorrect value", 1.1920929E-7f,
				Math.ulp(-1.0f), 0f);
		assertEquals("Returned incorrect value", 1.2207031E-4f, Math
				.ulp(1153.0f), 0f);
		assertEquals("Returned incorrect value", 5.6E-45f, Math
				.ulp(9.403954E-38f), 0f);
    }
}
