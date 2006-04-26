/* Copyright 1998, 2005 The Apache Software Foundation or its licensors, as applicable
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

public class StrictMathTest extends junit.framework.TestCase {

	double HYP = StrictMath.sqrt(2.0);

	double OPP = 1.0;

	double ADJ = 1.0;

	/* Required to make previous preprocessor flags work - do not remove */
	int unused = 0;

	/**
	 * @tests java.lang.StrictMath#abs(double)
	 */
	public void test_absD() {
		// Test for method double java.lang.StrictMath.abs(double)

		assertTrue("Incorrect double abs value",
				(StrictMath.abs(-1908.8976) == 1908.8976));
		assertTrue("Incorrect double abs value",
				(StrictMath.abs(1908.8976) == 1908.8976));
	}

	/**
	 * @tests java.lang.StrictMath#abs(float)
	 */
	public void test_absF() {
		// Test for method float java.lang.StrictMath.abs(float)
		assertTrue("Incorrect float abs value",
				(StrictMath.abs(-1908.8976f) == 1908.8976f));
		assertTrue("Incorrect float abs value",
				(StrictMath.abs(1908.8976f) == 1908.8976f));
	}

	/**
	 * @tests java.lang.StrictMath#abs(int)
	 */
	public void test_absI() {
		// Test for method int java.lang.StrictMath.abs(int)
		assertTrue("Incorrect int abs value",
				(StrictMath.abs(-1908897) == 1908897));
		assertTrue("Incorrect int abs value",
				(StrictMath.abs(1908897) == 1908897));
	}

	/**
	 * @tests java.lang.StrictMath#abs(long)
	 */
	public void test_absJ() {
		// Test for method long java.lang.StrictMath.abs(long)
		assertTrue("Incorrect long abs value", (StrictMath
				.abs(-19088976000089L) == 19088976000089L));
		assertTrue("Incorrect long abs value",
				(StrictMath.abs(19088976000089L) == 19088976000089L));
	}

	/**
	 * @tests java.lang.StrictMath#acos(double)
	 */
	public void test_acosD() {
		// Test for method double java.lang.StrictMath.acos(double)
		assertTrue("Returned incorrect arc cosine", StrictMath.cos(StrictMath
				.acos(ADJ / HYP)) == ADJ / HYP);
	}

	/**
	 * @tests java.lang.StrictMath#asin(double)
	 */
	public void test_asinD() {
		// Test for method double java.lang.StrictMath.asin(double)
		assertTrue("Returned incorrect arc sine", StrictMath.sin(StrictMath
				.asin(OPP / HYP)) == OPP / HYP);
	}

	/**
	 * @tests java.lang.StrictMath#atan(double)
	 */
	public void test_atanD() {
		// Test for method double java.lang.StrictMath.atan(double)
		double answer = StrictMath.tan(StrictMath.atan(1.0));
		assertTrue("Returned incorrect arc tangent: " + answer, answer <= 1.0
				&& answer >= 9.9999999999999983E-1);
	}

	/**
	 * @tests java.lang.StrictMath#atan2(double, double)
	 */
	public void test_atan2DD() {
		// Test for method double java.lang.StrictMath.atan2(double, double)
		double answer = StrictMath.atan(StrictMath.tan(1.0));
		assertTrue("Returned incorrect arc tangent: " + answer, answer <= 1.0
				&& answer >= 9.9999999999999983E-1);
	}

	/**
	 * @tests java.lang.StrictMath#ceil(double)
	 */
	public void test_ceilD() {
		// Test for method double java.lang.StrictMath.ceil(double)
                assertEquals("Incorrect ceiling for double",
                             79, StrictMath.ceil(78.89), 0.0);
		assertEquals("Incorrect ceiling for double",
                             -78, StrictMath.ceil(-78.89), 0.0);
	}

	/**
	 * @tests java.lang.StrictMath#cos(double)
	 */
	public void test_cosD() {
		// Test for method double java.lang.StrictMath.cos(double)

		assertTrue("Returned incorrect cosine", StrictMath.cos(StrictMath
				.acos(ADJ / HYP)) == ADJ / HYP);
	}

	/**
	 * @tests java.lang.StrictMath#exp(double)
	 */
	public void test_expD() {
		// Test for method double java.lang.StrictMath.exp(double)
		assertTrue("Incorrect answer returned for simple power", StrictMath
				.abs(StrictMath.exp(4D) - StrictMath.E * StrictMath.E
						* StrictMath.E * StrictMath.E) < 0.1D);
		assertTrue("Incorrect answer returned for larger power", StrictMath
				.log(StrictMath.abs(StrictMath.exp(5.5D)) - 5.5D) < 10.0D);
	}

	/**
	 * @tests java.lang.StrictMath#floor(double)
	 */
	public void test_floorD() {
		// Test for method double java.lang.StrictMath.floor(double)
                assertEquals("Incorrect floor for double",
                             78, StrictMath.floor(78.89), 0.0);
		assertEquals("Incorrect floor for double",
                             -79, StrictMath.floor(-78.89), 0.0);
	}

	/**
	 * @tests java.lang.StrictMath#IEEEremainder(double, double)
	 */
	public void test_IEEEremainderDD() {
		// Test for method double java.lang.StrictMath.IEEEremainder(double,
		// double)
		assertEquals("Incorrect remainder returned", 0.0, StrictMath.IEEEremainder(
				1.0, 1.0));
		assertTrue(
				"Incorrect remainder returned",
				StrictMath.IEEEremainder(1.32, 89.765) >= 1.4705063220631647E-2
						|| StrictMath.IEEEremainder(1.32, 89.765) >= 1.4705063220631649E-2);
	}

	/**
	 * @tests java.lang.StrictMath#log(double)
	 */
	public void test_logD() {
		// Test for method double java.lang.StrictMath.log(double)
		for (double d = 10; d >= -10; d -= 0.5) {
			double answer = StrictMath.log(StrictMath.exp(d));
			assertTrue("Answer does not equal expected answer for d = " + d
					+ " answer = " + answer,
					StrictMath.abs(answer - d) <= StrictMath
							.abs(d * 0.00000001));
		}
	}

	/**
	 * @tests java.lang.StrictMath#max(double, double)
	 */
	public void test_maxDD() {
		// Test for method double java.lang.StrictMath.max(double, double)
		assertEquals("Incorrect double max value", 1908897.6000089, StrictMath.max(
				-1908897.6000089, 1908897.6000089));
		assertEquals("Incorrect double max value", 1908897.6000089, StrictMath.max(2.0,
				1908897.6000089));
		assertEquals("Incorrect double max value", -2.0, StrictMath.max(-2.0,
				-1908897.6000089));

	}

	/**
	 * @tests java.lang.StrictMath#max(float, float)
	 */
	public void test_maxFF() {
		// Test for method float java.lang.StrictMath.max(float, float)
		assertTrue("Incorrect float max value", StrictMath.max(-1908897.600f,
				1908897.600f) == 1908897.600f);
		assertTrue("Incorrect float max value", StrictMath.max(2.0f,
				1908897.600f) == 1908897.600f);
		assertTrue("Incorrect float max value", StrictMath.max(-2.0f,
				-1908897.600f) == -2.0f);
	}

	/**
	 * @tests java.lang.StrictMath#max(int, int)
	 */
	public void test_maxII() {
		// Test for method int java.lang.StrictMath.max(int, int)
		assertEquals("Incorrect int max value", 19088976, StrictMath.max(-19088976,
				19088976));
		assertEquals("Incorrect int max value",
				19088976, StrictMath.max(20, 19088976));
		assertEquals("Incorrect int max value",
				-20, StrictMath.max(-20, -19088976));
	}

	/**
	 * @tests java.lang.StrictMath#max(long, long)
	 */
	public void test_maxJJ() {
		// Test for method long java.lang.StrictMath.max(long, long)
		assertEquals("Incorrect long max value", 19088976000089L, StrictMath.max(-19088976000089L,
				19088976000089L));
		assertEquals("Incorrect long max value", 19088976000089L, StrictMath.max(20,
				19088976000089L));
		assertEquals("Incorrect long max value", -20, StrictMath.max(-20,
				-19088976000089L));
	}

	/**
	 * @tests java.lang.StrictMath#min(double, double)
	 */
	public void test_minDD() {
		// Test for method double java.lang.StrictMath.min(double, double)
		assertEquals("Incorrect double min value", -1908897.6000089, StrictMath.min(
				-1908897.6000089, 1908897.6000089));
		assertEquals("Incorrect double min value", 2.0, StrictMath.min(2.0,
				1908897.6000089));
		assertEquals("Incorrect double min value", -1908897.6000089, StrictMath.min(-2.0,
				-1908897.6000089));
	}

	/**
	 * @tests java.lang.StrictMath#min(float, float)
	 */
	public void test_minFF() {
		// Test for method float java.lang.StrictMath.min(float, float)
		assertTrue("Incorrect float min value", StrictMath.min(-1908897.600f,
				1908897.600f) == -1908897.600f);
		assertTrue("Incorrect float min value", StrictMath.min(2.0f,
				1908897.600f) == 2.0f);
		assertTrue("Incorrect float min value", StrictMath.min(-2.0f,
				-1908897.600f) == -1908897.600f);
	}

	/**
	 * @tests java.lang.StrictMath#min(int, int)
	 */
	public void test_minII() {
		// Test for method int java.lang.StrictMath.min(int, int)
		assertEquals("Incorrect int min value", -19088976, StrictMath.min(-19088976,
				19088976));
		assertEquals("Incorrect int min value",
				20, StrictMath.min(20, 19088976));
		assertEquals("Incorrect int min value",
				-19088976, StrictMath.min(-20, -19088976));

	}

	/**
	 * @tests java.lang.StrictMath#min(long, long)
	 */
	public void test_minJJ() {
		// Test for method long java.lang.StrictMath.min(long, long)
		assertEquals("Incorrect long min value", -19088976000089L, StrictMath.min(-19088976000089L,
				19088976000089L));
		assertEquals("Incorrect long min value", 20, StrictMath.min(20,
				19088976000089L));
		assertEquals("Incorrect long min value", -19088976000089L, StrictMath.min(-20,
				-19088976000089L));
	}

	/**
	 * @tests java.lang.StrictMath#pow(double, double)
	 */
	public void test_powDD() {
		// Test for method double java.lang.StrictMath.pow(double, double)
		assertTrue("pow returned incorrect value",
				(long) StrictMath.pow(2, 8) == 256l);
		assertTrue("pow returned incorrect value",
				StrictMath.pow(2, -8) == 0.00390625d);
	}

	/**
	 * @tests java.lang.StrictMath#rint(double)
	 */
	public void test_rintD() {
		// Test for method double java.lang.StrictMath.rint(double)
		assertEquals("Failed to round properly - up to odd",
				3.0, StrictMath.rint(2.9));
		assertTrue("Failed to round properly - NaN", Double.isNaN(StrictMath
				.rint(Double.NaN)));
		assertEquals("Failed to round properly down  to even", 2.0, StrictMath
				.rint(2.1));
		assertTrue("Failed to round properly " + 2.5 + " to even", StrictMath
				.rint(2.5) == 2.0);
	}

	/**
	 * @tests java.lang.StrictMath#round(double)
	 */
	public void test_roundD() {
		// Test for method long java.lang.StrictMath.round(double)
		assertEquals("Incorrect rounding of a float",
				-91, StrictMath.round(-90.89d));
	}

	/**
	 * @tests java.lang.StrictMath#round(float)
	 */
	public void test_roundF() {
		// Test for method int java.lang.StrictMath.round(float)
		assertEquals("Incorrect rounding of a float",
				-91, StrictMath.round(-90.89f));
	}

	/**
	 * @tests java.lang.StrictMath#sin(double)
	 */
	public void test_sinD() {
		// Test for method double java.lang.StrictMath.sin(double)
		assertTrue("Returned incorrect sine", StrictMath.sin(StrictMath
				.asin(OPP / HYP)) == OPP / HYP);
	}

	/**
	 * @tests java.lang.StrictMath#sqrt(double)
	 */
	public void test_sqrtD() {
		// Test for method double java.lang.StrictMath.sqrt(double)
		assertEquals("Incorrect root returned1",
                             2, StrictMath.sqrt(StrictMath.pow(StrictMath.sqrt(2), 4)), 0.0);
		assertEquals("Incorrect root returned2", 7, StrictMath.sqrt(49), 0.0);
	}

	/**
	 * @tests java.lang.StrictMath#tan(double)
	 */
	public void test_tanD() {
		// Test for method double java.lang.StrictMath.tan(double)
		assertTrue(
				"Returned incorrect tangent: ",
				StrictMath.tan(StrictMath.atan(1.0)) <= 1.0
						|| StrictMath.tan(StrictMath.atan(1.0)) >= 9.9999999999999983E-1);
	}

	/**
	 * @tests java.lang.StrictMath#random()
	 */
	public void test_random() {
		// There isn't a place for these tests so just stick them here
		assertEquals("Wrong value E",
				4613303445314885481L, Double.doubleToLongBits(StrictMath.E));
		assertEquals("Wrong value PI",
				4614256656552045848L, Double.doubleToLongBits(StrictMath.PI));

		for (int i = 500; i >= 0; i--) {
			double d = StrictMath.random();
			assertTrue("Generated number is out of range: " + d, d >= 0.0
					&& d < 1.0);
		}
	}

	/**
	 * @tests java.lang.StrictMath#toRadians(double)
	 */
	public void test_toRadiansD() {
		for (double d = 500; d >= 0; d -= 1.0) {
			double converted = StrictMath.toDegrees(StrictMath.toRadians(d));
			assertTrue("Converted number not equal to original. d = " + d,
					converted >= d * 0.99999999 && converted <= d * 1.00000001);
		}
	}

	/**
	 * @tests java.lang.StrictMath#toDegrees(double)
	 */
	public void test_toDegreesD() {
		for (double d = 500; d >= 0; d -= 1.0) {
			double converted = StrictMath.toRadians(StrictMath.toDegrees(d));
			assertTrue("Converted number not equal to original. d = " + d,
					converted >= d * 0.99999999 && converted <= d * 1.00000001);
		}
	}

	/**
	 * Sets up the fixture, for example, open a network connection. This method
	 * is called before a test is executed.
	 */
	protected void setUp() {
	}

	/**
	 * Tears down the fixture, for example, close a network connection. This
	 * method is called after a test is executed.
	 */
	protected void tearDown() {
	}
}
